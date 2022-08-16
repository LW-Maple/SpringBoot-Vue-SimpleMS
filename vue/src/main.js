import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'


import ElementPlus from 'element-plus'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import 'element-plus/dist/index.css'

import zhCn from 'element-plus/es/locale/lang/zh-cn'

import './assets/css/global.css'


//const app = createApp(App).use(store).use(ElementPlus).use(router).mount('#app')

const app = createApp(App)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}
app.use(store)
    .use(router)
    .use(ElementPlus, {
        locale: zhCn,
    })
    .mount('#app')

