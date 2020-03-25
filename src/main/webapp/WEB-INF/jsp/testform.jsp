<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Test data</title>
</head>
<jsp:include page="menu.jsp" />
<body>
	<h3 style="color: red;">Add New test data</h3>

	<div id="addTest">
		<form:form action="/addNewtest" method="post" modelAttribute="testdata">
			<p>
				<label>Scenario :</label>
				<form:input path="testDesc" />
			</p>
			<p>
				<label>Input URI :</label>
				<form:input path="inputUri" />
			</p>
			<p>
				<label>Input JSON :</label>
				<form:input path="inputJson" />
			</p>
			<p>
				<label>Expected Response :</label>
				<form:input path="expResp" />
			</p>
			<p>
				<label>Expected JSON :</label>
				<form:input path="expJson" />
			</p>
			<p>
				<label>Result Response</label>
				<form:input path="resultresp" />
			</p>
			<p>
				<label>Result JSON</label>
				<form:input path="resultJson" />
			</p>
			<p>
				<label>Environment :</label>
				<form:input path="environment" />

			</p>
			<input type="SUBMIT" value="Submit" />
		</form:form>
	</div>
</body>
</html>
