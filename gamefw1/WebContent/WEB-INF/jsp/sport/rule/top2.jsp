<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<label>
                <select name="jumpMenu" id="jumpMenu" onchange="chg_roul(this.options[this.selectedIndex].value);">
                    <option value="${ctx }/sport/help/qa" >一般体育说明</option>
                    <option value="${ctx }/sport/help/outright" >冠军</option>
                    <option value="${ctx }/sport/help/parlays_multiples" >连串过关 / 复式过关</option>
                    <option value="${ctx }/sport/help/football" >足球</option>
                    <option value="${ctx }/sport/help/basketball">篮球</option>
                </select>
</label>