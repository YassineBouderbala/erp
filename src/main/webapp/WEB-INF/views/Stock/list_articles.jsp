<%@page import="com.erp.myapp.security.Path"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Liste des articles</title>
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
<c:set var="nbArticle" value="${0*0}"/>
<c:set var="qtéPrixArticle" value="${0*0}"/>
<c:set var="Path_stock" value="<%=Path.stock%>"></c:set>
	<span>Rechercher par :</span><br/><br/>
	<form action="${pageContext.request.contextPath}${Path_stock}/searchArticleEdit" method="post">
		<span>Désignation</span><input type="text" name="designation" />
		<span>Référence</span><input type="text" name="reference" />
		<input type="submit" value="rechercher" />
	</form>
	<br/>
	<span>Trier par :</span>
	<select NAME=URL onchange=window.location=this.options[selectedIndex].value>
		<option></option>
		<option value="<%=request.getContextPath()%>${Path_stock}/articles?all">Désignation</option>
  		<option value="<%=request.getContextPath()%>${Path_stock}/articles?triQuantite">Quantité croissante</option> 
  		<option value="<%=request.getContextPath()%>${Path_stock}/articles?triAchat">Prix d'achat croissant</option>
  		<option value="<%=request.getContextPath()%>${Path_stock}/articles?triVente">Prix de vente croissant</option>
	</select>
	<h3>Liste des articles en stock</h3>
	<c:choose>
		<c:when test="${not empty list_article}">
			<table>
				<tr>
					<td>DESIGNATION</td>
					<td>PRIX HT ACHAT</td>
					<td>PRIX HT VENTE</td>
					<td>QTE</td>
					<td>REFERENCE</td>
					<td>TVA</td>
					<td>FOURNISSEUR</td>
					<td>SUPPRIMER</td>
					<td>ENTREE DE STOCK</td>
					<td>SORTIE DE STOCK</td>
				</tr>
				<c:forEach items="${list_article}" var="article">
					<tr>
						<td>${article.designation}</td>
						<td>${article.prix_ht_achat}</td>
						<td>${article.prix_ht_vente}</td>
						<td>${article.quantite}</td>
						<td>${article.reference}</td>
						<td>${article.tva}</td>
						<td><a href="${pageContext.request.contextPath}${Path_stock}/showFournisseur?guid=${article.fournisseur.guid}">${article.fournisseur.name}</a></td>
						<td><a href="${pageContext.request.contextPath}${Path_stock}/delete_article?refArticle=${article.designation}">supprimer</a></td>
						<td><a href="${pageContext.request.contextPath}${Path_stock}/entries?refArticle=${article.designation}">entrée de stock</a></td>
						<td><a href="${pageContext.request.contextPath}${Path_stock}/outputs?refArticle=${article.designation}">sortie de stock</a></td>
					</tr>
					<c:set var="nbArticle" value="${nbArticle + 1}"/>
					<c:set var="qtéPrixArticle" value="${qtéPrixArticle + article.quantite * article.prix_ht_achat}"/>
				</c:forEach>
			</table>
			<p>Nombre d'article : <c:out value="${nbArticle}"/></p>
			<p>Valeur total: <c:out value="${qtéPrixArticle}"/></p>
		</c:when>
		<c:when test="${empty list_article}">
			<p>AUCUN ARTICLES DISPONIBLES</p>
		</c:when>
	</c:choose>
</body>
</html>