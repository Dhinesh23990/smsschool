'use strict';

angular.module(appCon.appName).controller('notificationcontroller',
					['$injector',  '$scope', 'DTOptionsBuilder',
        function($injector, $scope, DTOptionsBuilder,$cookieStore) {
var $http,$state,$modal,$cookieStore,$filter;
$http = $injector.get('$http');
$state = $injector.get('$state');
$modal = $injector.get('$modal');
$filter = $injector.get('$filter');
$cookieStore = $injector.get('$cookieStore');
$filter = $injector.get('$filter');
$scope.hidesearchdet = true;
$scope.updatehidesearchdet = false;
$scope.tablehidesearchdet = true;
$scope.errormsg = false;


$('input[name="birthdate"]').daterangepicker({
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
$scope.currentPage = 1
  ,$scope.numPerPage = 10
  ,$scope.maxSize = 5;


$scope.dtOptions = DTOptionsBuilder.newOptions()
						.withOption('lengthChange',false)
						.withOption('info',false)
						.withOption('searching',false)
						.withOption('sort',false)
						.withOption('filter',false)
						.withOption("paging",false);
						
$scope.getAllNotificationData = function(){
$scope.attendancetable = true;
var date = $filter('date')($scope.attenddancedate, "dd/MM/yyyy");
	
$http({
		url:appCon.globalCon.serviceBaseURL+"notification/getAllNotifiBySchoolId?schoolId="+$cookieStore.get('schoolId')+"&pageIndex=0&pageSize="+appCon.globalCon.paginationSize,
		method:'GET'
	}).success(function(result){
		$scope.totalRecords = result.responseVO.totalRecords;	
	});
} 

$scope.$watch('currentPage + numPerPage', function() {
	   
    var begin = (($scope.currentPage - 1) * $scope.numPerPage)
    , end = begin + $scope.numPerPage;
	
     $http({
		url:appCon.globalCon.serviceBaseURL+"notification/getAllNotifiBySchoolId?schoolId="+$cookieStore.get('schoolId')+"&pageIndex="+begin+"&pageSize="+appCon.globalCon.paginationSize,
		method:'GET'
	}).success(function(result){
		var itemsdata = result.responseVO.notificationVOs;
		var index_id = begin+1;
		angular.forEach(itemsdata,function(value,key){
			console.log(value.message);
			value.indexId = index_id;
			index_id++;
		})
		$scope.items = itemsdata;
		console.log('hello'+$scope.items);
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

console.log('after123');

$scope.updatehidesearchdet=false;

  $scope.createNotification = function(){
	  console.log('vasudevan');
	  console.log($scope.data)
		 $scope.errormsg = false;
		
		$scope.data.schoolId = $cookieStore.get('schoolId');
		$scope.notificationData = {"SMSRequest":{"NotificationVO":$scope.data}};
		$injector.get('notificationmanagementservice')['saveNotification']($scope.notificationData).then(        		
			 //Request Success 
				
			function(result) {	
				console.log("after");
				console.log($scope.data)
				console.log(result.data.statusFlag);
				if(result.data.statusFlag == 'Ok')
					$state.go($state.current, {}, {reload: true});
				if(result.data.statusFlag == 'Error')
					$scope.errormsg = true;
			},
			function(error){
				console.log('failure');
			
			});			
}
   
   
      
$scope.edit = function(id){
		
    $scope.tablehidesearchdet= false;
	$scope.updatehidesearchdet = true;
	$scope.hidesearchdet = false; 
		
	$http({
		url:appCon.globalCon.serviceBaseURL+"notification/getNotificationById?notificationId="+id,
		method:'GET'
	}).success(function(result){
		
		var getupdata = result.responseVO.NotificationVO;
		console.log('hai checking'+getupdata);
		getupdata.createdDate = $filter('date')(getupdata.createdDate, "dd/MM/yyyy");
		getupdata.updatedDate = $filter('date')(getupdata.updatedDate, "dd/MM/yyyy");
		$scope.UpdateVO = getupdata;
		
	});
}

$scope.update = function(){
	var updata = $scope.UpdateVO;
	$scope.updatedata = {"SMSRequest":{"NotificationVO":updata}};
	console.log($scope.updatedata);	
	$injector.get('notificationmanagementservice')['updateNotification']($scope.updatedata).then(        		
		 //Request Success 
		function(result) {
			console.log(result);
			if(result.data.statusFlag == 'Ok')
				$state.go($state.current, {}, {reload: true});
			if(result.data.statusFlag == 'Error')
					$scope.errormsg = true;
		},
		function(error){
			console.log('failure');
		});
	}
	

$scope.backtosearch = function(){ 
    $scope.NotificationVO = "";
	$scope.hidesearchdet = false;
	$scope.tablehidesearchdet = false;
	$scope.noresultsfound = false;
	
}
$scope.returnsearch = function(){
	$scope.notificationform.$setPristine();
		  var defaultForm = {
				  message : ""  
          };
		 
		$scope.NotificationVO = defaultForm;
	$scope.noresultsfound = true;
	$scope.hidesearchdet = true;
	$scope.tablehidesearchdet = true;

}

$scope.updatereturnsearch = function(){
	   $scope.errormsg = false;	   
	   $scope.updatehidesearchdet = false;
	   $scope.hidesearchdet = true;
	   $scope.tablehidesearchdet = true;
	  
   }	
   $scope.cancel = function(){
	    $scope.updatehidesearchdet = false;
		$scope.errormsg = false;
		$scope.hidesearchdet = true;
	    $scope.tablehidesearchdet = true;		
	}
	$scope.clear = function(){
		  var defaultForm =  {
				  message : ""  
          };
		  console.log("before");
		  
		  console.log("after");
		$scope.NotificationVO = angular.copy(defaultForm);
		$scope.notificationform.$setPristine();
		$scope.errormsg = false;		
	}
	
	$scope.deletenotification = function(deletenotificationid){
		 $scope.$modalInstance = $modal.open({
				 scope: $scope,
				 templateUrl: 'deletenotification.html',
				 controller: function($scope, $modalInstance, $rootScope, $state) {
					  $scope.deletenotificationok = function() {
						  deletenotification(deletenotificationid);
						
					  };
					  $scope.okcancel = function() {
						  $modalInstance.close();
					  };
				  }
			  });
		}

		function deletenotification(deleteid){
			console.log(deleteid);
			$http({
				url:appCon.globalCon.serviceBaseURL+"notification/deleteNotification?notificationId="+deleteid,
				method:'DELETE'
			}).success(function(result){
				console.log(result);
				$state.go($state.current, {}, {reload: true});
				
			});
		}

}]);


angular.module(appCon.appName).directive('numericOnlyNotification', function(){
    return {
        require: 'ngModel',
        link: function(scope, element, attrs, modelCtrl) {

            modelCtrl.$parsers.push(function (inputValue) {
                var transformedInput = inputValue ? inputValue.replace(/[^a-zA-Z+-1]/g,'') : null;

                if (transformedInput!=inputValue) {
                    modelCtrl.$setViewValue(transformedInput);
                    modelCtrl.$render();
                }

                return transformedInput;
            });
        }
    };
});


