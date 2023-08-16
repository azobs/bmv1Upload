package com.c2psi.bmv1.dto;

import java.util.Objects;
import com.c2psi.bmv1.dto.AddressDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * UserbmDto
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-16T08:50:41.193143300+01:00[Africa/Douala]")
public class UserbmDto   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("userLogin")
  private String userLogin;

  @JsonProperty("userCni")
  private String userCni;

  @JsonProperty("userPassword")
  private String userPassword;

  @JsonProperty("userRepassword")
  private String userRepassword;

  @JsonProperty("userName")
  private String userName;

  @JsonProperty("userSurname")
  private String userSurname;

  @JsonProperty("userDob")
  @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE)
  private LocalDate userDob;

  @JsonProperty("userPicture")
  private String userPicture;

  /**
   * Gets or Sets userState
   */
  public enum UserStateEnum {
    ACTIVATED("Activated"),
    
    DEACTIVATED("Deactivated"),
    
    CONNECTED("Connected"),
    
    DISCONNECTED("Disconnected");

    private String value;

    UserStateEnum(String value) {
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
    public static UserStateEnum fromValue(String value) {
      for (UserStateEnum b : UserStateEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("userState")
  private UserStateEnum userState;

  @JsonProperty("userAddress")
  private AddressDto userAddress;

  public UserbmDto id(Long id) {
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

  public UserbmDto userLogin(String userLogin) {
    this.userLogin = userLogin;
    return this;
  }

  /**
   * Get userLogin
   * @return userLogin
  */
  @ApiModelProperty(example = "login", value = "")

@Size(min = 3, max = 15) 
  public String getUserLogin() {
    return userLogin;
  }

  public void setUserLogin(String userLogin) {
    this.userLogin = userLogin;
  }

  public UserbmDto userCni(String userCni) {
    this.userCni = userCni;
    return this;
  }

  /**
   * Get userCni
   * @return userCni
  */
  @ApiModelProperty(example = "107235260", value = "")

@Size(min = 9, max = 17) 
  public String getUserCni() {
    return userCni;
  }

  public void setUserCni(String userCni) {
    this.userCni = userCni;
  }

  public UserbmDto userPassword(String userPassword) {
    this.userPassword = userPassword;
    return this;
  }

  /**
   * Get userPassword
   * @return userPassword
  */
  @ApiModelProperty(example = "password", value = "")

@Size(min = 4) 
  public String getUserPassword() {
    return userPassword;
  }

  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }

  public UserbmDto userRepassword(String userRepassword) {
    this.userRepassword = userRepassword;
    return this;
  }

  /**
   * Get userRepassword
   * @return userRepassword
  */
  @ApiModelProperty(example = "password", value = "")

@Size(min = 4) 
  public String getUserRepassword() {
    return userRepassword;
  }

  public void setUserRepassword(String userRepassword) {
    this.userRepassword = userRepassword;
  }

  public UserbmDto userName(String userName) {
    this.userName = userName;
    return this;
  }

  /**
   * Get userName
   * @return userName
  */
  @ApiModelProperty(example = "userName", value = "")

@Size(min = 2, max = 30) 
  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public UserbmDto userSurname(String userSurname) {
    this.userSurname = userSurname;
    return this;
  }

  /**
   * Get userSurname
   * @return userSurname
  */
  @ApiModelProperty(value = "")

@Size(min = 2, max = 30) 
  public String getUserSurname() {
    return userSurname;
  }

  public void setUserSurname(String userSurname) {
    this.userSurname = userSurname;
  }

  public UserbmDto userDob(LocalDate userDob) {
    this.userDob = userDob;
    return this;
  }

  /**
   * Get userDob
   * @return userDob
  */
  @ApiModelProperty(value = "")

  @Valid
@Size(min = 10, max = 19) 
  public LocalDate getUserDob() {
    return userDob;
  }

  public void setUserDob(LocalDate userDob) {
    this.userDob = userDob;
  }

  public UserbmDto userPicture(String userPicture) {
    this.userPicture = userPicture;
    return this;
  }

  /**
   * Get userPicture
   * @return userPicture
  */
  @ApiModelProperty(value = "")

@Size(min = 1, max = 15) 
  public String getUserPicture() {
    return userPicture;
  }

  public void setUserPicture(String userPicture) {
    this.userPicture = userPicture;
  }

  public UserbmDto userState(UserStateEnum userState) {
    this.userState = userState;
    return this;
  }

  /**
   * Get userState
   * @return userState
  */
  @ApiModelProperty(value = "")


  public UserStateEnum getUserState() {
    return userState;
  }

  public void setUserState(UserStateEnum userState) {
    this.userState = userState;
  }

  public UserbmDto userAddress(AddressDto userAddress) {
    this.userAddress = userAddress;
    return this;
  }

  /**
   * Get userAddress
   * @return userAddress
  */
  @ApiModelProperty(value = "")

  @Valid

  public AddressDto getUserAddress() {
    return userAddress;
  }

  public void setUserAddress(AddressDto userAddress) {
    this.userAddress = userAddress;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserbmDto userbmDto = (UserbmDto) o;
    return Objects.equals(this.id, userbmDto.id) &&
        Objects.equals(this.userLogin, userbmDto.userLogin) &&
        Objects.equals(this.userCni, userbmDto.userCni) &&
        Objects.equals(this.userPassword, userbmDto.userPassword) &&
        Objects.equals(this.userRepassword, userbmDto.userRepassword) &&
        Objects.equals(this.userName, userbmDto.userName) &&
        Objects.equals(this.userSurname, userbmDto.userSurname) &&
        Objects.equals(this.userDob, userbmDto.userDob) &&
        Objects.equals(this.userPicture, userbmDto.userPicture) &&
        Objects.equals(this.userState, userbmDto.userState) &&
        Objects.equals(this.userAddress, userbmDto.userAddress);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, userLogin, userCni, userPassword, userRepassword, userName, userSurname, userDob, userPicture, userState, userAddress);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserbmDto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    userLogin: ").append(toIndentedString(userLogin)).append("\n");
    sb.append("    userCni: ").append(toIndentedString(userCni)).append("\n");
    sb.append("    userPassword: ").append(toIndentedString(userPassword)).append("\n");
    sb.append("    userRepassword: ").append(toIndentedString(userRepassword)).append("\n");
    sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
    sb.append("    userSurname: ").append(toIndentedString(userSurname)).append("\n");
    sb.append("    userDob: ").append(toIndentedString(userDob)).append("\n");
    sb.append("    userPicture: ").append(toIndentedString(userPicture)).append("\n");
    sb.append("    userState: ").append(toIndentedString(userState)).append("\n");
    sb.append("    userAddress: ").append(toIndentedString(userAddress)).append("\n");
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

