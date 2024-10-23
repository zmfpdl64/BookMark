package woojin.bookmaker.common.utils;

import java.lang.reflect.Field;

public class PrintUtils {
    public static final String INDENT = "    ";

    public static String print (Object obj) {
        StringBuilder result = new StringBuilder();
        Class<?> clazz = obj.getClass();

        // 클래스 이름 추가
        result.append("{ \n");

        // 필드 정보를 가져와서 문자열로 정리
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true); // private 필드에 접근하기 위해 설정
            try {
                // 필드 이름과 값을 추가
                result.append(INDENT).append(field.getName())
                        .append(": ")
                        .append(field.get(obj)) // 필드의 값을 가져옴
                        .append("\n");
            } catch (IllegalAccessException e) {
                result.append(field.getName()).append(": <access denied>\n");
            }
        }
        result.append("} \n");
        return result.toString();
    }
}
