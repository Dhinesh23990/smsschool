'use strict';

angular.module(appCon.appName).controller('navbarController', ['$injector', '$attrs', '$element', '$scope', '$modal', '$parse', 'ngDialog',
      function($injector, $attrs, $element, $scope, $modal, $parse,ngDialog) {
	
	var $state,$cookieStore,$http;
	$state = $injector.get('$state');
	$cookieStore = $injector.get('$cookieStore');
	$http = $injector.get('$http');
	$scope.simplenavbar = "check";
	$http ({
		url:appCon.globalCon.serviceBaseURL+"school/getSchoolById?schoolId="+$cookieStore.get('schoolId')+"&pageIndex=0&pageSize=100",
		method:'GET'
		}).success(function(result){
             $scope.schooldata = result.responseVO.SchoolVO;
			
		});
		    angular.element('#listResults').on('click', 'li:first', function() {
			angular.element("#slimScrollDiv").find('ul li:first').remove('active');
			angular.element("#slimScrollDiv").find('ul li:first').addClass('active');
			angular.element("#slimScrollDiv").find('ul').parent('li').removeClass('active');
		});
          var l = angular.element(this),
		  o = angular.element("#slimScrollDiv").find('ul').parent('li'),
          n = o.children('a'),
		  k = o.find('ul li a');
		  kl = o.find('ul li');
          c = angular.element("#slimScrollDiv").find('.nav-sidebar').children('li').not(o),
          d = c.children('a'),
          m = angular.element('.appWrap'),
          b = m.find('#navigation-toggle');
          //g = angular.element(a);
          l.on('mouseenter', function () {
            m.hasClass('hz-menu') && !m.hasClass('viewport-sm') && (l.addClass('nav-expanded'), m.addClass('nav-expanded'))
          }),
          l.on('mouseleave', function () {
            m.hasClass('hz-menu') && !m.hasClass('viewport-sm') && (l.removeClass('nav-expanded'), m.removeClass('nav-expanded'))
          }),
          o.addClass('dropdown');
          var h = o.find('ul >.dropdown');
          h.addClass('submenu'),
          n.append('<span class="indicator"></span>'),
          n.on('click', function (t) {
            var a = $(this),
            i = a.parent('li'),
            l = angular.element('.submenu.open');
			angular.element("#slimScrollDiv").find('li').removeClass('active');
			i.addClass('active');
            i.hasClass('submenu') || o.not(i).removeClass('open').find('ul').slideUp(),
            l.not(a.parents('.submenu')).removeClass('open').find('ul').slideUp(),
            i.toggleClass('open').find('>ul').stop().slideToggle(),
            t.preventDefault()
          }),
		  k.on('click', function () {
				kl.removeClass('active');
				angular.element(this).closest('li').addClass('active');
          }),
          d.on('click', function () {
            o.removeClass('open').find('ul').slideUp()
          });
          var v = angular.element('.dropdown>ul>.active').parent();
          v.css('display', 'block');
		  b.on('click', function () {
            m.hasClass('viewport-sm') ? (l.toggleClass('navigation-hidden'), m.toggleClass('navigation-hidden'))  : (l.toggleClass('navigation-sm'), m.toggleClass('navigation-sm')),
			angular.element("#nav-bar").hasClass('navigation-sm') ? (angular.element("#nav-bar").removeClass('navigation-sm')) : (angular.element("#nav-bar").addClass('navigation-sm'))
          });
		  
		$scope.gotocenterpage = function(page){
			$state.go(page);
		} 
		
        $scope.gotosmspage = function(){
			$state.go('configsms');	
		} 	
      
}]);