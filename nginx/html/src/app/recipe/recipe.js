angular.module( 'DietPlanner.recipe', [
  'ui.router',
  'ui.router.state',
  'ngResource',
  'elasticsearch'
])

/**
 * Each section or module of the site can also have its own routes. AngularJS
 * will handle ensuring they are all available at run-time, but splitting it
 * this way makes each module more "self-contained".
 */
.config(function config( $stateProvider ) {
  $stateProvider.state( 'recipe', {
    url: '/recipe',
    views: {
      "main": {
        controller: 'RecipeCtrl',
        templateUrl: 'recipe/recipe.tpl.html'
      }
    },
    data:{ pageTitle: 'Recipe' }
  });
})


.service('esService', function (esFactory) {
  var service = {};

  service.search = function(queryString, callback) {
    var service = esFactory({
      host: window.location.protocol + '//' + window.location.host + '/search',
      sniffOnStart: true,
      sniffInterval: 300000
    });

    var query = {
        "q": queryString,
        "index": "ingredients"
    };
    service.search(query).then(callback);
  };

  return service;
})

/**
 * And of course we define a controller for our route.
 */
.controller( 'RecipeCtrl', ['$scope', 'esService', function RecipeController( $scope, esService ) {
  $scope.model = {
    searchInput: '',
    ingredients: [],
    hits: []
  };

  $scope.search = function() {
    $scope.model.hits = [];
    esService.search($scope.model.searchInput, function(response) {
      angular.forEach(response.hits.hits, function(ingredient) {
        var source = ingredient._source;
        angular.extend(source, {amount: 0, cals: function() {return (this.amount * this.calories / 100);}});
        $scope.model.hits.push(source);
      });
    });
  };
  $scope.add = function(ingredient) {
    console.log(ingredient);
    $scope.model.ingredients.push(ingredient);
  };

  $scope.totalCalories = function() {
    return $scope.model.ingredients.reduce(function(sum, ingredient) {
      return sum += ingredient.cals();
    }, 0);
  };
}])

;
