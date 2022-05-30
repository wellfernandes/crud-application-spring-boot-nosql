package wellfernandes.com.github.crud.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import wellfernandes.com.github.crud.domain.User;
import wellfernandes.com.github.crud.dto.UserDTO;
import wellfernandes.com.github.crud.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET) // or @GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {

		List<User> list = userService.findAll();
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());

		return ResponseEntity.ok().body(listDto);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET) // or @GetMapping
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {

		User user = userService.findById(id);

		return ResponseEntity.ok().body(new UserDTO(user));
	}

	@RequestMapping(method = RequestMethod.POST) // or @PostMapping
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDTO) {

		User user = userService.fromDTO(objDTO);
		user = userService.insert(user);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE) // or @GetMapping
	public ResponseEntity<Void> delete(@PathVariable String id) {

		userService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
