package mdeis.module1.blog.app.comment.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class NewCommentApi {

    @NotBlank
    String description;
    @NotNull
    Integer postId;
    @NotNull
    Integer userId;
}
