package com.c2psi.bmv1.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * A permission that really give right to act on an object of the system
 */
@ApiModel(description = "A permission that really give right to act on an object of the system")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-11T16:08:25.464702700+01:00[Africa/Douala]")
public class PermissionDto   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("permissionName")
  private String permissionName;

  @JsonProperty("permissionDescription")
  private String permissionDescription;

  public PermissionDto id(Long id) {
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

  public PermissionDto permissionName(String permissionName) {
    this.permissionName = permissionName;
    return this;
  }

  /**
   * Get permissionName
   * @return permissionName
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getPermissionName() {
    return permissionName;
  }

  public void setPermissionName(String permissionName) {
    this.permissionName = permissionName;
  }

  public PermissionDto permissionDescription(String permissionDescription) {
    this.permissionDescription = permissionDescription;
    return this;
  }

  /**
   * Get permissionDescription
   * @return permissionDescription
  */
  @ApiModelProperty(value = "")


  public String getPermissionDescription() {
    return permissionDescription;
  }

  public void setPermissionDescription(String permissionDescription) {
    this.permissionDescription = permissionDescription;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PermissionDto permissionDto = (PermissionDto) o;
    return Objects.equals(this.id, permissionDto.id) &&
        Objects.equals(this.permissionName, permissionDto.permissionName) &&
        Objects.equals(this.permissionDescription, permissionDto.permissionDescription);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, permissionName, permissionDescription);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PermissionDto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    permissionName: ").append(toIndentedString(permissionName)).append("\n");
    sb.append("    permissionDescription: ").append(toIndentedString(permissionDescription)).append("\n");
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

