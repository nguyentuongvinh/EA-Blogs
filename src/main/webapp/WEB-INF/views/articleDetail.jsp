<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.text.SimpleDateFormat" %>  
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml11.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome to EA Blogs</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Bootstrap Core CSS -->
	<link rel="stylesheet" href="../../resources/css/bootstrap.min.css" />
	
    <!-- Custom CSS -->
    <link rel="stylesheet" href="../../resources/css/clean-blog.min.css" />

    <!-- Custom Fonts -->
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href='http://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>
	    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-custom navbar-fixed-top">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header page-scroll">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="${pageContext.request.contextPath}">Welcome to Programming Blog</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a href="${pageContext.request.contextPath}">Home</a>
                    </li>
					<c:forEach items="${categories}" var="element"> 
					  <li>
					  	<a href="<c:url value="/article/category/${element.id}/"/>">${element.name}</a>
					  </li>
					</c:forEach>

                    <sec:authorize access="isAnonymous()">
						<li>
	                        <a href="${pageContext.request.contextPath}/loginRequest">Login</a>
	                    </li>
					</sec:authorize>
					<sec:authorize access="isAuthenticated()">
						<sec:authentication property="principal" var="authentication"/>
						<li>
					    	<a href="${pageContext.request.contextPath}/logoutRequest">Logout (${authentication.username})</a>
					    </li>
					</sec:authorize>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

    <!-- Page Header -->
    <!-- Set your background image for this header on the line below. -->
    <header class="intro-header" style="background-image: url('../../resources/img/post-bg.jpg')">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                    <div class="post-heading">
                        <h1>${article.subject}</h1>
                        <h2 class="subheading">Problems look mighty small from 150 miles up</h2>
                        <%-- <span class="meta">Posted by <a href="#">${article.publishedBy.fullname}</a> on ${article.publishedBy.publishDate}</span> --%>
                        <p class="meta">Posted by <a href="${pageContext.request.contextPath}/user/${article.publishedBy.id}/">${article.publishedBy.fullname}</a> on ${article.publishDate}</p>
                    </div>
                </div>
            </div>
        </div>
    </header>

    <!-- Post Content -->
    <article>
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                	<img src="${pageContext.request.contextPath}/articleImage/${article.id}" />
                    <p>${article.content}</p>
             </div>
            </div>
        </div>
    </article>
    
	<hr>
	
	<!-- Comment -->
	<sec:authorize access="isAuthenticated()">
		<div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1"> 
			<h4>Leave a Comment:</h4>
	        <form role="form" action="postComment/" method="post">
	            <div class="form-group">
	                <textarea name="comment" class="form-control" rows="3" required="required"></textarea>
	            </div>
	             
	            <button type="submit" class="btn btn-primary">Submit</button>	            
	            
	            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	        </form>
	    </div>
	</sec:authorize>

    <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
        <h5>${NumOfComments} Comments</h5>
        <hr>
	    <c:forEach items="${comments}" var="element"> 
	    <table>
		    <tr>
		        <td width="50">
		        	<div class="gig-comment-photo">
		        		<img src="${pageContext.request.contextPath}/userPhoto/${element.postedBy.id}" />
						<!-- <img class="gig-comment-img" alt="" src="https://s.yimg.com/dh/ap/social/profile/profile_b192.png" style="vertical-align: top; height: 37px;"> -->
					</div>
		        </td>
		        <td>
		        	<div class="gig-comment-header">
					<div class="gig-comment-header-right">
						<span class="gig-comment-edited" style="display: none;">(edited)</span>
						<%-- <span class="gig-comment-time">${element.postedDate}</span> --%>
						<span class="gig-comment-time">${element.postDate}</span>
					</div>
					<div class="gig-comment-header-left">
					<span class="gig-comment-username">${element.postedBy.fullname}</span></div></div>
		        </td>
		    </tr>

		    <tr>
		    	<td></td>
		    	<td>
		    	<div class="gig-comment-body"><p>${element.content}</p></div>
		    	</td>
		    </tr>
		</table>
		<hr>
		</c:forEach>
    </div>
    
    <!-- Footer -->
    <footer>
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                    <ul class="list-inline text-center">
                        <li>
                            <a href="#">
                                <span class="fa-stack fa-lg">
                                    <i class="fa fa-circle fa-stack-2x"></i>
                                    <i class="fa fa-twitter fa-stack-1x fa-inverse"></i>
                                </span>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="fa-stack fa-lg">
                                    <i class="fa fa-circle fa-stack-2x"></i>
                                    <i class="fa fa-facebook fa-stack-1x fa-inverse"></i>
                                </span>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="fa-stack fa-lg">
                                    <i class="fa fa-circle fa-stack-2x"></i>
                                    <i class="fa fa-github fa-stack-1x fa-inverse"></i>
                                </span>
                            </a>
                        </li>
                    </ul>
                    <p class="copyright text-muted">Copyright &copy; EA Blogs 2016</p>
                </div>
            </div>
        </div>
    </footer>

    <!-- jQuery -->
    <script src="../../resources/js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="../../resources/js/bootstrap.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="../../resources/js/clean-blog.min.js"></script>
</body>
</html>