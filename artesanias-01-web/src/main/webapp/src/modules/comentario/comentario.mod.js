/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("comentarioModule", ['ui.router']);

    mod.constant("comentarioContext", "api/comentario");

    mod.constant("comentarioContext", "comentarioss");

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/Comentario/';
            $urlRouterProvider.otherwise("/comentariosList");

            $stateProvider.state('comentarioss', {
                url: '/comentarios',
                abstract: true,
                parent: 'obrasDetail',
                resolve: {
                    comentario: ['$http', 'obrasContext', function ($http, obrasContext, comentariosContext, $params) {
                            return $http.get(obrasContext + '/' + $params.obraId + '/' + comentariosContext);
                        }]
                },
                views: {
                    'childrenView': {
                        templateUrl: basePath + 'comentarios.html'
                    }
                },
            }).state('comentariosList', {
                url: '/list',
                parent: 'comentarios',
                views: {
                    'listView': {
                        templateUrl: basePath + 'comentarios.list.html',
                        controller: ['$scope', 'comentarios', function ($scope, comentarios) {
                                $scope.reviewsRecords = comentarios.data;
                            }]
                    }
                }
            });
        }]);
})(window.angular);

