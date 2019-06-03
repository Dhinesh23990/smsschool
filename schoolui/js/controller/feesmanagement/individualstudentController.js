'use strict';

angular.module(appCon.appName).controller('individualstudentController',
					['$injector',  '$scope', 'DTOptionsBuilder', 'MethodService',
        function($injector, $scope, DTOptionsBuilder, MethodService) {
var $http,$state,$modal,$cookieStore,$filter;
$http = $injector.get('$http');
$state = $injector.get('$state');
$modal = $injector.get('$modal');
$cookieStore = $injector.get('$cookieStore');
$filter = $injector.get('$filter');
$scope.isOpen = false;
$scope.paymentdetails = true;
$scope.hidesearchdet = false;
$scope.feecollected = true;
$scope.paymenttable=true;
$scope.hidesearchdetgrid = false;
$scope.feesplitdetails = false;
$scope.ovhidesearchdet = false;
$scope.overalldetails = false;
$scope.payshowline=true;
$scope.fsshowline=false;
$scope.ovshowline=false;
$scope.setpaymentandfeesplit = 'Payment';
$scope.paymentandfeesplit ="Payment";
$scope.dtOptions = DTOptionsBuilder.newOptions()
						.withOption('lengthChange',false)
						.withOption('searching',false);

console.log("wekcine");

//class configuration
	$http({
		url:appCon.globalCon.serviceBaseURL+"class/getAllClassBySchoolId?shoolId="+$cookieStore.get('schoolId')+"&pageIndex=0&pageSize="+appCon.globalCon.paginationSize,
		method:'GET'
	}).success(function(result){
		
		var classdata = result.responseVO.classVOs;		
		$scope.classes = [];
		angular.forEach(classdata,function(value, key){
			$scope.classes.push({value:value.className,key:value.className});
		});
	});
						
//list of student details

MethodService.get("mock/fees/payment.json").then(function(data){
	$scope.items = data.StaffVO;
});


/*$http.get("mock/fees/payment.json").success(function(data){
	if(data.Status == "Ok")
		$scope.items = data.AdminVO;
});*/

$scope.backtosearch = function(){
	$scope.paymentdetails = true;
	$scope.payshowline=true;
	$scope.feecollected = true;
	$scope.hidesearchdet = false;
		
}
$scope.returnsearch = function(){
	$scope.paymentdetails = false;
		$scope.feecollected = false;
	$scope.hidesearchdet = true;

}

$scope.backtosearchgrid = function(){

	
	$scope.fshidesearchdet = false;
	$scope.fsshowline=true;
		$scope.feesplitdetails = true;
}
$scope.fsreturnsearch = function(){
	
	$scope.feesplitdetails = false;

	$scope.fshidesearchdet = true;
	
}
$scope.ovbacktosearchgrid = function(){

	
	$scope.ovhidesearchdet = false;
	$scope.ovshowline=true;
		$scope.overalldetails = true;
}
$scope.ovreturnsearch = function(){
	
	$scope.overalldetails = false;

	$scope.ovhidesearchdet = true;
	
}
	


    
            

//add student details

$scope.genders = [{value:"Male",key:"Male"},{value:"Female",key:"Female"},{value:"Transgender",key:"Transgender"}];
$scope.religions = [{value:"Hindu",key:"Hindu"},{value:"Christian",key:"Christian"},{value:"Muslim",key:"Muslim"}];
$scope.bloodgroups = [{value:"A1+ve",key:"A1+ve"},{value:"A1-ve",key:"A1-ve"},{value:"AB+ve",key:"AB+ve"}
,{value:"AB-ve",key:"AB-ve"},{value:"O+ve",key:"O+ve"},{value:"O-ve",key:"O-ve"}];
$scope.castes = [{value:"BC",key:"BC"},{value:"MBC",key:"MBC"},{value:"OBC",key:"OBC"},{value:"OC",key:"OC"},{value:"SC",key:"SC"}];
$scope.types = [{value:"Class wise",key:"Class wise"},{value:"Section wise",key:"Section wise"}];
$scope.batches = [{value:"2015-2016",key:"2015-2016"},{value:"2016-2017",key:"2016-2017"},{value:"2017-2018",key:"2017-2018"}];
$scope.mediums = [{value:"Tamil Medium",key:"Tamil Medium"},{value:"English Medium",key:"English Medium"}];
$scope.classes = [{value:"LKG",key:"LKG"},{value:"PREKG",key:"PREKG"},{value:"UKG",key:"UKG"},{value:"I",key:"I"},{value:"II",key:"II"},{value:"III",key:"III"},{value:"IV",key:"IV"}];
$scope.sections = [{value:"A",key:"A"},{value:"B",key:"B"}];
$scope.courses = [{value:"General",key:"General"}];
$scope.languages = [{value:"Tamil",key:"Tamil"},{value:"English",key:"English"},{value:"Hindi",key:"Hindi"}];
$scope.feetypes = [{value:"Annual Fees",key:"Annual Fees"},{value:"Monthly Fees",key:"Monthly Fees"},{value:"Miscellaneous Fees",key:"Miscellaneous Fees"},{value:"Term Fees",key:"Term Fees"}];
$scope.feecomponents = [{value:"Tutition Fees",key:"Tutition Fees"},{value:"Transport Fees",key:"Transport Fees"},{value:"Hostel Fees",key:"Hostel Fees"},{value:"Food and Snacks Fees",key:"Food and Snacks Fees"},{value:"Penalty and Fine ",key:"Penalty and Fine "},{value:"Book Fees",key:"Book Fees"},{value:"Uniform Fees",key:"Uniform Fees"},{value:"Library Fees",key:"Library Fees"},{value:"Co-Curricular Fees",key:"Co-Curricular Fees"},{value:"Extra-Curricular Fees",key:"Extra-Curricular Fees"},{value:"Other Fees",key:"Other Fees"}];
$scope.feecategories = [{value:"Government Fee",key:"Government Fee"},{value:"Management Fee",key:"Management Fee"}];
$scope.cities= [{value:"Madurai",key:"Madurai"},{value:"Chennai",key:"Chennai"},{value:"Trichy",key:"Trichy"},{value:"Coimbatore",key:"Coimbatore"}];
$scope.states= [{value:"Tamilnadu",key:"Tamilnadu"},{value:"Andhrapradesh",key:"Andhrapradesh"},{value:"karnataka",key:"karnataka"},{value:"kerala",key:"kerala"}];
$scope.pincodes= [{value:"625001",key:"625001"},{value:"625002",key:"625002"},{value:"625003",key:"625003"},{value:"625004",key:"625004"}];


$scope.paymentdet = function(){

	//if($scope.paymentandfeesplit != "Payment"){
		$scope.paymentdetails = true;
		$scope.feesplitdetails = false;
		$scope.feesplit = false;
		$scope.overalldetails = false;
		$scope.setpaymentandfeesplit = "Payment";
		$scope.paymentandfeesplit ="Payment";
		$scope.feecollected = true;		
		$scope.hidesearchdet = false;
		$scope.feesplittable=false;
		$scope.paymenttable=true;
		$scope.ovhidesearchdet = false;
		$scope.overalltable=false;	
		$scope.payshowline=true;
		$scope.fsshowline=false;	
		$scope.ovshowline=false;
		angular.element("#feesplit").removeClass("activeview");
		angular.element("#payment").removeClass("hoverview").removeClass("defaultcol");
		angular.element("#feesplit").addClass("hoverview").addClass("defaultcol");
		angular.element("#payment").addClass("activeview");	
		angular.element("#overall").addClass("hoverview").addClass("defaultcol");
		angular.element("#overall").removeClass("activeview");		
	//}
}

$scope.feesplitdet = function(){
	
	//if($scope.paymentandfeesplit == "Payment"){
		$scope.paymentandfeesplit="Fee Split";
		$scope.paymentdetails = false;
		$scope.overalldetails = false;
		$scope.feesplitdetails = true;
		$scope.feecollected = false;
		$scope.hidesearchdet = false;
		$scope.feesplittable=true;
		$scope.paymenttable=false;
		$scope.fshidesearchdet = false;
		$scope.ovhidesearchdet = false;
		$scope.overalltable=false;
		$scope.payshowline=false;
		$scope.fsshowline=true;	
		$scope.ovshowline=false;			
		$scope.setpaymentandfeesplit = "Fee Split";
		angular.element("#feesplit").removeClass("hoverview").removeClass("defaultcol");
		angular.element("#payment").removeClass("activeview");
		angular.element("#overall").removeClass("hoverview").removeClass("defaultcol");
		angular.element("#overall").removeClass("activeview");
		angular.element("#feesplit").addClass("activeview");
		angular.element("#payment").addClass("hoverview").addClass("defaultcol");
		angular.element("#overall").addClass("hoverview").addClass("defaultcol");
	//}
}
$scope.overalldet = function(){
	
	//if($scope.paymentandfeesplit == "Payment"){
		$scope.paymentandfeesplit="Overall";
		$scope.paymentdetails = false;
		$scope.feesplitdetails = false;
		$scope.overalldetails = true;
		$scope.feecollected = false;
		$scope.hidesearchdet = false;
		$scope.feesplittable=false;
		$scope.paymenttable=false;
		$scope.payshowline=false;
		$scope.fsshowline=false;
		$scope.ovshowline=true;
		$scope.fshidesearchdet = false;
		$scope.ovhidesearchdet = false;
		$scope.overalltable=true;	
		$scope.setpaymentandfeesplit = "overall";
		angular.element("#overall").removeClass("hoverview").removeClass("defaultcol");
		angular.element("#feesplit").removeClass("activeview");
		angular.element("#payment").removeClass("activeview");
		angular.element("#overall").addClass("activeview");
		angular.element("#feesplit").addClass("hoverview").addClass("defaultcol");
		angular.element("#payment").addClass("hoverview").addClass("defaultcol");
	//}
}

$scope.clearallsearchvalue = function(){
	$scope.searchgrid = "";
}

$scope.deletestudentdata = function(){	
   $scope.$modalInstance = $modal.open({
		 scope: $scope,
		 templateUrl: 'deletestudent.html',
		 controller: function($scope, $modalInstance, $rootScope, $state) {
			  $scope.deletestaffok = function() {
				  $modalInstance.close();
			  };
			  $scope.okcancel = function() {
				  $modalInstance.close();
			  };
		  }
	  });
	}

}]);

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


  
