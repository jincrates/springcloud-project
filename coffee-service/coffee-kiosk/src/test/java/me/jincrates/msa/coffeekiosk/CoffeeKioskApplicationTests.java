package me.jincrates.msa.coffeekiosk;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = CoffeeKioskApplicationTests.class)
class CoffeeKioskApplicationTests {

    @Test
    void contextLoads() {
    }

}

/**
 * Test Double
 * <p>
 * Dubmmy - 아무 것도 하지 않는 깡통 객체
 * <p>
 * Fake - 단순한 형태로 동일한 기능을 수행하나, 프로덕션에서 쓰기에는 부족한 객체(ex. FakeRepository)
 * <p>
 * Stub - 테스트에서 요청한 겻에 대해 미리 준비한 결과를 제공하는 객체. 그 외에는 응답하지 않는다.
 * <p>
 * Spy - Stub이면서 호출된 내용을 기록하여 보여줄 수 있는 객체. 일부는 실제 객체처럼 동작시키고 일부만 Stubbing 할 수 있다.
 * <p>
 * Mock - 행위에 대한 기대를 명세하고, 그에 따라 동작하도록 만들어진 객체
 * <p>
 * Stub과 Mock의 차이
 * <p>
 * Stub -> 상태 검증(State Verification)
 * <p>
 * Mock -> 행위 검증(Behavior Verification)
 * <p>
 * Stub과 Mock의 차이
 * <p>
 * Stub -> 상태 검증(State Verification)
 * <p>
 * Mock -> 행위 검증(Behavior Verification)
 * <p>
 * Stub과 Mock의 차이 Stub -> 상태 검증(State Verification)
 * <p>
 * Mock -> 행위 검증(Behavior Verification)
 * <p>
 * Stub과 Mock의 차이 Stub -> 상태 검증(State Verification) Mock -> 행위 검증(Behavior Verification)
 */

/**
 * Stub과 Mock의 차이
 * Stub -> 상태 검증(State Verification)
 * Mock -> 행위 검증(Behavior Verification)
 */