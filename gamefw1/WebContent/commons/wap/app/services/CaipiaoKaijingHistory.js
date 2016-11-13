angular.module('AppService')
	.factory('CaipiaoKaijiangHistory', ['$q', '$http', function ($q, $http) {
		
		return {
			getCurrentResults: function () {
				var defered = $q.defer();
				$http({
					url: baseURI + '/m/results/current',
					method: 'GET'
				}).then(function (res) {
					defered.resolve(res);
				}, function (res) {
					defered.reject(res);
				});
				
				return defered.promise;
			},
			getGameResults: function (gameCode) {
				var defered = $q.defer();
				$http({
					url: baseURI + '/m/results/history/'+gameCode,
					method: 'GET'
				}).then(function (res) {
					defered.resolve(res);
				}, function (res) {
					defered.reject(res);
				});
				
				return defered.promise;
			}
		};
		
	}]);