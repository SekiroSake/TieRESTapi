(function() {
    'use strict';

    angular
        .module('tieEntityApp')
        .controller('CbcrTable1DialogController', CbcrTable1DialogController);

    CbcrTable1DialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'CbcrTable1', 'TieDoc'];

    function CbcrTable1DialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, CbcrTable1, TieDoc) {
        var vm = this;

        vm.cbcrTable1 = entity;
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
            if (vm.cbcrTable1.id !== null) {
                CbcrTable1.update(vm.cbcrTable1, onSaveSuccess, onSaveError);
            } else {
                CbcrTable1.save(vm.cbcrTable1, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('tieEntityApp:cbcrTable1Update', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
