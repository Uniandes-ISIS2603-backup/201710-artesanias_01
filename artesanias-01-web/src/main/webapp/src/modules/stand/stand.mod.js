/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


(function (ng) {
    var mod = ng.module("standModule", ['ui.router']);
    
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/stand/';
            $urlRouterProvider.otherwise("/standList");

            $stateProvider.state('stand', {
                url: '/stand',
                abstract: true,
                resolve: {
                    stand: ['$http', function ($http) {
                            console.log("Holi");
                            return $http.get('data/stand.json');
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'stand.html',
                        controller: ['$scope', 'stand', function ($scope, stand) {
                                console.log("Holi2");
                                $scope.standRecords = stand.data;
                            }]
                    }
                }
            }).state('standList', {
                url: '/stand/list',
                resolve: {
                    stand: ['$http', function ($http) {
                            console.log("3");
                            return $http.get('data/stand.json');
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'stand.list.html',
                        controller: ['$scope', 'stand', function ($scope, stand) {
                                console.log("Holi4");
                                $scope.standRecords = stand.data;
                            }]
                    }
                }
            });
        }]);
})(window.angular);

