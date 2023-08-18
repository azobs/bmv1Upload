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
 * A article in the system
 */
@ApiModel(description = "A article in the system")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-18T07:37:22.558276100+01:00[Africa/Casablanca]")
public class ArticleDto   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("artCode")
  private String artCode;

  @JsonProperty("artName")
  private String artName;

  @JsonProperty("artShortname")
  private String artShortname;

  @JsonProperty("artDescription")
  private String artDescription;

  @JsonProperty("artThreshold")
  private Integer artThreshold;

  @JsonProperty("artLowlimitwholesale")
  private Integer artLowlimitwholesale;

  @JsonProperty("artlowlimitSemiwholesale")
  private Integer artlowlimitSemiwholesale;

  @JsonProperty("artQuantityinstock")
  private Integer artQuantityinstock;

  @JsonProperty("artPfId")
  private Long artPfId;

  @JsonProperty("artUnitId")
  private Long artUnitId;

  @JsonProperty("artBasepriceId")
  private Long artBasepriceId;

  @JsonProperty("artPosId")
  private Long artPosId;

  public ArticleDto id(Long id) {
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

  public ArticleDto artCode(String artCode) {
    this.artCode = artCode;
    return this;
  }

  /**
   * Get artCode
   * @return artCode
  */
  @ApiModelProperty(example = "A0000", value = "")


  public String getArtCode() {
    return artCode;
  }

  public void setArtCode(String artCode) {
    this.artCode = artCode;
  }

  public ArticleDto artName(String artName) {
    this.artName = artName;
    return this;
  }

  /**
   * Get artName
   * @return artName
  */
  @ApiModelProperty(example = "artName", value = "")


  public String getArtName() {
    return artName;
  }

  public void setArtName(String artName) {
    this.artName = artName;
  }

  public ArticleDto artShortname(String artShortname) {
    this.artShortname = artShortname;
    return this;
  }

  /**
   * Get artShortname
   * @return artShortname
  */
  @ApiModelProperty(value = "")


  public String getArtShortname() {
    return artShortname;
  }

  public void setArtShortname(String artShortname) {
    this.artShortname = artShortname;
  }

  public ArticleDto artDescription(String artDescription) {
    this.artDescription = artDescription;
    return this;
  }

  /**
   * Get artDescription
   * @return artDescription
  */
  @ApiModelProperty(value = "")


  public String getArtDescription() {
    return artDescription;
  }

  public void setArtDescription(String artDescription) {
    this.artDescription = artDescription;
  }

  public ArticleDto artThreshold(Integer artThreshold) {
    this.artThreshold = artThreshold;
    return this;
  }

  /**
   * Get artThreshold
   * @return artThreshold
  */
  @ApiModelProperty(example = "0", value = "")


  public Integer getArtThreshold() {
    return artThreshold;
  }

  public void setArtThreshold(Integer artThreshold) {
    this.artThreshold = artThreshold;
  }

  public ArticleDto artLowlimitwholesale(Integer artLowlimitwholesale) {
    this.artLowlimitwholesale = artLowlimitwholesale;
    return this;
  }

  /**
   * Get artLowlimitwholesale
   * @return artLowlimitwholesale
  */
  @ApiModelProperty(example = "30", value = "")


  public Integer getArtLowlimitwholesale() {
    return artLowlimitwholesale;
  }

  public void setArtLowlimitwholesale(Integer artLowlimitwholesale) {
    this.artLowlimitwholesale = artLowlimitwholesale;
  }

  public ArticleDto artlowlimitSemiwholesale(Integer artlowlimitSemiwholesale) {
    this.artlowlimitSemiwholesale = artlowlimitSemiwholesale;
    return this;
  }

  /**
   * Get artlowlimitSemiwholesale
   * @return artlowlimitSemiwholesale
  */
  @ApiModelProperty(example = "25", value = "")


  public Integer getArtlowlimitSemiwholesale() {
    return artlowlimitSemiwholesale;
  }

  public void setArtlowlimitSemiwholesale(Integer artlowlimitSemiwholesale) {
    this.artlowlimitSemiwholesale = artlowlimitSemiwholesale;
  }

  public ArticleDto artQuantityinstock(Integer artQuantityinstock) {
    this.artQuantityinstock = artQuantityinstock;
    return this;
  }

  /**
   * Get artQuantityinstock
   * @return artQuantityinstock
  */
  @ApiModelProperty(example = "0", value = "")


  public Integer getArtQuantityinstock() {
    return artQuantityinstock;
  }

  public void setArtQuantityinstock(Integer artQuantityinstock) {
    this.artQuantityinstock = artQuantityinstock;
  }

  public ArticleDto artPfId(Long artPfId) {
    this.artPfId = artPfId;
    return this;
  }

  /**
   * Get artPfId
   * @return artPfId
  */
  @ApiModelProperty(value = "")


  public Long getArtPfId() {
    return artPfId;
  }

  public void setArtPfId(Long artPfId) {
    this.artPfId = artPfId;
  }

  public ArticleDto artUnitId(Long artUnitId) {
    this.artUnitId = artUnitId;
    return this;
  }

  /**
   * Get artUnitId
   * @return artUnitId
  */
  @ApiModelProperty(value = "")


  public Long getArtUnitId() {
    return artUnitId;
  }

  public void setArtUnitId(Long artUnitId) {
    this.artUnitId = artUnitId;
  }

  public ArticleDto artBasepriceId(Long artBasepriceId) {
    this.artBasepriceId = artBasepriceId;
    return this;
  }

  /**
   * Get artBasepriceId
   * @return artBasepriceId
  */
  @ApiModelProperty(value = "")


  public Long getArtBasepriceId() {
    return artBasepriceId;
  }

  public void setArtBasepriceId(Long artBasepriceId) {
    this.artBasepriceId = artBasepriceId;
  }

  public ArticleDto artPosId(Long artPosId) {
    this.artPosId = artPosId;
    return this;
  }

  /**
   * Get artPosId
   * @return artPosId
  */
  @ApiModelProperty(value = "")


  public Long getArtPosId() {
    return artPosId;
  }

  public void setArtPosId(Long artPosId) {
    this.artPosId = artPosId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ArticleDto articleDto = (ArticleDto) o;
    return Objects.equals(this.id, articleDto.id) &&
        Objects.equals(this.artCode, articleDto.artCode) &&
        Objects.equals(this.artName, articleDto.artName) &&
        Objects.equals(this.artShortname, articleDto.artShortname) &&
        Objects.equals(this.artDescription, articleDto.artDescription) &&
        Objects.equals(this.artThreshold, articleDto.artThreshold) &&
        Objects.equals(this.artLowlimitwholesale, articleDto.artLowlimitwholesale) &&
        Objects.equals(this.artlowlimitSemiwholesale, articleDto.artlowlimitSemiwholesale) &&
        Objects.equals(this.artQuantityinstock, articleDto.artQuantityinstock) &&
        Objects.equals(this.artPfId, articleDto.artPfId) &&
        Objects.equals(this.artUnitId, articleDto.artUnitId) &&
        Objects.equals(this.artBasepriceId, articleDto.artBasepriceId) &&
        Objects.equals(this.artPosId, articleDto.artPosId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, artCode, artName, artShortname, artDescription, artThreshold, artLowlimitwholesale, artlowlimitSemiwholesale, artQuantityinstock, artPfId, artUnitId, artBasepriceId, artPosId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ArticleDto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    artCode: ").append(toIndentedString(artCode)).append("\n");
    sb.append("    artName: ").append(toIndentedString(artName)).append("\n");
    sb.append("    artShortname: ").append(toIndentedString(artShortname)).append("\n");
    sb.append("    artDescription: ").append(toIndentedString(artDescription)).append("\n");
    sb.append("    artThreshold: ").append(toIndentedString(artThreshold)).append("\n");
    sb.append("    artLowlimitwholesale: ").append(toIndentedString(artLowlimitwholesale)).append("\n");
    sb.append("    artlowlimitSemiwholesale: ").append(toIndentedString(artlowlimitSemiwholesale)).append("\n");
    sb.append("    artQuantityinstock: ").append(toIndentedString(artQuantityinstock)).append("\n");
    sb.append("    artPfId: ").append(toIndentedString(artPfId)).append("\n");
    sb.append("    artUnitId: ").append(toIndentedString(artUnitId)).append("\n");
    sb.append("    artBasepriceId: ").append(toIndentedString(artBasepriceId)).append("\n");
    sb.append("    artPosId: ").append(toIndentedString(artPosId)).append("\n");
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

