package org.tspp.users;

import java.util.Objects;

public class User {

    User(String name, String patronymic, String surname, String mail, String password) {
        this.name = name;
        this.patronymic = patronymic;
        this.surname = surname;
        this.mail = mail;
        this.password = password;
    }

    public final String getName() {
        return this.name;
    }

    public final String getPatronymic() {
        return this.patronymic;
    }

    public final String getSurname() {
        return this.surname;
    }

    public final String getMail() {
        return this.mail;
    }

    public final boolean validatePassword(String password) {
        return Objects.equals(this.password, password);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof User o)) {
            return false;
        }
        return Objects.equals(this.getMail(), o.getMail());
    }

    private final String name;
    private final String patronymic;
    private final String surname;

    private final String mail;
    private final String password;
}
