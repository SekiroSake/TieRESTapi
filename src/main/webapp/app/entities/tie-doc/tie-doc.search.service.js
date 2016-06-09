(function() {
    'use strict';

    angular
        .module('tieEntityApp')
        .factory('TieDocSearch', TieDocSearch);

    TieDocSearch.$inject = ['$resource'];

    function TieDocSearch($resource) {
        var resourceUrl =  'api/_search/tie-docs/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
