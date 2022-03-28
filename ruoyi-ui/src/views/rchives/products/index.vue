<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="SKU编码" prop="sku">
        <el-input
          v-model="queryParams.sku"
          placeholder="请输入SKU编码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="产品条码" prop="ean">
        <el-input
          v-model="queryParams.ean"
          placeholder="请输入产品条码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="产品名称" prop="productName">
        <el-input
          v-model="queryParams.productName"
          placeholder="请输入产品名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="采购币种" prop="currencyId">
        <el-select
          v-model="queryParams.currencyId"
          placeholder="请选择采购币种"
          clearable
          size="small"
          style="width: 240px"
        >
          <el-option
            v-for="(currency,index) in currencyOptions"
            :key="currency.id"
            :label="currency.currencyName"
            :value="currency.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="供应商" prop="supplierId">
        <el-select
          v-model="queryParams.supplierId"
          placeholder="请选择供应商"
          clearable
          size="small"
          style="width: 240px"
        >
          <el-option
            v-for="(supplier,index) in supplierOptions"
            :key="supplier.id"
            :label="supplier.supplierName"
            :value="supplier.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="产品销售状态" prop="productStatus">
        <el-select
          v-model="queryParams.productStatus"
          placeholder="请选择产品销售状态"
          clearable
          size="small"
          style="width: 240px"
        >
          <el-option
            v-for="(status,index) in statusOptions"
            :key="status.id"
            :label="status.statusName"
            :value="status.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="默认采购员" prop="userId">
        <el-select
          v-model="queryParams.userId"
          placeholder="请选择默认采购员"
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
      <el-form-item label="品类" prop="varietiesId">
        <el-select
          v-model="queryParams.varietiesId"
          placeholder="请选择品类"
          clearable
          size="small"
          style="width: 240px"
        >
          <el-option
            v-for="(varieties,index) in varietiesOptions"
            :key="varieties.id"
            :label="varieties.varietiesName"
            :value="varieties.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="品牌" prop="brandId">
        <el-select
          v-model="queryParams.brandId"
          placeholder="请选择品牌"
          clearable
          size="small"
          style="width: 240px"
        >
          <el-option
            v-for="(brand,index) in brandOptions"
            :key="brand.id"
            :label="brand.brandName"
            :value="brand.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="工厂编码" prop="factoryCode">
        <el-input
          v-model="queryParams.factoryCode"
          placeholder="请输入工厂编码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="部门" prop="departmentId">
        <treeselect v-model="queryParams.departmentId" :options="deptOptions" :show-count="true" clearable size="small" style="width: 240px" placeholder="请选择归属部门" />
      </el-form-item>
      <el-form-item label="是否可用" prop="usable">
        <el-select
          v-model="queryParams.usable"
          placeholder="是否可用"
          clearable
          size="small"
          style="width: 240px"
        >
          <el-option
            v-for="dict in dict.type.sys_normal_disable"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
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
          v-hasPermi="['rchives:products:add']"
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
          v-hasPermi="['rchives:products:edit']"
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
          v-hasPermi="['rchives:products:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="info"
          plain
          icon="el-icon-upload2"
          size="mini"
          @click="handleImport"
          v-hasPermi="['rchives:products:import']"
        >导入</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['rchives:products:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="productsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="产品ID" align="center" prop="id" />-->
      <el-table-column label="SKU编码" align="center" prop="sku" />
      <el-table-column label="产品条码" align="center" prop="ean" />
      <el-table-column label="产品名称" align="center" prop="productName" />
<!--      <el-table-column label="单只毛重" align="center" prop="singleGrossWeight" />-->
<!--      <el-table-column label="单只净重" align="center" prop="singleNetWeight" />-->
      <el-table-column label="采购价" align="center" prop="purchasePrice" />
      <el-table-column label="采购币种" align="center">
        <template slot-scope="scope">
          <span>{{ currencyArray[scope.row.currencyId]?currencyArray[scope.row.currencyId].currencyName:scope.row.currencyId }}</span>
        </template>
      </el-table-column>
      <el-table-column label="供应商" align="center">
        <template slot-scope="scope">
          <span>{{ supplierArray[scope.row.supplierId]?supplierArray[scope.row.supplierId].supplierName:scope.row.supplierId }}</span>
        </template>
      </el-table-column>
      <el-table-column label="产品销售状态" align="center">
        <template slot-scope="scope">
          <span>{{ statusArray[scope.row.productStatus]?statusArray[scope.row.productStatus].statusName:scope.row.productStatus }}</span>
        </template>
      </el-table-column>
      <el-table-column label="默认采购员" align="center">
        <template slot-scope="scope">
          <span>{{ userArray[scope.row.userId]?userArray[scope.row.userId].nickName:scope.row.userId }}</span>
        </template>
      </el-table-column>
      <el-table-column label="品类" align="center">
        <template slot-scope="scope">
          <span>{{ varietiesArray[scope.row.varietiesId]?varietiesArray[scope.row.varietiesId].varietiesName:scope.row.varietiesId }}</span>
        </template>
      </el-table-column>
      <el-table-column label="品牌" align="center">
        <template slot-scope="scope">
          <span>{{ brandArray[scope.row.brandId]?brandArray[scope.row.brandId].brandName:scope.row.brandId }}</span>
        </template>
      </el-table-column>
      <el-table-column label="工厂编码" align="center" prop="factoryCode" />
      <el-table-column label="部门" align="center">
        <template slot-scope="scope">
          <span>{{ deptListArray[scope.row.departmentId]?deptListArray[scope.row.departmentId].deptName:scope.row.departmentId }}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否可用" align="center">
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.usable"
            :active-value="0"
            :inactive-value="1"
            @change="handleUsableChange(scope.row)"
          ></el-switch>
        </template>
      </el-table-column>
      <el-table-column label="操作人" align="center">
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
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['rchives:products:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['rchives:products:remove']"
          >删除</el-button>
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

    <!-- 添加或修改产品资料管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="SKU编码" prop="sku">
          <el-input v-model="form.sku" placeholder="请输入SKU编码" />
        </el-form-item>
        <el-form-item label="产品条码" prop="ean">
          <el-input v-model="form.ean" placeholder="请输入产品条码" />
        </el-form-item>
        <el-form-item label="产品名称" prop="productName">
          <el-input v-model="form.productName" placeholder="请输入产品名称" />
        </el-form-item>
        <el-form-item label="产品名称英文" prop="productNameEn">
          <el-input v-model="form.productNameEn" placeholder="请输入产品名称英文" />
        </el-form-item>
        <el-form-item label="单只毛重" prop="singleGrossWeight">
          <el-input v-model="form.singleGrossWeight" placeholder="请输入单只毛重" />
        </el-form-item>
        <el-form-item label="单只净重" prop="singleNetWeight">
          <el-input v-model="form.singleNetWeight" placeholder="请输入单只净重" />
        </el-form-item>
        <el-form-item label="单只长" prop="singleLong">
          <el-input v-model="form.singleLong" placeholder="请输入单只长" />
        </el-form-item>
        <el-form-item label="单只宽" prop="singleWidth">
          <el-input v-model="form.singleWidth" placeholder="请输入单只宽" />
        </el-form-item>
        <el-form-item label="单只高" prop="singleHeight">
          <el-input v-model="form.singleHeight" placeholder="请输入单只高" />
        </el-form-item>
        <el-form-item label="外箱毛重" prop="grossWeight">
          <el-input v-model="form.grossWeight" placeholder="请输入外箱毛重" />
        </el-form-item>
        <el-form-item label="外箱净重" prop="netWeight">
          <el-input v-model="form.netWeight" placeholder="请输入外箱净重" />
        </el-form-item>
        <el-form-item label="外箱长" prop="length">
          <el-input v-model="form.length" placeholder="请输入外箱长" />
        </el-form-item>
        <el-form-item label="外箱宽" prop="width">
          <el-input v-model="form.width" placeholder="请输入外箱宽" />
        </el-form-item>
        <el-form-item label="外箱高" prop="height">
          <el-input v-model="form.height" placeholder="请输入外箱高" />
        </el-form-item>
        <el-form-item label="采购价" prop="purchasePrice">
          <el-input v-model="form.purchasePrice" placeholder="请输入采购价" />
        </el-form-item>
        <el-form-item label="采购币种" prop="currencyId">
          <el-select
            v-model="form.currencyId"
            placeholder="请选择采购币种"
            clearable
            size="small"
            style="width: 240px"
          >
            <el-option
              v-for="(currency,index) in currencyOptions"
              :key="currency.id"
              :label="currency.currencyName"
              :value="currency.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="供应商" prop="supplierId">
          <el-select
            v-model="form.supplierId"
            placeholder="请选择供应商"
            clearable
            size="small"
            style="width: 240px"
          >
            <el-option
              v-for="(supplier,index) in supplierOptions"
              :key="supplier.id"
              :label="supplier.supplierName"
              :value="supplier.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="产品销售状态" prop="productStatus">
          <el-select
            v-model="form.productStatus"
            placeholder="请选择产品销售状态"
            clearable
            size="small"
            style="width: 240px"
          >
            <el-option
              v-for="(status,index) in statusOptions"
              :key="status.id"
              :label="status.statusName"
              :value="status.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="默认采购员" prop="userId">
          <el-select
            v-model="form.userId"
            placeholder="请选择默认采购员"
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
        <el-form-item label="产品单位" prop="unitId">
          <el-select
            v-model="form.unitId"
            placeholder="请选择产品单位"
            clearable
            size="small"
            style="width: 240px"
          >
            <el-option
              v-for="(unit,index) in unitOptions"
              :key="unit.id"
              :label="unit.unitName"
              :value="unit.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="品类" prop="varietiesId">
          <el-select
            v-model="form.varietiesId"
            placeholder="请选择品类"
            clearable
            size="small"
            style="width: 240px"
          >
            <el-option
              v-for="(varieties,index) in varietiesOptions"
              :key="varieties.id"
              :label="varieties.varietiesName"
              :value="varieties.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="品牌" prop="brandId">
          <el-select
            v-model="form.brandId"
            placeholder="请选择品牌"
            clearable
            size="small"
            style="width: 240px"
          >
            <el-option
              v-for="(brand,index) in brandOptions"
              :key="brand.id"
              :label="brand.brandName"
              :value="brand.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="箱数量" prop="numberOfBoxes">
          <el-input v-model="form.numberOfBoxes" placeholder="请输入箱数量" />
        </el-form-item>
        <el-form-item label="墨量" prop="inkQuantity">
          <el-input v-model="form.inkQuantity" placeholder="请输入墨量" />
        </el-form-item>
        <el-form-item label="页数" prop="pageNumber">
          <el-input v-model="form.pageNumber" placeholder="请输入页数" />
        </el-form-item>
        <el-form-item label="产品描述" prop="description">
          <el-input v-model="form.description" placeholder="请输入产品描述" />
        </el-form-item>
        <el-form-item label="工厂编码" prop="factoryCode">
          <el-input v-model="form.factoryCode" placeholder="请输入工厂编码" />
        </el-form-item>
        <el-form-item label="部门" prop="departmentId">
          <treeselect v-model="form.departmentId" :options="deptOptions" :show-count="true" placeholder="请选择归属部门" />
        </el-form-item>
        <el-form-item label="是否可用" prop="usable">
          <el-radio-group v-model="form.usable">
            <el-radio
              v-for="dict in dict.type.sys_normal_disable"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
<!--        <el-form-item label="操作人ID" prop="operatorId">-->
<!--          <el-input v-model="form.operatorId" placeholder="请输入操作人ID" />-->
<!--        </el-form-item>-->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 用户导入对话框 -->
    <el-dialog :title="upload.title" :visible.sync="upload.open" width="400px" append-to-body>
      <el-upload
        ref="upload"
        :limit="1"
        accept=".xlsx, .xls"
        :headers="upload.headers"
        :action="upload.url + '?updateSupport=' + upload.updateSupport"
        :disabled="upload.isUploading"
        :on-progress="handleFileUploadProgress"
        :on-success="handleFileSuccess"
        :auto-upload="false"
        drag
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        <div class="el-upload__tip text-center" slot="tip">
          <div>
            <div style="color:red;"><label style="font-weight: bold;">注意：</label>表格中带<label style="color:blue;font-size: 17px;"> * </label>号的列字段为必填，其他为选填</div>
            <div style="color:red;">表格中字段名带id的，请参考表格下方<label style="color:blue;font-size: 12px;">【基础资料】</label>对应的ID填写</div>
          </div>
          <div class="el-upload__tip" slot="tip">
            <el-checkbox v-model="upload.updateSupport" /> 是否更新已经存在的产品数据
          </div>
          <span>仅允许导入xls、xlsx格式文件。</span>
          <el-link type="primary" :underline="false" style="font-size:12px;vertical-align: baseline;" @click="importTemplate">下载模板</el-link>
        </div>
      </el-upload>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFileForm">确 定</el-button>
        <el-button @click="upload.open = false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listProducts, getProducts, delProducts, addProducts, updateProducts, changeProductUsable } from "@/api/rchives/products";
import {treeselectDept,listDept} from "@/api/system/dept";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import {listCurrency} from "@/api/rchives/currency";
import {listSupplier} from "@/api/rchives/supplier";
import {listSales_status} from "@/api/rchives/sales_status";
import {listDeptUser} from "@/api/system/user";
import {listUnit} from "@/api/rchives/unit";
import {listVarieties} from "@/api/rchives/varieties";
import {listBrand} from "@/api/rchives/brand";
import {getToken} from "@/utils/auth";

export default {
  name: "Products",
  components: { Treeselect },
  dicts: ['sys_normal_disable', 'sys_user_sex'],
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
      // 产品资料管理表格数据
      productsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 部门树选项
      deptOptions: undefined,
      //采购币种下拉
      currencyOptions: undefined,
      //供应商下拉
      supplierOptions: undefined,
      //产品销售状态下拉
      statusOptions: undefined,
      //默认采购员下拉
      userOptions: undefined,
      //产品单位下拉
      unitOptions: undefined,
      //品类下拉
      varietiesOptions: undefined,
      //品牌下拉
      brandOptions: undefined,
      //部门列表下拉
      deptListOptions: undefined,
      //采购币种数组
      currencyArray: [],
      //供应商数组
      supplierArray: [],
      //产品销售状态数组
      statusArray: [],
      //默认采购员数组
      userArray: [],
      //品类数组
      varietiesArray: [],
      //品牌数组
      brandArray: [],
      //部门列表数组
      deptListArray: [],
      // 用户导入参数
      upload: {
        // 是否显示弹出层（产品导入）
        open: false,
        // 弹出层标题（产品导入）
        title: "",
        // 是否禁用上传
        isUploading: false,
        // 是否更新已经存在的用户数据
        updateSupport: 0,
        // 设置上传的请求头部
        headers: { Authorization: "Bearer " + getToken() },
        // 上传的地址
        url: process.env.VUE_APP_BASE_API + "/rchives/products/importData"
      },
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        sku: null,
        ean: null,
        productName: null,
        currencyId: null,
        supplierId: null,
        productStatus: null,
        userId: null,
        varietiesId: null,
        brandId: null,
        factoryCode: null,
        departmentId: null,
        usable: null,
        operatorId: null,
        createTime: null,
        isQuery:false
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getTreeselectDept();
    this.getCurrencyList();
    this.getSupplierList();
    this.getStatusList();
    this.getDeptUserList();
    this.getUnitList();
    this.getVarietiesList();
    this.getBrandList();
    this.getDeptList();
    this.getList();
  },
  methods: {
    /** 查询产品资料管理列表 */
    getList() {
      this.loading = true;
      listProducts(this.queryParams).then(response => {
        this.productsList = response.rows;
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
        sku: null,
        ean: null,
        productName: null,
        productNameEn: null,
        singleGrossWeight: null,
        singleNetWeight: null,
        singleLong: null,
        singleWidth: null,
        singleHeight: null,
        grossWeight: null,
        netWeight: null,
        length: null,
        width: null,
        height: null,
        purchasePrice: null,
        currencyId: null,
        supplierId: null,
        productStatus: null,
        userId: null,
        unitId: null,
        varietiesId: null,
        brandId: null,
        numberOfBoxes: null,
        inkQuantity: null,
        pageNumber: null,
        description: null,
        factoryCode: null,
        departmentId: null,
        usable: "0",
        operatorId: null,
        createTime: null,
        updateTime: null,
        isQuery:false
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
      this.handleQuery(1);
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
      this.title = "添加产品资料管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getProducts(id).then(response => {
        this.form = response.data;
        this.form.usable = String(this.form.usable);
        this.open = true;
        this.title = "修改产品资料管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateProducts(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addProducts(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除产品资料管理编号为"' + ids + '"的数据项？').then(function() {
        return delProducts(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('rchives/products/export', {
        ...this.queryParams
      }, `products_${new Date().getTime()}.xlsx`)
    },
    /** 查询部门下拉树结构 */
    getTreeselectDept() {
      treeselectDept().then(response => {
        this.deptOptions = response.data;
      });
    },
    /** 查询币种列表 */
    getCurrencyList(){
      listCurrency().then(response => {
        for (let i=0; i<response.rows.length; i++){
          this.currencyArray[response.rows[i]['id']] = response.rows[i];
        }
        this.currencyOptions = response.rows;
      });
    },
    /** 查询供应商列表 */
    getSupplierList(){
      listSupplier().then(response => {
        for (let i=0; i<response.rows.length; i++){
          this.supplierArray[response.rows[i]['id']] = response.rows[i];
        }
        this.supplierOptions = response.rows;
      });
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
    /** 查询部门采购员下拉列表 */
    getDeptUserList(){
      listDeptUser().then(response => {
        for (let i=0; i<response.rows.length; i++){
          this.userArray[response.rows[i]['userId']] = response.rows[i];
        }
        this.userOptions = response.rows;
      });
    },
    /** 单位下拉列表 */
    getUnitList(){
      listUnit().then(response => {
        this.unitOptions = response.rows;
      });
    },
    /** 品类下拉列表 */
    getVarietiesList(){
      listVarieties().then(response => {
        for (let i=0; i<response.rows.length; i++){
          this.varietiesArray[response.rows[i]['id']] = response.rows[i];
        }
        this.varietiesOptions = response.rows;
      });
    },
    /** 品牌下拉列表 */
    getBrandList(){
      listBrand().then(response => {
        for (let i=0; i<response.rows.length; i++){
          this.brandArray[response.rows[i]['id']] = response.rows[i];
        }
        this.brandOptions = response.rows;
      });
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
    /** 修改产品是否可用 */
    handleUsableChange(row) {
      let text = row.usable === 0 ? "启用" : "停用";
      this.$modal.confirm('确认要"' + text + '""' + row.productName + '"吗？').then(function() {
        return changeProductUsable(row.id, row.usable);
      }).then(() => {
        this.$modal.msgSuccess(text + "成功");
      }).catch(function() {
        row.usable = row.usable === 0 ? 1 : 0;
      });
    },
    /** 导入按钮操作 */
    handleImport() {
      this.upload.title = "产品导入";
      this.upload.open = true;
    },
    /** 下载模板操作 */
    importTemplate() {
      this.download('rchives/products/importTemplate', {
      }, `products_template_${new Date().getTime()}.xlsx`)
    },
    // 文件上传中处理
    handleFileUploadProgress(event, file, fileList) {
      this.upload.isUploading = true;
    },
    // 文件上传成功处理
    handleFileSuccess(response, file, fileList) {
      this.upload.open = false;
      this.upload.isUploading = false;
      this.$refs.upload.clearFiles();
      this.$alert("<div style='overflow: auto;overflow-x: hidden;max-height: 70vh;padding: 10px 20px 0;'>" + response.msg + "</div>", "导入结果", { dangerouslyUseHTMLString: true });
      this.getList();
    },
    // 提交上传文件
    submitFileForm() {
      this.$refs.upload.submit();
    }
  }
};
</script>
