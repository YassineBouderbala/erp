<%@page import="com.erp.myapp.security.Path"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Fournisseur</title>
</head>
<body>
<c:set var="Path_stock" value="<%=Path.stock%>"></c:set>
<c:set var="modify_variable" value="?modify=0"></c:set>
<div style="float:left;border:1px solid black; width: 300px;">
	<c:if test="${not empty listFournisseur}">
		<c:forEach var="fournisseur" items="${listFournisseur}">
			<p><a href="<%=request.getContextPath()%>${Path_stock}/showFournisseur?guid=${fournisseur.guid}">${fournisseur.name}</a> <a href="<%=request.getContextPath()%>${Path_stock}/fournisseur?modify=1&guid=${fournisseur.guid}">Modifier</a> 
			<c:if test="${empty fournisseur.article}"><a href="<%=request.getContextPath()%>${Path_stock}/fournisseur/delete?guid=${fournisseur.guid}">Supprimer</a></c:if></p>
		</c:forEach>
	</c:if>
	<c:if test="${empty listFournisseur}">
		<p>Aucun fournisseur disponible</p>
	</c:if>
</div>
<a href="<%=request.getContextPath()%>${Path_stock}/fournisseur?modify=0">Ajouter un fournisseur</a>
<div style="float:left;border:1px solid black; width: 800px;">
<a href="<%=request.getContextPath()%>${Path_stock}/addArticleIn?refFournisseur=${fournisseur.guid}">Ajouter un article pour ce fournisseur</a> 
	<h2>${fournisseur.name}</h2>
	<p>${fournisseur.email}</p>
	<p>${fournisseur.code_postal}</p>
	<p>${fournisseur.address}</p>
	<p>${fournisseur.city}</p>
	<p>${fournisseur.country}</p>
	<p>${fournisseur.phone1}</p>
	<p>${fournisseur.phone2}</p>
	<p>${fournisseur.fax1}</p>
	<p>${fournisseur.fax2}</p>
	<p>${fournisseur.name}</p>
	<p>${fournisseur.name}</p>	
</div>
</body>
</html>