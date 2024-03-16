package org.learning.mapping.one2one.repository;

import org.learning.mapping.one2one.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
