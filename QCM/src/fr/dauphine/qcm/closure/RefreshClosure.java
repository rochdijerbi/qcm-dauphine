package fr.dauphine.qcm.closure;

import org.apache.commons.collections.Closure;
import org.hibernate.Session;

/**
 * Rafraichit une entité dans la session Hibernate
 */
public class RefreshClosure implements Closure {

	/**
	 * Session Hibernate
	 */
	private Session session;

	/**
	 * Constructeur
	 * 
	 * @param session
	 *            Session Hibernate
	 */
	public RefreshClosure(Session session) {
		this.session = session;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(Object entity) {
		session.refresh(entity);
	}
}
