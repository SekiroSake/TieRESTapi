package com.tie.app.web.rest;

import com.tie.app.TieEntityApp;
import com.tie.app.domain.CbcrEntity;
import com.tie.app.repository.CbcrEntityRepository;
import com.tie.app.repository.search.CbcrEntitySearchRepository;

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
 * Test class for the CbcrEntityResource REST controller.
 *
 * @see CbcrEntityResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TieEntityApp.class)
@WebAppConfiguration
@IntegrationTest
public class CbcrEntityResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB";
    private static final String DEFAULT_ENTITY_CODE = "AAAAA";
    private static final String UPDATED_ENTITY_CODE = "BBBBB";
    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB";
    private static final String DEFAULT_TAX_ID_NUM = "AAAAA";
    private static final String UPDATED_TAX_ID_NUM = "BBBBB";
    private static final String DEFAULT_INCORP_COUNTRY_CODE = "AAAAA";
    private static final String UPDATED_INCORP_COUNTRY_CODE = "BBBBB";
    private static final String DEFAULT_OTHER_ENTITY_INFO = "AAAAA";
    private static final String UPDATED_OTHER_ENTITY_INFO = "BBBBB";
    private static final String DEFAULT_TID_NUM = "AAAAA";
    private static final String UPDATED_TID_NUM = "BBBBB";

    private static final Integer DEFAULT_IS_PERMANENT_ESTABLISHMENT = 1;
    private static final Integer UPDATED_IS_PERMANENT_ESTABLISHMENT = 2;
    private static final String DEFAULT_ADDR_LEAGAL_TYPE = "AAAAA";
    private static final String UPDATED_ADDR_LEAGAL_TYPE = "BBBBB";
    private static final String DEFAULT_ADDR_FREE_TEXT = "AAAAA";
    private static final String UPDATED_ADDR_FREE_TEXT = "BBBBB";
    private static final String DEFAULT_ADDR_STREET = "AAAAA";
    private static final String UPDATED_ADDR_STREET = "BBBBB";
    private static final String DEFAULT_ADDR_BUILDING_IDENTIFIER = "AAAAA";
    private static final String UPDATED_ADDR_BUILDING_IDENTIFIER = "BBBBB";
    private static final String DEFAULT_ADD_SUITE_IDENTIFIER = "AAAAA";
    private static final String UPDATED_ADD_SUITE_IDENTIFIER = "BBBBB";
    private static final String DEFAULT_ADDR_FLOOR_IDENTIFIER = "AAAAA";
    private static final String UPDATED_ADDR_FLOOR_IDENTIFIER = "BBBBB";
    private static final String DEFAULT_ADDR_POB = "AAAAA";
    private static final String UPDATED_ADDR_POB = "BBBBB";
    private static final String DEFAULT_ADDR_POST_CODE = "AAAAA";
    private static final String UPDATED_ADDR_POST_CODE = "BBBBB";
    private static final String DEFAULT_ADDR_CITY = "AAAAA";
    private static final String UPDATED_ADDR_CITY = "BBBBB";
    private static final String DEFAULT_ADDR_COUNTRY_SUBENTITY = "AAAAA";
    private static final String UPDATED_ADDR_COUNTRY_SUBENTITY = "BBBBB";

    @Inject
    private CbcrEntityRepository cbcrEntityRepository;

    @Inject
    private CbcrEntitySearchRepository cbcrEntitySearchRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restCbcrEntityMockMvc;

    private CbcrEntity cbcrEntity;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CbcrEntityResource cbcrEntityResource = new CbcrEntityResource();
        ReflectionTestUtils.setField(cbcrEntityResource, "cbcrEntitySearchRepository", cbcrEntitySearchRepository);
        ReflectionTestUtils.setField(cbcrEntityResource, "cbcrEntityRepository", cbcrEntityRepository);
        this.restCbcrEntityMockMvc = MockMvcBuilders.standaloneSetup(cbcrEntityResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        cbcrEntitySearchRepository.deleteAll();
        cbcrEntity = new CbcrEntity();
        cbcrEntity.setName(DEFAULT_NAME);
        cbcrEntity.setEntityCode(DEFAULT_ENTITY_CODE);
        cbcrEntity.setDescription(DEFAULT_DESCRIPTION);
        cbcrEntity.setTaxIdNum(DEFAULT_TAX_ID_NUM);
        cbcrEntity.setIncorpCountryCode(DEFAULT_INCORP_COUNTRY_CODE);
        cbcrEntity.setOtherEntityInfo(DEFAULT_OTHER_ENTITY_INFO);
        cbcrEntity.setTidNum(DEFAULT_TID_NUM);
        cbcrEntity.setIsPermanentEstablishment(DEFAULT_IS_PERMANENT_ESTABLISHMENT);
        cbcrEntity.setAddrLeagalType(DEFAULT_ADDR_LEAGAL_TYPE);
        cbcrEntity.setAddrFreeText(DEFAULT_ADDR_FREE_TEXT);
        cbcrEntity.setAddrStreet(DEFAULT_ADDR_STREET);
        cbcrEntity.setAddrBuildingIdentifier(DEFAULT_ADDR_BUILDING_IDENTIFIER);
        cbcrEntity.setAddSuiteIdentifier(DEFAULT_ADD_SUITE_IDENTIFIER);
        cbcrEntity.setAddrFloorIdentifier(DEFAULT_ADDR_FLOOR_IDENTIFIER);
        cbcrEntity.setAddrPOB(DEFAULT_ADDR_POB);
        cbcrEntity.setAddrPostCode(DEFAULT_ADDR_POST_CODE);
        cbcrEntity.setAddrCity(DEFAULT_ADDR_CITY);
        cbcrEntity.setAddrCountrySubentity(DEFAULT_ADDR_COUNTRY_SUBENTITY);
    }

    @Test
    @Transactional
    public void createCbcrEntity() throws Exception {
        int databaseSizeBeforeCreate = cbcrEntityRepository.findAll().size();

        // Create the CbcrEntity

        restCbcrEntityMockMvc.perform(post("/api/cbcr-entities")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(cbcrEntity)))
                .andExpect(status().isCreated());

        // Validate the CbcrEntity in the database
        List<CbcrEntity> cbcrEntities = cbcrEntityRepository.findAll();
        assertThat(cbcrEntities).hasSize(databaseSizeBeforeCreate + 1);
        CbcrEntity testCbcrEntity = cbcrEntities.get(cbcrEntities.size() - 1);
        assertThat(testCbcrEntity.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCbcrEntity.getEntityCode()).isEqualTo(DEFAULT_ENTITY_CODE);
        assertThat(testCbcrEntity.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testCbcrEntity.getTaxIdNum()).isEqualTo(DEFAULT_TAX_ID_NUM);
        assertThat(testCbcrEntity.getIncorpCountryCode()).isEqualTo(DEFAULT_INCORP_COUNTRY_CODE);
        assertThat(testCbcrEntity.getOtherEntityInfo()).isEqualTo(DEFAULT_OTHER_ENTITY_INFO);
        assertThat(testCbcrEntity.getTidNum()).isEqualTo(DEFAULT_TID_NUM);
        assertThat(testCbcrEntity.getIsPermanentEstablishment()).isEqualTo(DEFAULT_IS_PERMANENT_ESTABLISHMENT);
        assertThat(testCbcrEntity.getAddrLeagalType()).isEqualTo(DEFAULT_ADDR_LEAGAL_TYPE);
        assertThat(testCbcrEntity.getAddrFreeText()).isEqualTo(DEFAULT_ADDR_FREE_TEXT);
        assertThat(testCbcrEntity.getAddrStreet()).isEqualTo(DEFAULT_ADDR_STREET);
        assertThat(testCbcrEntity.getAddrBuildingIdentifier()).isEqualTo(DEFAULT_ADDR_BUILDING_IDENTIFIER);
        assertThat(testCbcrEntity.getAddSuiteIdentifier()).isEqualTo(DEFAULT_ADD_SUITE_IDENTIFIER);
        assertThat(testCbcrEntity.getAddrFloorIdentifier()).isEqualTo(DEFAULT_ADDR_FLOOR_IDENTIFIER);
        assertThat(testCbcrEntity.getAddrPOB()).isEqualTo(DEFAULT_ADDR_POB);
        assertThat(testCbcrEntity.getAddrPostCode()).isEqualTo(DEFAULT_ADDR_POST_CODE);
        assertThat(testCbcrEntity.getAddrCity()).isEqualTo(DEFAULT_ADDR_CITY);
        assertThat(testCbcrEntity.getAddrCountrySubentity()).isEqualTo(DEFAULT_ADDR_COUNTRY_SUBENTITY);

        // Validate the CbcrEntity in ElasticSearch
        CbcrEntity cbcrEntityEs = cbcrEntitySearchRepository.findOne(testCbcrEntity.getId());
        assertThat(cbcrEntityEs).isEqualToComparingFieldByField(testCbcrEntity);
    }

    @Test
    @Transactional
    public void getAllCbcrEntities() throws Exception {
        // Initialize the database
        cbcrEntityRepository.saveAndFlush(cbcrEntity);

        // Get all the cbcrEntities
        restCbcrEntityMockMvc.perform(get("/api/cbcr-entities?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(cbcrEntity.getId().intValue())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
                .andExpect(jsonPath("$.[*].entityCode").value(hasItem(DEFAULT_ENTITY_CODE.toString())))
                .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
                .andExpect(jsonPath("$.[*].taxIdNum").value(hasItem(DEFAULT_TAX_ID_NUM.toString())))
                .andExpect(jsonPath("$.[*].incorpCountryCode").value(hasItem(DEFAULT_INCORP_COUNTRY_CODE.toString())))
                .andExpect(jsonPath("$.[*].otherEntityInfo").value(hasItem(DEFAULT_OTHER_ENTITY_INFO.toString())))
                .andExpect(jsonPath("$.[*].tidNum").value(hasItem(DEFAULT_TID_NUM.toString())))
                .andExpect(jsonPath("$.[*].isPermanentEstablishment").value(hasItem(DEFAULT_IS_PERMANENT_ESTABLISHMENT)))
                .andExpect(jsonPath("$.[*].addrLeagalType").value(hasItem(DEFAULT_ADDR_LEAGAL_TYPE.toString())))
                .andExpect(jsonPath("$.[*].addrFreeText").value(hasItem(DEFAULT_ADDR_FREE_TEXT.toString())))
                .andExpect(jsonPath("$.[*].addrStreet").value(hasItem(DEFAULT_ADDR_STREET.toString())))
                .andExpect(jsonPath("$.[*].addrBuildingIdentifier").value(hasItem(DEFAULT_ADDR_BUILDING_IDENTIFIER.toString())))
                .andExpect(jsonPath("$.[*].addSuiteIdentifier").value(hasItem(DEFAULT_ADD_SUITE_IDENTIFIER.toString())))
                .andExpect(jsonPath("$.[*].addrFloorIdentifier").value(hasItem(DEFAULT_ADDR_FLOOR_IDENTIFIER.toString())))
                .andExpect(jsonPath("$.[*].addrPOB").value(hasItem(DEFAULT_ADDR_POB.toString())))
                .andExpect(jsonPath("$.[*].addrPostCode").value(hasItem(DEFAULT_ADDR_POST_CODE.toString())))
                .andExpect(jsonPath("$.[*].addrCity").value(hasItem(DEFAULT_ADDR_CITY.toString())))
                .andExpect(jsonPath("$.[*].addrCountrySubentity").value(hasItem(DEFAULT_ADDR_COUNTRY_SUBENTITY.toString())));
    }

    @Test
    @Transactional
    public void getCbcrEntity() throws Exception {
        // Initialize the database
        cbcrEntityRepository.saveAndFlush(cbcrEntity);

        // Get the cbcrEntity
        restCbcrEntityMockMvc.perform(get("/api/cbcr-entities/{id}", cbcrEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(cbcrEntity.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.entityCode").value(DEFAULT_ENTITY_CODE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.taxIdNum").value(DEFAULT_TAX_ID_NUM.toString()))
            .andExpect(jsonPath("$.incorpCountryCode").value(DEFAULT_INCORP_COUNTRY_CODE.toString()))
            .andExpect(jsonPath("$.otherEntityInfo").value(DEFAULT_OTHER_ENTITY_INFO.toString()))
            .andExpect(jsonPath("$.tidNum").value(DEFAULT_TID_NUM.toString()))
            .andExpect(jsonPath("$.isPermanentEstablishment").value(DEFAULT_IS_PERMANENT_ESTABLISHMENT))
            .andExpect(jsonPath("$.addrLeagalType").value(DEFAULT_ADDR_LEAGAL_TYPE.toString()))
            .andExpect(jsonPath("$.addrFreeText").value(DEFAULT_ADDR_FREE_TEXT.toString()))
            .andExpect(jsonPath("$.addrStreet").value(DEFAULT_ADDR_STREET.toString()))
            .andExpect(jsonPath("$.addrBuildingIdentifier").value(DEFAULT_ADDR_BUILDING_IDENTIFIER.toString()))
            .andExpect(jsonPath("$.addSuiteIdentifier").value(DEFAULT_ADD_SUITE_IDENTIFIER.toString()))
            .andExpect(jsonPath("$.addrFloorIdentifier").value(DEFAULT_ADDR_FLOOR_IDENTIFIER.toString()))
            .andExpect(jsonPath("$.addrPOB").value(DEFAULT_ADDR_POB.toString()))
            .andExpect(jsonPath("$.addrPostCode").value(DEFAULT_ADDR_POST_CODE.toString()))
            .andExpect(jsonPath("$.addrCity").value(DEFAULT_ADDR_CITY.toString()))
            .andExpect(jsonPath("$.addrCountrySubentity").value(DEFAULT_ADDR_COUNTRY_SUBENTITY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCbcrEntity() throws Exception {
        // Get the cbcrEntity
        restCbcrEntityMockMvc.perform(get("/api/cbcr-entities/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCbcrEntity() throws Exception {
        // Initialize the database
        cbcrEntityRepository.saveAndFlush(cbcrEntity);
        cbcrEntitySearchRepository.save(cbcrEntity);
        int databaseSizeBeforeUpdate = cbcrEntityRepository.findAll().size();

        // Update the cbcrEntity
        CbcrEntity updatedCbcrEntity = new CbcrEntity();
        updatedCbcrEntity.setId(cbcrEntity.getId());
        updatedCbcrEntity.setName(UPDATED_NAME);
        updatedCbcrEntity.setEntityCode(UPDATED_ENTITY_CODE);
        updatedCbcrEntity.setDescription(UPDATED_DESCRIPTION);
        updatedCbcrEntity.setTaxIdNum(UPDATED_TAX_ID_NUM);
        updatedCbcrEntity.setIncorpCountryCode(UPDATED_INCORP_COUNTRY_CODE);
        updatedCbcrEntity.setOtherEntityInfo(UPDATED_OTHER_ENTITY_INFO);
        updatedCbcrEntity.setTidNum(UPDATED_TID_NUM);
        updatedCbcrEntity.setIsPermanentEstablishment(UPDATED_IS_PERMANENT_ESTABLISHMENT);
        updatedCbcrEntity.setAddrLeagalType(UPDATED_ADDR_LEAGAL_TYPE);
        updatedCbcrEntity.setAddrFreeText(UPDATED_ADDR_FREE_TEXT);
        updatedCbcrEntity.setAddrStreet(UPDATED_ADDR_STREET);
        updatedCbcrEntity.setAddrBuildingIdentifier(UPDATED_ADDR_BUILDING_IDENTIFIER);
        updatedCbcrEntity.setAddSuiteIdentifier(UPDATED_ADD_SUITE_IDENTIFIER);
        updatedCbcrEntity.setAddrFloorIdentifier(UPDATED_ADDR_FLOOR_IDENTIFIER);
        updatedCbcrEntity.setAddrPOB(UPDATED_ADDR_POB);
        updatedCbcrEntity.setAddrPostCode(UPDATED_ADDR_POST_CODE);
        updatedCbcrEntity.setAddrCity(UPDATED_ADDR_CITY);
        updatedCbcrEntity.setAddrCountrySubentity(UPDATED_ADDR_COUNTRY_SUBENTITY);

        restCbcrEntityMockMvc.perform(put("/api/cbcr-entities")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedCbcrEntity)))
                .andExpect(status().isOk());

        // Validate the CbcrEntity in the database
        List<CbcrEntity> cbcrEntities = cbcrEntityRepository.findAll();
        assertThat(cbcrEntities).hasSize(databaseSizeBeforeUpdate);
        CbcrEntity testCbcrEntity = cbcrEntities.get(cbcrEntities.size() - 1);
        assertThat(testCbcrEntity.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCbcrEntity.getEntityCode()).isEqualTo(UPDATED_ENTITY_CODE);
        assertThat(testCbcrEntity.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testCbcrEntity.getTaxIdNum()).isEqualTo(UPDATED_TAX_ID_NUM);
        assertThat(testCbcrEntity.getIncorpCountryCode()).isEqualTo(UPDATED_INCORP_COUNTRY_CODE);
        assertThat(testCbcrEntity.getOtherEntityInfo()).isEqualTo(UPDATED_OTHER_ENTITY_INFO);
        assertThat(testCbcrEntity.getTidNum()).isEqualTo(UPDATED_TID_NUM);
        assertThat(testCbcrEntity.getIsPermanentEstablishment()).isEqualTo(UPDATED_IS_PERMANENT_ESTABLISHMENT);
        assertThat(testCbcrEntity.getAddrLeagalType()).isEqualTo(UPDATED_ADDR_LEAGAL_TYPE);
        assertThat(testCbcrEntity.getAddrFreeText()).isEqualTo(UPDATED_ADDR_FREE_TEXT);
        assertThat(testCbcrEntity.getAddrStreet()).isEqualTo(UPDATED_ADDR_STREET);
        assertThat(testCbcrEntity.getAddrBuildingIdentifier()).isEqualTo(UPDATED_ADDR_BUILDING_IDENTIFIER);
        assertThat(testCbcrEntity.getAddSuiteIdentifier()).isEqualTo(UPDATED_ADD_SUITE_IDENTIFIER);
        assertThat(testCbcrEntity.getAddrFloorIdentifier()).isEqualTo(UPDATED_ADDR_FLOOR_IDENTIFIER);
        assertThat(testCbcrEntity.getAddrPOB()).isEqualTo(UPDATED_ADDR_POB);
        assertThat(testCbcrEntity.getAddrPostCode()).isEqualTo(UPDATED_ADDR_POST_CODE);
        assertThat(testCbcrEntity.getAddrCity()).isEqualTo(UPDATED_ADDR_CITY);
        assertThat(testCbcrEntity.getAddrCountrySubentity()).isEqualTo(UPDATED_ADDR_COUNTRY_SUBENTITY);

        // Validate the CbcrEntity in ElasticSearch
        CbcrEntity cbcrEntityEs = cbcrEntitySearchRepository.findOne(testCbcrEntity.getId());
        assertThat(cbcrEntityEs).isEqualToComparingFieldByField(testCbcrEntity);
    }

    @Test
    @Transactional
    public void deleteCbcrEntity() throws Exception {
        // Initialize the database
        cbcrEntityRepository.saveAndFlush(cbcrEntity);
        cbcrEntitySearchRepository.save(cbcrEntity);
        int databaseSizeBeforeDelete = cbcrEntityRepository.findAll().size();

        // Get the cbcrEntity
        restCbcrEntityMockMvc.perform(delete("/api/cbcr-entities/{id}", cbcrEntity.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate ElasticSearch is empty
        boolean cbcrEntityExistsInEs = cbcrEntitySearchRepository.exists(cbcrEntity.getId());
        assertThat(cbcrEntityExistsInEs).isFalse();

        // Validate the database is empty
        List<CbcrEntity> cbcrEntities = cbcrEntityRepository.findAll();
        assertThat(cbcrEntities).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchCbcrEntity() throws Exception {
        // Initialize the database
        cbcrEntityRepository.saveAndFlush(cbcrEntity);
        cbcrEntitySearchRepository.save(cbcrEntity);

        // Search the cbcrEntity
        restCbcrEntityMockMvc.perform(get("/api/_search/cbcr-entities?query=id:" + cbcrEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cbcrEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].entityCode").value(hasItem(DEFAULT_ENTITY_CODE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].taxIdNum").value(hasItem(DEFAULT_TAX_ID_NUM.toString())))
            .andExpect(jsonPath("$.[*].incorpCountryCode").value(hasItem(DEFAULT_INCORP_COUNTRY_CODE.toString())))
            .andExpect(jsonPath("$.[*].otherEntityInfo").value(hasItem(DEFAULT_OTHER_ENTITY_INFO.toString())))
            .andExpect(jsonPath("$.[*].tidNum").value(hasItem(DEFAULT_TID_NUM.toString())))
            .andExpect(jsonPath("$.[*].isPermanentEstablishment").value(hasItem(DEFAULT_IS_PERMANENT_ESTABLISHMENT)))
            .andExpect(jsonPath("$.[*].addrLeagalType").value(hasItem(DEFAULT_ADDR_LEAGAL_TYPE.toString())))
            .andExpect(jsonPath("$.[*].addrFreeText").value(hasItem(DEFAULT_ADDR_FREE_TEXT.toString())))
            .andExpect(jsonPath("$.[*].addrStreet").value(hasItem(DEFAULT_ADDR_STREET.toString())))
            .andExpect(jsonPath("$.[*].addrBuildingIdentifier").value(hasItem(DEFAULT_ADDR_BUILDING_IDENTIFIER.toString())))
            .andExpect(jsonPath("$.[*].addSuiteIdentifier").value(hasItem(DEFAULT_ADD_SUITE_IDENTIFIER.toString())))
            .andExpect(jsonPath("$.[*].addrFloorIdentifier").value(hasItem(DEFAULT_ADDR_FLOOR_IDENTIFIER.toString())))
            .andExpect(jsonPath("$.[*].addrPOB").value(hasItem(DEFAULT_ADDR_POB.toString())))
            .andExpect(jsonPath("$.[*].addrPostCode").value(hasItem(DEFAULT_ADDR_POST_CODE.toString())))
            .andExpect(jsonPath("$.[*].addrCity").value(hasItem(DEFAULT_ADDR_CITY.toString())))
            .andExpect(jsonPath("$.[*].addrCountrySubentity").value(hasItem(DEFAULT_ADDR_COUNTRY_SUBENTITY.toString())));
    }
}
