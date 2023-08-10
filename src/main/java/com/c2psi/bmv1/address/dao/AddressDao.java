package com.c2psi.bmv1.address.dao;

import com.c2psi.bmv1.address.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressDao extends JpaRepository<Address, Long>, JpaSpecificationExecutor<Address> {
    Optional<Address> findAddressById(Long id);
    Optional<Address> findAddressByEmail(String email);
}
