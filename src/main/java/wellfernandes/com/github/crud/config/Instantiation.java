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

		
		postRepository.saveAll(Arrays.asList(post1, post2));

	}
}
