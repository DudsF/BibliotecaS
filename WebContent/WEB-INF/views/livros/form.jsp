<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<c:import url= "../menu.jsp"></c:import>
<h1>Adicionar Livro</h1>
	<form action="/BibiotecaS/livros" method="post">
		
		<div>
			<label>T�tulo: </label> 
			<input type="text" name="titulo">
		</div>
		<div>
			<label>Autor: </label> 
			<input type="text" name="autor">
		</div>
		<div>
			<label>Editora: </label> 
			<input type="text" name="editora">
		</div>
		<div>
			<label>Ano de Publica��o: </label>
			<input type="text" name="anoPub">
		</div>
		<div>
			<label>Edi��o: </label> 
			<input type="text" name="edicao">
		</div>
		
		<div>
			<button type="submit">Cadastrar</button>
		</div>
	</form>
</body>
</html>