(function() {
	var dashboardModule = angular.module('dashboard', []);
	
	dashboardModule.controller('UserCrtl', ['$scope', function($scope) {
		$scope.name = 'Guersel';
	}]);
	
	dashboardModule.directive('sortableTable', function() {
		return {
			restrict: 'E',
			templateUrl: '/components/sortableTable'
		};
	});
	
		
})();

