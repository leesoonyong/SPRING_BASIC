package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefulServiceTest {
    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService bean = ac.getBean(StatefulService.class);
        StatefulService bean2 = ac.getBean(StatefulService.class);
        //ThreadA A사용자가 10000원주문
        int userAPrice = bean.order("userA", 10000);

        //ThreadB B사용자가 20000원주문
        int userBPrice = bean2.order("userB", 20000);
        
        //ThreadA 사용자A 주문 금액 조회
        System.out.println("userAPrice = " + userAPrice);
        System.out.println("userBPrice = " + userBPrice);

//        Assertions.assertThat(bean.getPrice()).isEqualTo(20000);
    }

    static class TestConfig{

        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }
}
