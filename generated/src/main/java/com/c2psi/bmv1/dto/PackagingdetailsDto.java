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
 * A Packaging details in the system
 */
@ApiModel(description = "A Packaging details in the system")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-27T14:53:37.924409800+01:00[Africa/Casablanca]")
public class PackagingdetailsDto   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("packagenumberUsed")
  private Integer packagenumberUsed;

  @JsonProperty("packagenumberReturn")
  private Integer packagenumberReturn;

  @JsonProperty("pdPackagingId")
  private Long pdPackagingId;

  @JsonProperty("pdLoading")
  private Long pdLoading;

  public PackagingdetailsDto id(Long id) {
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

  public PackagingdetailsDto packagenumberUsed(Integer packagenumberUsed) {
    this.packagenumberUsed = packagenumberUsed;
    return this;
  }

  /**
   * Get packagenumberUsed
   * @return packagenumberUsed
  */
  @ApiModelProperty(example = "1", value = "")


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
  @ApiModelProperty(example = "1", value = "")


  public Integer getPackagenumberReturn() {
    return packagenumberReturn;
  }

  public void setPackagenumberReturn(Integer packagenumberReturn) {
    this.packagenumberReturn = packagenumberReturn;
  }

  public PackagingdetailsDto pdPackagingId(Long pdPackagingId) {
    this.pdPackagingId = pdPackagingId;
    return this;
  }

  /**
   * Get pdPackagingId
   * @return pdPackagingId
  */
  @ApiModelProperty(value = "")


  public Long getPdPackagingId() {
    return pdPackagingId;
  }

  public void setPdPackagingId(Long pdPackagingId) {
    this.pdPackagingId = pdPackagingId;
  }

  public PackagingdetailsDto pdLoading(Long pdLoading) {
    this.pdLoading = pdLoading;
    return this;
  }

  /**
   * Get pdLoading
   * @return pdLoading
  */
  @ApiModelProperty(value = "")


  public Long getPdLoading() {
    return pdLoading;
  }

  public void setPdLoading(Long pdLoading) {
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
    return Objects.equals(this.id, packagingdetailsDto.id) &&
        Objects.equals(this.packagenumberUsed, packagingdetailsDto.packagenumberUsed) &&
        Objects.equals(this.packagenumberReturn, packagingdetailsDto.packagenumberReturn) &&
        Objects.equals(this.pdPackagingId, packagingdetailsDto.pdPackagingId) &&
        Objects.equals(this.pdLoading, packagingdetailsDto.pdLoading);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, packagenumberUsed, packagenumberReturn, pdPackagingId, pdLoading);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PackagingdetailsDto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    packagenumberUsed: ").append(toIndentedString(packagenumberUsed)).append("\n");
    sb.append("    packagenumberReturn: ").append(toIndentedString(packagenumberReturn)).append("\n");
    sb.append("    pdPackagingId: ").append(toIndentedString(pdPackagingId)).append("\n");
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

