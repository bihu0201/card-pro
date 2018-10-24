layui.config({
	base : "js/"
}).use(['form','element','layer','jquery'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		element = layui.element(),
		$ = layui.jquery;

	$(".panel a").on("click",function(){
		window.parent.addTab($(this));
	})


	//用户数
	$.get("../json/usersList.json",
		function(data){
			$(".userAll span").text(data.length);
		}
	)

	// //新消息
	// $.get("../json/message.json",
	// 	function(data){
	// 		$(".newMessage span").text(data.length);
	// 	}
	// )


	//数字格式化
	$(".panel span").each(function(){
		$(this).html($(this).text()>9999 ? ($(this).text()/10000).toFixed(2) + "<em>万</em>" : $(this).text());	
	})

	//系统基本参数
	if(window.sessionStorage.getItem("systemParameter")){
		var systemParameter = JSON.parse(window.sessionStorage.getItem("systemParameter"));
		fillParameter(systemParameter);
	}else{
		$.ajax({
			url : "../json/systemParameter.json",
			type : "get",
			dataType : "json",
			success : function(data){
				fillParameter(data);
			}
		})
	}

	//填充数据方法
 	function fillParameter(data){
 		//判断字段数据是否存在
 		function nullData(data){
 			if(data == '' || data == "undefined"){
 				return "未定义";
 			}else{
 				return data;
 			}
 		}
 		$(".version").text(nullData("1.0.0"));
		$(".author").text(nullData("windows"));
		$(".homePage").text(nullData("甘肃鑫媒网络科技服务有限公司"));
		$(".server").text(nullData("甘肃省张掖市甘州区丽都街5号甘州明珠A塔22层2203室"));
		$(".dataBase").text(nullData("0936-8688005"));
		$(".maxUpload").text(nullData("1834807484@qq.com"));
		$(".userRights").text(nullData("查看客户抽奖信息，抽奖用户管理，礼品管理"));
 	}

})
