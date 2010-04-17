package fr.dauphine.qcm.component.repository.impl;

import org.springframework.stereotype.Repository;

import fr.dauphine.qcm.component.repository.IResultRepository;
import fr.dauphine.qcm.model.Result;

@Repository
public final class ResultRepositoryImpl extends AbstractRepositoryImpl<Result>
		implements IResultRepository {

}
