package mdeis.module1.blog.usecase.tag;

import mdeis.module1.blog.domain.Tag;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetListTag {

    private final GetTagById getTagById;

    public GetListTag(GetTagById getTagById) {
        this.getTagById = getTagById;
    }

    public List<Tag> invoke(List<Integer> tagIdList) {
        ArrayList<Tag > tagsList = new ArrayList<>();
        tagIdList.forEach(it -> {
            Tag role = getTagById.invoke(it);
            tagsList.add(role);
        });
        return tagsList;
    }

}
