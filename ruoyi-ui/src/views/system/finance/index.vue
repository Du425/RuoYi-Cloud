<template>
  <div class="app-container">

    <div class="chart-container"></div>
    <el-table v-loading="loading" :data="dailyIncome">
      <el-table-column label="日期" align="center" prop="date" />
      <el-table-column label="每日进账" align="center" prop="income" />
      <el-table-column label="总进账" align="center">
        <template slot-scope="{ row }">
          {{ dailyIncome.reduce((sum, item) => sum + item.income, 0) }}
        </template>
      </el-table-column>
    </el-table>

    <el-table v-loading="loading" :data="orderList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="订单号" align="center" prop="id" />
      <el-table-column label="房间Id" align="center" prop="roomId" />
      <el-table-column label="用户名" align="center" prop="username" />
      <el-table-column label="手机号" align="center" prop="phone" />
      <el-table-column label="房间数量" align="center" prop="roomNumber" />
      <el-table-column label="总价" align="center" prop="totalPrice" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="订单入住时长" align="center" prop="orderDays" />
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

  </div>
</template>

<script>
import {listOrderFinance} from "@/api/system/finance";
import echarts from 'echarts'

export default {
  name: "Finance",
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

      chartData: [],

      totalAccout: null,
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
        username: null,
        phone: null,
        idCard: null,
        price: null,
        totalPrice: null,
        roomType: null,
        roomNumber: null,
        createTime: null,
        orderType: null,
        roomTypeId: null,
        orderDays: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  mounted() {
    // 假设dailyIncome是从服务器获取的数据
    this.chartData = this.dailyIncome.map(item => [item.date, item.income])

    // 初始化图表
    const chart = echarts.init(document.querySelector('.chart-container'))

    // 设置图表选项
    const option = {
      title: {
        text: '每日进账折线图'
      },
      xAxis: {
        type: 'category',
        data: this.dailyIncome.map(item => item.date)
      },
      yAxis: {
        type: 'value'
      },
      series: [{
        data: this.chartData,
        type: 'line'
      }]
    }
    // 渲染图表
    chart.setOption(option)
  },
  computed: {
    dailyIncome() {
      let result = {};
      for (let i = 0; i < this.orderList.length; i++) {
        let order = this.orderList[i];
        let createTime = new Date(order.createTime);
        let days = Math.floor((new Date() - createTime) / 86400000) + 1;
        let dailyPrice = order.totalPrice / days;
        let dateStr = this.parseTime(createTime, "{y}-{m}-{d}");
        if (result[dateStr]) {
          result[dateStr] += dailyPrice;
        } else {
          result[dateStr] = dailyPrice;
        }
      }
      let data = [];
      for (let date in result) {
        data.push({
          date: date,
          income: result[date].toFixed(2)
        });
      }
      return data;
    }
  },
  methods: {
    /** 查询 列表 */
    getList() {
      this.loading = true;
      listOrderFinance(this.queryParams).then(response => {
        console.log(response)
        this.orderList = response.data;
        this.total = response.data.length;
        this.loading = false;
      });
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.totalAccout = null;
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
  }
};

</script>
<style>
.chart-container {
  width: 100%;
  height: 500px;
}
</style>


