if (typeof Array.prototype.indexOf == 'undefined') {
	Array.prototype.indexOf = function (val, fromIndex ) {
	    if (this == null) {
	      throw new TypeError('"this" is null or not defined');
	    }

	    var o = Object(this);

	    var len = o.length >>> 0;

	    if (len === 0) {
	      return -1;
	    }

	    var n = +fromIndex || 0;

	    if (Math.abs(n) === Infinity) {
	      n = 0;
	    }

	    if (n >= len) {
	      return -1;
	    }

	    k = Math.max(n >= 0 ? n : len - Math.abs(n), 0);

	    while (k < len) {
	      if (k in o && o[k] === searchElement) {
	        return k;
	      }
	      k++;
	    }
	    return -1;
	};
}

var TplRoutobj, TplRoutobj_Bet,
G = {};
$.UT = {};
$.UT_Bet = {};
$.CL = {};
G.ResultSplitChar = "\u00ea\u00ea\u00ea";
 
/****业务代码****/
var Core = {
    maxItem: 100,
    formatFloat: function(a, e) {
        var g = Math.pow(10, e);
        return Math.round(a * g) / g;
    },
    initwindowOpen: function(a, e) {
        window.open(a, e, "resizable=yes, scrollbars=yes ,width=1000, height=700", "toolbar=0,location=0,menubar=0").focus();
    },
    arrayIntersection: function(a, e) {
        for (var g = 0, c = 0, h = []; g < a.length && c < e.length;) 
        	a[g] < e[c] ? g++:(a[g] > e[c] || (h.push(a[g]), g++), c++);
        return h;
    },
    getArrayShare: function(a) {
        var e, g, c, h, l, m, q, t = [],
        r = {},
        y = ",",
        w = [];
        e = a.length;
        h = a[0];
        l = h.length;
        if (e) {
            if (1 == e) return h;
            for (m = 0; m < e; m++) c = a[m].length,
            l > c && (l = c, h = a[m], q = m);
            a.splice(q, 1);
            e = a.length;
            for (m = 0; m < e; m++) {
                r = {};
                g = a[m];
                c = g.length;
                for (q = 0; q < c; q++) r[g[q]] = !0;
                t.push(r);
            }
            for (m = 0; m < l; m++) for (a = h[m], q = 0; q < e; q++) t[q][a] && -1 == y.indexOf("," + a + ",") && (w.push(a), y += a + ",");
            return w;
        }
    },
    array_intersect: function(a) {
        var e = {},
        g = arguments.length,
        c = g - 1,
        h = "",
        l = {},
        m = 0,
        q = "";
        a: for (h in a) b: for (m = 1; m < g; m++) {
            l = arguments[m];
            for (q in l) if (l[q] === a[h]) {
                m === c && (e[h] = a[h]);
                continue b;
            }
            continue a;
        }
        return e;
    },
    count: function(a, e) {
        var g, c = 0;
        if (null === a || "undefined" === typeof a) return 0;
        if (a.constructor !== Array && a.constructor !== Object) return 1;
        "COUNT_RECURSIVE" === e && (e = 1);
        1 != e && (e = 0);
        for (g in a) a.hasOwnProperty(g) && (c++, 1 != e || !a[g] || a[g].constructor !== Array && a[g].constructor !== Object || (c += this.count(a[g], 1)));
        return c;
    },
    getParameterByName: function(a) {
        a = a.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
        a = (new RegExp("[\\?&]" + a + "=([^&#]*)")).exec(location.search);
        return null == a ? "": decodeURIComponent(a[1].replace(/\+/g, " "));
    },
    key_exists: function(a, e) {
        if (!e || e.constructor !== Array && e.constructor !== Object) return ! 1;
        for (var g = 0; g < e.length; g++) if (e[g] === a) return ! 0;
        return ! 1;
    },
    __GetUnique: function(a) {
        for (var e = [], g = 0; g < a.length; g++) - 1 == $.inArray(a[g], e) && e.push(a[g]);
        return e;
    },
    __FloatAdd: function(a, e) {
        var g, c;
        try {
            g = a.toString().split(".")[1].length;
        } catch(h) {
            g = 0;
        }
        try {
            c = e.toString().split(".")[1].length;
        } catch(l) {
            c = 0;
        }
        g = Math.pow(10, Math.max(g, c));
        return (a * g + e * g) / g;
    },
    __FloatSubtraction: function(a, e) {
        var g, c, h;
        try {
            g = a.toString().split(".")[1].length;
        } catch(l) {;
            g = 0;
        }
        try {
            c = e.toString().split(".")[1].length;
        } catch(m) {
            c = 0;
        }
        h = Math.pow(10, Math.max(g, c));
        return ((a * h - e * h) / h).toFixed(g >= c ? g: c);
    },
    __FloatMul: function(a, e) {
        var g = 0,
        c = a.toString(),
        h = e.toString();
        try {
            g += c.split(".")[1].length;
        } catch(l) {}
        try {
            g += h.split(".")[1].length;
        } catch(m) {}
        return Number(c.replace(".", "")) * Number(h.replace(".", "")) / Math.pow(10, g);
    },
    __FloatDiv: function(a, e) {
        var g = 0,
        c = 0,
        h, l;
        try {
            g = a.toString().split(".")[1].length;
        } catch(m) {}
        try {
            c = e.toString().split(".")[1].length;
        } catch(q) {}
        with(Math) return h = Number(a.toString().replace(".", "")),
        l = Number(e.toString().replace(".", "")),
        h / l * pow(10, c - g);
    },
    _ajax_template: function(a, e, cb) {
    	cb || (cb = function () {});
    	console.log('a' + a);
        $.ajax({
            type: "get",
            url: a,
            dataType: "text",
            data: "",
            async: true,
            timeout: 6E4,
            cache: !0,
            beforeSend: function(a) {},
            error: function(a, c, e) {},
            success: function(a) {
                switch (e) {
	                case "g":
	                    $.UT = a.split(G.ResultSplitChar);
	                    TplRoutobj = $.parseJSON($.UT[0].toString());
	                    $.CL.setLayout(TplRoutobj.bet);
	                    break;
	                case "b":
	                    $.UT_Bet = a.split(G.ResultSplitChar),
	                    TplRoutobj_Bet = $.parseJSON($.UT_Bet[0].toString());
	                    break;
                }
                
                cb.apply(this);
            },
            complete: function(a, c) {}
        });
    },
    _is_null: function(a) {
        return "undefined" == typeof a || null == a ? !0 : !1;
    },
    IsSafari: function() {
        var a = navigator.userAgent.toLowerCase();
        if ( - 1 != a.indexOf("safari")) return - 1 < a.indexOf("chrome") ? 2 : 1;
    },
    detectCSSFeature: function(a) {
        var e = !1,
        g = ["Webkit", "Moz", "ms", "O"],
        c = document.createElement("div"),
        h = null;
        a = a.toLowerCase();
        c.style[a] && (e = !0);
        if (!1 === e) for (h = a.charAt(0).toUpperCase() + a.substr(1), a = 0; a < g.length; a++) if (void 0 !== c.style[g[a] + h]) {
            e = !0;
            break;
        }
        return e;
    },
    createCookie: function(a, e, g) {
        if (g) {
            var c = new Date;
            c.setTime(c.getTime() + 864E5 * g);
            g = "";
            g = "" + c.toGMTString();
        } else g = "";
        document.cookie = a + "=" + e + "; expires=" + g + "; path=/";
    },
    GetCookie: function(a) {
        a += "=";
        for (var e = a.length,
        g = document.cookie.length,
        c = 0; c < g;) {
            var h = c + e;
            if (document.cookie.substring(c, h) == a) return Core.GetCookieVal(h);
            c = document.cookie.indexOf(" ", c) + 1;
            if (0 == c) break;
        }
        return null;
    },
    GetCookieVal: function(a) {
        var e = document.cookie.indexOf(";", a); - 1 == e && (e = document.cookie.length);
        return unescape(document.cookie.substring(a, e));
    },
    DeleteCookie: function(a) {
        Core.createCookie(a, "", -1);
    },
    formatFloat: function(a, e) {
        var g = Math.pow(10, e);
        return Math.round(a * g) / g;
    },
    centerMe: function(a) {
        var e = $(window).width(),
        g = $(window).scrollTop(),
        c = $(a).width();
        $(a).css("top", g + 0 + "px");
        $(a).css("left", parseInt(e / 2 - c / 2) + "px");
    },
    checkhHtml5: function() {
        return "undefined" !== typeof Worker ? !0 : !1;
    },
    SetTemplate: function(a) {
        $.CL.setLayout(a);
    },
    SetTopTemplate: function(a) {
        $.CL.setTopLayout(a);
    },
    SetBetTemplate: function(a) {
        $.CL.setBetLayout(a);
    },
    GetUrl: function(a, e) {
        a = a || window.location.toString();
        var g = "",
        c = "";
        if ( - 1 != a.indexOf("?")) {
            var h = a.split("?")[1].split("&"),
            l;
            for (l in h) g = h[l].split("=")[0],
            c = "pages" == g ? c + a.replace("?" + h[0], "") : c + decodeURI(h[l].split("=")[1]);
            return c;
        }
        return a;
    }
};


