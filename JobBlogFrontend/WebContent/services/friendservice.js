app.factory('FriendService',function($http){
	
	var friendservice = {}
	var BASE_URL = "http://localhost:8080/JobBlogFrontend"
		
	friendservice.getSuggestedUsers = function(){
		console.log('in friend service')
		return $http.get(BASE_URL+'/suggestedUsers')
	}
	
	friendservice.sendRequest = function(toId){
		return $http.post(BASE_URL+"/friendRequest",toId)
	}
	
	friendservice.getPendingRequest = function(){
		return $http.get(BASE_URL+"/pendingRequest")
	}
	
	friendservice.acceptRequest = function(pendingRequest){
		var url = BASE_URL+"/acceptRequest"
		return $http.put(url,pendingRequest)
	}
	
	friendservice.deleteRequest = function(pendingRequest){
		var url = BASE_URL+"/deleteRequest"
		return $http.put(url,pendingRequest)
	}
	
	friendservice.listOfFriends = function(){
		var url=BASE_URL+"/listOfFriends"
		return $http.get(url)
	}
	
	friendservice.deleteFriend = function(id){
		var url=BASE_URL+"/deleteFriend/"+id
		return $http['delete'](url)
	}
	return friendservice
})