/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (5.3.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.c2psi.bmv1.api;

import com.c2psi.bmv1.dto.CategoryDto;
import com.c2psi.bmv1.dto.FilterRequest;
import com.c2psi.bmv1.dto.PageofCategoryDto;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-08T23:11:35.582723400+01:00[Africa/Douala]")
@Validated
@Api(value = "category", description = "the category API")
public interface CategoryApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * DELETE /category/delete/{id} : Path used to delete a category in the system with its id
     *
     * @param id The id that represent the Category to delete. It&#39;s compulsory if not the operation can&#39;t proceed (required)
     * @return Category deleted successfully (status code 200)
     *         or Bad request. Category ID must be an integer and larger than 0. (status code 400)
     *         or Authorization information is missing or invalid. (status code 401)
     *         or The user who ask the ressource is Forbidden. (status code 403)
     *         or The expected ressource is not found. (status code 404)
     *         or Unexpected error at the server side. (status code 500)
     */
    @ApiOperation(value = "Path used to delete a category in the system with its id", nickname = "deleteCategoryById", notes = "", response = Boolean.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Category deleted successfully", response = Boolean.class),
        @ApiResponse(code = 400, message = "Bad request. Category ID must be an integer and larger than 0."),
        @ApiResponse(code = 401, message = "Authorization information is missing or invalid."),
        @ApiResponse(code = 403, message = "The user who ask the ressource is Forbidden."),
        @ApiResponse(code = 404, message = "The expected ressource is not found."),
        @ApiResponse(code = 500, message = "Unexpected error at the server side.") })
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/category/delete/{id}",
        produces = { "application/json" }
    )
    default ResponseEntity<Boolean> _deleteCategoryById(@ApiParam(value = "The id that represent the Category to delete. It's compulsory if not the operation can't proceed", required = true) @PathVariable("id") Long id) {
        return deleteCategoryById(id);
    }

    // Override this method
    default  ResponseEntity<Boolean> deleteCategoryById(Long id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /category/getby/{id} : Find a Category in the system by its id
     *
     * @param id The id that represent the Category found. It&#39;s compulsory if not the operation can&#39;t proceed (required)
     * @return Category found successfully (status code 200)
     *         or Bad request. Category ID must be an integer and larger than 0. (status code 400)
     *         or Authorization information is missing or invalid. (status code 401)
     *         or The user who ask the ressource is Forbidden. (status code 403)
     *         or The expected ressource is not found. (status code 404)
     *         or Unexpected error at the server side. (status code 500)
     */
    @ApiOperation(value = "Find a Category in the system by its id", nickname = "getCategoryById", notes = "", response = CategoryDto.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Category found successfully", response = CategoryDto.class),
        @ApiResponse(code = 400, message = "Bad request. Category ID must be an integer and larger than 0."),
        @ApiResponse(code = 401, message = "Authorization information is missing or invalid."),
        @ApiResponse(code = 403, message = "The user who ask the ressource is Forbidden."),
        @ApiResponse(code = 404, message = "The expected ressource is not found."),
        @ApiResponse(code = 500, message = "Unexpected error at the server side.") })
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/category/getby/{id}",
        produces = { "application/json" }
    )
    default ResponseEntity<CategoryDto> _getCategoryById(@ApiParam(value = "The id that represent the Category found. It's compulsory if not the operation can't proceed", required = true) @PathVariable("id") Long id) {
        return getCategoryById(id);
    }

    // Override this method
    default  ResponseEntity<CategoryDto> getCategoryById(Long id) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"catPosDto\" : { \"posName\" : \"posName\", \"posAccountDto\" : { \"damageNumber\" : 6, \"accountClientDto\" : { \"clientCni\" : \"clientCni\", \"clientBalance\" : 5, \"clientName\" : \"clientName\", \"clientOthername\" : \"clientOthername\", \"clientAddress\" : { \"country\" : \"country\", \"numtel3\" : \"numtel3\", \"town\" : \"town\", \"numtel1\" : \"678470262\", \"numtel2\" : \"numtel2\", \"localisation\" : \"localisation\", \"id\" : 0, \"email\" : \"abc@gmail.com\", \"quarter\" : \"quarter\" } }, \"accountType\" : \"Client\", \"accountProviderDto\" : { \"providerBalance\" : 5, \"providerAddress\" : { \"country\" : \"country\", \"numtel3\" : \"numtel3\", \"town\" : \"town\", \"numtel1\" : \"678470262\", \"numtel2\" : \"numtel2\", \"localisation\" : \"localisation\", \"id\" : 0, \"email\" : \"abc@gmail.com\", \"quarter\" : \"quarter\" }, \"providerAcronym\" : \"providerAcronym\", \"providerDescription\" : \"providerDescription\", \"providerName\" : \"providerName\" }, \"accountArticleDto\" : { \"artName\" : \"artName\", \"artQuantityinstock\" : 3, \"artPf\" : { \"pfProduct\" : { \"prodDescription\" : \"prodDescription\", \"prodCode\" : \"prodCode\", \"prodName\" : \"prodName\", \"prodPerishable\" : true, \"prodAlias\" : \"prodAlias\" }, \"pfPicture\" : \"pfPicture\", \"pfFormat\" : { \"formatName\" : \"formatName\", \"formatCapacity\" : 2.027123023002322 } }, \"artCode\" : \"artCode\", \"artLowlimitwholesale\" : 7, \"artUnit\" : { \"unitName\" : \"unitName\", \"unitAbbreviation\" : \"unitAbbreviation\" }, \"artDescription\" : \"artDescription\", \"artThreshold\" : 2, \"artlowlimitSemiwholesale\" : 9, \"artShortname\" : \"artShortname\", \"artBaseprice\" : { \"bpSemiwholeprice\" : 1.2315135367772556, \"bpPurchaseprice\" : 4.145608029883936, \"bpDetailsprice\" : 1.0246457001441578, \"bpRistourne\" : 6.84685269835264, \"bpCurrency\" : { \"currencyName\" : \"currencyName\", \"currencyAbbreviation\" : \"currencyAbbreviation\" }, \"bpWholeprice\" : 7.386281948385884, \"bpPrecompte\" : 1.4894159098541704 } }, \"accountPackagingDto\" : { \"packDescription\" : \"packDescription\", \"packagingProvider\" : { \"providerBalance\" : 5, \"providerAddress\" : { \"country\" : \"country\", \"numtel3\" : \"numtel3\", \"town\" : \"town\", \"numtel1\" : \"678470262\", \"numtel2\" : \"numtel2\", \"localisation\" : \"localisation\", \"id\" : 0, \"email\" : \"abc@gmail.com\", \"quarter\" : \"quarter\" }, \"providerAcronym\" : \"providerAcronym\", \"providerDescription\" : \"providerDescription\", \"providerName\" : \"providerName\" }, \"packLabel\" : \"packLabel\", \"packFirstcolor\" : \"packFirstcolor\", \"packPrice\" : 7.457744773683766 }, \"coverNumber\" : 0, \"packageNumber\" : 1 }, \"posAddressDto\" : { \"country\" : \"country\", \"numtel3\" : \"numtel3\", \"town\" : \"town\", \"numtel1\" : \"678470262\", \"numtel2\" : \"numtel2\", \"localisation\" : \"localisation\", \"id\" : 0, \"email\" : \"abc@gmail.com\", \"quarter\" : \"quarter\" }, \"posAcronym\" : \"posAcronym\", \"posEnterprise\" : { \"entAcronym\" : \"entAcronym\", \"entNiu\" : \"entNiu\", \"entSocialreason\" : \"entSocialreason\", \"entName\" : \"entName\", \"entLogo\" : \"entLogo\", \"entRegime\" : \"entRegime\", \"entDescription\" : \"entDescription\", \"entAdmin\" : { \"userLogin\" : \"userLogin\", \"userAddress\" : { \"country\" : \"country\", \"numtel3\" : \"numtel3\", \"town\" : \"town\", \"numtel1\" : \"678470262\", \"numtel2\" : \"numtel2\", \"localisation\" : \"localisation\", \"id\" : 0, \"email\" : \"abc@gmail.com\", \"quarter\" : \"quarter\" }, \"userPicture\" : \"userPicture\", \"userPassword\" : \"userPassword\", \"userState\" : \"Activated\", \"userDob\" : \"2000-01-23\", \"userCni\" : \"userCni\", \"id\" : 0, \"userName\" : \"userName\", \"userRepassword\" : \"userRepassword\", \"userSurname\" : \"userSurname\" } }, \"posDescription\" : \"posDescription\", \"posCurrency\" : { \"currencyName\" : \"currencyName\", \"currencyAbbreviation\" : \"currencyAbbreviation\" } }, \"catCode\" : \"catCode\", \"catName\" : \"catName\", \"catDescription\" : \"catDescription\", \"catShortname\" : \"catShortname\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * POST /category/list : Path used to list category that respect certain criteria. A criteria is an instance of a Filter object
     *
     * @param filterRequest  (optional)
     * @return Category list found successfully (status code 200)
     */
    @ApiOperation(value = "Path used to list category that respect certain criteria. A criteria is an instance of a Filter object", nickname = "getCategoryList", notes = "", response = CategoryDto.class, responseContainer = "List", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Category list found successfully", response = CategoryDto.class, responseContainer = "List") })
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/category/list",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<List<CategoryDto>> _getCategoryList(@ApiParam(value = "") @Valid @RequestBody(required = false) FilterRequest filterRequest) {
        return getCategoryList(filterRequest);
    }

    // Override this method
    default  ResponseEntity<List<CategoryDto>> getCategoryList(FilterRequest filterRequest) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"catPosDto\" : { \"posName\" : \"posName\", \"posAccountDto\" : { \"damageNumber\" : 6, \"accountClientDto\" : { \"clientCni\" : \"clientCni\", \"clientBalance\" : 5, \"clientName\" : \"clientName\", \"clientOthername\" : \"clientOthername\", \"clientAddress\" : { \"country\" : \"country\", \"numtel3\" : \"numtel3\", \"town\" : \"town\", \"numtel1\" : \"678470262\", \"numtel2\" : \"numtel2\", \"localisation\" : \"localisation\", \"id\" : 0, \"email\" : \"abc@gmail.com\", \"quarter\" : \"quarter\" } }, \"accountType\" : \"Client\", \"accountProviderDto\" : { \"providerBalance\" : 5, \"providerAddress\" : { \"country\" : \"country\", \"numtel3\" : \"numtel3\", \"town\" : \"town\", \"numtel1\" : \"678470262\", \"numtel2\" : \"numtel2\", \"localisation\" : \"localisation\", \"id\" : 0, \"email\" : \"abc@gmail.com\", \"quarter\" : \"quarter\" }, \"providerAcronym\" : \"providerAcronym\", \"providerDescription\" : \"providerDescription\", \"providerName\" : \"providerName\" }, \"accountArticleDto\" : { \"artName\" : \"artName\", \"artQuantityinstock\" : 3, \"artPf\" : { \"pfProduct\" : { \"prodDescription\" : \"prodDescription\", \"prodCode\" : \"prodCode\", \"prodName\" : \"prodName\", \"prodPerishable\" : true, \"prodAlias\" : \"prodAlias\" }, \"pfPicture\" : \"pfPicture\", \"pfFormat\" : { \"formatName\" : \"formatName\", \"formatCapacity\" : 2.027123023002322 } }, \"artCode\" : \"artCode\", \"artLowlimitwholesale\" : 7, \"artUnit\" : { \"unitName\" : \"unitName\", \"unitAbbreviation\" : \"unitAbbreviation\" }, \"artDescription\" : \"artDescription\", \"artThreshold\" : 2, \"artlowlimitSemiwholesale\" : 9, \"artShortname\" : \"artShortname\", \"artBaseprice\" : { \"bpSemiwholeprice\" : 1.2315135367772556, \"bpPurchaseprice\" : 4.145608029883936, \"bpDetailsprice\" : 1.0246457001441578, \"bpRistourne\" : 6.84685269835264, \"bpCurrency\" : { \"currencyName\" : \"currencyName\", \"currencyAbbreviation\" : \"currencyAbbreviation\" }, \"bpWholeprice\" : 7.386281948385884, \"bpPrecompte\" : 1.4894159098541704 } }, \"accountPackagingDto\" : { \"packDescription\" : \"packDescription\", \"packagingProvider\" : { \"providerBalance\" : 5, \"providerAddress\" : { \"country\" : \"country\", \"numtel3\" : \"numtel3\", \"town\" : \"town\", \"numtel1\" : \"678470262\", \"numtel2\" : \"numtel2\", \"localisation\" : \"localisation\", \"id\" : 0, \"email\" : \"abc@gmail.com\", \"quarter\" : \"quarter\" }, \"providerAcronym\" : \"providerAcronym\", \"providerDescription\" : \"providerDescription\", \"providerName\" : \"providerName\" }, \"packLabel\" : \"packLabel\", \"packFirstcolor\" : \"packFirstcolor\", \"packPrice\" : 7.457744773683766 }, \"coverNumber\" : 0, \"packageNumber\" : 1 }, \"posAddressDto\" : { \"country\" : \"country\", \"numtel3\" : \"numtel3\", \"town\" : \"town\", \"numtel1\" : \"678470262\", \"numtel2\" : \"numtel2\", \"localisation\" : \"localisation\", \"id\" : 0, \"email\" : \"abc@gmail.com\", \"quarter\" : \"quarter\" }, \"posAcronym\" : \"posAcronym\", \"posEnterprise\" : { \"entAcronym\" : \"entAcronym\", \"entNiu\" : \"entNiu\", \"entSocialreason\" : \"entSocialreason\", \"entName\" : \"entName\", \"entLogo\" : \"entLogo\", \"entRegime\" : \"entRegime\", \"entDescription\" : \"entDescription\", \"entAdmin\" : { \"userLogin\" : \"userLogin\", \"userAddress\" : { \"country\" : \"country\", \"numtel3\" : \"numtel3\", \"town\" : \"town\", \"numtel1\" : \"678470262\", \"numtel2\" : \"numtel2\", \"localisation\" : \"localisation\", \"id\" : 0, \"email\" : \"abc@gmail.com\", \"quarter\" : \"quarter\" }, \"userPicture\" : \"userPicture\", \"userPassword\" : \"userPassword\", \"userState\" : \"Activated\", \"userDob\" : \"2000-01-23\", \"userCni\" : \"userCni\", \"id\" : 0, \"userName\" : \"userName\", \"userRepassword\" : \"userRepassword\", \"userSurname\" : \"userSurname\" } }, \"posDescription\" : \"posDescription\", \"posCurrency\" : { \"currencyName\" : \"currencyName\", \"currencyAbbreviation\" : \"currencyAbbreviation\" } }, \"catCode\" : \"catCode\", \"catName\" : \"catName\", \"catDescription\" : \"catDescription\", \"catShortname\" : \"catShortname\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * POST /category/page : Path used to list category page by page that respect certain criteria. With the Page object, we can configure the page number and size that we want
     *
     * @param filterRequest  (optional)
     * @return Category page found successfully (status code 200)
     */
    @ApiOperation(value = "Path used to list category page by page that respect certain criteria. With the Page object, we can configure the page number and size that we want", nickname = "getCategoryPage", notes = "", response = PageofCategoryDto.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Category page found successfully", response = PageofCategoryDto.class) })
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/category/page",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<PageofCategoryDto> _getCategoryPage(@ApiParam(value = "") @Valid @RequestBody(required = false) FilterRequest filterRequest) {
        return getCategoryPage(filterRequest);
    }

    // Override this method
    default  ResponseEntity<PageofCategoryDto> getCategoryPage(FilterRequest filterRequest) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"totalPages\" : 6, \"pageSize\" : 5, \"currentPage\" : 1, \"content\" : [ { \"catPosDto\" : { \"posName\" : \"posName\", \"posAccountDto\" : { \"damageNumber\" : 6, \"accountClientDto\" : { \"clientCni\" : \"clientCni\", \"clientBalance\" : 5, \"clientName\" : \"clientName\", \"clientOthername\" : \"clientOthername\", \"clientAddress\" : { \"country\" : \"country\", \"numtel3\" : \"numtel3\", \"town\" : \"town\", \"numtel1\" : \"678470262\", \"numtel2\" : \"numtel2\", \"localisation\" : \"localisation\", \"id\" : 0, \"email\" : \"abc@gmail.com\", \"quarter\" : \"quarter\" } }, \"accountType\" : \"Client\", \"accountProviderDto\" : { \"providerBalance\" : 5, \"providerAddress\" : { \"country\" : \"country\", \"numtel3\" : \"numtel3\", \"town\" : \"town\", \"numtel1\" : \"678470262\", \"numtel2\" : \"numtel2\", \"localisation\" : \"localisation\", \"id\" : 0, \"email\" : \"abc@gmail.com\", \"quarter\" : \"quarter\" }, \"providerAcronym\" : \"providerAcronym\", \"providerDescription\" : \"providerDescription\", \"providerName\" : \"providerName\" }, \"accountArticleDto\" : { \"artName\" : \"artName\", \"artQuantityinstock\" : 3, \"artPf\" : { \"pfProduct\" : { \"prodDescription\" : \"prodDescription\", \"prodCode\" : \"prodCode\", \"prodName\" : \"prodName\", \"prodPerishable\" : true, \"prodAlias\" : \"prodAlias\" }, \"pfPicture\" : \"pfPicture\", \"pfFormat\" : { \"formatName\" : \"formatName\", \"formatCapacity\" : 2.027123023002322 } }, \"artCode\" : \"artCode\", \"artLowlimitwholesale\" : 7, \"artUnit\" : { \"unitName\" : \"unitName\", \"unitAbbreviation\" : \"unitAbbreviation\" }, \"artDescription\" : \"artDescription\", \"artThreshold\" : 2, \"artlowlimitSemiwholesale\" : 9, \"artShortname\" : \"artShortname\", \"artBaseprice\" : { \"bpSemiwholeprice\" : 1.2315135367772556, \"bpPurchaseprice\" : 4.145608029883936, \"bpDetailsprice\" : 1.0246457001441578, \"bpRistourne\" : 6.84685269835264, \"bpCurrency\" : { \"currencyName\" : \"currencyName\", \"currencyAbbreviation\" : \"currencyAbbreviation\" }, \"bpWholeprice\" : 7.386281948385884, \"bpPrecompte\" : 1.4894159098541704 } }, \"accountPackagingDto\" : { \"packDescription\" : \"packDescription\", \"packagingProvider\" : { \"providerBalance\" : 5, \"providerAddress\" : { \"country\" : \"country\", \"numtel3\" : \"numtel3\", \"town\" : \"town\", \"numtel1\" : \"678470262\", \"numtel2\" : \"numtel2\", \"localisation\" : \"localisation\", \"id\" : 0, \"email\" : \"abc@gmail.com\", \"quarter\" : \"quarter\" }, \"providerAcronym\" : \"providerAcronym\", \"providerDescription\" : \"providerDescription\", \"providerName\" : \"providerName\" }, \"packLabel\" : \"packLabel\", \"packFirstcolor\" : \"packFirstcolor\", \"packPrice\" : 7.457744773683766 }, \"coverNumber\" : 0, \"packageNumber\" : 1 }, \"posAddressDto\" : { \"country\" : \"country\", \"numtel3\" : \"numtel3\", \"town\" : \"town\", \"numtel1\" : \"678470262\", \"numtel2\" : \"numtel2\", \"localisation\" : \"localisation\", \"id\" : 0, \"email\" : \"abc@gmail.com\", \"quarter\" : \"quarter\" }, \"posAcronym\" : \"posAcronym\", \"posEnterprise\" : { \"entAcronym\" : \"entAcronym\", \"entNiu\" : \"entNiu\", \"entSocialreason\" : \"entSocialreason\", \"entName\" : \"entName\", \"entLogo\" : \"entLogo\", \"entRegime\" : \"entRegime\", \"entDescription\" : \"entDescription\", \"entAdmin\" : { \"userLogin\" : \"userLogin\", \"userAddress\" : { \"country\" : \"country\", \"numtel3\" : \"numtel3\", \"town\" : \"town\", \"numtel1\" : \"678470262\", \"numtel2\" : \"numtel2\", \"localisation\" : \"localisation\", \"id\" : 0, \"email\" : \"abc@gmail.com\", \"quarter\" : \"quarter\" }, \"userPicture\" : \"userPicture\", \"userPassword\" : \"userPassword\", \"userState\" : \"Activated\", \"userDob\" : \"2000-01-23\", \"userCni\" : \"userCni\", \"id\" : 0, \"userName\" : \"userName\", \"userRepassword\" : \"userRepassword\", \"userSurname\" : \"userSurname\" } }, \"posDescription\" : \"posDescription\", \"posCurrency\" : { \"currencyName\" : \"currencyName\", \"currencyAbbreviation\" : \"currencyAbbreviation\" } }, \"catCode\" : \"catCode\", \"catName\" : \"catName\", \"catDescription\" : \"catDescription\", \"catShortname\" : \"catShortname\" }, { \"catPosDto\" : { \"posName\" : \"posName\", \"posAccountDto\" : { \"damageNumber\" : 6, \"accountClientDto\" : { \"clientCni\" : \"clientCni\", \"clientBalance\" : 5, \"clientName\" : \"clientName\", \"clientOthername\" : \"clientOthername\", \"clientAddress\" : { \"country\" : \"country\", \"numtel3\" : \"numtel3\", \"town\" : \"town\", \"numtel1\" : \"678470262\", \"numtel2\" : \"numtel2\", \"localisation\" : \"localisation\", \"id\" : 0, \"email\" : \"abc@gmail.com\", \"quarter\" : \"quarter\" } }, \"accountType\" : \"Client\", \"accountProviderDto\" : { \"providerBalance\" : 5, \"providerAddress\" : { \"country\" : \"country\", \"numtel3\" : \"numtel3\", \"town\" : \"town\", \"numtel1\" : \"678470262\", \"numtel2\" : \"numtel2\", \"localisation\" : \"localisation\", \"id\" : 0, \"email\" : \"abc@gmail.com\", \"quarter\" : \"quarter\" }, \"providerAcronym\" : \"providerAcronym\", \"providerDescription\" : \"providerDescription\", \"providerName\" : \"providerName\" }, \"accountArticleDto\" : { \"artName\" : \"artName\", \"artQuantityinstock\" : 3, \"artPf\" : { \"pfProduct\" : { \"prodDescription\" : \"prodDescription\", \"prodCode\" : \"prodCode\", \"prodName\" : \"prodName\", \"prodPerishable\" : true, \"prodAlias\" : \"prodAlias\" }, \"pfPicture\" : \"pfPicture\", \"pfFormat\" : { \"formatName\" : \"formatName\", \"formatCapacity\" : 2.027123023002322 } }, \"artCode\" : \"artCode\", \"artLowlimitwholesale\" : 7, \"artUnit\" : { \"unitName\" : \"unitName\", \"unitAbbreviation\" : \"unitAbbreviation\" }, \"artDescription\" : \"artDescription\", \"artThreshold\" : 2, \"artlowlimitSemiwholesale\" : 9, \"artShortname\" : \"artShortname\", \"artBaseprice\" : { \"bpSemiwholeprice\" : 1.2315135367772556, \"bpPurchaseprice\" : 4.145608029883936, \"bpDetailsprice\" : 1.0246457001441578, \"bpRistourne\" : 6.84685269835264, \"bpCurrency\" : { \"currencyName\" : \"currencyName\", \"currencyAbbreviation\" : \"currencyAbbreviation\" }, \"bpWholeprice\" : 7.386281948385884, \"bpPrecompte\" : 1.4894159098541704 } }, \"accountPackagingDto\" : { \"packDescription\" : \"packDescription\", \"packagingProvider\" : { \"providerBalance\" : 5, \"providerAddress\" : { \"country\" : \"country\", \"numtel3\" : \"numtel3\", \"town\" : \"town\", \"numtel1\" : \"678470262\", \"numtel2\" : \"numtel2\", \"localisation\" : \"localisation\", \"id\" : 0, \"email\" : \"abc@gmail.com\", \"quarter\" : \"quarter\" }, \"providerAcronym\" : \"providerAcronym\", \"providerDescription\" : \"providerDescription\", \"providerName\" : \"providerName\" }, \"packLabel\" : \"packLabel\", \"packFirstcolor\" : \"packFirstcolor\", \"packPrice\" : 7.457744773683766 }, \"coverNumber\" : 0, \"packageNumber\" : 1 }, \"posAddressDto\" : { \"country\" : \"country\", \"numtel3\" : \"numtel3\", \"town\" : \"town\", \"numtel1\" : \"678470262\", \"numtel2\" : \"numtel2\", \"localisation\" : \"localisation\", \"id\" : 0, \"email\" : \"abc@gmail.com\", \"quarter\" : \"quarter\" }, \"posAcronym\" : \"posAcronym\", \"posEnterprise\" : { \"entAcronym\" : \"entAcronym\", \"entNiu\" : \"entNiu\", \"entSocialreason\" : \"entSocialreason\", \"entName\" : \"entName\", \"entLogo\" : \"entLogo\", \"entRegime\" : \"entRegime\", \"entDescription\" : \"entDescription\", \"entAdmin\" : { \"userLogin\" : \"userLogin\", \"userAddress\" : { \"country\" : \"country\", \"numtel3\" : \"numtel3\", \"town\" : \"town\", \"numtel1\" : \"678470262\", \"numtel2\" : \"numtel2\", \"localisation\" : \"localisation\", \"id\" : 0, \"email\" : \"abc@gmail.com\", \"quarter\" : \"quarter\" }, \"userPicture\" : \"userPicture\", \"userPassword\" : \"userPassword\", \"userState\" : \"Activated\", \"userDob\" : \"2000-01-23\", \"userCni\" : \"userCni\", \"id\" : 0, \"userName\" : \"userName\", \"userRepassword\" : \"userRepassword\", \"userSurname\" : \"userSurname\" } }, \"posDescription\" : \"posDescription\", \"posCurrency\" : { \"currencyName\" : \"currencyName\", \"currencyAbbreviation\" : \"currencyAbbreviation\" } }, \"catCode\" : \"catCode\", \"catName\" : \"catName\", \"catDescription\" : \"catDescription\", \"catShortname\" : \"catShortname\" } ], \"totalElements\" : 0 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * POST /category/create : Path used to save a new category of product in the system
     *
     * @param categoryDto  (optional)
     * @return Category saved successfully (status code 200)
     *         or Bad request. There is something wrong in the request. (status code 400)
     *         or Authorization information is missing or invalid. (status code 401)
     *         or The user who ask the ressource is Forbidden. (status code 403)
     *         or The expected ressource is not found. (status code 404)
     *         or Unexpected error at the server side. (status code 500)
     */
    @ApiOperation(value = "Path used to save a new category of product in the system", nickname = "saveCategory", notes = "", response = CategoryDto.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Category saved successfully", response = CategoryDto.class),
        @ApiResponse(code = 400, message = "Bad request. There is something wrong in the request."),
        @ApiResponse(code = 401, message = "Authorization information is missing or invalid."),
        @ApiResponse(code = 403, message = "The user who ask the ressource is Forbidden."),
        @ApiResponse(code = 404, message = "The expected ressource is not found."),
        @ApiResponse(code = 500, message = "Unexpected error at the server side.") })
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/category/create",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<CategoryDto> _saveCategory(@ApiParam(value = "") @Valid @RequestBody(required = false) CategoryDto categoryDto) {
        return saveCategory(categoryDto);
    }

    // Override this method
    default  ResponseEntity<CategoryDto> saveCategory(CategoryDto categoryDto) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"catPosDto\" : { \"posName\" : \"posName\", \"posAccountDto\" : { \"damageNumber\" : 6, \"accountClientDto\" : { \"clientCni\" : \"clientCni\", \"clientBalance\" : 5, \"clientName\" : \"clientName\", \"clientOthername\" : \"clientOthername\", \"clientAddress\" : { \"country\" : \"country\", \"numtel3\" : \"numtel3\", \"town\" : \"town\", \"numtel1\" : \"678470262\", \"numtel2\" : \"numtel2\", \"localisation\" : \"localisation\", \"id\" : 0, \"email\" : \"abc@gmail.com\", \"quarter\" : \"quarter\" } }, \"accountType\" : \"Client\", \"accountProviderDto\" : { \"providerBalance\" : 5, \"providerAddress\" : { \"country\" : \"country\", \"numtel3\" : \"numtel3\", \"town\" : \"town\", \"numtel1\" : \"678470262\", \"numtel2\" : \"numtel2\", \"localisation\" : \"localisation\", \"id\" : 0, \"email\" : \"abc@gmail.com\", \"quarter\" : \"quarter\" }, \"providerAcronym\" : \"providerAcronym\", \"providerDescription\" : \"providerDescription\", \"providerName\" : \"providerName\" }, \"accountArticleDto\" : { \"artName\" : \"artName\", \"artQuantityinstock\" : 3, \"artPf\" : { \"pfProduct\" : { \"prodDescription\" : \"prodDescription\", \"prodCode\" : \"prodCode\", \"prodName\" : \"prodName\", \"prodPerishable\" : true, \"prodAlias\" : \"prodAlias\" }, \"pfPicture\" : \"pfPicture\", \"pfFormat\" : { \"formatName\" : \"formatName\", \"formatCapacity\" : 2.027123023002322 } }, \"artCode\" : \"artCode\", \"artLowlimitwholesale\" : 7, \"artUnit\" : { \"unitName\" : \"unitName\", \"unitAbbreviation\" : \"unitAbbreviation\" }, \"artDescription\" : \"artDescription\", \"artThreshold\" : 2, \"artlowlimitSemiwholesale\" : 9, \"artShortname\" : \"artShortname\", \"artBaseprice\" : { \"bpSemiwholeprice\" : 1.2315135367772556, \"bpPurchaseprice\" : 4.145608029883936, \"bpDetailsprice\" : 1.0246457001441578, \"bpRistourne\" : 6.84685269835264, \"bpCurrency\" : { \"currencyName\" : \"currencyName\", \"currencyAbbreviation\" : \"currencyAbbreviation\" }, \"bpWholeprice\" : 7.386281948385884, \"bpPrecompte\" : 1.4894159098541704 } }, \"accountPackagingDto\" : { \"packDescription\" : \"packDescription\", \"packagingProvider\" : { \"providerBalance\" : 5, \"providerAddress\" : { \"country\" : \"country\", \"numtel3\" : \"numtel3\", \"town\" : \"town\", \"numtel1\" : \"678470262\", \"numtel2\" : \"numtel2\", \"localisation\" : \"localisation\", \"id\" : 0, \"email\" : \"abc@gmail.com\", \"quarter\" : \"quarter\" }, \"providerAcronym\" : \"providerAcronym\", \"providerDescription\" : \"providerDescription\", \"providerName\" : \"providerName\" }, \"packLabel\" : \"packLabel\", \"packFirstcolor\" : \"packFirstcolor\", \"packPrice\" : 7.457744773683766 }, \"coverNumber\" : 0, \"packageNumber\" : 1 }, \"posAddressDto\" : { \"country\" : \"country\", \"numtel3\" : \"numtel3\", \"town\" : \"town\", \"numtel1\" : \"678470262\", \"numtel2\" : \"numtel2\", \"localisation\" : \"localisation\", \"id\" : 0, \"email\" : \"abc@gmail.com\", \"quarter\" : \"quarter\" }, \"posAcronym\" : \"posAcronym\", \"posEnterprise\" : { \"entAcronym\" : \"entAcronym\", \"entNiu\" : \"entNiu\", \"entSocialreason\" : \"entSocialreason\", \"entName\" : \"entName\", \"entLogo\" : \"entLogo\", \"entRegime\" : \"entRegime\", \"entDescription\" : \"entDescription\", \"entAdmin\" : { \"userLogin\" : \"userLogin\", \"userAddress\" : { \"country\" : \"country\", \"numtel3\" : \"numtel3\", \"town\" : \"town\", \"numtel1\" : \"678470262\", \"numtel2\" : \"numtel2\", \"localisation\" : \"localisation\", \"id\" : 0, \"email\" : \"abc@gmail.com\", \"quarter\" : \"quarter\" }, \"userPicture\" : \"userPicture\", \"userPassword\" : \"userPassword\", \"userState\" : \"Activated\", \"userDob\" : \"2000-01-23\", \"userCni\" : \"userCni\", \"id\" : 0, \"userName\" : \"userName\", \"userRepassword\" : \"userRepassword\", \"userSurname\" : \"userSurname\" } }, \"posDescription\" : \"posDescription\", \"posCurrency\" : { \"currencyName\" : \"currencyName\", \"currencyAbbreviation\" : \"currencyAbbreviation\" } }, \"catCode\" : \"catCode\", \"catName\" : \"catName\", \"catDescription\" : \"catDescription\", \"catShortname\" : \"catShortname\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PUT /category/update : Path used to update or modify an existing category in the system
     *
     * @param categoryDto  (optional)
     * @return Category updated successfully (status code 200)
     *         or Bad request. There is something wrong in the request. (status code 400)
     *         or Authorization information is missing or invalid. (status code 401)
     *         or The user who ask the ressource is Forbidden. (status code 403)
     *         or The expected ressource is not found. (status code 404)
     *         or Unexpected error at the server side. (status code 500)
     */
    @ApiOperation(value = "Path used to update or modify an existing category in the system", nickname = "updateCategory", notes = "", response = CategoryDto.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Category updated successfully", response = CategoryDto.class),
        @ApiResponse(code = 400, message = "Bad request. There is something wrong in the request."),
        @ApiResponse(code = 401, message = "Authorization information is missing or invalid."),
        @ApiResponse(code = 403, message = "The user who ask the ressource is Forbidden."),
        @ApiResponse(code = 404, message = "The expected ressource is not found."),
        @ApiResponse(code = 500, message = "Unexpected error at the server side.") })
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/category/update",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<CategoryDto> _updateCategory(@ApiParam(value = "") @Valid @RequestBody(required = false) CategoryDto categoryDto) {
        return updateCategory(categoryDto);
    }

    // Override this method
    default  ResponseEntity<CategoryDto> updateCategory(CategoryDto categoryDto) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"catPosDto\" : { \"posName\" : \"posName\", \"posAccountDto\" : { \"damageNumber\" : 6, \"accountClientDto\" : { \"clientCni\" : \"clientCni\", \"clientBalance\" : 5, \"clientName\" : \"clientName\", \"clientOthername\" : \"clientOthername\", \"clientAddress\" : { \"country\" : \"country\", \"numtel3\" : \"numtel3\", \"town\" : \"town\", \"numtel1\" : \"678470262\", \"numtel2\" : \"numtel2\", \"localisation\" : \"localisation\", \"id\" : 0, \"email\" : \"abc@gmail.com\", \"quarter\" : \"quarter\" } }, \"accountType\" : \"Client\", \"accountProviderDto\" : { \"providerBalance\" : 5, \"providerAddress\" : { \"country\" : \"country\", \"numtel3\" : \"numtel3\", \"town\" : \"town\", \"numtel1\" : \"678470262\", \"numtel2\" : \"numtel2\", \"localisation\" : \"localisation\", \"id\" : 0, \"email\" : \"abc@gmail.com\", \"quarter\" : \"quarter\" }, \"providerAcronym\" : \"providerAcronym\", \"providerDescription\" : \"providerDescription\", \"providerName\" : \"providerName\" }, \"accountArticleDto\" : { \"artName\" : \"artName\", \"artQuantityinstock\" : 3, \"artPf\" : { \"pfProduct\" : { \"prodDescription\" : \"prodDescription\", \"prodCode\" : \"prodCode\", \"prodName\" : \"prodName\", \"prodPerishable\" : true, \"prodAlias\" : \"prodAlias\" }, \"pfPicture\" : \"pfPicture\", \"pfFormat\" : { \"formatName\" : \"formatName\", \"formatCapacity\" : 2.027123023002322 } }, \"artCode\" : \"artCode\", \"artLowlimitwholesale\" : 7, \"artUnit\" : { \"unitName\" : \"unitName\", \"unitAbbreviation\" : \"unitAbbreviation\" }, \"artDescription\" : \"artDescription\", \"artThreshold\" : 2, \"artlowlimitSemiwholesale\" : 9, \"artShortname\" : \"artShortname\", \"artBaseprice\" : { \"bpSemiwholeprice\" : 1.2315135367772556, \"bpPurchaseprice\" : 4.145608029883936, \"bpDetailsprice\" : 1.0246457001441578, \"bpRistourne\" : 6.84685269835264, \"bpCurrency\" : { \"currencyName\" : \"currencyName\", \"currencyAbbreviation\" : \"currencyAbbreviation\" }, \"bpWholeprice\" : 7.386281948385884, \"bpPrecompte\" : 1.4894159098541704 } }, \"accountPackagingDto\" : { \"packDescription\" : \"packDescription\", \"packagingProvider\" : { \"providerBalance\" : 5, \"providerAddress\" : { \"country\" : \"country\", \"numtel3\" : \"numtel3\", \"town\" : \"town\", \"numtel1\" : \"678470262\", \"numtel2\" : \"numtel2\", \"localisation\" : \"localisation\", \"id\" : 0, \"email\" : \"abc@gmail.com\", \"quarter\" : \"quarter\" }, \"providerAcronym\" : \"providerAcronym\", \"providerDescription\" : \"providerDescription\", \"providerName\" : \"providerName\" }, \"packLabel\" : \"packLabel\", \"packFirstcolor\" : \"packFirstcolor\", \"packPrice\" : 7.457744773683766 }, \"coverNumber\" : 0, \"packageNumber\" : 1 }, \"posAddressDto\" : { \"country\" : \"country\", \"numtel3\" : \"numtel3\", \"town\" : \"town\", \"numtel1\" : \"678470262\", \"numtel2\" : \"numtel2\", \"localisation\" : \"localisation\", \"id\" : 0, \"email\" : \"abc@gmail.com\", \"quarter\" : \"quarter\" }, \"posAcronym\" : \"posAcronym\", \"posEnterprise\" : { \"entAcronym\" : \"entAcronym\", \"entNiu\" : \"entNiu\", \"entSocialreason\" : \"entSocialreason\", \"entName\" : \"entName\", \"entLogo\" : \"entLogo\", \"entRegime\" : \"entRegime\", \"entDescription\" : \"entDescription\", \"entAdmin\" : { \"userLogin\" : \"userLogin\", \"userAddress\" : { \"country\" : \"country\", \"numtel3\" : \"numtel3\", \"town\" : \"town\", \"numtel1\" : \"678470262\", \"numtel2\" : \"numtel2\", \"localisation\" : \"localisation\", \"id\" : 0, \"email\" : \"abc@gmail.com\", \"quarter\" : \"quarter\" }, \"userPicture\" : \"userPicture\", \"userPassword\" : \"userPassword\", \"userState\" : \"Activated\", \"userDob\" : \"2000-01-23\", \"userCni\" : \"userCni\", \"id\" : 0, \"userName\" : \"userName\", \"userRepassword\" : \"userRepassword\", \"userSurname\" : \"userSurname\" } }, \"posDescription\" : \"posDescription\", \"posCurrency\" : { \"currencyName\" : \"currencyName\", \"currencyAbbreviation\" : \"currencyAbbreviation\" } }, \"catCode\" : \"catCode\", \"catName\" : \"catName\", \"catDescription\" : \"catDescription\", \"catShortname\" : \"catShortname\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}