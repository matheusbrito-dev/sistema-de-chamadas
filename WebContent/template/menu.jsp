<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<div class="navbar-default sidebar" role="navigation">
	<div class="sidebar-nav navbar-collapse">
		<ul class="nav" id="side-menu">
			
			<li>
				<a href="index.html">
					<i class="glyphicon glyphicon-th"></i>
					Home
				</a>
			</li>
			<li>
				<a href="#">
					<i class="glyphicon glyphicon-sunglasses"></i>
					Matheus
					<span class="fa arrow"></span>
				</a>
				<ul class="nav nav-second-level">
					<li>
						<a href="flot.html">Low</a>
					</li>
					<li>
						<a href="morris.html">High</a>
					</li>
				</ul>
				<!-- /.nav-second-level -->
			</li>
			<li>
				<a href="${rootWeb}/turmaAction.do?method=abrirTela">
					<i class="fa fa-graduation-cap"></i>
					Turma
				</a>
			</li>
			<li>
				<a href="${rootWeb}/alunoAction.do?method=abrirTela">
					<i class="fa fa-child"></i>
					Aluno
				</a>
			</li>
			<li>
				<a href="${rootWeb}/presencaAction.do?method=abrirTela">
					<i class="fa fa-street-view"></i>
					Presença
				</a>
			</li>
			
		</ul>
	</div>
	<!-- /.sidebar-collapse -->
</div>
<!-- /.navbar-static-side -->