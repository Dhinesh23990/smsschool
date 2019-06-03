'use strict';
angular.module(appCon.appName).controller('studentaddattendancemgmtController',
					['$injector',  '$scope', 'DTOptionsBuilder',
        function($injector, $scope, DTOptionsBuilder) {
var $http,$state,$modal,$cookieStore,$filter;
$http = $injector.get('$http');
$state = $injector.get('$state');
$modal = $injector.get('$modal');
$cookieStore = $injector.get('$cookieStore');
$filter=$injector.get('$filter');
$scope.attendancetable = false;
$scope.attenddancedate=new Date();
$scope.morning= true;
$scope.afternoon=true;
  
  //list of student details
$scope.dtOptions = DTOptionsBuilder.newOptions()
						.withOption('lengthChange',false)
						.withOption('searching',false)
						.withOption('sort',false)
						.withOption('filter',false)
						.withOption("paging",false);
						
$scope.getAllStudent = function(){
	$scope.attendancetable = true;
	var date = $filter('date')($scope.attenddancedate, "dd/MM/yyyy");
	$http({
		url:appCon.globalCon.serviceBaseURL+"attendance/getStudentsByClassAndSection?schoolId="+$cookieStore.get('schoolId')+"&date="+date+"&standard="+$scope.className+"&section="+$scope.sectionName,
		method:'GET'
	}).success(function(result){
		var allstudentdata = result.responseVO.attendanceList;
		angular.forEach(allstudentdata,function(value,key){
				value.afternoon = true;
				value.morning = true;
				value.fullDay = false;
			});
		$scope.checked =false;
		$scope.checkeddisabledfullday = true;
		$scope.checkeddisabledmorning = false;
		$scope.checkeddisabledafternoon = false;
		$scope.checkedMorning = true;
		$scope.checkedAfternoon = true;
		console.log(allstudentdata);
		$scope.items = allstudentdata;
		
	});
}

$scope.clearAllattendance = function(){
	$scope.getAllStudent();
}

//class list
	
	
$http({
		url:appCon.globalCon.serviceBaseURL+"class/getAllClassBySchoolId?shoolId="+$cookieStore.get('schoolId')+"&pageIndex=0&pageSize="+appCon.globalCon.paginationSize,
		method:'GET'
	}).success(function(result){
		var classdata = result.responseVO.classVOs;		
		$scope.classes = [];
		angular.forEach(classdata,function(value, key){
			$scope.classes.push({value:value.className,key:value.id});
		});
	});
	//section list
	$http({
		url:appCon.globalCon.serviceBaseURL+"section/getAllSectionBySchoolId?shoolId="+$cookieStore.get('schoolId')+"&pageIndex=0&pageSize="+appCon.globalCon.paginationSize,
		method:'GET'
	}).success(function(result){
		var sectiondata = result.responseVO.sectionVOs;		
		$scope.sections = [];
		angular.forEach(sectiondata,function(value, key){
			$scope.sections.push({value:value.sectionName,key:value.id});
		});
	});

 
 $scope.checkalloneday = function(){
		var fulldaydata = $scope.items;
		console.log($scope.checked);
		if ($scope.checked) {
			angular.forEach(fulldaydata,function(value,key){
				value.fullDay = true;
				value.morning = false;
				value.afternoon = false;
			});
			$scope.checkedMorning = false;
            $scope.checkedAfternoon = false;
			$scope.checkeddisabledfullday = false;
			$scope.checkeddisabledmorning = true;
			$scope.checkeddisabledafternoon = true;
			$scope.items= fulldaydata;
		}
		else{
			angular.forEach(fulldaydata,function(value,key){
				value.fullDay = false;
				value.morning = true;
				value.afternoon = true;
			});
			$scope.checkeddisabledfullday = true;
			$scope.checkeddisabledmorning = false;
			$scope.checkeddisabledafternoon = false;
			$scope.checkedMorning = true;
            $scope.checkedAfternoon = true;
			$scope.items= fulldaydata;
			console.log($scope.items);
		}
		
	}
	
	$scope.checkallmorning = function(){
		
			var morningdata = $scope.items;
		if ($scope.checkedMorning) {
			
			angular.forEach(morningdata,function(value,key){
				value.fullDay = false;
				value.morning = true;
			});
            $scope.checked = false;
            $scope.checkedMorning = true;
			$scope.checkeddisabledfullday = true;
			$scope.checkeddisabledmorning = false;
			$scope.items= morningdata;
        } else {
			angular.forEach(morningdata,function(value,key){
				value.morning = false;
				if(value.afternoon == false)
					value.fullDay = true;
			});
			$scope.items= morningdata;
			
			$scope.checkeddisabledmorning = true;
			console.log($scope.checkedAfternoon);
			if(!$scope.checkedAfternoon){
				$scope.checked = true;
				$scope.checkeddisabledfullday = false;
			}
			else{
				$scope.checkeddisabledfullday = true;
			}
            $scope.checkedMorning = false;
        }
	}
	
	$scope.checkallafternone = function(){
		console.log($scope.checkedAfternoon);
		var afternoondata = $scope.items;
		if ($scope.checkedAfternoon) {
				angular.forEach(afternoondata,function(value,key){
				value.fullDay = false;
				value.afternoon = true;
			});
			$scope.items= afternoondata;
			$scope.checked = false;
			$scope.checkedAfternoon = true;
			$scope.checkeddisabledfullday = true;
			$scope.checkeddisabledafternoon = false;
        } else {
			angular.forEach(afternoondata,function(value,key){
				value.afternoon = false;
				if(value.morning == false)
					value.fullDay = true;
			});
			$scope.items= afternoondata;
			$scope.checkeddisabledafternoon = true;
			if(!$scope.checkedMorning){
				$scope.checked = true;
				$scope.checkeddisabledfullday = false;
			}
			else{
				$scope.checkeddisabledfullday = true;
			}
        	$scope.checkedAfternoon = false;
        }
	}
	
	$scope.saveattendance = function(){
		var date = $filter('date')($scope.attenddancedate, "yyyy-dd-MM");
		// assign_json = [];
		var assign_json = $scope.items;
		angular.forEach(assign_json,function(value,key){
			if(value.day == null){
				value.day = date;
			}
			value.schoolId = $cookieStore.get('schoolId');
			value.attendancePercenatge = "75 %";
			
		});
		
		$scope.attdet =  assign_json;
		console.log($scope.attdet);
		
		$injector.get('attendancemanagementservice')['saveBulkAttendance']($scope.attdet).then(        		
		 //Request Success 
		function(result) {
			console.log(result.data.statusFlag);
			if(result.data.statusFlag == 'Ok')
				$state.go('studentattendance');
			
		},		
		function(error){
			console.log('failure');
		});
	}


}]);


