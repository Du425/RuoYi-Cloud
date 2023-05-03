<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="选择日期" prop="roomId">
        <el-date-picker clearable
                        v-model="queryParams.createTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        @keyup.enter.native="handleQuery"
                        placeholder="请选择订单创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <div class="chart-container" id="lineChart"></div>
    <el-table v-loading="loading" :data="dailyIncome">
      <el-table-column label="日期" align="center" prop="date" />
      <el-table-column label="每日进账" align="center" prop="income" />
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
import * as echarts from 'echarts/core';
import { GridComponent } from 'echarts/components';
import { LineChart } from 'echarts/charts';
import { UniversalTransition } from 'echarts/features';
import { CanvasRenderer } from 'echarts/renderers';
import { TitleComponent } from 'echarts/components';


echarts.use([GridComponent, LineChart, CanvasRenderer, UniversalTransition, TitleComponent]);

export default {
  name: "Finance",
  data() {
    return {
      // 设置图表选项
      option: {
        title: {
          text: '每日进账折线图'
        },
        xAxis: {
          type: 'category',
          data: []
        },
        yAxis: {
          type: 'value'
        },
        series: [{
          data: [820, 932],
          type: 'line'
        }]
      },
      dailyIncome: [],
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
  mounted() {
    this.getList();
  },
  methods: {
    initLineChart() {
      let myChart = echarts.init(document.getElementById("lineChart"))
      this.option.series = [{
        data: this.dailyIncome.map(item => item.income),
        type: 'line'
      }]
      this.option.xAxis.data = this.dailyIncome.map(item => [item.date])
      myChart.setOption(this.option)
    },
    /** 查询 列表 */
    getList() {
      this.loading = true;
      listOrderFinance(this.queryParams).then(response => {
        console.log(response)
        this.orderList = response.data;
        this.total = response.data.length;
        this.loading = false;

        // TODO
        this.getDailyIncome();
        this.initLineChart()
      });
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.totalAccout = null;
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
    getDailyIncome() {
      let result = {};
      console.log(this.orderList)
      for (let i = 0; i < this.orderList.length; i++) {
        let order = this.orderList[i];
        let createTime = new Date(order.createTime);
        let dailyPrice = order.totalPrice;
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
      this.dailyIncome = data
    }
  }
};

</script>
<style>
.chart-container {
  width: 100%;
  height: 500px;
}
</style>


