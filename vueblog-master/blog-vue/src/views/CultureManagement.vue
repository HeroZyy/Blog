<template>
  <div>
    <el-select v-model="selectedClass" placeholder="选择分类" @change="fetchBlogs">
      <el-option label="历史文化" :value="1"></el-option>
      <el-option label="制作工艺" :value="2"></el-option>
      <el-option label="茶艺茶道" :value="3"></el-option>
    </el-select>

    <el-button type="primary" @click="openDialog">新增</el-button>

    <el-table :data="blogs" stripe style="width: 100%">
      <el-table-column prop="title" label="标题" width="300" />
      <el-table-column prop="bclass" label="分类" />
      <el-table-column prop="column1" label="症状表现" />
      <el-table-column prop="column2" label="预防方法" />
      <el-table-column prop="column3" label="治理方法" />
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
          <el-input v-model="editForm.bclass" disabled />
        </el-form-item>

        <el-form-item label="分类">
          <el-input v-model="editForm.column1" disabled />
        </el-form-item>

        <el-form-item label="分类">
          <el-input v-model="editForm.column2" disabled />
        </el-form-item>

        <el-form-item label="分类">
          <el-input v-model="editForm.column3" disabled />
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
      editForm: { id: null, title: "", bclass: "", content: "" , column1:"", column2:"", column3:""},
      selectedClass: null, // 新增字段用于存储用户选择的分类
    };
  },
  methods: {
    fetchBlogs() {
      if (this.selectedClass) { // 检查是否有选择的分类
        this.$axios.get(`/culture/bclass`, {
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
      this.editForm = { id: null, title: "", bclass: this.selectedClass, content: "",  column1:"", column2:"", column3:"" };
    },
    editBlog(culture) {
      // 编辑博客时，显示被编辑博客的信息
      this.dialogVisible = true;
      this.editForm = { ...culture };
    },
    saveBlog() {
      const url = this.editForm.id ? "/culture/edit" : "/culture/add"; // 提交到相应url
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
    deleteBlog(culture) {
      this.$axios.get(`/culture/delete/${culture.id}`, {
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