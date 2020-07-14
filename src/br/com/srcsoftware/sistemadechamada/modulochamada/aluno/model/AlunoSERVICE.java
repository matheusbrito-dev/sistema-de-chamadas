package br.com.srcsoftware.sistemadechamada.modulochamada.aluno.model;

import java.util.List;

import br.com.srcsoftware.manager.abstracts.AbstractPO;
import br.com.srcsoftware.manager.connection.HibernateConnection;
import br.com.srcsoftware.manager.exceptions.BackendException;
import br.com.srcsoftware.manager.interfaces.Crud;
import br.com.srcsoftware.sistemadechamada.modulochamada.aluno.dao.AlunoDAO;

public final class AlunoSERVICE implements Crud{
	/** Garante a aplicação da associação entre o SERVICE e o DAO */
	/**
	 * Toda constante deve possuir um valor definido
	 * Os unicos lugares possíveis de inicialização de uma constante
	 * são:
	 * - No ato da DECLAÇÃO ex:("AlunoDAO DAO = new AlunoDAO()")
	 * - Na PRIMEIRA LINHA DO CONSTRUTOR;
	 * ------------AlunoSERVICE(){
	 * ---------------DAO = new AlunoDAO();
	 * ------------}
	 * -Em um Bloco Estático
	 * ------------static{
	 * ------------------DAO = new AlunoDAO();
	 * ------------}
	 */
	private final AlunoDAO DAO;

	public AlunoSERVICE(){
		//Quando o FACEDE instanciar o SERVICE automaticamente irá para o DAO
		DAO = new AlunoDAO();
	}

	@Override
	public void inserir( final AbstractPO PO ) throws BackendException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			/** Inicio do bloco de Transação */
			hibernate.iniciarTransacao();

			if ( PO == null ) {
				throw new BackendException( "Objeto nulo passado como parametro!" );
			}
			AlunoPO aluno = null;
			if ( PO instanceof AlunoPO ) {
				aluno = (AlunoPO) PO;
			} else {
				throw new BackendException( "O Objeto PO passado não condiz com o contexto!" );
			}

			DAO.inserir( hibernate, aluno );
			hibernate.commitTransacao();
			/** Fim do bloco de transação */

		} catch ( BackendException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BackendException( "Erro desconhecido ao inserir ", e );
		}

	}

	@Override
	public void alterar( final AbstractPO PO ) throws BackendException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			/** Inicio do bloco de Transação */
			hibernate.iniciarTransacao();

			if ( PO == null ) {
				throw new BackendException( "Objeto nulo passado como parametro!" );
			}
			AlunoPO aluno = null;
			if ( PO instanceof AlunoPO ) {
				aluno = (AlunoPO) PO;
			} else {
				throw new BackendException( "O Objeto PO passado não condiz com o contexto!" );
			}

			DAO.alterar( hibernate, aluno );
			hibernate.commitTransacao();
			/** Fim do bloco de transação */

		} catch ( BackendException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BackendException( "Erro desconhecido ao alterar ", e );
		}

	}

	@Override
	public void excluir( final AbstractPO PO ) throws BackendException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			/** Inicio do bloco de Transação */
			hibernate.iniciarTransacao();

			if ( PO == null ) {
				throw new BackendException( "Objeto nulo passado como parametro!" );
			}
			AlunoPO aluno = null;
			if ( PO instanceof AlunoPO ) {
				aluno = (AlunoPO) PO;
			} else {
				throw new BackendException( "O Objeto PO passado não condiz com o contexto!" );
			}

			DAO.excluir( hibernate, aluno );
			hibernate.commitTransacao();
			/** Fim do bloco de transação */

		} catch ( BackendException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BackendException( "Erro desconhecido ao excluir ", e );
		}

	}

	@Override
	public List filtrar( final AbstractPO PO ) throws BackendException {
		try {
			AlunoPO aluno = null;

			if ( PO != null ) {

				if ( PO instanceof AlunoPO ) {
					aluno = (AlunoPO) PO;
				} else {
					throw new BackendException( "O Objeto PO passado não condiz com o contexto!" );
				}
			}

			return DAO.filtrar( aluno );
		} catch ( BackendException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BackendException( "Erro desconhecido ao excluir ", e );
		}
	}

	@Override
	public AbstractPO filtrarPorId( final String ID ) throws BackendException {
		try {

			if ( ID == null ) {
				throw new BackendException( "ID nulo passado como parâmetro" );
			}

			return DAO.filtrarPorId( Long.valueOf( ID ) );
		} catch ( BackendException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BackendException( "Erro desconhecido ao excluir ", e );
		}

	}

}
