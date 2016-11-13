var ary = location.href.split("_");
jQuery('.switch-page').slide({
  titCell: '.switch-name .item',
  mainCell: '.switch-content ul',
  trigger: 'click',
  defaultIndex: ary[1]
});
jQuery('.switch-mod').slide({
  titCell: '.switch-menu .item',
  mainCell: '.switch-group',
  trigger: 'click'
});
jQuery('.slide-paging').slide({
  titCell: '.slide-paging-group',
  mainCell: '.slide-paging-body ul',
  autoPage: true,
  autoPlay: true,
  vis: 1,
  trigger: 'click'
});
// 彈層
layer.config({
  skin: 'ybDialog'
});
var index = parent.layer.getFrameIndex(window.name);
$('.btn-contact').click(function(){
  parent.layer.open({
    type: 2,
    title: false,
    shadeClose: true,
    area: ['700px', '304px'],
    content: [rootPath+'/member/gotoOnlineContact', 'no']
  });
});
// 日厲選擇
//$('.datepicker').datepicker();
$('.datepicker').datepicker({ dateFormat: 'yy-mm-dd' });
// 下拉菜單
$('.dropmenu').selectmenu();
// 操作步驟
function stepOne(){
  layer.confirm('OK?', {icon: 3, title: '确认提交的信息'}, function(index){
    layer.close(index);
    $('.progress-body-step1').hide();
    $('.progress-body-step2').load('pages/recharge-bank-step2.html');
    $('.progress-body-step2').show();
    $('.step-one').removeClass('step-active');
    $('.step-two').addClass('step-active');
  });
}
function stepTwo(){
  layer.confirm('OK?', {icon: 3, title: '确认提交的信息'}, function(index){
    layer.close(index);
    $('.progress-body-step2').hide();
    $('.progress-body-step3').load('pages/recharge-bank-step2.html');
    $('.progress-body-step3').show();
    $('.step-two').removeClass('step-active');
    $('.step-three').addClass('step-active');
  });
}