
var app=angular.module("app",['ngRoute'])
	
	app.config(function($routeProvider){
		$routeProvider
		.when('/login',{controller:'UserCtrl',templateUrl:'views/login.html'})
		.when('/register',{controller:'UserCtrl',templateUrl:'views/register.html'})
		.otherwise({templateUrl:'index.html'})
	})