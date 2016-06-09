(function() {
    'use strict';

    angular
        .module('tieEntityApp')
        .controller('CbcrEntityDetailController', CbcrEntityDetailController);

    CbcrEntityDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'entity', 'CbcrEntity', 'TieDoc'];

    function CbcrEntityDetailController($scope, $rootScope, $stateParams, entity, CbcrEntity, TieDoc) {
        var vm = this;

        vm.cbcrEntity = entity;

        var unsubscribe = $rootScope.$on('tieEntityApp:cbcrEntityUpdate', function(event, result) {
            vm.cbcrEntity = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
