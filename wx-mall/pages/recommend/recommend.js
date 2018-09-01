// pages/recommend/recommend.js
const util = require('../../utils/util.js');
const api = require('../../config/api.js');
const user = require('../../services/user.js');

Page({

  /**
   * 页面的初始数据
   */
  data: {
    nickname:'微信昵称',
    initialDate:'2018-01-01  09:00',
    list:[
      {'brokerage':0.00,'name':'佣金','icon_url':''},
      {'brokerage':0.00,'name':'可提现佣金','icon_url':''},
      {'brokerage':0.00,'name':'佣金明细','icon_url':'/static/images/images/yongjinmingxi.png'},
      {'brokerage':0,'name':'我的团队','icon_url':'/static/images/images/wodetuandui.png'}
    ],
    userInfo:[]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var userId = options.userId
    this.setData({userId:userId})
    console.log(userId)
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
  
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    var _this = this
    let data = {'userId':this.data.userId}
    util.request(api.Recommend,data).then(function(res){
      console.log('res==' + JSON.stringify(res))
      _this.setData({userInfo:res.data})
    })
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
  
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
    
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
  
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
  
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  
  }
})