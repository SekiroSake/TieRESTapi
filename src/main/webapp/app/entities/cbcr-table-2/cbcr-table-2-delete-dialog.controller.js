(function() {
    'use strict';

    angular
        .module('tieEntityApp')
        .controller('CbcrTable2DeleteController',CbcrTable2DeleteController);

    CbcrTable2DeleteController.$inject = ['$uibModalInstance', 'entity', 'CbcrTable2'];

    function CbcrTable2DeleteController($uibModalInstance, entity, CbcrTable2) {
        var vm = this;

        vm.cbcrTable2 = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            CbcrTable2.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
