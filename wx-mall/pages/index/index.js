const util = require('../../utils/util.js');
const api = require('../../config/api.js');
const user = require('../../services/user.js');


//获取应用实例
const app = getApp()
Page({
  data: {
    userId:'',
    newGoods: [],
    hotGoods: [],
    topics: [],
    brands: [],
    floorGoods: [],
    banner: [],
    activity:[],
    news:[],
    channel: [
      {
        id: 1, url: 'http://pdjawoz4r.bkt.clouddn.com/whcmhlkj.com/20180822/200235950f91ee.mp4', icon_url
        :"/static/images/images/teacherVideo.png",name:'导师视频'},
      {
        id: 1, url: '/pages/category/category?id=1005000', icon_url
          : "/static/images/images/wshop.png", name: '微商城'
      },
      {
        id: 1, url: '/pages/category/category?id=1005000', icon_url
          : "/static/images/images/buss.png", name: '商家入驻'
      },
      {
        id: 1, url: '/pages/recommend/recommend?userId='+wx.getStorageSync('userId'), icon_url
          : "/static/images/images/recommend.png", name: '项目推荐'
      },
      {
        id: 1, url: '/pages/category/category?id=1005000', icon_url
          : "/static/images/images/price.png", name: '礼品兑换'
      },
      {
        id: 1, url: '/pages/category/category?id=1005000', icon_url
          : "/static/images/images/wgame.png", name: '微信小游戏'
      },
      {
        id: 1, url: '/pages/category/category?id=1005000', icon_url
          : "/static/images/images/nav.png", name: '门店导航'
      },
      {
        id: 1, url: '/pages/about/about', icon_url
          : "/static/images/images/about.png", name: '关于我们'
      },
    ]
  },
  onShareAppMessage: function () {
    return {
      title: 'NideShop',
      desc: '仿网易严选微信小程序商城',
      path: '/pages/index/index'
    }
  },onPullDownRefresh(){
	  	// 增加下拉刷新数据的功能
	    var self = this;
	    this.getIndexData();
 },
  getIndexData: function () {
    let that = this;
    var data = new Object();
    util.request(api.
    
    
    
    s).then(function (res) {
      if (res.errno === 0) {
        data.newGoods= res.data.newGoodsList
      that.setData(data);
      }
    });
    util.request(api.IndexUrlHotGoods).then(function (res) {
      if (res.errno === 0) {
        data.hotGoods = res.data.hotGoodsList
        that.setData(data);
      }
    });
    util.request(api.IndexUrlTopic).then(function (res) {
      if (res.errno === 0) {
        data.topics = res.data.topicList
      that.setData(data);
      }
    });
    util.request(api.IndexUrlBrand).then(function (res) {
      if (res.errno === 0) {
        data.brand = res.data.brandList
      that.setData(data);
      }
    });
    util.request(api.IndexUrlCategory).then(function (res) {
      if (res.errno === 0) {
        data.floorGoods = res.data.categoryList
        that.setData(data);
      }
    });
    util.request(api.IndexUrlBanner).then(function (res) {

      if (res.errno === 0) {
        data.banner = res.data.banner
        that.setData(data);
      }
    });
    
    //活动资讯
    let activityData = {'limit':3,'page':1} 
    util.request(api.IndexUrlActivity,activityData).then(function(res){
      if(res.code == 0){
        var arr = []
        for(var i=0;i<res.page.list.length;i++){
          var item = res.page.list[i]
          var st = item.startDate
          // var et = item.endDate
          item.startTime = util.formatTime(new Date(st))
          arr.push(item)
        }
        that.setData({activity:arr})
      }
    });
    //新闻资讯
    util.request(api.IndexUrlNews,activityData).then(function(res){
      console.log(res)
      if(res.code == 0){
        that.setData({news:res.page.list})
      }
    })

  },
  onLoad: function (options) {
    // this.getIndexData();
  },
  onReady: function () {
    // 页面渲染完成
  },
  onShow: function () {
    this.getIndexData();
  },
  onHide: function () {
    // 页面隐藏
  },
  onUnload: function () {
    // 页面关闭
  },
})
