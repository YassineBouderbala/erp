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
	<h2>Fournisseur</h2>
		<c:if test="${modify eq 1}">
			<c:set var="modify_variable" value="?modify=1&guid=${fournisseur.guid}"/>
		</c:if>
		<f:form modelAttribute="fournisseur" action="${pageContext.request.contextPath}${Path_stock}/fournisseurEdit${modify_variable}" method="post">
		<table>
			<tr>
				<td>Nom de l'entreprise*</td>
				<td><f:input path="name"/></td>
				<td>${errorName}<f:errors path="name"></f:errors></td>
			</tr>
			<tr>
				<td>Téléphone 1</td>
				<td><f:input path="phone1"/></td>
				<td><f:errors path="phone1"></f:errors></td>
			</tr>
			<tr>
				<td>Téléphone 2</td>
				<td><f:input path="phone2"/></td>
				<td><f:errors path="phone2"></f:errors></td>
			</tr>
			<tr>
				<td>Fax 1</td>
				<td><f:input path="fax1"/></td>
				<td><f:errors path="fax1"></f:errors></td>
			</tr>
			<tr>
				<td>Fax 2</td>
				<td><f:input path="fax2"/></td>
				<td><f:errors path="fax2"></f:errors></td>
			</tr>
			<tr>
				<td>Email*</td>
				<td><f:input path="email"/></td>
				<td>${errorEmail}<f:errors path="email"></f:errors></td>
			</tr>
			<tr>
				<td>Code postal*</td>
				<td><f:input path="code_postal"/></td>
				<td><f:errors path="code_postal"></f:errors></td>
			</tr>
			<tr>
				<td>Adresse *</td>
				<td><f:input path="address"/></td>
				<td><f:errors path="address"></f:errors></td>
			</tr>
			<tr>
				<td>Ville*</td>
				<td><f:input path="city"/></td>
				<td><f:errors path="city"></f:errors></td>
			</tr>
			<tr>
				<td>Pays*</td>
				<td><f:input path="country"/></td>
				<td><f:errors path="country"></f:errors></td>
			</tr>
			<tr>
				<td><input type="submit"/></td>
			</tr>
		</table>
	</f:form>	
</div>
</body>
</html>