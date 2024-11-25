package mdeis.module1.blog.usecase.tag;

import mdeis.module1.blog.data.TagRepository;
import mdeis.module1.blog.domain.Tag;
import mdeis.module1.blog.domain.exception.SaveEntityException;
import org.springframework.stereotype.Component;

@Component
public class DeleteTag {

    private final TagRepository tagRepository;

    public DeleteTag(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public void invoke(Tag tag) {
        try {
            tagRepository.delete(tag);
        } catch (Exception e) {
            throw new SaveEntityException("Error deleting tag: " + e.getMessage());
        }
    }

}
