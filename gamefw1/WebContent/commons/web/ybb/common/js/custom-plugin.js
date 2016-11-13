(function ($) {
	
	$.keys = function (arr){
		var keys = [];
		for (var key in arr) keys.push(key);
		
		return keys;
	};
	
	$.urlParam = function (name, url) {
	    if (!url) url = window.location.href;
	    name = name.replace(/[\[\]]/g, "\\$&");
	    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
	        results = regex.exec(url);
	    if (!results) return null;
	    if (!results[2]) return '';
	    return decodeURIComponent(results[2].replace(/\+/g, " "));
	};
	
	Number.prototype.pad = function (size) {
		var s = String(this);
		while (s.length < (size || 2) ) s  = '0' + s;
		return s;
	};
	
	// Lightweight queue callback timer
	$.callbackQueue = (function () {
		var queues = {};
		
		// interval callback executer
		setInterval(function () {
			for (var queueName in queues) {
				var callbacks = queues[queueName];
				while ((callback = callbacks.shift())) {
					var fn = callback['cb'];
					var context = callback['context'];
					fn.apply(context, []);
				}
			}
		}, 100);
		
		return function(queueName, callback, context) {
			context || (context = {});
			if (typeof queues[queueName] == 'undefined') queues[queueName] = [];
			queues[queueName].push({
				cb: callback,
				context: context
			});
		};
	})();
	
	window.registerPlugin = ( function () {
		var plugins = {};
		return function (name, callback) {
			if (typeof callback != 'undefined') plugins[name] = callback;
			if (typeof name == 'undefined') return plugins;
			
			return plugins[name];
		};
	})();
	
	$(function () {
		$('input, button, textarea, a, div, b, span, img, label').each(function() {
			var plugins = registerPlugin(),
				el = $(this);
			
			var attachPluginName = el.attr('plugin'),
				args,
				callback;
			if (attachPluginName) {
				callback = plugins[attachPluginName];
				args = [];
			}
			else {
				for (var name in plugins) {
					var attrName = 'plugin-' + name;
					if ( attrVal = el.attr(attrName) ) {
						args = attrVal.split(',');
						callback = plugins[name];
					}
				}
			}
			callback && callback.apply(el, args);
		});
	});
})(jQuery);

// placeholder 插件
registerPlugin('placeholder', function (placeholder) {
	var el = $(this);
	var test = document.createElement("input");
	var supportedPlaceHolder = ('placeholder' in test);
	if (supportedPlaceHolder) {
		el.attr('placeholder', placeholder);
	}
	// 模拟
	else {
		el.val(placeholder);
		el.focus(function () {
			if (el.val() == placeholder) {
				el.val('');
			}
		});
		el.blur(function () {
			if (el.val() == '' || el.val().trim() == '') {
				el.val(placeholder);
			}
		});
	}
});

// 幻灯片插件
registerPlugin('superslide', function () {
	var $container = $(this);
	
	
	function allImagesLoadedCallback () {
		var pagerSelector = $(this).attr('plugin-data-pager');
		var options = {
				type: 'slide',
				autoPlay: true,
				interTime: 1500,
				easing: 'swing',
				effect: 'fold',
				delayTime: 900,
				trigger: 'click'
		};
		if (pagerSelector) {
			options['autoPage'] = true;
			options['titCell'] = pagerSelector;
		}
		$(this).slide(options);
	}
	
	var image = new Image();
	image.onload = function () {
		var imgWidth = image.width,
			imgHeight = image.height;
		
		var winWidth = $(window).width();
		var realHeight = imgHeight * (winWidth / imgWidth);
		$container.css('height', realHeight);
	};
	
	image.src = $container.find('img:eq(0)').attr('src');
	
	allImagesLoadedCallback.apply($container);
});


// 跑马灯
registerPlugin('marquee', function() {
	var $ul = $(this).find('ul');
	
	var oldWidth = 0;
	$ul.find('li').each(function () {
		oldWidth += $(this).find('a').width();
	});
	$ul.css('width', oldWidth);
	
	$ul.data('old-width', $ul.width());
	var duration = ( $ul.width() / $ul.parent().width() ) * 1000 * 30;
	
	function startAnimate() {
		var maxWidth = $ul.width();
		$ul.animate({
			'margin-left': '-' + maxWidth + 'px'
		}, {
			duration: duration,
			easing: 'linear',
			complete: function () {
				$ul.css({'margin-left': 0});
				startAnimate();
			}
		});
	}
	
	(setTimeout(startAnimate, 1000));
});

