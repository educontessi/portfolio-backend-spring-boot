package io.github.educontessi.service;

import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import io.github.educontessi.model.Pais;
import io.github.educontessi.repository.PaisRepository;

@Service
public class PaisService {

  @Autowired
  private PaisRepository repository;

  public Pais update(Long id, Pais entity) {
    Pais pais = findById(id);
    BeanUtils.copyProperties(entity, pais, "id");
    return repository.save(pais);
  }

  public Pais findById(Long id) {
    Optional<Pais> pais = repository.findById(id);
    if (!pais.isPresent()) {
      throw new EmptyResultDataAccessException(1);
    }
    return pais.get();
  }


}
