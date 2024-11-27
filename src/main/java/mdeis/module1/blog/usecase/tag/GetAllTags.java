package mdeis.module1.blog.usecase.tag;

import jakarta.persistence.EntityNotFoundException;
import mdeis.module1.blog.data.TagRepository;
import mdeis.module1.blog.domain.Tag;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllTags {

    private final TagRepository tagRepository;

    public GetAllTags(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<Tag> invoke() {
        List<Tag> tags = tagRepository.findAll();
        if (!tags.isEmpty()) {
            return tags;
        } else {
            throw new EntityNotFoundException("Tags not found");
        }
    }

}
