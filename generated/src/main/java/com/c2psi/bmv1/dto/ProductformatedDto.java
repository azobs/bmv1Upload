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
 * A product formated in the system
 */
@ApiModel(description = "A product formated in the system")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-05T20:37:01.434321300+01:00[Africa/Casablanca]")
public class ProductformatedDto   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("pfPicture")
  private String pfPicture;

  @JsonProperty("pfProductId")
  private Long pfProductId;

  @JsonProperty("pfFormatId")
  private Long pfFormatId;

  public ProductformatedDto id(Long id) {
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

  public ProductformatedDto pfProductId(Long pfProductId) {
    this.pfProductId = pfProductId;
    return this;
  }

  /**
   * Get pfProductId
   * @return pfProductId
  */
  @ApiModelProperty(value = "")


  public Long getPfProductId() {
    return pfProductId;
  }

  public void setPfProductId(Long pfProductId) {
    this.pfProductId = pfProductId;
  }

  public ProductformatedDto pfFormatId(Long pfFormatId) {
    this.pfFormatId = pfFormatId;
    return this;
  }

  /**
   * Get pfFormatId
   * @return pfFormatId
  */
  @ApiModelProperty(value = "")


  public Long getPfFormatId() {
    return pfFormatId;
  }

  public void setPfFormatId(Long pfFormatId) {
    this.pfFormatId = pfFormatId;
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
    return Objects.equals(this.id, productformatedDto.id) &&
        Objects.equals(this.pfPicture, productformatedDto.pfPicture) &&
        Objects.equals(this.pfProductId, productformatedDto.pfProductId) &&
        Objects.equals(this.pfFormatId, productformatedDto.pfFormatId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, pfPicture, pfProductId, pfFormatId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductformatedDto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    pfPicture: ").append(toIndentedString(pfPicture)).append("\n");
    sb.append("    pfProductId: ").append(toIndentedString(pfProductId)).append("\n");
    sb.append("    pfFormatId: ").append(toIndentedString(pfFormatId)).append("\n");
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

