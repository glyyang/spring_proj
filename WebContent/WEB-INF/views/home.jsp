
	<%@include file="header.jsp"%>
	
  	
  	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  	<!-- <link rel="stylesheet" href="/resources/demos/style.css"> -->
  	<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">
  	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
	<!-- <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script> -->
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
  	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  	<script src="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>
	
</head>
<body>
<jsp:include page="nav.jsp" />
<form action="${pageContext.request.contextPath }/fetchServiceOrder" method="post">
	<div class="input-group" style="width: 50%"><span class="input-group-addon" id="basic-addon1"  style="width: 10%">From:</span>
	<input type="text" class="form-control" name="ff" id="datepicker1"/><span class="input-group-addon" id="basic-addon1"  style="width: 5%">:</span><input type="text" class="form-control" name="ffHr"  id="timepicker1"/></div>
	
	<div class="input-group" style="width: 50%"><span class="input-group-addon" id="basic-addon1"  style="width: 10%">To:</span>
	<input type="text" class="form-control" name="tt" id="datepicker2"/><span class="input-group-addon" id="basic-addon1"  style="width: 5%">:</span><input type="text" class="form-control" name="ttHr"  id="timepicker2"/></div>
	
	<br>Connection Identity:
	
	<div class="input-group" style="width: 30%"><span class="input-group-addon" id="basic-addon1"  style="width: 10%">Service Provider:</span><select id="sel" name="ws" class="form-control">
		<option value="amazon">Amazon</option>
	</select></div>
	<div class="input-group" style="width: 30%"><input type="submit" class="input-group-addon" id="basic-addon1" value="Submit" style="width: 100%; font-weight: bold;"/></div>
</form>

<h1>Most Recent 1000 Orders:</h1>

<form name="submitForm" method="POST" action="${pageContext.request.contextPath }/fetchOrder">
    <input type="hidden" name="param1" value="param1Value">
    <input type="submit" class="btn btn-default" value="Check Orders in Db"/>
</form>

<br><a href="${pageContext.request.contextPath }/search" class="btn btn-default">Search Orders</a><br>

<table id="myTab" class="display" data-order='[[ 2, "desc" ]]'>
	<thead>
		<tr>
			<td>DB Item ID</td>
			<td>Amazon Order ID</td>
			<td>Purchase Date</td>
			<td>Fulfillment Channel</td>
			<td>Total Cost</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${orders }" var="order">
			<tr>
				<td>${order.id }</td>
				<td>${order.amazonOrderId }</td>
				<td>${order.purchaseDateConverted }</td>
				<td>${order.fulfillmentChannel }</td>
				<td>${order.orderCost }${order.orderCurrency }</td>
			</tr>	
		</c:forEach>
	</tbody>
</table>

<script type="text/javascript">
	$(document).ready( function () {
		var time = new Date();
    	var day1 = time.getDate()-1;
    	var day2 = time.getDate();
    	var month = time.getMonth()+1;
    	if(month < 10){month = "0" + month}
    	if(day1 < 10){day1 = "0"+day1}
    	if(day2 < 10){day2 = "0"+day2}
    	var year = time.getFullYear();
    	
    	var cDate1 = month+"/"+day1+"/"+year;
    	var cDate2 = month+"/"+day2+"/"+year;
    	
	    $("#datepicker1").val(cDate1);
	    $("#datepicker2").val(cDate2);
	    $( "#timepicker1" ).val("12:00 AM");
    	$( "#timepicker2" ).val("12:00 AM");
	} );


  $( function() {
    $( "#datepicker1" ).datepicker();
    $( "#datepicker2" ).datepicker();
    $( "#timepicker1" ).timepicker();
    $( "#timepicker2" ).timepicker();
    $( "#myTab" ).DataTable();
  } );
</script>

<p>${ desc }</p>

</body>
</html>
