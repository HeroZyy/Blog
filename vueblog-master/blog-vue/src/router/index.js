import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '@/views/Login.vue'
import Blog from '@/views/Blog.vue'
import BlogDetail from '@/views/BlogDetail.vue'
import EventDetail from '@/views/EventDetail.vue'
import BlogEdit from '@/views/BlogEdit.vue'
import Register from '@/views/register.vue'
import User from '@/views/User.vue'
import BlogEvents from "@/views/BlogEvents.vue";
import BlogCulture from "@/views/BlogCulture.vue";
import BlogCultureDetail from "@/views/BlogCultureDetail.vue";
import Admin from "@/views/Admin.vue";

Vue.use(VueRouter)

const routes = [
    {
        path: '/event/:blogId',
        name: 'EventDetail',
        component: EventDetail
    },
    {
        path: '/',
        name: 'Index',
        redirect: {name: "Blog"}
    },
    {
        path: '/blog',
        name: 'Blog',
        component: Blog
    },
    {
        path: '/admin',
        name: 'Admin',
        meta: {layoutClass: 'admin'},
        component: Admin
    },
    {
        path: '/register',
        name: 'Register',
        component: Register
    },
    {
        path: '/login',
        name: 'Login',
        component: Login
    },
    {
        path: '/event',
        name: 'BlogEvents',
        component: BlogEvents,
        meta: {
            requireAuth: true
        }
    },
    {
        path: '/culture',
        name: 'BlogCulture',
        component: BlogCulture,
        meta: {
            requireAuth: true
        }
    },
    {
        path: '/community',
        name: 'BlogCommunity',
        component: BlogEdit,
        meta: {
            requireAuth: true
        }
    },
    {
        path: '/blog/add',
        name: 'BlogEdit',
        component: BlogEdit,
        meta: {
            requireAuth: true
        }
    },
    {
        path: '/add_events',
        name: 'BlogEditEvents',
        component: BlogEdit,
        meta: {
            requireAuth: true
        }
    },
    {
        path: '/blog/:blogId',
        name: 'BlogDetail',
        component: BlogDetail
    },
    {
        path: '/culture/:blogId',
        name: 'BlogCultureDetail',
        component: BlogCultureDetail
    },
    {
        path: '/user/:user_name',
        name: 'User',
        component: User
    },
    {
        path: '/event/:blogId/edit',
        name: 'BlogEdit',
        component: BlogEdit,
        meta: {
            requireAuth: true
        }
    },
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
