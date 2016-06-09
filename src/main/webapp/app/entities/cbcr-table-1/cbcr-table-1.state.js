(function() {
    'use strict';

    angular
        .module('tieEntityApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('cbcr-table-1', {
            parent: 'entity',
            url: '/cbcr-table-1',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'tieEntityApp.cbcrTable1.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/cbcr-table-1/cbcr-table-1-s.html',
                    controller: 'CbcrTable1Controller',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('cbcrTable1');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('cbcr-table-1-detail', {
            parent: 'entity',
            url: '/cbcr-table-1/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'tieEntityApp.cbcrTable1.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/cbcr-table-1/cbcr-table-1-detail.html',
                    controller: 'CbcrTable1DetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('cbcrTable1');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'CbcrTable1', function($stateParams, CbcrTable1) {
                    return CbcrTable1.get({id : $stateParams.id}).$promise;
                }]
            }
        })
        .state('cbcr-table-1.new', {
            parent: 'cbcr-table-1',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/cbcr-table-1/cbcr-table-1-dialog.html',
                    controller: 'CbcrTable1DialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                taxJurisdiction: null,
                                revenueUnrelatedParty: null,
                                revenueRelatedParty: null,
                                revenueTotal: null,
                                plBeforeIncomeTax: null,
                                incomeTaxPaid: null,
                                incomeTaxAccrued: null,
                                statedCapital: null,
                                accumulatedEarnings: null,
                                numberOfEmployees: null,
                                tangibleAssetsNonCash: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('cbcr-table-1', null, { reload: true });
                }, function() {
                    $state.go('cbcr-table-1');
                });
            }]
        })
        .state('cbcr-table-1.edit', {
            parent: 'cbcr-table-1',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/cbcr-table-1/cbcr-table-1-dialog.html',
                    controller: 'CbcrTable1DialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['CbcrTable1', function(CbcrTable1) {
                            return CbcrTable1.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('cbcr-table-1', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('cbcr-table-1.delete', {
            parent: 'cbcr-table-1',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/cbcr-table-1/cbcr-table-1-delete-dialog.html',
                    controller: 'CbcrTable1DeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['CbcrTable1', function(CbcrTable1) {
                            return CbcrTable1.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('cbcr-table-1', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
