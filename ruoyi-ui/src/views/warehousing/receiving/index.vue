<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="采购单号" prop="purchaseId">
        <el-input
          v-model="queryParams.purchaseId"
          placeholder="请输入采购单号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="入库单号" prop="receiptId">
        <el-input
          v-model="queryParams.receiptId"
          placeholder="请输入入库单号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="跟踪单号" prop="trackingNumber">
        <el-input
          v-model="queryParams.trackingNumber"
          placeholder="请输入跟踪单号"
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
          style="width: 200px"
        >
          <el-option
            v-for="(warehouse,index) in warehouseOptions"
            :key="warehouse.id"
            :label="warehouse.warehouseName"
            :value="warehouse.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select
          v-model="queryParams.status"
          placeholder="请选择状态"
          clearable
          size="small"
          style="width: 240px"
        >
          <el-option
            v-for="dict in dict.type.sys_purchase_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="部门" prop="deptId">
        <treeselect v-model="queryParams.deptId" :options="deptOptions" :show-count="true" clearable size="small" style="width: 240px" placeholder="请选择部门" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="primary"-->
<!--          plain-->
<!--          icon="el-icon-plus"-->
<!--          size="mini"-->
<!--          @click="handleAdd"-->
<!--          v-hasPermi="['warehousing:receiving:add']"-->
<!--        >新增</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="success"-->
<!--          plain-->
<!--          icon="el-icon-edit"-->
<!--          size="mini"-->
<!--          :disabled="single"-->
<!--          @click="handleUpdate"-->
<!--          v-hasPermi="['warehousing:receiving:edit']"-->
<!--        >修改</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="warning"-->
<!--          plain-->
<!--          icon="el-icon-download"-->
<!--          size="mini"-->
<!--          @click="handleExport"-->
<!--          v-hasPermi="['warehousing:receiving:export']"-->
<!--        >导出</el-button>-->
<!--      </el-col>-->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="receivingList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="采购单号/跟踪号/入库单号" align="center" prop="purchaseId">
        <template slot-scope="scope">
          <span>
            采购单号：{{ scope.row.purchaseId }}<br/>
            跟踪号：{{ scope.row.trackingNumber }}<br/>
            入库单号：{{ scope.row.receiptId }}<br/>
          </span>
        </template>
      </el-table-column>
      <el-table-column label="仓库ID" align="center">
        <template slot-scope="scope">
          <span>{{ warehouseArray[scope.row.warehouseId]?warehouseArray[scope.row.warehouseId].warehouseName:scope.row.warehouseId }}</span>
        </template>
      </el-table-column>
      <el-table-column label="SKU入库情况" align="center">
        <template slot-scope="scope">
          <span>
            预期数：{{scope.row.params.quantity}}<br/>
            已收数：{{scope.row.params.quantityReceived}}<br/>
            未收：{{scope.row.params.uncollected}}<br/>
            待上架：{{scope.row.params.put}}<br/>
          </span>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center">
        <template slot-scope="scope">
          {{dict.type.sys_purchase_status[scope.row.status]?dict.type.sys_purchase_status[scope.row.status].label:scope.row.status}}
        </template>
      </el-table-column>
      <el-table-column label="日期" align="center" prop="estimatedArrivalTime" width="180">
        <template slot-scope="scope">
          <span>
            添加时间：{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}<br/>
            更新时间：{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}<br/>
            预计到货：{{ parseTime(scope.row.estimatedArrivalTime, '{y}-{m}-{d}') }}<br/>
          </span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            @click="receivingInfo(scope.row)"
            v-hasPermi="['warehousing:receiving:getInfo']"
          >收货</el-button>
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

    <!-- 收货对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="80%" append-to-body>
      <el-row>
        <el-col :span="24">
          <el-row style="padding-bottom: 30px;">
            <el-col :span="4">
              采购单：{{this.form.purchaseId}}
            </el-col>
            <el-col :span="4">
              入库单号：{{this.form.receiptId}}
            </el-col>
            <el-col :span="4">
              跟踪号：{{this.form.trackingNumber}}
            </el-col>
            <el-col :span="4">
              供应商：{{ supplierArray[this.form.supplierId]?supplierArray[this.form.supplierId].supplierName:this.form.supplierId }}
            </el-col>
            <el-col :span="4">
              收货仓：{{ warehouseArray[this.form.warehouseId]?warehouseArray[this.form.warehouseId].warehouseName:this.form.warehouseId }}
            </el-col>
            <el-col :span="4">
              采购员：{{ userArray[this.form.buyerId]?userArray[this.form.buyerId].nickName:this.form.buyerId }}
            </el-col>
          </el-row>
          <el-row style="padding-bottom: 30px;">
            <el-col :span="24" style="text-align: right;">
              <el-button type="warning" @click="receiving(false)">确认收货</el-button>
              <el-button type="success" @click="receiving(true)">收货并上架</el-button>
