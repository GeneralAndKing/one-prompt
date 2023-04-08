import { AuthCode, UserToken } from '@/model/Auth'
import { api } from '@/boot/axios'

export const authCodeMini = (): Promise<AuthCode> =>
  api.get('/auth/code/MINI')

export const authMini = (authCode: string): Promise<UserToken> =>
  api.post('/auth/mini', { authCode })
