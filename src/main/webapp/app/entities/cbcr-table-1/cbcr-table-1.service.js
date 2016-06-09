(function() {
    'use strict';
    angular
        .module('tieEntityApp')
        .factory('CbcrTable1', CbcrTable1);

    CbcrTable1.$inject = ['$resource'];

    function CbcrTable1 ($resource) {
        var resourceUrl =  'api/cbcr-table-1-s/:id';

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
