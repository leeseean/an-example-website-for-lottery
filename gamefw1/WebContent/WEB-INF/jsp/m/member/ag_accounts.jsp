<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>额度转换</title>
  <%@include file="../inc/mobile_common.jsp"%>
</head>

<body>
  <div data-role="page" id="ag-accounts">
    <div data-role="header" data-position="fixed">
      <div class="ui-btn-left" data-role="controlgroup" data-type="horizontal">
        <a href="#quickMenu" data-role="button" data-icon="bars" data-iconpos="notext"></a>
        <a href="${ctx}/m/wap/member"  data-role="button" data-ajax="false" data-icon="back" data-iconpos="notext"></a>
      </div>
      <h1>额度转换</h1>
    </div>
    <!-- /header -->
    <div data-role="content" class="ybm-deposit">
      <div class="deposit-accounts accounts-list">
        <ul data-role="listview" data-inset="true" data-corners="false" class="item">
          <li data-role="list-divider">余额总览</li>
          <li>
            <div class="ui-grid-a">
              <div class="ui-block-a">账户余额</div>
              <div class="ui-block-b">${user.userMoney }</div>
            </div>
          </li>
          <li>
            <div class="ui-grid-a">
              <div class="ui-block-a">AG余额</div>
              <div id="ag_money">
              	<a href="javascript:void(0);" onclick="loadMoney('ag');">查询</a>
              </div>
            </div>
          </li>
          <li>
            <div class="ui-grid-a">
              <div class="ui-block-a">MG余额</div>
              <div id="mg_money">
              	<a href="javascript:void(0);" onclick="loadMoney('mg');">查询</a>
              </div>
            </div>
          </li>
          <li>
            <div class="ui-grid-a">
              <div class="ui-block-a">PT余额</div>
              <div id="pt_money">
              	<a href="javascript:void(0);" onclick="loadMoney('pt');">查询</a>
              </div>
            </div>
          </li>
          <li>
            <div class="ui-grid-a">
              <div class="ui-block-a">OS余额</div>
              <div id="os_money">
              	<a href="javascript:void(0);" onclick="loadMoney('os');">查询</a>
              </div>
            </div>
          </li>
          <li>
            <div class="ui-grid-a">
              <div class="ui-block-a">沙巴余额</div>
              <div id="sb_money">
              	<a href="javascript:void(0);" onclick="loadMoney('sb');">查询</a>
              </div>
            </div>
          </li>
          <li>
            <div class="ui-grid-a">
              <div class="ui-block-a">BBIN</div>
              <div id="bbin_money">
              	<a href="javascript:void(0);" onclick="loadMoney('bbin');">查询</a>
              </div>
            </div>
          </li>
          <li>
            <div class="ui-grid-a">
              <div class="ui-block-a">TTG</div>
              <div id="ttg_money">
              	<a href="javascript:void(0);" onclick="loadMoney('ttg');">查询</a>
              </div>
            </div>
          </li>
          <li>
            <div class="ui-grid-a">
              <div class="ui-block-a">HG</div>
              <div id="hg_money">
              	<a href="javascript:void(0);" onclick="loadMoney('hg');">查询</a>
              </div>
            </div>
          </li>
        </ul>
      </div>
    </div>
    <!-- /content -->
    <div data-role="footer" data-position="fixed" data-id="deposit-footer">
      <div data-role="navbar">
        <ul>
          <li><a href="#ag-accounts" data-transition="none" class="ui-btn-active ui-state-persist">账户余额</a></li>
          <li><a href="#ag-in-form" data-transition="none">转入</a></li>
          <li><a href="#ag-out-form" data-transition="none">转出</a></li>
        </ul>
      </div>
    </div>
    <!-- /footer -->
    <!-- 左側快捷菜單 -->
    <div data-role="panel" data-display="overlay" id="quickMenu" class="ybm-panel">
    	<%@ include file="../inc/left_memu.jsp" %>
    </div>
    <!-- /左側快捷菜單 -->
  </div>
  <!-- /page -->
	
	
	  <div data-role="page" id="ag-in-form">
    <div data-role="header" data-position="fixed">
      <div class="ui-btn-left" data-role="controlgroup" data-type="horizontal">
        <a href="#quickMenu2" data-transition="none" data-role="button" data-icon="bars" data-iconpos="notext"></a>
        <a href="${ctx}/m/wap/member" data-role="button" data-ajax="false" data-icon="back" data-iconpos="notext"></a>
      </div>
      <h1>额度转换</h1>
    </div>
    <!-- /header -->
    <div data-role="content" class="ybm-deposit">
      <div class="ybm-form deposit-form ui-mini">
        <ul data-role="listview">
          <li data-role="divider" data-theme="e">账户余额：${user.userMoney }</li>
          
          <li>
            <div data-role="fieldcontain">
              <label for="f-d-10">请选择充值的游戏平台</label>
              <select id="f-d-10" name="f-d-10">
              	<option value="ag">AG</option>
              	<option value="mg">MG</option>
              	<option value="pt">PT</option>
              	<option value="os">OS</option>
              	<option value="sb">沙巴</option>
              	<option value="bbin">BBIN</option>
              	<option value="ttg">TTG</option>
              	<option value="hg">HG</option>
              </select>
            </div>
          </li>
          
          <li>
            <div data-role="fieldcontain">
              <label for="f-d-11">充值金额</label>
              <input type="number" onkeyup="digitOnly(this);" name="f-d-11" id="f-d-11" pattern="[0-9]*" placeholder="输入充值金额" value="10">
            </div>
          </li>
          <!-- /item -->
          <li class="tac">
            <fieldset data-role="controlgroup" data-type="horizontal" data-corners="false">
              <a href="javascript:void(0);" onclick="incheckparam();" data-transition="pop" data-rel="dialog" data-role="button" data-corners="false"
					data-inline="true" data-icon="check" data-theme="b">
					确认
				</a>
            </fieldset>
          </li>
          <!-- /item -->
        </ul>
      </div>
    </div>
    <!-- /content -->
    <div data-role="footer" data-position="fixed" data-id="deposit-footer">
      <div data-role="navbar">
        <ul>
          <li><a href="#ag-accounts" data-transition="none">账户余额</a></li>
          <li><a href="#ag-in-form" data-transition="none" class="ui-btn-active ui-state-persist">转入</a></li>
          <li><a href="#ag-out-form" data-transition="none">转出</a></li>
        </ul>
      </div>
    </div>
    <!-- /footer -->
    <!-- 左側快捷菜單 -->
    <div data-role="panel" data-display="overlay" id="quickMenu2" class="ybm-panel">
      <%@ include file="../inc/left_memu.jsp" %>
    </div>
    <!-- /左側快捷菜單 -->
  </div>
  
  
  
  <div data-role="page" id="ag-out-form">
    <div data-role="header" data-position="fixed">
      <div class="ui-btn-left" data-role="controlgroup" data-type="horizontal">
        <a href="#quickMenu3" data-transition="none" data-role="button" data-icon="bars" data-iconpos="notext"></a>
        <a href="${ctx}/m/wap/member" data-role="button" data-ajax="false" data-icon="back" data-iconpos="notext"></a>
      </div>
      <h1>额度转换</h1>
    </div>
    <!-- /header -->
    <div data-role="content" class="ybm-deposit">
      <div class="ybm-form deposit-form ui-mini">
        <ul data-role="listview">
          <li data-role="divider" data-theme="e">账户余额：${user.userMoney }</li>
          <li>
            <div data-role="fieldcontain">
              <label for="f-d-13">请选择转出的游戏平台</label>
              <select id="f-d-13" name="f-d-13">
              	<option value="ag">AG</option>
              	<option value="mg">MG</option>
              	<option value="pt">PT</option>
              	<option value="os">OS</option>
              	<option value="sb">沙巴</option>
              	<option value="bbin">BBIN</option>
              	<option value="ttg">TTG</option>
              	<option value="hg">HG</option>
              </select>
            </div>
          </li>
          <li>
            <div data-role="fieldcontain">
              <label for="f-d-12">转出金额</label>
              <input type="number" onkeyup="digitOnly(this);" name="f-d-12" id="f-d-12" pattern="[0-9]*" placeholder="输入转出金额" value="10">
            </div>
          </li>
          <!-- /item -->
          <li class="tac">
            <fieldset data-role="controlgroup" data-type="horizontal" data-corners="false">
              <a href="javascript:void(0);" onclick="outcheckparam();" data-transition="pop" data-rel="dialog" data-role="button" data-corners="false"
					data-inline="true" data-icon="check" data-theme="b">
					确认
				</a>
            </fieldset>
          </li>
          <!-- /item -->
        </ul>
      </div>
    </div>
    <!-- /content -->
    <div data-role="footer" data-position="fixed" data-id="deposit-footer">
      <div data-role="navbar">
        <ul>
          <li><a href="#ag-accounts" data-transition="none">账户余额</a></li>
          <li><a href="#ag-in-form" data-transition="none">转入</a></li>
          <li><a href="#ag-out-form" data-transition="none" class="ui-btn-active ui-state-persist">转出</a></li>
        </ul>
      </div>
    </div>
    <!-- /footer -->
    <!-- 左側快捷菜單 -->
    <div data-role="panel" data-display="overlay" id="quickMenu3" class="ybm-panel">
      <%@ include file="../inc/left_memu.jsp" %>
    </div>
    <!-- /左側快捷菜單 -->
  </div>
	  
