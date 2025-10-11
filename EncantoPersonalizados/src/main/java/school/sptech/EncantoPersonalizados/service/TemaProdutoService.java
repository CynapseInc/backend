package school.sptech.EncantoPersonalizados.service;

import org.springframework.stereotype.Service;
import school.sptech.EncantoPersonalizados.dto.temaProduto.TemaProdutoMapper;
import school.sptech.EncantoPersonalizados.dto.temaProduto.TemaProdutoRequestDTO;
import school.sptech.EncantoPersonalizados.dto.temaProduto.TemaProdutoResponseDTO;
import school.sptech.EncantoPersonalizados.entities.TemaProduto;
import school.sptech.EncantoPersonalizados.repository.TemaProdutoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TemaProdutoService {
    public final TemaProdutoRepository repository;

    public TemaProdutoService(TemaProdutoRepository repository) {
        this.repository = repository;
    }


    public TemaProduto findById(Integer id){
        Optional<TemaProduto> optionalTemaProduto = repository.findById(id);
        if(optionalTemaProduto.isEmpty()) return null;
        return optionalTemaProduto.get();
    }


    public List<TemaProdutoResponseDTO> get(){
        List<TemaProduto> entity = repository.findAll();
        return TemaProdutoMapper.toDto(entity);
    }

    public TemaProdutoResponseDTO store(TemaProdutoRequestDTO dto){
        TemaProduto entity = repository.save(TemaProdutoMapper.toEntity(dto));
        return TemaProdutoMapper.toDto(entity);
    }

}
