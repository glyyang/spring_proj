
<%@include file="header.jsp"%>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js">
</script>
<title>Insert title here</title>
</head>
<body>
<jsp:include page="nav.jsp" />
<form ng-app="myApp" name="fform" action="${pageContext.request.contextPath }/updateInventory" method="post">
<br>Inventory&nbspCode:&nbsp<input type="text" name="itemCode" ng-model="itemCode" required>
<br>Item&nbspTitle:&nbsp<input type="text" name="desc" ng-model="desc" required>
<br>Payment&nbspCurrency:&nbsp<select name="currency">
	<option value="USD">USD</option>
	<option value="RMB">RMB</option>
	<option value="EUR">EUR</option>
	<option value="GBP">GBP</option>
</select>
<br>Unit&nbspCost:&nbsp<input type="number" step="0.01" name="cost">
<br>Unit&nbspCount:&nbsp<input type="number" step="1" name="count">
<br><input type="submit" value="Update">
</form>

</body>
</html>