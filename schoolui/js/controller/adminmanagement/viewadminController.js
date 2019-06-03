'use strict';

angular.module(appCon.appName).controller('viewadminController',
					['$injector',  '$scope', 'DTOptionsBuilder', '$stateParams', 'md5',
        function($injector, $scope, DTOptionsBuilder, $stateParams, md5) {
var $http,$state,$modal,$cookieStore,$filter,edit_studentid;

$http = $injector.get('$http');
$state = $injector.get('$state');
$modal = $injector.get('$modal');
$cookieStore = $injector.get('$cookieStore');
$filter = $injector.get('$filter');
$scope.isOpen = false;
var profiledata = $cookieStore.get('userProfile');
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



$scope.viewadmindata = function(){
	
	$injector.get('usermanagementservice')['viewAdmin']().then(        		
	 //Request Success 
	function(result) {
		var admindata = result.data.responseVO.adminVO;
		admindata.dob =  $filter('date')(admindata.dob, "dd-MM-yyyy"); 
		admindata.dateOfJoining =  $filter('date')(admindata.dateOfJoining, "dd-MM-yyyy");
		if(admindata.casteName == null)
			admindata.casteName= "---";
		if(admindata.emergencyContactNumber == null)
			admindata.emergencyContactNumber = "---";
		if(admindata.religionName == null)
			admindata.religionName = "---";
		if(admindata.adminImage.length > 0){
			$scope.defaultimage =false;
			$scope.schoolimage = true;
			$scope.image_id = appCon.globalCon.serviceBaseURL+"fileUpload/downloadFile?id="+admindata.adminImage[0].blobId+"&directory="+admindata.adminImage[0].directory+ "&authType=token&authorization="+$scope.login_usertoken;
		}else{
			$scope.defaultimage =true;
			$scope.schoolimage =false;
		}
		
		$scope.AdminVO = admindata;
	},		
	function(error){
		console.log('failure');
	});
	
	$http({
		url:appCon.globalCon.serviceBaseURL+"dashboard/getAllDashboardCount?schoolId="+$cookieStore.get('schoolId'),
		method:'GET'
	}).success(function(result){
		 var count_det	= result.responseVO;
		 $scope.schoolsmsdata = result.responseVO.SchoolVO;
		 $scope.overallper = $filter('number')((count_det.PresentStudents/count_det.StudentCount)*100, 0);
		 $scope.countdata = count_det;
	});
		
}

$scope.changepassword = function(){
	$scope.errormsg = false;
	var currentPassword = md5.createHash($scope.currentPassword),
	newPassword = md5.createHash($scope.newPassword),
	confirmPassword = md5.createHash($scope.confirmPassword);
	$http({
		url:appCon.globalCon.serviceBaseURL+"user/changePassword?userId="+
		profiledata.oid+"&oldPassword="+currentPassword+"&newPassword="+
		newPassword+"&confirmPassword="+confirmPassword,
		method:'GET'
	}).success(function(result){
		console.log(result);
		if (result.statusFlag == 'Ok'){
			$scope.errormsg = true;
			$scope.showmessage = "Successfully Updated";
		}
		if (result.statusFlag == 'Error'){
			$scope.errormsg = true;
			if(result.errorMsg == 'OLD AND NEW PASSWORD SAME')
				$scope.showmessage = "Old and New Password is Same";
			if(result.errorMsg == 'OLD PASSWORD IS WRONG')
				$scope.showmessage = "Old Password is Wrong";
		}
		
	});
}

$scope.overviewfunction =function(){
	$scope.errormsg = false;
	$scope.changepsw.$setPristine();
	$scope.currentPassword="";
	$scope.newPassword="";
	$scope.confirmPassword="";
}

$scope.clearpassword = function(){
	$scope.errormsg = false;
	$scope.changepsw.$setPristine();
	$scope.currentPassword="";
	$scope.newPassword="";
	$scope.confirmPassword="";
}


}]);




  

  