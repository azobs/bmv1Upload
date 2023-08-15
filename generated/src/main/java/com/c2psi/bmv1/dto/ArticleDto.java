package com.c2psi.bmv1.dto;

import java.util.Objects;
import com.c2psi.bmv1.dto.BasepriceDto;
import com.c2psi.bmv1.dto.PointofsaleDto;
import com.c2psi.bmv1.dto.ProductformatedDto;
import com.c2psi.bmv1.dto.UnitDto;
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
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-15T06:34:19.158834900+01:00[Africa/Douala]")
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

  @JsonProperty("artPf")
  private ProductformatedDto artPf;

  @JsonProperty("artUnit")
  private UnitDto artUnit;

  @JsonProperty("artBaseprice")
  private BasepriceDto artBaseprice;

  @JsonProperty("artPos")
  private PointofsaleDto artPos;

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

  public ArticleDto artPf(ProductformatedDto artPf) {
    this.artPf = artPf;
    return this;
  }

  /**
   * Get artPf
   * @return artPf
  */
  @ApiModelProperty(value = "")

  @Valid

  public ProductformatedDto getArtPf() {
    return artPf;
  }

  public void setArtPf(ProductformatedDto artPf) {
    this.artPf = artPf;
  }

  public ArticleDto artUnit(UnitDto artUnit) {
    this.artUnit = artUnit;
    return this;
  }

  /**
   * Get artUnit
   * @return artUnit
  */
  @ApiModelProperty(value = "")

  @Valid

  public UnitDto getArtUnit() {
    return artUnit;
  }

  public void setArtUnit(UnitDto artUnit) {
    this.artUnit = artUnit;
  }

  public ArticleDto artBaseprice(BasepriceDto artBaseprice) {
    this.artBaseprice = artBaseprice;
    return this;
  }

  /**
   * Get artBaseprice
   * @return artBaseprice
  */
  @ApiModelProperty(value = "")

  @Valid

  public BasepriceDto getArtBaseprice() {
    return artBaseprice;
  }

  public void setArtBaseprice(BasepriceDto artBaseprice) {
    this.artBaseprice = artBaseprice;
  }

  public ArticleDto artPos(PointofsaleDto artPos) {
    this.artPos = artPos;
    return this;
  }

  /**
   * Get artPos
   * @return artPos
  */
  @ApiModelProperty(value = "")

  @Valid

  public PointofsaleDto getArtPos() {
    return artPos;
  }

  public void setArtPos(PointofsaleDto artPos) {
    this.artPos = artPos;
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
        Objects.equals(this.artPf, articleDto.artPf) &&
        Objects.equals(this.artUnit, articleDto.artUnit) &&
        Objects.equals(this.artBaseprice, articleDto.artBaseprice) &&
        Objects.equals(this.artPos, articleDto.artPos);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, artCode, artName, artShortname, artDescription, artThreshold, artLowlimitwholesale, artlowlimitSemiwholesale, artQuantityinstock, artPf, artUnit, artBaseprice, artPos);
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
    sb.append("    artPf: ").append(toIndentedString(artPf)).append("\n");
    sb.append("    artUnit: ").append(toIndentedString(artUnit)).append("\n");
    sb.append("    artBaseprice: ").append(toIndentedString(artBaseprice)).append("\n");
    sb.append("    artPos: ").append(toIndentedString(artPos)).append("\n");
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

