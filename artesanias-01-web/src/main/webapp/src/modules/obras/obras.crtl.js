/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//Author: James Lake . jh.lake

(function (ng) {
    var mod = ng.controller("ObrasController", ['$stateProvider', '$scope', 'obra', function ($stateProvider, $scope, obra) {
                                console.log("Muestra una obra");
                                $scope.obra = obra.data;
                            }]);
    
    
})(window.angular);


