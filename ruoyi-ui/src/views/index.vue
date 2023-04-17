
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

