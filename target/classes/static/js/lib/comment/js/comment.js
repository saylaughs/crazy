//<!--textarea限制字数-->
    function keyUP(t){
        var len = $(t).val().length;
        if(len > 139){
            $(t).val($(t).val().substring(0,140));
        }
    }
    var i=0;
//<!--textarea高度自适应-->
    $(function () {
        $('.content').flexText();
        var c_id="";
//<!--点击评论创建评论条-->
    $('.commentAll').on('click','.plBtn',function(){
        var myDate = new Date();
        //获取当前年
        var year=myDate.getFullYear();
        //获取当前月
        var month=myDate.getMonth()+1;
        //获取当前日
        var date=myDate.getDate();
        var h=myDate.getHours();       //获取当前小时数(0-23)
        var m=myDate.getMinutes();     //获取当前分钟数(0-59)
        if(m<10) m = '0' + m;
        var s=myDate.getSeconds();
        if(s<10) s = '0' + s;
        var now=year+'-'+month+"-"+date+" "+h+':'+m+":"+s;
        //获取输入内容
        var oSize = $(this).siblings('.flex-text-wrap').find('.comment-input').val();
        console.log(oSize);
        var user_id=$(".user_id").attr("value");
        var userName=$(".user_id").attr("username");
        if(oSize.replace(/(^\s*)|(\s*$)/g, "") != ''){
            if (user_id!='') {
                $.getJSON("/saylaugh/comment/add", {"a_id": $(".a-title").attr("value"),"user_id": user_id,"c_datetime": new Date(now),"c_content": oSize, "like": 0, "top": 0}, function (data) {
                    c_id=data.message;
                    success("评论成功");
                    var id=$(".A"+(i-1)).attr("c_id");
                    $(".A"+(i-1)).attr("value",c_id);
                    $(".A"+(i-1)).attr("c_id",c_id);
                    $(".A"+(i-1)).parents(".comment-show-con-list").find(".pl-hf").attr("value",data.user_id);
                    $(".A"+(i-1)).parents(".comment-show-con-list").find(".date-dz-z").attr("value",c_id);
                    // alert(".A"+(i-1))
                })
            }
            else {
                error("评论失败,你可能未登录。<a href='/saylaugh/index' style='color: orange;text-decoration: none'>首页登录！</a>");
                return;
            }
            //动态创建评论模块
            var oHtml = '<div class="comment-show-con clearfix "><div class="comment-show-con-img pull-left"><img src="/saylaugh/static/js/lib/comment/images/header.jpg" alt=""></div> <div class="comment-show-con-list pull-left clearfix"><div class="pl-text clearfix"> <a href="/saylaugh/owner/'+user_id+'" class="comment-size-name">'+userName+': </a> <span class="my-pl-con A'+i+'" c_id>&nbsp;'+ oSize +'</span> </div> <div class="date-dz"> <span class="date-dz-left pull-left comment-time">'+now+'</span> <div class="date-dz-right pull-right comment-pl-block"><a href="javascript:;" class="PaPa removeBlock">删除</a> <a href="javascript:;" class="date-dz-pl pl-hf hf-con-block pull-left">回复</a> <span class="pull-left date-dz-line">|</span> <a href="javascript:;" class="date-dz-z pull-left"><i class="date-dz-z-click-red" th:attr="comtype=1" ></i>赞 (<i class="z-num">0</i>)</a> </div> </div><div class="hf-list-con"></div></div> </div>';
            $(this).parents('.reviewArea ').siblings('.comment-show').prepend(oHtml);
            $(this).siblings('.flex-text-wrap').find('.comment-input').prop('value','').siblings('pre').find('span').text('');
            i++;
        }

    });
//<!--点击回复动态创建回复块-->
    $('.comment-show').on('click','.pl-hf',function(){
    	$(".reviewArea").slideUp(700);
        //获取回复人的名字
        var fhName = $(this).parents('.date-dz-right').parents('.date-dz').siblings('.pl-text').find('.comment-size-name').html();
        //回复@
        var fhN = '回复@'+fhName;
        //var oInput = $(this).parents('.date-dz-right').parents('.date-dz').siblings('.hf-con');
        var fhHtml = '<div class="hf-con pull-left"> <textarea class="content comment-input hf-input" placeholder="" onkeyup="keyUP(this)"></textarea> <a href="javascript:;" class="hf-pl">评论</a></div>';
        //显示回复
        if($(this).is('.hf-con-block')){
            $(this).parents('.date-dz-right').parents('.date-dz').append(fhHtml);
            $(this).removeClass('hf-con-block');
            $('.content').flexText();
            $(this).parents('.date-dz-right').siblings('.hf-con').find('.pre').css('padding','6px 15px');
            //console.log($(this).parents('.date-dz-right').siblings('.hf-con').find('.pre'))
            //input框自动聚焦
            $(this).parents('.date-dz-right').siblings('.hf-con').find('.hf-input').val('').focus().val(fhN);
        }else {
            $(this).addClass('hf-con-block');
            $(this).parents('.date-dz-right').siblings('.hf-con').remove();
        }
    });
    var user_id=$(".user_id").attr("value");
//<!--评论回复块创建-->
    $('.comment-show').on('click','.hf-pl',function(){
        // if (user_id==''){
        //     error("未登录.<a href='/saylaugh/index' style='color: orange;text-decoration: none'>首页登录！</a>");
        //     return;
        // }
        var oThis = $(this);
        var myDate = new Date();
        //获取当前年
        var year=myDate.getFullYear();
        //获取当前月
        var month=myDate.getMonth()+1;
        //获取当前日
        var date=myDate.getDate();
        var h=myDate.getHours();       //获取当前小时数(0-23)
        var m=myDate.getMinutes();     //获取当前分钟数(0-59)
        if(m<10) m = '0' + m;
        var s=myDate.getSeconds();
        if(s<10) s = '0' + s;
        var now=year+'-'+month+"-"+date+" "+h+':'+m+":"+s;
        //获取输入内容
        var oHfVal = $(this).siblings('.flex-text-wrap').find('.hf-input').val();
        console.log(oHfVal)
        var oHfName = $(this).parents('.hf-con').parents('.date-dz').siblings('.pl-text').find('.comment-size-name').html();
        var oAllVal = '回复@'+oHfName;
        var userName=$(".user_id").attr("username");
        if(oHfVal.replace(/^ +| +$/g,'') == '' || oHfVal == oAllVal){

        }else {
            $.getJSON("/saylaugh/static/js/lib/comment/json/pl.json",function(data){
                var oAt = '';
                var oHf = '';
                $.each(data,function(n,v){
                    delete v.hfContent;
                    delete v.atName;
                    var arr;
                    var ohfNameArr;
                    if(oHfVal.indexOf("@") == -1){
                        data['atName'] = '';
                        data['hfContent'] = oHfVal;
                    }else {
                        arr = oHfVal.split(':');
                        ohfNameArr = arr[0].split('@');
                        data['hfContent'] = arr[1];
                        data['atName'] = ohfNameArr[1];
                    }
                    if(data.atName == ''){
                        oAt = data.hfContent;
                    }else {
                        oAt = '回复<a href="#" class="atName">@'+data.atName+'</a> : '+data.hfContent;
                    }
                    oHf = data.hfName;
                });
                if (user_id==''){
                    error("未登录.<a href='/saylaugh/index' style='color: orange;text-decoration: none'>首页登录！</a>");
                    return;
                }
               var c_id=oThis.parents('.hf-con').parents(".comment-show-con-list").find(".my-pl-con").attr("value");
                // alert(c_id);
                var r_id=0;
               var reply_user_id=oThis.parents('.hf-con').parents(".comment-show-con-list").find(".pl-hf").attr("value");
                $.getJSON("/saylaugh/reply/addReply",{"c_id":c_id,"user_id":user_id,"reply_user_id":reply_user_id,"r_askTime":new Date(now),"r_askContent":data.hfContent,"like":0,"reply_user_name":data.atName},function (data) {
                    if (data.message==1){
                        r_id=data.r_id;
                        $(".B"+(i)).attr("value",r_id);
                        success("评论成功")
                    }
                    else {
                        error("评论失败");
                    }
                })
                var oHtml = '<div class="all-pl-con"><div class="pl-text hfpl-text clearfix"><a href="#" class="comment-size-name">'+userName+' : </a><span class="my-pl-con">'+oAt+'</span></div><div class="date-dz"> <span class="date-dz-left pull-left comment-time">'+now+'</span> <div class="date-dz-right pull-right comment-pl-block"> <a href="javascript:;" class="ErZi removeBlock">删除</a> <a href="javascript:;" class="date-dz-pl pl-hf hf-con-block pull-left">回复</a> <span class="pull-left date-dz-line">|</span> <a href="javascript:;" class="date-dz-z pull-left B'+i+'" th:attr="comtype=0" ><i class="date-dz-z-click-red"></i>赞 (<i class="z-num">0</i>)</a> </div> </div></div>';
                oThis.parents('.hf-con').parents('.comment-show-con-list').find('.hf-list-con').css('display','block').last().prepend(oHtml) && oThis.parents('.hf-con').siblings('.date-dz-right').find('.pl-hf').addClass('hf-con-block') && oThis.parents('.hf-con').remove();
            });
        }
         $(".reviewArea").slideDown(900);
    });
//<!--删除评论块-->
    $('.commentAll').on('click','.removeBlock',function(){
        if (user_id==''){
            error("未登录.<a href='/saylaugh/index' style='color: orange;text-decoration: none'>首页登录！</a>");
            return;
        }
        var len=$(this).parents(".comment-show-con-list").find(".ErZi").length;
        var c_id=$(this).parents(".comment-show-con-list").find(".my-pl-con").attr("value");
        $.getJSON("/saylaugh/comment/del",{"c_id":c_id,"length":len},function (data) {
            if (data.message==1||data.message==2){
                success("删除成功");
            }
        })
        var oT = $(this).parents('.date-dz-right').parents('.date-dz').parents('.all-pl-con');
        // alert(len);
        if (len>0){
            $(this).parents(".comment-show-con-list").find(".my-pl-con").first().addClass("del").html("该评论已删除。");
            return;
        }
        else{
             $(this).parents(".comment-show-con").remove();
            return;
        }
        $(".reviewArea").slideDown(900);
        if(oT.siblings('.all-pl-con').length >= 1){
            oT.remove();
        }else {
            $(this).parents('.date-dz-right').parents('.date-dz').parents('.all-pl-con').parents('.hf-list-con').css('display','none')
            oT.remove();
        }
        $(this).parents('.date-dz-right').parents('.date-dz').parents('.comment-show-con-list').parents('.comment-show-con').remove();

    })
//<!--点赞-->
    $('.comment-show').on('click','.date-dz-z',function(){
        if (user_id==''){
            error("未登录.<a href='/saylaugh/index' style='color: orange;text-decoration: none'>首页登录！</a>");
            return;
        }
        var r_id=$(this).attr("value");
        var zNum = $(this).find('.z-num').html();
        var type=$(this).attr("comtype");
        var math="";
        if($(this).is('.date-dz-z-click')){
            math="N";
            zNum--;
            $(this).removeClass('date-dz-z-click red');
            $(this).find('.z-num').html(zNum);
            $(this).find('.date-dz-z-click-red').removeClass('red');
        }else {
            math="Y"
            zNum++;
            $(this).addClass('date-dz-z-click');
            $(this).find('.z-num').html(zNum);
            $(this).find('.date-dz-z-click-red').addClass('red');
        }
        $.getJSON("/saylaugh/comment/addLike",{"type":type,"math":math,"id":r_id},function (data) {
            if (data.message==1){
                hashMap.put("message","点赞成功");
            }
            else{
                hashMap.put("message","点赞失败，请刷新页面。");
            }
        })
    })
    });