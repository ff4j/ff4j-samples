<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html>
<head>
<title>Error</title>
<#assign home><@spring.url relativeUrl="/"/></#assign>
<#assign bootstrap><@spring.url relativeUrl="/css/bootstrap.min.css"/></#assign>
<link rel="stylesheet" href="${bootstrap}" />
</head>
<body>
	<div class="container">
		
		<h2>An error occured :  </h2>
		
		<table style="border:1px solid #CCCCCC">
		<tr>
		<td style="background-color:#EEEEEE;width:200px">Reported at </td>
		<td style="width:200px">${timestamp?datetime}</td>
		</tr>
		<tr>
        <td style="background-color:#EEEEEE;width:200px">Error code</td>
        <td><b>${status}</b></td>
        </tr>
		<tr>
		<td style="background-color:#EEEEEE;width:200px">Error type</td>
		<td><b>${error}</b></td>
		</tr>
		
		<tr>
        <td style="background-color:#EEEEEE;width:200px"> Error message</td>
        <td>${message}</td>
        </tr>
        </table>
		</div>
		
		
	</div>
</body>
</html>
