package com.c2psi.bmv1.dto;

import java.util.Objects;
import com.c2psi.bmv1.dto.SupplyinvoiceDto;
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
 * PageofSupplyinvoiceDto
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-11T17:01:56.543198200+01:00[Africa/Douala]")
public class PageofSupplyinvoiceDto   {
  @JsonProperty("totalElements")
  private Integer totalElements;

  @JsonProperty("totalPages")
  private Integer totalPages;

  @JsonProperty("currentPage")
  private Integer currentPage;

  @JsonProperty("pageSize")
  private Integer pageSize;

  @JsonProperty("content")
  @Valid
  private List<SupplyinvoiceDto> content = null;

  public PageofSupplyinvoiceDto totalElements(Integer totalElements) {
    this.totalElements = totalElements;
    return this;
  }

  /**
   * Get totalElements
   * @return totalElements
  */
  @ApiModelProperty(value = "")


  public Integer getTotalElements() {
    return totalElements;
  }

  public void setTotalElements(Integer totalElements) {
    this.totalElements = totalElements;
  }

  public PageofSupplyinvoiceDto totalPages(Integer totalPages) {
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

  public PageofSupplyinvoiceDto currentPage(Integer currentPage) {
    this.currentPage = currentPage;
    return this;
  }

  /**
   * Get currentPage
   * minimum: 0
   * @return currentPage
  */
  @ApiModelProperty(example = "0", value = "")

@Min(0) 
  public Integer getCurrentPage() {
    return currentPage;
  }

  public void setCurrentPage(Integer currentPage) {
    this.currentPage = currentPage;
  }

  public PageofSupplyinvoiceDto pageSize(Integer pageSize) {
    this.pageSize = pageSize;
    return this;
  }

  /**
   * Get pageSize
   * minimum: 1
   * @return pageSize
  */
  @ApiModelProperty(example = "10", value = "")

@Min(1) 
  public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  public PageofSupplyinvoiceDto content(List<SupplyinvoiceDto> content) {
    this.content = content;
    return this;
  }

  public PageofSupplyinvoiceDto addContentItem(SupplyinvoiceDto contentItem) {
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

  public List<SupplyinvoiceDto> getContent() {
    return content;
  }

  public void setContent(List<SupplyinvoiceDto> content) {
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
    PageofSupplyinvoiceDto pageofSupplyinvoiceDto = (PageofSupplyinvoiceDto) o;
    return Objects.equals(this.totalElements, pageofSupplyinvoiceDto.totalElements) &&
        Objects.equals(this.totalPages, pageofSupplyinvoiceDto.totalPages) &&
        Objects.equals(this.currentPage, pageofSupplyinvoiceDto.currentPage) &&
        Objects.equals(this.pageSize, pageofSupplyinvoiceDto.pageSize) &&
        Objects.equals(this.content, pageofSupplyinvoiceDto.content);
  }

  @Override
  public int hashCode() {
    return Objects.hash(totalElements, totalPages, currentPage, pageSize, content);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PageofSupplyinvoiceDto {\n");
    
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

