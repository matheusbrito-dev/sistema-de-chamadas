<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<!--  TAGS PARA O USO DO STRUTS NO JSP  -->
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Cadastro de Turmas</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">

		<html:form action="turmaAction.do" method="post" styleId="tagForm">
			<html:hidden property="method" value="nada" styleId="method" />
			<!-- AQUI ESTA SENDO CONFIGURADO A FORMA DE EXIBIÇÃO DAS MENSAGENS DO SISTEMA -->
			<div class="row">
				<logic:messagesPresent message="false">
					<div class="alert alert-danger">
						<html:messages id="message" message="false">
							<bean:write name="message" filter="false" />
						</html:messages>
					</div>
				</logic:messagesPresent>
				<logic:messagesPresent message="true">
					<div class="alert alert-success">
						<html:messages id="message" message="true">
							<bean:write name="message" filter="false" />
						</html:messages>
					</div>
				</logic:messagesPresent>
			</div>
			<div class="panel panel-green">
				<div class="panel-body">



					<!-- CRIAÇÃO DOS CAMPOS JUNTAMENTE COM SUAS LABELS -->
					<!-- LINHA -->
					<div class="row">
						<!-- COLUNA -->
						<div class="form-group col-lg-4 col-md-4 col-sm-12 col-xs-12">
							<label>Código</label>
							<html:text styleClass="form-control input-sm bloqueado" styleId="id" name="turmaForm" property="turma.idToString" />

						</div>

						<div class="form-group col-lg-4 col-md-4 col-sm-12 col-xs-12">
							<label>Data cadastro</label>
							<html:text styleClass="form-control input-sm bloqueado" styleId="dataHoraCadastro" name="turmaForm" property="turma.dataHoraCadastroToString" />

						</div>
						
						<div class="form-group col-lg-4 col-md-4 col-sm-12 col-xs-12">
							<label>Tipo</label>
							<html:text styleClass="form-control input-sm obrigatorio" styleId="tipo" name="turmaForm" property="turma.tipo" />
						</div>
						
						<div class="form-group col-lg-4 col-md-4 col-sm-12 col-xs-12">
							<label>Carga Horaria</label>
							<html:text styleClass="form-control input-sm obrigatorio" styleId="cargaHorariaToString" name="turmaForm" property="turma.cargaHorariaToString" />
						</div>
						
						<div class="form-group col-lg-4 col-md-4 col-sm-12 col-xs-12">
							<label>Data Inicio</label>
							<html:text styleClass="form-control input-sm obrigatorio" styleId="dataInicioToString" name="turmaForm" property="turma.dataInicioToString" />
						</div>
						
						<div class="form-group col-lg-4 col-md-4 col-sm-12 col-xs-12">
							<label>Data Termino</label>
							<html:text styleClass="form-control input-sm obrigatorio" styleId="dataTerminoToString" name="turmaForm" property="turma.dataTerminoToString" />
						</div>

						<div class="form-group col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<label>Nome</label>
							<html:text styleClass="form-control input-sm obrigatorio" styleId="nome" name="turmaForm" property="turma.nome" />
						</div>
						
						<div class="form-group col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<label>Nome Instrutor</label>
							<html:text styleClass="form-control input-sm obrigatorio" styleId="nomeInstrutor" name="turmaForm" property="turma.nomeInstrutor" />
						</div>
						
						
						
					</div>

					<!-- FECHAMENTO ROW DOS CAMPOS -->

					<!-- LINHA COM A TABELA DOS DADOS -->
					<div class="row">
						<div class="table-responsive col-lg-12">
							<table class="table table-striped table-bordered table-hover">
								<thead>
									<tr class="bg-success">
										<th>Código</th>
										<th>Data cadastro</th>
										<th>Nome</th>
										<th>Nome instrutor</th>
										<th>Tipo</th>
										<th>Carga Horaria</th>
										<th>Data Inicio</th>
										<th>Data Termino</th>
										<th>Selecionar</th>
									</tr>
								</thead>

								<tbody>
									<!-- PROPRIEDADES:
									id - Objeto corrente do FOR
									indexId - è o contador como por exemplo o (i)
									name - Nome do Form onde a lista esta
									property - Nome do atributo que representa a lista
									type = Tipo do Objeto que esta dentro(ArrayList<type>) da lista -->
									<logic:iterate id="turmaCorrente" indexId="i" name="turmaForm" property="turmas" type="br.com.srcsoftware.sistemadechamada.modulochamada.turma.model.TurmaPO">
										<tr>
											<td>${turmaCorrente.idToString}</td>
											<td>${turmaCorrente.dataHoraCadastroToString}</td>
											<td>${turmaCorrente.nome}</td>
											<td>${turmaCorrente.nomeInstrutor}</td>
											<td>${turmaCorrente.tipo}</td>
											<td>${turmaCorrente.cargaHorariaToString}</td>
											<td>${turmaCorrente.dataInicioToString}</td>
											<td>${turmaCorrente.dataTerminoToString}</td>
											

											<td class="text-center">
												<a href="${rootWeb}/turmaAction.do?method=selecionar&idSelecionar=${turmaCorrente.idToString}">
													<i class="btn btn-xs btn-primary btn-outline glyphicon glyphicon-edit"></i>
												</a>
											</td>
										</tr>
									</logic:iterate>
								</tbody>

							</table>
						</div>
					</div>

				</div>
				<!-- FECHA O  panel-body -->

				<!-- RODAPÉ body PRINCIPAL -->
				<div class="panel-footer">
					<div class="row">

						<!-- Logica para definir a exibição do BOTAO inserir -->
						<logic:empty name="turmaForm" property="turma.idToString">
							<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
								<button type="submit" class="btn btn-success btn-block" id="inserir">
									<i class="glyphicon glyphicon-floppy-save"></i>
									Inserir
								</button>
							</div>
						</logic:empty>

						<!-- Logica para definir a exibição do BOTAO Alterar e Excluir -->
						<logic:notEmpty name="turmaForm" property="turma.idToString">
							<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
								<button type="submit" class="btn btn-primary btn-block" id="alterar">
									<i class="glyphicon glyphicon-retweet"></i>
									Alterar
								</button>
							</div>
							<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
								<button type="button" class="btn btn-danger btn-block" id="excluir">
									<i class="glyphicon glyphicon-remove"></i>
									Excluir
								</button>
							</div>
						</logic:notEmpty>

						<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
							<button type="button" class="btn btn-info btn-block" id="filtrar">
								<i class="glyphicon glyphicon-th-list"></i>
								Filtrar
							</button>
						</div>

						<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
							<button type="button" class="btn btn-warning btn-block" id="limpar">
								<i class="glyphicon glyphicon-erase"></i>
								Limpar
							</button>
						</div>

					</div>
				</div>

			</div>

		</html:form>

	</div>

