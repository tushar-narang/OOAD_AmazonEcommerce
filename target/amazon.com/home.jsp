<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="author" content="Bootstrap-ecommerce by Vosidiy">

<title>Amazon OOAD Project</title>
<script src="http://code.jquery.com/jquery-1.11.0.min.js" type="text/javascript"></script>


<link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico">

<!-- jQuery -->
<script src="js/jquery-2.0.0.min.js" type="text/javascript"></script>

<!-- Bootstrap4 files-->
<script src="js/bootstrap.bundle.min.js" type="text/javascript"></script>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>

<!-- Font awesome 5 -->
<link href="fonts/fontawesome/css/fontawesome-all.min.css" type="text/css" rel="stylesheet">

<!-- plugin: owl carousel  -->
<link href="plugins/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
<link href="plugins/owlcarousel/assets/owl.theme.default.css" rel="stylesheet">

<script src="plugins/owlcarousel/owl.carousel.min.js"></script>

<!-- custom style -->
<link href="css/ui.css" rel="stylesheet" type="text/css"/>
<link href="css/responsive.css" rel="stylesheet" media="only screen and (max-width: 1200px)" />
<link href="css/custom.css" rel="stylesheet"  />

<!-- custom javascript -->
<script src="js/script.js" type="text/javascript"></script>
<script src="js/scripts/viewProductDetail.js" type="text/javascript"></script>

<script type="text/javascript">

var next = 1;			// fixed, please do not modfy;
var current = 0;		// fixed, please do not modfy;
var interval = 4000;	// You can set single picture show time;
var fadeTime = 800;	// You can set fadeing-transition time;
var imgNum = 5;		

function SignOut() {
	localStorage.removeItem("userdata");
	window.location.href = "http://localhost:8055/amazon.com/";
}

function setCategory(id1) {
	localStorage.setItem("selectedcategory",id1);
	console.log('value of lc is :'+localStorage.getItem("selectedcategory"));
	window.location.href = "http://localhost:8055/amazon.com/search.html";
}