<!--              <el-button type="info">未到货</el-button>-->
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="24">
              <el-table v-loading="loading" :data="receivingProductList" @selection-change="handleSelectionChangeReceiving">
                <el-table-column type="selection" width="55" align="center" />
                <el-table-column label="产品SKU" align="center" prop="productSku"/>
                <el-table-column label="产品名称" align="center" prop="productName"/>
                <el-table-column label="状态" align="center">
                  <template slot-scope="scope">
                    {{dict.type.sys_receiving_status[scope.row.status]?dict.type.sys_receiving_status[scope.row.status].label:scope.row.status}}
                  </template>
                </el-table-column>
                <el-table-column label="预期数" align="center" prop="quantity"/>
                <el-table-column label="已收数" align="center" prop="quantityReceived"/>
                <el-table-column label="未收数" align="center">
                  <template slot-scope="scope">
                    <div style="display: flex;align-items: center;"><el-input @blur="isLarge(scope.row)" v-model="scope.row.params.uncollected" /></div>
                  </template>
                </el-table-column>
                <el-table-column label="备注" align="center">
                  <template slot-scope="scope">
                    <div style="display: flex;align-items: center;"><el-input @blur="" v-model="scope.row.remark" /></div>
                  </template>
                </el-table-column>
<!--                <el-table-column label="操作" align="center" class-name="small-padding fixed-width">-->
<!--                  <template slot-scope="scope">-->
<!--                    <el-button-->
<!--                      size="mini"-->
<!--                      type="text"-->
<!--                      @click="undelivered(scope.row)"-->
<!--                      v-hasPermi="['warehousing:receiving:undelivered']"-->
<!--                    >未到货</el-button>-->
<!--                  </template>-->
<!--                </el-table-column>-->
              </el-table>
            </el-col>
          </el-row>
        </el-col>
      </el-row>
    </el-dialog>
  </div>
</template>

