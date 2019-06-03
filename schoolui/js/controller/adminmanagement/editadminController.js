'use strict';

angular.module(appCon.appName).controller('editadminController',
					['$injector',  '$scope', 'DTOptionsBuilder',
        function($injector, $scope, DTOptionsBuilder) {
var $http,$state,$modal,$cookieStore,$filter;

$http = $injector.get('$http');
$state = $injector.get('$state');
$modal = $injector.get('$modal');
$cookieStore = $injector.get('$cookieStore');
$filter = $injector.get('$filter');
$scope.hidesearchdet = true;
$scope.hidesearchdetgrid = true;
$scope.errormsg = false;
$scope.submitted = true;
$scope.isOpen = false;
$scope.success_file = false;
$scope.login_usertoken = appCon.cookie.getItem('loginusertoken');

$('input[name="birthdate"]').daterangepicker({
        singleDatePicker: true,
        showDropdowns: true,
		maxDate:new Date()
	});
$('input[name="admissionDate"]').daterangepicker({
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


$scope.getAllAdmindata = function(){
	
	$http ({
		url:appCon.globalCon.serviceBaseURL+"dashboard/getAllConfigurationBySchoolId?schoolId="+$cookieStore.get('schoolId'),
		method:'GET'
		}).success(function(result){
			
			var bloodgroupdata = result.responseVO.BloodGroupVOs;		
			$scope.bloodgroups = [];
			angular.forEach(bloodgroupdata,function(value, key){
				$scope.bloodgroups.push({value:value.bloodgroupName,key:value.id});
			});
			var nationalitydata = result.responseVO.NationalityVOs;		
			$scope.nationalities = [];
			angular.forEach(nationalitydata,function(value, key){
				$scope.nationalities.push({value:value.nationalityName,key:value.id});
			});
			var motherTonguedata = result.responseVO.MotherTongueVOs;		
			$scope.motherTongues = [];
			angular.forEach(motherTonguedata,function(value, key){
				$scope.motherTongues.push({value:value.motherTongueName,key:value.id});
			});
			var countrydata = result.responseVO.CountryVOs;		
			$scope.countries = [];
			angular.forEach(countrydata,function(value, key){
				$scope.countries.push({value:value.countryName,key:value.id});
			});
			var castedata = result.responseVO.CasteVOs;		
			$scope.castes = [];
			angular.forEach(castedata,function(value, key){
				$scope.castes.push({value:value.casteName,key:value.id});
			});
			var genderdata = result.responseVO.GenderVOs;		
			$scope.genders = [];
			angular.forEach(genderdata,function(value, key){
				$scope.genders.push({value:value.gender,key:value.id});
			});
			var religiondata = result.responseVO.ReligionVOs;		
			$scope.religions = [];
			angular.forEach(religiondata,function(value, key){
				$scope.religions.push({value:value.religionName,key:value.id});
			});
			var edudata = result.responseVO.EducationalVOs;
			$scope.educationalQualifications=[];
			angular.forEach(edudata,function(value,key){
				$scope.educationalQualifications.push({value:value.educationName,key:value.id});
			});
		});
	
	$injector.get('usermanagementservice')['getAdmin']().then(        		
		 //Request Success 
		function(result) {
			var assignadmindata = result.data.responseVO.adminVO;
			if(assignadmindata.countryId != null)
			{
				$http({
				url:appCon.globalCon.serviceBaseURL+"state/getAllStateByCountryName?countryName="+assignadmindata.countryId,
				method:'GET'
				}).success(function(result){
					var statedata = result.responseVO.stateVOs;	
					$scope.states = [];
					angular.forEach(statedata,function(value, key){
						$scope.states.push({value:value.stateName,key:value.id});
					});
				});
			}
			
			if(assignadmindata.stateId != null)
			{
				$http({
				url:appCon.globalCon.serviceBaseURL+"city/getAllCityByStateCountryName?countryName="+assignadmindata.countryId+"&stateName="+assignadmindata.stateId,
				method:'GET'
				}).success(function(result){
					
					var statedata = result.responseVO.cityVOs;
					$scope.cities = [];
					angular.forEach(statedata,function(value, key){
						$scope.cities.push({value:value.cityName,key:value.id});
					});
				});
			}
			
			if(assignadmindata.comCountryId != null)
			{
				$http({
				url:appCon.globalCon.serviceBaseURL+"state/getAllStateByCountryName?countryName="+assignadmindata.comCountryId,
				method:'GET'
				}).success(function(result){
					var statedata = result.responseVO.stateVOs;	
					$scope.comstates = [];
					angular.forEach(statedata,function(value, key){
						$scope.comstates.push({value:value.stateName,key:value.id});
					});
				});
			}
			
			if(assignadmindata.comStateId != null)
			{
				$http({
				url:appCon.globalCon.serviceBaseURL+"city/getAllCityByStateCountryName?countryName="+assignadmindata.comCountryId+"&stateName="+assignadmindata.comStateId,
				method:'GET'
				}).success(function(result){
					
					var statedata = result.responseVO.cityVOs;
					$scope.comcities = [];
					angular.forEach(statedata,function(value, key){
						$scope.comcities.push({value:value.cityName,key:value.id});
					});
				});
			}
			$injector.get('usermanagementservice')['getAdmin']().then(        		
			 //Request Success 
			function(result) {
				
				var admindata = result.data.responseVO.adminVO;
				if(admindata.adminImage.length > 0){
					$scope.image_id = appCon.globalCon.serviceBaseURL+"fileUpload/downloadFile?id="+admindata.adminImage[0].blobId+"&directory="+admindata.adminImage[0].directory+ "&authType=token&authorization="+$scope.login_usertoken;
				}
				$scope.dobsd =  $filter('date')(admindata.dob, "MM/dd/yyyy");
				$('#dobbirth').data('daterangepicker').setStartDate($scope.dobsd);
				$scope.admissionDatesd =  $filter('date')(admindata.dateOfJoining, "MM/dd/yyyy");
				$('#admissionDate').data('daterangepicker').setStartDate($scope.admissionDatesd);
				$scope.dob =  new Date(admindata.dob);
				$scope.dateOfJoining =  new Date(admindata.dateOfJoining);
				$scope.AdminVO = admindata;
			},		
			function(error){
				console.log('failure');
			});
		},		
		function(error){
			console.log('failure');
		});
	
	
}


//edit student details
$scope.copyaddr=function(){
	
	if($scope.sameAddress==true)
	{
		$scope.sameAddressUpdate();
	}	
}
	
$scope.sameAddressUpdate=function(){

	if($scope.sameAddress==true){
		var dataAdmin=$scope.AdminVO;
		if(dataAdmin.countryId){
		$http({
		url:appCon.globalCon.serviceBaseURL+"state/getAllStateByCountryName?countryName="+dataAdmin.countryId,
		method:'GET'
		}).success(function(result){
			var statedata = result.responseVO.stateVOs;	
			$scope.comstates = [];
			angular.forEach(statedata,function(value, key){
				$scope.comstates.push({value:value.stateName,key:value.id});
			});
			dataAdmin.comStateId=dataAdmin.stateId;
		});
		}
	
		if(dataAdmin.countryId && dataAdmin.stateId){
		$http({
		url:appCon.globalCon.serviceBaseURL+"city/getAllCityByStateCountryName?countryName="+dataAdmin.countryId+"&stateName="+dataAdmin.stateId,
		method:'GET'
		}).success(function(result){
			var statedata = result.responseVO.cityVOs;
					
			$scope.comcities = [];
			angular.forEach(statedata,function(value, key){
				$scope.comcities.push({value:value.cityName,key:value.id});
			});
			dataAdmin.comCityId=dataAdmin.cityId;
		});
		}
	
	dataAdmin.comAddressLine1=dataAdmin.addressLine1;
	dataAdmin.comAddressLine2=dataAdmin.addressLine2;
	dataAdmin.comCountryId=dataAdmin.countryId;
	dataAdmin.comPinCode=dataAdmin.pincode;
	}
	if($scope.sameAddress==false){
		var dataAdmin=$scope.AdminVO;
	dataAdmin.comAddressLine1="";
	dataAdmin.comAddressLine2="";
	dataAdmin.comCountryId="";
	dataAdmin.comStateId="";
	dataAdmin.comCityId="";
	dataAdmin.comPinCode="";
	}
	
}
	
$scope.firststep = function(){
	angular.element("#addadminDiv").find('ul li').removeClass('active');
	angular.element("#sndid").addClass('active');
	angular.element("#firststep").removeClass('active');
	angular.element("#thirdstep").removeClass('active');
	angular.element("#fourthstep").removeClass('active');
	angular.element("#secondstep").addClass('active');
	}

$scope.firstpreviousstep = function(){
	angular.element("#addadminDiv").find('ul li').removeClass('active');
	angular.element("#fstid").addClass('active');
	angular.element("#secondstep").removeClass('active');
	angular.element("#thirdstep").removeClass('active');
	angular.element("#fourthstep").removeClass('active');
	angular.element("#firststep").addClass('active');
}

$scope.secondstep = function(){
	angular.element("#addadminDiv").find('ul li').removeClass('active');
	angular.element("#thirdid").addClass('active');
	angular.element("#secondstep").removeClass('active');
	angular.element("#firststep").removeClass('active');
	angular.element("#fourthstep").removeClass('active');
	angular.element("#thirdstep").addClass('active');
	
}
$scope.secondpreviousstep = function(){
	angular.element("#addadminDiv").find('ul li').removeClass('active');
	angular.element("#sndid").addClass('active');
	angular.element("#thirdstep").removeClass('active');
	angular.element("#secondstep").addClass('active');
}

$scope.thirdstep = function(){
	angular.element("#addadminDiv").find('ul li').removeClass('active');
	angular.element("#fourid").addClass('active');
	angular.element("#thirdstep").removeClass('active');
	angular.element("#firststep").removeClass('active');
	angular.element("#secondstep").removeClass('active');
	angular.element("#fourthstep").addClass('active');
	
}

$scope.thirdpreviousstep = function(){
	angular.element("#addadminDiv").find('ul li').removeClass('active');
	angular.element("#thirdid").addClass('active');
	angular.element("#fourthstep").removeClass('active');
	angular.element("#thirdstep").addClass('active');
}

$scope.finalstep = function(){
	
	$scope.errormsg = false;
	$scope.errormsgemail = false;
	var data = $scope.AdminVO;
	data.dob = $filter('date')($scope.dob, "dd/MM/yyyy");
	data.dateOfJoining = $filter('date')($scope.dateOfJoining, "dd/MM/yyyy");
	data.createdDate = $filter('date')(data.createdDate, "dd/MM/yyyy");
	data.updatedDate = $filter('date')(data.updatedDate, "dd/MM/yyyy");
	if($scope.adminImage){
		data.adminImage = $scope.adminImage;
	}
	$scope.data = {"SMSRequest":{"AdminVO":$scope.AdminVO}};
	console.log($scope.data);	
	$injector.get('usermanagementservice')['updateAdmin']($scope.data).then(        		
		 //Request Success 
		function(result) {
			if(result.data.statusFlag == 'Ok')
				$state.go('adminmanagement');
			if(result.data.statusFlag == 'Error'){
				if(result.data.errorMsg == "ADMIN ALREADY EXISTS" || result.data.errorMsg == "USERNAME ALREADY EXISTS"){
				angular.element("#addadminDiv").find('ul li').removeClass('active');
				angular.element("#fstid").addClass('active');
				angular.element("#fourthstep").removeClass('active');
				angular.element("#firststep").addClass('active');
				$scope.errormsg = true;
				if(result.data.errorMsg == "ADMIN ALREADY EXISTS")
					$scope.displayerrormsg = "Admin Already Exists";
				if(result.data.errorMsg == "USERNAME ALREADY EXISTS")
					$scope.displayerrormsg = "UserName Already Exists";
				}
				if(result.data.errorMsg == "Email Id Already Exists"){
					angular.element("#addadminDiv").find('ul li').removeClass('active');
					angular.element("#sndid").addClass('active');
					angular.element("#fourthstep").removeClass('active');
					angular.element("#secondstep").addClass('active');
					$scope.errormsgemail = true;
				}
				if(result.data.errorMsg == 'ADMIN EMPLOYEE NUMBER EXISTS'){
					angular.element("#addadminDiv").find('ul li').removeClass('active');
					angular.element("#thirdid").addClass('active');
					angular.element("#fourthstep").removeClass('active');
					angular.element("#thirdstep").addClass('active');
					$scope.errormsgad = true;
				}
			}
		},		
		function(error){
			console.log('failure');
		});
}

$scope.getallstatebycountry = function(countryname){		
		$http({
		url:appCon.globalCon.serviceBaseURL+"state/getAllStateByCountryName?countryName="+countryname,
		method:'GET'
		}).success(function(result){
			var statedata = result.responseVO.stateVOs,clearstatedata = $scope.AdminVO;	
			clearstatedata.state = "";
			clearstatedata.city = "";
			$scope.states = [];
			angular.forEach(statedata,function(value, key){
				$scope.states.push({value:value.stateName,key:value.id});
			});
		});
	}
	
	$scope.getallcitybystatecountry = function(countryname,statename){
		$http({
		url:appCon.globalCon.serviceBaseURL+"city/getAllCityByStateCountryName?countryName="+countryname+"&stateName="+statename,
		method:'GET'
		}).success(function(result){
			
			var statedata = result.responseVO.cityVOs,clearcitydata = $scope.AdminVO;
			clearcitydata.city = "";			
			$scope.cities = [];
			angular.forEach(statedata,function(value, key){
				$scope.cities.push({value:value.cityName,key:value.id});
			});
		});
	}
	
	
	$scope.getallstatebycountrycom = function(countryname){		
		$http({
		url:appCon.globalCon.serviceBaseURL+"state/getAllStateByCountryName?countryName="+countryname,
		method:'GET'
		}).success(function(result){
			var statedata = result.responseVO.stateVOs,clearstatedata = $scope.AdminVO;	
			clearstatedata.comStateId = "";
			clearstatedata.comCityId = "";
			$scope.comstates = [];
			angular.forEach(statedata,function(value, key){
				$scope.comstates.push({value:value.stateName,key:value.id});
			});
		});
	}
	
	$scope.getallcitybystatecountrycom = function(countryname,statename){
		$http({
		url:appCon.globalCon.serviceBaseURL+"city/getAllCityByStateCountryName?countryName="+countryname+"&stateName="+statename,
		method:'GET'
		}).success(function(result){
			
			var statedata = result.responseVO.cityVOs,clearcitydata = $scope.AdminVO;
			clearcitydata.comCityId = "";			
			$scope.comcities = [];
			angular.forEach(statedata,function(value, key){
				$scope.comcities.push({value:value.cityName,key:value.id});
			});
		});
	}


 

}]);

angular.module(appCon.appName).directive('updateAdminDirective', ['$parse', '$http', function($parse, $http) {
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
								scope.success_file = false;
								console.log(scope.login_usertoken);
								var imagedata = ans_respone.blobDetailsVO;
								for (var i = 0; i < imagedata.length; i++) {
									scope.image_id = appCon.globalCon.serviceBaseURL + "fileUpload/downloadFile?id=" + imagedata[0].blobId + "&directory=" + imagedata[0].directory + "&authType=token&authorization="+scope.login_usertoken;
								}
								console.log(scope.image_id);
								scope.adminImage = imagedata;
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


  
