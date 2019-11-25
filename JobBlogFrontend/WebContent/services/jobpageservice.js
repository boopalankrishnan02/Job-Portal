app.factory('JobPageService',function($http){
	
	var jobpageservice={}
	
	var BASE_URL="http://localhost:8080/JobBlogFrontend"
	
		jobpageservice.getJob = function(id){
		return $http.get(BASE_URL+"/getJob/"+id)
	}
	return jobpageservice;
})