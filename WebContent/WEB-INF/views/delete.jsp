
<%@include file="header.jsp"%>
<title>Insert title here</title>
</head>
<body>
<jsp:include page="nav.jsp" />
<form action="${pageContext.request.contextPath }/deleteInventory" method="post">
<br>Inventory&nbspCode:&nbsp<input type="text" name="itemCode">
<br>Item&nbspTitle:&nbsp<input type="text" name="desc">
<br><input type="submit" value="Delete">
</form>

</body>
</html>