import { api } from '@/boot/axios'
import { Navigation } from '@/model/Dashboard'
import { Page } from '@/model/Base'
import { PromptComplete } from '@/model/PromptComplete'

export const dashboardMenu = (): Promise<Navigation[]> => api.get('/navigation')

export const dashboardData = (categoryIds: number[] = [], name = '', page = 0): Promise<Page<PromptComplete>> =>
  api.get('/prompt', { data: { categoryIds, name, page } })
