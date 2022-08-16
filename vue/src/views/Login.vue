<template>
    <div style="width: 100%; height: 100vh;
     background-image:url(https://nyat-static.globalslb.net/natfrp/index/bg1.jpg);overflow: hidden;
     overflow: hidden" >
        <div style="width: 400px; margin: 150px auto">
            <div style="color: #cccccc; font-size: 30px; text-align: center; padding: 30px" >欢迎</div>
            <div class="layout"  >
                <el-tabs type="border-card" style="background:rgba(255,255,255,0.8)">
                    <el-tab-pane label="登录" >
                        <el-form>
                            <el-form-item>
                                <el-input v-model="form.username" placeholder="请输入用户名" >
                                    <template #prepend>
                                        <el-icon><User/></el-icon>
                                    </template>
                                </el-input>
                            </el-form-item>

                            <el-form-item>
                                <el-input type="password" v-model="form.password" show-password placeholder="请输入密码">
                                    <template #prepend>
                                        <el-icon><Lock /></el-icon>
                                    </template>
                                </el-input>
                            </el-form-item>

                            <el-form-item >
                                <div style="display:flex;">
                                    <el-input v-model="compareCode" style="width: 58%;height: 58%; " placeholder="请输入验证码" >
                                        <template #prepend>
                                            <el-icon><Key /></el-icon>
                                        </template>
                                    </el-input>
                                    <ValidCode @input="createValidCode" style="width: 120px; height: 32px;margin-left: 3px"/>
                                </div>
                            </el-form-item>

                            <el-form-item>
                                <el-button
                                        style="width: 100%"
                                        type="primary"
                                        class="loginBtn"
                                        @click="login">
                                    登录
                                </el-button>
                            </el-form-item>
                        </el-form>

                    </el-tab-pane>
                    <el-tab-pane label="注册">
                        <el-form

                                label-position="right"
                                label-width="100px"
                                style="max-width: 460px"
                                class="loginForm">

                            <el-form-item label="用户名：">
                                <el-input v-model="regForm.username" placeholder="请输入用户名"/>
                            </el-form-item>

                            <el-form-item label="密码：">
                                    <el-input
                                            placeholder="请输入密码"
                                            type="password"
                                            v-model="regForm.password"
                                            show-password >
                                    </el-input>
                            </el-form-item>

                            <el-form-item label="确认密码：" >
                                    <el-input type="password"
                                              placeholder="请再次输入密码"
                                              v-model="confirmPassword"
                                              @blur="confirmFunc"
                                              show-password>
                                    </el-input>
                            </el-form-item>


                            <el-button
                                    style="width: 100%"
                                    type="primary"
                                    class="loginBtn"
                                    @click="register">
                                注册
                            </el-button>
                        </el-form>
                    </el-tab-pane>
                </el-tabs>
            </div>
        </div>
    </div>
</template>

<script>
    import request from "@/utils/request";
    import ValidCode from "@/components/ValidCode";

    export default {
        name:"Login",
        components:{
            ValidCode
        },
        data(){
            return{
                form:{},
                regForm:{},
                confirmPassword:'',
                validCode:'',
                compareCode:''
            }
        },
        methods:{
            login(){
                if (this.validCode.toLowerCase() !== this.compareCode.toLowerCase()){
                    this.$message.error("验证码不正确，请重试")
                    return false
                }
                request.post("/user/login",this.form).then(res=>{
                    if (res.code==='0'){
                        this.$message({
                            type:"success",
                            message:"登录成功"
                        })
                        console.log(res)
                        sessionStorage.setItem("user",JSON.stringify(res.data))
                        this.$router.push("/")
                    }else {
                        this.$message({
                            type:"error",
                            message:"登录失败"
                        })
                    }

                })
            },
            createValidCode(data){
              this.validCode=data
            },
            register(){
                request.post("/user/register",this.regForm).then(res=>{
                    if (res.code==='0'){
                        this.$message({
                            type:"success",
                            message:"注册成功"
                        })
                        location.reload()
                    }else {
                        this.$message({
                            type:"error",
                            message:"注册失败"
                        })
                    }
                })
            },
            confirmFunc(){
                if (this.regForm.password!==this.confirmPassword){
                    this.$message({
                        type:"error",
                        message:"两次输入密码不一致"
                    })
                }
            }
        }
    };

</script>

<style scoped>

</style>