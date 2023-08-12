package com.c2psi.bmv1.dto;

import java.util.Objects;
import com.c2psi.bmv1.dto.PointofsaleDto;
import com.c2psi.bmv1.dto.UserbmDto;
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
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-12T10:38:54.913224900+01:00[Africa/Douala]")
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

  @JsonProperty("deliveryDeliver")
  private UserbmDto deliveryDeliver;

  @JsonProperty("deliveryPos")
  private PointofsaleDto deliveryPos;

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
    return Objects.equals(this.id, deliveryDto.id) &&
        Objects.equals(this.deliveryCode, deliveryDto.deliveryCode) &&
        Objects.equals(this.deliveryDate, deliveryDto.deliveryDate) &&
        Objects.equals(this.deliveryComment, deliveryDto.deliveryComment) &&
        Objects.equals(this.deliveryState, deliveryDto.deliveryState) &&
        Objects.equals(this.deliveryDeliver, deliveryDto.deliveryDeliver) &&
        Objects.equals(this.deliveryPos, deliveryDto.deliveryPos);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, deliveryCode, deliveryDate, deliveryComment, deliveryState, deliveryDeliver, deliveryPos);
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

