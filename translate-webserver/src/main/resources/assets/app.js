(function() {
  var app = angular.module('translate', []);

  app.controller('TranslateController', ['$http', '$rootScope', function($http, $rootScope) {
    var self = this;
    this.outputs = [];
    $rootScope.$watch(function() {
      return self.source;
    }, function(source) {
      $http.post('api/translate', source).success(function(outputs) {
        self.outputs = outputs;
      });
    });
  }]);
})();
