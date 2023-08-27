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
 * A format used to format product in the system
 */
@ApiModel(description = "A format used to format product in the system")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-27T14:53:37.924409800+01:00[Africa/Casablanca]")
public class FormatDto   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("formatName")
  private String formatName;

  @JsonProperty("formatCapacity")
  private Double formatCapacity;

  @JsonProperty("formatPosId")
  private Long formatPosId;

  public FormatDto id(Long id) {
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

  public FormatDto formatName(String formatName) {
    this.formatName = formatName;
    return this;
  }

  /**
   * Get formatName
   * @return formatName
  */
  @ApiModelProperty(example = "formatName", value = "")


  public String getFormatName() {
    return formatName;
  }

  public void setFormatName(String formatName) {
    this.formatName = formatName;
  }

  public FormatDto formatCapacity(Double formatCapacity) {
    this.formatCapacity = formatCapacity;
    return this;
  }

  /**
   * Get formatCapacity
   * @return formatCapacity
  */
  @ApiModelProperty(value = "")


  public Double getFormatCapacity() {
    return formatCapacity;
  }

  public void setFormatCapacity(Double formatCapacity) {
    this.formatCapacity = formatCapacity;
  }

  public FormatDto formatPosId(Long formatPosId) {
    this.formatPosId = formatPosId;
    return this;
  }

  /**
   * Get formatPosId
   * @return formatPosId
  */
  @ApiModelProperty(value = "")


  public Long getFormatPosId() {
    return formatPosId;
  }

  public void setFormatPosId(Long formatPosId) {
    this.formatPosId = formatPosId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FormatDto formatDto = (FormatDto) o;
    return Objects.equals(this.id, formatDto.id) &&
        Objects.equals(this.formatName, formatDto.formatName) &&
        Objects.equals(this.formatCapacity, formatDto.formatCapacity) &&
        Objects.equals(this.formatPosId, formatDto.formatPosId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, formatName, formatCapacity, formatPosId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FormatDto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    formatName: ").append(toIndentedString(formatName)).append("\n");
    sb.append("    formatCapacity: ").append(toIndentedString(formatCapacity)).append("\n");
    sb.append("    formatPosId: ").append(toIndentedString(formatPosId)).append("\n");
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

