package com.c2psi.bmv1.dto;

import java.util.Objects;
import com.c2psi.bmv1.dto.PermissionDto;
import com.c2psi.bmv1.dto.RoleDto;
import com.c2psi.bmv1.dto.UserbmDto;
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
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-08T23:11:35.582723400+01:00[Africa/Douala]")
public class UserbmRoleDto   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("userbm")
  private UserbmDto userbm;

  @JsonProperty("role")
  private RoleDto role;

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
  @ApiModelProperty(readOnly = true, value = "")


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UserbmRoleDto userbm(UserbmDto userbm) {
    this.userbm = userbm;
    return this;
  }

  /**
   * Get userbm
   * @return userbm
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public UserbmDto getUserbm() {
    return userbm;
  }

  public void setUserbm(UserbmDto userbm) {
    this.userbm = userbm;
  }

  public UserbmRoleDto role(RoleDto role) {
    this.role = role;
    return this;
  }

  /**
   * Get role
   * @return role
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public RoleDto getRole() {
    return role;
  }

  public void setRole(RoleDto role) {
    this.role = role;
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
        Objects.equals(this.userbm, userbmRoleDto.userbm) &&
        Objects.equals(this.role, userbmRoleDto.role) &&
        Objects.equals(this.permissions, userbmRoleDto.permissions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, userbm, role, permissions);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserbmRoleDto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    userbm: ").append(toIndentedString(userbm)).append("\n");
    sb.append("    role: ").append(toIndentedString(role)).append("\n");
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

