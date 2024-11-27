package mdeis.module1.blog.app.tag.service;

import mdeis.module1.blog.app.tag.api.NewTagApi;
import mdeis.module1.blog.app.tag.api.TagApi;
import mdeis.module1.blog.app.tag.service.mapper.TagMapper;
import mdeis.module1.blog.domain.Tag;
import mdeis.module1.blog.usecase.tag.TagUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    private final TagUseCase tagUseCase;
    private final TagMapper tagMapper;

    public TagServiceImpl(TagUseCase tagUseCase, TagMapper tagMapper) {
        this.tagUseCase = tagUseCase;
        this.tagMapper = tagMapper;
    }

    @Override
    public TagApi createTag(NewTagApi newTagApi) {
        return tagMapper.map(tagUseCase.createTag.invoke(newTagApi));
    }

    @Override
    public TagApi readTag(Integer tagId) {
        return tagMapper.map(tagUseCase.getTagById.invoke(tagId));
    }

    @Override
    public TagApi updateTag(Integer tagId, NewTagApi newTagApi) {
        Tag tag = tagUseCase.getTagById.invoke(tagId);
        return tagMapper.map(tagUseCase.updateTag.invoke(tag, newTagApi));
    }

    @Override
    public void deleteTag(Integer tagId) {
        Tag tag = tagUseCase.getTagById.invoke(tagId);
        tagUseCase.deleteTag.invoke(tag);
    }

    @Override
    public List<TagApi> getAllTags() {
        return tagUseCase.getAllTags.invoke().stream().map(tagMapper::map).toList();
    }
}
