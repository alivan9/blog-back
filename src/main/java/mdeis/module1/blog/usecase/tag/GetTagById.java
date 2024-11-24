package mdeis.module1.blog.usecase.tag;

import jakarta.persistence.EntityNotFoundException;
import mdeis.module1.blog.data.TagRepository;
import mdeis.module1.blog.domain.Tag;
import org.springframework.stereotype.Component;

@Component
public class GetTagById {

    private final TagRepository tagRepository;

    public GetTagById(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Tag invoke(Integer tagId) {
        return tagRepository.findById(tagId).orElseThrow(
                () -> new EntityNotFoundException("Tag not found with id: " + tagId));
    }

}
