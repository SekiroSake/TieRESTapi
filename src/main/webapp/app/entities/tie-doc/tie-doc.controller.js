(function() {
    'use strict';

    angular
        .module('tieEntityApp')
        .controller('TieDocController', TieDocController);

    TieDocController.$inject = ['$scope', '$state', 'TieDoc', 'TieDocSearch'];

    function TieDocController ($scope, $state, TieDoc, TieDocSearch) {
        var vm = this;
        
        vm.tieDocs = [];
        vm.search = search;

        loadAll();

        function loadAll() {
            TieDoc.query(function(result) {
                vm.tieDocs = result;
            });
        }

        function search () {
            if (!vm.searchQuery) {
                return vm.loadAll();
            }
            TieDocSearch.query({query: vm.searchQuery}, function(result) {
                vm.tieDocs = result;
            });
        }    }
})();
