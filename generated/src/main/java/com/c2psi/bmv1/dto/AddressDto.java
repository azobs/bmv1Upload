package com.c2psi.bmv1.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Address of a user, an enterprise or a shop
 */
@ApiModel(description = "Address of a user, an enterprise or a shop")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-08T23:11:35.582723400+01:00[Africa/Douala]")
public class AddressDto   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("email")
  private String email;

  @JsonProperty("numtel1")
  private String numtel1;

  @JsonProperty("numtel2")
  private String numtel2;

  @JsonProperty("numtel3")
  private String numtel3;

  @JsonProperty("quarter")
  private String quarter;

  @JsonProperty("town")
  private String town;

  @JsonProperty("country")
  private String country;

  @JsonProperty("localisation")
  private String localisation;

  public AddressDto id(Long id) {
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

  public AddressDto email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
  */
  @ApiModelProperty(example = "abc@gmail.com", required = true, value = "")
  @NotNull

@Size(min = 5, max = 30) 
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public AddressDto numtel1(String numtel1) {
    this.numtel1 = numtel1;
    return this;
  }

  /**
   * Get numtel1
   * @return numtel1
  */
  @ApiModelProperty(example = "678470262", required = true, value = "")
  @NotNull

@Size(min = 9, max = 13) 
  public String getNumtel1() {
    return numtel1;
  }

  public void setNumtel1(String numtel1) {
    this.numtel1 = numtel1;
  }

  public AddressDto numtel2(String numtel2) {
    this.numtel2 = numtel2;
    return this;
  }

  /**
   * Get numtel2
   * @return numtel2
  */
  @ApiModelProperty(value = "")

@Size(min = 9, max = 13) 
  public String getNumtel2() {
    return numtel2;
  }

  public void setNumtel2(String numtel2) {
    this.numtel2 = numtel2;
  }

  public AddressDto numtel3(String numtel3) {
    this.numtel3 = numtel3;
    return this;
  }

  /**
   * Get numtel3
   * @return numtel3
  */
  @ApiModelProperty(value = "")

@Size(min = 9, max = 13) 
  public String getNumtel3() {
    return numtel3;
  }

  public void setNumtel3(String numtel3) {
    this.numtel3 = numtel3;
  }

  public AddressDto quarter(String quarter) {
    this.quarter = quarter;
    return this;
  }

  /**
   * Get quarter
   * @return quarter
  */
  @ApiModelProperty(value = "")

@Size(min = 3, max = 25) 
  public String getQuarter() {
    return quarter;
  }

  public void setQuarter(String quarter) {
    this.quarter = quarter;
  }

  public AddressDto town(String town) {
    this.town = town;
    return this;
  }

  /**
   * Get town
   * @return town
  */
  @ApiModelProperty(value = "")

@Size(min = 3, max = 25) 
  public String getTown() {
    return town;
  }

  public void setTown(String town) {
    this.town = town;
  }

  public AddressDto country(String country) {
    this.country = country;
    return this;
  }

  /**
   * Get country
   * @return country
  */
  @ApiModelProperty(value = "")

@Size(min = 3, max = 25) 
  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public AddressDto localisation(String localisation) {
    this.localisation = localisation;
    return this;
  }

  /**
   * The path of the resource or the image that represent the localisatiuon plan
   * @return localisation
  */
  @ApiModelProperty(value = "The path of the resource or the image that represent the localisatiuon plan")

@Size(min = 1, max = 25) 
  public String getLocalisation() {
    return localisation;
  }

  public void setLocalisation(String localisation) {
    this.localisation = localisation;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AddressDto addressDto = (AddressDto) o;
    return Objects.equals(this.id, addressDto.id) &&
        Objects.equals(this.email, addressDto.email) &&
        Objects.equals(this.numtel1, addressDto.numtel1) &&
        Objects.equals(this.numtel2, addressDto.numtel2) &&
        Objects.equals(this.numtel3, addressDto.numtel3) &&
        Objects.equals(this.quarter, addressDto.quarter) &&
        Objects.equals(this.town, addressDto.town) &&
        Objects.equals(this.country, addressDto.country) &&
        Objects.equals(this.localisation, addressDto.localisation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, email, numtel1, numtel2, numtel3, quarter, town, country, localisation);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AddressDto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    numtel1: ").append(toIndentedString(numtel1)).append("\n");
    sb.append("    numtel2: ").append(toIndentedString(numtel2)).append("\n");
    sb.append("    numtel3: ").append(toIndentedString(numtel3)).append("\n");
    sb.append("    quarter: ").append(toIndentedString(quarter)).append("\n");
    sb.append("    town: ").append(toIndentedString(town)).append("\n");
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
    sb.append("    localisation: ").append(toIndentedString(localisation)).append("\n");
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

