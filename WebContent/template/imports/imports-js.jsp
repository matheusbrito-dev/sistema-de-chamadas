<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>


  <!-- jQuery -->
    <script src="${rootWeb}/assets/sb_admin_2/vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="${rootWeb}/assets/sb_admin_2/vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="${rootWeb}/assets/sb_admin_2/vendor/metisMenu/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="${rootWeb}/assets/sb_admin_2/dist/js/sb-admin-2.js"></script>
    
    
	<!--MASCARA-->
	<script src = "${rootWeb}/assets/jquery_mask_plugin-master/dist/jquery.mask.min.js"></script>

	<!-- MODAL (Bootstrap-Dialog) -->
	<script src = "${rootWeb }/assets/dialog/dist/js/bootstrap-dialog.js"></script>
	
	<script type="text/javascript">

	$(document).ready(function(){
		
		/* Bem Vindo ao ambiente JQuery */
		
		/* Setando o Foco Inicial */
		$('#nome').focus();
		
		//Desliga o auto-complete da pagina
		$('#tagForm').prop('autocomplete','off');
		
		// Bloquando campos
		$('.bloqueado').prop('disabled',true);
		
		// Criando a estilização para campos obrigatórios
		$('.obrigatorio').prop('required','required');
		$('.obrigatorio').css('border-color','orange');
		
		// Definindo as Mascaras
		$('.dinheiro').mask('000.000.000.000.000,00',{
			reverse: true
			
		});
	});
	</script>