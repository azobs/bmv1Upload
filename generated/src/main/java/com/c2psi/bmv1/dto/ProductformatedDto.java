package com.c2psi.bmv1.dto;

import java.util.Objects;
import com.c2psi.bmv1.dto.FormatDto;
import com.c2psi.bmv1.dto.ProductDto;
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
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-10T08:08:31.170887700+01:00[Africa/Douala]")
public class ProductformatedDto   {
  @JsonProperty("pfPicture")
  private String pfPicture;

  @JsonProperty("pfProduct")
  private ProductDto pfProduct;

  @JsonProperty("pfFormat")
  private FormatDto pfFormat;

  public ProductformatedDto pfPicture(String pfPicture) {
    this.pfPicture = pfPicture;
    return this;
  }

  /**
   * Get pfPicture
   * @return pfPicture
  */
  @ApiModelProperty(value = "")


  public String getPfPicture() {
    return pfPicture;
  }

  public void setPfPicture(String pfPicture) {
    this.pfPicture = pfPicture;
  }

  public ProductformatedDto pfProduct(ProductDto pfProduct) {
    this.pfProduct = pfProduct;
    return this;
  }

  /**
   * Get pfProduct
   * @return pfProduct
  */
  @ApiModelProperty(value = "")

  @Valid

  public ProductDto getPfProduct() {
    return pfProduct;
  }

  public void setPfProduct(ProductDto pfProduct) {
    this.pfProduct = pfProduct;
  }

  public ProductformatedDto pfFormat(FormatDto pfFormat) {
    this.pfFormat = pfFormat;
    return this;
  }

  /**
   * Get pfFormat
   * @return pfFormat
  */
  @ApiModelProperty(value = "")

  @Valid

  public FormatDto getPfFormat() {
    return pfFormat;
  }

  public void setPfFormat(FormatDto pfFormat) {
    this.pfFormat = pfFormat;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductformatedDto productformatedDto = (ProductformatedDto) o;
    return Objects.equals(this.pfPicture, productformatedDto.pfPicture) &&
        Objects.equals(this.pfProduct, productformatedDto.pfProduct) &&
        Objects.equals(this.pfFormat, productformatedDto.pfFormat);
  }

  @Override
  public int hashCode() {
    return Objects.hash(pfPicture, pfProduct, pfFormat);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductformatedDto {\n");
    
    sb.append("    pfPicture: ").append(toIndentedString(pfPicture)).append("\n");
    sb.append("    pfProduct: ").append(toIndentedString(pfProduct)).append("\n");
    sb.append("    pfFormat: ").append(toIndentedString(pfFormat)).append("\n");
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

