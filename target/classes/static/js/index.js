$(document).ready(function() {
	
	
	$(".artcle-detail").mousemove(function () {
		$(this).children(".u-title").css("color","#4B0082");
	}).mouseleave(function () {
		$(this).children(".u-title").css("color","#878787");
	})

	$(".u-title").click(function () {
		var href=$(this).attr("href");
		window.open(href,"_self");
	})

	$(".u-list").click(function () {
		var href=$(this).attr("href");
		window.open(href,"_self");
	})



	
	$(".menu-title:first").next().slideDown(500);
	var mail=0;
	$(".menu-title").click(function() {
		var display = $(this).next().css("display")
		if(display == 'none') {
			$(".menu-title").css("background-color", "rgba(247,247,248,0.1)");
			$(".menus").slideUp();
			$(this).next().slideDown(500);
			$(this).css("background-color", "rgba(227,227,227,0.1)");
		} else {
			$(".menu-title").css("background-color", "rgb(247,247,248,0.1)");
			$(this).next().slideUp(500);
		}
	})

	$(".menu-new").click(function() {
		$(this).css("background-color", "rgb(247,247,248,0.2)");
		$(this).siblings().css("background-color", "rgb(227,227,227,0.2)");
	})

	$(".input-lable").hover(function() {
		$(".glyphicon-search").css("color", "DimGrey");;
	}, function() {
		$(".glyphicon-search").css("color", "black");;
	});

	$(".u-list").click(function () {
		location.href=$(this).children(".u-list-detail").attr("value");
	})

	$(document).on("click",".artcle-detail",function () {
		location.href=$(this).children("h4").attr("href");
	})


	//输入框移动  下划线 
	$(".login-input input").focus(function() {
		$(this).parent(".login-input").each(function() {
			$("label", this).css({
				"line-height": "0px",
				"font-size": "12px",
				"font-weight": "100",
				"top": "0px"
			})
			$(".spin", this).css({
				"width": "100%"
			})
		});
	}).blur(function() {
		$(".spin").css({
			"width": "0px"
		})
		if($(this).val() == "") {
			$(this).parent(".login-input").each(function() {
				$("label", this).css({
					"line-height": "40px",
					"font-size": "15px",
					"font-weight": "300",
					"top": "10px"
				})
			});
		}
	});

	//切换登录或注册
	$(".res-span").click(function() {
		$(this).parents(".box").fadeOut(600, function() {
			$(".overbox").fadeIn(600);
		});
	})
	$(".login-span").click(function() {
		$(this).parents(".overbox").fadeOut(600, function() {
			$(".box").fadeIn(600);
		});
	})

	// 点击后刷新验证码
	$("#canvas").on('click', function() {
		code_draw();
	})


	$("#login-go").on('click', function() {
		// 将输入的内容转为大写，可通过这步进行大小写验证
		var val = $(".login-number").val().toLowerCase();
		// 获取生成验证码值
		var num = $('#canvas').attr('data-code');
		
		var userName=$("#username").val();

		var  reg=/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
		if(userName==''||(!reg.test(userName))){
			$("#username").focus();
			spop({
			template: "邮箱不能为空或邮箱格式不正确", // string required. Without it nothing happens!
			style: 'error', // error or success
			autoclose: 3000, // miliseconds
			position: 'top-right', // top-left top-center bottom-left bottom-center bottom-right
			icon: true, // or false
			group: false // string, add a id reference 
			});
			return;
		}
		var userpassword=$("#userpassword").val();
		if(userpassword==''|| userpassword.length<6){
			$("#userpassword").focus();
			spop({
			template: "密码不能为空或者密码长度小于6位", // string required. Without it nothing happens!
			style: 'error', // error or success
			autoclose: 3000, // miliseconds
			position: 'top-right', // top-left top-center bottom-left bottom-center bottom-right
			icon: true, // or false
			group: false // string, add a id reference 
			});
			return;			
		}
		
		if(val == '') {
			$(".login-number").focus();
			spop({
			template: "请输入验证码", // string required. Without it nothing happens!
			style: 'error', // error or success
			autoclose: 3000, // miliseconds
			position: 'top-right', // top-left top-center bottom-left bottom-center bottom-right
			icon: true, // or false
			group: false // string, add a id reference 
			});
			return;
		}
		else if(val!=num){
			$(".login-number").focus();
			code_draw();
			spop({
			template: "验证码输入错误", // string required. Without it nothing happens!
			style: 'error', // error or success
			autoclose: 3000, // miliseconds
			position: 'top-right', // top-left top-center bottom-left bottom-center bottom-right
			icon: true, // or false
			group: false // string, add a id reference 
			});
			return;
		}
		
		if(userName!=''&&userpassword!=''&&val!=''){
			$.getJSON("user/login",{"email":userName,"password":userpassword},function (data) {
				var page=data.page;
				if (page.indexOf("error")==-1){
					code_draw();
					spop({
						template: "登录成功", // string required. Without it nothing happens!
						style: 'success', // error or success
						autoclose: 3000, // miliseconds
						position: 'top-right', // top-left top-center bottom-left bottom-center bottom-right
						icon: true, // or false
						group: false // string, add a id reference
					})
					location.reload();
					return true;
				}
				else{
					code_draw();
					spop({
						template: "邮箱或密码错误。", // string required. Without it nothing happens!
						style: 'error', // error or success
						autoclose: 3000, // miliseconds
						position: 'top-right', // top-left top-center bottom-left bottom-center bottom-right
						icon: true, // or false
						group: false // string, add a id reference
					})
					return false;
				}

			})

		}
	})

	function getRootPath_web() {
		//获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
		var curWwwPath = window.document.location.href;
		//获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
		var pathName = window.document.location.pathname;
		var pos = curWwwPath.indexOf(pathName);
		//获取主机地址，如： http://localhost:8083
		var localhostPaht = curWwwPath.substring(0, pos);
		//获取带"/"的项目名，如：/uimcardprj
		var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
		return (localhostPaht + projectName);
	}

	$("#res-go").on('click', function() {
		var userName=$("#regname").val();
		var  reg=/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
		if(userName==''|| !reg.test(userName)){
			$("#regname").focus();
			spop({
			template: "请输入邮箱", // string required. Without it nothing happens!
			style: 'error', // error or success
			autoclose: 3000, // miliseconds
			position: 'top-right', // top-left top-center bottom-left bottom-center bottom-right
			icon: true, // or false
			group: false // string, add a id reference
			});
			return;
		}
		var userpassword=$("#regpass").val();
		if(userpassword==''|| userpassword.length<6){
			$("#regpass").focus();
			spop({
			template: "密码不能为空或者密码长度小于6位", // string required. Without it nothing happens!
			style: 'error', // error or success
			autoclose: 3000, // miliseconds
			position: 'top-right', // top-left top-center bottom-left bottom-center bottom-right
			icon: true, // or false
			group: false // string, add a id reference
			});
			return;
		}
		var userpassword2=$("#re-regpass").val();
		if(userpassword2==''|| userpassword2.length<6){
			$("#re-regpass").focus();
			spop({
			template: "请重新确认密码", // string required. Without it nothing happens!
			style: 'error', // error or success
			autoclose: 3000, // miliseconds
			position: 'top-right', // top-left top-center bottom-left bottom-center bottom-right
			icon: true, // or false
			group: false // string, add a id reference
			});
			return;
		}
		if(userpassword2!=userpassword){
			$("#re-regpass").focus();
			$("#regpass").focus();
			spop({
			template: "两次密码不正确", // string required. Without it nothing happens!
			style: 'error', // error or success
			autoclose: 3000, // miliseconds
			position: 'top-right', // top-left top-center bottom-left bottom-center bottom-right
			icon: true, // or false
			group: false // string, add a id reference
			});
			return;
		}
		var val = $(".v-number").val();
		if(val == '') {
			$(".qq-val").focus();
			spop({
			template: "请输入邮件接收的验证码", // string required. Without it nothing happens!
			style: 'error', // error or success
			autoclose: 3000, // miliseconds
			position: 'top-right', // top-left top-center bottom-left bottom-center bottom-right
			icon: true, // or false
			group: false // string, add a id reference
			});
			return;
		}
		else if(val!=mail){
			spop({
			template: "邮件验证码输入错误", // string required. Without it nothing happens!
			style: 'error', // error or success
			autoclose: 3000, // miliseconds
			position: 'top-right', // top-left top-center bottom-left bottom-center bottom-right
			icon: true, // or false
			group: false // string, add a id reference
			});
			return;
		}
		if(userName!=''&&userpassword2!=''&&val!=''){
				var email=$("#regname").val();
				var pwd=$("#re-regpass").val();
				var num=$(".v-number").val();
				$.getJSON("" +
					"" +
					"user/register",{"email":email,"password":pwd,"qqSent":num},function (data){
					if (data.bool=='true'){
						spop({
							template: "注册成功", // string required. Without it nothing happens!
							style: 'success', // error or success
							autoclose: 3000, // miliseconds
							position: 'top-right', // top-left top-center bottom-left bottom-center bottom-right
							icon: true, // or false
							group: false // string, add a id reference
						})
					}
					else {
						spop({
							template: "注册失败", // string required. Without it nothing happens!
							style: 'error', // error or success
							autoclose: 3000, // miliseconds
							position: 'top-right', // top-left top-center bottom-left bottom-center bottom-right
							icon: true, // or false
							group: false // string, add a id reference
						})
						return false;
					}
				})
			return true;
		}


	})




	$("#sendemail").click(function () {
		var userName=$("#regname").val();
		var  reg=/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
		if(userName==''|| ! reg.test(userName)){
			$("#regname").focus();
			spop({
				template: "邮箱不能为空或格式不正确", // string required. Without it nothing happens!
				style: 'error', // error or success
				autoclose: 3000, // miliseconds
				position: 'top-right', // top-left top-center bottom-left bottom-center bottom-right
				icon: true, // or false
				group: false // string, add a id reference
			});
			return false;
		}
		if(i==60){
			$(this).css({"disabled":"disabled","cursor":"not-allowed"});
			time=setInterval (hide,1000);
		}
		$.getJSON("user/email",{"email":userName},function (data) {
			mail=data.num;
		})
		
	})
	
	var time=0;

	
	var i=60;
		var hide=function(){
		i--;
		$(".qq-send").html("剩余"+i+"秒");
		if(i==0){
				clearInterval (time);
				$(".qq-send").html("重新发送");
				i=60;
				$(".qq-send").css({"disabled":"","cursor":"pointer"});
			}
	}


	   var value=$(".glyphicon-thumbs-up").next().attr("value");
		if (value=="true"){
			$(".glyphicon-thumbs-up").css("color","red");
		}
	   if (value=="false"){
			$(".glyphicon-thumbs-up").css("color","#FD8698");
		}


	$(".glyphicon-thumbs-up").click(function () {
		var like=parseInt($(this).next().attr("like"));

		if($(this).css("color")=="rgb(255, 0, 0)")
		{
			if(like==0){
				return false;
			}
			$(this).css("color","#FD8698");
			$(this).next().html((parseInt()-1)+"人点赞");
			$(this).next().attr("like",parseInt(like)-1);
		}
		else if($(this).css("color")=="rgb(253, 134, 152)"){
			$(this).css("color","red");
			$(this).next().html((parseInt(like)+1)+"人点赞");
			$(this).next().attr("like",parseInt(like)+1);
		}
		var a_id=$(this).attr("a_id");
		$.getJSON("/saylaughs/like",{"a_id":a_id},function (data) {
			alert(data.info)
			console.log(data.info);
		})
	})


	window.setInterval(long_time,1000);

	function  long_time() {
			var now=new Date();
			var endTime = new Date("2020-08-01 0:0:0");
			var diff=endTime.getTime()-now.getTime();
			diff=diff/1000;
			var day,hour,minute,seconds;
			day=Math.floor(diff/(24*60*60));
			hour=Math.floor((diff/(60*60))%24);
			minute=Math.floor(diff/60%24);
			seconds=Math.floor(diff%60);
			var str;
			str=toTwo(day)+"天"+toTwo(hour)+"个小时"+toTwo(minute)+'分钟'+toTwo(seconds)+"秒";
			$(".long-time").html(str);
	}

	function toTwo(val){
		val=val+"";
		val=val.replace("-","");
		return val<10?'0'+val:val
	}


	$(".ower").click(function () {
		if ($(this).html()=="登录/信息"){
			//切换登录模块和信息模块。
			code_draw();
			var init=$(".init-div").css("display");
			var login=$(".login-div").css("display");
			if(init=="none"){
				$(".init-div").slideDown();
				$(".login-div").slideUp();
			}
			if(login=="none"){
				$(".init-div").slideUp();
				$(".login-div").slideDown();
			}
		}
	})


	var pages=1;
	var pageSize=5;

	$(".input-lable").click(function () {
		if ($(".search-input").val()!="") {
			$(".search-init").stop().show();
			$(".page-init").show();
			pages = 1;
			pageSize = 5;
			pageClick(pages, pageSize);
		}
		else{
			$(".search-init").stop().hide();
		}
	})

	$(".previous").click(function () {
		pages--;
		$(".search-init").stop().show();
		if (pages==1){
			return;
		}
		pageClick(pages,pageSize);
	})

	$(".next").click(function () {
		$(".search-init").stop().show();
		pages++;
		pageClick(pages,pageSize)
	})

	$(".search-input").click(function () {
		if ($(this).val()!="") {
			$(".search-init").stop().show(200);
		}
	})

	var contentName='/saylaugh';

	function pageClick(pages,pageSize){
		var text=$(".search-input").val();
		$(".search-div ul").empty();
			$.getJSON(contentName+"/article/hotArticle",{"text":text,"page":pages,"pageSize":pageSize},function (data) {
				for (var i=0;i<data.list.length;i++){
					$(".search-div ul").append("<li value="+contentName+"/article/"+data.list[i].a_id+">"+data.list[i].body.substring(0,10)+"<span class='author'>作者："+data.list[i].say_user.userName+"</span></li>")
				}
				$(".currPage").html(data.currPage+"页/"+data.endPage+"页");

				if (data.currPage==1){
					$(".previous").hide(500);
				}
				else{
					$(".previous").show(500);
				}

				if (data.currPage==data.endPage){
					$(".next").hide(500);
				}
				else{
					$(".next").show(500);
				}
			})
		$(".search-init").stop().slideDown(200);
	}

	$(document).on("click",".search-init li",function () {
		 location.href=$(this).attr("value");
	})

	$(".search-init").mouseleave(function () {
		$(this).hide();
	})

	$(".list-unstyled li").click(function () {
		var url=$(this).children("a").attr("value");
		$.getJSON(url,function (data) {
			$(".artcle-detail").remove();
			for (var i=0;i<data.list.length;i++){
				$("#carousel-example-generic").after("<div class='artcle-detail' id='first-artcle'>" +
					"<h4 class='u-title' href='"+contentName+"/article/"+data.list[i].a_id+"'>"+data.list[i].title+"</h4>" +
					"<div class='u-detail' >"+data.list[i].a_text+"</div><div class='u-img'><img src='"+contentName+"/static/img/"+data.list[i].cover_photo	+"'  /></div>" +
					"<div class='u-author'><span class='u-like'><label a_id="+data.list[i].a_id+"  class='glyphicon glyphicon-thumbs-up'></label>" +
					"<label like="+data.list[i].favour+">"+data.list[i].favour+"人点赞</label></span>" +
					"<span class='u-browse'><label class='glyphicon glyphicon-eye-open '>" +
					"</label><label>"+data.list[i].readSum+"</label></span><span class='u-user'>" +
					"<label class='glyphicon glyphicon-user '></label><label>" +
					"<a href='/ower/"+data.list[i].say_user.user_id+"'>"+data.list[i].say_user.userName+"</a></label></span>" +
					"<span class='u-date'><label  class='glyphicon glyphicon-time'></label>" +
					"<label>"+data.list[i].send_time+"</label></span> </div> </div>")
			}
		})
	})


	$(".recommend").click(function () {
		var url=contentName+"/article/recommend";
		$.getJSON(url,function (data) {
			reloadData(data);
		})
	})

	$(".hotArticle").click(function () {
		var url=contentName+"/article/order";
		$.getJSON(url,function (data) {
			reloadData(data);
		})
	})

	$(".timeArticle").click(function () {
		var url=contentName+"/article/time";
		$.getJSON(url,function (data) {
			reloadData(data);
		})
	})
	
	$(".ownerArticle").click(function () {
		var url=contentName+"/article/ownerArticle";
		// alert(2)
		$.getJSON(url,function (data) {
			if (data.list==null){
				if ($(".login-div").css("display")=="none"){
					$(".init-div").slideUp(500);
					$(".login-div").slideDown(500);
					$("#username").focus();
				}
				else{
					$("#username").focus();
				}
				return;
			}
			else{
				// alert(22332)
				$(".artcle-detail").remove();
				for (var i=0;i<data.list.length;i++){
					$("#carousel-example-generic").after("<div class='artcle-detail' id='first-artcle'>" +
						"<h4 class='u-title' href='"+contentName+"/article/"+data.list[i].a_id+"'>"+data.list[i].title+"</h4>" +
						"<div class='u-detail' >"+data.list[i].a_text+"</div><div class='u-img'><img src='"+contentName+"/static/img/"+data.list[i].cover_photo	+"'  /></div>" +
						"<div class='u-author'><span class='u-like'><label a_id="+data.list[i].a_id+"  class='glyphicon glyphicon-thumbs-up'></label>" +
						"<label like="+data.list[i].favour+">"+data.list[i].favour+"人点赞</label></span>" +
						"<span class='u-browse'><label class='glyphicon glyphicon-eye-open '>" +
						"</label><label>"+data.list[i].readSum+"</label></span><span class='u-user'>" +
						"<label class='glyphicon glyphicon-user '></label><label>" +
						"<a href='/ower/"+data.list[i].say_user.user_id+"'>"+data.list[i].say_user.userName+"</a></label></span>" +
						"<span class='u-date'><label  class='glyphicon glyphicon-time'></label>" +
						"<label>"+data.list[i].send_time+"</label></span> </div> </div>")
				}
			}
		})
	})
	
	function reloadData(data) {
		$(".artcle-detail").remove();
		for (var i=data.length-1;i>-0;i--){
			$("#carousel-example-generic").after("<div class='artcle-detail' id='first-artcle'>" +
				"<h4 class='u-title' href='"+contentName+"/article/"+data[i].a_id+"'>"+data[i].title+"</h4>" +
				"<div class='u-detail' >"+data[i].a_text+"</div><div class='u-img'><img src='"+contentName+"/static/img/"+data[i].cover_photo	+"'  /></div>" +
				"<div class='u-author'><span class='u-like'><label a_id="+data[i].a_id+"  class='glyphicon glyphicon-thumbs-up'></label>" +
				"<label like="+data[i].favour+">"+data[i].favour+"人点赞</label></span>" +
				"<span class='u-browse'><label class='glyphicon glyphicon-eye-open '>" +
				"</label><label>"+data[i].readSum+"</label></span><span class='u-user'>" +
				"<label class='glyphicon glyphicon-user '></label><label>" +
				"<a href='/ower/"+data[i].say_user.user_id+"'>"+data[i].say_user.userName+"</a></label></span>" +
				"<span class='u-date'><label  class='glyphicon glyphicon-time'></label>" +
				"<label>"+data[i].send_time+"</label></span> </div> </div>")
		}
	}



})

function success(str){
	spop({
		template: str, // string required. Without it nothing happens!
		style: 'success', // error or success
		autoclose: 3000, // miliseconds
		position: 'top-right', // top-left top-center bottom-left bottom-center bottom-right
		icon: true, // or false
		group: false // string, add a id reference
	})
}


function error(str){
	spop({
		template: str, // string required. Without it nothing happens!
		style: 'error', // error or success
		autoclose: 3000, // miliseconds
		position: 'top-right', // top-left top-center bottom-left bottom-center bottom-right
		icon: true, // or false
		group: false // string, add a id reference
	})
}