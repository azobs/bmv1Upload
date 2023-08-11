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
 * A selling unit of product formated in the system
 */
@ApiModel(description = "A selling unit of product formated in the system")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-11T17:01:56.543198200+01:00[Africa/Douala]")
public class UnitDto   {
  @JsonProperty("unitName")
  private String unitName;

  @JsonProperty("unitAbbreviation")
  private String unitAbbreviation;

  @JsonProperty("unitPos")
  private PointofsaleDto unitPos;

  public UnitDto unitName(String unitName) {
    this.unitName = unitName;
    return this;
  }

  /**
   * Get unitName
   * @return unitName
  */
  @ApiModelProperty(example = "unitName", value = "")


  public String getUnitName() {
    return unitName;
  }

  public void setUnitName(String unitName) {
    this.unitName = unitName;
  }

  public UnitDto unitAbbreviation(String unitAbbreviation) {
    this.unitAbbreviation = unitAbbreviation;
    return this;
  }

  /**
   * Get unitAbbreviation
   * @return unitAbbreviation
  */
  @ApiModelProperty(example = "UN", value = "")


  public String getUnitAbbreviation() {
    return unitAbbreviation;
  }

  public void setUnitAbbreviation(String unitAbbreviation) {
    this.unitAbbreviation = unitAbbreviation;
  }

  public UnitDto unitPos(PointofsaleDto unitPos) {
    this.unitPos = unitPos;
    return this;
  }

  /**
   * Get unitPos
   * @return unitPos
  */
  @ApiModelProperty(value = "")

  @Valid

  public PointofsaleDto getUnitPos() {
    return unitPos;
  }

  public void setUnitPos(PointofsaleDto unitPos) {
    this.unitPos = unitPos;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UnitDto unitDto = (UnitDto) o;
    return Objects.equals(this.unitName, unitDto.unitName) &&
        Objects.equals(this.unitAbbreviation, unitDto.unitAbbreviation) &&
        Objects.equals(this.unitPos, unitDto.unitPos);
  }

  @Override
  public int hashCode() {
    return Objects.hash(unitName, unitAbbreviation, unitPos);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UnitDto {\n");
    
    sb.append("    unitName: ").append(toIndentedString(unitName)).append("\n");
    sb.append("    unitAbbreviation: ").append(toIndentedString(unitAbbreviation)).append("\n");
    sb.append("    unitPos: ").append(toIndentedString(unitPos)).append("\n");
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

