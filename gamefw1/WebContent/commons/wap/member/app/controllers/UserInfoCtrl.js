angular.module('AppController')
	.controller('UserInfoController', ['$rootScope', '$scope', '$filter', 'CommomService',
	              function ($rootScope, $scope, $filter, CommomService) {
		$rootScope.title = '用户资料';
		$scope.userinfo = {};
		CommomService.accountInfo().then(function (res) {
			if (!res['data']['rs']) {
				$rootScope.alert(res['data']['msg']);
			}
			$scope.userinfo = res['data']['datas'];
		});
	}]); 