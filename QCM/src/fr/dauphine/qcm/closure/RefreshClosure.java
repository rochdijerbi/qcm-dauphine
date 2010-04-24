package fr.dauphine.qcm.closure;

import org.apache.commons.collections.Closure;
import org.hibernate.Session;

public class RefreshClosure implements Closure {

	private Session session;
	
	public RefreshClosure(Session session) {
		this.session = session;
	}
	
	@Override
	public void execute(Object entity) {
		session.refresh(entity);
	}
}
