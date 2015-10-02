$(document).ready(function(){
	$(document).on('change', '#type_article', function() { // Lorsque l'on clique sur le select type
		if($(this).val() == "stock"){ // Select stock
			$.ajax({
				url : 'ajax/getAllArticles',
				type : 'POST',
				data: {'cmp': 1 },
				success : function(data) {
					$("#article_name").html(data); // On affiche le select des articles
				}
			});
		}else if($(this).val() == "autre"){ // Select autre
			$("#article_name").html("<input type='text' name='name_article' id='name_article' />");
			$("#prix_ht_achat_article input").attr("value", "");
			$("#prix_ht_achat_article input").attr("readonly",false);
			$("#prix_ht_vente_article input").attr("value", "");
			$("#prix_ht_vente_article input").attr("readonly",false);
			$("#tva_article input").attr("value", "");
			$("#tva_article input").attr("readonly",false);
		}
	});	
	
	$(document).on('change', '.select_article_name', function() { // Quand on choisit l'article voulu on affiche ses informations dans les différents td
		$.ajax({
			url : 'ajax/getArticleByOne',
			type : 'POST',
			dataType: "json",
			data: {'designation': $(this).val() },
			success : function(data) {
				$("#prix_ht_achat_article input").attr("value", data.prix_ht_achat);
				$("#prix_ht_achat_article input").attr("readonly",true);
				$("#prix_ht_vente_article input").attr("value", data.prix_ht);
				$("#prix_ht_vente_article input").attr("readonly",true);
				$("#tva_article input").attr("value", data.tva);
				$("#tva_article input").attr("readonly",true);
			}
		});
	});
});