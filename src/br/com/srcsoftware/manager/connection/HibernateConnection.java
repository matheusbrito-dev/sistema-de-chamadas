package br.com.srcsoftware.manager.connection;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import br.com.srcsoftware.manager.abstracts.AbstractPO;
import br.com.srcsoftware.manager.exceptions.BackendException;
import br.com.srcsoftware.manager.utilidades.Utilidades;

public final class HibernateConnection{

	private StandardServiceRegistry registry;
	private static SessionFactory sessionFactory;
	private Session currentSession;
	private Transaction transaction;

	public void destroy() {
		if ( registry != null ) {
			StandardServiceRegistryBuilder.destroy( registry );
		}
	}

	private SessionFactory getSessionFactory() {
		/** Usando o Singleton */
		if ( sessionFactory == null ) {
			try {
				/**
				 * Criando um registry builder
				 * Usado para definir os property do hibernate.cfg.xml aqui no java.
				 * Ex: registryBuilder.applySetting( Environment.DRIVER, "com.mysql.jdbc.Driver" );
				 */
				StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();

				/** Criando o Resgistry */
				registry = registryBuilder.configure( "hibernate.cfg.xml" ).build();

				/**
				 * Criando o MetadataSource
				 * Usado para informar os mapeamentos dos POS caso queira fazer pelo Java.
				 * Ex: sources.addAnnotatedClass( AbstractPO.class );
				 */
				MetadataSources sources = new MetadataSources( registry );

				/** Criando o MetadataBuilder */
				MetadataBuilder metadataBuilder = sources.getMetadataBuilder();

				//Especificando o SCHEMA padrão que sera usado para todas as Tabelas. pS: Funciona apenas quando não informamos explicitamente
				metadataBuilder.applyImplicitCatalogName( Utilidades.SCHEMA );

				Metadata metadata = metadataBuilder.build();

				/** Criando o SessionFactory */
				sessionFactory = metadata.getSessionFactoryBuilder().build();

			} catch ( Throwable e ) {
				e.printStackTrace();
				destroy();
			}
		}

		return sessionFactory;
	}

	public Session getCurrentSession() throws BackendException {
		if ( currentSession == null ) {
			throw new BackendException( "A sessão não está aberta!" );
		}
		return currentSession;
	}

	public void setCurrentSession( Session currentSession ) {
		this.currentSession = currentSession;
	}

	private void abrirSessao() {
		setCurrentSession( getSessionFactory().openSession() );
	}

	private void fecharSessao() throws BackendException {

		getCurrentSession().close();
		setCurrentSession( null );
	}

	/**
	 * 
	 * Método responsável por Iniciar uma transação com o banco de dados
	 * 
	 * @throws BackendException
	 *
	 * @author Matheus de Brito Vieira Martins <mathbvm@gmail.com.br>
	 * @since 7 de ago de 2018 19:28:06
	 * @version 1.0
	 */

	public void iniciarTransacao() throws BackendException {
		abrirSessao();
		transaction = getCurrentSession().beginTransaction();
	}

	public void commitTransacao() throws BackendException {
		if ( transaction == null ) {
			throw new BackendException( "A sessão não está aberta!" );
		}

		transaction.commit();
		fecharSessao();
		transaction = null;
	}

	public void rollbackTransacao() throws BackendException {

		if ( transaction == null ) {
			throw new BackendException( "A sessão não está aberta!" );
		}

		transaction.rollback();
		fecharSessao();
		transaction = null;
	}

	public AbstractPO inserir( AbstractPO po ) throws BackendException {
		try {
			return (AbstractPO) getCurrentSession().merge( po );
		} catch ( Throwable e ) {
			throw new BackendException( "Erro ao Inserir", e );
		}
	}

	public void alterar( AbstractPO po ) throws BackendException {
		try {
			getCurrentSession().merge( po );
		} catch ( Throwable e ) {
			throw new BackendException( "Erro ao alterar", e );
		}
	}

	public void excluir( AbstractPO po ) throws BackendException {
		try {
			getCurrentSession().delete( po );
		} catch ( Throwable e ) {
			throw new BackendException( "Erro ao Exclur", e );
		}
	}

	public AbstractPO filtrarPorId( Long id, Class clazz ) throws BackendException {
		try {
			iniciarTransacao();

			/** utilizando CriteriaBuilder para a confecção de nossas Queries */
			//Criando a Criteria com base na CriteriaBuilder
			CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
			CriteriaQuery criteria = builder.createQuery( clazz );

			//Definindo o From
			Root root = criteria.from( clazz );

			//Deixando a Criteria preparada para a consulta
			criteria.select( root );

			//Definindo os parametros do WHERE
			Predicate idParam = builder.equal( root.get( "id" ), id );

			//Adicionando o Predicado no WHERE
			criteria.where( idParam );

			Object encontrado = getCurrentSession().createQuery( criteria ).getSingleResult();

			commitTransacao();

			return (AbstractPO) encontrado;

		} catch ( javax.persistence.NoResultException e ) {
			return null;

		} catch ( Throwable e ) {
			throw new BackendException( "Erro ao Filtrar por ID", e );
		}
	}
	
}