// 刷新验证码
registerPlugin('refreshLoginCode', function (img, source) {
	$(this).click(function () {
		$(this).find(img).attr('src', source + "?time=" + new Date().getTime());
	}).click();
});

// 图片自动切换插件
registerPlugin('imgAutoSlide', function (second, duration) {
	var $imgs = $(this).find('img'),
		crtIndex = 0;
	duration || (duration = 2);

	setInterval(function () {
		var $crtImg = $imgs.eq(crtIndex);
		
		var nextIndex = crtIndex + 1;
		if (nextIndex >= $imgs.size()) {
			nextIndex = 0;
		}
		var $nextImg = $imgs.eq(nextIndex);
		
		$crtImg.animate({
			opacity: 0
		}, {
			duration: duration * 1000
		});
		
		$nextImg.animate({
			opacity: 1
		}, {
			duration: duration* 1000 
		});
		
		if (crtIndex >= $imgs.size() - 1) {
			crtIndex = 0;
		}
		else {
			crtIndex += 1;
		}
	}, second * 1000);
});

// 二级导航
registerPlugin("navSubmenu", function (submenuSelector, parentSelector) {
	var $nav = $(this),
		$submenuContainer = $(submenuSelector);
	
	$nav.find('ul > li').mouseenter(function () {
		var $li = $(this),
			hovering = 'hovering';
		if ($li.hasClass(hovering)) return;
		var cls = $(this).attr('class');
		if (cls && (matches = cls.match(/[\d]+/))) {
			var index = matches[0];
			$li.addClass(hovering);
			
			if (!$submenuContainer.hasClass('hover')) {
				$submenuContainer.addClass('hover');
			}
			
			$submenuContainer.find('ul.submenu').addClass('hideme');
			$submenuContainer.find('ul.submenu-'+index).css({opacity: 0}).removeClass('hideme').animate({
				opacity: 1
			}, {duration :500, complete: function () {
				$li.removeClass(hovering);
				$(this).removeAttr('style');
			}});
		}
		else {
			hideAll();
		}
	});
	
	function hideAll() {
		$submenuContainer.find('ul.submenu').css({
			opcity: 0
		}).addClass('hideme').removeAttr('style');
		
		$submenuContainer.removeClass('hover');
		
		$submenuContainer.stop(true, true).animate({
			opacity: 0
		}, {duration: 100, complete: function () {
			$(this).removeAttr('style');
		}});
	}
	
	$(parentSelector).hover(function () {
		//
	}, function () {
		hideAll();
	});
});

// 帮助中心
registerPlugin('helpCenterNavgation', function () {
	var $self = $(this),
		$mainNav = $(this).find('.nav-left');
	
	// 获取页面内容
	$mainNav.find('li >a ').click(function (event) {
		event.preventDefault();
		$(this).parent().addClass('current').siblings().removeClass('current');
		loadHelpCenterPageContent($(this).attr('href')).then(function (res) {
			$self.find('.page-body').html(res);
			bindSubNavAction();
		}, function () {
			alert('发生了未知错误');
		});
		
		return false;
	}).each(function () {
		// 找出与页面对应的帮助内容并加载
		if ($(this).parent().hasClass('current')) {
			$(this).click();
		}
	});
		
	// 默认
	if ($mainNav.find('li.current').size() <= 0) {
		$mainNav.find('li > a:eq(0)')
			.click()
			.parent()
			.addClass('current');
	}
	
	function loadHelpCenterPageContent(url) {
		var deferred = $.Deferred();
		$.ajax({
			url: url
		}).done(function (res) {
			deferred.resolve(res);
		}).fail(function() {
			deferred.inject();
		});
		
		return deferred.promise();
	}
	
	function bindSubNavAction() {
		$self.find('.tab-title > li').click(function () {
			var index = $(this).index();
			$self.find('.tab-content').find('div').hide().eq(index).show();
		});
	}
});

