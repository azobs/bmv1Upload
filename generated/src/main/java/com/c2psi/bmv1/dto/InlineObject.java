package com.c2psi.bmv1.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * InlineObject
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-27T09:34:19.948066200+01:00[Africa/Casablanca]")
public class InlineObject   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("fileName")
  private org.springframework.core.io.Resource fileName;

  /**
   * Gets or Sets imageOf
   */
  public enum ImageOfEnum {
    ARTICLE("ARTICLE"),
    
    LOGO("LOGO"),
    
    PERSON("PERSON"),
    
    PRODUCTFORMATED("PRODUCTFORMATED"),
    
    INVOICE("INVOICE");

    private String value;

    ImageOfEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static ImageOfEnum fromValue(String value) {
      for (ImageOfEnum b : ImageOfEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("imageOf")
  private ImageOfEnum imageOf;

  public InlineObject id(Long id) {
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

  public InlineObject fileName(org.springframework.core.io.Resource fileName) {
    this.fileName = fileName;
    return this;
  }

  /**
   * Get fileName
   * @return fileName
  */
  @ApiModelProperty(value = "")

  @Valid

  public org.springframework.core.io.Resource getFileName() {
    return fileName;
  }

  public void setFileName(org.springframework.core.io.Resource fileName) {
    this.fileName = fileName;
  }

  public InlineObject imageOf(ImageOfEnum imageOf) {
    this.imageOf = imageOf;
    return this;
  }

  /**
   * Get imageOf
   * @return imageOf
  */
  @ApiModelProperty(value = "")


  public ImageOfEnum getImageOf() {
    return imageOf;
  }

  public void setImageOf(ImageOfEnum imageOf) {
    this.imageOf = imageOf;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InlineObject inlineObject = (InlineObject) o;
    return Objects.equals(this.id, inlineObject.id) &&
        Objects.equals(this.fileName, inlineObject.fileName) &&
        Objects.equals(this.imageOf, inlineObject.imageOf);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, fileName, imageOf);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InlineObject {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    fileName: ").append(toIndentedString(fileName)).append("\n");
    sb.append("    imageOf: ").append(toIndentedString(imageOf)).append("\n");
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

