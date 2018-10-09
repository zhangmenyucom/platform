var util = require('../../../utils/util.js');
var api = require('../../../config/api.js');
var user = require('../../../services/user.js');
var app = getApp();

Page({
    data: {
        userInfo: {},
        hasMobile: '',
        registerTime:'',
        role:'',
        token:'',
        mobile:'',
        details:{}
    },
    onLoad: function (options) {
        // 页面初始化 options为页面跳转所带来的参数
        // console.log(app.globalData)

    },
    onReady: function () {

    },
    onShow: function () {
      

        let userInfo = wx.getStorageSync('userInfo');
        let token = wx.getStorageSync('token');
        let userId = wx.getStorageSync('userId')

        this.setData({ userInfo: userInfo})

        var _this = this
        let data = { 'userId': userId }
        util.request(api.Recommend, data).then(function (res) {
          // console.log('ress==' + JSON.stringify(res))
          res.data.register_time = util.formatTime(new Date(res.data.register_time)).substring(0, 10)
          _this.setData({
            registerTime: res.data.register_time,
            role: res.data.userLevel,

          })
        })
        
        // 页面显示
        if (userInfo && token) {
            app.globalData.userInfo = userInfo;
            app.globalData.token = token;
          }
        
        

        // this.setData({
        //     userInfo: app.globalData.userInfo,
        // });

        

    },
    onHide: function () {
        // 页面隐藏

    },
    onUnload: function () {
        // 页面关闭
    },
    
    singin(){
      var token = wx.getStorageSync('token')
      wx.request({
        url: 'https://whcmhlkj.com/api/user/sign',
        method:'POST',
        header: {
          'Content-Type': 'application/json',
          'X-Nideshop-Token': token
        },
        success:res=>{
          console.log('res=='+JSON.stringify(res))
          if(res.data.errno == 0){
            wx.showModal({
              title: '提示',
              content: res.data.errmsg,
            })
            
          }else{
            wx.showModal({
              title: '提示',
              content: res.data.errmsg,
            })
          }
        }
      })
    },
    bindGetUserInfo(e) {
      let userInfo = wx.getStorageSync('userInfo');
      let token = wx.getStorageSync('token');
      //登录并获取用户信息
      if (userInfo && token) {
        user.loginByWeixin(e.detail).then(res => {
          // console.log('res==' + JSON.stringify(res))
                this.setData({
                    userInfo: res.data.userInfo,
                    token: res.data.token,
                });
                app.globalData.userInfo = res.data.userInfo;
                app.globalData.token = res.data.token;
                app.globalData.userId = res.data.userId
                wx.setStorageSync('userId',res.data.userId )
                var _this = this
                let data = { 'userId': res.data.userId }
                util.request(api.Recommend, data).then(function (res) {
                  res.data.register_time = util.formatTime(new Date(res.data.register_time)).substring(0, 10)
                  _this.setData({
                    registerTime: res.data.register_time,
                    role: res.data.userLevel,
             
                  })
                })
            }).catch((err) => {
                console.log(err)
            });
            return
      }else{
        if (e.detail.userInfo) {
          //用户按了允许授权按钮
          this.setData({ details: e.detail })
          user.loginByWeixin(e.detail).then(res => {
            // console.log('res1==' + JSON.stringify(res))
            this.setData({
              userInfo: res.data.userInfo,
              token: res.data.token,
            });
            app.globalData.userInfo = res.data.userInfo;
            app.globalData.token = res.data.token;
            app.globalData.userId = res.data.userId
            wx.setStorageSync('userId', res.data.userId)
            var _this = this
            let data = { 'userId': res.data.userId }
            util.request(api.Recommend, data).then(function (res) {
              console.log('ress==' + JSON.stringify(res))
              res.data.register_time = util.formatTime(new Date(res.data.register_time)).substring(0, 10)
              _this.setData({
                registerTime: res.data.register_time,
                role: res.data.userLevel,

              })
            })
          }).catch((err) => {
            console.log(err)
          });
        } else {
          //用户按了拒绝按钮
          wx.showModal({
            title: '警告通知',
            content: '您点击了拒绝授权,将无法正常显示个人信息,点击确定重新获取授权。',
            success: function (res) {
              if (res.confirm) {
                wx.openSetting({
                  success: (res) => {
                    if (res.authSetting["scope.userInfo"]) {////如果用户重新同意了授权登录
                      user.loginByWeixin(e.detail).then(res => {
                        this.setData({
                          userInfo: res.data.userInfo
                        });
                        app.globalData.userInfo = res.data.userInfo;
                        app.globalData.token = res.data.token;
                      }).catch((err) => {
                        console.log(err)
                      });
                    }
                  }
                })
              }
            }
          });
        }
      }

    },

  getPhoneNumber: function (e) {
    console.log(e.detail.errMsg)
    console.log(e.detail.iv)
    console.log(e.detail.encryptedData)

    
    
    if (e.detail.errMsg == 'getPhoneNumber:fail user deny') {
      wx.showModal({
        title: '提示',
        showCancel: false,
        content: '未授权',
        success: function (res) { }
      })
    } else {
      //微信绑定手机
      var data = { 'iv': e.detail.iv, 'encryptedData': e.detail.encryptedData }
      util.request(api.Wxbindphone,data).then((res)=>{
        if (res.data.errno == 0) {
          console.log('res-' + JSON.stringify(res))
        } else {
          wx.showModal({
            title: '提示',
            content: res.data.errmsg,
          })

          return
        }
      })
      wx.showModal({
        title: '提示',
        showCancel: false,
        content: '同意授权',
        success: function (res) {
          // console.log('phone='+JSON.stringify(res))
         }
      })
    }
  },

    exitLogin: function () {
        wx.showModal({
            title: '',
            confirmColor: '#b4282d',
            content: '退出登录？',
            success: function (res) {
                if (res.confirm) {
                    wx.removeStorageSync('token');
                    wx.removeStorageSync('userInfo');
                    wx.switchTab({
                        url: '/pages/index/index'
                    });
                }
            }
        })

    }
})