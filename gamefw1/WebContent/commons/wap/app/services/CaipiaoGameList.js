angular.module('AppService')
	.factory('CaipiaoGameList', ['$q', '$http', function ($q, $http) {
		
		return function () {
			var defered = $q.defer();
			
			$http({
				method: 'GET',
				url: baseURI + "/m/game/list"
			}).then(function (res) {
				defered.resolve(res);
			}, function (res) {
				defered.reject(res);
			});
			
			return defered.promise;
		};
	}]);