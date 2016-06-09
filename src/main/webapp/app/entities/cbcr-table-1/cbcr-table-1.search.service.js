(function() {
    'use strict';

    angular
        .module('tieEntityApp')
        .factory('CbcrTable1Search', CbcrTable1Search);

    CbcrTable1Search.$inject = ['$resource'];

    function CbcrTable1Search($resource) {
        var resourceUrl =  'api/_search/cbcr-table-1-s/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
