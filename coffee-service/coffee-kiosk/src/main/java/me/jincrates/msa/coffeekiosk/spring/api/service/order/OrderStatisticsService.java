package me.jincrates.msa.coffeekiosk.spring.api.service.order;

import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import me.jincrates.msa.coffeekiosk.spring.api.service.mail.MailService;
import me.jincrates.msa.coffeekiosk.spring.domain.order.Order;
import me.jincrates.msa.coffeekiosk.spring.domain.order.OrderRepository;
import me.jincrates.msa.coffeekiosk.spring.domain.order.OrderStatus;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderStatisticsService {

    private final OrderRepository orderRepository;
    private final MailService mailService;

    public void sendOrderStatisticsMail(LocalDate orderDate, String email) {
        // 해당 일자에 결제완료된 주문들을 가져와서
        List<Order> orders = orderRepository.findOrdersBy(
            orderDate.atStartOfDay(),
            orderDate.plusDays(1).atStartOfDay(),
            OrderStatus.COMPLETE
        );

        // 총 매출 합계를 계산하고
        int totalAmount = orders.stream()
            .mapToInt(Order::getTotalPrice)
            .sum();

        // 메일 전송
        boolean result = mailService.sendMail(
            "no-reply@kiosk.com",
            email,
            String.format("[매출통계] %s", orderDate),
            String.format("총 매출 합계는 %s원입니다.", totalAmount)
        );

        if (!result) {
            throw new IllegalArgumentException("매출 통계 메일 전송에 실패했습니다.");
        }
    }
}