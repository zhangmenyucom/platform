// pages/myTeam/myTeam.js
const util = require('../../utils/util.js');
const api = require('../../config/api.js');
const user = require('../../services/user.js');
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    currentTop: 0,
    scrollTop: 180,
    userInfo:[],
    userId:'',
    teamList:[],
    // 选中的
    selectedHidden: true,
    animationHidden: true,
    selectedItem: {},
    // 几个结果
    resultHidden: true,
    datalists: [],
    dataArray:{}
  },

  bindChange: function (e) {

    var that = this;
    that.setData({ currentTab: e.detail.current });

  },
  //点击tab切换 

  swichNav: function (e) {

    var that = this;

    if (this.data.currentTab === e.target.dataset.current) {
      return false;
    } else {
      that.setData({
        currentTab: e.target.dataset.current
      })
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var userId = options.userId
    this.setData({userId:userId})
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
    var data = {'userId':_this.data.userId}
    util.request(api.Recommend,data).then((res)=>{
      
      if(res.errno == 0){
        res.data.register_time = util.formatTime(new Date(res.data.register_time)).substring(0, 10)
        res.data.last_login_time = util.formatTime(new Date(res.data.last_login_time)).substring(0, 10)
        _this.setData({ 
          dataArray: res.data,
          datalists: res.data.subUserList
        })
        console.log(JSON.stringify(res.data))
      }
    })
  },

  // 选择一列
  selectList: function (e) {
    let top = 38;
    if (e.currentTarget.dataset.item.level != 1) {
      top = 96;
    }
    this.setData({
      currentTop: e.currentTarget.offsetTop + top,
      selectedHidden: false,
      animationHidden: true,
      selectedItem: e.currentTarget.dataset.item
    });

    this.setData({
      currentTop: 48,
      animationHidden: false,
      scrollTop: 266
    });
    setTimeout(res => {
      this.setData({
        datalists: e.currentTarget.dataset.item.subUserList
      });
    }, 200);
  },
  // 列表返回上一级
  backTap: function () {
    if (this.data.selectedItem.level == 1) {
      this.setData({
        scrollTop: 48,
        selectedHidden: true,
        datalists: this.data.dataArray.subUserList
      })
    } else {
      let id = this.data.selectedItem.id;
      let tempData = this.findParent(this.data.dataArray, id);
      this.setData({
        datalists: tempData.subUserList,
        selectedItem: tempData
      })
    }
  },
  //递归找父级
  findParent: function (data, id) {
    for (let i = 0; i < data.subUserList.length; i++) {
      if (data.subUserList[i].id == id) {
        return data;
      } else {
        let childrenData = this.findParent(data.subUserList[i], id);
        if (childrenData != null) {
          return childrenData;
        }
      }
    }
    return null;
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