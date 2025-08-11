package tech.ixirsii.map.server.data;

import lombok.NonNull;

/**
 * A simple message.
 *
 * @author Ryan Porterfield
 * @param message Message.
 * @since 1.0.0
 */
public record Message(@NonNull String message) {
}
