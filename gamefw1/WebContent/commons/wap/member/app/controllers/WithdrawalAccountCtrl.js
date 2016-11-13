angular.module('AppController')
	.controller('WithDrawalAccountController', ['$rootScope', '$scope', '$filter', 'CommomService',
	              function ($rootScope, $scope, $filter, CommomService) {
		$rootScope.title = '收款账户';
		$scope.userBank = {};
		CommomService.accountInfo().then(function(res) {
			if (!res['data']['rs']) {
				$rootScope.alert(res['data']['msg']);
			}
			else {
				if (0 >= res['data']['datas']['userBankCard'].trim().length) {
					$scope.userBank = false
				}
				else {
					$scope.userBank = res['data']['datas'];
				}
			}
		});
	}]); 