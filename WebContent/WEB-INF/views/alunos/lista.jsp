<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listagem de aluno</title>
</head>
<body>
<c:import url= "../menu.jsp"></c:import>
<h1>Lista de alunos</h1>

	<table border="2">
		<thead>
			<tr>
				
				<th>Id</th>
				<th>Matricula</th>
				<th>Nome</th>
				<th>CPF</th>
				<th>Data de Nascimento</th>
				<th>Endereço</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="aluno" items="${alunos }">
				<tr>
					<td>${aluno.id }</td>
					<td>${aluno.matricula }</td>
					<td>${aluno.nome }</td>
					<td>${aluno.CPF }</td>
					<td>${aluno.dataNascimento.time }</td>
					<td>${aluno.endereco }</td>
				</tr>
			</c:forEach>

		</tbody>

	</table>
</body>
</html>