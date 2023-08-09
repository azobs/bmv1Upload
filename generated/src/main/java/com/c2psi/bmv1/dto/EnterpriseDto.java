package com.c2psi.bmv1.dto;

import java.util.Objects;
import com.c2psi.bmv1.dto.UserbmDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * An enterprise which can contain multiple pointofsale all manage in the system
 */
@ApiModel(description = "An enterprise which can contain multiple pointofsale all manage in the system")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-08T23:11:35.582723400+01:00[Africa/Douala]")
public class EnterpriseDto   {
  @JsonProperty("entRegime")
  private String entRegime;

  @JsonProperty("entSocialreason")
  private String entSocialreason;

  @JsonProperty("entDescription")
  private String entDescription;

  @JsonProperty("entNiu")
  private String entNiu;

  @JsonProperty("entName")
  private String entName;

  @JsonProperty("entAcronym")
  private String entAcronym;

  @JsonProperty("entLogo")
  private String entLogo;

  @JsonProperty("entAdmin")
  private UserbmDto entAdmin;

  public EnterpriseDto entRegime(String entRegime) {
    this.entRegime = entRegime;
    return this;
  }

  /**
   * Get entRegime
   * @return entRegime
  */
  @ApiModelProperty(value = "")


  public String getEntRegime() {
    return entRegime;
  }

  public void setEntRegime(String entRegime) {
    this.entRegime = entRegime;
  }

  public EnterpriseDto entSocialreason(String entSocialreason) {
    this.entSocialreason = entSocialreason;
    return this;
  }

  /**
   * Get entSocialreason
   * @return entSocialreason
  */
  @ApiModelProperty(value = "")


  public String getEntSocialreason() {
    return entSocialreason;
  }

  public void setEntSocialreason(String entSocialreason) {
    this.entSocialreason = entSocialreason;
  }

  public EnterpriseDto entDescription(String entDescription) {
    this.entDescription = entDescription;
    return this;
  }

  /**
   * Get entDescription
   * @return entDescription
  */
  @ApiModelProperty(value = "")


  public String getEntDescription() {
    return entDescription;
  }

  public void setEntDescription(String entDescription) {
    this.entDescription = entDescription;
  }

  public EnterpriseDto entNiu(String entNiu) {
    this.entNiu = entNiu;
    return this;
  }

  /**
   * Get entNiu
   * @return entNiu
  */
  @ApiModelProperty(value = "")


  public String getEntNiu() {
    return entNiu;
  }

  public void setEntNiu(String entNiu) {
    this.entNiu = entNiu;
  }

  public EnterpriseDto entName(String entName) {
    this.entName = entName;
    return this;
  }

  /**
   * Get entName
   * @return entName
  */
  @ApiModelProperty(value = "")


  public String getEntName() {
    return entName;
  }

  public void setEntName(String entName) {
    this.entName = entName;
  }

  public EnterpriseDto entAcronym(String entAcronym) {
    this.entAcronym = entAcronym;
    return this;
  }

  /**
   * Get entAcronym
   * @return entAcronym
  */
  @ApiModelProperty(value = "")


  public String getEntAcronym() {
    return entAcronym;
  }

  public void setEntAcronym(String entAcronym) {
    this.entAcronym = entAcronym;
  }

  public EnterpriseDto entLogo(String entLogo) {
    this.entLogo = entLogo;
    return this;
  }

  /**
   * Get entLogo
   * @return entLogo
  */
  @ApiModelProperty(value = "")


  public String getEntLogo() {
    return entLogo;
  }

  public void setEntLogo(String entLogo) {
    this.entLogo = entLogo;
  }

  public EnterpriseDto entAdmin(UserbmDto entAdmin) {
    this.entAdmin = entAdmin;
    return this;
  }

  /**
   * Get entAdmin
   * @return entAdmin
  */
  @ApiModelProperty(value = "")

  @Valid

  public UserbmDto getEntAdmin() {
    return entAdmin;
  }

  public void setEntAdmin(UserbmDto entAdmin) {
    this.entAdmin = entAdmin;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EnterpriseDto enterpriseDto = (EnterpriseDto) o;
    return Objects.equals(this.entRegime, enterpriseDto.entRegime) &&
        Objects.equals(this.entSocialreason, enterpriseDto.entSocialreason) &&
        Objects.equals(this.entDescription, enterpriseDto.entDescription) &&
        Objects.equals(this.entNiu, enterpriseDto.entNiu) &&
        Objects.equals(this.entName, enterpriseDto.entName) &&
        Objects.equals(this.entAcronym, enterpriseDto.entAcronym) &&
        Objects.equals(this.entLogo, enterpriseDto.entLogo) &&
        Objects.equals(this.entAdmin, enterpriseDto.entAdmin);
  }

  @Override
  public int hashCode() {
    return Objects.hash(entRegime, entSocialreason, entDescription, entNiu, entName, entAcronym, entLogo, entAdmin);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EnterpriseDto {\n");
    
    sb.append("    entRegime: ").append(toIndentedString(entRegime)).append("\n");
    sb.append("    entSocialreason: ").append(toIndentedString(entSocialreason)).append("\n");
    sb.append("    entDescription: ").append(toIndentedString(entDescription)).append("\n");
    sb.append("    entNiu: ").append(toIndentedString(entNiu)).append("\n");
    sb.append("    entName: ").append(toIndentedString(entName)).append("\n");
    sb.append("    entAcronym: ").append(toIndentedString(entAcronym)).append("\n");
    sb.append("    entLogo: ").append(toIndentedString(entLogo)).append("\n");
    sb.append("    entAdmin: ").append(toIndentedString(entAdmin)).append("\n");
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

