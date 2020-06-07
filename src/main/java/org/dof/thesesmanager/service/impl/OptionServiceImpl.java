package org.dof.thesesmanager.service.impl;

import org.dof.thesesmanager.service.OptionService;
import org.dof.thesesmanager.domain.Option;
import org.dof.thesesmanager.repository.OptionRepository;
import org.dof.thesesmanager.service.dto.OptionDTO;
import org.dof.thesesmanager.service.mapper.OptionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Option}.
 */
@Service
@Transactional
public class OptionServiceImpl implements OptionService {

    private final Logger log = LoggerFactory.getLogger(OptionServiceImpl.class);

    private final OptionRepository optionRepository;

    private final OptionMapper optionMapper;

    public OptionServiceImpl(OptionRepository optionRepository, OptionMapper optionMapper) {
        this.optionRepository = optionRepository;
        this.optionMapper = optionMapper;
    }

    /**
     * Save a option.
     *
     * @param optionDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public OptionDTO save(OptionDTO optionDTO) {
        log.debug("Request to save Option : {}", optionDTO);
        Option option = optionMapper.toEntity(optionDTO);
        option = optionRepository.save(option);
        return optionMapper.toDto(option);
    }

    /**
     * Get all the options.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OptionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Options");
        return optionRepository.findAll(pageable)
            .map(optionMapper::toDto);
    }


    /**
     * Get one option by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<OptionDTO> findOne(Long id) {
        log.debug("Request to get Option : {}", id);
        return optionRepository.findById(id)
            .map(optionMapper::toDto);
    }

    /**
     * Delete the option by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Option : {}", id);
        optionRepository.deleteById(id);
    }
}
