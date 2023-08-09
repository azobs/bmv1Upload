package com.c2psi.bmv1.dto;

import java.util.Objects;
import com.c2psi.bmv1.dto.ArticleDto;
import com.c2psi.bmv1.dto.BackinDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * A Backin in the system which represent articles return back by a client after delivery
 */
@ApiModel(description = "A Backin in the system which represent articles return back by a client after delivery")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-08T23:11:35.582723400+01:00[Africa/Douala]")
public class BackindetailsDto   {
  @JsonProperty("bidQuantity")
  private Double bidQuantity;

  @JsonProperty("bidComment")
  private String bidComment;

  @JsonProperty("bidArticle")
  private ArticleDto bidArticle;

  @JsonProperty("bidBackin")
  private BackinDto bidBackin;

  public BackindetailsDto bidQuantity(Double bidQuantity) {
    this.bidQuantity = bidQuantity;
    return this;
  }

  /**
   * Get bidQuantity
   * @return bidQuantity
  */
  @ApiModelProperty(value = "")


  public Double getBidQuantity() {
    return bidQuantity;
  }

  public void setBidQuantity(Double bidQuantity) {
    this.bidQuantity = bidQuantity;
  }

  public BackindetailsDto bidComment(String bidComment) {
    this.bidComment = bidComment;
    return this;
  }

  /**
   * Get bidComment
   * @return bidComment
  */
  @ApiModelProperty(value = "")


  public String getBidComment() {
    return bidComment;
  }

  public void setBidComment(String bidComment) {
    this.bidComment = bidComment;
  }

  public BackindetailsDto bidArticle(ArticleDto bidArticle) {
    this.bidArticle = bidArticle;
    return this;
  }

  /**
   * Get bidArticle
   * @return bidArticle
  */
  @ApiModelProperty(value = "")

  @Valid

  public ArticleDto getBidArticle() {
    return bidArticle;
  }

  public void setBidArticle(ArticleDto bidArticle) {
    this.bidArticle = bidArticle;
  }

  public BackindetailsDto bidBackin(BackinDto bidBackin) {
    this.bidBackin = bidBackin;
    return this;
  }

  /**
   * Get bidBackin
   * @return bidBackin
  */
  @ApiModelProperty(value = "")

  @Valid

  public BackinDto getBidBackin() {
    return bidBackin;
  }

  public void setBidBackin(BackinDto bidBackin) {
    this.bidBackin = bidBackin;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BackindetailsDto backindetailsDto = (BackindetailsDto) o;
    return Objects.equals(this.bidQuantity, backindetailsDto.bidQuantity) &&
        Objects.equals(this.bidComment, backindetailsDto.bidComment) &&
        Objects.equals(this.bidArticle, backindetailsDto.bidArticle) &&
        Objects.equals(this.bidBackin, backindetailsDto.bidBackin);
  }

  @Override
  public int hashCode() {
    return Objects.hash(bidQuantity, bidComment, bidArticle, bidBackin);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BackindetailsDto {\n");
    
    sb.append("    bidQuantity: ").append(toIndentedString(bidQuantity)).append("\n");
    sb.append("    bidComment: ").append(toIndentedString(bidComment)).append("\n");
    sb.append("    bidArticle: ").append(toIndentedString(bidArticle)).append("\n");
    sb.append("    bidBackin: ").append(toIndentedString(bidBackin)).append("\n");
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

