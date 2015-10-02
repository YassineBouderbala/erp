<%@page import="com.erp.myapp.security.Path"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Modifez votre devis</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/jquery/jquery-ui.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/comptabilite/devis_create.js"></script>
</head>
<body>
	<c:set var="Path_comptabilite" value="<%=Path.comptabilite%>"></c:set>
	<f:form id="form_create_devis" action="${pageContext.request.contextPath}${Path_comptabilite}/modify_devisEdit?refDevis=${devis.guid}" method="post">
		<table id="table_devis">
			<tr>
				<p>Date de validité du devis : <input type="text" id="datepicker" name="date" value="${devis.date_validity}"></p>
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
			<c:set var="count" value="30000000" />
			<c:forEach items="${devis.line}" var="line">
			<c:set var="count" value="${count + 1}"/>
			<tr class="tref${count}">
				<td>
					<c:choose>
						<c:when test="${line.type == 'Matériel'}">
							<c:set var="materiel_selected" value="selected='selected'"></c:set>
							<c:set var="read_only" value="readonly"></c:set>
						</c:when>
						<c:otherwise>
							<c:set var="heure_selected" value="selected='selected'"></c:set>
						</c:otherwise>
					</c:choose> 				
					<select class="type" id="${count}" name="type">
						<option value=""></option>
						<option value="Matériel" ${materiel_selected}>Matériel</option>
						<option value="Heure" ${heure_selected}>Heure</option>
					</select>
				</td>
				<td id="td_description_${count}"><input style='width:200px;' type='text' value='${line.description}' name='designation' autocomplete='off' required ${read_only}/></td>
				<td id="td_reference_${count}"><input name='reference' value='${line.reference}' autocomplete='off'  readonly/></td>
				<td id="td_quantite_${count}"><input id='${count}' class='input_quantity' value="${line.quantity}" name='quantity' type='text' autocomplete='off' required/></td>
				<td id="td_tva_${count}"><input value='${line.tva}' name='tva' autocomplete='off' ${read_only}/></td>
				<td id="td_prix_ht_${count}"><input id='input_prix_ht_${count}' name='prix_ht' value='${line.prix_ht}' autocomplete='off' required ${read_only}/></td>
				<td id="td_total_ht_${count}"><input id='input_total_ht_${count}' value='${line.total_ht}' name='total_ht' value='' type='text' autocomplete='off' required readonly/><b id='deline' class='tref${count}' style='cursor:pointer;'> Supprimer la ligne</b></td>
			</tr>
			<c:set var="materiel_selected" value=""></c:set>
			<c:set var="heure_selected" value=""></c:set>
			<c:set var="read_only" value=""></c:set>
			</c:forEach>
		</table>
		<input type="submit" />
	</f:form>
	<br/>
	<b class='insert_line' style='cursor:pointer;'>Insérez une ligne</b>
	<br/><br/>
	<b style="color:red;"><c:if test="${not empty error}">Modification du devis impossible du à des champs manquants ou mal formatés réessayez</c:if></b>
</body>
</html>