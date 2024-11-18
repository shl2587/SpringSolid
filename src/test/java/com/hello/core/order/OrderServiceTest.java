package com.hello.core.order;

import com.hello.core.AppConfig;
import com.hello.core.discount.FixDiscountPolicy;
import com.hello.core.member.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    OrderService orderService;

    MemberService memberService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder() {
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

    @Test
    void fieldInjectionTest() {
//        OrderServiceImpl orderService = new OrderServiceImpl();
        // 필드 주입 방식의 경우 setter를 만들어 줘야함
//        orderService.setMemberRepository(new MemoryMemberRepository());
//        orderService.setDiscountPolicy(new FixDiscountPolicy());

        orderService.createOrder(1L, "itemA", 10000);
    }
}
