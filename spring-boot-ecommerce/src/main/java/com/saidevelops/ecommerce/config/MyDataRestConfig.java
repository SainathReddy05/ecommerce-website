package com.saidevelops.ecommerce.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;

import org.hibernate.boot.Metadata;
//import org.hibernate.mapping.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.saidevelops.ecommerce.entity.Product;
import com.saidevelops.ecommerce.entity.ProductCategory;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer{
	
	private EntityManager entityManager;
	
	@Autowired
    public MyDataRestConfig(EntityManager theEntityManager) {
		// TODO Auto-generated constructor stub
		entityManager=theEntityManager;
	}

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
//		// TODO Auto-generated method stub
//		RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);
	
		HttpMethod[] theUnsupportedActions = {HttpMethod.PUT,HttpMethod.POST,HttpMethod.DELETE};
		
		config.getExposureConfiguration()
				.forDomainType(Product.class)
				.withItemExposure((metdata,httpMethods) -> httpMethods.disable(theUnsupportedActions))
				.withCollectionExposure((metdata,httpMethods) -> httpMethods.disable(theUnsupportedActions));
		
		config.getExposureConfiguration()
				.forDomainType(ProductCategory.class)
				.withItemExposure((metdata,httpMethods) -> httpMethods.disable(theUnsupportedActions))
				.withCollectionExposure((metdata,httpMethods) -> httpMethods.disable(theUnsupportedActions));

		
		exposeIds(config);
		}

	private void exposeIds(RepositoryRestConfiguration config) {
		// TODO Auto-generated method stub
		
		//get a list of all entity classes from the entity manager
	
		Set<EntityType<?>> entities= entityManager.getMetamodel().getEntities();
		
		//Create an array of entity types
		List<Class> entityClasses=new ArrayList<>();
		
		//Get the entity types for entities
		for(EntityType tempEntityType:entities){
			entityClasses.add(tempEntityType.getJavaType());
		}
		
		// Expose the entity ids fot the array of entity/domain types
		Class[] domainTypes=entityClasses.toArray(new Class[0]);
		
		config.exposeIdsFor(domainTypes);
		
		
		
		
	}
	}

	
