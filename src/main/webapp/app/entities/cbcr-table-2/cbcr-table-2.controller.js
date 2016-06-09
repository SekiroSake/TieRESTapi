(function() {
    'use strict';

    angular
        .module('tieEntityApp')
        .controller('CbcrTable2Controller', CbcrTable2Controller);

    CbcrTable2Controller.$inject = ['$scope', '$state', 'CbcrTable2', 'CbcrTable2Search'];

    function CbcrTable2Controller ($scope, $state, CbcrTable2, CbcrTable2Search) {
        var vm = this;
        
        vm.cbcrTable2S = [];
        vm.search = search;

        loadAll();

        function loadAll() {
            CbcrTable2.query(function(result) {
                vm.cbcrTable2S = result;
            });
        }

        function search () {
            if (!vm.searchQuery) {
                return vm.loadAll();
            }
            CbcrTable2Search.query({query: vm.searchQuery}, function(result) {
                vm.cbcrTable2S = result;
            });
        }    }
})();
