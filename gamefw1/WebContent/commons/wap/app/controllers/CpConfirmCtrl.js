angular.module('AppController')
	.controller('CpConfirmController', ['$rootScope', '$scope', '$stateParams' , 'CaipiaoPeiyu', 'CaipiaoCreateOrder', '$state', 'ngDialog',
	                                  function ($rootScope, $scope, $stateParams, CaipiaoPeiyu, CaipiaoCreateOrder, $state, ngDialog) {
		
		if (typeof $rootScope.caipiaoInfo == 'undefined') {
			$state.go('home');
			return ;
		}
		
		$rootScope.title = "投注确认";
		$scope.caipiaoItems = CaipiaoCreateOrder.getItems();
		var totalBalance = $rootScope.account.balance;
		
		$scope.minBet = $rootScope.caipiaoInfo.minBetMap[$rootScope.code2.toUpperCase()];
		$scope.maxBet = $rootScope.caipiaoInfo.maxBetMap[$rootScope.code2.toUpperCase()];
		
		$scope.createOrder = function () {
			var totalPrice = 0;
			for (var index in $scope.caipiaoItems) {
				var caipiaoItem = $scope.caipiaoItems[index];
				totalPrice += parseInt(caipiaoItem.xzje);
			}
			if (totalPrice <= 0 ) {
				return;
			}
			if (totalPrice > totalBalance) {
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
				.createOrder($rootScope.code)
				.then(function (res) {
					ngDialog.open({
						template: '<p>'+res['data']+'</p>',
					});
					$rootScope.back();
					$rootScope.disabled = false;
				});
		};
		
		$scope.removeCaipiaoItem = function(index) {
			$scope.caipiaoItems.splice(index, 1);
			if ($scope.caipiaoItems.length <= 0) {
				$rootScope.back();
			}
		};
		
	}]);