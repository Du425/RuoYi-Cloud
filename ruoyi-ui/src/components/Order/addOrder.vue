<template>
  <!-- 添加或修改 对话框 -->
  <el-dialog :title="title" :visible.sync="open" :close-on-click-modal="false" width="500px" append-to-body>
    <el-form ref="form" :model="this.$props.form" :rules="rules" label-width="80px">
      <el-form-item label="房间Id" prop="roomId">
        <el-input v-model="this.$props.form.roomId" disabled />
      </el-form-item>
      <el-form-item label="数量" prop="quantity">
        <el-input v-model="this.$props.form.quantity" placeholder="请输入数量" />
      </el-form-item>
      <el-form-item label="用户名" prop="username">
        <el-input v-model="this.$props.form.username" placeholder="请输入用户名" />
      </el-form-item>
      <el-form-item label="手机号" prop="phone">
        <el-input v-model="this.$props.form.phone" placeholder="请输入手机号" />
      </el-form-item>
      <el-form-item label="身份证" prop="idCard">
        <el-input v-model="this.$props.form.idCard" placeholder="请输入身份证" />
      </el-form-item>
      <el-form-item label="单价" prop="price">
        <el-input v-model="this.$props.form.price" disabled />
      </el-form-item>
      <el-form-item label="总价" prop="totalPrice">
        <el-input v-model="this.$props.form.totalPrice" placeholder="请输入总价" />
      </el-form-item>
      <el-form-item label="房间数量" prop="roomNumber">
        <el-input v-model="this.$props.form.roomNumber" placeholder="请输入房间数量" />
      </el-form-item>
      <el-form-item label="入住日期" prop="checkinDate">
        <el-date-picker clearable
                        v-model="this.$props.form.checkinDate"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择入住时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="离店日期" prop="checkoutDate">
        <el-date-picker clearable
                        v-model="this.$props.form.checkoutDate"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择离店时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="房型Id" prop="roomTypeId">
        <el-input v-model="this.$props.form.roomTypeId" placeholder="请输入房型Id" />
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="submitForm">确 定</el-button>
      <el-button @click="cancel">取 消</el-button>
    </div>
  </el-dialog>
</template>

<script>
import {addOrder} from "@/api/system/order";

export default {
  name: "addOrder",
  props: {
    form: Array
  },
  methods: {
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if(valid) {
          addOrder(this.form).then(response => {
            this.$modal.msgSuccess("下单成功！");
            this.open = false;
            this.getList();
          });
        }
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
  }
}
</script>

<style scoped>

</style>
