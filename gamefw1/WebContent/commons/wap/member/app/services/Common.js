angular.module('AppService', [])
	.factory('CommomService', ['$rootScope' ,'$q', '$http', function ($rootScope,$q, $http) {

		return {
			saveBankInfo: function (bankType, bankCard, bankAddress, withdrawlPwd) {
				// /member/saveBackInfo
				return $http({
					method: 'POST',
					url: baseURI + '/member/saveBackInfo',
					headers: {'Content-Type': 'application/x-www-form-urlencoded'},
					transformRequest: function (obj) {
						var str = [];
				        for(var p in obj)
				        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
				        return str.join("&");
					},
					data: {userBankType: bankType,
						userBankCard:bankCard,
						userBankAddress:bankAddress,
						userWithdrawPassword: withdrawlPwd
						},
				});
			},
			getEduRecords: function (eduType, flatName, beginTime, endTime) {
				return $http({
					method: 'POST',
					url: baseURI + '/memberinterface/getEduRecord',
					headers: {'Content-Type': 'application/x-www-form-urlencoded'},
					transformRequest: function (obj) {
						var str = [];
				        for(var p in obj)
				        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
				        return str.join("&");
					},
					data: {pageNo: $rootScope.pager.pageNo(), pageSize: $rootScope.pager.pageSize() ,beginTime: beginTime, endTime: endTime, eduType: eduType, flatName: flatName}
				});
			},
			accountInfo: function () {
				return $http({
					method: 'GET',
					url: baseURI + '/memberinterface/getUserInfo'
				});
			},
			messages: function (type) {
				var code = type == 'user' ? 2: 1;
				return $http({
					method: 'GET',
					url: baseURI + '/memberinterface/getUserMessage?code='+code,
				});
			},
			loadMessage: function (id) {
				return $http({
					method: 'GET',
					url: baseURI + '/memberinterface/getUserMessageDetail?id=' + id,
				});
			},
			delMessage: function (id) {
				return $http({
					method: 'GET',
					url: baseURI + '/m/message/delMessage?id='+id,
				});
			},
			getTransferOutLog: function(hkType, beginTime, endTime) {
				return $http({
					method: 'POST',
					url: baseURI + '/memberinterface/getwithdrawRecord',
					headers: {'Content-Type': 'application/x-www-form-urlencoded'},
					transformRequest: function (obj) {
						var str = [];
				        for(var p in obj)
				        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
				        return str.join("&");
					},
					data: {pageNo: $rootScope.pager.pageNo(), pageSize: $rootScope.pager.pageSize() ,withdrawType: hkType, beginTime: beginTime, endTime: endTime},
				});
			},
			getTransferInLog:function (hkType, beginTime, endTime) {
				return $http({
					method: 'POST',
					url: baseURI + '/memberinterface/getDepositRecord',
					headers: {'Content-Type': 'application/x-www-form-urlencoded'},
					transformRequest: function (obj) {
						var str = [];
				        for(var p in obj)
				        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
				        return str.join("&");
					},
					data: {pageNo: $rootScope.pager.pageNo(), pageSize: $rootScope.pager.pageSize() ,withdrawType: hkType, beginTime: beginTime, endTime: endTime},
				});
			},
			getSportOrder: function (cateType, status, beginTime, endTime) {
				return $http({
					method: 'POST',
					url: baseURI + '/memberinterface/getSportOrder',
					headers: {'Content-Type': 'application/x-www-form-urlencoded'},
					transformRequest: function (obj) {
						var str = [];
				        for(var p in obj)
				        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
				        return str.join("&");
					},
					data: {
						pageNo: $rootScope.pager.pageNo(),
						pageSize: $rootScope.pager.pageSize() ,
						beginTime: beginTime,
						endTime: endTime,
						cateType: cateType,
						status: status
					},
				});
			},
			getSlotOrder: function (cateType, beginTime, endTime) {
				return $http({
					method: 'POST',
					url: baseURI + '/memberinterface/getSlotOrder',
					headers: {'Content-Type': 'application/x-www-form-urlencoded'},
					transformRequest: function (obj) {
						var str = [];
				        for(var p in obj)
				        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
				        return str.join("&");
					},
					data: {
						pageNo: $rootScope.pager.pageNo(),
						pageSize: $rootScope.pager.pageSize() ,
						beginTime: beginTime,
						endTime: endTime,
						cateType: cateType,
					},
				});
			},
			getBankList: function () {
				return $http({
					method: 'GET',
					url: baseURI + '/memberinterface/getBankList',
				});
			},
			getThirdpayList: function () {
				return $http({
					method: 'GET',
					url: baseURI + '/memberinterface/getThreePayList',
				});
			},
			getBlanceMoney: function(type) {
				return $http({
					method: 'POST',
					url: baseURI + '/member/getBlanceMoney?flatName='+type,
					headers: {'Content-Type': 'application/x-www-form-urlencoded'},
					transformRequest: function (obj) {
						var str = [];
				        for(var p in obj)
				        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
				        return str.join("&");
					},
					data: {type: 0, t: '0.1962978259738235'}
				});
			},
			flatDoEduTo: function (money, flatName) {
				return $http({
					method: 'POST',
					url: baseURI + '/member/doEdu',
					headers: {'Content-Type': 'application/x-www-form-urlencoded'},
					transformRequest: function (obj) {
						var str = [];
				        for(var p in obj)
				        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
				        return str.join("&");
					},
					data: {wPoints: money, toFlatName: flatName, fromFlatName: 'account'}
				});
			},
			flatDoEduFrom: function (money, flatName) {
				return $http({
					method: 'POST',
					url: baseURI + '/member/doEdu',
					headers: {'Content-Type': 'application/x-www-form-urlencoded'},
					transformRequest: function (obj) {
						var str = [];
				        for(var p in obj)
				        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
				        return str.join("&");
					},
					data: {wPoints: money, fromFlatName: flatName, toFlatName: 'account'}
				});
			},
			postDepositMessage: function (hkMoney, hkCompanyBank, createTime, hkType, hkUserName, payNo, hour, min) {
				return $http({
					method: 'POST',
					url: baseURI + '/member/doBankPay',
					headers: {'Content-Type': 'application/x-www-form-urlencoded'},
					transformRequest: function (obj) {
						var str = [];
				        for(var p in obj)
				        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
				        return str.join("&");
					},
					data: {hkMoney: hkMoney,
							hkCompanyBank: hkCompanyBank,
							time: createTime,
							timeHour: hour,
							timeMinute: min,
							hkType: hkType,
							hkUserName: hkUserName,
							payNo: payNo,
							mobile: 1}
				});
			},
			postThirdpayDepositMessage: function (money, account, time, type, payNo) {
				return $http({
					method: 'POST',
					url: baseURI + '/m/member/doKjPay',
					headers: {'Content-Type': 'application/x-www-form-urlencoded'},
					transformRequest: function (obj) {
						var str = [];
				        for(var p in obj)
				        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
				        return str.join("&");
					},
					data: {hkMoney: money,
							hkUserName: account,
							createTime: time,
							payType: type,
							payNo: payNo}
				});
			},
			saveWithdrawal: function (money, userWithdrawPassword) {
				return $http({
					method: 'POST',
					url: baseURI + '/member/saveWithdraw',
					headers: {'Content-Type': 'application/x-www-form-urlencoded'},
					transformRequest: function (obj) {
						var str = [];
				        for(var p in obj)
				        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
				        return str.join("&");
					},
					data: {money: money, userWithdrawPassword: userWithdrawPassword}
				});
			},
			updatePwd: function (oldpwd, newpwd, confirmpwd) {
				return $http({
					method: 'POST',
					url: baseURI + '/member/doLoginPwd',
					headers: {'Content-Type': 'application/x-www-form-urlencoded'},
					transformRequest: function (obj) {
						var str = [];
				        for(var p in obj)
				        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
				        return str.join("&");
					},
					data: {oldPwd: oldpwd, newPwd: newpwd, renewPwd: confirmpwd}
				});
			},
			updateDepositPwd: function (oldpwd, newpwd, confirmpwd) {
				return $http({
					method: 'POST',
					url: baseURI + '/member/doWithdrawPwd',
					headers: {'Content-Type': 'application/x-www-form-urlencoded'},
					transformRequest: function (obj) {
						var str = [];
				        for(var p in obj)
				        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
				        return str.join("&");
					},
					data: {oldPwd: oldpwd, newPwd: newpwd, renewPwd: confirmpwd}
				});
			},
		};
	}]);