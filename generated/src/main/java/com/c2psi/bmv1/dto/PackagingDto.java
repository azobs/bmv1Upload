package com.c2psi.bmv1.dto;

import java.util.Objects;
import com.c2psi.bmv1.dto.PointofsaleDto;
import com.c2psi.bmv1.dto.ProviderDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * A Packaging in the system
 */
@ApiModel(description = "A Packaging in the system")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-10T08:08:31.170887700+01:00[Africa/Douala]")
public class PackagingDto   {
  @JsonProperty("packLabel")
  private String packLabel;

  @JsonProperty("packDescription")
  private String packDescription;

  @JsonProperty("packFirstcolor")
  private String packFirstcolor;

  @JsonProperty("packPrice")
  private Double packPrice;

  @JsonProperty("packagingPos")
  private PointofsaleDto packagingPos;

  @JsonProperty("packagingProvider")
  private ProviderDto packagingProvider;

  public PackagingDto packLabel(String packLabel) {
    this.packLabel = packLabel;
    return this;
  }

  /**
   * Get packLabel
   * @return packLabel
  */
  @ApiModelProperty(example = "packLabel", value = "")


  public String getPackLabel() {
    return packLabel;
  }

  public void setPackLabel(String packLabel) {
    this.packLabel = packLabel;
  }

  public PackagingDto packDescription(String packDescription) {
    this.packDescription = packDescription;
    return this;
  }

  /**
   * Get packDescription
   * @return packDescription
  */
  @ApiModelProperty(value = "")


  public String getPackDescription() {
    return packDescription;
  }

  public void setPackDescription(String packDescription) {
    this.packDescription = packDescription;
  }

  public PackagingDto packFirstcolor(String packFirstcolor) {
    this.packFirstcolor = packFirstcolor;
    return this;
  }

  /**
   * Get packFirstcolor
   * @return packFirstcolor
  */
  @ApiModelProperty(value = "")


  public String getPackFirstcolor() {
    return packFirstcolor;
  }

  public void setPackFirstcolor(String packFirstcolor) {
    this.packFirstcolor = packFirstcolor;
  }

  public PackagingDto packPrice(Double packPrice) {
    this.packPrice = packPrice;
    return this;
  }

  /**
   * Get packPrice
   * @return packPrice
  */
  @ApiModelProperty(value = "")


  public Double getPackPrice() {
    return packPrice;
  }

  public void setPackPrice(Double packPrice) {
    this.packPrice = packPrice;
  }

  public PackagingDto packagingPos(PointofsaleDto packagingPos) {
    this.packagingPos = packagingPos;
    return this;
  }

  /**
   * Get packagingPos
   * @return packagingPos
  */
  @ApiModelProperty(value = "")

  @Valid

  public PointofsaleDto getPackagingPos() {
    return packagingPos;
  }

  public void setPackagingPos(PointofsaleDto packagingPos) {
    this.packagingPos = packagingPos;
  }

  public PackagingDto packagingProvider(ProviderDto packagingProvider) {
    this.packagingProvider = packagingProvider;
    return this;
  }

  /**
   * Get packagingProvider
   * @return packagingProvider
  */
  @ApiModelProperty(value = "")

  @Valid

  public ProviderDto getPackagingProvider() {
    return packagingProvider;
  }

  public void setPackagingProvider(ProviderDto packagingProvider) {
    this.packagingProvider = packagingProvider;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PackagingDto packagingDto = (PackagingDto) o;
    return Objects.equals(this.packLabel, packagingDto.packLabel) &&
        Objects.equals(this.packDescription, packagingDto.packDescription) &&
        Objects.equals(this.packFirstcolor, packagingDto.packFirstcolor) &&
        Objects.equals(this.packPrice, packagingDto.packPrice) &&
        Objects.equals(this.packagingPos, packagingDto.packagingPos) &&
        Objects.equals(this.packagingProvider, packagingDto.packagingProvider);
  }

  @Override
  public int hashCode() {
    return Objects.hash(packLabel, packDescription, packFirstcolor, packPrice, packagingPos, packagingProvider);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PackagingDto {\n");
    
    sb.append("    packLabel: ").append(toIndentedString(packLabel)).append("\n");
    sb.append("    packDescription: ").append(toIndentedString(packDescription)).append("\n");
    sb.append("    packFirstcolor: ").append(toIndentedString(packFirstcolor)).append("\n");
    sb.append("    packPrice: ").append(toIndentedString(packPrice)).append("\n");
    sb.append("    packagingPos: ").append(toIndentedString(packagingPos)).append("\n");
    sb.append("    packagingProvider: ").append(toIndentedString(packagingProvider)).append("\n");
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

