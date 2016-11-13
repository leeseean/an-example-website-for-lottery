angular.module('AppController')
	.controller('OrderLogController', ['$rootScope', '$scope', '$filter', '$stateParams', 'CommomService',
	              function ($rootScope, $scope, $filter, $stateParams, CommomService) {

		$rootScope.title = "注单查询";
		if ($rootScope.order == undefined) {
			return $rootScope.go('order');
		}
		$scope.orderLogs = [];
		($scope.init = function init() {
			if ($rootScope.order.platform == 'sport') {
				CommomService.getSportOrder($rootScope.order.type, $rootScope.order.status, $rootScope.order.beginTime, $rootScope.order.endTime).then(function(res) {
					if (!res['data']['rs']) {
						$rootScope.alert(res['data']['msg']);
					}
					$rootScope.pager.init(res['data']['datas']);
					$scope.orderLogs = res['data']['datas']['result'];
					angular.forEach($scope.orderLogs, function (orderLog, i1) {
						for (var i = 0; i < orderLog.details.length; i++) {
							orderLog['details'][i]['score'] = angular.fromJson(orderLog['details'][i]['tmp1']);
						}
						console.log(orderLog);
					});
				});
			}
			else {
				CommomService.getSlotOrder($rootScope.order.platform, $rootScope.order.beginTime, $rootScope.order.endTime).then(function(res) {
					if (!res['data']['rs']) {
						$rootScope.alert(res['data']['msg']);
					}
					$rootScope.pager.init(res['data']['datas']);
					$scope.orderLogs = res['data']['datas']['result'];
				});
			}
		})();
		 
	}]);