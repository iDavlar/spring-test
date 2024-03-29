package by.davlar.spring.database;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@RequiredArgsConstructor
public class DatabaseConnection {

    private final String username;
    private final String password;
    private final String url;
}
