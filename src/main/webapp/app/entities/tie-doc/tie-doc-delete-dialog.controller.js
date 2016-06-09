(function() {
    'use strict';

    angular
        .module('tieEntityApp')
        .controller('TieDocDeleteController',TieDocDeleteController);

    TieDocDeleteController.$inject = ['$uibModalInstance', 'entity', 'TieDoc'];

    function TieDocDeleteController($uibModalInstance, entity, TieDoc) {
        var vm = this;

        vm.tieDoc = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            TieDoc.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
