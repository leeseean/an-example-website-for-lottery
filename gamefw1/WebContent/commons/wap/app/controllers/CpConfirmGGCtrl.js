angular.module('AppController')
	.controller('CpConfirmGGController', ['$rootScope', '$scope', '$stateParams' , 'CaipiaoPeiyu', 'CaipiaoCreateOrder', '$state', 'ngDialog',
	                                  function ($rootScope, $scope, $stateParams, CaipiaoPeiyu, CaipiaoCreateOrder, $state, ngDialog) {
		
		if (typeof $rootScope.caipiaoInfo == 'undefined') {
			$state.go('home');
			return ;
		}
		
		$rootScope.title = "投注确认";
		$scope.xzje = $rootScope.xzje;
		
		$scope.minBet = $rootScope.caipiaoInfo.minBetMap[$rootScope.code2.toUpperCase()];
		$scope.maxBet = $rootScope.caipiaoInfo.maxBetMap[$rootScope.code2.toUpperCase()];
		
		$scope.caipiaoItems = CaipiaoCreateOrder.getItems();
		
		$scope.createOrder = function () {
			for (var index in $scope.caipiaoItems) {
				$scope.caipiaoItems[index]['xzje'] = $scope.xzje;
			}
			
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
				.createOrder($rootScope.code, 'cl')
				.then(function (res) {
					$rootScope.disabled = false;
					ngDialog.open({
						template: '<p>'+res['data']+'</p>',
					});
					$rootScope.back();
				});
		};
		
		$scope.removeCaipiaoItem = function(index) {
			$scope.caipiaoItems.splice(index, 1);
			if ($scope.caipiaoItems.length <= 0) {
				$rootScope.back();
			}
		};
		
	}]);