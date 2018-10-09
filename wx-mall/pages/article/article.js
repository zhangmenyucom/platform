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
    news:[],
    newsId:''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var _this = this
    var newsId = options.newsId
    wx.removeStorageSync('newsId')
    wx.setStorageSync('newsId', newsId )
    _this.setData({newsId:newsId})
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
    //新闻资讯
    util.request(api.indexUrlArticle+_this.data.newsId).then(function(res){
      console.log(res)
      if(res.code == 0){
        res.article.createTime = util.formatTime(new Date(res.article.createTime)).substring(0, 10)
        _this.setData({news:res.article})
      }
      wx.setNavigationBarTitle({
        title: res.article.title
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