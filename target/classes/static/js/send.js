$(document).ready(function () {
    $(".send-ok").click(function () {
        $(".send-ok").attr("status",'1');
        // $("button[title='预览']").click();

        var title=$(".art-title").val();//标题
        var category1=$("span").attr("status","1");
        var date=$("#timeID").text();
        var content=getcontent();
        var img=getcontent().find("img").html();
        alert(img);
        // $.getJSON("/art/add",{},function(result){
        //     $.each(result, function(i, field){
        //         $("div").append(field + " ");
        //     });
        // });


        spop({
          template: "发送成功", // string required. Without it nothing happens!
          style: 'success', // error or success
          autoclose: 3000, // miliseconds
          position: 'top-right', // top-left top-center bottom-left bottom-center bottom-right
          icon: true, // or false
          group: false // string, add a id reference
        })
    })

    $(".send-save").click(function () {
        $(".send-save").attr("status",'1');
        // $("button[title='预览']").click();
        spop({
            template: "保存成功", // string required. Without it nothing happens!
            style: 'success', // error or success
            autoclose: 3000, // miliseconds
            position: 'top-right', // top-left top-center bottom-left bottom-center bottom-right
            icon: true, // or false
            group: false // string, add a id reference
        })
    })

    $(".parent-div span").click(function () {
        var status=$(this).attr("status");
        if (status=="0"){
            $(this).css("background-color","rgba(255,170,224,0.47)");
            $(this).attr("status","1");
        }
        else{
            $(this).css("background-color","#DA70D6");
            $(this).attr("status","0");
        }

    })



    // $(document).on("button[title='关闭']","click",function () {
    //     var status=$(".send-ok").attr("status");
    //     var status2=$(".send-save").attr("status");
    //     alert(status)
    //     if (status=="1"){
    //         alert(22)
    //         spop({
    //             template: "发送成功", // string required. Without it nothing happens!
    //             style: 'success', // error or success
    //             autoclose: 3000, // miliseconds
    //             position: 'top-right', // top-left top-center bottom-left bottom-center bottom-right
    //             icon: true, // or false
    //             group: false // string, add a id reference
    //         })
    //     }
    //
    //     if(status2=="1"){
    //         alert(23211212333)
    //         spop({
    //             template: "保存成功", // string required. Without it nothing happens!
    //             style: 'success', // error or success
    //             autoclose: 3000, // miliseconds
    //             position: 'top-right', // top-left top-center bottom-left bottom-center bottom-right
    //             icon: true, // or false
    //             group: false // string, add a id reference
    //         })
    //     }
    //
    //     $(".send-ok").attr("status",'1');
    //     $(".send-save").attr("status",'1');
    // })
})