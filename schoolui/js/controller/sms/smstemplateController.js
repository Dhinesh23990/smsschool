'use strict';

angular.module(appCon.appName).controller('smstempcontroller',
					['$injector',  '$scope', 'DTOptionsBuilder',
        function($injector, $scope, DTOptionsBuilder) {
var $http,$state,$modal,$cookieStore,$filter;
$http = $injector.get('$http');
$state = $injector.get('$state');
$modal = $injector.get('$modal');
$cookieStore = $injector.get('$cookieStore');
$filter = $injector.get('$filter');
$scope.hidesearchdet = true;
$scope.updatehidesearchdet = false;
$scope.tablehidesearchdet = true;

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

	$scope.Sms = [{value:"All Student",key:"Student"},{value:"Class Wise",key:"Class Wise"},{value:"Induvidual Student",key:"Induvidual Student"}
               ,{value:"All Teacher",key:"All Teacher"},{value:"Induvidual Teacher",key:"Induvidual Teacher"},{value:"Admin/Management",key:"Admin/Management"}
			   ,{value:"Group/Category",key:"Group/Category"}];
			   
	$scope.getAllSmsTempData = function(){
	
		$http({
				url:appCon.globalCon.serviceBaseURL+"smstemplate/getAllSmsTemplate?schoolId="+$cookieStore.get('schoolId')+"&pageIndex=0&pageSize="+appCon.globalCon.paginationSize,
				method:'GET'
			}).success(function(result){
				$scope.totalRecords = result.responseVO.totalRecords;
		});
	}
			
	$scope.$watch('currentPage + numPerPage', function() {
	   
    var begin = (($scope.currentPage - 1) * $scope.numPerPage)
    , end = begin + $scope.numPerPage;
	
     $http({
		url:appCon.globalCon.serviceBaseURL+"smstemplate/getAllSmsTemplate?schoolId="+$cookieStore.get('schoolId')+"&pageIndex="+begin+"&pageSize="+appCon.globalCon.paginationSize,
		method:'GET'
	}).success(function(result){
		var itemsdata = result.responseVO.smsTemplateList;
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
		
	$scope.createsmstemp = function(){ 
			
			var data = $scope.SmsTemplateVO;
			data.schoolId = $cookieStore.get('schoolId');
			$scope.data = {"SMSRequest":{"SmsTemplateVO":$scope.SmsTemplateVO}};
			console.log($scope.data);
			//console.log($scope.data);	
			$injector.get('smsservice')['saveSmsTemplate']($scope.data).then(        		
				 //Request Success 
				function(result) {				
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
			url:appCon.globalCon.serviceBaseURL+"smstemplate/getSmsTemplate?id="+id,
			method:'GET'
		}).success(function(result){
			console.log(result);
			var getupdata = result.responseVO.smsLog;
			getupdata.createdDate = $filter('date')(getupdata.createdDate, "dd/MM/yyyy");
			getupdata.updatedDate = $filter('date')(getupdata.updatedDate, "dd/MM/yyyy");
			$scope.UpdateVO = getupdata;
		});
	}	
			
			
	$scope.update = function(){
		var updata = $scope.UpdateVO;
		$scope.updatedata = {"SMSRequest":{"SmsTemplateVO":updata}};
		//console.log($scope.updatedata);	
		$injector.get('smsservice')['updateSmsTemplate']($scope.updatedata).then(        		
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
			
	  
	  
		
	$scope.deletesmstemp = function(deleteSmsTemplate){
	   
	   console.log(deleteSmsTemplate);
		$scope.$modalInstance = $modal.open({
			 scope: $scope,
			 templateUrl: 'deletesmstemp.html',
			 controller: function($scope, $modalInstance, $rootScope, $state) {
				  $scope.deletesmstempok = function() {
					  deleletok(deleteSmsTemplate);
				  };
				  $scope.okcancel = function() {
					  $modalInstance.close();
				  };
			  }
		  });
	}

	function deleletok(deleteid){
		console.log("dfsdfds");
		$http({
			url:appCon.globalCon.serviceBaseURL+"smstemplate/deleteSmsTemplate?smsTemplateId="+deleteid,
			method:'DELETE'
		}).success(function(result){
			console.log(result);
			$state.go($state.current, {}, {reload: true});
			
		});
	}		  

			
	$scope.backtosearch = function(){ 
		$scope.SmsTemplateVO = "";
		$scope.search="";	
		$scope.hidesearchdet = false;
		$scope.tablehidesearchdet = false;
	}



	$scope.returnsearch = function(){
		$scope.createform.$setPristine();
			   var defaultForm = {
				  shortDescription : "",
				  template : ""
			  };
		$scope.SmsTemplateVO = defaultForm;
		$scope.hidesearchdet = true;
		$scope.tablehidesearchdet = true;
	}

	$scope.clearcontent = function(){
		$scope.className = "";
	}

   $scope.updatereturnsearch = function(){   
	   $scope.updatehidesearchdet = false;
	   $scope.hidesearchdet = true;
	   $scope.tablehidesearchdet = true;
   }
    
	$scope.cancel = function(){
		$scope.updatehidesearchdet = false;	
		$scope.hidesearchdet = true;
		$scope.tablehidesearchdet = true;
		
	}
   $scope.clear = function(){
	    $scope.createform.$setPristine();
		   var defaultForm = {
              shortDescription : "",
			  template : ""
          };
		$scope.SmsTemplateVO = defaultForm;
		
	}
	


	$scope.finalstep = function(){
		$state.go('studentmanagement');
	}


}]);



