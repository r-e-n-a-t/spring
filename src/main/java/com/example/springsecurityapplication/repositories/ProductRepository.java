package com.example.springsecurityapplication.repositories;

import com.example.springsecurityapplication.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {


    // Поиск всех продуктов по части наименования продукта в не зависомости от регистра
    List<Product> findByTitleContainingIgnoreCase(String name);
    // Поиск всех продуктов фильтрация по диапазону цены
    @Query(value = "select * from product where (price >= ?1 and price <= ?2)", nativeQuery = true)
    List<Product> findByPrice(float ot, float Do);
    // Поиск всех продуктов сортировка по возрастанию цены
    @Query(value = "select * from product order by price",nativeQuery = true)
    List<Product> findOrderAsc();
    // Поиск всех продуктов сортировка по убыванию цены
    @Query(value = "select * from product order by price desc",nativeQuery = true)
    List<Product> findOrderDesk(String name);

    // Поиск сортировка по возрастанию цены
    @Query(value = "select * from product where (lower(title) LIKE %?1%) or (lower(title) LIKE '?1%') OR (lower(title) LIKE '%?1') order by price",nativeQuery = true)
    List<Product> findByTitleAsc(String title);
    // Поиск по категории
    @Query(value = "select * from product where category_id = ?1",nativeQuery = true)
    List<Product> findByCategory(int category);

    // Поиск по наименованию, а также сортировка по убыванию цены
    @Query(value = "select * from product where (lower(title) LIKE %?1%) or (lower(title) LIKE '?1%') OR (lower(title) LIKE '%?1') order by price desc",nativeQuery = true)
    List<Product> findByTitleDesc(String title);
    // Поиск по наименованию и фильтрация по категории
    @Query(value = "select * from product where category_id = ?2 and(lower(title) LIKE %?1%) or (lower(title) LIKE '?1%') OR (lower(title) LIKE '%?1')",nativeQuery = true)
    List<Product> findByTitleAndCategory(String title, int category);
    // Поиск по наименованию, сортировка по возрастанию цены, а также фильтрация по категории
    @Query(value = "select * from product where category_id = ?2 and(lower(title) LIKE %?1%) or (lower(title) LIKE '?1%') OR (lower(title) LIKE '%?1') order by price",nativeQuery = true)
    List<Product> findByTitleAndCategoryAsc(String title, int category);

    // Поиск по наименованию, сортировка по убыванию цены, а также фильтрация по категории
    @Query(value = "select * from product where category_id = ?2 and(lower(title) LIKE %?1%) or (lower(title) LIKE '?1%') OR (lower(title) LIKE '%?1') order by price desc",nativeQuery = true)
    List<Product> findByTitleAndCategoryDesc(String title, int category);
    // Поиск по диапазону цены, а также сортировка по возрастанию цены
    @Query(value = "select * from product where (price >= ?1 and price <= ?2) order by price",nativeQuery = true)
    List<Product> findByPriceAsc(float ot, float Do);

    // Поиск по диапазону цены, а также сортировка по убыванию цены
    @Query(value = "select * from product where (price >= ?1 and price <= ?2) order by price desc",nativeQuery = true)
    List<Product> findByPriceDesc(float ot, float Do);
    // Поиск по диапазону цены, сортировка по возрастанию цены,  а также фильтрация по категории
    @Query(value = "select * from product where (price >= ?1 and price <= ?2) and category_id = ?3 order by price",nativeQuery = true)
    List<Product> findByCategoryOrderByPriceAsc(float ot, float Do, int category);

    // Поиск по диапазону цены, сортировка по убыванию цены,  а также фильтрация по категории
    @Query(value = "select * from product where (price >= ?1 and price <= ?2) and category_id = ?3 order by price desc",nativeQuery = true)
    List<Product> findByCategoryOrderByPriceDesc(float ot, float Do, int category);

    // Поиск по диапазону цены, а также фильтрация по категории
    @Query(value = "select * from product where (price >= ?1 and price <= ?2) and category_id = ?3",nativeQuery = true)
    List<Product> findByCategoryOrderByPrice(float ot, float Do, int category);
    // сортировка по возрастанию цены, а также фильтрация по категории
    @Query(value = "select * from product where category_id = ?1 order by price",nativeQuery = true)
    List<Product> findByCategoryAsc(int category);

    // сортировка по убыванию цены, а также фильтрация по категории
    @Query(value = "select * from product where category_id = ?1 order by price desc",nativeQuery = true)
    List<Product> findByCategoryDesc(int category);

    // Поиск по наименованию и фильтрация по диапазону цены
    @Query(value = "select * from product where ((lower(title) LIKE %?1%) or (lower(title) LIKE '?1%') OR (lower(title) LIKE '%?1')) and (price >= ?2 and price <= ?3)", nativeQuery = true)
    List<Product> findByTitleAndPriceGreaterThanEqualAndPriceLessThanEqual(String title, float ot, float Do);

    // Поиск по наименованию и фильтрация по диапазону цены, а также сортировка по возрастанию цены
    @Query(value = "select * from product where (price >= ?2 and price <= ?3) and (lower(title) LIKE %?1%) or (lower(title) LIKE '?1%') OR (lower(title) LIKE '%?1') order by price",nativeQuery = true)
    List<Product> findByTitleOrderByPriceAsc(String title, float ot, float Do);

    // Поиск по наименованию и фильтрация по диапазону цены, а также сортировка по убыванию цены
    @Query(value = "select * from product where (price >= ?2 and price <= ?3) and (lower(title) LIKE %?1%) or (lower(title) LIKE '?1%') OR (lower(title) LIKE '%?1') order by price desc",nativeQuery = true)
    List<Product> findByTitleOrderByPriceDesc(String title, float ot, float Do);

    // Поиск по наименованию и фильтрация по диапазону цены, сортировка по возрастанию цены,  а также фильтрация по категории
    @Query(value = "select * from product where (price >= ?2 and price <= ?3) and category_id = ?4 and (lower(title) LIKE %?1%) or (lower(title) LIKE '?1%') OR (lower(title) LIKE '%?1') order by price",nativeQuery = true)
    List<Product> findByTitleAndCategoryOrderByPriceAsc(String title, float ot, float Do, int category);

    // Поиск по наименованию и фильтрация по диапазону цены, сортировка по убыванию цены,  а также фильтрация по категории
    @Query(value = "select * from product where (price >= ?2 and price <= ?3) and category_id = ?4 and (lower(title) LIKE %?1%) or (lower(title) LIKE '?1%') OR (lower(title) LIKE '%?1') order by price desc",nativeQuery = true)
    List<Product> findByTitleAndCategoryOrderByPriceDesc(String title, float ot, float Do, int category);


}

