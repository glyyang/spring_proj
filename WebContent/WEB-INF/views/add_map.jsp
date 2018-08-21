
<%@include file="header.jsp"%>
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
</head>
<body>
<jsp:include page="nav.jsp" />
<form id="fillin" action="${pageContext.request.contextPath }/add_mapping" method="post">
<div class="input-group" style="width: 50%"><span class="input-group-addon" id="basic-addon1"  style="width: 20%">Item&nbspAsin:&nbsp</span><input type="text" class="form-control" placeholder="ASIN" name="asin" id="asin"></div>
<div class="input-group" style="width: 50%"><span class="input-group-addon" id="basic-addon1"  style="width: 20%">Item&nbspSku:&nbsp</span><input type="text"  class="form-control" placeholder="SKU" name="sku" id="sku"></div>
<div class="input-group" style="width: 50%"><span class="input-group-addon" id="basic-addon1"  style="width: 20%">Item&nbspName:&nbsp</span><input type="text"  class="form-control" placeholder="TITLE" name="title" id="title"></div>
<div class="input-group" style="width: 50%"><span class="input-group-addon" id="basic-addon1"  style="width: 20%">Item&nbspCategory:&nbsp</span><input type="text"  class="form-control" placeholder="CATEGORY" name="category" id="category"></div>
<div class="input-group" style="width: 50%"><span class="input-group-addon" id="basic-addon1"  style="width: 20%">Item&nbspType:&nbsp</span><input type="text"  class="form-control" placeholder="TYPE" name="type" id="type"></div>
<div class="input-group" style="width: 50%"><span class="input-group-addon" id="basic-addon1"  style="width: 20%">Item&nbspSize:&nbsp</span><input type="text"  class="form-control" placeholder="SIZE" name="size" id="size"></div>
<div class="input-group" style="width: 50%"><span class="input-group-addon" id="basic-addon1"  style="width: 20%">Item&nbspThickness:&nbsp</span><input type="text"  class="form-control" placeholder="THICKNESS" name="thickness" id="thickness"></div>
<div class="input-group" style="width: 50%"><input type="submit" class="input-group-addon" id="basic-addon1" value="Submit" style="width: 100%"></div>
</form>

<form action="${pageContext.request.contextPath }/check_mapping" method="post">
<br><input type="submit" value="Checking Mappings">
</form>
<!--<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<br><button onclick="post()">Click Here</button> 
<script>
	function post(){
	
		 $.ajax({
			 url: "${contextPath}/check_mapping",
			 type: "POST"
		 });
	}
</script>
-->
<p><h1>Unmapped Items:</h1></p>
<table id="myTab" class="display">
	<thead>
	<tr>
			
			<td>ASIN</td>
			<td>SKU</td>
			<td>DESCRIPTION</td>
		</tr>	
	</thead>
	<tbody>
	<c:forEach items="${sLT }" var="SLTA">
		<tr>
			
			    
			    
			
				
					
					<td>${SLTA[0] }
					<!-- <input type="hidden" name="asinV" value="${SLTA[0] }"> -->
					</td>
					<td>
					<a HREF="#fillin" onClick="autoFillAll('${SLTA[0] }', '${SLTA[1] }', '${SLTA[2].replace('\'','\\\'').replace('\"',' in.') }', '','','',''); return true;" >${SLTA[1] }</a>
					
					<!-- <input type="hidden" name="skuV" value="${SLTA[1] }"> -->
					</td>
					<td>${SLTA[2] }</td>
					<!-- <input type="hidden" name="titleV" value="${SLTA[2] }"> -->
			
		</tr>
	</c:forEach>
	</tbody>
</table>
<p><h1>Existing Mappings:</h1></p>
<table id="myTab1" class="display">
	<thead>
	<tr>
			
			<td>ASIN</td>
			<td>SKU</td>
			<td>TITLE</td>
			<td>CATEGORY</td>
			<td>TYPE</td>
			<td>SIZE</td>
			<td>THiCKNESS</td>
		</tr>	
	</thead>
	<tbody>
	<c:forEach items="${sULT }" var="SULTA">
		<tr>
			
			    
			    
			
				
					
					<td>${SULTA[0] }
					<!-- <input type="hidden" name="asinV" value="${SLTA[0] }"> -->
					</td>
					<td>
					<a HREF="#fillin" onClick="autoFillAll('${SULTA[0] }', '${SULTA[1] }', '${SULTA[2].replace('\'','\\\'').replace('\"',' in. ') }', '${SULTA[3].replace('\'','\\\'').replace('\"',' in. ') }', '${SULTA[4].replace('\'','\\\'').replace('\"',' in. ') }', '${SULTA[5].replace('\'','\\\'').replace('\"',' in. ') }', '${SULTA[6].replace('\'','\\\'').replace('\"',' in. ') }'); return true;" >${SULTA[1] }</a>
					
					<!-- <input type="hidden" name="skuV" value="${SLTA[1] }"> -->
					</td>
					<td>${SULTA[2] }</td>
					<!-- <input type="hidden" name="titleV" value="${SLTA[2] }"> -->
					<td>${SULTA[3] }</td>
					<td>${SULTA[4] }</td>
					<td>${SULTA[5] }</td>
					<td>${SULTA[6] }</td>
			
		</tr>
	</c:forEach>
	</tbody>
</table>
<p>${ desc }</p>
<script type="text/javascript">
$(document).ready( function () {
    $('#myTab').DataTable();
    $('#myTab1').DataTable();
} );

  function autoFill(x, y, t1) {
    document.getElementById("asin").value = x;
    document.getElementById("sku").value = y;
    document.getElementById("title").value = t1;
  }
  
  function autoFillAll(x, y, t1, t2, t3, t4, t5) {
    document.getElementById("asin").value = x;
    document.getElementById("sku").value = y;
    document.getElementById("title").value = t1;
    document.getElementById("category").value = t2;
    document.getElementById("type").value = t3;
    document.getElementById("size").value = t4;
    document.getElementById("thickness").value = t5;
  }

</script>



</body>

</html>