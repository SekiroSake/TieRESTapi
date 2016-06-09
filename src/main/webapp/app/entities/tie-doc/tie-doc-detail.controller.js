(function() {
    'use strict';

    angular
        .module('tieEntityApp')
        .controller('TieDocDetailController', TieDocDetailController);

    TieDocDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'entity', 'TieDoc', 'CbcrEntity', 'CbcrTable1', 'CbcrTable2', 'CbcrTable3'];

    function TieDocDetailController($scope, $rootScope, $stateParams, entity, TieDoc, CbcrEntity, CbcrTable1, CbcrTable2, CbcrTable3) {
        var vm = this;

        vm.tieDoc = entity;

        var unsubscribe = $rootScope.$on('tieEntityApp:tieDocUpdate', function(event, result) {
            vm.tieDoc = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
