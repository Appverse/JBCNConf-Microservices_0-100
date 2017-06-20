package com.gft.jbcnconf.movements.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static com.gft.jbcnconf.movements.util.EnumSerializerHelper.getEnumFromString;

/**
 * MovementType Enum
 * @author MOCR
 *
 */
public enum MovementType {
	RESTAURANT,
	HOTEL, 
	SUPERMARKET,
	BOOKSHOP,
	TRANSPORT;
	
	@JsonCreator 
	public static MovementType fromValue (String value) {
		return getEnumFromString (MovementType.class, value);
	}
	
	@JsonValue
    public String toJson() {
         return name().toUpperCase();
    }
}
