package com.c2psi.bmv1.dto;

import java.util.Objects;
import com.c2psi.bmv1.dto.PermissionDto;
import com.c2psi.bmv1.dto.UserbmRoleDto;
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
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-13T03:59:42.033168+01:00[Africa/Douala]")
public class UserbmRolePermission   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("userbmrole")
  private UserbmRoleDto userbmrole;

  @JsonProperty("permission")
  private PermissionDto permission;

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

  public UserbmRolePermission id(Long id) {
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

  public UserbmRolePermission userbmrole(UserbmRoleDto userbmrole) {
    this.userbmrole = userbmrole;
    return this;
  }

  /**
   * Get userbmrole
   * @return userbmrole
  */
  @ApiModelProperty(value = "")

  @Valid

  public UserbmRoleDto getUserbmrole() {
    return userbmrole;
  }

  public void setUserbmrole(UserbmRoleDto userbmrole) {
    this.userbmrole = userbmrole;
  }

  public UserbmRolePermission permission(PermissionDto permission) {
    this.permission = permission;
    return this;
  }

  /**
   * Get permission
   * @return permission
  */
  @ApiModelProperty(value = "")

  @Valid

  public PermissionDto getPermission() {
    return permission;
  }

  public void setPermission(PermissionDto permission) {
    this.permission = permission;
  }

  public UserbmRolePermission operation(OperationEnum operation) {
    this.operation = operation;
    return this;
  }

  /**
   * Get operation
   * @return operation
  */
  @ApiModelProperty(example = "ADD", value = "")


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
    return Objects.equals(this.id, userbmRolePermission.id) &&
        Objects.equals(this.userbmrole, userbmRolePermission.userbmrole) &&
        Objects.equals(this.permission, userbmRolePermission.permission) &&
        Objects.equals(this.operation, userbmRolePermission.operation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, userbmrole, permission, operation);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserbmRolePermission {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    userbmrole: ").append(toIndentedString(userbmrole)).append("\n");
    sb.append("    permission: ").append(toIndentedString(permission)).append("\n");
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

