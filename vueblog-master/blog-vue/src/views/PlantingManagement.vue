<template>
  <div>
    <el-select v-model="selectedClass" placeholder="选择分类" @change="fetchBlogs">
      <el-option label="种植技术" :value="1"></el-option>
      <el-option label="种植知识" :value="2"></el-option>
      <el-option label="病虫害防治" :value="3"></el-option>
    </el-select>

    <el-button type="primary" @click="openDialog">新增</el-button>

    <el-table :data="blogs" stripe style="width: 100%">
      <el-table-column prop="title" label="标题" width="300" />
      <el-table-column prop="bclass" label="分类" />
      <el-table-column prop="column1" label="施肥方法" />
      <el-table-column prop="column2" label="修剪技术" />
      <el-table-column prop="column3" label="采摘时机" />
      <el-table-column prop="gmtCreate" label="创建时间" />
      <el-table-column label="操作">
        <template #default="scope">
          <el-button size="mini" @click="editBlog(scope.row)">编辑</el-button>
          <el-button size="mini" type="danger" @click="deleteBlog(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog title="编辑博客" :visible.sync="dialogVisible">
      <el-form :model="editForm">
        <el-form-item label="标题">
          <el-input v-model="editForm.title" />
        </el-form-item>

        <el-form-item label="分类">
          <el-input v-model="editForm.bclass"  />
        </el-form-item>

        <el-form-item label="施肥方法">
          <el-input v-model="editForm.column1"  />
        </el-form-item>
        <el-form-item label="修剪技术">
          <el-input v-model="editForm.column2"  />
        </el-form-item>
        <el-form-item label="采摘时机">
          <el-input v-model="editForm.column3"  />
        </el-form-item>

        <el-form-item label="内容">
          <el-input type="textarea" v-model="editForm.content" />
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="saveBlog">保存</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      blogs: [],
      dialogVisible: false,
      editForm: { id: null, title: "", bclass: "", content: "" ,  column1:"", column2:"", column3:"" },
      selectedClass: null, // 新增字段用于存储用户选择的分类
    };
  },
  methods: {
    fetchBlogs() {
      if (this.selectedClass) { // 检查是否有选择的分类
        this.$axios.get(`/blog/bclass`, {
          params: {
            bclass: this.selectedClass,
            currentPage: 1 // 使用固定的当前页数，您可能会根据需要动态修改
          }
        }).then((res) => {
          this.blogs = res.data.data.records;
        });
      }
    },
    openDialog() {
      // 重置表单并显示对话框
      this.dialogVisible = true;
      this.editForm = { id: null, title: "", bclass: this.selectedClass, content: "",  column1:"", column2:"", column3:""  };
    },
    editBlog(blog) {
      // 编辑博客时，显示被编辑博客的信息
      this.dialogVisible = true;
      this.editForm = { ...blog };
    },
    saveBlog() {
      const url = this.editForm.id ? "/blog/edit" : "/blog/add"; // 提交到相应url
      this.$axios.post(url, this.editForm, {
        headers: {
          Authorization: localStorage.getItem("token")
        }
      }).then(() => {
        this.dialogVisible = false;
        this.fetchBlogs();
        this.$message.success("保存成功");
      });
    },
    deleteBlog(blog) {
      this.$axios.get(`/blog/delete/${blog.id}`, {
        headers: {
          Authorization: localStorage.getItem("token")
        }
      }).then(() => {
        this.fetchBlogs();
        this.$message.success("删除成功");
      });
    },
  },
  created() {
    this.fetchBlogs();
  },
};
</script>

<style>
/* 你可以根据需要添加或修改样式 */
</style>