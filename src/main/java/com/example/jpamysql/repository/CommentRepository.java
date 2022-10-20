package com.example.jpamysql.repository;

import com.example.jpamysql.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
  /*  @Query(value = """
            SELECT * 
            FROM comments
            where (:postName is null OR :postName = ''  OR  POST_NAME LIKE %:postName%)
                AND (:commentP is null  OR :commentP = '' OR  COMMENT LIKE %:commentP%) 
        """, nativeQuery = true)
    public List<Comment> findByPostNameAndCommentName(@Param("postName") String postName, @Param("commentP") String comment);
*/
  @Query(value = "SELECT * FROM comments" +
            " where " +
            "(:#{#postName} is null OR :#{#postName} = ''  OR  POST_NAME LIKE %:#{#postName}%) " +
            "AND (:#{#commentP} is null  OR :#{#commentP} = '' OR  COMMENT LIKE %:#{#commentP}%)", nativeQuery = true)
    public List<Comment> findByPostNameAndCommentName( @Nullable @Param("postName") String postName,@Nullable @Param("commentP") String comment);


}