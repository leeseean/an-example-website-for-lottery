<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div data-role="dialog" data-close-btn="none">
  <div data-role="header">
    <h1>操作结果</h1>
  </div>
  <div data-role="content" class="ybm-order-confirm">
    <ul data-role="listview">
      <li data-role="list-divider" data-theme="e">
      	${msg}
      </li>
      <li style=" text-align: center;">
          <fieldset data-role="controlgroup" data-type="horizontal" data-corners="false">
            <a href="${ctx }/m/wap/member" data-role="button" data-ajax="false" data-icon="check" data-theme="b">确认</a>
          </fieldset>
      </li>
    </ul>
  </div>
</div>
