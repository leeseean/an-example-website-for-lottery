angular.module('AppController', [])
	.controller('HomeController', ['$rootScope', '$scope', 'ngDialog', '$http',
	              function ($rootScope, $scope, ngDialog, $http) {

		$rootScope.title = '会员中心';
		$scope.dialog = {title: '入款记录'};
		$scope.withdrawLog = {};
		$scope.withdrawLog.hkType = '';
		$scope.beginTime = new Date().toPreWeekInputValue();
		$scope.endTime = new Date().toDateInputValue();
		$scope.hkTypes = [{
			id: -1,
			label: '全部类型',
			value: '',
		}, {
			id: 1,
			label: '公司入款',
			value: 1,
		}, {
			id: 2,
			label: '线上支付',
			value: 2,
		}, {
			id: 3,
			label: '红利赠送',
			value: 3,
		}, {
			id: 5,
			label: '投注返水',
			value: 5,
		}, {
			id: 4,
			label: '后台入款',
			value: 4,
		}, {
			id: 6,
			label: '免费彩金',
			value: 6,
		},{
			id: 7,
			label: '存款赠送',
			value: 7,
		},{
			id: 8,
			label: '存款优惠',
			value: 9,
		}];
		$scope.out_hkTypes = [{
			id: -1,
			label: '全部类型',
			value: '',
		},{
			id: 11,
			label: '会员提款',
			value: 11,
		},{
			id: 12,
			label: '系统扣款',
			value: 12,
		}];
		$scope.openDispensingDialog = function (title, type) {
			$scope.dialog.title = title;
			$scope.dialog.type = type;
			ngDialog.open({
				template: templateBaseURI + '/dialog/dispensing-log.html',
				plain: false,
				scope: $scope,
				width: '90%',
				className: 'ng-dialog-money-log mh-ngdialog-theme'
			});
		};

		$scope.dialog.eduFlatNames = [{
			id: -1,
			label: '全部',
			value: ''
		}, {
			id: 1,
			label: 'AG',
			value: 'ag',
			value: 'ag'
		}, {
			id: 2,
			label: 'MG',
			value: 'mg',
			value: 'mg'
		}, {
			id: 3,
			label: 'OS',
			value: 'os',
			value: 'os'
		}, {
			id: 5,
			label: 'PT',
			value: 'pt',
			label: 'PT',
			value: 'pt'
		}, {
			id: 6,
			label: '沙巴',
			value: 'sb'
		}, {
			id: 7,
			label: 'BBIN',
			value: 'bbin'
		}, {
			id: 8,
			label: 'TTG',
			value: 'ttg'
		}, {
			id: 9,
			label: 'HG',
			value: 'hg'
		}, {
			id: 10,
			label: 'SA',
			value: 'sa'
		}, {
			id: 11,
			label: 'DS',
			value: 'ds'
		}];
		$scope.dialog.eduTypes = [{
			id: -1,
			label: '全部',
			value: ''
		}, {
			id: 1,
			label: '转出',
			value: '1'
		}, {
			id: 2,
			label: '转入',
			value: '2'
		}];
		$rootScope.edu = {
				flatName: '',
				eduType: '',
				beginTime: new Date().toPreWeekInputValue(),
				endTime: new Date().toDateInputValue()
		};
		$scope.openEduHistoryDialog = function () {
			$scope.dialog.title = '转换记录';
			ngDialog.open({
				template: templateBaseURI + '/dialog/eduhistory-form.html',
				plain: false,
				scope: $scope,
				width: '90%',
				className: 'ng-dialog-money-log mh-ngdialog-theme'
			});
		};

		$scope.showDispositSelector = false;
		$scope.toggelDispositTypes = function () {
			$scope.showDispositSelector = !$scope.showDispositSelector;
		};
		$scope.showLists = {};
		//支付宝在线
		$http.get(baseURI + '/memberinterface/onlinekjPay?payType=2',{
			timeout:30000
		}).then(function(res){
			if(angular.isEmpty(res.data.datas)){
				$scope.showLists.alipay_online_show = false;
			}else{
				$scope.showLists.alipay_online_show = true;
			};
			$scope.alipay_online = res.data.datas;
			console.log($scope.alipay_online);
		},function(error){});
		//微信在线
		$http.get(baseURI + '/memberinterface/onlinekjPay?payType=1',{
			timeout:30000
		}).then(function(res){
			if(angular.isEmpty(res.data.datas)){
				$scope.showLists.wechat_online_show = false;
			}else{
				$scope.showLists.wechat_online_show = true;
			};
			$scope.wechat_online = res.data.datas;
			console.log($scope.wechat_online);
		},function(error){});
		//支付宝传统
		$http.get(baseURI + '/memberinterface/getThreePayInfo?payType=2',{
			timeout:30000
		}).then(function(res){
			if(angular.isEmpty(res.data.datas)){
				$scope.showLists.alipay_culture_show = false;
			}else{
				$scope.showLists.alipay_culture_show = true;
			};
			$scope.alipay_culture = res.data.datas;
			console.log($scope.alipay_culture);
		},function(error){});
		//微信传统
		$http.get(baseURI + '/memberinterface/getThreePayInfo?payType=1',{
			timeout:30000
		}).then(function(res){
			if(angular.isEmpty(res.data.datas)){
				$scope.showLists.wechat_culture_show = false;
			}else{
				$scope.showLists.wechat_culture_show = true;
			};
			$scope.wechat_culture = res.data.datas;
			console.log($scope.wechat_culture);
		},function(error){});
	}]);