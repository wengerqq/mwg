<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="产品SKU">
        <el-input
          v-model="queryParams.params.productsSku"
          placeholder="请输入产品SKU"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="仓库" prop="warehouseId">
        <el-select
          v-model="queryParams.warehouseId"
          placeholder="请选择仓库"
          clearable
          size="small"
          style="width: 240px"
        >
          <el-option
            v-for="(warehouse,index) in warehouseOptions"
            :key="warehouse.id"
            :label="warehouse.warehouseName"
            :value="warehouse.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="部门">
        <treeselect v-model="queryParams.params.deptId" :options="deptOptions" :show-count="true" clearable size="small" style="width: 240px" placeholder="请选择归属部门" />
      </el-form-item>
      <el-form-item label="操作人" prop="operatorId">
        <el-select
          v-model="queryParams.operatorId"
          placeholder="请选择操作人"
          clearable
          size="small"
          style="width: 240px"
        >
          <el-option
            v-for="(user,index) in userOptions"
            :key="user.userId"
            :label="user.nickName"
            :value="user.userId"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="创建时间" prop="createTime">
        <el-date-picker clearable size="small"
          v-model="queryParams.createTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="更新时间" prop="updateTime">
        <el-date-picker clearable size="small"
          v-model="queryParams.updateTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择更新时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['warehouse:stock_check:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="stock_checkList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="库存ID" align="center" prop="id" />-->
      <el-table-column label="产品信息" align="center" width="200">
        <template slot-scope="scope">
          <span>
            产品SKU：{{ scope.row.products.sku }}<br/>
            产品名称：{{ scope.row.products.productName }}<br/>
            销售状态：{{ statusArray[scope.row.products.productStatus]?statusArray[scope.row.products.productStatus].statusName:scope.row.products.productStatus }}<br/>
          </span>
        </template>
      </el-table-column>
      <el-table-column label="仓库名称" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.warehouse.warehouseName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="采购在途" align="center" prop="procurementInTransit" />
      <el-table-column label="退件在途" align="center" prop="returnedPartsInTransit" />
      <el-table-column label="待上架" align="center" prop="readyToGoOnTheShelf" />
      <el-table-column label="可用数量" align="center" prop="availableQuantity" />
      <el-table-column label="可售数量" align="center" prop="saleableQuantity" />
      <el-table-column label="待出库" align="center" prop="toBeDelivered" />
      <el-table-column label="操作人" align="center" prop="operatorId">
        <template slot-scope="scope">
          <span>{{ userArray[scope.row.operatorId]?userArray[scope.row.operatorId].nickName:scope.row.operatorId }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新时间" align="center" prop="updateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
  </div>
</template>

<script>
import { listStock_check, getStock_check } from "@/api/warehouse/stock_check";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import {listSales_status} from "@/api/rchives/sales_status";
import {listDeptUser} from "@/api/system/user";
import {listDept, treeselectDept} from "@/api/system/dept";
import {listWarehouse_settings} from "@/api/warehouse/warehouse_settings";

export default {
  name: "Stock_check",
  components: { Treeselect },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 库存查询表格数据
      stock_checkList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 部门树选项
      deptOptions: undefined,
      //产品销售状态下拉
      statusOptions: undefined,
      //操作员下拉
      userOptions: undefined,
      //仓库下拉
      warehouseOptions: undefined,
      //产品销售状态数组
      statusArray: [],
      //操作员数组
      userArray: [],
      //仓库数组
      warehouseArray: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        productsId: null,
        warehouseId: null,
        procurementInTransit: null,
        returnedPartsInTransit: null,
        readyToGoOnTheShelf: null,
        availableQuantity: null,
        saleableQuantity: null,
        toBeDelivered: null,
        operatorId: null,
        createTime: null,
        updateTime: null,
        isQuery:false,
        params:{
          productsSku: null,
          deptId:null,
        }
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getStatusList();
    this.getDeptUserList();
    this.getTreeselectDept();
    this.getDeptWarehouseList();
    this.getList();
  },
  methods: {
    /** 查询库存查询列表 */
    getList() {
      this.loading = true;
      listStock_check(this.queryParams).then(response => {
        this.stock_checkList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        productsId: null,
        warehouseId: null,
        procurementInTransit: null,
        returnedPartsInTransit: null,
        readyToGoOnTheShelf: null,
        availableQuantity: null,
        saleableQuantity: null,
        toBeDelivered: null,
        operatorId: null,
        createTime: null,
        updateTime: null,
        deptId:null,
        isQuery:false,
        params:{
          productsSku: null,
          deptId:null,
        }
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery(isReset = 0) {
      this.queryParams.isQuery = isReset===1?false:true;
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.queryParams.params.productsSku = null;
      this.queryParams.params.deptId = null;
      this.handleQuery(1);
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },

    /** 导出按钮操作 */
    handleExport() {
      this.download('warehouse/stock_check/export', {
        ...this.queryParams
      }, `stock_check_${new Date().getTime()}.xlsx`)
    },

    /** 查询产品销售状态列表 */
    getStatusList(){
      listSales_status().then(response => {
        for (let i=0; i<response.rows.length; i++){
          this.statusArray[response.rows[i]['id']] = response.rows[i];
        }
        this.statusOptions = response.rows;
      });
    },
    /** 查询部门操作员下拉列表 */
    getDeptUserList(){
      listDeptUser().then(response => {
        for (let i=0; i<response.rows.length; i++){
          this.userArray[response.rows[i]['userId']] = response.rows[i];
        }
        this.userOptions = response.rows;
      });
    },
    /** 查询部门下拉树结构 */
    getTreeselectDept() {
      treeselectDept().then(response => {
        this.deptOptions = response.data;
      });
    },
    /** 查询部门仓库下拉列表 */
    getDeptWarehouseList(){
      listWarehouse_settings().then(response => {
        for (let i=0; i<response.rows.length; i++){
          this.warehouseArray[response.rows[i]['id']] = response.rows[i];
        }
        this.warehouseOptions = response.rows;
      });
    },
  }
};
</script>
