!function($){
  // 给表单 添加 事件
  $(document).delegate('.ajax-post','click',function(){
    var target;
    var target_form = $(this).attr("target-form");

    if(($(this).attr('type') == 'button') || (target = $(this).attr('href')) || (target = $(this).attr('url'))){
      form = $('.' + target_form);
      if(form.get(0) == undefined){
        return false;
      }else if(form.get(0).nodeName == "FORM"){
        if($(this).hasClass('comfirm')){
          if(!confirm("确定要执行这操作吗")){
            return false;
          }
        }
        if ($(this).attr('url') !== undefined) {
            target = $(this).attr('url');
        } else {
            target = form.get(0).action;
        }
        if(target == ''){
          alert("填写提交路径");
          return false;
        }
        if(!validateForm(form)){
        	return false;
        };
        var query;
        var xhr = new XMLHttpRequest();
        
        if(!form.get(0).getAttribute("enctype")){
            query = form.serialize();
            alert(query);
            var xhr = new XMLHttpRequest();
            xhr.open('POST', target);
            xhr.send(query);
            xhr.onreadystatechange = function(){
            	if(xhr.readyState == 4){
            		if(xhr.status == 200 || xhr.status == 304){
            			var data = eval("("+xhr.responseText+")");
            			alert(data.msg);
            		}
            	}
            };
        }else{ //提交表单中有文件
            if(window.FormData){
              var formData = new FormData();
              //建立一个upload 表单项，值为上传的文件
              formData.append('upload',document.getElementById('upload').files[0]);
              
              xhr.open('POST', target);
              xhr.send(formData);
            }
        }
        return false;

      }else{
    	  alert("结束");
      }
    }

  });
}(jQuery);
//验证表单类型
function validateForm(node){
   var nodeList = node.find('input,select,textarea');
   for(var i = 0 ; i < nodeList.length;i++){
	   
	   if(nodeList.get(i).required){
		   if(nodeList.get(i).value == ''){
			   alert(nodeList.get(i).getAttribute("required"));
			   return false;
		   }
//		   alert(nodeList.get(i).getAttribute("type"));
//		   alert();
	   }
   }
   return true;
}

