$('.nav__item').click(function() {
    $(this).toggleClass("curr").siblings(".nav__item").removeClass("curr");
});
$('.ag-form-content').hover(function() {
  $(this).addClass('row-curr');
}, function() {
  $(this).removeClass('row-curr');
});
$('.ui-date').datepicker({
  monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
  dayNamesMin: ['日', '一', '二', '三', '四', '五', '六']
});
$('.ui-btn').button();
// $('.ui-select').selectmenu();
function goBack(){
  window.history.back();
}