angular.module('AppController')
	.controller('CpKLSFController', ['$rootScope', '$scope', '$stateParams' , '$state' , 
	                                  'CaipiaoPeiyu', 'CaipiaoCreateOrder', 'CaipiaoInfo', 
	                                  'CaipiaoMultiGroupPreorder','ngDialog',
	                                  function ($rootScope, $scope, $stateParams, $state , 
	                                		  CaipiaoPeiyu, CaipiaoCreateOrder, CaipiaoInfo, 
	                                		  CaipiaoMultiGroupPreorder, ngDialog) {
		
		$rootScope.code = $stateParams.game_code;
		if ($rootScope.code == 'GDKLSF') {
			$rootScope.codeLabel = '广东快乐十分';
			$rootScope.codeID = 8;
		}
		else if ($rootScope.code == 'TJKLSF') {
			$rootScope.codeLabel = '天津快乐十分';
			$rootScope.codeID = 9;
		}
		var code2Ids = {
				'd1q': 74,
				'd2q': 75,
				'd3q': 76,
				'd4q': 77,
				'd5q': 78,
				'd6q': 79,
				'd7q': 80,
				'd8q': 81,
				'zhlh': 82,
				'lm': 83,
		};
		var code2Names ={
				'd1q': '第一球',
				'd2q':'第二球',
				'd3q': '第三球',
				'd4q':'第四球',
				'd5q':'第五球',
				'd6q':'第六球',
				'd7q':'第七球',
				'd8q': '第八球',
				'zhlh': '总和龙虎',
				'lm': '连码'
		};
		$rootScope.code2 = $stateParams.pankou;
		$scope.pankou = $rootScope.code2.toUpperCase();
 
		$rootScope.code2ID = code2Ids[$rootScope.code2];
		$rootScope.code2Label = code2Names[$rootScope.code2];
		$rootScope.title = $rootScope.codeLabel + ' ' + $rootScope.code2Label;
		
		// 连码ID
		$scope.code3 = 'RX2';

		// 获取彩票信息
		CaipiaoInfo($rootScope.codeID, $rootScope.code2ID).then(function (res) {
			var data = res['data'];
			$rootScope.caipiaoInfo = data;
		});
		
		// 设置赔率
		$scope.peiyu = {};
		var peiyu;
		CaipiaoPeiyu($rootScope.code.toUpperCase(), $rootScope.code2.toUpperCase()).then(function (res) {
			peiyu = res['data']['datas'];
			for (var key in peiyu) {
				peiyuitem = peiyu[key];
				$scope.peiyu[peiyuitem['id']] = peiyuitem['pl'];
			}
		});
		
		$rootScope.selectedPeiyu = {};
		
		$scope.reset = function () {
			$scope.selectedPeiyu = {};
			CaipiaoCreateOrder.resetItems();
		};
		
		$scope.confirm = function () {
			if (!$rootScope.isLogged() ) {
				alert('请先登录');
				$rootScope.redirectToLogin();
				return ;
			}
			else if (angular.isEmpty($scope.selectedPeiyu)) {
				return;
			}
			
			CaipiaoCreateOrder.resetItems();
			var totalSelected = 0;
			angular.forEach($scope.selectedPeiyu ,function (index, id) {
				if ($scope.selectedPeiyu[index] == false) return;
				totalSelected += 1;
				for (var i in peiyu) {
					var item = peiyu[i];
					if (item['id'] == id) {
						CaipiaoCreateOrder.setItem({
							uid: id,
							rate: item['pl'],
							label: $rootScope.codeLabel,
							label2: $rootScope.code2Label,
							xzje: $rootScope.xzje,
							number: item['number']
						});
					}
 				}
			});
			
			$state.go('caipiao.confirm');
		};
		
		if ($scope.code2 == 'lm') {
			$scope.confirm = function () {
				var ids = [];
				angular.forEach($scope.selectedPeiyu ,function (index, id) {
					if ($scope.selectedPeiyu[id] == false) return;
					ids.push(id);
				});
				var code3Required = {
						RX2: '2',
						RX3: '3',
						RX4: '4',
						RX5: '5'
				};
				var requiredNum = code3Required[$scope.code3];
				if (ids.length < requiredNum) {
					ngDialog.open({
						plain: true,
						template: '<p>至少需要选择'+requiredNum+'个号码！</p>'
					});
				} 
				else {
					CaipiaoMultiGroupPreorder($rootScope.code, requiredNum, ids).then(function (res) {
						$rootScope.subType = $scope.code3;
						console.log($rootScope.subType);
						if (typeof res['data'] == 'object') {
								var token = res['data']['token'];
								for (var index in res['data']['orderList']) {
									CaipiaoCreateOrder.setItem({
										'preorder': res['data']['orderList'][index],
										'xzje': $rootScope.xzje,
										'token': token
									});
								}
								$state.go('caipiao.confirm.hx', {flag: 'mul'});
							}
					});
				}
			};
		}
	}]);