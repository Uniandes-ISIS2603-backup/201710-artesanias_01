/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//Author: James Lake . jh.lake

(function (ng) {
    var mod = ng.module("ObrasModule", ['ui.router']);
    
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/Obras/';
            $urlRouterProvider.otherwise("/obrasList");

            $stateProvider.state('obraDetail', {
                url: '/obras/{:id}' ,
                abstract: true,
                resolve: {
                    obra: ['$stateProvider', '$http', function ($stateProvider, $http) {
                            console.log("Se conecto obra");
                            $http.get('data/obras.json', function(data){
                                angular.forEach(data.data, function(obra, key){
                                    if(key == $stateProvider.params.id)
                                    {
                                        return obra;
                                    }
                                });
                            });
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'obras.html',
                        controller: "ObrasController"
                    }
                }
            }).state('obrasList', {
                url: '/obras/list',
                resolve: {
                    obras: ['$http', function ($http) {
                            console.log("Estoy cargando lista de obras");
                            return $http.get('data/obras.json');
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'obras.list.html',
                        controller: ['$scope', 'obras', function ($scope, obras) {
                                console.log("Las cargué");
                                $scope.obrasList = obras.data;
                            }]
                    }
                }
            });
        }]);
})(window.angular);



