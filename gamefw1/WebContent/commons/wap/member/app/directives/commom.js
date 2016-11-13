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
	.directive('pager', ['$rootScope', function ($rootScope){
		return {
			restrict: 'E',
			scope: {
				loadData: '&',
			},
			link: function ($scope, element, attrs) {
				$scope.pager = $rootScope.pager;
				$scope.clickable = true;
				$rootScope.$on('pagerLoad', function () {
					$scope.haveNext = $rootScope.pager.haveNext();
					$scope.havePrev = $rootScope.pager.havePre();
					$scope.clickable = true;
				});
				$scope.next = function (){
					if ($rootScope.pager.haveNext() && $scope.clickable) {
						$rootScope.pager.pageNo($rootScope.pager.nextPageNo());
						$scope.loadData();
						$scope.clickable = false;
					}
				};
				$scope.prev = function () {
					if ($rootScope.pager.havePre() && $scope.clickable) {
						$rootScope.pager.pageNo($rootScope.pager.prePageNo());
						$scope.loadData();
						$scope.clickable = false;
					}
				};
			},
			templateUrl: templateBaseURI + '/directives/pager.html',
		};
	}])
	.directive('setClassWhenAtBottom', ['$window', function($window) {
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











