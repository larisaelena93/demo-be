package com.example.modelbe.config;

import com.example.modelbe.entity.Country;
import com.example.modelbe.entity.Product;
import com.example.modelbe.entity.ProductCategory;
import com.example.modelbe.entity.State;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {
    //entityManager from Hibernate JPA that helps us interogate db
    private EntityManager entityManager;

    @Autowired

    public MyDataRestConfig(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        //List containing restricted HTTP requests
        HttpMethod[] theUnsupportedActions = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE};
        //disable HTTP methods for each Repository Rest Resource: Product, ProductCategory, State, Country
        disableHttpMethods(Product.class, config, theUnsupportedActions);
        disableHttpMethods(ProductCategory.class, config, theUnsupportedActions);
        disableHttpMethods(State.class, config, theUnsupportedActions);
        disableHttpMethods(Country.class, config, theUnsupportedActions);
    }

    private void disableHttpMethods(Class theClass, RepositoryRestConfiguration config, HttpMethod[] theUnsupportedActions) {
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure((metadata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metadata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
    }

}
