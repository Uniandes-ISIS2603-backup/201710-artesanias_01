(function (ng) {
    var mod = ng.module("comentarioModule", ['ui.router']);
    mod.constant("comentarioContext", "api/cometario");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/comentario/';
            $urlRouterProvider.otherwise("/comentarioList");
            self = this;            
            $stateProvider.state('comentario', {
                url: '/comentario',
                abstract: true,
                resolve: {
                    pabellones: ['$http',function ($http) {
                            console.log("P-Holi-11");
                            return $http.get('data/comentarios.json');  
                            console.log("P-Holi-12");
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'comentario.html',
                        controller: ['$scope', 'comentario', function ($scope, comentario) {
                                console.log("P-Holi-21");
                                $scope.comentarioRecords = comentario.data;
                                console.log("P-Holi-22");
                            }]
                    }
                }
            }).state('comentarioList', {
                url: '/list',
                parent: 'comentario',
                views: {
                    'listView': {
                        templateUrl: basePath + 'comentario.list.html'
                    }
                }
            });
        }]);
})(window.angular);
