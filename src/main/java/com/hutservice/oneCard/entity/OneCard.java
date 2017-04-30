package com.hutservice.oneCard.entity;

import com.hutservice.application.config.AppConfig;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author lidx
 * @date 2017/4/28
 * @describe
 */
@Entity
public class OneCard {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = AppConfig.UUID_GENERATOR)
    @GeneratedValue(generator = "idGenerator")
    private String oneCardID;
}
