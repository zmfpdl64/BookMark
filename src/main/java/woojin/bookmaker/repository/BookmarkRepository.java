package woojin.bookmaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Integer> {

    @Query("select b from Bookmark b where b.userId =:userId and b.categoryId =:categoryId")
    List<Bookmark> findByUserIdAndCategoryId(
            @Param("userId") Integer userId,
            @Param("categoryId") Integer categoryId
    );
}
