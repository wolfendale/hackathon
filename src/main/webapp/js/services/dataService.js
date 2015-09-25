fmt.service('dataService', function($log, $http, $q) {

    this.setEntity = function(entity) {
        this.entity = entity;
    };

    this.getEntity = function(entity) {
        return this.entity;
    };

});
