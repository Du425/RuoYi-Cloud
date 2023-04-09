
<!--<template>-->
<!--  <div class="hotel-page">-->
<!--    <header>-->
<!--      <navigation-bar />-->
<!--    </header>-->
<!--    <main>-->
<!--      <hotel-search />-->
<!--      <hotel-room-list :rooms="rooms" />-->
<!--      <booking-form />-->
<!--    </main>-->
<!--    <footer>-->
<!--      <copyright />-->
<!--    </footer>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--import NavigationBar from "@/components/NavigationBar.vue";-->
<!--import HotelSearch from "@/components/HotelSearch.vue";-->
<!--import HotelRoomList from "@/components/HotelRoomList.vue";-->
<!--import BookingForm from "@/components/BookingForm.vue";-->
<!--import Copyright from "@/components/Copyright.vue";-->

<!--export default {-->
<!--  name: "HotelPage",-->
<!--  components: {-->
<!--    NavigationBar,-->
<!--    HotelSearch,-->
<!--    HotelRoomList,-->
<!--    BookingForm,-->
<!--    Copyright,-->
<!--  },-->
<!--  data() {-->
<!--    return {-->
<!--      rooms: [],-->
<!--    };-->
<!--  },-->
<!--  created() {-->
<!--    // fetch rooms data from API-->
<!--    this.fetchRooms();-->
<!--  },-->
<!--  methods: {-->
<!--    fetchRooms() {-->
<!--      // make API request to fetch rooms data-->
<!--      // and update rooms property with the response-->
<!--      // this.rooms = response.data;-->
<!--    },-->
<!--  },-->
<!--};-->
<!--</script>-->

<!--<style scoped>-->
<!--.hotel-page {-->
<!--  display: flex;-->
<!--  flex-direction: column;-->
<!--  min-height: 100vh;-->
<!--}-->

<!--main {-->
<!--  flex: 1;-->
<!--  display: flex;-->
<!--  flex-direction: column;-->
<!--}-->
<!--</style>-->











<template>
  <div class="app-container home">

    <calendar></calendar>

    <el-row :gutter="20">
      <el-col :sm="24" :lg="24">

      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :sm="24" :lg="12" style="padding-left: 20px">
        <h2>酒店后台管理框架</h2>
        <p>
          她可以用于所有的Web应用程序，如网站管理后台，网站会员中心，CMS，CRM，OA等等，当然，您也可以对她进行深度定制，以做出更强系统。所有前端后台代码封装过后十分精简易上手，出错概率低。同时支持移动客户端访问。系统会陆续更新一些实用功能。
        </p>
        <p>
          <b>当前版本:</b> <span>v{{ version }}</span>
        </p>
        <p>
          <el-tag type="danger">&yen;免费开源</el-tag>
        </p>
      </el-col>

      <el-col :sm="24" :lg="12" style="padding-left: 50px">
        <el-row>
          <el-col :span="12">
            <h2>技术选型</h2>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="6">
            <h4>后端技术</h4>
            <ul>
              <li>SpringBoot</li>
              <li>SpringCloud</li>
              <li>Nacos</li>
              <li>Sentinel</li>
              <li>Seata</li>
              <li>Minio</li>
              <li>...</li>
            </ul>
          </el-col>
          <el-col :span="6">
            <h4>前端技术</h4>
            <ul>
              <li>Vue</li>
              <li>Vuex</li>
              <li>Element-ui</li>
              <li>Axios</li>
              <li>Echarts</li>
              <li>Quill</li>
              <li>...</li>
            </ul>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
    <el-divider />
  </div>
</template>

<script>
import Calendar from "@/components/Calendar/calendar";
export default {
  name: "Index",
  components: {
    'calendar': Calendar
  },
  data() {
    return {
      // 版本号
      version: "1.1.0",
    };
  },
  methods: {
    goTarget(href) {
      window.open(href, "_blank");
    },
    getCalendar() {
      this.$refs['hotelCalendar'].getCalenderList(date, mixDate, maxDate);
    }
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

