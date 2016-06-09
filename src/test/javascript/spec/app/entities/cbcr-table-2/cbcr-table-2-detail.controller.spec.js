'use strict';

describe('Controller Tests', function() {

    describe('CbcrTable2 Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockCbcrTable2, MockTieDoc;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockCbcrTable2 = jasmine.createSpy('MockCbcrTable2');
            MockTieDoc = jasmine.createSpy('MockTieDoc');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'CbcrTable2': MockCbcrTable2,
                'TieDoc': MockTieDoc
            };
            createController = function() {
                $injector.get('$controller')("CbcrTable2DetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'tieEntityApp:cbcrTable2Update';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
