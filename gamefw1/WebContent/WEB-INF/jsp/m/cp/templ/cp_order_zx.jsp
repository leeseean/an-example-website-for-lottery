<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html class="ui-mobile"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><base href=".">
    <title>${cpType.cpTypeName }</title>
    
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=10, user-scalable=yes">
 
    <style>
        .ui-font-gray {
            color: #999;
            font-size: 12px;
        }
    </style>
    <%@include file="../../inc/mobile_common.jsp"%>
    
     <script src="${resourceRoot}/m/js/comm.js"></script>
</head>
<body class="ui-mobile-viewport ui-overlay-c">


    
<div class="ui-loader ui-corner-all ui-body-a ui-loader-default">
	<span class="ui-icon ui-icon-loading"></span><h1>loading</h1>
</div>
<div class="ui-page ui-body-c ui-page-active" tabindex="0" data-role="page" data-url="" data-external-page="true" style="min-height: 626px;">

	     <%@include file="../../inc/mobile_cporder_header.jsp"%>

	<div role="main">	
        <form action="${ctx}/m/mobileCpOrder/goOrderList" id="order_form" method="post">
            <input type="hidden" id="gameTypeCode" name="gameTypeCode" value="${param.gameTypeCode }" />
            <input type="hidden" id="gameTypeName" name="gameTypeName" value="${gameTypeName }" />
            <input type="hidden" id="cpTypeCode" name="cpTypeCode" value="${cpType.cpTypeCode }" />
            <input type="hidden" id="minMoney" name="minMoney" value="${cpType.gminSingle }" />
            <input type="hidden" id="maxMoney" name="maxMoney" value="${cpType.gmaxSingle }" />
            <input type="hidden" id="maxSumMoney" name="maxSumMoney" value="${cpType.singleCredit }" />
            <input type="hidden" id="userMoney" name="userMoney" value="${userMoney }" />
        <div class="ui-bar ui-bar-a ui-shadow">
	    	<div class="">
	            期数：<span style=" font-weight: bold" id="GameNum">${nextResult.nextFormatQs }</span> &nbsp;开奖日期：<span style=" font-weight: bold">${nextResult.nextTime }</span>
	    	</div>           
	    	<div class="">
	            额度：<span style=" font-weight: bold">${userMoney }</span>     	
	         </div>     
   	 	</div>
		
		
		 <div class="ui-grid-d">
            <div class="ui-block-a">
                <div id="checkboxes2">
                    <fieldset data-role="controlgroup" data-type="horizontal" class="ui-corner-all ui-controlgroup ui-controlgroup-horizontal" aria-disabled="false" data-disabled="false" data-shadow="false" data-corners="true" data-exclude-invisible="true" data-mini="false" data-init-selector=":jqmData(role=&#39;controlgroup&#39;)"><div class="ui-controlgroup-controls">
                        <div class="ui-checkbox"><input id="checkbox2" onchange="GS_Chk(&#39;前三组选三&#39;, this)" value="0" data-theme="c" type="checkbox"><label for="checkbox2" data-corners="true" data-shadow="false" data-iconshadow="true" data-wrapperels="span" data-icon="checkbox-off" data-theme="c" data-mini="false" class="ui-checkbox-off ui-btn ui-btn-up-c ui-btn-corner-all ui-fullsize ui-btn-icon-left ui-first-child ui-last-child"><span class="ui-btn-inner"><span class="ui-btn-text">
                            0
                        </span><span class="ui-icon ui-icon-checkbox-off ui-icon-shadow">&nbsp;</span></span></label></div>
                        
                    </div></fieldset>
                </div>
            </div>
            <div class="ui-block-b">
                <div id="checkboxes2">
                    <fieldset data-role="controlgroup" data-type="horizontal" class="ui-corner-all ui-controlgroup ui-controlgroup-horizontal" aria-disabled="false" data-disabled="false" data-shadow="false" data-corners="true" data-exclude-invisible="true" data-mini="false" data-init-selector=":jqmData(role=&#39;controlgroup&#39;)"><div class="ui-controlgroup-controls">
                        <div class="ui-checkbox"><input id="checkbox2" onchange="GS_Chk(&#39;前三组选三&#39;, this)" value="1" data-theme="c" type="checkbox"><label for="checkbox2" data-corners="true" data-shadow="false" data-iconshadow="true" data-wrapperels="span" data-icon="checkbox-off" data-theme="c" data-mini="false" class="ui-checkbox-off ui-btn ui-btn-up-c ui-btn-corner-all ui-fullsize ui-btn-icon-left ui-first-child ui-last-child"><span class="ui-btn-inner"><span class="ui-btn-text">
                            1
                        </span><span class="ui-icon ui-icon-checkbox-off ui-icon-shadow">&nbsp;</span></span></label></div>
                        
                    </div></fieldset>
                </div>
            </div>
            <div class="ui-block-c">
                <div id="checkboxes2">
                    <fieldset data-role="controlgroup" data-type="horizontal" class="ui-corner-all ui-controlgroup ui-controlgroup-horizontal" aria-disabled="false" data-disabled="false" data-shadow="false" data-corners="true" data-exclude-invisible="true" data-mini="false" data-init-selector=":jqmData(role=&#39;controlgroup&#39;)"><div class="ui-controlgroup-controls">
                        <div class="ui-checkbox"><input id="checkbox2" onchange="GS_Chk(&#39;前三组选三&#39;, this)" value="2" data-theme="c" type="checkbox"><label for="checkbox2" data-corners="true" data-shadow="false" data-iconshadow="true" data-wrapperels="span" data-icon="checkbox-off" data-theme="c" data-mini="false" class="ui-first-child ui-last-child ui-checkbox-off ui-btn ui-btn-up-c ui-btn-corner-all ui-fullsize ui-btn-icon-left"><span class="ui-btn-inner"><span class="ui-btn-text">
                            2
                        </span><span class="ui-icon ui-icon-checkbox-off ui-icon-shadow">&nbsp;</span></span></label></div>
                        
                    </div></fieldset>
                </div>
            </div>
            <div class="ui-block-d">
                <div id="checkboxes2">
                    <fieldset data-role="controlgroup" data-type="horizontal" class="ui-corner-all ui-controlgroup ui-controlgroup-horizontal" aria-disabled="false" data-disabled="false" data-shadow="false" data-corners="true" data-exclude-invisible="true" data-mini="false" data-init-selector=":jqmData(role=&#39;controlgroup&#39;)"><div class="ui-controlgroup-controls">
                        <div class="ui-checkbox"><input id="checkbox2" onchange="GS_Chk(&#39;前三组选三&#39;, this)" value="3" data-theme="c" type="checkbox"><label for="checkbox2" data-corners="true" data-shadow="false" data-iconshadow="true" data-wrapperels="span" data-icon="checkbox-off" data-theme="c" data-mini="false" class="ui-first-child ui-last-child ui-checkbox-off ui-btn ui-btn-up-c ui-btn-corner-all ui-fullsize ui-btn-icon-left"><span class="ui-btn-inner"><span class="ui-btn-text">
                            3
                        </span><span class="ui-icon ui-icon-checkbox-off ui-icon-shadow">&nbsp;</span></span></label></div>
                        
                    </div></fieldset>
                </div>
            </div>
            <div class="ui-block-e">
                <div id="checkboxes2">
                    <fieldset data-role="controlgroup" data-type="horizontal" class="ui-corner-all ui-controlgroup ui-controlgroup-horizontal" aria-disabled="false" data-disabled="false" data-shadow="false" data-corners="true" data-exclude-invisible="true" data-mini="false" data-init-selector=":jqmData(role=&#39;controlgroup&#39;)"><div class="ui-controlgroup-controls">
                        <div class="ui-checkbox"><input id="checkbox2" onchange="GS_Chk(&#39;前三组选三&#39;, this)" value="4" data-theme="c" type="checkbox"><label for="checkbox2" data-corners="true" data-shadow="false" data-iconshadow="true" data-wrapperels="span" data-icon="checkbox-off" data-theme="c" data-mini="false" class="ui-first-child ui-last-child ui-checkbox-off ui-btn ui-btn-up-c ui-btn-corner-all ui-fullsize ui-btn-icon-left"><span class="ui-btn-inner"><span class="ui-btn-text">
                            4
                        </span><span class="ui-icon ui-icon-checkbox-off ui-icon-shadow">&nbsp;</span></span></label></div>
                        
                    </div></fieldset>
                </div>
            </div>
            <div class="ui-block-a">
                <div id="checkboxes2">
                    <fieldset data-role="controlgroup" data-type="horizontal" class="ui-corner-all ui-controlgroup ui-controlgroup-horizontal" aria-disabled="false" data-disabled="false" data-shadow="false" data-corners="true" data-exclude-invisible="true" data-mini="false" data-init-selector=":jqmData(role=&#39;controlgroup&#39;)"><div class="ui-controlgroup-controls">
                        <div class="ui-checkbox"><input id="checkbox2" onchange="GS_Chk(&#39;前三组选三&#39;, this)" value="5" data-theme="c" type="checkbox"><label for="checkbox2" data-corners="true" data-shadow="false" data-iconshadow="true" data-wrapperels="span" data-icon="checkbox-off" data-theme="c" data-mini="false" class="ui-checkbox-off ui-btn ui-btn-corner-all ui-fullsize ui-btn-icon-left ui-first-child ui-last-child ui-btn-hover-c"><span class="ui-btn-inner"><span class="ui-btn-text">
                            5
                        </span><span class="ui-icon ui-icon-checkbox-off ui-icon-shadow">&nbsp;</span></span></label></div>
                        
                    </div></fieldset>
                </div>
            </div>
            <div class="ui-block-b">
                <div id="checkboxes2">
                    <fieldset data-role="controlgroup" data-type="horizontal" class="ui-corner-all ui-controlgroup ui-controlgroup-horizontal" aria-disabled="false" data-disabled="false" data-shadow="false" data-corners="true" data-exclude-invisible="true" data-mini="false" data-init-selector=":jqmData(role=&#39;controlgroup&#39;)"><div class="ui-controlgroup-controls">
                        <div class="ui-checkbox"><input id="checkbox2" onchange="GS_Chk(&#39;前三组选三&#39;, this)" value="6" data-theme="c" type="checkbox"><label for="checkbox2" data-corners="true" data-shadow="false" data-iconshadow="true" data-wrapperels="span" data-icon="checkbox-off" data-theme="c" data-mini="false" class="ui-checkbox-off ui-btn ui-btn-up-c ui-btn-corner-all ui-fullsize ui-btn-icon-left ui-first-child ui-last-child"><span class="ui-btn-inner"><span class="ui-btn-text">
                           6
                        </span><span class="ui-icon ui-icon-checkbox-off ui-icon-shadow">&nbsp;</span></span></label></div>
                        
                    </div></fieldset>
                </div>
            </div>
            <div class="ui-block-c">
                <div id="checkboxes2">
                    <fieldset data-role="controlgroup" data-type="horizontal" class="ui-corner-all ui-controlgroup ui-controlgroup-horizontal" aria-disabled="false" data-disabled="false" data-shadow="false" data-corners="true" data-exclude-invisible="true" data-mini="false" data-init-selector=":jqmData(role=&#39;controlgroup&#39;)"><div class="ui-controlgroup-controls">
                        <div class="ui-checkbox"><input id="checkbox2" onchange="GS_Chk(&#39;前三组选三&#39;, this)" value="7" data-theme="c" type="checkbox"><label for="checkbox2" data-corners="true" data-shadow="false" data-iconshadow="true" data-wrapperels="span" data-icon="checkbox-off" data-theme="c" data-mini="false" class="ui-first-child ui-last-child ui-checkbox-off ui-btn ui-btn-up-c ui-btn-corner-all ui-fullsize ui-btn-icon-left"><span class="ui-btn-inner"><span class="ui-btn-text">
                            7
                        </span><span class="ui-icon ui-icon-checkbox-off ui-icon-shadow">&nbsp;</span></span></label></div>
                        
                    </div></fieldset>
                </div>
            </div>
            <div class="ui-block-d">
                <div id="checkboxes2">
                    <fieldset data-role="controlgroup" data-type="horizontal" class="ui-corner-all ui-controlgroup ui-controlgroup-horizontal" aria-disabled="false" data-disabled="false" data-shadow="false" data-corners="true" data-exclude-invisible="true" data-mini="false" data-init-selector=":jqmData(role=&#39;controlgroup&#39;)"><div class="ui-controlgroup-controls">
                        <div class="ui-checkbox"><input id="checkbox2" onchange="GS_Chk(&#39;前三组选三&#39;, this)" value="8" data-theme="c" type="checkbox"><label for="checkbox2" data-corners="true" data-shadow="false" data-iconshadow="true" data-wrapperels="span" data-icon="checkbox-off" data-theme="c" data-mini="false" class="ui-first-child ui-last-child ui-checkbox-off ui-btn ui-btn-up-c ui-btn-corner-all ui-fullsize ui-btn-icon-left"><span class="ui-btn-inner"><span class="ui-btn-text">
                            8
                        </span><span class="ui-icon ui-icon-checkbox-off ui-icon-shadow">&nbsp;</span></span></label></div>
                        
                    </div></fieldset>
                </div>
            </div>
            <div class="ui-block-e">
                <div id="checkboxes2">
                    <fieldset data-role="controlgroup" data-type="horizontal" class="ui-corner-all ui-controlgroup ui-controlgroup-horizontal" aria-disabled="false" data-disabled="false" data-shadow="false" data-corners="true" data-exclude-invisible="true" data-mini="false" data-init-selector=":jqmData(role=&#39;controlgroup&#39;)"><div class="ui-controlgroup-controls">
                        <div class="ui-checkbox"><input id="checkbox2" onchange="GS_Chk(&#39;前三组选三&#39;, this)" value="9" data-theme="c" type="checkbox"><label for="checkbox2" data-corners="true" data-shadow="false" data-iconshadow="true" data-wrapperels="span" data-icon="checkbox-off" data-theme="c" data-mini="false" class="ui-first-child ui-last-child ui-checkbox-off ui-btn ui-btn-up-c ui-btn-corner-all ui-fullsize ui-btn-icon-left"><span class="ui-btn-inner"><span class="ui-btn-text">
                            9
                        </span><span class="ui-icon ui-icon-checkbox-off ui-icon-shadow">&nbsp;</span></span></label></div>
                        
                    </div></fieldset>
                </div>
            </div>
        </div>
		
		 <div>
            <label for="textinput2">下注赔率:</label>
            <span id="zu3zu6Rate" style="color: red"></span>
        </div>
        
         <div>
            <label for="textinput2">下注额度:</label>
            <div class="ui-input-text ui-shadow-inset ui-corner-all ui-btn-shadow ui-body-c ui-mini"><input id="money" placeholder="" value="" data-mini="true" onkeypress="return CheckKey(event);" type="text" class="ui-input-text ui-body-c"></div>
        </div>
                 
        <div data-corners="true" data-shadow="true" data-iconshadow="true" data-wrapperels="span" data-theme="b" data-disabled="false" class="ui-btn ui-shadow ui-btn-corner-all ui-btn-up-b" aria-disabled="false">
        	<span class="ui-btn-inner"><span class="ui-btn-text">下注</span></span>
        	<input type="button" data-theme="b" value="下注" class="ui-btn-hidden" id="ok" onclick="zu3zu6OK();" aria-disabled="false" data-disabled="false" disabled="disabled" />
        </div>
        <input type="hidden" id="zu3zu6Str" value="" tp3="前三组选三" rate="15.30">
        <div data-corners="true" data-shadow="true" data-iconshadow="true" data-wrapperels="span" data-theme="b" data-disabled="false" class="ui-btn ui-shadow ui-btn-corner-all ui-btn-up-b" aria-disabled="false">
        	<span class="ui-btn-inner"><span class="ui-btn-text">取消</span></span>
        	<input type="button" data-theme="b" value="取消" class="ui-btn-hidden" onclick="clearChk()" aria-disabled="false" data-disabled="false" />
        </div>
         
            
        </form>
	</div>

        <!-- /底部 -->
        <jsp:include page="../../inc/mobile_foot.jsp" />
        
 </div>
    
</body>

</html>