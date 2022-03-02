package com.saidevelops.ecommerce.dao;

//import java.awt.print.Pageable;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

import com.saidevelops.ecommerce.entity.Product;


@CrossOrigin("http://localhost:4200")
public interface ProductRepository extends JpaRepository<Product, Long> {

	
	//Page<Product> findByCategoryId(@RequestParam("id") Long id, Pageable pageable);
	
//	@Query("select p from Product p where category=?1")
	Page<Product> findByCategoryId(@Param("id") Long id, Pageable pageable);
	//Chad uses @RequestParam but I have used @Param
	
	
	// Spring will automatically generate below query for the below code
	//select * from Product p where p.name like '%name%'
	Page<Product> findByNameContaining(@Param("name") String name, Pageable pageable);
	
}
