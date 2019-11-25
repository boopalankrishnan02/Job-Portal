app.factory('BlogPostService',function($http){
	
	var blogpostservice={}
	var BASE_URL="http://localhost:8080/JobBlogFrontend"
	
	blogpostservice.addBlogPost=function(blog){
		var url=BASE_URL+"/addBlogPost"
		return $http.post(url,blog)
	}
	
	blogpostservice.getAllBlogs=function(){
		var url=BASE_URL+"/approvedBlogs"
		return $http.get(url)
	}
	
	blogpostservice.getBlogsWaitingApproval=function(){
		return $http.get(BASE_URL+"/blogsWaitingApproval")
	}
	
	blogpostservice.approveBlogPost=function(blogPost){
		return $http.put(BASE_URL+"/approveBlogPost",blogPost)
	}
	
	blogpostservice.getBlog=function(id){
		var url=BASE_URL+"/getBlog/"+id;
		return $http.get(url)
	}
	
	blogpostservice.rejectBlog = function(blogPost){
		var url=BASE_URL+"/rejectBlogPost"
		return $http.put(url,blogPost)
	}
	
	blogpostservice.like = function(blogPost){
		var url=BASE_URL+"/like"
		return $http.put(url,blogPost)
	}
	
	blogpostservice.dislike = function(blogPost){
		var url=BASE_URL+"/dislike"
		return $http.put(url,blogPost)
	}
	
	blogpostservice.getComments = function(id){
		var url = BASE_URL+"/getComments/"+id
		return $http.get(url)
	}
	
	blogpostservice.addComment = function(blogPost,commentTxt){
		var url = BASE_URL+"/addComment?commentTxt="+commentTxt
		return $http.post(url,blogPost)
	}
	
	blogpostservice.deleteComment = function(blogComment){
		var url=BASE_URL+"/deleteComment"
		return $http.put(url,blogComment)
	}
	
	blogpostservice.updateBlogPost = function(blogPost){
		var url=BASE_URL+"/updateBlogPost"
		return $http.put(url,blogPost)
	}
	
	return blogpostservice;
})