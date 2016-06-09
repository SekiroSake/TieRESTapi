package com.tie.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A CbcrTable1.
 */
@Entity
@Table(name = "cbcr_table_1")
@Document(indexName = "cbcrtable1")
public class CbcrTable1 implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "tax_jurisdiction")
    private String taxJurisdiction;

    @Column(name = "revenue_unrelated_party", precision=10, scale=2)
    private BigDecimal revenueUnrelatedParty;

    @Column(name = "revenue_related_party", precision=10, scale=2)
    private BigDecimal revenueRelatedParty;

    @Column(name = "revenue_total", precision=10, scale=2)
    private BigDecimal revenueTotal;

    @Column(name = "pl_before_income_tax", precision=10, scale=2)
    private BigDecimal plBeforeIncomeTax;

    @Column(name = "income_tax_paid", precision=10, scale=2)
    private BigDecimal incomeTaxPaid;

    @Column(name = "income_tax_accrued", precision=10, scale=2)
    private BigDecimal incomeTaxAccrued;

    @Column(name = "stated_capital", precision=10, scale=2)
    private BigDecimal statedCapital;

    @Column(name = "accumulated_earnings", precision=10, scale=2)
    private BigDecimal accumulatedEarnings;

    @Column(name = "number_of_employees")
    private Integer numberOfEmployees;

    @Column(name = "tangible_assets_non_cash", precision=10, scale=2)
    private BigDecimal tangibleAssetsNonCash;

    @OneToOne(mappedBy = "cbcrTable1")
    @JsonIgnore
    private TieDoc tieDoc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaxJurisdiction() {
        return taxJurisdiction;
    }

    public void setTaxJurisdiction(String taxJurisdiction) {
        this.taxJurisdiction = taxJurisdiction;
    }

    public BigDecimal getRevenueUnrelatedParty() {
        return revenueUnrelatedParty;
    }

    public void setRevenueUnrelatedParty(BigDecimal revenueUnrelatedParty) {
        this.revenueUnrelatedParty = revenueUnrelatedParty;
    }

    public BigDecimal getRevenueRelatedParty() {
        return revenueRelatedParty;
    }

    public void setRevenueRelatedParty(BigDecimal revenueRelatedParty) {
        this.revenueRelatedParty = revenueRelatedParty;
    }

    public BigDecimal getRevenueTotal() {
        return revenueTotal;
    }

    public void setRevenueTotal(BigDecimal revenueTotal) {
        this.revenueTotal = revenueTotal;
    }

    public BigDecimal getPlBeforeIncomeTax() {
        return plBeforeIncomeTax;
    }

    public void setPlBeforeIncomeTax(BigDecimal plBeforeIncomeTax) {
        this.plBeforeIncomeTax = plBeforeIncomeTax;
    }

    public BigDecimal getIncomeTaxPaid() {
        return incomeTaxPaid;
    }

    public void setIncomeTaxPaid(BigDecimal incomeTaxPaid) {
        this.incomeTaxPaid = incomeTaxPaid;
    }

    public BigDecimal getIncomeTaxAccrued() {
        return incomeTaxAccrued;
    }

    public void setIncomeTaxAccrued(BigDecimal incomeTaxAccrued) {
        this.incomeTaxAccrued = incomeTaxAccrued;
    }

    public BigDecimal getStatedCapital() {
        return statedCapital;
    }

    public void setStatedCapital(BigDecimal statedCapital) {
        this.statedCapital = statedCapital;
    }

    public BigDecimal getAccumulatedEarnings() {
        return accumulatedEarnings;
    }

    public void setAccumulatedEarnings(BigDecimal accumulatedEarnings) {
        this.accumulatedEarnings = accumulatedEarnings;
    }

    public Integer getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public void setNumberOfEmployees(Integer numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

    public BigDecimal getTangibleAssetsNonCash() {
        return tangibleAssetsNonCash;
    }

    public void setTangibleAssetsNonCash(BigDecimal tangibleAssetsNonCash) {
        this.tangibleAssetsNonCash = tangibleAssetsNonCash;
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
        CbcrTable1 cbcrTable1 = (CbcrTable1) o;
        if(cbcrTable1.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, cbcrTable1.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CbcrTable1{" +
            "id=" + id +
            ", taxJurisdiction='" + taxJurisdiction + "'" +
            ", revenueUnrelatedParty='" + revenueUnrelatedParty + "'" +
            ", revenueRelatedParty='" + revenueRelatedParty + "'" +
            ", revenueTotal='" + revenueTotal + "'" +
            ", plBeforeIncomeTax='" + plBeforeIncomeTax + "'" +
            ", incomeTaxPaid='" + incomeTaxPaid + "'" +
            ", incomeTaxAccrued='" + incomeTaxAccrued + "'" +
            ", statedCapital='" + statedCapital + "'" +
            ", accumulatedEarnings='" + accumulatedEarnings + "'" +
            ", numberOfEmployees='" + numberOfEmployees + "'" +
            ", tangibleAssetsNonCash='" + tangibleAssetsNonCash + "'" +
            '}';
    }
}
