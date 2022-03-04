package org.thraex.share.file.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 鬼王
 * @date 2021/12/03 09:42
 */
public class ShareFile implements Serializable {

    private String id;

    private String name;

    private String remark;

    /**
     * {@link ShareFile#id}
     */
    private String parentId;

    private String level;

    /**
     * Base file info id
     */
    private String fileId;

    private Boolean folder;

    /**
     * 0: User; 1: Org
     * Use enum
     */
    private Short type;

    /**
     * User id or Org id
     */
    private String owner;

    private String createdBy;

    private LocalDateTime createdDate;

    private String modifiedBy;

    private LocalDateTime modifiedDate;

}
