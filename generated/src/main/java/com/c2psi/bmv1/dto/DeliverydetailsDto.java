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
 * A Deliverydetails in the system which can contain multiple command
 */
@ApiModel(description = "A Deliverydetails in the system which can contain multiple command")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-01T16:05:37.277942500+01:00[Africa/Casablanca]")
public class DeliverydetailsDto   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("packageUsed")
  private Integer packageUsed;

  @JsonProperty("packageReturn")
  private Integer packageReturn;

  @JsonProperty("ddPackagingId")
  private Long ddPackagingId;

  @JsonProperty("ddDeliveryId")
  private Long ddDeliveryId;

  public DeliverydetailsDto id(Long id) {
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

  public DeliverydetailsDto packageUsed(Integer packageUsed) {
    this.packageUsed = packageUsed;
    return this;
  }

  /**
   * Get packageUsed
   * @return packageUsed
  */
  @ApiModelProperty(example = "0", value = "")


  public Integer getPackageUsed() {
    return packageUsed;
  }

  public void setPackageUsed(Integer packageUsed) {
    this.packageUsed = packageUsed;
  }

  public DeliverydetailsDto packageReturn(Integer packageReturn) {
    this.packageReturn = packageReturn;
    return this;
  }

  /**
   * Get packageReturn
   * @return packageReturn
  */
  @ApiModelProperty(example = "0", value = "")


  public Integer getPackageReturn() {
    return packageReturn;
  }

  public void setPackageReturn(Integer packageReturn) {
    this.packageReturn = packageReturn;
  }

  public DeliverydetailsDto ddPackagingId(Long ddPackagingId) {
    this.ddPackagingId = ddPackagingId;
    return this;
  }

  /**
   * Get ddPackagingId
   * @return ddPackagingId
  */
  @ApiModelProperty(value = "")


  public Long getDdPackagingId() {
    return ddPackagingId;
  }

  public void setDdPackagingId(Long ddPackagingId) {
    this.ddPackagingId = ddPackagingId;
  }

  public DeliverydetailsDto ddDeliveryId(Long ddDeliveryId) {
    this.ddDeliveryId = ddDeliveryId;
    return this;
  }

  /**
   * Get ddDeliveryId
   * @return ddDeliveryId
  */
  @ApiModelProperty(value = "")


  public Long getDdDeliveryId() {
    return ddDeliveryId;
  }

  public void setDdDeliveryId(Long ddDeliveryId) {
    this.ddDeliveryId = ddDeliveryId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DeliverydetailsDto deliverydetailsDto = (DeliverydetailsDto) o;
    return Objects.equals(this.id, deliverydetailsDto.id) &&
        Objects.equals(this.packageUsed, deliverydetailsDto.packageUsed) &&
        Objects.equals(this.packageReturn, deliverydetailsDto.packageReturn) &&
        Objects.equals(this.ddPackagingId, deliverydetailsDto.ddPackagingId) &&
        Objects.equals(this.ddDeliveryId, deliverydetailsDto.ddDeliveryId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, packageUsed, packageReturn, ddPackagingId, ddDeliveryId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DeliverydetailsDto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    packageUsed: ").append(toIndentedString(packageUsed)).append("\n");
    sb.append("    packageReturn: ").append(toIndentedString(packageReturn)).append("\n");
    sb.append("    ddPackagingId: ").append(toIndentedString(ddPackagingId)).append("\n");
    sb.append("    ddDeliveryId: ").append(toIndentedString(ddDeliveryId)).append("\n");
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

