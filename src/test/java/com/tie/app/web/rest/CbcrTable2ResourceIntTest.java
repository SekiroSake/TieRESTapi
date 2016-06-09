package com.tie.app.web.rest;

import com.tie.app.TieEntityApp;
import com.tie.app.domain.CbcrTable2;
import com.tie.app.repository.CbcrTable2Repository;
import com.tie.app.repository.search.CbcrTable2SearchRepository;

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
 * Test class for the CbcrTable2Resource REST controller.
 *
 * @see CbcrTable2Resource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TieEntityApp.class)
@WebAppConfiguration
@IntegrationTest
public class CbcrTable2ResourceIntTest {

    private static final String DEFAULT_ENTITY_CODE = "AAAAA";
    private static final String UPDATED_ENTITY_CODE = "BBBBB";
    private static final String DEFAULT_TAX_JURISDICTION = "AAAAA";
    private static final String UPDATED_TAX_JURISDICTION = "BBBBB";
    private static final String DEFAULT_TAX_JURIS_OF_INCORPORATION = "AAAAA";
    private static final String UPDATED_TAX_JURIS_OF_INCORPORATION = "BBBBB";

    private static final Integer DEFAULT_MAIN_BUS_R_AND_D = 1;
    private static final Integer UPDATED_MAIN_BUS_R_AND_D = 2;

    private static final Integer DEFAULT_MAIN_BUS_HOLDING_IP = 1;
    private static final Integer UPDATED_MAIN_BUS_HOLDING_IP = 2;

    private static final Integer DEFAULT_MAIN_BUS_PURCHASING = 1;
    private static final Integer UPDATED_MAIN_BUS_PURCHASING = 2;

    private static final Integer DEFAULT_MAIN_BUS_SALES_MKT_DISTR = 1;
    private static final Integer UPDATED_MAIN_BUS_SALES_MKT_DISTR = 2;

    private static final Integer DEFAULT_MAIN_BUS_ADMIN_MGMT_SUPPORT_SVC = 1;
    private static final Integer UPDATED_MAIN_BUS_ADMIN_MGMT_SUPPORT_SVC = 2;

    private static final Integer DEFAULT_MAIN_BUS_PROV_SVC_TO_UNRELATED_PARTIES = 1;
    private static final Integer UPDATED_MAIN_BUS_PROV_SVC_TO_UNRELATED_PARTIES = 2;

    private static final Integer DEFAULT_MAIN_BUS_INTERNAL_GROUP_FINANCE = 1;
    private static final Integer UPDATED_MAIN_BUS_INTERNAL_GROUP_FINANCE = 2;

    private static final Integer DEFAULT_MAIN_BUS_REGULATED_FIN_SVC = 1;
    private static final Integer UPDATED_MAIN_BUS_REGULATED_FIN_SVC = 2;

    private static final Integer DEFAULT_MAIN_BUS_INSURANCE = 1;
    private static final Integer UPDATED_MAIN_BUS_INSURANCE = 2;

    private static final Integer DEFAULT_MAIN_BUS_HOLDING_EQUITY_INSTRUMENTS = 1;
    private static final Integer UPDATED_MAIN_BUS_HOLDING_EQUITY_INSTRUMENTS = 2;

    private static final Integer DEFAULT_MAIN_BUS_DORMANT = 1;
    private static final Integer UPDATED_MAIN_BUS_DORMANT = 2;

    private static final Integer DEFAULT_MAIN_BUS_OTHER = 1;
    private static final Integer UPDATED_MAIN_BUS_OTHER = 2;
    private static final String DEFAULT_MAIN_BUS_OTHER_NOTES = "AAAAA";
    private static final String UPDATED_MAIN_BUS_OTHER_NOTES = "BBBBB";

    @Inject
    private CbcrTable2Repository cbcrTable2Repository;

    @Inject
    private CbcrTable2SearchRepository cbcrTable2SearchRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restCbcrTable2MockMvc;

