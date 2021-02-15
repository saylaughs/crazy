tinymce.init({
	    selector: '#tinymce_demo', //容器，可使用css选择器
	    language:'zh_CN', //调用放在langs文件夹内的语言包
	    toolbar: true, //工具栏
	    menubar: true, //菜单栏
	    branding:false, //右下角技术支持
	    inline: false, //开启内联模式
	    elementpath: false,
	    min_height:560, //最小高度
	    height: 800,  //高度
	    skin: 'oxide',
	    toolbar_sticky:true,
	    visualchars_default_state:true, //显示不可见字符
	    image_caption: true,
	    paste_data_images: true,
	    relative_urls : false,
	   // remove_script_host : false,
	    removed_menuitems: 'newdocument',  //清除“文件”菜单
	    plugins: "lists,hr, advlist,anchor,autolink,autoresize,charmap,code,codesample,emoticons,fullscreen,image,media,insertdatetime,link,pagebreak,paste,preview,print,searchreplace,table,textcolor,toc,visualchars,wordcount", //依赖lists插件
	    toolbar: 'bullist numlist anchor charmap emoticons fullscreen hr image insertdatetime link media pagebreak paste preview print searchreplace textcolor wordcount',
	    //选中时出现的快捷工具，与插件有依赖关系 
	    images_upload_url:'/saylaughs/art/img', /*后图片上传接口*/ /*返回值为json类型 {'location':'uploads/jpg'}*/
 	    setup: function(editor){ 
	   		 editor.on('change',function(){ editor.save(); });
		}

	});

function setcontent(){
		tinyMCE.activeEditor.setContent("<h1>设置内容1</h1>");
		//tinyMCE.editors[0].setContent("<h1>设置内容2</h1>");
	}
	function getcontent(){
		alert(tinyMCE.activeEditor.getContent());
	}
   /*3、获取不带HTML标记的纯文本内容：
	var activeEditor = tinymce.activeEditor;
	var editBody = activeEditor.getBody();
	activeEditor.selection.select(editBody);
	var text = activeEditor.selection.getContent( {'format' : 'text' } );*/
	function getbody(){
		var activeEditor = tinymce.activeEditor;
		var editBody = activeEditor.getBody();
		activeEditor.selection.select(editBody);
		var text = activeEditor.selection.getContent( {'format' : 'text' } );
		alert(text);
		console.log(text);
	}
	
 function times()
        {
            var time=new Date();
            var year=time.getFullYear();
            var month=time.getMonth();
            var day=time.getDate();
            var hour=time.getHours();
            var minute=time.getMinutes();
            var seconds=time.getSeconds();
            if(seconds<10)
            {
                seconds="0"+seconds;
            }
            if(minute<10)
            {
                minute="0"+minute;
            }
            if(hour<10)
            {
                hour="0"+hour;
            }
            document.getElementById("timeID").innerHTML="发表时间："+year+"年"+month+"月"+day+"日"+"&nbsp;" +
                    "&nbsp;"+hour+":"+minute+":"+seconds;
        }
        setInterval("times()",1000);