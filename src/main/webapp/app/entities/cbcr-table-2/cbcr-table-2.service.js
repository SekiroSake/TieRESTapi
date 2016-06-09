(function() {
    'use strict';
    angular
        .module('tieEntityApp')
        .factory('CbcrTable2', CbcrTable2);

    CbcrTable2.$inject = ['$resource'];

    function CbcrTable2 ($resource) {
        var resourceUrl =  'api/cbcr-table-2-s/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
