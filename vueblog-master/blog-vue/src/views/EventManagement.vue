<template>
  <div>
    <el-button type="primary" @click="openDialog">新增</el-button>
    <el-table :data="events" stripe style="width: 100%">
      <el-table-column prop="title" label="标题" width="300" />
      <el-table-column prop="bclass" label="分类" />
      <el-table-column prop="gmtCreate" label="创建时间" />
      <el-table-column label="操作">
        <template #default="scope">
          <el-button size="mini" @click="editEvent(scope.row)">编辑</el-button>
          <el-button size="mini" type="danger" @click="deleteEvent(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog title="编辑活动" :visible.sync="dialogVisible">
      <el-form :model="editForm">
        <el-form-item label="标题">
          <el-input v-model="editForm.title" />
        </el-form-item>
<!--        <el-form-item label="分类">-->
<!--          <el-input v-model="editForm.bclass" />-->
        </el-form-item>
        <el-form-item label="内容">
          <el-input type="textarea" v-model="editForm.content" />
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="saveEvent">保存</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      events: [],
      dialogVisible: false,
      editForm: { id: null, title: "", bclass: "", content: "" },
    };
  },
  methods: {
    fetchEvents() {
      this.$axios.get("/event").then((res) => {
        this.events = res.data.data.records;
      });
    },
    openDialog() {
      this.dialogVisible = true;
      this.editForm = { id: null, title: "", bclass: "", content: "" };
    },
    editEvent(event) {
      this.dialogVisible = true;
      this.editForm = { ...event };
    },
    saveEvent() {
      const url = this.editForm.id ? "/event/edit" : "/event/edit";
      this.$axios.post(url, this.editForm, {
              headers: {
                Authorization: localStorage.getItem("token")
              }
            }).then(() => {
        this.dialogVisible = false;
        this.fetchEvents();
        this.$message.success("保存成功");
      });
    },
    deleteEvent(event) {
      this.$axios.get(`/event/delete/${event.id}`, {
              headers: {
                Authorization: localStorage.getItem("token")
              }
            }).then(() => {
        this.fetchEvents();
        this.$message.success("删除成功");
      });
    },
  },
  created() {
    this.fetchEvents();
  },
};
</script>
