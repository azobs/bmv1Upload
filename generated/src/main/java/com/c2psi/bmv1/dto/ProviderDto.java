package com.c2psi.bmv1.dto;

import java.util.Objects;
import com.c2psi.bmv1.dto.AddressDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * A provider in the system
 */
@ApiModel(description = "A provider in the system")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-26T01:24:15.865861+01:00[Africa/Casablanca]")
public class ProviderDto   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("providerName")
  private String providerName;

  @JsonProperty("providerAcronym")
  private String providerAcronym;

  @JsonProperty("providerDescription")
  private String providerDescription;

  @JsonProperty("providerBalance")
  private Double providerBalance;

  @JsonProperty("providerAddress")
  private AddressDto providerAddress;

  @JsonProperty("providerPosId")
  private Long providerPosId;

  public ProviderDto id(Long id) {
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

  public ProviderDto providerName(String providerName) {
    this.providerName = providerName;
    return this;
  }

  /**
   * Get providerName
   * @return providerName
  */
  @ApiModelProperty(example = "providerName", value = "")


  public String getProviderName() {
    return providerName;
  }

  public void setProviderName(String providerName) {
    this.providerName = providerName;
  }

  public ProviderDto providerAcronym(String providerAcronym) {
    this.providerAcronym = providerAcronym;
    return this;
  }

  /**
   * Get providerAcronym
   * @return providerAcronym
  */
  @ApiModelProperty(example = "PN", value = "")


  public String getProviderAcronym() {
    return providerAcronym;
  }

  public void setProviderAcronym(String providerAcronym) {
    this.providerAcronym = providerAcronym;
  }

  public ProviderDto providerDescription(String providerDescription) {
    this.providerDescription = providerDescription;
    return this;
  }

  /**
   * Get providerDescription
   * @return providerDescription
  */
  @ApiModelProperty(value = "")


  public String getProviderDescription() {
    return providerDescription;
  }

  public void setProviderDescription(String providerDescription) {
    this.providerDescription = providerDescription;
  }

  public ProviderDto providerBalance(Double providerBalance) {
    this.providerBalance = providerBalance;
    return this;
  }

  /**
   * Get providerBalance
   * @return providerBalance
  */
  @ApiModelProperty(example = "0", value = "")


  public Double getProviderBalance() {
    return providerBalance;
  }

  public void setProviderBalance(Double providerBalance) {
    this.providerBalance = providerBalance;
  }

  public ProviderDto providerAddress(AddressDto providerAddress) {
    this.providerAddress = providerAddress;
    return this;
  }

  /**
   * Get providerAddress
   * @return providerAddress
  */
  @ApiModelProperty(value = "")

  @Valid

  public AddressDto getProviderAddress() {
    return providerAddress;
  }

  public void setProviderAddress(AddressDto providerAddress) {
    this.providerAddress = providerAddress;
  }

  public ProviderDto providerPosId(Long providerPosId) {
    this.providerPosId = providerPosId;
    return this;
  }

  /**
   * Get providerPosId
   * @return providerPosId
  */
  @ApiModelProperty(value = "")


  public Long getProviderPosId() {
    return providerPosId;
  }

  public void setProviderPosId(Long providerPosId) {
    this.providerPosId = providerPosId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProviderDto providerDto = (ProviderDto) o;
    return Objects.equals(this.id, providerDto.id) &&
        Objects.equals(this.providerName, providerDto.providerName) &&
        Objects.equals(this.providerAcronym, providerDto.providerAcronym) &&
        Objects.equals(this.providerDescription, providerDto.providerDescription) &&
        Objects.equals(this.providerBalance, providerDto.providerBalance) &&
        Objects.equals(this.providerAddress, providerDto.providerAddress) &&
        Objects.equals(this.providerPosId, providerDto.providerPosId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, providerName, providerAcronym, providerDescription, providerBalance, providerAddress, providerPosId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProviderDto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    providerName: ").append(toIndentedString(providerName)).append("\n");
    sb.append("    providerAcronym: ").append(toIndentedString(providerAcronym)).append("\n");
    sb.append("    providerDescription: ").append(toIndentedString(providerDescription)).append("\n");
    sb.append("    providerBalance: ").append(toIndentedString(providerBalance)).append("\n");
    sb.append("    providerAddress: ").append(toIndentedString(providerAddress)).append("\n");
    sb.append("    providerPosId: ").append(toIndentedString(providerPosId)).append("\n");
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

