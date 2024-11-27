package mdeis.module1.blog.app.post.api;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class FilterPostResponseApi {

    Integer id;
    String title;
    String description;
    String frontpage;
    String category;
    Timestamp createdAt;
}
