package wellfernandes.com.github.crud.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import wellfernandes.com.github.crud.domain.Post;

@Repository // save, read, delete and update
public interface PostRepository extends MongoRepository<Post, String> {

}
