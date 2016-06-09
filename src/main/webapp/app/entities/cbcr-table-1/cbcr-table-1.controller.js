(function() {
    'use strict';

    angular
        .module('tieEntityApp')
        .controller('CbcrTable1Controller', CbcrTable1Controller);

    CbcrTable1Controller.$inject = ['$scope', '$state', 'CbcrTable1', 'CbcrTable1Search'];

    function CbcrTable1Controller ($scope, $state, CbcrTable1, CbcrTable1Search) {
        var vm = this;
        
        vm.cbcrTable1S = [];
        vm.search = search;

        loadAll();

        function loadAll() {
            CbcrTable1.query(function(result) {
                vm.cbcrTable1S = result;
            });
        }

        function search () {
            if (!vm.searchQuery) {
                return vm.loadAll();
            }
            CbcrTable1Search.query({query: vm.searchQuery}, function(result) {
                vm.cbcrTable1S = result;
            });
        }    }
})();
