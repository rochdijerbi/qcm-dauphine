package fr.dauphine.qcm.component.repository;

import java.util.Collection;

import fr.dauphine.qcm.model.Tag;

public interface ITagRepository extends IAbstractRepository<Tag> {

	Collection<Tag> loadAllOrderedByQuestionnairesSize();

}
