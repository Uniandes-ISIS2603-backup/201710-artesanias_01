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
                    pabellones: ['$http','pabellonesContext', function ($http, pabellonesContext) {
                            console.log("P-Holi-11");
                            console.log("pabellonesContext: "+pabellonesContext);
                            return $http.get(pabellonesContext);
                            console.log("P-Holi-12");
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'pabellones.html',
                        controller: ['$scope', 'pabellones', function ($scope, pabellones) {
                                console.log("P-Holi-21");
                                $scope.pabellonesRecords = pabellones.data;
                                console.log("P-Holi-22");
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
