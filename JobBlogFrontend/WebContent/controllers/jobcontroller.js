app.controller('JobCtrl',function($scope,JobService,$location,$routeParams){
//	alert("entering job cotroller")
	var id=$routeParams.id
	
	$scope.myFilter={};
	
	$scope.registration=function(job){
		JobService.registration(job).then(
				function(response){
					console.log(job)
					swal("Job posted successfully...")
					$location.path('/')
				},function(response){
					$scope.error=response.data
				})	
				
	}
	
	function getAllJobs(){
		JobService.getAllJobs().then(function(response){
			$scope.jobs=response.data
		},function(response){
			if(response.status==401)
				$location.path('/login')
		})
	}
	
	$scope.deleteJob = function(id) {
		//alert("entering delete job fuction")
		JobService.deleteJob(id).then(function(response) {
			//alert("delete job service")
			swal("Job Deleted from Project Job Successfully")
			getAllJobs()
		}, function(response) {
			if (Response.status == 401)
				$location.path('/login')
			$scope.error = Response.data
		})
	}
	
	if ($routeParams.id != undefined){	//??
		console.log('Job based on ID ' + id)
		JobService.updateJobForm(id).then(function(response) {
		$scope.job = response.data
		console.log($scope.job)
		console.log(response.data)				
	}, function(response) {
		if (response.status == 401)
			$location.path('/login')
		$scope.error = response.data
	})
	}
	$scope.updateJob = function(job) {
		JobService.updateJob(job).then(function(response) {
			swal("Job Updated Successfully....")
			$location.path('/getAllJobs')
		}, function(response) {
			if (response.status == 401)
				$location.path('/login')
			$scope.error = response.data
		})
	}
	
	$scope.filter=function(category){
		$scope.myFilter=category
	}
	
	getAllJobs()
})