<template>
  <div>
    <el-card class="box-card">
      <div>
        <!-- 搜索与添加区域 -->
        <el-row :gutter="20">
          <el-col :span="7">
            <el-input @keyup.enter.native="getConfigListByTemplateId(temolateId)" placeholder="请输入内容" v-model="temolateId">
              <el-button slot="append" icon="el-icon-search" @click="getConfigListByTemplateId(temolateId)"></el-button>
            </el-input>
          </el-col>
          <el-col :span="4">
            <el-button type="primary" @click="addDialogVisible = true">添加模板</el-button>
          </el-col>
        </el-row>

        <!-- 模板列表区域 -->
        <el-table :data="configList" border stripe>
          <el-table-column  label="#" min-width="20">
             <template slot-scope="scope">
                <span v-text="getIndex(scope.$index)"> </span>
             </template>
          </el-table-column>
          <el-table-column label="模板id" prop="templateId"></el-table-column>
          <el-table-column
            label="模板内容"
            prop="templateInfo"
          ></el-table-column>
          <el-table-column label="邮箱" prop="mail"></el-table-column>
          <el-table-column label="微信" prop="wechat"></el-table-column>
          <el-table-column label="手机" prop="telephone"></el-table-column>
          <el-table-column label="状态" prop="state"></el-table-column>
          <el-table-column label="操作">
            <template slot-scope="scope">
              <el-tooltip
                class="item"
                effect="dark"
                content="修改"
                placement="top"
              >
              <!-- 修改按钮 -->
                <el-button
                  type="primary"
                  icon="el-icon-edit"
                  size="mini"
                  @click="showEditDialog(scope.row.templateId)"
                ></el-button>
              </el-tooltip>
              <el-tooltip
                class="item"
                effect="dark"
                content="删除"
                placement="top"
              >
              <!-- 删除按钮 -->
                <el-button
                  type="danger"
                  icon="el-icon-delete"
                  size="mini"
                  @click="deleteConfigByTemplateId(scope.row.templateId)"
                ></el-button>
              </el-tooltip>
            </template>
          </el-table-column>
        </el-table>

        <!-- 分页区域 -->
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="queryInfo.pagenum"
          :page-sizes="[5, 10, 15, 30]"
          :page-size="queryInfo.pagesize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
        >
        </el-pagination>  

        <!-- 添加模板对话框 -->
        <el-dialog
          title="添加告警模板"
          :visible.sync="addDialogVisible"
          width="50%" @close="addDialogClose">
          <!-- 内容主体区域 -->
          <el-form size="mini" :inline="true" label-width="100px" :model="addForm" :rules="addFormRules" ref="addFormRef">
            <el-form-item label="模板id" prop="templateId">
              <el-input v-model="addForm.templateId"></el-input>
            </el-form-item>
            <el-form-item label="系统编码" prop="systemNumber">
              <el-input v-model="addForm.systemNumber"></el-input>
            </el-form-item>
            <el-form-item label="模板内容" prop="templateInfo">
              <el-input type="textarea" v-model="addForm.templateInfo"></el-input>
            </el-form-item>
            <el-form-item label="联系人姓名" prop="contactsName">
              <el-input v-model="addForm.contactsName"></el-input>
            </el-form-item>
            <el-form-item label="模板名称" prop="templateName">
              <el-input v-model="addForm.templateName"></el-input>
            </el-form-item>
            <el-form-item label="模板类型" prop="systemInfoType">
              <el-input v-model="addForm.systemInfoType"></el-input>
            </el-form-item>
            <el-form-item label="手机" prop="telephone">
              <el-input type="textarea" v-model="addForm.telephone"></el-input>
            </el-form-item>
            <el-form-item label="邮箱" prop="mail">
              <el-input type="textarea" v-model="addForm.mail"></el-input>
            </el-form-item>
            <el-form-item label="企业微信" prop="wechat">
              <el-input type="textarea" v-model="addForm.wechat"></el-input>
            </el-form-item>
          </el-form>
          <!-- 底部区域 -->
          <span slot="footer" class="dialog-footer">
            <el-button @click="addDialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="addConfig">确 定</el-button>
          </span>
        </el-dialog>

        <!-- 修改模板对话框 -->
        <el-dialog
          title="修改告警模板"
          :visible.sync="editDialogVisible"
          width="50%" @close="editDialogClose">
          <!-- 内容主体区域 -->
          <el-form size="mini" :inline="true" label-width="100px" :model="editConfigForm" :rules="addFormRules" ref="editFormRef">
            <el-form-item label="id" prop="id">
              <el-input v-model="editConfigForm.id" disabled></el-input>
            </el-form-item>
            <el-form-item label="模板id" prop="templateId">
              <el-input v-model="editConfigForm.templateId"></el-input>
            </el-form-item>
            <el-form-item label="系统编码" prop="systemNumber">
              <el-input v-model="editConfigForm.systemNumber"></el-input>
            </el-form-item>
            <el-form-item label="模板名称" prop="templateName">
              <el-input v-model="editConfigForm.templateName"></el-input>
            </el-form-item>           
            <el-form-item label="是否有效" prop="state">
              <el-input v-model="editConfigForm.state"></el-input>
            </el-form-item>
            <el-form-item label="更新人" prop="updateUserId">
              <el-input v-model="editConfigForm.updateUserId"></el-input>
            </el-form-item>
            <el-form-item label="TIMEOUT" prop="timeOut">
              <el-input v-model="editConfigForm.timeOut"></el-input>
            </el-form-item>
            <el-form-item label="时间戳" prop="timeStamp">
              <el-input v-model="editConfigForm.timeStamp"></el-input>
            </el-form-item>
            <el-form-item label="内容相似度阈值" prop="similarity">
              <el-input v-model="editConfigForm.similarity"></el-input>
            </el-form-item>
            <el-form-item label="邮件类型" prop="mailType">
              <el-input v-model="editConfigForm.mailType"></el-input>
            </el-form-item>
            <el-form-item label="模板类型" prop="systemInfoType">
              <el-input v-model="editConfigForm.systemInfoType"></el-input>
            </el-form-item>
            <el-form-item label="模板内容" prop="templateInfo">
              <el-input type="textarea" v-model="editConfigForm.templateInfo"></el-input>
            </el-form-item>
            <el-form-item label="联系人姓名" prop="contactsName">
              <el-input v-model="editConfigForm.contactsName"></el-input>
            </el-form-item>
            <el-form-item label="手机" prop="telephone">
              <el-input type="textarea" v-model="editConfigForm.telephone"></el-input>
            </el-form-item>
            <el-form-item label="邮箱" prop="mail">
              <el-input type="textarea" v-model="editConfigForm.mail"></el-input>
            </el-form-item>
            <el-form-item label="企业微信" prop="wechat">
              <el-input type="textarea" v-model="editConfigForm.wechat"></el-input>
            </el-form-item>
          </el-form>
          <!-- 底部区域 -->
          <span slot="footer" class="dialog-footer">
            <el-button @click="editDialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="editConfigInfo">确 定</el-button>
          </span>
        </el-dialog>

      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      //获取模板列表的参数对象
      queryInfo:{
        templateId:'',
        //当前页数
        pagenum:1,
        //当前每页显示多少条
        pagesize:5
      },
      temolateId:'',
      configList: [],
      total: 0,
      //控制添加模板对话框的显示与隐藏
      addDialogVisible:false,
      //添加模板的表单数据
      addForm:{
        templateId:'',
        systemNumber:'',
        templateInfo:'',
        contactsName:'',
        telephone:'',
        templateName:'',
        systemInfoType:'',
        mail:'',
        wechat:'',
      },
      //添加表单的验证规则对象
      addFormRules:{
        templateId:[
          {required:true,message:'请输入模板id',trigger:'blur'}
        ],
        systemNumber:[
          {required:true,message:'请输入系统编码',trigger:'blur'}
        ],
        templateInfo:[
          {required:true,message:'请输入模板内容',trigger:'blur'}
        ],
        contactsName:[
          {required:true,message:'请输入联系人姓名',trigger:'blur'}
        ],
        templateName:[
          {required:true,message:'请输入模板名称',trigger:'blur'}
        ],
        systemInfoType:[
          {required:true,message:'请输入模板类型',trigger:'blur'}
        ],

        state:[
          {required:true,message:'请输入状态',trigger:'blur'}
        ],
        updateUserId:[
          {required:true,message:'请输入更新人',trigger:'blur'}
        ],
        timeOut:[
          {required:true,message:'请输入timeOut',trigger:'blur'}
        ],
        timeStamp:[
          {required:true,message:'请输入是否携带时间戳',trigger:'blur'}
        ],
        similarity:[
          {required:true,message:'请输入内容相似度阈值',trigger:'blur'}
        ],
        mailType:[
          {required:true,message:'请输入邮件类型',trigger:'blur'}
        ],
      },
      //控制修改模板对话框的显示与隐藏
      editDialogVisible:false,
      //查询到的模板信息对象
      editConfigForm:{}
    };
  },

  created() {
    this.getConfigList();
  },

  methods: {
    async getConfigList() {
      this.queryInfo.templateId = ''
      const {
        data: res,
      } = await this.$http.post(
        "queryTemplate",
        //  { templateId: "" } 
        this.queryInfo
      );
      this.configList = res.obj.rows;
      this.total = res.obj.total;
    },
    //获取表格序号
    getIndex($index) {
        //表格序号
        return (this.queryInfo.pagenum - 1) * this.queryInfo.pagesize + $index + 1
    },
    //搜索框功能，根据templateId搜索模板
    async getConfigListByTemplateId(temolateId){
      this.queryInfo.templateId = temolateId
        const {
        data: res,
      } = await this.$http.post(
        "queryTemplate",
         this.queryInfo
      );
      this.configList = res.obj.rows;
      console.log(res.obj.rows.length);
      if(res.obj.rows.length==0){
          //刷新数据列表
          this.getConfigList()
          //提示修改成功
          this.$message.error('没有找到该模板')
      }
    },

    //监听pagesize改变的事件
    handleSizeChange(newSize){
      this.queryInfo.pagesize = newSize
      //刷新模板列表
      this.getConfigList()
    },
    //监听页码值改变的事件
    handleCurrentChange(newPage){
      this.queryInfo.pagenum = newPage
      //刷新模板列表
      this.getConfigList()
    },
    //监听添加模板对话框的关闭事件
    addDialogClose(){
        this.$refs.addFormRef.resetFields()
    },
    //添加新模板
    addConfig(){
        //校验表单数据是否为空
        this.$refs.addFormRef.validate(async valid =>{
          if(!valid) return;
          //校验成功，发起添加模板的网络请求          
        const {data: res} = await this.$http.post(  
          "addTemplate",this.addForm
        )    
        if(res.success == false){
          this.$message.error('添加模板失败')
        }

        this.$message.success('添加模板成功')

        //隐藏添加模板的对话框
        this.addDialogVisible = false
        //刷新模板列表
        this.getConfigList()
    })
    },
    //展示修改模板的对话框
    async showEditDialog(templateid){
      this.editDialogVisible = true
      this.queryInfo.templateId = templateid
      const {
        data: res,
      } = await this.$http.post(
        "queryTemplate/",
        this.queryInfo
      );
      this.editConfigForm = res.obj.rows[0];
    },
    //监听修改模板对话框的关闭事件
    editDialogClose(){
      this.$refs.editFormRef.resetFields();
    },
    //修改模板信息并提交
    editConfigInfo(){
      this.$refs.editFormRef.validate(valid =>{
        if(!valid) return 

        //发起修改模板的网络请求
        this.$http.post(
          "updateTemplate",this.editConfigForm
        ).then(res =>{
          console.log(res.data.success);
          if(!res.data.success){
            return this.$message.error('修改模板失败')
          }
          //关闭对话框
          this.editDialogVisible = false
          //刷新数据列表
          this.getConfigList()
          //提示修改成功
          this.$message.success('修改成功')
        })
      })
    },
    deleteConfigByTemplateId(templateid){
      this.$confirm('此操作会将模板变成无效, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
      }).then(() =>{
        console.log(templateid);
        this.$http.post("deleteTemplate/",
        { templateId: templateid }).then(res =>{
          console.log(res);
           if(!res.data.success){
            return this.$message.error('删除模板失败')
          }
            //刷新数据列表
          this.getConfigList()
          //提示修改成功
          this.$message.success('删除成功')
        })
      }
      ).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });          
        });
    }
  },
};
</script>

<style scoped>
.box-card {
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.15);
}
.el-table {
  margin-top: 15px;
}
</style>
