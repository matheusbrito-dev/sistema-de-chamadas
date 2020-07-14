package br.com.srcsoftware.sistemadechamada.modulochamada.aluno.controller.testabackend;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.srcsoftware.manager.exceptions.BackendException;
import br.com.srcsoftware.sistemadechamada.modulochamada.aluno.controller.AlunoFACADE;
import br.com.srcsoftware.sistemadechamada.modulochamada.aluno.model.AlunoPO;
import br.com.srcsoftware.sistemadechamada.modulochamada.turma.controller.TurmaFACADE;
import br.com.srcsoftware.sistemadechamada.modulochamada.turma.model.TurmaPO;

public class TestaBackend {

	public static void main( String[ ] args ) {

		try {

			/** Criando uma Aluno */
			AlunoPO po = new AlunoPO();
			po.setTelefones("33243621");
			po.setNome("Matheus de Britossss");
			po.setEndereco("Rua Augusto Pietro");
			po.setFacebook("Matheus de Brito");
			po.setEmail("mathbvm@gmail.com");
			po.setTurma(criarTurma());

			/** Inserir */
			new AlunoFACADE().inserir( po );

			/** Filtrando todos para ver se inseriu */
			List encontrados = new AlunoFACADE().filtrar( null );
			System.out.println( encontrados );

			/** Filtrando por ID para alterar */
			AlunoPO encontrado = (AlunoPO) new AlunoFACADE().filtrarPorId( "1" );

			/** Alterando o Aluno encontrada */
			encontrado.setNome( "Matheus de Brito Vieira" );
			new AlunoFACADE().alterar( encontrado );

			/** Filtrando por ID para verificar alteração e excluir */
			encontrado = (AlunoPO) new AlunoFACADE().filtrarPorId( "1" );

			/** Excluindo */
			new AlunoFACADE().excluir( encontrado );

			/** Filtrando todos para ver se excluiu */
			encontrados = new AlunoFACADE().filtrar( null );
			System.out.println( encontrados );
		} catch ( BackendException e ) {
			e.printStackTrace();
			JOptionPane.showMessageDialog( null, e.getMessage() );
		} catch ( Exception e ) {
			e.printStackTrace();
			JOptionPane.showMessageDialog( null, e.getMessage() );
		}

	}

	private static TurmaPO criarTurma() throws BackendException {

		TurmaPO po = new TurmaPO();
		po.setTipo("Java");
		po.setNomeInstrutor("Gabriel");
		po.setNome("Turma 3");
		po.setCargaHoraria(new Short ("4000"));
		po.setDataInicioToString("30/05/2018");
		po.setDataTerminoToString("31/06/2018");
		po.setDataHoraCadrastro(LocalDateTime.now());

		new TurmaFACADE().inserir( po );

		return po;
	}
	
	
}
