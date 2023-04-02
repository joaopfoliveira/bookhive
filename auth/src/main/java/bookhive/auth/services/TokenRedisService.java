package bookhive.auth.services;

import bookhive.auth.model.TokenEntity;
import bookhive.auth.repositories.TokenRedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TokenRedisService {
    @Autowired
    private TokenRedisRepository tokenRedisRepository;

    public TokenEntity save(TokenEntity entity) {
        return tokenRedisRepository.save(entity);
    }


    public Optional<TokenEntity> findById(String id) {
        return tokenRedisRepository.findById(id);
    }

    public Iterable<TokenEntity> findAll() {
        return tokenRedisRepository.findAll();
    }
}
