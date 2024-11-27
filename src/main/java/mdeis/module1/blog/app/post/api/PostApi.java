package mdeis.module1.blog.app.post.api;

import lombok.Data;
import mdeis.module1.blog.domain.*;

import java.sql.Timestamp;
import java.util.List;

@Data
public class PostApi {

    Integer id;
    String title;
    String description;
    String content;
    String frontpage;
    PostStatus status;
    Category category;
    User approvedBy;
    User createdBy;
    List<Tag> tags;
    List<Score> scores;
    List<Comment> comments;
    Timestamp createdAt;
}
