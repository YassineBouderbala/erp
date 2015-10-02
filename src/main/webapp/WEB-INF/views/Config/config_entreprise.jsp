<%@page import="com.erp.myapp.security.Path"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Configuration</title>
</head>
<body>
	<c:set var="Path_admin" value="<%=Path.admin%>"></c:set>
	<h1>Informations de l'entreprise</h1>
	<div style="float: left; border: 1px solid black">
		<c:choose>
			<c:when test="${empty entreprise.id}">
				<f:form modelAttribute="entreprise"
					action="${pageContext.request.contextPath}${Path_admin}/config/entrepriseEdit"
					method="post">
					<table>
						<tr>
							<td>Nom de l'entreprise</td>
							<td><f:input path="name" autocomplete="off" type="text"
									placeholder="Nom de l'entreprise" /></td>
							<td><f:errors path="name"></f:errors></td>
						</tr>
						<tr>
							<td>Capital</td>
							<td><f:input path="capital" autocomplete="off" type="text"
									placeholder="Capital" /></td>
							<td><f:errors path="capital"></f:errors></td>
						</tr>
						<tr>
							<td>Siren</td>
							<td><f:input path="siren_number" autocomplete="off"
									type="text" placeholder="siren" /></td>
							<td><f:errors path="siren_number"></f:errors></td>
						</tr>
						<tr>
							<td>Numéro de tva</td>
							<td><f:input path="tv_number" autocomplete="off" type="text"
									placeholder="Numéro Tva" /></td>
							<td><f:errors path="tv_number"></f:errors></td>
						</tr>
						<tr>
							<td>Forme juridique</td>
							<td><f:input path="forme_juridique" autocomplete="off"
									type="text" placeholder="Forme juridique" /></td>
							<td><f:errors path="forme_juridique"></f:errors></td>
						</tr>
						<tr>
							<td>Pays</td>
							<td><f:input path="country" autocomplete="off" type="text"
									placeholder="Pays" /></td>
							<td><f:errors path="country"></f:errors></td>
						</tr>
						<tr>
							<td>Ville</td>
							<td><f:input path="city" autocomplete="off" type="text"
									placeholder="Ville" /></td>
							<td><f:errors path="city"></f:errors></td>
						</tr>
						<tr>
							<td>Code postal</td>
							<td><f:input path="code_postal" autocomplete="off"
									type="text" placeholder="21000" /></td>
							<td><f:errors path="code_postal"></f:errors></td>
						</tr>
						<tr>
							<td>Adresse</td>
							<td><f:input path="address" autocomplete="off" type="text"
									placeholder="18 avenue de l'exemple" /></td>
							<td><f:errors path="address"></f:errors></td>
						</tr>
						<tr>
							<td>Téléphone</td>
							<td><f:input path="phone" autocomplete="off" type="text"
									placeholder="0606060606" /></td>
							<td><f:errors path="phone"></f:errors></td>
						</tr>
						<tr>
							<td>Fax</td>
							<td><f:input path="fax" autocomplete="off" type="text"
									placeholder="0606060606" /></td>
							<td><f:errors path="fax"></f:errors></td>
						</tr>
						<tr>
							<td>Email</td>
							<td><f:input path="email" autocomplete="off" type="text"
									placeholder="exemple@gmail.com" /></td>
							<td><f:errors path="email"></f:errors></td>
						</tr>
						<tr>
							<td>Type de monnaie</td>
							<td><f:input path="money_type" autocomplete="off"
									type="text" placeholder="Euros,Dollars etc ..." /></td>
							<td><f:errors path="money_type"></f:errors></td>
						</tr>
						<tr>
							<td></td>
							<td><input type="submit" value="Enregistrer" /></td>
							<td></td>
						</tr>
					</table>
				</f:form>
			</c:when>
			<c:otherwise>
				<table>
					<tr>
						<td>Nom de l'entreprise</td>
						<td><b>${entreprise.name}</b></td>
					</tr>
					<tr>
						<td>Capital</td>
						<td><b>${entreprise.capital}</b></td>
					</tr>
					<tr>
						<td>Siren</td>
						<td><b>${entreprise.siren_number}</b></td>
					</tr>
					<tr>
						<td>Numéro de Tva</td>
						<td><b>${entreprise.tv_number}</b></td>
					</tr>
					<tr>
						<td>Forme juridique</td>
						<td><b>${entreprise.forme_juridique}</b></td>
					</tr>
					<tr>
						<td>Pays</td>
						<td><b>${entreprise.country}</b></td>
					</tr>
					<tr>
						<td>Ville</td>
						<td><b>${entreprise.city}</b></td>
					</tr>
					<tr>
						<td>Code postal</td>
						<td><b>${entreprise.code_postal}</b></td>
					</tr>
					<tr>
						<td>Adresse</td>
						<td><b>${entreprise.address}</b></td>
					</tr>
					<tr>
						<td>Téléphone</td>
						<td><b>${entreprise.phone}</b></td>
					</tr>
					<tr>
						<td>Fax</td>
						<td><b>${entreprise.fax}</b></td>
					</tr>
					<tr>
						<td>Email</td>
						<td><b>${entreprise.email}</b></td>
					</tr>
					<tr>
						<td>Type de monnaie</td>
						<td><b>${entreprise.money_type}</b></td>
					</tr>
				</table>
				<p><a href="${pageContext.request.contextPath}${Path_admin}/config/entreprise_modify">Modifier</a></p>
			</c:otherwise>
		</c:choose>
	</div>
	<c:if test="${not empty entreprise.id}">
		<div style="margin-left: 100px;float: left; border: 1px solid black;">
			<c:choose>
				<c:when test="${empty entreprise.logo}">
					<h3>Vous n'avez aucun logo</h3>
					<form
						action="${pageContext.request.contextPath}${Path_admin}/config/entrepriseLogoEdit"
						method="post" enctype="multipart/form-data">
						<table>
							<tr>
								<td>Logo de l'entreprise</td>
								<td><input type="file" name="file" /></td>
								<td>${errorLogo}</td>
							</tr>
							<tr>
								<td></td>
								<td><input type="submit" value="enregistrer" /></td>
							</tr>
						</table>
					</form>
				</c:when>
				<c:otherwise>
					<p>Logo Entreprise</p>
					<img style="width: 250px;height: 100px;" src="<%=request.getContextPath()%>/resources/pictures/picture_entreprise/${entreprise.logo}" />
				</c:otherwise>
			</c:choose>
			<p><a href="${pageContext.request.contextPath}${Path_admin}/config/entreprise_modify">Modifier</a></p>
		</div>
	</c:if>
</body>
</html>