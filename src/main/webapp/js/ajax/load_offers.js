//Creating A Wrapper For The Product
function makeCarousel(data) {
	var template = '<div class="owl-init slider-main owl-carousel owl-loaded owl-drag" data-items="4" data-nav="true" data-dots="false"><div class="owl-stage-outer"><div class="owl-stage" style="transform: translate3d(-1430px, 0px, 0px); transition: all 0s ease 0s; width: 5720px;">';
	template  = template + data;
	template = template + '</div></div></div>';
	template = template +'<div class="owl-nav"><button type="button" role="presentation" class="owl-prev"><i class="fa fa-chevron-left"></i></button><button type="button" role="presentation" class="owl-next"><i class="fa fa-chevron-right"></i></button></div>';
	return template;
}

//Insert The Product Images
function createProduct(data, activity) {
	var template = "<div class='owl-item "+activity+"' style='width: 357.5px;'><div class='item-slide'>";
	template = template + "<img src='"+data.product_images[0].url+"' style='height: 50%; width: 50%; object-fit: contain; display:block;' />";
	template = template + "</div></div>";
	return template;
}
$(document).ready(function() {
	
	$.ajax({
	    url : "http://localhost:8055/amazon.com/webapi/ProductController/products",
	    type : "GET",
	    async: true,
	    success : function(products) {
	    	var innerText;
	    	//Create All Internal Images
	    	var count = 0;
	    	products.forEach(function(item) {
	    		count++;
	    		if(count > 4) {
	    			innerText = innerText + createProduct(item, "cloned");	
	    		} else {
	    			innerText = innerText + createProduct(item, "active");	
	    		}
	    		
	    	});
	    	//Insert Image To Carosel
	    	var carouselData = makeCarousel(innerText);
	    	var temp = "<div class='owl-init slider-main owl-carousel owl-loaded owl-drag' data-items='4' data-nav='true' data-dots='false'><div class='owl-stage-outer'><div class='owl-stage' style='transform: translate3d(-2860px, 0px, 0px); transition: all 0.25s ease 0s; width: 5720px;'><div class='owl-item cloned' style='width: 357.5px;'><div class='item-slide'><img src='images/products/5.jpg' style='height: 50%; width: 50%; object-fit: contain'></div></div><div class='owl-item cloned' style='width: 357.5px;'><div class='item-slide'><img src='images/products/6.jpg' style='height: 50%; width: 50%; object-fit: contain'></div></div><div class='owl-item cloned' style='width: 357.5px;'><div class='item-slide'><img src='images/products/7.jpg' style='height: 50%; width: 50%; object-fit: contain'></div></div><div class='owl-item cloned' style='width: 357.5px;'><div class='item-slide'><img src='images/products/8.jpg' style='height: 50%; width: 50%; object-fit: contain'></div></div><div class='owl-item' style='width: 357.5px;'><div class='item-slide'><img src='images/products/1.jpg' style='height: 50%; width: 50%; object-fit: contain'></div></div><div class='owl-item' style='width: 357.5px;'><div class='item-slide'><img src='images/products/2.jpg' style='height: 50%; width: 50%; object-fit: contain'></div></div><div class='owl-item' style='width: 357.5px;'><div class='item-slide'><img src='images/products/3.jpg' style='height: 50%; width: 50%; object-fit: contain'></div></div><div class='owl-item' style='width: 357.5px;'><div class='item-slide'><img src='images/products/4.jpg' style='height: 50%; width: 50%; object-fit: contain'></div></div><div class='owl-item active' style='width: 357.5px;'><div class='item-slide'><img src='images/products/5.jpg' style='height: 50%; width: 50%; object-fit: contain'></div></div><div class='owl-item active' style='width: 357.5px;'><div class='item-slide'><img src='images/products/6.jpg' style='height: 50%; width: 50%; object-fit: contain'></div></div><div class='owl-item active' style='width: 357.5px;'><div class='item-slide'><img src='images/products/7.jpg' style='height: 50%; width: 50%; object-fit: contain'></div></div><div class='owl-item active' style='width: 357.5px;'><div class='item-slide'><img src='images/products/8.jpg' style='height: 50%; width: 50%; object-fit: contain'></div></div><div class='owl-item cloned' style='width: 357.5px;'><div class='item-slide'><img src='images/products/1.jpg' style='height: 50%; width: 50%; object-fit: contain'></div></div><div class='owl-item cloned' style='width: 357.5px;'><div class='item-slide'><img src='images/products/2.jpg' style='height: 50%; width: 50%; object-fit: contain'></div></div><div class='owl-item cloned' style='width: 357.5px;'><div class='item-slide'><img src='images/products/3.jpg' style='height: 50%; width: 50%; object-fit: contain'></div></div><div class='owl-item cloned' style='width: 357.5px;'><div class='item-slide'><img src='images/products/4.jpg' style='height: 50%; width: 50%; object-fit: contain'></div></div></div></div><div class='owl-nav'><button type='button' role='presentation' class='owl-prev'><i class='fa fa-chevron-left'></i></button><button type='button' role='presentation' class='owl-next'><i class='fa fa-chevron-right'></i></button></div><div class='owl-dots disabled'></div></div>";
	    	//Insert Carosel To Today's Deals Div
	    	$('#TodaysDeals').html(temp);
	    	$('.owl-carousel').owlCarousel('refresh');

	    },
	    error: function() {
	       console.log("Some Error");
	    }
	 });
	
	
});