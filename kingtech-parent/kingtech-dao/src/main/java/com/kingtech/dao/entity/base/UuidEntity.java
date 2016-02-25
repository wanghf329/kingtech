/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kingtech.dao.entity.base;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.hibernate.annotations.GenericGenerator;

@Data
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
public abstract class UuidEntity extends BaseEntity {

	@Id
	@GeneratedValue(generator = "uuidGenerator")
	@GenericGenerator(name = "uuidGenerator", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(length = 36)
	private String id;
	
}
