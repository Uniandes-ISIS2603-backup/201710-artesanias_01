/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


(function (ng) {
    var mod = ng.module("conferenciaModule", ['ui.router']);
    
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/Conferencia/';
            $urlRouterProvider.otherwise("/conferenciaList");

            $stateProvider.state('conferencia', {
                url: '/conferencia',
                abstract: true,
                resolve: {
                    conferencia: ['$http', function ($http) {
                        
                            return $http.get('data/conferencia.json');
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'conferencia.html',
                        controller: ['$scope', 'conferencia', function ($scope, conferencia) {
                               
                                $scope.conferenciaRecords = conferencia.data;
                            }]
                    }
                }
            }).state('conferenciaList', {
                url: '/list',
                parent: 'conferencia',
                
                views: {
                    'listView': {
                        templateUrl: basePath + 'conferencia.list.html',
                        
                    }
                }
            })
            
            .state('conferenciaDetail', {
                url: '{conferenciaId:int}/detail',
                parent: 'conferencia',
                param: {
                conferenciaId: null
                },
               
                
                views: {
                    'detailView': {
                        templateUrl: basePath + 'conferencia.detail.html',
                        controller: ['$scope', '$stateParams', function ($scope, $params) {
                                $scope.currentConferencia = $scope.conferenciaRecords[$params.conferenciaId-1];
                            }]
                    }

                }

            });
        }]);
})(window.angular);

