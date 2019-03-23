$(document).ready(function(){
    var udata = JSON.parse(localStorage.getItem("userdata"));

	var api = "http://localhost:8055/amazon.com/webapi/AddressController/customeraddress/"+udata.id;
	
	
	$.get(api , function(data, status){
		console.log(data);
		data.forEach(function(item) {
			var temp = "<tr><td>"+item.addressline1+", "+ item.addressline2 +", "+item.street+", "+ item.city +", "+item.pincode+"</td>";
			var btnInfo = "<td><button class='btn btn-primary' onclick=\"shipHere('"+item.addressline1+"','"+item.addressline2+"','"+item.street+"','"+item.city+"','"+item.pincode+"');\">Ship Here!</button></td></tr>";
			$('#address').append(temp + btnInfo);
		});
	});
});


function shipHere(addressline1, addressline2, street, city, pincode) {
	var data = new FormData();
	data.append('addressline1', addressline1);
	data.append('addressline2', addressline2);
	data.append('street', street);
	data.append('city', city);
	data.append('pincode', pincode);
	
	var data2 = addressline1 + ", " + addressline2 + ", " +  street + ", " + city + ", " + pincode;
	localStorage.setItem("shippingAddress", data2);
	window.location.href = "payment.html";
}