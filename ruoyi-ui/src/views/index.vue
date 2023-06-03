<template>
  <div class="app-container home">

    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" >
      <el-row>
        <el-col :span="12">
          <el-form-item label="请选择入住时间">
            <el-date-picker
              v-model="dateRange"
              :picker-options="pickerOptions"
              style="width: 240px"
              value-format="yyyy-MM-dd"
              type="daterange"
              range-separator="--"
              start-placeholder="入住日期"
              end-placeholder="离店日期"
            ></el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item labl="房间型号挑选">
            <el-select v-model="queryParams.code" placeholder="请选择房间型号" @change="getList">
              <el-option label="大床房" value="大床房" :filterable="true" :filter-method="filterMethod"></el-option>
              <el-option label="双床房" value="双床房" :filterable="true" :filter-method="filterMethod"></el-option>
              <el-option label="套房" value="套房" :filterable="true" :filter-method="filterMethod"></el-option>
              <el-option label="钟点房" value="钟点房" :filterable="true" :filter-method="filterMethod"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
          </el-form-item>
        </el-col>
      </el-row>

    </el-form>

    <el-row>
      <el-col :span="11" v-for="(room, index) in roomList" :key="room.id" :offset="index % 2 === 1 ? 2 : 0" v-if="index % 2 === 0">
        <el-card :body-style="{padding: '0px'}">
          <el-image :src="room.imgUrl" fit="contain" class="room-image"></el-image>
          <div>
            <el-tag>价格：{{ room.price }}</el-tag>
            <h3>房型：{{ room.code }}</h3>
            <h3>入住人数：{{ room.roomType}}</h3>
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
          <el-image :src="room.imgUrl" fit="contain" class="room-image"></el-image>
          <div>
            <el-tag>价格：{{ room.price }}</el-tag>
            <h3>房型：{{ room.code }}</h3>
            <h3>描述：{{ room.roomType}}</h3>
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
          <el-input v-model="form.roomCode"  diabled/>
        </el-form-item>
        <el-form-item label="预定房间数量" prop="quantity">
          <el-input-number v-model="form.quantity" :min=1 @change="calculateTotalMoney()"></el-input-number>
        </el-form-item>
        <el-form-item label="住客姓名" prop="username">
          <el-input v-model="form.username" placeholder="请输入住客姓名" />
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入手机号"/>
        </el-form-item>
        <el-form-item label="身份证" prop="idCard">
          <el-input v-model="form.idCard" placeholder="请输入身份证"/>
        </el-form-item>
        <el-form-item label="单价" prop="price">
          <el-input v-model="form.price" type="number" disabled />
        </el-form-item>
        <el-form-item label="入住日期" prop="checkinDate">
          <el-date-picker clearable
                          :picker-options="pickerOptions"
                          v-model="form.checkinDate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择入住时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="离店日期" prop="checkoutDate">
          <el-date-picker clearable
                          :picker-options="pickerOptions"
                          v-model="form.checkoutDate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          @blur="calculateTotalMoney()"
                          placeholder="请选择离店时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="总价" prop="totalPrice">
          <el-input :value="totalMoney" />
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
      data: {
        "2023/5/31":3,
        "2023/6/1":1
      },
      totalMoney: "",
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
      dateRange: '',
      pickerOptions: {
        disabledDate(time) {
          const today = new Date();
          today.setHours(0, 0, 0, 0); // 设置为当天的开始时间
          return time.getTime() < today.getTime(); // 禁用过去的日期
        }
      },
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
        totalPrice: "",
        quantity: 1,
        checkoutDate:"",
        checkinDate:""
      },
      // 表单校验
      rules: {
        idCard: [
          { required: true, message: '请输入身份证号码', trigger: 'blur' },
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
          { required: true, message: '请输入手机号', trigger: 'blur' },
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
  mounted() {
    this.getList()
  },
  methods: {

    getList() {
      this.loading = true;
      console.log("call")
      listRoom(this.queryParams).then(response => {
        this.roomList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    calculateTotalMoney() {
      if (!(this.form.checkinDate == null || this.form.checkoutDate == null)) {
        const checkinDate = new Date(this.form.checkinDate);
        const checkoutDate = new Date(this.form.checkoutDate);
        const timeDiff = checkoutDate.getTime() - checkinDate.getTime();
        const stayDays = Math.ceil(timeDiff / (1000 * 3600 * 24));
        this.totalMoney = this.form.price * stayDays * this.form.quantity
      }
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
    filterMethod(value, option) {
      return option.label.indexOf(value) !== -1; // 进行字符串的模糊匹配
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
        code: null,
        roomNumber: null,
        checkinDate: null,
        checkoutDate: null,
        createTime: null,
        updateTime: null,
        orderType: null,
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
      this.queryParams = "";
      this.getList();
    },
    /** 提交按钮 */
    submitForm() {
      const start = new Date(this.form.checkinDate);
      const end = new Date(this.form.checkoutDate);
      let currentDate = start;
      let map = new Map(Object.entries(this.data))
      console.log(map)
      let dateList = [];
      while (currentDate <= end) {
        let dateStr = currentDate.toLocaleDateString()
        if (map.get(dateStr) < this.form.quantity) {
          dateList.push(dateStr)
        }
        currentDate.setDate(currentDate.getDate() + 1);
      }
      if (dateList.length > 0) {
        this.$message.error(dateList + "没有房间了")
        return
      }
      this.$refs["form"].validate(valid => {
        if(valid) {
          this.form.totalPrice = this.form.price * this.form.quantity
          this.$confirm('<img src="http://127.0.0.1:9300/statics/2023/05/15/alipay_20230515144602A001.jpg" style= "width: 100%">', '收款码', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            dangerouslyUseHTMLString: true
          }).then(() => {
            addOrder(this.form).then(response => {
              this.open = false;
              this.getList();
            });
            this.$message({
              type: 'success',
              message: '下单成功!'
            });
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消订单'
            });
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

  //.room-image {
  //  //width: 700px; /* 设置固定宽度 */
  //  height: 200px; /* 设置固定高度 */
  //}

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

