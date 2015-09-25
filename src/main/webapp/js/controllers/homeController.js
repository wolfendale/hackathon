fmt.controller('homeController', function($scope, $log, $http, $location, utilityService, dataService) {
    function wordCloudItem(text, size, sentiment){
        this.text = text;
        this.weight = size+5;
        this.html={
            class:sentiment + " word-cloud-item"
        };
        this.handlers = {
            click:function(item){       
                $log.log(text);
            }};
    }
    
    $scope.words = [];
    $scope.searchQuery = "";
    
    $scope.showLoading = true;
    
    utilityService.makeGetRequest("/sentiment", function(response){
        angular.forEach(response.data.sortedTopicData, function(item){
            var sentiment;
            if(item.averageScore < -.5 ){
                sentiment = "negative";
            }else if(item.averageScore > .3){
                sentiment = "positive";
            }else{
                sentiment = "neutral";
            }
            $scope.words.push(new wordCloudItem(item.topic, item.count, sentiment));
            $scope.showLoading = false;
        });
    });
    
    $scope.shape = "rectangular";
    
    $scope.fontSize = {from: 0.4, to: 1};
    
    $scope.doSearchFn = function(searchQuery){
        dataService.setEntity(searchQuery);
        $location.path('pages/entityDetails.html');
    }
    
    function isEmpty(input) {
        if (typeof input == "undefined" || input.trim().length == 0) {
            return true;
        }
        return false;
    }

    $scope.alerts = {
        createProject: [
            /*{ type: 'danger', msg: 'Alert message' },
            { type: 'warning', msg: 'Warning message' },
            { type: 'success', msg: 'Success message' }*/
        ]
    };
});