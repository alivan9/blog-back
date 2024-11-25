package mdeis.module1.blog.app.tag.service;

import mdeis.module1.blog.app.tag.api.NewTagApi;
import mdeis.module1.blog.app.tag.api.TagApi;

public interface TagService {

    TagApi createTag(NewTagApi newTagApi);

    TagApi readTag(Integer tagId);

    TagApi updateTag(Integer tagId, NewTagApi newTagApi);

    void deleteTag(Integer newTagApi);
}
