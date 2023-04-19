<template>
  <div class="app-container home">

    <el-form>
      <el-form-item label="请选择入住时间">
        <el-date-picker
          v-model="dateRange"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="--"
          start-placeholder="入住日期"
          end-placeholder="离店日期"
        ></el-date-picker>
      </el-form-item>
    </el-form>

    <el-row>
      <el-col :span="11" v-for="(room, index) in roomList" :key="room.id" :offset="index % 2 === 1 ? 2 : 0" v-if="index % 2 === 0">
        <el-card :body-style="{padding: '0px'}">
          <el-image :src="room.imgUrl" fit="contain"  ></el-image>
          <div>
            <el-tag>价格：{{ room.price }}</el-tag>
            <h3>房型：{{ room.title }}</h3>
            <h3>入住人数：{{ room.code}}</h3>
            <div class="bottom clearfix">
              <el-button type="primary"
                         class="button "
                         plain
                         @click="handleAdd(room)"
                         icon="el-icon-plus"
                         v-hasPermi="['system:order:add']"
              >预定</el-button>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="11" v-for="(room, index) in roomList" :key="room.id" :offset="index % 2 === 0 ? 2 : 0" v-if="index % 2 === 1">
        <el-card :body-style="{padding: '0px'}">
          <el-image :src="room.imgUrl" fit="contain"  ></el-image>
          <div>
            <el-tag>价格：{{ room.price }}</el-tag>
            <h3>房型：{{ room.title }}</h3>
            <h3>入住人数：{{ room.code}}</h3>
            <div class="bottom clearfix">
              <el-button type="primary"
                         class="button "
                         plain
                         @click="handleAdd(room)"
                         icon="el-icon-plus"
                         v-hasPermi="['system:order:add']"
              >预定</el-button>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <!-- 添加或修改 对话框 -->
    <el-dialog :title="title" :visible.sync="open" :close-on-click-modal="false" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="房间Id" prop="roomId">
          <el-input v-model="form.roomId" disabled />
        </el-form-item>
        <el-form-item label="房型" prop="roomCode">
          <el-input v-model="form.roomCode" placeholder="请输入房型" />
        </el-form-item>
        <el-form-item label="预定房间数量" prop="quantity">
          <el-input v-model="form.quantity" placeholder="请输入数量" />
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
          <el-input v-model="form.price" disabled />
        </el-form-item>
        <el-form-item label="总价" prop="totalPrice">
          <el-input v-model="form.price * form.quantity" disabled/>
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

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import Calendar from "@/components/Calendar/calendar";
import { listRoom, getRoom } from "@/api/system/room";
import {addOrder, updateOrder} from "@/api/system/order";

export default {
  name: "Index",
  components: {
    'calendar': Calendar
  },
  data() {
    return {
      // 版本号
      version: "1.1.0",
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      imgUrl: null,
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      dateRange: [],
      //  表格数据
      roomList: [],
      // 弹出层标题
      title: "下单",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        price: null,
        code: null,
        title: null,
        number: null,
        roomStatus: null,
        roomType: null,
      },
      // 表单参数
      form: {
        roomId: "",
        price: "",
        roomCode:"",
        totalPrice: ""
      },
      // 表单校验
      rules: {

      }
    };
  },
  mounted() {
    this.getList()
  },
  methods: {
    /** 查询 列表 */
    getList() {
      this.loading = true;
      console.log("call")
      listRoom(this.queryParams).then(response => {
        this.roomList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 新增按钮操作 */
    handleAdd(room) {
      this.reset();
      this.form = {
        roomId: room.id,
        price: room.price,
        roomCode: room.code,
      }

      this.open = true;
      this.title = "预定";
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        roomId: null,
        roomCode: null,
        userId: null,
        quantity: null,
        status: null,
        username: null,
        phone: null,
        idCard: null,
        price: null,
        totalPrice: null,
        roomType: null,
        roomNumber: null,
        checkinDate: null,
        checkoutDate: null,
        creatTime: null,
        updateTime: null,
        orderType: null,
        orderDays: null
      };
      this.resetForm("form");
    },

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
  },
};
</script>

<style scoped lang="scss">
.home {
  blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }
  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }
  .col-item {
    margin-bottom: 20px;
  }
  .room-list {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
  }

  .room-item {
    width: 300px;
    margin-bottom: 20px;
  }

  .room-item img {
    width: 100%;
    height: 200px;
    object-fit: cover;
  }

  .room-info {
    padding: 10px;
    background-color: #f2f2f2;
  }

  .button {
    padding: 0;
    float: left;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }
}
</style>

