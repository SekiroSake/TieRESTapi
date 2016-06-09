(function() {
    'use strict';
    angular
        .module('tieEntityApp')
        .factory('CbcrEntity', CbcrEntity);

    CbcrEntity.$inject = ['$resource'];

    function CbcrEntity ($resource) {
        var resourceUrl =  'api/cbcr-entities/:id';

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
