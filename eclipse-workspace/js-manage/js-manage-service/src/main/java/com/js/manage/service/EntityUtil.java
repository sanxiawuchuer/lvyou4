package com.js.manage.service;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class EntityUtil {
	 public EntityUtil() {
	    }

	    public static <E> boolean isEmpty(E entity) {
	        if (!isWrapClass(entity)) {
	            if (null == entity) {
	                return true;
	            } else if (null != getSize(entity)) {
	                return getSize(entity) == 0;
	            } else {
	                Field[] fieldlist = entity.getClass().getDeclaredFields();
	                Field[] var2 = fieldlist;
	                int var3 = fieldlist.length;

	                for(int var4 = 0; var4 < var3; ++var4) {
	                    Field field = var2[var4];
	                    if (!isEmpty(entity, field.getName())) {
	                        return false;
	                    }
	                }

	                return true;
	            }
	        } else {
	            return null == entity || "".equals(String.valueOf(entity).trim());
	        }
	    }

	    public static <E, V> void setTer(E entity, String property, V value) {
	        if (null != entity && !isEmpty(property) && null != value) {
	            Object entityTemp = entity;
	            String[] arrProperty = property.trim().split("\\.");

	            for(int i = 0; i < arrProperty.length - 1; ++i) {
	                Object newTemp = invokeReturn(entityTemp, "get" + toUpperFirst(arrProperty[i]));
	                if (null == newTemp) {
	                    newTemp = newEntity(entityTemp, arrProperty[i]);
	                    invokeNoReturn(entityTemp, "set" + toUpperFirst(arrProperty[i]), newTemp);
	                }

	                entityTemp = newTemp;
	            }

	            invokeNoReturn(entityTemp, "set" + toUpperFirst(arrProperty[arrProperty.length - 1]), value);
	        }
	    }

	    public static <E> Object getTer(E entity, String property) {
	        if (null != entity && !isEmpty(property)) {
	            Object entityTemp = entity;
	            String[] var3 = property.trim().split("\\.");
	            int var4 = var3.length;

	            for(int var5 = 0; var5 < var4; ++var5) {
	                String propertyArr = var3[var5];
	                entityTemp = invokeReturn(entityTemp, "get" + toUpperFirst(propertyArr));
	                if (null == entityTemp) {
	                    break;
	                }
	            }

	            return entityTemp;
	        } else {
	            return null;
	        }
	    }

	    public static <E> boolean isEmpty(E entity, String property) {
	        Object value = getTer(entity, property);
	        if (isEmpty(null == value ? "" : String.valueOf(value))) {
	            return true;
	        } else {
	            Integer size = getSize(value);
	            return null != size && size == 0;
	        }
	    }

	    public static <M> boolean isEmptyMuster(M muster) {
	        if (null == muster) {
	            return true;
	        } else {
	            Integer size = getSize(muster);
	            return size != null && size == 0;
	        }
	    }

	    public static <T> void sameObjectSetTerNotNull(T to, T on) {
	        Field[] toFieldlist = to.getClass().getDeclaredFields();
	        Field[] var3 = toFieldlist;
	        int var4 = toFieldlist.length;

	        for(int var5 = 0; var5 < var4; ++var5) {
	            Field field = var3[var5];
	            String pName = field.getName();
	            Object toValue = getTer(to, pName);
	            if (!isEmpty(toValue)) {
	                invoke(on, "set" + toUpperFirst(pName), new Class[]{field.getType()}, new Object[]{toValue});
	            }
	        }

	    }

	    public static <E> Integer getSize(E entity) {
	        Object size = invokeReturn(entity, "size");
	        return null == size ? null : Integer.parseInt(size.toString());
	    }

	    public static <E> String propertyNullCheck(E entity, Map<String, String> propertyMap) {
	        Iterator var2 = propertyMap.keySet().iterator();

	        String propertyName;
	        do {
	            if (!var2.hasNext()) {
	                return null;
	            }

	            propertyName = (String)var2.next();
	        } while(!isEmpty(getTer(entity, propertyName)));

	        return (String)propertyMap.get(propertyName);
	    }

	    public static <T, O> void mapToOther(T to, O on, Map<String, String> mapPN) {
	        if (!isEmpty(to) && !isEmpty(on)) {
	            Iterator var3 = mapPN.keySet().iterator();

	            while(var3.hasNext()) {
	                String onPropertyName = (String)var3.next();
	                setTer(on, (String)mapPN.get(onPropertyName), getTer(to, onPropertyName));
	            }

	        }
	    }

	    public static <E> void removeNullList(List<E> list) {
	        for(int i = 0; i < list.size(); ++i) {
	            if (isEmpty(list.get(i))) {
	                list.remove(i);
	                --i;
	            }
	        }

	    }

	    private static <E> Object invoke(E entity, String methodName, Class[] valueTypes, Object[] values) {
	        try {
	            return entity.getClass().getMethod(methodName, null == valueTypes ? new Class[0] : valueTypes).invoke(entity, null == values ? new Object[0] : values);
	        } catch (Exception var5) {
	            return null;
	        }
	    }

	    private static <E, V> Object invokeReturn(E entity, String methodName, V value) {
	        return invoke(entity, methodName, null == value ? null : new Class[]{value.getClass()}, null == value ? null : new Object[]{value});
	    }

	    private static <E, V> void invokeNoReturn(E entity, String methodName, V value) {
	        invoke(entity, methodName, null == value ? null : new Class[]{value.getClass()}, null == value ? null : new Object[]{value});
	    }

	    private static <E> Object invokeReturn(E entity, String methodName) {
	        return invoke(entity, methodName, (Class[])null, (Object[])null);
	    }

	    private static <E> void invokeNoReturn(E entity, String methodName) {
	        invoke(entity, methodName, (Class[])null, (Object[])null);
	    }

	    private static String toUpperFirst(String str) {
	        return isEmpty(str) ? str : str.replaceFirst(str.substring(0, 1), str.substring(0, 1).toUpperCase());
	    }

	    private static boolean isEmpty(String str) {
	        return null == str || "".equals(str.trim());
	    }

	    private static <E, O> O newEntity(E entity, String property) {
	        property = property.trim();
	        Field[] fieldlist = entity.getClass().getDeclaredFields();
	        Field[] var3 = fieldlist;
	        int var4 = fieldlist.length;

	        for(int var5 = 0; var5 < var4; ++var5) {
	            Field field = var3[var5];
	            String pName = field.getName();
	            if (pName.equals(property)) {
	                String className = field.getType().toString();

	                try {
	                    return (O) Class.forName(className.substring(className.indexOf(" ") + 1)).newInstance();
	                } catch (Exception var10) {
	                    return null;
	                }
	            }
	        }

	        return null;
	    }

	    private static boolean isWrapClass(Object obj) {
	        return obj instanceof String || obj instanceof Number || obj instanceof Date;
	    }
}
