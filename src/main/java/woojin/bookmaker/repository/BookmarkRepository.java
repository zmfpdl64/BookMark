package woojin.bookmaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Integer> {

    @Query("select b from Bookmark b where b.userId =:userId and b.categoryId =:categoryId and b.deleted=false")
    List<Bookmark> findByUserIdAndCategoryId(
            @Param("userId") Integer userId,
            @Param("categoryId") Integer categoryId
    );

    @Query("select b from Bookm" +
            "ark b where b.userId =:userId and b.id=:id and b.deleted=false")
    Bookmark findByUserIdAndId(
            @Param("userId") Integer userId,
            @Param("id") Integer bookmarkId
    );
}
