package com.c2psi.bmv1.dto;

import java.util.Objects;
import com.c2psi.bmv1.dto.CurrencyDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * A conversion rule used to convert one currency in another
 */
@ApiModel(description = "A conversion rule used to convert one currency in another")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-10T08:08:31.170887700+01:00[Africa/Douala]")
public class CurrencyconversionDto   {
  @JsonProperty("conversionFactor")
  private Double conversionFactor;

  @JsonProperty("currencySource")
  private CurrencyDto currencySource;

  @JsonProperty("currencyDestination")
  private CurrencyDto currencyDestination;

  public CurrencyconversionDto conversionFactor(Double conversionFactor) {
    this.conversionFactor = conversionFactor;
    return this;
  }

  /**
   * Get conversionFactor
   * @return conversionFactor
  */
  @ApiModelProperty(value = "")


  public Double getConversionFactor() {
    return conversionFactor;
  }

  public void setConversionFactor(Double conversionFactor) {
    this.conversionFactor = conversionFactor;
  }

  public CurrencyconversionDto currencySource(CurrencyDto currencySource) {
    this.currencySource = currencySource;
    return this;
  }

  /**
   * Get currencySource
   * @return currencySource
  */
  @ApiModelProperty(value = "")

  @Valid

  public CurrencyDto getCurrencySource() {
    return currencySource;
  }

  public void setCurrencySource(CurrencyDto currencySource) {
    this.currencySource = currencySource;
  }

  public CurrencyconversionDto currencyDestination(CurrencyDto currencyDestination) {
    this.currencyDestination = currencyDestination;
    return this;
  }

  /**
   * Get currencyDestination
   * @return currencyDestination
  */
  @ApiModelProperty(value = "")

  @Valid

  public CurrencyDto getCurrencyDestination() {
    return currencyDestination;
  }

  public void setCurrencyDestination(CurrencyDto currencyDestination) {
    this.currencyDestination = currencyDestination;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CurrencyconversionDto currencyconversionDto = (CurrencyconversionDto) o;
    return Objects.equals(this.conversionFactor, currencyconversionDto.conversionFactor) &&
        Objects.equals(this.currencySource, currencyconversionDto.currencySource) &&
        Objects.equals(this.currencyDestination, currencyconversionDto.currencyDestination);
  }

  @Override
  public int hashCode() {
    return Objects.hash(conversionFactor, currencySource, currencyDestination);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CurrencyconversionDto {\n");
    
    sb.append("    conversionFactor: ").append(toIndentedString(conversionFactor)).append("\n");
    sb.append("    currencySource: ").append(toIndentedString(currencySource)).append("\n");
    sb.append("    currencyDestination: ").append(toIndentedString(currencyDestination)).append("\n");
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

