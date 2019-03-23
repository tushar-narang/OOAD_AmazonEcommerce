function addNewCard() {
	var form = $('#cardAdd')[0];
    var data = new FormData(form);
    
    var udata = JSON.parse(localStorage.getItem("userdata"));
    
    $.ajax({
    	type: "POST",
		enctype: 'multipart/form-data',
        url: "http://localhost:8055/amazon.com/webapi/CardController/addCard/"+udata.id,
        data: data, 
       	processData: false,
        contentType: false,
        cache: false,
        async: true,
        timeout: 600000,
        
        success:function(result){
        	if(result == "0") {
        		$('#card-pane').removeClass().addClass("alert alert-danger");
        		$('#card-pane').text("Card Already Exists");
        	} else {
        		$('#card-pane').removeClass().addClass("alert alert-success");
        		$('#card-pane').text("Successfully Added The Card");
        	}
        	//window.alert(result);
        	//window.location.href="http://localhost:8055/amazon.com/";
        	//location.reload(true);
        }
    });
    
}


function displayCards() {
    var udata = JSON.parse(localStorage.getItem("userdata"));
	var api = "http://localhost:8055/amazon.com/webapi/CardController/getCards/"+udata.id;
	

	//Ajax Call for getting individual product
	$.get(api , function(data, status){
	    var cards = data;
	    	    var text = "";
	    cards.forEach(function(card) {
	    	
	    	text = text + "<form  name='myform' id='card_form_"+card.cardno+"'><tr><td>"+card.cardno+"</td>";
	    	text = text + "<td>"+card.cvv+"</td>";
	    	text = text + "<td>INR "+card.balance+"</td>";
	    	text = text + "<td><div class='price-wrap'><input type='numeric' id='card_no_"+card.cardno+"' name='amount' class='form-control'/></div></td>";
	    	text = text + "<td class='text-right'><div class='btn btn-outline-success' type='button' onClick=\"addMoney('"+card.cardno+"');\"> Add Money</div></td></tr></form>";	

	    	//console.log(text);
	    	
	    });
    	$('#cardHolder').html(text);

	    
	});
	console.log("Here");
	api = "http://localhost:8055/amazon.com/webapi/UserController/getAmountOwed/"+udata.id;
	console.log("Here");	
	$.get(api , function(data, status){
		console.log(data);
		$('#userBalance').html("Amazon Owes You: "+ data);
	    	
	    });
    	
	    

	
	
}

function addMoney(card_no) {
	console.log(card_no);
    var amount = $('#card_no_'+card_no).val();
    
    var api = "http://localhost:8055/amazon.com/webapi/CardController/addBalance/"+card_no+"/"+amount;
    
    $.get(api , function(data, status){
	    
	    
    	if(data == "0") {
    		$('#card-info').removeClass().addClass("alert alert-danger");
    		$('#card-info').text("Card Already Exists");
    	} else {
    		alert("Successfully Added");
        	//window.location.href="http://localhost:8055/amazon.com/";
        	location.reload(true);
    	}

	    
	});
    
}

function moneyOwed(moneyOwed) {
	
}