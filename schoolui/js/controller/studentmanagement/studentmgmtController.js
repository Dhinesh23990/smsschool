'use strict';

angular.module(appCon.appName).controller('studentmgmtController',
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
$scope.listandgridview = "List View";
$scope.submitted = true;
$scope.isOpen = false;
$scope.success_file = false;
$scope.login_usertoken = appCon.cookie.getItem('loginusertoken');
var searchstudent = $stateParams.searchId;

if(searchstudent){
	if(searchstudent.length == 10){
		$scope.search = {'parentMobileNumber1':searchstudent};
	}
	else
		$scope.search = {'studentId':searchstudent};
}

//list of student details

$scope.currentPage = 1,$scope.numPerPage = 10,$scope.maxSize = 5;

  $scope.dtOptions = DTOptionsBuilder.newOptions()
						.withOption('lengthChange',false)
						.withOption('info',false)
						.withOption('searching',false)
						.withOption('sort',false)
						.withOption('filter',false)
						.withOption("paging",false);
						
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

$scope.StudentVO = {physicallyChallenged:"No"};
  
$scope.getallstudentdata = function(){
	$http({
		url:appCon.globalCon.serviceBaseURL+"student/getAllStudent?schoolId="+$cookieStore.get('schoolId')+"&pageIndex=0&pageSize="+appCon.globalCon.paginationSize,
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
		url:appCon.globalCon.serviceBaseURL+"student/getAllStudent?schoolId="+$cookieStore.get('schoolId')+"&pageIndex="+begin+"&pageSize="+appCon.globalCon.paginationSize,
		method:'GET'
	}).success(function(result){
		var itemsdata = result.responseVO.studentList;
		var index_id = begin+1;
		angular.forEach(itemsdata,function(value,key){
			value.indexId = index_id;
			index_id++;
			value.dob =  $filter('date')(value.dob, "dd-MM-yyyy");
			value.admissionDate =  $filter('date')(value.admissionDate, "dd-MM-yyyy");
			if(value.studentImage.length > 0){
				value.image_url = appCon.globalCon.serviceBaseURL+"fileUpload/downloadFile?id="+value.studentImage[0].blobId+"&directory="+value.studentImage[0].directory+ "&authType=token&authorization="+$scope.login_usertoken;
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
	  
    var gridbegin = (($scope.gridcurrentPage - 1) * $scope.gridnumPerPage)
    , gridend = gridbegin + $scope.gridnumPerPage;
		
     $http({
		url:appCon.globalCon.serviceBaseURL+"student/getAllStudent?schoolId="+$cookieStore.get('schoolId')+"&pageIndex="+gridbegin+"&pageSize="+$scope.gridnumPerPage,
		method:'GET'
	}).success(function(result){
		var itemsdata = result.responseVO.studentList;
		var index_id = gridbegin+1;
		angular.forEach(itemsdata,function(value,key){
			value.indexId = index_id;
			index_id++;
			value.dob =  $filter('date')(value.dob, "dd-MM-yyyy");
			value.admissionDate =  $filter('date')(value.admissionDate, "dd-MM-yyyy");
			if(value.studentImage.length > 0){
				value.image_url = appCon.globalCon.serviceBaseURL+"fileUpload/downloadFile?id="+value.studentImage[0].blobId+"&directory="+value.studentImage[0].directory+ "&authType=token&authorization="+$scope.login_usertoken;
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
		if($scope.checktotalRecords > 12)
			$scope.abovedata = true;
		else
			$scope.abovedata = false;
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

$scope.listviewdet = function(){
	if($scope.setlistandgridview != "listview"){
		$scope.studentlistviewdetails = true;
		$scope.studentgridviewdetails = false;
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
		$scope.studentlistviewdetails = false;
		$scope.studentgridviewdetails = true;
		$scope.setlistandgridview = "gridview";
		angular.element("#grid_view").removeClass("hoverview").removeClass("defaultcol");
		angular.element("#list_view").removeClass("activeview");
		angular.element("#grid_view").addClass("activeview");
		angular.element("#list_view").addClass("hoverview").addClass("defaultcol");
	}
}

$scope.clearallsearchvalue = function(){
	$scope.searchgrid = "";
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

	$http ({
		url:appCon.globalCon.serviceBaseURL+"dashboard/getAllConfigurationBySchoolId?schoolId="+$cookieStore.get('schoolId'),
		method:'GET'
		}).success(function(result){
             var batchdata = result.responseVO.BatchVOs;	
			 $scope.batchs = [];
			angular.forEach(batchdata,function(value, key){
				$scope.batchs.push({value:value.batchName,key:value.id});
			});
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
			var classdata = result.responseVO.ClassVOs;		
			$scope.classs = [];
			angular.forEach(classdata,function(value, key){
				$scope.classs.push({value:value.className,key:value.id});
			});
			var mediumdata = result.responseVO.MediumVOs;		
			$scope.mediums = [];
			angular.forEach(mediumdata,function(value, key){
				$scope.mediums.push({value:value.mediumName,key:value.id});
			});
			var sectiondata = result.responseVO.SectionVOs;		
			$scope.sections = [];
			angular.forEach(sectiondata,function(value, key){
				$scope.sections.push({value:value.sectionName,key:value.id});
			});
			var coursedata = result.responseVO.CourseVOs;		
			$scope.courses = [];
			angular.forEach(coursedata,function(value, key){
				$scope.courses.push({value:value.courseName,key:value.id});
			});
			var languagedata = result.responseVO.LanguageVOs;		
			$scope.languages = [];
			angular.forEach(languagedata,function(value, key){
				$scope.languages.push({value:value.languageName,key:value.id});
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
			
		});
	
	$scope.getallstatebycountry = function(countryname){		
		$http({
		url:appCon.globalCon.serviceBaseURL+"state/getAllStateByCountryName?countryName="+countryname,
		method:'GET'
		}).success(function(result){
			var statedata = result.responseVO.stateVOs,clearstatedata = $scope.StudentVO;	
			clearstatedata.stateId = "";
			clearstatedata.cityId = "";
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
			
			var statedata = result.responseVO.cityVOs,clearcitydata = $scope.StudentVO;
			clearcitydata.cityId = "";			
			$scope.cities = [];
			angular.forEach(statedata,function(value, key){
				$scope.cities.push({value:value.cityName,key:value.id});
			});
		});
	}


$scope.firststep = function(){
	angular.element("#addstuDiv").find('ul li').removeClass('active');
	angular.element("#sndid").addClass('active');
	angular.element("#firststep").removeClass('active');
	angular.element("#thirdstep").removeClass('active');
	angular.element("#fourthstep").removeClass('active');
	angular.element("#fivthstep").removeClass('active');
	angular.element("#secondstep").addClass('active');
	}

$scope.firstpreviousstep = function(){
	angular.element("#addstuDiv").find('ul li').removeClass('active');
	angular.element("#fstid").addClass('active');
	angular.element("#secondstep").removeClass('active');
	angular.element("#thirdstep").removeClass('active');
	angular.element("#fourthstep").removeClass('active');
	angular.element("#fivthstep").removeClass('active');
	angular.element("#firststep").addClass('active');
}

$scope.secondstep = function(){
	angular.element("#addstuDiv").find('ul li').removeClass('active');
	angular.element("#thirdid").addClass('active');
	angular.element("#secondstep").removeClass('active');
	angular.element("#firststep").removeClass('active');
	angular.element("#fourthstep").removeClass('active');
	angular.element("#fivthstep").removeClass('active');
	angular.element("#thirdstep").addClass('active');
	
}
$scope.secondpreviousstep = function(){
	angular.element("#addstuDiv").find('ul li').removeClass('active');
	angular.element("#sndid").addClass('active');
	angular.element("#thirdstep").removeClass('active');
	angular.element("#secondstep").addClass('active');
}

$scope.thirdstep = function(){
	angular.element("#addstuDiv").find('ul li').removeClass('active');
	angular.element("#fourid").addClass('active');
	angular.element("#thirdstep").removeClass('active');
	angular.element("#firststep").removeClass('active');
	angular.element("#secondstep").removeClass('active');
	angular.element("#fivthstep").removeClass('active');
	angular.element("#fourthstep").addClass('active');
	
}

$scope.thirdpreviousstep = function(){
	angular.element("#addstuDiv").find('ul li').removeClass('active');
	angular.element("#thirdid").addClass('active');
	angular.element("#fourthstep").removeClass('active');
	angular.element("#thirdstep").addClass('active');
}

$scope.fourthstep = function(){
	angular.element("#addstuDiv").find('ul li').removeClass('active');
	angular.element("#fiveid").addClass('active');
	angular.element("#fourthstep").removeClass('active');
	angular.element("#firststep").removeClass('active');
	angular.element("#secondstep").removeClass('active');
	angular.element("#thirdstep").removeClass('active');
	angular.element("#fivthstep").addClass('active');
	
}

$scope.fourthpreviousstep = function(){
	angular.element("#addstuDiv").find('ul li').removeClass('active');
	angular.element("#fourid").addClass('active');
	angular.element("#fivthstep").removeClass('active');
	angular.element("#fourthstep").addClass('active');
}

$scope.finalstep = function(){
	
	$scope.errormsg = false;
	$scope.errormsgad = false;
	var data = $scope.StudentVO;
	data.schoolId = $cookieStore.get('schoolId');
	data.dob = $filter('date')($scope.dob, "dd/MM/yyyy");
	data.admissionDate = $filter('date')($scope.admissionDate, "dd/MM/yyyy");
	if($scope.studentImage){
		data.studentImage = $scope.studentImage;
	}
	$scope.data = {"SMSRequest":{"StudentVO":$scope.StudentVO}};
	console.log($scope.data);	
	$injector.get('usermanagementservice')['saveStudent']($scope.data).then(        		
		 //Request Success 
		function(result) {
			if(result.data.statusFlag == 'Ok')
				$state.go('studentmanagement');
			if(result.data.statusFlag == 'Error'){
				if(result.data.errorMsg == 'STUDENT ALREADY EXISTS'){
					angular.element("#addstuDiv").find('ul li').removeClass('active');
					angular.element("#fstid").addClass('active');
					angular.element("#fivthstep").removeClass('active');
					angular.element("#firststep").addClass('active');
					$scope.errormsg = true;
				}
				if(result.data.errorMsg == 'STUDENT ADMISSION NUMBER EXISTS'){
					angular.element("#addstuDiv").find('ul li').removeClass('active');
					angular.element("#thirdid").addClass('active');
					angular.element("#fivthstep").removeClass('active');
					angular.element("#thirdstep").addClass('active');
					$scope.errormsgad = true;
				}
			}
		},		
		function(error){
			console.log('failure');
		});
}


$scope.deletestudentdata = function(deletestudentid){	
   $scope.$modalInstance = $modal.open({
		 scope: $scope,
		 templateUrl: 'deletestudent.html',
		 controller: function($scope, $modalInstance, $rootScope, $state) {
			  $scope.deletestudentok = function() {
				  deletestudent(deletestudentid);
				  $modalInstance.close();
			  };
			  $scope.okcancel = function() {
				  $modalInstance.close();
			  };
		  }
	  });
}

function deletestudent(deleteid){
	console.log(deleteid);
	$http({
		url:appCon.globalCon.serviceBaseURL+"student/deleteStudent?studentId="+deleteid,
		method:'DELETE'
	}).success(function(result){
		$state.go($state.current, {}, {reload: true});
		
	});
}

/*$scope.submitForm = function() {
    $scope.submitted = true;
    if (myForm.$valid) {
        alert('Form submitted - fields passed validation');
    }
};*/  

$scope.aresure = function(){
	$scope.$modalInstance = $modal.open({
		 scope: $scope,
		 templateUrl: 'docancel.html',
		 controller: function($scope, $modalInstance, $rootScope, $state) {
			  $scope.yes = function() {
				  $state.go("studentmanagement");
			  };
			  $scope.okcancel = function() {
				  $modalInstance.close();
			  };
		  }
	  });
}


}]);

angular.module(appCon.appName).directive('myStudentDirective', ['$parse', '$http', function($parse, $http) {
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
								scope.studentImage = imagedata;
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

angular.module(appCon.appName).directive('numericOnlyAdmission', function(){
    return {
        require: 'ngModel',
        link: function(scope, element, attrs, modelCtrl) {

            modelCtrl.$parsers.push(function (inputValue) {
                var transformedInput = inputValue ? inputValue.replace(/[^A-Za-z0-9\_/-]/g,'') : null;

                if (transformedInput!=inputValue) {
                    modelCtrl.$setViewValue(transformedInput);
                    modelCtrl.$render();
                }

                return transformedInput;
            });
        }
    };
});


  
