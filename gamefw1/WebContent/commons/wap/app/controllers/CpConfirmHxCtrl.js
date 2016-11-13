angular.module('AppController')
	.controller('CpConfirmHxController', ['$rootScope', '$scope', '$stateParams' , 'CaipiaoPeiyu', 'CaipiaoCreateOrder', '$state', 'ngDialog',
	                                  function ($rootScope, $scope, $stateParams, CaipiaoPeiyu, CaipiaoCreateOrder, $state, ngDialog) {
		
		if (typeof $rootScope.caipiaoInfo == 'undefined') {
			$state.go('home');
			return ;
		}
		
		$rootScope.title = "投注确认";
		$scope.caipiaoItems = CaipiaoCreateOrder.getItems();
		$scope.xzje = $rootScope.xzje;
		
		$scope.minBet = $rootScope.caipiaoInfo.minBetMap[$rootScope.code2.toUpperCase()];
		$scope.maxBet = $rootScope.caipiaoInfo.maxBetMap[$rootScope.code2.toUpperCase()];
		
		$scope.createOrder = function () {
			var totalBalance = $rootScope.account.balance;
			if ($scope.xzje > totalBalance) {

				ngDialog.open({
					template: '<p>余额不足</p>',
				});
				return ;
			} 
			$rootScope.disabled = true;
			$rootScope.disabledMsg = '订单投注中';
			CaipiaoCreateOrder
				.resetItems()
				.setItem($scope.caipiaoItems)
				.setPrice($scope.xzje)
				.createMultiGroupOrder($rootScope.code.toUpperCase(), $rootScope.code2.toUpperCase(), $rootScope.subType)
				.then(function (res) {
					$rootScope.disabled = false;
					ngDialog.open({
						template: '<p>'+res['data']+'</p>',
					});
					$rootScope.back();
				});
		};
		
	}]);