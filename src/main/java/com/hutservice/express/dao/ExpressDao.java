package com.hutservice.express.dao;

import com.hutservice.express.entity.Express;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author lidx
 * @date 2017/3/21
 * @describe
 */
public interface ExpressDao extends JpaRepository<Express, String>, JpaSpecificationExecutor<Express> {
}
