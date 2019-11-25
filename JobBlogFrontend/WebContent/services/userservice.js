app.factory('UserService',function($http){
	var userservice={}
	var BASE_URL="http://localhost:8080/JobBlogFrontend"
		userservice.registration=function(user){
		var url=BASE_URL+"/register";
		return $http.post(url,user)
	}
	
	
	userservice.login=function(user){
		var url=BASE_URL+"/login";
		console.log("user service",url);
		return $http.put(url,user)
	}
	
	userservice.logout=function(){
		var url=BASE_URL+"/logout";
		return $http.put(url)
	}
	
	userservice.updateProfile=function(user){
		var url=BASE_URL+"/updateProfile";
		return $http.put(url,user)
	}
	return userservice;
})