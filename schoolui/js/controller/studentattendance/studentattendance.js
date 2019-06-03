'use strict';

angular.module(appCon.appName).controller('studentattendancemgmtController',
					['$injector',  '$scope', 'DTOptionsBuilder',
        function($injector, $scope, DTOptionsBuilder) {
var $http,$state,$modal,$cookieStore,$filter;
$http = $injector.get('$http');
$state = $injector.get('$state');
$modal = $injector.get('$modal');
$cookieStore = $injector.get('$cookieStore');
$filter = $injector.get('$filter');
$scope.hidesearchdet = true;
$scope.isOpen = false;
$scope.setlistandgridview = 'listview';
$scope.listandgridview ="studentview";
$scope.showsearchbutton = true;
$scope.studentlistviewdetails = true;
$scope.studentgridviewdetails = false;
$scope.studentsingleviewdetails = false;
$scope.day_view = false;
$scope.consolidated_view = false;
$scope.student_view = false;

$('input[name="birthdate"]').daterangepicker({
        singleDatePicker: true,
        showDropdowns: true,
		maxDate:new Date()
});

$('input[name="todate"]').daterangepicker({
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



$scope.listviewdet = function(){
	//if($scope.setlistandgridview != "listview" && listandgridview != 'studentview'){
		$scope.studentlistviewdetails = true;
		$scope.studentgridviewdetails = false;
		$scope.studentsingleviewdetails = false;
		$scope.day_view = false;
		$scope.consolidated_view = false;
		$scope.student_view = false;
		$scope.attendancegrid.$setPristine();
		$scope.stuattendance.$setPristine();
		$scope.attendancestudentview.$setPristine();
		$scope.attenddancedate = "";
		$scope.className = "";
		$scope.sectionName = "";
		$scope.fromdate = "";
		$scope.todate = "";
		$scope.conclassName = "";
		$scope.consectionName = "";
		$scope.studentId = "";
		$scope.stdviewfromdate = "";
		$scope.stdviewtodate = "";
		angular.element("#grid_view").removeClass("activeview");
		angular.element("#student_view").removeClass("activeview");
		angular.element("#list_view").removeClass("hoverview").removeClass("defaultcol");
		angular.element("#grid_view").removeClass("hoverview").removeClass("defaultcol");
		angular.element("#student_view").removeClass("hoverview").removeClass("defaultcol");
		angular.element("#list_view").addClass("activeview");
		angular.element("#grid_view").addClass("hoverview").addClass("defaultcol");
		angular.element("#student_view").addClass("hoverview").addClass("defaultcol");
		
}

$scope.gridviewdet = function(){
	
		$scope.studentlistviewdetails = false;
		$scope.studentgridviewdetails = true;
		$scope.studentsingleviewdetails = false;
		$scope.day_view = false;
		$scope.consolidated_view = false;
		$scope.student_view = false;
		$scope.attendancegrid.$setPristine();
		$scope.stuattendance.$setPristine();
		$scope.attendancestudentview.$setPristine();
		$scope.attenddancedate = "";
		$scope.className = "";
		$scope.sectionName = "";
		$scope.fromdate = "";
		$scope.todate = "";
		$scope.conclassName = "";
		$scope.consectionName = "";
		$scope.studentId = "";
		$scope.stdviewfromdate = "";
		$scope.stdviewtodate = "";
		angular.element("#grid_view").removeClass("hoverview").removeClass("defaultcol");
		angular.element("#list_view").removeClass("activeview");
		angular.element("#list_view").removeClass("hoverview").removeClass("defaultcol");
		angular.element("#student_view").removeClass("hoverview").removeClass("defaultcol");
		angular.element("#student_view").removeClass("activeview");
		angular.element("#grid_view").addClass("activeview");
		angular.element("#list_view").addClass("hoverview").addClass("defaultcol");
		angular.element("#student_view").addClass("hoverview").addClass("defaultcol");
	
}

$scope.studentviewdet = function(){
		$scope.studentlistviewdetails = false;
		$scope.studentgridviewdetails = false;
		$scope.studentsingleviewdetails = true;
		$scope.day_view = false;
		$scope.consolidated_view = false;
		$scope.student_view = false;
		$scope.attendancegrid.$setPristine();
		$scope.stuattendance.$setPristine();
		$scope.attendancestudentview.$setPristine();
		$scope.attenddancedate = "";
		$scope.className = "";
		$scope.sectionName = "";
		$scope.fromdate = "";
		$scope.todate = "";
		$scope.conclassName = "";
		$scope.consectionName = "";
		$scope.studentId = "";
		$scope.stdviewfromdate = "";
		$scope.stdviewtodate = "";
		angular.element("#grid_view").removeClass("hoverview").removeClass("defaultcol");
		angular.element("#list_view").removeClass("activeview");
		angular.element("#list_view").removeClass("hoverview").removeClass("defaultcol");
		angular.element("#grid_view").removeClass("activeview");
		angular.element("#student_view").removeClass("hoverview").removeClass("defaultcol");
		angular.element("#student_view").addClass("activeview");
		angular.element("#list_view").addClass("hoverview").addClass("defaultcol");
		angular.element("#grid_view").addClass("hoverview").addClass("defaultcol");
}
	
	$http({
		url:appCon.globalCon.serviceBaseURL+"class/getAllClassBySchoolId?shoolId="+$cookieStore.get('schoolId')+"&pageIndex=0&pageSize=100",
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
		url:appCon.globalCon.serviceBaseURL+"section/getAllSectionBySchoolId?shoolId="+$cookieStore.get('schoolId')+"&pageIndex=0&pageSize=100",
		method:'GET'
	}).success(function(result){
		var sectiondata = result.responseVO.sectionVOs;		
		$scope.sections = [];
		angular.forEach(sectiondata,function(value, key){
			$scope.sections.push({value:value.sectionName,key:value.id});
		});
	});


$scope.showclsandsec = function(){

	$scope.showsearchbutton = false;
	//$scope.showhidebutton = true;
}

$scope.returnsearch = function(){

	//$scope.showhidebutton = false;
	$scope.showsearchbutton = true;
}

//show by search
$scope.dtOptions = DTOptionsBuilder.newOptions()
						.withOption('lengthChange',false)
						.withOption('searching',false)
						.withOption('sort',false)
						.withOption('filter',false)
						.withOption("paging",false);
						
$scope.showbysearch = function(){
	$scope.attendancetable = true;
	$scope.date = $filter('date')($scope.attenddancedate, "yyyy/MM/dd");
	$http({
		url:appCon.globalCon.serviceBaseURL+"attendance/getAllStudentsByClassAndSectionAndDate?schoolId="+$cookieStore.get('schoolId')+"&date="+$scope.date+"&standard="+$scope.className+"&section="+$scope.sectionName,
		method:'GET'
	}).success(function(result){					
		var allstudentdata = result.responseVO.attendanceList;
		$scope.fulldays = false;
		$scope.mornings = false;
		$scope.afternoons = false;
		angular.forEach(allstudentdata,function(value,key){
			if(value.fullDay == true)
				$scope.fulldays = true;
			if(value.morning == true || value.afternoon == true){
				$scope.mornings = true;
				$scope.afternoons = true;
			}
			
		});
		$scope.day_view = true;
		$scope.consolidated_view = false;
		$scope.student_view = false;
		$scope.items = allstudentdata;
	});
}


$scope.conshowbysearch = function(){
	
	$scope.Fromdate = $filter('date')($scope.fromdate, "yyyy/MM/dd");
	$scope.Todate = $filter('date')($scope.todate, "yyyy/MM/dd");
	$http({
		url:appCon.globalCon.serviceBaseURL+"attendance/getAllStudentsByClassAndSectionAndDate?schoolId="+$cookieStore.get('schoolId')+"&date="+$scope.Fromdate+"&standard="+$scope.conclassName+"&section="+$scope.consectionName+"&endDate="+$scope.Todate,
		method:'GET'
	}).success(function(result){
		
		var allstudentdata = result.responseVO.attendanceList;
		console.log(allstudentdata);
		/*angular.forEach(allstudentdata,function(value,key){
			value.attenddancedate =  $filter('date')(value.attenddancedate, "dd-MM-yyyy");
		
		});*/
		$scope.day_view = false;
		$scope.consolidated_view = true;
		$scope.student_view = false;
		$scope.conitems = allstudentdata;
	});
}

$scope.studentshowbysearch = function(){
	
	$scope.Fromstdviewdate = $filter('date')($scope.stdviewfromdate, "yyyy/MM/dd");
	$scope.Tostdviewdate = $filter('date')($scope.stdviewtodate, "yyyy/MM/dd");
	$http({
		url:appCon.globalCon.serviceBaseURL+"attendance/getAllStudentsByStudentIdAndDate?schoolId="+$cookieStore.get('schoolId')+"&studentId="+$scope.studentId+"&date="+$scope.Fromstdviewdate+"&endDate="+$scope.Tostdviewdate,
		method:'GET'
	}).success(function(result){
		var allstudentdata = result.responseVO.attendanceList;
		$scope.studentIdetails = result.responseVO.studentVO;
		angular.forEach(allstudentdata,function(value,key){
			value.day =  $filter('date')(value.day, "dd-MM-yyyy");
		});
		$scope.day_view = false;
		$scope.consolidated_view = false;
		$scope.student_view = true;
		$scope.studentviewdetails = allstudentdata;
	});
}


}]);


