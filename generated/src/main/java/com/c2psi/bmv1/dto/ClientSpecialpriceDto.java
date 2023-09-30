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
 * A client in the system
 */
@ApiModel(description = "A client in the system")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-28T04:24:19.978343600+01:00[Africa/Casablanca]")
public class ClientSpecialpriceDto   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("appliedDate")
  @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime appliedDate;

  @JsonProperty("clientId")
  private Long clientId;

  @JsonProperty("specialpriceId")
  private Long specialpriceId;

  @JsonProperty("articleId")
  private Long articleId;

  public ClientSpecialpriceDto id(Long id) {
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

  public ClientSpecialpriceDto appliedDate(OffsetDateTime appliedDate) {
    this.appliedDate = appliedDate;
    return this;
  }

  /**
   * Get appliedDate
   * @return appliedDate
  */
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getAppliedDate() {
    return appliedDate;
  }

  public void setAppliedDate(OffsetDateTime appliedDate) {
    this.appliedDate = appliedDate;
  }

  public ClientSpecialpriceDto clientId(Long clientId) {
    this.clientId = clientId;
    return this;
  }

  /**
   * Get clientId
   * @return clientId
  */
  @ApiModelProperty(value = "")


  public Long getClientId() {
    return clientId;
  }

  public void setClientId(Long clientId) {
    this.clientId = clientId;
  }

  public ClientSpecialpriceDto specialpriceId(Long specialpriceId) {
    this.specialpriceId = specialpriceId;
    return this;
  }

  /**
   * Get specialpriceId
   * @return specialpriceId
  */
  @ApiModelProperty(value = "")


  public Long getSpecialpriceId() {
    return specialpriceId;
  }

  public void setSpecialpriceId(Long specialpriceId) {
    this.specialpriceId = specialpriceId;
  }

  public ClientSpecialpriceDto articleId(Long articleId) {
    this.articleId = articleId;
    return this;
  }

  /**
   * Get articleId
   * @return articleId
  */
  @ApiModelProperty(value = "")


  public Long getArticleId() {
    return articleId;
  }

  public void setArticleId(Long articleId) {
    this.articleId = articleId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ClientSpecialpriceDto clientSpecialpriceDto = (ClientSpecialpriceDto) o;
    return Objects.equals(this.id, clientSpecialpriceDto.id) &&
        Objects.equals(this.appliedDate, clientSpecialpriceDto.appliedDate) &&
        Objects.equals(this.clientId, clientSpecialpriceDto.clientId) &&
        Objects.equals(this.specialpriceId, clientSpecialpriceDto.specialpriceId) &&
        Objects.equals(this.articleId, clientSpecialpriceDto.articleId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, appliedDate, clientId, specialpriceId, articleId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ClientSpecialpriceDto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    appliedDate: ").append(toIndentedString(appliedDate)).append("\n");
    sb.append("    clientId: ").append(toIndentedString(clientId)).append("\n");
    sb.append("    specialpriceId: ").append(toIndentedString(specialpriceId)).append("\n");
    sb.append("    articleId: ").append(toIndentedString(articleId)).append("\n");
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

