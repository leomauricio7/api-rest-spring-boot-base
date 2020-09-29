package io.github.lmauricio.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Locale;

// class de configuracao para internacionazacao das mensagens
@Configuration // anotation para dizer que a classe é ua configuration
public class InternacionalizacaoConfig {

    // metodo de fonte de messages
    @Bean
    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource rbms  = new ReloadableResourceBundleMessageSource();
        rbms.setBasename("classpath:messages"); // informa qual é o arquivo que está com as mensagens
        rbms.setDefaultEncoding("ISO-8859-1"); // seta o encoding das messagens
        rbms.setDefaultLocale(Locale.getDefault()); // setando a locate default
        return  rbms;
    }

    // metodo para fazer a interpolacao -> pega o metodo source e troca pela msg que esta dentro do arquivo
    @Bean
    public LocalValidatorFactoryBean validatorFactoryBean(){
        LocalValidatorFactoryBean bean =  new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource()); // setando metodo de msg
        return bean;
    }
}
