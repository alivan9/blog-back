package mdeis.module1.blog.app.post.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class NewPostApi {

    @NotBlank
    String title;
    @NotBlank
    String description;
    @NotBlank
    String content;
    String frontpage;
    @NotNull
    Integer categoryId;
    @NotNull
    Integer userId;
    @NotNull
    List<Integer> tags;
}
