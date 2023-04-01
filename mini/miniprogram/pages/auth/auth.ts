import Toast from 'tdesign-miniprogram/toast'
import {auth, getPhoneNumber} from '../../api/auth'

Page({
  data: {},
  onLoad() {
    const openId = wx.getStorageSync("openId")
    if (!openId) {
      auth().catch(() => console.error('登陆失败'))
    }
  },
  getUserInfo(e: WechatMiniprogram.ButtonGetPhoneNumber) {
    if (!e.detail.code) {
      Toast({
        context: this,
        selector: '#t-toast',
        message: '您已拒绝登录'
      })
      return
    }
    const code = e.detail.code
    getPhoneNumber(code)
      .then((res: PhoneResponse) => {
        console.log(res)
      })
  }
})
