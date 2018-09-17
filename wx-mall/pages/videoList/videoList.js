// pages/videoList/videoList.js
const util = require('../../utils/util.js');
const api = require('../../config/api.js');
const user = require('../../services/user.js');

Page({

  /**
   * 页面的初始数据
   */
  data: {
    videoList:[],
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
    var data = {'limit':_this.data.limit,'page':_this.data.page}
    util.request(api.VideoList,data).then((res)=>{
      if(res.code == 0){
        var arr = res.page.list
        var arr1 = []
        for(var i=0;i<arr.length;i++){
          var item = arr[i]
          item.createTime = util.formatTime(new Date(item.createTime)).substring(0, 10)
          arr1.push(item)
        }
        _this.setData({videoList:arr1})
        console.log(_this.data.videoList)
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