import { api } from 'boot/axios'
import { Model } from 'src/model/Model'

export const modelList = (): Promise<Model[]> =>
  api.get('/model')
