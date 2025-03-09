package org.mertguler.cinemium.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.mertguler.cinemium.model.core.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
