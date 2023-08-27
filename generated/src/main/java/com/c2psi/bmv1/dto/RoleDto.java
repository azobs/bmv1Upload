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
 * RoleDto
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-27T14:53:37.924409800+01:00[Africa/Casablanca]")
public class RoleDto   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("roleName")
  private String roleName;

  @JsonProperty("roleDescription")
  private String roleDescription;

  /**
   * Gets or Sets roleType
   */
  public enum RoleTypeEnum {
    ADMINBM("ADMINBM"),
    
    ADMINENTERPRISE("ADMINENTERPRISE"),
    
    EMPLOYE("EMPLOYE");

    private String value;

    RoleTypeEnum(String value) {
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
    public static RoleTypeEnum fromValue(String value) {
      for (RoleTypeEnum b : RoleTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("roleType")
  private RoleTypeEnum roleType;

  @JsonProperty("rolePosId")
  private Long rolePosId;

  @JsonProperty("roleEntId")
  private Long roleEntId;

  public RoleDto id(Long id) {
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

  public RoleDto roleName(String roleName) {
    this.roleName = roleName;
    return this;
  }

  /**
   * Get roleName
   * @return roleName
  */
  @ApiModelProperty(example = "Admin", value = "")

@Size(min = 3, max = 20) 
  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  public RoleDto roleDescription(String roleDescription) {
    this.roleDescription = roleDescription;
    return this;
  }

  /**
   * Get roleDescription
   * @return roleDescription
  */
  @ApiModelProperty(value = "")

@Size(max = 256) 
  public String getRoleDescription() {
    return roleDescription;
  }

  public void setRoleDescription(String roleDescription) {
    this.roleDescription = roleDescription;
  }

  public RoleDto roleType(RoleTypeEnum roleType) {
    this.roleType = roleType;
    return this;
  }

  /**
   * Get roleType
   * @return roleType
  */
  @ApiModelProperty(example = "ADMINBM", value = "")


  public RoleTypeEnum getRoleType() {
    return roleType;
  }

  public void setRoleType(RoleTypeEnum roleType) {
    this.roleType = roleType;
  }

  public RoleDto rolePosId(Long rolePosId) {
    this.rolePosId = rolePosId;
    return this;
  }

  /**
   * Get rolePosId
   * @return rolePosId
  */
  @ApiModelProperty(value = "")


  public Long getRolePosId() {
    return rolePosId;
  }

  public void setRolePosId(Long rolePosId) {
    this.rolePosId = rolePosId;
  }

  public RoleDto roleEntId(Long roleEntId) {
    this.roleEntId = roleEntId;
    return this;
  }

  /**
   * Get roleEntId
   * @return roleEntId
  */
  @ApiModelProperty(value = "")


  public Long getRoleEntId() {
    return roleEntId;
  }

  public void setRoleEntId(Long roleEntId) {
    this.roleEntId = roleEntId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RoleDto roleDto = (RoleDto) o;
    return Objects.equals(this.id, roleDto.id) &&
        Objects.equals(this.roleName, roleDto.roleName) &&
        Objects.equals(this.roleDescription, roleDto.roleDescription) &&
        Objects.equals(this.roleType, roleDto.roleType) &&
        Objects.equals(this.rolePosId, roleDto.rolePosId) &&
        Objects.equals(this.roleEntId, roleDto.roleEntId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, roleName, roleDescription, roleType, rolePosId, roleEntId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RoleDto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    roleName: ").append(toIndentedString(roleName)).append("\n");
    sb.append("    roleDescription: ").append(toIndentedString(roleDescription)).append("\n");
    sb.append("    roleType: ").append(toIndentedString(roleType)).append("\n");
    sb.append("    rolePosId: ").append(toIndentedString(rolePosId)).append("\n");
    sb.append("    roleEntId: ").append(toIndentedString(roleEntId)).append("\n");
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

