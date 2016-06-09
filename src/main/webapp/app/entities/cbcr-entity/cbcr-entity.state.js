(function() {
    'use strict';

    angular
        .module('tieEntityApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('cbcr-entity', {
            parent: 'entity',
            url: '/cbcr-entity',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'tieEntityApp.cbcrEntity.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/cbcr-entity/cbcr-entities.html',
                    controller: 'CbcrEntityController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('cbcrEntity');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('cbcr-entity-detail', {
            parent: 'entity',
            url: '/cbcr-entity/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'tieEntityApp.cbcrEntity.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/cbcr-entity/cbcr-entity-detail.html',
                    controller: 'CbcrEntityDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('cbcrEntity');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'CbcrEntity', function($stateParams, CbcrEntity) {
                    return CbcrEntity.get({id : $stateParams.id}).$promise;
                }]
            }
        })
        .state('cbcr-entity.new', {
            parent: 'cbcr-entity',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/cbcr-entity/cbcr-entity-dialog.html',
                    controller: 'CbcrEntityDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                name: null,
                                entityCode: null,
                                description: null,
                                taxIdNum: null,
                                incorpCountryCode: null,
                                otherEntityInfo: null,
                                tidNum: null,
                                isPermanentEstablishment: null,
                                addrLeagalType: null,
                                addrFreeText: null,
                                addrStreet: null,
                                addrBuildingIdentifier: null,
                                addSuiteIdentifier: null,
                                addrFloorIdentifier: null,
                                addrPOB: null,
                                addrPostCode: null,
                                addrCity: null,
                                addrCountrySubentity: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('cbcr-entity', null, { reload: true });
                }, function() {
                    $state.go('cbcr-entity');
                });
            }]
        })
        .state('cbcr-entity.edit', {
            parent: 'cbcr-entity',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/cbcr-entity/cbcr-entity-dialog.html',
                    controller: 'CbcrEntityDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['CbcrEntity', function(CbcrEntity) {
                            return CbcrEntity.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('cbcr-entity', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('cbcr-entity.delete', {
            parent: 'cbcr-entity',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/cbcr-entity/cbcr-entity-delete-dialog.html',
                    controller: 'CbcrEntityDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['CbcrEntity', function(CbcrEntity) {
                            return CbcrEntity.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('cbcr-entity', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
