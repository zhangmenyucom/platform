var util = require('../../../utils/util.js');
var api = require('../../../config/api.js');

Page({
  data:{
    orderList: [],
    page: 1,
    size: 10,
    loadmoreText: '正在加载更多数据',
    nomoreText: '全部加载完成',
    nomore: false,
    totalPages: 1
  },
  onLoad:function(options){
    // 页面初始化 options为页面跳转所带来的参数
    // 页面显示

    wx.showLoading({
      title: '加载中...',
      success: function () {

      }
    });
    this.getOrderList();
  },

  /**
       * 页面上拉触底事件的处理函数
       */
  onReachBottom: function () {
    console.log("下一页")
    this.getOrderList()
  },

  getOrderList(){
    let that = this;

    if (that.data.totalPages <= that.data.page - 1) {
      that.setData({
        nomore: true
      })
      return;
    }

    util.request(api.OrderList, {page: that.data.page, size: that.data.size}).then(function (res) {
      if (res.errno === 0) {
        console.log(res.data);
        that.setData({
          orderList: that.data.orderList.concat(res.data.data),
          page: res.data.currentPage + 1,
          totalPages: res.data.totalPages
        });
        wx.hideLoading();
      }
    });
  },
  payOrder(e){
    console.log(e);
    var index= e.target.dataset.orderIndex;
    var orderId = this.data.orderList[index].id;
    var actualPrice = this.data.orderList[index].actual_price;
    wx.redirectTo({
      url: '/pages/pay/pay?orderId=' + orderId + '&actualPrice=' + actualPrice,
    })
  },
  onReady:function(){
    // 页面渲染完成
  },
  onShow:function(){

  },
  onHide:function(){
    // 页面隐藏
  },
  onUnload:function(){
    // 页面关闭
  }
})