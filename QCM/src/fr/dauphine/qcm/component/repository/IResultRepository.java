package fr.dauphine.qcm.component.repository;

import fr.dauphine.qcm.model.Result;

public interface IResultRepository extends IAbstractRepository<Result> {

	Result loadByUserIdAndQuestionnaireId(Long userId, Long questionnaireId);

}
