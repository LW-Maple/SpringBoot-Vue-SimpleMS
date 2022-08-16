// 跨域配置 但项目在打包之后这个配置不会被打包进dist文件夹
module.exports = {
  devServer: {                //记住，别写错了devServer
    port: 9876,//设置前端页面本地默认端口  选填
    proxy: {                 //设置代理，必须填
      '/api': {              //设置拦截器  拦截器格式   斜杠+拦截器名字，名字可以自己定
        target: 'http://localhost:9090/',     //代理的目标地址
        changeOrigin: true,              //是否设置同源，输入是的
        pathRewrite: {                   //路径重写
          '^/api': ''                     //选择忽略拦截器里面的内容
        }
      }
    }
  }
}