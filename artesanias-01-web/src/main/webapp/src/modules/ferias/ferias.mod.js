xfunction (ng) {
    var mod = ng.module("feriaModule", ['ui.router']);
    mod.constant("feriasContext", "api/ferias");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/ferias/';
            $urlRouterProvider.otherwise("/feriasList");
            self = this;            
            $stateProvider.state('ferias', {
                url: '/ferias',
                abstract: true,
                resolve: {
                    ferias: ['$http', function ($http) {
                            return $http.get('data/ferias.json');
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'ferias.html',
                        controller: ['$scope', 'ferias', function ($scope, ferias) {
                                $scope.feriasRecords = ferias.data;
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
