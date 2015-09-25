fmt.controller('entityDetailsController', function($scope, $log, utilityService, dataService) {

    function wordCloudItem(text, size, color){
        this.text = text;
        this.weight = size;
        this.html={
            class:" word-cloud-item",
            style: "color:"+color
        };
        this.handlers = {
            click:function(item){       
                $log.log(text);
            }};
    }
    
    $scope.pieChart = {
        data : [],
        options: {thickness:100}
    };
    
    $scope.searchQuery = dataService.getEntity();

    $scope.words = [];
    
    $scope.showLoading = true;
    
    utilityService.makeGetRequest("/twitter-sentiment/"+$scope.searchQuery, function(response){
        $scope.showLoading = false;
        $scope.pieChart.data = [
              {label: "negative", value: 12.2, color: "red"}, 
              {label: "neutral", value: 45, color: "grey"},
              {label: "positive", value: 10, color: "green"}
            ];
        
        var maxCount = 0, minCount;
        
        angular.forEach(response.data.sortedTopicData, function(item){
            if(item.count != undefined && item.count > maxCount){
                maxCount = item.count;
            }
            if(minCount == undefined || item.count < minCount){
                minCount = item.count;
            }
        });
        
        maxCount = maxCount - minCount;
        minCount = 0;
             
        angular.forEach(response.data.sortedTopicData, function(item){
            var size = (item.count-minCount)/maxCount * 10;
            $scope.words.push(new wordCloudItem(item.topic, size, item.averageScoreColour));
        });
    });
    
    $scope.shape = "rectangular";
    
    $scope.fontSize = {from: 0.4, to: 1}; 

});
