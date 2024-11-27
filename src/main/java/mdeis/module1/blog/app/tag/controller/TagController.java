package mdeis.module1.blog.app.tag.controller;

import jakarta.validation.Valid;
import mdeis.module1.blog.app.tag.api.NewTagApi;
import mdeis.module1.blog.app.tag.api.TagApi;
import mdeis.module1.blog.app.tag.service.TagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/tags", produces = MediaType.APPLICATION_JSON_VALUE)
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<TagApi> createCategory(@RequestBody @Valid NewTagApi newTagApi) {
        return new ResponseEntity<>(tagService.createTag(newTagApi), HttpStatus.OK);
    }

    @GetMapping(value = "/{tagId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<TagApi> readCategory(@PathVariable Integer tagId) {
        return new ResponseEntity<>(tagService.readTag(tagId), HttpStatus.OK);
    }

    @PutMapping(value = "/{tagId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<TagApi> updateCategory(@PathVariable Integer tagId, @RequestBody @Valid NewTagApi newTagApi) {
        return new ResponseEntity<>(tagService.updateTag(tagId, newTagApi), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{tagId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> deleteCategory(@PathVariable Integer tagId) {
        tagService.deleteTag(tagId);
        return new ResponseEntity<>("Tag deleted.", HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<TagApi>> getAllTags() {
        return new ResponseEntity<>(tagService.getAllTags(), HttpStatus.OK);
    }
}
