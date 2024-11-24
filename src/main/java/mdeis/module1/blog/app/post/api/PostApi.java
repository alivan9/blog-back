package mdeis.module1.blog.app.post.api;

import lombok.Data;
import mdeis.module1.blog.domain.*;

import java.util.List;

@Data
public class PostApi {

    Integer id;
    String title;
    String description;
    Category category;
    User approvedBy;
    User user;
    List<Tag> tags;
    List<Score> scores;
}
