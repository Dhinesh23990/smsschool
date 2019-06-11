'use strict';

angular.module(appCon.appName).controller('dashboardheadercontroller',
					['$injector',  '$scope',
        function($injector, $scope) {
var $http,$state,$modal,$cookieStore,$rootScope,$location,$window;
$http = $injector.get('$http');
$state = $injector.get('$state');
$modal = $injector.get('$modal');
$cookieStore = $injector.get('$cookieStore');
$rootScope = $injector.get('$rootScope');
$location = $injector.get('$location');
$window = $injector.get('$window');
$scope.firstbutton = true;
$scope.sndbutton = false;/*
$scope.profiledata = $cookieStore.get('userProfile');
console.log($scope.profiledata);*/
$scope.school_id = $cookieStore.get('schoolId');
console.log($scope.school_id);
$scope.defaultimage = false;
$scope.schoolimage = false;
$scope.login_usertoken = appCon.cookie.getItem('loginusertoken');
$rootScope.simplenavbar  = "navigation-dark";

if($cookieStore.get('userProfile')){
	angular.element('.appWrap').removeClass('core');
}

$scope.changenavbar = function(){
	$scope.firstbutton = false;
	$scope.sndbutton = true;
	$rootScope.simplenavbar  = "navigation-sm";
}

$scope.changerepeatnavbar = function(){
	$scope.firstbutton = true;
	$scope.sndbutton = false;
	$rootScope.simplenavbar  = "navigation-dark";
}

$scope.schoolheaderdata = function(){
	$rootScope.simplenavbar ="navigation-dark";
	$http ({
		url:appCon.globalCon.serviceBaseURL+"school/getSchoolById?schoolId="+$cookieStore.get('schoolId')+"&pageIndex=0&pageSize=100",
		method:'GET'
		}).success(function(result){
             var schoolimagedata = result.responseVO.SchoolVO;
             
             $scope.profiledata = schoolimagedata;
             
			if(schoolimagedata.schoolImage.length > 0){
				$scope.defaultimage =false;				
				$scope.schoolimage = true;
				$scope.image_id = appCon.globalCon.serviceBaseURL+"fileUpload/downloadFile?id="+schoolimagedata.schoolImage[0].blobId+"&directory="+schoolimagedata.schoolImage[0].directory+ "&authType=token&authorization="+$scope.login_usertoken;
				
			}else{
				$scope.defaultimage =true;
				console.log($scope.defaultimage,"DefaultImage")
				$scope.schoolimage =false;
			}
		});
}

$scope.logoutschool = function(){
	var userprofiledata = angular.fromJson($cookieStore.get('userProfile'));
	$scope.checkuserrole = userprofiledata.userRole;
	var demourl = localStorage.getItem("demourl");
	angular.element('.appWrap').addClass('core');
	$rootScope.userProfile = false;
	$cookieStore.remove('userProfile');
	$cookieStore.remove('loginusertoken');
	$cookieStore.remove('schoolId'); 
	localStorage.clear();
	appCon.cookie.removeItem('loginusertoken');
	appCon.cookie.removeItem('dashboard');
	if(demourl == "oxford"){
		$window.location.href = appCon.globalCon.redirecturl;
	}else{
		if(userprofiledata.userRole == 'SCHOOLADMIN')
			$location.path("/login");
		if(userprofiledata.userRole == 'ROLEADMIN')
			$location.path("/admin");
	}
}

$scope.searchstudentdet = function(searchvalue){
	
	$http ({
		url:appCon.globalCon.serviceBaseURL+"student/getStudentByIdOrNo?schoolId="+$cookieStore.get('schoolId')+"&searchString="+searchvalue,
		method:'GET'
		}).success(function(result){
            var studentdata = result.responseVO.StudentVO;
			if(studentdata != null){
				$scope.searchvalue = "";
				$state.go("studentmanagement",{'searchId':searchvalue});
			}else{
				$scope.searchvalue = "";
				$scope.$modalInstance = $modal.open({
				 scope: $scope,
				 templateUrl: 'searchstudent.html',
				 controller: function($scope, $modalInstance, $rootScope, $state) {
					  $scope.searchstudentok = function() {
						 $modalInstance.close();
					  };
					  $scope.searchcancel = function() {
						  $modalInstance.close();
					  };
				  }
			  });
			}
			
		});
}


}]);



