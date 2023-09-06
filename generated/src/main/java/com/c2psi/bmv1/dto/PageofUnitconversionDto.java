package com.c2psi.bmv1.dto;

import java.util.Objects;
import com.c2psi.bmv1.dto.UnitconversionDto;
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
 * PageofUnitconversionDto
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-05T20:37:01.434321300+01:00[Africa/Casablanca]")
public class PageofUnitconversionDto   {
  @JsonProperty("totalElements")
  private Long totalElements;

  @JsonProperty("totalPages")
  private Integer totalPages;

  @JsonProperty("currentPage")
  private Integer currentPage;

  @JsonProperty("pageSize")
  private Integer pageSize;

  @JsonProperty("content")
  @Valid
  private List<UnitconversionDto> content = null;

  public PageofUnitconversionDto totalElements(Long totalElements) {
    this.totalElements = totalElements;
    return this;
  }

  /**
   * Get totalElements
   * @return totalElements
  */
  @ApiModelProperty(value = "")


  public Long getTotalElements() {
    return totalElements;
  }

  public void setTotalElements(Long totalElements) {
    this.totalElements = totalElements;
  }

  public PageofUnitconversionDto totalPages(Integer totalPages) {
    this.totalPages = totalPages;
    return this;
  }

  /**
   * Get totalPages
   * @return totalPages
  */
  @ApiModelProperty(value = "")


  public Integer getTotalPages() {
    return totalPages;
  }

  public void setTotalPages(Integer totalPages) {
    this.totalPages = totalPages;
  }

  public PageofUnitconversionDto currentPage(Integer currentPage) {
    this.currentPage = currentPage;
    return this;
  }

  /**
   * Get currentPage
   * @return currentPage
  */
  @ApiModelProperty(example = "0", value = "")


  public Integer getCurrentPage() {
    return currentPage;
  }

  public void setCurrentPage(Integer currentPage) {
    this.currentPage = currentPage;
  }

  public PageofUnitconversionDto pageSize(Integer pageSize) {
    this.pageSize = pageSize;
    return this;
  }

  /**
   * Get pageSize
   * @return pageSize
  */
  @ApiModelProperty(example = "10", value = "")


  public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  public PageofUnitconversionDto content(List<UnitconversionDto> content) {
    this.content = content;
    return this;
  }

  public PageofUnitconversionDto addContentItem(UnitconversionDto contentItem) {
    if (this.content == null) {
      this.content = new ArrayList<>();
    }
    this.content.add(contentItem);
    return this;
  }

  /**
   * Get content
   * @return content
  */
  @ApiModelProperty(value = "")

  @Valid

  public List<UnitconversionDto> getContent() {
    return content;
  }

  public void setContent(List<UnitconversionDto> content) {
    this.content = content;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PageofUnitconversionDto pageofUnitconversionDto = (PageofUnitconversionDto) o;
    return Objects.equals(this.totalElements, pageofUnitconversionDto.totalElements) &&
        Objects.equals(this.totalPages, pageofUnitconversionDto.totalPages) &&
        Objects.equals(this.currentPage, pageofUnitconversionDto.currentPage) &&
        Objects.equals(this.pageSize, pageofUnitconversionDto.pageSize) &&
        Objects.equals(this.content, pageofUnitconversionDto.content);
  }

  @Override
  public int hashCode() {
    return Objects.hash(totalElements, totalPages, currentPage, pageSize, content);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PageofUnitconversionDto {\n");
    
    sb.append("    totalElements: ").append(toIndentedString(totalElements)).append("\n");
    sb.append("    totalPages: ").append(toIndentedString(totalPages)).append("\n");
    sb.append("    currentPage: ").append(toIndentedString(currentPage)).append("\n");
    sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
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

