angular.module('AppController')
	.controller('DepositController', ['$rootScope', '$scope', '$filter', 'CommomService', 'ngDialog', '$timeout',
	              function ($rootScope, $scope, $filter, CommomService, ngDialog,  $timeout) {
		$scope.bankList = [];
		$scope.hkCompanyBank = {};
		
		$rootScope.title = '入款表单';
		
		$scope.transferFrom = [{
			id: '银行柜台',
			value: '银行柜台',
			label: '银行柜台'
		},{
			id: 'ATM现金',
			value: 'ATM现金',
			label: 'ATM现金'
		},{
			id: 'ATM卡转',
			value: 'ATM卡转',
			label: 'ATM卡转'
		},{
			id: '网银转账',
			value: '网银转账',
			label: '网银转账'
		},{
			id: '手机银行转帐',
			value: '手机银行转帐',
			label: '手机银行转帐'
		},{
			id: '微信转账',
			value: '微信转账',
			label: '微信转账'
		},{
			id: '支付宝转账',
			value: '支付宝转账',
			label: '支付宝转账'
		}];
		
		$scope.mins = [];
		$scope.min = '00';
		for (var i = 0; i < 61; i++) {
			$scope.mins.push({
				id: i,
				value: i < 10 ? "0"+i: ""+i,
				label: i < 10 ? "0"+i+'分': ""+i+'分',
			});
		}
		$scope.hours = [];
		$scope.hour = '00';
		for (var i = 0; i < 25; i++) {
			$scope.hours.push({
				id: i,
				value: i < 10 ? "0"+i: ""+i,
				label: i < 10 ? "0"+i+'小时': ""+i+'小时',
			});
		}
		
		$scope.hkType = $scope.transferFrom[0];
		$scope.createTime = $rootScope.today;
		
		CommomService.getBankList().then(function (res) {
			if (!res['data']['rs']) {
				$rootScope.alert(res['data']['msg']);
			}
			var data = res['data']['datas'];
			angular.forEach(data, function(item) {
				$scope.bankList.push({
					label: item.bankType + '-' + item.bankUser,
					value: item.payNo,
					id: item.bankCard
				});

				$scope.hkCompanyBank = $scope.bankList[0];
			});
		});
		
		$scope.submitDepositForm = function () {
			var money = $scope.hkMoney*1;
			if (money < 100 || isNaN(money)) {
				$rootScope.alert('充值金额不能少于100元');
			}
			else if ($scope.hkUserName == undefined || $scope.hkUserName.length < 1) {
				$rootScope.alert('请输入汇款人姓名');
			} 
			else {
				CommomService.postDepositMessage($scope.hkMoney, 
					$scope.hkCompanyBank['label'], 
					$scope.createTime,
					$scope.hkType['id'],
					$scope.hkUserName,
					$scope.hkCompanyBank.value,
					$scope.hour,
					$scope.min).then(function (res) {
						$rootScope.alert(res['data']['msg']).closePromise.then(function () {
							$rootScope.go('home');
						});
					});
			}
		};
	}]);