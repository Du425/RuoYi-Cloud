<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="房间Id" prop="roomId">
        <el-input
          v-model="queryParams.roomId"
          placeholder="请输入房间Id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户名" prop="username">
        <el-input
          v-model="queryParams.username"
          placeholder="请输入用户名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="手机号" prop="phone">
        <el-input
          v-model="queryParams.phone"
          placeholder="请输入用户手机号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="身份证" prop="idCard">
        <el-input
          v-model="queryParams.idCard"
          placeholder="请输入用户身份证"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间" prop="createTime">
        <el-date-picker clearable
          v-model="queryParams.createTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="房间类型" prop="roomCode">
        <el-input
          v-model="queryParams.roomCode"
          placeholder="请输入房间类型"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['system:order:add']"
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
          v-hasPermi="['system:order:edit']"
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
          v-hasPermi="['system:order:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:order:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="orderList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="订单号" align="center" prop="id" />
      <el-table-column label="房间Id" align="center" prop="roomId" />
      <el-table-column label="房间数量" align="center" prop="quantity" />
      <el-table-column label="订单状态" align="center" prop="status" />
      <el-table-column label="用户名" align="center" prop="username" />
      <el-table-column label="手机号" align="center" prop="phone" />
      <el-table-column label="身份证" align="center" prop="idCard" />
      <el-table-column label="单价" align="center" prop="price" />
      <el-table-column label="总价" align="center" prop="totalPrice" />
      <el-table-column label="房间类型" align="center" prop="roomCode" />
      <el-table-column label="入住日期" align="center" prop="checkinDate"/>
      <el-table-column label="离店日期" align="center" prop="checkoutDate"/>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="订单入住时长" align="center" prop="orderDays" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:order:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:order:remove']"
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

    <!-- 添加或修改 对话框 -->
    <el-dialog :title="title" :visible.sync="open" :close-on-click-modal="false" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="房间Id" prop="roomId">
          <el-input v-model="form.roomId" placeholder="请输入房间Id" />
        </el-form-item>
        <el-form-item label="房型" prop="roomCode">
          <el-input v-model="form.roomCode" placeholder="请输入房型" />
        </el-form-item>
        <el-form-item label="预定房间数量" prop="quantity">
          <el-input-number v-model="form.quantity" :min=1></el-input-number>
        </el-form-item>
        <el-form-item label="用户名" prop="username">
          <el-input v-model="form.username" placeholder="请输入用户名" />
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入手机号" />
        </el-form-item>
        <el-form-item label="身份证" prop="idCard">
          <el-input v-model="form.idCard" placeholder="请输入身份证" />
        </el-form-item>
        <el-form-item label="单价" prop="price">
          <el-input v-model="form.price" placeholder="请输入房间价格" />
        </el-form-item>
        <el-form-item label="总价" prop="totalPrice">
<!--          订单天数，房间数量都要计算-->
          <el-input v-model="form.price * form.quantity" placeholder="请输入总价" />
        </el-form-item>
        <el-form-item label="入住日期" prop="checkinDate">
          <el-date-picker clearable
                          v-model="form.checkinDate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择入住时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="离店日期" prop="checkoutDate">
          <el-date-picker clearable
                          v-model="form.checkoutDate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择离店时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="订单状态" prop="status">
          <el-select v-model="form.status" placeholder="请选择订单状态" >
            <el-option label="支付成功" value="支付成功"></el-option>
            <el-option label="入住成功" value="入住成功"></el-option>
            <el-option label="已离店" value="已离店"></el-option>
            <el-option label="订单取消" value="订单取消"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listOrder, getOrder, delOrder, addOrder, updateOrder , listOrderRole } from "@/api/system/order";

export default {
  name: "Order",
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
      //  表格数据
      orderList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        roomId: null,
        userId: null,
        quantity: null,
        status: null,
        roomCode: null,
        username: null,
        phone: null,
        idCard: null,
        price: null,
        totalPrice: null,
        roomNumber: null,
        createTime: null,
        orderType: null,
        roomTypeId: null,
        orderDays: null
      },
      // 表单参数
      form: {
      },
      // 表单校验
      rules: {
        idCard: [
          { required: true, message: '请输入手机号', trigger: 'blur' },
          {
            validator: (rule, value, callback) => {
              const reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
              if (!reg.test(value)) {
                callback(new Error('请输入正确的身份证号码'));
              } else {
                callback();
              }
            },
            trigger: 'blur'
          }
        ],
        phone: [
          { required: true, message: '请输入身份证', trigger: 'blur' },
          {
            validator: (rule, value, callback) => {
              const reg = /^1(3\d|4[5-9]|5[0-35-9]|6[567]|7[0-8]|8\d|9[0-35-9])\d{8}$/;
              if (!reg.test(value)) {
                callback(new Error('请输入正确的手机号码'));
              } else {
                callback();
              }
            },
            trigger: 'blur'
          }
        ]
      }
    };
  },
  created() {
    this.getListByRole();
  },
  methods: {
    /** 查询 列表 */
    getListByRole() {
      this.loading = true;
      listOrderRole().then(response => {
        this.orderList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    getList() {
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
        roomId: null,
        userId: null,
        quantity: null,
        status: null,
        username: null,
        phone: null,
        idCard: null,
        price: null,
        totalPrice: null,
        roomCode: null,
        roomNumber: null,
        checkinDate: null,
        checkoutDate: null,
        createTime: null,
        updateTime: null,
        orderType: null,
        roomTypeId: null,
        orderDays: null
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
      this.title = "添加";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getOrder(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            console.log(this.form)
            this.form.totalPrice = this.form.price * this.form.quantity
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
      this.$modal.confirm('是否确认删除 编号为"' + ids + '"的数据项？').then(function() {
        return delOrder(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/order/export', {
        ...this.queryParams
      }, `order_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
