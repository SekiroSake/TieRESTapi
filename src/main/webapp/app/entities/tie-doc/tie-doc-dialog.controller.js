(function() {
    'use strict';

    angular
        .module('tieEntityApp')
        .controller('TieDocDialogController', TieDocDialogController);

    TieDocDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', '$q', 'entity', 'TieDoc', 'CbcrEntity', 'CbcrTable1', 'CbcrTable2', 'CbcrTable3'];

    function TieDocDialogController ($timeout, $scope, $stateParams, $uibModalInstance, $q, entity, TieDoc, CbcrEntity, CbcrTable1, CbcrTable2, CbcrTable3) {
        var vm = this;

        vm.tieDoc = entity;
        vm.clear = clear;
        vm.save = save;
        vm.cbcrentitys = CbcrEntity.query({filter: 'tiedoc-is-null'});
        $q.all([vm.tieDoc.$promise, vm.cbcrentitys.$promise]).then(function() {
            if (!vm.tieDoc.cbcrEntity || !vm.tieDoc.cbcrEntity.id) {
                return $q.reject();
            }
            return CbcrEntity.get({id : vm.tieDoc.cbcrEntity.id}).$promise;
        }).then(function(cbcrEntity) {
            vm.cbcrentities.push(cbcrEntity);
        });
        vm.cbcrtable1s = CbcrTable1.query({filter: 'tiedoc-is-null'});
        $q.all([vm.tieDoc.$promise, vm.cbcrtable1s.$promise]).then(function() {
            if (!vm.tieDoc.cbcrTable1 || !vm.tieDoc.cbcrTable1.id) {
                return $q.reject();
            }
            return CbcrTable1.get({id : vm.tieDoc.cbcrTable1.id}).$promise;
        }).then(function(cbcrTable1) {
            vm.cbcrtable1s.push(cbcrTable1);
        });
        vm.cbcrtable2s = CbcrTable2.query({filter: 'tiedoc-is-null'});
        $q.all([vm.tieDoc.$promise, vm.cbcrtable2s.$promise]).then(function() {
            if (!vm.tieDoc.cbcrTable2 || !vm.tieDoc.cbcrTable2.id) {
                return $q.reject();
            }
            return CbcrTable2.get({id : vm.tieDoc.cbcrTable2.id}).$promise;
        }).then(function(cbcrTable2) {
            vm.cbcrtable2s.push(cbcrTable2);
        });
        vm.cbcrtable3s = CbcrTable3.query({filter: 'tiedoc-is-null'});
        $q.all([vm.tieDoc.$promise, vm.cbcrtable3s.$promise]).then(function() {
            if (!vm.tieDoc.cbcrTable3 || !vm.tieDoc.cbcrTable3.id) {
                return $q.reject();
            }
            return CbcrTable3.get({id : vm.tieDoc.cbcrTable3.id}).$promise;
        }).then(function(cbcrTable3) {
            vm.cbcrtable3s.push(cbcrTable3);
        });

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.tieDoc.id !== null) {
                TieDoc.update(vm.tieDoc, onSaveSuccess, onSaveError);
            } else {
                TieDoc.save(vm.tieDoc, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('tieEntityApp:tieDocUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
