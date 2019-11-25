app.controller('JobPageCtrl',function($scope,JobPageService,$location,$routeParams){
	
	var id=$routeParams.id
	
	function getJob(id){
		JobPageService.getJob(id).then(function(response){
			$scope.job=response.data
		},function(response){
			$scope.error=response.data
		})
	}
	
	
	
	getJob(id)
})