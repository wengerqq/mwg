<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="仓库名称" prop="warehouseName">
        <el-input
          v-model="queryParams.warehouseName"
          placeholder="请输入仓库名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="仓库状态" prop="status">
        <el-select
          v-model="queryParams.status"
          placeholder="仓库状态"
          clearable
          size="small"
          style="width: 215px"
        >
          <el-option
            v-for="dict in dict.type.sys_warehouse_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="仓库类型" prop="type">
        <el-select
          v-model="queryParams.type"
          placeholder="仓库类型"
          clearable
          size="small"
          style="width: 215px"
        >
          <el-option
            v-for="dict in dict.type.sys_warehouse_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="运营方式" prop="operate">
        <el-select
          v-model="queryParams.operate"
          placeholder="仓库类型"
          clearable
          size="small"
          style="width: 215px"
        >
          <el-option
            v-for="dict in dict.type.sys_warehouse_operate"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="是否头程" prop="headStroke">
        <el-select
          v-model="queryParams.headStroke"
          placeholder="是否头程"
          clearable
          size="small"
          style="width: 215px"
        >
          <el-option
            v-for="dict in dict.type.sys_yes_no"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="国家地区" prop="country">
        <el-select
          v-model="queryParams.country"
          placeholder="国家地区"
          clearable
          size="small"
          style="width: 215px"
        >
          <el-option
            v-for="dict in dict.type.sys_country_region"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="邮编" prop="zipCode">
        <el-input
          v-model="queryParams.zipCode"
          placeholder="请输入邮编"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="公司名称" prop="corporateName">
        <el-input
          v-model="queryParams.corporateName"
          placeholder="请输入公司名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系人" prop="contacts">
        <el-input
          v-model="queryParams.contacts"
          placeholder="请输入联系人"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="电话" prop="phone">
        <el-input
          v-model="queryParams.phone"
          placeholder="请输入电话"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="部门" prop="deptId">
        <treeselect v-model="queryParams.deptId" :options="deptOptions" :show-count="true" clearable size="small" style="width: 240px" placeholder="请选择归属部门" />
      </el-form-item>
      <el-form-item label="创建时间" prop="createTime">
        <el-date-picker clearable size="small"
          v-model="queryParams.createTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择创建时间">
        </el-date-picker>
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
          v-hasPermi="['warehouse:warehouse_settings:add']"
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
          v-hasPermi="['warehouse:warehouse_settings:edit']"
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
          v-hasPermi="['warehouse:warehouse_settings:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="info"
          plain
          icon="el-icon-upload2"
          size="mini"
          @click="handleImport"
          v-hasPermi="['warehouse:warehouse_settings:import']"
        >导入</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['warehouse:warehouse_settings:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="warehouse_settingsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="仓库ID" align="center" prop="id" />
      <el-table-column label="仓库名称" align="center" prop="warehouseName" />
      <el-table-column label="仓库状态" align="center">
        <template slot-scope="scope">
          <span>{{scope.row.status?dict.type.sys_warehouse_status[scope.row.status].label:scope.row.status}}</span>
        </template>
      </el-table-column>
      <el-table-column label="仓库类型" align="center">
        <template slot-scope="scope">
          <span>{{scope.row.type?dict.type.sys_warehouse_type[scope.row.type].label:scope.row.type}}</span>
        </template>
      </el-table-column>
      <el-table-column label="运营方式" align="center">
        <template slot-scope="scope">
          <span>{{scope.row.operate?dict.type.sys_warehouse_operate[scope.row.operate].label:scope.row.operate}}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否头程" align="center">
        <template slot-scope="scope">
          <span>{{scope.row.headStroke&&scope.row.headStroke!=0?scope.row.headStroke=="Y"?"是":"否":scope.row.headStroke}}</span>
        </template>
      </el-table-column>
      <el-table-column label="国家地区" align="center">
        <template slot-scope="scope">
          <span>{{scope.row.country?dict.type.sys_country_region[scope.row.country].label:scope.row.country}}</span>
        </template>
      </el-table-column>
      <el-table-column label="省份" align="center" prop="province" />
      <el-table-column label="城市" align="center" prop="city" />
      <el-table-column label="邮编" align="center" prop="zipCode" />
      <el-table-column label="公司名称" align="center" prop="corporateName" />
      <el-table-column label="联系人" align="center" prop="contacts" />
      <el-table-column label="电话" align="center" prop="phone" />
      <el-table-column label="部门" align="center">
        <template slot-scope="scope">
          <span>{{ deptListArray[scope.row.deptId]?deptListArray[scope.row.deptId].deptName:scope.row.deptId }}</span>
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
      <el-table-column label="操作人" align="center">
        <template slot-scope="scope">
          <span>{{ userArray[scope.row.operatorId]?userArray[scope.row.operatorId].nickName:scope.row.operatorId }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['warehouse:warehouse_settings:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['warehouse:warehouse_settings:remove']"
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

    <!-- 添加或修改仓库设置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="仓库名称" prop="warehouseName">
          <el-input v-model="form.warehouseName" placeholder="请输入仓库名称" />
        </el-form-item>
        <el-form-item label="仓库状态" prop="status">
          <el-select
            v-model="form.status"
            placeholder="仓库状态"
            clearable
            size="small"
            style="width: 215px"
          >
            <el-option
              v-for="dict in dict.type.sys_warehouse_status"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="仓库类型" prop="type">
          <el-select
            v-model="form.type"
            placeholder="仓库类型"
            clearable
            size="small"
            style="width: 215px"
          >
            <el-option
              v-for="dict in dict.type.sys_warehouse_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="运营方式" prop="operate">
          <el-select
            v-model="form.operate"
            placeholder="运营方式"
            clearable
            size="small"
            style="width: 215px"
          >
            <el-option
              v-for="dict in dict.type.sys_warehouse_operate"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="是否头程" prop="headStroke">
          <el-radio-group v-model="form.headStroke">
            <el-radio
              v-for="dict in dict.type.sys_yes_no"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="国家地区" prop="country">
          <el-select
            v-model="form.country"
            placeholder="国家地区"
            clearable
            size="small"
            style="width: 215px"
          >
            <el-option
              v-for="dict in dict.type.sys_country_region"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="省份" prop="province">
          <el-input v-model="form.province" placeholder="请输入省份" />
        </el-form-item>
        <el-form-item label="城市" prop="city">
          <el-input v-model="form.city" placeholder="请输入城市" />
        </el-form-item>
        <el-form-item label="邮编" prop="zipCode">
          <el-input v-model="form.zipCode" placeholder="请输入邮编" />
        </el-form-item>
        <el-form-item label="门牌号" prop="houseNumber">
          <el-input v-model="form.houseNumber" placeholder="请输入门牌号" />
        </el-form-item>
        <el-form-item label="公司名称" prop="corporateName">
          <el-input v-model="form.corporateName" placeholder="请输入公司名称" />
        </el-form-item>
        <el-form-item label="地址一" prop="addressOne">
          <el-input v-model="form.addressOne" placeholder="请输入地址一" />
        </el-form-item>
        <el-form-item label="地址二" prop="addressTwo">
          <el-input v-model="form.addressTwo" placeholder="请输入地址二" />
        </el-form-item>
        <el-form-item label="联系人" prop="contacts">
          <el-input v-model="form.contacts" placeholder="请输入联系人" />
        </el-form-item>
        <el-form-item label="电话" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入电话" />
        </el-form-item>
        <el-form-item label="部门" prop="deptId">
          <treeselect v-model="form.deptId" :options="deptOptions" :show-count="true" placeholder="请选择归属部门" />
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

    <!-- 仓库导入对话框 -->
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
import { listWarehouse_settings, getWarehouse_settings, delWarehouse_settings, addWarehouse_settings, updateWarehouse_settings } from "@/api/warehouse/warehouse_settings";
import {treeselectDept,listDept} from "@/api/system/dept";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import {listDeptUser} from "@/api/system/user";
import {getToken} from "@/utils/auth";

export default {
  name: "Warehouse_settings",
  components: { Treeselect },
  dicts: ['sys_warehouse_status','sys_warehouse_type','sys_warehouse_operate','sys_yes_no','sys_country_region'],
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
      // 仓库设置表格数据
      warehouse_settingsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 部门树选项
      deptOptions: undefined,
      //部门列表下拉
      deptListOptions: undefined,
      //操作员下拉
      userOptions: undefined,
      //部门列表数组
      deptListArray: [],
      //操作员数组
      userArray: [],
      // 仓库导入参数
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
        url: process.env.VUE_APP_BASE_API + "/warehouse/warehouse_settings/importData"
      },
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        warehouseName: null,
        status: null,
        type: null,
        operate: null,
        headStroke: null,
        country: null,
        zipCode: null,
        corporateName: null,
        contacts: null,
        phone: null,
        deptId: null,
        createTime: null,
        operatorId: null,
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
    Promise.all([
      this.getTreeselectDept(),
      this.getDeptUserList(),
      this.getDeptList(),
    ]).then(res => {
      this.getList();
    })
  },
  methods: {
    /** 查询仓库设置列表 */
    getList() {
      this.loading = true;
      listWarehouse_settings(this.queryParams).then(response => {
        this.warehouse_settingsList = response.rows;
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
        warehouseName: null,
        status: null,
        type: null,
        operate: null,
        headStroke: 'Y',
        country: null,
        province: null,
        city: null,
        zipCode: null,
        houseNumber: null,
        corporateName: null,
        addressOne: null,
        addressTwo: null,
        contacts: null,
        phone: null,
        deptId: null,
        createTime: null,
        updateTime: null,
        operatorId: null,
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
      this.title = "添加仓库设置";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getWarehouse_settings(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改仓库设置";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateWarehouse_settings(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addWarehouse_settings(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除仓库设置编号为"' + ids + '"的数据项？').then(function() {
        return delWarehouse_settings(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('warehouse/warehouse_settings/export', {
        ...this.queryParams
      }, `warehouse_settings_${new Date().getTime()}.xlsx`)
    },

    /** 查询部门下拉树结构 */
    getTreeselectDept() {
      treeselectDept().then(response => {
        this.deptOptions = response.data;
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

    /** 查询部门采购员下拉列表 */
    getDeptUserList(){
      listDeptUser().then(response => {
        for (let i=0; i<response.rows.length; i++){
          this.userArray[response.rows[i]['userId']] = response.rows[i];
        }
        this.userOptions = response.rows;
      });
    },

    /** 导入按钮操作 */
    handleImport() {
      this.upload.title = "仓库导入";
      this.upload.open = true;
    },
    /** 下载模板操作 */
    importTemplate() {
      this.download('warehouse/warehouse_settings/importTemplate', {
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
