app.controller('UserCtrl',function($scope,UserService,$location,$rootScope,$cookieStore){
	//alert('entering user controller js')
	$scope.registration = function(user){
		//alert('entering registration js method')
		UserService.registration(user).then(
				function(response){
					//console.log(user) 	
				swal('Registration successfull...login')
					$location.path('/login')
				},function(response){
					swal("Mobile number already resitered with us..enter another number")
					$scope.error=response.data
				})
	}
	
		$scope.login = function(user){
			//alert('entering login js method')
			UserService.login(user).then(function(response){
				swal("login successfull....")
				$cookieStore.put('UserDetails',response.data)
				$rootScope.user=response.data
				console.log("login success",$scope.user)
				$location.path('/')
			},function(response){
				swal("login error...mobile number or password invalid")
				console.log("login error")
				$scope.error=response.data
			})
		}
		
		$scope.updateProfile = function(user){
			UserService.updateProfile(user).then(function(response){
				$rootScope.user=response.data
				$cookieStore.put("UserDetails",response.data)
				swal("Profile Updates Successfully....")
				console.log(response.data)
				$location.path('/')
				
			},function(response){
				if(response.data==401)
					$location.path('/login')
				$scope.error=response.data
			})
		}
})