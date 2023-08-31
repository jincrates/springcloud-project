package me.jincrates.orderservice.intg.api.controller;

import me.jincrates.orderservice.api.controller.claim.request.ClaimCreateRequest;
import me.jincrates.orderservice.api.controller.claim.request.ClaimProductRequest;
import me.jincrates.orderservice.api.controller.claim.request.DeliveryInfoRequest;
import me.jincrates.orderservice.domain.claim.ClaimReason;
import me.jincrates.orderservice.domain.claim.ClaimType;
import me.jincrates.orderservice.intg.ControllerTestSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class OrderControllerTest extends ControllerTestSupport {

    @Test
    @DisplayName("신규 클레임을 접수한다.")
    void createClaim() throws Exception {
        // given
        ClaimCreateRequest request = ClaimCreateRequest.builder()
                .orderId(1L)
                .type(ClaimType.RETURN)
                .reason(ClaimReason.CHANGE_MIND)
                .memo(String.format("%s(으)로 인한 %s입니다.", ClaimReason.CHANGE_MIND.getDescription(),
                        ClaimType.RETURN.getDescription()))
                .claimProducts(List.of(
                                ClaimProductRequest.builder()
                                        .orderProductId(1L)
                                        .quantity(5)
                                        .build(),
                                ClaimProductRequest.builder()
                                        .orderProductId(2L)
                                        .quantity(10)
                                        .build(),
                                ClaimProductRequest.builder()
                                        .orderProductId(3L)
                                        .quantity(15)
                                        .build()
                        )
                )
                //.imageIdList(null)
                .collectionDelivery(
                        DeliveryInfoRequest.builder()
                                .recipientName("김칸트")
                                .recipientMobileNo("01012345678")
                                .zipCode("06038")
                                .address1("서울특별시 도산대로 4길 15(논현동)")
                                .address2("5층")
                                .roadAddress("서울특별시 강남구 도산대로 4길 15(논현동)")
                                .landAddress("서울특별시 강남구 논현동 16-23 큐브타워")
                                .deliveryTypeCode("V")
                                .deliveryEnterMethodCode("E")
                                .deliveryEnterMethodMessage("창문을 통해 들어오세요.")
                                .build()
                )
                .exchangeDelivery(null)
                .build();

        // when // then
        mockMvc.perform(
                        post("/api/v1/claims/receipt")
                                .contentType(APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.message").value("OK"));
    }

    @Test
    @DisplayName("신규 클레임을 접수할 때, 주문 번호는 필수이다.")
    void createClaimWithNullOrderId() throws Exception {
        // given
        ClaimCreateRequest request = ClaimCreateRequest.builder()
                //.orderId(1L)
                .type(ClaimType.RETURN)
                .reason(ClaimReason.CHANGE_MIND)
                .memo(String.format("%s(으)로 인한 %s입니다.", ClaimReason.CHANGE_MIND.getDescription(),
                        ClaimType.RETURN.getDescription()))
                .claimProducts(List.of(
                                ClaimProductRequest.builder()
                                        .orderProductId(1L)
                                        .quantity(5)
                                        .build(),
                                ClaimProductRequest.builder()
                                        .orderProductId(2L)
                                        .quantity(10)
                                        .build(),
                                ClaimProductRequest.builder()
                                        .orderProductId(3L)
                                        .quantity(15)
                                        .build()
                        )
                )
                //.imageIdList(null)
                .collectionDelivery(
                        DeliveryInfoRequest.builder()
                                .recipientName("김칸트")
                                .recipientMobileNo("01012345678")
                                .zipCode("06038")
                                .address1("서울특별시 도산대로 4길 15(논현동)")
                                .address2("5층")
                                .roadAddress("서울특별시 강남구 도산대로 4길 15(논현동)")
                                .landAddress("서울특별시 강남구 논현동 16-23 큐브타워")
                                .deliveryTypeCode("V")
                                .deliveryEnterMethodCode("E")
                                .deliveryEnterMethodMessage("창문을 통해 들어오세요.")
                                .build()
                )
                .exchangeDelivery(null)
                .build();

        // when // then
        mockMvc.perform(
                        post("/api/v1/claims/receipt")
                                .contentType(APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(400))
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value("주문 번호는 필수입니다."));
    }

    @Test
    @DisplayName("신규 클레임을 접수할 때, 접수 유형은 필수이다.")
    void createClaimWithNullClaimType() throws Exception {
        // given
        ClaimCreateRequest request = ClaimCreateRequest.builder()
                .orderId(1L)
                //.type(ClaimType.RETURN)
                .reason(ClaimReason.CHANGE_MIND)
                .memo(String.format("%s(으)로 인한 %s입니다.", ClaimReason.CHANGE_MIND.getDescription(),
                        ClaimType.RETURN.getDescription()))
                .claimProducts(List.of(
                                ClaimProductRequest.builder()
                                        .orderProductId(1L)
                                        .quantity(5)
                                        .build(),
                                ClaimProductRequest.builder()
                                        .orderProductId(2L)
                                        .quantity(10)
                                        .build(),
                                ClaimProductRequest.builder()
                                        .orderProductId(3L)
                                        .quantity(15)
                                        .build()
                        )
                )
                //.imageIdList(null)
                .collectionDelivery(
                        DeliveryInfoRequest.builder()
                                .recipientName("김칸트")
                                .recipientMobileNo("01012345678")
                                .zipCode("06038")
                                .address1("서울특별시 도산대로 4길 15(논현동)")
                                .address2("5층")
                                .roadAddress("서울특별시 강남구 도산대로 4길 15(논현동)")
                                .landAddress("서울특별시 강남구 논현동 16-23 큐브타워")
                                .deliveryTypeCode("V")
                                .deliveryEnterMethodCode("E")
                                .deliveryEnterMethodMessage("창문을 통해 들어오세요.")
                                .build()
                )
                .exchangeDelivery(null)
                .build();

        // when // then
        mockMvc.perform(
                        post("/api/v1/claims/receipt")
                                .contentType(APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(400))
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value("접수 유형은 필수입니다."));
    }

    @Test
    @DisplayName("신규 클레임을 접수할 때, 접수 사유는 필수이다.")
    void createClaimWithNullClaimReason() throws Exception {
        // given
        ClaimCreateRequest request = ClaimCreateRequest.builder()
                .orderId(1L)
                .type(ClaimType.RETURN)
                //.reason(ClaimReason.CHANGE_MIND)
                .memo(String.format("%s(으)로 인한 %s입니다.", ClaimReason.CHANGE_MIND.getDescription(),
                        ClaimType.RETURN.getDescription()))
                .claimProducts(List.of(
                                ClaimProductRequest.builder()
                                        .orderProductId(1L)
                                        .quantity(5)
                                        .build(),
                                ClaimProductRequest.builder()
                                        .orderProductId(2L)
                                        .quantity(10)
                                        .build(),
                                ClaimProductRequest.builder()
                                        .orderProductId(3L)
                                        .quantity(15)
                                        .build()
                        )
                )
                //.imageIdList(null)
                .collectionDelivery(
                        DeliveryInfoRequest.builder()
                                .recipientName("김칸트")
                                .recipientMobileNo("01012345678")
                                .zipCode("06038")
                                .address1("서울특별시 도산대로 4길 15(논현동)")
                                .address2("5층")
                                .roadAddress("서울특별시 강남구 도산대로 4길 15(논현동)")
                                .landAddress("서울특별시 강남구 논현동 16-23 큐브타워")
                                .deliveryTypeCode("V")
                                .deliveryEnterMethodCode("E")
                                .deliveryEnterMethodMessage("창문을 통해 들어오세요.")
                                .build()
                )
                .exchangeDelivery(null)
                .build();

        // when // then
        mockMvc.perform(
                        post("/api/v1/claims/receipt")
                                .contentType(APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(400))
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value("접수 사유는 필수입니다."));
    }

    @Test
    @DisplayName("신규 클레임을 접수할 때, 접수 사유는 필수이다.")
    void claimReasonIsProductDefectsAndImageIdListIsNull() throws Exception {
        // given
        ClaimCreateRequest request = ClaimCreateRequest.builder()
                .orderId(1L)
                .type(ClaimType.RETURN)
                .reason(ClaimReason.PRODUCT_DEFECTS)
                .memo(String.format("%s(으)로 인한 %s입니다.", ClaimReason.PRODUCT_DEFECTS.getDescription(),
                        ClaimType.RETURN.getDescription()))
                .claimProducts(List.of(
                                ClaimProductRequest.builder()
                                        .orderProductId(1L)
                                        .quantity(5)
                                        .build(),
                                ClaimProductRequest.builder()
                                        .orderProductId(2L)
                                        .quantity(10)
                                        .build(),
                                ClaimProductRequest.builder()
                                        .orderProductId(3L)
                                        .quantity(15)
                                        .build()
                        )
                )
                //.imageIdList(null)
                .collectionDelivery(
                        DeliveryInfoRequest.builder()
                                .recipientName("김칸트")
                                .recipientMobileNo("01012345678")
                                .zipCode("06038")
                                .address1("서울특별시 도산대로 4길 15(논현동)")
                                .address2("5층")
                                .roadAddress("서울특별시 강남구 도산대로 4길 15(논현동)")
                                .landAddress("서울특별시 강남구 논현동 16-23 큐브타워")
                                .deliveryTypeCode("V")
                                .deliveryEnterMethodCode("E")
                                .deliveryEnterMethodMessage("창문을 통해 들어오세요.")
                                .build()
                )
                .exchangeDelivery(null)
                .build();

        // when // then
        mockMvc.perform(
                        post("/api/v1/claims/receipt")
                                .contentType(APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(400))
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value("파손 및 불량인 경우 첨부 이미지는 필수입니다."));
    }

    @Test
    @DisplayName("신규 클레임을 접수할 때, 상세 사유는 필수이다.")
    void createClaimWithNullMemo() throws Exception {
        // given
        ClaimCreateRequest request = ClaimCreateRequest.builder()
                .orderId(1L)
                .type(ClaimType.RETURN)
                .reason(ClaimReason.CHANGE_MIND)
                //.memo(String.format("%s(으)로 인한 %s입니다.", ClaimReason.CHANGE_MIND.getDescription(), ClaimType.RETURN.getDescription()))
                .claimProducts(List.of(
                                ClaimProductRequest.builder()
                                        .orderProductId(1L)
                                        .quantity(5)
                                        .build(),
                                ClaimProductRequest.builder()
                                        .orderProductId(2L)
                                        .quantity(10)
                                        .build(),
                                ClaimProductRequest.builder()
                                        .orderProductId(3L)
                                        .quantity(15)
                                        .build()
                        )
                )
                //.imageIdList(null)
                .collectionDelivery(
                        DeliveryInfoRequest.builder()
                                .recipientName("김칸트")
                                .recipientMobileNo("01012345678")
                                .zipCode("06038")
                                .address1("서울특별시 도산대로 4길 15(논현동)")
                                .address2("5층")
                                .roadAddress("서울특별시 강남구 도산대로 4길 15(논현동)")
                                .landAddress("서울특별시 강남구 논현동 16-23 큐브타워")
                                .deliveryTypeCode("V")
                                .deliveryEnterMethodCode("E")
                                .deliveryEnterMethodMessage("창문을 통해 들어오세요.")
                                .build()
                )
                .exchangeDelivery(null)
                .build();

        // when // then
        mockMvc.perform(
                        post("/api/v1/claims/receipt")
                                .contentType(APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(400))
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value("상세 사유는 필수입니다."));
    }

    @Test
    @DisplayName("신규 클레임을 접수할 때, 상세 사유는 최대 500자까지만 가능하다.")
    void createClaimWithMemoLengthIs500() throws Exception {
        // given
        String memo = "a".repeat(500);

        ClaimCreateRequest request = ClaimCreateRequest.builder()
                .orderId(1L)
                .type(ClaimType.RETURN)
                .reason(ClaimReason.CHANGE_MIND)
                .memo(memo)
                .claimProducts(List.of(
                                ClaimProductRequest.builder()
                                        .orderProductId(1L)
                                        .quantity(5)
                                        .build(),
                                ClaimProductRequest.builder()
                                        .orderProductId(2L)
                                        .quantity(10)
                                        .build(),
                                ClaimProductRequest.builder()
                                        .orderProductId(3L)
                                        .quantity(15)
                                        .build()
                        )
                )
                //.imageIdList(null)
                .collectionDelivery(
                        DeliveryInfoRequest.builder()
                                .recipientName("김칸트")
                                .recipientMobileNo("01012345678")
                                .zipCode("06038")
                                .address1("서울특별시 도산대로 4길 15(논현동)")
                                .address2("5층")
                                .roadAddress("서울특별시 강남구 도산대로 4길 15(논현동)")
                                .landAddress("서울특별시 강남구 논현동 16-23 큐브타워")
                                .deliveryTypeCode("V")
                                .deliveryEnterMethodCode("E")
                                .deliveryEnterMethodMessage("창문을 통해 들어오세요.")
                                .build()
                )
                .exchangeDelivery(null)
                .build();

        // when // then
        assertThat(memo.length()).isEqualTo(500);

        mockMvc.perform(
                        post("/api/v1/claims/receipt")
                                .contentType(APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.message").value("OK"));
    }

    @Test
    @DisplayName("신규 클레임을 접수할 때, 상세 사유는 500자를 넘을 수 없다.")
    void createClaimWithMemoLengthGreaterThan500() throws Exception {
        // given
        String memo = "a".repeat(501);

        ClaimCreateRequest request = ClaimCreateRequest.builder()
                .orderId(1L)
                .type(ClaimType.RETURN)
                .reason(ClaimReason.CHANGE_MIND)
                .memo(memo)
                .claimProducts(List.of(
                                ClaimProductRequest.builder()
                                        .orderProductId(1L)
                                        .quantity(5)
                                        .build(),
                                ClaimProductRequest.builder()
                                        .orderProductId(2L)
                                        .quantity(10)
                                        .build(),
                                ClaimProductRequest.builder()
                                        .orderProductId(3L)
                                        .quantity(15)
                                        .build()
                        )
                )
                //.imageIdList(null)
                .collectionDelivery(
                        DeliveryInfoRequest.builder()
                                .recipientName("김칸트")
                                .recipientMobileNo("01012345678")
                                .zipCode("06038")
                                .address1("서울특별시 도산대로 4길 15(논현동)")
                                .address2("5층")
                                .roadAddress("서울특별시 강남구 도산대로 4길 15(논현동)")
                                .landAddress("서울특별시 강남구 논현동 16-23 큐브타워")
                                .deliveryTypeCode("V")
                                .deliveryEnterMethodCode("E")
                                .deliveryEnterMethodMessage("창문을 통해 들어오세요.")
                                .build()
                )
                .exchangeDelivery(null)
                .build();

        // when // then
        assertThat(memo.length()).isGreaterThan(500);

        mockMvc.perform(
                        post("/api/v1/claims/receipt")
                                .contentType(APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(400))
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value("상세 사유는 최대 500자까지만 작성 가능합니다."));
    }

    @Test
    @DisplayName("신규 클레임을 접수할 때, 첨부 이미지는 최대 5개까지만 가능하다.")
    void createClaimWithImageIdListIs5() throws Exception {
        // given
        List<Long> imageIdList = List.of(1L, 2L, 3L, 4L, 5L);

        ClaimCreateRequest request = ClaimCreateRequest.builder()
                .orderId(1L)
                .type(ClaimType.RETURN)
                .reason(ClaimReason.CHANGE_MIND)
                .memo(String.format("%s(으)로 인한 %s입니다.", ClaimReason.CHANGE_MIND.getDescription(),
                        ClaimType.RETURN.getDescription()))
                .claimProducts(List.of(
                                ClaimProductRequest.builder()
                                        .orderProductId(1L)
                                        .quantity(5)
                                        .build(),
                                ClaimProductRequest.builder()
                                        .orderProductId(2L)
                                        .quantity(10)
                                        .build(),
                                ClaimProductRequest.builder()
                                        .orderProductId(3L)
                                        .quantity(15)
                                        .build()
                        )
                )
                .imageIdList(imageIdList)
                .collectionDelivery(
                        DeliveryInfoRequest.builder()
                                .recipientName("김칸트")
                                .recipientMobileNo("01012345678")
                                .zipCode("06038")
                                .address1("서울특별시 도산대로 4길 15(논현동)")
                                .address2("5층")
                                .roadAddress("서울특별시 강남구 도산대로 4길 15(논현동)")
                                .landAddress("서울특별시 강남구 논현동 16-23 큐브타워")
                                .deliveryTypeCode("V")
                                .deliveryEnterMethodCode("E")
                                .deliveryEnterMethodMessage("창문을 통해 들어오세요.")
                                .build()
                )
                .exchangeDelivery(null)
                .build();

        // when // then
        assertThat(imageIdList.size()).isEqualTo(5);

        mockMvc.perform(
                        post("/api/v1/claims/receipt")
                                .contentType(APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.message").value("OK"));
    }

    @Test
    @DisplayName("신규 클레임을 접수할 때, 첨부 이미지는 최대 5개까지만 가능하다.")
    void createClaimWithImageIdListGreaterThan5() throws Exception {
        // given
        List<Long> imageIdList = List.of(1L, 2L, 3L, 4L, 5L, 6L);

        ClaimCreateRequest request = ClaimCreateRequest.builder()
                .orderId(1L)
                .type(ClaimType.RETURN)
                .reason(ClaimReason.CHANGE_MIND)
                .memo(String.format("%s(으)로 인한 %s입니다.", ClaimReason.CHANGE_MIND.getDescription(),
                        ClaimType.RETURN.getDescription()))
                .claimProducts(List.of(
                                ClaimProductRequest.builder()
                                        .orderProductId(1L)
                                        .quantity(5)
                                        .build(),
                                ClaimProductRequest.builder()
                                        .orderProductId(2L)
                                        .quantity(10)
                                        .build(),
                                ClaimProductRequest.builder()
                                        .orderProductId(3L)
                                        .quantity(15)
                                        .build()
                        )
                )
                .imageIdList(imageIdList)
                .collectionDelivery(
                        DeliveryInfoRequest.builder()
                                .recipientName("김칸트")
                                .recipientMobileNo("01012345678")
                                .zipCode("06038")
                                .address1("서울특별시 도산대로 4길 15(논현동)")
                                .address2("5층")
                                .roadAddress("서울특별시 강남구 도산대로 4길 15(논현동)")
                                .landAddress("서울특별시 강남구 논현동 16-23 큐브타워")
                                .deliveryTypeCode("V")
                                .deliveryEnterMethodCode("E")
                                .deliveryEnterMethodMessage("창문을 통해 들어오세요.")
                                .build()
                )
                .exchangeDelivery(null)
                .build();

        // when // then
        assertThat(imageIdList.size()).isGreaterThan(5);

        mockMvc.perform(
                        post("/api/v1/claims/receipt")
                                .contentType(APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(400))
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value("첨부 이미지는 최대 5개까지만 등록 가능합니다."));
    }

    @Test
    @DisplayName("신규 클레임을 접수할 때, 접수 유형이 교환이면 교환 배송지는 필수이다.1")
    void claimTypeIsExchangeWithNullExchangeDelivery() throws Exception {
        // given

        ClaimCreateRequest request = ClaimCreateRequest.builder()
                .orderId(1L)
                .type(ClaimType.EXCHANGE)
                .reason(ClaimReason.CHANGE_MIND)
                .memo(String.format("%s(으)로 인한 %s입니다.", ClaimReason.CHANGE_MIND.getDescription(),
                        ClaimType.RETURN.getDescription()))
                .claimProducts(List.of(
                                ClaimProductRequest.builder()
                                        .orderProductId(1L)
                                        .quantity(5)
                                        .build(),
                                ClaimProductRequest.builder()
                                        .orderProductId(2L)
                                        .quantity(10)
                                        .build(),
                                ClaimProductRequest.builder()
                                        .orderProductId(3L)
                                        .quantity(15)
                                        .build()
                        )
                )
                .collectionDelivery(
                        DeliveryInfoRequest.builder()
                                .recipientName("김칸트")
                                .recipientMobileNo("01012345678")
                                .zipCode("06038")
                                .address1("서울특별시 도산대로 4길 15(논현동)")
                                .address2("5층")
                                .roadAddress("서울특별시 강남구 도산대로 4길 15(논현동)")
                                .landAddress("서울특별시 강남구 논현동 16-23 큐브타워")
                                .deliveryTypeCode("V")
                                .deliveryEnterMethodCode("E")
                                .deliveryEnterMethodMessage("창문을 통해 들어오세요.")
                                .build()
                )
                .exchangeDelivery(null)
                .build();

        // when // then
        mockMvc.perform(
                        post("/api/v1/claims/receipt")
                                .contentType(APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(400))
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value("교환 배송지를 입력하지 않았습니다."));
    }

    @Test
    @DisplayName("신규 클레임을 접수할 때, 접수 유형이 교환이면 교환 배송지는 필수이다.2")
    void claimTypeIsExchangeWithNotNullExchangeDelivery() throws Exception {
        // given
        DeliveryInfoRequest exchangeDelivery = DeliveryInfoRequest.builder()
                .recipientName("김칸트")
                .recipientMobileNo("01012345678")
                .zipCode("06038")
                .address1("서울특별시 도산대로 4길 15(논현동)")
                .address2("5층")
                .roadAddress("서울특별시 강남구 도산대로 4길 15(논현동)")
                .landAddress("서울특별시 강남구 논현동 16-23 큐브타워")
                .deliveryTypeCode("V")
                .deliveryEnterMethodCode("E")
                .deliveryEnterMethodMessage("창문을 통해 들어오세요.")
                .build();

        ClaimCreateRequest request = ClaimCreateRequest.builder()
                .orderId(1L)
                .type(ClaimType.EXCHANGE)
                .reason(ClaimReason.CHANGE_MIND)
                .memo(String.format("%s(으)로 인한 %s입니다.", ClaimReason.CHANGE_MIND.getDescription(),
                        ClaimType.RETURN.getDescription()))
                .claimProducts(List.of(
                                ClaimProductRequest.builder()
                                        .orderProductId(1L)
                                        .quantity(5)
                                        .build(),
                                ClaimProductRequest.builder()
                                        .orderProductId(2L)
                                        .quantity(10)
                                        .build(),
                                ClaimProductRequest.builder()
                                        .orderProductId(3L)
                                        .quantity(15)
                                        .build()
                        )
                )
                .collectionDelivery(
                        DeliveryInfoRequest.builder()
                                .recipientName("김칸트")
                                .recipientMobileNo("01012345678")
                                .zipCode("06038")
                                .address1("서울특별시 도산대로 4길 15(논현동)")
                                .address2("5층")
                                .roadAddress("서울특별시 강남구 도산대로 4길 15(논현동)")
                                .landAddress("서울특별시 강남구 논현동 16-23 큐브타워")
                                .deliveryTypeCode("V")
                                .deliveryEnterMethodCode("E")
                                .deliveryEnterMethodMessage("창문을 통해 들어오세요.")
                                .build()
                )
                .exchangeDelivery(exchangeDelivery)
                .build();

        // when // then
        mockMvc.perform(
                        post("/api/v1/claims/receipt")
                                .contentType(APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.message").value("OK"));
    }
}