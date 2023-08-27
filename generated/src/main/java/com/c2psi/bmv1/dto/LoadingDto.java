package com.c2psi.bmv1.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.OffsetDateTime;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * A Loading in the system
 */
@ApiModel(description = "A Loading in the system")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-27T14:53:37.924409800+01:00[Africa/Casablanca]")
public class LoadingDto   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("loadCode")
  private String loadCode;

  @JsonProperty("loadDate")
  @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime loadDate;

  @JsonProperty("loadExpectedamount")
  private Double loadExpectedamount;

  @JsonProperty("loadPaidamount")
  private Double loadPaidamount;

  @JsonProperty("loadRemise")
  private Double loadRemise;

  @JsonProperty("loadReport")
  private String loadReport;

  @JsonProperty("loadManagerId")
  private Long loadManagerId;

  @JsonProperty("loadSalerId")
  private Long loadSalerId;

  @JsonProperty("loadPosId")
  private Long loadPosId;

  public LoadingDto id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @ApiModelProperty(value = "")


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LoadingDto loadCode(String loadCode) {
    this.loadCode = loadCode;
    return this;
  }

  /**
   * Get loadCode
   * @return loadCode
  */
  @ApiModelProperty(example = "L0000", value = "")


  public String getLoadCode() {
    return loadCode;
  }

  public void setLoadCode(String loadCode) {
    this.loadCode = loadCode;
  }

  public LoadingDto loadDate(OffsetDateTime loadDate) {
    this.loadDate = loadDate;
    return this;
  }

  /**
   * Get loadDate
   * @return loadDate
  */
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getLoadDate() {
    return loadDate;
  }

  public void setLoadDate(OffsetDateTime loadDate) {
    this.loadDate = loadDate;
  }

  public LoadingDto loadExpectedamount(Double loadExpectedamount) {
    this.loadExpectedamount = loadExpectedamount;
    return this;
  }

  /**
   * Get loadExpectedamount
   * minimum: 1
   * @return loadExpectedamount
  */
  @ApiModelProperty(value = "")

@DecimalMin("1") 
  public Double getLoadExpectedamount() {
    return loadExpectedamount;
  }

  public void setLoadExpectedamount(Double loadExpectedamount) {
    this.loadExpectedamount = loadExpectedamount;
  }

  public LoadingDto loadPaidamount(Double loadPaidamount) {
    this.loadPaidamount = loadPaidamount;
    return this;
  }

  /**
   * Get loadPaidamount
   * minimum: 1
   * @return loadPaidamount
  */
  @ApiModelProperty(value = "")

@DecimalMin("1") 
  public Double getLoadPaidamount() {
    return loadPaidamount;
  }

  public void setLoadPaidamount(Double loadPaidamount) {
    this.loadPaidamount = loadPaidamount;
  }

  public LoadingDto loadRemise(Double loadRemise) {
    this.loadRemise = loadRemise;
    return this;
  }

  /**
   * Get loadRemise
   * minimum: 0
   * @return loadRemise
  */
  @ApiModelProperty(value = "")

@DecimalMin("0") 
  public Double getLoadRemise() {
    return loadRemise;
  }

  public void setLoadRemise(Double loadRemise) {
    this.loadRemise = loadRemise;
  }

  public LoadingDto loadReport(String loadReport) {
    this.loadReport = loadReport;
    return this;
  }

  /**
   * Get loadReport
   * @return loadReport
  */
  @ApiModelProperty(value = "")


  public String getLoadReport() {
    return loadReport;
  }

  public void setLoadReport(String loadReport) {
    this.loadReport = loadReport;
  }

  public LoadingDto loadManagerId(Long loadManagerId) {
    this.loadManagerId = loadManagerId;
    return this;
  }

  /**
   * Get loadManagerId
   * @return loadManagerId
  */
  @ApiModelProperty(value = "")


  public Long getLoadManagerId() {
    return loadManagerId;
  }

  public void setLoadManagerId(Long loadManagerId) {
    this.loadManagerId = loadManagerId;
  }

  public LoadingDto loadSalerId(Long loadSalerId) {
    this.loadSalerId = loadSalerId;
    return this;
  }

  /**
   * Get loadSalerId
   * @return loadSalerId
  */
  @ApiModelProperty(value = "")


  public Long getLoadSalerId() {
    return loadSalerId;
  }

  public void setLoadSalerId(Long loadSalerId) {
    this.loadSalerId = loadSalerId;
  }

  public LoadingDto loadPosId(Long loadPosId) {
    this.loadPosId = loadPosId;
    return this;
  }

  /**
   * Get loadPosId
   * @return loadPosId
  */
  @ApiModelProperty(value = "")


  public Long getLoadPosId() {
    return loadPosId;
  }

  public void setLoadPosId(Long loadPosId) {
    this.loadPosId = loadPosId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LoadingDto loadingDto = (LoadingDto) o;
    return Objects.equals(this.id, loadingDto.id) &&
        Objects.equals(this.loadCode, loadingDto.loadCode) &&
        Objects.equals(this.loadDate, loadingDto.loadDate) &&
        Objects.equals(this.loadExpectedamount, loadingDto.loadExpectedamount) &&
        Objects.equals(this.loadPaidamount, loadingDto.loadPaidamount) &&
        Objects.equals(this.loadRemise, loadingDto.loadRemise) &&
        Objects.equals(this.loadReport, loadingDto.loadReport) &&
        Objects.equals(this.loadManagerId, loadingDto.loadManagerId) &&
        Objects.equals(this.loadSalerId, loadingDto.loadSalerId) &&
        Objects.equals(this.loadPosId, loadingDto.loadPosId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, loadCode, loadDate, loadExpectedamount, loadPaidamount, loadRemise, loadReport, loadManagerId, loadSalerId, loadPosId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LoadingDto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    loadCode: ").append(toIndentedString(loadCode)).append("\n");
    sb.append("    loadDate: ").append(toIndentedString(loadDate)).append("\n");
    sb.append("    loadExpectedamount: ").append(toIndentedString(loadExpectedamount)).append("\n");
    sb.append("    loadPaidamount: ").append(toIndentedString(loadPaidamount)).append("\n");
    sb.append("    loadRemise: ").append(toIndentedString(loadRemise)).append("\n");
    sb.append("    loadReport: ").append(toIndentedString(loadReport)).append("\n");
    sb.append("    loadManagerId: ").append(toIndentedString(loadManagerId)).append("\n");
    sb.append("    loadSalerId: ").append(toIndentedString(loadSalerId)).append("\n");
    sb.append("    loadPosId: ").append(toIndentedString(loadPosId)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

