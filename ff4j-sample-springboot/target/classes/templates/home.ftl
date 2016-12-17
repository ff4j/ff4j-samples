<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html>
<head>
 <title>${title}</title>
 <#assign home><@spring.url relativeUrl="/"/></#assign>
 <#assign actuinfo><@spring.url relativeUrl="/info"/></#assign>
 <#assign actujolokiaagent><@spring.url relativeUrl="/jolokia"/></#assign>
 <#assign actumetrics><@spring.url relativeUrl="/metrics"/></#assign>
 <#assign actutrace><@spring.url relativeUrl="/trace"/></#assign>
 
 <#assign odata><@spring.url relativeUrl="/odata/$metadata"/></#assign>
 <#assign crud><@spring.url relativeUrl="/api/trades"/></#assign>
 <#assign hateoas><@spring.url relativeUrl="/api/trades/hateoas"/></#assign>
 <#assign bootstrap><@spring.url relativeUrl="/css/bootstrap.min.css"/></#assign>
 <link rel="stylesheet" href="${bootstrap}" />
 <link rel="stylesheet" href="/css/heroic-features.css" />
</head>
<body>

 <!-- Navigation -->
    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
         		
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li><a href="/" style="color:#00ab8b"><span class="glyphicon glyphicon-home"></span>&nbsp; Showcase APPLICATION </a></li>
                    
                    <li class="dropdown">
                      <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                      <span class="glyphicon glyphicon-pencil"></span>&nbsp; FF4J <span class="caret"></span></a>
                      <ul class="dropdown-menu" role="menu">
                        <li><a href="/ff4j-console">WebConsole</a></li>
                        <li><a href="/api/ff4j">Web API</a></li>
                        <li><a href="/swagger/index.html">Swagger Documentation</a></li>
                      </ul>
                    </li>
                    
                    <li class="dropdown">
                      <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                      <span class="glyphicon glyphicon-pencil"></span>&nbsp;Spring-BOOT<span class="caret"></span></a>
                      <ul class="dropdown-menu" role="menu">
                        <li><a href="/info">General Information</a></li>
                        <li><a href="/metrics">Monitoring</a></li>
                        <li><a href="/health">Health</a></li>
                        <li><a href="/beans">Beans</a></li>
                      </ul>
                    </li>
                   
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

 <!-- Page Content -->
    <div class="container">

        <!-- Title -->
        <div class="row">
		<h1><p><br> SpringBoot and FF4J Application </h1>
		
	   </div>
	</div>
	
	 <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>
	
</body>
</html>
