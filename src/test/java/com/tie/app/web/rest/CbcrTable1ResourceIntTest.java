package com.tie.app.web.rest;

import com.tie.app.TieEntityApp;
import com.tie.app.domain.CbcrTable1;
import com.tie.app.repository.CbcrTable1Repository;
import com.tie.app.repository.search.CbcrTable1SearchRepository;

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
import java.math.BigDecimal;;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the CbcrTable1Resource REST controller.
 *
 * @see CbcrTable1Resource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TieEntityApp.class)
@WebAppConfiguration
@IntegrationTest
public class CbcrTable1ResourceIntTest {

    private static final String DEFAULT_TAX_JURISDICTION = "AAAAA";
    private static final String UPDATED_TAX_JURISDICTION = "BBBBB";

    private static final BigDecimal DEFAULT_REVENUE_UNRELATED_PARTY = new BigDecimal(1);
    private static final BigDecimal UPDATED_REVENUE_UNRELATED_PARTY = new BigDecimal(2);

    private static final BigDecimal DEFAULT_REVENUE_RELATED_PARTY = new BigDecimal(1);
    private static final BigDecimal UPDATED_REVENUE_RELATED_PARTY = new BigDecimal(2);

    private static final BigDecimal DEFAULT_REVENUE_TOTAL = new BigDecimal(1);
    private static final BigDecimal UPDATED_REVENUE_TOTAL = new BigDecimal(2);

    private static final BigDecimal DEFAULT_PL_BEFORE_INCOME_TAX = new BigDecimal(1);
    private static final BigDecimal UPDATED_PL_BEFORE_INCOME_TAX = new BigDecimal(2);

    private static final BigDecimal DEFAULT_INCOME_TAX_PAID = new BigDecimal(1);
    private static final BigDecimal UPDATED_INCOME_TAX_PAID = new BigDecimal(2);

    private static final BigDecimal DEFAULT_INCOME_TAX_ACCRUED = new BigDecimal(1);
    private static final BigDecimal UPDATED_INCOME_TAX_ACCRUED = new BigDecimal(2);

    private static final BigDecimal DEFAULT_STATED_CAPITAL = new BigDecimal(1);
    private static final BigDecimal UPDATED_STATED_CAPITAL = new BigDecimal(2);

    private static final BigDecimal DEFAULT_ACCUMULATED_EARNINGS = new BigDecimal(1);
    private static final BigDecimal UPDATED_ACCUMULATED_EARNINGS = new BigDecimal(2);

    private static final Integer DEFAULT_NUMBER_OF_EMPLOYEES = 1;
    private static final Integer UPDATED_NUMBER_OF_EMPLOYEES = 2;

    private static final BigDecimal DEFAULT_TANGIBLE_ASSETS_NON_CASH = new BigDecimal(1);
    private static final BigDecimal UPDATED_TANGIBLE_ASSETS_NON_CASH = new BigDecimal(2);

    @Inject
    private CbcrTable1Repository cbcrTable1Repository;

    @Inject
    private CbcrTable1SearchRepository cbcrTable1SearchRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restCbcrTable1MockMvc;

    private CbcrTable1 cbcrTable1;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CbcrTable1Resource cbcrTable1Resource = new CbcrTable1Resource();
        ReflectionTestUtils.setField(cbcrTable1Resource, "cbcrTable1SearchRepository", cbcrTable1SearchRepository);
        ReflectionTestUtils.setField(cbcrTable1Resource, "cbcrTable1Repository", cbcrTable1Repository);
        this.restCbcrTable1MockMvc = MockMvcBuilders.standaloneSetup(cbcrTable1Resource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        cbcrTable1SearchRepository.deleteAll();
        cbcrTable1 = new CbcrTable1();
        cbcrTable1.setTaxJurisdiction(DEFAULT_TAX_JURISDICTION);
        cbcrTable1.setRevenueUnrelatedParty(DEFAULT_REVENUE_UNRELATED_PARTY);
        cbcrTable1.setRevenueRelatedParty(DEFAULT_REVENUE_RELATED_PARTY);
        cbcrTable1.setRevenueTotal(DEFAULT_REVENUE_TOTAL);
        cbcrTable1.setPlBeforeIncomeTax(DEFAULT_PL_BEFORE_INCOME_TAX);
        cbcrTable1.setIncomeTaxPaid(DEFAULT_INCOME_TAX_PAID);
        cbcrTable1.setIncomeTaxAccrued(DEFAULT_INCOME_TAX_ACCRUED);
        cbcrTable1.setStatedCapital(DEFAULT_STATED_CAPITAL);
        cbcrTable1.setAccumulatedEarnings(DEFAULT_ACCUMULATED_EARNINGS);
        cbcrTable1.setNumberOfEmployees(DEFAULT_NUMBER_OF_EMPLOYEES);
        cbcrTable1.setTangibleAssetsNonCash(DEFAULT_TANGIBLE_ASSETS_NON_CASH);
    }

