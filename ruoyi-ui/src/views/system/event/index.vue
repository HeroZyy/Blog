<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="标题" prop="userId">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="创建时间" prop="gmtCreate">-->
<!--        <el-date-picker clearable-->
<!--          v-model="queryParams.gmtCreate"-->
<!--          type="date"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          placeholder="请选择创建时间">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="删除" prop="deleted">-->
<!--        <el-input-->
<!--          v-model="queryParams.deleted"-->
<!--          placeholder="请输入删除"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="修改时间" prop="gmtModified">-->
<!--        <el-date-picker clearable-->
<!--          v-model="queryParams.gmtModified"-->
<!--          type="date"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          placeholder="请选择修改时间">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
      <el-form-item label="简介" prop="description">
        <el-input
          v-model="queryParams.description"
          placeholder="请输入简介"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="类型" prop="bclass">-->
<!--        <el-input-->
<!--          v-model="queryParams.bclass"-->
<!--          placeholder="请输入类型"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="图片" prop="img">-->
<!--        <el-input-->
<!--          v-model="queryParams.img"-->
<!--          placeholder="请输入图片"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:event:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:event:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:event:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:event:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="eventList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="标题" align="center" prop="title" />
      <el-table-column label="内容" align="center" prop="content" />
<!--      <el-table-column label="用户名" align="center" prop="userId" />-->
<!--      <el-table-column label="创建时间" align="center" prop="gmtCreate" width="180">-->
<!--        <template slot-scope="scope">-->
<!--          <span>{{ parseTime(scope.row.gmtCreate, '{y}-{m}-{d}') }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column label="状态" align="center" prop="status" />-->
<!--      <el-table-column label="删除" align="center" prop="deleted" />-->
<!--      <el-table-column label="修改时间" align="center" prop="gmtModified" width="180">-->
<!--        <template slot-scope="scope">-->
<!--          <span>{{ parseTime(scope.row.gmtModified, '{y}-{m}-{d}') }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->
      <el-table-column label="简介" align="center" prop="description" />
<!--      <el-table-column label="类型" align="center" prop="bclass" />-->
<!--      <el-table-column label="内容1" align="center" prop="column1" />-->
<!--      <el-table-column label="内容2" align="center" prop="column2" />-->
<!--      <el-table-column label="内容3" align="center" prop="column3" />-->
<!--      <el-table-column label="图片" align="center" prop="img" />-->
<!--      <el-table-column label="内容4" align="center" prop="column4" />-->
<!--      <el-table-column label="内容5" align="center" prop="column5" />-->
<!--      <el-table-column label="视频" align="center" prop="video" />-->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:event:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:event:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改活动管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="标题" prop="title">
          <el-input v-model="form.title" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="内容">
          <editor v-model="form.content" :min-height="192"/>
        </el-form-item>
<!--        <el-form-item label="用户名" prop="userId">-->
<!--          <el-input v-model="form.userId" placeholder="请输入用户名" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="创建时间" prop="gmtCreate">-->
<!--          <el-date-picker clearable-->
<!--            v-model="form.gmtCreate"-->
<!--            type="date"-->
<!--            value-format="yyyy-MM-dd"-->
<!--            placeholder="请选择创建时间">-->
<!--          </el-date-picker>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="删除" prop="deleted">-->
<!--          <el-input v-model="form.deleted" placeholder="请输入删除" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="修改时间" prop="gmtModified">-->
<!--          <el-date-picker clearable-->
<!--            v-model="form.gmtModified"-->
<!--            type="date"-->
<!--            value-format="yyyy-MM-dd"-->
<!--            placeholder="请选择修改时间">-->
<!--          </el-date-picker>-->
<!--        </el-form-item>-->
        <el-form-item label="简介" prop="description">
          <el-input v-model="form.description" placeholder="请输入简介" />
        </el-form-item>
<!--        <el-form-item label="类型" prop="bclass">-->
<!--          <el-input v-model="form.bclass" placeholder="请输入类型" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="内容1" prop="column1">-->
<!--          <el-input v-model="form.column1" type="textarea" placeholder="请输入内容" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="内容2" prop="column2">-->
<!--          <el-input v-model="form.column2" type="textarea" placeholder="请输入内容" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="内容3" prop="column3">-->
<!--          <el-input v-model="form.column3" type="textarea" placeholder="请输入内容" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="图片" prop="img">-->
<!--          <el-input v-model="form.img" placeholder="请输入图片" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="内容4" prop="column4">-->
<!--          <el-input v-model="form.column4" type="textarea" placeholder="请输入内容" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="内容5" prop="column5">-->
<!--          <el-input v-model="form.column5" type="textarea" placeholder="请输入内容" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="视频" prop="video">-->
<!--          <el-input v-model="form.video" type="textarea" placeholder="请输入内容" />-->
<!--        </el-form-item>-->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listEvent, getEvent, delEvent, addEvent, updateEvent } from "@/api/system/event";

export default {
  name: "Event",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 活动管理表格数据
      eventList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: null,
        content: null,
        userId: null,
        gmtCreate: null,
        status: null,
        deleted: null,
        gmtModified: null,
        description: null,
        bclass: null,
        column1: null,
        column2: null,
        column3: null,
        img: null,
        column4: null,
        column5: null,
        video: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        title: [
          { required: true, message: "标题不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询活动管理列表 */
    getList() {
      this.loading = true;
      listEvent(this.queryParams).then(response => {
        this.eventList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        title: null,
        content: null,
        userId: null,
        gmtCreate: null,
        status: null,
        deleted: null,
        gmtModified: null,
        description: null,
        bclass: null,
        column1: null,
        column2: null,
        column3: null,
        img: null,
        column4: null,
        column5: null,
        video: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加活动管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getEvent(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改活动管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateEvent(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addEvent(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除活动管理编号为"' + ids + '"的数据项？').then(function() {
        return delEvent(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/event/export', {
        ...this.queryParams
      }, `event_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
