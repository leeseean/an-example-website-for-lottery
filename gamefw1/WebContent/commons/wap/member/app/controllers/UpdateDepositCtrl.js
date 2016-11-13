angular.module('AppController')
	.controller('UpdateDepositController', ['$rootScope', '$scope', '$filter', 'CommomService', '$timeout',
	              function ($rootScope, $scope, $filter, CommomService, $timeout) {
		$scope.oldpwd = '';
		$scope.newpwd= '';
		$scope.confirmpwd = '';
		$scope.submitUpdatePwd = function () {
			
			if ($scope.oldpwd != '' && $scope.newpwd !='' && $scope.confirmpwd != '') {
				if ($scope.newpwd != $scope.confirmpwd){
					$rootScope.alert('确认密码不正确');
				}
				else if ($scope.oldpwd.length < 4) {
					$rootScope.alert('密码长度不能小于4');
				}
				else if ($scope.oldpwd == $scope.newpwd){
					$rootScope.alert('新密码不能和旧密码一样');
				}
				else {
					var newpwd = $scope.newpwd * 1;
					if (typeof newpwd != 'number' || isNaN(newpwd)) {
						$rootScope.alert('取款密码只能是数字');
					}
					else {CommomService
						.updateDepositPwd($scope.oldpwd, $scope.newpwd, $scope.confirmpwd)
						.then(function (res) {
							$rootScope.alert(res['data']['msg']);
						if (res['data']['rs']) {
							$timeout(function () {
								$rootScope.back();
							}, 900);
						}
					});
					}
					
				}
			}
		};
	}]);