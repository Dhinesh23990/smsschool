'use strict';

angular.module(appCon.appName).controller('configbatchconfigController',
					['$injector',  '$scope', 'DTOptionsBuilder',
        function($injector, $scope, DTOptionsBuilder) {
var $http,$state,$modal,$cookieStore,$filter;
$http = $injector.get('$http');
$state = $injector.get('$state');
$modal = $injector.get('$modal');
$cookieStore =$injector.get('$cookieStore');
$filter = $injector.get('$filter');
$scope.hidesearchdet = true;
$scope.configbatchdet = true;	

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

	$http ({
		url:appCon.globalCon.serviceBaseURL+"dashboard/getAllConfigurationBySchoolId?schoolId="+$cookieStore.get('schoolId'),
		method:'GET'
		}).success(function(result){
            var batchdata = result.responseVO.BatchVOs;	
			 $scope.batchdata = [];
			angular.forEach(batchdata,function(value, key){
				$scope.batchdata.push({value:value.batchName,key:value.id});
			});
			var classdata = result.responseVO.ClassVOs;		
			$scope.classdata = [];
			angular.forEach(classdata,function(value, key){
				$scope.classdata.push({value:value.className,key:value.id});
			});
			var mediumdata = result.responseVO.MediumVOs;		
			$scope.mediumdata = [];
			angular.forEach(mediumdata,function(value, key){
				$scope.mediumdata.push({value:value.mediumName,key:value.id});
			});
			var sectiondata = result.responseVO.SectionVOs;		
			$scope.section = [];
			angular.forEach(sectiondata,function(value, key){
				$scope.section.push({value:value.sectionName,key:value.id});
			});
			var coursedata = result.responseVO.CourseVOs;		
			$scope.course = [];
			angular.forEach(coursedata,function(value, key){
				$scope.course.push({value:value.courseName,key:value.id});
			});
			
	});
		
	$http({
		url:appCon.globalCon.serviceBaseURL+"teacher/getAllTeacher?schoolId="+$cookieStore.get('schoolId')+"&pageIndex=0&pageSize=100",
		method:'GET'
	}).success(function(result){
		var datateacherdetail = result.responseVO.teacherList;
		$scope.teacher = [];
		angular.forEach(datateacherdetail,function(value, key){
			$scope.teacher.push({value:value.staffName,key:value.id});
		});
		
	});
	
	$scope.currentPage = 1
  ,$scope.numPerPage = 10
  ,$scope.maxSize = 5;

	
	$scope.getallbatchconfigdata = function(){

		$http({
			url:appCon.globalCon.serviceBaseURL+"batchConfiguration/getAllBatchConfigurationBySchoolId?shoolId="+$cookieStore.get('schoolId')+"&pageIndex=0&pageSize="+appCon.globalCon.paginationSize,
			method:'GET'
		}).success(function(result){
			$scope.totalRecords = result.responseVO.totalRecords;
			
		});
	}
	
	$scope.$watch('currentPage + numPerPage', function() {
	   
    var begin = (($scope.currentPage - 1) * $scope.numPerPage)
    , end = begin + $scope.numPerPage;
	
     $http({
		url:appCon.globalCon.serviceBaseURL+"batchConfiguration/getAllBatchConfigurationBySchoolId?shoolId="+$cookieStore.get('schoolId')+"&pageIndex="+begin+"&pageSize="+appCon.globalCon.paginationSize,
		method:'GET'
	}).success(function(result){
		var itemsdata = result.responseVO.batchConfigurationVOs;
		var index_id = begin+1;
		angular.forEach(itemsdata,function(value,key){
			value.indexId = index_id;
			index_id++;
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
	
	$scope.createbatchconfig = function(){ 
    $scope.errormsg = false;
	var data = $scope.BatchConfigurationVO;
	data.schoolId = $cookieStore.get('schoolId');
	$scope.data = {"SMSRequest":{"BatchConfigurationVO":$scope.BatchConfigurationVO}};
	
		
	$injector.get('configmanagementservice')['saveBatchConfiguration']($scope.data).then(        		
		 //Request Success 
		function(result) {
			
			if(result.data.statusFlag == 'Ok')
				$state.go($state.current, {}, {reload: true});
			if(result.data.statusFlag == 'Error')
				$scope.errormsg = true;
		},		
		function(error){
			console.log('failure');
		});
		
	
}


$scope.update = function(){
		$scope.errormsg = false;
	var updata = $scope.UpdateVO;
	$scope.updatedata = {"SMSRequest":{"BatchConfigurationVO":updata}};
	console.log($scope.updatedata);	
	$injector.get('configmanagementservice')['updateBatchConfiguration']($scope.updatedata).then(        		
		 //Request Success 
		function(result) {
			
			if(result.data.statusFlag == 'Ok')
				$state.go($state.current, {}, {reload: true});
			if(result.data.statusFlag == 'Error')
				$scope.errormsg = true;
		},
		function(error){
			console.log('failure');
		});
	}	
	
	
	

$scope.edit = function(id)
{
	
	$scope.createform = false;
	$scope.hidesearchdet = false;
	$scope.updateview=false;
	$scope.updatedata = true;
	$scope.configbatchdet = false;
	$scope.createdata = true;
	$http({
		url:appCon.globalCon.serviceBaseURL+"batchConfiguration/getBatchConfigurationById?batchConfigurationId="+id,
		method:'GET'
	}).success(function(result){
		
		var getupdata = result.responseVO.BatchConfigurationVO;
		getupdata.createdDate = $filter('date')(getupdata.createdDate, "dd/MM/yyyy");
		getupdata.updatedDate = $filter('date')(getupdata.updatedDate, "dd/MM/yyyy");
		$scope.UpdateVO = getupdata;
	});
	
}


$scope.updatereturnsearch =function()
{
	$scope.hidesearchdet = true;
	$scope.configbatchdet = true;
	$scope.updatedata = false;
	$scope.errormsg = false;
	
}

$scope.backtosearch = function(){
	
    $scope.BatchConfigurationVO = "";
	$scope.hidesearchdet = false;
	$scope.configbatchdet = false;
	$scope.updateview=false;
	$scope.createdata = true;
	$scope.createform = true;
	$scope.viewdet = false;
	$scope.errormsg = false;
	
}
$scope.returnsearch = function(){
	$scope.createForm.$setPristine();
		  var defaultForm = {
             batchId : "",  
			 mediumId : "",
			 classId : "",  
			 sectionId : "",
			 courseId : "",  
			 classTeacherId : ""
          };                  
	$scope.BatchConfigurationVO = defaultForm;
	$scope.errormsg = false;
	$scope.hidesearchdet = true;
	$scope.configbatchdet = true;
	

}

$scope.clearcontent = function(){
	$scope.createForm.$setPristine();
	  var defaultForm = {
             batchId : "",  
			 mediumId : "",
			 classId : "",  
			 sectionId : "",
			 courseId : "",  
			 classTeacherId : ""
          }; 
	  $scope.BatchConfigurationVO = angular.copy(defaultForm);
}
$scope.view = function(id)
{
	$scope.hidesearchdet = true;
	$scope.configbatchdet = false;
	$scope.updatedata = false;
	$scope.viewdet = true;
	$scope.updateview=true;
	$scope.errormsg = false;
	$http({
		url:appCon.globalCon.serviceBaseURL+"batchConfiguration/getBatchConfigurationById?batchConfigurationId="+id,
		method:'GET'
	}).success(function(result){
		
		var getupdata = result.responseVO.BatchConfigurationVO;
		getupdata.createdDate = $filter('date')(getupdata.createdDate, "dd/MM/yyyy");
		getupdata.updatedDate = $filter('date')(getupdata.updatedDate, "dd/MM/yyyy");
		$scope.UpdateVO = getupdata;
	});
}
$scope.viewupdate = function()
{
	$scope.updatedata = true;
	$scope.hidesearchdet = false;
	$scope.configbatchdet = false;
	$scope.viewdet = false;
	$scope.createdata = false;
	$scope.updateview = false;
	$scope.createform = false;
	$scope.errormsg = false;
	
}

$scope.go = function(){
	   
		$scope.updatedata = false;	
		$scope.hidesearchdet = true;
		$scope.configbatchdet = true;
		$scope.errormsg = false;
	
		
	}
	$scope.deletebatchconfigure = function(deletebatchconfigid){

	$scope.$modalInstance = $modal.open({
		 scope: $scope,
		 templateUrl: 'deletebatchconfig.html',
		 controller: function($scope, $modalInstance, $rootScope, $state) {
			  $scope.deletebatchconfigok = function() {
				  deleletconformbatchconfig(deletebatchconfigid);
			  };
			  $scope.okcancel = function() {
				  $modalInstance.close();
			  };
		  }
	  });
}

function deleletconformbatchconfig(deleteid){
	
	$http({
		url:appCon.globalCon.serviceBaseURL+"batchConfiguration/deleteBatchConfiguration?batchConfigurationId="+deleteid,
		method:'DELETE'
	}).success(function(result){
		$state.go($state.current, {}, {reload: true});
		
	});
}
		

}]);


