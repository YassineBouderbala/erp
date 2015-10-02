$(document).ready(function(){
	var cmp = 1;
	var cmpToWrite = 1;
		
	$(function() { //choix de la date
	    $( "#datepicker" ).datepicker({
	    	dateFormat: 'dd-mm-yy'
	    });
	});
	
	$(document).on('change', '.type', function() { // Lorsque l'on clique sur le select type
		cmpToWrite =$(this).attr('id');
		if($(this).val() == "Matériel"){ // Select Matériel
			$.ajax({
				url : 'ajax/getAllArticles',
				type : 'POST',
				data: {'cmp': cmpToWrite },
				success : function(data) {
					$("#td_description_"+cmpToWrite+"").html(data); // On affiche le select des articles
				}
			});
		}else if($(this).val() == "Heure"){ // Select Heure
			$("#td_description_"+cmpToWrite+"").html("<input type='text' name='designation' autocomplete='off' required/>");
			$("#td_reference_"+cmpToWrite+"").html("<input name='reference' value='' autocomplete='off' readonly />");
			$("#td_quantite_"+cmpToWrite+"").html("<input class='input_quantity' id='"+cmpToWrite+"' name='quantity' type='text' autocomplete='off' required/>");
			$("#td_tva_"+cmpToWrite+"").html("<input name='tva' autocomplete='off'/>");
			$("#td_prix_ht_"+cmpToWrite+"").html("<input id='input_prix_ht_"+cmpToWrite+"' name='prix_ht' value='' autocomplete='off' required/>");
			$("#td_total_ht_"+cmpToWrite+"").html("<input id='input_total_ht_"+cmpToWrite+"' name='total_ht' value='' type='text' autocomplete='off' required readonly/><b id='deline' class='tref"+cmpToWrite+"' style='cursor:pointer;'> Supprimer la ligne</b>");
		}
	});
	
	$(document).on('change', '.select_designation', function() { // Quand on choisit l'article voulu on affiche ses informations dans les différents td
		cmpToWrite =$(this).attr('id');
		$.ajax({
			url : 'ajax/getArticleByOne',
			type : 'POST',
			dataType: "json",
			data: {'designation': $(this).val() },
			success : function(data) {
				$("#td_reference_"+cmpToWrite+"").html("<input name='reference' value='"+data.reference+"' autocomplete='off'  readonly/>");
				$("#td_quantite_"+cmpToWrite+"").html("<input class='input_quantity' id='"+cmpToWrite+"' name='quantity' type='text' autocomplete='off' required/>");
				$("#td_tva_"+cmpToWrite+"").html("<input name='tva' value='"+data.tva+"' autocomplete='off' readonly />");
				$("#td_prix_ht_"+cmpToWrite+"").html("<input id='input_prix_ht_"+cmpToWrite+"' name='prix_ht' value='"+data.prix_ht+"' autocomplete='off' required readonly />");
				$("#td_total_ht_"+cmpToWrite+"").html("<input id='input_total_ht_"+cmpToWrite+"' name='total_ht' value='' type='text' autocomplete='off' required readonly/><b id='deline' class='tref"+cmpToWrite+"' style='cursor:pointer;'> Supprimer la ligne</b>");
			}
		});
	});
	
	$(document).on('keydown', '.input_quantity', function(e) { // Des qu'on appuye sur tab dans l'input quantity on calcule le prix total
		if (e.keyCode == 9) {
			cmpToWrite =$(this).attr('id');
			$("#input_total_ht_"+cmpToWrite+"").attr('value',$(this).val() * $("#input_prix_ht_"+cmpToWrite+"").val());
		}		
	});
	
	$(document).on('click', '.insert_line', function() { // Ajout d'une ligne
		cmp++;
		$("#table_devis").append("<tr class='tref"+cmp+"'> <td> <select class='type' id='"+cmp+"' name='type'> <option value=''></option> <option value='Matériel'>Matériel</option> <option value='Heure'>Heure</option> </select> </td> <td id='td_description_"+cmp+"'></td> <td id='td_reference_"+cmp+"'></td> <td id='td_quantite_"+cmp+"'></td> <td id='td_tva_"+cmp+"'></td> <td id='td_prix_ht_"+cmp+"'></td> <td id='td_total_ht_"+cmp+"'></td> </tr>");
	});
	
	$(document).on('click','#deline',function(){ // Suppression d'une ligne
		$('.'+$(this).attr("class")+'').remove();
	});

});