package wellfernandes.com.github.crud.services;

import java.util.List;
import java.util.NoSuchElementException;
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

	public User update(User obj) {
		try {
			Optional<User> newUser = userRepository.findById(obj.getId());
			User user = newUser.get();
			updateData(user, obj);
			return userRepository.save(user);
		} catch (NoSuchElementException e) {
			throw new ObjectNotFoundException("Object not found.");
		}
	}

	// auxiliary method for user update
	private void updateData(User user, User obj) {
		user.setName(obj.getName());
		user.setEmail(obj.getEmail());
	}

	public User fromDTO(UserDTO objDTO) {
		return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail());
	}
}
