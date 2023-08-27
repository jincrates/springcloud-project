package me.jincrates.msa.coffeekiosk.unit.calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentDateTest {

    @TestFactory
    @DisplayName("시작일이 01월 28일이고")
    Collection<DynamicTest> calculateEndDate1() {
        // given
        PaymentDate startDate = new PaymentDate("2023-01-28");

        return List.of(
                DynamicTest.dynamicTest("결제일이 01월 28일(평년)일 때, 종료일은 02월 27일이다.", () -> {
                    // given
                    PaymentDate paymentDate = new PaymentDate("2023-01-28");

                    // when
                    PaymentDate endDate = PaymentDate.calculateEndDate(startDate, paymentDate);

                    // then
                    assertThat(paymentDate.isLeap()).isFalse();
                    assertThat(endDate.getDate()).isEqualTo(LocalDate.of(2023, 2, 27));
                }),
                DynamicTest.dynamicTest("결제일이 01월 28일(윤년)일 때, 종료일은 02월 27일이다.", () -> {
                    // given
                    PaymentDate paymentDate = new PaymentDate("2024-01-28");

                    // when
                    PaymentDate endDate = PaymentDate.calculateEndDate(startDate, paymentDate);

                    // then
                    assertThat(paymentDate.isLeap()).isTrue();
                    assertThat(endDate.getDate()).isEqualTo(LocalDate.of(2024, 2, 27));
                }),
                DynamicTest.dynamicTest("결제일이 02월 28일(평년)일 때, 종료일은 03월 27일이다.", () -> {
                    // given
                    PaymentDate paymentDate = new PaymentDate("2023-02-28");

                    // when
                    PaymentDate endDate = PaymentDate.calculateEndDate(startDate, paymentDate);

                    // then
                    assertThat(paymentDate.isLeap()).isFalse();
                    assertThat(endDate.getDate()).isEqualTo(LocalDate.of(2023, 3, 27));
                }),
                DynamicTest.dynamicTest("결제일이 02월 28일(윤년)일 때, 종료일은 03월 27일이다.", () -> {
                    // given
                    PaymentDate paymentDate = new PaymentDate("2024-02-28");

                    // when
                    PaymentDate endDate = PaymentDate.calculateEndDate(startDate, paymentDate);

                    // then
                    assertThat(paymentDate.isLeap()).isTrue();
                    assertThat(endDate.getDate()).isEqualTo(LocalDate.of(2024, 3, 27));
                })
        );
    }

    @TestFactory
    @DisplayName("시작일이 01월 29일이고")
    Collection<DynamicTest> calculateEndDate2() {
        // given
        PaymentDate startDate = new PaymentDate("2023-01-29");

        return List.of(
                DynamicTest.dynamicTest("결제일이 01월 29일(평년)일 때, 종료일은 02월 28일이다.", () -> {
                    // given
                    PaymentDate paymentDate = new PaymentDate("2023-01-29");

                    // when
                    PaymentDate endDate = PaymentDate.calculateEndDate(startDate, paymentDate);

                    // then
                    assertThat(paymentDate.isLeap()).isFalse();
                    assertThat(endDate.getDate()).isEqualTo(LocalDate.of(2023, 2, 28));
                }),
                DynamicTest.dynamicTest("결제일이 03월 01일(평년)일 때, 종료일은 03월 28일이다.", () -> {
                    // given
                    PaymentDate paymentDate = new PaymentDate("2023-03-01");

                    // when
                    PaymentDate endDate = PaymentDate.calculateEndDate(startDate, paymentDate);

                    // then
                    assertThat(paymentDate.isLeap()).isFalse();
                    assertThat(endDate.getDate()).isEqualTo(LocalDate.of(2023, 3, 28));
                }),
                DynamicTest.dynamicTest("결제일이 03월 29일(평년)일 때, 종료일은 04월 28일이다.", () -> {
                    // given
                    PaymentDate paymentDate = new PaymentDate("2023-03-29");

                    // when
                    PaymentDate endDate = PaymentDate.calculateEndDate(startDate, paymentDate);

                    // then
                    assertThat(paymentDate.isLeap()).isFalse();
                    assertThat(endDate.getDate()).isEqualTo(LocalDate.of(2023, 4, 28));
                }),

                DynamicTest.dynamicTest("결제일이 01월 29일(윤년)일 때, 종료일은 02월 28일이다.", () -> {
                    // given
                    PaymentDate paymentDate = new PaymentDate("2024-01-29");

                    // when
                    PaymentDate endDate = PaymentDate.calculateEndDate(startDate, paymentDate);

                    // then
                    assertThat(paymentDate.isLeap()).isTrue();
                    assertThat(endDate.getDate()).isEqualTo(LocalDate.of(2024, 2, 28));
                }),
                DynamicTest.dynamicTest("결제일이 02월 29일(윤년)일 때, 종료일은 03월 28일이다.", () -> {
                    // given
                    PaymentDate paymentDate = new PaymentDate("2024-02-29");

                    // when
                    PaymentDate endDate = PaymentDate.calculateEndDate(startDate, paymentDate);

                    // then
                    assertThat(paymentDate.isLeap()).isTrue();
                    assertThat(endDate.getDate()).isEqualTo(LocalDate.of(2024, 3, 28));
                }),
                DynamicTest.dynamicTest("결제일이 03월 29일(윤년)일 때, 종료일은 04월 28일이다.", () -> {
                    // given
                    PaymentDate paymentDate = new PaymentDate("2024-03-29");

                    // when
                    PaymentDate endDate = PaymentDate.calculateEndDate(startDate, paymentDate);

                    // then
                    assertThat(paymentDate.isLeap()).isTrue();
                    assertThat(endDate.getDate()).isEqualTo(LocalDate.of(2024, 4, 28));
                })
        );
    }

    @TestFactory
    @DisplayName("시작일이 01월 30일이고")
    Collection<DynamicTest> calculateEndDate3() {
        // given
        PaymentDate startDate = new PaymentDate("2023-01-30");

        return List.of(
                DynamicTest.dynamicTest("결제일이 01월 30일(평년)일 때, 종료일은 02월 28일이다.", () -> {
                    // given
                    PaymentDate paymentDate = new PaymentDate("2023-01-30");

                    // when
                    PaymentDate endDate = PaymentDate.calculateEndDate(startDate, paymentDate);

                    // then
                    assertThat(paymentDate.isLeap()).isFalse();
                    assertThat(endDate.getDate()).isEqualTo(LocalDate.of(2023, 2, 28));
                }),
                DynamicTest.dynamicTest("결제일이 03월 01일(평년)일 때, 종료일은 03월 29일이다.", () -> {
                    // given
                    PaymentDate paymentDate = new PaymentDate("2023-03-01");

                    // when
                    PaymentDate endDate = PaymentDate.calculateEndDate(startDate, paymentDate);

                    // then
                    assertThat(paymentDate.isLeap()).isFalse();
                    assertThat(endDate.getDate()).isEqualTo(LocalDate.of(2023, 3, 29));
                }),
                DynamicTest.dynamicTest("결제일이 03월 30일(평년)일 때, 종료일은 04월 29일이다.", () -> {
                    // given
                    PaymentDate paymentDate = new PaymentDate("2023-03-30");

                    // when
                    PaymentDate endDate = PaymentDate.calculateEndDate(startDate, paymentDate);

                    // then
                    assertThat(paymentDate.isLeap()).isFalse();
                    assertThat(endDate.getDate()).isEqualTo(LocalDate.of(2023, 4, 29));
                }),

                DynamicTest.dynamicTest("결제일이 01월 30일(윤년)일 때, 종료일은 02월 28일이다.", () -> {
                    // given
                    PaymentDate paymentDate = new PaymentDate("2024-01-30");

                    // when
                    PaymentDate endDate = PaymentDate.calculateEndDate(startDate, paymentDate);

                    // then
                    assertThat(paymentDate.isLeap()).isTrue();
                    assertThat(endDate.getDate()).isEqualTo(LocalDate.of(2024, 2, 28));
                }),
                DynamicTest.dynamicTest("결제일이 02월 29일(윤년)일 때, 종료일은 03월 29일이다.", () -> {
                    // given
                    PaymentDate paymentDate = new PaymentDate("2024-02-29");

                    // when
                    PaymentDate endDate = PaymentDate.calculateEndDate(startDate, paymentDate);

                    // then
                    assertThat(paymentDate.isLeap()).isTrue();
                    assertThat(endDate.getDate()).isEqualTo(LocalDate.of(2024, 3, 29));
                }),
                DynamicTest.dynamicTest("결제일이 03월 30일(윤년)일 때, 종료일은 04월 29일이다.", () -> {
                    // given
                    PaymentDate paymentDate = new PaymentDate("2024-03-30");

                    // when
                    PaymentDate endDate = PaymentDate.calculateEndDate(startDate, paymentDate);

                    // then
                    assertThat(paymentDate.isLeap()).isTrue();
                    assertThat(endDate.getDate()).isEqualTo(LocalDate.of(2024, 4, 29));
                })
        );
    }

    @TestFactory
    @DisplayName("시작일이 01월 31일이고")
    Collection<DynamicTest> calculateEndDate4() {
        // given
        PaymentDate startDate = new PaymentDate("2023-01-31");

        return List.of(
                DynamicTest.dynamicTest("결제일이 01월 31일(평년)일 때, 종료일은 02월 28일이다.", () -> {
                    // given
                    PaymentDate paymentDate = new PaymentDate("2023-01-31");

                    // when
                    PaymentDate endDate = PaymentDate.calculateEndDate(startDate, paymentDate);

                    // then
                    assertThat(paymentDate.isLeap()).isFalse();
                    assertThat(endDate.getDate()).isEqualTo(LocalDate.of(2023, 2, 28));
                }),
                DynamicTest.dynamicTest("결제일이 03월 01일(평년)일 때, 종료일은 03월 30일이다.", () -> {
                    // given
                    PaymentDate paymentDate = new PaymentDate("2023-03-01");

                    // when
                    PaymentDate endDate = PaymentDate.calculateEndDate(startDate, paymentDate);

                    // then
                    assertThat(paymentDate.isLeap()).isFalse();
                    assertThat(endDate.getDate()).isEqualTo(LocalDate.of(2023, 3, 30));
                }),
                DynamicTest.dynamicTest("결제일이 03월 31일(평년)일 때, 종료일은 04월 29일이다.", () -> {
                    // given
                    PaymentDate paymentDate = new PaymentDate("2023-03-31");

                    // when
                    PaymentDate endDate = PaymentDate.calculateEndDate(startDate, paymentDate);

                    // then
                    assertThat(paymentDate.isLeap()).isFalse();
                    assertThat(endDate.getDate()).isEqualTo(LocalDate.of(2023, 4, 29));
                }),
                DynamicTest.dynamicTest("결제일이 04월 30일(평년)일 때, 종료일은 05월 30일이다.", () -> {
                    // given
                    PaymentDate paymentDate = new PaymentDate("2023-04-30");

                    // when
                    PaymentDate endDate = PaymentDate.calculateEndDate(startDate, paymentDate);

                    // then
                    assertThat(paymentDate.isLeap()).isFalse();
                    assertThat(endDate.getDate()).isEqualTo(LocalDate.of(2023, 5, 30));
                }),

                DynamicTest.dynamicTest("결제일이 01월 31일(윤년)일 때, 종료일은 02월 28일이다.", () -> {
                    // given
                    PaymentDate paymentDate = new PaymentDate("2024-01-31");

                    // when
                    PaymentDate endDate = PaymentDate.calculateEndDate(startDate, paymentDate);

                    // then
                    assertThat(paymentDate.isLeap()).isTrue();
                    assertThat(endDate.getDate()).isEqualTo(LocalDate.of(2024, 2, 28));
                }),
                DynamicTest.dynamicTest("결제일이 02월 29일(윤년)일 때, 종료일은 03월 30일이다.", () -> {
                    // given
                    PaymentDate paymentDate = new PaymentDate("2024-02-29");

                    // when
                    PaymentDate endDate = PaymentDate.calculateEndDate(startDate, paymentDate);

                    // then
                    assertThat(paymentDate.isLeap()).isTrue();
                    assertThat(endDate.getDate()).isEqualTo(LocalDate.of(2024, 3, 30));
                }),
                DynamicTest.dynamicTest("결제일이 03월 31일(윤년)일 때, 종료일은 04월 29일이다.", () -> {
                    // given
                    PaymentDate paymentDate = new PaymentDate("2024-03-31");

                    // when
                    PaymentDate endDate = PaymentDate.calculateEndDate(startDate, paymentDate);

                    // then
                    assertThat(paymentDate.isLeap()).isTrue();
                    assertThat(endDate.getDate()).isEqualTo(LocalDate.of(2024, 4, 29));
                }),
                DynamicTest.dynamicTest("결제일이 04월 30일(윤년)일 때, 종료일은 05월 30일이다.", () -> {
                    // given
                    PaymentDate paymentDate = new PaymentDate("2024-04-30");

                    // when
                    PaymentDate endDate = PaymentDate.calculateEndDate(startDate, paymentDate);

                    // then
                    assertThat(paymentDate.isLeap()).isTrue();
                    assertThat(endDate.getDate()).isEqualTo(LocalDate.of(2024, 5, 30));
                })
        );
    }

    @TestFactory
    @DisplayName("시작일이 02월 01일이고")
    Collection<DynamicTest> calculateEndDate5() {
        // given
        PaymentDate startDate = new PaymentDate("2023-02-01");

        return List.of(
                DynamicTest.dynamicTest("결제일이 02월 01일(평년)일 때, 종료일은 02월 28일이다.", () -> {
                    // given
                    PaymentDate paymentDate = new PaymentDate("2023-02-01");

                    // when
                    PaymentDate endDate = PaymentDate.calculateEndDate(startDate, paymentDate);

                    // then
                    assertThat(paymentDate.isLeap()).isFalse();
                    assertThat(endDate.getDate()).isEqualTo(LocalDate.of(2023, 2, 28));
                }),
                DynamicTest.dynamicTest("결제일이 03월 01일(평년)일 때, 종료일은 03월 30일이다.", () -> {
                    // given
                    PaymentDate paymentDate = new PaymentDate("2023-03-01");

                    // when
                    PaymentDate endDate = PaymentDate.calculateEndDate(startDate, paymentDate);

                    // then
                    assertThat(paymentDate.isLeap()).isFalse();
                    assertThat(endDate.getDate()).isEqualTo(LocalDate.of(2023, 3, 31));
                }),
                DynamicTest.dynamicTest("결제일이 04월 01일(평년)일 때, 종료일은 04월 30일이다.", () -> {
                    // given
                    PaymentDate paymentDate = new PaymentDate("2023-04-01");

                    // when
                    PaymentDate endDate = PaymentDate.calculateEndDate(startDate, paymentDate);

                    // then
                    assertThat(paymentDate.isLeap()).isFalse();
                    assertThat(endDate.getDate()).isEqualTo(LocalDate.of(2023, 4, 30));
                }),
                DynamicTest.dynamicTest("결제일이 05월 01일(평년)일 때, 종료일은 05월 31일이다.", () -> {
                    // given
                    PaymentDate paymentDate = new PaymentDate("2023-05-01");

                    // when
                    PaymentDate endDate = PaymentDate.calculateEndDate(startDate, paymentDate);

                    // then
                    assertThat(paymentDate.isLeap()).isFalse();
                    assertThat(endDate.getDate()).isEqualTo(LocalDate.of(2023, 5, 31));
                }),

                DynamicTest.dynamicTest("결제일이 02월 01일(윤년)일 때, 종료일은 02월 28일이다.", () -> {
                    // given
                    PaymentDate paymentDate = new PaymentDate("2024-02-01");

                    // when
                    PaymentDate endDate = PaymentDate.calculateEndDate(startDate, paymentDate);

                    // then
                    assertThat(paymentDate.isLeap()).isTrue();
                    assertThat(endDate.getDate()).isEqualTo(LocalDate.of(2024, 2, 28));
                }),
                DynamicTest.dynamicTest("결제일이 02월 29일(윤년)일 때, 종료일은 03월 31일이다.", () -> {
                    // given
                    PaymentDate paymentDate = new PaymentDate("2024-02-29");

                    // when
                    PaymentDate endDate = PaymentDate.calculateEndDate(startDate, paymentDate);

                    // then
                    assertThat(paymentDate.isLeap()).isTrue();
                    assertThat(endDate.getDate()).isEqualTo(LocalDate.of(2024, 3, 31));
                }),
                DynamicTest.dynamicTest("결제일이 04월 01일(윤년)일 때, 종료일은 04월 30일이다.", () -> {
                    // given
                    PaymentDate paymentDate = new PaymentDate("2024-04-01");

                    // when
                    PaymentDate endDate = PaymentDate.calculateEndDate(startDate, paymentDate);

                    // then
                    assertThat(paymentDate.isLeap()).isTrue();
                    assertThat(endDate.getDate()).isEqualTo(LocalDate.of(2024, 4, 30));
                }),
                DynamicTest.dynamicTest("결제일이 05월 01일(윤년)일 때, 종료일은 05월 31일이다.", () -> {
                    // given
                    PaymentDate paymentDate = new PaymentDate("2024-05-01");

                    // when
                    PaymentDate endDate = PaymentDate.calculateEndDate(startDate, paymentDate);

                    // then
                    assertThat(paymentDate.isLeap()).isTrue();
                    assertThat(endDate.getDate()).isEqualTo(LocalDate.of(2024, 5, 31));
                })
        );
    }
}