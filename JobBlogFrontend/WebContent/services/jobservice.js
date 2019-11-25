app.factory('JobService',function($http){
	var jobservice={}
	var BASE_URL="http://localhost:8080/JobBlogFrontend"
		jobservice.registration=function(job){
		var url=BASE_URL+"/addJob"
		return $http.post(url,job)
	}
	
	jobservice.getAllJobs=function(){
		return $http.get(BASE_URL+"/getAllJobs")
	}
	
	jobservice.deleteJob=function(id){
		return $http['delete'](BASE_URL+"/deleteJob/"+id)
	}
	
	jobservice.updateJobForm=function(id){
		console.log(id)
		return $http.get(BASE_URL+"/updateJobForm/"+id)
	}
	
	jobservice.updateJob=function(job){
		return $http.put(BASE_URL+"/updatejob",job)
	}
	
	return jobservice;
})