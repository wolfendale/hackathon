fmt.controller('homeController', function($scope, $log, $http) {
    function wordCloudItem(text, size, sentiment){
        this.text = text;
        this.weight = size;
        this.html={
            class:sentiment + " word-cloud-item"
        };
        handlers = {click:$scope.wordClickAction};
    }
    
    $scope.words = [
      new wordCloudItem("Lorem", 3,"negative"),
      {text: "Ipsum", weight: 2},
      {text: "Dolor", weight: 1},
      {text: "Sit", weight: 2},
      {text: "Amet", weight: 3},
      {text: "Consectetur", weight: 3},
      {text: "Adipiscing", weight: 3},
      {text: "Elit", weight: 2},
      {text: "Nam et", weight: 1},
      {text: "Leo", weight: 3},
      {text: "Sapien", weight: 2},
      {text: "Pellentesque", weight: 3},
      {text: "habitant", weight: 3},
      {text: "morbi", weight: 3},
      {text: "tristisque", weight: 3},
      {text: "senectus", weight: 3},
      {text: "et netus", weight: 3},
      {text: "et malesuada", weight: 3},
      {text: "fames", weight: 2},
      {text: "ac turpis", weight: 2},
      {text: "egestas", weight: 2},
      {text: "Aenean", weight: 2},
      {text: "vestibulum", weight: 2},
      {text: "elit", weight: 2},
      {text: "sit amet", weight: 2},
      {text: "metus", weight: 2},
      {text: "adipiscing", weight: 2},
      {text: "ut ultrices", weight: 2}
    ];
    
    $scope.shape = "rectangular";
    
    $scope.wordClickAction = function(){
        alert('here');
    };
    
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