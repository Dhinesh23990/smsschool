'use strict';

angular.module(appCon.appName).factory("MethodService", ['$http',
    function ($http) { // This service connects to our REST API

       // var serviceBase = 'api/v1/';

        var obj = {};
       
        obj.get = function (rajasekar) {
            return $http.get(rajasekar).then(function (results) 
			{
                return results.data;
            });
        };
        obj.post = function (q, object) {
            return $http.post(q, object).then(function (results) {
                return results.data;
            });
        };
        obj.put = function (q, object) {
            return $http.put(q, object).then(function (results) {
                return results.data;
            });
        };
        obj.delete = function (q) {
            return $http.delete(q).then(function (results) {
                return results.data;
            });
        };

        return obj;
}]);