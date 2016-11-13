(function($){
	$.extend({   

		clientHeight: function(){
			var winHeight;
			if (window.innerHeight) winHeight = window.innerHeight; 
			else if ((document.body) && (document.body.clientHeight)) 
				winHeight = document.body.clientHeight; 

			//计算window高度
			if (document.documentElement 
				&& document.documentElement.clientHeight){winHeight = document.documentElement.clientHeight;}

			return winHeight;
		},
		
		/** window.open 参数对象{url:'',width:'',height:'',vArguments:{}}*/
		showModalDialog: function(param){
			var sUrl = param.url;
			var sWidth = param.width;
			var sHeight = param.height;
			var vArguments = param.vArguments;
			
			if(typeof(vArguments) == "undefined" || vArguments == null){
				vArguments = window.self;
			}

			if(typeof(sWidth) == "undefined"){
				sWidth = screen.availWidth*0.65;
			}else{
				sWidth = parseInt(sWidth);
			}

			if(typeof(sHeight) == "undefined"){
				sHeight = screen.availHeight*0.85;
			}else{
				sHeight = parseInt(sHeight);
			}
			
			sFeatures = "dialogHeight:"+sHeight+"px;dialogWidth:"+ sWidth +"px;status:yes;scroll:yes;resizable:no;center:yes";
			return window.showModalDialog(sUrl, vArguments, sFeatures);
		},

		/**设定固定大小*/
		showModalDialogFix_min: function(sUrl, vArguments){
			var sHeight = screen.availHeight * 0.55;
			var sWidth = screen.availWidth * 0.85;
			
			return $.showModalDialog({url:sUrl, width:sWidth, height:sHeight, vArguments:vArguments});
		},

		/**设定固定大小*/
		showModalDialogFix_mid: function(sUrl, vArguments){
			var sHeight = screen.availHeight * 0.65;
			var sWidth = screen.availWidth * 0.85;

			return $.showModalDialog({url:sUrl, width:sWidth, height:sHeight, vArguments:vArguments});
		},


		/**设定固定大小*/
		showModalDialogFix_max: function(sUrl, vArguments){
			var sHeight = screen.availHeight * 0.75;
			var sWidth = screen.availWidth * 0.85;

			return $.showModalDialog({url:sUrl, width:sWidth, height:sHeight, vArguments:vArguments});
		},
		
		/** window.open 参数对象{url:'',width:'',height:''}*/
		fnShowWindow: function(param){
			param.height = parseInt(param.height);
			param.width = parseInt(param.width);
			param.url = $.trim(param.url);
			var sTop = (window.screen.availHeight - param.height)/2;
			var sLeft = (window.screen.availWidth - param.width)/2;
			var sFeatures = "height="+param.height+", width="+param.width+", top="+sTop+", left="+sLeft+", scrollbars=yes, toolbar=no, menubar=no, resizable=yes, location=no, status=yes"
			var oNewWindow = window.open(param.url, "_blank", sFeatures);
			if(parseInt(navigator.appVersion) >= 4){oNewWindow.window.focus();}
			return oNewWindow;
		},

		/**window.open 打开小窗口*/
		fnShowWindow_min: function(sUrl, sFeatures){
			var sHeight = screen.availHeight * 0.55;
			var sWidth = screen.availWidth * 0.85;
			return $.fnShowWindow({url:sUrl, width:sWidth, height:sHeight});
		},

		/**window.open 打开中型窗口*/
		fnShowWindow_mid: function(sUrl, sFeatures){
			var sHeight = screen.availHeight * 0.65;
			var sWidth = screen.availWidth * 0.85;
			return $.fnShowWindow({url:sUrl, width:sWidth, height:sHeight});
			
		},

		/**window.open 打开大窗口*/
		fnShowWindow_max: function(sUrl, sFeatures){
			var sHeight = screen.availHeight * 0.75;
			var sWidth = screen.availWidth * 0.85;
			return $.fnShowWindow({url:sUrl, width:sWidth, height:sHeight});
		},
		
		/**判断是否是非法字符**/
		resolvChar: function(str){
			/*if (str.length == 1){
				if( str == '&' || str == '<'
				  || str == '>' || c == '\''|| c == '\"'
				  || str == '%'
				  || str == '|' 
				  || str == '%' 
				  || str == '＆' || str == '＜'
				  || str == '＞' ){
					return true;
				}
		   	}else {
			  	for (i = 0; i < str.length; i++){
					var c = str.charAt(i);
					if( c == '&' || c == '<'
					  || c == '>' || c == '\''|| c == '\"'
					  || c == '%'
					  || str == '|' 
					  || c == '%' 
					  || c == '＆' || c == '＜'
					  || c == '＞' ){
						return true;
					}
				}
			}*/

			//判断是否包含html标签
			var htmlReg = /<.*?>/g;
			if(htmlReg.test(str)){ return true; }

			return false;
		},
		//列表查询提交
		form_submit: function(submit_form, param){
			if(param.action){ submit_form.action = param.action; }
			if(param.method){ submit_form.method = param.method; }
			if(param.pageNo){ $("input[name=pageNo]", $(submit_form)).val(param.pageNo); }
			if(param.pageSize){ $("input[name=pageSize]", $(submit_form)).val(param.pageSize); }


			submit_form.submit();
		}
		
	});
})(jQuery);