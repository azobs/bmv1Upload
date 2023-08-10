package com.c2psi.bmv1.dto;

import java.util.Objects;
import com.c2psi.bmv1.dto.AddressDto;
import com.c2psi.bmv1.dto.PointofsaleDto;
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
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-10T08:08:31.170887700+01:00[Africa/Douala]")
public class ProviderDto   {
  @JsonProperty("providerName")
  private String providerName;

  @JsonProperty("providerAcronym")
  private String providerAcronym;

  @JsonProperty("providerDescription")
  private String providerDescription;

  @JsonProperty("providerBalance")
  private Integer providerBalance;

  @JsonProperty("providerAddress")
  private AddressDto providerAddress;

  @JsonProperty("providerPos")
  private PointofsaleDto providerPos;

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

  public ProviderDto providerBalance(Integer providerBalance) {
    this.providerBalance = providerBalance;
    return this;
  }

  /**
   * Get providerBalance
   * @return providerBalance
  */
  @ApiModelProperty(example = "0", value = "")


  public Integer getProviderBalance() {
    return providerBalance;
  }

  public void setProviderBalance(Integer providerBalance) {
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

  public ProviderDto providerPos(PointofsaleDto providerPos) {
    this.providerPos = providerPos;
    return this;
  }

  /**
   * Get providerPos
   * @return providerPos
  */
  @ApiModelProperty(value = "")

  @Valid

  public PointofsaleDto getProviderPos() {
    return providerPos;
  }

  public void setProviderPos(PointofsaleDto providerPos) {
    this.providerPos = providerPos;
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
    return Objects.equals(this.providerName, providerDto.providerName) &&
        Objects.equals(this.providerAcronym, providerDto.providerAcronym) &&
        Objects.equals(this.providerDescription, providerDto.providerDescription) &&
        Objects.equals(this.providerBalance, providerDto.providerBalance) &&
        Objects.equals(this.providerAddress, providerDto.providerAddress) &&
        Objects.equals(this.providerPos, providerDto.providerPos);
  }

  @Override
  public int hashCode() {
    return Objects.hash(providerName, providerAcronym, providerDescription, providerBalance, providerAddress, providerPos);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProviderDto {\n");
    
    sb.append("    providerName: ").append(toIndentedString(providerName)).append("\n");
    sb.append("    providerAcronym: ").append(toIndentedString(providerAcronym)).append("\n");
    sb.append("    providerDescription: ").append(toIndentedString(providerDescription)).append("\n");
    sb.append("    providerBalance: ").append(toIndentedString(providerBalance)).append("\n");
    sb.append("    providerAddress: ").append(toIndentedString(providerAddress)).append("\n");
    sb.append("    providerPos: ").append(toIndentedString(providerPos)).append("\n");
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

