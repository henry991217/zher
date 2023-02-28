<template>
  <div class="login-background">
    <div class="me-login-box me-login-box-radius">
      <h1>汇率后台管理 登录</h1>

      <el-form ref="userForm" :model="userForm" :rules="rules">
        <el-form-item prop="account">
          <el-input placeholder="管理员用户名" v-model="userForm.account" prefix-icon="el-icon-user"></el-input>
        </el-form-item>

        <el-form-item prop="password">
          <el-input placeholder="密码" type="password" v-model="userForm.password" prefix-icon="el-icon-key" show-password></el-input>
        </el-form-item>

        <el-form-item size="small" class="me-login-button">
          <el-button type="primary" @click.native.prevent="login('userForm')">登录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>


  import request from "@/utils/request";

  export default {
    name: 'Login',
    data() {
      return {
        userForm: {
          account: '',
          password: ''
        },
        rules: {
          account: [
            {required: true, message: '请输入管理员用户', trigger: 'blur'},
            {max: 10, message: '不能大于10个字符', trigger: 'blur'}
          ],
          password: [
            {required: true, message: '请输入密码', trigger: 'blur'},
            {max: 10, message: '不能大于10个字符', trigger: 'blur'}
          ]
        }
      }
    },
    methods: {
		resetForm()
		{
			this.$refs.formName.resetFields();
		},
      login(userForm) {
        let that = this

        this.$refs[userForm].validate((valid) => {
          if (valid) {
           request.post("/user/login",this.userForm).then(
               res=>
               {
                 if(res.code=='200')
                 {
                 this.$message.success("登录成功，即将跳转页面")
                   console.log(res)
                   sessionStorage.setItem("adminlogin",JSON.stringify(res.data))
                 this.$router.replace("/u239fhaAJAiK9sajsfha3U4OEWjp7KRSa")//u239fhaAJAiK9sajsfha3U4OEWjp7KRSa

               }
                 else{
                   console.log(res)
                   this.$message.error("登陆失败，请检查用户名或密码是否输入正确")
                 }
               }
           )

      }

    })
  }
    }
  }
</script>

<style scoped>
  #login {
    min-width: 100%;
    min-height: 100%;
  }

  .me-video-player {
    background-color: transparent;
    width: 100%;
    height: 100%;
    object-fit: fill;
    display: block;
    position: absolute;
    left: 0;
    z-index: 0;
    top: 0;
  }

  .me-login-box {
    position: absolute;
    width: 300px;
    height: 260px;
    background-image: linear-gradient(rgba(237,244,255,0.98),blanchedalmond);
    margin-top: 150px;
    margin-left: -180px;
    left: 50%;
    padding: 30px;

  }

  .me-login-box-radius {
    border-radius: 10px;
    box-shadow: 0px 0px 1px 1px rgba(161, 159, 159, 0.1);
  }

  .me-login-box h1 {
    text-align: center;
    font-size: 24px;
    margin-bottom: 20px;
    vertical-align: middle;
  }

  .login-background{
    height: 100vh;
    background-image: linear-gradient(to bottom right,#45536c,#efc32c);
  }

  .me-login-button {
    text-align: center;
  }

  .me-login-button button {
    width: 100%;
  }

</style>