<%--确认弹框 --%>
<div data-role="dialog" data-close-btn="none" id="indialog">
  <div data-role="header">
    <h1>额度转入</h1>
  </div>
  <div data-role="content" class="ybm-order-confirm">
    <ul data-role="listview">
      <li data-role="list-divider" data-theme="e">
       	确定转入吗?
      </li>
      <li style=" text-align: center;" id="in_sub">
          <fieldset data-role="controlgroup" data-type="horizontal" data-corners="false">
            <a href="javascript:void(0);" id="insubmit" data-rel="dialog" data-transition="pop" data-role="button" data-icon="check" data-theme="b">确认</a>
            <a href="" data-role="button" data-rel="back" data-icon="back">取消</a>
          </fieldset>
      </li>
      <li style="display: none;" id="in_msg"><font style="color: red;">请求处理中...请勿关闭页面</font></li>
    </ul>
  </div>
</div>

<div data-role="dialog" data-close-btn="none" id="outdialog">
  <div data-role="header">
    <h1>额度转出</h1>
  </div>
  <div data-role="content" class="ybm-order-confirm">
    <ul data-role="listview">
      <li data-role="list-divider" data-theme="e">
       	确定转出吗?
      </li>
      <li style=" text-align: center;" id="out_sub">
          <fieldset data-role="controlgroup" data-type="horizontal" data-corners="false">
            <a href="javascript:void(0);" id="outsubmit" data-rel="dialog" data-transition="pop" data-role="button" data-icon="check" data-theme="b">确认</a>
            <a href="" data-role="button" data-rel="back" data-icon="back">取消</a>
          </fieldset>
      </li>
      <li style="display: none;" id="out_msg"><font style="color: red;">请求处理中...请勿关闭页面</font></li>
    </ul>
  </div>
