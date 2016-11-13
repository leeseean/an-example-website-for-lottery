angular.module('AppController')
	.controller('OrderController', ['$rootScope', '$scope', '$filter', 'ngDialog',
	              function ($rootScope, $scope, $filter, ngDialog) {

		$rootScope.title = "注单查询";
		$rootScope.backState = 'home';
		$scope.dialog= {title: 'AG注单查询'};
		$rootScope.order = {};
		$rootScope.order.type = '';
		$rootScope.order.status = '';
		$rootScope.order.beginTime = new Date().toPreWeekInputValue();
		$rootScope.order.endTime = new Date().toDateInputValue();
		$rootScope.order.platform = '';
		$scope.types = [{
			id: -1,
			label: '全部',
			value: ''
		}, {
			id: 'ft',
			label: '足球',
			value: 'ft',
		}, {
			id: 'bk',
			label: '篮球',
			value: 'bk',
		}, {
			id: 'wq',
			label: '网球',
			value: 'wq',
		}, {
			id: 'pq',
			label: '排球',
			value: 'pq',
		}, {
			id: 'ymq',
			label: '羽毛球',
			value: 'ymq',
		}, {
			id: 'bpq',
			label: '兵乓球',
			value: 'bpq',
		}, {
			id: 'bq',
			label: '棒球',
			value: 'bq',
		}];
		$scope.statusArray = [{
			id: '-1',
			label: '全部',
			value: '',
		}, {
			id: '1',
			label: '未结算',
			value: '-2,-1,1',
		}, {
			id: '2',
			label: '已结算',
			value: '2',
		}];
		$scope.openDialog = function (type) {
			var templateUrl = 'order-query-form.html';
			$scope.order.platform = type;
			$scope.dialog.title = type.toUpperCase() + '注单查询';
			if (type == 'sport') {
				templateUrl = 'order-query-sport-form.html';
				$scope.dialog.title = '体育注单查询';
			}
			else if (type == 'sb') {
				$scope.dialog.title = '沙巴注单查询';
			}
			ngDialog.open({
				template: templateBaseURI + '/dialog/' + templateUrl,
				plain: false,
				width: '90%',
				scope: $scope,
				className: 'ng-dialog-order ng-dialog-conversion-in mh-ngdialog-theme'
			});
		};
		 
	}]);