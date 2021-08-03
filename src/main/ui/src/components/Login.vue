<template>
   <div class="login">
    <div class="login_box">
        <!-- 头像区域 -->
        <div class="avatar_box">
            <img src="../assets/logo.jpg">
        </div>
        <!-- 标题区域 -->
        <div class="text">
            <span>中国大地保险告警配置平台</span>
        </div>
        <!-- 表单登录区域 -->
        <el-form ref="loginForm" :model="loginForm" :rules="rules" label-width="0px" class="login_form">
            <el-form-item prop="username">
                <el-input v-model="loginForm.username" prefix-icon="el-icon-user"></el-input>
            </el-form-item>
            <el-form-item prop="password">
                <el-input @keyup.enter.native="doLogin" v-model="loginForm.password" prefix-icon="el-icon-lock" type="password"></el-input>
            </el-form-item>
            <el-form-item class="btns">
                 <el-button type="primary" @click="doLogin">登录</el-button>
                 <el-button @click="resetForm">重置</el-button>
            </el-form-item>
        </el-form>
    </div>
   </div> 
</template>
    
<script>
export default {
  data(){
      return {
          //登录表单的数据绑定对象
          loginForm:{
              username:'',
              password:''
          },
          //表单的验证规则对象
          rules:{
              //验证用户名是否为空
              username:[
                  { required: true, message: '请输入用户名称', trigger: 'blur' }
              ],
              //验证密码是否为空
              password:[
                  { required: true, message: '请输入密码', trigger: 'blur' }
              ]
          }
      }
  },
  methods:{
      //点击重置按钮，重置登录表单
      resetForm(){
       this.$refs.loginForm.resetFields()
      },
      doLogin(){
          this.$refs.loginForm.validate(valid =>{
              if(!valid) return ;
              if(this.loginForm.username!=='alarm' || this.loginForm.password!=='ccic1234') return this.$message.error("密码或者用户名错误");
              this.$message.success("登陆成功");
              this.$store.commit('changeLoginState',true)
              this.$router.push('/home')
          });
      }
  }
}
</script>

<style >
    .login{
        background-color:royalblue;
        height: 100%;
    }

    .login_box{
        width:450px;
        height: 300px;
        background-color: #fff;
        border-radius: 3px;
        position: absolute;
        left: 50%;
        top:50%;
        transform: translate(-50%,-50%);


    }
            .avatar_box{
            height: 130px;
            width: 130px;
            border:1px soild #eee;
            border-radius: 50%;
            padding: 10px;
            box-shadow: 0 0 10px #ddd;
            position: absolute;
            left:50%;
            transform: translate(-50%,-70%);
            background-color: #fff;
          
        }
          img{
                width:100%;
                height: 100%;
                border-radius: 50%;
                background-color: #eee;
            }
             .text{
                    position: absolute;
                  left:50%;
                  transform: translate(-50%,250%);
            }

            .login_form{
                position: absolute;
                bottom: 0;
                width: 100%;
                padding: 0 10%;
                box-sizing: border-box;
            }

            .btns{
                display: flex;
                justify-content: flex-end;
            }
</style>

