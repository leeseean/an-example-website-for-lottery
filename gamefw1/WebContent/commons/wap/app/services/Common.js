angular.module('AppService')
	.factory('CommomService', ['$q', '$http', function ($q, $http) {
		
		return {
			AccountInfo: function () {
				return $http({
					method: 'GET',
					url: baseURI + '/cp/user_info'
				});
			},
			messages: function () {
				return $http({
					method: 'GET',
					url: baseURI + '/m/lottery/info',
				});
			}
		};
	}]);