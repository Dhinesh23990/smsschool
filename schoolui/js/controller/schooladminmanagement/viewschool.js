'use strict';

angular.module(appCon.appName).controller('viewschoolController',
					['$injector',  '$scope', 'DTOptionsBuilder', 'MethodService', 'md5',
        function($injector, $scope, DTOptionsBuilder, MethodService, md5) {
var $http,$state,$modal,$cookieStore,$filter;
$http = $injector.get('$http');
$state = $injector.get('$state');
$modal = $injector.get('$modal');
$cookieStore = $injector.get('$cookieStore');
$filter = $injector.get('$filter');
$scope.errormsg = false;
$scope.submitted = true;
$scope.isOpen = false;
$scope.profiledata = $cookieStore.get('userProfile');
$scope.login_usertoken = appCon.cookie.getItem('loginusertoken');
$scope.defaultimage =false;
$scope.schoolimage =false;

$scope.viewschooldata = function(){
	
	$injector.get('schooladmin')['getSchoolById']().then(        		
	 //Request Success 
	function(result) {
		$scope.SchoolVO	= result.data.responseVO.SchoolVO;
	},		
	function(error){
		console.log('failure');
	});	
	
	
	
	$injector.get('schooladmin')['getCountById']().then(        		
	 //Request Success 
	function(result) {
		console.log(result);
	//	$scope.countdata = result.responseVO;
	  
	   var count_det = result.data.responseVO;
	   $scope.countdata	= count_det;
	   $scope.overallper = $filter('number')((count_det.PresentStudents/count_det.StudentCount)*100, 0);
	   $scope.schoolsmsdata = result.data.responseVO.SchoolVO;
	   if($scope.schoolsmsdata.schoolImage.length > 0){
				$scope.defaultimage =false;
				$scope.schoolimage = true;
				$scope.image_id = appCon.globalCon.serviceBaseURL+"fileUpload/downloadFile?id="+$scope.schoolsmsdata.schoolImage[0].blobId+"&directory="+$scope.schoolsmsdata.schoolImage[0].directory+ "&authType=token&authorization="+$scope.login_usertoken;
				console.log($scope.image_id);
		}else{
			$scope.defaultimage =true;
			$scope.schoolimage =false;
		}
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



$scope.changepassword = function(){
	$scope.errormsg = false;
	var currentPassword = md5.createHash($scope.currentPassword),
	newPassword = md5.createHash($scope.newPassword),
	confirmPassword = md5.createHash($scope.confirmPassword);
	$http({
		url:appCon.globalCon.serviceBaseURL+"user/changePassword?userId="+
		$scope.profiledata.oid+"&oldPassword="+currentPassword+"&newPassword="+
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

$scope.setvalidation = function(psw1,psw2){
	if(psw1&&psw2&&psw1==psw2)
		$scope.passwordcheck="checkvalue";
	else
		$scope.passwordcheck="";
}


}]);