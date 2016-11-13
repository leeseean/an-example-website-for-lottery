angular.module('AppController')
	.controller('CpHistoryController', ['$rootScope', '$scope', 'CaipiaoOrderHistory', 'ngDialog',
	    function($rootScope, $scope, CaipiaoOrderHistory, ngDialog) {
		$rootScope.title = '投注记录';
		$scope.showmore = [];
		$scope.historyList = [];
		
		$scope.pageNo = 1;
		$scope.pageSize = 25;
		$scope.totalPage = 0;
		
		function reloadHistory() {
			$scope.showmore = [];
			ngDialog.open({
				template: '<p>数据加载中</p>'
			});
			CaipiaoOrderHistory($scope.crtGame.game_code, $scope.dayObject.day, $scope.pageNo, $scope.pageSize).then(function (res) {
				setTimeout(ngDialog.close, 500);
				if (res['data']['state'] == 1) {
					$scope.historyList = res['data']['data']['records'];
					$scope.totalPage = res['data']['data']['totalPage'];
				}
				else { 
					$rootScope.redirectToLogin();
				}
			});
		}

		$scope.crtGame = {};
		$scope.dayObject = {
				day: 0,
				label: ''
		};
		
		$scope.prevPage = function () {
			if ($scope.pageNo > 1 ) {
				$scope.pageNo -= 1;
			}
		}
		
		$scope.nextPage = function () {
			if ($scope.pageNo < $scope.totalPage + 1 ) {
				$scope.pageNo += 1;
			}
		}
		
		$scope.$watch('dayObject', function (newval) {
			$scope.pageNo = 1;
			reloadHistory();
		}, true);
		
		$scope.$watch('pageNo', function () {
			reloadHistory();
		});
		
		$scope.$watch('crtGame', function (newval) {
			$scope.pageNo = 1;
			reloadHistory();
		});
		
		$scope.date = new Date();
		var weeks = ['日' ,'一', '二', '三', '四', '五', '六'];
		$scope.weekName = '星期'+ weeks[$scope.date.getDay()];
	}]);