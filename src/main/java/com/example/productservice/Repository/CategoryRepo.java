package com.example.productservice.Repository;

import com.example.productservice.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Long> {

//    Category findByCategoryName(String category_name);

}
