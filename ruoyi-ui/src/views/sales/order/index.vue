<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="订单号" prop="orderId">
        <el-input
          v-model="queryParams.orderId"
          placeholder="请输入订单号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-input
          v-model="queryParams.status"
          placeholder="请输入状态"
          clearable
          size="small"
        />
      </el-form-item>
      <el-form-item label="站点" prop="site">
        <el-input
          v-model="queryParams.site"
          placeholder="请输入站点"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="买家代码" prop="buyerCode">
        <el-input
          v-model="queryParams.buyerCode"
          placeholder="请输入买家代码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="买家姓名" prop="buyerName">
        <el-input
          v-model="queryParams.buyerName"
          placeholder="请输入买家姓名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="买家邮箱" prop="buyerEmail">
        <el-input
          v-model="queryParams.buyerEmail"
          placeholder="请输入买家邮箱"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="总金额" prop="total">
        <el-input
          v-model="queryParams.total"
          placeholder="请输入总金额"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="运费" prop="freight">
        <el-input
          v-model="queryParams.freight"
          placeholder="请输入运费"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="总金额" prop="totalFreight">
        <el-input
          v-model="queryParams.totalFreight"
          placeholder="请输入总金额"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="参考号" prop="referenceNumber">
        <el-input
          v-model="queryParams.referenceNumber"
          placeholder="请输入参考号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="仓库号" prop="warehouseNumber">
        <el-input
          v-model="queryParams.warehouseNumber"
          placeholder="请输入仓库号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="发货仓库ID" prop="deliveryWarehouseId">
        <el-input
          v-model="queryParams.deliveryWarehouseId"
          placeholder="请输入发货仓库ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="支付方式ID" prop="paymentMethodId">
        <el-input
          v-model="queryParams.paymentMethodId"
          placeholder="请输入支付方式ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="配送仓库ID" prop="warehouseDeliveryId">
        <el-input
          v-model="queryParams.warehouseDeliveryId"
          placeholder="请输入配送仓库ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="重量" prop="weight">
        <el-input
          v-model="queryParams.weight"
          placeholder="请输入重量"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="收件人税号" prop="recipientTaxNumber">
        <el-input
          v-model="queryParams.recipientTaxNumber"
          placeholder="请输入收件人税号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="客服备注" prop="customerServiceRemarks">
        <el-input
          v-model="queryParams.customerServiceRemarks"
          placeholder="请输入客服备注"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="订单备注" prop="orderRemarks">
        <el-input
          v-model="queryParams.orderRemarks"
          placeholder="请输入订单备注"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="税号所属国家代码" prop="countryCodeOfTaxNumber">
        <el-input
          v-model="queryParams.countryCodeOfTaxNumber"
          placeholder="请输入税号所属国家代码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="收件人" prop="addressee">
        <el-input
          v-model="queryParams.addressee"
          placeholder="请输入收件人"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="手机电话" prop="phone">
        <el-input
          v-model="queryParams.phone"
          placeholder="请输入手机电话"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="公司名" prop="companyName">
        <el-input
          v-model="queryParams.companyName"
          placeholder="请输入公司名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="收件人国家" prop="recipientCountry">
        <el-input
          v-model="queryParams.recipientCountry"
          placeholder="请输入收件人国家"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="收件人省/州" prop="recipientProvinceState">
        <el-input
          v-model="queryParams.recipientProvinceState"
          placeholder="请输入收件人省/州"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="城市" prop="city">
        <el-input
          v-model="queryParams.city"
          placeholder="请输入城市"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="区" prop="district">
        <el-input
          v-model="queryParams.district"
          placeholder="请输入区"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="详细地址" prop="detailedAddress">
        <el-input
          v-model="queryParams.detailedAddress"
          placeholder="请输入详细地址"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="门牌号" prop="houseNumber">
        <el-input
          v-model="queryParams.houseNumber"
          placeholder="请输入门牌号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
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
      <el-form-item label="CPF税号/通关序列号" prop="cpfTaxOrCustomsClearanceSerialNumber">
        <el-input
          v-model="queryParams.cpfTaxOrCustomsClearanceSerialNumber"
          placeholder="请输入CPF税号/通关序列号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="账单地址" prop="billingAddress">
        <el-input
          v-model="queryParams.billingAddress"
          placeholder="请输入账单地址"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="审核时间" prop="auditTime">
        <el-date-picker clearable size="small"
          v-model="queryParams.auditTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择审核时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="发货时间" prop="deliveryTime">
        <el-date-picker clearable size="small"
          v-model="queryParams.deliveryTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择发货时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <el-row>
      <el-col :span="24" style="padding-bottom: 17px;">
        <template v-if="orderSignList!=null">
          <el-menu class="el-menu-demo" mode="horizontal">
            <template v-for="dict in dict.type.sys_order_status">
              <el-submenu :index="dict.value" :class="queryParams.status==dict.value?'select-opt':''" @click.native="handleOpen(dict.value)">
                  <template slot="title">{{ dict.label }}</template>
                  <template v-for="childList in orderSignList[dict.value]">
                    <el-menu-item :index="childList.label" @click="handleSign(childList.dictValue,dict.value)">{{childList.dictLabel}}</el-menu-item>
                  </template>
              </el-submenu>
            </template>
          </el-menu>
        </template>
        <el-row style="padding-top: 12px;">
          <el-col :span="24">
            <template v-for="childList in orderOptList[queryParams.status]">
              <el-button type="success" plain size="mini" @click="optMethodItem(childList.remark,queryParams.status)" style="color: #7D7DFF;background: #ECECFF;border-color: #7D7DFF;">{{childList.dictLabel}}</el-button>
            </template>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
    <el-table v-loading="loading" :data="orderList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
          <el-table-column label="订单详情" align="center">
            <template slot-scope="scope">
              <span>
                订单：<el-button @click="handleUpdate(scope.row)" type="text" style="font-weight: bold;">{{scope.row.orderId}}</el-button><br/>
                买家名称：{{scope.row.buyerName}}<br/>
                Email：{{scope.row.buyerEmail}}<br/>
                参考号：{{scope.row.referenceNumber}}<br/>
                <span style="font-weight: bolder;" v-if="scope.row.signType">自定义标记：<label style="color:red;">{{orderSignList[queryParams.status][scope.row.signType].dictLabel}}</label></span>
              </span>
            </template>
          </el-table-column>
          <el-table-column label="订单明细" align="center" width="600">
            <template slot-scope="scope">
              <el-row v-for="(detail, key) in scope.row.params.orderDetailList" :key="key" :class="scope.row.params.orderDetailList.length==1||key+1==scope.row.params.orderDetailList.length?'no-border':'yes-border'">
                <el-col :span="10">
                  <el-row>
                    <el-col :span="24">
                      <el-row><el-col :span="24">产品名称：{{ detail.productName }}</el-col></el-row>
                      <el-row><el-col :span="24">产品SKU：{{ detail.productSku }}</el-col></el-row>
                      <el-row v-if="detail.abnormal"><el-col :span="24" style="font-weight: bold;">SKU异常：<span style="color: red;">{{ detail.abnormal }}</span></el-col></el-row>
                    </el-col>
                  </el-row>
                </el-col>
                <el-col :span="14">
                  <el-row>
                    <el-col :span="12">数量：{{ detail.quantity }}</el-col>
                    <el-col :span="12">单价：{{ detail.price }}</el-col>
                  </el-row>
                </el-col>
              </el-row>
              <div style="font-weight: bolder;text-align: left;padding: 15px 0px 8px 0px;border-top: 1px solid #E0E0E0;width: 100%;" v-if="scope.row.customerServiceRemarks">客服备注：<label style="color:#1890ff;">{{scope.row.customerServiceRemarks}}</label></div>
              <div style="font-weight: bolder;text-align: left;padding-top:8px;border-top: 1px solid #E0E0E0;width: 100%;" v-if="scope.row.customerServiceRemarks">异常信息：<label style="color:red;">{{scope.row.abnormal}}</label></div>
            </template>
          </el-table-column>
          <el-table-column label="订单金额" align="center">
            <template slot-scope="scope">
              <span>
                总金额（含运费）：{{scope.row.totalFreight}}<br/>
                交易额：{{scope.row.total}}<br/>
                运费：{{scope.row.freight}}
              </span>
            </template>
          </el-table-column>
          <el-table-column label="发货信息" align="center">
            <template slot-scope="scope">
              <span>
                国家或地区:{{scope.row.recipientCountry}}<br/>
                发运仓库:{{ warehouseArray[scope.row.deliveryWarehouseId]?warehouseArray[scope.row.deliveryWarehouseId].warehouseName:scope.row.deliveryWarehouseId }}<br/> <!--未分配-->
                仓库配送:{{dict.type.sys_shipping_type[scope.row.warehouseDeliveryId]?dict.type.sys_shipping_type[scope.row.warehouseDeliveryId].label:scope.row.warehouseDeliveryId}}<br/> <!--未分配-->
              </span>
            </template>
          </el-table-column>
          <el-table-column label="日期" align="center">
            <template slot-scope="scope">
              <span>
                创建:{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}<br/>
                审核:{{ parseTime(scope.row.auditTime, '{y}-{m}-{d}') }}<br/>
                发货:{{ parseTime(scope.row.deliveryTime, '{y}-{m}-{d}') }}<br/>
              </span>
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

    <!-- 添加或修改订单管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="80%" class="info" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="3"><div class="grid-content bg-purple">订单</div></el-col>
          <el-col :span="5"><div class="grid-content bg-purple-light">{{form.orderId}}</div></el-col>
          <el-col :span="3"><div class="grid-content bg-purple">买家ID</div></el-col>
          <el-col :span="5">
            <div class="grid-content bg-purple-light">
                <el-input v-model="form.buyerCode" placeholder="买家ID"/>
            </div>
          </el-col>
          <el-col :span="3"><div class="grid-content bg-purple">买家姓名</div></el-col>
          <el-col :span="5"><div class="grid-content bg-purple-light">{{form.buyerName}}</div></el-col>
        </el-row>
        <el-row>
          <el-col :span="3"><div class="grid-content bg-purple">站点</div></el-col>
          <el-col :span="5"><div class="grid-content bg-purple-light">{{form.site}}</div></el-col>
          <el-col :span="3"><div class="grid-content bg-purple">Email</div></el-col>
          <el-col :span="5">
            <div class="grid-content bg-purple-light">
              <el-input v-model="form.buyerEmail" placeholder="Email"/>
            </div>
          </el-col>
          <el-col :span="3"><div class="grid-content bg-purple">参考号</div></el-col>
          <el-col :span="5"><div class="grid-content bg-purple-light">{{form.referenceNumber}}</div></el-col>
        </el-row>
        <el-row>
          <el-col :span="3"><div class="grid-content bg-purple">交易额</div></el-col>
          <el-col :span="5"><div class="grid-content bg-purple-light">{{form.total}}</div></el-col>
          <el-col :span="3"><div class="grid-content bg-purple">运费</div></el-col>
          <el-col :span="5"><div class="grid-content bg-purple-light">{{form.freight}}</div></el-col>
          <el-col :span="3"><div class="grid-content bg-purple">总金额（含运费）</div></el-col>
          <el-col :span="5"><div class="grid-content bg-purple-light">{{form.totalFreight}}</div></el-col>
        </el-row>
        <el-row>
          <el-col :span="3"><div class="grid-content bg-purple">仓库号</div></el-col>
          <el-col :span="5"><div class="grid-content bg-purple-light">{{form.warehouseNumber}}</div></el-col>
          <el-col :span="3"><div class="grid-content bg-purple">发货仓库</div></el-col>
          <el-col :span="5"><div class="grid-content bg-purple-light">{{ warehouseArray[form.deliveryWarehouseId]?warehouseArray[form.deliveryWarehouseId].warehouseName:form.deliveryWarehouseId }}</div></el-col>
          <el-col :span="3"><div class="grid-content bg-purple">仓库配送</div></el-col>
          <el-col :span="5"><div class="grid-content bg-purple-light">{{dict.type.sys_shipping_type[form.warehouseDeliveryId]?dict.type.sys_shipping_type[form.warehouseDeliveryId].label:form.warehouseDeliveryId}}</div></el-col>
        </el-row>
        <el-row>
          <el-col :span="3"><div class="grid-content bg-purple">支付方式</div></el-col>
          <el-col :span="5"><div class="grid-content bg-purple-light">{{dict.type.sys_payment_collection[form.paymentMethodId]?dict.type.sys_payment_collection[form.paymentMethodId].label:form.paymentMethodId}}</div></el-col>
          <el-col :span="3"><div class="grid-content bg-purple">重量</div></el-col>
          <el-col :span="5"><div class="grid-content bg-purple-light">{{form.weight}}</div></el-col>
          <el-col :span="3"><div class="grid-content bg-purple">收件人税号</div></el-col>
          <el-col :span="5">
            <div class="grid-content bg-purple-light">
              <el-input v-model="form.recipientTaxNumber" placeholder="收件人税号"/>
            </div>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="3"><div class="grid-content bg-purple">客服备注</div></el-col>
          <el-col :span="21">
            <div class="grid-content bg-purple-light">
              <el-input v-model="form.customerServiceRemarks" placeholder="客服备注"/>
            </div>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="3"><div class="grid-content bg-purple">订单备注</div></el-col>
          <el-col :span="21"><div class="grid-content bg-purple-light">{{form.orderRemarks}}</div></el-col>
        </el-row>
        <el-row class="info-bottom">
          <el-col :span="3"><div class="grid-content bg-purple">税号所属国家代码</div></el-col>
          <el-col :span="21">
            <div class="grid-content bg-purple-light">
              <el-input v-model="form.countryCodeOfTaxNumber" placeholder="税号所属国家代码"/>
            </div>
          </el-col>
        </el-row>
        <br/><br/>
        <el-row>
          <el-col :span="3"><div class="grid-content bg-purple">收件人</div></el-col>
          <el-col :span="5">
            <div class="grid-content bg-purple-light">
              <el-input v-model="form.addressee" placeholder="收件人"/>
            </div>
          </el-col>
          <el-col :span="3"><div class="grid-content bg-purple">手机号码/电话</div></el-col>
          <el-col :span="5">
            <div class="grid-content bg-purple-light">
              <el-input v-model="form.phone" placeholder="手机号码/电话"/>
            </div>
          </el-col>
          <el-col :span="3"><div class="grid-content bg-purple">公司名</div></el-col>
          <el-col :span="5">
            <div class="grid-content bg-purple-light">
              <el-input v-model="form.companyName" placeholder="公司名"/>
            </div>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="3"><div class="grid-content bg-purple">收件人国家</div></el-col>
          <el-col :span="5">
            <div class="grid-content bg-purple-light">
              <el-input v-model="form.recipientCountry" placeholder="收件人国家"/>
            </div>
          </el-col>
          <el-col :span="3"><div class="grid-content bg-purple">收件人省/州</div></el-col>
          <el-col :span="5">
            <div class="grid-content bg-purple-light">
              <el-input v-model="form.recipientProvinceState" placeholder="收件人省/州"/>
            </div>
          </el-col>
          <el-col :span="3"><div class="grid-content bg-purple">城市</div></el-col>
          <el-col :span="5">
            <div class="grid-content bg-purple-light">
              <el-input v-model="form.city" placeholder="城市"/>
            </div>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="3"><div class="grid-content bg-purple">区</div></el-col>
          <el-col :span="5">
            <div class="grid-content bg-purple-light">
              <el-input v-model="form.district" placeholder="区"/>
            </div>
          </el-col>
          <el-col :span="3"><div class="grid-content bg-purple">详细地址</div></el-col>
          <el-col :span="5">
            <div class="grid-content bg-purple-light">
              <el-input v-model="form.detailedAddress" placeholder="详细地址"/>
            </div>
          </el-col>
          <el-col :span="3"><div class="grid-content bg-purple">门牌号</div></el-col>
          <el-col :span="5">
            <div class="grid-content bg-purple-light">
              <el-input v-model="form.houseNumber" placeholder="门牌号"/>
            </div>
          </el-col>
        </el-row>
        <el-row class="info-bottom">
          <el-col :span="3"><div class="grid-content bg-purple">邮编</div></el-col>
          <el-col :span="5">
            <div class="grid-content bg-purple-light">
              <el-input v-model="form.zipCode" placeholder="邮编"/>
            </div>
          </el-col>
          <el-col :span="3"><div class="grid-content bg-purple">CPF税号/通关序列号</div></el-col>
          <el-col :span="5">
            <div class="grid-content bg-purple-light">
              <el-input v-model="form.cpfTaxOrCustomsClearanceSerialNumber" placeholder="CPF税号/通关序列号"/>
            </div>
          </el-col>
          <el-col :span="3"><div class="grid-content bg-purple">账单地址</div></el-col>
          <el-col :span="5">
            <div class="grid-content bg-purple-light">
              <el-input v-model="form.billingAddress" placeholder="账单地址"/>
            </div>
          </el-col>
        </el-row>
        <br/><br/>
        <el-row>
          <el-col :span="24">
            <el-table :data="orderDetail" style="width: 100%">
              <el-table-column label="产品SKU">
                <template slot-scope="scope">
                  {{scope.row.productSku}}
                </template>
              </el-table-column>
              <el-table-column label="产品名称">
                <template slot-scope="scope">
                  {{scope.row.productName}}
                </template>
              </el-table-column>
              <el-table-column label="数量">
                <template slot-scope="scope">
                  {{scope.row.quantity}}
                </template>
              </el-table-column>
              <el-table-column label="单价">
                <template slot-scope="scope">
                  {{scope.row.price}}
                </template>
              </el-table-column>
              <el-table-column label="库存">
                <template slot-scope="scope">
                  {{scope.row.stock}}
                </template>
              </el-table-column>
              <el-table-column label="备注">
                <template slot-scope="scope">
                  {{scope.row.remarks}}
                </template>
              </el-table-column>
            </el-table>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="submitForm">保 存 修 改</el-button>
          <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 自定义标记 -->
    <el-dialog
      title="自定义标记"
      :visible.sync="dialogVisibleSign"
      width="25%"
      @close="closeSign">
      <div style="padding:0px 13px;">
          <div style="display: flex;align-items: center;justify-content: space-between;">
            <span style="padding-right: 20px;">A、新增加一个标记</span>
            <el-input v-model="addSign" name="addSign" placeholder="请输入标记" style="width: 270px;"></el-input>
          </div>
          <div>
            <p>B、选择已有标记</p>
            <div style="border: 2px solid rgba(238, 238, 238, 1);">
              <el-table
                :data="alreadyMarkedList"
                stripe
                :show-header="false"
                style="width: 100%">
                <el-table-column label="标记名称">
                  <template slot-scope="scope">
                    <div style="cursor: pointer;" @click="checkMark(scope.row)" :id="'select-sign-'+scope.row.dictValue"><el-button type="text">{{ scope.row.dictLabel }}</el-button></div>
                  </template>
                </el-table-column>
              </el-table>
            </div>
          </div>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisibleSign = false">取 消</el-button>
        <el-button type="primary" @click="okSign">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 客服备注 -->
    <el-dialog
      title="客服备注"
      :visible.sync="dialogVisibleRemarks"
      width="25%"
      @close="remarks = null">
      <div>
        <el-input
          type="textarea"
          :rows="4"
          placeholder="请填写客服备注"
          v-model="remarks">
        </el-input>
        <p style="color:red;font-size: 12px;">注意：批量备注，若存在历史备注，将会被覆盖，备注为空时，将删除现有备注。</p>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisibleRemarks = false">取 消</el-button>
        <el-button type="primary" @click="okRemarks">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 发货审核 -->
    <el-dialog
      title="订单审核"
      :visible.sync="dialogVisibleDa"
      width="30%"
      class="da"
      @close="closeDa">
      <div>
        <p style="padding-bottom: 15px;border-bottom: 1px solid #dcdcdc;font-size: 16px;">仓库及运输方式选择：</p>
        <el-radio v-model="selectDa" label="1" @change="showSelect" style="padding-left: 25px;">按已分配仓库</el-radio>
        <el-radio v-model="selectDa" label="2" @change="showSelect" style="display: block;padding: 18px 0px 0px 25px;">重新指定仓库</el-radio>
        <div :class="selectDa=='2'?'select-show':'select-hidden'" style="padding: 20px 0px 0px 50px;">
          <el-select placeholder="请选择仓库" v-model="form.deliveryWarehouseId" @change="daSelectWarehouse" clearable size="small" style="width: 200px">
            <el-option
              v-for="(warehouse,index) in warehouseOptions"
              :key="warehouse.id"
              :label="warehouse.warehouseName"
              :value="warehouse.id"
            />
          </el-select>
          <el-select placeholder="请选择运输方式" v-model="form.warehouseDeliveryId" @change="daSelectShippingType" clearable size="small" style="padding-left: 15px;">
            <el-option
              v-for="item in dict.type.sys_shipping_type"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </div>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisibleDa = false">取 消</el-button>
        <el-button type="primary" @click="okDa">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 截单 -->
    <el-dialog
      title="截单"
      :visible.sync="dialogVisibleCut"
      width="30%"
      @close="closeCut">
      <div>
        <div>
          <el-checkbox v-model="cutChecked" @change="isChecked">确认服务商已删除该订单</el-checkbox>
        </div>
        <div style="padding:20px 0px;">
          截单原因：
          <el-select v-model="interceptReason" @change="form.abnormal = interceptReason" placeholder="请选择" style="width: 170px;margin-right: 20px;">
            <el-option
              v-for="item in dict.type.cut_order_reason"
              :key="item.value"
              :label="item.label"
              :value="item.label">
            </el-option>
          </el-select>
          自定义标记：
          <el-select v-model="signId" @change="form.signType = signId" placeholder="请选择" style="width: 170px;">
            <el-option
              v-for="childList in orderSignList[6]"
              :key="childList.dictValue"
              :label="childList.dictLabel"
              :value="childList.dictValue">
            </el-option>
          </el-select>
        </div>
        <div>
          <el-input
            type="textarea"
            :rows="4"
            placeholder="请输入内容"
            v-model="interceptReason">
          </el-input>
        </div>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="okCut">截单后转问题件</el-button>
        <el-button @click="dialogVisibleCut = false">关 闭</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<style type="text/css">
  .yes-border{padding: 10px 10px;border-bottom: 1px solid #E0E0E0}
  .no-border{padding: 10px 10px}
  .info .el-row{border-top: 1px solid #BEBEBE;border-left: 1px solid #BEBEBE;}
  .info .el-row .el-col{border-right: 1px solid #ccc;}
  .grid-content{height: 40px;line-height: 40px;}
  .grid-content .el-input--medium .el-input__inner{background-color: white;height: 31px;}
  .bg-purple{text-align: right;padding-right:8px;background-color: #d0d0d0;}
  .bg-purple-light{text-align: left;padding:0px 8px;background-color: #F0F0F0;}
  .info-bottom{border-bottom: 1px solid #BEBEBE;}
  .dialog-footer{text-align: center;}
  .el-menu--horizontal > .el-submenu .el-submenu__icon-arrow{display: none;}
  .select-opt{border-bottom: 2px solid #409eff;}
  .select-sign{background: #ECECFF !important;}
  .da .el-dialog__body{padding:0px 20px 30px 20px; }
  .select-show{display: block;}
  .select-hidden{display: none;}
  .msgbox{width: 450px;}
</style>
<script>
import { listOrder, getOrder, delOrder, addOrder, updateOrder, getOrderStatusList, updateOrderSign, updateOrderRemarks, updateOrderDa, updateOrderStatus, updateOrderCut } from "@/api/sales/order";
import {listWarehouse_settings} from "@/api/warehouse/warehouse_settings";
import { addData } from "@/api/system/dict/data";

export default {
  name: "Order",
  dicts: ['sys_shipping_type','sys_payment_collection','sys_order_status','sys_shipping_type','cut_order_reason'],
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
      // 订单管理表格数据
      orderList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      /*********************************数据源-start**************************************/
      //仓库下拉
      warehouseOptions: undefined,
      //仓库数组
      warehouseArray: [],
      /*********************************数据集-end**************************************/
      //订单详情
      orderDetail:[],
      //订单操作列表
      orderOptList:null,
      //订单标记列表
      orderSignList:null,
      //订单状态列表
      signType:null,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        orderId: null,
        status:1,
        site: null,
        buyerCode: null,
        buyerName: null,
        buyerEmail: null,
        total: null,
        freight: null,
        totalFreight: null,
        referenceNumber: null,
        warehouseNumber: null,
        deliveryWarehouseId: null,
        paymentMethodId: null,
        warehouseDeliveryId: null,
        weight: null,
        recipientTaxNumber: null,
        customerServiceRemarks: null,
        orderRemarks: null,
        countryCodeOfTaxNumber: null,
        addressee: null,
        phone: null,
        companyName: null,
        recipientCountry: null,
        recipientProvinceState: null,
        city: null,
        district: null,
        detailedAddress: null,
        houseNumber: null,
        zipCode: null,
        cpfTaxOrCustomsClearanceSerialNumber: null,
        billingAddress: null,
        signType: null,
        auditTime: null,
        deliveryTime: null,
      },
      // 表单参数
      form: {
        params:{
          ids:null
        }
      },
      // 表单校验
      rules: {
      },
      /** 自定义标记 */
      dialogVisibleSign: false,
      alreadyMarkedList:[],
      selectSign:null,
      addSign:null,
      /** 客服备注 */
      dialogVisibleRemarks: false,
      remarks:null,
      /** 发货审核 */
      dialogVisibleDa:false,
      selectDa:"1",
      /** 截单 */
      dialogVisibleCut:false,
      interceptReason:null,
      signId:null,
      cutChecked:false
    };
  },
  created() {
    this.getOrderStatusLists();
    this.getDeptWarehouseList();
    this.getList();
  },
  methods: {
    /** 查询订单管理列表 */
    getList() {
      this.getOrderStatusLists();
      this.getDeptWarehouseList();
      this.loading = true;
      listOrder(this.queryParams).then(response => {
        this.orderList = response.rows;
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
        orderId: null,
        status: 1,
        site: null,
        buyerCode: null,
        buyerName: null,
        buyerEmail: null,
        total: null,
        freight: null,
        totalFreight: null,
        referenceNumber: null,
        warehouseNumber: null,
        deliveryWarehouseId: null,
        paymentMethodId: null,
        warehouseDeliveryId: null,
        weight: null,
        recipientTaxNumber: null,
        customerServiceRemarks: null,
        orderRemarks: null,
        countryCodeOfTaxNumber: null,
        addressee: null,
        phone: null,
        companyName: null,
        recipientCountry: null,
        recipientProvinceState: null,
        city: null,
        district: null,
        detailedAddress: null,
        houseNumber: null,
        zipCode: null,
        cpfTaxOrCustomsClearanceSerialNumber: null,
        billingAddress: null,
        sign: null,
        auditTime: null,
        deliveryTime: null,
        createTime: null
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
      this.title = "添加订单管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getOrder(id).then(response => {
        this.form = response.data;
        this.orderDetail = response.data.params.orderDetailList;
        this.open = true;
        this.title = "订单详情";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateOrder(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addOrder(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除订单管理编号为"' + ids + '"的数据项？').then(function() {
        return delOrder(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('sales/order/export', {
        ...this.queryParams
      }, `order_${new Date().getTime()}.xlsx`)
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
    /** 查询订单状态数据 */
    getOrderStatusLists(){
      getOrderStatusList().then(response => {
        this.orderOptList = response.data.optData;
        this.orderSignList = response.data.signData;
        this.signType = response.data.signType;
      });
    },
    /** 切换标签 */
    handleOpen(value) {
      this.addSign = this.selectSign = this.queryParams.signType = null;
      this.queryParams.status = value;
      this.getList();
    },
    /** 标记查询 */
    handleSign(signType,status){
      this.queryParams.signType = signType;
      this.queryParams.status = status;
      this.getList();
    },
    /** 选中标记 */
    checkMark(row){
      this.selectSign = row.dictValue;
      let childs = document.getElementById("select-sign-"+row.dictValue).parentNode.parentNode.parentNode.parentNode.childNodes;
      for(let i = 0; i < childs.length-1; i++) {
        childs[i].children[0].classList.remove("select-sign");
      }
      document.getElementById("select-sign-"+row.dictValue).parentNode.parentNode.classList.add("select-sign");
    },
    /** 发货审核 */
    //显示仓库和运输方式选择器
    showSelect(val){
      this.selectDa = val;
    },
    //选中仓库
    daSelectWarehouse(val){
      this.form.deliveryWarehouseId = val;
    },
    //选中运输方式
    daSelectShippingType(val){
      this.form.warehouseDeliveryId = val;
    },
    //提交发货审核
    okDa(){
      if((this.form.deliveryWarehouseId == null || this.form.warehouseDeliveryId == null) && this.selectDa=="2"){
        this.$modal.msgError("请选择仓库和运输方式");
        return false;
      }
      this.form.params.ids = this.ids;
      updateOrderDa(this.form).then(response => {
        let msg = "";
        for(let i=0;i<response.data.length;i++){
          msg += response.data[i];
        }
        this.$alert(msg, '处理结果', {
          customClass:'msgbox',
          dangerouslyUseHTMLString: true
        });
        this.dialogVisibleDa = false;
        this.getList();
      });
    },
    //关闭窗口
    closeDa(){
      this.selectDa = '1';
      this.form.deliveryWarehouseId = this.form.warehouseDeliveryId = null;
    },
    /** 客服备注 */
    //客服备注提交
    okRemarks(){
      let data = {
        ids:this.ids,
        remarks:this.remarks
      }
      updateOrderRemarks(data).then(response => {
        this.$modal.msgSuccess("备注成功");
        this.dialogVisibleRemarks = false;
        this.getList();
      });
    },
    /** 自定义标记 */
    //自定义标记提交
    okSign(){
      if(this.addSign==null&&this.selectSign==null){
        this.$modal.msgError("请选择或新增自定义标签");
        return false;
      }
      let signType = null;
      if(this.addSign!=null){
        this.selectSign = null;
        let dictValue = this.orderSignList[this.queryParams.status].length;
        let data ={
          dictLabel:this.addSign,
          dictValue:dictValue.toString(),
          dictType:this.signType[this.queryParams.status],
          listClass:"default"
        }
        addData(data).then(response => {
          this.$modal.msgSuccess("新增成功");
          this.getList();
        });
        signType = this.orderSignList[this.queryParams.status].length;
      }else{
        signType = this.selectSign;
      }
      let data = {
        ids:this.ids,
        signType:signType
      }
      updateOrderSign(data).then(response => {
        this.$modal.msgSuccess("设置成功");
        this.dialogVisibleSign = false;
        this.getList();
      });
    },
    //标记窗口关闭时调用
    closeSign(){
      this.selectSign = this.addSign = null;
      document.getElementsByClassName("select-sign")[0].classList.remove("select-sign");
    },
    /** 截单 */
    //关闭截单弹窗事件
    closeCut(){
      this.interceptReason = this.signId = null;
      this.cutChecked = false;
    },
    //选中改变事件
    isChecked(event){
      this.cutChecked = event;
    },
    //截单提交
    okCut(){
      if(!this.cutChecked){
        this.$confirm('您还未勾选确认服务商是否已删除该订单，是否确认？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.cutChecked = true;
        }).catch(() => {
          this.cutChecked = false;
        });
        return false;
      }
      this.form.params.ids = this.ids;
      updateOrderCut(this.form).then(response => {
        let msg = "";
        for(let i=0;i<response.data.length;i++){
          msg += response.data[i];
        }
        this.$alert(msg, '处理结果', {
          customClass:'msgbox',
          dangerouslyUseHTMLString: true
        });
        this.dialogVisibleCut = false;
        this.getList();
      });
    },
    /** 待发货审核 */
    /** 待发货 */
    /** 已发货 */
    //转冻结
    transferFreezing(status){
      this.updateOrderStatus(4);
    },
    /** 冻结中 */
    //转已发货
    turnShipped(status){
      alert("转已发货"+status);
    },
    /** 缺货 */
    //解锁库存
    unlockInventory(status){
      alert("解锁库存"+status);
    },
    //锁定库存
    lockInventory(status){
      alert("锁定库存"+status);
    },
    /** 公共方法 */
    //发货审核
    deliveryAudit(status){
      if(!this.ids.length){
        this.$modal.msgError("请先选择订单");
        return false;
      }
      this.dialogVisibleDa = true;
    },
    //转待发货审核
    turnDeliveryAudit(status){
      alert("转待发货审核"+status);
    },
    //冻结
    frozen(status){
      this.updateOrderStatus(4);
    },
    //截单
    cutOrder(status){
      if(!this.ids.length){
        this.$modal.msgError("请先选择订单");
        return false;
      }
      this.dialogVisibleCut = true;
    },
    //作废
    toVoid(status){
      this.updateOrderStatus(7)
    },
    //客服备注
    csRemarks(status){
      if(!this.ids.length){
        this.$modal.msgError("请先选择订单");
        return false;
      }
      this.dialogVisibleRemarks = true;
    },
    //自定义标记
    openSignDialog(status){
      if(!this.ids.length){
        this.$modal.msgError("请先选择订单");
        return false;
      }
      this.alreadyMarkedList = this.orderSignList[status]
      this.dialogVisibleSign = true;
    },

    /** 操作方法集合 */
    optMethodItem(method,status){
      switch (method){
        //已发货
        case "transferFreezing":
          this.transferFreezing(status);
          break;
        //冻结中
        case "turnShipped":
          this.turnShipped(status);
          break;
        //缺货
        case "unlockInventory":
          this.unlockInventory(status);
          break;
        case "lockInventory":
          this.lockInventory(status);
          break;
        //公共方法
        case "deliveryAudit":
          this.deliveryAudit(status);
          break;
        case "turnDeliveryAudit":
          this.turnDeliveryAudit(status);
          break;
        case "frozen":
          this.frozen(status);
          break;
        case "cutOrder":
          this.cutOrder(status);
          break;
        case "toVoid":
          this.toVoid(status);
          break;
        case "csRemarks":
          this.csRemarks(status);
          break;
        case "openSignDialog":
          this.openSignDialog(status);
          break;
        default:;
      }
    },

    //更新订单状态
    updateOrderStatus(type){
      let msg = "";
      switch (type){
        case 4:msg="冻结";break;
        case 7:msg="废弃";break;
        default:;
      }
      if(!this.ids.length){
        this.$modal.msgError("请先选择订单");
        return false;
      }
      this.$confirm('确定执行冻结操作吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.form.status = type;
        this.form.signType = 0;
        this.form.params.ids = this.ids;
        updateOrderStatus(this.form).then(response => {
          this.$message({
            type: 'success',
            message: '已'+msg
          });
          this.getList();
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消'
        });
      });
    }
  }
};
</script>
