import {post} from '../utils/request'

export const auth = (authCode: string): Promise<AuthResponse> =>
  new Promise<AuthResponse>((resolve, reject) => {
    wx.login({
      success: (loginResult: WechatMiniprogram.LoginSuccessCallbackResult) => {
        post<AuthResponse>('/wechat/auth', {code: loginResult.code, authCode})
          .then((result: AuthResponse) => {
            wx.setStorageSync('openId', result.openId)
            resolve(result)
          })
          .catch(() => {
            reject()
          })
      },
      fail: (err :WechatMiniprogram.GeneralCallbackResult) => {
        console.error('请求登录信息失败', err)
        reject()
      }
    })
  })

export const getPhoneNumber = (code: string): Promise<PhoneResponse> =>
  post<PhoneResponse>('/wechat/phoneNumber', {code})

export const login = (code: string, authCode: string, openId: string): Promise<void> =>
  post<void>('/wechat/login', {code, authCode, openId})
