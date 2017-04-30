package com.hutservice.common.service;

import com.hutservice.application.config.AppConfig;
import com.hutservice.application.exception.AppException;
import com.hutservice.common.dao.DictionaryDao;
import com.hutservice.common.dao.ImageInfoDao;
import com.hutservice.common.dao.LocationDao;
import com.hutservice.common.entity.Dictionary;
import com.hutservice.common.entity.ImageInfo;
import com.hutservice.common.entity.Location;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 业务服务实现类。
 *
 * @author Ewing
 */
@Service
public class CommonServiceImpl implements CommonService {
    private final Logger logger = LoggerFactory.getLogger(CommonServiceImpl.class);

    private DictionaryDao dictionaryDao;
    private LocationDao locationDao;
    private ImageInfoDao imageInfoDao;

    @Autowired
    public void setDictionaryDao(DictionaryDao dictionaryDao) {
        this.dictionaryDao = dictionaryDao;
    }

    @Autowired
    public void setLocationDao(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    @Autowired
    public void setImageInfoDao(ImageInfoDao imageInfoDao) {
        this.imageInfoDao = imageInfoDao;
    }

    /**
     * 根据类型获取字典值。
     */
    @Override
    public List<Dictionary> findDictionaryByType(String type) {
        return dictionaryDao.findByTypeOrderByValue(type);
    }

    /**
     * 获取地理位置信息。
     */
    @Override
    public List<Location> findLocationByParentId(String parentId) {
        return locationDao.findByParentIdOrderBySort(parentId);
    }

    /**
     * 根据ID获取图片。
     */
    @Override
    public ImageInfo getImageInfoById(String imageId) {
        return imageInfoDao.findOne(imageId);
    }

    /**
     * 添加图片的方法。
     */
    @Override
    @Transactional
    public ImageInfo addImage(InputStream inputStream) {
    	logger.info("addImage");
        if (inputStream == null)
            throw new AppException("图片输入流不能为空！");
        // 取Web根目录，只适用于 Web根目录/子目录/类目录 结构，否则取的是类目录。
        String classpath = CommonServiceImpl.class.getClassLoader().getResource("").getPath();
        String topDirectory = classpath.replaceAll("(.+)/[^/]+/[^/]+/", "$1");
        try {
            //裁剪图片大小（保持比例）。
            @SuppressWarnings("rawtypes")
			Thumbnails.Builder imgBuilder = Thumbnails.of(inputStream)
                    .size(AppConfig.IMAGE_MAX_WIDTH, AppConfig.IMAGE_MAX_HEIGHT)
                    .outputFormat(AppConfig.IMAGE_SAVE_FORMAT)
                    .outputQuality(1);
            BufferedImage image = imgBuilder.asBufferedImage();
            // 保存图片信息，大小，格式，存储目录等。
            ImageInfo imageInfo = new ImageInfo();
            imageInfo.setWidth(image.getWidth());
            imageInfo.setHeight(image.getHeight());
            imageInfo.setFormat(AppConfig.IMAGE_SAVE_FORMAT);
            imageInfo.setCreateDate(new Date());
            imageInfo = imageInfoDao.save(imageInfo);
            String url = "/" + AppConfig.IMAGE_DIRECTORY + "/" + imageInfo.getImageId() + "." + AppConfig.IMAGE_SAVE_FORMAT;
            imageInfo.setUrl(url);
            imageInfo = imageInfoDao.save(imageInfo);
            // 创建图片文件的目录。
            File directory = new File(topDirectory + "/" + AppConfig.IMAGE_DIRECTORY);
            if (!directory.exists()) directory.mkdirs();
            // 以图片ID为文件名称，以图片格式为后缀。
            String imagePath = topDirectory + url;
            // 由于文件读写不受事务控制，所以最后保存图片。
            ImageIO.write(image, imageInfo.getFormat(), new File(imagePath));
            return imageInfo;
        } catch (IOException e) {
            throw new AppException("图片输入输出流存储异常！");
        }
    }

    /**
     * 删除图片的方法。
     */
    @Override
    @Transactional
    public void deleteImage(String imageId) {
    	logger.info("deleteImage");
        if (!StringUtils.hasText(imageId))
            throw new AppException("请选择要删除的图片！");
        // 取Web根目录，只适用于 Web根目录/子目录/类目录 结构，否则取的是类目录。
        String classpath = CommonServiceImpl.class.getClassLoader().getResource("").getPath();
        String topDirectory = classpath.replaceAll("(.+)/[^/]+/[^/]+/", "$1");
        ImageInfo imageInfo = imageInfoDao.findOne(imageId);
        // 图片信息不为空则删除。
        String imagePath;
        if (imageInfo != null) {
            imageInfoDao.delete(imageId);
            imagePath = topDirectory + imageInfo.getUrl();
        } else {
            imagePath = topDirectory + "/" + AppConfig.IMAGE_DIRECTORY
                    + "/" + imageId + "." + AppConfig.IMAGE_SAVE_FORMAT;
        }
        // 尝试删除图片文件。
        File file = new File(imagePath);
        if (file.exists())
            if (!file.delete())
                throw new AppException("删除图片文件失败！");
    }

    @Override
    public String getImageUrl(String imageId) {
    	logger.info("getImageUrl");
        if (!StringUtils.hasText(imageId)) return null;
        ImageInfo image = imageInfoDao.findOne(imageId);
        if (image == null) {
            return null;
        } else {
            return image.getUrl();
        }
    }

    @Override
    public Map<String, String> getImageUrls(List<String> imageIds) {
    	logger.info("getImageUrls");
        // 根据图片ID集合查询所有图片对象。
        List<ImageInfo> imageInfos = imageInfoDao.findAll(imageIds);
        // 存储图片对象到Map容器中。
        Map<String, String> imageUrls = new HashMap<>();
        for (ImageInfo image : imageInfos) {
            imageUrls.put(image.getImageId(), image.getUrl());
        }
        return imageUrls;
    }

}
