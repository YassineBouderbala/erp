<%@page import="com.erp.myapp.security.Path"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Theme</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/config/theme.js"></script>
</head>
<body>
<c:set var="Path_admin" value="<%=Path.admin%>"></c:set>
<div style="float:left;">
	<h3>Bas de page du devis</h3>
	<form action="${pageContext.request.contextPath}${Path_admin}/config/theme_footer_devis_create" method="post">
		<textarea id="textarea_devis" rows="10" cols="50" name="footer_devis">${theme.footer_devis}</textarea><br/><br/>
		<p id="nbCharacterDevis">${errorFooterDevis}</p>
		<input type="submit" value="Enregistrer"/>
	</form>
</div>
<div style="float:left;margin-left:100px;">
	<h3>Bas de page de la facture</h3>
	<form action="${pageContext.request.contextPath}${Path_admin}/config/theme_footer_facture_create" method="post">
		<textarea id="textarea_facture" rows="10" cols="50" name="footer_facture">${theme.footer_facture}</textarea><br/><br/>
		<p id="nbCharacterFacture">${errorFooterFacture}</p>
		<input type="submit" value="Enregistrer"/>
	</form>
</div>

</body>
</html>