</div>

<div data-role="dialog" data-close-btn="none" id="resp_success">
  <div data-role="header">
    <h1>操作结果</h1>
  </div>
  <div data-role="content" class="ybm-order-confirm">
    <ul data-role="listview">
      <li data-role="list-divider" data-theme="e" id="respMsg">
      </li>
      <li style=" text-align: center;">
          <fieldset data-role="controlgroup" data-type="horizontal" data-corners="false">
            <a href="${ctx }/m/edu/goedu" data-role="button" data-ajax="false" data-icon="check" data-theme="b">确认</a>
          </fieldset>
      </li>
    </ul>
  </div>
</div>

<script type="text/javascript">
function digitOnly ($this) {
	var n = $($this);
	var r = /^\+?[1-9][0-9]*$/;
	if (!r.test(n.val())) {
		n.val("");
	}
}

function checkSatus(code){
	var flag = false;
	$.ajax({
		url : rootPath+"/valid/checkflatsatus",
		type: "post",
		data : {"code":code},
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		timeout : 30000,
		dataType :"json",
		cache : false,
		async:false,
		error:function(){
			alert("网络连接有问题!");
		},
		success : function(obj) {
			if (obj.datas.status) {
				alert(obj.datas.msg);
			}else{
				flag = true;
			}
		}
	}); 
	return flag;
}

