angular.module('AppController')
	.controller('ConversionController', ['$rootScope', '$scope', '$filter', 'ngDialog', 'CommomService',
	              function ($rootScope, $scope, $filter, ngDialog, CommomService) {
		
		$rootScope.title = '额度转换';
		$scope.dialog = {
			title: '',
			subtitle: '',
			type: '',
			money: ''
		};
		$scope.money_ag = '';
		$scope.money_mg = '';
		$scope.money_pt = '';
		$scope.money_os = '';
		$scope.money_bbin = '';
		$scope.money_sb = '';
		$scope.money_ttg = '';
		$scope.money_hg = '';
		$scope.money_sa = '';
		$scope.money_ds = '';
		$scope.submitConversionIn = function (type, money) {
			if (money*1 < 10 ) {
				$rootScope.alert("转入额度不能少于10元");
			}
			else {
				ngDialog.closeAll();
				$rootScope.alert("额度转换中...");
				CommomService.flatDoEduTo(money, type).then(function (res) {
					$scope.view(type);
					ngDialog.closeAll();
					$rootScope.alert(res['data']['msg']);
					$scope.dialog.money = '';
				});
			}
		};
		$scope.submitConversionOut = function (type, money) {
			if (money*1 < 10 ) {
				$rootScope.alert("转入额度不能少于10元");
			}
			else {
				ngDialog.closeAll();
				$rootScope.alert("额度转换中...");
				CommomService.flatDoEduFrom(money, type).then(function (res) {
					$scope.view(type);
					ngDialog.closeAll();
					$rootScope.alert(res['data']['msg']);
					$scope.dialog.money = '';
				});
			}
		};
		$scope.conversionIn = function (type) {
			$scope.dialog.title = type.toUpperCase() + "平台转入";
			$scope.dialog.subtitle = "从总平台转入到" + type.toUpperCase() + "平台";
			$scope.dialog.type = type;
			ngDialog.open({
				template: templateBaseURI + '/dialog/conversion-in.html',
				plain: false,
				width: '90%',
				scope: $scope,
				className: 'ng-dialog-conversion-in mh-ngdialog-theme'
			});
		};
		
		$scope.conversionOut = function (type) {
			$scope.dialog.title = type.toUpperCase() + "平台转出";
			$scope.dialog.subtitle = "从" + type.toUpperCase() + "平台转出到总平台";
			$scope.dialog.type = type;
			ngDialog.open({
				template: templateBaseURI + '/dialog/conversion-out.html',
				plain: false,
				width: '90%',
				scope: $scope,
				className: 'ng-dialog-conversion-in mh-ngdialog-theme'
			});
		};
		
		$scope.view = function (type) {
			CommomService.getBlanceMoney(type).then(function (res) {
				if (res['data']['rs']) {
					$scope['money_'+type] = res['data']['datas']['flatMoney'];
					$rootScope.account.balance = res['data']['datas']['accountMoney'];
				}
				else {
					$rootScope.alert(res['data']['msg']);
				}
			});
		};
		
	}]); 