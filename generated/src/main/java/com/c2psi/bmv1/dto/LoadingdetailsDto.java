package com.c2psi.bmv1.dto;

import java.util.Objects;
import com.c2psi.bmv1.dto.ArticleDto;
import com.c2psi.bmv1.dto.LoadingDto;
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
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-08T23:11:35.582723400+01:00[Africa/Douala]")
public class LoadingdetailsDto   {
  @JsonProperty("quantityTaken")
  private Double quantityTaken;

  @JsonProperty("quantityReturn")
  private Double quantityReturn;

  @JsonProperty("ldArticle")
  private ArticleDto ldArticle;

  @JsonProperty("ldLoading")
  private LoadingDto ldLoading;

  public LoadingdetailsDto quantityTaken(Double quantityTaken) {
    this.quantityTaken = quantityTaken;
    return this;
  }

  /**
   * Get quantityTaken
   * @return quantityTaken
  */
  @ApiModelProperty(value = "")


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
  @ApiModelProperty(value = "")


  public Double getQuantityReturn() {
    return quantityReturn;
  }

  public void setQuantityReturn(Double quantityReturn) {
    this.quantityReturn = quantityReturn;
  }

  public LoadingdetailsDto ldArticle(ArticleDto ldArticle) {
    this.ldArticle = ldArticle;
    return this;
  }

  /**
   * Get ldArticle
   * @return ldArticle
  */
  @ApiModelProperty(value = "")

  @Valid

  public ArticleDto getLdArticle() {
    return ldArticle;
  }

  public void setLdArticle(ArticleDto ldArticle) {
    this.ldArticle = ldArticle;
  }

  public LoadingdetailsDto ldLoading(LoadingDto ldLoading) {
    this.ldLoading = ldLoading;
    return this;
  }

  /**
   * Get ldLoading
   * @return ldLoading
  */
  @ApiModelProperty(value = "")

  @Valid

  public LoadingDto getLdLoading() {
    return ldLoading;
  }

  public void setLdLoading(LoadingDto ldLoading) {
    this.ldLoading = ldLoading;
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
    return Objects.equals(this.quantityTaken, loadingdetailsDto.quantityTaken) &&
        Objects.equals(this.quantityReturn, loadingdetailsDto.quantityReturn) &&
        Objects.equals(this.ldArticle, loadingdetailsDto.ldArticle) &&
        Objects.equals(this.ldLoading, loadingdetailsDto.ldLoading);
  }

  @Override
  public int hashCode() {
    return Objects.hash(quantityTaken, quantityReturn, ldArticle, ldLoading);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LoadingdetailsDto {\n");
    
    sb.append("    quantityTaken: ").append(toIndentedString(quantityTaken)).append("\n");
    sb.append("    quantityReturn: ").append(toIndentedString(quantityReturn)).append("\n");
    sb.append("    ldArticle: ").append(toIndentedString(ldArticle)).append("\n");
    sb.append("    ldLoading: ").append(toIndentedString(ldLoading)).append("\n");
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

