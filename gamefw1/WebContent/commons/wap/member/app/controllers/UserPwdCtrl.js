angular.module('AppController')
	.controller('UserPwdController', ['$rootScope', '$scope', '$filter', 'CommomService',
	              function ($rootScope, $scope, $filter, CommomService) {
		$rootScope.title = '账户安全';
		$scope.oldpwd = '';
		$scope.newpwd= '';
		$scope.confirmpwd = '';
		$scope.submitUpdatePwd = function () {
			if ($scope.oldpwd != '' && $scope.newpwd !='' && $scope.confirmpwd != '') {
				console.log('修改密码');
				if ($scope.newpwd != $scope.confirmpwd){
					$rootScope.alert('确认密码不正确');
				}
				else if ($scope.newpwd.length < 6) {
					$rootScope.alert('密码长度不能小于6');
				}
				else if ($scope.oldpwd == $scope.newpwd){
					$rootScope.alert('新密码不能和旧密码一样');
				}
				else {
					CommomService
						.updatePwd($scope.oldpwd, $scope.newpwd, $scope.confirmpwd)
						.then(function (res) {
							$rootScope.alert(res['data']['msg']);
						if (res['data']['rs']) {
							$rootScope.back();
						}
					});
				}
			}
		};
	}]);