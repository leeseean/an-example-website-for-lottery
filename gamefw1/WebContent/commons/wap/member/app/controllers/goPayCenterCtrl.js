angular.module('AppController')
	.controller('goPayCenterCtrl', ['$rootScope', '$scope', '$filter', '$stateParams', 'CommomService', '$sce', '$http', '$q', '$document',
		function($rootScope, $scope, $filter, $stateParams, CommomService, $sce, $http, $q, $document) {
			console.log($stateParams);
			$rootScope.title = "支付中心";
			if($stateParams.payType){
				localStorage.setItem('payType', $stateParams.payType)
			};
			if($stateParams.sendParams){
				localStorage.setItem('sendParams', $stateParams.sendParams)
			};
			if($stateParams.payValue){
				localStorage.setItem('payValue', $stateParams.payValue)
			};	
			if($stateParams.pay_url){
				localStorage.setItem('pay_url', $stateParams.pay_url)
			};	
			$scope.sendParams = $stateParams.sendParams;
			$scope.payValue = $stateParams.payValue;
			var payCenterForm = document.getElementById('payCenterForm');
			payCenterForm.attributes.action.value = 'http://'+localStorage.getItem('pay_url');
			payCenterForm[0].value = localStorage.getItem('sendParams');
			payCenterForm[1].value = localStorage.getItem('payValue');
			payCenterForm[2].value = localStorage.getItem('payType');
			// console.dir(document.getElementById('payCenterForm'));
			payCenterForm.submit();
		}
	]);