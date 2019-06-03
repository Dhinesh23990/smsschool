'use strict';

angular.module(appCon.appName).controller('editstudentController',
					['$injector',  '$scope', 'DTOptionsBuilder', '$stateParams',
        function($injector, $scope, DTOptionsBuilder, $stateParams) {
var $http,$state,$modal,$cookieStore,$filter,edit_studentid;

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
edit_studentid=$stateParams.studentId;
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

$scope.StudentVO = {physicallyChallenged:"No"};

$scope.getstudentdata = function(){
		
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
	
	
	$injector.get('usermanagementservice')['getStudent']().then(        		
		 //Request Success 
		function(result) {
			var studentdata = result.data.responseVO.studentVO;
			if(studentdata.countryId != null)
			{
				$http({
				url:appCon.globalCon.serviceBaseURL+"state/getAllStateByCountryName?countryName="+studentdata.countryId,
				method:'GET'
				}).success(function(result){
					var statedata = result.responseVO.stateVOs;	
					$scope.states = [];
					angular.forEach(statedata,function(value, key){
						$scope.states.push({value:value.stateName,key:value.id});
					});
				});
			}
			
			if(studentdata.stateId != null)
			{
				$http({
				url:appCon.globalCon.serviceBaseURL+"city/getAllCityByStateCountryName?countryName="+studentdata.countryId+"&stateName="+studentdata.stateId,
				method:'GET'
				}).success(function(result){
					
					var statedata = result.responseVO.cityVOs;
					$scope.cities = [];
					angular.forEach(statedata,function(value, key){
						$scope.cities.push({value:value.cityName,key:value.id});
					});
				});
			}	

			$injector.get('usermanagementservice')['getStudent']().then(        		
			 //Request Success 
			function(result) {
				var studentdata = result.data.responseVO.studentVO;
				if(studentdata.studentImage.length > 0){
					$scope.image_id = appCon.globalCon.serviceBaseURL+"fileUpload/downloadFile?id="+studentdata.studentImage[0].blobId+"&directory="+studentdata.studentImage[0].directory+ "&authType=token&authorization="+$scope.login_usertoken;
				}
				$scope.dobsd =  $filter('date')(studentdata.dob, "MM/dd/yyyy");
				$('#dobbirth').data('daterangepicker').setStartDate($scope.dobsd);
				$scope.admissionDatesd =  $filter('date')(studentdata.admissionDate, "MM/dd/yyyy");
				$('#admissionDate').data('daterangepicker').setStartDate($scope.admissionDatesd);
				$scope.StudentVO = studentdata;
			},		
			function(error){
				console.log('failure');
			});
			
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
	console.log("checkfirststep");
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
	var data = $scope.StudentVO;
	data.dob = $filter('date')($scope.dob, "dd/MM/yyyy");
	data.admissionDate = $filter('date')($scope.admissionDate, "dd/MM/yyyy");
	data.parentDob = $filter('date')($scope.parentDob, "dd/MM/yyyy");
	data.createdDate = $filter('date')(data.createdDate, "dd/MM/yyyy");
	data.updatedDate = $filter('date')(data.updatedDate, "dd/MM/yyyy");
	if($scope.studentImage){
		data.studentImage = $scope.studentImage;
	}
	$scope.data = {"SMSRequest":{"StudentVO":$scope.StudentVO}};
	$injector.get('usermanagementservice')['updateStudent']($scope.data).then(        		
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

angular.module(appCon.appName).directive('updateStudentDirective', ['$parse', '$http', function($parse, $http) {
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


  
