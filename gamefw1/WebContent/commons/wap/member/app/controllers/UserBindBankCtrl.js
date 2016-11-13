angular.module('AppController')
	.controller('UserBindBankController', ['$rootScope', '$scope', '$filter', 'CommomService', '$timeout',
	              function ($rootScope, $scope, $filter, CommomService, $timeout) {
		$rootScope.title = '设置银行卡';
		$scope.allowBankes = [{
			id: 1,
			label: '中国银行',
			value: '中国银行',
		}, {
			id: 2,
			label: '工商银行',
			value: '工商银行',
		}, {
			id: 3,
			label: '农业银行',
			value: '农业银行',
		}, {
			id: 4,
			label: '建设银行',
			value: '建设银行',
		}, {
			id: 5,
			label: '邮政银行',
			value: '邮政银行',
		}, {
			id: 6,
			label: '招商银行',
			value: '招商银行',
		}, {
			id: 7,
			label: '民生银行',
			value: '民生银行',
		}, {
			id: 8,
			label: '兴业银行',
			value: '兴业银行',
		}, {
			id: 9,
			label: '中信银行',
			value: '中信银行',
		}, {
			id: 10,
			label: '交通银行',
			value: '交通银行',
		}, {
			id: 11,
			label: '华厦银行',
			value: '华厦银行',
		}, {
			id: 12,
			label: '广发银行',
			value: '广发银行',
		}, {
			id: 13,
			label: '光大银行',
			value: '光大银行',
		}, {
			id: 14,
			label: '平安银行',
			value: '平安银行',
		}, {
			id: 15,
			label: '浦发银行',
			value: '浦发银行',
		}, {
			id: 16,
			label: '农村信用社',
			value: '农村信用社',
		}];
		
		$scope.bankType = '中国银行';
		
		$scope.saveBankInfo = function(){ 
			if ($scope.bankType 
					&& $scope.bankCard 
					&& $scope.bankAddress 
					&& $scope.bankWithdrawPwd) {
				if ($scope.bankWithdrawPwd.length != 4){
					$rootScope.alert('取款密码必须是4位有效数字');
				}
				else if (isNaN($scope.bankWithdrawPwd*1)) {
					$rootScope.alert('取款密码必须是数字组成');
				}
				else {
					CommomService.saveBankInfo($scope.bankType, $scope.bankCard, $scope.bankAddress, $scope.bankWithdrawPwd)
						.then(function (res) {
							$rootScope.alert(res['data']['msg']);
							if (res['data']['rs']) {
								$timeout(function () {
									$rootScope.go('home');
								}, 900);
							}
						});
				}
			}
			else {
				$rootScope.alert('资料不完整');
			}
		};

		
	}]);