'use strict';

describe('Controller Tests', function() {

    describe('CbcrEntity Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockCbcrEntity, MockTieDoc;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockCbcrEntity = jasmine.createSpy('MockCbcrEntity');
            MockTieDoc = jasmine.createSpy('MockTieDoc');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'CbcrEntity': MockCbcrEntity,
                'TieDoc': MockTieDoc
            };
            createController = function() {
                $injector.get('$controller')("CbcrEntityDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'tieEntityApp:cbcrEntityUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
