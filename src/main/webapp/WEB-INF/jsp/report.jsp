<%@page session="false"%>
<html>
<head>
<title>Welcome</title>
</head>
<body>
	<jsp:include page="menu.jsp" />
	
	<div id="report">
		<form action="/reportgenerate" method="get">
					<input type="SUBMIT" value="Report" />
		</form>
		</div>
	      
	<h3 style="color: red;">press report </h3>
</body>
</html>