<script>
import { listReceiving, getReceiving, delReceiving, addReceiving, updateReceiving, undelivered, receiving } from "@/api/warehousing/receiving";
import {listSupplier} from "@/api/rchives/supplier";
import {listWarehouse_settings} from "@/api/warehouse/warehouse_settings";
import {listDeptUser} from "@/api/system/user";
import {listDept, treeselectDept} from "@/api/system/dept";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "Receiving",
  components: { Treeselect },
  dicts: ['sys_purchase_status','sys_receiving_status'],
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
      // 收货管理表格数据
      receivingList: [],
      // 收货产品表格数据
      receivingProductList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      /*********************************数据源-start**************************************/
      //仓库下拉
      warehouseOptions: undefined,
      //仓库数组
      warehouseArray: [],
      //供应商数组
      supplierArray: [],
      //默认采购员数组
      userArray: [],
      // 部门树选项
      deptOptions: undefined,
      //部门列表下拉
      deptListOptions: undefined,
      //部门列表数组
      deptListArray: [],
      /*********************************数据集-end**************************************/
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        purchaseId: null,
        receiptId: null,
        warehouseId: null,
        transferOrNot: null,
        supplierId: null,
        buyerId: null,
        status: null,
        trackingNumber: null,
        settlementMethod: null,
        currency: null,
        paymentMethod: null,
        collectionMethod: null,
        paymentCycleType: null,
        supplierShippingType: null,
        freight: null,
        estimatedArrivalTime: null,
        deptId: null,
        remarks: null,
        operatorId: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      //-----------------------选中产品 start------------------------------
      //选中产品
      temporarySelect:[],
      // 选中数组
      selectProductsIds: [],
      // 非单个禁用
      selectProductsSingle: true,
      // 非多个禁用
      selectProductsMultiple: true,
      // 产品列表表格数据
      selectProductsList: null,
      //-----------------------选中产品 end------------------------------
    };
  },
  created() {
    this.getSupplierList();
    this.getDeptWarehouseList();
    this.getDeptUserList();
    this.getTreeselectDept();
    this.getDeptList();
    this.getList();
  },
  methods: {
    /** 查询收货管理列表 */
    getList() {
      this.loading = true;
      listReceiving(this.queryParams).then(response => {
        this.receivingList = response.rows;
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
        purchaseId: null,
        receiptId: null,
        warehouseId: null,
        transferOrNot: null,
        supplierId: null,
        buyerId: null,
        status: 0,
        trackingNumber: null,
        settlementMethod: null,
        currency: null,
        paymentMethod: null,
        collectionMethod: null,
        paymentCycleType: null,
        supplierShippingType: null,
        freight: null,
        estimatedArrivalTime: null,
        deptId: null,
        remarks: null,
        operatorId: null,
        createTime: null,
        updateTime: null,
        finalMark: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    handleSelectionChangeReceiving(selection){
      this.temporarySelect = selection
      this.selectProductsIds = selection.map(item => item.id)
      this.selectProductsSingle = selection.length!==1
      this.selectProductsMultiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加收货管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getReceiving(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改收货管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateReceiving(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addReceiving(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除收货管理编号为"' + ids + '"的数据项？').then(function() {
        return delReceiving(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('warehousing/receiving/export', {
        ...this.queryParams
      }, `receiving_${new Date().getTime()}.xlsx`)
    },

    /** 查询供应商列表 */
    getSupplierList(){
      listSupplier().then(response => {
        for (let i=0; i<response.rows.length; i++){
          this.supplierArray[response.rows[i]['id']] = response.rows[i];
        }
        //this.supplierOptions = response.rows;
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
    /** 查询部门采购员下拉列表 */
    getDeptUserList(){
      listDeptUser().then(response => {
        for (let i=0; i<response.rows.length; i++){
          this.userArray[response.rows[i]['userId']] = response.rows[i];
        }
        //this.userOptions = response.rows;
      });
    },

    //收货详情
    receivingInfo(row){
      const id = row.id || this.ids
      getReceiving(id).then(response => {
        this.form = response.data;
        this.receivingProductList = this.form.params.receivingProductList;
        this.open = true;
        this.title = "收货详情";
      });
    },

    //未到货
    undelivered(row){
      const id = row.id || this.ids
      undelivered(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "收货详情";
      });
    },

    //判断总收数是否大于预期数
    isLarge(row){
      let uncollected = row.params.uncollected;
      let quantityReceived = row.quantityReceived;
      if(+uncollected + +quantityReceived>row.quantity){
        this.$modal.msgWarning("总收数不能大于预期数");
      }
    },

    //收货
    receiving(isPut){
      if(this.selectProductsIds.length>0){
        let val = this.temporarySelect;
        let count = 0;
        if(val){
          val.forEach((val,index)=>{
            let uncollected = val.params.uncollected;
            let quantityReceived = val.quantityReceived;
            if(+uncollected + +quantityReceived>val.quantity){
              count += 1;
            }
            val.params.isPut = isPut;
          })
          if(count>0){
            this.$modal.msgWarning("总收数不能大于预期数");
            return false;
          }
          receiving(val).then(response => {
            this.$modal.msgSuccess("操作成功");
            this.open = false;
            this.getList();
          });
        }
      }else {
        this.$modal.msgWarning("请选择收货产品");
      }
    },

    /** 部门下拉列表 */
    getDeptList(){
      listDept().then(response => {
        for (let i=0; i<response.data.length; i++){
          this.deptListArray[response.data[i]['deptId']] = response.data[i];
        }
        this.deptListOptions = response.data;
      });
    },
    /** 查询部门下拉树结构 */
    getTreeselectDept() {
      treeselectDept().then(response => {
        this.deptOptions = response.data;
      });
    },
  }
};
</script>
