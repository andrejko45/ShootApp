import { createRouter, createWebHistory } from 'vue-router'

import UsersView from '@/views/UsersView.vue'
import CompetitionsView from '@/views/CompetitionsView.vue'
import RolesView from '@/views/RolesView.vue'
import ParticipationsView from '@/views/ParticipationsView.vue'
import RoundsView from '@/views/RoundsView.vue'
import ResultsView from '@/views/ResultsView.vue'
import DashboardView from '@/views/DashboardView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/users',
      name: 'users',
      component: UsersView,
    },
    {
      path: '/competitions',
      name: 'competitions',
      component: CompetitionsView,
    },
    {
      path: '/roles',
      name: 'roles',
      component: RolesView,
    },
    {
      path: '/participations',
      name: 'participations',
      component: ParticipationsView,
    },
    {
      path: '/rounds',
      name: 'rounds',
      component: RoundsView,
    },
    {
      path: '/results',
      name: 'results',
      component: ResultsView,
    },
    {
      path: '/',
      redirect: '/users'
    },
    {
      path: '/',
      name: 'dashboard',
      component: DashboardView
    },
  ],
})

export default router
