<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listagem de livro</title>
</head>
<style>
.centralizado {
	text-align: center;
	margin: 0 auto;
}
</style>
<body>
<c:import url= "../menu.jsp"></c:import>
<div class= "centralizando">
	<h1>Lista de livros</h1>
</div>
	<table border="2">
		<thead>
			<tr>
				
				<th>Id</th>
				<th>Título</th>
				<th>Autor</th>
				<th>Editora</th>
				<th>Ano de Publicação</th>
				<th>Edição</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="livro" items="${livros }">
				<tr>
					<td>${livro.id }</td>
					<td>${livro.titulo }</td>
					<td>${livro.autor }</td>
					<td>${livro.editora }</td>
					<td>${livro.anoPub.time }</td>
					<td>${livro.edicao }</td>
					
				</tr>
			</c:forEach>

		</tbody>

	</table>
</body>
</html>