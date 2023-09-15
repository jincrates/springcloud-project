package me.jincrates.ecommerce.members.adapter.web;

import static java.util.stream.Collectors.toList;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.jincrates.ecommerce.members.adapter.web.request.MemberCreateRequest;
import me.jincrates.ecommerce.members.adapter.web.response.MemberCreateResponse;
import me.jincrates.ecommerce.members.adapter.web.response.MemberResponse;
import me.jincrates.ecommerce.members.application.port.MemberUseCase;
import me.jincrates.ecommerce.members.application.service.response.MemberCreateServiceResponse;
import me.jincrates.ecommerce.members.application.service.response.MemberServiceResponse;
import me.jincrates.global.common.response.CommonResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "회원 서비스", description = "회원 등록/조회 API")
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberUseCase memberUseCase;

    @Operation(summary = "회원 등록")
    @ApiResponse(responseCode = "200", description = "회원 등록 성공",
        content = @Content(schema = @Schema(implementation = MemberCreateResponse.class, description = "가입한 회원")))
    @PostMapping("/api/v1/members")
    public CommonResponse<?> createMember(
        @Valid @RequestBody MemberCreateRequest request) {
        MemberCreateServiceResponse response = memberUseCase.register(request.toServiceRequest());
        return CommonResponse.ok(response.toResponse());
    }

    @Operation(summary = "회원 조회")
    @ApiResponse(responseCode = "200", description = "회원 조회 성공",
        content = @Content(schema = @Schema(implementation = MemberResponse.class, description = "조회된 회원")))
    @GetMapping("/api/v1/members/{member_id}")
    public CommonResponse<?> getMember(
        @PathVariable("member_id") Long memberId
    ) {
        return CommonResponse.ok(memberUseCase.getMemberById(memberId).toResponse());
    }

    @Operation(summary = "전체 회원 조회")
    @ApiResponse(responseCode = "200", description = "전체 회원 조회 성공",
        content = @Content(array = @ArraySchema(schema = @Schema(implementation = MemberResponse.class, description = "회원 리스트"))))
    @GetMapping("/api/v1/members")
    public CommonResponse<?> getMembers() {
        return CommonResponse.ok(memberUseCase.getMembers().stream()
            .map(MemberServiceResponse::toResponse)
            .collect(toList()));
    }
}
