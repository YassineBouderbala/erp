$(document).ready(function(){	
	$('#textarea_devis').keydown(function (e){
	    if(e.keyCode == 13){
	    	$(this).val($(this).val()+"<br/>")
	    }
	})
	$('#textarea_facture').keydown(function (e){
	    if(e.keyCode == 13){
	    	$(this).val($(this).val()+"<br/>")
	    }
	})
});