//加载账户金额
function loadMoney(flatName)
{
	if(!checkSatus(flatName)){
		return;
	}
	
	$.post(rootPath+"/member/getBlanceMoney?flatName="+flatName, {type:0 ,t:Math.random()}, function(data){
		if(data.rs){
			$("#"+flatName+"_money").text(data.datas.flatMoney);
		}else{
			$("#"+flatName+"_money a").text("请重新刷新获取金额");
		}
	}, "json");
}

function incheckparam()
{
	var toFlatName = $("#f-d-10").val();
	var inmoney = $("#f-d-11").val();
	if(!checkParam(inmoney)){
		return;
	}
	$.mobile.changePage("#indialog", "pop", true, true);
}

function outcheckparam()
{
	var fromFlatName = $("#f-d-13").val();
	var outmoney = $("#f-d-12").val();
	if(outmoney == ""){
		alert("请输入金额");
		return false;
	}
	if(outmoney <= 0){
		alert("请正确输入金额");
		return false;
	}
	$.mobile.changePage("#outdialog", "pop", true, true);
}


$("#insubmit").click(function(event){
	var toFlatName = $("#f-d-10").val();
	if(!checkSatus(toFlatName)){
		return;
	}
	var inmoney = $("#f-d-11").val();
	if(!checkParam(inmoney))
		return;
	$("#in_sub").hide();
	$("#in_msg").show();
	$.ajax( {
		url : rootPath+"/member/doEdu",
		type : "POST",
		data :{'wPoints':inmoney,'fromFlatName':"account",'toFlatName':toFlatName},
		dataType : "json",
		timeout:30000,
		success : function(data) {
			$("#respMsg").text(data.msg);
			$.mobile.changePage("#resp_success", "pop", true, true);
		}
	});
});

$("#outsubmit").click(function(event){
	var fromFlatName = $("#f-d-13").val();
	var outmoney = $("#f-d-12").val();
	
	if(!checkSatus(fromFlatName)){
		return;
	}
	
	if(outmoney == ""){
		alert("请输入金额");
		return false;
	}
	if(outmoney <= 0){
		alert("请正确输入金额");
		return false;
	}
	$("#out_sub").hide();
	$("#out_msg").show();
	$.ajax( {
		url : rootPath+"/member/doEdu",
		type : "POST",
		data :{'wPoints':outmoney,'fromFlatName':fromFlatName,'toFlatName':"account"},
		dataType : "json",
		timeout:30000,
		success : function(data) {
			$("#respMsg").text(data.msg);
			$.mobile.changePage("#resp_success", "pop", true, true);
		}
	});
});

function checkParam(inmoney){
	if(inmoney == ""){
		alert("请输入金额");
		return false;
	}
	if (inmoney < 10){
		alert("最小额度金额:10元");
		return false;
	}
	return true;
}
</script>
</body>

</html>
