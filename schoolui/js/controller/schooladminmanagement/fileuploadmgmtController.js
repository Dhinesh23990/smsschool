'use strict';

angular.module(appCon.appName).controller('fileuploadmgmtController',
					['$injector',  '$scope', 'DTOptionsBuilder', '$stateParams',
function($injector, $scope, DTOptionsBuilder, $stateParams) {
	var $http,$state,$modal,$cookieStore,$filter,school_id;
	$http = $injector.get('$http');
	$state = $injector.get('$state');
	$modal = $injector.get('$modal');
	$filter = $injector.get('$filter');
	$cookieStore = $injector.get('$cookieStore');
	$scope.login_usertoken = appCon.cookie.getItem('loginusertoken');
	$scope.school_id = $stateParams.schoolId;
	$scope.emptyfile =false;
	$scope.show_datatables = false;
	$scope.coursedata = false;
	$scope.loading = false;
	$scope.dtOptions = DTOptionsBuilder.newOptions()
						.withOption('lengthChange',false)
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
		$state.go('admindashboard');		
	}

	$scope.getallschooldata = function(){
		
		$http({
			url:appCon.globalCon.serviceBaseURL+"school/getSchoolById?schoolId="+$scope.school_id,
			method:'GET'
		}).success(function(result){
			var schooldata = result.responseVO.SchoolVO;
			$scope.schoolname = schooldata.schoolName;
		});
		/*$http({
				url:appCon.globalCon.serviceBaseURL+"section/getAllSectionBySchoolId?shoolId="+$scope.school_id+"&pageIndex=0&pageSize=500",
				method:'GET'
		}).success(function(result){
			$scope.sectiondatas = result.responseVO.sectionVOs;
			var jk=0;
			angular.forEach($scope.sectiondatas,function(value,key){
				if(jk == 0)
					$scope.sectionId = value.id;
				jk++;
			})
		});
			
		$http({
			url:appCon.globalCon.serviceBaseURL+"class/getAllClassBySchoolId?shoolId="+$scope.school_id+"&pageIndex=0&pageSize=500",
			method:'GET'
		}).success(function(result){
			$scope.classdatas = result.responseVO.classVOs;
			var jk=0;
			angular.forEach($scope.classdatas,function(value,key){
				if(jk == 0)
					$scope.classId = value.id;
				jk++;
			})
		});

		*/

		$http ({
		url:appCon.globalCon.serviceBaseURL+"dashboard/getAllConfigurationBySchoolId?schoolId="+$scope.school_id,
		method:'GET'
		}).success(function(result){
			$scope.batchdatas = result.responseVO.BatchVOs;	
			$scope.classdatas = result.responseVO.ClassVOs;
			$scope.mediumdatas = result.responseVO.MediumVOs;
			$scope.sectiondatas = result.responseVO.SectionVOs;
			$scope.coursedatas = result.responseVO.CourseVOs;

			var jk=0,kj=0,jj=0;
			angular.forEach($scope.classdatas,function(value,key){
				if(jk == 0)
					$scope.classId = value.id;
				jk++;
			});
			angular.forEach($scope.sectiondatas,function(value,key){
				if(kj == 0)
					$scope.sectionId = value.id;
				kj++;
			});
			angular.forEach($scope.batchdatas,function(value,key){
				if(jj == 0)
					$scope.batchId = value.batchCode;
				jj++;
			});


		});
	}
	
	$scope.updateuploaddata = function(item){
		$scope.loading = true;
		var data = item;
		data.createdDate = $filter('date')(data.createdDate, "dd/MM/yyyy");
		data.updatedDate = $filter('date')(data.updatedDate, "dd/MM/yyyy");
		$scope.data = {"SMSRequest":{"BulkImportStudentDetailVO":data}};
		$injector.get('schooladmin')['updateBulkInportStudent']($scope.data).then(        		
		 //Request Success 
		function(result) {
			$scope.loading = false;
			if(result.data.statusFlag == 'Ok')
				$scope.searchdata();
		},		
		function(error){
			console.log('failure');
		});
	}
	
	
	$scope.uploaddata = function(){
		$scope.emptyfile =false;
		$scope.loading = true;
		var uploadUrl = appCon.globalCon.serviceBaseURL+ "student/bulkImportStudent?schoolId="+$scope.school_id+"&classId="+$scope.classId+"&sectionId="+$scope.sectionId+"&file="+$scope.uploadfiledata;
		$http.post(uploadUrl, $scope.uploadfiledata, {
			transformRequest: angular.identity,
			headers: {
			'Content-Type': undefined
			}
		}).success(function(response){
			if(response.statusFlag == 'Ok'){
				$scope.loading = false;
				$scope.$modalInstance = $modal.open({
				 scope: $scope,
				 templateUrl: 'successupload.html',
				 controller: function($scope, $modalInstance, $rootScope, $state) {
					  $scope.deletestudentok = function() {
						 $state.go($state.current, {},{reload:true});
					  };
				  }
				});
			}
			if(response.errorMsg == 'file is empty'){
				$scope.loading = false;
				$scope.emptyfile =true;
				$scope.empty_file = "Your File is Empty";
			}
		});
		
	}
	
	$scope.searchdata = function(){
		$scope.coursedata = false;
		$scope.show_datatables = false;
		$http({
			url:appCon.globalCon.serviceBaseURL+"student/getUploadStudentsByClassAndSection?schoolId="+
			$scope.school_id+"&classId="+$scope.classId+"&sectionId="+$scope.sectionId+"&batchId="+$scope.batchId,
			method:'GET'
		}).success(function(result){
			var uploaddata = result.responseVO.studentList;
			if(uploaddata.length > 0){
				$scope.show_datatables = true;
				angular.forEach(uploaddata,function(value,key){
					if(value.course)
						$scope.coursedata = true;
				});
			}
			
			$scope.uploaditems = uploaddata;
		});
	}
	
}]);

angular.module(appCon.appName).directive('myFileuploadDirective', ['$parse', '$http', function($parse, $http) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.ngFileModel);
            var isMultiple = attrs.multiple;
            var modelSetter = model.assign;
            element.bind('change', function() {
                var values;
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
                    values = value;
                });
                scope.$apply(function() {
                    var validFileType = ".xls,.XLS,.xlsx,.XLSX";
                    var extension = values.name.substring(values.name.lastIndexOf('.'));
					if (validFileType.indexOf(extension) >= 0) {
						var formData = new FormData();
						formData.append('file', element[0].files[0]);
						scope.uploadfiledata = formData;
						var uploadUrl = appCon.globalCon.serviceBaseURL+ "student/bulkImportStudent?schoolId="+scope.school_id+"&classId="+scope.classId+"&sectionId="+scope.sectionId+"&file="+formData;
						scope.uploadfile = "success";
						scope.success_file = true;
						scope.successfile = "Successfully uploaded";
						/*
						$http.post(uploadUrl, formData, {
							transformRequest: angular.identity,
							headers: {
							'Content-Type': undefined
							}
						}).success(function(response){
							console.log(response);
						});*/
					}else{
						 scope.uploadfile = "";
						 scope.successfile = "Only support the file types xls, xlsx";
						 scope.success_file = true;
					}
                });
            });

        }

    };
}]);
