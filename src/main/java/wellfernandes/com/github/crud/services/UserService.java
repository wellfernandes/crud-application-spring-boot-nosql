package wellfernandes.com.github.crud.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wellfernandes.com.github.crud.domain.User;
import wellfernandes.com.github.crud.dto.UserDTO;
import wellfernandes.com.github.crud.repository.UserRepository;
import wellfernandes.com.github.crud.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findById(String id) {
		Optional<User> user = userRepository.findById(id);

		return user.orElseThrow(() -> new ObjectNotFoundException("object not found!"));
	}

	public User insert(User obj) {
		return userRepository.insert(obj);
	}
	
	public void delete(String id) {
		userRepository.findById(id);
		userRepository.deleteById(id);
	}
	
	public User fromDTO(UserDTO objDTO) {
		return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail());
	}
}
