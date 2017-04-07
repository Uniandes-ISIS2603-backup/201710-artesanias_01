/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("conferenciaModule", ['ui.router']);
//    mod.constant("conferenciaContext", "api/books");
    mod.config(['$stateProvider',  '$urlRouterProvider', function ( $stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/conferencia/';
            $urlRouterProvider.otherwise("/conferenciaList");

            $stateProvider.state('conferencia', {
                url: '/conferencia',
                abstract: true,
                resolve: {
                    conferencia: ['$http', 'conferenciaContext', function ($http, conferenciaContext) {
                            return $http.get(conferenciaContext);
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
                        templateUrl: basePath + 'conferencia.list.html'
                    }
                }
            }).state('conferenciaDetail', {
                url: '/{conferenciaId:int}/detail',
                parent: 'conferencia',
                param: {
                    conferenciaId: null
                },
                resolve:  {
                    currentConferencia: ['$http', 'conferenciaContext', '$stateParams', function ($http, conferenciaContext, $params) {
                            return $http.get(conferenciaContext+'/'+$params.conferenciaId);
                        }]
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'conferencia.detail.html',
                        controller: ['$scope', 'currentConferencia', function ($scope,  currentConferencia) {
                                $scope.currentConferencia = currentConferencia.data;
                            }]
                    }

                }

            });
        }]);
})(window.angular);

