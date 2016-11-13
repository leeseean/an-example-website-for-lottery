angular.module('AppController')
	.controller('DepositAccountController', ['$rootScope', '$scope', '$filter', 'CommomService',
	              function ($rootScope, $scope, $filter, CommomService) {
		
		$rootScope.title = '入款账户';
		
		$scope.bankList = [];
		$scope.thirdpayList = [];
		
		CommomService.getBankList().then(function (res) {
			$scope.bankList = res['data']['datas'];
		});
		CommomService.getThirdpayList().then(function (res) {
			$scope.thirdpayList = res['data']['datas'];
		});
	}]); 