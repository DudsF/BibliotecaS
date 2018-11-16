<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listagem de emprestimos abertos</title>
</head>
<style>
#customers {
    font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
    border-collapse: collapse;
    width: 100%;
    top: 50%;
    left: 50%;
    center;
    
     
}

#customers td, #customers th {
    border: 1px solid black;
    padding: 10px;
}

#customers tr:nth-child{background-color: #F6CED8;}

#customers tr:hover {background-color: lavender;}

#customers th {
	
    padding-top: 12px;
    padding-bottom: 12px;
    text-align: left;
    background-color: LightCoral;
    color: white;
    }
    
    
    .container {
 	position: absolute;
    margin-top: 20%;
    left: 50%;
    width: 50%;
    transform: translateY(-50%) translateX(-50%);
	center;
	border-radius: 5px;
	background-color: #ccc;
	padding: 20px;
}
.centraliza {
	-webkit-text-stroke-width: 1px; 
    -webkit-text-stroke-color: #000;
	text-align: center;
	color: LightCoral ;
}
</style>
<body>
<c:import url= "../menu.jsp"></c:import>
<div class="container">

<div class= "centraliza">
<h1>Emprestimos Abertos</h1>
</div>
<table id="customers">
		<thead>
			<tr>
				<th>Livro</th>
				<th>Matrícula</th>
				<th>Data do emprestimo</th>
				<th>Devolução</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="emprestimo" items="${emprestimo}">
				<tr>
					<td>${emprestimo.livro.titulo}</td>
					<td>${emprestimo.aluno.matricula}</td>
					<td><fmt:formatDate value="${emprestimo.dataEmprestimo.time}" pattern="dd/MM/yyyy" /></td>
					<c:if test="${empty emprestimo.dataDevolucao}">
							<td class="is-one-third">
								<form method="POST" action="/BibliotecaS/emprestimo/devolucao"> 
									<input type="hidden" name="alunoID" value="${emprestimo.aluno.id}"/>
									<input type="hidden" name="livroID" value="${emprestimo.livro.id}"/>
									<button type="submit" class="button is-info">Fazer Devolução</button>  
								</form>
							</td>
						</c:if>
						<c:if test="${not empty emprestimo.dataDevolucao}">
							<td><fmt:formatDate value="${emprestimo.dataDevolucao.time}" pattern="dd/MM/yyyy" /></td>
						</c:if>
				</tr>
					
					<td><a href="/BibliotecaS/emprestimo/devolucao?id=${emprestimo.id}">Fazer Devolução</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
</body>
</html>