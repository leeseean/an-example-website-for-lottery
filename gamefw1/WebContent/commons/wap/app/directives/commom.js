angular.module('AppDirective', ['AppService'])
	.directive('appmarquee', [function () {
		return {
			restrict: 'E',
			scope: {
				newsList: '=data'
			},
			link: function ($scope, element, attr, controller){
				$scope.$watch(function () {
					return element.find('ul').html();
				}, function (newval, oldval) {
					var totalWidth = 0;
					angular.forEach(element.find('li'), function (li) {
						totalWidth += li.offsetWidth;
					});
					totalWidth += 100;
					element.find('ul').css({width: totalWidth + 'px'});
				});
			},
			templateUrl: templateBaseURI + ('/parts/marquee.html')
		};
	}])
	.directive('carousel', ['$interval' , '$window' ,function ($interval, $window) {
		return {
			restrict: 'E',
			scope: {
				images: '=images'
			},
			link: function ($scope, element, attr, controller){
				$scope.width = $window.innerWidth;
				$scope.$watch(function () {
					return  element.html();
				}, function (newval, oldval) {
					var imageSrc = element.find('img')[0].src;
					var image = new Image();
					image.onload = function () {
						var width = image.width;
						var height = image.height;
					};
					image.src = imageSrc;
					
					element.find('img').bind('load', function () {
						var swipeEl = element[0].getElementsByClassName('swipe')[0];
						var swiper = Swipe(swipeEl);
						$interval(function () {
							swiper.next();
						}, 3000);
					});
				});
			},
			templateUrl: templateBaseURI + '/parts/carousel.html',
		};
	}])
	.directive('czfilter', ['CaipiaoGameList', function (CaipiaoGameList) {
		return {
			restrict: 'E',
			transclude: true,
			scope: {
				changeHandler: '&change',
				crtgame: '=crtgame'
			},
			link: function ($scope, element, attr, controller) {
				$scope.onChange = function (game_code) {
					var crtgame = {};
					for (var index in $scope.gameList) {
						var game = $scope.gameList[index];
						if (game['game_code'] == game_code) {
							crtgame = game;
						}
					}
					$scope.crtgame = crtgame;
					$scope.changeHandler(crtgame);
				};
				$scope.gameList = {};
				CaipiaoGameList().then(function (res) {
					$scope.gameList = res['data']['data'];
				});
			},
			templateUrl: templateBaseURI + '/parts/caizhong_filter.html',
		};
	}])
	.directive('dayFilter', [function () {
		return {
			restrict: 'E',
			transclude: true,
			scope: {
				changeHandler: '&change',
				dayLabel: '=dayLabel',
				day: '=day'
			},
			link: function ($scope, element, attr, controller) {
				$scope.onChange = function (day) {
					if (day == 0 || day == undefined) {
						$scope.day = 0;
						$scope.dayLabel = '全部';
					}
					else {
						$scope.day = $scope.days[day]['day'];
						$scope.dayLabel = $scope.days[day]['label'];
					}
					$scope.changeHandler(day);
				};
				$scope.days = [ {day: 0, label: '全部'} ,{day: 1, label: '今天'}, {day: 3, label: '三天内'}, {day: 7, label: '一周内'}, {day: 15, label: '半个月内'}, {day: 30, label: '一个月内'} ];
			},
			templateUrl: templateBaseURI + '/parts/day_filter.html',
		};
	}])
	.directive('ball', ['$rootScope',function ($rootScope) {
			
		var colors_default = {
			red: [1,2,7,8,12, 13, 18, 19, 23, 24, 29, 30, 34, 35, 40, 45, 46],
			blue: [3, 4, ,9, 10, 14, 15, 20, 25, 26, 31, 36, 37, 41, 42, 47, 48],
			green: [5, 6, 11, 16, 17, 21, 22, 27, 28, 32, 33, 38, 39, 43, 44, 49]
		};
		var colors_pcegg = {
				gray: [0, 13, 14, 27],
				green: [1, 4, 7, 10, 16, 19, 22, 25],
				blue: [2, 8, 5, 11, 17, 20, 23, 26],
				red: [3, 6, 9, 12, 15, 18, 21, 24],
		};
		var colors_kk = {
				'kk-color1': [1],
				'kk-color2': [2],
				'kk-color3': [3],
				'kk-color4': [4],
				'kk-color5': [5],
				'kk-color6': [6],
				'kk-color7': [7],
				'kk-color8': [8],
				'kk-color9': [9],
				'kk-color10': [10],
		};
		var colors_pcegg_history = {
				'pc-gray': [0, 13, 14, 27],
				'pc-green': [1, 4, 7, 10, 16, 19, 22, 25],
				'pc-blue': [2, 8, 5, 11, 17, 20, 23, 26],
				'pc-red': [3, 6, 9, 12, 15, 18, 21, 24],
		};
		
		function linkFunc (scope, element, attr, controller) {
			var number = parseInt(scope.number);
			var type = scope.type;
			var colors = colors_default;
			if (type == 'pcegg') {
				colors = colors_pcegg;
			}
			else if (type == 'pcegg_history') {
				colors = colors_pcegg_history;
			}
			else if (type == 'kk') {
				colors = colors_kk;
			}
			for (var color in colors) {
				if (colors[color].indexOf(number) != -1) {
					scope.color = color;
					break;
				}
			}
		}
		
		return {
			restrict: 'E',
			transclude: true,
			scope: {
				number: '=number',
				type: '@type'
			},
			compile: function (element, attributes) {
				return function (scope, telem, tattr, ngModelCtrl) {
					scope.$watch('number',function (newval) {
						linkFunc( scope, telem, tattr, ngModelCtrl);
					});
					linkFunc( scope, telem, tattr, ngModelCtrl);
				}
			},
			template: '<span ng-class="[\'ball\', \'ball-\'+color]">{{number < 10 ? number: number}}</span>'
		};
	}])
	.directive('timer', ['$interval' , '$q' , '$rootScope' , 'CaipiaoInfo', function ($interval, $q, $rootScope, CaipiaoInfo) {
		
		
		formatSecondToTime = function (second) {
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
			return day + hour + '' + min  + second;
		};
		
		formatSecondToChineseTime = function (second) {
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
			return day + hour + '' + min  + second;
		};
		
		function refreshCaipiaoInfo() {
			return CaipiaoInfo($rootScope.codeID, $rootScope.code2ID);
		}
		
		return {
			restrict: 'E',
			transclude: false,
			scope: {
				type: '@',
				format: '@'
			},
			link: function ($scope, element, attr, controller) {

				$rootScope.disabled = true;
				$rootScope.disabledMsg = '数据加载中';
				
				$scope.second = 0;
				$rootScope.gameInfo = {};
				
				function reload(cb){
					return refreshCaipiaoInfo().then(function (res) {
						$rootScope.gameInfo = res['data'];
						$rootScope.caipiaoInfo = $rootScope.gameInfo;
					});
				}
				reload();
				
				$rootScope.$watch('gameInfo', function(gameInfo) {
					if (Object.keys(gameInfo).length < 1) {
						return;
					}

					var seconds = gameInfo['timeMap'][$rootScope.code].split('_');
					$rootScope.isWeihu = seconds[2];
					$rootScope.isKaijiang = seconds[3];
					$rootScope.kaijiangZhong = false;
					
					if ($rootScope.isWeihu != 0) {
						$rootScope.disabled = true;
						$rootScope.disabledMsg = '维护中';
					}
					else {
						if ($scope.type == 'fengpan') {
							$scope.second = seconds[1];
						}
						else if ($scope.type == 'kaijiang') {
							$scope.second = seconds[0];
						}
						
						if (seconds[1] <= 0 && seconds[0] > 0 ) {
							$rootScope.kaijiangZhong = true;
						}
					}
				});
				
				// 倒计时结束后 重新加载数据 开启新一轮倒计时
				$scope.$watch('second', function (newval) {
					if (typeof $rootScope.gameInfo.status == 'undefined') return ;
					
					// 开奖后 刷新
					if (newval <= 1 && $scope.type == 'kaijiang') {
						reload();
					}
					
					if (newval <= 1 && $scope.type == 'fengpan') {
						if ($rootScope.isWeihu != 0) {
							$rootScope.disabled = true;
							$rootScope.disabledMsg = '维护中';
						}
						else {
							$rootScope.$emit('fengpan');
							$rootScope.disabled = true;
							$rootScope.disabledMsg = '当前已封盘';
							$rootScope.kaijiangZhong = true;
						}
					}
					
					if (newval > 1 && $scope.type == 'fengpan') {
						$rootScope.disabled = false;
					}
					
					if (newval > 1 && $scope.type == 'kaijiang') {
						$rootScope.disabledMsg = '当前开奖中';
					}
				});
				
				$scope.$on('$destroy', function () {
					if (angular.isDefined(timer1)) {
						$interval.cancel(timer1);
						timer1 = undefined;
					}
					if (angular.isDefined(timer2)) {
						$interval.cancel(timer2);
						timer2 = undefined;
					}
				});
				
				// 1分钟后 自动刷新
				var timer2 = $interval(function () {
					reload();
				}, 1000 * 60);
				
				var format = $scope.format == 'cn' ? formatSecondToChineseTime: formatSecondToTime;
				var timer1 = $interval(function () {
					if ($scope.second > 1) {
						$scope.second -= 1;
						element.html(format($scope.second));
					}
					else {
						if ($scope.type == 'fengpan') {
							if ($rootScope.isWeihu != 0) {
								element.html('维护中');
							}
							else {
								element.html('已封盘');
							}
						}
					}
				}, 1000);
			}
		};
	}]).directive('pk10header',[function(){
		return {
			restrict:'AE',
			templateUrl: templateBaseURI+'/caipiao/headers/pk10_header.html',
			replace: true
		};
	}]).directive('setClassWhenAtBottom', ['$window', function($window) {
		var $win = angular.element($window);
		
		return {
			restrict: 'A',
			link: function ($scope, element, attrs) {
				
				function offset(elem) {
					var rect = elem.getBoundingClientRect();
				    if ( rect.width || rect.height || elem.getClientRects().length ) {
				        var doc = elem.ownerDocument;
				        var win = $window;
				        var docElem = doc.documentElement;

				        return {
				            top: rect.top + win.pageYOffset - docElem.clientTop,
				            left: rect.left + win.pageXOffset - docElem.clientLeft
				        };
				    }
				}
				var topClass = attrs.setClassWhenAtBottom,
				offsetTop = offset(element[0]).top;
				var height = element[0].getBoundingClientRect()['height'];
				
				$win.on('scroll', function (e) {
					if ($window.pageYOffset >= offsetTop) {
					  element.addClass(topClass);
					} else {
					  element.removeClass(topClass);
					}
				});
			}
		}
	}]);











