import { api } from 'boot/axios'
import { AuthCode, UserToken } from 'src/model/Auth'

export const authCode = (): Promise<AuthCode> =>
  api.get('/auth/code')

export const authWechat = (authCode: string): Promise<UserToken> =>
  api.post('/auth/wechat', { authCode })