    @Test
    @Transactional
    public void createCbcrTable1() throws Exception {
        int databaseSizeBeforeCreate = cbcrTable1Repository.findAll().size();

        // Create the CbcrTable1

        restCbcrTable1MockMvc.perform(post("/api/cbcr-table-1-s")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(cbcrTable1)))
                .andExpect(status().isCreated());

        // Validate the CbcrTable1 in the database
        List<CbcrTable1> cbcrTable1S = cbcrTable1Repository.findAll();
        assertThat(cbcrTable1S).hasSize(databaseSizeBeforeCreate + 1);
        CbcrTable1 testCbcrTable1 = cbcrTable1S.get(cbcrTable1S.size() - 1);
        assertThat(testCbcrTable1.getTaxJurisdiction()).isEqualTo(DEFAULT_TAX_JURISDICTION);
        assertThat(testCbcrTable1.getRevenueUnrelatedParty()).isEqualTo(DEFAULT_REVENUE_UNRELATED_PARTY);
        assertThat(testCbcrTable1.getRevenueRelatedParty()).isEqualTo(DEFAULT_REVENUE_RELATED_PARTY);
        assertThat(testCbcrTable1.getRevenueTotal()).isEqualTo(DEFAULT_REVENUE_TOTAL);
        assertThat(testCbcrTable1.getPlBeforeIncomeTax()).isEqualTo(DEFAULT_PL_BEFORE_INCOME_TAX);
        assertThat(testCbcrTable1.getIncomeTaxPaid()).isEqualTo(DEFAULT_INCOME_TAX_PAID);
        assertThat(testCbcrTable1.getIncomeTaxAccrued()).isEqualTo(DEFAULT_INCOME_TAX_ACCRUED);
        assertThat(testCbcrTable1.getStatedCapital()).isEqualTo(DEFAULT_STATED_CAPITAL);
        assertThat(testCbcrTable1.getAccumulatedEarnings()).isEqualTo(DEFAULT_ACCUMULATED_EARNINGS);
        assertThat(testCbcrTable1.getNumberOfEmployees()).isEqualTo(DEFAULT_NUMBER_OF_EMPLOYEES);
        assertThat(testCbcrTable1.getTangibleAssetsNonCash()).isEqualTo(DEFAULT_TANGIBLE_ASSETS_NON_CASH);

