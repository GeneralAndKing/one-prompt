import { api } from 'boot/axios'

export const categoryList = () =>
  api.get('/')
