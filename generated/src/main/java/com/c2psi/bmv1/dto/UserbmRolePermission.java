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
 * UserbmRolePermission
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-08T23:11:35.582723400+01:00[Africa/Douala]")
public class UserbmRolePermission   {
  @JsonProperty("userbmroleId")
  private Long userbmroleId;

  @JsonProperty("permissionId")
  private Long permissionId;

  /**
   * Gets or Sets operation
   */
  public enum OperationEnum {
    ADD("ADD"),
    
    REMOVE("REMOVE");

    private String value;

    OperationEnum(String value) {
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
    public static OperationEnum fromValue(String value) {
      for (OperationEnum b : OperationEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("operation")
  private OperationEnum operation;

  public UserbmRolePermission userbmroleId(Long userbmroleId) {
    this.userbmroleId = userbmroleId;
    return this;
  }

  /**
   * Get userbmroleId
   * @return userbmroleId
  */
  @ApiModelProperty(value = "")


  public Long getUserbmroleId() {
    return userbmroleId;
  }

  public void setUserbmroleId(Long userbmroleId) {
    this.userbmroleId = userbmroleId;
  }

  public UserbmRolePermission permissionId(Long permissionId) {
    this.permissionId = permissionId;
    return this;
  }

  /**
   * Get permissionId
   * @return permissionId
  */
  @ApiModelProperty(value = "")


  public Long getPermissionId() {
    return permissionId;
  }

  public void setPermissionId(Long permissionId) {
    this.permissionId = permissionId;
  }

  public UserbmRolePermission operation(OperationEnum operation) {
    this.operation = operation;
    return this;
  }

  /**
   * Get operation
   * @return operation
  */
  @ApiModelProperty(value = "")


  public OperationEnum getOperation() {
    return operation;
  }

  public void setOperation(OperationEnum operation) {
    this.operation = operation;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserbmRolePermission userbmRolePermission = (UserbmRolePermission) o;
    return Objects.equals(this.userbmroleId, userbmRolePermission.userbmroleId) &&
        Objects.equals(this.permissionId, userbmRolePermission.permissionId) &&
        Objects.equals(this.operation, userbmRolePermission.operation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userbmroleId, permissionId, operation);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserbmRolePermission {\n");
    
    sb.append("    userbmroleId: ").append(toIndentedString(userbmroleId)).append("\n");
    sb.append("    permissionId: ").append(toIndentedString(permissionId)).append("\n");
    sb.append("    operation: ").append(toIndentedString(operation)).append("\n");
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

