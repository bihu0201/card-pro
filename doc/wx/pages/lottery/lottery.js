const app = getApp();
Page({
  data: {
    circleList: [],//圆点数组
    awardList: [],//奖品数组
    colorCircleFirst: '#FFDF2F',//圆点颜色1
    colorCircleSecond: '#FE4D32',//圆点颜色2
    colorAwardDefault: '#F5F0FC',//奖品默认颜色
    colorAwardSelect: '#871a8e',//奖品选中颜色
    indexSelect: 0,//被选中的奖品index
    isRunning: false,//是否正在抽奖
    imageAward: [],//奖品图片数组
    text: "恭喜用户市1367511****于2018-10-23 1:48:30获取吉祥如意奖:雨伞",
    marqueePace: 1,//滚动速度
    marqueeDistance: 0,//初始滚动距离
    marquee_margin: 30,
    size: 14,
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    interval: 20, // 时间间隔
    activityId:"",
    LoadingTime:""
  },

  onLoad: function (options) {  
    var that = this;
    var userid = decodeURIComponent(options.userid)
    if (userid) { 
      var userid1 = userid;//商户的userid
      console.log(userid1);
      app.globalData.userId = userid1
    }
   
    /*
    wx.getUserInfo({
      success: res => {
        this.globalData.userInfo = res.userInfo
      }
    }) 
    */
    this.cainilike(that.callbackLike);
    
  },
  userTag:function(){
   
    if (app.globalData.flg == 0){
      this.userUrl();
    }

  },
  /*
     wx.navigateTo({
      url: '../register/register'
    })*/
  userUrl:function(){
    var that = this;
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
    //查询用户是否有资格进入此界面
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
        console.log(res.data);
        if (res.data.code == 0) {
            if (res.data.data == '') {
              app.globalData.flg = 0;
              wx.request({
                url: 'https://wechat.gsxmkj.com/api/userTag/add',
                data: {
                  userOpenId: userOpenId,
                  tag:0,
                  userId:app.globalData.userId
                },
                method: "post",
                header: {
                  'content-type': 'application/x-www-form-urlencoded',
                },
                dataType: 'json',
                success: function (res) {
                }
              })
            } else {
            if(res.data.data[0].tag == '1'){
              app.globalData.flg = 1;
            }else{
              app.globalData.flg = 0;
            }
          }
        }
      }
    })
    if (app.globalData.flg == 0) {
      wx.navigateTo({
        url: '../register/register'
      })
    }else{
      console.log("1");
    }

  },
  
  globalData:{
      userInfo:null,
      userId: "118"
  },

  cainilike: function (callback) {
    var that = this;
    var userText = "";
    wx.request({
      url: 'https://wechat.gsxmkj.com/api/user/userInfo',
      data:{
        userId: app.globalData.userId
      }, 
      method: "get",
      header: {
        'content-type': 'application/x-www-form-urlencoded',
        'Accept': 'application/json'
      },
      dataType: 'json',
      success: function (res) {
        if (res.data.code == 0) {
          console.log(res)
          var list=[];
          for (var i = 0; i < res.data.data.awardList.length; i++) {
            list.push(res.data.data.awardList[i].awardIcon);
          }
          for (var i = 0; i < res.data.data.memberAwardList.length; i++) {
            if (res.data.data.memberAwardList[i].awardId != '9'){
              userText = userText + "恭喜用户 " + res.data.data.memberAwardList[i].wechatName + "于" + res.data.data.memberAwardList[i].awardTime + "获得" + res.data.data.memberAwardList[i].award.awardName + ":" + res.data.data.memberAwardList[i].award.remark+"-------------";
             }
          }
          that.setData({
            activityId : res.data.data.activitieList[0].id
          })
          that.setData({
            text: userText
          })
          callback(list);
        } else {
          console.log("获取失败");
        }
      }
    })
  
  },
  callbackLike: function (res) {
    this.setData({
      imageAward:res,
    })
    var _this = this;
    //圆点设置
    var leftCircle = 7.5;
    var topCircle = 7.5;
    var circleList = [];
    for (var i = 0; i < 24; i++) {
      if (i == 0) {
        topCircle = 15;
        leftCircle = 15;
      } else if (i < 6) {
        topCircle = 7.5;
        leftCircle = leftCircle + 102.5;
      } else if (i == 6) {
        topCircle = 15
        leftCircle = 620;
      } else if (i < 12) {
        topCircle = topCircle + 94;
        leftCircle = 620;
      } else if (i == 12) {
        topCircle = 565;
        leftCircle = 620;
      } else if (i < 18) {
        topCircle = 570;
        leftCircle = leftCircle - 102.5;
      } else if (i == 18) {
        topCircle = 565;
        leftCircle = 15;
      } else if (i < 24) {
        topCircle = topCircle - 94;
        leftCircle = 7.5;
      } else {
        return
      }
      circleList.push({ topCircle: topCircle, leftCircle: leftCircle });
    }
    this.setData({
      circleList: circleList
    })
    //圆点闪烁
    setInterval(function () {
      if (_this.data.colorCircleFirst == '#FFDF2F') {
        _this.setData({
          colorCircleFirst: '#FE4D32',
          colorCircleSecond: '#FFDF2F',
        })
      } else {
        _this.setData({
          colorCircleFirst: '#FFDF2F',
          colorCircleSecond: '#FE4D32',
        })
      }
    }, 500)
    //奖品item设置
    var awardList = [];
    //间距,怎么顺眼怎么设置吧.
    var topAward = 25;
    var leftAward = 25;
    for (var j = 0; j < 8; j++) {
      if (j == 0) {
        topAward = 25;
        leftAward = 25;
      } else if (j < 3) {
        topAward = topAward;
        //166.6666是宽.15是间距.下同
        leftAward = leftAward + 166.6666 + 15;
      } else if (j < 5) {
        leftAward = leftAward;
        //150是高,15是间距,下同
        topAward = topAward + 150 + 15;
      } else if (j < 7) {
        leftAward = leftAward - 166.6666 - 15;
        topAward = topAward;
      } else if (j < 8) {
        leftAward = leftAward;
        topAward = topAward - 150 - 15;
      }
      var imageAward = this.data.imageAward[j];
      awardList.push({ topAward: topAward, leftAward: leftAward, imageAward: imageAward });
    }
    this.setData({
      awardList: awardList
    })
  },  
  //开始游戏
  startGame: function () {
    var that = this;
    var userOpenId = (wx.getStorageSync('userOpenId'));//用户的id
    var resRquest = 0;
    var awardName = "";
    var remark = "";
    var msg = "";
    var code = 0;
    var wechatName = "";
    if (userOpenId) {
    }else{
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
    wx.request({
      url: 'https://wechat.gsxmkj.com/api/memberaward/draw',
      data: {
        wechatCode: userOpenId,
        wechatName:this.globalData.userInfo.nickName,
        userId: app.globalData.userId,
        activityId: this.data.activityId
      },
      method: "post",
      header: {
        'content-type': 'application/x-www-form-urlencoded',
      },
      dataType: 'json',
      success: function (res) {
        if (res.data.code == 0) {
          //console.log(res.data.data.msg);
      
         // console.log(res.data.data.awards[0].awardId);
          //console.log(res.data.data.awards[0].award.awardOk);
          msg = res.data.data.msg;
          resRquest = res.data.data.awards[0].awardId;
          awardName = res.data.data.awards[0].award.awardName;
          remark = res.data.data.awards[0].remark;
          code = res.data.code;
        } else {
          //console.log(res.data.data.msg);
          //console.log(res.data.data.awards);
          //console.log(res.data.data.awards[0].awardId);
         // console.log(res.data.data.awards[0].award.awardOk);
          msg = res.data.data.msg;
          resRquest = res.data.data.awards[0].awardId;
          awardName = res.data.data.awards[0].award.awardName;
          remark = res.data.data.awards[0].remark;
          code = res.data.code;
        }
      }
    })
    if (this.data.isRunning) return
    this.setData({
      isRunning: true
    })
    var _this = this;
    var indexSelect = 0;
    var i = 0;
    
    var timer = setInterval(function () {
      if(code != 0){
        i = 1001;
      }
      indexSelect++;
      //这里我只是简单粗暴用y=30*x+200函数做的处理.可根据自己的需求改变转盘速度
      i += 30;
      if (i > 1000) {
        //去除循环
        clearInterval(timer)
        //console.log(resRquest);
       
        switch (resRquest){
          case 1:
            indexSelect = 0
          break;
          case 2:
            indexSelect = 1
            break;
          case 3:
            indexSelect = 2
            break;
          case 4:
            indexSelect = 3
            break;
          case 6:
            indexSelect = 4
            break;
          case 7:
           indexSelect = 5
            break;
          case 8:
            indexSelect = 6
            break;
          case 9:
            indexSelect = 7
            break;
        }
        if (resRquest == 9){
          wx.showModal({
            title: '很遗憾您没有中奖',
            content: awardName,
            // content: '吉祥如意奖: 插线板',
            showCancel: false,//去掉取消按钮
            success: function (res) {
              console.log(_this.data.indexSelect);
              if (res.confirm) {
                _this.setData({
                  isRunning: false
                })
              }
            }
          })
        }else{
          //console.log(awardOk);
          if (remark == null || remark == 'null'){
            remark = "";
          }else{
            remark = ":" + remark
          }
          wx.showModal({
            title: msg,
            confirmColor:"#FFDF2F",
            content: awardName + remark,
            // content: '吉祥如意奖: 插线板',
            showCancel: false,//去掉取消按钮
            success: function (res) {
             // console.log(_this.data.indexSelect);
              let bgMusic = wx.getBackgroundAudioManager();
              bgMusic.src = "https://wechat.gsxmkj.com/zhongjiang.mp3";
              bgMusic.play();
              setTimeout(function () {
                let bgMusic = wx.getBackgroundAudioManager();
                bgMusic.stop();
              }, 8000) //延迟时间 这里是*/
              if (res.confirm) {
                _this.setData({
                  isRunning: false
                })
              }
            }
          })
        } 
      }
      indexSelect = indexSelect % 8;
      _this.setData({
        indexSelect: indexSelect
      })
    }, (200 + i))
  },
  onShow: function () {
    this.userUrl();
    var that = this;
    var length = that.data.text.length * that.data.size;//文字长度
    var windowWidth = wx.getSystemInfoSync().windowWidth;// 屏幕宽度
    //console.log(length,windowWidth);
    that.setData({
      length: length,
      windowWidth: windowWidth
    });
    that.scrolltxt();// 第一个字消失后立即从右边出现
  },
  scrolltxt: function () {
    var that = this;
    var length = that.data.length;//滚动文字的宽度
    var windowWidth = that.data.windowWidth;//屏幕宽度
    if (length > windowWidth) {
      var interval = setInterval(function () {
        var maxscrollwidth = length + that.data.marquee_margin;//滚动的最大宽度，文字宽度+间距，如果需要一行文字滚完后再显示第二行可以修改marquee_margin值等于windowWidth即可
        var crentleft = that.data.marqueeDistance;
        if (crentleft < maxscrollwidth) {//判断是否滚动到最大宽度
          that.setData({
            marqueeDistance: crentleft + that.data.marqueePace
          })
        }
        else {
          //console.log("替换");
          that.setData({
            marqueeDistance: 0 // 直接重新滚动
          });
          clearInterval(interval);
          that.scrolltxt();
        }
      }, that.data.interval);
    }
    else {
      that.setData({ marquee_margin: "1000" });//只显示一条不滚动右边间距加大，防止重复显示
    }
  },
  bindGetUserInfo: function (e) {
    this.globalData.userInfo = e.detail.userInfo
    this.startGame()
  }
})