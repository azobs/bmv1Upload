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
 * A product formated in the system
 */
@ApiModel(description = "A product formated in the system")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-08T23:11:35.582723400+01:00[Africa/Douala]")
public class BasepriceDto   {
  @JsonProperty("bpPurchaseprice")
  private Double bpPurchaseprice;

  @JsonProperty("bpWholeprice")
  private Double bpWholeprice;

  @JsonProperty("bpSemiwholeprice")
  private Double bpSemiwholeprice;

  @JsonProperty("bpDetailsprice")
  private Double bpDetailsprice;

  @JsonProperty("bpPrecompte")
  private Double bpPrecompte;

  @JsonProperty("bpRistourne")
  private Double bpRistourne;

  @JsonProperty("bpCurrency")
  private CurrencyDto bpCurrency;

  public BasepriceDto bpPurchaseprice(Double bpPurchaseprice) {
    this.bpPurchaseprice = bpPurchaseprice;
    return this;
  }

  /**
   * Get bpPurchaseprice
   * @return bpPurchaseprice
  */
  @ApiModelProperty(value = "")


  public Double getBpPurchaseprice() {
    return bpPurchaseprice;
  }

  public void setBpPurchaseprice(Double bpPurchaseprice) {
    this.bpPurchaseprice = bpPurchaseprice;
  }

  public BasepriceDto bpWholeprice(Double bpWholeprice) {
    this.bpWholeprice = bpWholeprice;
    return this;
  }

  /**
   * Get bpWholeprice
   * @return bpWholeprice
  */
  @ApiModelProperty(value = "")


  public Double getBpWholeprice() {
    return bpWholeprice;
  }

  public void setBpWholeprice(Double bpWholeprice) {
    this.bpWholeprice = bpWholeprice;
  }

  public BasepriceDto bpSemiwholeprice(Double bpSemiwholeprice) {
    this.bpSemiwholeprice = bpSemiwholeprice;
    return this;
  }

  /**
   * Get bpSemiwholeprice
   * @return bpSemiwholeprice
  */
  @ApiModelProperty(value = "")


  public Double getBpSemiwholeprice() {
    return bpSemiwholeprice;
  }

  public void setBpSemiwholeprice(Double bpSemiwholeprice) {
    this.bpSemiwholeprice = bpSemiwholeprice;
  }

  public BasepriceDto bpDetailsprice(Double bpDetailsprice) {
    this.bpDetailsprice = bpDetailsprice;
    return this;
  }

  /**
   * Get bpDetailsprice
   * @return bpDetailsprice
  */
  @ApiModelProperty(value = "")


  public Double getBpDetailsprice() {
    return bpDetailsprice;
  }

  public void setBpDetailsprice(Double bpDetailsprice) {
    this.bpDetailsprice = bpDetailsprice;
  }

  public BasepriceDto bpPrecompte(Double bpPrecompte) {
    this.bpPrecompte = bpPrecompte;
    return this;
  }

  /**
   * Get bpPrecompte
   * @return bpPrecompte
  */
  @ApiModelProperty(value = "")


  public Double getBpPrecompte() {
    return bpPrecompte;
  }

  public void setBpPrecompte(Double bpPrecompte) {
    this.bpPrecompte = bpPrecompte;
  }

  public BasepriceDto bpRistourne(Double bpRistourne) {
    this.bpRistourne = bpRistourne;
    return this;
  }

  /**
   * Get bpRistourne
   * @return bpRistourne
  */
  @ApiModelProperty(value = "")


  public Double getBpRistourne() {
    return bpRistourne;
  }

  public void setBpRistourne(Double bpRistourne) {
    this.bpRistourne = bpRistourne;
  }

  public BasepriceDto bpCurrency(CurrencyDto bpCurrency) {
    this.bpCurrency = bpCurrency;
    return this;
  }

  /**
   * Get bpCurrency
   * @return bpCurrency
  */
  @ApiModelProperty(value = "")

  @Valid

  public CurrencyDto getBpCurrency() {
    return bpCurrency;
  }

  public void setBpCurrency(CurrencyDto bpCurrency) {
    this.bpCurrency = bpCurrency;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BasepriceDto basepriceDto = (BasepriceDto) o;
    return Objects.equals(this.bpPurchaseprice, basepriceDto.bpPurchaseprice) &&
        Objects.equals(this.bpWholeprice, basepriceDto.bpWholeprice) &&
        Objects.equals(this.bpSemiwholeprice, basepriceDto.bpSemiwholeprice) &&
        Objects.equals(this.bpDetailsprice, basepriceDto.bpDetailsprice) &&
        Objects.equals(this.bpPrecompte, basepriceDto.bpPrecompte) &&
        Objects.equals(this.bpRistourne, basepriceDto.bpRistourne) &&
        Objects.equals(this.bpCurrency, basepriceDto.bpCurrency);
  }

  @Override
  public int hashCode() {
    return Objects.hash(bpPurchaseprice, bpWholeprice, bpSemiwholeprice, bpDetailsprice, bpPrecompte, bpRistourne, bpCurrency);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BasepriceDto {\n");
    
    sb.append("    bpPurchaseprice: ").append(toIndentedString(bpPurchaseprice)).append("\n");
    sb.append("    bpWholeprice: ").append(toIndentedString(bpWholeprice)).append("\n");
    sb.append("    bpSemiwholeprice: ").append(toIndentedString(bpSemiwholeprice)).append("\n");
    sb.append("    bpDetailsprice: ").append(toIndentedString(bpDetailsprice)).append("\n");
    sb.append("    bpPrecompte: ").append(toIndentedString(bpPrecompte)).append("\n");
    sb.append("    bpRistourne: ").append(toIndentedString(bpRistourne)).append("\n");
    sb.append("    bpCurrency: ").append(toIndentedString(bpCurrency)).append("\n");
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

