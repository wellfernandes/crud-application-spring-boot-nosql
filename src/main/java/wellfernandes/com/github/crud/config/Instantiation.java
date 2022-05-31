package wellfernandes.com.github.crud.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import wellfernandes.com.github.crud.domain.Post;
import wellfernandes.com.github.crud.domain.User;
import wellfernandes.com.github.crud.dto.AuthorDTO;
import wellfernandes.com.github.crud.dto.CommentDTO;
import wellfernandes.com.github.crud.repository.PostRepository;
import wellfernandes.com.github.crud.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {

		// mock
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		userRepository.deleteAll();
		postRepository.deleteAll();

		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");

		userRepository.saveAll(Arrays.asList(maria, alex, bob)); // salving users first

		Post post1 = new Post(null, sdf.parse("21/03/2018"), "lets travel!", "Im going to Sao Paulo!",
				new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "good morning!!", "I woke up happy today.",
				new AuthorDTO(maria));

		CommentDTO c1 = new CommentDTO("good trip, bro!", sdf.parse("21/03/2022"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Enjoy!", sdf.parse("22/03/2022"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Have a nice day!", sdf.parse("21/03/2022"), new AuthorDTO(alex));

		post1.getListComments().addAll(Arrays.asList(c1, c2));
		post2.getListComments().addAll(Arrays.asList(c3));

		postRepository.saveAll(Arrays.asList(post1, post2));

		maria.getListPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);

	}
}
