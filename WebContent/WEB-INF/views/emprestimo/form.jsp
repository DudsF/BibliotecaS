<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Adicionar Aluno</h1>
	<form action="/BibliotecaS/alunos" method="post">
		
		<div>
			<label>Matricula: </label> 
			<input type="text" name="matricula">
		</div>
		<div>
			<label>Título do Livro: </label> 
			<input type="text" name="nome">
		</div>
		<div>
	
			<button type="submit">Emprestar</button>
		</div>
	</form>
</body>
</html>