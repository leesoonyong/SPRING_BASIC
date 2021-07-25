package hello.core.scan.fillter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.*;

import static org.springframework.context.annotation.ComponentScan.*;

public class ComponentAppConfigTest {

    @Test
    void filterScan(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = ac.getBean("beanA",BeanA.class);
        Assertions.assertThat(beanA).isNotNull();

        org.junit.jupiter.api.Assertions.assertThrows(
                NoSuchBeanDefinitionException.class,
                () -> ac.getBean("beanB", BeanB.class));


    }

    @Configuration
    @ComponentScan(includeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
                   excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
    )
    static class ComponentFilterAppConfig {

    }
}
