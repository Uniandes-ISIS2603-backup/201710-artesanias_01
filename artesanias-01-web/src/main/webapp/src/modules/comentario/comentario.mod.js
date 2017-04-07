/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("reviewModule", ['ui.router']);

    mod.constant("booksContext", "api/reviews");

    mod.constant("reviewsContext", "reviews");

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/reviews/';
            $urlRouterProvider.otherwise("/reviewsList");

            $stateProvider.state('reviews', {
                url: '/reviews',
                abstract: true,
                parent: 'obrasDetail',
                resolve: {
                    reviews: ['$http', 'obrasContext', 'reviewsContext', '$stateParams', function ($http, obrasContext, reviewsContext, $params) {
                            return $http.get(obrasContext + '/' + $params.obraId + '/' + reviewsContext);
                        }]
                },
                views: {
                    'childrenView': {
                        templateUrl: basePath + 'reviews.html'
                    }
                },
            }).state('reviewsList', {
                url: '/list',
                parent: 'reviews',
                views: {
                    'listView': {
                        templateUrl: basePath + 'reviews.list.html',
                        controller: ['$scope', 'reviews', function ($scope, reviews) {
                                $scope.reviewsRecords = reviews.data;
                            }]
                    }
                }
            });
        }]);
})(window.angular);

