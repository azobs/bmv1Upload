package com.c2psi.bmv1.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * A token to open a session on the system
 */
@ApiModel(description = "A token to open a session on the system")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-16T08:50:41.193143300+01:00[Africa/Douala]")
public class TokenDto   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("tokenValue")
  private String tokenValue;

  /**
   * Gets or Sets tokenType
   */
  public enum TokenTypeEnum {
    BEARER("Bearer"),
    
    JWT("Jwt");

    private String value;

    TokenTypeEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static TokenTypeEnum fromValue(String value) {
      for (TokenTypeEnum b : TokenTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("tokenType")
  private TokenTypeEnum tokenType;

  @JsonProperty("expired")
  private Boolean expired;

  @JsonProperty("revoked")
  private Boolean revoked;

  @JsonProperty("tokenUserbmId")
  private Long tokenUserbmId;

  public TokenDto id(Long id) {
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

  public TokenDto tokenValue(String tokenValue) {
    this.tokenValue = tokenValue;
    return this;
  }

  /**
   * Get tokenValue
   * @return tokenValue
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getTokenValue() {
    return tokenValue;
  }

  public void setTokenValue(String tokenValue) {
    this.tokenValue = tokenValue;
  }

  public TokenDto tokenType(TokenTypeEnum tokenType) {
    this.tokenType = tokenType;
    return this;
  }

  /**
   * Get tokenType
   * @return tokenType
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public TokenTypeEnum getTokenType() {
    return tokenType;
  }

  public void setTokenType(TokenTypeEnum tokenType) {
    this.tokenType = tokenType;
  }

  public TokenDto expired(Boolean expired) {
    this.expired = expired;
    return this;
  }

  /**
   * Get expired
   * @return expired
  */
  @ApiModelProperty(value = "")


  public Boolean getExpired() {
    return expired;
  }

  public void setExpired(Boolean expired) {
    this.expired = expired;
  }

  public TokenDto revoked(Boolean revoked) {
    this.revoked = revoked;
    return this;
  }

  /**
   * Get revoked
   * @return revoked
  */
  @ApiModelProperty(value = "")


  public Boolean getRevoked() {
    return revoked;
  }

  public void setRevoked(Boolean revoked) {
    this.revoked = revoked;
  }

  public TokenDto tokenUserbmId(Long tokenUserbmId) {
    this.tokenUserbmId = tokenUserbmId;
    return this;
  }

  /**
   * Get tokenUserbmId
   * @return tokenUserbmId
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Long getTokenUserbmId() {
    return tokenUserbmId;
  }

  public void setTokenUserbmId(Long tokenUserbmId) {
    this.tokenUserbmId = tokenUserbmId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TokenDto tokenDto = (TokenDto) o;
    return Objects.equals(this.id, tokenDto.id) &&
        Objects.equals(this.tokenValue, tokenDto.tokenValue) &&
        Objects.equals(this.tokenType, tokenDto.tokenType) &&
        Objects.equals(this.expired, tokenDto.expired) &&
        Objects.equals(this.revoked, tokenDto.revoked) &&
        Objects.equals(this.tokenUserbmId, tokenDto.tokenUserbmId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, tokenValue, tokenType, expired, revoked, tokenUserbmId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TokenDto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    tokenValue: ").append(toIndentedString(tokenValue)).append("\n");
    sb.append("    tokenType: ").append(toIndentedString(tokenType)).append("\n");
    sb.append("    expired: ").append(toIndentedString(expired)).append("\n");
    sb.append("    revoked: ").append(toIndentedString(revoked)).append("\n");
    sb.append("    tokenUserbmId: ").append(toIndentedString(tokenUserbmId)).append("\n");
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

