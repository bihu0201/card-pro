var app = getApp()
Page({
  data: {
    imgUrls: [
      '../../img/1.png',
      '../../img/2.png',
      '../../img/3.png',
      '../../img/4.png',
      '../../img/5.png',
      '../../img/6.png',
      '../../img/7.png',
      '../../img/google.png'
    ],
    indicatorDots: true,
    autoplay: true,
    circular: true,
    interval: 5000,
    duration: 1000,
    LoadingTime: "",
    urlWxgzh:"https://wechat.gsxmkj.com/images/gzh.jpg"
  },
  onLoad:function(){

  },
  goBaidu: function () {
    wx.navigateTo({
      url: '../out/out', //
      success: function () {
                 console.log("aaaaaaaaaaaaaaaa")
      },       //成功后的回调；
      fail: function () { },         //失败后的回调；
      complete: function () {
        console.log("cccc")
      }      //结束后的回调(成功，失败都会执行)
    })
  },
  userUrl: function () {
    var _this = this;
    var flg = 0;
    _this.setData({
      LoadingTime:setInterval(function(){
        //取用户openid
        var userOpenId = (wx.getStorageSync('userOpenId'));//用户的id
        if (userOpenId) {
        } else {
          wx.login({
            success: function (res) {
              var weixin_code = res.code;//获取code
              wx.request({
                url: 'https://wechat.gsxmkj.com/api/wechat/getwechat',
                data: {
                  code: weixin_code
                },
                method: "post",
                header: {
                  'content-type': 'application/x-www-form-urlencoded',
                },
                dataType: 'json',
                success: function (res) {
                  if (res.data.code == 0) {
                    //如果成功 存入缓存
                    wx.setStorageSync('userOpenId', res.data.data.openId);
                    userOpenId = res.data.data.openId;
                  }
                }
              })
            }
          })
        }
        //查询用户
        wx.request({
          url: 'https://wechat.gsxmkj.com/api/userTag/getBean',
          data: {
            userOpenId: userOpenId,
            userId: app.globalData.userId
          },
          method: "get",
          header: {
            'content-type': 'application/x-www-form-urlencoded',
          },
          dataType: 'json',
          success: function (res) {
            if (res.data.code == 0) {
              if (res.data.data[0].tag == '1') {
                //表示有资格
                app.globalData.flg = 1;
                flg = 1;
              }
            }
          }
        })
        if(flg == 1){
          app.globalData.flg = 1;
          wx.navigateBack({
            delta:1
          })
          clearInterval(_this.data.LoadingTime);
        }
      },1000)
    })

  },
  onShow: function () {
    this.userUrl();
  },
  previewImage: function (e) {
    var current = e.target.dataset.src;
    wx.previewImage({
      current: current,
      urls: this.data.urlWxgzh.split(',')
      // 需要预览的图片http链接  使用split把字符串转数组。不然会报错
    })
  }




})