</div>

<jsp:include page="../../template/imports/imports-js.jsp"></jsp:include>

<script type="text/javascript">
	/* 
	EXECUTADO APÓS A CARGA DA PAGINA 
	Trabalhando com JQuery para manipular os componentes.
	# pega os elementos pelo ID - $("#nome")
	. pega os elementos por CLASS - $(".bloqueado")
	attr serve para adicionar atributos em tempo de execução
	 */
	$(document).ready(function() {

		/* Bem Vindo ao ambiente JQuery */

		/* Setando o Foco Inicial */
		$('#tipo').focus();

		//Desliga o auto-complete da pagina
		$('#tagForm').prop('autocomplete', 'off');

		//Definindo os tamanhos maximos dos campos 
		$('#nome').prop('maxlength', 20);

		//Definindo os Placeholders dos campos
		$('#tipo').prop('placeholder', 'Tipo de aula. Ex: Java WEB');
		$('#nome').prop('placeholder', 'Nome da turma. Ex: Turma 3');
		$('#nomeInstrutor').prop('placeholder', 'Nome do instrutor. Ex: Gabriel');
		$('#dataInicioToString').mask('00/00/0000');
		$('#dataInicioToString').prop('placeholder','00/00/000');
		$('#dataTerminoToString').mask('00/00/0000');
		$('#dataTerminoToString').prop('placeholder','00/00/000');

		//(keyup)

		//DEFININDO OS EVENTOS DOS BOTOES
		$('#inserir').on('click', function() {
			$('#method').val('inserir');
		})
		$('#alterar').on('click', function() {
			$('#method').val('alterar');
		})
		$('#excluir').on('click', function() {

			BootstrapDialog.show({
				size : BootstrapDialog.SIZE_LARGE,
				title : 'Atenção',
				message : 'Tem certeza que deseja excluir esse registro',
				closable : true,
				type : BootstrapDialog.TYPE_DANGER,
				buttons : [ {
					label : 'Excluir',
					action : function(dialogRef) {
						$('#method').val('excluir');
						$('#tagForm').submit();

						dialogRef.close();
					}
				}, {
					label : 'Cancelar',
					action : function(dialogRef) {
						dialogRef.close();
					}
				} ]
			});

		});

		$('#filtrar').on('click', function() {
			$('#method').val('filtrar');
			$('#tagForm').submit();
		});
		$('#limpar').on('click', function() {
			$('#method').val('limpar');
			$('#tagForm').submit();
		});

	});
</script>