import Toast from 'tdesign-miniprogram/toast'
import {auth, login} from '../../api/auth'
import {parseQueryParams} from '../../utils/util'

Page({
  data: {
    authCode: '',
    openId: ''
  },
  onLoad(option: Record<string, string>) {
    const openId = wx.getStorageSync('openId')
    if (!openId) {
      auth().catch(() => console.error('登陆失败'))
    }
    const scene = decodeURIComponent(option.scene)
    const param = parseQueryParams(scene)
    this.setData({authCode: param.authCode, openId})
  },
  async getUserInfo(e: WechatMiniprogram.ButtonGetPhoneNumber) {
    console.log(this.data.authCode, this.data.authCode === undefined, this.data.authCode === '')
    if (this.data.authCode === undefined || this.data.authCode === '') {
      await wx.showToast({
        title: '未获取到登陆信息，请重新扫码尝试',
        icon: 'none',
        duration: 5000
      })
      return
    }
    if (!e.detail.code) {
      Toast({
        context: this,
        selector: '#t-toast',
        message: '您已拒绝登录'
      })
      return
    }
    const code = e.detail.code
    try {
      await wx.showLoading({
        title: '登陆中'
      })
      await login(code, this.data.authCode, this.data.openId)
      await wx.hideLoading()
      await wx.showToast({
        title: '登陆成功，即将跳转至首页',
        icon: 'none',
        duration: 3000
      })
      setTimeout(() => {
        wx.navigateTo({url: 'pages/index/index'})
      }, 3000)
    } catch (e) {
      await wx.hideLoading()
    }
  }
})
