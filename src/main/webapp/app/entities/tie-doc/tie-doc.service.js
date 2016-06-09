(function() {
    'use strict';
    angular
        .module('tieEntityApp')
        .factory('TieDoc', TieDoc);

    TieDoc.$inject = ['$resource'];

    function TieDoc ($resource) {
        var resourceUrl =  'api/tie-docs/:id';

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