function nextFadeIn(){
	//make image fade in and fade out at one time, without splash vsual;
	$('.fadeImg img').eq(current).delay(interval).fadeOut(fadeTime)
	.end().eq(next).delay(interval).hide().fadeIn(fadeTime, nextFadeIn);
	    
	// if You have 5 images, then (eq) range is 0~4 
	// so we should reset to 0 when value > 4; 
	if(next < imgNum-1){ next++; } else { next = 0;}
	if(current < imgNum-1){ current++; } else { current =0; }
};
$(document).ready(function(){
console.log("here");

var udata = JSON.parse(localStorage.getItem("userdata"));
var is_birthday = false;

if(udata == null) {
	$('#nameText').text("Hello, Sign In");
	//$('#deliverTo').text("");
	//$('#myAmazon').text("Your Amazon");m
} else {
	$('#nameText').text("Hello, " + udata.fname);
	$('#deliverTo').text("Deliver To, " + udata.fname);
	//$('#myAmazon').text(udata.fname+"'s Amazon");
	$('#signInButton').hide();
	var responsebirthdate = new Date(udata.dob);
	var currentdate = new Date ();
	console.log(responsebirthdate);
	console.log(currentdate);
	console.log(currentdate.getMonth());
	console.log(responsebirthdate.getMonth());
	console.log(currentdate.getDate());
	console.log(responsebirthdate.getDate());
	if(currentdate.getMonth() == responsebirthdate.getMonth()+1 &&  currentdate.getDate() == responsebirthdate.getDate() ){
	console.log("happy birthday");
	is_birthday = true;
	}
}

var api;
api = "http://localhost:8055/amazon.com/webapi/AdvertismentController/advertisments";


$.get(api , function(data, status){
    console.log("hello");
    myString = "";
	for(i=0;i< data.length;i++)
		{
		if(i == 0) {
			var myString = myString+ "<div class='item-slide'>		<img src='http://localhost:8055/amazon.com/"+data[i].url+"' onclick= 'setCategory("+data[i].categorytoshow+");'>		</div>";
			console.log(myString);

		} else {
			var myString = myString+ "<div class='item-slide'>		<img style='display:none' src='http://localhost:8055/amazon.com/"+data[i].url+"'  onclick= 'setCategory("+data[i].categorytoshow+");'>		</div>";
	
		}
	
		}
	if(is_birthday){
		console.log("ssup bcdo");
		var myString =  myString+ "<div class='item-slide'>		<img style='display:none' src='images/advertisments/birthday.jpg'>	</div>";
		imgNum = data.length + 1;
		$('#notbdayDisplay').hide();
		$('#bdayDisplay').show();
	}else{
		imgNum = data.length;
		$('#notbdayDisplay').show();
		$('#bdayDisplay').hide();
	}
	console.log("no of adverts" + imgNum);
	$('#myAdverts').html(myString+"");
	
	$('.fadeImg').css('position','relative');
	$('.fadeImg img').css({'position':'absolute','width':'100%','height':'500px'});

	nextFadeIn();
});
var api;
api = "http://localhost:8055/amazon.com/webapi/ProductController/products";


$.get(api , function(data, status){
    data.forEach(function(product) {
    	var items = "";
    	var responsebirthdate = new Date(udata.dob);
    	var currentdate = new Date ();
    	
    	if(currentdate.getMonth() == responsebirthdate.getMonth()+1 &&  currentdate.getDate() == responsebirthdate.getDate() ){    	
    		if(product.is_bdayproduct == true){
        		items = "<div class='item-slide'><img src='"+product.product_images[0].url+"'  style='height: 50%; width: 50%; object-fit: contain' onclick=\"viewProductDetails('"+product.id+"');\"/></div>";	

    		}
   			$('#birth_header').text("Birthday Offer");
    	} else if(product.is_bdayproduct == false){
    		
   			items = "<div class='item-slide'><img src='"+product.product_images[0].url+"'  style='height: 50%; width: 50%; object-fit: contain' onclick=\"viewProductDetails('"+product.id+"');\"/></div>";	
    	}
    	$('#birthday_products').append(items);
    });
});



var api;
api = "http://localhost:8055/amazon.com/webapi/CategoryController/rootcategories";

var myString="";
$.get(api , function(data, status){
   // alert("hello");
    
    myString = "<ul>";
    
		
    for(var i = 0; i < data.length; i++)
	{ 
   	 
    	myString = myString + "<li>";
    	myString=myString+"<a class='dropdown-toggle' id='"+data[i].cat_id+"' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false' onclick ='setCategory("+data[i].cat_id+");'  >"+data[i].categoryname+"</a>";
    	
    	var current_category = data[i].cat_id;
    	
    	myString = myString + getSubCat(current_category);
    	 
    	  myString = myString + "</li>";
    	 console.log(myString);
	}
    myString = myString + "</ul>";

    
    $('#shopbyCat').html(myString);
   // $('#addsupid').text(0);

		});






});

function getSubCat(catID)
{
	/*var api1="http://localhost:8055/amazon.com/webapi/CategoryController/categories/"+catID;
	
	var myString = "";
	$.get(api1 , function(data1, status){
	    alert("hello1"+myString);    	   
	    myString = myString + "<ul class='dropdown-menu'>";
	    for(var j = 0; j < data1.length; j++)
		{ 
	   	
	    	myString = myString + "<li>";
	    	myString=myString+"<a href='#'>"+data1[j].categoryname+"</a>";
	    	 
	    	 
	    	  myString = myString + "</li>";
	    	 console.log(myString);
		}
	    
	    myString = myString + "</ul>";

	});*/
	var myString = "";

	$.ajax({
	    url: "http://localhost:8055/amazon.com/webapi/CategoryController/categories/"+catID,
	    type: 'GET',
	    async: false,
	    success: function(data1) {
		    myString = myString + "<ul>";
		    for(var j = 0; j < data1.length; j++)
			{ 
		   	
		    	myString = myString + "<li>";
		    	myString=myString+"<a href='#'  onclick ='setCategory("+data1[j].cat_id+");' >"+data1[j].categoryname+"</a>";
		    	 
		    	 
		    	  myString = myString + "</li>";
		    	 console.log(myString);
			}
		    
		    myString = myString + "</ul>";
	    }
	});
	return myString;
}




</script>

