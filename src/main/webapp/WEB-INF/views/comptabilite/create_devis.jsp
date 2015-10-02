<%@page import="com.erp.myapp.utils.Article_Util"%>
<%@page import="com.erp.myapp.security.Path"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Créez votre devis</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/jquery/jquery-ui.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/comptabilite/devis_create.js"></script>
</head>
<body>
	<c:set var="Path_comptabilite" value="<%=Path.comptabilite%>"></c:set>
	<c:set var="Article_Util" value="<%=Article_Util.lengthLine%>"></c:set>
	<b style="color:red;">*</b><span>Attention ! Le nombre de caractères pour les heures ne doit pas dépasser ${Article_Util} caractères</span>
	<f:form id="form_create_devis" action="${pageContext.request.contextPath}${Path_comptabilite}/createDevisEdit?refCustomer=${customer.guid}" method="post">
		<table id="table_devis">
			<tr>
				<p>Date de validité du devis : <input type="text" id="datepicker" name="date"></p>
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
	<b style="color:red;"><c:if test="${not empty error}">Création du devis impossible du à des champs manquants ou mal formatés, réessayez</c:if></b>
</body>
<script type="text/javascript">
	setDateDefault(); // on met une valeur par défaut sur la date
	function setDateDefault(){
		var date = new Date();
		var day = date.getDate() + 1;
		var month = date.getMonth() + 1;
		var year = date.getFullYear();
		if (month < 10) month = "0" + month;
		if (day < 10) day = "0" + day;
		var today = day + "-" + month + "-" + year;
		document.getElementById('datepicker').value = today;
	}
</script>
</html>