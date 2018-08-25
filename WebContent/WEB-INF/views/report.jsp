
		<%@include file="header.jsp"%>
		<title>Insert title here</title>
		<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
		<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
		<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
		<style>
			div.display1 {
			    display: none;
			}
			
			
			
			table {
				width: 100%;
			}
			
			th, td {
				width: 50%;
			}
			
			.stat_t {
				width: 90%;
				text-align: center;
			}
			
			.stat_t td {
				width: 15%;
				text-align: center;
			}
			
		</style>
	</head>
	<body>
		<jsp:include page="nav.jsp" />
		<form action="${pageContext.request.contextPath }/get_report" method="post">
			<select id="sel" name="dropdown" onchange="showForm()">
			  <option value="Today">Today</option>
			  <option value="Yesterday">Yesterday</option>
			  <option value="Last 24 Hours">Last 24 Hours</option>
			  <option value="This Seven Days">This Seven Days</option>
			  <option value="This Month">This Month</option>
			  <option value="This Year">This Year</option>
			  <option value="Custom Input">Custom Input</option>
			  <option value="All">All</option>
			</select>
			
			<div id="myDiv" class="display1">
				<br>From: (format: YYYY-MM-dd:hh:mm:ss)
				<br><input type="text" placeholder="YYYY" name="fyear"/>
				&nbsp-&nbsp<input type="text" placeholder="MM" name="fmonth"/>
				&nbsp-&nbsp<input type="text" placeholder="dd" name="fday"/>
				&nbsp:&nbsp<input type="text" placeholder="hh" name="fhour"/>
				&nbsp:&nbsp<input type="text" placeholder="mm" name="fminute"/>
				&nbsp:&nbsp<input type="text" placeholder="ss" name="fsecond"/>CST
				<br>To: (format: YYYY-MM-dd:hh:mm:ss)
				<br><input type="text" placeholder="YYYY" name="tyear"/>
				&nbsp-&nbsp<input type="text" placeholder="MM" name="tmonth"/>
				&nbsp-&nbsp<input type="text" placeholder="dd" name="tday"/>
				&nbsp:&nbsp<input type="text" placeholder="hh" name="thour"/>
				&nbsp:&nbsp<input type="text" placeholder="mm" name="tminute"/>
				&nbsp:&nbsp<input type="text" placeholder="ss" name="tsecond"/>CST
			</div>
			<div class="input-group" style="width: 50%"><span class="input-group-addon" id="basic-addon1"  style="width: 20%">ASIN:</span><input type="text" class="form-control" placeholder="ASIN" name="asin"></div>
			<div class="input-group" style="width: 50%"><span class="input-group-addon" id="basic-addon1"  style="width: 20%">SKU:</span><input type="text" class="form-control" placeholder="SKU" name="sku"></div>
			<div class="input-group" style="width: 50%"><span class="input-group-addon" id="basic-addon1"  style="width: 20%">TITLE:</span><input type="text" class="form-control" placeholder="TITLE" name="title"></div>
			<input type="submit" name="submit" value="Submit">
		</form>
		
		<h1>Statistical summaries:</h1>
		
		<h2>Cumulative statistics:</h2>
		<br>Date&nbsprange:&nbsp${timeFrom }&nbspto&nbsp${timeTo }
		<br>Items&nbspTotal:&nbsp${total }
		<br>Items&nbspTotal&nbspCount:&nbsp${totalCount }
		
		
		<!-- <p><h2>Item total by ASIN purchase summary:</h2></p>
		<table id="myTab" data-order='[[ 1, "desc" ]]' class="display">
			<thead>
				<tr>
					
					<td>ASIN</td>
					<td>Count</td>
				</tr>	
			</thead>
			<tbody>
				<c:forEach items="${asinC }" var="item">
					<tr>
						<td>${item.key }</td>
						<td>${item.value }</td>
					</tr>
				</c:forEach>
			
			</tbody>
		</table>
		
		<p><h2>Item total by SKU purchase summary:</h2></p>
		<table id="myTab1" data-order='[[ 1, "desc" ]]' class="display">
			<thead>
				<tr>
					
					<td>SKU</td>
					<td>Count</td>
				</tr>	
			</thead>
			<tbody>
				<c:forEach items="${skuC }" var="item">
					<tr>
						<td>${item.key }</td>
						<td>${item.value }</td>
					</tr>
				</c:forEach>
			
			</tbody>
		</table>
		
		<p><h2>Item total by ASIN cost summary:</h2></p>
		<table id="myTab2" data-order='[[ 1, "desc" ]]' class="display">
			<thead>
				<tr>
					
					<td>ASIN</td>
					<td>Cost</td>
				</tr>	
			</thead>
			<tbody>
				<c:forEach items="${asinP }" var="item">
					<tr>
						<td>${item.key }</td>
						<td>${item.value }</td>
					</tr>
				</c:forEach>
			
			</tbody>
		</table>
		
		<p><h2>Item total by SKU cost summary:</h2></p>
		<table id="myTab3" data-order='[[ 1, "desc" ]]' class="display">
			<thead>
				<tr>
					
					<td>ASIN</td>
					<td>Cost</td>
				</tr>	
			</thead>
			<tbody>
				<c:forEach items="${skuP }" var="item">
					<tr>
						<td>${item.key }</td>
						<td>${item.value }</td>
					</tr>
				</c:forEach>
			
			</tbody>
		</table> -->
		
		<p><h2>Item total by name purchase summary:</h2></p>
		<table id="myTab" data-order='[[ 1, "desc" ]]' class="display">
			<thead>
				<tr>
					
					<td>TITLE</td>
					<td>Count</td>
				</tr>	
			</thead>
			<tbody>
				<c:forEach items="${titleC }" var="item">
					<tr>
						<td>${item.key }</td>
						<fmt:parseNumber var ="i" integerOnly ="true" type="number" value="${item.value}" />
						<td>${item.value }</td>
					</tr>
				</c:forEach>
			
			</tbody>
		</table>
		
		<p><h2>Item total by name cost summary:</h2></p>
		<table id="myTab1" data-order='[[ 1, "desc" ]]' class="display">
			<thead>
				<tr>
					
					<td>TITLE</td>
					<td>Cost</td>
				</tr>	
			</thead>
			<tbody>
				<c:forEach items="${titleP }" var="item">
					<tr>
						<td>${item.key }</td>
						<td>${item.value }</td>
					</tr>
				</c:forEach>
			
			</tbody>
		</table>
		
		<p>
		<h2>Daily comparison statistics summary:</h2>
		</p>
		<table class="stat_t">
			<thead>
				<tr>
					
					<td> </td>
					<td>Time range</td>
					<td>Cost Increase</td>
					<td>Count Increase</td>
					<td>Cost</td>
					<td>Count</td>
				</tr>	
			</thead>
			<tbody>
				<c:forEach items="${costStat }" var="item" varStatus="stat">
					<tr>
						<td>${strStat[stat.index] }: </td>
						<td>${fromL[stat.index].getTime() }&nbsp-&nbsp${toL[stat.index].getTime() }</td>
						<td>${item }</td>
						<td>${countStat[stat.index] }</td>
						<td>${costVice[stat.index] }USD</td>
						<td>${countVice[stat.index] }</td>
					</tr>
				</c:forEach>
			
			</tbody>
		</table>
		
		<p>${ desc }</p>
		
		<script type="text/javascript">
			$(document).ready( function () {
			    $('#myTab').DataTable();
			    $('#myTab1').DataTable();
			    //$('#myTab2').DataTable();
			    //$('#myTab3').DataTable();
			    //$('#myTab4').DataTable();
			    //$('#myTab5').DataTable();
			} );
			
			function showForm(){
				if(document.getElementById("sel").value == "Custom Input"){
					document.getElementById("myDiv").style.display = "block";
				}else{
					document.getElementById("myDiv").style.display = "none";
				}
			}
		</script>
		
		
		
	</body>
</html>