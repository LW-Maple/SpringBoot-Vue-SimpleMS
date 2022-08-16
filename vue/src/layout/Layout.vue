<template>
    <div>
        <Header> </Header>
        <div  id="1" style="display: flex">
            <!--        导航栏-->
            <Aside></Aside>
            <!--        主体-->
            <router-view style="flex: 1" />
        </div>
    </div>
</template>

<script>
    import Header from "@/components/Header";
    import Aside from "@/components/Aside";
    export default {
        name: "Layout",
        components:{
            Header,
            Aside
        }
    }
</script>

<style scoped>

</style>