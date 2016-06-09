(function() {
    'use strict';

    angular
        .module('tieEntityApp')
        .controller('CbcrEntityDialogController', CbcrEntityDialogController);

    CbcrEntityDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'CbcrEntity', 'TieDoc'];

    function CbcrEntityDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, CbcrEntity, TieDoc) {
        var vm = this;

        vm.cbcrEntity = entity;
        vm.clear = clear;
        vm.save = save;
        vm.tiedocs = TieDoc.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.cbcrEntity.id !== null) {
                CbcrEntity.update(vm.cbcrEntity, onSaveSuccess, onSaveError);
            } else {
                CbcrEntity.save(vm.cbcrEntity, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('tieEntityApp:cbcrEntityUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
