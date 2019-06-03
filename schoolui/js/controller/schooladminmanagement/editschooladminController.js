'use strict';

angular.module(appCon.appName).controller('editschooladminController',
					['$injector',  '$scope', 'DTOptionsBuilder',
        function($injector, $scope, DTOptionsBuilder) {
var $http,$state,$cookieStore,$filter;
$http = $injector.get('$http');
$state = $injector.get('$state');
$filter = $injector.get('$filter');
$cookieStore = $injector.get('$cookieStore');
$scope.errormsg = false;
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


$scope.getSchoolById = function(){
	$injector.get('schooladmin')['getSchoolById']().then(        		
		 //Request Success 
		function(result) {
			var schooldata = result.data.responseVO.SchoolVO;
			if(schooldata.schoolImage.length > 0){
				$scope.image_id = appCon.globalCon.serviceBaseURL+"fileUpload/downloadFile?id="+schooldata.schoolImage[0].blobId+"&directory="+schooldata.schoolImage[0].directory+ "&authType=token&authorization="+$scope.login_usertoken;
			}
			console.log($scope.image_id);
			$('#dobbirth').data('daterangepicker').setStartDate($filter('date')(schooldata.smsStartDate, "MM/dd/yyyy"));
			$('#admissionDate').data('daterangepicker').setStartDate($filter('date')(schooldata.smsEndDate, "MM/dd/yyyy"));
			$scope.SchoolVO = schooldata;
		},		
		function(error){
			console.log('failure');
		});
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
	var data = $scope.SchoolVO;
	data.smsStartDate = $filter('date')($scope.smsStartDate, "dd/MM/yyyy");
	data.smsEndDate = $filter('date')($scope.smsEndDate, "dd/MM/yyyy");
	data.createdDate = $filter('date')(data.createdDate, "dd/MM/yyyy");
	data.updatedDate = $filter('date')(data.updatedDate, "dd/MM/yyyy");
	if($scope.schoolImage){
		data.schoolImage = $scope.schoolImage;
	}
	$scope.data = {"SMSRequest":{"SchoolVO":$scope.SchoolVO}};
	console.log($scope.data);	
	$injector.get('schooladmin')['updateSchool']($scope.data).then(        		
		 //Request Success 
		function(result) {
			console.log(result.data.statusFlag);
			if(result.data.statusFlag == 'Ok')
				$state.go('schoolmgmt');
			if(result.data.statusFlag == 'Error'){
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



}]);


angular.module(appCon.appName).directive('mySchoolupdateDirective', ['$parse', '$http', function($parse, $http) {
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


  
