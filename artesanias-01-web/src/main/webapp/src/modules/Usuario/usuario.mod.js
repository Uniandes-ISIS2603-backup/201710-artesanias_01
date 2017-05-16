/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * 
 * 
 * Autor: Felipe Velásquez/f.velasquez11/SarkFox
 */


(function (ng) {
     var mod = ng.module("usuarioModule", ['ui.router']);
     mod.constant("usuarioContext", "api/usuarios");
     mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
             var basePath = 'src/modules/Usuario/';
             $urlRouterProvider.otherwise("/usuariosList");
 
             $stateProvider.state('usuario', {
                 url: '/usuarios',
                 abstract: true,
                 resolve: {
                     usuarios: ['$http', 'usuariosContext', function ($http, usuariosContext) {
                            return $http.get(usuariosContext);
                        }]
                 },
                 views: {
                     'mainView': {
                         templateUrl: basePath + 'usuario.html'
                     }
                 }
             }).state('usuariosList', {
                 url: '/usuarios/list',
                 parent:'usuario',
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
                 resolve: {
                    currentUsuario: ['$http', 'usuariosContext', '$stateParams', function ($http, usuariosContext, $params) {
                            return $http.get(usuariosContext + '/' + $params.usuarioId);
                        }]
                },
                 views: {
                     'detailView': {
                         templateUrl: basePath + 'usuario.detail.html',
                         controller: ['$scope', 'currentUsuario', function ($scope, currentUsuario) {
                                $scope.currentUsuario = currentUsuario.data;                               
                            }]
                     },
                 }
 
             });
         }]);
         
 })(window.angular); 