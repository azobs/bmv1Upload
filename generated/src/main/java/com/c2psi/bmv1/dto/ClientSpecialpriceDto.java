package com.c2psi.bmv1.dto;

import java.util.Objects;
import com.c2psi.bmv1.dto.ArticleDto;
import com.c2psi.bmv1.dto.ClientDto;
import com.c2psi.bmv1.dto.SpecialpriceDto;
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
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-11T16:08:25.464702700+01:00[Africa/Douala]")
public class ClientSpecialpriceDto   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("appliedDate")
  @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime appliedDate;

  @JsonProperty("client")
  private ClientDto client;

  @JsonProperty("specialprice")
  private SpecialpriceDto specialprice;

  @JsonProperty("article")
  private ArticleDto article;

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

  public ClientSpecialpriceDto client(ClientDto client) {
    this.client = client;
    return this;
  }

  /**
   * Get client
   * @return client
  */
  @ApiModelProperty(value = "")

  @Valid

  public ClientDto getClient() {
    return client;
  }

  public void setClient(ClientDto client) {
    this.client = client;
  }

  public ClientSpecialpriceDto specialprice(SpecialpriceDto specialprice) {
    this.specialprice = specialprice;
    return this;
  }

  /**
   * Get specialprice
   * @return specialprice
  */
  @ApiModelProperty(value = "")

  @Valid

  public SpecialpriceDto getSpecialprice() {
    return specialprice;
  }

  public void setSpecialprice(SpecialpriceDto specialprice) {
    this.specialprice = specialprice;
  }

  public ClientSpecialpriceDto article(ArticleDto article) {
    this.article = article;
    return this;
  }

  /**
   * Get article
   * @return article
  */
  @ApiModelProperty(value = "")

  @Valid

  public ArticleDto getArticle() {
    return article;
  }

  public void setArticle(ArticleDto article) {
    this.article = article;
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
        Objects.equals(this.client, clientSpecialpriceDto.client) &&
        Objects.equals(this.specialprice, clientSpecialpriceDto.specialprice) &&
        Objects.equals(this.article, clientSpecialpriceDto.article);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, appliedDate, client, specialprice, article);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ClientSpecialpriceDto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    appliedDate: ").append(toIndentedString(appliedDate)).append("\n");
    sb.append("    client: ").append(toIndentedString(client)).append("\n");
    sb.append("    specialprice: ").append(toIndentedString(specialprice)).append("\n");
    sb.append("    article: ").append(toIndentedString(article)).append("\n");
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

