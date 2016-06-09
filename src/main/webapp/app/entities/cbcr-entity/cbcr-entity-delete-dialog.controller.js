(function() {
    'use strict';

    angular
        .module('tieEntityApp')
        .controller('CbcrEntityDeleteController',CbcrEntityDeleteController);

    CbcrEntityDeleteController.$inject = ['$uibModalInstance', 'entity', 'CbcrEntity'];

    function CbcrEntityDeleteController($uibModalInstance, entity, CbcrEntity) {
        var vm = this;

        vm.cbcrEntity = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            CbcrEntity.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
