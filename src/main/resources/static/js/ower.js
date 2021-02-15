$(document).ready(function () {
	$(".own-content").eq(0).fadeIn("700");
	$(".menu-ul li").click(function () {
		var index=$(this).index();
		$(".own-content").eq(index).fadeIn("700").siblings(".own-content").hide();
	})
	
	$(".am-icon-check").click(function(){
		$(".am-circle").attr("src",$(".up-pre-before img").attr("src"));
		 $("#doc-modal-1").modal({width:'0px'});
		 spop({
				template: "上传成功", // string required. Without it nothing happens!
				style: 'success', // error or success
				autoclose: 3000, // miliseconds
				position: 'top-right', // top-left top-center bottom-left bottom-center bottom-right
				icon: true, // or false
				group: false // string, add a id reference
		}) 
	})
})
