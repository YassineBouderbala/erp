<%@page import="com.erp.myapp.security.Path"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/jquery/jquery-ui.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/stock/entries.js"></script>
<title>Sorties de stock</title>
<style type="text/css">
	table tr td{
		border: 1px solid black;
		padding: 3px;
	}
	table{
		border-collapse: collapse;
	}
</style>
</head>
<body>
<c:set var="Path_stock" value="<%=Path.stock%>"></c:set>
<c:set var="nbOutputs" value="${0*0}"/>
<c:set var="qtéOutputs" value="${0*0}"/>
<c:set var="tvacollectee" value="${0*0}"/>
<c:set var="benefice" value="${0*0}"/>
<div style="float:left;">
<form action="${pageContext.request.contextPath}${Path_stock}/searchOutputsEdit" method="post">
	<table style="border:none;">
		<tr>
			<td>Article datant du <input type="text" class="datepicker" name="date1" value="${date1}"  /> au <input type="text" name="date2" class="datepicker" value="${date2}"  /><input type="submit" value="Rechercher" /></td>
		</tr>
	</table>
	<br/>
</form>
<c:choose>
	<c:when test="${not empty listOuputs}">
		<table>
			<tr>
				<td>ARTICLE</td>
				<td>PRIX DE achhat</td>
				<td>PRIX DE VENTE</td>
				<td>DATE DE VENTE</td>
				<td>QUANTITE VENDUES</td>
				<td>FOURNISSEUR</td>
			</tr>
			<c:forEach items="${listOuputs}" var="outputs">
				<tr>
					<td><a href="${pageContext.request.contextPath}${Path_stock}/articles?refArticle=${outputs.article.designation}">${outputs.article.designation}</a></td>
					<td>${outputs.article.prix_ht_achat}</td>
					<td>${outputs.article.prix_ht_vente}</td>
					<td>${outputs.date}</td>
					<td>${outputs.quantity}</td>
					<td><a href="${pageContext.request.contextPath}${Path_stock}/showFournisseur?guid=${outputs.article.fournisseur.guid}">${outputs.article.fournisseur.name}</a></td>
				</tr>
				<c:set var="nbOutputs" value="${nbOutputs + 1}"/>
				<c:set var="qtéOutputs" value="${qtéOutputs + outputs.quantity * outputs.article.prix_ht_vente}"/>
				<c:set var="tvacollectee" value="${tvacollectee + outputs.article.prix_ht_vente * outputs.article.tva / 100 * outputs.quantity }"/>
				<c:set var="benefice" value="${benefice + ((outputs.article.prix_ht_vente - outputs.article.prix_ht_achat) * outputs.quantity)  }"/>
			</c:forEach>
		</table>
	</c:when>
	<c:when test="${empty listOuputs}">
		<p>AUCUNE SORTIE DE STOCK DISPONIBLES</p>
	</c:when>
	</c:choose>
</div>
<div style='float:left;margin-left:50px;'>
	<p><b>Nombre de sortie : ${nbOutputs}</b></p>
	<p><b>Total de vente : ${qtéOutputs}</b></p>
	<p><b>TVA collectée : ${tvacollectee}</b></p>
	<p><b>Bénéfices : ${benefice}</b></p>
</div>
</body>
</html>