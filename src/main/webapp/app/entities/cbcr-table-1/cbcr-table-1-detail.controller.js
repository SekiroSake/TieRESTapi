(function() {
    'use strict';

    angular
        .module('tieEntityApp')
        .controller('CbcrTable1DetailController', CbcrTable1DetailController);

    CbcrTable1DetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'entity', 'CbcrTable1', 'TieDoc'];

    function CbcrTable1DetailController($scope, $rootScope, $stateParams, entity, CbcrTable1, TieDoc) {
        var vm = this;

        vm.cbcrTable1 = entity;

        var unsubscribe = $rootScope.$on('tieEntityApp:cbcrTable1Update', function(event, result) {
            vm.cbcrTable1 = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
