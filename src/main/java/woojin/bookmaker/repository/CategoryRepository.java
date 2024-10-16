package woojin.bookmaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query("select c from Category c where c.userId =:userId and c.deleted=false")
    List<Category> getCategoriesByUserId(@Param("userId") Integer userId);

    @Query("select c from Category c where c.id =:id and c.deleted=false")
    Category getCategoryById(@Param("id") Integer categoryId);
}
