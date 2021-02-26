package dev.highright96.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class PrototypeTest {

    @Test
    void singletonBeanTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        System.out.println("find bean1");
        PrototypeBean bean1 = ac.getBean(PrototypeBean.class);

        System.out.println("find bean2");
        PrototypeBean bean2 = ac.getBean(PrototypeBean.class);

        System.out.println("bean1 : " + bean1);
        System.out.println("bean2 : " + bean2);

        Assertions.assertThat(bean1).isNotSameAs(bean2);

        bean1.destroy();
        bean2.destroy();

        ac.close();
    }

    @Scope("prototype")
    static class PrototypeBean{

        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy");
        }
    }
}
