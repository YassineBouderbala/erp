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
<title>Entrée de stock</title>
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
<c:set var="nbEntry" value="${0*0}"/>
<c:set var="qtéAchat" value="${0*0}"/>
<c:set var="tvadeductible" value="${0*0}"/>
<div style="float:left;">
<form action="${pageContext.request.contextPath}${Path_stock}/searchEntriesEdit" method="post">
	<table style="border:none;">
		<tr>
			<td>Article datant du <input type="text" class="datepicker" name="date1" value="${date1}"  /> au <input type="text" name="date2" class="datepicker" value="${date2}" /><input type="submit" value="Rechercher" /></td>
		</tr>
	</table>
	<br/>
</form>
<c:choose>
	<c:when test="${not empty listEntries}">
		<table>
			<tr>
				<td>ARTICLE</td>
				<td>PRIX D'ACHAT</td>
				<td>DATE D'ACHAT</td>
				<td>QUANTITE ACHETEE</td>
				<td>FOURNISSEUR</td>
				<td>SUPPRIMER</td>
			</tr>
			<c:forEach items="${listEntries}" var="entry">
				<tr>
					<td><a href="${pageContext.request.contextPath}${Path_stock}/articles?refArticle=${entry.article.designation}">${entry.article.designation}</a></td>
					<td>${entry.article.prix_ht_achat}</td>
					<td>${entry.date}</td>
					<td>${entry.quantity}</td>
					<td><a href="${pageContext.request.contextPath}${Path_stock}/showFournisseur?guid=${entry.article.fournisseur.guid}">${entry.article.fournisseur.name}</a></td>
					<td><a href="${pageContext.request.contextPath}${Path_stock}/delete_entry?ref=${entry.id}">Supprimer</a></td>
					<c:set var="nbEntry" value="${nbEntry + 1}"/>
					<c:set var="qtéAchat" value="${qtéAchat + entry.quantity * entry.article.prix_ht_achat}"/>
					<c:set var="tvadeductible" value="${tvadeductible + entry.article.prix_ht_achat * entry.article.tva / 100 * entry.quantity }"/>
				</tr>
			</c:forEach>
		</table>
	</c:when>
	<c:when test="${empty listEntries}">
		<p>AUCUNE ENTREE DE STOCK DISPONIBLES</p>
	</c:when>
	</c:choose>
</div>
<div style='float:left;margin-left:50px;'>
	<p><b>Nombre d'entrée : ${nbEntry}</b></p>
	<p><b>Total d'achat : ${qtéAchat}</b></p>
	<p><b>TVA déductible : ${tvadeductible}</b></p>
</div>
</body>
</html>