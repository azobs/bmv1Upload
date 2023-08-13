package com.c2psi.bmv1.dto;

import java.util.Objects;
import com.c2psi.bmv1.dto.ClientDto;
import com.c2psi.bmv1.dto.DeliveryDto;
import com.c2psi.bmv1.dto.LoadingDto;
import com.c2psi.bmv1.dto.PointofsaleDto;
import com.c2psi.bmv1.dto.SaleinvoiceDto;
import com.c2psi.bmv1.dto.UserbmDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.OffsetDateTime;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * A Command in the system
 */
@ApiModel(description = "A Command in the system")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-13T03:59:42.033168+01:00[Africa/Douala]")
public class CommandDto   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("cmdCode")
  private String cmdCode;

  @JsonProperty("cmdDate")
  @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime cmdDate;

  @JsonProperty("cmdComment")
  private String cmdComment;

  /**
   * Gets or Sets cmdState
   */
  public enum CmdStateEnum {
    INEDITING("InEditing"),
    
    EDITED("Edited"),
    
    INDELIVERY("InDelivery"),
    
    DELIVERY("Delivery");

    private String value;

    CmdStateEnum(String value) {
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
    public static CmdStateEnum fromValue(String value) {
      for (CmdStateEnum b : CmdStateEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("cmdState")
  private CmdStateEnum cmdState;

  /**
   * Gets or Sets cmdNature
   */
  public enum CmdNatureEnum {
    CASH("Cash"),
    
    COVER("Cover"),
    
    DAMAGE("Damage");

    private String value;

    CmdNatureEnum(String value) {
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
    public static CmdNatureEnum fromValue(String value) {
      for (CmdNatureEnum b : CmdNatureEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("cmdNature")
  private CmdNatureEnum cmdNature;

  @JsonProperty("cmdDelivery")
  private DeliveryDto cmdDelivery;

  @JsonProperty("cmdLoading")
  private LoadingDto cmdLoading;

  @JsonProperty("cmdClient")
  private ClientDto cmdClient;

  @JsonProperty("cmdSaler")
  private UserbmDto cmdSaler;

  @JsonProperty("cmdInvoice")
  private SaleinvoiceDto cmdInvoice;

  @JsonProperty("cmdPos")
  private PointofsaleDto cmdPos;

  public CommandDto id(Long id) {
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

  public CommandDto cmdCode(String cmdCode) {
    this.cmdCode = cmdCode;
    return this;
  }

  /**
   * Get cmdCode
   * @return cmdCode
  */
  @ApiModelProperty(example = "C0000", value = "")


  public String getCmdCode() {
    return cmdCode;
  }

  public void setCmdCode(String cmdCode) {
    this.cmdCode = cmdCode;
  }

  public CommandDto cmdDate(OffsetDateTime cmdDate) {
    this.cmdDate = cmdDate;
    return this;
  }

  /**
   * Get cmdDate
   * @return cmdDate
  */
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getCmdDate() {
    return cmdDate;
  }

  public void setCmdDate(OffsetDateTime cmdDate) {
    this.cmdDate = cmdDate;
  }

  public CommandDto cmdComment(String cmdComment) {
    this.cmdComment = cmdComment;
    return this;
  }

  /**
   * Get cmdComment
   * @return cmdComment
  */
  @ApiModelProperty(value = "")


  public String getCmdComment() {
    return cmdComment;
  }

  public void setCmdComment(String cmdComment) {
    this.cmdComment = cmdComment;
  }

  public CommandDto cmdState(CmdStateEnum cmdState) {
    this.cmdState = cmdState;
    return this;
  }

  /**
   * Get cmdState
   * @return cmdState
  */
  @ApiModelProperty(example = "InEditing", value = "")


  public CmdStateEnum getCmdState() {
    return cmdState;
  }

  public void setCmdState(CmdStateEnum cmdState) {
    this.cmdState = cmdState;
  }

  public CommandDto cmdNature(CmdNatureEnum cmdNature) {
    this.cmdNature = cmdNature;
    return this;
  }

  /**
   * Get cmdNature
   * @return cmdNature
  */
  @ApiModelProperty(example = "Cash", value = "")


  public CmdNatureEnum getCmdNature() {
    return cmdNature;
  }

  public void setCmdNature(CmdNatureEnum cmdNature) {
    this.cmdNature = cmdNature;
  }

  public CommandDto cmdDelivery(DeliveryDto cmdDelivery) {
    this.cmdDelivery = cmdDelivery;
    return this;
  }

  /**
   * Get cmdDelivery
   * @return cmdDelivery
  */
  @ApiModelProperty(value = "")

  @Valid

  public DeliveryDto getCmdDelivery() {
    return cmdDelivery;
  }

  public void setCmdDelivery(DeliveryDto cmdDelivery) {
    this.cmdDelivery = cmdDelivery;
  }

  public CommandDto cmdLoading(LoadingDto cmdLoading) {
    this.cmdLoading = cmdLoading;
    return this;
  }

  /**
   * Get cmdLoading
   * @return cmdLoading
  */
  @ApiModelProperty(value = "")

  @Valid

  public LoadingDto getCmdLoading() {
    return cmdLoading;
  }

  public void setCmdLoading(LoadingDto cmdLoading) {
    this.cmdLoading = cmdLoading;
  }

  public CommandDto cmdClient(ClientDto cmdClient) {
    this.cmdClient = cmdClient;
    return this;
  }

  /**
   * Get cmdClient
   * @return cmdClient
  */
  @ApiModelProperty(value = "")

  @Valid

  public ClientDto getCmdClient() {
    return cmdClient;
  }

  public void setCmdClient(ClientDto cmdClient) {
    this.cmdClient = cmdClient;
  }

  public CommandDto cmdSaler(UserbmDto cmdSaler) {
    this.cmdSaler = cmdSaler;
    return this;
  }

  /**
   * Get cmdSaler
   * @return cmdSaler
  */
  @ApiModelProperty(value = "")

  @Valid

  public UserbmDto getCmdSaler() {
    return cmdSaler;
  }

  public void setCmdSaler(UserbmDto cmdSaler) {
    this.cmdSaler = cmdSaler;
  }

  public CommandDto cmdInvoice(SaleinvoiceDto cmdInvoice) {
    this.cmdInvoice = cmdInvoice;
    return this;
  }

  /**
   * Get cmdInvoice
   * @return cmdInvoice
  */
  @ApiModelProperty(value = "")

  @Valid

  public SaleinvoiceDto getCmdInvoice() {
    return cmdInvoice;
  }

  public void setCmdInvoice(SaleinvoiceDto cmdInvoice) {
    this.cmdInvoice = cmdInvoice;
  }

  public CommandDto cmdPos(PointofsaleDto cmdPos) {
    this.cmdPos = cmdPos;
    return this;
  }

  /**
   * Get cmdPos
   * @return cmdPos
  */
  @ApiModelProperty(value = "")

  @Valid

  public PointofsaleDto getCmdPos() {
    return cmdPos;
  }

  public void setCmdPos(PointofsaleDto cmdPos) {
    this.cmdPos = cmdPos;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CommandDto commandDto = (CommandDto) o;
    return Objects.equals(this.id, commandDto.id) &&
        Objects.equals(this.cmdCode, commandDto.cmdCode) &&
        Objects.equals(this.cmdDate, commandDto.cmdDate) &&
        Objects.equals(this.cmdComment, commandDto.cmdComment) &&
        Objects.equals(this.cmdState, commandDto.cmdState) &&
        Objects.equals(this.cmdNature, commandDto.cmdNature) &&
        Objects.equals(this.cmdDelivery, commandDto.cmdDelivery) &&
        Objects.equals(this.cmdLoading, commandDto.cmdLoading) &&
        Objects.equals(this.cmdClient, commandDto.cmdClient) &&
        Objects.equals(this.cmdSaler, commandDto.cmdSaler) &&
        Objects.equals(this.cmdInvoice, commandDto.cmdInvoice) &&
        Objects.equals(this.cmdPos, commandDto.cmdPos);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, cmdCode, cmdDate, cmdComment, cmdState, cmdNature, cmdDelivery, cmdLoading, cmdClient, cmdSaler, cmdInvoice, cmdPos);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CommandDto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    cmdCode: ").append(toIndentedString(cmdCode)).append("\n");
    sb.append("    cmdDate: ").append(toIndentedString(cmdDate)).append("\n");
    sb.append("    cmdComment: ").append(toIndentedString(cmdComment)).append("\n");
    sb.append("    cmdState: ").append(toIndentedString(cmdState)).append("\n");
    sb.append("    cmdNature: ").append(toIndentedString(cmdNature)).append("\n");
    sb.append("    cmdDelivery: ").append(toIndentedString(cmdDelivery)).append("\n");
    sb.append("    cmdLoading: ").append(toIndentedString(cmdLoading)).append("\n");
    sb.append("    cmdClient: ").append(toIndentedString(cmdClient)).append("\n");
    sb.append("    cmdSaler: ").append(toIndentedString(cmdSaler)).append("\n");
    sb.append("    cmdInvoice: ").append(toIndentedString(cmdInvoice)).append("\n");
    sb.append("    cmdPos: ").append(toIndentedString(cmdPos)).append("\n");
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

