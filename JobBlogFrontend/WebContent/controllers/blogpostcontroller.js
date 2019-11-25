app.controller('BlogPostCtrl',function($scope,BlogPostService,$location,$rootScope,$routeParams,$sce){
	var id = $routeParams.id
	
	$scope.addBlogPost=function(blog){
		BlogPostService.addBlogPost(blog).then(function(response){
			getAllBlogs()
			swal("Article submited successfully....your article will be posted in the community blog after being approved by our team")
			$location.path('/')
		},function(response){
			if(response.staus==401)
				$location.path('/login')
			$scope.error=response.data
		})
	}
	
	
	function getAllBlogs(){
		BlogPostService.getAllBlogs().then(function(response){
			console.log("approved blogs"+response.data)
			console.log("Getting the data")
			$scope.blogsApproved=response.data
		},function(response){
			if(response.status==401)
				$location.path('/login')
			$scope.error=response.data	
		})
	}
	
	function getBlogsWaitingApproval(){
		BlogPostService.getBlogsWaitingApproval().then(function(response){
			$scope.blogsWaitingApproval=response.data;
		},function(response){
			if(response.status==401)
				$location.path('/login')
		})
	}
	
	$scope.approveBlogPost = function(blogPost){
		BlogPostService.approveBlogPost(blogPost).then(function(response){
			$location.path('/blogsWaitingApproval')
		},function(response){
			if(response.status==401)
				$location.path('/login')
			$scope.error=response.data
		})
	}
	
	$scope.rejectBlog = function(blogPost){
		BlogPostService.rejectBlog(blogPost).then(function(response){
			console.log(blogPost)
			$location.path('/blogsWaitingApproval')
		},function(response){
			if(response.stauts==401)
				$location.path('/login')
			$scope.error=response.data
		})
	}
	
	$scope.deleteBlog = function(blogPost){
		BlogPostService.rejectBlog(blogPost).then(function(response){
			getAllBlogs()
		},function(response){
			if(response.status==401)
				$location.path('/login')
			$scope.error=response.data
		})
	}
	
	getAllBlogs()
	
	if($rootScope.user.u_role=='ADMIN')
		getBlogsWaitingApproval()
	
})