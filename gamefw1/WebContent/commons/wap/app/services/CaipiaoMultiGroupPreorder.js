angular.module('AppService')
	.factory('CaipiaoMultiGroupPreorder', ['$q', '$http', function ($q, $http) {
		
		var multiGroupPreorder = function (gameCode, multiLen, ids) {
			var defered = $q.defer();
			
			$http({
				method: 'POST',
				url: baseURI + '/cpOrder/goMultiGroupOrder',
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
					cids: ids.join(','),
					gameCode: gameCode,
					multilen: multiLen
				}
			}).then(function (res) {
				defered.resolve(res);
			}, function (res) {
				defered.reject(res);
			});
			
			return defered.promise;
		};
		multiGroupPreorder.HKLM = function (gameCode, nums, rrtype, pabc, dm1, dm2) {
			return $http({
				method: 'POST',
				url: baseURI + '/cpOrder/goLmOrder',
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
					nums: nums.join(','),
					gameCode: gameCode,
					rrtype: rrtype,
					pabc: pabc,
					dm1: dm1,
					dm2: dm2
				}
			});
		}
		multiGroupPreorder.YZGG = function (gameCode, typeCode, cateCode, multiLen, bwNum, swNum, gwNum) {
			var defered = $q.defer();
			
			$http({
				method: 'POST',
				url: baseURI + '/cpOrder/getGroupOrderList',
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
					bwNum: bwNum,
					swNum: swNum,
					gwNum: gwNum,
					gameCode: gameCode,
					typeCode: typeCode,
					cateCode: cateCode,
					multilen: multiLen
				}
			}).then(function (res) {
				defered.resolve(res);
			}, function (res) {
				defered.reject(res);
			});
			
			return defered.promise;
		};
		
		multiGroupPreorder.FSZH = function (gameCode, typeCode, cateCode, multiLen, bwNum, swNum, gwNum) {
			return multiGroupPreorder.YZGG(gameCode, typeCode, cateCode, multiLen, bwNum, swNum, gwNum);
		};
		
		multiGroupPreorder.ZXS = function (gameCode, typeCode, cateCode, multiLen, num) {
			var defered = $q.defer();
			
			$http({
				method: 'POST',
				url: baseURI + '/cpOrder/getGroupOrderList',
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
					num: num,
					gameCode: gameCode,
					typeCode: typeCode,
					cateCode: cateCode,
					multilen: multiLen
				}
			}).then(function (res) {
				defered.resolve(res);
			}, function (res) {
				defered.reject(res);
			});
			
			return defered.promise;
		};
		
		return multiGroupPreorder;
	}]);