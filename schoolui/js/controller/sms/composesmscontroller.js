'use strict';

angular.module(appCon.appName).controller('composesmscontroller',
					['$injector',  '$scope', 'DTOptionsBuilder',
        function($injector, $scope, DTOptionsBuilder) {
var $http,$state,$modal,$cookieStore;
$http = $injector.get('$http');
$state = $injector.get('$state');
$modal = $injector.get('$modal');
$cookieStore = $injector.get('$cookieStore');
$scope.show = false;
$scope.loading =false;

$scope.ComposeSMSVO = {distributionType:"checkdata"};
	
	$('select').multi({
		search_placeholder: 'Search ...',
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
	
	
$scope.Sms = [{value:"All Student",key:"All Student"},{value:"Class Wise",key:"Class Wise"},{value:"Section Wise",key:"Section Wise"},{value:"Individual Student",key:"Individual Student"}
               ,{value:"All Teacher",key:"All Teacher"},{value:"Individual Teacher",key:"Individual Teacher"},{value:"Admin/Management",key:"Admin/Management"}];
			  
$scope.furtherdata = [{value:"Template",key:"Template"},{value:"User Defined",key:"User Defined"}];


$scope.selectdistribution = function(distribution){
 
     var singledrop = $scope.SmsLogVO;
  
	if(distribution == null){
		$scope.SmsLogVO = "";
	}
	if(distribution == "Class Wise" || "All Student" || "Individual Teacher" || "Admin/Management" || "All Teacher"){
		singledrop.furtherdata = "";
		singledrop.gender = "";
	}
	
	if(distribution == "Section Wise"){
		singledrop.classdata = "";
		singledrop.furtherdata = "";
	}
/*	if(distribution == "Class Wise" && classdata == null){		
		singledrop.furtherdata = "";
		SmsLogVO.classdata
	}  */
	
}
   $scope.composeclear = function(){
	   
	   $scope.messagearea = "";
   }



  $scope.selectclass = function(classwise){
	  
	  var singledrop = $scope.SmsLogVO;
	  
	  if(classwise == null){
		$scope.SmsLogVO = "";
	    }
		
		 if(singledrop.furtherdata != null && singledrop.distribution == 'Individual Student'){
		$http({
				url:appCon.globalCon.serviceBaseURL+"student/getAllStudentsByClassAndSection?schoolId="+$cookieStore.get('schoolId')+"&standard="+classwise+"&section="+singledrop.sectiondata,
				method:'GET'
			}).success(function(result){				
				var shorts = result.responseVO.studentList;	
				$scope.studentlistdata = [];
				
				angular.forEach(shorts,function(value, key){
					$scope.studentlistdata.push({id:value.id,name:value.studentName});
				});
				$scope.demoOptions = {
					filterPlaceHolder: 'Start typing to filter the lists below.',
					helpMessage: ' To List (Click on Left Side Panel to Select Options and Right Side Panel to Deselect the Options)',
					/* angular will use this to filter your lists */
					orderProperty: 'name',
					/* this contains the initial list of all items (i.e. the left side) */
					items: $scope.studentlistdata,
					/* this list should be initialized as empty or with any pre-selected items */
					selectedItems: [] 
				}; 	                 
				
			console.log($scope.studentlistdata);
		});
		}
	  
  }

  
   
  $scope.selectsection = function(section){
	  
	  var singledrop = $scope.SmsLogVO;
	  
	  if(section == null){
		singledrop.furtherdata = "";
	 }
	 if(singledrop.furtherdata != null && singledrop.distribution == 'Individual Student'){
		$http({
				url:appCon.globalCon.serviceBaseURL+"student/getAllStudentsByClassAndSection?schoolId="+$cookieStore.get('schoolId')+"&standard="+singledrop.classdata+"&section="+section,
				method:'GET'
			}).success(function(result){				
				var shorts = result.responseVO.studentList;	
				$scope.studentlistdata = [];
				
				angular.forEach(shorts,function(value, key){
					$scope.studentlistdata.push({id:value.id,name:value.studentName});
				});
				$scope.demoOptions = {
					filterPlaceHolder: 'Start typing to filter the lists below.',
					helpMessage: ' To List (Click on Left Side Panel to Select Options and Right Side Panel to Deselect the Options)',
					/* angular will use this to filter your lists */
					orderProperty: 'name',
					/* this contains the initial list of all items (i.e. the left side) */
					items: $scope.studentlistdata,
					/* this list should be initialized as empty or with any pre-selected items */
					selectedItems: [] 
				}; 	                 
				
			console.log($scope.studentlistdata);
		});
		}
	  
  }
		
$scope.selectuserdefined = function(furtherdata,distribution,classs,section){
	
	 var clearsmsalltemplate = $scope.SmsLogVO;
	 clearsmsalltemplate.shortDescrip = "";
	
	if(furtherdata == 'User Defined' && (distribution == 'All Student' || distribution == 'All Teacher' || distribution == 'Admin/Management')){
		console.log(furtherdata);
		
		if(distribution != "All Teacher" && distribution != "Individual Teacher" && distribution != "Admin/Management"){
				$scope.tem_parentmobileno1 = true;
				$scope.tem_parentmobileno2 = false;
				$scope.tem_studentmobileno = false;
				$scope.tem_adminmobileno = false;
				$scope.tem_classteachermobileno = false;
		}
		if(distribution == "All Teacher" || distribution == "Individual Teacher" || distribution == "Admin/Management"){
			$scope.tem_parentmobileno1 = false;
			$scope.tem_parentmobileno2 = false;
			$scope.tem_studentmobileno = false;
			$scope.tem_adminmobileno = true;
			$scope.tem_classteachermobileno = false;
		}
		
		$scope.templatedesc = furtherdata;
		$scope.$modalInstance = $modal.open({
			 scope: $scope,
			 templateUrl: 'userdefined.html',
			 controller: function($scope, $modalInstance, $rootScope, $state) {
				  $scope.composeuserdefinedok = function(mge) {
					  console.log(mge);
					   sendsmssave(mge);
					  $modalInstance.close();
				  };
				  $scope.okuserdefinedcancel = function() {
					  $modalInstance.close();
				  };
			  }
		  });
	} 
	
	 if(furtherdata != null && distribution == 'Individual Teacher'){
		$http({
				url:appCon.globalCon.serviceBaseURL+"teacher/getAllTeacher?schoolId="+$cookieStore.get('schoolId')+"&pageIndex=0&pageSize=100",
				method:'GET'
			}).success(function(result){		
            
				var shorts = result.responseVO.teacherList;	
				$scope.teacherlistdata = [];
			angular.forEach(shorts,function(value, key){
				$scope.teacherlistdata.push({id:value.id,name:value.staffName});
			});
			$scope.demoOptions = {
				filterPlaceHolder: 'Start typing to filter the lists below.',
				helpMessage: ' To List (Click on Left Side Panel to Select Options and Right Side Panel to Deselect the Options)',
				/* angular will use this to filter your lists */
				orderProperty: 'name',
				/* this contains the initial list of all items (i.e. the left side) */
				items: $scope.teacherlistdata,
				/* this list should be initialized as empty or with any pre-selected items */
				selectedItems: [] 
				}; 	
		});


	}
	if(furtherdata != null && distribution == 'Individual Student'){
		$http({
				url:appCon.globalCon.serviceBaseURL+"student/getAllStudentsByClassAndSection?schoolId="+$cookieStore.get('schoolId')+"&standard="+classs+"&section="+section,
				method:'GET'
			}).success(function(result){				
				var shorts = result.responseVO.studentList;	
				$scope.studentlistdata = [];
				
				angular.forEach(shorts,function(value, key){
					$scope.studentlistdata.push({id:value.id,name:value.studentName});
				});
				$scope.demoOptions = {
					filterPlaceHolder: 'Start typing to filter the lists below.',
					helpMessage: ' To List (Click on Left Side Panel to Select Options and Right Side Panel to Deselect the Options)',
					/* angular will use this to filter your lists */
					orderProperty: 'name',
					/* this contains the initial list of all items (i.e. the left side) */
					items: $scope.studentlistdata,
					/* this list should be initialized as empty or with any pre-selected items */
					selectedItems: [] 
				}; 	                 
				
			console.log($scope.studentlistdata);
		});
		}
	if(furtherdata != null && distribution == 'Class Wise'){
		$http({
		url:appCon.globalCon.serviceBaseURL+"class/getAllClassBySchoolId?shoolId="+$cookieStore.get('schoolId')+"&pageIndex=0&pageSize=100",
		method:'GET'
		}).success(function(result){		
			var classes = result.responseVO.classVOs;
			 $scope.classdat = [];
			 $scope.listclasses = classes;
			angular.forEach(classes,function(value, key){
				$scope.classdat.push({id:value.id,name:value.className});
			});
			$scope.demoOptions = {
			filterPlaceHolder: 'Start typing to filter the lists below.',
			helpMessage: ' To List (Click on Left Side Panel to Select Options and Right Side Panel to Deselect the Options)',
			/* angular will use this to filter your lists */
			orderProperty: 'name',
			/* this contains the initial list of all items (i.e. the left side) */
			items: $scope.classdat,
			/* this list should be initialized as empty or with any pre-selected items */
			selectedItems: [] 
			};	
			
		});
	
		}
		
		if(furtherdata != null && distribution == 'Section Wise'){
			$http({
			url:appCon.globalCon.serviceBaseURL+"section/getAllSectionBySchoolId?shoolId="+$cookieStore.get('schoolId')+"&pageIndex=0&pageSize=100",
			method:'GET'
			}).success(function(result){
				var sections = result.responseVO.sectionVOs;		     
				$scope.sectiondat = [];
				 $scope.listsections = sections;
				angular.forEach(sections,function(value, key){
					$scope.sectiondat.push({id:value.id,name:value.sectionName});
				});
				$scope.demoOptions = {
					filterPlaceHolder: 'Start typing to filter the lists below.',
					helpMessage: ' To List (Click on Left Side Panel to Select Options and Right Side Panel to Deselect the Options)',
					/* angular will use this to filter your lists */
					orderProperty: 'name',
					/* this contains the initial list of all items (i.e. the left side) */
					items: $scope.sectiondat,
					/* this list should be initialized as empty or with any pre-selected items */
					selectedItems: [] 
				};
			});
		}
		
	}

	
/*$scope.selectgenderdata = function(furtherdata,description,distribution){
	
	if(furtherdata == 'User Defined'){
			if(distribution != "All Teacher" && distribution != "Individual Teacher" && distribution != "Admin/Management"){
				$scope.tem_parentmobileno1 = true;
				$scope.tem_parentmobileno2 = false;
				$scope.tem_studentmobileno = false;
				$scope.tem_adminmobileno = false;
				$scope.tem_classteachermobileno = false;
			}
			if(distribution == "All Teacher" || distribution == "Individual Teacher" || distribution == "Admin/Management"){
				$scope.tem_parentmobileno1 = false;
				$scope.tem_parentmobileno2 = false;
				$scope.tem_studentmobileno = false;
				$scope.tem_adminmobileno = true;
				$scope.tem_classteachermobileno = false;
			}
			
			 $scope.$modalInstance = $modal.open({
			 scope: $scope,
			 templateUrl: 'userdefined.html',
			 controller: function($scope, $modalInstance, $rootScope, $state) {
				  $scope.composeuserdefinedok = function(mge) {
					  console.log(mge);
					   sendsmssave(mge);
					  $modalInstance.close();
				  };
				  $scope.okuserdefinedcancel = function() {
					  $modalInstance.close();
				  };
			  }
		  });
	} 
	if(furtherdata == 'Template' && description && (distribution == 'All Student' || distribution == 'All Teacher' || distribution == 'Admin/Management')){
		$scope.selectfurtherdata(description);
	}
	
	
}*/

$scope.changeparentmobileno1 = function(event){
	if(event.target.checked)
		$scope.tem_parentmobileno1 = event.target.checked;
	else
		$scope.tem_parentmobileno1 = event.target.checked;
}

$scope.changeparentmobileno2 = function(event){
	
	if(event.target.checked)
		$scope.tem_parentmobileno2 = event.target.checked;
	else
		$scope.tem_parentmobileno2 = event.target.checked;
	
}
$scope.changestudentmobileno = function(event){
	if(event.target.checked)
		$scope.tem_studentmobileno = event.target.checked;
	else
		$scope.tem_studentmobileno = event.target.checked;
	
}
$scope.changeadminmobileno = function(event){
	if(event.target.checked)
		$scope.tem_adminmobileno = event.target.checked;
	else
		$scope.tem_adminmobileno = event.target.checked;
	
}
$scope.changeteachermobileno = function(event){
	if(event.target.checked)
		$scope.tem_classteachermobileno = event.target.checked;
	else
		$scope.tem_classteachermobileno = event.target.checked;
	
}

$scope.getinputlength = function(getlen){
	console.log($scope.temcontent);
	console.log(getlen.length);
	$scope.temcontent = $scope.temcontent + getlen.length;
}


$scope.selectfurtherdata = function(furtherdata){
	
	var assignsmslogvos = $scope.SmsLogVO;
	if(assignsmslogvos.distribution != "All Teacher" && assignsmslogvos.distribution != "Individual Teacher" && assignsmslogvos.distribution != "Admin/Management"){
		$scope.tem_parentmobileno1 = true;
		$scope.tem_parentmobileno2 = false;
		$scope.tem_studentmobileno = false;
		$scope.tem_adminmobileno = false;
		$scope.tem_classteachermobileno = false;
	}
	if(assignsmslogvos.distribution == "All Teacher" || assignsmslogvos.distribution == "Individual Teacher" || assignsmslogvos.distribution == "Admin/Management"){
		$scope.tem_parentmobileno1 = false;
		$scope.tem_parentmobileno2 = false;
		$scope.tem_studentmobileno = false;
		$scope.tem_adminmobileno = true;
		$scope.tem_classteachermobileno = false;
	}
	
	var addText = furtherdata;
	if(furtherdata != null){
			
		var regExp = /\(([^)]+)\)/g;
		var matches = furtherdata.match(regExp);
		var empty_inputfield = angular.element(document.querySelector('#add_remove'));
		empty_inputfield.empty();
		var add_inputfield = angular.element(document.querySelector('#add_remove'));
		var setcontentlength = furtherdata.length;
		$scope.setinputtag = [];
		$scope.checkboxvalue = [{tem_parentmobileno1:true,name:"Parent Mobile Number1"},{tem_parentmobileno1:false,name:"Parent Mobile Number2"},{tem_parentmobileno1:false,name:"Student Mobile Number"},{tem_parentmobileno1:false,name:"Admin Mobile Number"},{tem_parentmobileno1:false,name:"Class Teacher Mobile Number"}];
		if(matches != null){
			for (var i = 0; i < matches.length; i++) {
				var str = matches[i],content="";
				//if(str.substring(1, str.length - 1) == "date" || str.substring(1, str.length - 1) == "DATE"){
				if(str.substring(1, str.length - 1).includes("date") || str.substring(1, str.length - 1).includes("DATE")){
					content = 'true';
					$scope.setinputtag.push({check:content,data:str.substring(1, str.length - 1),answerdata:""});
				}else{
					content = 'false';
					$scope.setinputtag.push({check:content,data:str.substring(1, str.length - 1),answerdata:""});
				}
						/*if(str.substring(1, str.length - 1) == "date" || str.substring(1, str.length - 1) == "DATE"){
							add_inputfield.append('<div class="col-md-12" style="margin:7px 0px;padding:0px">'+
							'<div class="col-md-3" style="padding:0px">'+str.substring(1, str.length - 1)+
							'<span style="font-weight:bold">=></span></div><div class="col-md-9" style="padding:0px">'+
							'<input type="text" placeholder="YYYY-DD-MM" data-ng-model="messageVO.date'+i+'" name="date'+i+'" class="form-control" validator="required" valid-method="watch"'+ 
							'required-error-message="Date is required"></div>'+
							'</div>'); 
						}else{
							add_inputfield.append('<div class="col-md-12" style="margin:7px 0px;padding:0px">'+
							'<div class="col-md-3" style="padding:0px">'+str.substring(1, str.length - 1)+
							'<span style="font-weight:bold">=></span></div><div class="col-md-9" style="padding:0px">'+
							'<input type="text" placeholder="Enter the '+str.substring(1, str.length - 1)+'" data-ng-model="messageVO.date'+i+'" name="date'+i+'" class="form-control" '+
							'validator="required" valid-method="watch"'+
							'required-error-message="'+str.substring(1, str.length - 1)+' is required"></div>'+
							'</div>'); 
						}*/
						setcontentlength = setcontentlength- str.substring(1, str.length - 1).length;
			}
			$scope.templatedesc = furtherdata;
			$scope.temcontent = setcontentlength;
			
			 $scope.$modalInstance = $modal.open({
			 scope: $scope,
			 templateUrl: 'template.html',
			 controller: function($scope, $modalInstance, $rootScope, $state) {
				 $scope.sendandsavesms = function(){
					 var checkuserdefinedcontent = "userdefined";
					 sendsmssave(checkuserdefinedcontent);
					 $modalInstance.close();
				 }
				  $scope.composeok = function() {
					  $modalInstance.close();
				  };
				  $scope.okcancel = function() {
					  $modalInstance.close();
				  };
			  }
		  });
		}
		else{
			add_inputfield.append('<div class="col-md-12" style="margin:7px 0px;padding:0px">No Varaibles Fields</div>'); 
			$scope.templatedesc = furtherdata;
			$scope.temcontent = furtherdata.length;
			 $scope.$modalInstance = $modal.open({
			 scope: $scope,
			 templateUrl: 'template.html',
			 controller: function($scope, $modalInstance, $rootScope, $state) {
				 $scope.sendandsavesms = function(){
					 var checkuserdefinedcontent = "userdefined";
					 sendsmssave(checkuserdefinedcontent);
					 $modalInstance.close();
				 }
				  $scope.composeok = function() {
					  $modalInstance.close();
				  };
				  $scope.okcancel = function() {
					  $modalInstance.close();
				  };
			  }
		  });
		 
		}
	} 
} 

$scope.continueuserdefined = function(distribution){
	
	if(distribution != "All Teacher" && distribution != "Individual Teacher" && distribution != "Admin/Management"){
				$scope.tem_parentmobileno1 = true;
				$scope.tem_parentmobileno2 = false;
				$scope.tem_studentmobileno = false;
				$scope.tem_adminmobileno = false;
				$scope.tem_classteachermobileno = false;
	}
	if(distribution == "All Teacher" || distribution == "Individual Teacher" || distribution == "Admin/Management"){
		$scope.tem_parentmobileno1 = false;
		$scope.tem_parentmobileno2 = false;
		$scope.tem_studentmobileno = false;
		$scope.tem_adminmobileno = true;
		$scope.tem_classteachermobileno = false;
	}
	$scope.$modalInstance = $modal.open({
		 scope: $scope,
		 templateUrl: 'userdefined.html',
		 controller: function($scope, $modalInstance, $rootScope, $state) {
			  $scope.composeuserdefinedok = function(mge) {
				   sendsmssave(mge);
				  $modalInstance.close();
			  };
			  $scope.okuserdefinedcancel = function() {
				  $modalInstance.close();
			  };
		  }
	});
}


$scope.selectgenderdata = function(furtherdata,description,distribution,gender){
	
	if(furtherdata == 'User Defined'){
			if(distribution != "All Teacher" && distribution != "Individual Teacher" && distribution != "Admin/Management"){
				$scope.tem_parentmobileno1 = true;
				$scope.tem_parentmobileno2 = false;
				$scope.tem_studentmobileno = false;
				$scope.tem_adminmobileno = false;
				$scope.tem_classteachermobileno = false;
			}
			if(distribution == "All Teacher" || distribution == "Individual Teacher" || distribution == "Admin/Management"){
				$scope.tem_parentmobileno1 = false;
				$scope.tem_parentmobileno2 = false;
				$scope.tem_studentmobileno = false;
				$scope.tem_adminmobileno = true;
				$scope.tem_classteachermobileno = false;
			}
			if(distribution == "All Teacher" || distribution == "All Student" || distribution == "Admin/Management"){
				 $scope.$modalInstance = $modal.open({
				 scope: $scope,
				 templateUrl: 'userdefined.html',
				 controller: function($scope, $modalInstance, $rootScope, $state) {
					  $scope.composeuserdefinedok = function(mge) {
						   sendsmssave(mge);
						  $modalInstance.close();
					  };
					  $scope.okuserdefinedcancel = function() {
						  $modalInstance.close();
					  };
				  }
			  });
		}
	} 
	if(furtherdata == 'Template' && description && (distribution == 'All Student' || distribution == 'All Teacher' || distribution == 'Admin/Management')){
		$scope.selectfurtherdata(description);
	}
	
	if(distribution == "Individual Student"){
		var SmsLogVOdata = $scope.SmsLogVO;
		if(SmsLogVOdata.gender)
			SmsLogVOdata.gender = SmsLogVOdata.gender
		else
			SmsLogVOdata.gender="";
		
		$http({
				url:appCon.globalCon.serviceBaseURL+"student/getAllStudentsByClassAndSectionAndGender?schoolId="+$cookieStore.get('schoolId')+"&standard="+SmsLogVOdata.classdata+"&section="+SmsLogVOdata.sectiondata+"&gender="+SmsLogVOdata.gender,
				method:'GET'
			}).success(function(result){				
				var shorts = result.responseVO.studentList;	
				$scope.studentlistdata = [];
				
			angular.forEach(shorts,function(value, key){
				$scope.studentlistdata.push({id:value.id,name:value.studentName});
			});
			$scope.demoOptions = {
				filterPlaceHolder: 'Start typing to filter the lists below.',
				helpMessage: ' To List (Click on Left Side Panel to Select Options and Right Side Panel to Deselect the Options)',
				/* angular will use this to filter your lists */
				orderProperty: 'name',
				/* this contains the initial list of all items (i.e. the left side) */
				items: $scope.studentlistdata,
				/* this list should be initialized as empty or with any pre-selected items */
				selectedItems: [] 
				}; 	                 
				
			console.log($scope.studentlistdata);
		});
	}
	if(distribution == "Individual Teacher"){
		var SmsLogVOdata = $scope.SmsLogVO;
		if(SmsLogVOdata.gender)
			SmsLogVOdata.gender = SmsLogVOdata.gender
		else
			SmsLogVOdata.gender="";
		$http({
				url:appCon.globalCon.serviceBaseURL+"teacher/getAllTeacherByGender?schoolId="+$cookieStore.get('schoolId')+"&gender="+SmsLogVOdata.gender,
				method:'GET'
			}).success(function(result){		
             console.log(result);
				var shorts = result.responseVO.teacherList;	
				$scope.teacherlistdata = [];
			angular.forEach(shorts,function(value, key){
				$scope.teacherlistdata.push({id:value.id,name:value.staffName});
			});
			$scope.demoOptions = {
				filterPlaceHolder: 'Start typing to filter the lists below.',
				helpMessage: ' To List (Click on Left Side Panel to Select Options and Right Side Panel to Deselect the Options)',
				/* angular will use this to filter your lists */
				orderProperty: 'name',
				/* this contains the initial list of all items (i.e. the left side) */
				items: $scope.teacherlistdata,
				/* this list should be initialized as empty or with any pre-selected items */
				selectedItems: [] 
				}; 	
		});
	}
}


function sendsmssave(messagearea){
	
	$scope.loading =true;
	console.log($scope.demoOptions);
	var assignsmslogvo = $scope.SmsLogVO,data = $scope.ComposeSMSVO,sentodata = [];
	if(assignsmslogvo.distribution == "All Student")
		data.distributionType = "ALL_STUDENTS";
	
	if(assignsmslogvo.distribution == "All Teacher"){
		data.distributionType = "ALL_TEACHERS";
		sentodata.push('Teacher');
	}
	
	if(assignsmslogvo.distribution == "Admin/Management"){
		data.distributionType = "ALL_ADMIN";
	}
	
	if(assignsmslogvo.distribution == "Class Wise"){
		data.distributionType = "CLASS_WISE";
		var classwisedata = $scope.demoOptions,assignclasswisedata = [];
		angular.forEach(classwisedata.selectedItems,function(value,key){
			assignclasswisedata.push(value.id);
		});
		console.log(assignclasswisedata);
		data.className = assignclasswisedata;
	}
	
	
	if(assignsmslogvo.distribution == "Section Wise"){
		data.distributionType = "SECTION_WISE";
		var classwisedata = $scope.demoOptions,assignclasswisedata = [];
		angular.forEach(classwisedata.selectedItems,function(value,key){
			assignclasswisedata.push(value.id);
		});
		console.log(assignsmslogvo.classdata)
		data.standard = assignsmslogvo.classdata;
		data.sectionList = assignclasswisedata;
	}

	if(assignsmslogvo.distribution == "Individual Student"){
		data.distributionType = "INDIVIDUAL_STUDENTS";
		var classwisedata = $scope.demoOptions,assignclasswisedata = [];
		angular.forEach(classwisedata.selectedItems,function(value,key){
			assignclasswisedata.push(value.id);
		});
		console.log(assignclasswisedata);
		data.receiverIds = assignclasswisedata;
	}
	
	if(assignsmslogvo.distribution == "Individual Teacher"){
		data.distributionType = "INDIVIDUAL_TEACHERS";
		sentodata.push('Teacher');
		var classwisedata = $scope.demoOptions,assignclasswisedata = [];
		angular.forEach(classwisedata.selectedItems,function(value,key){
			assignclasswisedata.push(value.id);
		});
		data.receiverIds = assignclasswisedata;
	}
	
	
	if(assignsmslogvo.furtherdata == "Template"){
		data.templateType = "Template";
		var datttext = $scope.templatedesc;
		angular.forEach($scope.setinputtag,function(value,key){
		 datttext = datttext.replace(new RegExp('(' + value.data + ')', 'gi'), value.answerdata);
		});
		datttext= datttext.replace(/\(/g, "").replace(/\)/g, "");
		data.message = datttext;
	}
	
	if(assignsmslogvo.gender){
		data.gender = assignsmslogvo.gender;
	}
	
	if(assignsmslogvo.furtherdata == "User Defined"){
		data.templateType = "User Defined";
		data.message = messagearea;
	}
	
	if($scope.tem_parentmobileno1){
		sentodata.push('Parent1');
	}
	if($scope.tem_parentmobileno2){
		sentodata.push('Parent2');
	}
	if($scope.tem_studentmobileno){
		sentodata.push('Student');
	}
	
	if($scope.tem_adminmobileno){
		sentodata.push('Admin');
	}
	
	if($scope.tem_classteachermobileno){
		sentodata.push('Teacher');
	}
	
	data.schoolId = $cookieStore.get('schoolId');
	data.sendTo = sentodata;
	
	$injector.get('smsservice')['sendSMS']($scope.ComposeSMSVO).then(        		
		 //Request Success 
		function(result) {
			
			if(result.data.statusFlag == 'Ok'){
				$scope.loading =false;
				$state.go('configsms');
			}
			
			if(result.data.statusFlag == 'Error')
			{
				$scope.loading =false;
				$scope.SmsLogVO = "";	
				$scope.errormesssage = result.data.errorMsg;
				$scope.$modalInstance = $modal.open({
				 scope: $scope,
				 templateUrl: 'sendingfailed.html',
				 controller: function($scope, $modalInstance, $rootScope, $state) {
					  $scope.sendingfailedokcancel = function(mge) {
						  $modalInstance.close();
					  };
				  }
			  });
			}
		},		
		function(error){
			console.log('failure');
		});
	
}


/*$scope.selectfurtherdatauserdefined = function(furtherdata){
	console.log(furtherdata);
	if(furtherdata = 'User Defined'){
		$scope.templatedesc = furtherdata;
			 $scope.$modalInstance = $modal.open({
			 scope: $scope,
			 templateUrl: 'template.html',
			 controller: function($scope, $modalInstance, $rootScope, $state) {
				  $scope.composeok = function() {
					  $modalInstance.close();
				  };
				  $scope.okcancel = function() {
					  $modalInstance.close();
				  };
			  }
		  });
	} 
} */


$scope.messagecount = 0;
$scope.getmessagecount = function(msgcount){
	$scope.messagecount = msgcount.length;
}


$http({
		url:appCon.globalCon.serviceBaseURL+"gender/getAllGenderBySchoolId?shoolId="+$cookieStore.get('schoolId')+"&pageIndex=0&pageSize="+appCon.globalCon.paginationSize,
		method:'GET'
	}).success(function(result){
		var genderdata = result.responseVO.religionVOs;		
		$scope.genders = [];
		angular.forEach(genderdata,function(value, key){
			$scope.genders.push({value:value.gender,key:value.id});
		});
	});


  $http({
			url:appCon.globalCon.serviceBaseURL+"smstemplate/getAllSmsTemplate?schoolId="+$cookieStore.get('schoolId')+"&pageIndex=0&pageSize=100",
			method:'GET'
		}).success(function(result){
			var shorts = result.responseVO.smsTemplateList;	
			$scope.discriptions = [];
	angular.forEach(shorts,function(value, key){
		$scope.discriptions.push({value:value.shortDescription,key:value.template});
	});
});

	
	$http({
		url:appCon.globalCon.serviceBaseURL+"section/getAllSectionBySchoolId?shoolId="+$cookieStore.get('schoolId')+"&pageIndex=0&pageSize=100",
		method:'GET'
	}).success(function(result){
		var sections = result.responseVO.sectionVOs;		     
	    $scope.sectiondatas = [];
		 $scope.listsections = sections;
		angular.forEach(sections,function(value, key){
			$scope.sectiondatas.push({value:value.sectionName,key:value.id});
		});
	});
	
	
	
	$http({
		url:appCon.globalCon.serviceBaseURL+"class/getAllClassBySchoolId?shoolId="+$cookieStore.get('schoolId')+"&pageIndex=0&pageSize=100",
		method:'GET'
	}).success(function(result){		
		var classes = result.responseVO.classVOs;
	     $scope.classdatas = [];
		 $scope.listclasses = classes;
	    angular.forEach(classes,function(value, key){
			$scope.classdatas.push({id:value.id,name:value.className});
		});
		
	});
	
	
	


}]);



