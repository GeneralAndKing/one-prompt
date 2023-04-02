import { api } from 'boot/axios'
import { AuthCode } from 'src/model/Auth'

export const authCode = (): Promise<AuthCode> =>
  api.get('/auth/code')
