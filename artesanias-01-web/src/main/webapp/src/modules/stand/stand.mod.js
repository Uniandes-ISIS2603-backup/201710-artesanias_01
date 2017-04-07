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
                        
                            return $http.get('data/stand.json');
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'stand.html',
                        controller: ['$scope', 'stand', function ($scope, stand) {
                               
                                $scope.standRecords = stand.data;
                            }]
                    }
                }
            }).state('standList', {
                url: '/list',
                parent: 'stand',
                
                views: {
                    'listView': {
                        templateUrl: basePath + 'stand.list.html',
                        
                    }
                }
            })
            
            .state('standDetail', {
                url: '{standId:int}/detail',
                parent: 'stand',
                param: {
                standId: null
                },
               
                
                views: {
                    'detailView': {
                        templateUrl: basePath + 'stand.detail.html',
                        controller: ['$scope', '$stateParams', function ($scope, $params) {
                                $scope.currentStand = $scope.standRecords[$params.standId-1];
                            }]
                    }

                }

            });
        }]);
})(window.angular);