/***页面布局代码***/
$.CL.setBetLayout = function(a) {

    if ("undefined" == typeof TplRoutobj_Bet){
    	setTimeout(function() {
	        $.CL.setBetLayout(a);
	        //console.debug("等待下注樣板載入中");//等待下注樣板載入中
	    },
	    200);
    }else {
    	
        if (void 0 == $.UT_Bet[TplRoutobj_Bet[a]] || null == TplRoutobj_Bet[a]) return ! 1;
        var e = $.trim($.UT_Bet[TplRoutobj_Bet[a]]);

        e = e.replace(/{resourceRoot}/g, resourceRoot);
        e = e.replace(/{ctx}/g, rootPath);

        $("#betplay_data").html(e);
        $("#topHtml").hide();
	    $("#rightHtml").show();
    }
};

//页面模板加载
$.CL.setLayout = function(a) {
 
    if ("undefined" == typeof TplRoutobj){
	    setTimeout(function() {
	        $.CL.setLayout(a);
	        //console.debug("等待功能樣板載入中");//等待功能樣板載入中
	    },200);
    }else {
        if (1 == a || null == a) var e = $.trim($.UT[a]);
        else {
            if (void 0 == $.UT[TplRoutobj[a]] || null == TplRoutobj[a]) return ! 1;
            e = $.trim($.UT[TplRoutobj[a]]);
        }
        e = e.replace(/{resourceRoot}/g, resourceRoot);
        e = e.replace(/{ctx}/g, rootPath);

        $("#menu_left").html(e);
        $("#topHtml").hide();
	    $("#leftHtml").show();
    }
};

