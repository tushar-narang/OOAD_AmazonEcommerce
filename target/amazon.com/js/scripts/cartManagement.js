function displayCart(){
	
	if(localStorage.getItem("directBuy") != null) {
		var api = "http://localhost:8055/amazon.com/webapi/ProductController/productsById/"+localStorage.getItem("cartProduct");
		var product;

		//Ajax Call for getting individual product
		$.get(api , function(data, status){
		    product = data[0];
		    console.log(product);
		    
		    var udata = JSON.parse(localStorage.getItem("userdata"));

		    var responsebirthdate = new Date(udata.dob);
			var currentdate = new Date();
			var discountedprice;
			if(currentdate.getMonth() == responsebirthdate.getMonth()+1 &&  currentdate.getDate() == responsebirthdate.getDate() ){
				discountedprice = product.bdayprice;
			}else{
				discountedprice = product.discountedprice;
			}
		    
		    $('#cartItems').append(generateItem(product.product_images[0].url, product.productname, product.id, localStorage.getItem("cartProductQty"), discountedprice));
		    var discount = (product.price - discountedprice)*parseInt(localStorage.getItem("cartProductQty"));
		    
		    $('#discount').html(discount);
		    $('#price').html(discountedprice*localStorage.getItem("cartProductQty"));
		});
		
	} else {
		window.location.href = "404.html";
	}
}


function generateItem(productImageUrl, productName, productId, productQuantity, productPrice) {
	var myItem = "<tr><td><figure class='media'><div class='img-wrap'>";
	myItem = myItem + "<img src='"+ productImageUrl+ "' class='img-thumbnail img-sm'>";
	myItem = myItem + "</div><figcaption class='media-body'>";
	myItem = myItem + "<h6 class='title text-truncate'>"+productName+"</h6>";
	myItem = myItem + "</figcaption></figure></td><td>";
	myItem = myItem + "<strong>"+productQuantity+"</strong>";
	myItem = myItem + "</td><td><div class='price-wrap'><var class='price'>INR "+productPrice*productQuantity+"</var>"; 
	myItem = myItem + "</div></td><td class='text-right'><a href='#' onclick='removeProduct();' class='btn btn-outline-danger'> × Remove</a></td></tr>";
	return myItem;
}

function removeProduct() {
	localStorage.removeItem("cartProductQty");
	localStorage.removeItem("cartProduct");
	localStorage.removeItem("directBuy");
	window.location.href="home.jsp";

}

function additemintocart (product_id , product_quantity , emailid) {
	
	var api = "http://localhost:8055/amazon.com/webapi/ProductController/addproductincart/"+product_id+"/"+product_quantity+"/"+emailid;
	
	
}

