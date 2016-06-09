'use strict';

describe('Controller Tests', function() {

    describe('CbcrTable1 Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockCbcrTable1, MockTieDoc;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockCbcrTable1 = jasmine.createSpy('MockCbcrTable1');
            MockTieDoc = jasmine.createSpy('MockTieDoc');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'CbcrTable1': MockCbcrTable1,
                'TieDoc': MockTieDoc
            };
            createController = function() {
                $injector.get('$controller')("CbcrTable1DetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'tieEntityApp:cbcrTable1Update';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
