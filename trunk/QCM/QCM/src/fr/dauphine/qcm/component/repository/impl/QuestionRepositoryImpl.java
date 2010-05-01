package fr.dauphine.qcm.component.repository.impl;

import org.springframework.stereotype.Repository;

import fr.dauphine.qcm.component.repository.IQuestionRepository;
import fr.dauphine.qcm.model.Question;

@Repository
public final class QuestionRepositoryImpl extends
		AbstractRepositoryImpl<Question> implements IQuestionRepository {

}
