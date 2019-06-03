'use strict';

angular.module(appCon.appName).controller('schooladminmgmtController',
					['$injector',  '$scope', 'DTOptionsBuilder', '$stateParams',
        function($injector, $scope, DTOptionsBuilder, $stateParams) {
var $http,$state,$modal,$cookieStore,$filter ;
$http = $injector.get('$http');
$state = $injector.get('$state');
$modal = $injector.get('$modal');
$filter = $injector.get('$filter');
$cookieStore = $injector.get('$cookieStore');
$scope.hidesearchdet = true;
$scope.hidesearchdetgrid = true;
$scope.studentlistviewdetails = true;
$scope.studentgridviewdetails = false;
$scope.errormsg = false;
$scope.setlistandgridview = 'listview';
$scope.listandgridview ="List View";
$scope.submitted = true;
$scope.isOpen = false;
$scope.showschoolimage =false;
$scope.success_file = false;
$scope.loading = false;
$scope.login_usertoken = appCon.cookie.getItem('loginusertoken');

$('input[name="birthdate"]').daterangepicker({
        singleDatePicker: true,
        showDropdowns: true,
		minDate:new Date()
	});
$('input[name="admissionDate"]').daterangepicker({
        singleDatePicker: true,
        showDropdowns: true,
		minDate:new Date()
});


$scope.schoolstatus = $stateParams.status;

$scope.gotodashboard = function()
{		
	angular.element("#slimScrollDiv").find('ul li:first').remove('active');
	angular.element("#slimScrollDiv").find('ul li:first').addClass('active');
	angular.element("#slimScrollDiv").find('ul').parent('li').removeClass('active');
	angular.element("#slimScrollDiv").find('ul li').removeClass('open');
	//angular.element("#ddremove").style.display = 'none';
	var yourUl = document.getElementById("ddremove");
	yourUl.style.display = yourUl.style.display === 'none' ? '' : 'none';
	$state.go('admindashboard');		
}

$scope.getallschooldata = function(){
	
	if($scope.schoolstatus == "" || $scope.schoolstatus == null || angular.isUndefined($scope.schoolstatus)){
		var url = appCon.globalCon.serviceBaseURL+"school/getAllSchool?pageIndex=0&pageSize=500"
	}else{
		var url = appCon.globalCon.serviceBaseURL+"school/getAllSchool?pageIndex=0&pageSize=500&status="+$scope.schoolstatus
	}
	$http({
		url:url,
		method:'GET'
	}).success(function(result){
		var allschooldata = result.responseVO.schoolVOs;
		angular.forEach(allschooldata, function(value,key){
		
			if(value.status == "ACTIVE")
				value.status = 'Active';
			
			if(value.status == "INACTIVE")
				value.status = 'InActive';
			
		});
		
		$scope.items = allschooldata;
		
		if($scope.items.length > 10){
			$scope.dtOptions = DTOptionsBuilder.newOptions()
						.withOption('lengthChange',false)
						.withOption('searching',false)
						.withOption('sort',false)
						.withOption('filter',false)
						.withPaginationType("full_numbers");
		}else{
			$scope.dtOptions = DTOptionsBuilder.newOptions()
						.withOption('lengthChange',false)
						.withOption('searching',false)
						.withOption('sort',false)
						.withOption('filter',false)
						.withOption("paging",false);
		}
		
	});
}

$scope.showalldata = function(){
	$scope.schoolstatus = "";
	$scope.getallschooldata();
}

$scope.backtosearch = function(){
	$scope.search="";
	$scope.hidesearchdet = false;
}
$scope.returnsearch = function(){
	$scope.search="";
	$scope.hidesearchdet = true;
}

$scope.backtosearchgrid = function(){
	$scope.searchgrid="";
	$scope.hidesearchdetgrid = false;
}
$scope.returnsearchgrid = function(){
	$scope.searchgrid="";
	$scope.hidesearchdetgrid = true;
}
$scope.firststep = function(){
	angular.element("#addstaffDiv").find('ul li').removeClass('active');
	angular.element("#sndid").addClass('active');
	angular.element("#firststep").removeClass('active');
	angular.element("#thirdstep").removeClass('active');
	angular.element("#secondstep").addClass('active');
	
	}

$scope.firstpreviousstep = function(){
	angular.element("#addstaffDiv").find('ul li').removeClass('active');
	angular.element("#fstid").addClass('active');
	angular.element("#secondstep").removeClass('active');
	angular.element("#thirdstep").removeClass('active');
	angular.element("#firststep").addClass('active');
}

$scope.secondstep = function(){
	angular.element("#addstaffDiv").find('ul li').removeClass('active');
	angular.element("#thirdid").addClass('active');
	angular.element("#secondstep").removeClass('active');
	angular.element("#firststep").removeClass('active');
	angular.element("#thirdstep").addClass('active');
	
}
$scope.secondpreviousstep = function(){
	angular.element("#addstaffDiv").find('ul li').removeClass('active');
	angular.element("#sndid").addClass('active');
	angular.element("#thirdstep").removeClass('active');
	angular.element("#secondstep").addClass('active');
}

$scope.finalstep = function(){
	
	$scope.errormsg = false;
	$scope.loading = true;
	var data = $scope.SchoolVO;
	data.smsStartDate = $filter('date')($scope.smsStartDate, "dd/MM/yyyy");
	data.smsEndDate = $filter('date')($scope.smsEndDate, "dd/MM/yyyy");
	if($scope.schoolImage){
		data.schoolImage = $scope.schoolImage;
	}
	$scope.data = {"SMSRequest":{"SchoolVO":$scope.SchoolVO}};
	$injector.get('schooladmin')['saveSchool']($scope.data).then(        		
		 //Request Success 
		function(result) {
			console.log(result.data.statusFlag);
			if(result.data.statusFlag == 'Ok'){
				$scope.loading = false;
				$state.go('schoolmgmt');
			}
			if(result.data.statusFlag == 'Error'){
				$scope.loading = false;
				angular.element("#addstuDiv").find('ul li').removeClass('active');
				angular.element("#fstid").addClass('active');
				angular.element("#thirdstep").removeClass('active');
				angular.element("#firststep").addClass('active');
				$scope.errormsg = true;
				if(result.data.errorMsg == "School Already Exists")
					$scope.schoolerrormsg = "School Name Already Exists";
				if(result.data.errorMsg == "User Name Already Exists")
					$scope.schoolerrormsg = "UserName Already Exists";
				if(result.data.errorMsg == "Email Id Already Exists")
					$scope.schoolerrormsg = "EmailId Already Exists";
			}
		},		
		function(error){
			console.log('failure');
		});
	
}
	
	$scope.clearsaveschool = function(){
		$state.go($state.current, {}, {reload: true});
	}
	
	$scope.setvalidation = function(psw1,psw2){
		if(psw1&&psw2&&psw1==psw2)
			$scope.passwordcheck = "checkvalue";
		else
			$scope.passwordcheck = "";
	}

	$scope.statusschooldata = function(status,school_id){
		
		if(status == "ACTIVE")
			$scope.activeorinactive = 'Active';
				
		if(status == "INACTIVE")
			$scope.activeorinactive = 'InActive';
		
		$scope.$modalInstance = $modal.open({
			 scope: $scope,
			 templateUrl: 'activeorinactiveschool.html',
			 controller: function($scope, $modalInstance, $rootScope, $state) {
				  $scope.activeschoolok = function() {
					  activeschooldet(status,school_id);
					  $modalInstance.close();
				  };
				  $scope.activeokcancel = function() {
					  $modalInstance.close();
				  };
			  }
		  });
		
		
	}

	function activeschooldet(status,school_id){
		$http({
			url:appCon.globalCon.serviceBaseURL+"school/updateSchoolStatus?schoolId="+school_id+'&status='+status,
			method:'POST'
			}).success(function(result){
				//$state.go($state.current, {}, {reload: true});
				$scope.getallschooldata();
		});
	}			


	$scope.deleteschooldata = function(deleteschoolid){	

	   $scope.$modalInstance = $modal.open({
			 scope: $scope,
			 templateUrl: 'deleteschool.html',
			 controller: function($scope, $modalInstance, $rootScope, $state) {
				  $scope.deleteschoolok = function() {
					  deleteschooldet(deleteschoolid);
					  $modalInstance.close();
				  };
				  $scope.okcancel = function() {
					  $modalInstance.close();
				  };
			  }
		  });
	}

	function deleteschooldet(deleteid){
		$http({
			url:appCon.globalCon.serviceBaseURL+"school/deleteSchool?schoolId="+deleteid,
			method:'DELETE'
		}).success(function(result){
			$state.go($state.current, {}, {reload: true});
			
		});
	}
	
	$scope.aresure = function(){
	$scope.$modalInstance = $modal.open({
		 scope: $scope,
		 templateUrl: 'docancel.html',
		 controller: function($scope, $modalInstance, $rootScope, $state) {
			  $scope.yes = function() {
				  $state.go("schoolmgmt");
			  };
			  $scope.okcancel = function() {
				  $modalInstance.close();
			  };
		  }
	  });
}

}]);



