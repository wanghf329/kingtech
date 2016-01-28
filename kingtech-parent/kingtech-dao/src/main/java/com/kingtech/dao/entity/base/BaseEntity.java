package com.kingtech.dao.entity.base;

import java.io.Serializable;

public abstract class BaseEntity implements Serializable {
    
    static final long serialVersionUID = 20160128L;

    @Override
    public String toString() {
    	return null;
//        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE, false);
    }
}