</head>
<body>
<header class="section-header background-amazon">
<nav class="navbar navbar-expand-lg navbar-light">
  <div class="container row">
	<!-- Amazon Logo -->
	<div class="col-md-3">
  	<a class="navbar-brand" href="home.jsp"><img class="logo" src="images/logos/amazon.png" alt="alibaba style e-commerce html template file" title="alibaba e-commerce html css theme"></a>
    <a href="404.html"  data-toggle="popover" data-trigger="hover" data-title="Unlimited FREE fast delivery, videos, music & more" data-content="Prime members enjoy unlimited free, fast delivery on eligible product items, video streaming, exclusive access to deals and more." style="position:relative; left:-50px;top:10px;"><small>Try Prime</small></a>
	</div>
	<!-- Amazon Logo End -->
	
	<!-- Search Bar -->
    <div class="col-md-5" id="navbarTop">
		<form action="#" class="py-1">
				<div class="input-group w-100">
					<select class="custom-select"  name="category_name">
						<option value="">All type</option>
						<option value="">Special</option>
						<option value="">Only best</option>
						<option value="">Latest</option>
					</select>
				    <input type="text" class="form-control" style="width:50%;" placeholder="Search">
				    <div class="input-group-append">
				      <button class="btn btn-warning" type="submit">
				        <i class="fa fa-search"></i> Search 
				      </button>
				    </div>
			    </div>
		</form>
    </div>
	<!-- Search Bar End -->
	
	<!-- Small Ad -->
	<div class="col-md-4">
		<span class="pull-right"><img src="images/banners/nav_top_ad.jpg"/></span>
	</div>
	<!-- Small Ad End -->
  </div>
</nav>

<section class="header-main shadow-sm">
<div class="container">
<div class="row-sm align-items-center">
	
	
	<!-- Nav Bar Category & Address -->
	<div class="col-lg-7-24 col-sm-3">
	<div class="row">
		<div class="col-sm-6"><i class="fas fa-map-marker-alt"></i>
			<small id="deliverTo">Deliver Here</small></br>
			<b>Bengaluru 560010</b>
		</div>
		<div class="col-sm-5 category-wrap dropdown py-1">
		<button type="button" class="btn background-amazon  dropdown-toggle" data-toggle="dropdown" ><b> Shop By Categories</b></button>
		<div class="dropdown-menu" id="shopbyCat">
			<a id="Skin Care" class="dropdown-item" href="search.html" onclick="setCategory(this.id);" >Skin Care </a>
			<a class="dropdown-item" href="404.html">Hair and Others </a> 
		</div>
	</div>
	</div>
	 
	</div>
	<!-- Nav Bar Category End -->
	<div class="col-lg-10-24 col-sm-8 small text-light">
		 <a class="text-light text-margin" href="addAddress.html">My Address</a> <a  class="text-light text-margin" href="customer_orders.html" id="myAmazon">My Orders</a> <a  class="text-light text-margin"  href="404.html">Today's Deals</a> <a class="text-light text-margin" href="Bank.html">Amazon Pay</a> <a class="text-light text-margin" href="addprd.html">Sell</a> <a class="text-light text-margin" href="manage_sales.html">Customer Service</a>  	</div> <!-- col.// -->
	<div class="col-lg-7-24 col-sm-12">
		<div class="widgets-wrap float-right row no-gutters py-1">
			<div class="col-auto">
			<div class="widget-header dropdown">
				<a href="404.html" data-toggle="dropdown" data-offset="20,10">
					<div class="icontext">
						
						<div class="text-wrap text-light">
							<small> <div id="nameText">Hello, Sign In</div><b> Your Orders</b>
							<i class="fa fa-caret-down"></i> </small>	
						</div>
					</div>
				</a>
				<div class="dropdown-menu" style="width:200px; padding-bottom: 0px;">
				<ul style="padding:5px;">
					<li class="list-manager"><div class="form-group" id="signInButton"><a href="login.html"><button type="submit" class="btn btn-warning btn-block"> Sign In  </button></a></div></li>
					<li class="list-manager"><a href="https://www.amazon.in/gp/css/homepage.html/ref=nav_youraccount_ya">Your Account</a></li>
					<li class="list-manager"><a href="https://www.amazon.in/gp/css/order-history/ref=nav_youraccount_orders">Your Order</a></li>
					<li class="list-manager"><a href="https://www.amazon.in/gp/registry/wishlist/ref=nav_youraccount_wl?ie=UTF8&requiresSignIn=1">Your Wishlist</a></li>
					<li class="list-manager"><a href="https://www.amazon.in/gp/yourstore/ref=nav_youraccount_recs">Your Recommendations</a></li>
					<li class="list-manager"><a href="https://www.amazon.in/gp/primecentral/ref=nav_youraccount_prime">Your Prime Membership</a></li>
					<li class="list-manager"><a href="https://www.amazon.in/b/ref=nav_youraccount_dvm_crs_gat_in_tn_yraccount?_encoding=UTF8&node=10882806031">Your Prime Video</a></li>
					<li class="list-manager"><a href="https://www.amazon.in/gp/subscribe-and-save/manage/ref=nav_youraccount_sns">Your Subscribe & save items</a></li>
					<li class="list-manager"><a href="https://www.amazon.in/business?_encoding=UTF8&node=11476704031&ref_=nav_ya_flyout_b2b_reg">Your Amazon Business Account</a></li>
					<li class="list-manager"><a href="https://www.amazon.in/p2p/ref=nav_youraccount_sell">Your Seller Account</a></li>
					<li class="list-manager"><a href="https://www.amazon.in/gp/digital/fiona/manage/ref=nav_youraccount_myk">Manage your content and devices</a></li>
					<li class="list-manager"><a href="#" onclick="SignOut();">Sign Out</a></li>
					

				</ul>
			</div>  <!-- widget-header .// -->
			</div> <!-- col.// -->
			
			<a href="prime.html" class="widget-header">
					<div class="icontext">	
						<div class="text-wrap text-light">
						<small>
							Try <br/><b>Prime</b> <i class="fa fa-caret-down"></i> 
						</small>
						</div>
					</div>
				</a>
			</div>
			<div class="col-auto">
			<a href="wishlist.html" class="widget-header">
					<div class="icontext">	
					
						<div class="text-wrap text-light">
						<small>	Your <br/><b>Lists</b> <i class="fa fa-caret-down"></i> </small>	
						</div>
					</div>
				</a>
			</div>
			<div class="col-auto">
				<a href="check_out.html" class="widget-header">
					<div class="icontext">
												<div class="icon-wrap"><i class="text-light icon-sm  fa fa-shopping-cart"></i></div>
						<span class="small round badge badge-secondary">0</span>

						<div class="text-wrap text-light">						
						<small>						
						Cart </small>
						</div>
					</div>
				</a>
			</div> <!-- col.// -->
			 <!-- col.// -->
		</div> <!-- widgets-wrap.// row.// -->
	</div> <!-- col.// -->
