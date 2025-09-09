package com.surocksang.documentation;

import com.surocksang.annotation.ApiRequest;
import com.surocksang.dto.ChartDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

public interface SongDocumentation {
    @Tag(name ="인증", description = "인증 API")
    @Operation(summary = "로그인", description = "사용자 이메일과 비밀번호를 이용하여 Json Web Token(JWT)을 발급받습니다.")
    @ApiRequest(
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ChartDto.class),
                    examples = {
                            @ExampleObject(
                                    name="사용자 로그인",
                                    summary = "사용자 로그인 예시",
                                    value= "{\"email\": \"example@example.com\", \"password\": \"example\"}"
                            )
                    }
            )
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인 성공", headers = {
                    @Header(
                            name = "Authorization",
                            description = "Bearer 토큰",
                            schema = @Schema(type = "string"),
                            required = true,
                            example = "Bearer Valid-Access-Token-Example"
                    ),
                    @Header(
                            name = "Set-Cookie",
                            description = "Http Only 리프레시 토큰",
                            schema = @Schema(type = "string"),
                            required = true,
                            example = "refresh_token=Valid-Refresh-Token-Example; Path=/; HttpOnly; Secure; Max-Age=604800"
                    )
            }),
            @ApiResponse(responseCode = "401", description = "인증 실패", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class),
                    examples = {
                            @ExampleObject(
                                    name="인증 실패",
                                    summary = "이메일 또는 비밀번호 오류",
                                    value = "{\"status\": 401, \"code\": \"ACCOUNT-001\", \"message\": \"이메일 또는 비밀번호를 확인해주세요.\"}"
                            )
                    }
            ))
    })
    ResponseEntity<?> downloadSong(@PathVariable Long musicId);

}
