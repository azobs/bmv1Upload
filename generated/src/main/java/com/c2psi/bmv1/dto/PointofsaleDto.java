package com.c2psi.bmv1.dto;

import java.util.Objects;
import com.c2psi.bmv1.dto.AddressDto;
import com.c2psi.bmv1.dto.CurrencyDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * A pointofsale object of the system
 */
@ApiModel(description = "A pointofsale object of the system")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-28T04:24:19.978343600+01:00[Africa/Casablanca]")
public class PointofsaleDto   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("posName")
  private String posName;

  @JsonProperty("posAcronym")
  private String posAcronym;

  @JsonProperty("posDescription")
  private String posDescription;

  @JsonProperty("posBalance")
  private Double posBalance;

  @JsonProperty("posEnterpriseId")
  private Long posEnterpriseId;

  @JsonProperty("posAddress")
  private AddressDto posAddress;

  @JsonProperty("posCurrency")
  private CurrencyDto posCurrency;

  public PointofsaleDto id(Long id) {
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

  public PointofsaleDto posName(String posName) {
    this.posName = posName;
    return this;
  }

  /**
   * Get posName
   * @return posName
  */
  @ApiModelProperty(example = "posName", value = "")


  public String getPosName() {
    return posName;
  }

  public void setPosName(String posName) {
    this.posName = posName;
  }

  public PointofsaleDto posAcronym(String posAcronym) {
    this.posAcronym = posAcronym;
    return this;
  }

  /**
   * Get posAcronym
   * @return posAcronym
  */
  @ApiModelProperty(value = "")


  public String getPosAcronym() {
    return posAcronym;
  }

  public void setPosAcronym(String posAcronym) {
    this.posAcronym = posAcronym;
  }

  public PointofsaleDto posDescription(String posDescription) {
    this.posDescription = posDescription;
    return this;
  }

  /**
   * Get posDescription
   * @return posDescription
  */
  @ApiModelProperty(value = "")


  public String getPosDescription() {
    return posDescription;
  }

  public void setPosDescription(String posDescription) {
    this.posDescription = posDescription;
  }

  public PointofsaleDto posBalance(Double posBalance) {
    this.posBalance = posBalance;
    return this;
  }

  /**
   * Get posBalance
   * @return posBalance
  */
  @ApiModelProperty(value = "")


  public Double getPosBalance() {
    return posBalance;
  }

  public void setPosBalance(Double posBalance) {
    this.posBalance = posBalance;
  }

  public PointofsaleDto posEnterpriseId(Long posEnterpriseId) {
    this.posEnterpriseId = posEnterpriseId;
    return this;
  }

  /**
   * Get posEnterpriseId
   * @return posEnterpriseId
  */
  @ApiModelProperty(value = "")


  public Long getPosEnterpriseId() {
    return posEnterpriseId;
  }

  public void setPosEnterpriseId(Long posEnterpriseId) {
    this.posEnterpriseId = posEnterpriseId;
  }

  public PointofsaleDto posAddress(AddressDto posAddress) {
    this.posAddress = posAddress;
    return this;
  }

  /**
   * Get posAddress
   * @return posAddress
  */
  @ApiModelProperty(value = "")

  @Valid

  public AddressDto getPosAddress() {
    return posAddress;
  }

  public void setPosAddress(AddressDto posAddress) {
    this.posAddress = posAddress;
  }

  public PointofsaleDto posCurrency(CurrencyDto posCurrency) {
    this.posCurrency = posCurrency;
    return this;
  }

  /**
   * Get posCurrency
   * @return posCurrency
  */
  @ApiModelProperty(value = "")

  @Valid

  public CurrencyDto getPosCurrency() {
    return posCurrency;
  }

  public void setPosCurrency(CurrencyDto posCurrency) {
    this.posCurrency = posCurrency;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PointofsaleDto pointofsaleDto = (PointofsaleDto) o;
    return Objects.equals(this.id, pointofsaleDto.id) &&
        Objects.equals(this.posName, pointofsaleDto.posName) &&
        Objects.equals(this.posAcronym, pointofsaleDto.posAcronym) &&
        Objects.equals(this.posDescription, pointofsaleDto.posDescription) &&
        Objects.equals(this.posBalance, pointofsaleDto.posBalance) &&
        Objects.equals(this.posEnterpriseId, pointofsaleDto.posEnterpriseId) &&
        Objects.equals(this.posAddress, pointofsaleDto.posAddress) &&
        Objects.equals(this.posCurrency, pointofsaleDto.posCurrency);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, posName, posAcronym, posDescription, posBalance, posEnterpriseId, posAddress, posCurrency);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PointofsaleDto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    posName: ").append(toIndentedString(posName)).append("\n");
    sb.append("    posAcronym: ").append(toIndentedString(posAcronym)).append("\n");
    sb.append("    posDescription: ").append(toIndentedString(posDescription)).append("\n");
    sb.append("    posBalance: ").append(toIndentedString(posBalance)).append("\n");
    sb.append("    posEnterpriseId: ").append(toIndentedString(posEnterpriseId)).append("\n");
    sb.append("    posAddress: ").append(toIndentedString(posAddress)).append("\n");
    sb.append("    posCurrency: ").append(toIndentedString(posCurrency)).append("\n");
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

