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
 * An account for pointofsale, provider or a client in the system
 */
@ApiModel(description = "An account for pointofsale, provider or a client in the system")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-28T04:24:19.978343600+01:00[Africa/Casablanca]")
public class AccountDto   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("coverNumber")
  private Integer coverNumber;

  @JsonProperty("damageNumber")
  private Integer damageNumber;

  @JsonProperty("packageNumber")
  private Integer packageNumber;

  /**
   * Gets or Sets accountType
   */
  public enum AccountTypeEnum {
    CLIENT("Client"),
    
    POS("Pos"),
    
    PROVIDER("Provider");

    private String value;

    AccountTypeEnum(String value) {
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
    public static AccountTypeEnum fromValue(String value) {
      for (AccountTypeEnum b : AccountTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("accountType")
  private AccountTypeEnum accountType;

  @JsonProperty("accountClientId")
  private Long accountClientId;

  @JsonProperty("accountPosId")
  private Long accountPosId;

  @JsonProperty("accountProviderId")
  private Long accountProviderId;

  @JsonProperty("accountArticleId")
  private Long accountArticleId;

  @JsonProperty("accountPackagingId")
  private Long accountPackagingId;

  public AccountDto id(Long id) {
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

  public AccountDto coverNumber(Integer coverNumber) {
    this.coverNumber = coverNumber;
    return this;
  }

  /**
   * Get coverNumber
   * minimum: 0
   * @return coverNumber
  */
  @ApiModelProperty(example = "0", value = "")

@Min(0) 
  public Integer getCoverNumber() {
    return coverNumber;
  }

  public void setCoverNumber(Integer coverNumber) {
    this.coverNumber = coverNumber;
  }

  public AccountDto damageNumber(Integer damageNumber) {
    this.damageNumber = damageNumber;
    return this;
  }

  /**
   * Get damageNumber
   * minimum: 0
   * @return damageNumber
  */
  @ApiModelProperty(example = "0", value = "")

@Min(0) 
  public Integer getDamageNumber() {
    return damageNumber;
  }

  public void setDamageNumber(Integer damageNumber) {
    this.damageNumber = damageNumber;
  }

  public AccountDto packageNumber(Integer packageNumber) {
    this.packageNumber = packageNumber;
    return this;
  }

  /**
   * Get packageNumber
   * minimum: 0
   * @return packageNumber
  */
  @ApiModelProperty(example = "0", value = "")

@Min(0) 
  public Integer getPackageNumber() {
    return packageNumber;
  }

  public void setPackageNumber(Integer packageNumber) {
    this.packageNumber = packageNumber;
  }

  public AccountDto accountType(AccountTypeEnum accountType) {
    this.accountType = accountType;
    return this;
  }

  /**
   * Get accountType
   * @return accountType
  */
  @ApiModelProperty(example = "Pos", value = "")


  public AccountTypeEnum getAccountType() {
    return accountType;
  }

  public void setAccountType(AccountTypeEnum accountType) {
    this.accountType = accountType;
  }

  public AccountDto accountClientId(Long accountClientId) {
    this.accountClientId = accountClientId;
    return this;
  }

  /**
   * Get accountClientId
   * @return accountClientId
  */
  @ApiModelProperty(value = "")


  public Long getAccountClientId() {
    return accountClientId;
  }

  public void setAccountClientId(Long accountClientId) {
    this.accountClientId = accountClientId;
  }

  public AccountDto accountPosId(Long accountPosId) {
    this.accountPosId = accountPosId;
    return this;
  }

  /**
   * Get accountPosId
   * @return accountPosId
  */
  @ApiModelProperty(value = "")


  public Long getAccountPosId() {
    return accountPosId;
  }

  public void setAccountPosId(Long accountPosId) {
    this.accountPosId = accountPosId;
  }

  public AccountDto accountProviderId(Long accountProviderId) {
    this.accountProviderId = accountProviderId;
    return this;
  }

  /**
   * Get accountProviderId
   * @return accountProviderId
  */
  @ApiModelProperty(value = "")


  public Long getAccountProviderId() {
    return accountProviderId;
  }

  public void setAccountProviderId(Long accountProviderId) {
    this.accountProviderId = accountProviderId;
  }

  public AccountDto accountArticleId(Long accountArticleId) {
    this.accountArticleId = accountArticleId;
    return this;
  }

  /**
   * Get accountArticleId
   * @return accountArticleId
  */
  @ApiModelProperty(value = "")


  public Long getAccountArticleId() {
    return accountArticleId;
  }

  public void setAccountArticleId(Long accountArticleId) {
    this.accountArticleId = accountArticleId;
  }

  public AccountDto accountPackagingId(Long accountPackagingId) {
    this.accountPackagingId = accountPackagingId;
    return this;
  }

  /**
   * Get accountPackagingId
   * @return accountPackagingId
  */
  @ApiModelProperty(value = "")


  public Long getAccountPackagingId() {
    return accountPackagingId;
  }

  public void setAccountPackagingId(Long accountPackagingId) {
    this.accountPackagingId = accountPackagingId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccountDto accountDto = (AccountDto) o;
    return Objects.equals(this.id, accountDto.id) &&
        Objects.equals(this.coverNumber, accountDto.coverNumber) &&
        Objects.equals(this.damageNumber, accountDto.damageNumber) &&
        Objects.equals(this.packageNumber, accountDto.packageNumber) &&
        Objects.equals(this.accountType, accountDto.accountType) &&
        Objects.equals(this.accountClientId, accountDto.accountClientId) &&
        Objects.equals(this.accountPosId, accountDto.accountPosId) &&
        Objects.equals(this.accountProviderId, accountDto.accountProviderId) &&
        Objects.equals(this.accountArticleId, accountDto.accountArticleId) &&
        Objects.equals(this.accountPackagingId, accountDto.accountPackagingId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, coverNumber, damageNumber, packageNumber, accountType, accountClientId, accountPosId, accountProviderId, accountArticleId, accountPackagingId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountDto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    coverNumber: ").append(toIndentedString(coverNumber)).append("\n");
    sb.append("    damageNumber: ").append(toIndentedString(damageNumber)).append("\n");
    sb.append("    packageNumber: ").append(toIndentedString(packageNumber)).append("\n");
    sb.append("    accountType: ").append(toIndentedString(accountType)).append("\n");
    sb.append("    accountClientId: ").append(toIndentedString(accountClientId)).append("\n");
    sb.append("    accountPosId: ").append(toIndentedString(accountPosId)).append("\n");
    sb.append("    accountProviderId: ").append(toIndentedString(accountProviderId)).append("\n");
    sb.append("    accountArticleId: ").append(toIndentedString(accountArticleId)).append("\n");
    sb.append("    accountPackagingId: ").append(toIndentedString(accountPackagingId)).append("\n");
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

