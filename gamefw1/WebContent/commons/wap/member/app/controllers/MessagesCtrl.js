angular.module('AppController')
	.controller('MessagesController', ['$rootScope', '$scope', '$filter', 'CommomService',
	              function ($rootScope, $scope, $filter, CommomService) {
		$scope.title = '会员消息';
		$scope.type = 'user'; 
		$scope.messages = [];
		 function loadMessages(type) {
			CommomService.messages(type).then(function (res) {
				$scope.messages=res['data']['datas'];
			});
		};
		$scope.$watch('type', function (newtype) {
			loadMessages(newtype);
		});
		$scope.showMessage = function ( message) {
			CommomService.loadMessage(message['id']).then(function () {
				$rootScope.alert(message['msgContent'], '消息内容');
				message['readStatus'] = 1;
			});
		};
		$scope.delMessage = function (message) {
			if (confirm("确认删除?")) {
				CommomService.delMessage(message['id']).then(function (res) {
					$rootScope.alert(res['data']['msg']);
					loadMessages($scope.type);
				});
			}
		};
	}]); 