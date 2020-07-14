package br.com.srcsoftware.sistemadechamada.modulochamada.turma.controller.testabackend;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.srcsoftware.manager.exceptions.BackendException;
import br.com.srcsoftware.sistemadechamada.modulochamada.turma.controller.TurmaFACADE;
import br.com.srcsoftware.sistemadechamada.modulochamada.turma.model.TurmaPO;


public final class TestaBackend{

	public static void main( String[ ] args ) {

		try {

			/** Criando uma Turma */
			TurmaPO po = new TurmaPO();
			po.setTipo("Java");
			po.setNomeInstrutor("Gabriel");
			po.setNome("Turma 3");
			po.setDataInicioToString("30/05/2018");
			po.setDataTerminoToString("31/06/2018");
			po.setDataHoraCadrastro(LocalDateTime.now());
			po.setCargaHoraria(new Short ("5000"));

			/** Inserir */
			new TurmaFACADE().inserir( po );

			/** Filtrando todos para ver se inseriu */
			List encontrados = new TurmaFACADE().filtrar( null );
			System.out.println( encontrados );

			/** Filtrando por ID para alterar */
			TurmaPO encontrado = (TurmaPO) new TurmaFACADE().filtrarPorId( "1" );

			/** Alterando o Turma encontrada */
			encontrado.setNome( "Turma 8" );
			new TurmaFACADE().alterar( encontrado );

			/** Filtrando por ID para verificar alteração e excluir */
			encontrado = (TurmaPO) new TurmaFACADE().filtrarPorId( "1" );

			/** Excluindo */
			new TurmaFACADE().excluir( encontrado );

			/** Filtrando todos para ver se excluiu */
			encontrados = new TurmaFACADE().filtrar( null );
			System.out.println( encontrados );
		} catch ( BackendException e ) {
			e.printStackTrace();
			JOptionPane.showMessageDialog( null, e.getMessage() );
		} catch ( Exception e ) {
			e.printStackTrace();
			JOptionPane.showMessageDialog( null, e.getMessage() );
		}

	}

}