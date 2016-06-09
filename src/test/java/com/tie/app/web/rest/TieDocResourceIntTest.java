package com.tie.app.web.rest;

import com.tie.app.TieEntityApp;
import com.tie.app.domain.TieDoc;
import com.tie.app.repository.TieDocRepository;
import com.tie.app.repository.search.TieDocSearchRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the TieDocResource REST controller.
 *
 * @see TieDocResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TieEntityApp.class)
@WebAppConfiguration
@IntegrationTest
public class TieDocResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB";
    private static final String DEFAULT_CODE = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB";
    private static final String DEFAULT_DESCRIPTION = "AAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBB";
    private static final String DEFAULT_REPORTING_ENTITY_CODE = "AAAAA";
    private static final String UPDATED_REPORTING_ENTITY_CODE = "BBBBB";
    private static final String DEFAULT_CURRENCY_CODE = "AAAAA";
    private static final String UPDATED_CURRENCY_CODE = "BBBBB";
    private static final String DEFAULT_RES_COUNTRY_CODE = "AAAAA";
    private static final String UPDATED_RES_COUNTRY_CODE = "BBBBB";

    @Inject
    private TieDocRepository tieDocRepository;

    @Inject
    private TieDocSearchRepository tieDocSearchRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restTieDocMockMvc;

    private TieDoc tieDoc;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TieDocResource tieDocResource = new TieDocResource();
        ReflectionTestUtils.setField(tieDocResource, "tieDocSearchRepository", tieDocSearchRepository);
        ReflectionTestUtils.setField(tieDocResource, "tieDocRepository", tieDocRepository);
        this.restTieDocMockMvc = MockMvcBuilders.standaloneSetup(tieDocResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        tieDocSearchRepository.deleteAll();
        tieDoc = new TieDoc();
        tieDoc.setName(DEFAULT_NAME);
        tieDoc.setCode(DEFAULT_CODE);
        tieDoc.setDescription(DEFAULT_DESCRIPTION);
        tieDoc.setReportingEntityCode(DEFAULT_REPORTING_ENTITY_CODE);
        tieDoc.setCurrencyCode(DEFAULT_CURRENCY_CODE);
        tieDoc.setResCountryCode(DEFAULT_RES_COUNTRY_CODE);
    }

    @Test
    @Transactional
    public void createTieDoc() throws Exception {
        int databaseSizeBeforeCreate = tieDocRepository.findAll().size();

        // Create the TieDoc

        restTieDocMockMvc.perform(post("/api/tie-docs")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(tieDoc)))
                .andExpect(status().isCreated());

        // Validate the TieDoc in the database
        List<TieDoc> tieDocs = tieDocRepository.findAll();
        assertThat(tieDocs).hasSize(databaseSizeBeforeCreate + 1);
        TieDoc testTieDoc = tieDocs.get(tieDocs.size() - 1);
        assertThat(testTieDoc.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testTieDoc.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testTieDoc.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testTieDoc.getReportingEntityCode()).isEqualTo(DEFAULT_REPORTING_ENTITY_CODE);
        assertThat(testTieDoc.getCurrencyCode()).isEqualTo(DEFAULT_CURRENCY_CODE);
        assertThat(testTieDoc.getResCountryCode()).isEqualTo(DEFAULT_RES_COUNTRY_CODE);

        // Validate the TieDoc in ElasticSearch
        TieDoc tieDocEs = tieDocSearchRepository.findOne(testTieDoc.getId());
        assertThat(tieDocEs).isEqualToComparingFieldByField(testTieDoc);
    }

    @Test
    @Transactional
    public void getAllTieDocs() throws Exception {
        // Initialize the database
        tieDocRepository.saveAndFlush(tieDoc);

        // Get all the tieDocs
        restTieDocMockMvc.perform(get("/api/tie-docs?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(tieDoc.getId().intValue())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
                .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
                .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
                .andExpect(jsonPath("$.[*].reportingEntityCode").value(hasItem(DEFAULT_REPORTING_ENTITY_CODE.toString())))
                .andExpect(jsonPath("$.[*].currencyCode").value(hasItem(DEFAULT_CURRENCY_CODE.toString())))
                .andExpect(jsonPath("$.[*].resCountryCode").value(hasItem(DEFAULT_RES_COUNTRY_CODE.toString())));
    }

    @Test
    @Transactional
    public void getTieDoc() throws Exception {
        // Initialize the database
        tieDocRepository.saveAndFlush(tieDoc);

        // Get the tieDoc
        restTieDocMockMvc.perform(get("/api/tie-docs/{id}", tieDoc.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(tieDoc.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.reportingEntityCode").value(DEFAULT_REPORTING_ENTITY_CODE.toString()))
            .andExpect(jsonPath("$.currencyCode").value(DEFAULT_CURRENCY_CODE.toString()))
            .andExpect(jsonPath("$.resCountryCode").value(DEFAULT_RES_COUNTRY_CODE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTieDoc() throws Exception {
        // Get the tieDoc
        restTieDocMockMvc.perform(get("/api/tie-docs/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTieDoc() throws Exception {
        // Initialize the database
        tieDocRepository.saveAndFlush(tieDoc);
        tieDocSearchRepository.save(tieDoc);
        int databaseSizeBeforeUpdate = tieDocRepository.findAll().size();

        // Update the tieDoc
        TieDoc updatedTieDoc = new TieDoc();
        updatedTieDoc.setId(tieDoc.getId());
        updatedTieDoc.setName(UPDATED_NAME);
        updatedTieDoc.setCode(UPDATED_CODE);
        updatedTieDoc.setDescription(UPDATED_DESCRIPTION);
        updatedTieDoc.setReportingEntityCode(UPDATED_REPORTING_ENTITY_CODE);
        updatedTieDoc.setCurrencyCode(UPDATED_CURRENCY_CODE);
        updatedTieDoc.setResCountryCode(UPDATED_RES_COUNTRY_CODE);

        restTieDocMockMvc.perform(put("/api/tie-docs")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedTieDoc)))
                .andExpect(status().isOk());

        // Validate the TieDoc in the database
        List<TieDoc> tieDocs = tieDocRepository.findAll();
        assertThat(tieDocs).hasSize(databaseSizeBeforeUpdate);
        TieDoc testTieDoc = tieDocs.get(tieDocs.size() - 1);
        assertThat(testTieDoc.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testTieDoc.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testTieDoc.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testTieDoc.getReportingEntityCode()).isEqualTo(UPDATED_REPORTING_ENTITY_CODE);
        assertThat(testTieDoc.getCurrencyCode()).isEqualTo(UPDATED_CURRENCY_CODE);
        assertThat(testTieDoc.getResCountryCode()).isEqualTo(UPDATED_RES_COUNTRY_CODE);

        // Validate the TieDoc in ElasticSearch
        TieDoc tieDocEs = tieDocSearchRepository.findOne(testTieDoc.getId());
        assertThat(tieDocEs).isEqualToComparingFieldByField(testTieDoc);
    }

    @Test
    @Transactional
    public void deleteTieDoc() throws Exception {
        // Initialize the database
        tieDocRepository.saveAndFlush(tieDoc);
        tieDocSearchRepository.save(tieDoc);
        int databaseSizeBeforeDelete = tieDocRepository.findAll().size();

        // Get the tieDoc
        restTieDocMockMvc.perform(delete("/api/tie-docs/{id}", tieDoc.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate ElasticSearch is empty
        boolean tieDocExistsInEs = tieDocSearchRepository.exists(tieDoc.getId());
        assertThat(tieDocExistsInEs).isFalse();

        // Validate the database is empty
        List<TieDoc> tieDocs = tieDocRepository.findAll();
        assertThat(tieDocs).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchTieDoc() throws Exception {
        // Initialize the database
        tieDocRepository.saveAndFlush(tieDoc);
        tieDocSearchRepository.save(tieDoc);

        // Search the tieDoc
        restTieDocMockMvc.perform(get("/api/_search/tie-docs?query=id:" + tieDoc.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tieDoc.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].reportingEntityCode").value(hasItem(DEFAULT_REPORTING_ENTITY_CODE.toString())))
            .andExpect(jsonPath("$.[*].currencyCode").value(hasItem(DEFAULT_CURRENCY_CODE.toString())))
            .andExpect(jsonPath("$.[*].resCountryCode").value(hasItem(DEFAULT_RES_COUNTRY_CODE.toString())));
    }
}
