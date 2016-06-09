(function() {
    'use strict';

    angular
        .module('tieEntityApp')
        .controller('CbcrTable1DeleteController',CbcrTable1DeleteController);

    CbcrTable1DeleteController.$inject = ['$uibModalInstance', 'entity', 'CbcrTable1'];

    function CbcrTable1DeleteController($uibModalInstance, entity, CbcrTable1) {
        var vm = this;

        vm.cbcrTable1 = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            CbcrTable1.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
