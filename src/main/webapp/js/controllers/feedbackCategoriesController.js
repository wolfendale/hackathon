fmt.controller('feedbackCategoriesController', function($scope, $log) {

    $scope.message = "test";

    $scope.doPageRedirect = function(url) {
        location.href = url;
    };

    function isEmpty(input) {
        if (typeof input == "undefined" || input == null || input.trim().length === 0) {
            return true;
        }
        return false;
    }

});
