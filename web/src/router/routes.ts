import { RouteRecordRaw } from 'vue-router'

const routes: RouteRecordRaw[] = [
  {
    path: '/',
    component: () => import('layouts/BlankLayout.vue'),
    children: [
      { path: '', component: () => import('pages/HomePage/HomePage.vue') },
      { path: '/chat', component: () => import('pages/ChatPage/ChatPage.vue') }
    ]
  },
  {
    path: '/dashboard',
    component: () => import('layouts/OldMainLayout.vue'),
    children: [
      { path: '', component: () => import('pages/DashboardPage/DashboardPage.vue') },
      { path: '/dashboard/info', component: () => import('pages/InfoPage/InfoPage.vue') },
      { path: '/dashboard/mine', component: () => import('pages/MinePage/MainPage.vue') }
    ]
  },
  // Always leave this as last one,
  // but you can also remove it
  {
    path: '/:catchAll(.*)*',
    component: () => import('pages/ErrorNotFound.vue')
  }
]

export default routes
