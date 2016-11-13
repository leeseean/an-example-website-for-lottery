jQuery('.switch-load-mod').slide({
  titCell: '.switch-load-name .item',
  mainCell: '.switch-load-group',
  trigger: 'click',
  startFun: function(i){
    var curCon = $('.switch-load-item').eq(i);
//    $.ajax({
//      url: curCon.attr('data-url'),
//      cache: true,
//      success: function(html){
//        curCon.html(html).css('background', 'none');
//      }
//    });
 
    
    
  }
});