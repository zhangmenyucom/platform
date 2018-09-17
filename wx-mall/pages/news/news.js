// pages/news/news.js

const util = require('../../utils/util.js');
const api = require('../../config/api.js');
const user = require('../../services/user.js');


Page({

  /**
   * 页面的初始数据
   */
  data: {
    news:[],
    limit:10,
    page:1
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
  
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
    var newsData ={'page':_this.data.page,'limit':_this.data.limit}
    util.request(api.IndexUrlNews, newsData).then(function (res) {
      console.log(res)
      if (res.code == 0) {
        var arr = res.page.list
        var arr1= []
        for(var i =0 ;i<arr.length;i++){
          var item = arr[i]
          item.createTime = util.formatTime(new Date(item.createTime)).substring(0, 10)
          arr1.push(item)
        }
        _this.setData({ news: arr1 })
      }
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