//菜单加载模板
$.CL.setTopLayout = function(a) {
	 if ("undefined" == typeof TplRoutobj){
	    setTimeout(function() {
	        $.CL.setLayout(a);
	        //console.debug("等待功能樣板載入中");//等待功能樣板載入中
	    },200);
    }else {
    	 if (1 == a || null == a){
    	    	var e = $.trim($.UT[a]);
    	    }else {
    	        if (void 0 == $.UT[TplRoutobj[a]] || null == TplRoutobj[a]) return ! 1;
    	        e = $.trim($.UT[TplRoutobj[a]]);
    	    }
    	 	e = e.replace(/{resourceRoot}/g, resourceRoot);
    	 	e = e.replace(/{ctx}/g, rootPath);
    	    $("#topHtml").html(e);
    	    
    	    $("#topHtml").show();
    	    $("#leftHtml").hide();
    	    $("#rightHtml").hide();
    }
};

//获取方法的名称
$.fnName = function(fn) {
	var names =  /^function\s+([\w\$]+)\s*\(/.exec( fn.toString() );
	if (names && names.length > 1) return names[1];
	return '';
};

//全局的 定时器
(function () {
	window.timer = (function () {
		
		function getFnName (cb) {
			var name = $.fnName(cb);
			if (name == '') throw '定时器回调函数必须有方法名称 不能是匿名名称';
			return name;
		}
		
		function generateTimerId() {
			var id = $(window).data('timer_id');
			if (!id) {
				id = 1;
			}
			else {
				id += 1;
			}
			$(window).data('timer_id', id);
			
			return id;
		}
		
		function Timer(second) {
			this.intervalSecond = second;
			this.parsed = false;
			this.parsedSecond = -1;
			this.timer = null;
			this._callbacks = [];
			this._callbacksIndex = [];
			this._id = generateTimerId();
		}
		
		// 注入定时器回调函数
		Timer.prototype.pushCallback = function(cb, context, fnName) {
			context || (context = this);
			fnName || (fnName = getFnName(cb));
			var args = [].slice.apply(arguments, [2]);
			var bindCb = function () { return cb.apply(context, args);};
			this._callbacks.push(bindCb);
			this._callbacksIndex.push(fnName);
		};
		
		// 注入定时器回调函数 - 重复注入函数情况下 之前注入会被覆盖掉
		Timer.prototype.onceCallback = function (cb, context, fnName) {
			// 先删除
			this.dropCallback(cb, fnName);
			// 再重新压入函数堆栈
			this.pushCallback(cb, context, fnName);
		};
		
		Timer.prototype.dropCallback = function (fnName, fnName2) {
			if (typeof fnName == 'function') fnName = getFnName(fnName);
			if (fnName2 ) fnName = fnName2;
			
			var self = this;
			$.each(this._callbacksIndex, function (index, name) {
				if (fnName == name) {
					self._callbacks.splice(index, 1);
					self._callbacksIndex.splice(index, 1);
				}
			});
		};
		
		// 事件定时器回调函数
		Timer.prototype._callback = function () {
			if (this.parsed && this.parsedSecond >= 0) {
				this.parsedSecond -=  1;
				return;
			}
			// 目前简单执行方法
			$.each(this._callbacks, function (index, cb) {
				if (typeof cb == 'function')  {
					cb();
				}
				else {
					console.log(['cb', cb, index]);
				}
			});
		};
		
		// 开启定时器
		Timer.prototype.start = function() {
			this.parsed = true;
			if (!this.timer) {
				var self = this;
				this.timer = setInterval(function () {
					return self._callback.apply(self);
				}, this.intervalSecond);
			}
			this.parsed = false;
		};
		
		// 暂停 秒
		Timer.prototype.parse = function(second) {
			second || (second = 1000000 );
			this.parsed = true;
			this.parsedSecond = second;
		};
		
		// 释放全局定时器
		Timer.prototype.destroy = function() {
			clearInterval(this.timer);
		};
		
		// 1 秒执行一次的 事件器
		return new Timer(1000);
	})();
	
	window.timer.start();
})();


// 数据Hook - 当数据发生变化后触发事件
(function ($) {
	function _DataHooker(contextName, fields) {
		this.emiter = $(window);
		this.prefixEventName = 'event:' + contextName;
		this.contextName = contextName;
		this.fields = fields;
	};
	_DataHooker.prototype = {
			getFieldEventName: function (field) {
				return this.prefixEventName+':'+ field;
			},
			on: function (field, cb, context) {
				this.emiter.on(this.getFieldEventName(field), function () {
					var args = [].slice.apply(arguments, [1]);
					context || (context = arguments[0]);
					cb.apply(context, args);
				});
			},
			update: function(data) {
				for (var field in this.fields) {
					var value =  data[field];
					if (typeof value != 'undefined') {
						this.emiter.trigger(this.getFieldEventName(field), [ data[field], data ]);
					}
				}
			}
	};
	
	// 暴漏接口
	var _hookers = {},
		_fieldMappings = {};
	DataHooker = function (contextName) {
		if (typeof _fieldMappings[contextName] == 'undefined') throw "未注册";
		if (typeof _hookers[contextName] == 'undefined') {
			_hookers[contextName] = new _DataHooker(contextName, _fieldMappings[contextName]);
		}
		return _hookers[contextName];
	};
	
	DataHooker.registerContext = function (name, template) {
		_fieldMappings[name] = template;
	};
	
	// 预定义Hooker
	DataHooker.lottoGetGameInfo = 'lottoGetGameInfo';
	DataHooker.registerContext(DataHooker.lottoGetGameInfo, {
		balance: 'number', 
		betDesc: 'object',
		cp_type_code: 'string',
		status: 'int',
		timeMap: 'object',
		cp_type_code: '',
	});
	
	window.DataHooker = DataHooker;
	
	// 预创建一些常用的 hooker
	window.HookerLottoGetGameInfo = DataHooker(DataHooker.lottoGetGameInfo);
	
})(jQuery);

// 时时彩导航倒计时
(function ($) {
	$.formatSecondToTime = function (second) {
		var day = ~~(second / ( 24 * 60 * 60) );
		second = second - day * 24 * 60 * 60 ;
		var hour = ~~ (second / ( 60 * 60 ) );
		second = second - hour * 60 * 60;
		var min = ~~ (second / 60);
		second = second - min * 60;
		
		// 天
		if (day > 0) day = day + '天';
		else day = '';
		
		// 小时
		if (hour > 0) hour = hour + '小时';
		else hour = '';
		
		// 分钟
		if (min < 10 && min > 0 ) {
			min = '0' + min + ':';
		}
		else if (min <= 0) {
			min = '00' + ':';
		}
		else {
			min += ':';
		}
		
		// 秒
		if (second < 10) second = '0' + second;
		return day + hour + ' ' + min  + second;
	};
	
	$.formatSecondToChineseTime = function (second) {
		var day = ~~(second / ( 24 * 60 * 60) );
		second = second - day * 24 * 60 * 60 ;
		var hour = ~~ (second / ( 60 * 60 ) );
		second = second - hour * 60 * 60;
		var min = ~~ (second / 60);
		second = second - min * 60;
		
		// 天
		if (day > 0) day = day + '天';
		else day = '';
		
		// 小时
		if (hour > 0) hour = hour + '小时';
		else hour = '';
		
		// 分钟
		if (min > 0) min += '分';
		else min = '';
		
		// 秒
		second = second + '秒';
		return day + hour + ' ' + min  + second;
	};

	
}	
)(jQuery);
