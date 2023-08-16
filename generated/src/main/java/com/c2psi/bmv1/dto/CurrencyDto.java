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
 * A currency used in the system
 */
@ApiModel(description = "A currency used in the system")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-16T08:50:41.193143300+01:00[Africa/Douala]")
public class CurrencyDto   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("currencyName")
  private String currencyName;

  @JsonProperty("currencyAbbreviation")
  private String currencyAbbreviation;

  public CurrencyDto id(Long id) {
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

  public CurrencyDto currencyName(String currencyName) {
    this.currencyName = currencyName;
    return this;
  }

  /**
   * Get currencyName
   * @return currencyName
  */
  @ApiModelProperty(example = "franc cfa", value = "")

@Size(max = 30) 
  public String getCurrencyName() {
    return currencyName;
  }

  public void setCurrencyName(String currencyName) {
    this.currencyName = currencyName;
  }

  public CurrencyDto currencyAbbreviation(String currencyAbbreviation) {
    this.currencyAbbreviation = currencyAbbreviation;
    return this;
  }

  /**
   * Get currencyAbbreviation
   * @return currencyAbbreviation
  */
  @ApiModelProperty(example = "F cfa", value = "")

@Size(max = 5) 
  public String getCurrencyAbbreviation() {
    return currencyAbbreviation;
  }

  public void setCurrencyAbbreviation(String currencyAbbreviation) {
    this.currencyAbbreviation = currencyAbbreviation;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CurrencyDto currencyDto = (CurrencyDto) o;
    return Objects.equals(this.id, currencyDto.id) &&
        Objects.equals(this.currencyName, currencyDto.currencyName) &&
        Objects.equals(this.currencyAbbreviation, currencyDto.currencyAbbreviation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, currencyName, currencyAbbreviation);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CurrencyDto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    currencyName: ").append(toIndentedString(currencyName)).append("\n");
    sb.append("    currencyAbbreviation: ").append(toIndentedString(currencyAbbreviation)).append("\n");
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

