angular.module('AppController')
	.controller('ThirdpayController', ['$rootScope', '$scope', '$filter', '$stateParams', 'CommomService',
	              function ($rootScope, $scope, $filter, $stateParams, CommomService) {

		console.log($stateParams);
		$rootScope.title = "扫码支付";
		$scope.thirdPay = true;
		if($stateParams['payNo']){
			localStorage.setItem('payNo', $stateParams['payNo'])
		};
		if($stateParams['type']){
			localStorage.setItem('type', $stateParams['type'])
		};
		if($stateParams['payRname']){
			localStorage.setItem('payRname', $stateParams['payRname'])
		};
		if($stateParams['payNname']){
			localStorage.setItem('payNname', $stateParams['payNname'])
		};
		var thirdType = $scope.thirdType = localStorage.getItem('type') == 'wechat' ? 1: 2;
		$scope.payRname = localStorage.getItem('payRname');
		$scope.payNname = localStorage.getItem('payNname');
		$scope.payNo = localStorage.getItem('payNo');
		// CommomService.getThirdpayList().then(function (res) {
		// 	if (!res['data']['rs']) {
		// 		$rootScope.alert(res['data']['msg']);
		// 	}
		// 	angular.forEach(res['data']['datas'], function (data) {
		// 		if (data['payType'] == thirdType) {
		// 			$scope.thirdPay = data;
		// 		}
		// 	});
		// });
		
	}]);