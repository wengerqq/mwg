<template>
  <div>
    <!--查看和审批对话框-->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="订单号" prop="orderNo">
          <el-input v-model="form.orderNo" readonly />
        </el-form-item>
        <el-form-item label="采购数量" prop="purchaseNumber">
          <el-input v-model="form.purchaseNumber" readonly />
        </el-form-item>
        <el-form-item label="申请人" prop="applyUserName">
          <el-input v-model="form.applyUserName" readonly />
        </el-form-item>
        <el-form-item label="申请时间" prop="applyTime">
          <el-date-picker clearable size="small" style="width: 200px"
                          v-model="form.applyTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择申请时间"
                          readonly>
          </el-date-picker>
        </el-form-item>
        <el-form-item label="流程实例ID" prop="instanceId">
          <el-input v-model="form.instanceId" readonly />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" readonly />
        </el-form-item>
      </el-form>
      <view-verify
        :open.sync="open"
        :row="row"
        :taskId="taskId"
        :type="type"
      />
    </el-dialog>
  </div>
</template>

<script>
import ViewVerify from "@/components/Activiti/ViewVerify/index";

export default {
  name: "InspectionForm",
  components: {
    ViewVerify,
  },
  props: {
    row: {
      type: Object,
    },
    taskId: {
      type: String,
    },
    taskName: {
      type: String,
    },
    type: {
      type: String,
      default: 'none',
    },
  },
  data() {
    return {
      title: '查看采购质检',
      form: this.row,
      open: false,
    }
  },
}
</script>

<style scoped>

</style>
