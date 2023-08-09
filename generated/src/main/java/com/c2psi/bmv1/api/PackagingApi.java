/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (5.3.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.c2psi.bmv1.api;

import com.c2psi.bmv1.dto.FilterRequest;
import com.c2psi.bmv1.dto.PackagingDto;
import com.c2psi.bmv1.dto.PageofPackagingDto;
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
@Api(value = "packaging", description = "the packaging API")
public interface PackagingApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * DELETE /packaging/delete/{id} : Path used to delete a packaging in the system with its id
     *
     * @param id The id that represent the Packaging to delete. It&#39;s compulsory if not the operation can&#39;t proceed (required)
     * @return Packaging deleted successfully (status code 200)
     *         or Bad request. Packaging ID must be an integer and larger than 0. (status code 400)
     *         or Authorization information is missing or invalid. (status code 401)
     *         or The user who ask the ressource is Forbidden. (status code 403)
     *         or The expected ressource is not found. (status code 404)
     *         or Unexpected error at the server side. (status code 500)
     */
    @ApiOperation(value = "Path used to delete a packaging in the system with its id", nickname = "deletePackagingById", notes = "", response = Boolean.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Packaging deleted successfully", response = Boolean.class),
        @ApiResponse(code = 400, message = "Bad request. Packaging ID must be an integer and larger than 0."),
        @ApiResponse(code = 401, message = "Authorization information is missing or invalid."),
        @ApiResponse(code = 403, message = "The user who ask the ressource is Forbidden."),
        @ApiResponse(code = 404, message = "The expected ressource is not found."),
        @ApiResponse(code = 500, message = "Unexpected error at the server side.") })
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/packaging/delete/{id}",
        produces = { "application/json" }
    )
    default ResponseEntity<Boolean> _deletePackagingById(@ApiParam(value = "The id that represent the Packaging to delete. It's compulsory if not the operation can't proceed", required = true) @PathVariable("id") Long id) {
        return deletePackagingById(id);
    }

    // Override this method
    default  ResponseEntity<Boolean> deletePackagingById(Long id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /packaging/getby/{id} : Find a Packaging in the system by its id
     *
     * @param id The id that represent the Packaging found. It&#39;s compulsory if not the operation can&#39;t proceed (required)
     * @return Packaging found successfully (status code 200)
     *         or Bad request. Packaging ID must be an integer and larger than 0. (status code 400)
     *         or Authorization information is missing or invalid. (status code 401)
     *         or The user who ask the ressource is Forbidden. (status code 403)
     *         or The expected ressource is not found. (status code 404)
     *         or Unexpected error at the server side. (status code 500)
     */
    @ApiOperation(value = "Find a Packaging in the system by its id", nickname = "getPackagingById", notes = "", response = PackagingDto.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Packaging found successfully", response = PackagingDto.class),
        @ApiResponse(code = 400, message = "Bad request. Packaging ID must be an integer and larger than 0."),
        @ApiResponse(code = 401, message = "Authorization information is missing or invalid."),
        @ApiResponse(code = 403, message = "The user who ask the ressource is Forbidden."),
        @ApiResponse(code = 404, message = "The expected ressource is not found."),
        @ApiResponse(code = 500, message = "Unexpected error at the server side.") })
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/packaging/getby/{id}",
        produces = { "application/json" }
    )
    default ResponseEntity<PackagingDto> _getPackagingById(@ApiParam(value = "The id that represent the Packaging found. It's compulsory if not the operation can't proceed", required = true) @PathVariable("id") Long id) {
        return getPackagingById(id);
    }

    // Override this method
    default  ResponseEntity<PackagingDto> getPackagingById(Long id) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"packDescription\" : \"packDescription\", \"packagingProvider\" : { \"providerBalance\" : 5, \"providerAddress\" : { \"country\" : \"country\", \"numtel3\" : \"numtel3\", \"town\" : \"town\", \"numtel1\" : \"678470262\", \"numtel2\" : \"numtel2\", \"localisation\" : \"localisation\", \"id\" : 0, \"email\" : \"abc@gmail.com\", \"quarter\" : \"quarter\" }, \"providerAcronym\" : \"providerAcronym\", \"providerDescription\" : \"providerDescription\", \"providerName\" : \"providerName\" }, \"packLabel\" : \"packLabel\", \"packFirstcolor\" : \"packFirstcolor\", \"packPrice\" : 7.457744773683766 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * POST /packaging/list : Path used to list packaging that respect certain criteria. A criteria is an instance of a Filter object
     *
     * @param filterRequest  (optional)
     * @return Packaging list found successfully (status code 200)
     */
    @ApiOperation(value = "Path used to list packaging that respect certain criteria. A criteria is an instance of a Filter object", nickname = "getPackagingList", notes = "", response = PackagingDto.class, responseContainer = "List", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Packaging list found successfully", response = PackagingDto.class, responseContainer = "List") })
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/packaging/list",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<List<PackagingDto>> _getPackagingList(@ApiParam(value = "") @Valid @RequestBody(required = false) FilterRequest filterRequest) {
        return getPackagingList(filterRequest);
    }

    // Override this method
    default  ResponseEntity<List<PackagingDto>> getPackagingList(FilterRequest filterRequest) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"packDescription\" : \"packDescription\", \"packagingProvider\" : { \"providerBalance\" : 5, \"providerAddress\" : { \"country\" : \"country\", \"numtel3\" : \"numtel3\", \"town\" : \"town\", \"numtel1\" : \"678470262\", \"numtel2\" : \"numtel2\", \"localisation\" : \"localisation\", \"id\" : 0, \"email\" : \"abc@gmail.com\", \"quarter\" : \"quarter\" }, \"providerAcronym\" : \"providerAcronym\", \"providerDescription\" : \"providerDescription\", \"providerName\" : \"providerName\" }, \"packLabel\" : \"packLabel\", \"packFirstcolor\" : \"packFirstcolor\", \"packPrice\" : 7.457744773683766 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * POST /packaging/page : Path used to list packaging page by page that respect certain criteria. With the Page object, we can configure the page number and size that we want
     *
     * @param filterRequest  (optional)
     * @return Packaging page found successfully (status code 200)
     */
    @ApiOperation(value = "Path used to list packaging page by page that respect certain criteria. With the Page object, we can configure the page number and size that we want", nickname = "getPackagingPage", notes = "", response = PageofPackagingDto.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Packaging page found successfully", response = PageofPackagingDto.class) })
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/packaging/page",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<PageofPackagingDto> _getPackagingPage(@ApiParam(value = "") @Valid @RequestBody(required = false) FilterRequest filterRequest) {
        return getPackagingPage(filterRequest);
    }

    // Override this method
    default  ResponseEntity<PageofPackagingDto> getPackagingPage(FilterRequest filterRequest) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"totalPages\" : 6, \"pageSize\" : 5, \"currentPage\" : 1, \"content\" : [ { \"packDescription\" : \"packDescription\", \"packagingProvider\" : { \"providerBalance\" : 5, \"providerAddress\" : { \"country\" : \"country\", \"numtel3\" : \"numtel3\", \"town\" : \"town\", \"numtel1\" : \"678470262\", \"numtel2\" : \"numtel2\", \"localisation\" : \"localisation\", \"id\" : 0, \"email\" : \"abc@gmail.com\", \"quarter\" : \"quarter\" }, \"providerAcronym\" : \"providerAcronym\", \"providerDescription\" : \"providerDescription\", \"providerName\" : \"providerName\" }, \"packLabel\" : \"packLabel\", \"packFirstcolor\" : \"packFirstcolor\", \"packPrice\" : 7.457744773683766 }, { \"packDescription\" : \"packDescription\", \"packagingProvider\" : { \"providerBalance\" : 5, \"providerAddress\" : { \"country\" : \"country\", \"numtel3\" : \"numtel3\", \"town\" : \"town\", \"numtel1\" : \"678470262\", \"numtel2\" : \"numtel2\", \"localisation\" : \"localisation\", \"id\" : 0, \"email\" : \"abc@gmail.com\", \"quarter\" : \"quarter\" }, \"providerAcronym\" : \"providerAcronym\", \"providerDescription\" : \"providerDescription\", \"providerName\" : \"providerName\" }, \"packLabel\" : \"packLabel\", \"packFirstcolor\" : \"packFirstcolor\", \"packPrice\" : 7.457744773683766 } ], \"totalElements\" : 0 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * POST /packaging/create : Path used to save a new Packaging in the system
     *
     * @param packagingDto  (optional)
     * @return Packaging saved successfully (status code 200)
     *         or Bad request. There is something wrong in the request. (status code 400)
     *         or Authorization information is missing or invalid. (status code 401)
     *         or The user who ask the ressource is Forbidden. (status code 403)
     *         or The expected ressource is not found. (status code 404)
     *         or Unexpected error at the server side. (status code 500)
     */
    @ApiOperation(value = "Path used to save a new Packaging in the system", nickname = "savePackaging", notes = "", response = PackagingDto.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Packaging saved successfully", response = PackagingDto.class),
        @ApiResponse(code = 400, message = "Bad request. There is something wrong in the request."),
        @ApiResponse(code = 401, message = "Authorization information is missing or invalid."),
        @ApiResponse(code = 403, message = "The user who ask the ressource is Forbidden."),
        @ApiResponse(code = 404, message = "The expected ressource is not found."),
        @ApiResponse(code = 500, message = "Unexpected error at the server side.") })
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/packaging/create",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<PackagingDto> _savePackaging(@ApiParam(value = "") @Valid @RequestBody(required = false) PackagingDto packagingDto) {
        return savePackaging(packagingDto);
    }

    // Override this method
    default  ResponseEntity<PackagingDto> savePackaging(PackagingDto packagingDto) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"packDescription\" : \"packDescription\", \"packagingProvider\" : { \"providerBalance\" : 5, \"providerAddress\" : { \"country\" : \"country\", \"numtel3\" : \"numtel3\", \"town\" : \"town\", \"numtel1\" : \"678470262\", \"numtel2\" : \"numtel2\", \"localisation\" : \"localisation\", \"id\" : 0, \"email\" : \"abc@gmail.com\", \"quarter\" : \"quarter\" }, \"providerAcronym\" : \"providerAcronym\", \"providerDescription\" : \"providerDescription\", \"providerName\" : \"providerName\" }, \"packLabel\" : \"packLabel\", \"packFirstcolor\" : \"packFirstcolor\", \"packPrice\" : 7.457744773683766 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PUT /packaging/update : Path used to update or modify an existing Packaging in the system
     *
     * @param packagingDto  (optional)
     * @return Packaging updated successfully (status code 200)
     *         or Bad request. There is something wrong in the request. (status code 400)
     *         or Authorization information is missing or invalid. (status code 401)
     *         or The user who ask the ressource is Forbidden. (status code 403)
     *         or The expected ressource is not found. (status code 404)
     *         or Unexpected error at the server side. (status code 500)
     */
    @ApiOperation(value = "Path used to update or modify an existing Packaging in the system", nickname = "updatePackaging", notes = "", response = PackagingDto.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Packaging updated successfully", response = PackagingDto.class),
        @ApiResponse(code = 400, message = "Bad request. There is something wrong in the request."),
        @ApiResponse(code = 401, message = "Authorization information is missing or invalid."),
        @ApiResponse(code = 403, message = "The user who ask the ressource is Forbidden."),
        @ApiResponse(code = 404, message = "The expected ressource is not found."),
        @ApiResponse(code = 500, message = "Unexpected error at the server side.") })
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/packaging/update",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<PackagingDto> _updatePackaging(@ApiParam(value = "") @Valid @RequestBody(required = false) PackagingDto packagingDto) {
        return updatePackaging(packagingDto);
    }

    // Override this method
    default  ResponseEntity<PackagingDto> updatePackaging(PackagingDto packagingDto) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"packDescription\" : \"packDescription\", \"packagingProvider\" : { \"providerBalance\" : 5, \"providerAddress\" : { \"country\" : \"country\", \"numtel3\" : \"numtel3\", \"town\" : \"town\", \"numtel1\" : \"678470262\", \"numtel2\" : \"numtel2\", \"localisation\" : \"localisation\", \"id\" : 0, \"email\" : \"abc@gmail.com\", \"quarter\" : \"quarter\" }, \"providerAcronym\" : \"providerAcronym\", \"providerDescription\" : \"providerDescription\", \"providerName\" : \"providerName\" }, \"packLabel\" : \"packLabel\", \"packFirstcolor\" : \"packFirstcolor\", \"packPrice\" : 7.457744773683766 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}