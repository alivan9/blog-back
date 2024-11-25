package mdeis.module1.blog.app.tag.api;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class NewTagApi {

    @NotBlank
    String name;
}
