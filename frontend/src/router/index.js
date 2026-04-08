import { createRouter, createWebHistory } from 'vue-router'
import ConflictListView from '../views/ConflictListView.vue'
import ConflicDetailView from '@/views/ConflicDetailView.vue'

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      component: ConflictListView
    },
    {
    path: '/conflicts/:id',
    component: ConflicDetailView
    }
  ]
})

export default router

