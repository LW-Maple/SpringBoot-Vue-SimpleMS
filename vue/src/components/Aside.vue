<template>
    <div id>
        <el-menu style="width: 200px; min-height: calc(100vh - 50px) "
                default-openeds="config"
               v-bind:default-active="choose"
                class="el-menu-vertical-demo">
            <el-sub-menu index="config">
                <template #title>
                    <el-icon><Cloudy /></el-icon>
                    系统管理
                </template>

                <el-menu-item index="home" @click="change('home')">用户管理</el-menu-item>
            </el-sub-menu>

            <el-menu-item index="book" @click="change('book')">
                <el-icon><document /></el-icon>
                <span>数据管理</span>
            </el-menu-item>

            <el-menu-item index="data" @click="change('data')">
                <el-icon><setting /></el-icon>
                <span>选项3</span>
            </el-menu-item>

        </el-menu>
    </div>
</template>

<script>
    import router from "@/router";

    export default {
        name: "Aside",
        data(){
            return{
                choose:''
            }
        },
        methods:{
            change(param){
                window.location.href="http://localhost:9876/"+param
            }
        },
        created() {
            //奇怪的bug到导致路由跳转失败，于是才有了这样的方法
            this.choose=router.currentRoute.value.path.substr(1)
        }
    }
</script>

<style >

</style>