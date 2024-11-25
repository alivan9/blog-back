package mdeis.module1.blog.app.tag.service.mapper;

import mdeis.module1.blog.app.tag.api.TagApi;
import mdeis.module1.blog.domain.Tag;
import org.springframework.stereotype.Component;

@Component
public class TagMapper {

    public TagApi map(Tag tag) {
        TagApi tagApi = new TagApi();
        tagApi.setId(tag.getId());
        tagApi.setName(tag.getName());
        return tagApi;
    }
}
