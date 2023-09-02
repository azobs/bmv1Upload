package com.c2psi.bmv1.dto;

import java.util.Objects;
import com.c2psi.bmv1.dto.PermissionDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * UserbmRoleDto
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-01T16:05:37.277942500+01:00[Africa/Casablanca]")
public class UserbmRoleDto   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("userbmId")
  private Long userbmId;

  @JsonProperty("roleId")
  private Long roleId;

  @JsonProperty("permissions")
  @Valid
  private List<PermissionDto> permissions = null;

  public UserbmRoleDto id(Long id) {
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

  public UserbmRoleDto userbmId(Long userbmId) {
    this.userbmId = userbmId;
    return this;
  }

  /**
   * Get userbmId
   * @return userbmId
  */
  @ApiModelProperty(value = "")


  public Long getUserbmId() {
    return userbmId;
  }

  public void setUserbmId(Long userbmId) {
    this.userbmId = userbmId;
  }

  public UserbmRoleDto roleId(Long roleId) {
    this.roleId = roleId;
    return this;
  }

  /**
   * Get roleId
   * @return roleId
  */
  @ApiModelProperty(value = "")


  public Long getRoleId() {
    return roleId;
  }

  public void setRoleId(Long roleId) {
    this.roleId = roleId;
  }

  public UserbmRoleDto permissions(List<PermissionDto> permissions) {
    this.permissions = permissions;
    return this;
  }

  public UserbmRoleDto addPermissionsItem(PermissionDto permissionsItem) {
    if (this.permissions == null) {
      this.permissions = new ArrayList<>();
    }
    this.permissions.add(permissionsItem);
    return this;
  }

  /**
   * Get permissions
   * @return permissions
  */
  @ApiModelProperty(value = "")

  @Valid

  public List<PermissionDto> getPermissions() {
    return permissions;
  }

  public void setPermissions(List<PermissionDto> permissions) {
    this.permissions = permissions;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserbmRoleDto userbmRoleDto = (UserbmRoleDto) o;
    return Objects.equals(this.id, userbmRoleDto.id) &&
        Objects.equals(this.userbmId, userbmRoleDto.userbmId) &&
        Objects.equals(this.roleId, userbmRoleDto.roleId) &&
        Objects.equals(this.permissions, userbmRoleDto.permissions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, userbmId, roleId, permissions);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserbmRoleDto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    userbmId: ").append(toIndentedString(userbmId)).append("\n");
    sb.append("    roleId: ").append(toIndentedString(roleId)).append("\n");
    sb.append("    permissions: ").append(toIndentedString(permissions)).append("\n");
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

