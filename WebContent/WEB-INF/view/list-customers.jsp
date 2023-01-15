<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>>
<html>
<head>
<title>Customer List</title>>
<!-- reference our style sheet -->
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>CRM -Customer Relationship Manager</h2>
		</div>
		<div id="content">
			<!-- put new customer button -->
			<input type="button" value="Add Customer"
				onclick="window.location.href='showFormForAdd'; return false;"
				class="add-button" />
			<!-- add our html table here -->
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>
				<!-- print data -->
				<c:forEach var="tempCustomer" items="${customers}">
					<!-- construct a customer link with customer id -->
					<c:url var="updateLink" value="/customer/showFormForUpdate">
						<c:param name="customerId" value="${tempCustomer.id }" />
					</c:url>	
					<c:url var="deleteLink" value="/customer/delete">
						<c:param name="customerId" value="${tempCustomer.id}" />
					</c:url>
					<tr>
						<td>${tempCustomer.firstName}</td>
						<td>${tempCustomer.lastName}</td>
						<td>${tempCustomer.email}</td>
						<td><a href="${updateLink} onclick="if(!(confirm('Are you sure  you want to delete this Customer ?'))) return false">Update | </a><a href="${deleteLink}">Delete</a></td>
						
					</tr>
				</c:forEach>
			</table>
		</div>

	</div>

</body>
</html>
