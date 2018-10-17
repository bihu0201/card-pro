// pages/register/register.js
const app = getApp()
Page({
  /**
  * 页面的初始数据
  */
  data: {
    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    loginName : "",
    userNme : "",
    email : "",
    phone : ""
  },
  loginNameInput: function (e) {
    this.setData({
      loginName: e.detail.detail.value
    })
  },
  userNameInput: function (e) {
    this.setData({
      userNme: e.detail.detail.value
    })
  },
  emailInput: function (e) {
    this.setData({
      email: e.detail.detail.value
    })
  },
  phoneInput: function (e) {
    this.setData({
      phone: e.detail.detail.value
    })
  },
  //事件处理函数
  bindViewTap: function () {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  register: function (event){
    var weixin_code = "";
    //获取code
    wx.login({
      success: function (res) {
        if (res.code) {
          weixin_code = res.code;
          //获取openid
            wx.request({
              url: 'http://localhost:8050/api/wechat/getwechat',
              data:{
                code:weixin_code
              },
              method: "post",
              header: {
                'content-type': 'application/x-www-form-urlencoded',
              },
              dataType: 'json',
              success: function (res) {
                wx.showToast({
                  title: '成功',
                  icon: 'succes',
                  duration: 2000,
                  mask: true
                })
              }
            })
        } else {
          console.log("用户标识获取失败");
        }
      }
    })  
    /*
    wx.request({
      url:'http://localhost:8050/api/user/register',
      data:{
        loginName: this.data.loginName,
        userName: this.data.userNme,
        email: this.data.email,
        phonenumber: this.data.phone,
        avatar:"",
        wechatCode:"adminmwb",
        wechat_icon:"/qweqwe/qweqwewq/qweqewqe.png"

      },
      method:"post",
      header: {
        'content-type': 'application/x-www-form-urlencoded',
      },
      dataType:'json',
      success: function (res) {
        if(res.code == 0){
          console.log(res.data+"");
        }else{
          console.log("注册失败");
        }
      }
    })
    */
  },
 

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    if (app.globalData.userInfo) {
      this.setData({
        userInfo: app.globalData.userInfo,
        hasUserInfo: true
      })
    } else if (this.data.canIUse) {
      // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
      // 所以此处加入 callback 以防止这种情况
      app.userInfoReadyCallback = res => {
        this.setData({
          userInfo: res.userInfo,
          hasUserInfo: true
        })
      }
    } else {
      // 在没有 open-type=getUserInfo 版本的兼容处理
      wx.getUserInfo({
        success: res => {
          app.globalData.userInfo = res.userInfo
          this.setData({
            userInfo: res.userInfo,
            hasUserInfo: true
          })
        }
      })
    }
  },
  getUserInfo: function (e) {
    console.log(e)
    app.globalData.userInfo = e.detail.userInfo
    this.setData({
      userInfo: e.detail.userInfo,
      hasUserInfo: true
    })
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
