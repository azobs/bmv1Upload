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
 * A Backin in the system which represent articles return back by a client after delivery
 */
@ApiModel(description = "A Backin in the system which represent articles return back by a client after delivery")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-26T01:24:15.865861+01:00[Africa/Casablanca]")
public class BackindetailsDto   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("bidQuantity")
  private Double bidQuantity;

  @JsonProperty("bidComment")
  private String bidComment;

  @JsonProperty("bidArticleId")
  private Long bidArticleId;

  @JsonProperty("bidBackinId")
  private Long bidBackinId;

  public BackindetailsDto id(Long id) {
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

  public BackindetailsDto bidQuantity(Double bidQuantity) {
    this.bidQuantity = bidQuantity;
    return this;
  }

  /**
   * Get bidQuantity
   * @return bidQuantity
  */
  @ApiModelProperty(example = "0", value = "")


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

  public BackindetailsDto bidArticleId(Long bidArticleId) {
    this.bidArticleId = bidArticleId;
    return this;
  }

  /**
   * Get bidArticleId
   * @return bidArticleId
  */
  @ApiModelProperty(value = "")


  public Long getBidArticleId() {
    return bidArticleId;
  }

  public void setBidArticleId(Long bidArticleId) {
    this.bidArticleId = bidArticleId;
  }

  public BackindetailsDto bidBackinId(Long bidBackinId) {
    this.bidBackinId = bidBackinId;
    return this;
  }

  /**
   * Get bidBackinId
   * @return bidBackinId
  */
  @ApiModelProperty(value = "")


  public Long getBidBackinId() {
    return bidBackinId;
  }

  public void setBidBackinId(Long bidBackinId) {
    this.bidBackinId = bidBackinId;
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
    return Objects.equals(this.id, backindetailsDto.id) &&
        Objects.equals(this.bidQuantity, backindetailsDto.bidQuantity) &&
        Objects.equals(this.bidComment, backindetailsDto.bidComment) &&
        Objects.equals(this.bidArticleId, backindetailsDto.bidArticleId) &&
        Objects.equals(this.bidBackinId, backindetailsDto.bidBackinId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, bidQuantity, bidComment, bidArticleId, bidBackinId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BackindetailsDto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    bidQuantity: ").append(toIndentedString(bidQuantity)).append("\n");
    sb.append("    bidComment: ").append(toIndentedString(bidComment)).append("\n");
    sb.append("    bidArticleId: ").append(toIndentedString(bidArticleId)).append("\n");
    sb.append("    bidBackinId: ").append(toIndentedString(bidBackinId)).append("\n");
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

