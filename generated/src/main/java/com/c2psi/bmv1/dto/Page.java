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
 * Page
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-08T23:11:35.582723400+01:00[Africa/Douala]")
public class Page   {
  @JsonProperty("pagenum")
  private Integer pagenum;

  @JsonProperty("pagesize")
  private Integer pagesize;

  public Page pagenum(Integer pagenum) {
    this.pagenum = pagenum;
    return this;
  }

  /**
   * Get pagenum
   * minimum: 1
   * @return pagenum
  */
  @ApiModelProperty(value = "")

@Min(1) 
  public Integer getPagenum() {
    return pagenum;
  }

  public void setPagenum(Integer pagenum) {
    this.pagenum = pagenum;
  }

  public Page pagesize(Integer pagesize) {
    this.pagesize = pagesize;
    return this;
  }

  /**
   * Get pagesize
   * minimum: 10
   * @return pagesize
  */
  @ApiModelProperty(value = "")

@Min(10) 
  public Integer getPagesize() {
    return pagesize;
  }

  public void setPagesize(Integer pagesize) {
    this.pagesize = pagesize;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Page page = (Page) o;
    return Objects.equals(this.pagenum, page.pagenum) &&
        Objects.equals(this.pagesize, page.pagesize);
  }

  @Override
  public int hashCode() {
    return Objects.hash(pagenum, pagesize);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Page {\n");
    
    sb.append("    pagenum: ").append(toIndentedString(pagenum)).append("\n");
    sb.append("    pagesize: ").append(toIndentedString(pagesize)).append("\n");
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
