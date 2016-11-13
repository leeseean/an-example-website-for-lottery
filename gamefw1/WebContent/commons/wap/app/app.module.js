angular.isEmpty = function (obj) {
	if (angular.isArray(obj)) return obj.length <= 0 ;
	if (angular.isObject(obj)) {
		var keys = Object.keys(obj);
		return keys.length == 0;
	}
	if (angular.isString(obj)) return obj.length == 0;
	if (angular.isNumber(obj)) return obj == 0;
	
	return false;
}

var app = angular.module('App', ['ui.router', 'AppDirective', 'AppController', 'AppService', 'AppFilter', 'AppTemplate', 'ngDialog'])
	.config(function($stateProvider, $urlRouterProvider, $httpProvider, ngDialogProvider) {
		
		$httpProvider.defaults.cache = true;
		
		ngDialogProvider.setDefaults({
			plain: true
		});
		
		$urlRouterProvider.otherwise('/home');
		$stateProvider
			.state('home', {
				url: '/home',
				controller: 'HomeController',
				templateUrl: templateBaseURI + '/home.html',
			})
			.state('caipiao', {
				url: '/caipiao',
				templateUrl: templateBaseURI + '/caipiao.html',
				controller: function ($stateParams, $location, $state) {
					if ($location.path() == '/caipiao') {
						$state.go('home');
					}
				}
			})
			.state('caipiao.history', {
				url: '/history',
				views: {
					'caipiao_page@caipiao': {
						templateUrl: templateBaseURI + '/history/caipiao_orders.html',
						controller: 'CpHistoryController',
					}
				}
			})
			.state('caipiao.kaijiang', {
				url: '/kaijiang',
				views: {
					'caipiao_page@caipiao': {
						templateUrl: templateBaseURI + '/history/caipiao_kaijiang.html',
						controller: 'CpKjCenterController'
					}
				}
			})
			.state('caipiao.kaijiang.detail', {
				url: '/:game_code',
				views:{
					'caipiao_page@caipiao': {
						templateUrl: templateBaseURI + '/history/caipiao_kaijiang_detail.html',
						controller: 'CpKjCenterController'
					}
				}
			})
			.state('caipiao.play', {
				url: '/game/:type_code/:game_code', 
				views: {
					'caipiao_pankou@caipiao': {
						templateUrl: function ($stateParams) {
							var type_code = $stateParams.type_code;
							return templateBaseURI + '/caipiao/'+type_code+'/'+type_code+'.html'
						},
						controller: function ($stateParams, $scope) {
							$scope.game_code = $stateParams.game_code;
							$scope.type_code = $stateParams.type_code;
						}
					},
				}
			})
			.state('caipiao.play.pankou', {
				url: '/pankou/:pankou/:type',
				views: {
					'caipiao_pankou@caipiao': {
						templateUrl: function ($stateParams) {
							// 加拿大28和幸运28属于PC蛋蛋
							var type_code = $stateParams.type_code;
							return templateBaseURI + '/caipiao/'+type_code+'/pk_' + $stateParams.pankou + '.html';
						},
						controllerProvider: function ($stateParams) {
							var type_code = $stateParams.type_code;
							console.log(['Controller', 'Cp'+type_code+'Controller']);
							return 'Cp'+type_code+'Controller';
						}
					}
				}
			})
			.state('caipiao.confirm', {
				url: '/confirm',
				views:{
					'caipiao_confirm@caipiao': {
						templateUrl: templateBaseURI + '/caipiao/confirm.html',
						controller: 'CpConfirmController',
					}
				},
				params: {
					'flag': 'normal',
				}
			})
			.state('caipiao.confirm.hx', {
				url: '/hx',
				views: {
					'caipiao_confirm@caipiao': {
						templateUrl: templateBaseURI + '/caipiao/confirm_hx.html',
						controller: 'CpConfirmHxController',
					}
				},
				params: {
					'flag': 'mul',
				}
			})
			.state('caipiao.confirm.gg', {
				url: '/gg',
				views: {
					'caipiao_confirm@caipiao': {
						templateUrl: templateBaseURI + '/caipiao/confirm_gg.html',
						controller: 'CpConfirmGGController',
					}
				},
				params: {
					'flag': 'cl',
				}
			})
			.state('404', {
				url: '/404',
				templateUrl: templateBaseURI + '/404.html'
			});
	})
	.run(function ($rootScope, $window, $interval, $cacheFactory, $sce, $http) {
		
		FastClick.attach(document.body);
		
		$interval(function () {
			$cacheFactory.get('$http').removeAll();
		}, 1000);
		
		function loadAccountInfo() {
			var injector = angular.injector(['ng']);
			var $http = injector.get('$http');
			$http.get(baseURI + '/m/userInfo').then(function (res) {
				try {
					var data = angular.fromJson(res['data']['data']);
					if (typeof data == 'undefined') return ;
					$rootScope.account = data;
				}
				catch (e) {
					//
				}
			});			
		}
		
		// 全局函数和变量
		angular.extend($rootScope, {
			templateURL: function (templateURI) {
				return templateBaseURI + templateURI
			},
			staticURL: function (uri) {
				return staticURI + uri;
			},
			title: '彩票中心',
			xzje: 10,
			setTitle: function (title) {
				$rootScope.title = title;
				window.document.title = title;
			},
			back: function () {
				$window.history.back();
			},
			currentNav: 'home',
			scrollToTop: function () {
				$window.scrollTo(0, 0);
			},
			isLogged: function () {
				return $rootScope.account.user_name != '';
			},
			redirectToLogin: function () {
				$window.location.href = baseURI + $rootScope.loginURL;
			},
			redirectToRegister: function () {
				$window.location.href = baseURI + '/m/register/goRegister';
			},
			range: function (start, end, step) {
				step = step || 1;
				var ids = [];
				for (var i = start; i < end; i += step) {
					ids.push(i);
				}
				return ids;
			},
			renderHTML: function (htmlCode) {
				if (typeof htmlCode != 'undefined') {
					return $sce.trustAsHtml(htmlCode.toString());
				} 
				return '';
			},
			goMainHome: function() {
				window.location.href=baseURI+"/m/main";
			},
			logout: function () {
				$http({
					    url: baseURI+"/loginOut",
					    type: "POST",
						headers: {
							'Content-Type': "application/x-www-form-urlencoded; charset=UTF-8",
						}
					}).then(function (res) {
						var obj = res['data'];
				    	if(obj.rs){
				    		window.location.href=baseURI+"/m/main";
				    	}else{
				    		 alert("用户退出系统出错~");
				    	}
					}, function () {
						alert("用户退出系统出错~");
					});
			},
			loadAccountInfo: loadAccountInfo,
			baseURI: baseURI,
			registerURL: '/m/register/goRegister',
			loginURL: '/m/main/index',
		});
		
		$rootScope.loadAccountInfo();
		
		$rootScope.account = {
				user_name: '',
				balance: ''
		};
		
		$rootScope.$watch('title', function (newval, oldval) {
			$rootScope.setTitle(newval);
		});
		
		$rootScope.$on('$stateChangeSuccess', function () {
			$rootScope.scrollToTop();
		});
		
	});

