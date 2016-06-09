(function() {
    'use strict';

    angular
        .module('tieEntityApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('tie-doc', {
            parent: 'entity',
            url: '/tie-doc',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'tieEntityApp.tieDoc.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/tie-doc/tie-docs.html',
                    controller: 'TieDocController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('tieDoc');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('tie-doc-detail', {
            parent: 'entity',
            url: '/tie-doc/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'tieEntityApp.tieDoc.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/tie-doc/tie-doc-detail.html',
                    controller: 'TieDocDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('tieDoc');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'TieDoc', function($stateParams, TieDoc) {
                    return TieDoc.get({id : $stateParams.id}).$promise;
                }]
            }
        })
        .state('tie-doc.new', {
            parent: 'tie-doc',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tie-doc/tie-doc-dialog.html',
                    controller: 'TieDocDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                name: null,
                                code: null,
                                description: null,
                                reportingEntityCode: null,
                                currencyCode: null,
                                resCountryCode: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('tie-doc', null, { reload: true });
                }, function() {
                    $state.go('tie-doc');
                });
            }]
        })
        .state('tie-doc.edit', {
            parent: 'tie-doc',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tie-doc/tie-doc-dialog.html',
                    controller: 'TieDocDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['TieDoc', function(TieDoc) {
                            return TieDoc.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('tie-doc', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('tie-doc.delete', {
            parent: 'tie-doc',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tie-doc/tie-doc-delete-dialog.html',
                    controller: 'TieDocDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['TieDoc', function(TieDoc) {
                            return TieDoc.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('tie-doc', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
