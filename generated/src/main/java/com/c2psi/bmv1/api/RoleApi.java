/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (5.3.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.c2psi.bmv1.api;

import com.c2psi.bmv1.dto.FilterRequest;
import com.c2psi.bmv1.dto.PageofRoleDto;
import com.c2psi.bmv1.dto.RoleDto;
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
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-28T04:24:19.978343600+01:00[Africa/Casablanca]")
@Validated
@Api(value = "role", description = "the role API")
public interface RoleApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * DELETE /role/bm/v1/delete/{id} : Path used to delete a role in the system with its id
     *
     * @param id The id that represent the Role to delete. It&#39;s compulsory if not the operation can&#39;t proceed (required)
     * @return Role deleted successfully (status code 200)
     *         or Bad request. User ID must be an integer and larger than 0. (status code 400)
     *         or Authorization information is missing or invalid. (status code 401)
     *         or The user who ask the ressource is Forbidden. (status code 403)
     *         or The expected ressource is not found. (status code 404)
     *         or Unexpected error at the server side. (status code 500)
     */
    @ApiOperation(value = "Path used to delete a role in the system with its id", nickname = "deleteRoleById", notes = "", response = Boolean.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Role deleted successfully", response = Boolean.class),
        @ApiResponse(code = 400, message = "Bad request. User ID must be an integer and larger than 0."),
        @ApiResponse(code = 401, message = "Authorization information is missing or invalid."),
        @ApiResponse(code = 403, message = "The user who ask the ressource is Forbidden."),
        @ApiResponse(code = 404, message = "The expected ressource is not found."),
        @ApiResponse(code = 500, message = "Unexpected error at the server side.") })
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/role/bm/v1/delete/{id}",
        produces = { "application/json" }
    )
    default ResponseEntity<Boolean> _deleteRoleById(@ApiParam(value = "The id that represent the Role to delete. It's compulsory if not the operation can't proceed", required = true) @PathVariable("id") Long id) {
        return deleteRoleById(id);
    }

    // Override this method
    default  ResponseEntity<Boolean> deleteRoleById(Long id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /role/bm/v1/getby/{id} : Find a Role in the system by its id
     *
     * @param id The id that represent the Role found. It&#39;s compulsory if not the operation can&#39;t proceed (required)
     * @return Role found successfully (status code 200)
     *         or Bad request. Role ID must be an integer and larger than 0. (status code 400)
     *         or Authorization information is missing or invalid. (status code 401)
     *         or The user who ask the ressource is Forbidden. (status code 403)
     *         or The expected ressource is not found. (status code 404)
     *         or Unexpected error at the server side. (status code 500)
     */
    @ApiOperation(value = "Find a Role in the system by its id", nickname = "getRoleById", notes = "", response = RoleDto.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Role found successfully", response = RoleDto.class),
        @ApiResponse(code = 400, message = "Bad request. Role ID must be an integer and larger than 0."),
        @ApiResponse(code = 401, message = "Authorization information is missing or invalid."),
        @ApiResponse(code = 403, message = "The user who ask the ressource is Forbidden."),
        @ApiResponse(code = 404, message = "The expected ressource is not found."),
        @ApiResponse(code = 500, message = "Unexpected error at the server side.") })
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/role/bm/v1/getby/{id}",
        produces = { "application/json" }
    )
    default ResponseEntity<RoleDto> _getRoleById(@ApiParam(value = "The id that represent the Role found. It's compulsory if not the operation can't proceed", required = true) @PathVariable("id") Long id) {
        return getRoleById(id);
    }

    // Override this method
    default  ResponseEntity<RoleDto> getRoleById(Long id) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"roleEntId\" : 1, \"roleName\" : \"Admin\", \"rolePosId\" : 6, \"id\" : 0, \"roleType\" : \"ADMINBM\", \"roleDescription\" : \"roleDescription\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /role/bm/v1/getby/{roletype} : Find a Role in the system by its login
     *
     * @param roletype The role type that represent the Role found. It&#39;s compulsory if not the operation can&#39;t proceed (required)
     * @return Role found successfully (status code 200)
     *         or Bad request. Role type must be an string and not empty. (status code 400)
     *         or Authorization information is missing or invalid. (status code 401)
     *         or The user who ask the ressource is Forbidden. (status code 403)
     *         or The expected ressource is not found. (status code 404)
     *         or Unexpected error at the server side. (status code 500)
     */
    @ApiOperation(value = "Find a Role in the system by its login", nickname = "getRoleByRoletype", notes = "", response = RoleDto.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Role found successfully", response = RoleDto.class),
        @ApiResponse(code = 400, message = "Bad request. Role type must be an string and not empty."),
        @ApiResponse(code = 401, message = "Authorization information is missing or invalid."),
        @ApiResponse(code = 403, message = "The user who ask the ressource is Forbidden."),
        @ApiResponse(code = 404, message = "The expected ressource is not found."),
        @ApiResponse(code = 500, message = "Unexpected error at the server side.") })
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/role/bm/v1/getby/{roletype}",
        produces = { "application/json" }
    )
    default ResponseEntity<RoleDto> _getRoleByRoletype(@ApiParam(value = "The role type that represent the Role found. It's compulsory if not the operation can't proceed", required = true) @PathVariable("roletype") String roletype) {
        return getRoleByRoletype(roletype);
    }

    // Override this method
    default  ResponseEntity<RoleDto> getRoleByRoletype(String roletype) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"roleEntId\" : 1, \"roleName\" : \"Admin\", \"rolePosId\" : 6, \"id\" : 0, \"roleType\" : \"ADMINBM\", \"roleDescription\" : \"roleDescription\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * POST /role/bm/v1/list : Path used to list role that respect certain criteria. A criteria is an instance of a Filter object
     *
     * @param filterRequest  (optional)
     * @return Role found successfully (status code 200)
     */
    @ApiOperation(value = "Path used to list role that respect certain criteria. A criteria is an instance of a Filter object", nickname = "getRoleList", notes = "", response = RoleDto.class, responseContainer = "List", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Role found successfully", response = RoleDto.class, responseContainer = "List") })
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/role/bm/v1/list",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<List<RoleDto>> _getRoleList(@ApiParam(value = "") @Valid @RequestBody(required = false) FilterRequest filterRequest) {
        return getRoleList(filterRequest);
    }

    // Override this method
    default  ResponseEntity<List<RoleDto>> getRoleList(FilterRequest filterRequest) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"roleEntId\" : 1, \"roleName\" : \"Admin\", \"rolePosId\" : 6, \"id\" : 0, \"roleType\" : \"ADMINBM\", \"roleDescription\" : \"roleDescription\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * POST /role/bm/v1/page : Path used to list roles page by page that respect certain criteria. With the Page object, we can configure the page number and size that we want
     *
     * @param filterRequest  (optional)
     * @return Role page found successfully (status code 200)
     */
    @ApiOperation(value = "Path used to list roles page by page that respect certain criteria. With the Page object, we can configure the page number and size that we want", nickname = "getRolePage", notes = "", response = PageofRoleDto.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Role page found successfully", response = PageofRoleDto.class) })
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/role/bm/v1/page",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<PageofRoleDto> _getRolePage(@ApiParam(value = "") @Valid @RequestBody(required = false) FilterRequest filterRequest) {
        return getRolePage(filterRequest);
    }

    // Override this method
    default  ResponseEntity<PageofRoleDto> getRolePage(FilterRequest filterRequest) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"totalPages\" : 6, \"pageSize\" : 10, \"currentPage\" : 0, \"content\" : [ { \"roleEntId\" : 1, \"roleName\" : \"Admin\", \"rolePosId\" : 6, \"id\" : 0, \"roleType\" : \"ADMINBM\", \"roleDescription\" : \"roleDescription\" }, { \"roleEntId\" : 1, \"roleName\" : \"Admin\", \"rolePosId\" : 6, \"id\" : 0, \"roleType\" : \"ADMINBM\", \"roleDescription\" : \"roleDescription\" } ], \"totalElements\" : 0 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * POST /role/bm/v1/create : Path used to save a new role in the system
     *
     * @param roleDto  (optional)
     * @return Role saved successfully (status code 200)
     *         or Bad request. There is something wrong in the request. (status code 400)
     *         or Authorization information is missing or invalid. (status code 401)
     *         or The user who ask the ressource is Forbidden. (status code 403)
     *         or The expected ressource is not found. (status code 404)
     *         or Unexpected error at the server side. (status code 500)
     */
    @ApiOperation(value = "Path used to save a new role in the system", nickname = "saveRole", notes = "", response = RoleDto.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Role saved successfully", response = RoleDto.class),
        @ApiResponse(code = 400, message = "Bad request. There is something wrong in the request."),
        @ApiResponse(code = 401, message = "Authorization information is missing or invalid."),
        @ApiResponse(code = 403, message = "The user who ask the ressource is Forbidden."),
        @ApiResponse(code = 404, message = "The expected ressource is not found."),
        @ApiResponse(code = 500, message = "Unexpected error at the server side.") })
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/role/bm/v1/create",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<RoleDto> _saveRole(@ApiParam(value = "") @Valid @RequestBody(required = false) RoleDto roleDto) {
        return saveRole(roleDto);
    }

    // Override this method
    default  ResponseEntity<RoleDto> saveRole(RoleDto roleDto) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"roleEntId\" : 1, \"roleName\" : \"Admin\", \"rolePosId\" : 6, \"id\" : 0, \"roleType\" : \"ADMINBM\", \"roleDescription\" : \"roleDescription\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PUT /role/bm/v1/update : Path used to update or modify an existing role in the system
     *
     * @param roleDto  (optional)
     * @return Role updated successfully (status code 200)
     *         or Bad request. There is something wrong in the request. (status code 400)
     *         or Authorization information is missing or invalid. (status code 401)
     *         or The user who ask the ressource is Forbidden. (status code 403)
     *         or The expected ressource is not found. (status code 404)
     *         or Unexpected error at the server side. (status code 500)
     */
    @ApiOperation(value = "Path used to update or modify an existing role in the system", nickname = "updateRole", notes = "", response = RoleDto.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Role updated successfully", response = RoleDto.class),
        @ApiResponse(code = 400, message = "Bad request. There is something wrong in the request."),
        @ApiResponse(code = 401, message = "Authorization information is missing or invalid."),
        @ApiResponse(code = 403, message = "The user who ask the ressource is Forbidden."),
        @ApiResponse(code = 404, message = "The expected ressource is not found."),
        @ApiResponse(code = 500, message = "Unexpected error at the server side.") })
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/role/bm/v1/update",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<RoleDto> _updateRole(@ApiParam(value = "") @Valid @RequestBody(required = false) RoleDto roleDto) {
        return updateRole(roleDto);
    }

    // Override this method
    default  ResponseEntity<RoleDto> updateRole(RoleDto roleDto) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"roleEntId\" : 1, \"roleName\" : \"Admin\", \"rolePosId\" : 6, \"id\" : 0, \"roleType\" : \"ADMINBM\", \"roleDescription\" : \"roleDescription\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