</div> <!-- row.// -->
	</div> <!-- container.// -->
</section> <!-- header-main .// -->
</header> <!-- section-header.// -->

<!-- ========================= SECTION MAIN ========================= -->
<section class="section-main bg padding-y-sm">
<div class="container">

<div class="row row-sm">
	 <!-- col.// -->
	<div class="col-md-12">

		<div class=" fadeImg"  id="myAdverts" onclick=""></div>


<!-- ================= main slide ================= -->
<!-- ================= main slide ================= -->
<!--   
	<div class="item-slide">
		<img src="images/banners/slide1.jpg">
	</div>
</div> -->
<!-- ============== main slidesow .end // ============= -->

	</div> <!-- col.// -->
	
</div> <!-- row.// -->
<div class="row" style="height:200px;"></div>


</div> <!-- container .//  -->
</section>
<!-- ========================= SECTION MAIN END// ========================= -->




<!-- ========================= SECTION ITEMS ========================= -->
<section class="section-request bg padding-y-sm">
<div class="container">
<div class="row-sm">
<div class="col-md-3" id="bdayDisplay">
   
   
    <figure class=" card card-product" onclick="window.location.href='404.html';">
    <div class="card-header bg-white"><h4>Special Birthday Offer</h4></div>
        <div class="img-wrap"> <img src="http://localhost:8055/amazon.com/images/products/2.jpg"></div>
        <figcaption class="info-wrap">
            <small>Voice Control</small>
           
            <div class="price-wrap">
                <small><a href="404.html">Explore all</a></small>
            </div> <!-- price-wrap.// -->
           
        </figcaption>
    </figure> <!-- card // -->
   
</div> 

<div class="col-md-3" id="notbdayDisplay">
   
   
    <figure class=" card card-product" onclick="window.location.href='404.html';">
    <div class="card-header bg-white"><h4>Meet The New Amazon Echo</h4></div>
        <div class="img-wrap"> <img src="http://localhost:8055/amazon.com/images/categories/1.jpg"></div>
        <figcaption class="info-wrap">
            <small>Voice Control</small>
           
            <div class="price-wrap">
                <small><a href="404.html">Explore all</a></small>
            </div> <!-- price-wrap.// -->
           
        </figcaption>
    </figure> <!-- card // -->
   
