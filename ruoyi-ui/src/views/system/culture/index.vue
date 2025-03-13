<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <!--      <el-form-item label="用户id" prop="userId">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.userId"-->
      <!--          placeholder="请输入用户id"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <el-form-item label="名称" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!--      <el-form-item label="描述" prop="description">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.description"-->
      <!--          placeholder="请输入描述"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="创建时间" prop="gmtCreate">-->
      <!--        <el-date-picker clearable-->
      <!--                        v-model="queryParams.gmtCreate"-->
      <!--                        type="date"-->
      <!--                        value-format="yyyy-MM-dd"-->
      <!--                        placeholder="请选择创建时间"-->
      <!--        >-->
      <!--        </el-date-picker>-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="修改时间" prop="gmtModified">-->
      <!--        <el-date-picker clearable-->
      <!--                        v-model="queryParams.gmtModified"-->
      <!--                        type="date"-->
      <!--                        value-format="yyyy-MM-dd"-->
      <!--                        placeholder="请选择修改时间"-->
      <!--        >-->
      <!--        </el-date-picker>-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="是否删除" prop="deleted">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.deleted"-->
      <!--          placeholder="请输入是否删除"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="作者" prop="author">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.author"-->
      <!--          placeholder="请输入作者"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <el-form-item label="分类" prop="bclass">
        <el-input
          v-model="queryParams.bclass"
          placeholder="请输入分类"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
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
          v-hasPermi="['system:culture:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:culture:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:culture:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:culture:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="cultureList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <!--      <el-table-column label="id" align="center" prop="id"/>-->
      <!--      <el-table-column label="用户id" align="center" prop="userId"/>-->
      <el-table-column label="名称" align="center" prop="title"/>
      <!--      <el-table-column label="描述" align="center" prop="description"/>-->
      <!--      <el-table-column label="内容" align="center" prop="content"/>-->
      <!--      <el-table-column label="创建时间" align="center" prop="gmtCreate" width="180">-->
      <!--        <template slot-scope="scope">-->
      <!--          <span>{{ parseTime(scope.row.gmtCreate, '{y}-{m}-{d}') }}</span>-->
      <!--        </template>-->
      <!--      </el-table-column>-->
      <!--      <el-table-column label="修改时间" align="center" prop="gmtModified" width="180">-->
      <!--        <template slot-scope="scope">-->
      <!--          <span>{{ parseTime(scope.row.gmtModified, '{y}-{m}-{d}') }}</span>-->
      <!--        </template>-->
      <!--      </el-table-column>-->
      <!--      <el-table-column label="状态" align="center" prop="status"/>-->
      <!--      <el-table-column label="是否删除" align="center" prop="deleted"/>-->
      <!--      <el-table-column label="作者" align="center" prop="author"/>-->
      <el-table-column label="分类" align="center" prop="bclass"/>
      <el-table-column label="内容" align="center" prop="column1"/>
      <!--      <el-table-column label="字段二" align="center" prop="column2"/>-->
      <!--      <el-table-column label="字段三" align="center" prop="column3"/>-->
      <!--      <el-table-column label="字段四" align="center" prop="column4"/>-->
      <!--      <el-table-column label="字段五" align="center" prop="column5"/>-->
      <el-table-column label="图片" align="center" prop="img"/>
      <el-table-column label="视频" align="center" prop="video"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:culture:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:culture:remove']"
          >删除
          </el-button>
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

    <!-- 添加或修改茶文化对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <!--        <el-form-item label="用户id" prop="userId">-->
        <!--          <el-input v-model="form.userId" placeholder="请输入用户id"/>-->
        <!--        </el-form-item>-->
        <el-form-item label="名称" prop="title">
          <el-input v-model="form.title" placeholder="请输入名称"/>
        </el-form-item>
        <!--        <el-form-item label="描述" prop="description">-->
        <!--          <el-input v-model="form.description" placeholder="请输入描述"/>-->
        <!--        </el-form-item>-->
        <!--        <el-form-item label="内容">-->
        <!--          <editor v-model="form.content" :min-height="192"/>-->
        <!--        </el-form-item>-->
        <!--        <el-form-item label="创建时间" prop="gmtCreate">-->
        <!--          <el-date-picker clearable-->
        <!--                          v-model="form.gmtCreate"-->
        <!--                          type="date"-->
        <!--                          value-format="yyyy-MM-dd"-->
        <!--                          placeholder="请选择创建时间"-->
        <!--          >-->
        <!--          </el-date-picker>-->
        <!--        </el-form-item>-->
        <!--        <el-form-item label="修改时间" prop="gmtModified">-->
        <!--          <el-date-picker clearable-->
        <!--                          v-model="form.gmtModified"-->
        <!--                          type="date"-->
        <!--                          value-format="yyyy-MM-dd"-->
        <!--                          placeholder="请选择修改时间"-->
        <!--          >-->
        <!--          </el-date-picker>-->
        <!--        </el-form-item>-->
        <!--        <el-form-item label="是否删除" prop="deleted">-->
        <!--          <el-input v-model="form.deleted" placeholder="请输入是否删除"/>-->
        <!--        </el-form-item>-->
        <!--        <el-form-item label="作者" prop="author">-->
        <!--          <el-input v-model="form.author" placeholder="请输入作者"/>-->
        <!--        </el-form-item>-->
        <el-form-item label="图片" prop="img">
          <el-input v-model="form.img" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
        <el-form-item label="分类" prop="bclass">
          <el-input v-model="form.bclass" placeholder="请输入分类"/>
        </el-form-item>
        <el-form-item label="内容" prop="column1">
          <el-input v-model="form.column1" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
        <!--        <el-form-item label="字段二" prop="column2">-->
        <!--          <el-input v-model="form.column2" type="textarea" placeholder="请输入内容"/>-->
        <!--        </el-form-item>-->
        <!--        <el-form-item label="字段三" prop="column3">-->
        <!--          <el-input v-model="form.column3" type="textarea" placeholder="请输入内容"/>-->
        <!--        </el-form-item>-->
        <!--        <el-form-item label="字段四" prop="column4">-->
        <!--          <el-input v-model="form.column4" type="textarea" placeholder="请输入内容"/>-->
        <!--        </el-form-item>-->
        <!--        <el-form-item label="字段五" prop="column5">-->
        <!--          <el-input v-model="form.column5" type="textarea" placeholder="请输入内容"/>-->
        <!--        </el-form-item>-->
        <!--        <el-form-item label="视频" prop="video">-->
        <!--          <el-input v-model="form.video" type="textarea" placeholder="请输入内容"/>-->
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
import { listCulture, getCulture, delCulture, addCulture, updateCulture } from '@/api/system/culture'
import Vue from 'vue'

