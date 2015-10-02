<%@page import="com.erp.myapp.security.Path"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Modifier un article</title>
</head>
<body>
<h3>Modifier l'article : ${article.designation}</h3>
<f:form  modelAttribute="article" action="modify_articleEdit?refArticle=${article.designation}" method="post">
	Prix ht achat <f:input path="prix_ht_achat" name="designation" autocomplete="off" type="text" placeholder="Prix hors taxe d'achat" value="${article.prix_ht_achat}"/>
	<br/><f:errors path="prix_ht_achat"></f:errors>
	Prix ht de vente<f:input path="prix_ht_vente" autocomplete="off" type="text" placeholder="Prix hors taxe de vente" value="${article.prix_ht_vente}"/>
	<br/><f:errors path="prix_ht_vente"></f:errors>
	Tva<f:input path="tva" autocomplete="off" type="text" placeholder="tva" value="${article.tva}"/>
	<br/><f:errors path="tva"></f:errors>
	<input type="submit" value="Modifier" />
</f:form>
</body>
</html>