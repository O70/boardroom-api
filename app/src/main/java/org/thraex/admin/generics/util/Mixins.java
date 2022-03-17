package org.thraex.admin.generics.util;

/**
 * TODO: Opt
 *
 * @author 鬼王
 * @date 2022/03/17 19:15
 */
public abstract class Mixins {

    public static final String WHERE_CLAUSE = "deleted = false";
    public static final String SOFT_DELETE_DICT = "UPDATE base_dict SET deleted = true WHERE id = ?";
    public static final String SOFT_DELETE_ROLE = "UPDATE base_role SET deleted = true WHERE id = ?";
    public static final String SOFT_DELETE_USER = "UPDATE base_user SET deleted = true WHERE id = ?";

    public static final String[] IGNORED_PATHS_UPDATE = {
            "id",
            "deleted",
            "createdBy",
            "createdDate",
            "modifiedBy",
            "modifiedDate"
    };

    public static final String[] IGNORED_PATHS_FIND = { "enabled", "deleted" };

}
