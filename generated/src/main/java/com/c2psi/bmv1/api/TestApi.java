/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (5.3.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.c2psi.bmv1.api;

import com.c2psi.bmv1.dto.TestDto;
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
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-10-01T07:39:22.173593300+01:00[Africa/Casablanca]")
@Validated
@Api(value = "test", description = "the test API")
public interface TestApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * GET /test/non_secure
     *
     * @return Sucessfull Test Operation (status code 200)
     */
    @ApiOperation(value = "", nickname = "apiTest", notes = "", response = TestDto.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Sucessfull Test Operation", response = TestDto.class) })
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/test/non_secure",
        produces = { "application/json" }
    )
    default ResponseEntity<TestDto> _apiTest() {
        return apiTest();
    }

    // Override this method
    default  ResponseEntity<TestDto> apiTest() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"test\" : \"test\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /test/secure
     *
     * @return Sucessfull Test secure Operation (status code 200)
     */
    @ApiOperation(value = "", nickname = "apiTestSecure", notes = "", response = TestDto.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Sucessfull Test secure Operation", response = TestDto.class) })
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/test/secure",
        produces = { "application/json" }
    )
    default ResponseEntity<TestDto> _apiTestSecure() {
        return apiTestSecure();
    }

    // Override this method
    default  ResponseEntity<TestDto> apiTestSecure() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"test\" : \"test\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /test/bm/v1/securebmv1
     *
     * @return Sucessfull Test secure Operation (status code 200)
     */
    @ApiOperation(value = "", nickname = "apiTestSecurebmv1", notes = "", response = TestDto.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Sucessfull Test secure Operation", response = TestDto.class) })
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/test/bm/v1/securebmv1",
        produces = { "application/json" }
    )
    default ResponseEntity<TestDto> _apiTestSecurebmv1() {
        return apiTestSecurebmv1();
    }

    // Override this method
    default  ResponseEntity<TestDto> apiTestSecurebmv1() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"test\" : \"test\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