registerPlugin('alert', function (msg) {
	$(this).parents('li').click(function (event) {
		event.preventDefault();
		alert(msg);
		return false;
	});
});

// 无限循环轮播插件
registerPlugin('infiniteCircleSlider', function (prev, next) {
	var $sliderCon = $(this),
		conWidth = $sliderCon.width();
	var $sliderItemsCon = $sliderCon.find('.bd');
	var total = $sliderItemsCon.find('>li').size();
	var slideWidthOneTime = 0;
	var $prev = $(prev),
		$next = $(next);
	
	var children = $sliderItemsCon.children().clone();
	$sliderItemsCon.append(children);
//	$sliderItemsCon.find('li').css({
//		width: ( conWidth  )/ children.size()
//	});
	
	slideWidthOneTime = $sliderItemsCon.find('li').width();
	var totalWidth = 0;
	
	var fns = (function (slideWidthOneTime, slideObject) {
			var maxIndex = children.size(),
				crtIndex = 0;
			
		return [function () {
			// next 
			if (slideObject.is(':animated')) return;
			if (crtIndex >= maxIndex) {
				crtIndex = 0;
				slideObject.css({'margin-left': 0});
			}
			
			slideObject.animate({
				'margin-left': -(crtIndex + 1) * slideWidthOneTime
			}, {duration: 800, complete: function () {
				crtIndex ++;
			}});
			
		}, function () {
			// prev
			if (slideObject.is(':animated')) return;
			
			if (crtIndex <= 0) {
				crtIndex = maxIndex;
				slideObject.css({
					'margin-left': -(maxIndex) * slideWidthOneTime
				});
			}
			slideObject.animate({
				'margin-left': -(crtIndex - 1)* slideWidthOneTime
			}, {duration: 800, complete: function () {
				crtIndex --;
			}});
		}];
	})(slideWidthOneTime, $sliderItemsCon);
	
	var slideToNext = fns[0],
		slideToPrev = fns[1];
	
	
	$(window).load(function () {
		$sliderItemsCon.find('li').each(function() {
			totalWidth += $(this).width();
		});
		$sliderItemsCon.css({width: totalWidth});
	});
	
	$(window).load(function () {
		setInterval(function () {
			slideToNext();
		}, 1000 * 5);
	});
	
	$prev.click(function() {
		$.callbackQueue('infinitePrevCallback', slideToPrev);
	});
	
	$next.click(function () {
		$.callbackQueue('infiniteNextCallback', slideToNext);
	});
});

//关闭左右浮动框 
registerPlugin("closeFloatPopup", function () {
	$(this).click(function (event) {
		event.preventDefault();
		$(this).parent().fadeOut();
		return false;
	});
});


// 美东时间
registerPlugin("timer", function(offset) {
	
		
		var $self = $(this);
			
		var serverDate, 
			click = 0,
			secondAfterRefreshDate = 10 * 60; // 10 分钟后刷新 server date
		function getDateToString() {
			var year = serverDate.getFullYear(),
				month = serverDate.getMonth() + 1,
				day = serverDate.getDate();
			
			
			var hour = serverDate.getHours(),
				min = serverDate.getMinutes(),
				seconds = serverDate.getSeconds();
			
			return year + '/' + month.pad(2) +  '/' + day.pad(2) + ' '  + hour.pad(2) + ':' + min.pad(2) + ':' + seconds.pad(2) + '(UTC -5:00)';
		}
		
		function refreshServerDate() {
			offset = offset * 1;
			var clientDate = new Date();
			var utc = clientDate.getTime() + (clientDate.getTimezoneOffset() * 60000);
			
			serverDate = new Date(utc + (offset * 60 * 60 * 1000));
		}
		
		function renderTimer(str) {
			$self.html('<span>'+str+'</span>');
		}
		
		refreshServerDate();
		
		setInterval(function () {
			if (click >= secondAfterRefreshDate ) {
				refreshServerDate();
			}
			
			serverDate = new Date(serverDate.getTime() + 1000); // 前进一秒
			renderTimer(getDateToString());// 渲染
			click += 1;
			
		}, 1000 * 1);
});