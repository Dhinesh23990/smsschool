'use strict';

angular.module(appCon.appName).controller('adminmgmtController',
					['$injector',  '$scope', 'DTOptionsBuilder', 'MethodService',
        function($injector, $scope, DTOptionsBuilder, MethodService) {
var $http,$state,$modal,$cookieStore,$filter;
$http = $injector.get('$http');
$state = $injector.get('$state');
$modal = $injector.get('$modal');
$cookieStore = $injector.get('$cookieStore');
$filter = $injector.get('$filter');
$scope.hidesearchdet = true;
$scope.hidesearchdetgrid = true;
$scope.adminlistviewdetails = true;
$scope.admingridviewdetails = false;
$scope.errormsg = false;
$scope.setlistandgridview ='listview';
$scope.listandgridview ="List View";
$scope.listandgridview ="List View";
$scope.submitted = true;
$scope.isOpen = false;
$scope.success_file = false;
$scope.loading = false;
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

$scope.dtOptions = DTOptionsBuilder.newOptions()
						.withOption('lengthChange',false)
						.withOption('info',false)
						.withOption('searching',false)
						.withOption('sort',false)
						.withOption('filter',false)
						.withOption("paging",false);

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
//list of admin details
$scope.currentPage = 1
  ,$scope.numPerPage = 10
  ,$scope.maxSize = 5;
  
$scope.getAllAdmindata = function(){
	$http({
		url:appCon.globalCon.serviceBaseURL+"admin/getAllAdmin?schoolId="+$cookieStore.get('schoolId')+"&pageIndex=0&pageSize="+appCon.globalCon.paginationSize,
		method:'GET'
	}).success(function(result){
		$scope.totalRecords = result.responseVO.totalRecords;
		$scope.gridtotalRecords = ($scope.totalRecords/12)*10;
	});
}

$scope.$watch('currentPage + numPerPage', function() {
	   
    var begin = (($scope.currentPage - 1) * $scope.numPerPage)
    , end = begin + $scope.numPerPage;
	
     $http({
		url:appCon.globalCon.serviceBaseURL+"admin/getAllAdmin?schoolId="+$cookieStore.get('schoolId')+"&pageIndex="+begin+"&pageSize="+appCon.globalCon.paginationSize,
		method:'GET'
	}).success(function(result){
		var itemsdata = result.responseVO.adminList;
		var index_id = begin+1;
		angular.forEach(itemsdata,function(value,key){
			value.indexId = index_id;
			index_id++;
			value.dob =  $filter('date')(value.dob, "dd-MM-yyyy");
			value.dateOfJoining =  $filter('date')(value.dateOfJoining, "dd-MM-yyyy");
			if(value.emailId == null)
				value.emailId = '----';
			if(value.specializedIn == null)
				value.specializedIn = '----';
			if(value.employeeNumber == null)
				value.employeeNumber = '----';
			if(value.experience == null)
				value.experience = '----';
			if(value.adminImage.length > 0){
				value.image_url = appCon.globalCon.serviceBaseURL+"fileUpload/downloadFile?id="+value.adminImage[0].blobId+"&directory="+value.adminImage[0].directory+ "&authType=token&authorization="+$scope.login_usertoken;
			}else{
				value.image_url = null;
			}
		})
		$scope.items = itemsdata;
		if($scope.items.length > 0)
			$scope.show_datatables = true;
		
		$scope.checktotalRecords = result.responseVO.totalRecords;
		$scope.show_begin = begin+1;
		if($scope.checktotalRecords > end)
			$scope.show_end = end;
		else
			$scope.show_end = $scope.checktotalRecords;
		
		if($scope.checktotalRecords > 10)
			$scope.abovedata = true;
		else
			$scope.abovedata = false;
	});
    
  }); 
  
  
  //grid view
  $scope.gridcurrentPage = 1,$scope.gridnumPerPage = 12;
  $scope.$watch('gridcurrentPage + gridnumPerPage', function() {
	   
    var begin = (($scope.gridcurrentPage - 1) * $scope.gridnumPerPage)
    , end = begin + $scope.gridnumPerPage;
	
     $http({
		url:appCon.globalCon.serviceBaseURL+"admin/getAllAdmin?schoolId="+$cookieStore.get('schoolId')+"&pageIndex="+begin+"&pageSize="+$scope.gridnumPerPage,
		method:'GET'
	}).success(function(result){
		var itemsdata = result.responseVO.adminList;
		var index_id = begin+1;
		angular.forEach(itemsdata,function(value,key){
			value.indexId = index_id;
			index_id++;
			value.dob =  $filter('date')(value.dob, "dd-MM-yyyy");
			value.dateOfJoining =  $filter('date')(value.dateOfJoining, "dd-MM-yyyy");
			if(value.emailId == null)
				value.emailId = '----';
			if(value.specializedIn == null)
				value.specializedIn = '----';
			if(value.employeeNumber == null)
				value.employeeNumber = '----';
			if(value.experience == null)
				value.experience = '----';
			if(value.adminImage.length > 0){
				value.image_url = appCon.globalCon.serviceBaseURL+"fileUpload/downloadFile?id="+value.adminImage[0].blobId+"&directory="+value.adminImage[0].directory+ "&authType=token&authorization="+$scope.login_usertoken;
			}else{
				value.image_url = null;
			}
		})
		$scope.griditems = itemsdata;
		if($scope.griditems.length > 0)
			$scope.show_datatables = true;
		
		$scope.checktotalRecords = result.responseVO.totalRecords;
		/*$scope.show_begin = begin+1;
		if($scope.checktotalRecords > end)
			$scope.show_end = end;
		else
			$scope.show_end = $scope.checktotalRecords;
		*/
		if($scope.checktotalRecords > 10)
			$scope.abovedata = true;
		else
			$scope.abovedata = false;
	});
    
  }); 
  

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

	//state
	$scope.getallstatebycountry = function(countryname){		
		$http({
		url:appCon.globalCon.serviceBaseURL+"state/getAllStateByCountryName?countryName="+countryname,
		method:'GET'
		}).success(function(result){
			var statedata = result.responseVO.stateVOs,clearstatedata = $scope.AdminVO;	
			clearstatedata.stateId = "";
			clearstatedata.cityId = "";
			$scope.states = [];
			angular.forEach(statedata,function(value, key){

				$scope.states.push({value:value.stateName,key:value.id});
			});
		});
	}
	//city
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
	//comstate
	$scope.getallstatebycountrycom = function(countryname){		
		$http({
		url:appCon.globalCon.serviceBaseURL+"state/getAllStateByCountryName?countryName="+countryname,
		method:'GET'
		}).success(function(result){
			var statedata = result.responseVO.stateVOs,clearstatedata = $scope.AdminVO;	
			clearstatedata.comState = "";
			clearstatedata.comCity = "";
			$scope.comstates = [];
			angular.forEach(statedata,function(value, key){
				$scope.comstates.push({value:value.stateName,key:value.id});
			});
		});
	}
	//comcity
	$scope.getallcitybystatecountrycom = function(countryname,statename){
		$http({
		url:appCon.globalCon.serviceBaseURL+"city/getAllCityByStateCountryName?countryName="+countryname+"&stateName="+statename,
		method:'GET'
		}).success(function(result){
			
			var statedata = result.responseVO.cityVOs,clearcitydata = $scope.AdminVO;
			clearcitydata.comCity = "";			
			$scope.comcities = [];
			angular.forEach(statedata,function(value, key){
				$scope.comcities.push({value:value.cityName,key:value.id});
			});
		});
	}

// educational qualification
	$http({
		url:appCon.globalCon.serviceBaseURL+"education/getAllEducationBySchoolId?shoolId="+$cookieStore.get('schoolId')+"&pageIndex=0&pageSize="+appCon.globalCon.paginationSize,
		method:'GET'
	}).success(function(result){
		console.log(result.responseVO);
	var edudata = result.responseVO.educationalVOs;
	$scope.educationalQualifications=[];
	angular.forEach(edudata,function(value,key){
		$scope.educationalQualifications.push({value:value.educationName,key:value.id});
	});
		
});
	



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

$scope.clearallsearchvalue = function(){
	$scope.searchgrid = "";
	$scope.dob="";
	$scope.dateOfJoining="";
}
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
	$scope.loading = true;
	$scope.errormsg = false;
	$scope.errormsgemail = false;
	var data = $scope.AdminVO;
	data.schoolId = $cookieStore.get('schoolId');
	data.dob = $filter('date')($scope.dob, "dd/MM/yyyy");
	data.dateOfJoining = $filter('date')($scope.dateOfJoining, "dd/MM/yyyy");
	if($scope.adminImage){
		data.adminImage = $scope.adminImage;
	}
	$scope.data = {"SMSRequest":{"AdminVO":$scope.AdminVO}};
	$injector.get('usermanagementservice')['saveAdmin']($scope.data).then(        		
		 //Request Success 
		function(result) {
			if(result.data.statusFlag == 'Ok'){
				$state.go('adminmanagement');
				$scope.loading = false;
			}
			
			if(result.data.statusFlag == 'Error'){
				$scope.loading = false;
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
					angular.element("#addstuDiv").find('ul li').removeClass('active');
					angular.element("#sndid").addClass('active');
					angular.element("#fourthstep").removeClass('active');
					angular.element("#secondstep").addClass('active');
					$scope.errormsgemail = true;
				}
				if(result.data.errorMsg == 'ADMIN EMPLOYEE NUMBER EXISTS'){
					angular.element("#addstuDiv").find('ul li').removeClass('active');
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
$scope.deleteadmindata = function(deleteadminid){	
   $scope.$modalInstance = $modal.open({
		 scope: $scope,
		 templateUrl: 'deleteadmin.html',
		 controller: function($scope, $modalInstance, $rootScope, $state) {
			  $scope.deleteadminok = function() {
				  deleteadmin(deleteadminid);
				
			  };
			  $scope.okcancel = function() {
				  $modalInstance.close();
			  };
		  }
	  });
}
function deleteadmin(deleteid){
	$http({
		url:appCon.globalCon.serviceBaseURL+"admin/deleteAdmin?adminId="+deleteid,
		method:'DELETE'
	}).success(function(result){
		$state.go($state.current, {}, {reload: true});
		
	});
}


$scope.listviewdet = function(){
	if($scope.setlistandgridview != "listview"){
		$scope.adminlistviewdetails = true;
		$scope.admingridviewdetails = false;
		$scope.setlistandgridview = "listview";
		$scope.listandgridview ="List View";
		angular.element("#grid_view").removeClass("activeview");
		angular.element("#list_view").removeClass("hoverview").removeClass("defaultcol");
		angular.element("#grid_view").addClass("hoverview").addClass("defaultcol");
		angular.element("#list_view").addClass("activeview");
	}
}

$scope.gridviewdet = function(){
	if($scope.setlistandgridview == "listview"){
		$scope.listandgridview ="Grid View";
		$scope.adminlistviewdetails = false;
		$scope.admingridviewdetails = true;
		$scope.setlistandgridview = "gridview";
		angular.element("#grid_view").removeClass("hoverview").removeClass("defaultcol");
		angular.element("#list_view").removeClass("activeview");
		angular.element("#grid_view").addClass("activeview");
		angular.element("#list_view").addClass("hoverview").addClass("defaultcol");
	}
}

$scope.aresure = function(){
	$scope.$modalInstance = $modal.open({
		 scope: $scope,
		 templateUrl: 'docancel.html',
		 controller: function($scope, $modalInstance, $rootScope, $state) {
			  $scope.yes = function() {
				  $state.go("adminmanagement");
			  };
			  $scope.okcancel = function() {
				  $modalInstance.close();
			  };
		  }
	  });
}



}]);

angular.module(appCon.appName).directive('myAdminDirective', ['$parse', '$http', function($parse, $http) {
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
								var imagedata = ans_respone.blobDetailsVO;
								for (var i = 0; i < imagedata.length; i++) {
									scope.image_id = appCon.globalCon.serviceBaseURL + "fileUpload/downloadFile?id=" + imagedata[0].blobId + "&directory=" + imagedata[0].directory + "&authType=token&authorization="+scope.login_usertoken;
								}
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

/*angular.module(appCon.appName).directive('inputMask', function(){
	return {
      restrict: 'A',
      scope: true,
      link: function (scope, element, attr) {
        var l;
        attr.inputMask ? l = scope.$eval(attr.inputMask)  : attr.maskOptions && (l = scope.$eval(attr.maskOptions)),
        angular.element(element).inputmask(l)
      }
    };
});*/
  
