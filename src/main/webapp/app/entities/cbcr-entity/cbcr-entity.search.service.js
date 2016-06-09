(function() {
    'use strict';

    angular
        .module('tieEntityApp')
        .factory('CbcrEntitySearch', CbcrEntitySearch);

    CbcrEntitySearch.$inject = ['$resource'];

    function CbcrEntitySearch($resource) {
        var resourceUrl =  'api/_search/cbcr-entities/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
