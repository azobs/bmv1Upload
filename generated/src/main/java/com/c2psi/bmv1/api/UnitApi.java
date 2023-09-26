/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (5.3.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.c2psi.bmv1.api;

import com.c2psi.bmv1.dto.FilterRequest;
import com.c2psi.bmv1.dto.PageofUnitDto;
import com.c2psi.bmv1.dto.PageofUnitconversionDto;
import com.c2psi.bmv1.dto.UnitDto;
import com.c2psi.bmv1.dto.UnitconversionDto;
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
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-26T01:24:15.865861+01:00[Africa/Casablanca]")
@Validated
@Api(value = "unit", description = "the unit API")
public interface UnitApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * DELETE /unit/bm/v1/unit/delete/{id} : Path used to delete a unit in the system with its id
     *
     * @param id The id that represent the Unit to delete. It&#39;s compulsory if not the operation can&#39;t proceed (required)
     * @return Unit deleted successfully (status code 200)
     *         or Bad request. Unit ID must be an integer and larger than 0. (status code 400)
     *         or Authorization information is missing or invalid. (status code 401)
     *         or The user who ask the ressource is Forbidden. (status code 403)
     *         or The expected ressource is not found. (status code 404)
     *         or Unexpected error at the server side. (status code 500)
     */
    @ApiOperation(value = "Path used to delete a unit in the system with its id", nickname = "deleteUnitById", notes = "", response = Boolean.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Unit deleted successfully", response = Boolean.class),
        @ApiResponse(code = 400, message = "Bad request. Unit ID must be an integer and larger than 0."),
        @ApiResponse(code = 401, message = "Authorization information is missing or invalid."),
        @ApiResponse(code = 403, message = "The user who ask the ressource is Forbidden."),
        @ApiResponse(code = 404, message = "The expected ressource is not found."),
        @ApiResponse(code = 500, message = "Unexpected error at the server side.") })
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/unit/bm/v1/unit/delete/{id}",
        produces = { "application/json" }
    )
    default ResponseEntity<Boolean> _deleteUnitById(@ApiParam(value = "The id that represent the Unit to delete. It's compulsory if not the operation can't proceed", required = true) @PathVariable("id") Long id) {
        return deleteUnitById(id);
    }

    // Override this method
    default  ResponseEntity<Boolean> deleteUnitById(Long id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * DELETE /unit/bm/v1/conversion/delete/{id} : Path used to delete a unit conversion in the system with its id
     *
     * @param id The id that represent the Unitconversion to delete. It&#39;s compulsory if not the operation can&#39;t proceed (required)
     * @return Unitconversion deleted successfully (status code 200)
     *         or Bad request. Unitconversion ID must be an integer and larger than 0. (status code 400)
     *         or Authorization information is missing or invalid. (status code 401)
     *         or The user who ask the ressource is Forbidden. (status code 403)
     *         or The expected ressource is not found. (status code 404)
     *         or Unexpected error at the server side. (status code 500)
     */
    @ApiOperation(value = "Path used to delete a unit conversion in the system with its id", nickname = "deleteUnitconversionById", notes = "", response = Boolean.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Unitconversion deleted successfully", response = Boolean.class),
        @ApiResponse(code = 400, message = "Bad request. Unitconversion ID must be an integer and larger than 0."),
        @ApiResponse(code = 401, message = "Authorization information is missing or invalid."),
        @ApiResponse(code = 403, message = "The user who ask the ressource is Forbidden."),
        @ApiResponse(code = 404, message = "The expected ressource is not found."),
        @ApiResponse(code = 500, message = "Unexpected error at the server side.") })
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/unit/bm/v1/conversion/delete/{id}",
        produces = { "application/json" }
    )
    default ResponseEntity<Boolean> _deleteUnitconversionById(@ApiParam(value = "The id that represent the Unitconversion to delete. It's compulsory if not the operation can't proceed", required = true) @PathVariable("id") Long id) {
        return deleteUnitconversionById(id);
    }

    // Override this method
    default  ResponseEntity<Boolean> deleteUnitconversionById(Long id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /unit/bm/v1/unit/getby/{id} : Find a Unit in the system by its id
     *
     * @param id The id that represent the Unit found. It&#39;s compulsory if not the operation can&#39;t proceed (required)
     * @return Unit found successfully (status code 200)
     *         or Bad request. Unit ID must be an integer and larger than 0. (status code 400)
     *         or Authorization information is missing or invalid. (status code 401)
     *         or The user who ask the ressource is Forbidden. (status code 403)
     *         or The expected ressource is not found. (status code 404)
     *         or Unexpected error at the server side. (status code 500)
     */
    @ApiOperation(value = "Find a Unit in the system by its id", nickname = "getUnitById", notes = "", response = UnitDto.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Unit found successfully", response = UnitDto.class),
        @ApiResponse(code = 400, message = "Bad request. Unit ID must be an integer and larger than 0."),
        @ApiResponse(code = 401, message = "Authorization information is missing or invalid."),
        @ApiResponse(code = 403, message = "The user who ask the ressource is Forbidden."),
        @ApiResponse(code = 404, message = "The expected ressource is not found."),
        @ApiResponse(code = 500, message = "Unexpected error at the server side.") })
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/unit/bm/v1/unit/getby/{id}",
        produces = { "application/json" }
    )
    default ResponseEntity<UnitDto> _getUnitById(@ApiParam(value = "The id that represent the Unit found. It's compulsory if not the operation can't proceed", required = true) @PathVariable("id") Long id) {
        return getUnitById(id);
    }

    // Override this method
    default  ResponseEntity<UnitDto> getUnitById(Long id) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"unitName\" : \"unitName\", \"unitAbbreviation\" : \"UN\", \"unitPosId\" : 6, \"id\" : 0 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * POST /unit/bm/v1/unit/list : Path used to list unit that respect certain criteria. A criteria is an instance of a Filter object
     *
     * @param filterRequest  (optional)
     * @return Unit list found successfully (status code 200)
     */
    @ApiOperation(value = "Path used to list unit that respect certain criteria. A criteria is an instance of a Filter object", nickname = "getUnitList", notes = "", response = UnitDto.class, responseContainer = "List", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Unit list found successfully", response = UnitDto.class, responseContainer = "List") })
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/unit/bm/v1/unit/list",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<List<UnitDto>> _getUnitList(@ApiParam(value = "") @Valid @RequestBody(required = false) FilterRequest filterRequest) {
        return getUnitList(filterRequest);
    }

    // Override this method
    default  ResponseEntity<List<UnitDto>> getUnitList(FilterRequest filterRequest) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"unitName\" : \"unitName\", \"unitAbbreviation\" : \"UN\", \"unitPosId\" : 6, \"id\" : 0 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * POST /unit/bm/v1/unit/page : Path used to list unit page by page that respect certain criteria. With the Page object, we can configure the page number and size that we want
     *
     * @param filterRequest  (optional)
     * @return Unit page found successfully (status code 200)
     */
    @ApiOperation(value = "Path used to list unit page by page that respect certain criteria. With the Page object, we can configure the page number and size that we want", nickname = "getUnitPage", notes = "", response = PageofUnitDto.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Unit page found successfully", response = PageofUnitDto.class) })
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/unit/bm/v1/unit/page",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<PageofUnitDto> _getUnitPage(@ApiParam(value = "") @Valid @RequestBody(required = false) FilterRequest filterRequest) {
        return getUnitPage(filterRequest);
    }

    // Override this method
    default  ResponseEntity<PageofUnitDto> getUnitPage(FilterRequest filterRequest) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"totalPages\" : 6, \"pageSize\" : 10, \"currentPage\" : 0, \"content\" : [ { \"unitName\" : \"unitName\", \"unitAbbreviation\" : \"UN\", \"unitPosId\" : 6, \"id\" : 0 }, { \"unitName\" : \"unitName\", \"unitAbbreviation\" : \"UN\", \"unitPosId\" : 6, \"id\" : 0 } ], \"totalElements\" : 0 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /unit/bm/v1/conversion/getby/{id} : Find a Unitconversion in the system by its id
     *
     * @param id The id that represent the Unitconversion found. It&#39;s compulsory if not the operation can&#39;t proceed (required)
     * @return Unitconversion found successfully (status code 200)
     *         or Bad request. Unitconversion ID must be an integer and larger than 0. (status code 400)
     *         or Authorization information is missing or invalid. (status code 401)
     *         or The user who ask the ressource is Forbidden. (status code 403)
     *         or The expected ressource is not found. (status code 404)
     *         or Unexpected error at the server side. (status code 500)
     */
    @ApiOperation(value = "Find a Unitconversion in the system by its id", nickname = "getUnitconversionById", notes = "", response = UnitconversionDto.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Unitconversion found successfully", response = UnitconversionDto.class),
        @ApiResponse(code = 400, message = "Bad request. Unitconversion ID must be an integer and larger than 0."),
        @ApiResponse(code = 401, message = "Authorization information is missing or invalid."),
        @ApiResponse(code = 403, message = "The user who ask the ressource is Forbidden."),
        @ApiResponse(code = 404, message = "The expected ressource is not found."),
        @ApiResponse(code = 500, message = "Unexpected error at the server side.") })
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/unit/bm/v1/conversion/getby/{id}",
        produces = { "application/json" }
    )
    default ResponseEntity<UnitconversionDto> _getUnitconversionById(@ApiParam(value = "The id that represent the Unitconversion found. It's compulsory if not the operation can't proceed", required = true) @PathVariable("id") Long id) {
        return getUnitconversionById(id);
    }

    // Override this method
    default  ResponseEntity<UnitconversionDto> getUnitconversionById(Long id) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"unitSourceId\" : 1, \"conversionFactor\" : 6.027456183070403, \"unitDestinationId\" : 5, \"id\" : 0 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * POST /unit/bm/v1/conversion/list : Path used to list unitconversion that respect certain criteria. A criteria is an instance of a Filter object
     *
     * @param filterRequest  (optional)
     * @return Unitconversion list found successfully (status code 200)
     */
    @ApiOperation(value = "Path used to list unitconversion that respect certain criteria. A criteria is an instance of a Filter object", nickname = "getUnitconversionList", notes = "", response = UnitconversionDto.class, responseContainer = "List", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Unitconversion list found successfully", response = UnitconversionDto.class, responseContainer = "List") })
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/unit/bm/v1/conversion/list",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<List<UnitconversionDto>> _getUnitconversionList(@ApiParam(value = "") @Valid @RequestBody(required = false) FilterRequest filterRequest) {
        return getUnitconversionList(filterRequest);
    }

    // Override this method
    default  ResponseEntity<List<UnitconversionDto>> getUnitconversionList(FilterRequest filterRequest) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"unitSourceId\" : 1, \"conversionFactor\" : 6.027456183070403, \"unitDestinationId\" : 5, \"id\" : 0 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * POST /unit/bm/v1/conversion/page : Path used to list unitconversion page by page that respect certain criteria. With the Page object, we can configure the page number and size that we want
     *
     * @param filterRequest  (optional)
     * @return Unitconversion page found successfully (status code 200)
     */
    @ApiOperation(value = "Path used to list unitconversion page by page that respect certain criteria. With the Page object, we can configure the page number and size that we want", nickname = "getUnitconversionPage", notes = "", response = PageofUnitconversionDto.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Unitconversion page found successfully", response = PageofUnitconversionDto.class) })
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/unit/bm/v1/conversion/page",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<PageofUnitconversionDto> _getUnitconversionPage(@ApiParam(value = "") @Valid @RequestBody(required = false) FilterRequest filterRequest) {
        return getUnitconversionPage(filterRequest);
    }

    // Override this method
    default  ResponseEntity<PageofUnitconversionDto> getUnitconversionPage(FilterRequest filterRequest) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"totalPages\" : 6, \"pageSize\" : 10, \"currentPage\" : 0, \"content\" : [ { \"unitSourceId\" : 1, \"conversionFactor\" : 6.027456183070403, \"unitDestinationId\" : 5, \"id\" : 0 }, { \"unitSourceId\" : 1, \"conversionFactor\" : 6.027456183070403, \"unitDestinationId\" : 5, \"id\" : 0 } ], \"totalElements\" : 0 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * POST /unit/bm/v1/unit/create : Path used to save a new unit used to sell productformated in the system
     *
     * @param unitDto  (optional)
     * @return Unit saved successfully (status code 200)
     *         or Bad request. There is something wrong in the request. (status code 400)
     *         or Authorization information is missing or invalid. (status code 401)
     *         or The user who ask the ressource is Forbidden. (status code 403)
     *         or The expected ressource is not found. (status code 404)
     *         or Unexpected error at the server side. (status code 500)
     */
    @ApiOperation(value = "Path used to save a new unit used to sell productformated in the system", nickname = "saveUnit", notes = "", response = UnitDto.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Unit saved successfully", response = UnitDto.class),
        @ApiResponse(code = 400, message = "Bad request. There is something wrong in the request."),
        @ApiResponse(code = 401, message = "Authorization information is missing or invalid."),
        @ApiResponse(code = 403, message = "The user who ask the ressource is Forbidden."),
        @ApiResponse(code = 404, message = "The expected ressource is not found."),
        @ApiResponse(code = 500, message = "Unexpected error at the server side.") })
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/unit/bm/v1/unit/create",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<UnitDto> _saveUnit(@ApiParam(value = "") @Valid @RequestBody(required = false) UnitDto unitDto) {
        return saveUnit(unitDto);
    }

    // Override this method
    default  ResponseEntity<UnitDto> saveUnit(UnitDto unitDto) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"unitName\" : \"unitName\", \"unitAbbreviation\" : \"UN\", \"unitPosId\" : 6, \"id\" : 0 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * POST /unit/bm/v1/conversion/create : Path used to save a new unitconversion used to sell productformated in the system
     *
     * @param unitconversionDto  (optional)
     * @return Unitconversion saved successfully (status code 200)
     *         or Bad request. There is something wrong in the request. (status code 400)
     *         or Authorization information is missing or invalid. (status code 401)
     *         or The user who ask the ressource is Forbidden. (status code 403)
     *         or The expected ressource is not found. (status code 404)
     *         or Unexpected error at the server side. (status code 500)
     */
    @ApiOperation(value = "Path used to save a new unitconversion used to sell productformated in the system", nickname = "saveUnitconversion", notes = "", response = UnitconversionDto.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Unitconversion saved successfully", response = UnitconversionDto.class),
        @ApiResponse(code = 400, message = "Bad request. There is something wrong in the request."),
        @ApiResponse(code = 401, message = "Authorization information is missing or invalid."),
        @ApiResponse(code = 403, message = "The user who ask the ressource is Forbidden."),
        @ApiResponse(code = 404, message = "The expected ressource is not found."),
        @ApiResponse(code = 500, message = "Unexpected error at the server side.") })
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/unit/bm/v1/conversion/create",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<UnitconversionDto> _saveUnitconversion(@ApiParam(value = "") @Valid @RequestBody(required = false) UnitconversionDto unitconversionDto) {
        return saveUnitconversion(unitconversionDto);
    }

    // Override this method
    default  ResponseEntity<UnitconversionDto> saveUnitconversion(UnitconversionDto unitconversionDto) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"unitSourceId\" : 1, \"conversionFactor\" : 6.027456183070403, \"unitDestinationId\" : 5, \"id\" : 0 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PUT /unit/bm/v1/unit/update : Path used to update or modify an existing unit in the system
     *
     * @param unitDto  (optional)
     * @return Unit updated successfully (status code 200)
     *         or Bad request. There is something wrong in the request. (status code 400)
     *         or Authorization information is missing or invalid. (status code 401)
     *         or The user who ask the ressource is Forbidden. (status code 403)
     *         or The expected ressource is not found. (status code 404)
     *         or Unexpected error at the server side. (status code 500)
     */
    @ApiOperation(value = "Path used to update or modify an existing unit in the system", nickname = "updateUnit", notes = "", response = UnitDto.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Unit updated successfully", response = UnitDto.class),
        @ApiResponse(code = 400, message = "Bad request. There is something wrong in the request."),
        @ApiResponse(code = 401, message = "Authorization information is missing or invalid."),
        @ApiResponse(code = 403, message = "The user who ask the ressource is Forbidden."),
        @ApiResponse(code = 404, message = "The expected ressource is not found."),
        @ApiResponse(code = 500, message = "Unexpected error at the server side.") })
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/unit/bm/v1/unit/update",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<UnitDto> _updateUnit(@ApiParam(value = "") @Valid @RequestBody(required = false) UnitDto unitDto) {
        return updateUnit(unitDto);
    }

    // Override this method
    default  ResponseEntity<UnitDto> updateUnit(UnitDto unitDto) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"unitName\" : \"unitName\", \"unitAbbreviation\" : \"UN\", \"unitPosId\" : 6, \"id\" : 0 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PUT /unit/bm/v1/conversion/update : Path used to update or modify an existing unitconversion in the system
     *
     * @param unitconversionDto  (optional)
     * @return Unitconversion updated successfully (status code 200)
     *         or Bad request. There is something wrong in the request. (status code 400)
     *         or Authorization information is missing or invalid. (status code 401)
     *         or The user who ask the ressource is Forbidden. (status code 403)
     *         or The expected ressource is not found. (status code 404)
     *         or Unexpected error at the server side. (status code 500)
     */
    @ApiOperation(value = "Path used to update or modify an existing unitconversion in the system", nickname = "updateUnitconversion", notes = "", response = UnitconversionDto.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Unitconversion updated successfully", response = UnitconversionDto.class),
        @ApiResponse(code = 400, message = "Bad request. There is something wrong in the request."),
        @ApiResponse(code = 401, message = "Authorization information is missing or invalid."),
        @ApiResponse(code = 403, message = "The user who ask the ressource is Forbidden."),
        @ApiResponse(code = 404, message = "The expected ressource is not found."),
        @ApiResponse(code = 500, message = "Unexpected error at the server side.") })
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/unit/bm/v1/conversion/update",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<UnitconversionDto> _updateUnitconversion(@ApiParam(value = "") @Valid @RequestBody(required = false) UnitconversionDto unitconversionDto) {
        return updateUnitconversion(unitconversionDto);
    }

    // Override this method
    default  ResponseEntity<UnitconversionDto> updateUnitconversion(UnitconversionDto unitconversionDto) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"unitSourceId\" : 1, \"conversionFactor\" : 6.027456183070403, \"unitDestinationId\" : 5, \"id\" : 0 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
