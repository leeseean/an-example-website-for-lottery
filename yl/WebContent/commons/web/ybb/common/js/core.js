$(function(){
// 首頁彈窗
/*$('.ybb-dia-hp').dialog({
  autoOpen: false,
  modal: true,
  show: {
      effect: 'fade', duration: 800
  }
});*/
// 会员登入
// $('.ybb-dia-si').dialog({
//     autoOpen: false,
//     modal: true
//   });
//   $('.signin-btn').click(function(){
//     $('.ybb-dia-si').dialog('open');
//     return false;
//   });
});
////////
// 全局 //
////////
// 驗證碼
function createCode() {
  $("#img_validateCode").attr("src",rootPath+"/resources-code.jpg?" + new Date().getTime());
}
createCode();
$(function(){
$('.ybb-pg-nv-tiny .item').hover(function(){
    $(this).find('.second').fadeIn();
  },function(){
    $(this).find('.second').fadeOut();
  });
// 輪播3組
// $('.widget-carousel').slide({
//   titCell: '.carousel-paging li',
//   mainCell: '.carousel-body ul',
//   effect: 'leftLoop',
//   vis: 3,
//   scroll: 1,
//   delayTime: 800,
//   trigger: 'click',
//   easing: 'swing'
// });
// $('.dropmenu').selectmenu();
//var s = $(".header-sticker");
//var pos = s.position();
//$(window).scroll(function() {
//    var windowpos = $(window).scrollTop();
//    // s.html("Distance from top:" + pos.top + "<br />Scroll position: " + windowpos);
//    if (windowpos >= pos.top) {
//        s.addClass("stick");
//    } else {
//        s.removeClass("stick");
//    }
//});
});

// 登入狀態失效 - 暂不启用
// $(function(){
// // 未登入
// $('.ybb-status-uk-ct').dialog({
//   autoOpen: false,
//   modal: true,
//   minHeight: 50,
//   show: {
//     effect: 'fadeIn'
//   }
// });
// $('.ybb-status-uk').click(function(){
//   $('.ybb-status-uk-ct').dialog('open');
// });
// });
// document.writeln("<div class=\"ybb-status-uk-ct\" title=\"提示\"><p>您尚未登录，请登录后再进行游戏</p></div>");

