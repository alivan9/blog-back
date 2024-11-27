package mdeis.module1.blog.app.tag.service;

import mdeis.module1.blog.app.tag.api.NewTagApi;
import mdeis.module1.blog.app.tag.api.TagApi;

import java.util.List;

public interface TagService {

    TagApi createTag(NewTagApi newTagApi);

    TagApi readTag(Integer tagId);

    TagApi updateTag(Integer tagId, NewTagApi newTagApi);

    void deleteTag(Integer newTagApi);

    List<TagApi> getAllTags();
}
