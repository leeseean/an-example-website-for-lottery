angular.module('AppFilter', [])
	.filter('currentdate', ['$filter', function ($filter) {
		return function () {
			return $filter('date')(new Date(), 'yyyy.MM.dd');
		};
	}])
	.filter('ballnumber', [function () {
		return function (input) {
			if (input < 10) return '0'+input;
			return input;
		};
	}])
	.filter('sum', [function() {
		return function (input) {
			if (!angular.isArray(input)) return input;
			var total = 0;
			for (var i = 0; i <input.length; i++) total += parseInt(input[i]);
			return total;
		}
	}])
	.filter('default', [function () {
		return function (input, defaultValue) {
			if (input) return input;
			return defaultValue;
		}
	}])
	.filter('substr', [function () {
		return function (input, start, len) {
			return input.substring(start, len);
		};
	}]);