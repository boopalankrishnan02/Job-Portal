app.controller('FriendCtrl',function($scope,FriendService,$location){
	
	
	
	function getSuggestedUsers(){
		FriendService.getSuggestedUsers().then(function(response){
			console.log('getSuggestedUsers')
			$scope.suggestedUsers = response.data
		},function(response){
			if(response.status==401)
				$location.path('/login')
		})
	}
	
	$scope.sendRequest = function(toId){
		FriendService.sendRequest(toId).then(function(response){
			swal("Friend request has been sent successfully....")
			getSuggestedUsers();
		},function(response){
			if(response.status==401)
				$location.path('/login')
		})
	}
	
	function getPendingRequest(){
		console.log('pendingRequests')
		FriendService.getPendingRequest().then(function(response){
			$scope.pendingRequests = response.data
			console.log('send pendingRequest data')
		},function(response){
			if(response.status==401)
				$location.path('/login')
		})
	}
	
	$scope.acceptRequest = function(pendingRequest){
		FriendService.acceptRequest(pendingRequest).then(function(response){
			getPendingRequest();
			getFriends();
		},function(response){
			if(response.status==401)
				$location.path('/login')
		})
	}
	
	$scope.deleteRequest = function(pendingRequest){
		FriendService.deleteRequest(pendingRequest).then(function(response){
			getPendingRequest()
		},function(response){
			if(response.status==401)
				$location.path('/login')
		})
	}
	
	function getFriends(){
	FriendService.listOfFriends().then(function(response){
		console.log('list of friends')
		console.log(response.data)
		$scope.friendDetails = response.data
	},function(response){
		if(response.status==401)
			$location.path('/login')
	})
	}
	
	$scope.deleteFriend = function(id){
		console.log('delete friend')
		console.log(id)
		FriendService.deleteFriend(id).then(function(response){
			getFriends()
		},function(response){
			if(response.status==401)
				$location.path('/login')
		})
	}
	
	getFriends()
	getPendingRequest()
	getSuggestedUsers();
})