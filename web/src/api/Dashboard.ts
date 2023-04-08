import { api } from '@/boot/axios'
import { Navigation } from '@/model/Dashboard'

export const dashboardMenu = (): Promise<Navigation[]> => api.get('/navigation')
