package school.sptech.EncantoPersonalizados.infrastructure.adapter;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Repository;
import school.sptech.EncantoPersonalizados.core.application.gateway.FotoProdutoGateway;
import school.sptech.EncantoPersonalizados.core.domain.FotoProduto;
import school.sptech.EncantoPersonalizados.infrastructure.persistence.repository.FotoProdutoRepository;

import java.util.Optional;

@Repository
public class FotoProdutoRepositoryAdapter implements FotoProdutoGateway {

    private final FotoProdutoRepository repository;
    private final CacheManager cacheManager;

    public FotoProdutoRepositoryAdapter(FotoProdutoRepository repository, CacheManager cacheManager) {
        this.repository = repository;
        this.cacheManager = cacheManager;
    }

    @Override
    public FotoProduto save(FotoProduto foto) {
        FotoProduto salvo = repository.save(foto);

        // Evict cache da própria foto (fotoProdutoById) para manter a entrada atualizada
        if (salvo.getId() != null) {
            Cache fotoCache = cacheManager.getCache("fotoProdutoById");
            if (fotoCache != null) fotoCache.evict(salvo.getId());
        }

        return salvo;
    }

    @Override
    // Cache da foto individual: evita acesso ao DB para leituras frequentes de metadados/URL
    @org.springframework.cache.annotation.Cacheable(cacheNames = "fotoProdutoById", key = "#id")
    public Optional<FotoProduto> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public void delete(FotoProduto foto) {
        repository.delete(foto);

        // Evict cache da própria foto removida (fotoProdutoById)
        if (foto.getId() != null) {
            Cache fotoCache = cacheManager.getCache("fotoProdutoById");
            if (fotoCache != null) fotoCache.evict(foto.getId());
        }

    }
}
