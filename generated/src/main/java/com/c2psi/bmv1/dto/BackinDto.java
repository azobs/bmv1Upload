package com.c2psi.bmv1.dto;

import java.util.Objects;
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
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-16T08:50:41.193143300+01:00[Africa/Douala]")
public class BackinDto   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("biDate")
  @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime biDate;

  @JsonProperty("biComment")
  private String biComment;

  @JsonProperty("biCommandId")
  private Long biCommandId;

  @JsonProperty("biSalerId")
  private Long biSalerId;

  public BackinDto id(Long id) {
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

  public BackinDto biCommandId(Long biCommandId) {
    this.biCommandId = biCommandId;
    return this;
  }

  /**
   * Get biCommandId
   * @return biCommandId
  */
  @ApiModelProperty(value = "")


  public Long getBiCommandId() {
    return biCommandId;
  }

  public void setBiCommandId(Long biCommandId) {
    this.biCommandId = biCommandId;
  }

  public BackinDto biSalerId(Long biSalerId) {
    this.biSalerId = biSalerId;
    return this;
  }

  /**
   * Get biSalerId
   * @return biSalerId
  */
  @ApiModelProperty(value = "")


  public Long getBiSalerId() {
    return biSalerId;
  }

  public void setBiSalerId(Long biSalerId) {
    this.biSalerId = biSalerId;
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
        Objects.equals(this.biCommandId, backinDto.biCommandId) &&
        Objects.equals(this.biSalerId, backinDto.biSalerId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, biDate, biComment, biCommandId, biSalerId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BackinDto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    biDate: ").append(toIndentedString(biDate)).append("\n");
    sb.append("    biComment: ").append(toIndentedString(biComment)).append("\n");
    sb.append("    biCommandId: ").append(toIndentedString(biCommandId)).append("\n");
    sb.append("    biSalerId: ").append(toIndentedString(biSalerId)).append("\n");
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

