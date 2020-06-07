package org.dof.thesesmanager.web.rest;

import org.dof.thesesmanager.ThesesManagerApp;
import org.dof.thesesmanager.domain.Option;
import org.dof.thesesmanager.repository.OptionRepository;
import org.dof.thesesmanager.service.OptionService;
import org.dof.thesesmanager.service.dto.OptionDTO;
import org.dof.thesesmanager.service.mapper.OptionMapper;
import org.dof.thesesmanager.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static org.dof.thesesmanager.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.dof.thesesmanager.domain.enumeration.Status;
/**
 * Integration tests for the {@link OptionResource} REST controller.
 */
@SpringBootTest(classes = ThesesManagerApp.class)
public class OptionResourceIT {

    private static final String DEFAULT_OPTION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_OPTION_NAME = "BBBBBBBBBB";

    private static final Status DEFAULT_STATUS = Status.ENABLED;
    private static final Status UPDATED_STATUS = Status.DISABLED;

    @Autowired
    private OptionRepository optionRepository;

    @Autowired
    private OptionMapper optionMapper;

    @Autowired
    private OptionService optionService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restOptionMockMvc;

    private Option option;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OptionResource optionResource = new OptionResource(optionService);
        this.restOptionMockMvc = MockMvcBuilders.standaloneSetup(optionResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Option createEntity(EntityManager em) {
        Option option = new Option()
            .optionName(DEFAULT_OPTION_NAME)
            .status(DEFAULT_STATUS);
        return option;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Option createUpdatedEntity(EntityManager em) {
        Option option = new Option()
            .optionName(UPDATED_OPTION_NAME)
            .status(UPDATED_STATUS);
        return option;
    }

    @BeforeEach
    public void initTest() {
        option = createEntity(em);
    }

    @Test
    @Transactional
    public void createOption() throws Exception {
        int databaseSizeBeforeCreate = optionRepository.findAll().size();

        // Create the Option
        OptionDTO optionDTO = optionMapper.toDto(option);
        restOptionMockMvc.perform(post("/api/options")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(optionDTO)))
            .andExpect(status().isCreated());

        // Validate the Option in the database
        List<Option> optionList = optionRepository.findAll();
        assertThat(optionList).hasSize(databaseSizeBeforeCreate + 1);
        Option testOption = optionList.get(optionList.size() - 1);
        assertThat(testOption.getOptionName()).isEqualTo(DEFAULT_OPTION_NAME);
        assertThat(testOption.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createOptionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = optionRepository.findAll().size();

        // Create the Option with an existing ID
        option.setId(1L);
        OptionDTO optionDTO = optionMapper.toDto(option);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOptionMockMvc.perform(post("/api/options")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(optionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Option in the database
        List<Option> optionList = optionRepository.findAll();
        assertThat(optionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllOptions() throws Exception {
        // Initialize the database
        optionRepository.saveAndFlush(option);

        // Get all the optionList
        restOptionMockMvc.perform(get("/api/options?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(option.getId().intValue())))
            .andExpect(jsonPath("$.[*].optionName").value(hasItem(DEFAULT_OPTION_NAME)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getOption() throws Exception {
        // Initialize the database
        optionRepository.saveAndFlush(option);

        // Get the option
        restOptionMockMvc.perform(get("/api/options/{id}", option.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(option.getId().intValue()))
            .andExpect(jsonPath("$.optionName").value(DEFAULT_OPTION_NAME))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingOption() throws Exception {
        // Get the option
        restOptionMockMvc.perform(get("/api/options/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOption() throws Exception {
        // Initialize the database
        optionRepository.saveAndFlush(option);

        int databaseSizeBeforeUpdate = optionRepository.findAll().size();

        // Update the option
        Option updatedOption = optionRepository.findById(option.getId()).get();
        // Disconnect from session so that the updates on updatedOption are not directly saved in db
        em.detach(updatedOption);
        updatedOption
            .optionName(UPDATED_OPTION_NAME)
            .status(UPDATED_STATUS);
        OptionDTO optionDTO = optionMapper.toDto(updatedOption);

        restOptionMockMvc.perform(put("/api/options")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(optionDTO)))
            .andExpect(status().isOk());

        // Validate the Option in the database
        List<Option> optionList = optionRepository.findAll();
        assertThat(optionList).hasSize(databaseSizeBeforeUpdate);
        Option testOption = optionList.get(optionList.size() - 1);
        assertThat(testOption.getOptionName()).isEqualTo(UPDATED_OPTION_NAME);
        assertThat(testOption.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingOption() throws Exception {
        int databaseSizeBeforeUpdate = optionRepository.findAll().size();

        // Create the Option
        OptionDTO optionDTO = optionMapper.toDto(option);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOptionMockMvc.perform(put("/api/options")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(optionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Option in the database
        List<Option> optionList = optionRepository.findAll();
        assertThat(optionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOption() throws Exception {
        // Initialize the database
        optionRepository.saveAndFlush(option);

        int databaseSizeBeforeDelete = optionRepository.findAll().size();

        // Delete the option
        restOptionMockMvc.perform(delete("/api/options/{id}", option.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Option> optionList = optionRepository.findAll();
        assertThat(optionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