    private CbcrTable2 cbcrTable2;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CbcrTable2Resource cbcrTable2Resource = new CbcrTable2Resource();
        ReflectionTestUtils.setField(cbcrTable2Resource, "cbcrTable2SearchRepository", cbcrTable2SearchRepository);
        ReflectionTestUtils.setField(cbcrTable2Resource, "cbcrTable2Repository", cbcrTable2Repository);
        this.restCbcrTable2MockMvc = MockMvcBuilders.standaloneSetup(cbcrTable2Resource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        cbcrTable2SearchRepository.deleteAll();
        cbcrTable2 = new CbcrTable2();
        cbcrTable2.setEntityCode(DEFAULT_ENTITY_CODE);
        cbcrTable2.setTaxJurisdiction(DEFAULT_TAX_JURISDICTION);
        cbcrTable2.setTaxJurisOfIncorporation(DEFAULT_TAX_JURIS_OF_INCORPORATION);
        cbcrTable2.setMainBusRAndD(DEFAULT_MAIN_BUS_R_AND_D);
        cbcrTable2.setMainBusHoldingIp(DEFAULT_MAIN_BUS_HOLDING_IP);
        cbcrTable2.setMainBusPurchasing(DEFAULT_MAIN_BUS_PURCHASING);
        cbcrTable2.setMainBusSalesMktDistr(DEFAULT_MAIN_BUS_SALES_MKT_DISTR);
        cbcrTable2.setMainBusAdminMgmtSupportSvc(DEFAULT_MAIN_BUS_ADMIN_MGMT_SUPPORT_SVC);
        cbcrTable2.setMainBusProvSvcToUnrelatedParties(DEFAULT_MAIN_BUS_PROV_SVC_TO_UNRELATED_PARTIES);
        cbcrTable2.setMainBusInternalGroupFinance(DEFAULT_MAIN_BUS_INTERNAL_GROUP_FINANCE);
        cbcrTable2.setMainBusRegulatedFinSvc(DEFAULT_MAIN_BUS_REGULATED_FIN_SVC);
        cbcrTable2.setMainBusInsurance(DEFAULT_MAIN_BUS_INSURANCE);
        cbcrTable2.setMainBusHoldingEquityInstruments(DEFAULT_MAIN_BUS_HOLDING_EQUITY_INSTRUMENTS);
        cbcrTable2.setMainBusDormant(DEFAULT_MAIN_BUS_DORMANT);
        cbcrTable2.setMainBusOther(DEFAULT_MAIN_BUS_OTHER);
        cbcrTable2.setMainBusOtherNotes(DEFAULT_MAIN_BUS_OTHER_NOTES);
    }

    @Test
    @Transactional
    public void createCbcrTable2() throws Exception {
        int databaseSizeBeforeCreate = cbcrTable2Repository.findAll().size();

        // Create the CbcrTable2

        restCbcrTable2MockMvc.perform(post("/api/cbcr-table-2-s")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(cbcrTable2)))
                .andExpect(status().isCreated());

        // Validate the CbcrTable2 in the database
        List<CbcrTable2> cbcrTable2S = cbcrTable2Repository.findAll();
        assertThat(cbcrTable2S).hasSize(databaseSizeBeforeCreate + 1);
        CbcrTable2 testCbcrTable2 = cbcrTable2S.get(cbcrTable2S.size() - 1);
        assertThat(testCbcrTable2.getEntityCode()).isEqualTo(DEFAULT_ENTITY_CODE);
        assertThat(testCbcrTable2.getTaxJurisdiction()).isEqualTo(DEFAULT_TAX_JURISDICTION);
        assertThat(testCbcrTable2.getTaxJurisOfIncorporation()).isEqualTo(DEFAULT_TAX_JURIS_OF_INCORPORATION);
        assertThat(testCbcrTable2.getMainBusRAndD()).isEqualTo(DEFAULT_MAIN_BUS_R_AND_D);
        assertThat(testCbcrTable2.getMainBusHoldingIp()).isEqualTo(DEFAULT_MAIN_BUS_HOLDING_IP);
        assertThat(testCbcrTable2.getMainBusPurchasing()).isEqualTo(DEFAULT_MAIN_BUS_PURCHASING);
        assertThat(testCbcrTable2.getMainBusSalesMktDistr()).isEqualTo(DEFAULT_MAIN_BUS_SALES_MKT_DISTR);
        assertThat(testCbcrTable2.getMainBusAdminMgmtSupportSvc()).isEqualTo(DEFAULT_MAIN_BUS_ADMIN_MGMT_SUPPORT_SVC);
        assertThat(testCbcrTable2.getMainBusProvSvcToUnrelatedParties()).isEqualTo(DEFAULT_MAIN_BUS_PROV_SVC_TO_UNRELATED_PARTIES);
        assertThat(testCbcrTable2.getMainBusInternalGroupFinance()).isEqualTo(DEFAULT_MAIN_BUS_INTERNAL_GROUP_FINANCE);
        assertThat(testCbcrTable2.getMainBusRegulatedFinSvc()).isEqualTo(DEFAULT_MAIN_BUS_REGULATED_FIN_SVC);
        assertThat(testCbcrTable2.getMainBusInsurance()).isEqualTo(DEFAULT_MAIN_BUS_INSURANCE);
        assertThat(testCbcrTable2.getMainBusHoldingEquityInstruments()).isEqualTo(DEFAULT_MAIN_BUS_HOLDING_EQUITY_INSTRUMENTS);
        assertThat(testCbcrTable2.getMainBusDormant()).isEqualTo(DEFAULT_MAIN_BUS_DORMANT);
        assertThat(testCbcrTable2.getMainBusOther()).isEqualTo(DEFAULT_MAIN_BUS_OTHER);
        assertThat(testCbcrTable2.getMainBusOtherNotes()).isEqualTo(DEFAULT_MAIN_BUS_OTHER_NOTES);

