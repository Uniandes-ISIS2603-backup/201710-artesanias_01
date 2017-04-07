/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


(function (ng) {
    var mod = ng.module("reservasModule", ['ui.router']);
    
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/Reservas/';
            $urlRouterProvider.otherwise("/reservasList");

            $stateProvider.state('reservas', {
                url: '/reservas',
                abstract: true,
                resolve: {
                    reservas: ['$http', function ($http) {
                        
                            return $http.get('data/reservas.json');
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'reservas.html',
                        controller: ['$scope', 'reservas', function ($scope, reservas) {
                               
                                $scope.reservasRecords = reservas.data;
                            }]
                    }
                }
            }).state('reservasList', {
                url: '/list',
                parent: 'reservas',
                
                views: {
                    'listView': {
                        templateUrl: basePath + 'reservas.list.html',
                        
                    }
                }
            })
            
            .state('reservasDetail', {
                url: '{reservasId:int}/detail',
                parent: 'reservas',
                param: {
                reservasId: null
                },
               
                
                views: {
                    'detailView': {
                        templateUrl: basePath + 'reservas.detail.html',
                        controller: ['$scope', '$stateParams', function ($scope, $params) {
                                $scope.currentreservas = $scope.reservasRecords[$params.reservasId-1];
                            }]
                    }

                }

            });
        }]);
})(window.angular);

