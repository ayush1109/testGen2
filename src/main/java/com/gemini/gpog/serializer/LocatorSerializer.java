package com.gemini.gpog.serializer;

import com.gemini.gpog.annotation.LocatorType;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;

public class LocatorSerializer {

	public static String getSerializedKey(Field field) {
		String annotationValue = field.getAnnotation(LocatorType.class).value();

		if (StringUtils.isEmpty(annotationValue)) {
			return field.getName();
		} else {
			return annotationValue;
		}
	}

}
