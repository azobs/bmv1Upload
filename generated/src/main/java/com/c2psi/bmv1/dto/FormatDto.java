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
 * A format used to format product in the system
 */
@ApiModel(description = "A format used to format product in the system")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-10T08:08:31.170887700+01:00[Africa/Douala]")
public class FormatDto   {
  @JsonProperty("formatName")
  private String formatName;

  @JsonProperty("formatCapacity")
  private Double formatCapacity;

  @JsonProperty("formatPos")
  private PointofsaleDto formatPos;

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

  public FormatDto formatPos(PointofsaleDto formatPos) {
    this.formatPos = formatPos;
    return this;
  }

  /**
   * Get formatPos
   * @return formatPos
  */
  @ApiModelProperty(value = "")

  @Valid

  public PointofsaleDto getFormatPos() {
    return formatPos;
  }

  public void setFormatPos(PointofsaleDto formatPos) {
    this.formatPos = formatPos;
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
    return Objects.equals(this.formatName, formatDto.formatName) &&
        Objects.equals(this.formatCapacity, formatDto.formatCapacity) &&
        Objects.equals(this.formatPos, formatDto.formatPos);
  }

  @Override
  public int hashCode() {
    return Objects.hash(formatName, formatCapacity, formatPos);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FormatDto {\n");
    
    sb.append("    formatName: ").append(toIndentedString(formatName)).append("\n");
    sb.append("    formatCapacity: ").append(toIndentedString(formatCapacity)).append("\n");
    sb.append("    formatPos: ").append(toIndentedString(formatPos)).append("\n");
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

