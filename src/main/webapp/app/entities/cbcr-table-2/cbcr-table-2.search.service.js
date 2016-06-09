(function() {
    'use strict';

    angular
        .module('tieEntityApp')
        .factory('CbcrTable2Search', CbcrTable2Search);

    CbcrTable2Search.$inject = ['$resource'];

    function CbcrTable2Search($resource) {
        var resourceUrl =  'api/_search/cbcr-table-2-s/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
