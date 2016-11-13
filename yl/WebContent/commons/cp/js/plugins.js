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
	
	$.fn.textLines = function () {
		var $el = $(this);
		var text = $el.text(),
			fontSize = parseInt($el.css('font-size'));
		var totalWidth = text.length * fontSize;
		
		return Math.ceil(totalWidth / $el.width());
	};
	
	window.registerPlugin = ( function () {
		var plugins = {};
		return function (name, callback) {
			if (typeof callback != 'undefined') plugins[name] = callback;
			if (typeof name == 'undefined') return plugins;
			
			return plugins[name];
		};
	})();
	
	function scanElementAndAttachPlugin(forceScan) {
		$('input, button, textarea, a, div, b, span, img, label').each(function() {
			var plugins = registerPlugin(),
				el = $(this);
			
			// has already binded plugin.
			if (el.data('attached') && !forceScan) return;
			
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
			el.data('attached', true);
		});
	}
	
	$(function () {
		setInterval(scanElementAndAttachPlugin, 1000 * 0.5 ); // .5s scan elements
		
		// scan document after ajax returned
		$(document).ajaxSuccess(function () {
			scanElementAndAttachPlugin(true);
		});
	});
})(jQuery);

// 自动计算高度插件
registerPlugin('autoHeightClass', function () {
	var classes = [].slice.call(arguments),
		$self = $(this);
	
	var selectorStr = classes.pop();
	
	$(selectorStr, $self).each(function () {
		var $el = $(this);
		var lines = $el.textLines();
		if (!$self.data('max-lines') || $self.data('max-lines') < lines) $self.data('max-lines', lines);
	});
	
	var maxLines = $self.data('max-lines');
	if (typeof classes[maxLines] == 'undefined') {
		maxLines = classes.length -1 ;
	}
	
	var heightClass = classes[maxLines];
	$(selectorStr, $self).addClass(heightClass);
});


