/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (5.3.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.c2psi.bmv1.api;

import com.c2psi.bmv1.dto.ArticleDto;
import com.c2psi.bmv1.dto.FilterRequest;
import com.c2psi.bmv1.dto.PageofArticleDto;
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
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-16T08:50:41.193143300+01:00[Africa/Douala]")
@Validated
@Api(value = "article", description = "the article API")
public interface ArticleApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * DELETE /article/bmV1.0/delete/{id} : Path used to delete an article in the system with its id
     *
     * @param id The id that represent the Article to delete. It&#39;s compulsory if not the operation can&#39;t proceed (required)
     * @return Article deleted successfully (status code 200)
     *         or Bad request. Article ID must be an integer and larger than 0. (status code 400)
     *         or Authorization information is missing or invalid. (status code 401)
     *         or The user who ask the ressource is Forbidden. (status code 403)
     *         or The expected ressource is not found. (status code 404)
     *         or Unexpected error at the server side. (status code 500)
     */
    @ApiOperation(value = "Path used to delete an article in the system with its id", nickname = "deleteArticleById", notes = "", response = Boolean.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Article deleted successfully", response = Boolean.class),
        @ApiResponse(code = 400, message = "Bad request. Article ID must be an integer and larger than 0."),
        @ApiResponse(code = 401, message = "Authorization information is missing or invalid."),
        @ApiResponse(code = 403, message = "The user who ask the ressource is Forbidden."),
        @ApiResponse(code = 404, message = "The expected ressource is not found."),
        @ApiResponse(code = 500, message = "Unexpected error at the server side.") })
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/article/bmV1.0/delete/{id}",
        produces = { "application/json" }
    )
    default ResponseEntity<Boolean> _deleteArticleById(@ApiParam(value = "The id that represent the Article to delete. It's compulsory if not the operation can't proceed", required = true) @PathVariable("id") Long id) {
        return deleteArticleById(id);
    }

    // Override this method
    default  ResponseEntity<Boolean> deleteArticleById(Long id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /article/bmV1.0/getby/{id} : Find an Article in the system by its id
     *
     * @param id The id that represent the Article found. It&#39;s compulsory if not the operation can&#39;t proceed (required)
     * @return Article found successfully (status code 200)
     *         or Bad request. Article ID must be an integer and larger than 0. (status code 400)
     *         or Authorization information is missing or invalid. (status code 401)
     *         or The user who ask the ressource is Forbidden. (status code 403)
     *         or The expected ressource is not found. (status code 404)
     *         or Unexpected error at the server side. (status code 500)
     */
    @ApiOperation(value = "Find an Article in the system by its id", nickname = "getArticleById", notes = "", response = ArticleDto.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Article found successfully", response = ArticleDto.class),
        @ApiResponse(code = 400, message = "Bad request. Article ID must be an integer and larger than 0."),
        @ApiResponse(code = 401, message = "Authorization information is missing or invalid."),
        @ApiResponse(code = 403, message = "The user who ask the ressource is Forbidden."),
        @ApiResponse(code = 404, message = "The expected ressource is not found."),
        @ApiResponse(code = 500, message = "Unexpected error at the server side.") })
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/article/bmV1.0/getby/{id}",
        produces = { "application/json" }
    )
    default ResponseEntity<ArticleDto> _getArticleById(@ApiParam(value = "The id that represent the Article found. It's compulsory if not the operation can't proceed", required = true) @PathVariable("id") Long id) {
        return getArticleById(id);
    }

    // Override this method
    default  ResponseEntity<ArticleDto> getArticleById(Long id) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"artName\" : \"artName\", \"artQuantityinstock\" : 0, \"artBasepriceId\" : 5, \"artThreshold\" : 0, \"artPosId\" : 5, \"artShortname\" : \"artShortname\", \"artUnitId\" : 1, \"artCode\" : \"A0000\", \"artLowlimitwholesale\" : 30, \"artDescription\" : \"artDescription\", \"artPfId\" : 6, \"artlowlimitSemiwholesale\" : 25, \"id\" : 0 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * POST /article/bmV1.0/list : Path used to list article that respect certain criteria. A criteria is an instance of a Filter object
     *
     * @param filterRequest  (optional)
     * @return Article list found successfully (status code 200)
     */
    @ApiOperation(value = "Path used to list article that respect certain criteria. A criteria is an instance of a Filter object", nickname = "getArticleList", notes = "", response = ArticleDto.class, responseContainer = "List", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Article list found successfully", response = ArticleDto.class, responseContainer = "List") })
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/article/bmV1.0/list",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<List<ArticleDto>> _getArticleList(@ApiParam(value = "") @Valid @RequestBody(required = false) FilterRequest filterRequest) {
        return getArticleList(filterRequest);
    }

    // Override this method
    default  ResponseEntity<List<ArticleDto>> getArticleList(FilterRequest filterRequest) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"artName\" : \"artName\", \"artQuantityinstock\" : 0, \"artBasepriceId\" : 5, \"artThreshold\" : 0, \"artPosId\" : 5, \"artShortname\" : \"artShortname\", \"artUnitId\" : 1, \"artCode\" : \"A0000\", \"artLowlimitwholesale\" : 30, \"artDescription\" : \"artDescription\", \"artPfId\" : 6, \"artlowlimitSemiwholesale\" : 25, \"id\" : 0 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * POST /article/bmV1.0/page : Path used to list article page by page that respect certain criteria. With the Page object, we can configure the page number and size that we want
     *
     * @param filterRequest  (optional)
     * @return Article page found successfully (status code 200)
     */
    @ApiOperation(value = "Path used to list article page by page that respect certain criteria. With the Page object, we can configure the page number and size that we want", nickname = "getArticlePage", notes = "", response = PageofArticleDto.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Article page found successfully", response = PageofArticleDto.class) })
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/article/bmV1.0/page",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<PageofArticleDto> _getArticlePage(@ApiParam(value = "") @Valid @RequestBody(required = false) FilterRequest filterRequest) {
        return getArticlePage(filterRequest);
    }

    // Override this method
    default  ResponseEntity<PageofArticleDto> getArticlePage(FilterRequest filterRequest) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"totalPages\" : 6, \"pageSize\" : 10, \"currentPage\" : 0, \"content\" : [ { \"artName\" : \"artName\", \"artQuantityinstock\" : 0, \"artBasepriceId\" : 5, \"artThreshold\" : 0, \"artPosId\" : 5, \"artShortname\" : \"artShortname\", \"artUnitId\" : 1, \"artCode\" : \"A0000\", \"artLowlimitwholesale\" : 30, \"artDescription\" : \"artDescription\", \"artPfId\" : 6, \"artlowlimitSemiwholesale\" : 25, \"id\" : 0 }, { \"artName\" : \"artName\", \"artQuantityinstock\" : 0, \"artBasepriceId\" : 5, \"artThreshold\" : 0, \"artPosId\" : 5, \"artShortname\" : \"artShortname\", \"artUnitId\" : 1, \"artCode\" : \"A0000\", \"artLowlimitwholesale\" : 30, \"artDescription\" : \"artDescription\", \"artPfId\" : 6, \"artlowlimitSemiwholesale\" : 25, \"id\" : 0 } ], \"totalElements\" : 0 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * POST /article/bmV1.0/create : Path used to save a new Article of product in the system
     *
     * @param articleDto  (optional)
     * @return Article saved successfully (status code 200)
     *         or Bad request. There is something wrong in the request. (status code 400)
     *         or Authorization information is missing or invalid. (status code 401)
     *         or The user who ask the ressource is Forbidden. (status code 403)
     *         or The expected ressource is not found. (status code 404)
     *         or Unexpected error at the server side. (status code 500)
     */
    @ApiOperation(value = "Path used to save a new Article of product in the system", nickname = "saveArticle", notes = "", response = ArticleDto.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Article saved successfully", response = ArticleDto.class),
        @ApiResponse(code = 400, message = "Bad request. There is something wrong in the request."),
        @ApiResponse(code = 401, message = "Authorization information is missing or invalid."),
        @ApiResponse(code = 403, message = "The user who ask the ressource is Forbidden."),
        @ApiResponse(code = 404, message = "The expected ressource is not found."),
        @ApiResponse(code = 500, message = "Unexpected error at the server side.") })
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/article/bmV1.0/create",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<ArticleDto> _saveArticle(@ApiParam(value = "") @Valid @RequestBody(required = false) ArticleDto articleDto) {
        return saveArticle(articleDto);
    }

    // Override this method
    default  ResponseEntity<ArticleDto> saveArticle(ArticleDto articleDto) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"artName\" : \"artName\", \"artQuantityinstock\" : 0, \"artBasepriceId\" : 5, \"artThreshold\" : 0, \"artPosId\" : 5, \"artShortname\" : \"artShortname\", \"artUnitId\" : 1, \"artCode\" : \"A0000\", \"artLowlimitwholesale\" : 30, \"artDescription\" : \"artDescription\", \"artPfId\" : 6, \"artlowlimitSemiwholesale\" : 25, \"id\" : 0 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PUT /article/bmV1.0/update : Path used to update or modify an existing article in the system
     *
     * @param articleDto  (optional)
     * @return Article updated successfully (status code 200)
     *         or Bad request. There is something wrong in the request. (status code 400)
     *         or Authorization information is missing or invalid. (status code 401)
     *         or The user who ask the ressource is Forbidden. (status code 403)
     *         or The expected ressource is not found. (status code 404)
     *         or Unexpected error at the server side. (status code 500)
     */
    @ApiOperation(value = "Path used to update or modify an existing article in the system", nickname = "updateArticle", notes = "", response = ArticleDto.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Article updated successfully", response = ArticleDto.class),
        @ApiResponse(code = 400, message = "Bad request. There is something wrong in the request."),
        @ApiResponse(code = 401, message = "Authorization information is missing or invalid."),
        @ApiResponse(code = 403, message = "The user who ask the ressource is Forbidden."),
        @ApiResponse(code = 404, message = "The expected ressource is not found."),
        @ApiResponse(code = 500, message = "Unexpected error at the server side.") })
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/article/bmV1.0/update",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<ArticleDto> _updateArticle(@ApiParam(value = "") @Valid @RequestBody(required = false) ArticleDto articleDto) {
        return updateArticle(articleDto);
    }

    // Override this method
    default  ResponseEntity<ArticleDto> updateArticle(ArticleDto articleDto) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"artName\" : \"artName\", \"artQuantityinstock\" : 0, \"artBasepriceId\" : 5, \"artThreshold\" : 0, \"artPosId\" : 5, \"artShortname\" : \"artShortname\", \"artUnitId\" : 1, \"artCode\" : \"A0000\", \"artLowlimitwholesale\" : 30, \"artDescription\" : \"artDescription\", \"artPfId\" : 6, \"artlowlimitSemiwholesale\" : 25, \"id\" : 0 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
