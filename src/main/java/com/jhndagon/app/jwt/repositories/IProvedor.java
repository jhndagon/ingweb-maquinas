package com.jhndagon.app.jwt.repositories;

import com.jhndagon.app.jwt.models.Provedor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProvedor extends PagingAndSortingRepository<Provedor, Long> {
}
