var app = angular.module('translate', []);

function TranslateController($http, $scope) {
  $scope.$watch('source', function(source) {
    $http.post('api/translate', source).success(function(outputs) {
      $scope.outputs = outputs;
    });
  });
}

app.controller('TranslateController', TranslateController);
