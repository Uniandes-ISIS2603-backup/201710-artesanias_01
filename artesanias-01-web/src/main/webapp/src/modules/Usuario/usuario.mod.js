/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//autor: Felipe Velàsquez (f.velasquez11/SarkFox)

(function (ng) {
    var mod = ng.module("usuarioModule", ['ui.router']);
    
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/Usuario/';
            $urlRouterProvider.otherwise("/usuariosList");

            $stateProvider.state('usuario', {
                url: '/usuarios',
                abstract: true,
                resolve: {
                    usuarios: ['$http', function ($http) {
                            console.log("Holi");
                            return $http.get('data/dataUsuarios.json');
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'usuario.html',
                        controller: ['$scope', 'usuarios', function ($scope, usuarios) {
                                console.log("Holi2");
                                $scope.usuariosRecords = usuarios.data;
                            }]
                    }
                }
            }).state('usuariosList', {
                url: '/usuarios/list',
                resolve: {
                    usuarios: ['$http', function ($http) {
                            console.log("3");
                            return $http.get('data/dataUsuarios.json');
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'usuario.list.html',
                        controller: ['$scope', 'usuarios', function ($scope, usuarios) {
                                console.log("Holi4");
                                $scope.usuariosRecords = usuarios.data;
                            }]
                    }
                }
            }).state('usuariosDetail', {
                url: '/{usuarioId:int}/detail',
                parent: 'usuario',
                param: {
                    usuarioId: null
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'usuario.detail.html',
                        controller: ['$scope', '$stateParams', 'usuarios', function ($scope, $params, usuarios) {
                                var usuariosRecords = usuarios.data;
                                $scope.currentUsuario = $scope.usuariosRecords[$params.usuarioId - 1];
                            }]
                    }

                }

            });
        }]);
        
})(window.angular);