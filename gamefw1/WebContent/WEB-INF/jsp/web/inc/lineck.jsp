
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="msg.jsp" %>
<div class="line-check-container">
	<div class="ybb-pg-ct clear">
		<div class="ybb-pg-sb">
			<div class="ybb-md-tt">无法正常访问？</div>
			<div class="ybb-md-ct">
				<ul>
					<li class="acc-title curr"><a href="javascript:void(0)">尝试清除缓存</a>
					</li>
					<li class="acc-body"
						style="height:auto;display:block;font-size:12px;color:white">选择：工具
						-> Internet选项 -> 在选择(删除历史浏览记录) -> 删除 -> 重启IE</li>
					<li class="acc-title"><a href="javascript:void(0)">如果您是视频卡</a>
					</li>
					<li class="acc-body" style="height:auto;font-size:12px;color:white">
						<p>请在您的游戏页面右下角点击"摄像头"的小图标，选择一个新线路使用看看！</p>
						<p>
							<img
								src="${resourceRoot}/web/ybb/assets/images/common/video-line.jpg"
								width="210">
						</p></li>
				</ul>
			</div>
		</div>
		<div class="ybb-pg-mn">
			<div class="speed-body">
				<h6></h6>
				<script type="text/javascript">
					time = 1;
					setInterval("time++", 100);
					b = 1;
					var speedUrl = new Array();
					speedUrl[1] = "www.mgm088.cc";
					speedUrl[2] = "www.mgm188.cc";
					speedUrl[3] = "www.mgm288.cc";
					speedUrl[4] = "www.mgm388.cc";
					speedUrl[5] = "www.mgm488.cc";
					speedUrl[6] = "www.mgm588.cc";
					speedUrl[7] = "www.mgm088.net";
					speedUrl[8] = "www.mgm188.net";
					speedUrl[9] = "www.mgm288.net";
					speedUrl[10] = "www.mgm388.net";
					speedUrl[11] = "www.mgm488.net";
					speedUrl[12] = "www.mgm588.net";
					function speedBtn() {
						document
								.write("<form name='speedTest' onsubmit='return false;'>");
						for ( var i = 1; i < speedUrl.length; i++)
							document
									.write("<div class='speed-row clear'><input type='text' id='txt"+i+"' value='测速中' class='speed-item speed-ms'><input type='text' id='url"+i+"' class='speed-item speed-url'><input type='button' value='进入网站' onclick='window.open(this.form.url"
											+ i
											+ ".value)' class='speed-item speed-view'></div>");
						document
								.write("<a onclick='window.location.reload();' class='speed-start'>重新测速</a></form>");
					}
					speedBtn();
					function auto(url) {

						document.getElementById("url" + b).value = url;
						if (time > 200) {
							document.getElementById("txt" + b).value = "链接超时";
						} else {

							document.getElementById("txt" + b).value = time
									/ 10 + "秒";

						}
						b++;
					}
					function run() {
						for ( var i = 1; i < speedUrl.length; i++)
							document
									.write("<span style='display:none'><img src='http://"
											+ speedUrl[i]
											+ "/"
											+ Math.random()
											+ " width='1' height='1' onerror=auto('http://"
											+ speedUrl[i] + "')></span>");
					}
					run();
				</script>
			</div>
		</div>
	</div>
</div>
