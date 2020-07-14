package br.com.srcsoftware.sistemadechamada.modulochamada.presenca.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.srcsoftware.manager.connection.HibernateConnection;
import br.com.srcsoftware.manager.exceptions.BackendException;
import br.com.srcsoftware.sistemadechamada.modulochamada.presenca.model.PresencaPO;
import br.com.srcsoftware.sistemadechamada.modulochamada.aluno.model.AlunoPO;

public class PresencaDAO {

	public void inserir( HibernateConnection hibernate, PresencaPO po ) throws BackendException {
		/**
		 * Invocando o método inserir do nosso
		 * componente de Conexão HibernateConnection
		 */

		PresencaPO poInserido = (PresencaPO) hibernate.inserir( po );

		/** Pegando o ID do Presenca Recem inserida */
		po.setId( poInserido.getId() );
	}

	public void alterar( HibernateConnection hibernate, PresencaPO po ) throws BackendException {

		hibernate.alterar( po );
	}

	public void excluir( HibernateConnection hibernate, PresencaPO po ) throws BackendException {

		hibernate.excluir( po );
	}

	public PresencaPO filtrarPorId( Long id ) throws BackendException {
		return (PresencaPO) new HibernateConnection().filtrarPorId( id, PresencaPO.class );
	}

	public List< PresencaPO > filtrar( PresencaPO poFiltrar ) throws BackendException {

		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			//Fazer o que tem que fazer
			/** utilizando CriteriaBuilder para a confecção de nossas Queries */
			//Criando a Criteria com base na CriteriaBuilder
			CriteriaBuilder builder = hibernate.getCurrentSession().getCriteriaBuilder();
			CriteriaQuery criteria = builder.createQuery( PresencaPO.class );

			//Definindo o FROM
			Root root = criteria.from( PresencaPO.class );

			//Deixando a Criteria preparada para a consulta
			criteria.select( root );

			//Definindo os Parametros(Predicados) para a consulta(WHERE)
			ArrayList< Predicate > predicados = new ArrayList< Predicate >();

			if ( poFiltrar != null ) {
				if ( poFiltrar.getData() != null) {
					Predicate dataParam = builder.equal(root.get("data"), poFiltrar.getData());
					predicados.add( dataParam );
				}
				if ( poFiltrar.getPresente() != null) {
					Predicate presenteParam = builder.equal(root.get("presente"), poFiltrar.getPresente());
					predicados.add( presenteParam );
				}
				if ( poFiltrar.getObservacao() != null && poFiltrar.getObservacao().isEmpty() ) {
					Predicate observacaoParam = builder.like( root.get( "observacao" ), poFiltrar.getObservacao().concat( "%" ) );
					predicados.add( observacaoParam );
				}

				/** Criando um JOIN com AlunoPO */
				Join< PresencaPO, AlunoPO > joinAluno = root.join( "aluno", JoinType.INNER );
				/*Sub join do join
				 * Join< AlunoPO, SubAlunoPO > joinSupAluno = joinAluno.join( "supAluno", JoinType.INNER );*/
				if ( poFiltrar.getAluno().getNome() != null && !poFiltrar.getAluno().getNome().isEmpty() ) {
					Predicate nomeAlunoParam = builder.like( joinAluno.get( "nome" ), poFiltrar.getAluno().getNome().concat( "%" ) );
					predicados.add( nomeAlunoParam );
				}

			}

			//Adicionando o Predicado no WHERE
			criteria.where( predicados.toArray( new Predicate[ predicados.size() ] ) );

			List< PresencaPO > encontrados = hibernate.getCurrentSession().createQuery( criteria ).getResultList();

			hibernate.commitTransacao();

			return encontrados;

		} catch ( BackendException e ) {
			hibernate.rollbackTransacao();
			throw e;
		} catch ( Throwable e ) {
			hibernate.rollbackTransacao();
			throw new BackendException( "Erro ao filtrar", e );
		}

	}
	
}
