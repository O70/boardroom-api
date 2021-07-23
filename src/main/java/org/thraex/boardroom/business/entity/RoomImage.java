package org.thraex.boardroom.business.entity;

import org.thraex.toolkit.entity.JpaEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author 鬼王
 * @date 2021/07/14 18:17
 */
@Entity
public class RoomImage extends JpaEntity<RoomImage> {

    /**
     * {@link Room#getId()}
     */
    @Column(length = IDENTIFIER_LENGTH)
    private String roomId;

    /**
     * File service FileInfo#getId()
     */
    @Column(length = IDENTIFIER_LENGTH)
    private String fileId;

    public String getRoomId() {
        return roomId;
    }

    public RoomImage setRoomId(String roomId) {
        this.roomId = roomId;
        return this;
    }

    public String getFileId() {
        return fileId;
    }

    public RoomImage setFileId(String fileId) {
        this.fileId = fileId;
        return this;
    }

}
