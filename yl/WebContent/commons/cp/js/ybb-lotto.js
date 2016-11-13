// $(document).ready(function() {});
$('.lotto-nav').slide({
  titCell: '.nav-main .ui-item',
  mainCell: '.nav-child',
  trigger: 'click',
  titOnClassName: 'ui-active'
});
// Tabs
$('.ui-tabs .ui-tabs-head .ui-link').on('click', function(event) {
  clearBetForm();
  	
  var currentAttrValue = $(this).attr('data-url');
  alert('----------');
  
  $('.ui-tabs-item' + currentAttrValue).fadeIn(400).siblings().hide();
  $(this).parent('li').addClass('ui-active').siblings().removeClass('ui-active');
  event.preventDefault();
});
// iCheck
$('.ui-checkbox').iCheck({
  checkboxClass: 'ui-checkbox-mini',
  checkedClass: 'checked'
});
$('.ui-radio').iCheck({
  radioClass: 'ui-radio-mini',
  checkedClass: 'checked'
});
