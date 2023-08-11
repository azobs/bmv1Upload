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
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-11T16:08:25.464702700+01:00[Africa/Douala]")
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

  @JsonProperty("accountClientDto")
  private ClientDto accountClientDto;

  @JsonProperty("accountPosDto")
  private PointofsaleDto accountPosDto;

  @JsonProperty("accountProviderDto")
  private ProviderDto accountProviderDto;

  @JsonProperty("accountArticleDto")
  private ArticleDto accountArticleDto;

  @JsonProperty("accountPackagingDto")
  private PackagingDto accountPackagingDto;

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

  public AccountDto accountClientDto(ClientDto accountClientDto) {
    this.accountClientDto = accountClientDto;
    return this;
  }

  /**
   * Get accountClientDto
   * @return accountClientDto
  */
  @ApiModelProperty(value = "")

  @Valid

  public ClientDto getAccountClientDto() {
    return accountClientDto;
  }

  public void setAccountClientDto(ClientDto accountClientDto) {
    this.accountClientDto = accountClientDto;
  }

  public AccountDto accountPosDto(PointofsaleDto accountPosDto) {
    this.accountPosDto = accountPosDto;
    return this;
  }

  /**
   * Get accountPosDto
   * @return accountPosDto
  */
  @ApiModelProperty(value = "")

  @Valid

  public PointofsaleDto getAccountPosDto() {
    return accountPosDto;
  }

  public void setAccountPosDto(PointofsaleDto accountPosDto) {
    this.accountPosDto = accountPosDto;
  }

  public AccountDto accountProviderDto(ProviderDto accountProviderDto) {
    this.accountProviderDto = accountProviderDto;
    return this;
  }

  /**
   * Get accountProviderDto
   * @return accountProviderDto
  */
  @ApiModelProperty(value = "")

  @Valid

  public ProviderDto getAccountProviderDto() {
    return accountProviderDto;
  }

  public void setAccountProviderDto(ProviderDto accountProviderDto) {
    this.accountProviderDto = accountProviderDto;
  }

  public AccountDto accountArticleDto(ArticleDto accountArticleDto) {
    this.accountArticleDto = accountArticleDto;
    return this;
  }

  /**
   * Get accountArticleDto
   * @return accountArticleDto
  */
  @ApiModelProperty(value = "")

  @Valid

  public ArticleDto getAccountArticleDto() {
    return accountArticleDto;
  }

  public void setAccountArticleDto(ArticleDto accountArticleDto) {
    this.accountArticleDto = accountArticleDto;
  }

  public AccountDto accountPackagingDto(PackagingDto accountPackagingDto) {
    this.accountPackagingDto = accountPackagingDto;
    return this;
  }

  /**
   * Get accountPackagingDto
   * @return accountPackagingDto
  */
  @ApiModelProperty(value = "")

  @Valid

  public PackagingDto getAccountPackagingDto() {
    return accountPackagingDto;
  }

  public void setAccountPackagingDto(PackagingDto accountPackagingDto) {
    this.accountPackagingDto = accountPackagingDto;
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
        Objects.equals(this.accountClientDto, accountDto.accountClientDto) &&
        Objects.equals(this.accountPosDto, accountDto.accountPosDto) &&
        Objects.equals(this.accountProviderDto, accountDto.accountProviderDto) &&
        Objects.equals(this.accountArticleDto, accountDto.accountArticleDto) &&
        Objects.equals(this.accountPackagingDto, accountDto.accountPackagingDto);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, coverNumber, damageNumber, packageNumber, accountType, accountClientDto, accountPosDto, accountProviderDto, accountArticleDto, accountPackagingDto);
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
    sb.append("    accountClientDto: ").append(toIndentedString(accountClientDto)).append("\n");
    sb.append("    accountPosDto: ").append(toIndentedString(accountPosDto)).append("\n");
    sb.append("    accountProviderDto: ").append(toIndentedString(accountProviderDto)).append("\n");
    sb.append("    accountArticleDto: ").append(toIndentedString(accountArticleDto)).append("\n");
    sb.append("    accountPackagingDto: ").append(toIndentedString(accountPackagingDto)).append("\n");
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

