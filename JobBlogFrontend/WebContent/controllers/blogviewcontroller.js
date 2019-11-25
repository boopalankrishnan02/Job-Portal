app.controller('BlogViewCtrl',function($scope,BlogPostService,$sce,$location,$routeParams){
	
	var id=$routeParams.id
	
	
	BlogPostService.getBlog(id).then(function(response){
		$scope.blogPost=response.data
		console.log(response.data)
		$scope.content=$sce.trustAsHtml($scope.blogPost.blogContent)
		},function(response){
			if(response.status==401)
				$location.path('/login')
			$scope.error=response.data
		})
	
		$scope.like = function(blogPost){
		BlogPostService.like(blogPost).then(function(response){
			$location.path('/getAllBlogs')
		},function(response){
			if(response.status==401)
				$location.path('/login')
			$scope.error=response.data
		})
		
	}
	
	
	$scope.updateBlogPost = function(blogPost){
		BlogPostService.updateBlogPost(blogPost).then(function(response){
			swal('Article submited successfully....will be posted in the community blog after being approved by our team')
			$location.path('/getAllBlogs')
		},function(response){
			if(response.status==401)
				$location.path('/login')
			$scope.error=response.data
		})
	}
	
		$scope.dislike = function(blogPost){
			BlogPostService.dislike(blogPost).then(function(response){
				$location.path('/getAllBlogs')
			},function(response){
				if(response.status==401)
					$location.path('/login')
				$scope.error = response.data
			})
		
	}
		
		function getComments(id){
			console.log(id)
			BlogPostService.getComments(id).then(function(response){
				$scope.blogComments = response.data
			},function(response){
				if(response.status==401)
					$location.path('/login')
			})
		}
		
		$scope.getComments = function(id){
			console.log(id)
			BlogPostService.getComments(id).then(function(response){
				$scope.blogComments = response.data
			},function(response){
				if(response.status==401)
					$location.path('/login')
			})
		}
		
		$scope.addComment = function(blogPost,commentTxt){
			if(commentTxt==undefined || commentTxt=="")
				$scope.error='please enter some txt..'
			
			else
			BlogPostService.addComment(blogPost,commentTxt).then(function(response){
				$scope.commentTxt=""
				$scope.blogComment = response.data
				getComments(id);
			},function(response){
				if(response.status==401)
					$location.path('/login')
			})
		
		}
		
		$scope.deleteComment = function(blogComment){
			BlogPostService.deleteComment(blogComment).then(function(response){
				console.log(blogComment)
				$scope.blogComment=undefined
				getComments(blogComment.blogPost.b_id)
			},function(response){
				if(response.status==401)
					$location.path('/login')
				$scope.error = response.data
			})
		}
})