(function (ng) {
    var mod = ng.module("feriaModule", ['ui.router']);
    mod.constant("feriasContext", "api/feria");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/ferias/';
            $urlRouterProvider.otherwise("/feriasList");
            self = this;            
            $stateProvider.state('ferias', {
                url: '/ferias',
                abstract: true,
                resolve: {
                    ferias: ['$http','feriasContext', function ($http, feriasContext) {
                            console.log("Holi-11");
                            console.log("feriasContext: "+feriasContext);
                            console.log("return: "+$http.get(feriasContext));
                            return $http.get(feriasContext);
                            console.log("Holi-12");
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'ferias.html',
                        controller: ['$scope', 'ferias', function ($scope, ferias) {
                                console.log("Holi-21");
                                $scope.feriasRecords = ferias.data;
                                console.log("Holi-22");
                            }]
                    }
                }
            }).state('feriasList', {
                url: '/list',
                parent: 'ferias',
                views: {
                    'listView': {
                        templateUrl: basePath + 'ferias.list.html'
                    }
                }
            }).state('feriaDetail', {
                url: '/{feriaId:int}/detail',
                parent: 'ferias',
                param: {
                    feriaId: null
                },
                views: {
                   
                    'detailView': {
                        templateUrl: basePath + 'ferias.detail.html',
                        controller: ['$scope', '$stateParams', function ($scope, $params) {
                                $scope.currentFeria = $scope.feriasRecords[$params.feriaId-1];
                            }]
                    }

                }

            });
        }]);
})(window.angular);