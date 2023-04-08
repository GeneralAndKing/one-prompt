import { api } from '@/boot/axios'

export const categoryMenu = () => api.get('/category')
