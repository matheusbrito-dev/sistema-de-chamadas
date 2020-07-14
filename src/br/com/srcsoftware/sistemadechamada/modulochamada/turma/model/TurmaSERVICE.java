package br.com.srcsoftware.sistemadechamada.modulochamada.turma.model;

import java.util.List;

import br.com.srcsoftware.manager.abstracts.AbstractPO;
import br.com.srcsoftware.manager.connection.HibernateConnection;
import br.com.srcsoftware.manager.exceptions.BackendException;
import br.com.srcsoftware.manager.interfaces.Crud;
import br.com.srcsoftware.manager.utilidades.Utilidades;
import br.com.srcsoftware.sistemadechamada.modulochamada.turma.dao.TurmaDAO;

public class TurmaSERVICE implements Crud{

	private final TurmaDAO DAO;

	public TurmaSERVICE(){
		//Quando o FACEDE instanciar o SERVICE automaticamente irá para o DAO
		DAO = new TurmaDAO();
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
			TurmaPO turma = null;
			if ( PO instanceof TurmaPO ) {
				turma = (TurmaPO) PO;
			} else {
				throw new BackendException( "O Objeto PO passado não condiz com o contexto!" );
			}
			
			if ( !turma.getNomeInstrutor().matches(Utilidades.REGEX_SOMENTE_LETRAS_E_ESPACO) ) {
				throw new BackendException( "No nome não são permitidos caracteres numéricos!" );
			}
			if ( !turma.getTipo().matches(Utilidades.REGEX_SOMENTE_LETRAS_E_ESPACO) ) {
				throw new BackendException( "No nome não são permitidos caracteres numéricos!" );
			}
			

			DAO.inserir( hibernate, turma );
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
			TurmaPO turma = null;
			if ( PO instanceof TurmaPO ) {
				turma = (TurmaPO) PO;
			} else {
				throw new BackendException( "O Objeto PO passado não condiz com o contexto!" );
			}

			if ( !turma.getNomeInstrutor().matches(Utilidades.REGEX_SOMENTE_LETRAS_E_ESPACO) ) {
				throw new BackendException( "No nome não são permitidos caracteres numéricos!" );
			}
			if ( !turma.getTipo().matches(Utilidades.REGEX_SOMENTE_LETRAS_E_ESPACO) ) {
				throw new BackendException( "No nome não são permitidos caracteres numéricos!" );
			}

			DAO.alterar( hibernate, turma );
			hibernate.commitTransacao();

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
			TurmaPO turma = null;
			if ( PO instanceof TurmaPO ) {
				turma = (TurmaPO) PO;
			} else {
				throw new BackendException( "O Objeto PO passado não condiz com o contexto!" );
			}

			DAO.excluir( hibernate, turma );
			hibernate.commitTransacao();

		} catch ( BackendException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BackendException( "Erro desconhecido ao excluir ", e );
		}

	}

	@Override
	public List filtrar( final AbstractPO PO ) throws BackendException {
		try {
			TurmaPO turma = null;

			if ( PO != null ) {

				if ( PO instanceof TurmaPO ) {
					turma = (TurmaPO) PO;
				} else {
					throw new BackendException( "O Objeto PO passado não condiz com o contexto!" );
				}
			}

			return DAO.filtrar( turma );
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

			System.out.println( "SERVICE: filtrando por id" );

			return DAO.filtrarPorId( Long.valueOf( ID ) );
		} catch ( BackendException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BackendException( "Erro desconhecido ao excluir ", e );
		}

	}
	
	
}
