angular.module('AppController')
    .controller('ThirdpayDepositFormController', ['$rootScope', '$scope', '$filter', '$stateParams', 'CommomService', '$timeout', 'ngDialog', '$http', '$sce', '$document', '$state', '$window',
        function($rootScope, $scope, $filter, $stateParams, CommomService, $timeout, ngDialog, $http, $sce, $document, $state, $window) {

            console.log($stateParams);
            //调取充值金额范围
            $scope.type = $stateParams['type'];
            $scope.payid = $stateParams['payid'];
            $scope.payValue = $stateParams['payValue'];
            if ($scope.type && $scope.payid && $scope.payValue) {
                localStorage.setItem('type', $scope.type);
                localStorage.setItem('payid', $scope.payid);
                localStorage.setItem('payValue', $scope.payValue);
            };
            if ($stateParams['payNo']) {
                localStorage.setItem('payNo', $stateParams['payNo']);
            };
            var thirdType = $scope.thirdType = localStorage.getItem('type') == 'wechat' ? 1 : 2;
            console.log(localStorage.getItem('payid'), localStorage.getItem('payValue'));
            if (localStorage.getItem('payValue')) {
                $http.get(baseURI + '/memberinterface/payInfo', {
                    timeout: 30000,
                    params: {
                        payid: localStorage.getItem('payid'),
                        payType: thirdType
                    }
                }).then(function(res) {
                    console.log(res);
                    $scope.thirdMaxEdu = res.data.datas.thirdMaxEdu;
                    $scope.thirdMinEdu = res.data.datas.thirdMinEdu;
                    console.log($scope.thirdMaxEdu, $scope.thirdMinEdu);
                }, function(error) {});
            };

            $rootScope.title = "充值";
            // var thirdType = $scope.thirdType = $stateParams['type'];
            // $scope.thirdType = $stateParams['type'];
            $scope.showImg = function() {
                ngDialog.open({
                    template: '<img ng-src="{{baseURI}}/resources-1.0/commons/wap/member/app/misc/weixinpayeg.jpg" style="width:267px;height:297px" />', //use plain text as template
                    plain: true,
                    className: 'ngdialog-theme-default'
                });
            };
            $scope.baseURI = baseURI;
            $scope.trustAction = $sce.trustAsResourceUrl(baseURI + '/memberinterface/doPayCenterData');
            $scope.createTime = $rootScope.today;
            $scope.submitting = false;
            //传统方式提交
            $scope.submitDepositForm1 = function() {
                if ($scope.submitting) return;
                $scope.submitting = true;
                if ($scope.hkMoney * 1 > 1 && $scope.hkMoney * 1 < 100) {
                    $rootScope.alert('充值金额不得低于100');
                    $scope.submitting = false;
                } else if ($scope.hkMoney >= 100) {
                    CommomService.postThirdpayDepositMessage($scope.hkMoney, $scope.hkUserName, $scope.createTime, $scope.thirdType, localStorage.getItem('payNo')).then(function(res) {
                        $scope.submitting = false;
                        // console.log(res);
                        $rootScope.alert(res['data']['msg']).closePromise.then(function() {
                            $rootScope.go('home');
                        });
                    });
                } else {
                    $rootScope.alert('请填写充值金额');
                    $scope.submitting = false;
                }
            };
            //在线方式提交
            $scope.submitDepositForm = function() {
                console.log($scope.hkMoney);
                if ($scope.submitting) return;
                $scope.submitting = true;
                if ($scope.hkMoney > $scope.thirdMaxEdu || $scope.hkMoney < $scope.thirdMinEdu) {
                    $rootScope.alert('充值金额不在有效范围内');
                    $scope.submitting = false;
                } else if ($scope.hkMoney && $scope.hkMoney <= $scope.thirdMaxEdu && $scope.hkMoney >= $scope.thirdMinEdu) {
                    $http.get(baseURI + '/memberinterface/doPayCenter', {
                        timeout: 30000,
                        params: {
                            money: $scope.hkMoney,
                            payid: localStorage.getItem('payid'),
                            payValue: localStorage.getItem('payValue'),
                            payType: thirdType
                        }
                    }).then(function(res) {
                        console.log(res);
                        var pay_url = res.data.datas.pay_url;
                        $scope.action = $sce.trustAsResourceUrl(pay_url);
                        var sendParams = res.data.datas.sendParams;
                        $scope.sendParams = sendParams;
                        $state.go('goPayCenter', { 'pay_url': pay_url, sendParams: sendParams, payValue: localStorage.getItem('payValue') ,payType: thirdType});
                        $scope.submitting = false;
                    }, function(error) {});
                } else {
                    $rootScope.alert('请填写充值金额');
                    $scope.submitting = false;
                }
            };
        }
    ]);
