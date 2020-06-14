import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'
/*import userrouter from './router'*/
import store from './store'
import './plugins/element.js'
import local from './lib/util'


import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'



// import store from '@/store'
import infiniteScroll from 'vue-infinite-scroll'
Vue.use(infiniteScroll)

import VueQuillEditor from 'vue-quill-editor'
Vue.use(VueQuillEditor)
Vue.use(local)


// // 按需引入 引入 ECharts 主模块
// var echarts = require('echarts/lib/echarts')
// // 引入柱状图
// require('echarts/lib/chart/bar')
// // 引入提示框和标题组件
// require('echarts/lib/component/tooltip')
// require('echarts/lib/component/title')
//
// import ECharts from 'vue-echarts'
// import 'echarts/lib/chart/line'
// Vue.component('chart', ECharts)

/**权限指令**/
Vue.directive('has', {
    bind: function (el, binding) {
        if (!Vue.prototype.$_has(binding.value)) {
            // el.parentNode.removeChild(el);
            el.style.display = 'none';
        }
    }
});
//权限检查方法
Vue.prototype.$_has = function (value) {
    let isExist = false;
    //  获取拥有的权限
    let buttonperms =this.$local.get('uid')
    console.log(buttonperms); //aa
    // 权限列表
    console.log(value); // aa bb
    if (buttonperms == value) {
        isExist = true;
    }
    return isExist;
};



Vue.config.productionTip = false


new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
