package com.kamadhenu.signalsusermanagement.exception;

import org.apache.commons.lang3.StringUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;


/**
 * <h1>Entity not found exception</h1>
 * <p>
 * This class provides the logic to throw the entity not found exceptions
 *
 * @author Kamadhenu
 */
public class EntityNotFoundException extends Exception {

    /**
     * Entity not found exception
     *
     * @param message the message for the error
     */
    public EntityNotFoundException(String message) {
        super(message);
    }

    /**
     *
     * @param clazz
     * @param searchParamsMap
     */
    public EntityNotFoundException(Class clazz, String... searchParamsMap) {
        super(EntityNotFoundException.generateMessage(clazz.getSimpleName(), toMap(String.class, String.class, searchParamsMap)));
    }

    /**
     *
     * @param entity
     * @param searchParams
     * @return
     */
    private static String generateMessage(String entity, Map<String, String> searchParams) {
        return StringUtils.capitalize(entity) +
                " was not found for parameters " +
                searchParams;
    }

    /**
     *
     * @param keyType
     * @param valueType
     * @param entries
     * @param <K>
     * @param <V>
     * @return
     */
    private static <K, V> Map<K, V> toMap(
            Class<K> keyType, Class<V> valueType, Object... entries) {
        if (entries.length % 2 == 1)
            throw new IllegalArgumentException("Invalid entries");
        return IntStream.range(0, entries.length / 2).map(i -> i * 2)
                .collect(HashMap::new,
                        (m, i) -> m.put(keyType.cast(entries[i]), valueType.cast(entries[i + 1])),
                        Map::putAll);
    }

}
