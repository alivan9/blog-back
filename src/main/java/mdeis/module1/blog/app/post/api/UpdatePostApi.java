package mdeis.module1.blog.app.post.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import mdeis.module1.blog.domain.PostStatus;

import java.util.List;

@Data
public class UpdatePostApi {

    @NotBlank
    String title;
    @NotBlank
    String description;
    @NotBlank
    String content;
    String frontpage;
    PostStatus status;
    @NotNull
    int approvedBy;
    @NotNull
    int categoryId;
    @NotNull
    List<Integer> tags;
}
