package fr.dauphine.qcm.util;

import javax.servlet.http.HttpSession;

import fr.dauphine.qcm.model.User;

/**
 * Gestion de l'utilisateur connecte.
 */
public final class UserUtil {

	/**
	 * Cle dans la session HTTP.
	 */
	public static final String SESSION_KEY = "connected_user";

	/**
	 * Constructeur prive.
	 */
	private UserUtil() {
	}

	/**
	 * Stocke l'utilisateur dans la session.
	 * 
	 * @param session
	 *            Session HTTP
	 * @param user
	 *            Utilisateur connecte
	 */
	public static void setUser(HttpSession session, User user) {
		session.setAttribute(SESSION_KEY, user);
	}

	/**
	 * Retire l'utilisateur de la session.
	 * 
	 * @param session
	 *            Session HTTP
	 */
	public static void unsetUser(HttpSession session) {
		session.removeAttribute(SESSION_KEY);
	}

	/**
	 * Recupere l'utilisateur dans la session.
	 * 
	 * @param session
	 *            Session HTTP
	 * @return Utilisateur connecte
	 */
	public static User getUser(HttpSession session) {
		return (User) session.getAttribute(SESSION_KEY);
	}
}
