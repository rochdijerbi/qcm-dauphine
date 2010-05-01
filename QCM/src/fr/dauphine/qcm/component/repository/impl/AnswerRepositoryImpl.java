package fr.dauphine.qcm.component.repository.impl;

import org.springframework.stereotype.Repository;

import fr.dauphine.qcm.component.repository.IAnswerRepository;
import fr.dauphine.qcm.model.Answer;

@Repository
public final class AnswerRepositoryImpl extends AbstractRepositoryImpl<Answer> implements IAnswerRepository {

}
