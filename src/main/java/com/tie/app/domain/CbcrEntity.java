package com.tie.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A CbcrEntity.
 */
@Entity
@Table(name = "cbcr_entity")
@Document(indexName = "cbcrentity")
public class CbcrEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(max = 80)
    @Column(name = "name", length = 80)
    private String name;

    @Column(name = "entity_code")
    private String entityCode;

    @Size(max = 300)
    @Column(name = "description", length = 300)
    private String description;

    @Column(name = "tax_id_num")
    private String taxIdNum;

    @Column(name = "incorp_country_code")
    private String incorpCountryCode;

    @Column(name = "other_entity_info")
    private String otherEntityInfo;

    @Column(name = "tid_num")
    private String tidNum;

    @Column(name = "is_permanent_establishment")
    private Integer isPermanentEstablishment;

    @Column(name = "addr_leagal_type")
    private String addrLeagalType;

    @Column(name = "addr_free_text")
    private String addrFreeText;

    @Column(name = "addr_street")
    private String addrStreet;

    @Column(name = "addr_building_identifier")
    private String addrBuildingIdentifier;

    @Column(name = "add_suite_identifier")
    private String addSuiteIdentifier;

    @Column(name = "addr_floor_identifier")
    private String addrFloorIdentifier;

    @Column(name = "addr_pob")
    private String addrPOB;

    @Column(name = "addr_post_code")
    private String addrPostCode;

    @Column(name = "addr_city")
    private String addrCity;

    @Column(name = "addr_country_subentity")
    private String addrCountrySubentity;

    @OneToOne(mappedBy = "cbcrEntity")
    @JsonIgnore
    private TieDoc tieDoc;

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

    public String getEntityCode() {
        return entityCode;
    }

    public void setEntityCode(String entityCode) {
        this.entityCode = entityCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTaxIdNum() {
        return taxIdNum;
    }

    public void setTaxIdNum(String taxIdNum) {
        this.taxIdNum = taxIdNum;
    }

    public String getIncorpCountryCode() {
        return incorpCountryCode;
    }

    public void setIncorpCountryCode(String incorpCountryCode) {
        this.incorpCountryCode = incorpCountryCode;
    }

    public String getOtherEntityInfo() {
        return otherEntityInfo;
    }

    public void setOtherEntityInfo(String otherEntityInfo) {
        this.otherEntityInfo = otherEntityInfo;
    }

    public String getTidNum() {
        return tidNum;
    }

    public void setTidNum(String tidNum) {
        this.tidNum = tidNum;
    }

    public Integer getIsPermanentEstablishment() {
        return isPermanentEstablishment;
    }

    public void setIsPermanentEstablishment(Integer isPermanentEstablishment) {
        this.isPermanentEstablishment = isPermanentEstablishment;
    }

    public String getAddrLeagalType() {
        return addrLeagalType;
    }

    public void setAddrLeagalType(String addrLeagalType) {
        this.addrLeagalType = addrLeagalType;
    }

    public String getAddrFreeText() {
        return addrFreeText;
    }

    public void setAddrFreeText(String addrFreeText) {
        this.addrFreeText = addrFreeText;
    }

    public String getAddrStreet() {
        return addrStreet;
    }

    public void setAddrStreet(String addrStreet) {
        this.addrStreet = addrStreet;
    }

    public String getAddrBuildingIdentifier() {
        return addrBuildingIdentifier;
    }

    public void setAddrBuildingIdentifier(String addrBuildingIdentifier) {
        this.addrBuildingIdentifier = addrBuildingIdentifier;
    }

    public String getAddSuiteIdentifier() {
        return addSuiteIdentifier;
    }

    public void setAddSuiteIdentifier(String addSuiteIdentifier) {
        this.addSuiteIdentifier = addSuiteIdentifier;
    }

    public String getAddrFloorIdentifier() {
        return addrFloorIdentifier;
    }

    public void setAddrFloorIdentifier(String addrFloorIdentifier) {
        this.addrFloorIdentifier = addrFloorIdentifier;
    }

    public String getAddrPOB() {
        return addrPOB;
    }

    public void setAddrPOB(String addrPOB) {
        this.addrPOB = addrPOB;
    }

    public String getAddrPostCode() {
        return addrPostCode;
    }

    public void setAddrPostCode(String addrPostCode) {
        this.addrPostCode = addrPostCode;
    }

    public String getAddrCity() {
        return addrCity;
    }

    public void setAddrCity(String addrCity) {
        this.addrCity = addrCity;
    }

    public String getAddrCountrySubentity() {
        return addrCountrySubentity;
    }

    public void setAddrCountrySubentity(String addrCountrySubentity) {
        this.addrCountrySubentity = addrCountrySubentity;
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
        CbcrEntity cbcrEntity = (CbcrEntity) o;
        if(cbcrEntity.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, cbcrEntity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CbcrEntity{" +
            "id=" + id +
            ", name='" + name + "'" +
            ", entityCode='" + entityCode + "'" +
            ", description='" + description + "'" +
            ", taxIdNum='" + taxIdNum + "'" +
            ", incorpCountryCode='" + incorpCountryCode + "'" +
            ", otherEntityInfo='" + otherEntityInfo + "'" +
            ", tidNum='" + tidNum + "'" +
            ", isPermanentEstablishment='" + isPermanentEstablishment + "'" +
            ", addrLeagalType='" + addrLeagalType + "'" +
            ", addrFreeText='" + addrFreeText + "'" +
            ", addrStreet='" + addrStreet + "'" +
            ", addrBuildingIdentifier='" + addrBuildingIdentifier + "'" +
            ", addSuiteIdentifier='" + addSuiteIdentifier + "'" +
            ", addrFloorIdentifier='" + addrFloorIdentifier + "'" +
            ", addrPOB='" + addrPOB + "'" +
            ", addrPostCode='" + addrPostCode + "'" +
            ", addrCity='" + addrCity + "'" +
            ", addrCountrySubentity='" + addrCountrySubentity + "'" +
            '}';
    }
}
