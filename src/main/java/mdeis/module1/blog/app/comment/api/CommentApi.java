package mdeis.module1.blog.app.comment.api;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CommentApi {
    Integer id;
    String description;
    Timestamp createdAt;
    Timestamp updateAt;
}
