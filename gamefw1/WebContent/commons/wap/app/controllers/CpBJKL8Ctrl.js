angular.module('AppController')
	.controller('CpKL8Controller', ['$rootScope', '$scope' ,'$stateParams' ,'$state' , 
	                                'CaipiaoPeiyu', 'CaipiaoInfo', 'CaipiaoCreateOrder' , 'ngDialog',
	                                  function ($rootScope, $scope, $stateParams,$state, CaipiaoPeiyu, CaipiaoInfo, CaipiaoCreateOrder, ngDialog) {
		
		/*console.log($stateParams);*/
		if ($stateParams['game_code'] == 'BJKL8') {
			$rootScope.code = 'BJKL8';
			$rootScope.codeID = 11;
			$rootScope.codeLabel = 'PC蛋蛋';
			$rootScope.code2 = 'TM';
			$rootScope.code2ID = 106;
			$rootScope.code2Label = '幸运28';
		}
		else {
			$rootScope.code = 'CAKENO';
			$rootScope.codeID = 12;
			$rootScope.codeLabel = 'PC蛋蛋';
			$rootScope.code2 = 'TM';
			$rootScope.code2ID = 106;
			$rootScope.code2Label = '加拿大28';
		}
		
		$rootScope.title = $rootScope.code2Label;
		
		// 获取彩票资料
		$rootScope.caipiaoInfo = {
				lastResult : {
					total: 0,
					last_qs: '0',
					last_result: [0,0,0],
				}
		};
		
		CaipiaoInfo($rootScope.codeID, $rootScope.code2ID).then(function (res) {
			var data = res['data'];
			$rootScope.caipiaoInfo = data;
		});
		
		$scope.peiyu = {};
		$scope.sourcePeiyu = {};
		CaipiaoPeiyu($rootScope.code, $rootScope.code2).then(function (res) {
			$scope.sourcePeiyu = res['data']['datas'];
			console.log(['peiyu']);
			if (typeof res['data'] != 'object') {
				$rootScope.redirectToLogin();
			}
			else {
				for (var index in res['data']['datas']) {
					var peiyu = res['data']['datas'][index];
					$scope.peiyu[peiyu.id] = peiyu['pl'];
				}
			}
		});
		
		$rootScope.selectedPeiyu = {};
		var elements = angular.element(document.getElementsByClassName('bg-hrec-gray'));
		elements.bind('click', function (event) {
			var span = angular.element(event.target);
			if (span.hasClass('bg-hrec-red')) {
				span.addClass('bg-hrec-gray');
				span.removeClass('bg-hrec-red');
				var id = span.attr('id');
				delete $scope.selectedPeiyu[id];
			}
			else if (span.hasClass('bg-hrec-gray')) {
				span.addClass('bg-hrec-red');
				span.removeClass('bg-hrec-gray');
				var id = span.attr('id');
				$scope.selectedPeiyu[id] = true;
			}
		});
		
		$scope.reset = function () {
			$rootScope.selectedPeiyu = $scope.selectedPeiyu = {};
			CaipiaoCreateOrder.resetItems();
			angular.forEach(angular.element(document.getElementsByClassName('bg-hrec-red')), function (element) {
				angular.element(element).removeClass('bg-hrec-red').addClass('bg-hrec-gray');
			});
		};
		
		$scope.temaNumber1 = 0;
		$scope.temaNumber2 = 1;
		$scope.temaNumber3 = 2;
		$scope.temaNumbers = [0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27];
		
		$scope.confirm = function () {
			if (!$rootScope.isLogged() ) {
				alert('请先登录');
				$rootScope.redirectToLogin();
				return ;
			}
			else if (angular.isEmpty($scope.selectedPeiyu)) {
				return;
			}
			else if ($scope.temaNumber1 == $scope.temaNumber2  
					|| $scope.temaNumber1 == $scope.temaNumber3
					|| $scope.temaNumber2 == $scope.temaNumber3) {
				ngDialog.open({
					template: '<p>特码包三中不能出现相同号码</p>'
				});
				return;
			}
			
			CaipiaoCreateOrder.resetItems();
			angular.forEach($scope.selectedPeiyu ,function (index, id) {
				if (index == false) return;
				var peiyu = $scope.sourcePeiyu;
				for (var i in $scope.sourcePeiyu) {
					var item = peiyu[i];
					if (item['id'] == id) {
						var temaNumber = [$scope.temaNumber1, $scope.temaNumber2, $scope.temaNumber3].join(',');
						CaipiaoCreateOrder.setItem({
							uid: id,
							rate: item['pl'],
							label: $rootScope.codeLabel,
							label2: $rootScope.code2Label,
							xzje: $rootScope.xzje,
							number: item['number']=='特码包三'?temaNumber:item['number']
						});
					}
 				}
			});
			$state.go('caipiao.confirm');
		};
		
	}]);