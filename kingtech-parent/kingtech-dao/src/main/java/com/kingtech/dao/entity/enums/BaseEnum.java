package com.kingtech.dao.entity.enums;

import java.io.Serializable;

/**
 * enumerations with String key
 *
 */
public interface BaseEnum extends Serializable {
	
	/**
     * key along with enum
     *
     * @return
     */
    String getKey();

}
