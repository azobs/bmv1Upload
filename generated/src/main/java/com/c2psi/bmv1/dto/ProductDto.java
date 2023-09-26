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
 * A product in the system
 */
@ApiModel(description = "A product in the system")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-26T01:24:15.865861+01:00[Africa/Casablanca]")
public class ProductDto   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("prodName")
  private String prodName;

  @JsonProperty("prodCode")
  private String prodCode;

  @JsonProperty("prodDescription")
  private String prodDescription;

  @JsonProperty("prodAlias")
  private String prodAlias;

  @JsonProperty("prodPerishable")
  private Boolean prodPerishable;

  @JsonProperty("prodCatId")
  private Long prodCatId;

  public ProductDto id(Long id) {
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

  public ProductDto prodName(String prodName) {
    this.prodName = prodName;
    return this;
  }

  /**
   * Get prodName
   * @return prodName
  */
  @ApiModelProperty(example = "prodName", value = "")


  public String getProdName() {
    return prodName;
  }

  public void setProdName(String prodName) {
    this.prodName = prodName;
  }

  public ProductDto prodCode(String prodCode) {
    this.prodCode = prodCode;
    return this;
  }

  /**
   * Get prodCode
   * @return prodCode
  */
  @ApiModelProperty(example = "P0000", value = "")


  public String getProdCode() {
    return prodCode;
  }

  public void setProdCode(String prodCode) {
    this.prodCode = prodCode;
  }

  public ProductDto prodDescription(String prodDescription) {
    this.prodDescription = prodDescription;
    return this;
  }

  /**
   * Get prodDescription
   * @return prodDescription
  */
  @ApiModelProperty(value = "")


  public String getProdDescription() {
    return prodDescription;
  }

  public void setProdDescription(String prodDescription) {
    this.prodDescription = prodDescription;
  }

  public ProductDto prodAlias(String prodAlias) {
    this.prodAlias = prodAlias;
    return this;
  }

  /**
   * Get prodAlias
   * @return prodAlias
  */
  @ApiModelProperty(value = "")


  public String getProdAlias() {
    return prodAlias;
  }

  public void setProdAlias(String prodAlias) {
    this.prodAlias = prodAlias;
  }

  public ProductDto prodPerishable(Boolean prodPerishable) {
    this.prodPerishable = prodPerishable;
    return this;
  }

  /**
   * Get prodPerishable
   * @return prodPerishable
  */
  @ApiModelProperty(example = "false", value = "")


  public Boolean getProdPerishable() {
    return prodPerishable;
  }

  public void setProdPerishable(Boolean prodPerishable) {
    this.prodPerishable = prodPerishable;
  }

  public ProductDto prodCatId(Long prodCatId) {
    this.prodCatId = prodCatId;
    return this;
  }

  /**
   * Get prodCatId
   * @return prodCatId
  */
  @ApiModelProperty(value = "")


  public Long getProdCatId() {
    return prodCatId;
  }

  public void setProdCatId(Long prodCatId) {
    this.prodCatId = prodCatId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductDto productDto = (ProductDto) o;
    return Objects.equals(this.id, productDto.id) &&
        Objects.equals(this.prodName, productDto.prodName) &&
        Objects.equals(this.prodCode, productDto.prodCode) &&
        Objects.equals(this.prodDescription, productDto.prodDescription) &&
        Objects.equals(this.prodAlias, productDto.prodAlias) &&
        Objects.equals(this.prodPerishable, productDto.prodPerishable) &&
        Objects.equals(this.prodCatId, productDto.prodCatId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, prodName, prodCode, prodDescription, prodAlias, prodPerishable, prodCatId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductDto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    prodName: ").append(toIndentedString(prodName)).append("\n");
    sb.append("    prodCode: ").append(toIndentedString(prodCode)).append("\n");
    sb.append("    prodDescription: ").append(toIndentedString(prodDescription)).append("\n");
    sb.append("    prodAlias: ").append(toIndentedString(prodAlias)).append("\n");
    sb.append("    prodPerishable: ").append(toIndentedString(prodPerishable)).append("\n");
    sb.append("    prodCatId: ").append(toIndentedString(prodCatId)).append("\n");
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

