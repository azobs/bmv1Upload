package com.c2psi.bmv1.dto;

import java.util.Objects;
import com.c2psi.bmv1.dto.LoadingDto;
import com.c2psi.bmv1.dto.PackagingDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * A Packaging details in the system
 */
@ApiModel(description = "A Packaging details in the system")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-08T23:11:35.582723400+01:00[Africa/Douala]")
public class PackagingdetailsDto   {
  @JsonProperty("packagenumberUsed")
  private Integer packagenumberUsed;

  @JsonProperty("packagenumberReturn")
  private Integer packagenumberReturn;

  @JsonProperty("pdPackaging")
  private PackagingDto pdPackaging;

  @JsonProperty("pdLoading")
  private LoadingDto pdLoading;

  public PackagingdetailsDto packagenumberUsed(Integer packagenumberUsed) {
    this.packagenumberUsed = packagenumberUsed;
    return this;
  }

  /**
   * Get packagenumberUsed
   * @return packagenumberUsed
  */
  @ApiModelProperty(value = "")


  public Integer getPackagenumberUsed() {
    return packagenumberUsed;
  }

  public void setPackagenumberUsed(Integer packagenumberUsed) {
    this.packagenumberUsed = packagenumberUsed;
  }

  public PackagingdetailsDto packagenumberReturn(Integer packagenumberReturn) {
    this.packagenumberReturn = packagenumberReturn;
    return this;
  }

  /**
   * Get packagenumberReturn
   * @return packagenumberReturn
  */
  @ApiModelProperty(value = "")


  public Integer getPackagenumberReturn() {
    return packagenumberReturn;
  }

  public void setPackagenumberReturn(Integer packagenumberReturn) {
    this.packagenumberReturn = packagenumberReturn;
  }

  public PackagingdetailsDto pdPackaging(PackagingDto pdPackaging) {
    this.pdPackaging = pdPackaging;
    return this;
  }

  /**
   * Get pdPackaging
   * @return pdPackaging
  */
  @ApiModelProperty(value = "")

  @Valid

  public PackagingDto getPdPackaging() {
    return pdPackaging;
  }

  public void setPdPackaging(PackagingDto pdPackaging) {
    this.pdPackaging = pdPackaging;
  }

  public PackagingdetailsDto pdLoading(LoadingDto pdLoading) {
    this.pdLoading = pdLoading;
    return this;
  }

  /**
   * Get pdLoading
   * @return pdLoading
  */
  @ApiModelProperty(value = "")

  @Valid

  public LoadingDto getPdLoading() {
    return pdLoading;
  }

  public void setPdLoading(LoadingDto pdLoading) {
    this.pdLoading = pdLoading;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PackagingdetailsDto packagingdetailsDto = (PackagingdetailsDto) o;
    return Objects.equals(this.packagenumberUsed, packagingdetailsDto.packagenumberUsed) &&
        Objects.equals(this.packagenumberReturn, packagingdetailsDto.packagenumberReturn) &&
        Objects.equals(this.pdPackaging, packagingdetailsDto.pdPackaging) &&
        Objects.equals(this.pdLoading, packagingdetailsDto.pdLoading);
  }

  @Override
  public int hashCode() {
    return Objects.hash(packagenumberUsed, packagenumberReturn, pdPackaging, pdLoading);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PackagingdetailsDto {\n");
    
    sb.append("    packagenumberUsed: ").append(toIndentedString(packagenumberUsed)).append("\n");
    sb.append("    packagenumberReturn: ").append(toIndentedString(packagenumberReturn)).append("\n");
    sb.append("    pdPackaging: ").append(toIndentedString(pdPackaging)).append("\n");
    sb.append("    pdLoading: ").append(toIndentedString(pdLoading)).append("\n");
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

