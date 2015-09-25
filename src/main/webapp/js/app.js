var fmt = angular.module('fmt', ['ui.bootstrap', 'ngAnimate', 'ngRoute','angular-jqcloud', 'n3-pie-chart']);

fmt.config(function($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl : 'pages/home.html',
                controller  : 'homeController'
            })
            .when('/pages/entityDetails.html', {
                templateUrl : 'pages/entityDetails.html',
                controller  : 'entityDetailsController'
            })
            .when('/pages/home.html', {
                templateUrl : 'pages/home.html',
                controller  : 'homeController'
            });
    });

fmt.value("ApplicationState", {
    showSearchBar: false
});