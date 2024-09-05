// Repository là nơi tương tác với database
package com.fukuda.Learn.Spring.Boot3.repository;

import com.fukuda.Learn.Spring.Boot3.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> { // Tương tác với entity bản dữ liệu
}
