angular.module('AppService', [])
	.factory('CaipiaoPeiyu', ['$http', '$q', function ($http, $q) {
		
		return function (code, cat, subCat, extra) {
			subCat = subCat || '';
			extra = extra || '';
			var deferered = $q.defer();
			
			$http({
				method: 'POST',
				url: baseURI + '/cp/server?tim='+new Date().getTime(),
				headers: {
					'Content-Type': 'application/x-www-form-urlencoded'
				},
				transformRequest: function (obj) {
					var str = [];
			        for(var p in obj)
			        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
			        return str.join("&");
				},
				data:{
					code: code,
					code2: cat,
					code3: subCat,
					code4: extra
				}
			}).then(function (response) {
				deferered.resolve(response);
			}, function (response) {
				deferered.reject(response);
			});
			
			return deferered.promise;
		};
	}]);