        // Validate the CbcrTable1 in ElasticSearch
        CbcrTable1 cbcrTable1Es = cbcrTable1SearchRepository.findOne(testCbcrTable1.getId());
        assertThat(cbcrTable1Es).isEqualToComparingFieldByField(testCbcrTable1);
    }

    @Test
    @Transactional
    public void getAllCbcrTable1S() throws Exception {
        // Initialize the database
        cbcrTable1Repository.saveAndFlush(cbcrTable1);

        // Get all the cbcrTable1S
        restCbcrTable1MockMvc.perform(get("/api/cbcr-table-1-s?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(cbcrTable1.getId().intValue())))
                .andExpect(jsonPath("$.[*].taxJurisdiction").value(hasItem(DEFAULT_TAX_JURISDICTION.toString())))
                .andExpect(jsonPath("$.[*].revenueUnrelatedParty").value(hasItem(DEFAULT_REVENUE_UNRELATED_PARTY.intValue())))
                .andExpect(jsonPath("$.[*].revenueRelatedParty").value(hasItem(DEFAULT_REVENUE_RELATED_PARTY.intValue())))
                .andExpect(jsonPath("$.[*].revenueTotal").value(hasItem(DEFAULT_REVENUE_TOTAL.intValue())))
                .andExpect(jsonPath("$.[*].plBeforeIncomeTax").value(hasItem(DEFAULT_PL_BEFORE_INCOME_TAX.intValue())))
                .andExpect(jsonPath("$.[*].incomeTaxPaid").value(hasItem(DEFAULT_INCOME_TAX_PAID.intValue())))
                .andExpect(jsonPath("$.[*].incomeTaxAccrued").value(hasItem(DEFAULT_INCOME_TAX_ACCRUED.intValue())))
                .andExpect(jsonPath("$.[*].statedCapital").value(hasItem(DEFAULT_STATED_CAPITAL.intValue())))
                .andExpect(jsonPath("$.[*].accumulatedEarnings").value(hasItem(DEFAULT_ACCUMULATED_EARNINGS.intValue())))
                .andExpect(jsonPath("$.[*].numberOfEmployees").value(hasItem(DEFAULT_NUMBER_OF_EMPLOYEES)))
                .andExpect(jsonPath("$.[*].tangibleAssetsNonCash").value(hasItem(DEFAULT_TANGIBLE_ASSETS_NON_CASH.intValue())));
    }

    @Test
    @Transactional
    public void getCbcrTable1() throws Exception {
        // Initialize the database
        cbcrTable1Repository.saveAndFlush(cbcrTable1);

        // Get the cbcrTable1
        restCbcrTable1MockMvc.perform(get("/api/cbcr-table-1-s/{id}", cbcrTable1.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(cbcrTable1.getId().intValue()))
            .andExpect(jsonPath("$.taxJurisdiction").value(DEFAULT_TAX_JURISDICTION.toString()))
            .andExpect(jsonPath("$.revenueUnrelatedParty").value(DEFAULT_REVENUE_UNRELATED_PARTY.intValue()))
            .andExpect(jsonPath("$.revenueRelatedParty").value(DEFAULT_REVENUE_RELATED_PARTY.intValue()))
            .andExpect(jsonPath("$.revenueTotal").value(DEFAULT_REVENUE_TOTAL.intValue()))
            .andExpect(jsonPath("$.plBeforeIncomeTax").value(DEFAULT_PL_BEFORE_INCOME_TAX.intValue()))
            .andExpect(jsonPath("$.incomeTaxPaid").value(DEFAULT_INCOME_TAX_PAID.intValue()))
            .andExpect(jsonPath("$.incomeTaxAccrued").value(DEFAULT_INCOME_TAX_ACCRUED.intValue()))
            .andExpect(jsonPath("$.statedCapital").value(DEFAULT_STATED_CAPITAL.intValue()))
            .andExpect(jsonPath("$.accumulatedEarnings").value(DEFAULT_ACCUMULATED_EARNINGS.intValue()))
            .andExpect(jsonPath("$.numberOfEmployees").value(DEFAULT_NUMBER_OF_EMPLOYEES))
            .andExpect(jsonPath("$.tangibleAssetsNonCash").value(DEFAULT_TANGIBLE_ASSETS_NON_CASH.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingCbcrTable1() throws Exception {
        // Get the cbcrTable1
        restCbcrTable1MockMvc.perform(get("/api/cbcr-table-1-s/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCbcrTable1() throws Exception {
        // Initialize the database
        cbcrTable1Repository.saveAndFlush(cbcrTable1);
        cbcrTable1SearchRepository.save(cbcrTable1);
        int databaseSizeBeforeUpdate = cbcrTable1Repository.findAll().size();

        // Update the cbcrTable1
        CbcrTable1 updatedCbcrTable1 = new CbcrTable1();
        updatedCbcrTable1.setId(cbcrTable1.getId());
        updatedCbcrTable1.setTaxJurisdiction(UPDATED_TAX_JURISDICTION);
        updatedCbcrTable1.setRevenueUnrelatedParty(UPDATED_REVENUE_UNRELATED_PARTY);
        updatedCbcrTable1.setRevenueRelatedParty(UPDATED_REVENUE_RELATED_PARTY);
        updatedCbcrTable1.setRevenueTotal(UPDATED_REVENUE_TOTAL);
        updatedCbcrTable1.setPlBeforeIncomeTax(UPDATED_PL_BEFORE_INCOME_TAX);
        updatedCbcrTable1.setIncomeTaxPaid(UPDATED_INCOME_TAX_PAID);
        updatedCbcrTable1.setIncomeTaxAccrued(UPDATED_INCOME_TAX_ACCRUED);
        updatedCbcrTable1.setStatedCapital(UPDATED_STATED_CAPITAL);
        updatedCbcrTable1.setAccumulatedEarnings(UPDATED_ACCUMULATED_EARNINGS);
        updatedCbcrTable1.setNumberOfEmployees(UPDATED_NUMBER_OF_EMPLOYEES);
        updatedCbcrTable1.setTangibleAssetsNonCash(UPDATED_TANGIBLE_ASSETS_NON_CASH);

        restCbcrTable1MockMvc.perform(put("/api/cbcr-table-1-s")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedCbcrTable1)))
                .andExpect(status().isOk());

        // Validate the CbcrTable1 in the database
        List<CbcrTable1> cbcrTable1S = cbcrTable1Repository.findAll();
        assertThat(cbcrTable1S).hasSize(databaseSizeBeforeUpdate);
        CbcrTable1 testCbcrTable1 = cbcrTable1S.get(cbcrTable1S.size() - 1);
        assertThat(testCbcrTable1.getTaxJurisdiction()).isEqualTo(UPDATED_TAX_JURISDICTION);
        assertThat(testCbcrTable1.getRevenueUnrelatedParty()).isEqualTo(UPDATED_REVENUE_UNRELATED_PARTY);
        assertThat(testCbcrTable1.getRevenueRelatedParty()).isEqualTo(UPDATED_REVENUE_RELATED_PARTY);
        assertThat(testCbcrTable1.getRevenueTotal()).isEqualTo(UPDATED_REVENUE_TOTAL);
        assertThat(testCbcrTable1.getPlBeforeIncomeTax()).isEqualTo(UPDATED_PL_BEFORE_INCOME_TAX);
        assertThat(testCbcrTable1.getIncomeTaxPaid()).isEqualTo(UPDATED_INCOME_TAX_PAID);
        assertThat(testCbcrTable1.getIncomeTaxAccrued()).isEqualTo(UPDATED_INCOME_TAX_ACCRUED);
        assertThat(testCbcrTable1.getStatedCapital()).isEqualTo(UPDATED_STATED_CAPITAL);
        assertThat(testCbcrTable1.getAccumulatedEarnings()).isEqualTo(UPDATED_ACCUMULATED_EARNINGS);
        assertThat(testCbcrTable1.getNumberOfEmployees()).isEqualTo(UPDATED_NUMBER_OF_EMPLOYEES);
        assertThat(testCbcrTable1.getTangibleAssetsNonCash()).isEqualTo(UPDATED_TANGIBLE_ASSETS_NON_CASH);

        // Validate the CbcrTable1 in ElasticSearch
        CbcrTable1 cbcrTable1Es = cbcrTable1SearchRepository.findOne(testCbcrTable1.getId());
        assertThat(cbcrTable1Es).isEqualToComparingFieldByField(testCbcrTable1);
    }

    @Test
    @Transactional
    public void deleteCbcrTable1() throws Exception {
        // Initialize the database
        cbcrTable1Repository.saveAndFlush(cbcrTable1);
        cbcrTable1SearchRepository.save(cbcrTable1);
        int databaseSizeBeforeDelete = cbcrTable1Repository.findAll().size();

        // Get the cbcrTable1
        restCbcrTable1MockMvc.perform(delete("/api/cbcr-table-1-s/{id}", cbcrTable1.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate ElasticSearch is empty
        boolean cbcrTable1ExistsInEs = cbcrTable1SearchRepository.exists(cbcrTable1.getId());
        assertThat(cbcrTable1ExistsInEs).isFalse();

        // Validate the database is empty
        List<CbcrTable1> cbcrTable1S = cbcrTable1Repository.findAll();
        assertThat(cbcrTable1S).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchCbcrTable1() throws Exception {
        // Initialize the database
        cbcrTable1Repository.saveAndFlush(cbcrTable1);
        cbcrTable1SearchRepository.save(cbcrTable1);

        // Search the cbcrTable1
        restCbcrTable1MockMvc.perform(get("/api/_search/cbcr-table-1-s?query=id:" + cbcrTable1.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cbcrTable1.getId().intValue())))
            .andExpect(jsonPath("$.[*].taxJurisdiction").value(hasItem(DEFAULT_TAX_JURISDICTION.toString())))
            .andExpect(jsonPath("$.[*].revenueUnrelatedParty").value(hasItem(DEFAULT_REVENUE_UNRELATED_PARTY.intValue())))
            .andExpect(jsonPath("$.[*].revenueRelatedParty").value(hasItem(DEFAULT_REVENUE_RELATED_PARTY.intValue())))
            .andExpect(jsonPath("$.[*].revenueTotal").value(hasItem(DEFAULT_REVENUE_TOTAL.intValue())))
            .andExpect(jsonPath("$.[*].plBeforeIncomeTax").value(hasItem(DEFAULT_PL_BEFORE_INCOME_TAX.intValue())))
            .andExpect(jsonPath("$.[*].incomeTaxPaid").value(hasItem(DEFAULT_INCOME_TAX_PAID.intValue())))
            .andExpect(jsonPath("$.[*].incomeTaxAccrued").value(hasItem(DEFAULT_INCOME_TAX_ACCRUED.intValue())))
            .andExpect(jsonPath("$.[*].statedCapital").value(hasItem(DEFAULT_STATED_CAPITAL.intValue())))
            .andExpect(jsonPath("$.[*].accumulatedEarnings").value(hasItem(DEFAULT_ACCUMULATED_EARNINGS.intValue())))
            .andExpect(jsonPath("$.[*].numberOfEmployees").value(hasItem(DEFAULT_NUMBER_OF_EMPLOYEES)))
            .andExpect(jsonPath("$.[*].tangibleAssetsNonCash").value(hasItem(DEFAULT_TANGIBLE_ASSETS_NON_CASH.intValue())));
    }
}
