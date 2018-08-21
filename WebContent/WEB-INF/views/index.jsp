
<%@include file="header.jsp"%>
<title>Insert title here</title>
</head>
<body>
<jsp:include page="nav.jsp" />
<div class="panel panel-default">
<a href="${pageContext.request.contextPath }/home">Service Update Orders</a>
<!-- <br><a href="${pageContext.request.contextPath }/inventory">Manage Inventory</a> -->
<br><a href="${pageContext.request.contextPath }/fetch_mapping_form">Add Order Mapping</a>
<!-- <br><a href="${pageContext.request.contextPath }/search">Fetch Orders</a> -->
<br><a href="${pageContext.request.contextPath }/get_items">List Order Mapping</a>
<br><a href="${pageContext.request.contextPath }/report">Access Reports</a>
<br><a href="${pageContext.request.contextPath }/analysis">Access Analysis</a>
</div>
</body>
</html>