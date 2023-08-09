package com.c2psi.bmv1.dto;

import java.util.Objects;
import com.c2psi.bmv1.dto.PointofsaleDto;
import com.c2psi.bmv1.dto.UserbmDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * A Loading in the system
 */
@ApiModel(description = "A Loading in the system")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-08T23:11:35.582723400+01:00[Africa/Douala]")
public class LoadingDto   {
  @JsonProperty("loadCode")
  private String loadCode;

  @JsonProperty("loadDate")
  private String loadDate;

  @JsonProperty("loadExpectedamount")
  private Double loadExpectedamount;

  @JsonProperty("loadPaidamount")
  private Double loadPaidamount;

  @JsonProperty("loadRemise")
  private Double loadRemise;

  @JsonProperty("loadReport")
  private String loadReport;

  @JsonProperty("loadManager")
  private UserbmDto loadManager;

  @JsonProperty("loadSaler")
  private UserbmDto loadSaler;

  @JsonProperty("loadPos")
  private PointofsaleDto loadPos;

  public LoadingDto loadCode(String loadCode) {
    this.loadCode = loadCode;
    return this;
  }

  /**
   * Get loadCode
   * @return loadCode
  */
  @ApiModelProperty(value = "")


  public String getLoadCode() {
    return loadCode;
  }

  public void setLoadCode(String loadCode) {
    this.loadCode = loadCode;
  }

  public LoadingDto loadDate(String loadDate) {
    this.loadDate = loadDate;
    return this;
  }

  /**
   * Get loadDate
   * @return loadDate
  */
  @ApiModelProperty(value = "")


  public String getLoadDate() {
    return loadDate;
  }

  public void setLoadDate(String loadDate) {
    this.loadDate = loadDate;
  }

  public LoadingDto loadExpectedamount(Double loadExpectedamount) {
    this.loadExpectedamount = loadExpectedamount;
    return this;
  }

  /**
   * Get loadExpectedamount
   * @return loadExpectedamount
  */
  @ApiModelProperty(value = "")


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
   * @return loadPaidamount
  */
  @ApiModelProperty(value = "")


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
   * @return loadRemise
  */
  @ApiModelProperty(value = "")


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

  public LoadingDto loadManager(UserbmDto loadManager) {
    this.loadManager = loadManager;
    return this;
  }

  /**
   * Get loadManager
   * @return loadManager
  */
  @ApiModelProperty(value = "")

  @Valid

  public UserbmDto getLoadManager() {
    return loadManager;
  }

  public void setLoadManager(UserbmDto loadManager) {
    this.loadManager = loadManager;
  }

  public LoadingDto loadSaler(UserbmDto loadSaler) {
    this.loadSaler = loadSaler;
    return this;
  }

  /**
   * Get loadSaler
   * @return loadSaler
  */
  @ApiModelProperty(value = "")

  @Valid

  public UserbmDto getLoadSaler() {
    return loadSaler;
  }

  public void setLoadSaler(UserbmDto loadSaler) {
    this.loadSaler = loadSaler;
  }

  public LoadingDto loadPos(PointofsaleDto loadPos) {
    this.loadPos = loadPos;
    return this;
  }

  /**
   * Get loadPos
   * @return loadPos
  */
  @ApiModelProperty(value = "")

  @Valid

  public PointofsaleDto getLoadPos() {
    return loadPos;
  }

  public void setLoadPos(PointofsaleDto loadPos) {
    this.loadPos = loadPos;
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
    return Objects.equals(this.loadCode, loadingDto.loadCode) &&
        Objects.equals(this.loadDate, loadingDto.loadDate) &&
        Objects.equals(this.loadExpectedamount, loadingDto.loadExpectedamount) &&
        Objects.equals(this.loadPaidamount, loadingDto.loadPaidamount) &&
        Objects.equals(this.loadRemise, loadingDto.loadRemise) &&
        Objects.equals(this.loadReport, loadingDto.loadReport) &&
        Objects.equals(this.loadManager, loadingDto.loadManager) &&
        Objects.equals(this.loadSaler, loadingDto.loadSaler) &&
        Objects.equals(this.loadPos, loadingDto.loadPos);
  }

  @Override
  public int hashCode() {
    return Objects.hash(loadCode, loadDate, loadExpectedamount, loadPaidamount, loadRemise, loadReport, loadManager, loadSaler, loadPos);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LoadingDto {\n");
    
    sb.append("    loadCode: ").append(toIndentedString(loadCode)).append("\n");
    sb.append("    loadDate: ").append(toIndentedString(loadDate)).append("\n");
    sb.append("    loadExpectedamount: ").append(toIndentedString(loadExpectedamount)).append("\n");
    sb.append("    loadPaidamount: ").append(toIndentedString(loadPaidamount)).append("\n");
    sb.append("    loadRemise: ").append(toIndentedString(loadRemise)).append("\n");
    sb.append("    loadReport: ").append(toIndentedString(loadReport)).append("\n");
    sb.append("    loadManager: ").append(toIndentedString(loadManager)).append("\n");
    sb.append("    loadSaler: ").append(toIndentedString(loadSaler)).append("\n");
    sb.append("    loadPos: ").append(toIndentedString(loadPos)).append("\n");
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