</div> <!-- col // -->
<div class="col-md-3">
   
   
    <figure class=" card card-product" onclick="window.location.href='404.html';">
    <div class="card-header bg-white"><h4>Up to ?1,500 back*</h4></div>
        <div class="img-wrap"> <img src="images/categories/2.jpg"></div>
        <figcaption class="info-wrap">
            <small>On ICICI debit & credit card EMI. *T&C Apply</small>
           
            <div class="price-wrap">
                <small><a href="404.html">Know More</a></small>
            </div> <!-- price-wrap.// -->
           
        </figcaption>
    </figure> <!-- card // -->
   
</div> <!-- col // -->
<div class="col-md-3">
   
   
    <figure class=" card card-product" onclick="window.location.href='404.html';">
    <div class="card-header bg-white"><h4>Fire TV Stick</h4></div>
        <div class="img-wrap"> <img src="images/categories/3.jpg"></div>
        <figcaption class="info-wrap">
            <small>Stream movies, TV shows & more | ?3,999</small>
           
            <div class="price-wrap">
                <small><a href="404.html">Shop now</a></small>
            </div> <!-- price-wrap.// -->
           
        </figcaption>
    </figure> <!-- card // -->
   
</div> <!-- col // --><div class="col-md-3">
   
   
    <figure class=" card card-product" onclick="window.location.href='404.html';">
    <div class="card-header bg-white"><h4>Get Amazon Kindle</h4></div>
        <div class="img-wrap"> <img src="images/categories/4.jpg"></div>
        <figcaption class="info-wrap">
            <small>Read More Books</small>
           
            <div class="price-wrap">
                <small><a href="404.html">Buy now</a></small>
            </div> <!-- price-wrap.// -->
           
        </figcaption>
    </figure> <!-- card // -->
   
</div> <!-- col // --><div class="col-md-3">
   
   
    <figure class=" card card-product">
    <div class="card-header bg-white"><h4>EMI on Debit Card</h4></div>
        <div class="img-wrap"> <img src="images/categories/5.jpg"></div>
        <figcaption class="info-wrap">
            <small>Loan up to Rs 1 Lakh</small>
           
            <div class="price-wrap">
                <small><a href="404.html">Check Eligibility</a></small>
            </div> <!-- price-wrap.// -->
           
        </figcaption>
    </figure> <!-- card // -->
   
</div> <!-- col // --><div class="col-md-3">
   
   
    <figure class=" card card-product">
    <div class="card-header bg-white"><h4>Mobile Prepaid Recharges</h4></div>
        <div class="img-wrap"> <img src="images/categories/6.jpg"></div>
        <figcaption class="info-wrap">
            <small>Up to ?50 back</small>
           
            <div class="price-wrap">
                <small><a href="404.html">Recharge Now</a></small>
            </div> <!-- price-wrap.// -->
           
        </figcaption>
    </figure> <!-- card // -->
   
</div> <!-- col // --><div class="col-md-3">
   
   
    <figure class=" card card-product">
    <div class="card-header bg-white"><h4>All-New Kindle Paperwhite</h4></div>
        <div class="img-wrap"> <img src="images/categories/7.jpg"></div>
        <figcaption class="info-wrap">
            <small>Thinner, Lighter, Waterproof with 2X Storage - At ?12,999</small>
           
            <div class="price-wrap">
                <small><a href="404.html">Shop Now</a></small>
            </div> <!-- price-wrap.// -->
           
        </figcaption>
    </figure> <!-- card // -->
   
</div> <!-- col // --><div class="col-md-3">
   
   
    <figure class=" card card-product">
    <div class="card-header bg-white"><h4>New On Prime Video</h4></div>
        <div class="img-wrap"> <img src="images/categories/8.png"></div>
        <figcaption class="info-wrap">
            <small>*Redirects to PrimeVideo.com</small>
           
            <div class="price-wrap">
                <small><a href="404.html">Join Prime To watch</a></small>
            </div> <!-- price-wrap.// -->
           
        </figcaption>
    </figure> <!-- card // -->
   
