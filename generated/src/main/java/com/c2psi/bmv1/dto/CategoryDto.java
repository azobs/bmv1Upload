package com.c2psi.bmv1.dto;

import java.util.Objects;
import com.c2psi.bmv1.dto.PointofsaleDto;
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
public class CategoryDto   {
  @JsonProperty("catName")
  private String catName;

  @JsonProperty("catShortname")
  private String catShortname;

  @JsonProperty("catCode")
  private String catCode;

  @JsonProperty("catDescription")
  private String catDescription;

  @JsonProperty("catParentDto")
  private CategoryDto catParentDto;

  @JsonProperty("catPosDto")
  private PointofsaleDto catPosDto;

  public CategoryDto catName(String catName) {
    this.catName = catName;
    return this;
  }

  /**
   * Get catName
   * @return catName
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

@Size(min = 2, max = 50) 
  public String getCatName() {
    return catName;
  }

  public void setCatName(String catName) {
    this.catName = catName;
  }

  public CategoryDto catShortname(String catShortname) {
    this.catShortname = catShortname;
    return this;
  }

  /**
   * Get catShortname
   * @return catShortname
  */
  @ApiModelProperty(value = "")

@Size(min = 2, max = 20) 
  public String getCatShortname() {
    return catShortname;
  }

  public void setCatShortname(String catShortname) {
    this.catShortname = catShortname;
  }

  public CategoryDto catCode(String catCode) {
    this.catCode = catCode;
    return this;
  }

  /**
   * Get catCode
   * @return catCode
  */
  @ApiModelProperty(value = "")

@Size(min = 3, max = 7) 
  public String getCatCode() {
    return catCode;
  }

  public void setCatCode(String catCode) {
    this.catCode = catCode;
  }

  public CategoryDto catDescription(String catDescription) {
    this.catDescription = catDescription;
    return this;
  }

  /**
   * Get catDescription
   * @return catDescription
  */
  @ApiModelProperty(value = "")

@Size(max = 256) 
  public String getCatDescription() {
    return catDescription;
  }

  public void setCatDescription(String catDescription) {
    this.catDescription = catDescription;
  }

  public CategoryDto catParentDto(CategoryDto catParentDto) {
    this.catParentDto = catParentDto;
    return this;
  }

  /**
   * Get catParentDto
   * @return catParentDto
  */
  @ApiModelProperty(value = "")

  @Valid

  public CategoryDto getCatParentDto() {
    return catParentDto;
  }

  public void setCatParentDto(CategoryDto catParentDto) {
    this.catParentDto = catParentDto;
  }

  public CategoryDto catPosDto(PointofsaleDto catPosDto) {
    this.catPosDto = catPosDto;
    return this;
  }

  /**
   * Get catPosDto
   * @return catPosDto
  */
  @ApiModelProperty(value = "")

  @Valid

  public PointofsaleDto getCatPosDto() {
    return catPosDto;
  }

  public void setCatPosDto(PointofsaleDto catPosDto) {
    this.catPosDto = catPosDto;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CategoryDto categoryDto = (CategoryDto) o;
    return Objects.equals(this.catName, categoryDto.catName) &&
        Objects.equals(this.catShortname, categoryDto.catShortname) &&
        Objects.equals(this.catCode, categoryDto.catCode) &&
        Objects.equals(this.catDescription, categoryDto.catDescription) &&
        Objects.equals(this.catParentDto, categoryDto.catParentDto) &&
        Objects.equals(this.catPosDto, categoryDto.catPosDto);
  }

  @Override
  public int hashCode() {
    return Objects.hash(catName, catShortname, catCode, catDescription, catParentDto, catPosDto);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CategoryDto {\n");
    
    sb.append("    catName: ").append(toIndentedString(catName)).append("\n");
    sb.append("    catShortname: ").append(toIndentedString(catShortname)).append("\n");
    sb.append("    catCode: ").append(toIndentedString(catCode)).append("\n");
    sb.append("    catDescription: ").append(toIndentedString(catDescription)).append("\n");
    sb.append("    catParentDto: ").append(toIndentedString(catParentDto)).append("\n");
    sb.append("    catPosDto: ").append(toIndentedString(catPosDto)).append("\n");
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

