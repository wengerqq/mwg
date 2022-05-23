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
      <el-form-item label="供应商" prop="supplierId">
        <el-select
          v-model="queryParams.supplierId"
          @change="selectSupplierQuery"
          placeholder="请选择供应商"
          clearable
          size="small"
          style="width: 280px"
        >
          <el-option
            v-for="(supplier,index) in supplierOptions"
            :key="supplier.id"
            :label="supplier.supplierName"
            :value="supplier.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="采购员" prop="buyerId">
        <el-select
          v-model="queryParams.buyerId"
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
      <el-form-item label="跟踪单号" prop="trackingNumber">
        <el-input
          v-model="queryParams.trackingNumber"
          placeholder="请输入跟踪单号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="采购单预计到货时间" prop="estimatedArrivalTime">
        <el-date-picker clearable size="small"
          v-model="queryParams.estimatedArrivalTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择采购单预计到货时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="部门" prop="deptId">
        <treeselect v-model="queryParams.deptId" :options="deptOptions" :show-count="true" clearable size="small" style="width: 240px" placeholder="请选择部门" />
      </el-form-item>
      <el-form-item label="备注" prop="remarks">
        <el-input
          v-model="queryParams.remarks"
          placeholder="请输入备注"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="操作人" prop="operatorId">
        <el-select
          v-model="queryParams.operatorId"
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
          v-hasPermi="['purchase:purchase:add']"
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
          v-hasPermi="['purchase:purchase:edit']"
        >修改</el-button>
      </el-col>
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="danger"-->
<!--          plain-->
<!--          icon="el-icon-delete"-->
<!--          size="mini"-->
<!--          :disabled="multiple"-->
<!--          @click="handleDelete"-->
<!--          v-hasPermi="['purchase:purchase:remove']"-->
<!--        >删除</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="info"
          plain
          icon="el-icon-upload2"
          size="mini"
          @click="handleImport"
          v-hasPermi="['purchase:purchase:import']"
        >导入</el-button>
      </el-col>
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="warning"-->
<!--          plain-->
<!--          icon="el-icon-download"-->
<!--          size="mini"-->
<!--          @click="handleExport"-->
<!--          v-hasPermi="['purchase:purchase:export']"-->
<!--        >导出</el-button>-->
<!--      </el-col>-->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="purchaseList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="采购单号" width="200" align="center" prop="purchaseId" />
      <el-table-column label="状态" align="center">
        <template slot-scope="scope">
          {{dict.type.sys_purchase_status[scope.row.status]?dict.type.sys_purchase_status[scope.row.status].label:scope.row.status}}
        </template>
      </el-table-column>
      <el-table-column label="仓库" align="center">
        <template slot-scope="scope">
          <span>{{ warehouseArray[scope.row.warehouseId]?warehouseArray[scope.row.warehouseId].warehouseName:scope.row.warehouseId }}</span>
        </template>
      </el-table-column>
      <el-table-column label="供应商" align="center">
        <template slot-scope="scope">
          <span>{{ supplierArray[scope.row.supplierId]?supplierArray[scope.row.supplierId].supplierName:scope.row.supplierId }}</span>
        </template>
      </el-table-column>
      <el-table-column label="采购员" align="center" prop="buyerId">
        <template slot-scope="scope">
          <span>{{ userArray[scope.row.buyerId]?userArray[scope.row.buyerId].nickName:scope.row.buyerId }}</span>
        </template>
      </el-table-column>
      <el-table-column label="跟踪单号" align="center" prop="trackingNumber" />
      <el-table-column label="采购单预计到货时间" align="center" prop="estimatedArrivalTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.estimatedArrivalTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="部门" align="center" prop="deptId">
        <template slot-scope="scope">
          <span>{{ deptListArray[scope.row.deptId]?deptListArray[scope.row.deptId].deptName:scope.row.deptId }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remarks" />
      <el-table-column label="操作人" align="center" prop="operatorId">
        <template slot-scope="scope">
          <span>{{ userArray[scope.row.operatorId]?userArray[scope.row.operatorId].nickName:scope.row.operatorId }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d} {h}:{i}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['purchase:purchase:edit']"
          >修改</el-button>
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-delete"-->
<!--            @click="handleDelete(scope.row)"-->
<!--            v-hasPermi="['purchase:purchase:remove']"-->
<!--          >删除</el-button>-->
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

    <!-- 添加或修改采购确认对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="85%" @close='closeDialog' append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-row>
            <el-col span="8">
              <el-form-item label="采购仓库" prop="warehouseId">
                <el-select
                  v-model="form.warehouseId"
                  placeholder="请选择仓库"
                  clearable
                  size="small"
                  style="width: 200px;margin-right: 20px;"
                >
                  <el-option
                    v-for="(warehouse,index) in warehouseOptions"
                    :key="warehouse.id"
                    :label="warehouse.warehouseName"
                    :value="warehouse.id"
                  />
                </el-select>
                <el-radio-group prop="transferOrNot" v-model="form.transferOrNot">
                  <el-radio
                    v-for="dict in dict.type.sys_transfer_or_not"
                    :key="dict.value"
                    :label="dict.value"
                  >{{dict.label}}</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
            <el-col span="8">
              <el-form-item label="供应商" prop="supplierId">
                <el-select
                  v-model="form.supplierId"
                  @change="selectSupplierQuery"
                  placeholder="请选择供应商"
                  clearable
                  size="small"
                  style="width: 280px"
                >
                  <el-option
                    v-for="(supplier,index) in supplierOptions"
                    :key="supplier.id"
                    :label="supplier.supplierName"
                    :value="supplier.id"
                  />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col span="8">
              <el-form-item label="采购员" prop="buyerId">
                <el-select
                  v-model="form.buyerId"
                  placeholder="请选择采购员"
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
            </el-col>
          </el-row>
          <el-row>
            <el-col span="8">
              <el-form-item label="跟踪单号" prop="trackingNumber">
                <el-input v-model="form.trackingNumber" placeholder="请输入跟踪单号" />
              </el-form-item>
            </el-col>
            <el-col span="8">
              <el-form-item label="结算方式" prop="settlementMethod">
                <el-select
                  v-model="form.settlementMethod"
                  placeholder="请选择结算方式"
                  clearable
                  size="small"
                  style="width: 240px"
                >
                  <el-option
                    v-for="dict in dict.type.sys_settlement_method"
                    :key="dict.value"
                    :label="dict.label"
                    :value="dict.value"
                  />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col span="8">
              <el-form-item label="币种" prop="currency">
                <el-select
                  v-model="form.currency"
                  placeholder="请选择币种"
                  clearable
                  size="small"
                  style="width: 240px"
                >
                  <el-option
                    v-for="dict in dict.type.sys_currency_type"
                    :key="dict.value"
                    :label="dict.label"
                    :value="dict.value"
                  />
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col span="8">
              <el-form-item label="支付方式" prop="paymentMethod">
                <el-select
                  v-model="form.paymentMethod"
                  placeholder="请选择支付方式"
                  clearable
                  size="small"
                  style="width: 240px"
                >
                  <el-option
                    v-for="dict in dict.type.sys_payment_collection"
                    :key="dict.value"
                    :label="dict.label"
                    :value="dict.value"
                  />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col span="8">
              <el-form-item label="收款方式" prop="collectionMethod">
                <el-select
                  v-model="form.collectionMethod"
                  placeholder="请选择支付方式"
                  clearable
                  size="small"
                  style="width: 240px"
                >
                  <el-option
                    v-for="dict in dict.type.sys_payment_collection"
                    :key="dict.value"
                    :label="dict.label"
                    :value="dict.value"
                  />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col span="8">
              <el-form-item label="运费" prop="freight">
                <el-input v-model="form.freight" placeholder="请输入运费" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col span="8">
              <el-form-item label="供应商运输方式" label-width="100px" prop="supplierShippingType">
                <el-select
                  v-model="form.supplierShippingType"
                  placeholder="请选择运输方式"
                  clearable
                  size="small"
                  style="width: 240px"
                >
                  <el-option
                    v-for="dict in dict.type.sys_supplier_shipping_type"
                    :key="dict.value"
                    :label="dict.label"
                    :value="dict.value"
                  />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col span="8">
              <el-form-item label="支付周期类型" label-width="100px" prop="paymentCycleType">
                <el-select
                  v-model="form.paymentCycleType"
                  placeholder="请选择支付周期类型"
                  clearable
                  size="small"
                  style="width: 240px"
                >
                  <el-option
                    v-for="dict in dict.type.sys_payment_cycle_type"
                    :key="dict.value"
                    :label="dict.label"
                    :value="dict.value"
                  />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col span="8">
              <el-form-item label="预计到货时间" label-width="100px" prop="estimatedArrivalTime">
                <el-date-picker clearable size="small"
                                v-model="form.estimatedArrivalTime"
                                type="date"
                                value-format="yyyy-MM-dd"
                                placeholder="选择采购单预计到货时间">
                </el-date-picker>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col span="8">
              <el-form-item label="创建时间" label-width="100px" prop="createTime">
                <el-date-picker clearable size="small"
                                v-model="form.createTime"
                                type="date"
                                value-format="yyyy-MM-dd HH:MM:ss"
                                placeholder="选择创建时间">
                </el-date-picker>
              </el-form-item>
            </el-col>
            <el-col span="8">
              <el-form-item label="部门" prop="deptId">
                <treeselect v-model="form.deptId" :options="deptOptions" :show-count="true" placeholder="请选择部门" />
              </el-form-item>
            </el-col>
            <el-col span="8">
              <el-form-item label="备注" prop="remarks">
                <el-input v-model="form.remarks" placeholder="请输入备注" />
              </el-form-item>
            </el-col>
          </el-row>
        </el-row>
        <el-row type="flex" class="row-bg" justify="end" style="padding: 25px 0px 13px 0px;align-items: center;">
          <el-col span="16" style="display: flex;justify-content: start;">
            <el-col span="4" >SKU种类：{{skuNum}}</el-col>
            <el-col span="4" >SKU总数：{{ skuCount }}</el-col>
            <el-col span="7" >商品金额（含税）：{{ taxTotal }} {{form.currency?dict.type.sys_currency_type[form.currency].label:form.currency}}</el-col>
            <el-col span="7" >商品金额（不含税）：{{ noTaxTotal }} {{form.currency?dict.type.sys_currency_type[form.currency].label:form.currency}}</el-col>
          </el-col>
          <el-col span="8" style="display: flex;justify-content: end;">
            <el-button type="primary" @click="addProducts">添加产品</el-button>
            <el-button type="danger" :disabled="selectProductsMultiple" @click="handleDeleteProduct">删除产品</el-button>
          </el-col>
        </el-row>
        <el-row>
            <el-table v-loading="loading" ref="multipleTable" :data="selectProductsList" @selection-change="handleSelectionChangeProducts">
              <el-table-column type="selection" width="55" align="center" />
              <el-table-column label="产品信息" align="center">
                <template slot-scope="scope">
                  <span>
                    产品SKU：{{ scope.row.productSku }}<br/>
                    产品名称：{{ scope.row.productName }}<br/>
                  </span>
                </template>
              </el-table-column>
              <el-table-column label="采购数量" width="150" align="center">
                <template slot-scope="scope">
                  <el-input @blur="getDetailTotal" v-model="scope.row.quantity" placeholder="请输入数量" />
                </template>
              </el-table-column>
              <el-table-column label="单价" width="250" align="center">
                <template slot-scope="scope">
                  本次采购价（{{scope.row.params.currency?dict.type.sys_currency_type[scope.row.params.currency].label:scope.row.params.currency}}）：<el-input @blur="getDetailTotal" v-model="scope.row.price" placeholder="请输入单价" />
                  上次采购价（{{scope.row.params.currency?dict.type.sys_currency_type[scope.row.params.currency].label:scope.row.params.currency}}）：<el-input v-model="scope.row.params.lastPrice" disabled="disabled" />
                </template>
              </el-table-column>
              <el-table-column label="总应付金额" align="center">
                <template slot-scope="scope">
                  <span>{{ (scope.row.price*scope.row.quantity).toFixed(2)}}</span>
                </template>
              </el-table-column>
              <el-table-column label="采购参考价" width="150" align="center">
                <template slot-scope="scope">
                  <el-input v-model="scope.row.referencePrice" placeholder="采购参考价" />
                </template>
              </el-table-column>
              <el-table-column label="税率" width="100" align="center">
                <template slot-scope="scope">
                  <div style="display: flex;align-items: center;"><el-input @blur="getDetailTotal" v-model="scope.row.taxRate" placeholder="税率" />%</div>
                </template>
              </el-table-column>
              <el-table-column label="备注/外部单号" align="center">
                <template slot-scope="scope">
                  备注：<el-input v-model="scope.row.remark" placeholder="备注" />
                  外部单号：<el-input v-model="scope.row.externalOrderNo" placeholder="外部单号" />
                  交付时间：<el-date-picker clearable size="small" v-model="scope.row.deliveryTime" type="date" value-format="yyyy-MM-dd" placeholder="选择交付时间" />
                </template>
              </el-table-column>
              <el-table-column label="操作" width="120" align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                  <el-button
                    size="mini"
                    type="text"
                    icon="el-icon-delete"
                    @click="handleDeleteProduct(scope.row,scope.$index)"
                  >删除</el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog title="选择采购产品" :visible.sync="openSelectProdcuts">
      <el-row>
        <el-form :model="queryParamsProducts" ref="queryFormProduct" :inline="true" v-show="true" label-width="68px">
          <el-form-item label="SKU编码" prop="sku">
            <el-input
              v-model="queryParamsProducts.sku"
              placeholder="请输入SKU编码"
              clearable
              size="small"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="产品名称" prop="productName">
            <el-input
              v-model="queryParamsProducts.productName"
              placeholder="请输入产品名称"
              clearable
              size="small"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQueryProduct">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQueryProduct">重置</el-button>
          </el-form-item>
        </el-form>
      </el-row>
      <el-row type="flex" class="row-bg" justify="end">
        <el-col span="8" style="display: flex;justify-content: end;">
          <el-button type="primary" :disabled="productsMultiple" @click="addSelectProducts">添加选中产品</el-button>
        </el-col>
      </el-row>
      <el-row>
        <el-table v-loading="loading" :data="productsList" @selection-change="handleSelectionChangeAddProducts">
          <el-table-column type="selection" width="55" align="center" />
          <el-table-column label="产品SKU" align="center" prop="sku" />
          <el-table-column label="产品名称" align="center" prop="productName" />
          <el-table-column label="供应商" align="center">
            <template slot-scope="scope">
              <span>{{ supplierArray[scope.row.supplierId]?supplierArray[scope.row.supplierId].supplierName:scope.row.supplierId }}</span>
            </template>
          </el-table-column>
          <el-table-column label="单价" align="center" prop="purchasePrice" />
        </el-table>
        <pagination
          v-show="productsTotal>0"
          :total="productsTotal"
          :page.sync="queryParamsProducts.pageNum"
          :limit.sync="queryParamsProducts.pageSize"
          @pagination="selectSupplierQuery"
        />
      </el-row>
    </el-dialog>
    <!-- 用户导入对话框 -->
    <el-dialog :title="upload.title" :visible.sync="upload.open" width="80%" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px" style="margin-bottom: 30px;">
        <el-row>
          <el-row>
            <el-col span="8">
              <el-form-item label="采购仓库" prop="warehouseId">
                <el-select
                  v-model="form.warehouseId"
                  placeholder="请选择仓库"
                  clearable
                  size="small"
                  style="width: 200px;margin-right: 20px;"
                >
                  <el-option
                    v-for="(warehouse,index) in warehouseOptions"
                    :key="warehouse.id"
                    :label="warehouse.warehouseName"
                    :value="warehouse.id"
                  />
                </el-select>
                <el-radio-group prop="transferOrNot" v-model="form.transferOrNot">
                  <el-radio
                    v-for="dict in dict.type.sys_transfer_or_not"
                    :key="dict.value"
                    :label="dict.value"
                  >{{dict.label}}</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
            <el-col span="8">
              <el-form-item label="供应商" prop="supplierId">
                <el-select
                  v-model="form.supplierId"
                  @change="selectSupplierQuery"
                  placeholder="请选择供应商"
                  clearable
                  size="small"
                  style="width: 280px"
                >
                  <el-option
                    v-for="(supplier,index) in supplierOptions"
                    :key="supplier.id"
                    :label="supplier.supplierName"
                    :value="supplier.id"
                  />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col span="8">
              <el-form-item label="采购员" prop="buyerId">
                <el-select
                  v-model="form.buyerId"
                  placeholder="请选择采购员"
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
            </el-col>
          </el-row>
          <el-row>
            <el-col span="8">
              <el-form-item label="跟踪单号" prop="trackingNumber">
                <el-input v-model="form.trackingNumber" placeholder="请输入跟踪单号" style="width: 350px;" />
              </el-form-item>
            </el-col>
            <el-col span="8">
              <el-form-item label="结算方式" prop="settlementMethod">
                <el-select
                  v-model="form.settlementMethod"
                  placeholder="请选择结算方式"
                  clearable
                  size="small"
                  style="width: 240px"
                >
                  <el-option
                    v-for="dict in dict.type.sys_settlement_method"
                    :key="dict.value"
                    :label="dict.label"
                    :value="dict.value"
                  />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col span="8">
              <el-form-item label="币种" prop="currency">
                <el-select
                  v-model="form.currency"
                  placeholder="请选择币种"
                  clearable
                  size="small"
                  style="width: 240px"
                >
                  <el-option
                    v-for="dict in dict.type.sys_currency_type"
                    :key="dict.value"
                    :label="dict.label"
                    :value="dict.value"
                  />
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col span="8">
              <el-form-item label="支付方式" prop="paymentMethod">
                <el-select
                  v-model="form.paymentMethod"
                  placeholder="请选择支付方式"
                  clearable
                  size="small"
                  style="width: 240px"
                >
                  <el-option
                    v-for="dict in dict.type.sys_payment_collection"
                    :key="dict.value"
                    :label="dict.label"
                    :value="dict.value"
                  />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col span="8">
              <el-form-item label="收款方式" prop="collectionMethod">
                <el-select
                  v-model="form.collectionMethod"
                  placeholder="请选择支付方式"
                  clearable
                  size="small"
                  style="width: 240px"
                >
                  <el-option
                    v-for="dict in dict.type.sys_payment_collection"
                    :key="dict.value"
                    :label="dict.label"
                    :value="dict.value"
                  />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col span="8">
              <el-form-item label="运费" prop="freight">
                <el-input v-model="form.freight" placeholder="请输入运费" style="width: 250px;" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col span="8">
              <el-form-item label="供应商运输方式" label-width="120px" prop="supplierShippingType">
                <el-select
                  v-model="form.supplierShippingType"
                  placeholder="请选择运输方式"
                  clearable
                  size="small"
                  style="width: 240px"
                >
                  <el-option
                    v-for="dict in dict.type.sys_supplier_shipping_type"
                    :key="dict.value"
                    :label="dict.label"
                    :value="dict.value"
                  />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col span="8">
              <el-form-item label="支付周期类型" label-width="100px" prop="paymentCycleType">
                <el-select
                  v-model="form.paymentCycleType"
                  placeholder="请选择支付周期类型"
                  clearable
                  size="small"
                  style="width: 240px"
                >
                  <el-option
                    v-for="dict in dict.type.sys_payment_cycle_type"
                    :key="dict.value"
                    :label="dict.label"
                    :value="dict.value"
                  />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col span="8">
              <el-form-item label="预计到货时间" label-width="100px" prop="estimatedArrivalTime">
                <el-date-picker clearable size="small"
                                v-model="form.estimatedArrivalTime"
                                type="date"
                                value-format="yyyy-MM-dd"
                                placeholder="选择采购单预计到货时间">
                </el-date-picker>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col span="8">
              <el-form-item label="创建时间" label-width="100px" prop="createTime">
                <el-date-picker clearable size="small"
                                v-model="form.createTime"
                                type="date"
                                value-format="yyyy-MM-dd HH:MM:ss"
                                placeholder="选择创建时间">
                </el-date-picker>
              </el-form-item>
            </el-col>
            <el-col span="8">
              <el-form-item label="部门" prop="deptId">
                <treeselect v-model="form.deptId" :options="deptOptions" :show-count="true" placeholder="请选择部门" style="width: 300px;" />
              </el-form-item>
            </el-col>
            <el-col span="8">
              <el-form-item label="备注" prop="remarks">
                <el-input v-model="form.remarks" placeholder="请输入备注" style="width: 350px;" />
              </el-form-item>
            </el-col>
          </el-row>
        </el-row>
      </el-form>
      <el-row>
        <el-col span="24" style="font-size: 18px;text-align: center;padding: 25px 0px 20px 0px;border-top: 1px solid #DCDFE6;">采购产品导入</el-col>
      </el-row>
      <el-upload
        ref="upload"
        :limit="1"
        accept=".xlsx, .xls"
        :headers="upload.headers"
        :action="upload.url + '?updateSupport=' + upload.updateSupport"
        :disabled="upload.isUploading"
        :on-change="onChange"
        :on-progress="handleFileUploadProgress"
        :on-success="handleFileSuccess"
        :auto-upload="false"
        :data="this.form"
        drag
        style="text-align: center;"
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        <div class="el-upload__tip text-center" slot="tip">
          <div>
            <div style="color:red;"><label style="font-weight: bold;">注意：</label>1、表格中带<label style="color:blue;font-size: 17px;"> * </label>号的列字段为必填，其他为选填</div>
            <div style="color:red;">2、请选择对应供应商的产品进行录入，如有一条产品非对应供应商，表格将会导入失败</div>
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
import { listPurchase, getPurchase, delPurchase, addPurchase, updatePurchase } from "@/api/purchase/purchase";
import {listWarehouse_settings} from "@/api/warehouse/warehouse_settings";
import {listSupplier} from "@/api/rchives/supplier";
import {listDeptUser} from "@/api/system/user";
import {listDept, treeselectDept} from "@/api/system/dept";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import {listProducts} from "@/api/rchives/products";
import ScrollPane from "@/layout/components/TagsView/ScrollPane";
import {getToken} from "@/utils/auth";

export default {
  name: "Purchase",
  components: {ScrollPane, Treeselect },
  dicts: ['sys_transfer_or_not','sys_settlement_method','sys_currency_type','sys_payment_collection','sys_supplier_shipping_type','sys_payment_cycle_type','sys_purchase_status'],
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
      // 采购确认表格数据
      purchaseList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      //仓库下拉
      warehouseOptions: undefined,
      //供应商下拉
      supplierOptions: undefined,
      //默认采购员下拉
      userOptions: undefined,
      // 部门树选项
      deptOptions: undefined,
      //部门列表下拉
      deptListOptions: undefined,
      //仓库数组
      warehouseArray: [],
      //供应商数组
      supplierArray: [],
      //默认采购员数组
      userArray: [],
      //部门列表数组
      deptListArray: [],
      // 用户导入参数
      upload: {
        //文件列表
        fileList:[],
        // 是否显示弹出层（采购单导入）
        open: false,
        // 弹出层标题（采购单导入）
        title: "",
        // 是否禁用上传
        isUploading: false,
        // 是否更新已经存在的用户数据
        updateSupport: 0,
        // 设置上传的请求头部
        headers: { Authorization: "Bearer " + getToken() },
        // 上传的地址
        url: process.env.VUE_APP_BASE_API + "/purchase/purchase/importData"
      },
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        purchaseId: null,
        warehouseId: null,
        supplierId: null,
        buyerId: null,
        status:null,
        trackingNumber: null,
        estimatedArrivalTime: null,
        deptId: null,
        remarks: null,
        operatorId: null,
        createTime: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        warehouseId:[{ required: true, message: '请选择采购仓库', trigger: 'blur' }],
        supplierId:[{ required: true, message: '请选择供应商', trigger: 'blur' }],
        buyerId:[{ required: true, message: '请选择采购员', trigger: 'blur' }],
        deptId:[{ required: true, message: '请选择部门', trigger: 'blur' }]
      },
      //当前供应商
      onSupplierId:null,
      //-----------------------产品汇总 start------------------------------
      //SKU种类
      skuNum:null,
      //SKU总数
      skuCount:null,
      //含税金额
      taxTotal:null,
      //不含税金额
      noTaxTotal:null,
      //-----------------------产品汇总 end------------------------------

      //-----------------------选择采购产品 start------------------------------
      //临时选中
      temporary:[],
      // 选中数组
      productsIds: [],
      // 非单个禁用
      productsSingle: true,
      // 非多个禁用
      productsMultiple: true,
      // 总条数
      productsTotal: 0,
      // 产品列表表格数据
      productsList: [],
      //是否显示选择产品弹出层
      openSelectProdcuts:false,
      // 查询参数
      queryParamsProducts: {
        pageNum: 1,
        pageSize: 10,
        sku: null,
        productName: null,
        supplierId: null
      },
      //-----------------------选择采购产品 end------------------------------

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
    this.getDeptWarehouseList();
    this.getSupplierList();
    this.getDeptUserList();
    this.getTreeselectDept();
    this.getDeptList();
    this.getList();
  },
  methods: {
    /** 查询采购确认列表 */
    getList() {
      this.loading = true;
      listPurchase(this.queryParams).then(response => {
        this.purchaseList = response.rows;
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
        purchaseId: null,
        warehouseId: null,
        transferOrNot:"1",
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
        createTime: null,
        updateTime: null,
        params:{}
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
      this.skuNum = this.skuCount = this.taxTotal = this.noTaxTotal = null;
      this.open = true;
      this.title = "添加采购单";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getPurchase(id).then(response => {
        this.selectSupplierQuery(row.supplierId);
        this.form = response.data;
        this.form.transferOrNot = this.form.transferOrNot.toString();
        this.onSupplierId = this.form.supplierId; //当前供应商ID
        this.selectProductsList = response.data.params.productsDetails;
        this.getDetailTotal();
        this.open = true;
        this.title = "修改采购单";
      });
    },
    /** 获取明细汇总 */
    getDetailTotal(){
      this.skuNum = this.skuCount = this.taxTotal = this.noTaxTotal = null;
      let detailData = this.selectProductsList;
      let skuCount = null;
      let taxTotal = null;
      let noTaxTotal = null;
      if(detailData){
        detailData.forEach((val,index)=>{
          let noTax = null;
          let tax = null;
          skuCount += Number(val.quantity);
          noTax += val.quantity*val.price;
          tax += noTax+(noTax*(val.taxRate*0.01));
          noTaxTotal += noTax;
          taxTotal += tax;
        })
        this.skuNum = detailData.length;
        this.skuCount = skuCount;
        this.taxTotal = Number(taxTotal).toFixed(2);
        this.noTaxTotal = Number(noTaxTotal).toFixed(2);
      }
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if(!this.selectProductsList){
            this.$modal.msgError("请添加采购产品");
            return false;
          }
          this.form.params.productsDetails = this.selectProductsList;
          let nullNum = 0;
          this.form.params.productsDetails.forEach( function(item) {
            if(!item.quantity||!item.price){
              nullNum += 1;
            }
          })
          if(nullNum>0){
            this.$modal.msgError("产品采购数量/采购价格不能为空");
            return false;
          }
          if (this.form.purchaseId != null) {
            updatePurchase(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPurchase(this.form).then(response => {
              this.$modal.msgSuccess(response.msg);
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const purchaseIds = row.purchaseId || this.ids;
      this.$modal.confirm('是否确认删除采购单号为"' + purchaseIds + '"的数据项？').then(function() {
        return delPurchase(purchaseIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('purchase/purchase/export', {
        ...this.queryParams
      }, `purchase_${new Date().getTime()}.xlsx`)
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
    /** 查询供应商列表 */
    getSupplierList(){
      listSupplier().then(response => {
        for (let i=0; i<response.rows.length; i++){
          this.supplierArray[response.rows[i]['id']] = response.rows[i];
        }
        this.supplierOptions = response.rows;
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

    /** 采购产品窗口 */
    addProducts(){
      if(this.form.supplierId != null){
        this.openSelectProdcuts = true;
      }else{
        this.$modal.msgError("请先选择供应商");
      }
    },
    /** 采购产品搜索按钮操作 */
    handleQueryProduct(){
      this.queryParamsProducts.pageNum = 1;
      this.getProductsList();
    },
    /** 采购产品重置按钮操作 */
    resetQueryProduct() {
      this.resetForm("queryFormProduct");
      this.handleQueryProduct();
    },
    // 采购产品多选框选中数据
    handleSelectionChangeAddProducts(selection) {
      this.temporary = selection
      this.productsIds = selection.map(item => item.id)
      this.productsSingle = selection.length!==1
      this.productsMultiple = !selection.length
    },

    /** 查询供应商采购产品 */
    selectSupplierQuery(value){
      if(value==""){
        this.form.supplierId = this.productsList = this.productsTotal = this.selectProductsList = null;
      }else{
        this.selectProductsList = null;
        this.queryParamsProducts.supplierId = value;
        this.form.params.isChange = (this.onSupplierId!=value);
        this.getProductsList();
      }
    },

    /** 供应商采购产品列表 */
    getProductsList(){
      listProducts(this.queryParamsProducts).then(response => {
        this.productsList = response.rows;
        this.productsTotal = response.total;
      });
    },

    /** 添加选中的产品 */
    addSelectProducts(){
      let temporary = this.temporary;
      let dataList = [];
      temporary.forEach((item,index)=>{
        if(this.selectProductsList){
          let count = 0;
          this.selectProductsList.forEach((v,i)=>{
            if(item.sku === v.productSku){
              count += 1;
            }
          })
          if(count == 0){
            dataList = {
              id: item.id,
              productSku: item.sku,
              productName: item.productName,
              quantity: null,
              price: item.purchasePrice,
              referencePrice: null,
              taxRate: null,
              externalOrderNo: null,
              remark: null,
              deliveryTime: null,
              params:{
                lastPrice: item.purchasePrice,
                currency: item.currencyId
              }
            }
            this.selectProductsList.push(dataList);
          }
        }else{
          dataList[index] = {
            id: item.id,
            productSku: item.sku,
            productName: item.productName,
            quantity: null,
            price: item.purchasePrice,
            referencePrice: null,
            taxRate: null,
            externalOrderNo: null,
            remark: null,
            deliveryTime: null,
            params:{
              lastPrice: item.purchasePrice,
              currency: item.currencyId
            }
          }
          this.selectProductsList = dataList;
        }
      })
      this.getDetailTotal();
      this.openSelectProdcuts = false;
    },

    closeDialog(){
      this.form.supplierId = this.productsList = this.productsTotal = this.selectProductsList = null;
    },

    // 采购产品多选框选中数据
    handleSelectionChangeProducts(selection) {
      this.temporarySelect = selection
      this.selectProductsIds = selection.map(item => item.id)
      this.selectProductsSingle = selection.length!==1
      this.selectProductsMultiple = !selection.length
    },

    /** 删除采购产品按钮操作 */
    handleDeleteProduct(row,index) {
      let productsIds = row.id || this.selectProductsIds;
      this.$modal.confirm('是否确认删除采购产品编号为"' + productsIds + '"的数据项？').then(function() {
      }).then(() => {
        if(typeof productsIds == "number"){
          this.selectProductsList.splice(index,1);
        }else{
          let val = this.temporarySelect;
          if(val){
            val.forEach((val,index)=>{
              this.selectProductsList.forEach((v,i)=>{
                if(val.id === v.id){
                  this.selectProductsList.splice(i,1);
                }
              })
            })
          }
          this.$refs.multipleTable.clearSelection();
        }
        this.getDetailTotal();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },

    /** 导入按钮操作 */
    handleImport() {
      this.reset();
      this.upload.title = "采购单导入";
      this.upload.open = true;
    },
    /** 下载模板操作 */
    importTemplate() {
      this.download('purchase/purchase/importTemplate', {
      }, `purchase_template_${new Date().getTime()}.xlsx`)
    },
    //文件更改时
    onChange(file,fileList){
      this.upload.fileList = fileList
    },
    // 文件上传中处理
    handleFileUploadProgress(event, file, fileList) {
      this.upload.isUploading = true;
    },
    // 文件上传成功处理
    handleFileSuccess(response, file, fileList) {
      this.upload.fileList = fileList;
      this.upload.open = false;
      this.upload.isUploading = false;
      this.$refs.upload.clearFiles();
      this.$alert("<div style='overflow: auto;overflow-x: hidden;max-height: 70vh;padding: 10px 20px 0;'>" + response.msg + "</div>", "导入结果", { dangerouslyUseHTMLString: true });
      this.getList();
    },
    // 提交上传文件
    submitFileForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if(this.upload.fileList.length==0){
            this.$modal.msgWarning("请上传文件");
          }
          this.$refs.upload.submit();
        }
      });
    }
  }
};
</script>
