package com.ctu.se.oda.model11.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import org.springframework.stereotype.Component;

@Component
public class ModelMapperUtil {
	public static <T> void copy(T from, T to) {
		try {
			PropertyDescriptor[] fromPds = java.beans.Introspector.getBeanInfo(from.getClass()).getPropertyDescriptors();
			PropertyDescriptor[] toPds = java.beans.Introspector.getBeanInfo(to.getClass()).getPropertyDescriptors();

			for (PropertyDescriptor fromPd : fromPds) {
				for (PropertyDescriptor toPd : toPds) {
					if (isMatchingProperty(fromPd, toPd)) {
						Method readMethod = fromPd.getReadMethod();
						Method writeMethod = toPd.getWriteMethod();

						if (readMethod != null && writeMethod != null) {
							Object value = readMethod.invoke(from);
							if (value != null) {
								writeMethod.invoke(to, value);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static boolean isMatchingProperty(PropertyDescriptor fromPd, PropertyDescriptor toPd) {
		return fromPd.getName().equals(toPd.getName()) && fromPd.getPropertyType().equals(toPd.getPropertyType())
				&& toPd.getWriteMethod() != null;
	}
}
