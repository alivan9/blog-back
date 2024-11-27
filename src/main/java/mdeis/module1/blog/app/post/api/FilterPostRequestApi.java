package mdeis.module1.blog.app.post.api;

import lombok.Data;

import java.util.List;

@Data
public class FilterPostRequestApi {
    List<Integer> categories;
    List<Integer> tags;
}
