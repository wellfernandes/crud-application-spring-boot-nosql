package wellfernandes.com.github.crud.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import wellfernandes.com.github.crud.domain.Post;
import wellfernandes.com.github.crud.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

	@Autowired
	private PostService postService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET) // or @GetMapping
	public ResponseEntity<Post> findById(@PathVariable String id) {

		Post post = postService.findById(id);

		return ResponseEntity.ok().body(post);
	}
}
