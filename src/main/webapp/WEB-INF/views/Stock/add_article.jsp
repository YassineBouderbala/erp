<%@page import="com.erp.myapp.security.Path"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ajouter un article</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/stock/add_article.js"></script>
</head>
<body>
	<c:set var="Path_stock" value="<%=Path.stock%>"></c:set>
	<h3>Ajouter un article pour le fournisseur ${fournisseur.name}</h3>
	<form method="post" action="${pageContext.request.contextPath}${Path_stock}/addArticleEdit?refFournisseur=${fournisseur.guid}">
		<table id="table_add_article">
			<tr>
				<td>Type d'article</td>
				<td>NOM</td>
				<td>PRIX D'ACHAT HT</td>
				<td>PRIX DE VENTE HT</td>
				<td>TVA(%)</td>
				<td>QUANTITE</td>
			</tr>
			<tr>
				<td>
					<select name="type_article" id="type_article">
						<option value=""></option>
						<option value="stock">Article en stock</option>
						<option value="autre">Autre</option>
					</select>
				</td>
				<td id="article_name">
					<input type="text" id="name_article" name="name_article" value="${article.designation}"/>
				</td>
				<td id="prix_ht_achat_article">
					<input type="text" id="prix_ht_achat_article" name="prix_ht_achat_article" value="${article.prix_ht_achat}"/>
				</td>
				<td id="prix_ht_vente_article">
					<input type="text" id="prix_ht_vente_article" name="prix_ht_vente_article" value="${article.prix_ht_vente}"/>
				</td>
				<td id="tva_article">
					<input type="text" id="tva_article" name="tva_article" value="${article.tva}"/>
				</td>
				<td id="quantite_article">
					<input type="text" id="quantite_article" name="quantite_article" value="${article.quantite}"/>
				</td>
			</tr>
			<tr>
				<td><input type="submit" value="Ajouter"/></td>
			</tr>
		</table>
	</form>
	<c:choose>
		<c:when test="${not empty messageCreate}">
			<h3 id="errorMessage" style='color:green;'>${messageCreate}</h3>
		</c:when>
		<c:when test="${not empty listErreur}">
			<c:forEach items="${listErreur}" var="erreur">
				<span style='color:red;'>${erreur}</span><br/>
			</c:forEach>
		</c:when>
	</c:choose>
</body>
</html>