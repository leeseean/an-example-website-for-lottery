angular.module('AppController')
	.controller('UserEduLogController', ['$rootScope', '$scope', '$filter', '$stateParams', 'CommomService',
	              function ($rootScope, $scope, $filter, $stateParams, CommomService) {
		
		$rootScope.title = '转换记录';
		if ($rootScope.edu == undefined) {
			return $rootScope.go('home');
		}
		$scope.eduLogs = [];
		function init() {
			CommomService.getEduRecords($rootScope.edu.eduType, $rootScope.edu.flatName, $rootScope.edu.beginTime, $rootScope.edu.endTime).then(function (res) {
				if (!res['data']['rs']) {
					$rootScope.alert(res['data']['msg']);
				}
				$rootScope.pager.init(res['data']['datas']);
				$scope.eduLogs = res['data']['datas']['result'];
			});
		}
		if ($rootScope.edu.beginTime) {
			init();
		}
		$scope.dataHandler = function () {
			init();
		};
	}]);