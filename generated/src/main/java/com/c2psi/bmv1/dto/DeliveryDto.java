package com.c2psi.bmv1.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.OffsetDateTime;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * A Delivery in the system which can contain multiple command
 */
@ApiModel(description = "A Delivery in the system which can contain multiple command")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-26T01:24:15.865861+01:00[Africa/Casablanca]")
public class DeliveryDto   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("deliveryCode")
  private String deliveryCode;

  @JsonProperty("deliveryDate")
  @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime deliveryDate;

  @JsonProperty("deliveryComment")
  private String deliveryComment;

  /**
   * Gets or Sets deliveryState
   */
  public enum DeliveryStateEnum {
    INEDITING("InEditing"),
    
    EDITED("Edited"),
    
    DELIVERY("Delivery");

    private String value;

    DeliveryStateEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static DeliveryStateEnum fromValue(String value) {
      for (DeliveryStateEnum b : DeliveryStateEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("deliveryState")
  private DeliveryStateEnum deliveryState;

  @JsonProperty("deliveryDeliverId")
  private Long deliveryDeliverId;

  @JsonProperty("deliveryPosId")
  private Long deliveryPosId;

  public DeliveryDto id(Long id) {
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

  public DeliveryDto deliveryCode(String deliveryCode) {
    this.deliveryCode = deliveryCode;
    return this;
  }

  /**
   * Get deliveryCode
   * @return deliveryCode
  */
  @ApiModelProperty(example = "D0001", value = "")


  public String getDeliveryCode() {
    return deliveryCode;
  }

  public void setDeliveryCode(String deliveryCode) {
    this.deliveryCode = deliveryCode;
  }

  public DeliveryDto deliveryDate(OffsetDateTime deliveryDate) {
    this.deliveryDate = deliveryDate;
    return this;
  }

  /**
   * Get deliveryDate
   * @return deliveryDate
  */
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getDeliveryDate() {
    return deliveryDate;
  }

  public void setDeliveryDate(OffsetDateTime deliveryDate) {
    this.deliveryDate = deliveryDate;
  }

  public DeliveryDto deliveryComment(String deliveryComment) {
    this.deliveryComment = deliveryComment;
    return this;
  }

  /**
   * Get deliveryComment
   * @return deliveryComment
  */
  @ApiModelProperty(value = "")


  public String getDeliveryComment() {
    return deliveryComment;
  }

  public void setDeliveryComment(String deliveryComment) {
    this.deliveryComment = deliveryComment;
  }

  public DeliveryDto deliveryState(DeliveryStateEnum deliveryState) {
    this.deliveryState = deliveryState;
    return this;
  }

  /**
   * Get deliveryState
   * @return deliveryState
  */
  @ApiModelProperty(example = "InEditing", value = "")


  public DeliveryStateEnum getDeliveryState() {
    return deliveryState;
  }

  public void setDeliveryState(DeliveryStateEnum deliveryState) {
    this.deliveryState = deliveryState;
  }

  public DeliveryDto deliveryDeliverId(Long deliveryDeliverId) {
    this.deliveryDeliverId = deliveryDeliverId;
    return this;
  }

  /**
   * Get deliveryDeliverId
   * @return deliveryDeliverId
  */
  @ApiModelProperty(value = "")


  public Long getDeliveryDeliverId() {
    return deliveryDeliverId;
  }

  public void setDeliveryDeliverId(Long deliveryDeliverId) {
    this.deliveryDeliverId = deliveryDeliverId;
  }

  public DeliveryDto deliveryPosId(Long deliveryPosId) {
    this.deliveryPosId = deliveryPosId;
    return this;
  }

  /**
   * Get deliveryPosId
   * @return deliveryPosId
  */
  @ApiModelProperty(value = "")


  public Long getDeliveryPosId() {
    return deliveryPosId;
  }

  public void setDeliveryPosId(Long deliveryPosId) {
    this.deliveryPosId = deliveryPosId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DeliveryDto deliveryDto = (DeliveryDto) o;
    return Objects.equals(this.id, deliveryDto.id) &&
        Objects.equals(this.deliveryCode, deliveryDto.deliveryCode) &&
        Objects.equals(this.deliveryDate, deliveryDto.deliveryDate) &&
        Objects.equals(this.deliveryComment, deliveryDto.deliveryComment) &&
        Objects.equals(this.deliveryState, deliveryDto.deliveryState) &&
        Objects.equals(this.deliveryDeliverId, deliveryDto.deliveryDeliverId) &&
        Objects.equals(this.deliveryPosId, deliveryDto.deliveryPosId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, deliveryCode, deliveryDate, deliveryComment, deliveryState, deliveryDeliverId, deliveryPosId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DeliveryDto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    deliveryCode: ").append(toIndentedString(deliveryCode)).append("\n");
    sb.append("    deliveryDate: ").append(toIndentedString(deliveryDate)).append("\n");
    sb.append("    deliveryComment: ").append(toIndentedString(deliveryComment)).append("\n");
    sb.append("    deliveryState: ").append(toIndentedString(deliveryState)).append("\n");
    sb.append("    deliveryDeliverId: ").append(toIndentedString(deliveryDeliverId)).append("\n");
    sb.append("    deliveryPosId: ").append(toIndentedString(deliveryPosId)).append("\n");
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

