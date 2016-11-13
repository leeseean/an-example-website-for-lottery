<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title></title>
  <%@include file="common.jsp"%>
  <base target="_self">
</head>

<body class="ag-frame-content font-hei">
  <div class="ag-wrap ag-edit">
    <table width="100%" class="ag-form">
      <thead>
        <tr>
          <th colspan="19">
            <div class="ag-form-head cf">
              <h2 class="col-one ele-title l">代理商资料修改</h2>
            </div>
          </th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td colspan="2" class="ag-form-title pr20 b">代理商账号</td>
          <td colspan="2" class="pl20 al">Admin</td>
        </tr>
        <tr>
          <td colspan="2" class="ag-form-title pr20 b">昵称</td>
          <td colspan="2" class="pl20 al">
            <input type="text" size="10" class="ui-write">
          </td>
        </tr>
        <tr>
          <td colspan="2" class="ag-form-title pr20 b">新密码</td>
          <td colspan="2" class="pl20 al">
            <input type="password" class="ui-write">
          </td>
        </tr>
        <tr>
          <td colspan="2" class="ag-form-title pr20 b">新安全码</td>
          <td colspan="2" class="pl20 al">
            <input type="text" class="ui-write">
          </td>
        </tr>
        <tr>
          <td colspan="2" class="ag-form-title pr20 b">电话号码</td>
          <td colspan="2" class="pl20 al">
            <input type="text" class="ui-write">
          </td>
        </tr>
        <tr>
          <td colspan="2" class="ag-form-title pr20 b">电子邮箱</td>
          <td colspan="2" class="pl20 al">
            <input type="text" class="ui-write">
          </td>
        </tr>
        <tr>
          <td rowspan="10" width="200" class="ag-form-title pr20 b">总代理占成</td>
          <td width="100" class="ag-form-title pr20 b">AG</td>
          <td class="pl20 al" width="100">
            <input type="text" size="1" maxlength="2" class="ui-write ac"> %
          </td>
          <td rowspan="10" class="pl10 gray">可设占成(0%-94%)</td>
        </tr>
        <tr>
          <td class="ag-form-title pr20 b">HG</td>
          <td class="pl20 al">
            <input type="text" size="1" maxlength="2" class="ui-write ac"> %
          </td>
        </tr>
        <tr>
          <td class="ag-form-title pr20 b">BBIN</td>
          <td class="pl20 al">
            <input type="text" size="1" maxlength="2" class="ui-write ac"> %
          </td>
        </tr>
        <tr>
          <td class="ag-form-title pr20 b">DS</td>
          <td class="pl20 al">
            <input type="text" size="1" maxlength="2" class="ui-write ac"> %
          </td>
        </tr>
        <tr>
          <td class="ag-form-title pr20 b">MG</td>
          <td class="pl20 al">
            <input type="text" size="1" maxlength="2" class="ui-write ac"> %
          </td>
        </tr>
        <tr>
          <td class="ag-form-title pr20 b">PT</td>
          <td class="pl20 al">
            <input type="text" size="1" maxlength="2" class="ui-write ac"> %
          </td>
        </tr>
        <tr>
          <td class="ag-form-title pr20 b">NT</td>
          <td class="pl20 al">
            <input type="text" size="1" maxlength="2" class="ui-write ac"> %
          </td>
        </tr>
        <tr>
          <td class="ag-form-title pr20 b">皇冠体育</td>
          <td class="pl20 al">
            <input type="text" size="1" maxlength="2" class="ui-write ac"> %
          </td>
        </tr>
        <tr>
          <td class="ag-form-title pr20 b">彩票</td>
          <td class="pl20 al">
            <input type="text" size="1" maxlength="2" class="ui-write ac"> %
          </td>
        </tr>
      </tbody>
      <tfoot>
        <tr>
          <td colspan="4">
            <div class="ag-form-foot cf">
              <div class="col-two col-affirm ac">
                <button class="ui-btn">确定</button><button onclick="goBack();" class="ui-btn ml10">返回</button>
              </div>
            </div>
          </td>
        </tr>
      </tfoot>
    </table>
  </div>
</body>
<%@include file="comm_foot.jsp"%>
</html>
