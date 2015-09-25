fmt.controller('fmtController', function($scope, $log, $http, $location, dataService, ApplicationState) {
    
    function isEmpty(input) {
        if (typeof input == "undefined" || input.trim().length == 0) {
            return true;
        }
        return false;
    }
    
    $scope.ApplicationState = ApplicationState;

    $scope.doPageRedirect = function(url) {
        location.href = url;
    };
    
    $scope.doSearchFn = function(searchQuery){
        dataService.setEntity(searchQuery);
        //$location.path('pages/entityDetails.html');
        $scope.$broadcast('search', searchQuery);
    }

    $scope.alerts = {
        createProject: [
            /*{ type: 'danger', msg: 'Alert message' },
            { type: 'warning', msg: 'Warning message' },
            { type: 'success', msg: 'Success message' }*/
        ]
    };
});