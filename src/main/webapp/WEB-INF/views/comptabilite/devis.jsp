<%@page import="com.erp.myapp.security.Path"%>
<%@page import="com.erp.myapp.utils.Avoir_Util"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Devis n° ${devis.reference}</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/jquery/jquery-ui.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/comptabilite/devis.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/bootstrap/bootstrap.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap/bootstrap.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery/jquery-ui.js"></script>
<style type='text/css'>
body {
	width: 100%;
}

#page_englobe {
	width: 1200px;
	//border: 1px solid black;
	margin: auto;
}

#part_devis {
	width: 950px;
	float: left;
	border: 1px solid black;
}

#part_config {
	width: 240px;
	height: 350px;
	border: 1px solid black;;
	float: right;
}

#table_general {
	width: 98%;
	height: 0px;;
}

.part1 {
	height: 200px;
}

.part2 {
	height: 180px;
}

.part3 {
	height: 85px;
}

.part4 {
	height: 0px;
}

.part5 {
	height: 280px;
}

.part6 {
	height: 40px;
}

#logo {
	width: 400px;
	height: 195px;
	float: left;
}

#logo img {
	height: 100%;
	width: 100%;
}

#details_header {
	float: left;
}

#details_header {
	font-size: 20px;
	padding-top: 25px;
}

#information_document {
	width: 345px;
	height: 168px;
	float: left;
	padding-top: 14px;
}

#information_client {
	font-size: 18px;
	float: left;
	padding-top: 20px;
}

#table_information_client {
	width: 480px;
	height: 110px;
	border-width: 1px;
	border-style: solid;
	border-color: black;
}

.table_information_validity_td {
	border-width: 1px;
	border-style: solid;
	border-color: black;
	height: 35px;
	width: 200px;
	text-align: center;
}

#table_information_validity {
	border-collapse: collapse;
}

#puch_validity {
	float: left;
}

#information_validity {
	float: left;
}

#table_information_generale_devis {
	border-collapse: collapse;
}

.table_information_generale_devis_td {
	text-align: center;
	height: 0px;
	padding-top: 10px;
	padding-bottom: 10px;
	border-width: 1px;
	border-style: solid;
	border-color: black;
}

#information_generale_devis {
	float: left;
}

#push_information_generale_devis {
	float: left;
	width: 0px;
}

#before_footer {
	width: 300px;
	float: left;
	height: 260px;
}

#header_table {
	background-color: #303233;
	color: white;
}

#color_header {
	color: #303233;
}

