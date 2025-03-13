<template>
  <div class="m-content">
    <div class="nav-left">
      <el-image style="width: 100px; height: 90px" :src="url" lazy></el-image>
    </div>
    <div class="nav-right">
      <span v-show="!isLogin"><el-link type="primary" href="/login">登录</el-link></span>
      <span v-show="isLogin">
        <el-link type="primary">
          <el-popover placement="bottom" width="40" trigger="click">
    <div>
      <el-avatar :size="50" :src="user.avatar"></el-avatar>
<!--      <el-input v-model="user.user_name" placeholder="修改用户名" size="small" style="margin-top: 10px;"></el-input>-->
<!--      <el-input v-model="password" type="password" placeholder="修改密码" size="small"-->
<!--                style="margin-top: 10px;"></el-input>-->
<!--      <el-input v-model="user.email" placeholder="修改电子邮件" size="small" style="margin-top: 10px;"></el-input>-->
<!--      <el-button type="primary" @click="updateUser" style="margin-top: 10px;">提交</el-button>-->
<!--      <el-button @click="resetForm" style="margin-top: 10px; margin-left: 10px;">重置</el-button>-->
    </div>
    <div slot="reference">
      <el-button>{{ user.user_name }}</el-button>
    </div>
          </el-popover>
        </el-link>
      </span>
      &nbsp;&nbsp;&nbsp;| &nbsp;&nbsp;&nbsp;
      <span v-show="!isLogin"><el-link type="primary" href="/register">注册</el-link></span>
      <span v-show="isLogin"><el-link type="danger" @click="logout">退出</el-link></span>
    </div>
    <div class="block">
      <div class="m-action">
        <el-menu class="el-menu-demo" mode="horizontal">
          <!-- 种植知识下拉菜单 -->
          <el-submenu index="1" class="planting-knowledge-submenu">
            <template slot="title">
              <el-link type="info">种植知识</el-link>
            </template>
            <el-menu-item
                v-for="item in plantingKnowledge"
                :key="item.menuId"
                :index="item.orderNum"
                @click="selectCategory(item.orderNum)"
            >
              {{ item.menuName }}
            </el-menu-item>
          </el-submenu>

          <!-- 茶文化下拉菜单 -->
          <el-submenu index="4">
            <template slot="title">
              <el-link type="info">茶文化</el-link>
            </template>
            <el-menu-item
                v-for="item in teaCulture"
                :key="item.menuId"
                :index="item.orderNum"
                @click="selectCategory(item.orderNum, 'culture')"
            >
              {{ item.menuName }}
            </el-menu-item>
          </el-submenu>
          <el-menu-item index="2">
            <el-link type="info" href="/event">活动预约</el-link>
          </el-menu-item>
          <el-menu-item index="3">
            <el-link type="info" href="/community">社区交流</el-link>
          </el-menu-item>
          <el-menu-item index="6">
            <el-link type="warning" @click="toUser">个人中心</el-link>
          </el-menu-item>
        </el-menu>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      plantingKnowledge: [],
      teaCulture: [],
      activeIndex: "1",
      password: "",
      ruleForm: {
        user_name: "",
        avatar: ""
      },
      user: {
        user_name: "请先登录",
        email: "",
        avatar: require("@/asserts/img/default_user.jpg"),
      },
      isLogin: false,
      url: "https://thumbs.dreamstime.com/b/%E7%99%BD%E8%89%B2%E8%83%8C%E6%99%AF%E4%B8%AD%E7%9A%84%E7%BB%BF%E8%8C%B6%E5%92%8C%E8%8C%B6%E5%8F%B6-%E6%97%A5%E6%9C%AC%E7%BB%BF%E8%8C%B6%E5%BD%A2%E8%B1%A1-198478124.jpg",
    };
  },
  methods: {
    updateUser() {
      // 在这里您可以添加逻辑来更新用户信息
      const user = {
        user_name: this.user.user_name,
        email: this.user.email,
        avatar: this.user.avatar,
        password: this.password, // 包含新密码
      };
      console.log('用户信息已更新:', user);

      // 例如，可以发送 API 请求更新用户信息。
      this.$axios.post("/sys/user/edit", user, {
        headers: {
          Authorization: localStorage.getItem("token"),
        },
      }).then(res => {
        if (this.$store.getters.getUserInfo.user_name) {
          this.user.user_name = this.$store.getters.getUserInfo.user_name;
          this.user.avatar = this.$store.getters.getUserInfo.avatar;
          this.isLogin = true;
        }
        this.$message.success('用户信息更新成功！');

      })
    },
    resetForm() {
      // 重置表单
      this.password = '';
      // 如果需要重置其他字段，可以在这里重置
    },
    async fetchPlantingKnowledge() {
      try {
        const response = await axios.get('/blog-menu');
        if (response.data.status === 200) {
          this.plantingKnowledge = response.data.data.records;
        } else {
          console.error('Error fetching planting knowledge:', response.data.msg);
        }
      } catch (error) {
        console.error('Failed to fetch planting knowledge:', error);
      }
    },
    async fetchTeaCulture() {
      try {
        const response = await axios.get('/culture-menu');
        if (response.data.status === 200) {
          this.teaCulture = response.data.data.records;
        } else {
          console.error('Error fetching tea culture:', response.data.msg);
        }
      } catch (error) {
        console.error('Failed to fetch tea culture:', error);
      }
    },
    logout() {
      const _this = this;
      _this.$axios
          .get("/logout", {
            headers: {
              Authorization: localStorage.getItem("token"),
            },
          })
          .then((res) => {
            _this.$store.commit("rmUserInfo");
            _this.$router.push("/login");
          });
    },
    toUser() {
      this.$router.push({path: "/user/" + this.user.user_name});
    },
    selectCategory(categoryId, type = 'blog') {
      const path = type === 'culture' ? '/culture' : '/blog';
      this.$router.push({path, query: {category: categoryId}});
    },
  }
  ,
  created() {
    this.fetchPlantingKnowledge();
    this.fetchTeaCulture();
    if (this.$store.getters.getUserInfo.user_name) {
      this.user.user_name = this.$store.getters.getUserInfo.user_name;
      this.user.avatar = this.$store.getters.getUserInfo.avatar;
      this.user.email = this.$store.getters.getUserInfo.email;
      this.isLogin = true;
    }
  }
  ,
}
;
</script>

