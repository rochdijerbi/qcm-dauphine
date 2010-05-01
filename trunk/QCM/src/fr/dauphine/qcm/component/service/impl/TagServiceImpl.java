package fr.dauphine.qcm.component.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.dauphine.qcm.component.repository.ITagRepository;
import fr.dauphine.qcm.component.service.ITagService;
import fr.dauphine.qcm.model.Tag;

@Service
@Transactional(readOnly = true)
public class TagServiceImpl implements ITagService {

	@Autowired
	private ITagRepository tagRepository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Collection<Tag> getAll() {
		return tagRepository.loadAllOrderedByQuestionnairesSize();
	}
}
