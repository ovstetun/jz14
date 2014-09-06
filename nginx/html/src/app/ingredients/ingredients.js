angular.module( 'ngBoilerplate.ingredients', [
  'ui.router',
  'ui.router.state',
  'ngResource'
])

/**
 * Each section or module of the site can also have its own routes. AngularJS
 * will handle ensuring they are all available at run-time, but splitting it
 * this way makes each module more "self-contained".
 */
.config(function config( $stateProvider ) {
  $stateProvider.state( 'ingredients', {
    url: '/ingredients',
    views: {
      "main": {
        controller: 'IngredientsCtrl',
        templateUrl: 'ingredients/ingredients.tpl.html'
      }
    },
    data:{ pageTitle: 'Ingredients' }
  });
  $stateProvider.state( 'ingredients/new', {
    url: '/ingredients/new',
    views: {
      "main": {
        controller: 'NewIngredientCtrl',
        templateUrl: 'ingredients/new.tpl.html'
      }
    },
    data:{ pageTitle: 'New Ingredient' }
  });
  $stateProvider.state( 'ingredients/edit', {
    url: '/ingredients/:id',
    views: {
      "main": {
        controller: 'EditIngredientCtrl',
        templateUrl: 'ingredients/edit.tpl.html'
      }
    },
    data:{ pageTitle: 'Edit ingredient' }
  });
})

.factory('Ingredients', ['$resource',
  function($resource){
    return $resource('/api/ingredients/:id', {id: '@id'}, {
      query: {method:'GET', params:{id:''}, isArray:true},
      create: {method:'POST', params:{id:''}}
    });
  }])

/**
 * And of course we define a controller for our route.
 */
.controller( 'IngredientsCtrl', ['$scope', 'Ingredients', function IngredientsController( $scope, Ingredients ) {
  $scope.ingredients = Ingredients.query();
}])

.controller( 'NewIngredientCtrl', ['$scope', '$state', 'Ingredients', function NewIngredientCtrl($scope, $state, Ingredients) {
  $scope.ingredient = {"name": "", "calories": 0};
  $scope.save = function() {
    Ingredients.create($scope.ingredient).$promise.then(function () {
      $state.go('ingredients', {}, {reload: true});
    });
  };
}])

.controller( 'EditIngredientCtrl', ['$scope', '$stateParams', 'Ingredients', function EditIngredientCtrl( $scope, $stateParams, Ingredients ) {
  Ingredients.get({id: $stateParams.id}, function(ingredient){
    $scope.ingredient = ingredient;
  });
}])

;
