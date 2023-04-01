import {post} from '../utils/request'

export const auth = (): Promise<AuthResponse> =>
  new Promise<AuthResponse>((resolve, reject) => {
    wx.login({
      success: async (loginResult: WechatMiniprogram.LoginSuccessCallbackResult) => {
        post<AuthResponse>('/wechat/auth', {code: loginResult.code})
          .then((result: AuthResponse) => {
            wx.setStorageSync("openId", result.openId)
            resolve(result)
          })
          .catch(() => {
            reject()
          })
      }
    })
  })

export const getPhoneNumber = (code: string): Promise<PhoneResponse> =>
  post<PhoneResponse>('/wechat/phoneNumber', {code})