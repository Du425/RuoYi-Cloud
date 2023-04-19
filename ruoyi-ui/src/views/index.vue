<template>
  <div class="app-container home">

    <el-form>
      <el-form-item label="请选择入住时间">
        <el-date-picker
          v-model="dateRange"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="入住日期"
          end-placeholder="离店日期"
        ></el-date-picker>
      </el-form-item>
    </el-form>

    <el-row>
      <el-col :span="10" v-for="(room, index) in roomList" :key="room.id" :offset="index % 2 === 1 ? 2 : 0" v-if="index % 2 === 0">
        <el-card :body-style="{padding: '0px'}">
          <el-image :src="room.imgUrl" fit="contain"  ></el-image>
          <div>
            <el-tag>价格：{{ room.price }}</el-tag>
            <h3>房型：{{ room.title }}</h3>
            <h3>入住人数：{{ room.code}}</h3>
            <div class="bottom clearfix">
              <el-button type="text" class="button">详情预定</el-button>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="10" v-for="(room, index) in roomList" :key="room.id" :offset="index % 2 === 0 ? 2 : 0" v-if="index % 2 === 1">
        <el-card :body-style="{padding: '0px'}">
          <el-image :src="room.imgUrl" fit="contain"  ></el-image>
          <div>
            <el-tag>价格：{{ room.price }}</el-tag>
            <h3>房型：{{ room.title }}</h3>
            <h3>入住人数：{{ room.code}}</h3>
            <div class="bottom clearfix">
              <el-button type="text" class="button">详情预定</el-button>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>



  </div>
</template>

<script>
import Calendar from "@/components/Calendar/calendar";
import { listRoom, getRoom } from "@/api/system/room";

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
      title: "",
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
      form: {},
      // 表单校验
      rules: {
        roomStatus: [
          { required: true, message: "在售、停售不能为空", trigger: "change" }
        ],
      }
    };
  },
  mounted() {
    this.getList()
  },
  methods: {
    selectDateRangeHandle(e) {
      this.show = false;
      this.dateRange = `入住${e.startDate}离店${e.endDate}, 住${e.seletDays - 1}晚`
    },
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

