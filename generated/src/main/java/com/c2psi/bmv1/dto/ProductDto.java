package com.c2psi.bmv1.dto;

import java.util.Objects;
import com.c2psi.bmv1.dto.CategoryDto;
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
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-12T06:34:45.513039400+01:00[Africa/Douala]")
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

  @JsonProperty("prodCat")
  private CategoryDto prodCat;

  public ProductDto id(Long id) {
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

  public ProductDto prodCat(CategoryDto prodCat) {
    this.prodCat = prodCat;
    return this;
  }

  /**
   * Get prodCat
   * @return prodCat
  */
  @ApiModelProperty(value = "")

  @Valid

  public CategoryDto getProdCat() {
    return prodCat;
  }

  public void setProdCat(CategoryDto prodCat) {
    this.prodCat = prodCat;
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
        Objects.equals(this.prodCat, productDto.prodCat);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, prodName, prodCode, prodDescription, prodAlias, prodPerishable, prodCat);
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
    sb.append("    prodCat: ").append(toIndentedString(prodCat)).append("\n");
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

