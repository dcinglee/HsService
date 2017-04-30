package com.hutservice.common.dao;

import com.hutservice.common.entity.ImageInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 图片存取类。
 *
 * @author Ewing
 */
public interface ImageInfoDao extends JpaRepository<ImageInfo, String> {

}
