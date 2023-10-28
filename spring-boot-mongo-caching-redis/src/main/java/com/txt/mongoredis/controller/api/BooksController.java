package com.txt.mongoredis.controller.api;

import com.txt.mongoredis.dto.common.ResponseDTO;
import com.txt.mongoredis.entities.mongodb.dbfirst.Books;
import com.txt.mongoredis.repositories.mongodb.dbfirst.BooksRepository;
import com.txt.mongoredis.track.TrackActivity;
import com.txt.mongoredis.utils.ActivityLogType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/book")
@Tag(name = "Books Controller", description = "Books")
public class BooksController {

    private final BooksRepository booksRepository;


    @Operation(summary = "Get list books", tags = {"Books"})
    @Parameters({
            @Parameter(name = "start", example = "0", description = "page number - default value: 0", in = ParameterIn.QUERY, schema = @Schema(type = "integer", format = "int32")),
            @Parameter(name = "size", example = "10", description = "Size of page - default value: 10", in = ParameterIn.QUERY, schema = @Schema(type = "integer", format = "int32")),
            @Parameter(name = "sort", example = "title", description = "sort field - default value: title", in = ParameterIn.QUERY, schema = @Schema(type = "string")),
            @Parameter(name = "sort", example = "asc", description = "desc|asc - default value: asc", in = ParameterIn.QUERY, schema = @Schema(type = "string"))})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Indicates the requested were returned."),
            @ApiResponse(responseCode = "500", description = "Wrong or Missing data in request params. 6 of them are: page, size, sort, order")})
    @GetMapping(path = "/get-books", produces = "application/json")
    @TrackActivity(type = ActivityLogType.USER_BOOK_ACTION)
    public ResponseDTO<Books> getBooks(
            @Parameter(hidden = true) @RequestParam Map<String, String> allRequestParams) throws Exception {

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStart(allRequestParams.get("start") != null ? Integer.valueOf(allRequestParams.get("start")) : 0);
        responseDTO.setSize(allRequestParams.get("size") != null ? Integer.valueOf(allRequestParams.get("size")) : 10);
        responseDTO.setSort(allRequestParams.get("order") != null ? allRequestParams.get("order") : "asc");
        responseDTO.setSort(allRequestParams.get("sort") != null ? allRequestParams.get("sort") : "id");

        Pageable pageable = PageRequest.of(responseDTO.getStart(), responseDTO.getSize());
        Page<Books> books = booksRepository.findAll(pageable);
        responseDTO.setData(books.getContent());
        responseDTO.setTotal(books.getTotalElements());

        return responseDTO;
    }
}
