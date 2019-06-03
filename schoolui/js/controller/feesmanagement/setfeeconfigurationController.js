'use strict';

angular.module(appCon.appName).controller('setfeeconfigurationController',
					['$injector',  '$scope', 'DTOptionsBuilder', 'MethodService',
        function($injector, $scope, DTOptionsBuilder, MethodService) {
var $http,$state,$modal,$filter,$cookieStore;
$http = $injector.get('$http');
$state = $injector.get('$state');
$modal = $injector.get('$modal');
$filter = $injector.get('$filter');
$cookieStore = $injector.get('$cookieStore');
$scope.hidesearchdet = true;
$scope.hidesearchdetgrid = true;
$scope.feeconfigurationdetails = true;
$scope.hidetable=true;
$scope.tab2hidetable=false;
$scope.viewfeesturcturedetails = false;
$scope.setfeeconfigurationandview = 'feeconfiguration';
$scope.feeconfigurationandview ="Fee Configuration";

$scope.terms = [{value:"1",key:"1"},{value:"2",key:"2"},{value:"3",key:"3"}];
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

	
$scope.findtotalamount = function(feetypes){
	var amt = 0;
	angular.forEach(feetypes,function(value,key){
		if(value.amount)
			amt = parseInt(amt) + parseInt(value.amount); 
	});
	$scope.totalAmount = amt;
}

$scope.findupdatetotalamount = function(feetypes){
	var amt = 0;
	angular.forEach(feetypes,function(value,key){
		if(value.amount)
			amt = parseInt(amt) + parseInt(value.amount); 
	});
	$scope.updatetotalAmount = amt;
}

$http ({
		url:appCon.globalCon.serviceBaseURL+"dashboard/getAllConfigurationBySchoolId?schoolId="+$cookieStore.get('schoolId'),
		method:'GET'
		}).success(function(result){
			var batchdata = result.responseVO.BatchVOs;	
			$scope.batches = [];
			angular.forEach(batchdata,function(value, key){
				$scope.batches.push({value:value.batchName,key:value.id});
			});
			var classdata = result.responseVO.ClassVOs;		
			$scope.classes = [];
			angular.forEach(classdata,function(value, key){
				$scope.classes.push({value:value.className,key:value.id});
			});
		});


						
$scope.getallfeeconfigtype = function(){
	
$http({
		url:appCon.globalCon.serviceBaseURL+"feeType/getAllFeeTypeBySchoolId?shoolId="+$cookieStore.get('schoolId')+"&pageIndex=0&pageSize=150",
		method:'GET'
	}).success(function(result){
		var feetypes = result.responseVO.feeTypeVOs;
		angular.forEach(feetypes,function(value,key){
			value.amount = 0;
		});
		$scope.feetypes = feetypes;
		$scope.totalAmount = 0;
	});
	
	
$http({
		url:appCon.globalCon.serviceBaseURL+"feeConfiguration/getAllFeeConfigurationBySchoolId?shoolId="+$cookieStore.get('schoolId')+"&pageIndex=0&pageSize="+appCon.globalCon.paginationSize,
		method:'GET'
	}).success(function(result){
		$scope.totalRecords = result.responseVO.totalRecords;
		
	});
}

$scope.$watch('currentPage + numPerPage', function() {
	   
    var begin = (($scope.currentPage - 1) * $scope.numPerPage)
    , end = begin + $scope.numPerPage;
	
     $http({
		url:appCon.globalCon.serviceBaseURL+"feeConfiguration/getAllFeeConfigurationBySchoolId?shoolId="+$cookieStore.get('schoolId')+"&pageIndex="+begin+"&pageSize="+appCon.globalCon.paginationSize,
		method:'GET'
	}).success(function(result){
		var itemsdata = result.responseVO.feeConfigurationVOs;
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
  
  
$scope.showfeestructuretable = function(){
	$scope.tab2hidetable=true;
	$http({
		url:appCon.globalCon.serviceBaseURL+"feeConfiguration/getAllFeeConfigurationByClassFeeCategory?shoolId="+$cookieStore.get('schoolId')+"&classId="+$scope.classId+"&feeCategory="+$scope.feeCategory,
		method:'GET'
	}).success(function(result){
		//$scope.items = result.responseVO.feeConfigurationVOs;
		var getallshowdata = result.responseVO.feeConfigurationVOs;
		if(getallshowdata){
			
		angular.forEach(getallshowdata,function(value,key){
			value.feeStartDate =  $filter('date')(value.feeStartDate, "dd-MM-yyyy");
			value.feeReminderDate =  $filter('date')(value.feeReminderDate, "dd-MM-yyyy");
		});
		}
		$scope.items = getallshowdata;
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
$scope.create = function(){ 
	$scope.errormsg = false;
	var data = $scope.FeeConfigurationVO,deletefeetype= [];
	data.schoolId = $cookieStore.get('schoolId');
	angular.forEach($scope.feetypes,function(value,key){
		
		var datafee = {"feeType":value.feeType,"amount":value.amount,"schoolId":$cookieStore.get('schoolId'),"checked":value.checked,"id":value.id}
		deletefeetype.push(datafee);
		
	});
	data.feeTypes = deletefeetype;
	data.amount  = $scope.totalAmount;
	//data.feeStartDate = $filter('date')($scope.feeStartDate, "dd/MM/yyyy");
	//data.feeReminderDate = $filter('date')($scope.feeReminderDate, "dd/MM/yyyy");
	
	$scope.data = {"SMSRequest":{"FeeConfigurationVO":$scope.FeeConfigurationVO}};
		
	$injector.get('feesmanagementservice')['saveFeeConfiguration']($scope.data).then(		 //Request Success 
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
$scope.edit = function(id){
    $scope.hidetable= false;
	$scope.showupdatefrm = true;
	$scope.hidesearchdet = false; 
	$http({
		url:appCon.globalCon.serviceBaseURL+"feeConfiguration/getFeeConfigurationById?feeConfigurationId="+id,
		method:'GET'
	}).success(function(result){
		var getupdata = result.responseVO.FeeConfigurationVO,checkfeetypes = [],assignfeetype;
		/*$scope.feeStartDate = $filter('date')(getupdata.feeStartDate, "dd/MM/yyyy");
		$scope.feeReminderDate = $filter('date')(getupdata.feeReminderDate, "dd/MM/yyyy");
		*/
		getupdata.createdDate = $filter('date')(getupdata.createdDate, "dd/MM/yyyy");
		getupdata.updatedDate = $filter('date')(getupdata.updatedDate, "dd/MM/yyyy");
		$scope.updatetotalAmount = getupdata.amount;
		
		assignfeetype = getupdata.feeTypes;
		angular.forEach(assignfeetype,function(value,key){
			checkfeetypes.push(value);
		})
		$http({
		url:appCon.globalCon.serviceBaseURL+"feeType/getAllFeeTypeBySchoolId?shoolId="+$cookieStore.get('schoolId')+"&pageIndex=0&pageSize=150",
		method:'GET'
			}).success(function(result){
				var feetypes = result.responseVO.feeTypeVOs;
				var check_feetype = feetypes.length - assignfeetype.length;
				if(check_feetype>0){
					for(var ik = feetypes.length;ik>checkfeetypes.length;ik--){
						
						var datafee = {"feeType":feetypes[ik-1].feeType,"amount":feetypes[ik-1].amount,"schoolId":$cookieStore.get('schoolId'),"checked":feetypes[ik-1].checked,"id":feetypes[ik-1].id}
						checkfeetypes.push(datafee);
					}
				}
				
				console.log(checkfeetypes);
				getupdata.feeTypes = checkfeetypes;
				$scope.UpdateVO = getupdata;
			});
		});
		
	}

	
$scope.update = function(){
	$scope.errormsg = false;
	var updata = $scope.UpdateVO;
	updata.amount = $scope.updatetotalAmount;
	$scope.updatedata = {"SMSRequest":{"FeeConfigurationVO":updata}};
	$injector.get('feesmanagementservice')['updateFeeConfiguration']($scope.updatedata).then(        		
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

$scope.show=function(){
	$scope.errormsg = false;
	$scope.hidetable=true;
}


$scope.backtosearch = function(){
	$scope.FeeConfigurationVO = "";
	$scope.hidetable=false;
	$scope.hidesearchdet = false;
}
$scope.returnsearch = function(){
	$scope.search="";
	$scope.createForm.$setPristine();
		   var defaultForm = {
              amount : "",
			  feeStartDate:"",
			  feeReminderDate:""
          };
	$scope.FeeConfigurationVO = defaultForm;
	$scope.hidetable=true;
	$scope.hidesearchdet = true;
}

$scope.backtosearchgrid = function(){

	$scope.hidesearchdetgrid = false;
}
$scope.returnsearchgrid = function(){
	$scope.searchgrid="";
	$scope.hidesearchdetgrid = true;
}
$scope.updatesearch	=function()
{
	$scope.hidesearchdet = true;
	$scope.hidetable=true;
	$scope.showupdatefrm = false;
}
    
 $scope.go=function()
{
	$scope.errormsg = false;
	$scope.hidesearchdet = true;
	$scope.hidetable=true;
	$scope.showupdatefrm = false;
	
}  
$scope.goclear=function()
{
	$scope.errormsg = false;
	$scope.createForm.$setPristine();
		   var defaultForm = {
              amount : "",
			  feeStartDate:"",
			  feeReminderDate:""
          };
	$scope.FeeConfigurationVO = defaultForm;
	$scope.getallfeeconfigtype();

}	


$scope.types = [{value:"Class wise",key:"Class wise"},{value:"Section wise",key:"Section wise"}];
$scope.feecategories=[{value:"Government Fee",key:"Government Fee "},{value:"Management Fee",key:"Management Fee"}];



$scope.feeconfigdet = function(){

	if($scope.setfeeconfigurationandview != "feeconfiguration"){
		$scope.feeconfigurationdetails = true;
		$scope.hidetable=true;
		$scope.viewfeesturcturedetails = false;
		$scope.setfeeconfigurationandview = "feeconfiguration";
		$scope.feeconfigurationandview ="Fee Configuration";
		angular.element("#view_feestructure").removeClass("activeview");
		angular.element("#fee_config").removeClass("hoverview").removeClass("defaultcol");
		angular.element("#view_feestructure").addClass("hoverview").addClass("defaultcol");
		angular.element("#fee_config").addClass("activeview");		
	}
}

$scope.viewfeestructdet = function(){
	
	if($scope.setfeeconfigurationandview == "feeconfiguration"){
		$scope.feeconfigurationandview="View Fee Sturcture";
		$scope.feeconfigurationdetails = false;
		$scope.hidetable=false;
		$scope.viewfeesturcturedetails = true;
		$scope.setfeeconfigurationandview = "View Fee Sturcture";
		angular.element("#view_feestructure").removeClass("hoverview").removeClass("defaultcol");
		angular.element("#fee_config").removeClass("activeview");
		angular.element("#view_feestructure").addClass("activeview");
		angular.element("#fee_config").addClass("hoverview").addClass("defaultcol");
	}
}



$scope.deletefeeconfig = function(configid){	
   $scope.$modalInstance = $modal.open({
		 scope: $scope,
		 templateUrl: 'deletefeeconfig.html',
		 controller: function($scope, $modalInstance, $rootScope, $state) {
			  $scope.deletefeeconfigok = function() {
				  	deletefeeconfigtype(configid);
				
			  };
			  $scope.okcancel = function() {
			
				$modalInstance.close();
			  };
		  }
	  });
	}
	
	function deletefeeconfigtype(delfeeconfigId){
		
	$http({
		url:appCon.globalCon.serviceBaseURL+"feeConfiguration/deleteFeeConfiguration?feeConfigurationId="+delfeeconfigId,
		method:'DELETE'
	}).success(function(result){
		$state.go($state.current, {}, {reload: true});
		
	});
}

}]);


angular.module(appCon.appName).directive('numericdecimal', function(){
    return {
        require: 'ngModel',
        link: function(scope, element, attrs, modelCtrl) {

            modelCtrl.$parsers.push(function (inputValue) {
                var transformedInput = inputValue ? inputValue.replace( /^\d+\.\d{0,2}$/,'') : null;

                if (transformedInput!=inputValue) {
                    modelCtrl.$setViewValue(transformedInput);
                    modelCtrl.$render();
                }

                return transformedInput;
            });
        }
    };
});



angular.module(appCon.appName).directive('myDirective', function ($http) {
    return {
        restrict: 'A',
        scope: true,
        link: function (scope, element, attr) {

            element.bind('change', function () {
                var formData = new FormData();
                formData.append('file', element[0].files[0]);
				console.log("fileupload");
				console.log(formData);
				
            });

        }
    };
});




