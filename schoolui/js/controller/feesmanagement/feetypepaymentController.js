	'use strict';

angular.module(appCon.appName).controller('feetypepaymentController',
					['$injector',  '$scope','$rootScope', 'DTOptionsBuilder', 'MethodService','$stateParams',
        function($injector, $scope, $rootScope, DTOptionsBuilder, MethodService,$stateParams) {
var $http,$state,$modal,$filter,$cookieStore;
$http = $injector.get('$http');
$state = $injector.get('$state');
$modal = $injector.get('$modal');
$cookieStore = $injector.get('$cookieStore');
$filter = $injector.get('$filter');
$scope.isOpen = false;
$scope.showstudent_det = false;
$scope.hidesearchdet = true;
$scope.hidetable = true;
$scope.showupdatefrm = false;
$scope.createviewdetails = true;
$scope.listviewdetails = false;
$scope.errorstudentdet = false;
$scope.searchpayment_det = true;
$scope.items='';
$scope.bookFees = 0;
$scope.tutionFees = 0;
$scope.sportFees = 0;
$scope.otherFees =0
$scope.totalAmount = 0;
var savePaymentFeeType=null;

/*$scope.studentid=$stateParams.id;
$scope.term=$stateParams.term;
console.log(studentid);
console.log(term);
var s=appCon.globalCon.serviceBaseURL+"feeConfiguration/getStudentDetailsById?schoolId="+$cookieStore.get('schoolId')+"&studentid="+studentid+"&term="+term;
console.log(s);
$http({
	url:appCon.globalCon.serviceBaseURL+"feeConfiguration/getStudentDetailsById?schoolId="+$cookieStore.get('schoolId')+"&studentid="+studentid+"&term="+term,
	method:'GET'
}).success(function(result){
	if(result.statusFlag == "Ok"){
		$scope.showstudent_det = true;
		$scope.errorstudentdet = false;
		var studentfeeconfiguration=result.responseVO;
		console.log(studentfeeconfiguration);
		$scope.items=studentfeeconfiguration.feeConfigurationVO;
		$scope.students=studentfeeconfiguration.StudentVO;
		console.log($scope.students);
		console.log($scope.items);
	}
	else{
		$scope.showstudent_det = false;
		$scope.errorstudentdet = true;
		if(result.errorMsg == "Student Details are Empty"){
			$scope.errormsgfeespay = result.errorMsg;
		}
		if(result.errorMsg == "FEE CONFIGURATION EMPTY"){
			$scope.errormsgfeespay = "Fees Configuration Empty";
		}
		if(result.errorMsg == "PAYMENT ALREADY PAID STUDENT"){
			$scope.errormsgfeespay = "Student already fees paid for this term on batch";
		}
	}
	
})*/


$scope.getFeesPaymentDetails = function(){
		$scope.getFeeConfigTerm($stateParams.batchId,$stateParams.studentId,$stateParams.term,$stateParams.studentname);			

}
$scope.currentPage = 1
  ,$scope.numPerPage = 10
  ,$scope.maxSize = 5;
$scope.terms = [{value:"1",key:"1"},{value:"2",key:"2"},{value:"3",key:"3"}];
$scope.dtOptions = DTOptionsBuilder.newOptions()
						.withOption('lengthChange',false)
						.withOption('info',false)
						.withOption('searching',false)
						.withOption('sort',false)
						.withOption('filter',false)
						.withOption("paging",false);
					
$http ({
		url:appCon.globalCon.serviceBaseURL+"dashboard/getAllConfigurationBySchoolId?schoolId="+$cookieStore.get('schoolId'),
		method:'GET'
		}).success(function(result){
			var batchdata = result.responseVO.BatchVOs;	
			$scope.batches = [];
			angular.forEach(batchdata,function(value, key){
				$scope.batches.push({value:value.batchName,key:value.id});
			});
		});
/*
 * classes
 */
$http({
	url:appCon.globalCon.serviceBaseURL+"class/getAllClassBySchoolId?shoolId="+$cookieStore.get('schoolId')+"&pageIndex=0&pageSize=100",
	method:'GET'
}).success(function(result){
	var classdata = result.responseVO.classVOs;		
	$scope.classes = [];
	angular.forEach(classdata,function(value, key){
		$scope.classes.push({value:value.className,key:value.id});
	});
});
/*
 * sections
 */
$http({
	url:appCon.globalCon.serviceBaseURL+"section/getAllSectionBySchoolId?shoolId="+$cookieStore.get('schoolId')+"&pageIndex=0&pageSize=100",
	method:'GET'
}).success(function(result){
	var sectiondata = result.responseVO.sectionVOs;		
	$scope.sections = [];
	angular.forEach(sectiondata,function(value, key){
		$scope.sections.push({value:value.sectionName,key:value.id});
	});
});


/*
 * this function take from student attendance 
 */
$scope.showbysearch = function(){
	$scope.attendancetable = true;
	$scope.date = $filter('date')($scope.attenddancedate, "yyyy/MM/dd");
	$http({
		url:appCon.globalCon.serviceBaseURL+"attendance/getAllStudentsByClassAndSectionAndDate?schoolId="+$cookieStore.get('schoolId')+"&date="+$scope.date+"&standard="+$scope.className+"&section="+$scope.sectionName,
		method:'GET'
	}).success(function(result){					
		var allstudentdata = result.responseVO.attendanceList;
		$scope.fulldays = false;
		$scope.mornings = false;
		$scope.afternoons = false;
		angular.forEach(allstudentdata,function(value,key){
			if(value.fullDay == true)
				$scope.fulldays = true;
			if(value.morning == true || value.afternoon == true){
				$scope.mornings = true;
				$scope.afternoons = true;
			}
			
		});
		$scope.day_view = true;
		$scope.consolidated_view = false;
		$scope.student_view = false;
		$scope.items = allstudentdata;
	});
}
/*
 * for batch filter(year)
 */

$scope.getallclassesbyyear = function(year){
	$http({
		url:appCon.globalCon.serviceBaseURL+"feeConfiguration/getAllclassesByYear?year="+year,
		method:'GET'
	}).success(function(result){
		console.log(result,"result")
		var classdata = result.responseVO.batchclass;
		console.log($scope.StudentVO)
//		clearclassdata = $scope.StudentVO;
//		clearclassdata.classId= "";
//		clearclassdata.section="";
//		$scope.classes = [];
//		angular.forEach(classdata,function(value,key){
//			$scope.classes.push({value:value.classses,key:value.id});
//		});
	});
}

/*
 * for class
 */

$scope.getallsectionbyyearclasses = function(year,classes){
	$http({
		url:appCon.globalConserviceBaseURL+"section/getSectionsByYearsAndClasses?year="+year+"&classes="+classes,
		method:'GET'
	}).success(function(result){
		var sectiondata = result.responseVO.sectionVOs,clearsectiondata = $scope.StudentVO;
		clearsectiondata.section="";
		$scope.sections = [];
		angular.forEach(sectiondata,function(value,key){
			$scope.sections.push({value:value.classes,key:value.id});
		});
	});
}


$scope.listviewdet = function(){
	$scope.listviewdetails = false;
	$scope.createviewdetails = true;
	$scope.searchpayment_det = true;
	$scope.cashpaymentsucces = false;
	$scope.errorpaymentsuccess = false;
	angular.element("#grid_view").removeClass("activeview");
	angular.element("#list_view").removeClass("hoverview").removeClass("defaultcol");
	angular.element("#grid_view").addClass("hoverview").addClass("defaultcol");
	angular.element("#list_view").addClass("activeview");
}

$scope.gridviewdet = function(){
	$scope.createviewdetails = false;
	$scope.listviewdetails = true;
	$scope.showstudent_det = false;
	$scope.payment_det = false;
	$scope.errorstudentdet = false;
	$scope.searchpayment_det = true;
	$scope.cashpaymentsucces = false;
	$scope.errorpaymentsuccess = false;
	$scope.searchpayment.$setPristine();
	$scope.searchid = "";
	$scope.term = "";
	$scope.batchId = "";
	angular.element("#grid_view").removeClass("hoverview").removeClass("defaultcol");
	angular.element("#list_view").removeClass("activeview");
	angular.element("#grid_view").addClass("activeview");
	angular.element("#list_view").addClass("hoverview").addClass("defaultcol");
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


/*$scope.searchstudentdet = function(searchvalue,term,batchId){
	$scope.showstudent_det = false;
	$scope.errorstudentdet = false;
	$http ({
		url:appCon.globalCon.serviceBaseURL+"feeConfiguration/getFeeConfigurationByStudentId?schoolId="+$cookieStore.get('schoolId')+"&term="+term+"&batchId="+batchId+"&searchString="+searchvalue,
		method:'GET'
		}).success(function(result){
			if(result.statusFlag == "Ok"){
				$scope.showstudent_det = true;
				$scope.errorstudentdet = false;
				var feeconfigurationvo = result.responseVO.FeeConfigurationVO, studentassigndet = result.responseVO.StudentVO;
				$scope.studentIdetails = studentassigndet;
				$scope.PaymentModeVO={studentId:studentassigndet.id,batchId:feeconfigurationvo.batchId};
				$scope.feetypes = feeconfigurationvo.feeTypes;
				$scope.totalAmount = feeconfigurationvo.amount;
			}
			else{
				$scope.showstudent_det = false;
				$scope.errorstudentdet = true;
				if(result.errorMsg == "Student Details are Empty"){
					$scope.errormsgfeespay = result.errorMsg;
				}
				if(result.errorMsg == "FEE CONFIGURATION EMPTY"){
					$scope.errormsgfeespay = "Fees Configuration Empty";
				}
				if(result.errorMsg == "PAYMENT ALREADY PAID STUDENT"){
					$scope.errormsgfeespay = "Student already fees paid for this term on batch";
				}
			}
		});
}*/

/*
 * datchId,className,sectionName,name
 */
/*$scope.searchstudentdet = function(batchId,className,sectionName,studentname){
	$scope.showstudent_det = false;
	$scope.errorstudentdet = false;
	$http ({
		url:appCon.globalCon.serviceBaseURL+"feeConfiguration/getAllFeeconfigurationByStudentId?schoolId="+$cookieStore.get('schoolId')+"&className="+className+"&batchId="+batchId+"&searchString="+searchvalue+"&sectionName="+sectionName,
		method:'GET'
		}).success(function(result){
			if(result.statusFlag == "Ok"){
				$scope.showstudent_det = true;
				$scope.errorstudentdet = false;
				var feeconfigurationvo = result.responseVO.FeeConfigurationVO, studentassigndet = result.responseVO.StudentVO;
				$scope.studentIdetails = studentassigndet;
				$scope.PaymentModeVO={studentId:studentassigndet.id,batchId:feeconfigurationvo.batchId};
				$scope.feetypes = feeconfigurationvo.feeTypes;
				$scope.itmes=feeconfigurationvo;
				$scope.totalAmount = feeconfigurationvo.amount;
			}
			else{
				$scope.showstudent_det = false;
				$scope.errorstudentdet = true;
				if(result.errorMsg == "Student Details are Empty"){
					$scope.errormsgfeespay = result.errorMsg;
				}
				if(result.errorMsg == "FEE CONFIGURATION EMPTY"){
					$scope.errormsgfeespay = "Fees Configuration Empty";
				}
				if(result.errorMsg == "PAYMENT ALREADY PAID STUDENT"){
					$scope.errormsgfeespay = "Student already fees paid for this term on batch";
				}
			}
		});
}*/

$scope.getFeeConfigTerm = function(batchId,studentId,term,studentName){
	
	
	$http ({
		url:appCon.globalCon.serviceBaseURL+"feeConfiguration/getFeeConfigurationByStudentIdAndTerm?schoolId="+$cookieStore.get('schoolId')+"&batchId="+batchId+"&studentId="+studentId+"&term="+term+"&studentName="+studentName,
		method:'GET'
		}).success(function(result){
			$scope.feesConfigDetails = result.responseVO.feeConfigurationVO;
			$scope.students=result.responseVO.StudentVO;
			$scope.payFeesTypes = result.responseVO.payFeesTypes;
			$scope.paymentModeVO = result.responseVO.PaymentModeVO;
			if($scope.paymentModeVO){
			$scope.totalAmount = result.responseVO.PaymentModeVO.pending;
			}else{
				$scope.totalAmount = result.responseVO.feeConfigurationVO.amount;
			}
			
		});
		
}



$scope.searchstudentdet = function(batchId,className,sectionName,studentname){
	
	$scope.feeTypeVO = [];

	batchId = batchId == undefined ? null:	batchId;
	className = className == undefined ? null:className;
	sectionName = sectionName == undefined ? null:	sectionName; 
	studentname = studentname == undefined ? null:	studentname; 
	
	$scope.showstudent_det = false;
	$scope.errorstudentdet = false;
	$http ({
		url:appCon.globalCon.serviceBaseURL+"feeConfiguration/getAllFeeconfigurationDetailsByStudentId?schoolId="+$cookieStore.get('schoolId')+"&batchId="+batchId + "&classId="+className+"&sectionId="+sectionName+"&searchString="+studentname,
		method:'GET'
		}).success(function(result){
			if(result.statusFlag == "Ok"){
				$scope.showstudent_det = true;
				$scope.errorstudentdet = false;
				console.log(result,"result")
				/*var feeconfigurationvo = result.responseVO.FeeConfigurationVO, studentassigndet = result.responseVO.StudentVO;
				$scope.studentIdetails = studentassigndet;
				$scope.PaymentModeVO={studentId:studentassigndet.id,batchId:feeconfigurationvo.batchId};
				$scope.feetypes = feeconfigurationvo.feeTypes;
				$scope.itmes=feeconfigurationvo;
				$scope.totalAmount = feeconfigurationvo.amount;*/
				$scope.studentfeeconfiguration =result.responseVO;											
				$scope.items=result.responseVO.FeeConfigurationVO;			
				for(var key in $scope.items){					
					$scope.feeTypeVO.push($scope.items[key].feeTypes);					
				}										
				$scope.students=$scope.studentfeeconfiguration.StudentVO;							
			}
			else{
				$scope.showstudent_det = false;
				$scope.errorstudentdet = true;
				if(result.errorMsg == "Student Details are Empty"){
					$scope.errormsgfeespay = result.errorMsg;
				}
				if(result.errorMsg == "FEE CONFIGURATION EMPTY"){
					$scope.errormsgfeespay = "Fees Configuration Empty";
				}
				if(result.errorMsg == "PAYMENT ALREADY PAID STUDENT"){
					$scope.errormsgfeespay = "Student already fees paid for this term on batch";
				}
			}
		});
}

$scope.cancelpaymentdet = function(){
	$state.go($state.current, {} ,{reload:true});
}

$scope.backtocreatepayment = function(){
	$scope.showstudent_det = true;
	$scope.payment_det = false;
	$scope.errorstudentdet = false;
	$scope.searchpayment_det = true;
	$scope.cashpaymentsucces = false;
}

$scope.backtopaymentsuccess = function(){
	$scope.payment_det = true;
	$scope.cashpaymentsucces = false;
	$scope.cardpaymentsucces = false;
	$scope.errorpaymentsuccess = false;
}

$scope.paidcashpayment = function(){
	$scope.paymentType = "Cash";
	$scope.payment_det = false;
	$scope.cashpaymentsucces = true;
	$scope.errorpaymentsuccess = false;
}

$scope.paidcardpayment = function(){
	$scope.paymentType = "Card";
	$scope.payment_det = false;
	$scope.cardpaymentsucces = true;
	$scope.errorpaymentsuccess = false;
}

$scope.paymentstudentdet = function(){
	
	$scope.showstudent_det =false;
	$scope.payment_det =true;
	$scope.searchpayment_det = false;
	$scope.errorstudentdet = false;
	$scope.errorpaymentsuccess = false;
}

$scope.paymentsuccess = function(){
	
	$scope.errorstudentdet = false;
	$scope.errorpaymentsuccess = false;
	var data = $scope.PaymentModeVO;
	data.schoolId = $cookieStore.get('schoolId');
	data.paymentType = $scope.paymentType;
	data.feeTypes = $scope.feetypes;
	data.status = "SUCCESS";
	data.amount  = $scope.totalAmount;
	data.term = $scope.term;
	$scope.data = {"SMSRequest":{"PaymentModeVO":$scope.PaymentModeVO}};
	$injector.get('feesmanagementservice')['savePaymentMode']($scope.data).then(
	function(result) {		
		if(result.data.statusFlag == 'Ok'){
			$state.go($state.current, {} ,{reload:true});
		}
		if(result.data.statusFlag == 'Error'){
			$scope.errorpaymentsuccess = true;
			$scope.errormsgfeespayment = "Fees Payment Already Exists";
		}
	},		
	function(error){
		console.log('failure');
	});
}


$scope.$watch('currentPage + numPerPage', function() {
	   
    var begin = (($scope.currentPage - 1) * $scope.numPerPage)
    , end = begin + $scope.numPerPage;
	
     $http({
		url:appCon.globalCon.serviceBaseURL+"paymentMode/getAllPaymentModeBySchoolId?shoolId="+$cookieStore.get('schoolId')+"&pageIndex="+begin+"&pageSize="+appCon.globalCon.paginationSize,
		method:'GET'
	}).success(function(result){
		var itemsdata = result.responseVO.paymentModeVOs;
		var feesTypesConfig = result.responseVO.feesType;
		var index_id = begin+1;
		angular.forEach(itemsdata,function(value,key){
			value.indexId = index_id;
			if(value.status == "SUCCESS")
				value.status = "Success";
			if(value.status == "FAILURE")
				value.status = "Failure";
			if(value.status == "PENDING")
				value.status = "Pending";
			value.paymentdate = $filter('date')(value.createdDate, "dd-MM-yyyy");
			index_id++;
		})
	
		$scope.paymentModeType = angular.copy(itemsdata);
		
		for(var key in feesTypesConfig){
			

			for(var key1 in itemsdata){
				
				
				
				if(feesTypesConfig[key].feeType === itemsdata[key1].feeTypes.feeType){

					
				}

			}
		}

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
  
$scope.create = function(){ 

	$scope.errormsg = false;
	var data = $scope.feeTypeVOs;
	data.schoolId = $cookieStore.get('schoolId');
	$scope.data = {"SMSRequest":{"feeTypeVOs":$scope.feeTypeVOs}};
		
	$injector.get('feesmanagementservice')['saveFeeType']($scope.data).then(        		
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
$scope.edit = function(id){
	
    $scope.hidetable= false;
	$scope.showupdatefrm = true;
	$scope.hidesearchdet = false; 
	$http({
		url:appCon.globalCon.serviceBaseURL+"feeType/getFeeTypeById?feeTypeId="+id,
		method:'GET'
	}).success(function(result){
		var getupdata = result.responseVO.FeeTypeVO;
		delete getupdata.createdDate;
		delete getupdata.updatedDate;
		$scope.UpdateVO = getupdata;
	});
	
}
$scope.update = function(){
	$scope.errormsg = false;
	var updata = $scope.UpdateVO;
	$scope.updatedata = {"SMSRequest":{"FeeTypeVO":updata}};
	console.log("updated");
	console.log($scope.updatedata);	
	$injector.get('feesmanagementservice')['updateFeeType']($scope.updatedata).then(        		
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


$scope.findtotalamount = function(feetypes){
	var amt = 0;

   savePaymentFeeType = feetypes;
   
	
	angular.forEach(feetypes,function(value,key){
		if(value.balanceAmount && value.paymentModeId){
			amt = parseInt(amt) + parseInt(value.balanceAmount);
		}else if(value.amount && !value.paymentModeId){
			
			amt = parseInt(amt) + parseInt(value.amount);
			console.log(amt)
		}
	});	
	
	$scope.totalAmount = angular.copy(amt);	
	
}




$scope.feesPay =  function(students,feesConfig,feePay) {
	
		
	var savePayment = {};
	var paymentModeTypeData = {};
	var feesTypes= [];
	
	$scope.errorstudentdet = false;
	$scope.errorpaymentsuccess = false;

	savePayment.feeConfigurationId = feesConfig.id;
	savePayment.schoolId = $cookieStore.get('schoolId');
	savePayment.studentId=students.id;
	savePayment.paymentType = feePay.paymentType;
	savePayment.amount = $scope.totalAmount;
	savePayment.pending = feesConfig.amount -  $scope.totalAmount;  
	savePayment.term=feesConfig.term;
	savePayment.classId =feesConfig.classId;
	savePayment.batchId = feesConfig.batchId;
	savePayment.bankName = feePay.cbank;
	savePayment.chequeNo = feePay.chequeNO;
	savePayment.ddNo = feePay.ddNO;
	savePayment.status =  $scope.totalAmount != feesConfig.amount ? 'pending':'paid'
	
	savePaymentFeeType = savePaymentFeeType == null ? feesConfig.feeTypes : savePaymentFeeType;
	
			
		
		angular.forEach(savePaymentFeeType,function(value, key){
		
		
		if(value.feeType === "Book Fee"){			
			feesTypes.push({
				feeTypeId : value.recId,
				feeType : value.feeType,
				amount :value.amount,
			});						
		} 
		
		if(value.feeType === "Tution Fee"){
			feesTypes.push({
				feeTypeId :value.recId,
				feeType : value.feeType,
				amount :value.amount,				
			});						
		} 
		
		if(value.feeType === "Sports Fee"){
			
			feesTypes.push({
				feeTypeId :value.recId,
				feeType : value.feeType,
				amount : value.amount,				
			})	
		} 
		
		if(value.feeType === "Other Fees"){
			
			feesTypes.push({
				feeTypeId :value.recId,
				feeType : value.feeType,
				amount :value.amount				
		});
		}
		
	});
	savePayment.feeTypes = feesTypes;
	
	$injector.get('feesmanagementservice')['savePaymentMode']({'SMSRequest':{'paymentModeVO':savePayment}}).then(        		
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
	
};



$scope.feesPayUpdate = function() {
	
	delete $scope.paymentModeVO.studentVO;
	var updatePayment = angular.copy($scope.paymentModeVO);
	var paymentModeTypeData = {};
	var feesTypes= [];
	
	
	$scope.errorstudentdet = false;
	$scope.errorpaymentsuccess = false;
	updatePayment.paymentType = $scope.feePay.paymentType;
	updatePayment.amount = $scope.totalAmount;
	updatePayment.pending = updatePayment.pending -  $scope.totalAmount;  
	updatePayment.bankName = $scope.feePay.cbank;
	updatePayment.chequeNo = $scope.feePay.chequeNO;
	updatePayment.ddNo = $scope.feePay.ddNO;
	updatePayment.status =  $scope.totalAmount != $scope.paymentModeVO.pending ? 'pending':'paid'
		
	
		console.log(updatePayment,"updatePayment");
	console.log(savePaymentFeeType,"savePaymentFeeType")
		
	savePaymentFeeType = savePaymentFeeType == null ? $scope.feePay.feeTypes : savePaymentFeeType;
	
			
		
		angular.forEach(savePaymentFeeType,function(value, key){
		
		
		if(value.feeType === "Book Fee"){			
			feesTypes.push({
				feeTypeId : value.recId,
				feeType : value.feeType,
				amount :value.balanceAmount,
			});						
		} 
		
		if(value.feeType === "Tution Fee"){
			feesTypes.push({
				feeTypeId :value.recId,
				feeType : value.feeType,
				amount :value.balanceAmount,				
			});						
		} 
		
		if(value.feeType === "Sports Fee"){
			
			feesTypes.push({
				feeTypeId :value.recId,
				feeType : value.feeType,
				amount : value.balanceAmount,				
			})	
		} 
		
		if(value.feeType === "Other Fees"){
			
			feesTypes.push({
				feeTypeId :value.recId,
				feeType : value.feeType,
				amount :value.balanceAmount				
		});
		}
		
	});
		updatePayment.feeTypes = feesTypes;

	$injector.get('feesmanagementservice')['updatePaymentMode']({'SMSRequest':{'paymentModeVO':updatePayment}}).then(        		
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


/*$http.get("mock/fees/feetype.json").success(function(data){
	if(data.Status == "Ok")
		$scope.items = data.feestypeVO;
});*/

$scope.backtosearch = function(){
	$scope.feeTypeVOs="";
	$scope.search="";
	$scope.hidesearchdet = false;
	$scope.hidetable = false;
	 $scope.errormsg = false;
}
$scope.returnsearch = function(){
	$scope.createForm.$setPristine();
		  var defaultForm = {
              feeType : "",
			  recurringPerYear:"",
			  remDuration:""
          };
		$scope.feeTypeVOs = defaultForm;
	$scope.hidesearchdet = true;
	$scope.hidetable = true;
	 $scope.errormsg = false;
}

$scope.updatesearch = function(){
	$scope.hidesearchdet = true;
	$scope.hidetable = true;
	$scope.showupdatefrm = false;
	
}

$scope.goclear = function(){
	
	$scope.createForm.$setPristine();
		  var defaultForm = {
              feeType : "",
			  recurringPerYear:"",
			  remDuration:""
          };
		$scope.feeTypeVOs = defaultForm;
	  $scope.errormsg = false;
	  $scope.createForm.$setPristine();
	  $scope.feeTypeVOs="";
		
	}
	
	$scope.go = function(){
	   $scope.errormsg = false;
		$scope.showupdatefrm = false;	
		$scope.hidesearchdet = true;
		$scope.hidetable = true;
		
		
	}


$scope.deleteFeeType = function(deletefeeTypeId){

	$scope.$modalInstance = $modal.open({
		 scope: $scope,
		 templateUrl: 'deleteFeeType.html',
		 controller: function($scope, $modalInstance, $rootScope, $state) {
			  $scope.deleteFeeTypeOk = function() {
				  deleteFeeType(deletefeeTypeId);
			  };
			  $scope.okcancel = function() {
				  $modalInstance.close();
			  };
		  }
	  });
}
function deleteFeeType(delfeeTypeId){
	console.log(delfeeTypeId);
	$http({
		url:appCon.globalCon.serviceBaseURL+"feeType/deleteFeeType?feeTypeId="+delfeeTypeId,
		method:'DELETE'
	}).success(function(result){
		$state.go($state.current, {}, {reload: true});
		
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

angular.module(appCon.appName).directive('numericOnlyClass', function(){
    return {
        require: 'ngModel',
        link: function(scope, element, attrs, modelCtrl) {

            modelCtrl.$parsers.push(function (inputValue) {
                var transformedInput = inputValue ? inputValue.replace(/[^a-zA-Z]/g,'') : null;

                if (transformedInput!=inputValue) {
                    modelCtrl.$setViewValue(transformedInput);
                    modelCtrl.$render();
                }

                return transformedInput;
            });
        }
    };
});


