package fr.dauphine.qcm.component.repository.impl;

import org.springframework.stereotype.Repository;

import fr.dauphine.qcm.component.repository.IQuestionnaireRepository;
import fr.dauphine.qcm.model.Questionnaire;

@Repository
public final class QuestionnaireRepositoryImpl extends
		AbstractRepositoryImpl<Questionnaire> implements IQuestionnaireRepository {

}
