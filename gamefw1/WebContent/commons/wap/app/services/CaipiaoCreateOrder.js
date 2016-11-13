angular.module('AppService')
	.factory('CaipiaoCreateOrder', ['$q', '$http', '$rootScope' , '$stateParams' ,function ($q, $http, $rootScope, $stateParams) {
		var orderItems = [];
		var price = 0;
		var orderFlag = '';
		
		return {
			resetItems: function () {
				orderItems = [];
				price = 0;
				return this;
			},
			setOrderFlag: function (flag) {
				orderFlag = flag;
				return this;
			},
			resetOrderFlag: function () {
				orderFlag = '';
				return this;
			},
			getItems: function () {
				return orderItems;
			},
			setItem: function (item) {
				if (!angular.isArray(item)) {
					item = [item];
				}
				orderItems = orderItems.concat(item);
				
				return this;
			},
			setPrice: function (p) {
				price = p;
				return this;
			},
			createMultiGroupOrder: function (gameCode, typeCode, cateCode , flag) {				
				if (!flag) {
					flag = $stateParams['flag'];
				}
				flag = flag || 'mul';
				
				
				var token = orderItems[0]['token'];
				
				var jsons = [{
						token: token,
						xzje: price,
						typeCode: typeCode,
						cateCode: cateCode,
				}];
				
				$http({
					method: 'POST',
					url: baseURI + '/cpOrder/saveOrder',
					headers: {
						'Content-Type': 'application/x-www-form-urlencoded'
					},
					transformRequest: function (obj) {
						var str = [];
				        for(var p in obj)
				        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
				        return str.join("&");
					},
					data: {
						jsons: angular.toJson(jsons),
						gameCode: gameCode,
						orderFlag: flag
					}
				}).then(function (res) {
					$rootScope.loadAccountInfo();
					defered.resolve(res);
				}, function (res) {
					defered.reject(res);
				});
				
				var defered = $q.defer();
				
				return defered.promise;
			},
			createOrder: function (gameCode, flag) {
				if (!flag) {
					if (orderFlag) flag = orderFlag;
					else flag = 'normal';
				}
				var defered = $q.defer();
				
				$http({
					method: 'POST',
					url: baseURI + '/cpOrder/saveOrder',
					headers: {
						'Content-Type': 'application/x-www-form-urlencoded'
					},
					transformRequest: function (obj) {
						var str = [];
				        for(var p in obj)
				        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
				        return str.join("&");
					},
					data: {
						jsons: angular.toJson(orderItems),
						gameCode: gameCode,
						orderFlag: flag
					}
				}).then(function (res) {
					$rootScope.loadAccountInfo();
					defered.resolve(res);
				}, function (res) {
					defered.reject(res);
				});
				
				return defered.promise;
			}
		};
	}]);