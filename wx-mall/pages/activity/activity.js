// pages/article/article.
//获取应用实例
const util = require('../../utils/util.js');
const api = require('../../config/api.js');
const user = require('../../services/user.js');
const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    activity:[],
    activityId:'',
    endTime:'',
    startTime:""
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    // console.log(options.activityId)
    var _this = this
    var activityId = options.activityId
    _this.setData({activityId:activityId})
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
    var activityId = _this.data.activityId
    //新闻资讯
    util.request(api.indexUrlActivityDetail+activityId).then(function(res){
      if(res.code == 0){
        var arr = res.activity;
        var st = arr.startDate
        var et = arr.endDate
        var startTime = util.formatTime(new Date(st)).substring(0, 10)
        var endTime = util.formatTime(new Date(et)).substring(0, 10)
       _this.setData({
         activity:arr,
         startTime:startTime,
         endTime:endTime
         })
      }
      wx.setNavigationBarTitle({
        title: res.activity.title
      })
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