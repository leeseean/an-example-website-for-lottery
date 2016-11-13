angular.module('AppController')
	.controller('WithdrawalController', ['$rootScope', '$scope', '$filter', 'CommomService', '$timeout',
	              function ($rootScope, $scope, $filter, CommomService, $timeout) {
		$rootScope.title = '收款表单';
		$scope.money = 1000;
		$scope.withdrawalPwd = '';
		$scope.submitting = false;
		$scope.submitWithdrawal = function () {
			if ($scope.submitting) return;
			$scope.submitting = true;
			if ($scope.money * 1 < 100) {
				$rootScope.alert('最小金额100元');
				$scope.submitting = false;
			}
			else if ($scope.withdrawalPwd.trim().length != 4) {
				$rootScope.alert('请正确输入4位取款密码');
				$scope.submitting = false;
			}
			else {
				var numPwd = $scope.withdrawalPwd * 1;
				if (isNaN(numPwd)) {
					$rootScope.alert('取款密码必须为数字');
					$scope.submitting = false;
				}
				else {
					CommomService
						.saveWithdrawal($scope.money, $scope.withdrawalPwd.trim())
						.then(function (res) {
							var dialog = $rootScope.alert(res['data']['msg']);
							$scope.submitting = false;
							dialog.closePromise.then(function () {
								$rootScope.go('home');
							});
						});
				}
			}
		};
	}]); 