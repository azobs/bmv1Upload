package com.c2psi.bmv1.dto;

import java.util.Objects;
import com.c2psi.bmv1.dto.CommandDto;
import com.c2psi.bmv1.dto.UserbmDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.OffsetDateTime;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * A Backin in the system which represent articles return back by a client after delivery
 */
@ApiModel(description = "A Backin in the system which represent articles return back by a client after delivery")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-12T06:34:45.513039400+01:00[Africa/Douala]")
public class BackinDto   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("biDate")
  @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime biDate;

  @JsonProperty("biComment")
  private String biComment;

  @JsonProperty("biCommand")
  private CommandDto biCommand;

  @JsonProperty("biSaler")
  private UserbmDto biSaler;

  public BackinDto id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @ApiModelProperty(readOnly = true, value = "")


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public BackinDto biDate(OffsetDateTime biDate) {
    this.biDate = biDate;
    return this;
  }

  /**
   * Get biDate
   * @return biDate
  */
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getBiDate() {
    return biDate;
  }

  public void setBiDate(OffsetDateTime biDate) {
    this.biDate = biDate;
  }

  public BackinDto biComment(String biComment) {
    this.biComment = biComment;
    return this;
  }

  /**
   * Get biComment
   * @return biComment
  */
  @ApiModelProperty(value = "")


  public String getBiComment() {
    return biComment;
  }

  public void setBiComment(String biComment) {
    this.biComment = biComment;
  }

  public BackinDto biCommand(CommandDto biCommand) {
    this.biCommand = biCommand;
    return this;
  }

  /**
   * Get biCommand
   * @return biCommand
  */
  @ApiModelProperty(value = "")

  @Valid

  public CommandDto getBiCommand() {
    return biCommand;
  }

  public void setBiCommand(CommandDto biCommand) {
    this.biCommand = biCommand;
  }

  public BackinDto biSaler(UserbmDto biSaler) {
    this.biSaler = biSaler;
    return this;
  }

  /**
   * Get biSaler
   * @return biSaler
  */
  @ApiModelProperty(value = "")

  @Valid

  public UserbmDto getBiSaler() {
    return biSaler;
  }

  public void setBiSaler(UserbmDto biSaler) {
    this.biSaler = biSaler;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BackinDto backinDto = (BackinDto) o;
    return Objects.equals(this.id, backinDto.id) &&
        Objects.equals(this.biDate, backinDto.biDate) &&
        Objects.equals(this.biComment, backinDto.biComment) &&
        Objects.equals(this.biCommand, backinDto.biCommand) &&
        Objects.equals(this.biSaler, backinDto.biSaler);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, biDate, biComment, biCommand, biSaler);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BackinDto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    biDate: ").append(toIndentedString(biDate)).append("\n");
    sb.append("    biComment: ").append(toIndentedString(biComment)).append("\n");
    sb.append("    biCommand: ").append(toIndentedString(biCommand)).append("\n");
    sb.append("    biSaler: ").append(toIndentedString(biSaler)).append("\n");
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

