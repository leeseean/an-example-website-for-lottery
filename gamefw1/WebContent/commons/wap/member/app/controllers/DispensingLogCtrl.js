angular.module('AppController')
	.controller('DispensingLogController', ['$rootScope', '$scope', '$filter', '$stateParams', 'CommomService',
	              function ($rootScope, $scope, $filter, $stateParams, CommomService) {
		$scope.logs = [];
		$rootScope.title = '出款记录';
		$scope.type = $stateParams['type'];
		if ($scope.type == 'in') {
			$rootScope.title = '入款记录';
		}
		function init() {
			// 出款记录
			if ($stateParams['type'] == 'out') {
				CommomService.getTransferOutLog($stateParams['hkType'], $stateParams['beginTime'], $stateParams['endTime']).then(function (res) {
					if (!res['data']['rs']) {
						$rootScope.alert(res['data']['msg']);
					}
					$rootScope.pager.init(res['data']['datas']);
					$scope.logs = res['data']['datas']['result'];
				});
			}
			// 入款记录
			else if ($stateParams['type'] == 'in') {
				CommomService.getTransferInLog($stateParams['hkType'], $stateParams['beginTime'], $stateParams['endTime']).then(function (res) {
					if (!res['data']['rs']) {
						$rootScope.alert(res['data']['msg']);
					}
					$rootScope.pager.init(res['data']['datas']);
					$scope.logs = res['data']['datas']['result'];
				});
			}
		}
		init();
		
		$scope.dataHandler = function () {
			init();
		};
	}]);