<template>
    <div style="height: 50px; line-height: 50px; border-bottom: 1px solid #ccc; display: flex">
        <div style="width: 200px; padding-left: 30px; font-weight: bold; color: dodgerblue">后台管理</div>
        <div style="flex: 1"></div>
        <div style="width: 160px; padding-top: 8px">
            <el-dropdown>
                <el-button type="primary">
                    {{user.nickname}}
                    <el-icon class="el-icon--right"><arrow-down /></el-icon>
                </el-button>
                <template #dropdown>
                    <el-dropdown-menu>
                        <el-dropdown-item @click="showInfo">个人信息</el-dropdown-item>
                        <el-dropdown-item @click="logout">退出</el-dropdown-item>
                    </el-dropdown-menu>
                </template>
            </el-dropdown>
        </div>
        <div>
            <el-dialog v-model="dialogVisible" title="个人信息" width="30%">
                <el-form v-model="form" label-width="120px">
                    <el-form-item label="ID">
                        <el-input v-model="form.id" disabled style="width: 80%"/>
                    </el-form-item>
                    <el-form-item label="用户名">
                        <el-input v-model="form.username"  style="width: 80%"/>
                    </el-form-item>
                    <el-form-item label="密码">
                        <el-input v-model="form.password"  style="width: 80%"/>
                    </el-form-item>
                    <el-form-item label="昵称">
                        <el-input v-model="form.nickname" style="width: 80%"/>
                    </el-form-item>
                    <el-form-item label="年龄">
                        <el-input v-model="form.age" style="width: 80%"/>
                    </el-form-item>
                    <el-form-item label="性别">
                        <el-radio-group v-model="form.sex">
                            <el-radio label="男"/>
                            <el-radio label="女"/>
                        </el-radio-group>
                    </el-form-item>
                    <el-form-item label="住址">
                        <el-input type="textarea" v-model="form.address" style="width: 80%"/>
                    </el-form-item>
                </el-form>
                <template #footer>
              <span class="dialog-footer">
                <el-button @click="dialogVisible = false">取消</el-button>
                <el-button type="primary" @click="save">提交</el-button>
              </span>
                </template>
            </el-dialog>
        </div>
    </div>
</template>

<script>

    import request from "@/utils/request";

    export default {
        name: "Header",
        data(){
            return{
                user:"",
                dialogVisible:false,
                form:{}
            }
        },
        methods:{
            logout(){
                this.$message({
                    type:"success",
                    message:"退出成功"
                })
                sessionStorage.clear()
                this.$router.push("/login")
            },
            showInfo(){
                this.dialogVisible=true
                this.form=this.user
            },
            save(){
                request.put("/user", this.form).then(res => {
                    if (res.code==='0'){
                        this.$message({
                            type:"success",
                            message:"更新成功"
                        })
                        location.reload();
                    }else {
                        this.$message({
                            type:"error",
                            message:res.msg
                        })
                    }
                })

            }
        },
        created() {
            if (JSON.parse(sessionStorage.getItem("user"))===null){
                window.stop();
                this.$router.push("/login")
            }else {
                this.user=JSON.parse(sessionStorage.getItem("user"))
            }
        }
    }
</script>

<style scoped>

</style>