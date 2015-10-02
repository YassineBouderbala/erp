<%@page import="com.erp.myapp.security.Path"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Details du stock</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/jquery/jquery-ui.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/stock/details.js"></script>
</head>
<body>
<c:set var="Path_stock" value="<%=Path.stock%>"></c:set>
<c:set var="Path_comptabilite" value="<%=Path.comptabilite%>"></c:set>
	<div style="float: left;">
		<form action="${pageContext.request.contextPath}${Path_stock}/detailsEdit" method="post">
			<table style="border: none;">
				<tr>
					<td>Details datant du <input type="text" class="datepicker" name="date1" value="${date1}" /> au <input type="text" name="date2" class="datepicker" value="${date2}" /><input type="submit" value="Rechercher" /></td>
				</tr>
			</table>
			<br />
		</form>
		<c:choose>
			<c:when test="${not empty listFacture}">
			<p>Tva Collectee : ${tva_collectee}</p>
			<p>Tva Déductible : ${tva_deductible}</p>
			<p>Tva à devoir : ${tva_a_payer}</p>
			<p>Chiffre d'affaire : ${chiffre_affaire}</p>
			
			<h2>Details</h2>
			<c:forEach items="${listFacture}" var="facture">
				<p>Facture n° ${facture.reference} du <b>${facture.date}</b></p>
				<p>Total HT de la facture : ${facture.devis.total.total_ht}</p>
				<p>TVA collectée : ${facture.devis.total.tva}</p>
				<b><a  target="_blank" href="<%=request.getContextPath()%>${Path_comptabilite}/pdf_create_facture?refDevis=${facture.devis.guid}">Voir Facture</a></b>
				<ul>
					<c:forEach items="${facture.avoir}" var="avoir">
					<li>Avoir n° ${avoir.reference}</li>
					<li>Total HT de l'avoir : ${avoir.total.total_ht}</li>
					<li>TVA collectée : ${avoir.total.tva}</li>
					<li><b><a  target="_blank" href="<%=request.getContextPath()%>${Path_comptabilite}/pdf_create_avoir?refAvoir=${avoir.guid}">Voir Avoir</a></b></li>
					<br/>
					<br/>
					</c:forEach>
				</ul>
				<p>----------------------------------------------------------------</p>		
			</c:forEach>
			</c:when>
			<c:when test="${empty listFacture}">
				<p>AUCUN DETAILS DISPONIBLES</p>
			</c:when>
		</c:choose>
	</div>
</body>
</html>