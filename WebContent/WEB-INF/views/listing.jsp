
	<%@include file="header.jsp"%>
	<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
	<!-- <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script> -->
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
</head>
<body>
<jsp:include page="nav.jsp" />
<form action="${pageContext.request.contextPath }/get_items" method="post">
<div class="input-group" style="width: 50%"><span class="input-group-addon" id="basic-addon1"  style="width: 20%">Item&nbspName:&nbsp</span><input type="text" class="form-control" placeholder="TITLE" name="title"></div>
<div class="input-group" style="width: 50%"><span class="input-group-addon" id="basic-addon1"  style="width: 20%">Item&nbspCategory:&nbsp</span><input type="text" class="form-control" placeholder="CATEGORY" name="category"></div>
<div class="input-group" style="width: 50%"><span class="input-group-addon" id="basic-addon1"  style="width: 20%">Item&nbspType:&nbsp</span><input type="text" class="form-control" placeholder="TYPE" name="type"></div>
<div class="input-group" style="width: 50%"><span class="input-group-addon" id="basic-addon1"  style="width: 20%">Item&nbspSize:&nbsp</span><input type="text" class="form-control" placeholder="SIZE" name="size"></div>
<div class="input-group" style="width: 50%"><span class="input-group-addon" id="basic-addon1"  style="width: 20%">Item&nbspThickness:&nbsp</span><input type="text" class="form-control" placeholder="THICKNESS" name="thickness"></div>
<div class="input-group" style="width: 50%"><input type="submit" class="input-group-addon" id="basic-addon1" value="Submit" style="width: 100%; font-weight: bold;"></div>
</form>
<div>Total number of items: ${fn:length(oiL)}</div>
<table id="myTab" class="display" data-order='[[4,"desc"]]'>
	<thead>
		<tr>
			<td>DB Item ID</td>
			<td>ASIN</td>
			<td>SKU</td>
			<td>Order Id</td>
			<td>Purchase Date</td>
			<td>Delivery time</td>
			<td>Shipment time</td>
			<td>Cost</td>
		</tr>
	
	</thead>
	<tbody>
		<c:forEach items="${oiL }" var="orderItem">
			<tr>
				<td>${orderItem.id }</td>
				<td>${orderItem.asin }</td>
				<td>${orderItem.sellerSKU }</td>
				<td>${orderItem.ord.amazonOrderId }</td>
				<td>${orderItem.ord.purchaseDateConverted }</td>
				<td>${orderItem.scheduledDeliveryStartDate } to ${orderItem.scheduledDeliveryEndDate }</td>
				<td>${orderItem.ord.earliestShipment } to ${orderItem.ord.latestShipment }</td>
				<td>${orderItem.itemPrice }</td>
			</tr>	
		</c:forEach>
	</tbody>
</table>

<script type="text/javascript">
	$(document).ready( function () {
	    $('#myTab').DataTable();
	} );
</script>
<p>${ desc }</p>
</body>
</html>