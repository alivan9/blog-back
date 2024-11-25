package mdeis.module1.blog.app.category.api;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class NewCategoryApi {

    @NotBlank
    String name;
}
