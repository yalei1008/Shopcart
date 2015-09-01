<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<style>

body {
    background-image: url('hiii.jpg');

} 
#header {
    background-color:pink;
    color:white;
    text-align:bottom;
    padding:5px;
}
#nav {
    line-height:30px;
    background-color:#eeeeee;
    height:300px;
    width:100px;
    float:center;
    padding:5px;	      
}
#section {
    width:350px;
    float:left;
    padding:10px;	 	 
}
#footer {
  
    clear:both;
    text-align:center;
   padding:5px;	 	 
}

</style>
</head>
<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
  <div id ="header" class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#"><FONT FACE="algerian">Evil Gulp</FONT></a>
    </div>
    <div id="header">
      <ul class="nav navbar-nav">
        <li class="active"><a href="shoppingcart.html"><FONT FACE="courier">Home</FONT></a></li>
        <li><a href="productlist.html"><FONT FACE="algerian">Product List</FONT></a></li>
        <li><a href="/Detail.jsp"><FONT FACE="algerian">Product Details</FONT></a></li>
        <li><a href="#"><FONT FACE="algerian">Shopping Carts</FONT></a></li>
         <li><a href="#"><FONT FACE="algerian">Order Confirmation</FONT></a></li>
            	<li>
	      		<a href="ShoppingCart"><span class="glyphicon glyphicon-shopping-cart"></span> <span class="badge">${numItems}</span></a></li>
      </ul>
    </div>
  </div>
</nav>
	${productData}
</body>
</html>


