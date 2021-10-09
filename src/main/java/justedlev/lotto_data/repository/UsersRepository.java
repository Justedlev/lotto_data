package justedlev.lotto_data.repository;

import justedlev.lotto_data.repository.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsersRepository extends MongoRepository<UserEntity, String> {
}
