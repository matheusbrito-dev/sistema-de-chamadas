package br.com.srcsoftware.sistemadechamada.modulochamada.presenca.dao.teste;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;

import br.com.srcsoftware.manager.abstracts.AbstractPO;
import br.com.srcsoftware.manager.connection.HibernateConnection;
import br.com.srcsoftware.manager.exceptions.BackendException;
import br.com.srcsoftware.sistemadechamada.modulochamada.aluno.model.AlunoPO;
import br.com.srcsoftware.sistemadechamada.modulochamada.presenca.dao.PresencaDAO;
import br.com.srcsoftware.sistemadechamada.modulochamada.presenca.model.PresencaPO;
import br.com.srcsoftware.sistemadechamada.modulochamada.turma.model.TurmaPO;

public class TestaDAO {
	@Test
	public void executar() throws BackendException {
		HibernateConnection hibernate = new HibernateConnection();

		try {

			/** Testando o componente de Conexão no contexto do PresencaDAO */
			PresencaDAO dao = new PresencaDAO();

			/** Inserindo ocom base no componente */

			/** Inserindo com base no componente */
			hibernate.iniciarTransacao();

			PresencaPO po = new PresencaPO();
			
			po.setAluno(criarAluno());
			po.setPresente(true);
			po.setObservacao("Presente");
			po.setData(LocalDate.now());

			System.out.println( po );
			po = (PresencaPO) hibernate.inserir( po );
			System.out.println( po );

			hibernate.commitTransacao();

			/** Alterando com base no componente */
			hibernate.iniciarTransacao();
			po.setObservacao("Presente alterado");
			hibernate.alterar( po );
			System.out.println( po );
			hibernate.commitTransacao();

			/** Testando o filtrar por ID */
			AbstractPO encontrado = hibernate.filtrarPorId( Long.valueOf( "1" ), PresencaPO.class );
			System.out.println( "XXXXXXXXXXXXXXXX" + encontrado );

			/** Testando o Filtrar */
			PresencaPO poFiltrar = new PresencaPO();
			poFiltrar.setObservacao("Presente alterado");
			List< PresencaPO > encontrados = dao.filtrar( poFiltrar );
			System.out.println( "************Filtrar" + encontrados );

			/** Excluindo baseado no Componente */
			/*hibernate.iniciarTransacao();
			hibernate.excluir( po );
			hibernate.commitTransacao();*/

		} catch ( BackendException e ) {
			e.printStackTrace();
			hibernate.rollbackTransacao();

		}
	}

	private TurmaPO criarTurma() throws BackendException {
		HibernateConnection hibernate = new HibernateConnection();

		/** Inserindo com base no componente */
		hibernate.iniciarTransacao();

		TurmaPO po = new TurmaPO();
		po.setTipo("Java");
		po.setNomeInstrutor("Gabriel");
		po.setNome("Turma 3");
		po.setCargaHoraria(new Short ("4000"));
		po.setDataInicioToString("30/05/2018");
		po.setDataTerminoToString("31/06/2018");
		
		po.setCargaHoraria(new Short ("5000"));

		System.out.println( po );
		po = (TurmaPO) hibernate.inserir( po );
		System.out.println( po );
		hibernate.commitTransacao();

		return po;
	}
	
	private AlunoPO criarAluno() throws BackendException{
		HibernateConnection hibernate = new HibernateConnection();
		
		hibernate.iniciarTransacao();
		
		AlunoPO po = new AlunoPO();
		
		po.setTelefones("33243621");
		po.setNome("Matheus de Britossss");
		po.setEndereco("Rua Augusto Pietro");
		po.setFacebook("Matheus de Brito");
		po.setEmail("mathbvm@gmail.com");
		
		po.setTurma(criarTurma());
		
		System.out.println( po );
		po = (AlunoPO) hibernate.inserir( po );
		System.out.println( po );
		hibernate.commitTransacao();
		
		return po;
	}

}
