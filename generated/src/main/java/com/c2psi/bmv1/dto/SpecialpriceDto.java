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
 * A Specialprice in the system
 */
@ApiModel(description = "A Specialprice in the system")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-26T01:24:15.865861+01:00[Africa/Casablanca]")
public class SpecialpriceDto   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("spCode")
  private String spCode;

  @JsonProperty("spWholeprice")
  private Double spWholeprice;

  @JsonProperty("spSemiwholeprice")
  private Double spSemiwholeprice;

  @JsonProperty("spDetailsprice")
  private Double spDetailsprice;

  @JsonProperty("spRistourne")
  private Double spRistourne;

  @JsonProperty("spPrecompte")
  private Double spPrecompte;

  @JsonProperty("spBasepriceId")
  private Long spBasepriceId;

  public SpecialpriceDto id(Long id) {
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

  public SpecialpriceDto spCode(String spCode) {
    this.spCode = spCode;
    return this;
  }

  /**
   * Get spCode
   * @return spCode
  */
  @ApiModelProperty(example = "SP0001", value = "")


  public String getSpCode() {
    return spCode;
  }

  public void setSpCode(String spCode) {
    this.spCode = spCode;
  }

  public SpecialpriceDto spWholeprice(Double spWholeprice) {
    this.spWholeprice = spWholeprice;
    return this;
  }

  /**
   * Get spWholeprice
   * minimum: 0
   * @return spWholeprice
  */
  @ApiModelProperty(value = "")

@DecimalMin(value = "0", inclusive = false) 
  public Double getSpWholeprice() {
    return spWholeprice;
  }

  public void setSpWholeprice(Double spWholeprice) {
    this.spWholeprice = spWholeprice;
  }

  public SpecialpriceDto spSemiwholeprice(Double spSemiwholeprice) {
    this.spSemiwholeprice = spSemiwholeprice;
    return this;
  }

  /**
   * Get spSemiwholeprice
   * minimum: 0
   * @return spSemiwholeprice
  */
  @ApiModelProperty(value = "")

@DecimalMin(value = "0", inclusive = false) 
  public Double getSpSemiwholeprice() {
    return spSemiwholeprice;
  }

  public void setSpSemiwholeprice(Double spSemiwholeprice) {
    this.spSemiwholeprice = spSemiwholeprice;
  }

  public SpecialpriceDto spDetailsprice(Double spDetailsprice) {
    this.spDetailsprice = spDetailsprice;
    return this;
  }

  /**
   * Get spDetailsprice
   * minimum: 0
   * @return spDetailsprice
  */
  @ApiModelProperty(value = "")

@DecimalMin(value = "0", inclusive = false) 
  public Double getSpDetailsprice() {
    return spDetailsprice;
  }

  public void setSpDetailsprice(Double spDetailsprice) {
    this.spDetailsprice = spDetailsprice;
  }

  public SpecialpriceDto spRistourne(Double spRistourne) {
    this.spRistourne = spRistourne;
    return this;
  }

  /**
   * Get spRistourne
   * @return spRistourne
  */
  @ApiModelProperty(example = "0", value = "")


  public Double getSpRistourne() {
    return spRistourne;
  }

  public void setSpRistourne(Double spRistourne) {
    this.spRistourne = spRistourne;
  }

  public SpecialpriceDto spPrecompte(Double spPrecompte) {
    this.spPrecompte = spPrecompte;
    return this;
  }

  /**
   * Get spPrecompte
   * @return spPrecompte
  */
  @ApiModelProperty(example = "0", value = "")


  public Double getSpPrecompte() {
    return spPrecompte;
  }

  public void setSpPrecompte(Double spPrecompte) {
    this.spPrecompte = spPrecompte;
  }

  public SpecialpriceDto spBasepriceId(Long spBasepriceId) {
    this.spBasepriceId = spBasepriceId;
    return this;
  }

  /**
   * Get spBasepriceId
   * @return spBasepriceId
  */
  @ApiModelProperty(value = "")


  public Long getSpBasepriceId() {
    return spBasepriceId;
  }

  public void setSpBasepriceId(Long spBasepriceId) {
    this.spBasepriceId = spBasepriceId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SpecialpriceDto specialpriceDto = (SpecialpriceDto) o;
    return Objects.equals(this.id, specialpriceDto.id) &&
        Objects.equals(this.spCode, specialpriceDto.spCode) &&
        Objects.equals(this.spWholeprice, specialpriceDto.spWholeprice) &&
        Objects.equals(this.spSemiwholeprice, specialpriceDto.spSemiwholeprice) &&
        Objects.equals(this.spDetailsprice, specialpriceDto.spDetailsprice) &&
        Objects.equals(this.spRistourne, specialpriceDto.spRistourne) &&
        Objects.equals(this.spPrecompte, specialpriceDto.spPrecompte) &&
        Objects.equals(this.spBasepriceId, specialpriceDto.spBasepriceId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, spCode, spWholeprice, spSemiwholeprice, spDetailsprice, spRistourne, spPrecompte, spBasepriceId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SpecialpriceDto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    spCode: ").append(toIndentedString(spCode)).append("\n");
    sb.append("    spWholeprice: ").append(toIndentedString(spWholeprice)).append("\n");
    sb.append("    spSemiwholeprice: ").append(toIndentedString(spSemiwholeprice)).append("\n");
    sb.append("    spDetailsprice: ").append(toIndentedString(spDetailsprice)).append("\n");
    sb.append("    spRistourne: ").append(toIndentedString(spRistourne)).append("\n");
    sb.append("    spPrecompte: ").append(toIndentedString(spPrecompte)).append("\n");
    sb.append("    spBasepriceId: ").append(toIndentedString(spBasepriceId)).append("\n");
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

