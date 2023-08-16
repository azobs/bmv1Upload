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
 * A Packaging in the system
 */
@ApiModel(description = "A Packaging in the system")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-16T08:50:41.193143300+01:00[Africa/Douala]")
public class PackagingDto   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("packLabel")
  private String packLabel;

  @JsonProperty("packDescription")
  private String packDescription;

  @JsonProperty("packFirstcolor")
  private String packFirstcolor;

  @JsonProperty("packPrice")
  private Double packPrice;

  @JsonProperty("packagingPosId")
  private Long packagingPosId;

  @JsonProperty("packagingProviderId")
  private Long packagingProviderId;

  public PackagingDto id(Long id) {
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

  public PackagingDto packagingPosId(Long packagingPosId) {
    this.packagingPosId = packagingPosId;
    return this;
  }

  /**
   * Get packagingPosId
   * @return packagingPosId
  */
  @ApiModelProperty(value = "")


  public Long getPackagingPosId() {
    return packagingPosId;
  }

  public void setPackagingPosId(Long packagingPosId) {
    this.packagingPosId = packagingPosId;
  }

  public PackagingDto packagingProviderId(Long packagingProviderId) {
    this.packagingProviderId = packagingProviderId;
    return this;
  }

  /**
   * Get packagingProviderId
   * @return packagingProviderId
  */
  @ApiModelProperty(value = "")


  public Long getPackagingProviderId() {
    return packagingProviderId;
  }

  public void setPackagingProviderId(Long packagingProviderId) {
    this.packagingProviderId = packagingProviderId;
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
    return Objects.equals(this.id, packagingDto.id) &&
        Objects.equals(this.packLabel, packagingDto.packLabel) &&
        Objects.equals(this.packDescription, packagingDto.packDescription) &&
        Objects.equals(this.packFirstcolor, packagingDto.packFirstcolor) &&
        Objects.equals(this.packPrice, packagingDto.packPrice) &&
        Objects.equals(this.packagingPosId, packagingDto.packagingPosId) &&
        Objects.equals(this.packagingProviderId, packagingDto.packagingProviderId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, packLabel, packDescription, packFirstcolor, packPrice, packagingPosId, packagingProviderId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PackagingDto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    packLabel: ").append(toIndentedString(packLabel)).append("\n");
    sb.append("    packDescription: ").append(toIndentedString(packDescription)).append("\n");
    sb.append("    packFirstcolor: ").append(toIndentedString(packFirstcolor)).append("\n");
    sb.append("    packPrice: ").append(toIndentedString(packPrice)).append("\n");
    sb.append("    packagingPosId: ").append(toIndentedString(packagingPosId)).append("\n");
    sb.append("    packagingProviderId: ").append(toIndentedString(packagingProviderId)).append("\n");
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

