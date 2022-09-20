/**
 * Created by Jirka on 25.01.2017.
 */
var app = angular.module('app', ['ngRoute','ngResource']);

app.config(['$locationProvider', function($locationProvider) {
    $locationProvider.hashPrefix("");
}]);

app.config(function($routeProvider){
    $routeProvider
        .when('/users',{
            templateUrl: '/views/users.html',
            controller: 'usersController'
        })
        .when('/roles',{
            templateUrl: '/views/roles.html',
            controller: 'rolesController'
        })
        .otherwise(
            { redirectTo: '/'}
        );
});