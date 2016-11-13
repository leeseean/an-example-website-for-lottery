angular.module('AppController')
	.controller('CpSSCController', ['$rootScope', '$scope', '$stateParams' , '$state' , 
	                                  'CaipiaoPeiyu', 'CaipiaoCreateOrder', 'CaipiaoInfo', 
	                                  'CaipiaoMultiGroupPreorder', 'substrFilter', 'ngDialog',
	                                  function ($rootScope, $scope, $stateParams, $state , 
	                                		  CaipiaoPeiyu, CaipiaoCreateOrder, CaipiaoInfo, 
	                                		  CaipiaoMultiGroupPreorder, substrFilter, ngDialog) {
		
		$rootScope.code = $stateParams['game_code'];
		var codeIds = {
				'CQSSC': {id: 4, name: '重庆时时彩'},
				'TJSSC': {id: 6, name: '天津时时彩'},
				'XJSSC': {id: 7, name: '新疆时时彩'}
		};
		var code2Ids = {
				zps: {id: 34, name: '主盘式'},
				yz: {id: 35, name: '一字'},
				ez: {id: 36, name: '二字'},
				sz: {id: 37, name: '三字'},
				yzgg: {id: 38, name: '一字过关'},
				kd: {id: 39, name: '跨度'},
				zxs: {id: 40, name: '组选三'},
				zxl: {id: 41, name: '组选六'},
				fszh: {id: 42, name: '复式组合'},
				qt: {id: 43, name: '其他'}
		};
		
		// code
		$rootScope.codeID = codeIds[$rootScope.code]['id'];
		$rootScope.codeLabel = codeIds[$rootScope.code]['name'];
		
		// code2
		$rootScope.code2 = $stateParams['pankou'];
		$rootScope.code2ID = code2Ids[$rootScope.code2]['id'];
		$rootScope.code2Label = code2Ids[$rootScope.code2]['name'];
		
		// type
		$rootScope.subType = $scope.subType = $stateParams.type;
		console.log($scope.subType);
		
		$rootScope.title = $rootScope.codeLabel + " - " + $rootScope.code2Label;
		
		$scope.selectedPeiyu = {};
		
		$scope.selectedNumber = {};
		
		$scope.groupSelectedNumber = {};

		// 默认位置
		$scope.weizhi = 'QSW';
		
		if ($scope.code2 == 'sz') {
			if ($scope.subType == 'szzh') {
				$scope.numbers = ['000' ,'001' ,'002' ,'003' ,'004' ,'005' ,'006' ,'007' ,'008' ,'009' ,'011' ,'012' ,'013' ,'014' ,'015' ,'016' ,'017' ,'018' ,'019' ,'022' ,'023' ,'024' ,'025' ,'026' ,'027' ,'028' ,'029' ,'033' ,'034' ,'035' ,'036' ,'037' ,'038' ,'039' ,'044' ,'045' ,'046' ,'047' ,'048' ,'049' ,'055' ,'056' ,'057' ,'058' ,'059' ,'066' ,'067' ,'068' ,'069' ,'077' ,'078' ,'079' ,'088' ,'089' ,'099' ,'111' ,'112' ,'113' ,'114' ,'115' ,'116' ,'117' ,'118' ,'119' ,'122' ,'123' ,'124' ,'125' ,'126' ,'127' ,'128' ,'129' ,'133' ,'134' ,'135' ,'136' ,'137' ,'138' ,'139' ,'144' ,'145' ,'146' ,'147' ,'148' ,'149' ,'155' ,'156' ,'157' ,'158' ,'159' ,'166' ,'167' ,'168' ,'169' ,'177' ,'178' ,'179' ,'188' ,'189' ,'199' ,'222' ,'223' ,'224' ,'225' ,'226' ,'227' ,'228' ,'229' ,'233' ,'234' ,'235' ,'236' ,'237' ,'238' ,'239' ,'244' ,'245' ,'246' ,'247' ,'248' ,'249' ,'255' ,'256' ,'257' ,'258' ,'259' ,'266' ,'267' ,'268' ,'269' ,'277' ,'278' ,'279' ,'288' ,'289' ,'299' ,'333' ,'334' ,'335' ,'336' ,'337' ,'338' ,'339' ,'344' ,'345' ,'346' ,'347' ,'348' ,'349' ,'355' ,'356' ,'357' ,'358' ,'359' ,'366' ,'367' ,'368' ,'369' ,'377' ,'378' ,'379' ,'388' ,'389' ,'399' ,'444' ,'445' ,'446' ,'447' ,'448' ,'449' ,'455' ,'456' ,'457' ,'458' ,'459' ,'466' ,'467' ,'468' ,'469' ,'477' ,'478' ,'479' ,'488' ,'489' ,'499' ,'555' ,'556' ,'557' ,'558' ,'559' ,'566' ,'567' ,'568' ,'569' ,'577' ,'578' ,'579' ,'588' ,'589' ,'599' ,'666' ,'667' ,'668' ,'669' ,'677' ,'678' ,'679' ,'688' ,'689' ,'699' ,'777' ,'778' ,'779' ,'788' ,'789' ,'799' ,'888' ,'889' ,'899' ,'999' ];
			}
			else if ($scope.subType == 'bsgdw') {
				$scope.numbers = ['000' ,'001' ,'002' ,'003' ,'004' ,'005' ,'006' ,'007' ,'008' ,'009' ,'010' ,'011' ,'012' ,'013' ,'014' ,'015' ,'016' ,'017' ,'018' ,'019' ,'020' ,'021' ,'022' ,'023' ,'024' ,'025' ,'026' ,'027' ,'028' ,'029' ,'030' ,'031' ,'032' ,'033' ,'034' ,'035' ,'036' ,'037' ,'038' ,'039' ,'040' ,'041' ,'042' ,'043' ,'044' ,'045' ,'046' ,'047' ,'048' ,'049' ,'050' ,'051' ,'052' ,'053' ,'054' ,'055' ,'056' ,'057' ,'058' ,'059' ,'060' ,'061' ,'062' ,'063' ,'064' ,'065' ,'066' ,'067' ,'068' ,'069' ,'070' ,'071' ,'072' ,'073' ,'074' ,'075' ,'076' ,'077' ,'078' ,'079' ,'080' ,'081' ,'082' ,'083' ,'084' ,'085' ,'086' ,'087' ,'088' ,'089' ,'090' ,'091' ,'092' ,'093' ,'094' ,'095' ,'096' ,'097' ,'098' ,'099' ,'100' ,'101' ,'102' ,'103' ,'104' ,'105' ,'106' ,'107' ,'108' ,'109' ,'110' ,'111' ,'112' ,'113' ,'114' ,'115' ,'116' ,'117' ,'118' ,'119' ,'120' ,'121' ,'122' ,'123' ,'124' ,'125' ,'126' ,'127' ,'128' ,'129' ,'130' ,'131' ,'132' ,'133' ,'134' ,'135' ,'136' ,'137' ,'138' ,'139' ,'140' ,'141' ,'142' ,'143' ,'144' ,'145' ,'146' ,'147' ,'148' ,'149' ,'150' ,'151' ,'152' ,'153' ,'154' ,'155' ,'156' ,'157' ,'158' ,'159' ,'160' ,'161' ,'162' ,'163' ,'164' ,'165' ,'166' ,'167' ,'168' ,'169' ,'170' ,'171' ,'172' ,'173' ,'174' ,'175' ,'176' ,'177' ,'178' ,'179' ,'180' ,'181' ,'182' ,'183' ,'184' ,'185' ,'186' ,'187' ,'188' ,'189' ,'190' ,'191' ,'192' ,'193' ,'194' ,'195' ,'196' ,'197' ,'198' ,'199' ,'200' ,'201' ,'202' ,'203' ,'204' ,'205' ,'206' ,'207' ,'208' ,'209' ,'210' ,'211' ,'212' ,'213' ,'214' ,'215' ,'216' ,'217' ,'218' ,'219' ,'220' ,'221' ,'222' ,'223' ,'224' ,'225' ,'226' ,'227' ,'228' ,'229' ,'230' ,'231' ,'232' ,'233' ,'234' ,'235' ,'236' ,'237' ,'238' ,'239' ,'240' ,'241' ,'242' ,'243' ,'244' ,'245' ,'246' ,'247' ,'248' ,'249' ,'250' ,'251' ,'252' ,'253' ,'254' ,'255' ,'256' ,'257' ,'258' ,'259' ,'260' ,'261' ,'262' ,'263' ,'264' ,'265' ,'266' ,'267' ,'268' ,'269' ,'270' ,'271' ,'272' ,'273' ,'274' ,'275' ,'276' ,'277' ,'278' ,'279' ,'280' ,'281' ,'282' ,'283' ,'284' ,'285' ,'286' ,'287' ,'288' ,'289' ,'290' ,'291' ,'292' ,'293' ,'294' ,'295' ,'296' ,'297' ,'298' ,'299' ,'300' ,'301' ,'302' ,'303' ,'304' ,'305' ,'306' ,'307' ,'308' ,'309' ,'310' ,'311' ,'312' ,'313' ,'314' ,'315' ,'316' ,'317' ,'318' ,'319' ,'320' ,'321' ,'322' ,'323' ,'324' ,'325' ,'326' ,'327' ,'328' ,'329' ,'330' ,'331' ,'332' ,'333' ,'334' ,'335' ,'336' ,'337' ,'338' ,'339' ,'340' ,'341' ,'342' ,'343' ,'344' ,'345' ,'346' ,'347' ,'348' ,'349' ,'350' ,'351' ,'352' ,'353' ,'354' ,'355' ,'356' ,'357' ,'358' ,'359' ,'360' ,'361' ,'362' ,'363' ,'364' ,'365' ,'366' ,'367' ,'368' ,'369' ,'370' ,'371' ,'372' ,'373' ,'374' ,'375' ,'376' ,'377' ,'378' ,'379' ,'380' ,'381' ,'382' ,'383' ,'384' ,'385' ,'386' ,'387' ,'388' ,'389' ,'390' ,'391' ,'392' ,'393' ,'394' ,'395' ,'396' ,'397' ,'398' ,'399' ,'400' ,'401' ,'402' ,'403' ,'404' ,'405' ,'406' ,'407' ,'408' ,'409' ,'410' ,'411' ,'412' ,'413' ,'414' ,'415' ,'416' ,'417' ,'418' ,'419' ,'420' ,'421' ,'422' ,'423' ,'424' ,'425' ,'426' ,'427' ,'428' ,'429' ,'430' ,'431' ,'432' ,'433' ,'434' ,'435' ,'436' ,'437' ,'438' ,'439' ,'440' ,'441' ,'442' ,'443' ,'444' ,'445' ,'446' ,'447' ,'448' ,'449' ,'450' ,'451' ,'452' ,'453' ,'454' ,'455' ,'456' ,'457' ,'458' ,'459' ,'460' ,'461' ,'462' ,'463' ,'464' ,'465' ,'466' ,'467' ,'468' ,'469' ,'470' ,'471' ,'472' ,'473' ,'474' ,'475' ,'476' ,'477' ,'478' ,'479' ,'480' ,'481' ,'482' ,'483' ,'484' ,'485' ,'486' ,'487' ,'488' ,'489' ,'490' ,'491' ,'492' ,'493' ,'494' ,'495' ,'496' ,'497' ,'498' ,'499' ,'500' ,'501' ,'502' ,'503' ,'504' ,'505' ,'506' ,'507' ,'508' ,'509' ,'510' ,'511' ,'512' ,'513' ,'514' ,'515' ,'516' ,'517' ,'518' ,'519' ,'520' ,'521' ,'522' ,'523' ,'524' ,'525' ,'526' ,'527' ,'528' ,'529' ,'530' ,'531' ,'532' ,'533' ,'534' ,'535' ,'536' ,'537' ,'538' ,'539' ,'540' ,'541' ,'542' ,'543' ,'544' ,'545' ,'546' ,'547' ,'548' ,'549' ,'550' ,'551' ,'552' ,'553' ,'554' ,'555' ,'556' ,'557' ,'558' ,'559' ,'560' ,'561' ,'562' ,'563' ,'564' ,'565' ,'566' ,'567' ,'568' ,'569' ,'570' ,'571' ,'572' ,'573' ,'574' ,'575' ,'576' ,'577' ,'578' ,'579' ,'580' ,'581' ,'582' ,'583' ,'584' ,'585' ,'586' ,'587' ,'588' ,'589' ,'590' ,'591' ,'592' ,'593' ,'594' ,'595' ,'596' ,'597' ,'598' ,'599' ,'600' ,'601' ,'602' ,'603' ,'604' ,'605' ,'606' ,'607' ,'608' ,'609' ,'610' ,'611' ,'612' ,'613' ,'614' ,'615' ,'616' ,'617' ,'618' ,'619' ,'620' ,'621' ,'622' ,'623' ,'624' ,'625' ,'626' ,'627' ,'628' ,'629' ,'630' ,'631' ,'632' ,'633' ,'634' ,'635' ,'636' ,'637' ,'638' ,'639' ,'640' ,'641' ,'642' ,'643' ,'644' ,'645' ,'646' ,'647' ,'648' ,'649' ,'650' ,'651' ,'652' ,'653' ,'654' ,'655' ,'656' ,'657' ,'658' ,'659' ,'660' ,'661' ,'662' ,'663' ,'664' ,'665' ,'666' ,'667' ,'668' ,'669' ,'670' ,'671' ,'672' ,'673' ,'674' ,'675' ,'676' ,'677' ,'678' ,'679' ,'680' ,'681' ,'682' ,'683' ,'684' ,'685' ,'686' ,'687' ,'688' ,'689' ,'690' ,'691' ,'692' ,'693' ,'694' ,'695' ,'696' ,'697' ,'698' ,'699' ,'700' ,'701' ,'702' ,'703' ,'704' ,'705' ,'706' ,'707' ,'708' ,'709' ,'710' ,'711' ,'712' ,'713' ,'714' ,'715' ,'716' ,'717' ,'718' ,'719' ,'720' ,'721' ,'722' ,'723' ,'724' ,'725' ,'726' ,'727' ,'728' ,'729' ,'730' ,'731' ,'732' ,'733' ,'734' ,'735' ,'736' ,'737' ,'738' ,'739' ,'740' ,'741' ,'742' ,'743' ,'744' ,'745' ,'746' ,'747' ,'748' ,'749' ,'750' ,'751' ,'752' ,'753' ,'754' ,'755' ,'756' ,'757' ,'758' ,'759' ,'760' ,'761' ,'762' ,'763' ,'764' ,'765' ,'766' ,'767' ,'768' ,'769' ,'770' ,'771' ,'772' ,'773' ,'774' ,'775' ,'776' ,'777' ,'778' ,'779' ,'780' ,'781' ,'782' ,'783' ,'784' ,'785' ,'786' ,'787' ,'788' ,'789' ,'790' ,'791' ,'792' ,'793' ,'794' ,'795' ,'796' ,'797' ,'798' ,'799' ,'800' ,'801' ,'802' ,'803' ,'804' ,'805' ,'806' ,'807' ,'808' ,'809' ,'810' ,'811' ,'812' ,'813' ,'814' ,'815' ,'816' ,'817' ,'818' ,'819' ,'820' ,'821' ,'822' ,'823' ,'824' ,'825' ,'826' ,'827' ,'828' ,'829' ,'830' ,'831' ,'832' ,'833' ,'834' ,'835' ,'836' ,'837' ,'838' ,'839' ,'840' ,'841' ,'842' ,'843' ,'844' ,'845' ,'846' ,'847' ,'848' ,'849' ,'850' ,'851' ,'852' ,'853' ,'854' ,'855' ,'856' ,'857' ,'858' ,'859' ,'860' ,'861' ,'862' ,'863' ,'864' ,'865' ,'866' ,'867' ,'868' ,'869' ,'870' ,'871' ,'872' ,'873' ,'874' ,'875' ,'876' ,'877' ,'878' ,'879' ,'880' ,'881' ,'882' ,'883' ,'884' ,'885' ,'886' ,'887' ,'888' ,'889' ,'890' ,'891' ,'892' ,'893' ,'894' ,'895' ,'896' ,'897' ,'898' ,'899' ,'900' ,'901' ,'902' ,'903' ,'904' ,'905' ,'906' ,'907' ,'908' ,'909' ,'910' ,'911' ,'912' ,'913' ,'914' ,'915' ,'916' ,'917' ,'918' ,'919' ,'920' ,'921' ,'922' ,'923' ,'924' ,'925' ,'926' ,'927' ,'928' ,'929' ,'930' ,'931' ,'932' ,'933' ,'934' ,'935' ,'936' ,'937' ,'938' ,'939' ,'940' ,'941' ,'942' ,'943' ,'944' ,'945' ,'946' ,'947' ,'948' ,'949' ,'950' ,'951' ,'952' ,'953' ,'954' ,'955' ,'956' ,'957' ,'958' ,'959' ,'960' ,'961' ,'962' ,'963' ,'964' ,'965' ,'966' ,'967' ,'968' ,'969' ,'970' ,'971' ,'972' ,'973' ,'974' ,'975' ,'976' ,'977' ,'978' ,'979' ,'980' ,'981' ,'982' ,'983' ,'984' ,'985' ,'986' ,'987' ,'988' ,'989' ,'990' ,'991' ,'992' ,'993' ,'994' ,'995' ,'996' ,'997' ,'998' ,'999'];
			}
		}
		
		$scope.$watch('selectedPeiyu', function (newval) {
			console.log(newval);
		}, true);
		
		// confirm
		$scope.confirm = function (cb) {
			// 确认回调函数
			if (!cb) {
				cb = function () {
					$state.go('caipiao.confirm', {flag: 'normal'})
				};
			}
			if (!$rootScope.isLogged() ) {
				alert('请先登录');
				$rootScope.redirectToLogin();
				return ;
			}
			else if (angular.isEmpty($scope.selectedPeiyu)) {
				return;
			}
			
			CaipiaoCreateOrder.resetItems();
			angular.forEach($scope.selectedPeiyu ,function (index, id) {
				if ($scope.selectedPeiyu[index] == false) return;
				for (var i in peiyu) {
					var item = peiyu[i];
					if (item['id'] == id) {
						CaipiaoCreateOrder.setItem({
							uid: id,
							rate: item['pl'],
							label: $rootScope.codeLabel,
							label2: $rootScope.code2Label,
							xzje: $rootScope.xzje,
							number: item['number']
						});
					}
 				}
			});

			cb();
		};
		
		$scope.reset = function () {
			$rootScope.selectedPeiyu = $scope.selectedPeiyu = {};
			$scope.selectedNumber = {};
			CaipiaoCreateOrder.resetItems();
			angular.forEach(angular.element(document.getElementsByClassName('bg-hrec-red')), function (element) {
				angular.element(element).removeClass('bg-hrec-red').addClass('bg-hrec-gray');
			});
			
			for (var shengxiao in $scope.hxCpSelected) {
				$scope.hxCpSelected[shengxiao] = false;
			}
		};
		
		// 设置赔率
		$scope.peiyu = {};
		var peiyu;
		CaipiaoPeiyu($rootScope.code.toUpperCase(), $rootScope.code2.toUpperCase(), $rootScope.subType.toUpperCase()).then(function (res) {
			peiyu = res['data']['datas'];
			for (key in peiyu) {
				peiyuitem = peiyu[key];
				$scope.peiyu[peiyuitem['id']] = peiyuitem['pl'];
			}
		});
		
		// 获取彩票资料
		$rootScope.caipiaoInfo = {
				lastResult : {
					last_qs: '20160701',
					last_result: [49,49,49,49,49],
				}
		};
		$scope.kaijiangSecond = 0;
		$scope.shengXiaoMap = {};
		$scope.hxCpSelected = {};
		
		// 获取彩票信息
		CaipiaoInfo($rootScope.codeID, $rootScope.code2ID).then(function (res) {
			var data = res['data'];
			$scope.shengXiaoMap = data['shengXiaoMap'];
			var hxCpSelected = {};
			for (var shengxiao in $scope.shengXiaoMap) {
				$scope.hxCpSelected[shengxiao] = false;
			}
			$rootScope.caipiaoInfo = data;
		});
		
		if ($scope.code2 == 'yzgg') {
			
			$scope.confirm = function (cb) {
				// 确认回调函数
				if (!cb) {
					cb = function () {
						$state.go('caipiao.confirm')
					};
				}
				if (!$rootScope.isLogged() ) {
					alert('请先登录');
					$rootScope.redirectToLogin();
					return ;
				}
				else if (angular.isEmpty($scope.selectedPeiyu)) {
					return;
				}
				
				var selectedPeiyu = {};
				var bwNum = swNum = gwNum = '';
				for (var key in $scope.selectedPeiyu) {
					if ($scope.selectedPeiyu[key] == false) continue;
					if (key.indexOf('BOXX') != -1) bwNum = key+'-'+$scope.selectedPeiyu[key];
					else if (key.indexOf('SXOX') != -1) swNum = key+'-'+$scope.selectedPeiyu[key];
					else if (key.indexOf('GXXO') != -1) gwNum = key+'-'+$scope.selectedPeiyu[key];
				}
				if (Object.keys($scope.selectedPeiyu).length < 2) {
					ngDialog.open({
						template: '<p>至少需要选择两组号码！</p>',
					});
					return ;
				}
				//gameCode, typeCode, cateCode, multilen, bwNum, swNum, gwNum
				$rootScope.subType = $scope.weizhi;
				CaipiaoMultiGroupPreorder.YZGG($scope.code.toUpperCase(), $scope.code2.toUpperCase(), $scope.weizhi.toUpperCase(), 88, bwNum, swNum, gwNum)
					.then(function (res) {
						if (typeof res['data'] == 'object') {
							var token = res['data']['token'];
							CaipiaoCreateOrder.resetItems();
							for (var index in res['data']['orderList']) {
								CaipiaoCreateOrder.resetItems();
								CaipiaoCreateOrder.setItem({
									'preorder': res['data']['orderList'][index],
									'xzje': $rootScope.xzje,
									'token': token
								});
							}
							$state.go('caipiao.confirm.hx', {flag: 'yzgg'});
						}
				});
			};
		}
		else if ($scope.code2 == 'zxs') {
			$scope.confirm = function () {

				if (!$rootScope.isLogged() ) {
					alert('请先登录');
					$rootScope.redirectToLogin();
					return ;
				}
				
				var numbers = [];
				for (var number in $scope.selectedNumber) {
					if ($scope.selectedNumber[number]) {
						numbers.push(number);
					}
				}
				if (numbers.length < 5) {
					ngDialog.open({
						template: '<p>选择的号码最小为5个！</p>',
					});
				}
				else {
					$rootScope.subType = $scope.weizhi;
					CaipiaoMultiGroupPreorder.ZXS($scope.code.toUpperCase(), $scope.code2.toUpperCase(), $scope.weizhi.toUpperCase(), 3, numbers.join(','))
						.then(function (res) {
							if (typeof res['data'] == 'object') {
								var token = res['data']['token'];
								CaipiaoCreateOrder.resetItems();
								for (var index in res['data']['orderList']) {
									CaipiaoCreateOrder.setItem({
										'preorder': res['data']['orderList'][index],
										'xzje': $rootScope.xzje,
										'token': token
									});
								}
								$state.go('caipiao.confirm.hx', {flag: 'group'});
							}
					});
				}
			};
		}
		else if ($scope.code2 == 'zxl') {//组选六
			$scope.confirm = function () {

				if (!$rootScope.isLogged() ) {
					alert('请先登录');
					$rootScope.redirectToLogin();
					return ;
				}
				
				var numbers = [];
				for (var number in $scope.selectedNumber) {
					if ($scope.selectedNumber[number]) {
						numbers.push(number);
					}
				}
				if (numbers.length < 4) {
					ngDialog.open({
						template: '<p>选择的号码最小为4个！</p>',
					});
				} else if(numbers.length > 8){
					ngDialog.open({
						template: '<p>选择的号码不能大于8个！</p>',
					});
				} else {
					$rootScope.subType = $scope.weizhi;
					CaipiaoMultiGroupPreorder.ZXS($scope.code.toUpperCase(), $scope.code2.toUpperCase(), $scope.weizhi.toUpperCase(), 6, numbers.join(','))
						.then(function (res) {
							if (typeof res['data'] == 'object') {
								var token = res['data']['token'];
								CaipiaoCreateOrder.resetItems();
								for (var index in res['data']['orderList']) {
									CaipiaoCreateOrder.setItem({
										'preorder': res['data']['orderList'][index],
										'xzje': $rootScope.xzje,
										'token': token
									});
								}
								$state.go('caipiao.confirm.hx', {flag: 'group'});
							}
					});
				}
			}
		}
		else if ($scope.code2 == 'fszh') {
			
			$scope.confirm = function () {

				var groupNumbers = {};
				var totalLen = 0;
				var groupLen = {
						'bwNum': 0,
						'gwNum': 0,
						'swNum': 0,
				};
				
				for( var groupName in $scope.groupSelectedNumber ) {
					var numbers = [];
					for (var number in $scope.groupSelectedNumber[groupName]) {
						if ($scope.groupSelectedNumber[groupName][number]) {
							numbers.push(number);
							totalLen += 1;
							groupLen[groupName] += 1;
						}
					}
					groupNumbers[groupName] = numbers;
				}
				
				var groupNames = {
						'bwNum': '百位',
						'gwNum': '个位',
						'swNum': '十位',
				};
				
				if (totalLen < 9 ) {
					var alertMsg = '';
					for (var groupName in groupLen) {
						if (groupLen[groupName] < 1) {
							alertMsg += groupNames[groupName];
						}
					}
					if (alertMsg == '') {
						ngDialog.open({
							template: '<p>三个位数选取号码不得少於9个，请继续选择</p>',
						});
					}
					else {
						ngDialog.open({
							template: '<p>'+alertMsg + ' 未选择号码，复式组合下注每位数最少选一个号码</p>',
						});
					}
				}else if (totalLen > 21 ) {
					ngDialog.open({
						template: '<p>三个位数选取号码不得多於21个</p>',
					});
				}else {
					//gameCode, typeCode, cateCode, multilen, bwNum, swNum, gwNum
					$rootScope.subType = $scope.weizhi;
					CaipiaoMultiGroupPreorder.YZGG($scope.code.toUpperCase(), 
							$scope.code2.toUpperCase(), 
							$scope.weizhi.toUpperCase(), 
							99, 
							groupNumbers['bwNum'].join(','), 
							groupNumbers['swNum'].join(','), 
							groupNumbers['gwNum'].join(','))
						.then(function (res) {
							if (typeof res['data'] == 'object') {
								var token = res['data']['token'];
								for (var index in res['data']['orderList']) {
									CaipiaoCreateOrder.resetItems();
									CaipiaoCreateOrder.setItem({
										'preorder': res['data']['orderList'][index],
										'xzje': $rootScope.xzje,
										'token': token
									});
								}
								$state.go('caipiao.confirm.hx', {flag: 'group'});
							}
					});
				}
			};
		}
		
	}]);