#part_config a {
	color: black;
	font-weight:bold;
}
#part_config p {
	margin-left: 50px;
}
</style>
</head>
<body>
	<c:set var="Path_comptabilite" value="<%=Path.comptabilite%>"></c:set>
	<c:set var="Path_delivery" value="<%=Path.delivery%>"></c:set>
	<div id="page_englobe">
		<div id="part_devis">
			<table id='table_general'>
				<tr>
					<td class='part2'>
						<div id='information_document'>
							<h2 id='color_header'>DEVIS</h2>
							<h3>
								N° <b>${devis.reference}</b>
							</h3>
						</div>
						<div id='information_client'>
							<table id='table_information_client'>
								<tr>
									<td style='font-size: 30px; text-align: center;'>${devis.customer.name}</td>
								</tr>
								<tr>
									<td style='text-align: center;'>${devis.customer.address}</td>
								</tr>
								<tr>
									<td style='text-align: center;'>${devis.customer.code_postal}
										${devis.customer.city}, ${devis.customer.country}</td>
								</tr>
							</table>
						</div>
					</td>
				</tr>
				<tr>
					<td class='part3'>
						<div id='puch_validity' style='width: 17%;'>
							<span>&nbsp;</span>
						</div>
						<div id='information_validity'>
							<table id='table_information_validity'>
								<tr>
									<td id='header_table' class='table_information_validity_td'>DATE</td>
									<td id='header_table' class='table_information_validity_td'>VALIDITE</td>
									<td id='header_table' class='table_information_validity_td'>Réf
										client</td>
								</tr>
								<tr>
									<td class='table_information_validity_td'>${devis.date}</td>
									<td class='table_information_validity_td'>${devis.date_validity}</td>
									<td class='table_information_validity_td'>${devis.customer.reference}</td>
								</tr>
							</table>
						</div>
					</td>
				</tr>
				<tr>
					<td class='part4'>
						<div id='push_information_generale_devis'>
							<span>&nbsp;</span>
						</div>
						<div id='information_generale_devis'>
							<table id='table_information_generale_devis'>
								<tr>
								<tr>
									<td id='header_table'
										class='table_information_generale_devis_td'
										style='width: 100px;'>Type</td>
									<td id='header_table'
										class='table_information_generale_devis_td'
										style='width: 130px;'>Référence</td>
									<td id='header_table'
										class='table_information_generale_devis_td'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;DESCRIPTION&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
									<td id='header_table'
										class='table_information_generale_devis_td'
										style='width: 110px;'>Quantité</td>
									<td id='header_table'
										class='table_information_generale_devis_td'
										style='width: 100px;'>Tva(%)</td>
									<td id='header_table'
										class='table_information_generale_devis_td'
										style='width: 130px;'>Prix ht</td>
									<td id='header_table'
										class='table_information_generale_devis_td'
										style='width: 130px;'>Total ht</td>
									<td id='header_table'
										class='table_information_generale_devis_td'
										style='width: 130px;'>Qté en stock</td>
								</tr>
								<c:forEach items="${devis.line}" var="line">
									<tr>
										<td class='table_information_generale_devis_td'>${line.type}</td>
										<td class='table_information_generale_devis_td'>${line.reference}</td>
										<td class='table_information_generale_devis_td'>${line.description}</td>
										<td class='table_information_generale_devis_td'>${line.quantity}</td>
										<td class='table_information_generale_devis_td'>${line.tva}</td>
										<td class='table_information_generale_devis_td'>${line.prix_ht}</td>
										<td class='table_information_generale_devis_td'>${line.total_ht}</td>
										<td class='table_information_generale_devis_td'
											style='color: red'>${line.article.quantite}</td>
									</tr>
								</c:forEach>
								<tr>
									<td class=''></td>
									<td class=''></td>
									<td class=''></td>
									<td class=''></td>
									<td class=''></td>
									<td class='table_information_generale_devis_td'
										id='header_table'>Total HT</td>
									<td class='table_information_generale_devis_td'>${devis.total.total_ht}</td>
									<td class=''></td>
								</tr>
								<tr>
									<td class=''></td>
									<td class=''></td>
									<td class=''></td>
									<td class=''></td>
									<td class=''></td>
									<td class='table_information_generale_devis_td'
										id='header_table'>Montant TVA</td>
									<td class='table_information_generale_devis_td'>${devis.total.tva}</td>
									<td class=''></td>
								</tr>
								<tr>
									<td class=''></td>
									<td class=''></td>
									<td class=''></td>
									<td class=''></td>
									<td class=''></td>
									<td class='table_information_generale_devis_td'
										id='header_table'>Total TTC</td>
									<td class='table_information_generale_devis_td'>${devis.total.total_ttc}</td>
									<td class=''></td>
								</tr>
								<tr>
									<td class=''></td>
									<td class=''></td>
									<td class=''></td>
									<td class=''></td>
									<td class=''></td>
									<td class='table_information_generale_devis_td'
										id='header_table'>A Payer</td>
									<td class='table_information_generale_devis_td'>${devis.total.total_ttc}</td>
									<td class=''></td>
								</tr>
							</table>
						</div>
					</td>
				</tr>
				<c:if test="${not empty listArticleStock}">
					<tr>
						<td class='part5'>
							<h3 style="color: red;">Impossible de valider le devis</h3>
							<c:forEach
								var="line" items="${listArticleStock}">
								<p>Quantité de <b>${line.article.designation}</b> en stock insuffisant. <b>${line.article.designation}</b> restants : ${line.article.quantite}, voulu : ${line.quantity}</p>
							</c:forEach>
						</td>
					</tr>
				</c:if>
			</table>
		</div>
		<div id="part_config">
			<c:choose>
    			<c:when test="${devis.valid == false}">
        			<p><a id="valid_devis" href="<%=request.getContextPath()%>${Path_comptabilite}/valid_devis?refDevis=${devis.guid}">Valider le devis</a><p>
        			<p><a href="<%=request.getContextPath()%>${Path_comptabilite}/pdf_create_devis?refDevis=${devis.guid}" target="_blank">Devis au format PDF</a><p>
        			<p><a href="<%=request.getContextPath()%>${Path_comptabilite}/modify_devis?refDevis=${devis.guid}">Modifier le Devis</a><p>    			
    			</c:when>
    			<c:otherwise>
					<c:if test="${empty devis.facture.id}">
						<p><a id="valid_devis" href="<%=request.getContextPath()%>${Path_comptabilite}/unvalid_devis?refDevis=${devis.guid}">Annuler le bon de commande</a><p>
					</c:if>
        			<p><a href="<%=request.getContextPath()%>${Path_comptabilite}/pdf_create_devis?refDevis=${devis.guid}" target="_blank">bon de commande au format PDF</a><p>
					<c:choose>
						<c:when test="${empty devis.bonDeLivraison.id}">
							<p><a href=""  data-toggle="modal" data-target=".bs-example-modal-sm">Créer le bon de livraison issue du devis</a></p>
						</c:when>
						<c:otherwise>
							<ul>
								<li><b><span id="bon_de_livraison_parent" style="cursor:pointer;" >Bon de Livraison <i style="color:green;">crée</i></span></b>
          							<ul id="bon_de_livraison_child">
          								<c:if test="${devis.bonDeLivraison.delivred == false}">
          									<b><li>Status <a href="<%=request.getContextPath()%>${Path_delivery}/check_delivred?refBonDeLivraison=${devis.bonDeLivraison.guid}"><i style="color:red;">Non livré</i></a></li></b>
          								</c:if>
          								<c:if test="${devis.bonDeLivraison.delivred == true}">
          									<b><li>Status <i style="color:green;">Livré</i></li></b>
          								</c:if>
            							<li><a href="<%=request.getContextPath()%>${Path_comptabilite}/pdf_create_bdl?refDevis=${devis.guid}" target="_blank"><i>Voir PDF</i></a></li>
            							<li><a href="" data-toggle="modal" data-target=".bs-example-modal-sm2"><i>Modifier</i></a></li>
          							</ul>
        						</li>
							</ul>
						</c:otherwise>
					</c:choose>      			
					<c:choose>
						<c:when test="${empty devis.facture.id}">
							<p><a href=""  data-toggle="modal" data-target=".bs-example-modal-sm3">Créer la facture issue du devis</a></p>
						</c:when>
						<c:otherwise>
							<ul>
								<li><b><span id="facture_parent" style="cursor:pointer;">Facture <i style="color:green;">crée</i></span></b>
          							<ul id="facture_child">
          								<c:if test="${devis.facture.payed == false}">
          									<b><li>Status <a href="<%=request.getContextPath()%>${Path_comptabilite}/check_paid?refFacture=${devis.facture.guid}"><i style="color:red;">Impayée</i></a></li></b>
          								</c:if>
          								<c:if test="${devis.facture.payed == true}">
          									<b><li>Status <i style="color:green;">Payée</i></li></b>
          								</c:if>
            							<li><a href="<%=request.getContextPath()%>${Path_comptabilite}/pdf_create_facture?refDevis=${devis.guid}" target="_blank"><i>Voir PDF</i></a></li>
            							<li><a href="" data-toggle="modal" data-target=".bs-example-modal-sm4"><i>Modifier</i></a></li>
            							<li><a href="<%=request.getContextPath()%>${Path_comptabilite}/createAvoir?refFacture=${devis.facture.guid}"><i>Créer un avoir</i></a></li>
										<li><b><span id="avoir_parent" style="cursor:pointer;">Liste des <i style="color:green;">avoirs</i></span></b>
          									<ul id="facture_child">
												<c:forEach var="avoir" items="${devis.facture.avoir}">
													<li><a href="${pageContext.request.contextPath}${Path_comptabilite}/pdf_create_avoir?refAvoir=${avoir.guid}" target="_blank"><i>Avoir n° ${avoir.reference}</i></a></li>
												</c:forEach>
          									</ul>
        								</li>
        								<li><a href="<%=request.getContextPath()%>${Path_comptabilite}/excel_facture_create?refFacture=${devis.facture.guid}">Exporter format excel</a></li>        								
          							</ul>
        						</li>
							</ul>
						</c:otherwise>
					</c:choose> 
    			</c:otherwise>
			</c:choose>
		</div>
	</div>
	<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-sm">
    		<div class="modal-content" style="height: 200px;">
  				<h4 style="margin-left: 24px;">Insérez une date de livraison</h4>
  				<form action="${pageContext.request.contextPath}${Path_comptabilite}/createBDLedit?refDevis=${devis.guid}" method="POST">
  					<p style="margin-left: 64px; margin-top: 40px;"><input type="text" class="datepicker" name="date" autocomplete="off"></p>
  					<input type="submit" value="Valider" style="margin-left: 113px;margin-top: 26px;">
  				</form>
			</div>
  		</div>
	</div>
	<div class="modal fade bs-example-modal-sm2" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-sm">
    		<div class="modal-content" style="height: 200px;">
  				<h4 style="margin-left: 24px;">Modifier la date de livraison</h4>
  				<p style="margin-top: 28px;margin-bottom: -20px;margin-left: 44px;">Date de livraison : ${devis.bonDeLivraison.date_livraison}</p>
  				<form action="${pageContext.request.contextPath}${Path_comptabilite}/modifiy_bdl?refDevis=${devis.guid}" method="POST">
  					<p style="margin-left: 64px; margin-top: 40px;"><input type="text" class="datepicker" name="date" autocomplete="off"></p>
  					<input type="submit" value="Valider" style="margin-left: 113px;margin-top: 26px;">
  				</form>
			</div>
  		</div>
	</div>
	<div class="modal fade bs-example-modal-sm3" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-sm">
    		<div class="modal-content" style="height: 200px;">
  				<h4 style="margin-left: 70px;">Créer une Facture</h4>
  				<form action="${pageContext.request.contextPath}${Path_comptabilite}/createFactureEdit?refDevis=${devis.guid}" method="POST">
  					<table style="margin-left:73px;margin-top:15px;">
  						<tr>
  							<td>Délai de payement</td>
  						</tr>
  						<tr>
  							<td><p style="margin-left: 0px; margin-top: 0px;"><input type="text" class="datepicker" name="date_payement" autocomplete="off"></p></td>
  						</tr>
   						<tr>
  							<td>Mode de règlement</td>
  						</tr>
  						<tr>
  							<td>	
  								<select name="moyen_payement"> 
   									<option value="Virement">Virement</option> 
   									<option value="Chèque">Chèque</option> 
   									<option value="Espèces">Espèces</option> 
   									<option value="Virement/Chèque">Virement/Chèque</option> 
   									<option value="Virement/Chèque/Espèces">Virement/Chèque/Espèces</option> 
								</select>
							</td>
  						</tr>
  					</table>
  					<input type="submit" value="Valider" style="margin-left: 113px;margin-top: 13px;">
  				</form>
			</div>
  		</div>
	</div>
	<div class="modal fade bs-example-modal-sm4" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-sm">
    		<div class="modal-content" style="height: 200px;">
  				<h4 style="margin-left: 70px;">Modifier une Facture</h4>
  				<form action="${pageContext.request.contextPath}${Path_comptabilite}/modifiy_facture?refDevis=${devis.guid}" method="POST">
  					<table style="margin-left:73px;margin-top:15px;">
  						<tr>
  							<td>Délai de payement : ${devis.facture.delay_paiement}</td>
  						</tr>
  						<tr>
  							<td><p style="margin-left: 0px; margin-top: 0px;"><input type="text" class="datepicker" name="date_payement" autocomplete="off"></p></td>
  						</tr>
   						<tr>
  							<td>Mode de règlement : ${devis.facture.mode_paiement} </td>
  						</tr>
  						<tr>
  							<td>	
  								<select name="moyen_payement"> 
   									<option value="Virement">Virement</option> 
   									<option value="Chèque">Chèque</option> 
   									<option value="Espèces">Espèces</option> 
   									<option value="Virement/Chèque">Virement/Chèque</option> 
   									<option value="Virement/Chèque/Espèces">Virement/Chèque/Espèces</option> 
								</select>
							</td>
  						</tr>
  					</table>
  					<input type="submit" value="Valider" style="margin-left: 113px;margin-top: 13px;">
  				</form>
			</div>
  		</div>
	</div>
</body>
</html>