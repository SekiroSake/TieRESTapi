'use strict';

describe('Controller Tests', function() {

    describe('TieDoc Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockTieDoc, MockCbcrEntity, MockCbcrTable1, MockCbcrTable2, MockCbcrTable3;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockTieDoc = jasmine.createSpy('MockTieDoc');
            MockCbcrEntity = jasmine.createSpy('MockCbcrEntity');
            MockCbcrTable1 = jasmine.createSpy('MockCbcrTable1');
            MockCbcrTable2 = jasmine.createSpy('MockCbcrTable2');
            MockCbcrTable3 = jasmine.createSpy('MockCbcrTable3');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'TieDoc': MockTieDoc,
                'CbcrEntity': MockCbcrEntity,
                'CbcrTable1': MockCbcrTable1,
                'CbcrTable2': MockCbcrTable2,
                'CbcrTable3': MockCbcrTable3
            };
            createController = function() {
                $injector.get('$controller')("TieDocDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'tieEntityApp:tieDocUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
