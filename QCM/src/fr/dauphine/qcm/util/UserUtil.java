package fr.dauphine.qcm.util;

import javax.servlet.http.HttpSession;

import fr.dauphine.qcm.model.User;

public final class UserUtil {

	public static final String SESSION_KEY = "user";
	
	private UserUtil() {
	}
	
	public static void setUser(HttpSession session, User user) {
		session.setAttribute(SESSION_KEY, user);
	}
	
	public static void unsetUser(HttpSession session) {
		session.removeAttribute(SESSION_KEY);
	}
	
	public static User getUser(HttpSession session) {
		return (User) session.getAttribute(SESSION_KEY);
	}
}
