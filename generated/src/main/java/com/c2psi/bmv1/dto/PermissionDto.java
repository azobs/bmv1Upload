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
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-08T23:11:35.582723400+01:00[Africa/Douala]")
public class PermissionDto   {
  @JsonProperty("permissionName")
  private String permissionName;

  @JsonProperty("permissionDescription")
  private String permissionDescription;

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
    return Objects.equals(this.permissionName, permissionDto.permissionName) &&
        Objects.equals(this.permissionDescription, permissionDto.permissionDescription);
  }

  @Override
  public int hashCode() {
    return Objects.hash(permissionName, permissionDescription);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PermissionDto {\n");
    
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

