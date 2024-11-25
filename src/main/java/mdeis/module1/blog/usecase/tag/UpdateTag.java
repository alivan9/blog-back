package mdeis.module1.blog.usecase.tag;

import mdeis.module1.blog.app.tag.api.NewTagApi;
import mdeis.module1.blog.data.TagRepository;
import mdeis.module1.blog.domain.Tag;
import mdeis.module1.blog.domain.exception.SaveEntityException;
import org.springframework.stereotype.Component;


@Component
public class UpdateTag {

    private final TagRepository tagRepository;

    public UpdateTag(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Tag invoke(Tag tag, NewTagApi newTagApi) {
        try {
            tag.setName(newTagApi.getName());
            tag = tagRepository.save(tag);
        } catch (Exception e) {
            throw new SaveEntityException("Error updating tag: " + e.getMessage());
        }
        return tag;
    }

}
