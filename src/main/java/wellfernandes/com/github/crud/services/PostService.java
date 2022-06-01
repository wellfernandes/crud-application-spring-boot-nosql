package wellfernandes.com.github.crud.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wellfernandes.com.github.crud.domain.Post;
import wellfernandes.com.github.crud.repository.PostRepository;
import wellfernandes.com.github.crud.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;

	public Post findById(String id) {
		Optional<Post> user = postRepository.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("object not found!"));
	}

	public List<Post> findByTitle(String text) {
		return postRepository.findByTitleContainingIgnoreCase(text);

		// alternative query using @query annotation in postRepository class
		// return postRepository.findByTitle(text);
	}

	public List<Post> fullSearch(String text, Date minDate, Date maxDate) {

		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
		return postRepository.fullSearch(text, minDate, maxDate);

	}
}
