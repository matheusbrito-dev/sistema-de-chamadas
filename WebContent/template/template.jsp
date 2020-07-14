<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

	<%@ taglib uri ="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<!-- Scriptlet - permite acesso a linguagem Java -->
<%
	/** Setando na Sessão o Caminho do WebContent */
	request.getSession().setAttribute("rootWeb",request.getContextPath());

%>

<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Matheus de Brito Vieira Martins">

    <title>Sistema Chamada</title>
	<link rel="shortcut icon" href='https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTjqlrDJwRt4-c4chKZI28c-yCq0T965btaDpZDFNtPJdrmk4hYnQ'/>

   	<jsp:include page="imports/imports-css.jsp"></jsp:include>

</head>

<body>

    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            
			<!-- ################################################# INICIO - CABEÇALHO ################################################## -->
			<%-- <jsp:include page="cabecalho.jsp"></jsp:include> --%>
			<tiles:insert name="header"/>
			<!-- ################################################# FIM - CABEÇALHO ################################################## -->

         	
            <!-- ############################## INICIO - MENU ################################### -->
            <%-- <jsp:include page="menu.jsp"></jsp:include> --%>
            <tiles:insert name="menu"/>            
           <!--  ############################## FIM - MENU ###################################### -->
           
           
          <!--  REPRESENTA A PARTE ONDE SÃO  "INJETADAS AS TELAS(BODY)" -->
        </nav>

        <div id="page-wrapper">
         
			<!-- REPRESENTA A TELA DO FORMULARIO -->
			<%-- <jsp:include page="../paginas/aluno/aluno-formulario.jsp"></jsp:include> --%>
			
			<!-- OS JSP DAS TELAS DEVEM SER CRIADOS DENTRO DE 'DIVS row' -->
			<tiles:insert name="body"/>

        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

   <jsp:include page="imports/imports-js.jsp"></jsp:include>

</body>

</html>
