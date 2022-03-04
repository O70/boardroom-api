package org.thraex.share.file.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 鬼王
 * @date 2021/12/03 10:00
 */
public class ShareFileUser implements Serializable {

    private String id;

    /**
     * {@link ShareFile#id}
     */
    private String fileId;

    private String userId;

    /**
     * new / upload
     */
    private Boolean write;

    private Boolean read;

    private Boolean download;

    private Boolean delete;

    private String createdBy;

    private LocalDateTime createdDate;

    private String modifiedBy;

    private LocalDateTime modifiedDate;

}