<style>
.planting-knowledge-submenu .el-menu-item {
  border-bottom: none; /* 确保其他菜单没有下划线 */
}

/* 只有在悬停种植知识菜单时才显示下划线 */
.planting-knowledge-submenu:hover .el-menu-item:before {
  content: ''; /* 添加内容为空的伪元素 */
  display: block; /* 使其成为块级元素 */
  height: 2px; /* 下划线的高度 */
  background: rgba(0, 0, 0, 0.5); /* 下划线颜色 */
  position: absolute; /* 绝对定位 */
  left: 50%; /* 居中对齐 */
  bottom: -5px; /* 距离底部的偏移量 */
  width: 100%; /* 悬停时宽度变为 100% */
  transition: width 0.3s; /* 下划线宽度变化时的过渡效果 */
}

.planting-knowledge-submenu:hover .el-menu-item:hover:before {
  width: 100%; /* 悬停时显示下划线 */
}

.m-content {
  max-width: 890px;
  height: 100px;
  margin: 0 auto;

}

.m-action {
  margin: 10px 0;
}

.nav-left {
  float: left;
}

.nav-right {
  float: right;
  height: 80px;
  padding-top: 30px;
  font-size: 14px;
  color: #909399;
}

.block {
  text-align: center;
}

.el-popover {
  text-align: center !important;
}

.el-menu-demo {
  padding-right: 10%;
  height: 80px;
  padding-top: 20px;
  background-color: rgba(0, 0, 0, 0);
  float: right;


}

/* 点击出来的下划线进行隐藏 */
.el-menu-item.is-active {
  border-bottom: none !important;
  color: rgba(0, 0, 0, 0);
}

/* 整体的下划线进行隐藏 */
.el-menu.el-menu--horizontal {
  border-bottom: none !important;
}

/* 导航栏的间距等样式 */
.el-menu-item {
  padding: 0 62px;
  font-size: 16px;
}

/* 点击出来的下划线动效残留进行隐藏 */
.el-menu--horizontal > .el-menu-item {
  border-bottom: none;
}

/* 点击以后的背景色进行隐藏 */
.el-menu--horizontal > .el-menu-item:not(.is-disabled):focus,
.el-menu--horizontal > .el-menu-item:not(.is-disabled):hover,
.el-menu--horizontal > .el-submenu .el-submenu__title:hover {
  background-color: rgba(0, 0, 0, 0);
}

/* 整体的下划线进行隐藏 */
.el-menu.el-menu--horizontal {
  border-bottom: none !important;
}

/* 点击以后的背景色进行隐藏 */
.el-menu--horizontal > .el-menu-item:not(.is-disabled):focus,
.el-menu--horizontal > .el-menu-item:not(.is-disabled):hover,
.el-menu--horizontal > .el-submenu .el-submenu__title:hover {
  background-color: rgba(0, 0, 0, 0);
}

</style>
