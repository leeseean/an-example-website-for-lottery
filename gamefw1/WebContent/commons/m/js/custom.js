$(document).ready(function() {
  $('#loader').fadeOut(500);
});

// 超时
setTimeout(function () {
	  $('#loader').fadeOut(500);
}, 1500);

$(document).on('pageinit', '.ybb-mobile-web', function() {
  $('.i-more').click(function() {
    $('.nav-more').slideToggle('fast');
    return false;
  });
  $(document).click(function(event) {
    $('.nav-more').slideUp('fast');
  });
});