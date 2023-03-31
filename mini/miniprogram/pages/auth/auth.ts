import Toast from 'tdesign-miniprogram/toast'
import {get} from '../../utils/request'

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
    get('http://dev.zyue.wiki:9002/hello')
      .then(res => {
        console.log(res)
      }).catch(err => {
      console.error(err)
    })
  }
})
