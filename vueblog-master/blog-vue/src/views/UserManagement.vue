<template>
  <div>
    <!-- 用户表格 -->
    <el-table :data="users" stripe style="width: 100%">
      <el-table-column prop="id" label="用户ID" width="100" />
      <el-table-column prop="user_name" label="用户名" width="200" />
      <el-table-column prop="email" label="邮箱" />
      <el-table-column prop="avatar" label="头像">
        <template #default="scope">
          <el-image :src="scope.row.avatar" style="width: 50px; height: 50px;" />
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template #default="scope">
          <el-button size="mini" @click="editUser(scope.row)">编辑</el-button>
          <el-button size="mini" type="danger" @click="deleteUser(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 编辑用户的弹窗 -->
    <el-dialog title="编辑用户" :visible.sync="dialogVisible">
      <el-form :model="editForm">
        <el-form-item label="用户名" required>
          <el-input v-model="editForm.user_name" />
        </el-form-item>
        <el-form-item label="邮箱" required>
          <el-input v-model="editForm.email" />
        </el-form-item>
        <el-form-item label="密码" required>
          <el-input v-model="editForm.password" />
        </el-form-item>
        <el-form-item label="头像">
          <el-input v-model="editForm.avatar" placeholder="头像URL" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="saveUser">保存</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      users: [], // 用户数据
      dialogVisible: false, // 编辑弹窗是否可见
      editForm: {
        id: null,
        user_name: "",
        email: "",
        avatar: "",
        password: ""
      }, // 编辑表单数据
    };
  },
  methods: {
    // 获取用户列表
    fetchUsers() {
      this.$axios.get("/blog/user/user" , {
              headers: {
                Authorization: localStorage.getItem("token")
              }
            }).then((res) => {
        this.users = res.data.data.records; // API 返回的数据结构匹配后端分页
      });
    },
    // 编辑用户
    editUser(user) {
      this.dialogVisible = true;
      this.editForm = {...user}; // 将当前行数据复制到编辑表单
    },
    // 保存用户信息
    saveUser() {
      this.$axios
          .post("/blog/user/user/edit", this.editForm,{
              headers: {
                Authorization: localStorage.getItem("token")
              }
            }) // 修改API路径为后端提供的路径
          .then(() => {
            this.dialogVisible = false;
            this.$message.success("用户信息更新成功");
            this.fetchUsers(); // 重新获取用户列表
          })
          .catch(() => {
            this.$message.error("更新失败，请重试");
          });
    },
    // 删除用户
    deleteUser(user) {
      this.$axios
          .get(`/blog/user/event/delete/${user.id}`,{
              headers: {
                Authorization: localStorage.getItem("token")
              }
            }) // 使用删除API
          .then(() => {
            this.$message.success("用户删除成功");
            this.fetchUsers(); // 重新获取用户列表
          })
          .catch(() => {
            this.$message.error("删除失败，请重试");
          });
    },
  },
  created() {
    this.fetchUsers(); // 页面加载时获取用户数据
  },
};
</script>
