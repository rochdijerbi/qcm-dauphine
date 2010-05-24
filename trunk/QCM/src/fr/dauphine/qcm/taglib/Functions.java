package fr.dauphine.qcm.taglib;

import java.util.Collection;

/**
 * Fonctions taglib supplementaires.
 */
public class Functions {

	/**
	 * Verifie si un objet est present dans une liste.
	 * 
	 * @param list
	 *            Liste conteneur
	 * @param object
	 *            Objet contenu
	 * @return Vrai si l'objet est dans la liste, faux sinon
	 */
	public static boolean contains(Collection<?> list, Object object) {
		return list.contains(object);
	}
}
