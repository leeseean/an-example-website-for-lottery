angular.module('AppController')
	.controller('CpHK6Controller', ['$rootScope', '$scope', '$stateParams' , '$state' , 
	                                  'CaipiaoPeiyu', 'CaipiaoCreateOrder', 'CaipiaoInfo', 
	                                  'CaipiaoMultiGroupPreorder', 'substrFilter', 'ngDialog',
	                                  function ($rootScope, $scope, $stateParams, $state , 
	                                		  CaipiaoPeiyu, CaipiaoCreateOrder, CaipiaoInfo, 
	                                		  CaipiaoMultiGroupPreorder, substrFilter, ngDialog) {
		
		$rootScope.code = 'HK6';
		$rootScope.codeID = 1;
		$rootScope.codeLabel = '香港六合彩';
		
		$rootScope.code2 = undefined;
		$rootScope.code2ID = undefined;
		$rootScope.code2Label = undefined;
		
		$scope.shengXiaoNames = { 
			'1SHU': '鼠',
			'2NIU': '牛',
			'3HU': '虎',
			'4TU': '兔',
			'5LONG': '龙',
			'6SHE': '蛇',
			'7MA': '马',
			'8YANG': '羊',
			'9HOU': '猴',
			'aJI': '鸡',
			'bGOU': '狗',
			'cZHU': '猪',
		};
		
		$scope.selectedPeiyu = {};
		CaipiaoCreateOrder.resetItems();
		
		// 默认订单确认函数 - 特殊订单单独覆盖
		$scope.confirm = function (cb) {
			// 确认回调函数
			if (!cb) {
				cb = function () {
					$state.go('caipiao.confirm')
				};
			}
			if (!$rootScope.isLogged() ) {
				alert('请登录');
				$rootScope.redirectToLogin();
				return ;
			}
			else if (angular.isEmpty($scope.selectedPeiyu)) {
				return;
			}
			CaipiaoCreateOrder.resetItems();
			angular.forEach($scope.selectedPeiyu ,function (index, id) {
				if (index == false) {
					return;
				}
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
			cb();
		};
		
		if ($stateParams['pankou'] == 'tm') {
			var names = {
					1: '特码A',
					2: '特码B',
			};
			$rootScope.code2 = 'TM';
			$rootScope.code2ID = 1;
			$rootScope.code2Label = names[$stateParams['type']];
			
			var subTypes = {
					1: 'A',
					2: 'B'
			};
			$scope.subType = subTypes[$stateParams['type']];
		}
		else if ($stateParams['pankou'] == 'zmt') {
			var names = {
					1: '正1特',
					2: '正2特',
					3: '正3特',
					4: '正4特',
					5: '正5特',
					6: '正6特',
			};
			var subTypes = {
					1: 'Z1T',
					2: 'Z2T',
					3: 'Z3T',
					4: 'Z4T',
					5: 'Z5T',
					6: 'Z6T',
			};
			$rootScope.code2 = 'ZMT';
			$rootScope.code2ID = 2;
			$rootScope.code2Label = names[$stateParams['type']];
			$scope.subType = subTypes[$stateParams['type']];
		}
		else if ($stateParams['pankou'] == 'hx') {
			var names = {
					1: '二肖',
					2: '四肖',
					3: '六肖'
			};
			var subTypes = {
					1: 'HX-EX',
					2: 'HX-SX',
					3: 'HX-LX'
			};
			$rootScope.code2 = 'HX';
			$rootScope.code2ID = 3;
			$rootScope.code2Label = names[$stateParams['type']];
			$scope.subType = subTypes[$stateParams['type']];
			$rootScope.subType = $scope.subType.replace('HX-', '');
			
			$scope.confirm = function () {
				if (!$rootScope.isLogged() ) {
					alert('请先登录');
					$rootScope.redirectToLogin();
					return ;
				}
				else {
					var selectedHx = 0;
					var minHxItems = {
							'HX-EX': 2,
							'HX-SX': 4,
							'HX-LX': 6
					};
					var minItem = minHxItems[$scope.subType];
					var ids = [];
					for (var shengxiao in $scope.hxCpSelected) {
						if ($scope.hxCpSelected[shengxiao] == false) {
							continue;
						};
						if ($scope.hxCpSelected[shengxiao]) {
							selectedHx += 1;
							ids.push(shengxiao);
						}
					}
					if (selectedHx < minItem) {
						ngDialog.open({
							plain: true,
							template: '<p>'+'请至少选择' + minItem + '个生肖'+'</p>'
						});
					}
					else {
						// 生成组合订单
						CaipiaoCreateOrder.resetItems();
						CaipiaoMultiGroupPreorder($rootScope.code, minItem, ids)
							.then(function (res) {
								if (typeof res['data'] != 'object') {
									$rootScope.redirectToLogin();
								}
								else {
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
				}
			};
		}
		// 组码
		else if ($stateParams['pankou'] == 'zm') {
			var names = {
					'A': '正码A',
					'B': '正码B'
			};
			$rootScope.code2 = 'ZM';
			$rootScope.code2ID = 3;
			$rootScope.code2Label = names[$stateParams['type']];
			$scope.subType = $stateParams.type;
			$rootScope.subType = $scope.subType;
		}
		
		// 正码1-6
		else if ($stateParams['pankou'] == 'zm1t6') {
			$rootScope.code2 = 'ZM1T6';
			$rootScope.code2ID = 4;
			$rootScope.code2Label = '正码1-6';
			$scope.subType = '';
			$rootScope.subType = $scope.subType;
		}
		
		// 过关
		else if ($stateParams['pankou'] == 'gg') {
			$scope.groups = {
					ZM1: '正码1', 
					ZM2: '正码2',
					ZM3: '正码3',
					ZM4: '正码4',
					ZM5: '正码5',
					ZM6: '正码6'
			};
			$scope.groupItems = {
					'0ODD': '单',
					'1EVEN': '双',
					'2BIG': '大',
					'3SMALL': '小',
					'4RED': '红波',
					'5GREEN': '绿波',
					'6BLUE': '蓝波'
			};
			$rootScope.code2 = 'GG';
			$rootScope.code2ID = 5;
			$rootScope.code2Label = '过关';
			$scope.subType = '';
			$rootScope.subType = $scope.subType;
			
			// 祖码单独处理
			var oldConfirm = $scope.confirm;
			$scope.confirm = function () {
				if (!$rootScope.isLogged() ) {
					alert('请先登录');
					$rootScope.redirectToLogin();
					return ;
				}
				
				var selectedPeiyu = $scope.selectedPeiyu;
				var groupNames = {};
				var newSelectedPeiyu = {};
				var len = Object.keys(selectedPeiyu).length;
				if (len < 2 ) {
					ngDialog.open({
						template: '请任选2-6个号码为一投注组合'
					});
				}
				else {
					for (var key in selectedPeiyu) {
						if (selectedPeiyu[key] == false) {
							continue;
						};
						newSelectedPeiyu[selectedPeiyu[key]] = true;
						groupNames[selectedPeiyu[key]] = $scope.groups[key];
					}
					$scope.selectedPeiyu = newSelectedPeiyu;
					oldConfirm.call(this, function () {
						var items = CaipiaoCreateOrder.getItems();
						for (var index in items) {
							var item = items[index];
							item['label2'] = groupNames[item['uid']];
						}
						CaipiaoCreateOrder.resetItems();
						CaipiaoCreateOrder.setItem(items);
						$state.go('caipiao.confirm.gg');
					});
				}
			}
		}
		else if ($stateParams['pankou'] == 'lm') {
			$rootScope.code2 = 'LM';
			$rootScope.code2ID = 6;
			$rootScope.code2Label = '连码';
			$scope.subType = $stateParams['type'];
			$rootScope.subType = $scope.subType; 
			
			$scope.subTypes = {
					0: '三全中',
					1: '三中二',
					2: '二全中',
					3: '二中特',
					4: '特串',
			};
			$scope.pabcArray = {
					1: '复式',
					2: '胆拖'
			}; 
			$rootScope.pabc = 1; // 复式  - 1  胆拖 - 2
			$scope.dan1 = '';
			$scope.dan2 = '';
			
			$scope.selectDan = function (number) {
				if ($scope.pabc == 2) {
					if (!$scope.dan1) {
						$scope.dan1 = number;
						$scope.selectedPeiyu[number] = true;
					}
					else if (!$scope.dan2) {
						$scope.dan2 = number;
						$scope.selectedPeiyu[number] = true;
					}
					else if ( ( $scope.dan1 != number && $scope.dan2 != number ) 
							|| ($scope.dan2 == number && $scope.subType == 4)
							|| ($scope.dan2 == number && $scope.subType == 3)
							|| ($scope.dan2 == number && $scope.subType == 2)) {
						$scope.selectedPeiyu[number] = $scope.selectedPeiyu[number] == true ? false: true;
					}
				}
				else {
					$scope.selectedPeiyu[number] = $scope.selectedPeiyu[number] == true ? false: true;
				}
			}
			$scope.resetAll = function () {
				$scope.dan1 = '';
				$scope.dan2 = '';
				$scope.selectedPeiyu = {};
			}
			
			$scope.confirm = function () {
				// 登陆检查
				if (!$rootScope.isLogged() ) {
					alert('请先登录');
					$rootScope.redirectToLogin();
					return ;
				}
				
				var subTypeRequired = {
					0: 3,
					1: 3,
					2: 2,
					3: 2,
					4: 2,
				};
				
				var selectedLen = 0;
				var nums = [];
				for (var key in $scope.selectedPeiyu) {
					if ($scope.selectedPeiyu[key] == true) {
						selectedLen += 1;
						nums.push(key);
					}
				}
				
				var requiredLen = subTypeRequired[$scope.subType];
				if (selectedLen < requiredLen) {
					ngDialog.open({
						template: '<p>'+'请至少选择' + requiredLen + '个号码下注'+'</p>'
					});
				}
				if (selectedLen > 10) {
					ngDialog.open({
						template: '<p>'+'最多只能选择10个号码下注'+'</p>'
					});
				}
				else {
					if ($scope.subType == 4 || $scope.subType == 2 || $scope.subType == 3) {
						$scope.dan2 = '';
					}
					CaipiaoMultiGroupPreorder.HKLM($scope.code, nums, $scope.subType, $scope.pabc, $scope.dan1, $scope.dan2)
						.then(function (res) {
							var token = res['data']['token'];
							if (!token) {
								ngDialog.open({
									template: '<p>' + res['data']['msg'] + '</p>'
								});
								return ;
							}
							for (var index in res['data']['orderList']) {
								CaipiaoCreateOrder.setItem({
									'preorder': res['data']['orderList'][index],
									'xzje': $rootScope.xzje,
									'token': token
								});
							}
							$state.go('caipiao.confirm.hx', {flag: 'lm'});
						});
				}
			}
			
		}
		else if ($stateParams['pankou'] == 'bb') {
			$rootScope.code2 = 'BB';
			$rootScope.code2ID = 6;
			$rootScope.code2Label = '半波';
			$scope.subType = $stateParams['type'];
			$rootScope.subType = $scope.subType;
		}
		else if ($stateParams['pankou'] == 'yxyztm') {
			$rootScope.code2 = 'YXYZTW';
			$rootScope.code2ID = 8;
			$rootScope.code2Label = '一肖&正特尾';
			$scope.subType = $stateParams['type'];
			$rootScope.subType = $scope.subType;
		}
		else if ($stateParams['pankou'] == 'txtw') {
			$rootScope.code2 = 'TXTW';
			$rootScope.code2ID = 9;
			$rootScope.code2Label = '特肖头尾';
			$scope.subType = $stateParams['type'];
			$rootScope.subType = $scope.subType;
		}
		else if ($stateParams['pankou'] == 'lx') {
			var names = {
					'ELX': '二连肖',
					'SALX': '三连肖',
					'SILX': '四连肖',
					'WLX': '五连肖'
			};
			$rootScope.code2 = 'LX';
			$rootScope.code2ID = 12;
			$scope.subType = $stateParams['type'];
			$rootScope.code2Label = '连肖 ' + names[$scope.subType];
			$rootScope.subType = $scope.subType;
			
			var minLen = 0;
			if ($scope.subType == 'ELX') {
				minLen = 2;
			}
			else if ($scope.subType == 'SALX') {
				minLen = 3;
			}
			else if ($scope.subType == 'SILX') {
				minLen = 4;
			}
			else if ($scope.subType == 'WLX') {
				minLen = 5;
			}
			
			$scope.confirm = function () {
				if (!$rootScope.isLogged() ) {
					alert('请先登录');
					$rootScope.redirectToLogin();
					return ;
				}
				else {
					var selectedHx = 0;
					var minHxItems = {
							'HX-EX': 2,
							'HX-SX': 4,
							'HX-LX': 6
					};
					var ids = [];
					for (var shengxiao in $scope.hxCpSelected) {
						if ($scope.hxCpSelected[shengxiao] == false) continue;
						if ($scope.hxCpSelected[shengxiao]) {
							selectedHx += 1;
							ids.push(shengxiao);
						}
					}
					if (selectedHx < minLen) {
						ngDialog.open({
							template: '<p>'+'请至少选择' + minLen + '个生肖'+'</p>'
						});
					}
					else {
						// 生成组合订单
						CaipiaoCreateOrder.resetItems();
						CaipiaoMultiGroupPreorder($rootScope.code, minLen, ids)
							.then(function (res) {
								if (typeof res['data'] != 'object') {
									$rootScope.redirectToLogin();
								}
								else {
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
				}
			};
		}
		else if ($stateParams['pankou'] == 'wsl') {
			$rootScope.code2 = 'WSL';
			$rootScope.code2ID = 11;
			$rootScope.code2Label = '尾数连';
			$scope.subType = $stateParams['type'];
			$rootScope.subType = $scope.subType;
			
			var minLen = 0;
			if ($scope.subType == 'EWL') {
				minLen = 2;
			}
			else if ($scope.subType == 'SAWL') {
				minLen = 3;
			}
			else if ($scope.subType == 'SIWL') {
				minLen = 4;
			}
			if (minLen <= 0) $rootScope.redirectToLogin();
			
			$scope.confirm = function () {
				if (!$rootScope.isLogged() ) {
					$rootScope.redirectToLogin();
					return ;
				}
				var ids = [];
				for (var id in $scope.selectedPeiyu) {
					if ($scope.selectedPeiyu[id] == false) continue;
					ids.push(id);
				}
				if (ids.length < minLen) {
					ngDialog.open({
						template: '<p>'+'至少需要选择'+minLen+'个尾号!'+'</p>'
					});
				}
				else {
					CaipiaoMultiGroupPreorder($rootScope.code, minLen, ids)
						.then(function (res) {
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
		else if ($stateParams['pankou'] == 'zybz') {
			$rootScope.code2 = 'ZYBZ';
			$rootScope.code2ID = 10;
			$rootScope.code2Label = '中与不中';
			$scope.subType = $stateParams['type'];
			$rootScope.subType = $scope.subType;
			
			var minLen = 0;
			if ($scope.subType == 'WBZ') {
				minLen = 5;
				$rootScope.code2Label = '五不中';
			}
			else if ($scope.subType == 'QBZ') {
				minLen = 7;
				$rootScope.code2Label = '七不中';
			}
			else if ($scope.subType == 'JBZ') {
				minLen = 9;
				$rootScope.code2Label = '九不中';
			}
			else if ($scope.subType == 'SZY') {
				minLen = 4;
				$rootScope.code2Label = '四中一';
			}
			else if ($scope.subType == 'LZY') {
				minLen = 6;
				$rootScope.code2Label = '六中一';
			}
			
			if (minLen <= 0) $state.go('home');
			
			$scope.confirm = function () {
				if (!$rootScope.isLogged() ) {
					$rootScope.redirectToLogin();
					return ;
				}
				var ids = [];
				for (var id in $scope.selectedPeiyu) {
					if ($scope.selectedPeiyu[id] == false) continue;
					ids.push(id);
				}
				if (ids.length < minLen) {
					ngDialog.open({
						template: '<p>'+'至少需要选择'+minLen+'个号码!'+'</p>'
					});
				}
				else{
					CaipiaoMultiGroupPreorder($rootScope.code, minLen, ids)
						.then(function (res) {
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
		$rootScope.title = "香港六合彩 - " + $rootScope.code2Label;
		
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
		
		// 获取彩票资料
		$rootScope.caipiaoInfo = {
				lastResult : {
					last_qs: '20160701',
					last_result: [49,49,49,49,49,49,49],
				}
		};
		$scope.kaijiangSecond = 0;
		$scope.shengXiaoMap = {};
		$scope.hxCpSelected = {};
		
		// 获取彩票信息
		CaipiaoInfo($rootScope.codeID, $rootScope.code2ID).then(function (res) {
			var data = res['data'];
			$scope.shengXiaoMap = data['shengXiaoMap'];
			var hxCpSelected = {};
			for (var shengxiao in $scope.shengXiaoMap) {
				$scope.hxCpSelected[shengxiao] = false;
			}
			$rootScope.caipiaoInfo = data;
		});
		
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
		}
		
	}]);