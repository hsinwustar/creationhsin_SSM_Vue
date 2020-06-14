import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

const routes = [

    // {
    //     path:'/adminmanage',
    //     name:'后台首页',
    //     component: ()=>import('../views/About'),
    //     show:true,
    //     redirect:"/adminmanage",
    //     children:[{path:'/adminmanage',name:'首页',component:()=>import('../views/back/AdminManage')}]
    // },
    {
        path:'/marticle',
        name:'系统管理',
        component: ()=>import('../views/About'),
        redirect:"/marticle",
        show:true,
        children:[
            {path:'/marticle', name:'文章审查', component:()=>import('../views/back/MArticle')},
            {path:'/muser',name:"用户管理",component:()=>import('../views/back/MUser')},
            // {path:'/maddarticle', name:'添加文章', component:()=>import('../views/back/MaddArticle')},
        ]
    },
    // {
    //     path:'/mchart',
    //     name:'图表',
    //     show:true,
    //     component: ()=>import('../views/About'),
    //     redirect:"/mchartuser",
    //     children:[
    //         {path:'/mchartuser',name:"统计用户",component:()=>import('../views/back/MChartUser')},
    //         {path:'/mchartarticle',name:"统计文章",component:()=>import('../views/back/MChartArticle')}
    //         ]
    // },
    //
    // {
    //     path:'/mlog',
    //     name:'日志',
    //     show:true,
    //     component:()=>import('../views/About'),
    //     redirect:"/mlog",
    //     children:[
    //         {path:'/mlog',name:"错误日志",component:()=>import('../views/back/MLog')}
    //         // {path:'/mchangeUserType',name:"更改权限",component:()=>import('../views/back/MchangeUserType')}
    //     ]
    // },

    // {path:'/mupdateArticleMessage', name:'修改图书信息', component:()=>import('../views/back/MupdateArticle')},
    {path:'/login', name:'登录', component: ()=>import('../views/Login')},
    {
        path:'/home',
        name:'首页',
        component: ()=>import('../views/HomeAbout'),
        redirect:"/home",
        children:[
            {path:'/home', name:'home', component:()=>import('../views/front/Home')},
            {path:'/follow', name:'关注', component:()=>import('../views/front/Follow/Follow')},
            {
                path:'/message',
                name:'消息',
                component: ()=>import('../views/front/Message/Message'),
                redirect: "/home",
                children: [
                    {path: '/message', name: '消息', component: ()=>import('../views/front/Message/Message')}
                ]
            },
            {path: '/followUser', name: '关注', component: ()=>import('../views/front/Follow/FollowUser')},
            {path:'/user', name:'个人中心', component:()=>import('../views/front/User/User')},
            {path:'/articledetails',name:'文章详情',component:() => import('../views/front/Article/ArticleDetails')},
            {path:'/thematic',name:'专题页面',component:() => import('../views/front/Thematic/Thematic')},
            {path:'/thematicFindAllList',name:'专题列表',component:() => import('../views/front/Thematic/ThematicFindAllList')},
            {path:'/thematicCreate',name:'创建专题',component:() => import('../views/front/Thematic/ThematicCreate')},
            {path:'/userEdit',name:'更改个人信息',component:() => import('../views/front/User/UserEdit')},
            {path:'/thematicEdit',name:'更改专题信息',component:() => import('../views/front/Thematic/thematicEdit')}
        ]
    },
    {path:"/articlePush",name:"发表文章",component:() => import('../views/front/Article/ArticlePush')},
    {path:"/articleTransit",name:"中转页面",component:() => import('../views/front/Article/ArticleTransit')},
    {path:'/index',name:"测试用例",component:() => import('../views/dropzone')},
    {path:'/thematicTransit',name:"中转",component:() => import('../views/front/Thematic/ThematicTransit')}



];

/*const user=[

]*/
const router = new Router({
        mode: 'history',
  base: process.env.BASE_URL,
  routes
});

export default router
