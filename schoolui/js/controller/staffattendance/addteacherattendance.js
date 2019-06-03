'use strict';
angular.module(appCon.appName).controller('teacheraddattendancemgmtController',
					['$injector',  '$scope', 'DTOptionsBuilder',
        function($injector, $scope, DTOptionsBuilder) {
var $http,$state,$modal,$cookieStore,$filter;
$http = $injector.get('$http');
$state = $injector.get('$state');
$modal = $injector.get('$modal');
$cookieStore = $injector.get('$cookieStore');
$filter=$injector.get('$filter');
$scope.attendancetable = false;
$scope.morning= true;
$scope.afternoon=true;

$('input[name="birthdate"]').daterangepicker({
        singleDatePicker: true,
        showDropdowns: true,
		maxDate:new Date()
});

$scope.gotodashboard = function()
{		
	angular.element("#slimScrollDiv").find('ul li:first').remove('active');
	angular.element("#slimScrollDiv").find('ul li:first').addClass('active');
	angular.element("#slimScrollDiv").find('ul').parent('li').removeClass('active');
	angular.element("#slimScrollDiv").find('ul li').removeClass('open');
	//angular.element("#ddremove").style.display = 'none';
	var yourUl = document.getElementById("ddremove");
	yourUl.style.display = yourUl.style.display === 'none' ? '' : 'none';
	$state.go('dashboard');		
}

$scope.dayoptions= [{value:"Morning",key:"Morning"},{value:"Afternoon",key:"Afternoon"}];
$scope.day = "Morning";
$scope.dtOptions = DTOptionsBuilder.newOptions()
						.withOption('lengthChange',false)
						.withOption('searching',false)
						.withOption('sort',false)
						.withOption('filter',false)
						.withOption("paging",false);
						
$http({
		url:appCon.globalCon.serviceBaseURL+"teacher/getAllTeacher?schoolId="+$cookieStore.get('schoolId')+'&pageIndex=0&pageSize=100',
		method:'GET'
	}).success(function(result){
		var allTeacherdata = result.responseVO.teacherList;
		$scope.teachernames = allTeacherdata;
	});
						
$scope.getAllTeacheratt = function(){
	console.log($scope.teachername);
	
	if(angular.isUndefined($scope.teachername) || $scope.teachername === null){
		$scope.teachername="ALL_TEACHERS";
	}
	console.log($scope.attenddancedate);
	var date = $filter('date')($scope.attenddancedate, "dd/MM/yyyy");
	console.log($scope.day);
	$http({
		url:appCon.globalCon.serviceBaseURL+"staffAttendance/getTeachersByClassAndSection?schoolId="+$cookieStore.get('schoolId')+"&date="+date+"&staffId="+$scope.teachername+"&attendanceDayAfter="+$scope.day,
		method:'GET'
	}).success(function(result){
			console.log(result);
			$scope.attendancetable = true;
			var allstaffdata = result.responseVO.staffAttendanceList;
			angular.forEach(allstaffdata,function(value,key){
				value.morning = true;
			});
			$scope.checked = true;
			$scope.items = allstaffdata;
		//var allTeacherdata = result.responseVO.teacherList;
		//$scope.teachernames =allTeacherdata.staffName;
	});
}



$scope.clearAllattendance = function(){
	$scope.step1.$setPristine();
	$scope.className ="";
	$scope.staffName ="";
	$scope.attendancetable = false;
}

 $scope.checkalloneday = function(){
		var fulldaydata = $scope.items;
		if($scope.checked){
			angular.forEach(fulldaydata,function(value,key){
				value.morning = true;
			});
			$scope.items= fulldaydata;
		}
		else{
			angular.forEach(fulldaydata,function(value,key){
				value.morning = false;
			});
			$scope.items= fulldaydata;
		}
		
	}
	
$scope.saveattendance = function(){
	var stdate = new Date($scope.attenddancedate)
	var date = $filter('date')(stdate, "yyyy-MM-dd");
	var assign_json = $scope.items;
	angular.forEach(assign_json,function(value,key){
		
		if(value.day == null){
			value.day = date;
		}
		value.dayAfter =$scope.day;
		value.schoolId = $cookieStore.get('schoolId');
		if(value.selectLeaveMasterId){
			value.presentDays = value.presentDays + 0;
		}else{
			value.presentDays = value.presentDays + 0.5;
		}
		value.schoolId = $cookieStore.get('schoolId');
		value.totalDays = value.totalDays+0.5;
		var calper = (value.presentDays/value.totalDays)*100;
		value.attendancePercenatge = calper;
		delete value.leaveMasterVO;
	});
	$scope.attdet =  assign_json;
	$injector.get('staffattendanceservice')['saveBulkStaffAttendance']($scope.attdet).then(        		
	 //Request Success 
	function(result) {
		console.log(result.data.statusFlag);
		if(result.data.statusFlag == 'Ok')
			$state.go('teacherattendance');
		
	},		
	function(error){
		console.log('failure');
	});
}


}]);


