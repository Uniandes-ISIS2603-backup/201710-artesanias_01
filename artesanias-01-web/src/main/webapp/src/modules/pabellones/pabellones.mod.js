(function (ng) {
    var mod = ng.module("pabellonModule", ['ui.router']);
    mod.constant("pabellonesContext", "api/pabellones");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/pabellones/';
            $urlRouterProvider.otherwise("/pabellonesList");
            self = this;            
            $stateProvider.state('pabellones', {
                url: '/pabellones',
                abstract: true,
                resolve: {
                    pabellones: ['$http', function ($http) {
                            return $http.get('data/pabellones.json');
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'pabellones.html',
                        controller: ['$scope', 'pabellones', function ($scope, pabellones) {
                                $scope.pabellonesRecords = pabellones.data;
                            }]
                    }
                }
            }).state('pabellonesList', {
                url: '/list',
                parent: 'pabellones',
                views: {
                    'listView': {
                        templateUrl: basePath + 'pabellones.list.html'
                    }
                }
            });
        }]);
})(window.angular);
