(function() {
    'use strict';

    angular
        .module('tieEntityApp')
        .controller('CbcrTable2DetailController', CbcrTable2DetailController);

    CbcrTable2DetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'entity', 'CbcrTable2', 'TieDoc'];

    function CbcrTable2DetailController($scope, $rootScope, $stateParams, entity, CbcrTable2, TieDoc) {
        var vm = this;

        vm.cbcrTable2 = entity;

        var unsubscribe = $rootScope.$on('tieEntityApp:cbcrTable2Update', function(event, result) {
            vm.cbcrTable2 = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
