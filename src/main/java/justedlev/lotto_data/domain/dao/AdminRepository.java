package justedlev.lotto_data.domain.dao;

import justedlev.lotto_data.domain.entities.LoginEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdminRepository extends MongoRepository<LoginEntity, String> {
}
