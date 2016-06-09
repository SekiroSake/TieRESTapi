package com.tie.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A CbcrTable2.
 */
@Entity
@Table(name = "cbcr_table_2")
@Document(indexName = "cbcrtable2")
public class CbcrTable2 implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "entity_code")
    private String entityCode;

    @Column(name = "tax_jurisdiction")
    private String taxJurisdiction;

    @Column(name = "tax_juris_of_incorporation")
    private String taxJurisOfIncorporation;

    @Column(name = "main_bus_r_and_d")
    private Integer mainBusRAndD;

    @Column(name = "main_bus_holding_ip")
    private Integer mainBusHoldingIp;

    @Column(name = "main_bus_purchasing")
    private Integer mainBusPurchasing;

    @Column(name = "main_bus_sales_mkt_distr")
    private Integer mainBusSalesMktDistr;

    @Column(name = "main_bus_admin_mgmt_support_svc")
    private Integer mainBusAdminMgmtSupportSvc;

    @Column(name = "main_bus_prov_svc_to_unrelated_parties")
    private Integer mainBusProvSvcToUnrelatedParties;

    @Column(name = "main_bus_internal_group_finance")
    private Integer mainBusInternalGroupFinance;

    @Column(name = "main_bus_regulated_fin_svc")
    private Integer mainBusRegulatedFinSvc;

    @Column(name = "main_bus_insurance")
    private Integer mainBusInsurance;

    @Column(name = "main_bus_holding_equity_instruments")
    private Integer mainBusHoldingEquityInstruments;

    @Column(name = "main_bus_dormant")
    private Integer mainBusDormant;

    @Column(name = "main_bus_other")
    private Integer mainBusOther;

    @Column(name = "main_bus_other_notes")
    private String mainBusOtherNotes;

    @OneToOne(mappedBy = "cbcrTable2")
    @JsonIgnore
    private TieDoc tieDoc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEntityCode() {
        return entityCode;
    }

    public void setEntityCode(String entityCode) {
        this.entityCode = entityCode;
    }

    public String getTaxJurisdiction() {
        return taxJurisdiction;
    }

    public void setTaxJurisdiction(String taxJurisdiction) {
        this.taxJurisdiction = taxJurisdiction;
    }

    public String getTaxJurisOfIncorporation() {
        return taxJurisOfIncorporation;
    }

    public void setTaxJurisOfIncorporation(String taxJurisOfIncorporation) {
        this.taxJurisOfIncorporation = taxJurisOfIncorporation;
    }

    public Integer getMainBusRAndD() {
        return mainBusRAndD;
    }

    public void setMainBusRAndD(Integer mainBusRAndD) {
        this.mainBusRAndD = mainBusRAndD;
    }

    public Integer getMainBusHoldingIp() {
        return mainBusHoldingIp;
    }

    public void setMainBusHoldingIp(Integer mainBusHoldingIp) {
        this.mainBusHoldingIp = mainBusHoldingIp;
    }

    public Integer getMainBusPurchasing() {
        return mainBusPurchasing;
    }

    public void setMainBusPurchasing(Integer mainBusPurchasing) {
        this.mainBusPurchasing = mainBusPurchasing;
    }

    public Integer getMainBusSalesMktDistr() {
        return mainBusSalesMktDistr;
    }

    public void setMainBusSalesMktDistr(Integer mainBusSalesMktDistr) {
        this.mainBusSalesMktDistr = mainBusSalesMktDistr;
    }

    public Integer getMainBusAdminMgmtSupportSvc() {
        return mainBusAdminMgmtSupportSvc;
    }

    public void setMainBusAdminMgmtSupportSvc(Integer mainBusAdminMgmtSupportSvc) {
        this.mainBusAdminMgmtSupportSvc = mainBusAdminMgmtSupportSvc;
    }

    public Integer getMainBusProvSvcToUnrelatedParties() {
        return mainBusProvSvcToUnrelatedParties;
    }

    public void setMainBusProvSvcToUnrelatedParties(Integer mainBusProvSvcToUnrelatedParties) {
        this.mainBusProvSvcToUnrelatedParties = mainBusProvSvcToUnrelatedParties;
    }

    public Integer getMainBusInternalGroupFinance() {
        return mainBusInternalGroupFinance;
    }

    public void setMainBusInternalGroupFinance(Integer mainBusInternalGroupFinance) {
        this.mainBusInternalGroupFinance = mainBusInternalGroupFinance;
    }

    public Integer getMainBusRegulatedFinSvc() {
        return mainBusRegulatedFinSvc;
    }

    public void setMainBusRegulatedFinSvc(Integer mainBusRegulatedFinSvc) {
        this.mainBusRegulatedFinSvc = mainBusRegulatedFinSvc;
    }

    public Integer getMainBusInsurance() {
        return mainBusInsurance;
    }

    public void setMainBusInsurance(Integer mainBusInsurance) {
        this.mainBusInsurance = mainBusInsurance;
    }

    public Integer getMainBusHoldingEquityInstruments() {
        return mainBusHoldingEquityInstruments;
    }

    public void setMainBusHoldingEquityInstruments(Integer mainBusHoldingEquityInstruments) {
        this.mainBusHoldingEquityInstruments = mainBusHoldingEquityInstruments;
    }

    public Integer getMainBusDormant() {
        return mainBusDormant;
    }

    public void setMainBusDormant(Integer mainBusDormant) {
        this.mainBusDormant = mainBusDormant;
    }

    public Integer getMainBusOther() {
        return mainBusOther;
    }

    public void setMainBusOther(Integer mainBusOther) {
        this.mainBusOther = mainBusOther;
    }

    public String getMainBusOtherNotes() {
        return mainBusOtherNotes;
    }

    public void setMainBusOtherNotes(String mainBusOtherNotes) {
        this.mainBusOtherNotes = mainBusOtherNotes;
    }

    public TieDoc getTieDoc() {
        return tieDoc;
    }

    public void setTieDoc(TieDoc tieDoc) {
        this.tieDoc = tieDoc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CbcrTable2 cbcrTable2 = (CbcrTable2) o;
        if(cbcrTable2.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, cbcrTable2.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CbcrTable2{" +
            "id=" + id +
            ", entityCode='" + entityCode + "'" +
            ", taxJurisdiction='" + taxJurisdiction + "'" +
            ", taxJurisOfIncorporation='" + taxJurisOfIncorporation + "'" +
            ", mainBusRAndD='" + mainBusRAndD + "'" +
            ", mainBusHoldingIp='" + mainBusHoldingIp + "'" +
            ", mainBusPurchasing='" + mainBusPurchasing + "'" +
            ", mainBusSalesMktDistr='" + mainBusSalesMktDistr + "'" +
            ", mainBusAdminMgmtSupportSvc='" + mainBusAdminMgmtSupportSvc + "'" +
            ", mainBusProvSvcToUnrelatedParties='" + mainBusProvSvcToUnrelatedParties + "'" +
            ", mainBusInternalGroupFinance='" + mainBusInternalGroupFinance + "'" +
            ", mainBusRegulatedFinSvc='" + mainBusRegulatedFinSvc + "'" +
            ", mainBusInsurance='" + mainBusInsurance + "'" +
            ", mainBusHoldingEquityInstruments='" + mainBusHoldingEquityInstruments + "'" +
            ", mainBusDormant='" + mainBusDormant + "'" +
            ", mainBusOther='" + mainBusOther + "'" +
            ", mainBusOtherNotes='" + mainBusOtherNotes + "'" +
            '}';
    }
}
