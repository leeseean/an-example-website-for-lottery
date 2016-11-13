angular.module('AppService')
	.factory('CaipiaoOrderHistory', ['$q', '$http', function ($q, $http) {
		
		var serialize = function (obj) {
		  var str = [];
		  for(var p in obj)
		    if (obj.hasOwnProperty(p)) {
		      str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		    }
		  return str.join("&");
		}
		
		return function (gameCode, day, pageNo, pageSize) {
			var defered = $q.defer();
			gameCode = gameCode || '';
			var query = '?'+ serialize({
				gameCode: gameCode,
				day: day,
				pageNo: pageNo,
				pageSize: pageSize
			});
			
			$http({
				method: 'GET',
				url: baseURI + "/m/order/history" + query,
			}).then(function (res) {
				defered.resolve(res);
			}, function (res) {
				defered.reject(res);
			});
			
			return defered.promise;
		};
	}]);