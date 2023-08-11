package com.c2psi.bmv1.dto;

import java.util.Objects;
import com.c2psi.bmv1.dto.AccountDto;
import com.c2psi.bmv1.dto.AddressDto;
import com.c2psi.bmv1.dto.CurrencyDto;
import com.c2psi.bmv1.dto.EnterpriseDto;
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
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-11T16:08:25.464702700+01:00[Africa/Douala]")
public class PointofsaleDto   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("posName")
  private String posName;

  @JsonProperty("posAcronym")
  private String posAcronym;

  @JsonProperty("posDescription")
  private String posDescription;

  @JsonProperty("posAddressDto")
  private AddressDto posAddressDto;

  @JsonProperty("posAccountDto")
  private AccountDto posAccountDto;

  @JsonProperty("posCurrency")
  private CurrencyDto posCurrency;

  @JsonProperty("posEnterprise")
  private EnterpriseDto posEnterprise;

  public PointofsaleDto id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @ApiModelProperty(readOnly = true, value = "")


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

  public PointofsaleDto posAddressDto(AddressDto posAddressDto) {
    this.posAddressDto = posAddressDto;
    return this;
  }

  /**
   * Get posAddressDto
   * @return posAddressDto
  */
  @ApiModelProperty(value = "")

  @Valid

  public AddressDto getPosAddressDto() {
    return posAddressDto;
  }

  public void setPosAddressDto(AddressDto posAddressDto) {
    this.posAddressDto = posAddressDto;
  }

  public PointofsaleDto posAccountDto(AccountDto posAccountDto) {
    this.posAccountDto = posAccountDto;
    return this;
  }

  /**
   * Get posAccountDto
   * @return posAccountDto
  */
  @ApiModelProperty(value = "")

  @Valid

  public AccountDto getPosAccountDto() {
    return posAccountDto;
  }

  public void setPosAccountDto(AccountDto posAccountDto) {
    this.posAccountDto = posAccountDto;
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

  public PointofsaleDto posEnterprise(EnterpriseDto posEnterprise) {
    this.posEnterprise = posEnterprise;
    return this;
  }

  /**
   * Get posEnterprise
   * @return posEnterprise
  */
  @ApiModelProperty(value = "")

  @Valid

  public EnterpriseDto getPosEnterprise() {
    return posEnterprise;
  }

  public void setPosEnterprise(EnterpriseDto posEnterprise) {
    this.posEnterprise = posEnterprise;
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
        Objects.equals(this.posAddressDto, pointofsaleDto.posAddressDto) &&
        Objects.equals(this.posAccountDto, pointofsaleDto.posAccountDto) &&
        Objects.equals(this.posCurrency, pointofsaleDto.posCurrency) &&
        Objects.equals(this.posEnterprise, pointofsaleDto.posEnterprise);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, posName, posAcronym, posDescription, posAddressDto, posAccountDto, posCurrency, posEnterprise);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PointofsaleDto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    posName: ").append(toIndentedString(posName)).append("\n");
    sb.append("    posAcronym: ").append(toIndentedString(posAcronym)).append("\n");
    sb.append("    posDescription: ").append(toIndentedString(posDescription)).append("\n");
    sb.append("    posAddressDto: ").append(toIndentedString(posAddressDto)).append("\n");
    sb.append("    posAccountDto: ").append(toIndentedString(posAccountDto)).append("\n");
    sb.append("    posCurrency: ").append(toIndentedString(posCurrency)).append("\n");
    sb.append("    posEnterprise: ").append(toIndentedString(posEnterprise)).append("\n");
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

