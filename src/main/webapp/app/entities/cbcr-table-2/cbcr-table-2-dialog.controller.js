(function() {
    'use strict';

    angular
        .module('tieEntityApp')
        .controller('CbcrTable2DialogController', CbcrTable2DialogController);

    CbcrTable2DialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'CbcrTable2', 'TieDoc'];

    function CbcrTable2DialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, CbcrTable2, TieDoc) {
        var vm = this;

        vm.cbcrTable2 = entity;
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
            if (vm.cbcrTable2.id !== null) {
                CbcrTable2.update(vm.cbcrTable2, onSaveSuccess, onSaveError);
            } else {
                CbcrTable2.save(vm.cbcrTable2, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('tieEntityApp:cbcrTable2Update', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