// 定位
// function stickyRelocate(){
//   var windowTop = $(window).scrollTop();
//   var divTop = $('.sticky-anchor').offset().top;
//   // if(windowTop > divTop){
//   if(windowTop > divTop){
//     $('.sticky').addClass('stick');
//   } else {
//     $('.sticky').removeClass('stick');
//   }
// }
// $(function(){
//   $(window).scroll(stickyRelocate);
//   stickyRelocate();
// });
// lazyload
$('.lazy').lazyload({
  effect: 'fadeIn'
});
$(function(){
// accordion
$('.accordion').accordion({
  active: false,
  collapsible: true,
  animate: 500,
  heightStyle: 'content',
  header: '.accordion-header'
});
// $('.md-slide').slide({
//   titCell: '.md-slide-paging li',
//   mainCell: '.md-slide-group',
//   effect: 'left',
//   vis: 3,
//   scroll: 1,
//   delayTime: 800,
//   trigger: 'click',
//   easing: 'easeOutCirc'
// });

$('.ui-button').button();

});
////////
// 優惠 //
////////
$(function(){
/**
 * 熱門優惠
 */
$('.discount-hot-item').hover(function(){
  $(this).addClass('active').children('.discount-hot-content').animate({height: '144px', opacity: '0.8'}, 100, 'linear');
},function(){
  $(this).removeClass('active').children('.discount-hot-content').animate({height: '0', opacity: '1'}, 100, 'linear');
});
var s = $(".discount-sticker");
var pos = s.position();
//$(window).scroll(function() {
//    var windowpos = $(window).scrollTop();
//    // s.html("Distance from top:" + pos.top + "<br />Scroll position: " + windowpos);
//    if (windowpos >= pos.top) {
//        s.addClass("stick");
//    } else {
//        s.removeClass("stick");
//    }
//});
/**
 * 優惠列表
 */
$('.promos-group').accordion({
  active: false,
  collapsible: true,
  animate: 500,
  heightStyle: 'content',
  header: '.promos-section-head'
});
/**
 * 優惠分類
 */
$('#discount-page').tabs({
  beforeLoad: function(event, ui){
    ui.jqXHR.fail(function(){
      ui.panel.html(
        '抱歉，暫無內容')
    });
  }
});
});
////////
// 電子 //
////////
$(function(){
/**
 * 廳載入
 */
$('#slots-page').tabs({
  show: {
    effect: 'fadeIn', duration: 300
  },
  beforeLoad: function(event, ui){
    ui.jqXHR.fail(function(){
      ui.panel.html(
        '抱歉，暫無內容')
    });
  }
});
    $('.lazy').lazyload({
      effect: 'fadeIn'
    });
    $('.ybb-slot-bd .item').hover(function(){
      $(this).addClass('current').children('.title').animate({height: '100%', opacity: '0.7'}, 100, 'linear').next('.overlay').animate({top: '0'}, 300, 'linear');
    },function(){
      $(this).removeClass('current').children('.title').animate({height: '30px', opacity: '0.8'}, 100, 'linear').next('.overlay').animate({top: '100%'}, 300, 'linear');
    });
});
////////
// 幫助 //
////////
$(function(){
/**
 * 左侧AJAX
 */
var containerId = '#tabs-container';
var tabsId = '#tabs';

$(document).ready(function(){
  // Preload tab on page load
  if($(tabsId + ' LI.current A').length > 0){
    loadTab($(tabsId + ' LI.current A'));
  }

    $(tabsId + ' A').click(function(){
      if($(this).parent().hasClass('current')){ return false; }

      $(tabsId + ' LI.current').removeClass('current');
      $(this).parent().addClass('current');

      loadTab($(this));
        return false;
    });
});
/**
 * 右側Tab
 */
// $('.assist-body').tabs();
function loadTab(tabObj){
    if(!tabObj || !tabObj.length){ return; }
    $(containerId).addClass('loading');
    $(containerId).fadeOut('fast');

    $(containerId).load(tabObj.attr('href'), function(){
        $(containerId).removeClass('loading');
        $(containerId).fadeIn('fast');
    });
}

});
////////
// 註冊 //
////////
$(function(){
  $('.ybb-su-birthday').datepicker({
    autoSize: false,
      changeMonth : true,
      changeYear  : true,
      monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'],
      dayNamesMin : ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
      showOn      : 'both',
      buttonImage : root+'/web/ybb/common/images/ico-calendar.gif',
      buttonImageOnly : true,
      yearRange   : '-100:-18',
      defaultDate : '-30y',
      beforeShow  : function () {
          setTimeout(function () {$('#ui-datepicker-div').css("z-index", 501);}, 100);
      }
  });
/**
 * 開戶協議
 */
$('.signup-agm-content').dialog({
  autoOpen: false,
  width: 600,
  dialogClass: 'signup-agm-dialog',
//  buttons: {
//    "关闭": function(){
//      $(this).dialog("close");
//    }
//  },
  modal: true
});
$('.signup-agm').click(function(){
  $('.signup-agm-content').dialog('open');
  return false;
});
});
////////
// 協議規則 //
////////
$(function(){
/**
 * Tab
 */
$('.ybb-pg-ar-st').tabs({
  event: 'mouseover',
  show: { effect: 'blind', duration: 100}
});
});
// 回收站

//$('.signup-agm').hover(function(){
//  $(this).addClass('ui-state-hover');
//},function(){
//  $(this).removeClass('ui-state-hover');
//});

// $('.widget-search > .field-clear').click(function(){
//   $(this).siblings('.field-write').val('');
// });
// 實時索引
// $('#search-live').hideseek({
//   ignore: '.ignore-me',
//   nodata: '正在火星实验室研究中...'
// });


// // Tab切換
// $('.fw-slide-slots').slide({
//   trigger: 'click',
//   titCell: '.fw-slide-title .item',
//   mainCell: '.fw-slide-body',
//   startFun: function(i){
//     var curCon = $('.fw-slide-slots .fw-game-group').eq(i);
//     $.ajax({
//       url: curCon.attr('rel'),
//       cache: true,
//       success: function(html){
//         curCon.html(html).css('background', 'none');
// $('.lazy').lazyload({
//   effect: 'fadeIn'
// });
// // 圖標動效
// $('.fw-game-group .item').hover(function(){
//   $(this).addClass('current').children('.title').animate({height: '100%', opacity: '0.7'}, 100, 'linear').next('.overlay').animate({top: '0'}, 300, 'linear');
// },function(){
//   $(this).removeClass('current').children('.title').animate({height: '30px', opacity: '0.8'}, 100, 'linear').next('.overlay').animate({top: '100%'}, 300, 'linear');
// });
//       }
//     });
//   }
// });