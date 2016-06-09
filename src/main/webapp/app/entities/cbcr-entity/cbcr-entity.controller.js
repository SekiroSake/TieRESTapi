(function() {
    'use strict';

    angular
        .module('tieEntityApp')
        .controller('CbcrEntityController', CbcrEntityController);

    CbcrEntityController.$inject = ['$scope', '$state', 'CbcrEntity', 'CbcrEntitySearch'];

    function CbcrEntityController ($scope, $state, CbcrEntity, CbcrEntitySearch) {
        var vm = this;
        
        vm.cbcrEntities = [];
        vm.search = search;

        loadAll();

        function loadAll() {
            CbcrEntity.query(function(result) {
                vm.cbcrEntities = result;
            });
        }

        function search () {
            if (!vm.searchQuery) {
                return vm.loadAll();
            }
            CbcrEntitySearch.query({query: vm.searchQuery}, function(result) {
                vm.cbcrEntities = result;
            });
        }    }
})();
