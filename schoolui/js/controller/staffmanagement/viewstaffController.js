'use strict';

angular.module(appCon.appName).controller('viewstaffController',
					['$injector',  '$scope', 'DTOptionsBuilder', '$stateParams',
        function($injector, $scope, DTOptionsBuilder, $stateParams) {
var $http,$state,$modal,$cookieStore,$filter,edit_studentid;

$http = $injector.get('$http');
$state = $injector.get('$state');
$modal = $injector.get('$modal');
$cookieStore = $injector.get('$cookieStore');
$filter = $injector.get('$filter');
$scope.isOpen = false;
$scope.login_usertoken = appCon.cookie.getItem('loginusertoken');
$scope.defaultimage =false;
$scope.schoolimage =false;

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

$scope.viewstaffdata = function(){

	$injector.get('usermanagementservice')['viewTeacher']().then(        		
	 //Request Success 
	function(result) {
		var teacherdata = result.data.responseVO.teacherVO,checkper = 0;
		teacherdata.dob =  $filter('date')(teacherdata.dob, "dd-MM-yyyy"); 
		teacherdata.dateOfJoining =  $filter('date')(teacherdata.dateOfJoining, "dd-MM-yyyy");
		if(teacherdata.caste == null)
			teacherdata.caste = "---";
		if(teacherdata.emergencyContactNumber == null)
			teacherdata.emergencyContactNumber = "---";
		if(teacherdata.religion == null)
			teacherdata.religion = "---";
		if(teacherdata.staffImage.length > 0){
			$scope.defaultimage =false;
			$scope.schoolimage = true;
			$scope.image_id = appCon.globalCon.serviceBaseURL+"fileUpload/downloadFile?id="+teacherdata.staffImage[0].blobId+"&directory="+teacherdata.staffImage[0].directory+ "&authType=token&authorization="+$scope.login_usertoken;
		}else{
			$scope.defaultimage =true;
			$scope.schoolimage =false;
		}
		if(teacherdata.totalStudentCount != 0)
			checkper=teacherdata.presentStudents/teacherdata.totalStudentCount;
		
		if(checkper == 0){
			teacherdata.studentAttendancePercentage = 0;
		}
		else{
			teacherdata.studentAttendancePercentage = checkper*100;
			/*var splitfloatvalue = checkper*100;
			console.log(splitfloatvalue);
			var ansper = splitfloatvalue.split('.');			
			teacherdata.studentAttendancePercentage = ansper[0];*/
		}
		$scope.TeacherVO = teacherdata;
	},		
	function(error){
		console.log('failure');
	});
			
		
	
}



}]);




  
