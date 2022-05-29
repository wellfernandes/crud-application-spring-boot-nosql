package wellfernandes.com.github.crud.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import wellfernandes.com.github.crud.domain.User;

@Repository // save, read, delete and update
public interface UserRepository extends MongoRepository<User, String> {

}
