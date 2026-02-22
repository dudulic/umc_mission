package com.example.demo.domain.repository;

import com.example.demo.domain.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {

}