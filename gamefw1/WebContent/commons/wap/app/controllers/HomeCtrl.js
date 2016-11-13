angular.module('AppController', [])
	.controller('HomeController', ['$rootScope', '$scope', 'CaipiaoGameList', 'CommomService',
	              function ($rootScope, $scope, CaipiaoGameList, CommomService ) {
		$scope.newsList = [];		
		$scope.images = [ $rootScope.staticURL('/misc/slider/slider1.png'), $rootScope.staticURL('/misc/slider/slider2.png')];
		$rootScope.setTitle('彩票中心');
		$scope.isHome = true;
		
		CommomService.messages().then(function (res) {
			var newsList = [];
			for (var index in res['data']['data']) {
				var news = res['data']['data'][index];
				newsList.push('恭喜'+news['user_name']+ '在'+news['game_name']+'中了彩金'+news['win_money']+'元');
			}
			$scope.newsList = newsList;
		});
		
		$scope.gameList = {};
		CaipiaoGameList().then(function (res){
			$scope.gameList = res['data']['data'];
		});
		
	}]);