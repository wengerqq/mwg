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
      <el-form-item label="上架单号" prop="putId">
        <el-input
          v-model="queryParams.putId"
          placeholder="请输入跟踪单号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="产品SKU" prop="productSku">
        <el-input
          v-model="queryParams.productSku"
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
            v-for="dict in dict.type.sys_put_status"
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
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['warehousing:put:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['warehousing:put:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['warehousing:put:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['warehousing:put:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="putList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="采购单ID" width="250" align="center">
        <template slot-scope="scope">
          <span>
            上架单：{{purchaseArray[scope.row.purchaseKeyId].putId}}<br/>
            入库单：{{purchaseArray[scope.row.purchaseKeyId].receiptId}}<br/>
            采购单：{{purchaseArray[scope.row.purchaseKeyId].purchaseId}}<br/>
          </span>
        </template>
      </el-table-column>
      <el-table-column label="产品信息" width="250" align="center">
        <template slot-scope="scope">
          <span>
            产品SKU：{{scope.row.productSku}}<br/>
            产品名称：{{scope.row.productName}}<br/>
          </span>
        </template>
      </el-table-column>
      <el-table-column label="数量" align="center" prop="quantity" />
      <el-table-column label="仓库" align="center">
        <template slot-scope="scope">
          <span>{{ warehouseArray[purchaseArray[scope.row.purchaseKeyId].warehouseId]?warehouseArray[purchaseArray[scope.row.purchaseKeyId].warehouseId].warehouseName:purchaseArray[scope.row.purchaseKeyId].warehouseId }}</span>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center">
        <template slot-scope="scope">
          {{dict.type.sys_put_status[scope.row.status]?dict.type.sys_put_status[scope.row.status].label:scope.row.status}}
        </template>
      </el-table-column>
      <el-table-column label="操作员" align="center">
        <template slot-scope="scope">
          <span>{{ userArray[scope.row.operatorId]?userArray[scope.row.operatorId].nickName:scope.row.operatorId }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="上架时间" align="center" prop="updateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope" v-if="scope.row.status==0">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['warehousing:put:edit']"
          >上架</el-button>
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

    <!-- 添加或修改上架管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="70%" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-table v-loading="loading" ref="multipleTable" :data="selectProductsList" @selection-change="handleSelectionChangeProducts">
          <el-table-column type="selection" width="55" align="center" />
          <el-table-column label="采购单ID" width="250" align="center">
            <template slot-scope="scope">
          <span>
            上架单：{{purchaseArray[scope.row.purchaseKeyId].putId}}<br/>
            入库单：{{purchaseArray[scope.row.purchaseKeyId].receiptId}}<br/>
            采购单：{{purchaseArray[scope.row.purchaseKeyId].purchaseId}}<br/>
          </span>
            </template>
          </el-table-column>
          <el-table-column label="产品信息" width="250" align="center">
            <template slot-scope="scope">
          <span>
            产品SKU：{{scope.row.productSku}}<br/>
            产品名称：{{scope.row.productName}}<br/>
          </span>
            </template>
          </el-table-column>
          <el-table-column label="上架数" align="center" prop="quantity" />
          <el-table-column label="实际上架数" align="center">
            <template slot-scope="scope">
              <div style="display: flex;align-items: center;"><el-input @blur="getDetailTotal" v-model="scope.row.params.actualPut"/></div>
            </template>
          </el-table-column>
          <el-table-column label="仓库" align="center">
            <template slot-scope="scope">
              <span>{{ warehouseArray[purchaseArray[scope.row.purchaseKeyId].warehouseId]?warehouseArray[purchaseArray[scope.row.purchaseKeyId].warehouseId].warehouseName:purchaseArray[scope.row.purchaseKeyId].warehouseId }}</span>
            </template>
          </el-table-column>
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope" v-if="scope.row.status==0">
              <el-button
                size="mini"
                type="text"
                @click="okPut(scope.row,true)"
                v-hasPermi="['warehousing:put:edit']"
              >确认上架</el-button>
              <br/>
              <el-button
                style="color:gray;"
                size="mini"
                type="text"
                @click="cancelPut(scope.row,true)"
                v-hasPermi="['warehousing:put:edit']"
              >取消上架</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer" style="text-align: center;">
        <el-button type="primary" @click="okPut">批 量 上 架</el-button>
        <el-button type="info" @click="cancelPut">批 量 取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listPut, getPurchaseKeyIdPut, delPut, addPut, updatePut, updatePutList, cancelPutList } from "@/api/warehousing/put";
import {listWarehouse_settings} from "@/api/warehouse/warehouse_settings";
import {listPurchase} from "@/api/purchase/purchase";
import {listDeptUser} from "@/api/system/user";
import {listDept, treeselectDept} from "@/api/system/dept";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "Put",
  components: { Treeselect },
  dicts: ['sys_put_status'],
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
      // 上架管理表格数据
      putList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,

      /*********************************数据源-start**************************************/
      //仓库下拉
      warehouseOptions: undefined,
      //仓库数组
      warehouseArray: [],
      //采购下拉
      purchaseOptions: undefined,
      //采购数组
      purchaseArray: [],
      //操作员下拉
      userOptions: undefined,
      //操作员数组
      userArray: [],
      // 部门树选项
      deptOptions: undefined,
      //部门列表下拉
      deptListOptions: undefined,
      //部门列表数组
      deptListArray: [],
      /*********************************数据集-end**************************************/

      //-----------------------上架详情 start------------------------------
      //选中产品
      temporarySelect:[],
      //选中上架id数组
      selectIds:[],
      // 选中采购id数组
      selectPurchaseKeyIds: [],
      // 非单个禁用
      selectProductsSingle: true,
      // 非多个禁用
      selectProductsMultiple: true,
      // 产品列表表格数据
      selectProductsList: null,
      //-----------------------上架详情 end------------------------------

      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        purchaseId: null,
        putId: null,
        receiptId: null,
        productSku: null,
        warehouseId: null,
        status: null,
        deptId: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
    this.getDeptPurchaseList();
    this.getDeptWarehouseList();
    this.getDeptUserList();
    this.getTreeselectDept();
    this.getDeptList();
  },
  methods: {
    /** 查询上架管理列表 */
    getList() {
      this.loading = true;
      listPut(this.queryParams).then(response => {
        this.putList = response.rows;
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
        purchaseKeyId: null,
        quantity: null,
        status: 0,
        operatorId: null,
        createTime: null,
        updateTime: null
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
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加上架管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const purchaseKeyId = row.purchaseKeyId || this.selectPurchaseKeyIds
      getPurchaseKeyIdPut(purchaseKeyId).then(response => {
        this.selectProductsList = response.data;
        this.open = true;
        this.title = "上架";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updatePut(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPut(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除上架管理编号为"' + ids + '"的数据项？').then(function() {
        return delPut(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('warehousing/put/export', {
        ...this.queryParams
      }, `put_${new Date().getTime()}.xlsx`)
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
    /** 查询采购下拉列表 */
    getDeptPurchaseList(){
      listPurchase().then(response => {
        for (let i=0; i<response.rows.length; i++){
          this.purchaseArray[response.rows[i]['id']] = response.rows[i];
        }
        this.purchaseOptions = response.rows;
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
    // 采购产品多选框选中数据
    handleSelectionChangeProducts(selection) {
      this.temporarySelect = selection
      this.selectIds = selection.map(item => item.id)
      this.selectPurchaseKeyIds = selection.map(item => item.purchaseKeyId)
      this.selectProductsSingle = selection.length!==1
      this.selectProductsMultiple = !selection.length
    },
    //确认上架或批量上架
    okPut(row,isOne=false){
      this.okOrCancelPut(row,isOne,1);
    },
    //取消上架或批量取消上架
    cancelPut(row,isOne=false){
      this.okOrCancelPut(row,isOne,0);
    },
    //上架或取消公共方法
    okOrCancelPut(row,isOne,type){
      let msg = type?"上架":"取消";
      let putData = [];
      if(isOne){
        putData[0] = row;
      }else if(!this.temporarySelect.length){
        this.$modal.msgError("请选中要"+msg+"的产品");
        return false;
      }else if(this.temporarySelect.length>0){
        putData = this.temporarySelect;
      }
      if(putData){
        let purchaseKeyId = putData[0].purchaseKeyId;
        let method = type?updatePutList(putData):cancelPutList(putData);
        method.then(response => {
          if(response.code==200){
            this.$modal.msgSuccess("已"+msg);
            getPurchaseKeyIdPut(purchaseKeyId).then(response => {
              this.selectProductsList = response.data;
              if(!this.selectProductsList.length){
                this.open = false;
              }
            });
            this.getList();
            if(!isOne){
              this.open = false;
            }
          }
        });
      }else{
        this.$modal.msgError("获取不到数据，请检查");
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
