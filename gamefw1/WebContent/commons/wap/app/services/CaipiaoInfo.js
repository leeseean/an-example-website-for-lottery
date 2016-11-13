angular.module('AppService')
	.factory('CaipiaoInfo', ['$q', '$http', function ($q, $http) {
		
		return function (gameTypeCode, subCode) {
			var defered = $q.defer();
			
			$http({
				method: 'GET',
				url: baseURI + '/cp/lottoGetGameInfo/'+gameTypeCode+ '/'+subCode,
			}).then(function (res) {
				if (typeof res['data'] == 'object') {
					res['data']['lastResult']['total'] = 0;
					for (var index in res['data']['lastResult']['last_result']) {
						res['data']['lastResult']['total'] +=  res['data']['lastResult']['last_result'][index]*1;
					}
				}
				defered.resolve(res);
			}, function (res) {
				defered.reject(res);
			});
			
			return defered.promise;
		};
	}]);


	