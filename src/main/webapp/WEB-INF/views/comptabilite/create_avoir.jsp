<%@page import="com.erp.myapp.security.Path"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Créer un avoir</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/jquery/jquery-ui.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/comptabilite/devis_create.js"></script>
</head>
<body>
	<c:set var="Path_comptabilite" value="<%=Path.comptabilite%>"></c:set>
	<f:form id="form_create_devis" action="${pageContext.request.contextPath}${Path_comptabilite}/createAvoirEdit?refFacture=${facture.guid}" method="post">
		<table id="table_devis">
			<tr>
				<h2>Avoir sur facture numero ${facture.reference}</h2>
			</tr>
			<tr>
				<td>TYPE</td>
				<td>DESCRIPTION</td>
				<td>REFERENCE</td>
				<td>QUANTITE</td>
				<td>TVA(%)</td>
				<td>PRIX HT</td>
				<td>TOTAL HT</td>
			</tr>
			<tr class="tref1">
				<td>
					<select class="type" id="1" name="type">
						<option value=""></option>
						<option value="Matériel">Matériel</option>
						<option value="Heure">Heure</option>
					</select>
				</td>
				<td id="td_description_1"></td>
				<td id="td_reference_1"></td>
				<td id="td_quantite_1"></td>
				<td id="td_tva_1"></td>
				<td id="td_prix_ht_1"></td>
				<td id="td_total_ht_1"></td>
			</tr>
		</table>
		<input type="submit" />
	</f:form>
	<br/>
	<b class='insert_line' style='cursor:pointer;'>Insérez une ligne</b>
	<br/><br/>
	<b style="color:red;"><c:if test="${not empty error}">Création de l'avoir impossible du à des champs manquants ou mal formatés, réessayez</c:if></b>
	<c:if test="${not empty listArticleAvoir}">
		<c:forEach items="${listArticleAvoir}" var="article">
			<p><b style="color:red;">Création impossible : la quantité de ${article.description} sur la facture est de ${article.quantity}.</b><p>		
		</c:forEach>
	</c:if>
	<p>* Les heures ne sont pas disponible pour l'avoir</p>
</body>
</html>