export default {
  name: 'Culture',
    watch: {
    $route() {
      Vue.prototype.$globalVariable.forEach(item => {
        // 这里需要根据条件判断 URL 和 dictValue
        // 假设要比较的是 dictValue 在 URL 中的某个具体部分
        // console.log(item)
        if (Vue.prototype.$now_path.includes(item.dictLabel)) {
          console.log('now : ')
          console.log(Vue.prototype.$now_path)
          console.log(item.dictValue)
          this.bclass = item.dictValue // 赋值 dictSort 给 bclass
          this.queryParams.bclass = item.dictValue
        }
      })
      // 输出结果
      console.log('bclass 的值: ', this.queryParams.bclass)
      this.getList(this.bclass)
    }
  },
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
      // 茶文化表格数据
      cultureList: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userId: null,
        title: null,
        description: null,
        content: null,
        gmtCreate: null,
        gmtModified: null,
        status: null,
        deleted: null,
        author: null,
        img: null,
        bclass: 1,
        column1: null,
        column2: null,
        column3: null,
        column4: null,
        column5: null,
        video: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        title: [
          { required: true, message: '名称不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  created() {

    // this.getList(this.bclass)
  },
  mounted() {
    Vue.prototype.$cultureVariable.forEach(item => {
      // 这里需要根据条件判断 URL 和 dictValue
      // 假设要比较的是 dictValue 在 URL 中的某个具体部分
      // console.log(item)

      if (Vue.prototype.$now_path.includes(item.dictLabel)) {
        console.log('now : ')
        console.log(Vue.prototype.$now_path)
        console.log(item.dictValue)
        this.bclass = item.dictValue // 赋值 dictSort 给 bclass
        this.queryParams.bclass = item.dictValue
      }
    })
    // 输出结果
    console.log('bclass 的值: ', this.queryParams.bclass)
    // this.getList(this.queryParams.bclass)
    this.getList(this.bclass)

  },
  methods: {
    /** 查询茶文化列表 */
    getList(bclass) {
      this.queryParams.bclass = bclass
      this.loading = true
      listCulture(this.queryParams).then(response => {
        this.cultureList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        userId: null,
        title: null,
        description: null,
        content: null,
        gmtCreate: null,
        gmtModified: null,
        status: null,
        deleted: null,
        author: null,
        img: null,
        bclass: null,
        column1: null,
        column2: null,
        column3: null,
        column4: null,
        column5: null,
        video: null
      }
      this.resetForm('form')
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.getList(this.bclass)
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm('queryForm')
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = '添加茶文化'
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const id = row.id || this.ids
      getCulture(id).then(response => {
        this.form = response.data
        this.open = true
        this.title = '修改茶文化'
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateCulture(this.form).then(response => {
              this.$modal.msgSuccess('修改成功')
              this.open = false
              this.getList(this.bclass)
            })
          } else {
            addCulture(this.form).then(response => {
              this.$modal.msgSuccess('新增成功')
              this.open = false
              this.getList(this.bclass)
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids
      this.$modal.confirm('是否确认删除茶文化编号为"' + ids + '"的数据项？').then(function() {
        return delCulture(ids)
      }).then(() => {
        this.$modal.msgSuccess('删除成功')
        this.getList(this.bclass)

      }).catch(() => {
      })
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/culture/export', {
        ...this.queryParams
      }, `culture_${new Date().getTime()}.xlsx`)
        this.getList(this.bclass)

    }
  }
}
</script>
