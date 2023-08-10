package com.c2psi.bmv1.dto;

import java.util.Objects;
import com.c2psi.bmv1.dto.EnterpriseDto;
import com.c2psi.bmv1.dto.PointofsaleDto;
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
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-10T08:08:31.170887700+01:00[Africa/Douala]")
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

  @JsonProperty("rolePos")
  private PointofsaleDto rolePos;

  @JsonProperty("roleEnt")
  private EnterpriseDto roleEnt;

  public RoleDto id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @ApiModelProperty(readOnly = true, value = "")


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

  public RoleDto rolePos(PointofsaleDto rolePos) {
    this.rolePos = rolePos;
    return this;
  }

  /**
   * Get rolePos
   * @return rolePos
  */
  @ApiModelProperty(value = "")

  @Valid

  public PointofsaleDto getRolePos() {
    return rolePos;
  }

  public void setRolePos(PointofsaleDto rolePos) {
    this.rolePos = rolePos;
  }

  public RoleDto roleEnt(EnterpriseDto roleEnt) {
    this.roleEnt = roleEnt;
    return this;
  }

  /**
   * Get roleEnt
   * @return roleEnt
  */
  @ApiModelProperty(value = "")

  @Valid

  public EnterpriseDto getRoleEnt() {
    return roleEnt;
  }

  public void setRoleEnt(EnterpriseDto roleEnt) {
    this.roleEnt = roleEnt;
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
        Objects.equals(this.rolePos, roleDto.rolePos) &&
        Objects.equals(this.roleEnt, roleDto.roleEnt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, roleName, roleDescription, roleType, rolePos, roleEnt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RoleDto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    roleName: ").append(toIndentedString(roleName)).append("\n");
    sb.append("    roleDescription: ").append(toIndentedString(roleDescription)).append("\n");
    sb.append("    roleType: ").append(toIndentedString(roleType)).append("\n");
    sb.append("    rolePos: ").append(toIndentedString(rolePos)).append("\n");
    sb.append("    roleEnt: ").append(toIndentedString(roleEnt)).append("\n");
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

