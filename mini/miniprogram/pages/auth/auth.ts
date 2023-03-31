import Toast from 'tdesign-miniprogram/toast'

Page({
  data: {},
  onLoad() {

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
    console.log(code)
  }
})
