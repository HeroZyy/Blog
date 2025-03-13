<template>
  <div>
    <Header></Header>
    <div class="block">
      <el-timeline>
        <el-timeline-item
          placement="top"
          v-for="(blog, index) in blogContents"
          :key="index"
        >
          <el-card>
            <h2>
              <router-link class="link" :to="{ name: 'EventDetail', params: { blogId: blog.id } }">
                {{ blog.title }}
              </router-link>
            </h2>
            <div class="new-meta-box">
              <div class="new-meta-item">
                <i class="el-icon-user-solid" aria-hidden="true"></i>
                <router-link class="link" :to="{ name: 'User', params: { user_name: blog.author } }">
                  {{ blog.author }}
                </router-link>
              </div>
              <div class="new-meta-item date">
                <i class="el-icon-s-order" aria-hidden="true"></i>
                <a class="notlink">
                  <p>{{ blog.gmtCreate }}</p>
                </a>
              </div>
            </div>
            <div class="fenge">
              <el-divider></el-divider>
            </div>

            <!-- 左侧封面图 + 右侧简介 + 预约按钮 -->
            <el-row :gutter="20" class="intro-container">
              <!-- 左侧图片 -->
              <el-col :span="8">
                <el-card shadow="hover" class="image-card">
                  <el-image
                    class="intro-image"
                    :src="blog.img || avatar"
                    fit="cover">
                  </el-image>
                </el-card>
              </el-col>

              <!-- 中间简介 -->
              <el-col :span="12">
                <blockquote>
                  <p><i class="el-icon-collection-tag"></i> 简介：{{ blog.description }}</p>
                </blockquote>
              </el-col>

              <!-- 右侧预约按钮 -->
              <el-col :span="4" class="button-col">
                <el-button type="primary" size="small" @click="makeReservation(blog)">
                  预约
                </el-button>
              </el-col>
            </el-row>
          </el-card>
        </el-timeline-item>
      </el-timeline>

      <!--分页-->
      <el-pagination
        class="pager"
        background
        layout="prev, pager, next"
        :current-page="currentPage"
        :page-size="pageSize"
        :total="total"
        @current-change="getPage">
      </el-pagination>
    </div>
    <Footer></Footer>
  </div>
</template>

<script>
import Header from "@/components/Header";
import Footer from "@/components/Footer";
export default {
  components: { Header, Footer },
  data() {
    return {
      blogContents: {},
      currentPage: 1,
      total: 0,
      pageSize: 5,
      avatar: require("@/asserts/img/148.jpg"),
    };
  },
  methods: {
    // 分页数据加载
    getPage(currentPage) {
      const _this = this;
      _this.$axios.get("/event?currentPage=" + currentPage).then((res) => {
        _this.blogContents = res.data.data.records;
        _this.currentPage = res.data.data.current;
        _this.total = res.data.data.total;
        _this.pageSize = res.data.data.size;
      });
    },
    // 预约按钮的逻辑
    makeReservation(blog) {
      // 打印日志或执行预约逻辑
      console.log("预约的内容为：", blog);

      // 弹出确认提示框
      this.$confirm(
        `确定预约博客《${blog.title}》吗？`,
        "预约确认",
        {
          confirmButtonText: "确认",
          cancelButtonText: "取消",
          type: "warning",
        }
      )
        .then(() => {
          // 如果用户确认，发送预约请求
          this.$message({
            type: "success",
            message: "预约成功！",
          });
          // 此处可以通过 Axios 向后端发送预约请求
          // this.$axios.post('/api/reservation', { id: blog.id });
        })
        .catch(() => {
          // 用户取消预约
          this.$message({
            type: "info",
            message: "已取消预约",
          });
        });
    },
  },
  created() {
    this.getPage(1);
  },
};
</script>

<style>
.fenge .el-divider {
  margin: 2px 0 !important;
}
.new-meta-item a {
  text-decoration: none;
  color: rgba(68, 68, 68, 0.65);
  padding-left: 0;
  padding-right: 4px;
}
.new-meta-box {
  font-size: 0.8125rem;
  padding-top: 1px;
  padding-bottom: 2px;
  display: flex;
  align-items: center;
  flex-wrap: wrap;
}
.new-meta-item {
  color: rgba(68, 68, 68, 0.65);
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 2px;
  margin: 0 8px 0 0;
  border-radius: 4px;
}
.intro-container {
  display: flex;
  align-items: center;
  margin-top: 10px;
}
.intro-image {
  width: 100%;
  height: 100px;
  border-radius: 5px;
  object-fit: cover;
}
blockquote {
  text-align: left;
  font-size: 0.9375rem;
  position: relative;
  width: 95%;
  padding: 0.7em 1.2em;
  background: #e8f4fd;
  border-left: 4px solid #2196f3;
  border-radius: 4px;
  margin: 0;
}
.pager {
  margin: 0 auto;
  text-align: center;
}
.link {
  color: skyblue;
  text-decoration: none;
}
.link:hover {
  color: #ff5722;
}

/* 右侧按钮样式 */
.button-col {
  display: flex;
  justify-content: center;
  align-items: center;
}

.el-button {
  border-radius: 5px;
  padding: 8px 16px;
  font-size: 14px;
}
</style>
