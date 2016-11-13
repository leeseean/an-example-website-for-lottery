angular.module('AppController')
	.controller('CpKjCenterController', ['$rootScope', '$scope', 'CaipiaoKaijiangHistory', '$stateParams',
	                                     function ($rootScope, $scope, CaipiaoKaijiangHistory, $stateParams) {
		
		$rootScope.title = '开奖中心';
		$scope.crtGameKaijiangList = {};
		$scope.kaijiangList = {};
		$scope.gameCode = $stateParams.game_code;
		$scope.totalPage = 1;
		$scope.pageSize = 25;
		$scope.pageNo = 1;
		$scope.historyItems = [];
		$scope.historyCurrentItems = [];
		
		function nextHistoryItems() {
			return $scope.historyItems.slice( ($scope.pageNo - 1)*$scope.pageSize, $scope.pageSize * $scope.pageNo );
		}
		
		$scope.prevPage = function () {
			if ($scope.pageNo < 1) return ;
			$scope.pageNo --;
			$scope.historyCurrentItems = nextHistoryItems();
		}
		
		$scope.nextPage = function () {
			if ($scope.pageNo > $scope.totalPage) return ;
			$scope.pageNo ++;
			$scope.historyCurrentItems = nextHistoryItems()
		}
		
		// 详情
		if ($stateParams.game_code) {
			CaipiaoKaijiangHistory.getGameResults($stateParams.game_code).then(function (res) {
				if (typeof res['data'] == 'object'){
					$rootScope.title = res['data']['data']['current']['game_name'];
					var codes = res['data']['data']['current']['opencode'].split(',');
					var total = 0;
					for (var index in codes) {
						total += codes[index]*1;
					}
					$scope.total = total;
					
					$scope.historyItems = res['data']['data']['history'];
					$scope.crtGameKaijiangList = res['data']['data'];
					
					$scope.totalPage = $scope.historyItems % $scope.pageSize == 0 ? $scope.historyItems.length / $scope.pageSize : ~~($scope.historyItems.length / $scope.pageSize) + 1;
					
					$scope.historyCurrentItems = nextHistoryItems();
					
					if ($scope.gameCode == 'HK6') {
						$scope.hkCodes = [ codes.splice(0, codes.length - 1), codes.splice(codes.length - 1) ];
					}
				}
				else $rootScope.redirectToLogin();
			}, $rootScope.redirectToLogin);
		}
		// 列表
		else {
			CaipiaoKaijiangHistory.getCurrentResults().then(function (res) {
				if (typeof res['data'] == 'object') {
					$scope.kaijiangList = res['data']['data'];
				}
				else 
					$rootScope.redirectToLogin();
			}, $rootScope.redirectToLogin);
		}
		
	}]);