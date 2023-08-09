package com.c2psi.bmv1.dto;

import java.util.Objects;
import com.c2psi.bmv1.dto.PointofsaleDto;
import com.c2psi.bmv1.dto.UserbmDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * A Delivery in the system which can contain multiple command
 */
@ApiModel(description = "A Delivery in the system which can contain multiple command")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-08T23:11:35.582723400+01:00[Africa/Douala]")
public class DeliveryDto   {
  @JsonProperty("deliveryCode")
  private String deliveryCode;

  @JsonProperty("deliveryDate")
  private String deliveryDate;

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

  @JsonProperty("deliveryDeliver")
  private UserbmDto deliveryDeliver;

  @JsonProperty("deliveryPos")
  private PointofsaleDto deliveryPos;

  public DeliveryDto deliveryCode(String deliveryCode) {
    this.deliveryCode = deliveryCode;
    return this;
  }

  /**
   * Get deliveryCode
   * @return deliveryCode
  */
  @ApiModelProperty(value = "")


  public String getDeliveryCode() {
    return deliveryCode;
  }

  public void setDeliveryCode(String deliveryCode) {
    this.deliveryCode = deliveryCode;
  }

  public DeliveryDto deliveryDate(String deliveryDate) {
    this.deliveryDate = deliveryDate;
    return this;
  }

  /**
   * Get deliveryDate
   * @return deliveryDate
  */
  @ApiModelProperty(value = "")


  public String getDeliveryDate() {
    return deliveryDate;
  }

  public void setDeliveryDate(String deliveryDate) {
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
  @ApiModelProperty(value = "")


  public DeliveryStateEnum getDeliveryState() {
    return deliveryState;
  }

  public void setDeliveryState(DeliveryStateEnum deliveryState) {
    this.deliveryState = deliveryState;
  }

  public DeliveryDto deliveryDeliver(UserbmDto deliveryDeliver) {
    this.deliveryDeliver = deliveryDeliver;
    return this;
  }

  /**
   * Get deliveryDeliver
   * @return deliveryDeliver
  */
  @ApiModelProperty(value = "")

  @Valid

  public UserbmDto getDeliveryDeliver() {
    return deliveryDeliver;
  }

  public void setDeliveryDeliver(UserbmDto deliveryDeliver) {
    this.deliveryDeliver = deliveryDeliver;
  }

  public DeliveryDto deliveryPos(PointofsaleDto deliveryPos) {
    this.deliveryPos = deliveryPos;
    return this;
  }

  /**
   * Get deliveryPos
   * @return deliveryPos
  */
  @ApiModelProperty(value = "")

  @Valid

  public PointofsaleDto getDeliveryPos() {
    return deliveryPos;
  }

  public void setDeliveryPos(PointofsaleDto deliveryPos) {
    this.deliveryPos = deliveryPos;
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
    return Objects.equals(this.deliveryCode, deliveryDto.deliveryCode) &&
        Objects.equals(this.deliveryDate, deliveryDto.deliveryDate) &&
        Objects.equals(this.deliveryComment, deliveryDto.deliveryComment) &&
        Objects.equals(this.deliveryState, deliveryDto.deliveryState) &&
        Objects.equals(this.deliveryDeliver, deliveryDto.deliveryDeliver) &&
        Objects.equals(this.deliveryPos, deliveryDto.deliveryPos);
  }

  @Override
  public int hashCode() {
    return Objects.hash(deliveryCode, deliveryDate, deliveryComment, deliveryState, deliveryDeliver, deliveryPos);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DeliveryDto {\n");
    
    sb.append("    deliveryCode: ").append(toIndentedString(deliveryCode)).append("\n");
    sb.append("    deliveryDate: ").append(toIndentedString(deliveryDate)).append("\n");
    sb.append("    deliveryComment: ").append(toIndentedString(deliveryComment)).append("\n");
    sb.append("    deliveryState: ").append(toIndentedString(deliveryState)).append("\n");
    sb.append("    deliveryDeliver: ").append(toIndentedString(deliveryDeliver)).append("\n");
    sb.append("    deliveryPos: ").append(toIndentedString(deliveryPos)).append("\n");
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

