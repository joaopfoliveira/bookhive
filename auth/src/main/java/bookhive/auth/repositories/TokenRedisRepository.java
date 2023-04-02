package bookhive.auth.repositories;

import bookhive.auth.model.TokenEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRedisRepository extends CrudRepository<TokenEntity, String> {
}
