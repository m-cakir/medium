package com.mcakir.bootiful.repositories;

import com.mcakir.bootiful.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
