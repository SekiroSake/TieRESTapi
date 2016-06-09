package com.tie.app.domain;

import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A TieDoc.
 */
@Entity
@Table(name = "tie_doc")
@Document(indexName = "tiedoc")
public class TieDoc implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(max = 80)
    @Column(name = "name", length = 80)
    private String name;

    @Size(max = 80)
    @Column(name = "code", length = 80)
    private String code;

    @Column(name = "description")
    private String description;

    @Column(name = "reporting_entity_code")
    private String reportingEntityCode;

    @Column(name = "currency_code")
    private String currencyCode;

    @Column(name = "res_country_code")
    private String resCountryCode;

    @OneToOne
    @JoinColumn(unique = true)
    private CbcrEntity cbcrEntity;

    @OneToOne
    @JoinColumn(unique = true)
    private CbcrTable1 cbcrTable1;

    @OneToOne
    @JoinColumn(unique = true)
    private CbcrTable2 cbcrTable2;

    @OneToOne
    @JoinColumn(unique = true)
    private CbcrTable3 cbcrTable3;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReportingEntityCode() {
        return reportingEntityCode;
    }

    public void setReportingEntityCode(String reportingEntityCode) {
        this.reportingEntityCode = reportingEntityCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getResCountryCode() {
        return resCountryCode;
    }

    public void setResCountryCode(String resCountryCode) {
        this.resCountryCode = resCountryCode;
    }

    public CbcrEntity getCbcrEntity() {
        return cbcrEntity;
    }

    public void setCbcrEntity(CbcrEntity cbcrEntity) {
        this.cbcrEntity = cbcrEntity;
    }

    public CbcrTable1 getCbcrTable1() {
        return cbcrTable1;
    }

    public void setCbcrTable1(CbcrTable1 cbcrTable1) {
        this.cbcrTable1 = cbcrTable1;
    }

    public CbcrTable2 getCbcrTable2() {
        return cbcrTable2;
    }

    public void setCbcrTable2(CbcrTable2 cbcrTable2) {
        this.cbcrTable2 = cbcrTable2;
    }

    public CbcrTable3 getCbcrTable3() {
        return cbcrTable3;
    }

    public void setCbcrTable3(CbcrTable3 cbcrTable3) {
        this.cbcrTable3 = cbcrTable3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TieDoc tieDoc = (TieDoc) o;
        if(tieDoc.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, tieDoc.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "TieDoc{" +
            "id=" + id +
            ", name='" + name + "'" +
            ", code='" + code + "'" +
            ", description='" + description + "'" +
            ", reportingEntityCode='" + reportingEntityCode + "'" +
            ", currencyCode='" + currencyCode + "'" +
            ", resCountryCode='" + resCountryCode + "'" +
            '}';
    }
}
