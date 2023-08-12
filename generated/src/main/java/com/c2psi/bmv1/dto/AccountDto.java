package com.c2psi.bmv1.dto;

import java.util.Objects;
import com.c2psi.bmv1.dto.ArticleDto;
import com.c2psi.bmv1.dto.ClientDto;
import com.c2psi.bmv1.dto.PackagingDto;
import com.c2psi.bmv1.dto.PointofsaleDto;
import com.c2psi.bmv1.dto.ProviderDto;
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
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-12T10:38:54.913224900+01:00[Africa/Douala]")
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

  @JsonProperty("accountClient")
  private ClientDto accountClient;

  @JsonProperty("accountPos")
  private PointofsaleDto accountPos;

  @JsonProperty("accountProvider")
  private ProviderDto accountProvider;

  @JsonProperty("accountArticle")
  private ArticleDto accountArticle;

  @JsonProperty("accountPackaging")
  private PackagingDto accountPackaging;

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

  public AccountDto accountClient(ClientDto accountClient) {
    this.accountClient = accountClient;
    return this;
  }

  /**
   * Get accountClient
   * @return accountClient
  */
  @ApiModelProperty(value = "")

  @Valid

  public ClientDto getAccountClient() {
    return accountClient;
  }

  public void setAccountClient(ClientDto accountClient) {
    this.accountClient = accountClient;
  }

  public AccountDto accountPos(PointofsaleDto accountPos) {
    this.accountPos = accountPos;
    return this;
  }

  /**
   * Get accountPos
   * @return accountPos
  */
  @ApiModelProperty(value = "")

  @Valid

  public PointofsaleDto getAccountPos() {
    return accountPos;
  }

  public void setAccountPos(PointofsaleDto accountPos) {
    this.accountPos = accountPos;
  }

  public AccountDto accountProvider(ProviderDto accountProvider) {
    this.accountProvider = accountProvider;
    return this;
  }

  /**
   * Get accountProvider
   * @return accountProvider
  */
  @ApiModelProperty(value = "")

  @Valid

  public ProviderDto getAccountProvider() {
    return accountProvider;
  }

  public void setAccountProvider(ProviderDto accountProvider) {
    this.accountProvider = accountProvider;
  }

  public AccountDto accountArticle(ArticleDto accountArticle) {
    this.accountArticle = accountArticle;
    return this;
  }

  /**
   * Get accountArticle
   * @return accountArticle
  */
  @ApiModelProperty(value = "")

  @Valid

  public ArticleDto getAccountArticle() {
    return accountArticle;
  }

  public void setAccountArticle(ArticleDto accountArticle) {
    this.accountArticle = accountArticle;
  }

  public AccountDto accountPackaging(PackagingDto accountPackaging) {
    this.accountPackaging = accountPackaging;
    return this;
  }

  /**
   * Get accountPackaging
   * @return accountPackaging
  */
  @ApiModelProperty(value = "")

  @Valid

  public PackagingDto getAccountPackaging() {
    return accountPackaging;
  }

  public void setAccountPackaging(PackagingDto accountPackaging) {
    this.accountPackaging = accountPackaging;
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
        Objects.equals(this.accountClient, accountDto.accountClient) &&
        Objects.equals(this.accountPos, accountDto.accountPos) &&
        Objects.equals(this.accountProvider, accountDto.accountProvider) &&
        Objects.equals(this.accountArticle, accountDto.accountArticle) &&
        Objects.equals(this.accountPackaging, accountDto.accountPackaging);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, coverNumber, damageNumber, packageNumber, accountType, accountClient, accountPos, accountProvider, accountArticle, accountPackaging);
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
    sb.append("    accountClient: ").append(toIndentedString(accountClient)).append("\n");
    sb.append("    accountPos: ").append(toIndentedString(accountPos)).append("\n");
    sb.append("    accountProvider: ").append(toIndentedString(accountProvider)).append("\n");
    sb.append("    accountArticle: ").append(toIndentedString(accountArticle)).append("\n");
    sb.append("    accountPackaging: ").append(toIndentedString(accountPackaging)).append("\n");
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

