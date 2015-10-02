$(document).ready(function(){
	var cmpToHideShow = -1;
	var cmpToHideShowFacture = -1;
	
	$("#bon_de_livraison_child").show();
	$("#facture_child").show();
	
	$("#bon_de_livraison_parent").click(function(){
		cmpToHideShow++;
		if(cmpToHideShow % 2 == 0){
			$("#bon_de_livraison_child").show();
		}else{
			$("#bon_de_livraison_child").hide();
		}
	});
	
	$("#facture_parent").click(function(){
		cmpToHideShowFacture++;
		if(cmpToHideShowFacture % 2 == 0){
			$("#facture_child").show();
		}else{
			$("#facture_child").hide();
		}
	});
	
	/*** SET DATE *****/
	
	setDateDefault();
	$(function() {
		$(".datepicker").datepicker({
			dateFormat : 'dd-mm-yy'
		});
	});	
	function setDateDefault() {
		var date = new Date();
		var day = date.getDate() + 1;
		var month = date.getMonth() + 1;
		var year = date.getFullYear();
		if (month < 10)
			month = "0" + month;
		if (day < 10)
			day = "0" + day;
		var today = day + "-" + month + "-" + year;
		$('.datepicker').value = today;
	}
});