angular.module(appCon.appName).directive('mySchoolDirective', ['$parse', '$http', function($parse, $http) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.ngFileModel);
            var isMultiple = attrs.multiple;
            var modelSetter = model.assign;
            element.bind('change', function() {
                var values = [],
                    albumExistId;
                angular.forEach(element[0].files, function(item) {
                    var value = {
                        // File Name 
                        name: item.name,
                        //File Size 
                        size: item.size,
                        //File URL to view 
                        url: URL.createObjectURL(item),
                        // File Input Value 
                        _file: item
                    };
                    values.push(value);
                });
                scope.loading = true;
                scope.$apply(function() {
                    scope.imagecorrect = true;
                    var validFileType = ".jpg , .png , .bmp , .jpeg , .JPG , .PNG , .BMP , .JPEG";
                    for (var i = 0; i < values.length; i++) {
                        var extension = values[i].name.substring(values[i].name.lastIndexOf('.'));
                        if (validFileType.indexOf(extension) < 0) {
                            scope.success_file = true;
                            scope.imagecorrect = false;
                        }
                    }
                    if (scope.imagecorrect) {
                        var formData = new FormData();
                        formData.append('file', element[0].files[0]);
						console.log(element[0].files[0]);
						console.log(formData);
                        var uploadUrl = appCon.globalCon.serviceBaseURL + "fileUpload/uploadFile?file=" + formData;
                        $http.post(uploadUrl, formData, {
							transformRequest: angular.identity,
							headers: {
								'Content-Type': undefined
							}
						})
						.success(function(ans_respone) {
							if (ans_respone.errorMessage) {
								scope.success_file = true;
							} else {
								scope.loading = false;
								scope.success_file = false;
								console.log(scope.login_usertoken);
								var imagedata = ans_respone.blobDetailsVO;
								for (var i = 0; i < imagedata.length; i++) {
									scope.image_id = appCon.globalCon.serviceBaseURL + "fileUpload/downloadFile?id=" + imagedata[0].blobId + "&directory=" + imagedata[0].directory + "&authType=token&authorization="+scope.login_usertoken;
								}
								scope.schoolImage = imagedata;
							}
						});
                    }
                });
            });

        }

    };

}]);

angular.module(appCon.appName).directive('numericOnlyAddstudent', function(){
    return {
        require: 'ngModel',
        link: function(scope, element, attrs, modelCtrl) {

            modelCtrl.$parsers.push(function (inputValue) {
                var transformedInput = inputValue ? inputValue.replace(/[^0-9]/g,'') : null;

                if (transformedInput!=inputValue) {
                    modelCtrl.$setViewValue(transformedInput);
                    modelCtrl.$render();
                }

                return transformedInput;
            });
        }
    };
});

angular.module(appCon.appName).directive('numericCharacter', function(){
    return {
        require: 'ngModel',
        link: function(scope, element, attrs, modelCtrl) {

            modelCtrl.$parsers.push(function (inputValue) {
                var transformedInput = inputValue ? inputValue.replace(/[^A-Z0-9-]/g, '') : null;
                  if (inputValue == undefined) inputValue = '';
                  var transformedInput = inputValue.toUpperCase();
                if (transformedInput!=inputValue) {
                    modelCtrl.$setViewValue(transformedInput);
                    modelCtrl.$render();
                }

                return transformedInput;
            });
        }
    };
});
  
