package com.c2psi.bmv1.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * An operation in the system
 */
@ApiModel(description = "An operation in the system")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-08T23:11:35.582723400+01:00[Africa/Douala]")
public class OperationDto   {
  @JsonProperty("opDate")
  private String opDate;

  @JsonProperty("opObject")
  private String opObject;

  @JsonProperty("opDescription")
  private String opDescription;

  /**
   * Gets or Sets opType
   */
  public enum OpTypeEnum {
    CREDIT("Credit"),
    
    WITHDRAWAL("Withdrawal"),
    
    CHANGE("Change"),
    
    OTHERS("Others");

    private String value;

    OpTypeEnum(String value) {
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
    public static OpTypeEnum fromValue(String value) {
      for (OpTypeEnum b : OpTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("opType")
  private OpTypeEnum opType;

  public OperationDto opDate(String opDate) {
    this.opDate = opDate;
    return this;
  }

  /**
   * Get opDate
   * @return opDate
  */
  @ApiModelProperty(value = "")


  public String getOpDate() {
    return opDate;
  }

  public void setOpDate(String opDate) {
    this.opDate = opDate;
  }

  public OperationDto opObject(String opObject) {
    this.opObject = opObject;
    return this;
  }

  /**
   * Get opObject
   * @return opObject
  */
  @ApiModelProperty(value = "")


  public String getOpObject() {
    return opObject;
  }

  public void setOpObject(String opObject) {
    this.opObject = opObject;
  }

  public OperationDto opDescription(String opDescription) {
    this.opDescription = opDescription;
    return this;
  }

  /**
   * Get opDescription
   * @return opDescription
  */
  @ApiModelProperty(value = "")


  public String getOpDescription() {
    return opDescription;
  }

  public void setOpDescription(String opDescription) {
    this.opDescription = opDescription;
  }

  public OperationDto opType(OpTypeEnum opType) {
    this.opType = opType;
    return this;
  }

  /**
   * Get opType
   * @return opType
  */
  @ApiModelProperty(value = "")


  public OpTypeEnum getOpType() {
    return opType;
  }

  public void setOpType(OpTypeEnum opType) {
    this.opType = opType;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OperationDto operationDto = (OperationDto) o;
    return Objects.equals(this.opDate, operationDto.opDate) &&
        Objects.equals(this.opObject, operationDto.opObject) &&
        Objects.equals(this.opDescription, operationDto.opDescription) &&
        Objects.equals(this.opType, operationDto.opType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(opDate, opObject, opDescription, opType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OperationDto {\n");
    
    sb.append("    opDate: ").append(toIndentedString(opDate)).append("\n");
    sb.append("    opObject: ").append(toIndentedString(opObject)).append("\n");
    sb.append("    opDescription: ").append(toIndentedString(opDescription)).append("\n");
    sb.append("    opType: ").append(toIndentedString(opType)).append("\n");
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

