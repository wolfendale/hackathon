testAutomationTool.service('utilityService', function($log, $http, $q,) {
    /**
     * this function makes a get request to the url with the given parameter
     */
    this.makeGetRequest = function(endpoint, handleSuccess) {
        var request = $http.get(endpoint);
        request.then(handleSuccess, handleError);
    };

    /**
     * this function makes a post request to the url with the given parameter
     */
    this.makePostRequest = function(endpoint, requestBody, handleSuccess) {
        var url = MongoConst.CONTEXT_ROOT + MongoConst.SERVLET_MAPPING + endpoint;
        var request = $http.post(url, requestBody);
        request.then(handleSuccess, handleError);
    };

    /**
     * this function will log the error to the console
     */
    function handleError(response) {
        if (angular.isObject(response) && angular.isObject(response.data) && response.data.message) {
            $log.error(response.data.message);
        } else {
            $log.error(GenConst.UNKNOWN_ERROR);
        }
    }
});
