(function() {
    'use strict';

    angular
        .module('tieEntityApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('cbcr-table-2', {
            parent: 'entity',
            url: '/cbcr-table-2',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'tieEntityApp.cbcrTable2.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/cbcr-table-2/cbcr-table-2-s.html',
                    controller: 'CbcrTable2Controller',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('cbcrTable2');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('cbcr-table-2-detail', {
            parent: 'entity',
            url: '/cbcr-table-2/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'tieEntityApp.cbcrTable2.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/cbcr-table-2/cbcr-table-2-detail.html',
                    controller: 'CbcrTable2DetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('cbcrTable2');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'CbcrTable2', function($stateParams, CbcrTable2) {
                    return CbcrTable2.get({id : $stateParams.id}).$promise;
                }]
            }
        })
        .state('cbcr-table-2.new', {
            parent: 'cbcr-table-2',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/cbcr-table-2/cbcr-table-2-dialog.html',
                    controller: 'CbcrTable2DialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                entityCode: null,
                                taxJurisdiction: null,
                                taxJurisOfIncorporation: null,
                                mainBusRAndD: null,
                                mainBusHoldingIp: null,
                                mainBusPurchasing: null,
                                mainBusSalesMktDistr: null,
                                mainBusAdminMgmtSupportSvc: null,
                                mainBusProvSvcToUnrelatedParties: null,
                                mainBusInternalGroupFinance: null,
                                mainBusRegulatedFinSvc: null,
                                mainBusInsurance: null,
                                mainBusHoldingEquityInstruments: null,
                                mainBusDormant: null,
                                mainBusOther: null,
                                mainBusOtherNotes: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('cbcr-table-2', null, { reload: true });
                }, function() {
                    $state.go('cbcr-table-2');
                });
            }]
        })
        .state('cbcr-table-2.edit', {
            parent: 'cbcr-table-2',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/cbcr-table-2/cbcr-table-2-dialog.html',
                    controller: 'CbcrTable2DialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['CbcrTable2', function(CbcrTable2) {
                            return CbcrTable2.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('cbcr-table-2', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('cbcr-table-2.delete', {
            parent: 'cbcr-table-2',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/cbcr-table-2/cbcr-table-2-delete-dialog.html',
                    controller: 'CbcrTable2DeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['CbcrTable2', function(CbcrTable2) {
                            return CbcrTable2.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('cbcr-table-2', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
