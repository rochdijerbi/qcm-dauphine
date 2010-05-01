package fr.dauphine.qcm.closure;

import org.apache.commons.collections.Closure;
import org.hibernate.Session;

/**
 * Sauvegarde une entité dans la session Hibernate
 */
public class SaveOrUpdateClosure implements Closure {

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
	public SaveOrUpdateClosure(Session session) {
		this.session = session;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(Object entity) {
		session.saveOrUpdate(entity);
	}
}