</div> <!-- col // -->
<!-- col // -->
</div> <!-- row.// -->
 
 
</div><!-- container // -->
</section>
<!-- ========================= SECTION ITEMS .END// ========================= -->
<section class="section-main bg padding-y-sm">
<div class="container">
<div class="card">
    <div class="card-header bg-white" id="birth_header">Today's Deals <a href="404.html"><small>See All Details</small></a></div>
    <div class="card-body">
<div class="row row-sm">
   
    <div class="col-md-12">
 
<!-- ================= main slide ================= -->
<div class="owl-init slider-main owl-carousel" id="birthday_products" data-items="4" data-nav="true" data-dots="false">
    
    
   
</div>	
<!-- ============== main slidesow .end // ============= -->
 
    </div> <!-- col.// -->
</div> <!-- row.// -->
    </div> <!-- card-body .// -->
</div> <!-- card.// -->
 
 
</div> <!-- container .//  -->
</section>
<!-- ========================= SECTION RECCOMENDATIONS .END// =============== -->
 
<!-- ========================= SECTION RECCOMENDATIONS ====================== -->
<section class="section-main bg padding-y-sm">
<div class="container">
<div class="card">
    <div class="card-header bg-white">Inspired by your browsing history</div>
    <div class="card-body">
<div class="row row-sm">
   
    <div class="col-md-12">
 
<!-- ================= main slide ================= -->
<div class="owl-init slider-main owl-carousel" data-items="4" data-nav="true" data-dots="false">
    <div class="item-slide">
        <img src="images/products/1.jpg"  style='height: 50%; width: 50%; object-fit: contain' />
    </div>
    <div class="item-slide">
        <img src="images/products/2.jpg"  style='height: 50%; width: 50%; object-fit: contain' />
    </div>
    <div class="item-slide">
        <img src="images/products/3.jpg"  style='height: 50%; width: 50%; object-fit: contain' />
    </div>
    <div class="item-slide">
        <img src="images/products/4.jpg"  style='height: 50%; width: 50%; object-fit: contain' />
    </div>
    <div class="item-slide">
        <img src="images/products/5.jpg"  style='height: 50%; width: 50%; object-fit: contain' />
    </div>
    <div class="item-slide">
        <img src="images/products/6.jpg"  style='height: 50%; width: 50%; object-fit: contain' />
    </div>
    <div class="item-slide">
        <img src="images/products/7.jpg"  style='height: 50%; width: 50%; object-fit: contain' />
    </div>
    <div class="item-slide">
        <img src="images/products/8.jpg"  style='height: 50%; width: 50%; object-fit: contain' />
    </div>
   
</div>
<!-- ============== main slidesow .end // ============= -->
 
    </div> <!-- col.// -->
</div> <!-- row.// -->
    </div> <!-- card-body .// -->
</div> <!-- card.// -->
 
 
</div> <!-- container .//  -->
</section>
<!-- ========================= SECTION RECCOMENDATIONS .END// =============== -->
 
<!-- ========================= SECTION RECCOMENDATIONS ====================== -->
<section class="section-main bg padding-y-sm">
<div class="container">
<div class="card">
    <div class="card-header bg-white">Related to Items You Viewed</div>
    <div class="card-body">
<div class="row row-sm">
   
    <div class="col-md-12">
 
<!-- ================= main slide ================= -->
<div class="owl-init slider-main owl-carousel" data-items="4" data-nav="true" data-dots="false">
    <div class="item-slide">
        <img src="images/products/1.jpg"  style='height: 50%; width: 50%; object-fit: contain' />
    </div>
    <div class="item-slide">
        <img src="images/products/2.jpg"  style='height: 50%; width: 50%; object-fit: contain' />
    </div>
    <div class="item-slide">
        <img src="images/products/3.jpg"  style='height: 50%; width: 50%; object-fit: contain' />
    </div>
    <div class="item-slide">
        <img src="images/products/4.jpg"  style='height: 50%; width: 50%; object-fit: contain' />
    </div>
    <div class="item-slide">
        <img src="images/products/5.jpg"  style='height: 50%; width: 50%; object-fit: contain' />
    </div>
    <div class="item-slide">
        <img src="images/products/6.jpg"  style='height: 50%; width: 50%; object-fit: contain' />
    </div>
    <div class="item-slide">
        <img src="images/products/7.jpg"  style='height: 50%; width: 50%; object-fit: contain' />
    </div>
    <div class="item-slide">
        <img src="images/products/8.jpg"  style='height: 50%; width: 50%; object-fit: contain' />
    </div>
   
