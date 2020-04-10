package com.jhndagon.app.jwt.repositories;

import com.jhndagon.app.jwt.models.Maquina;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMaquina extends PagingAndSortingRepository<Maquina, Long> {
}
