<template>
  <div>
    <Header ></Header>
    <div class="block">
      <!-- 主内容网格展示 -->
      <div class="blog-grid">
        <div class="grid-item" v-for="(blog, index) in blogContents" :key="index">
          <el-card shadow="hover" class="grid-card">
            <router-link class="link" :to="{ name: 'BlogCultureDetail', params: { blogId: blog.id } }">
              <el-image class="grid-image" :src="blog.img || avatar" fit="cover"></el-image>
            </router-link>
            <h3 class="grid-title">
              <router-link class="link" :to="{ name: 'BlogCultureDetail', params: { blogId: blog.id } }">
                {{ blog.title }}
              </router-link>
            </h3>
          </el-card>
        </div>
      </div>
      <!-- 分页 -->
      <el-pagination
          class="pager"
          background
          layout="prev, pager, next"
          :current-page="currentPage"
          :page-size="pageSize"
          :total="total"
          @current-change="getPage"
      ></el-pagination>
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
      blogContentsAll: [],
      blogContents: [],
      currentPage: 1,
      total: 0,
      pageSize: 30,
      avatar: require("@/asserts/img/148.jpg"),
      selectedCategory: null,
    };
  },
  watch: {
    "$route.query.category": {
      handler(newCategory) {
        this.fetchAllBlogs(newCategory);
      },
      immediate: true, // 组件加载时立即触发
    },
  },
  methods: {
    async fetchAllBlogs(bclass) {
      try {
        const res = await this.$axios.get("/culture/bclass", {
          params: { bclass }
        });

        if (res.data.status === 200) {
          const records = res.data.data.records;
          this.blogContentsAll = Array.isArray(records) ? records : [];
          this.total = res.data.data.total; // 更新总数
          this.getPage(this.currentPage); // 重新计算当前页面的内容
        } else {
          console.error("获取博客数据失败:", res.data.msg);
          this.blogContentsAll = [];
          this.total = 0;
        }
      } catch (error) {
        console.error("获取博客数据失败:", error);
        this.blogContentsAll = [];
        this.total = 0;
      }
    },
    getPage(currentPage) {
      const startIndex = (currentPage - 1) * this.pageSize;
      const endIndex = currentPage * this.pageSize;
      this.blogContents = this.blogContentsAll.slice(startIndex, endIndex);
      this.currentPage = currentPage;
    },
  },
  created() {
    this.fetchAllBlogs(this.$route.query.category);
  },
};
</script>

<style>
.blog-grid {
  display: grid;
  grid-template-columns: repeat(4, 1fr); /* 每行显示 6 个 */
  gap: 20px; /* 卡片之间的间距 */
}

.grid-item {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.grid-card {
  padding: 0;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
  text-align: center;
  height: 260px; /* 统一高度 */
}

.grid-image {
  width: 100%;
  height: 150px;
  object-fit: cover;
}

.grid-title {
  font-size: 1rem;
  margin: 10px 0;
  color: #333;
  text-align: center;
}

.grid-title .link {
  color: #2196f3;
  text-decoration: none;
}

.grid-title .link:hover {
  color: #ff5722;
}

.pager {
  margin: 20px auto;
  text-align: center;
}
</style>
