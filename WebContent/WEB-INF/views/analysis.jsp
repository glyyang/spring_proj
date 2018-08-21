
	<%@include file="header.jsp"%>
	<title>Home</title>
	
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<!-- <link rel="stylesheet" href="/resources/demos/style.css"> -->
	<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>
</head>
<body>
<jsp:include page="nav.jsp" />
<form action="${pageContext.request.contextPath }/analytic" method="post">
	<div class="input-group" style="width: 50%"><span class="input-group-addon" id="basic-addon1"  style="width: 10%">From:</span>
	<input type="text" class="form-control" name="ff" id="datepicker1"/><span class="input-group-addon" id="basic-addon1"  style="width: 5%">:</span><input type="text" class="form-control" name="ffHr"  id="timepicker1"/></div>
	
	<div class="input-group" style="width: 50%"><span class="input-group-addon" id="basic-addon1"  style="width: 10%">To:</span>
	<input type="text" class="form-control" name="tt" id="datepicker2"/><span class="input-group-addon" id="basic-addon1"  style="width: 5%">:</span><input type="text" class="form-control" name="ttHr"  id="timepicker2"/></div>
	<div class="input-group" style="width: 30%"><span class="input-group-addon" id="basic-addon1"  style="width: 20%">File Path:</span><input type="text" class="form-control" name="fpath"/></div>
	<div class="input-group" style="width: 30%"><span class="input-group-addon" id="basic-addon1"  style="width: 20%">ASIN:</span><input type="text" class="form-control" name="asin"/></div>
	<div class="input-group" style="width: 30%"><span class="input-group-addon" id="basic-addon1"  style="width: 20%">SKU:</span><input type="text" class="form-control" name="sku"/></div>
	<div class="input-group" style="width: 30%"><span class="input-group-addon" id="basic-addon1"  style="width: 20%">Title:</span><input type="text" class="form-control" name="title"/></div>
	<div class="input-group" style="width: 30%"><input type="submit" class="input-group-addon" id="basic-addon1" value="Submit" style="width:100%; font-weight:bold;"/></div>
</form>

<div>
<img alt="Analysis Result" width="800" height="800" src="data:image/png;base64,${imgSrc }">
</div>

<form action="${pageContext.request.contextPath }/compare_statistic" method="post">
	<div class="input-group" style="width: 50%"><span class="input-group-addon" id="basic-addon1"  style="width: 10%">From:</span>
	<input type="text" class="form-control" name="ff2" id="datepicker3"/><span class="input-group-addon" id="basic-addon1"  style="width: 5%">:</span><input type="text" class="form-control" name="ffHr2"  id="timepicker3"/></div>
	
	<div class="input-group" style="width: 50%"><span class="input-group-addon" id="basic-addon1"  style="width: 10%">To:</span>
	<input type="text" class="form-control" name="tt2" id="datepicker4"/><span class="input-group-addon" id="basic-addon1"  style="width: 5%">:</span><input type="text" class="form-control" name="ttHr2"  id="timepicker4"/></div>
	
	<div class="input-group" style="width: 30%"><input type="submit" class="input-group-addon" id="basic-addon1" value="Submit" style="width:100%; font-weight:bold;"/></div>
</form>

<div>
<img alt="Analysis Result" width="800" height="800" src="data:image/png;base64,${imgSrc2 }">
</div>

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
    	$("#datepicker3").val(cDate1);
	    $("#datepicker4").val(cDate2);
	    $( "#timepicker3" ).val("12:00 AM");
    	$( "#timepicker4" ).val("12:00 AM");
	} );

  $( function() {
    $( "#datepicker1" ).datepicker();
    $( "#datepicker2" ).datepicker();
    $( "#timepicker1" ).timepicker();
    $( "#timepicker2" ).timepicker();
    $( "#datepicker3" ).datepicker();
    $( "#datepicker4" ).datepicker();
    $( "#timepicker3" ).timepicker();
    $( "#timepicker4" ).timepicker();
  } );
</script>
<p>${ desc }</p>

<!-- <script  type="text/javascript">
function show_image(src, width, height, alt) {
    var img = document.createElement("img");
    img.src = src;
    img.width = width;
    img.height = height;
    img.alt = alt;

    // This next line will just add it to the <body> tag
    document.body.appendChild(img);
}
</script> -->
</body>
</html>
