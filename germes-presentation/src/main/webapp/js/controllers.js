app.controller('CityCtrl', [ '$scope', 'cityService', function($scope, cityService) {
	$scope.rowsPerPage = 10;
	$scope.isRegionCenter = function(city) {
		if (city.district) {
			return false;
		}
		return true;
	}
	$scope.cities = cityService.query();
  } 
]);

app.controller('TranslateCtrl', ['$translate', '$scope', function($translate, $scope) {
	$scope.changeLanguage = function(lang) {
		$translate.use(lang);
	}
}]);