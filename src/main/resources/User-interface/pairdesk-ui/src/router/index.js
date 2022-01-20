import { createRouter, createWebHashHistory } from 'vue-router'
import Home from '../views/Overview.vue'

const routes = [
  {
    path: '/login',
    name: 'Login',
    meta:{Navigation:false},
    component: () => import(/* webpackChunkName: "about" */ '../views/LoginPage.vue')
  },
  {
    path: '/',
    name: 'Home',
    component: Home,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue'),
    meta: {
      requiresAuth: true
    }
  },
  {
    path: '/features',
    name: 'Features',
    component: () => import(/* webpackChunkName: "about" */ '../views/FeaturesPage.vue'),
    meta: {
      requiresAuth: true
    }
  },
  {
    path: '/users',
    name: 'Users',
    component: () => import(/* webpackChunkName: "about" */ '../views/UsersPage.vue'),
    meta: {
      requiresAuth: true
    }
  },
  {
    path: '/features/:featureId',
    name: 'FeaturesDetail',
    params: true,
    component: () => import(/* webpackChunkName: "about" */ '../views/FeatureDetailPage.vue'),
    meta: {
      requiresAuth: true
    }
  },
  {
    path: '/users/:userId',
    name: 'UsersDetail',
    params: true,
    component: () => import(/* webpackChunkName: "about" */ '../views/UserDetailsPage.vue'),
    meta: {
      requiresAuth: true
    }
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

router.beforeEach((to, from, next) => {
  if (to.matched.some(record => record.meta.requiresAuth)) {
    if (localStorage.getItem('user-token') == null) {
      next({
        path: '/login',
        params: { nextUrl: to.fullPath }
      })
    } else {
      let user = JSON.parse(localStorage.getItem('user'))
      if (to.matched.some(record => record.meta.is_admin)) {
        if (user.is_admin === 1) {
          next()
        } else {
          next({ name: 'userboard' })
        }
      } else {
        next()
      }
    }
  } else if (to.matched.some(record => record.meta.guest)) {
    if (localStorage.getItem('user-token') == null) {
      next()
    } else {
      next({ name: 'userboard' })
    }
  } else {
    next()
  }
})

export default router