</div>
<!-- ============== main slidesow .end // ============= -->
 
    </div> <!-- col.// -->
</div> <!-- row.// -->
    </div> <!-- card-body .// -->
</div> <!-- card.// -->
 
 
</div> <!-- container .//  -->
</section>
<!-- ========================= SECTION RECCOMENDATIONS .END// =============== -->
 
 
<!-- ========================= SECTION RECCOMENDATIONS ====================== -->
<section class="section-main bg padding-y-sm">
<div class="container">
<div class="card">
    <div class="card-header bg-white">Inspired by wishlist</div>
    <div class="card-body">
<div class="row row-sm">
   
    <div class="col-md-12">
 
<!-- ================= main slide ================= -->
<div class="owl-init slider-main owl-carousel" data-items="4" data-nav="true" data-dots="false">
    <div class="item-slide">
        <img src="images/products/1.jpg"  style='height: 50%; width: 50%; object-fit: contain' />
       
    </div>
    <div class="item-slide">
        <img src="images/products/2.jpg"  style='height: 50%; width: 50%; object-fit: contain' />
    </div>
    <div class="item-slide">
        <img src="images/products/3.jpg"  style='height: 50%; width: 50%; object-fit: contain' />
    </div>
    <div class="item-slide">
        <img src="images/products/4.jpg"  style='height: 50%; width: 50%; object-fit: contain' />
    </div>
    <div class="item-slide">
        <img src="images/products/5.jpg"  style='height: 50%; width: 50%; object-fit: contain' />
    </div>
    <div class="item-slide">
        <img src="images/products/6.jpg"  style='height: 50%; width: 50%; object-fit: contain' />
    </div>
    <div class="item-slide">
        <img src="images/products/7.jpg"  style='height: 50%; width: 50%; object-fit: contain' />
    </div>
    <div class="item-slide">
        <img src="images/products/8.jpg"  style='height: 50%; width: 50%; object-fit: contain' />
    </div>
   
</div>
<!-- ============== main slidesow .end // ============= -->
 
    </div> <!-- col.// -->
</div> <!-- row.// -->
    </div> <!-- card-body .// -->
</div> <!-- card.// -->
 
 
</div> <!-- container .//  -->
</section>
<!-- ========================= SECTION RECCOMENDATIONS .END// =============== -->
 

<!-- ========================= FOOTER ========================= -->
<footer class="section-footer background-amazon">
	<div class="container">
		<section class="footer-top padding-top">
			<div class="row">
				<aside class="col-sm-3"></aside>
				<aside class="col-sm-3  col-md-3 white">
					<h5 class="title">My Account</h5>
					<ul class="list-unstyled">
						<li> <a href="404.html"> User Login </a></li>
						<li> <a href="404.html"> User register </a></li>
						<li> <a href="404.html"> Account Setting </a></li>
						<li> <a href="404.html"> My Orders </a></li>
						<li> <a href="404.html"> My Wishlist </a></li>
					</ul>
				</aside>
				
				<aside class="col-sm-3">
					<article class="white">
						<h5 class="title">Contacts</h5>
						<p>
							<strong>Phone: </strong> +123456789 <br> 
						    <strong>Fax:</strong> +123456789
						</p>

						 <div class="btn-group white">
						    <a class="btn btn-facebook" title="Facebook" target="_blank" href="404.html"><i class="fab fa-facebook-f  fa-fw"></i></a>
						    <a class="btn btn-instagram" title="Instagram" target="_blank" href="404.html"><i class="fab fa-instagram  fa-fw"></i></a>
						    <a class="btn btn-youtube" title="Youtube" target="_blank" href="404.html"><i class="fab fa-youtube  fa-fw"></i></a>
						    <a class="btn btn-twitter" title="Twitter" target="_blank" href="404.html"><i class="fab fa-twitter  fa-fw"></i></a>
						</div>
					</article>
				</aside>
			</div> <!-- row.// -->
			<br> 
		</section>
		
	</div><!-- //container -->
</footer>
<!-- ========================= FOOTER END // ========================= -->

<script src="scripts/default.js" type="text/javascript"></script>

</body>

</html>