<template>
  <div class="abc" id="app">
    <el-container style="height: 100vh; background-color: #f5f7fa;">
      <!-- 左侧导航栏 -->
      <el-aside class="admin-aside">
        <el-menu
          :default-active="activeMenu"
          @select="handleMenuSelect"
          class="el-menu-vertical"
          text-color="#fff"
          background-color="transparent"
          active-text-color="#ffd04b"
        >
          <el-menu-item index="user">
            <i class="el-icon-user"></i>
            <span>用户管理</span>
          </el-menu-item>
          <el-menu-item index="planting">
            <i class="el-icon-s-platform"></i>
            <span>种植知识管理</span>
          </el-menu-item>
          <el-menu-item index="culture">
            <i class="el-icon-coffee"></i>
            <span>茶文化管理</span>
          </el-menu-item>
          <el-menu-item index="event">
            <i class="el-icon-date"></i>
            <span>活动管理</span>
          </el-menu-item>
        </el-menu>
      </el-aside>

      <!-- 内容部分 -->
      <el-container class="admin-main-container">
        <el-header class="admin-header">
          <h2 class="page-title">{{ pageTitle }}</h2>
        </el-header>
        <el-main class="admin-main">
          <div v-if="activeMenu === 'user'">
            <UserManagement />
          </div>
          <div v-else-if="activeMenu === 'planting'">
<!--             <el-select v-model="selectedClass" placeholder="请选择分类" @change="fetchBlogs">-->
<!--              <el-option label="种植技术" :value="1"></el-option>-->
<!--              <el-option label="种植知识" :value="2"></el-option>-->
<!--              <el-option label="病虫害防治" :value="3"></el-option>-->
<!--            </el-select>-->
            <PlantingManagement />
          </div>
          <div v-else-if="activeMenu === 'culture'">
            <CultureManagement />
          </div>
          <div v-else-if="activeMenu === 'event'">
            <EventManagement />
          </div>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import UserManagement from "./UserManagement.vue";
import PlantingManagement from "./PlantingManagement.vue";
import CultureManagement from "./CultureManagement.vue";
import EventManagement from "./EventManagement.vue";

export default {
  components: { UserManagement, PlantingManagement, CultureManagement, EventManagement },
  data() {
    return {
      activeMenu: "user",
      pageTitle: "用户管理",
    };
  },
  methods: {
    handleMenuSelect(index) {
      this.activeMenu = index;
      switch (index) {
        case "user":
          this.pageTitle = "用户管理";
          break;
        case "planting":
          this.pageTitle = "种植知识管理";
          break;
        case "culture":
          this.pageTitle = "茶文化管理";
          break;
        case "event":
          this.pageTitle = "活动管理";
          break;
        default:
          this.pageTitle = "后台管理";
          break;
      }
    },
  },
};
</script>

<style>
/* 清除所有默认样式 */
html, body {
  margin: 0; /* 去除默认的外边距 */
  padding: 0; /* 去除默认的内边距 */
  width: 100%;
  height: 100%;
}

/* 左侧导航栏样式 */
.admin-aside {
  background-color: #1e1e1e; /* 黑色背景 */
  color: #fff; /* 白色文字 */
  box-shadow: 2px 0 6px rgba(0, 0, 0, 0.5); /* 添加右侧阴影 */
  height: 100%; /* 高度占满屏幕 */
  padding-top: 20px; /* 增加顶部内边距 */
}

/* 导航栏样式调整 */
.el-menu-vertical {
  border: none; /* 移除边框 */
}

.el-menu-item {
  font-size: 16px; /* 文字大小 */
  font-weight: bold; /* 加粗文字 */
  padding-left: 20px; /* 增加左右内边距 */
  transition: background-color 0.3s; /* 添加过渡效果 */
}

.el-menu-item span {
  margin-left: 10px; /* 图标与文字间距 */
}

.el-menu-item:hover {
  background-color: rgba(255, 255, 255, 0.1); /* 鼠标悬停时的背景色 */
}

/* 内容区容器 */
.admin-main-container {
  display: flex;
  flex-direction: column; /* 垂直布局 */
  flex-grow: 1; /* 让内容区自动填满剩余空间 */
  background-color: #ffffff; /* 内容区背景为白色 */
}

/* 页头样式 */
.admin-header {
  height: 60px; /* 固定高度 */
  display: flex;
  align-items: center; /* 垂直居中对齐 */
  padding: 0 20px; /* 内边距 */
  background-color: #ffffff; /* 白色背景 */
  border-bottom: 1px solid #e0e0e0; /* 添加下边框 */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 添加轻微阴影 */
}

.page-title {
  font-size: 20px; /* 标题文字大小 */
  font-weight: bold; /* 加粗标题 */
  color: #333333; /* 标题文字颜色 */
  text-align: left; /* 左对齐 */
}

/* 主内容区样式 */
.admin-main {
  flex-grow: 1; /* 让内容区填满剩余空间 */
  padding: 20px; /* 内边距 */
  overflow-y: auto; /* 纵向滚动支持 */
  background-color: #f5f7fa; /* 浅灰色背景 */
}

.abc{
  max-width: 100%;
  margin: 0 auto;
}

</style>