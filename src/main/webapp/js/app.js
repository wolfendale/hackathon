var fmt = angular.module('fmt', ['ui.bootstrap', 'ngAnimate', 'ngRoute','angular-jqcloud']);

fmt.config(function($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl : 'pages/home.html',
                controller  : 'homeController'
            })
            .when('/pages/feedbackCategories.html', {
                templateUrl : 'pages/feedbackCategories.html',
                controller  : 'feedbackCategoriesController'
            })
            .when('/pages/home.html', {
                templateUrl : 'pages/home.html',
                controller  : 'homeController'
            });
    });