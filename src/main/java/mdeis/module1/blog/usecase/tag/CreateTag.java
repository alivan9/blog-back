package mdeis.module1.blog.usecase.tag;

import mdeis.module1.blog.app.tag.api.NewTagApi;
import mdeis.module1.blog.data.TagRepository;
import mdeis.module1.blog.domain.Tag;
import mdeis.module1.blog.domain.exception.SaveEntityException;
import org.springframework.stereotype.Component;


@Component
public class CreateTag {

    private final TagRepository tagRepository;

    public CreateTag(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Tag invoke(NewTagApi newTagApi) {
        Tag tag = new Tag();
        try {
            tag.setName(newTagApi.getName());
            tag = tagRepository.save(tag);
        } catch (Exception e) {
            throw new SaveEntityException("Error creating Tag: " + e.getMessage());
        }
        return tag;
    }

}
