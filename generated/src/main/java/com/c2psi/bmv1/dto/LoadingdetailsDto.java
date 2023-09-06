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
 * A Loading details in the system
 */
@ApiModel(description = "A Loading details in the system")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-05T20:37:01.434321300+01:00[Africa/Casablanca]")
public class LoadingdetailsDto   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("quantityTaken")
  private Double quantityTaken;

  @JsonProperty("quantityReturn")
  private Double quantityReturn;

  @JsonProperty("ldArticleId")
  private Long ldArticleId;

  @JsonProperty("ldLoadingId")
  private Long ldLoadingId;

  public LoadingdetailsDto id(Long id) {
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

  public LoadingdetailsDto quantityTaken(Double quantityTaken) {
    this.quantityTaken = quantityTaken;
    return this;
  }

  /**
   * Get quantityTaken
   * @return quantityTaken
  */
  @ApiModelProperty(example = "1", value = "")


  public Double getQuantityTaken() {
    return quantityTaken;
  }

  public void setQuantityTaken(Double quantityTaken) {
    this.quantityTaken = quantityTaken;
  }

  public LoadingdetailsDto quantityReturn(Double quantityReturn) {
    this.quantityReturn = quantityReturn;
    return this;
  }

  /**
   * Get quantityReturn
   * @return quantityReturn
  */
  @ApiModelProperty(example = "0", value = "")


  public Double getQuantityReturn() {
    return quantityReturn;
  }

  public void setQuantityReturn(Double quantityReturn) {
    this.quantityReturn = quantityReturn;
  }

  public LoadingdetailsDto ldArticleId(Long ldArticleId) {
    this.ldArticleId = ldArticleId;
    return this;
  }

  /**
   * Get ldArticleId
   * @return ldArticleId
  */
  @ApiModelProperty(value = "")


  public Long getLdArticleId() {
    return ldArticleId;
  }

  public void setLdArticleId(Long ldArticleId) {
    this.ldArticleId = ldArticleId;
  }

  public LoadingdetailsDto ldLoadingId(Long ldLoadingId) {
    this.ldLoadingId = ldLoadingId;
    return this;
  }

  /**
   * Get ldLoadingId
   * @return ldLoadingId
  */
  @ApiModelProperty(value = "")


  public Long getLdLoadingId() {
    return ldLoadingId;
  }

  public void setLdLoadingId(Long ldLoadingId) {
    this.ldLoadingId = ldLoadingId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LoadingdetailsDto loadingdetailsDto = (LoadingdetailsDto) o;
    return Objects.equals(this.id, loadingdetailsDto.id) &&
        Objects.equals(this.quantityTaken, loadingdetailsDto.quantityTaken) &&
        Objects.equals(this.quantityReturn, loadingdetailsDto.quantityReturn) &&
        Objects.equals(this.ldArticleId, loadingdetailsDto.ldArticleId) &&
        Objects.equals(this.ldLoadingId, loadingdetailsDto.ldLoadingId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, quantityTaken, quantityReturn, ldArticleId, ldLoadingId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LoadingdetailsDto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    quantityTaken: ").append(toIndentedString(quantityTaken)).append("\n");
    sb.append("    quantityReturn: ").append(toIndentedString(quantityReturn)).append("\n");
    sb.append("    ldArticleId: ").append(toIndentedString(ldArticleId)).append("\n");
    sb.append("    ldLoadingId: ").append(toIndentedString(ldLoadingId)).append("\n");
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

