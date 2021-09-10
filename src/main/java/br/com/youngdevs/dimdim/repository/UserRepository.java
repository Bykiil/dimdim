package br.com.youngdevs.dimdim.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.youngdevs.dimdim.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
