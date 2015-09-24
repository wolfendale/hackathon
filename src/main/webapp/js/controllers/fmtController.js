fmt.controller('fmtController', function($scope, $log, $http) {
    
    function isEmpty(input) {
        if (typeof input == "undefined" || input.trim().length == 0) {
            return true;
        }
        return false;
    }

    $scope.doPageRedirect = function(url) {
        location.href = url;
    };

    $scope.alerts = {
        createProject: [
            /*{ type: 'danger', msg: 'Alert message' },
            { type: 'warning', msg: 'Warning message' },
            { type: 'success', msg: 'Success message' }*/
        ]
    };
});