        // Validate the CbcrTable2 in ElasticSearch
        CbcrTable2 cbcrTable2Es = cbcrTable2SearchRepository.findOne(testCbcrTable2.getId());
        assertThat(cbcrTable2Es).isEqualToComparingFieldByField(testCbcrTable2);
    }

    @Test
    @Transactional
    public void getAllCbcrTable2S() throws Exception {
        // Initialize the database
        cbcrTable2Repository.saveAndFlush(cbcrTable2);

        // Get all the cbcrTable2S
        restCbcrTable2MockMvc.perform(get("/api/cbcr-table-2-s?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(cbcrTable2.getId().intValue())))
                .andExpect(jsonPath("$.[*].entityCode").value(hasItem(DEFAULT_ENTITY_CODE.toString())))
                .andExpect(jsonPath("$.[*].taxJurisdiction").value(hasItem(DEFAULT_TAX_JURISDICTION.toString())))
                .andExpect(jsonPath("$.[*].taxJurisOfIncorporation").value(hasItem(DEFAULT_TAX_JURIS_OF_INCORPORATION.toString())))
                .andExpect(jsonPath("$.[*].mainBusRAndD").value(hasItem(DEFAULT_MAIN_BUS_R_AND_D)))
                .andExpect(jsonPath("$.[*].mainBusHoldingIp").value(hasItem(DEFAULT_MAIN_BUS_HOLDING_IP)))
                .andExpect(jsonPath("$.[*].mainBusPurchasing").value(hasItem(DEFAULT_MAIN_BUS_PURCHASING)))
                .andExpect(jsonPath("$.[*].mainBusSalesMktDistr").value(hasItem(DEFAULT_MAIN_BUS_SALES_MKT_DISTR)))
                .andExpect(jsonPath("$.[*].mainBusAdminMgmtSupportSvc").value(hasItem(DEFAULT_MAIN_BUS_ADMIN_MGMT_SUPPORT_SVC)))
                .andExpect(jsonPath("$.[*].mainBusProvSvcToUnrelatedParties").value(hasItem(DEFAULT_MAIN_BUS_PROV_SVC_TO_UNRELATED_PARTIES)))
                .andExpect(jsonPath("$.[*].mainBusInternalGroupFinance").value(hasItem(DEFAULT_MAIN_BUS_INTERNAL_GROUP_FINANCE)))
                .andExpect(jsonPath("$.[*].mainBusRegulatedFinSvc").value(hasItem(DEFAULT_MAIN_BUS_REGULATED_FIN_SVC)))
                .andExpect(jsonPath("$.[*].mainBusInsurance").value(hasItem(DEFAULT_MAIN_BUS_INSURANCE)))
                .andExpect(jsonPath("$.[*].mainBusHoldingEquityInstruments").value(hasItem(DEFAULT_MAIN_BUS_HOLDING_EQUITY_INSTRUMENTS)))
                .andExpect(jsonPath("$.[*].mainBusDormant").value(hasItem(DEFAULT_MAIN_BUS_DORMANT)))
                .andExpect(jsonPath("$.[*].mainBusOther").value(hasItem(DEFAULT_MAIN_BUS_OTHER)))
                .andExpect(jsonPath("$.[*].mainBusOtherNotes").value(hasItem(DEFAULT_MAIN_BUS_OTHER_NOTES.toString())));
    }

    @Test
    @Transactional
    public void getCbcrTable2() throws Exception {
        // Initialize the database
        cbcrTable2Repository.saveAndFlush(cbcrTable2);

        // Get the cbcrTable2
        restCbcrTable2MockMvc.perform(get("/api/cbcr-table-2-s/{id}", cbcrTable2.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(cbcrTable2.getId().intValue()))
            .andExpect(jsonPath("$.entityCode").value(DEFAULT_ENTITY_CODE.toString()))
            .andExpect(jsonPath("$.taxJurisdiction").value(DEFAULT_TAX_JURISDICTION.toString()))
            .andExpect(jsonPath("$.taxJurisOfIncorporation").value(DEFAULT_TAX_JURIS_OF_INCORPORATION.toString()))
            .andExpect(jsonPath("$.mainBusRAndD").value(DEFAULT_MAIN_BUS_R_AND_D))
            .andExpect(jsonPath("$.mainBusHoldingIp").value(DEFAULT_MAIN_BUS_HOLDING_IP))
            .andExpect(jsonPath("$.mainBusPurchasing").value(DEFAULT_MAIN_BUS_PURCHASING))
            .andExpect(jsonPath("$.mainBusSalesMktDistr").value(DEFAULT_MAIN_BUS_SALES_MKT_DISTR))
            .andExpect(jsonPath("$.mainBusAdminMgmtSupportSvc").value(DEFAULT_MAIN_BUS_ADMIN_MGMT_SUPPORT_SVC))
            .andExpect(jsonPath("$.mainBusProvSvcToUnrelatedParties").value(DEFAULT_MAIN_BUS_PROV_SVC_TO_UNRELATED_PARTIES))
            .andExpect(jsonPath("$.mainBusInternalGroupFinance").value(DEFAULT_MAIN_BUS_INTERNAL_GROUP_FINANCE))
            .andExpect(jsonPath("$.mainBusRegulatedFinSvc").value(DEFAULT_MAIN_BUS_REGULATED_FIN_SVC))
            .andExpect(jsonPath("$.mainBusInsurance").value(DEFAULT_MAIN_BUS_INSURANCE))
            .andExpect(jsonPath("$.mainBusHoldingEquityInstruments").value(DEFAULT_MAIN_BUS_HOLDING_EQUITY_INSTRUMENTS))
            .andExpect(jsonPath("$.mainBusDormant").value(DEFAULT_MAIN_BUS_DORMANT))
            .andExpect(jsonPath("$.mainBusOther").value(DEFAULT_MAIN_BUS_OTHER))
            .andExpect(jsonPath("$.mainBusOtherNotes").value(DEFAULT_MAIN_BUS_OTHER_NOTES.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCbcrTable2() throws Exception {
        // Get the cbcrTable2
        restCbcrTable2MockMvc.perform(get("/api/cbcr-table-2-s/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCbcrTable2() throws Exception {
        // Initialize the database
        cbcrTable2Repository.saveAndFlush(cbcrTable2);
        cbcrTable2SearchRepository.save(cbcrTable2);
        int databaseSizeBeforeUpdate = cbcrTable2Repository.findAll().size();

        // Update the cbcrTable2
        CbcrTable2 updatedCbcrTable2 = new CbcrTable2();
        updatedCbcrTable2.setId(cbcrTable2.getId());
        updatedCbcrTable2.setEntityCode(UPDATED_ENTITY_CODE);
        updatedCbcrTable2.setTaxJurisdiction(UPDATED_TAX_JURISDICTION);
        updatedCbcrTable2.setTaxJurisOfIncorporation(UPDATED_TAX_JURIS_OF_INCORPORATION);
        updatedCbcrTable2.setMainBusRAndD(UPDATED_MAIN_BUS_R_AND_D);
        updatedCbcrTable2.setMainBusHoldingIp(UPDATED_MAIN_BUS_HOLDING_IP);
        updatedCbcrTable2.setMainBusPurchasing(UPDATED_MAIN_BUS_PURCHASING);
        updatedCbcrTable2.setMainBusSalesMktDistr(UPDATED_MAIN_BUS_SALES_MKT_DISTR);
        updatedCbcrTable2.setMainBusAdminMgmtSupportSvc(UPDATED_MAIN_BUS_ADMIN_MGMT_SUPPORT_SVC);
        updatedCbcrTable2.setMainBusProvSvcToUnrelatedParties(UPDATED_MAIN_BUS_PROV_SVC_TO_UNRELATED_PARTIES);
        updatedCbcrTable2.setMainBusInternalGroupFinance(UPDATED_MAIN_BUS_INTERNAL_GROUP_FINANCE);
        updatedCbcrTable2.setMainBusRegulatedFinSvc(UPDATED_MAIN_BUS_REGULATED_FIN_SVC);
        updatedCbcrTable2.setMainBusInsurance(UPDATED_MAIN_BUS_INSURANCE);
        updatedCbcrTable2.setMainBusHoldingEquityInstruments(UPDATED_MAIN_BUS_HOLDING_EQUITY_INSTRUMENTS);
        updatedCbcrTable2.setMainBusDormant(UPDATED_MAIN_BUS_DORMANT);
        updatedCbcrTable2.setMainBusOther(UPDATED_MAIN_BUS_OTHER);
        updatedCbcrTable2.setMainBusOtherNotes(UPDATED_MAIN_BUS_OTHER_NOTES);

        restCbcrTable2MockMvc.perform(put("/api/cbcr-table-2-s")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedCbcrTable2)))
                .andExpect(status().isOk());

        // Validate the CbcrTable2 in the database
        List<CbcrTable2> cbcrTable2S = cbcrTable2Repository.findAll();
        assertThat(cbcrTable2S).hasSize(databaseSizeBeforeUpdate);
        CbcrTable2 testCbcrTable2 = cbcrTable2S.get(cbcrTable2S.size() - 1);
        assertThat(testCbcrTable2.getEntityCode()).isEqualTo(UPDATED_ENTITY_CODE);
        assertThat(testCbcrTable2.getTaxJurisdiction()).isEqualTo(UPDATED_TAX_JURISDICTION);
        assertThat(testCbcrTable2.getTaxJurisOfIncorporation()).isEqualTo(UPDATED_TAX_JURIS_OF_INCORPORATION);
        assertThat(testCbcrTable2.getMainBusRAndD()).isEqualTo(UPDATED_MAIN_BUS_R_AND_D);
        assertThat(testCbcrTable2.getMainBusHoldingIp()).isEqualTo(UPDATED_MAIN_BUS_HOLDING_IP);
        assertThat(testCbcrTable2.getMainBusPurchasing()).isEqualTo(UPDATED_MAIN_BUS_PURCHASING);
        assertThat(testCbcrTable2.getMainBusSalesMktDistr()).isEqualTo(UPDATED_MAIN_BUS_SALES_MKT_DISTR);
        assertThat(testCbcrTable2.getMainBusAdminMgmtSupportSvc()).isEqualTo(UPDATED_MAIN_BUS_ADMIN_MGMT_SUPPORT_SVC);
        assertThat(testCbcrTable2.getMainBusProvSvcToUnrelatedParties()).isEqualTo(UPDATED_MAIN_BUS_PROV_SVC_TO_UNRELATED_PARTIES);
        assertThat(testCbcrTable2.getMainBusInternalGroupFinance()).isEqualTo(UPDATED_MAIN_BUS_INTERNAL_GROUP_FINANCE);
        assertThat(testCbcrTable2.getMainBusRegulatedFinSvc()).isEqualTo(UPDATED_MAIN_BUS_REGULATED_FIN_SVC);
        assertThat(testCbcrTable2.getMainBusInsurance()).isEqualTo(UPDATED_MAIN_BUS_INSURANCE);
        assertThat(testCbcrTable2.getMainBusHoldingEquityInstruments()).isEqualTo(UPDATED_MAIN_BUS_HOLDING_EQUITY_INSTRUMENTS);
        assertThat(testCbcrTable2.getMainBusDormant()).isEqualTo(UPDATED_MAIN_BUS_DORMANT);
        assertThat(testCbcrTable2.getMainBusOther()).isEqualTo(UPDATED_MAIN_BUS_OTHER);
        assertThat(testCbcrTable2.getMainBusOtherNotes()).isEqualTo(UPDATED_MAIN_BUS_OTHER_NOTES);

        // Validate the CbcrTable2 in ElasticSearch
        CbcrTable2 cbcrTable2Es = cbcrTable2SearchRepository.findOne(testCbcrTable2.getId());
        assertThat(cbcrTable2Es).isEqualToComparingFieldByField(testCbcrTable2);
    }

    @Test
    @Transactional
    public void deleteCbcrTable2() throws Exception {
        // Initialize the database
        cbcrTable2Repository.saveAndFlush(cbcrTable2);
        cbcrTable2SearchRepository.save(cbcrTable2);
        int databaseSizeBeforeDelete = cbcrTable2Repository.findAll().size();

        // Get the cbcrTable2
        restCbcrTable2MockMvc.perform(delete("/api/cbcr-table-2-s/{id}", cbcrTable2.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate ElasticSearch is empty
        boolean cbcrTable2ExistsInEs = cbcrTable2SearchRepository.exists(cbcrTable2.getId());
        assertThat(cbcrTable2ExistsInEs).isFalse();

        // Validate the database is empty
        List<CbcrTable2> cbcrTable2S = cbcrTable2Repository.findAll();
        assertThat(cbcrTable2S).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchCbcrTable2() throws Exception {
        // Initialize the database
        cbcrTable2Repository.saveAndFlush(cbcrTable2);
        cbcrTable2SearchRepository.save(cbcrTable2);

        // Search the cbcrTable2
        restCbcrTable2MockMvc.perform(get("/api/_search/cbcr-table-2-s?query=id:" + cbcrTable2.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cbcrTable2.getId().intValue())))
            .andExpect(jsonPath("$.[*].entityCode").value(hasItem(DEFAULT_ENTITY_CODE.toString())))
            .andExpect(jsonPath("$.[*].taxJurisdiction").value(hasItem(DEFAULT_TAX_JURISDICTION.toString())))
            .andExpect(jsonPath("$.[*].taxJurisOfIncorporation").value(hasItem(DEFAULT_TAX_JURIS_OF_INCORPORATION.toString())))
            .andExpect(jsonPath("$.[*].mainBusRAndD").value(hasItem(DEFAULT_MAIN_BUS_R_AND_D)))
            .andExpect(jsonPath("$.[*].mainBusHoldingIp").value(hasItem(DEFAULT_MAIN_BUS_HOLDING_IP)))
            .andExpect(jsonPath("$.[*].mainBusPurchasing").value(hasItem(DEFAULT_MAIN_BUS_PURCHASING)))
            .andExpect(jsonPath("$.[*].mainBusSalesMktDistr").value(hasItem(DEFAULT_MAIN_BUS_SALES_MKT_DISTR)))
            .andExpect(jsonPath("$.[*].mainBusAdminMgmtSupportSvc").value(hasItem(DEFAULT_MAIN_BUS_ADMIN_MGMT_SUPPORT_SVC)))
            .andExpect(jsonPath("$.[*].mainBusProvSvcToUnrelatedParties").value(hasItem(DEFAULT_MAIN_BUS_PROV_SVC_TO_UNRELATED_PARTIES)))
            .andExpect(jsonPath("$.[*].mainBusInternalGroupFinance").value(hasItem(DEFAULT_MAIN_BUS_INTERNAL_GROUP_FINANCE)))
            .andExpect(jsonPath("$.[*].mainBusRegulatedFinSvc").value(hasItem(DEFAULT_MAIN_BUS_REGULATED_FIN_SVC)))
            .andExpect(jsonPath("$.[*].mainBusInsurance").value(hasItem(DEFAULT_MAIN_BUS_INSURANCE)))
            .andExpect(jsonPath("$.[*].mainBusHoldingEquityInstruments").value(hasItem(DEFAULT_MAIN_BUS_HOLDING_EQUITY_INSTRUMENTS)))
            .andExpect(jsonPath("$.[*].mainBusDormant").value(hasItem(DEFAULT_MAIN_BUS_DORMANT)))
            .andExpect(jsonPath("$.[*].mainBusOther").value(hasItem(DEFAULT_MAIN_BUS_OTHER)))
            .andExpect(jsonPath("$.[*].mainBusOtherNotes").value(hasItem(DEFAULT_MAIN_BUS_OTHER_NOTES.toString())));
    }
}
