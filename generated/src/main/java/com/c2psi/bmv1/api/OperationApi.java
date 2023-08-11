/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (5.3.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.c2psi.bmv1.api;

import com.c2psi.bmv1.dto.FilterRequest;
import com.c2psi.bmv1.dto.OperationDto;
import com.c2psi.bmv1.dto.PageofOperationDto;
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
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-11T16:08:25.464702700+01:00[Africa/Douala]")
@Validated
@Api(value = "operation", description = "the operation API")
public interface OperationApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * DELETE /operation/bmV1.0/delete/{id} : Path used to delete an operation in the system with its id
     *
     * @param id The id that represent the Operation to delete. It&#39;s compulsory if not the operation can&#39;t proceed (required)
     * @return Operation deleted successfully (status code 200)
     *         or Bad request. Operation ID must be an integer and larger than 0. (status code 400)
     *         or Authorization information is missing or invalid. (status code 401)
     *         or The user who ask the ressource is Forbidden. (status code 403)
     *         or The expected ressource is not found. (status code 404)
     *         or Unexpected error at the server side. (status code 500)
     */
    @ApiOperation(value = "Path used to delete an operation in the system with its id", nickname = "deleteOperationById", notes = "", response = Boolean.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Operation deleted successfully", response = Boolean.class),
        @ApiResponse(code = 400, message = "Bad request. Operation ID must be an integer and larger than 0."),
        @ApiResponse(code = 401, message = "Authorization information is missing or invalid."),
        @ApiResponse(code = 403, message = "The user who ask the ressource is Forbidden."),
        @ApiResponse(code = 404, message = "The expected ressource is not found."),
        @ApiResponse(code = 500, message = "Unexpected error at the server side.") })
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/operation/bmV1.0/delete/{id}",
        produces = { "application/json" }
    )
    default ResponseEntity<Boolean> _deleteOperationById(@ApiParam(value = "The id that represent the Operation to delete. It's compulsory if not the operation can't proceed", required = true) @PathVariable("id") Long id) {
        return deleteOperationById(id);
    }

    // Override this method
    default  ResponseEntity<Boolean> deleteOperationById(Long id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /operation/bmV1.0/getby/{id} : Find an Operation in the system by its id
     *
     * @param id The id that represent the Operation found. It&#39;s compulsory if not the operation can&#39;t proceed (required)
     * @return Operation found successfully (status code 200)
     *         or Bad request. Operation ID must be an integer and larger than 0. (status code 400)
     *         or Authorization information is missing or invalid. (status code 401)
     *         or The user who ask the ressource is Forbidden. (status code 403)
     *         or The expected ressource is not found. (status code 404)
     *         or Unexpected error at the server side. (status code 500)
     */
    @ApiOperation(value = "Find an Operation in the system by its id", nickname = "getOperationById", notes = "", response = OperationDto.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Operation found successfully", response = OperationDto.class),
        @ApiResponse(code = 400, message = "Bad request. Operation ID must be an integer and larger than 0."),
        @ApiResponse(code = 401, message = "Authorization information is missing or invalid."),
        @ApiResponse(code = 403, message = "The user who ask the ressource is Forbidden."),
        @ApiResponse(code = 404, message = "The expected ressource is not found."),
        @ApiResponse(code = 500, message = "Unexpected error at the server side.") })
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/operation/bmV1.0/getby/{id}",
        produces = { "application/json" }
    )
    default ResponseEntity<OperationDto> _getOperationById(@ApiParam(value = "The id that represent the Operation found. It's compulsory if not the operation can't proceed", required = true) @PathVariable("id") Long id) {
        return getOperationById(id);
    }

    // Override this method
    default  ResponseEntity<OperationDto> getOperationById(Long id) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"opObject\" : \"opObject\", \"opType\" : \"Credit\", \"id\" : 0, \"opDate\" : \"2000-01-23T04:56:07.000+00:00\", \"opDescription\" : \"opDescription\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * POST /operation/bmV1.0/list : Path used to list operation that respect certain criteria. A criteria is an instance of a Filter object
     *
     * @param filterRequest  (optional)
     * @return Operation list found successfully (status code 200)
     */
    @ApiOperation(value = "Path used to list operation that respect certain criteria. A criteria is an instance of a Filter object", nickname = "getOperationList", notes = "", response = OperationDto.class, responseContainer = "List", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Operation list found successfully", response = OperationDto.class, responseContainer = "List") })
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/operation/bmV1.0/list",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<List<OperationDto>> _getOperationList(@ApiParam(value = "") @Valid @RequestBody(required = false) FilterRequest filterRequest) {
        return getOperationList(filterRequest);
    }

    // Override this method
    default  ResponseEntity<List<OperationDto>> getOperationList(FilterRequest filterRequest) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"opObject\" : \"opObject\", \"opType\" : \"Credit\", \"id\" : 0, \"opDate\" : \"2000-01-23T04:56:07.000+00:00\", \"opDescription\" : \"opDescription\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * POST /operation/bmV1.0/page : Path used to list Operation page by page that respect certain criteria. With the Page object, we can configure the page number and size that we want
     *
     * @param filterRequest  (optional)
     * @return Operation page found successfully (status code 200)
     */
    @ApiOperation(value = "Path used to list Operation page by page that respect certain criteria. With the Page object, we can configure the page number and size that we want", nickname = "getOperationPage", notes = "", response = PageofOperationDto.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Operation page found successfully", response = PageofOperationDto.class) })
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/operation/bmV1.0/page",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<PageofOperationDto> _getOperationPage(@ApiParam(value = "") @Valid @RequestBody(required = false) FilterRequest filterRequest) {
        return getOperationPage(filterRequest);
    }

    // Override this method
    default  ResponseEntity<PageofOperationDto> getOperationPage(FilterRequest filterRequest) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"totalPages\" : 6, \"pageSize\" : 10, \"currentPage\" : 0, \"content\" : [ { \"opObject\" : \"opObject\", \"opType\" : \"Credit\", \"id\" : 0, \"opDate\" : \"2000-01-23T04:56:07.000+00:00\", \"opDescription\" : \"opDescription\" }, { \"opObject\" : \"opObject\", \"opType\" : \"Credit\", \"id\" : 0, \"opDate\" : \"2000-01-23T04:56:07.000+00:00\", \"opDescription\" : \"opDescription\" } ], \"totalElements\" : 0 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * POST /operation/bmV1.0/create : Path used to save a new Operation in the system to have history of all operations in the system
     *
     * @param operationDto  (optional)
     * @return Operation saved successfully (status code 200)
     *         or Bad request. There is something wrong in the request. (status code 400)
     *         or Authorization information is missing or invalid. (status code 401)
     *         or The user who ask the ressource is Forbidden. (status code 403)
     *         or The expected ressource is not found. (status code 404)
     *         or Unexpected error at the server side. (status code 500)
     */
    @ApiOperation(value = "Path used to save a new Operation in the system to have history of all operations in the system", nickname = "saveOperation", notes = "", response = OperationDto.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Operation saved successfully", response = OperationDto.class),
        @ApiResponse(code = 400, message = "Bad request. There is something wrong in the request."),
        @ApiResponse(code = 401, message = "Authorization information is missing or invalid."),
        @ApiResponse(code = 403, message = "The user who ask the ressource is Forbidden."),
        @ApiResponse(code = 404, message = "The expected ressource is not found."),
        @ApiResponse(code = 500, message = "Unexpected error at the server side.") })
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/operation/bmV1.0/create",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<OperationDto> _saveOperation(@ApiParam(value = "") @Valid @RequestBody(required = false) OperationDto operationDto) {
        return saveOperation(operationDto);
    }

    // Override this method
    default  ResponseEntity<OperationDto> saveOperation(OperationDto operationDto) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"opObject\" : \"opObject\", \"opType\" : \"Credit\", \"id\" : 0, \"opDate\" : \"2000-01-23T04:56:07.000+00:00\", \"opDescription\" : \"opDescription\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PUT /operation/bmV1.0/update : Path used to update or modify an existing Operation in the system
     *
     * @param operationDto  (optional)
     * @return Operation updated successfully (status code 200)
     *         or Bad request. There is something wrong in the request. (status code 400)
     *         or Authorization information is missing or invalid. (status code 401)
     *         or The user who ask the ressource is Forbidden. (status code 403)
     *         or The expected ressource is not found. (status code 404)
     *         or Unexpected error at the server side. (status code 500)
     */
    @ApiOperation(value = "Path used to update or modify an existing Operation in the system", nickname = "updateOperation", notes = "", response = OperationDto.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Operation updated successfully", response = OperationDto.class),
        @ApiResponse(code = 400, message = "Bad request. There is something wrong in the request."),
        @ApiResponse(code = 401, message = "Authorization information is missing or invalid."),
        @ApiResponse(code = 403, message = "The user who ask the ressource is Forbidden."),
        @ApiResponse(code = 404, message = "The expected ressource is not found."),
        @ApiResponse(code = 500, message = "Unexpected error at the server side.") })
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/operation/bmV1.0/update",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<OperationDto> _updateOperation(@ApiParam(value = "") @Valid @RequestBody(required = false) OperationDto operationDto) {
        return updateOperation(operationDto);
    }

    // Override this method
    default  ResponseEntity<OperationDto> updateOperation(OperationDto operationDto) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"opObject\" : \"opObject\", \"opType\" : \"Credit\", \"id\" : 0, \"opDate\" : \"2000-01-23T04:56:07.000+00:00\", \"opDescription\" : \"opDescription\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
