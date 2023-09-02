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
 * A conversion rule used to convert one currency in another
 */
@ApiModel(description = "A conversion rule used to convert one currency in another")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-01T16:05:37.277942500+01:00[Africa/Casablanca]")
public class CurrencyconversionDto   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("conversionFactor")
  private Double conversionFactor;

  @JsonProperty("currencySourceId")
  private Long currencySourceId;

  @JsonProperty("currencyDestinationId")
  private Long currencyDestinationId;

  public CurrencyconversionDto id(Long id) {
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

  public CurrencyconversionDto currencySourceId(Long currencySourceId) {
    this.currencySourceId = currencySourceId;
    return this;
  }

  /**
   * Get currencySourceId
   * @return currencySourceId
  */
  @ApiModelProperty(value = "")


  public Long getCurrencySourceId() {
    return currencySourceId;
  }

  public void setCurrencySourceId(Long currencySourceId) {
    this.currencySourceId = currencySourceId;
  }

  public CurrencyconversionDto currencyDestinationId(Long currencyDestinationId) {
    this.currencyDestinationId = currencyDestinationId;
    return this;
  }

  /**
   * Get currencyDestinationId
   * @return currencyDestinationId
  */
  @ApiModelProperty(value = "")


  public Long getCurrencyDestinationId() {
    return currencyDestinationId;
  }

  public void setCurrencyDestinationId(Long currencyDestinationId) {
    this.currencyDestinationId = currencyDestinationId;
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
    return Objects.equals(this.id, currencyconversionDto.id) &&
        Objects.equals(this.conversionFactor, currencyconversionDto.conversionFactor) &&
        Objects.equals(this.currencySourceId, currencyconversionDto.currencySourceId) &&
        Objects.equals(this.currencyDestinationId, currencyconversionDto.currencyDestinationId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, conversionFactor, currencySourceId, currencyDestinationId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CurrencyconversionDto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    conversionFactor: ").append(toIndentedString(conversionFactor)).append("\n");
    sb.append("    currencySourceId: ").append(toIndentedString(currencySourceId)).append("\n");
    sb.append("    currencyDestinationId: ").append(toIndentedString(currencyDestinationId)).append("\n");
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

