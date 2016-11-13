angular.module('AppController')
	.controller('CpPK10Controller', ['$rootScope', '$scope', 'CaipiaoInfo', 'CaipiaoPeiyu', 'CaipiaoCreateOrder', '$stateParams', '$state' , 
	        function ($rootScope, $scope, CaipiaoInfo, CaipiaoPeiyu, CaipiaoCreateOrder, $stateParams, $state) {
		
		$rootScope.code = 'BJPK10';
		$rootScope.codeID = 10;
		$rootScope.codeLabel = '北京PK10';
		//对应菜单中的第三层节点的id（小盘口）
		var code2Ids = {
				SM: '94',
				GJ: '95',
				YJ: '96',
				JJ: '97',
				D4M: '98',
				D5M: '99',
				D6M: '100',
				D7M: '101',
				D8M: '102',
				D9M: '103',
				D10M: '104',
				GYJH: '105'
		};
		//盘口名字
		var code2Labels = {
				SM: '双面',
				GJ: '冠军',
				YJ: '亚军',
				JJ: '季军',
				D4M: '第四名',
				D5M: '第五名',
				D6M:'第六名',
				D7M:'第七名',
				D8M:'第八名',
				D9M:'第九名',
				D10M:'第十名',
				GYJH:'冠军亚和'
		};
		$rootScope.code2 = $stateParams.pankou;
		$rootScope.code2ID = code2Ids[$rootScope.code2];
		$rootScope.code2Label = code2Labels[$rootScope.code2];
		
		$rootScope.title = $rootScope.codeLabel + " " + $rootScope.code2Label;
		
		
		// 获取彩票信息
		CaipiaoInfo($rootScope.codeID, $rootScope.code2ID).then(function (res) {
			var data = res['data'];
			$rootScope.caipiaoInfo = data;
		});
		
		// 设置赔率
		$scope.peiyu = {};
		var peiyu;
		CaipiaoPeiyu($rootScope.code, $rootScope.code2).then(function (res) {
			peiyu = res['data']['datas'];
			for (key in peiyu) {
				peiyuitem = peiyu[key];
				$scope.peiyu[peiyuitem['id']] = peiyuitem['pl'];
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
				$scope.selectedPeiyu[id] = false;
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
			
			for (var shengxiao in $scope.hxCpSelected) {
				$scope.hxCpSelected[shengxiao] = false;
			}
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
			angular.forEach($scope.selectedPeiyu ,function (index, id) {
				if (index == false) return;
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
		
		
	}]);