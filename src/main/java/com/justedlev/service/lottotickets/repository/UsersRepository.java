package com.justedlev.service.lottotickets.repository;

import com.justedlev.service.lottotickets.repository.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsersRepository extends MongoRepository<UserEntity, String> {
}
