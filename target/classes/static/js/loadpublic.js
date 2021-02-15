$(document).ready(function() {
	var stars = 1600; /*星星的密集程度，数字越大越多*/
	var $stars = $(".stars");
	var r = 900; /*星星的看起来的距离,值越大越远,可自行调制到自己满意的样子*/
	for(var i = 0; i < stars; i++) {
		var $star = $("<div/>").addClass("star");
		$stars.append($star);
	}
	$(".star").each(function() {
		var cur = $(this);
		var s = 0.2 + (Math.random() * 1);
		var curR = r + (Math.random() * 300);
		cur.css({
			transformOrigin: "0 0 " + curR + "px",
			transform: " translate3d(0,0,-" + curR + "px) rotateY(" + (Math.random() * 360) + "deg) rotateX(" + (Math.random() * -50) + "deg) scale(" + s + "," + s + ")"

		})
	})

	$('.text2').textillate({
		initialDelay: 1000, //设置动画开始时间
		in: {
			effect: 'flipInX' //设置动画名称
		}
	});

	$("*").mouseover(function() {
		var cur = $(this).css("cursor");
		if(cur == 'auto') {
			//			$(this).css("cursor", "url(img/normal.cur),pointer");
			$(this).addClass("cur-auto");
			$(this).removeClass("cur-pointer");
		}
		if(cur == 'pointer') {
			//$(this).css("cursor", "url(img/link.cur),pointer");
			$(this).removeClass("cur-auto");
			$(this).addClass("cur-pointer");
		}
		// else{
		// 	$(this).addClass("cur-auto");
		// 	$(this).removeClass("cur-pointer");
		// }
	})


	WIDGET = {
		CONFIG: {
			"layout": 1,
			"width": 450,
			"height": 150,
			"background": 4,
			"dataColor": "FFFFFF",
			"backgroundColor": "#FFC0CB",
			"language": "zh",
			"aqiColor": "000000",
			"borderRadius": 5,
			"key": "VnAjuPyzrg"
		}
	}

	$(".menu-owner").mouseenter(function(event) {
		// var evt = event.srcElement ? event.srcElement : event.target;
		// if(evt.id == 'weather'){
		$("#weather-v2-plugin-standard").stop().fadeIn(500);
		// }
	}).mouseleave(function (event) {
		// var evt = event.srcElement ? event.srcElement : event.target;
		// if(evt.id == 'weather') {
		$("#weather-v2-plugin-standard").stop().fadeOut(500);
		// }
	})
	
	for (i in document.images) document.images[i].ondragstart = imgdragstart;
 
    function imgdragstart() {
        return false;
    };
    
    document.ondragstart = function() {
    	return false;
	};


	var d="<div class='snow'>❅<div>"

	setInterval(function(){

		var f=$(document).width();

		var e=Math.random()*f-100;

		var o=0.3+Math.random();

		var fon=10+Math.random()*30;

		var l = e - 100 + 200 * Math.random();

		var k=2000 + 5000 * Math.random();

		$(d).clone().appendTo(".body-star").css({

			left:e+"px",

			opacity:o,

			"font-size":fon,

		}).animate({

			top:"400px",

			left:l+"px",

			opacity:0.1,

		},k,"linear",function(){$(this).remove()})

	},200)
 
    
})