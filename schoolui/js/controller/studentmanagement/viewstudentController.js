'use strict';

angular.module(appCon.appName).controller('viewstudentController',
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

$scope.viewstudentdata = function(){

	$injector.get('usermanagementservice')['viewStudent']().then(        		
	 //Request Success 
	function(result) {
		var studentdata = result.data.responseVO.studentVO;
		studentdata.dob =  $filter('date')(studentdata.dob, "dd-MM-yyyy"); 
		studentdata.admissionDate =  $filter('date')(studentdata.admissionDate, "dd-MM-yyyy");
		
		if(studentdata.caste == null)
			studentdata.caste = "---";
		
		if(studentdata.emergencyContactNumber == null)
			studentdata.emergencyContactNumber = "---";
		
		if(studentdata.studentImage.length > 0){
			$scope.defaultimage =false;
			$scope.schoolimage = true;
			$scope.image_id = appCon.globalCon.serviceBaseURL+"fileUpload/downloadFile?id="+studentdata.studentImage[0].blobId+"&directory="+studentdata.studentImage[0].directory+ "&authType=token&authorization="+$scope.login_usertoken;
		}else{
			$scope.defaultimage =true;
			$scope.schoolimage =false;
		}
		$scope.parentDob =  new Date(studentdata.parentDob);
		$scope.absentdays = studentdata.totalDays - studentdata.presentDays
		$scope.StudentVO = studentdata;
	},		
	function(error){
		console.log('failure');
	});
				
}

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



}]);




  
