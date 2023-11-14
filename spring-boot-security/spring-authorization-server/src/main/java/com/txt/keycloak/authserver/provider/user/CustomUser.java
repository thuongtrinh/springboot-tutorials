package com.txt.keycloak.authserver.provider.user;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.keycloak.common.util.MultivaluedHashMap;
import org.keycloak.component.ComponentModel;
import org.keycloak.credential.LegacyUserCredentialManager;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.SubjectCredentialManager;
import org.keycloak.models.UserModel;
import org.keycloak.storage.adapter.AbstractUserAdapter;

class CustomUser extends AbstractUserAdapter {

    private final String username;
    private final String email;
    private final String firstName;
    private final String lastName;
    private final Date birthDate;
    private final Boolean emailVerified;
    private final Boolean enabled;

    private CustomUser(KeycloakSession session, RealmModel realm,
                       ComponentModel storageProviderModel,
                       String username,
                       String email,
                       String firstName,
                       String lastName,
                       Date birthDate,
                       Boolean emailVerified,
                       Boolean enabled) {
        super(session, realm, storageProviderModel);
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.emailVerified = emailVerified;
        this.enabled = enabled;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean isEmailVerified() {
        return emailVerified;
    }

    public Date getBirthDate() {
        return birthDate;
    }

   /* public Boolean getEmailVerified() {
        return emailVerified;
    }

    public Boolean getEnabled() {
        return enabled;
    }*/

    @Override
    public Map<String, List<String>> getAttributes() {
        MultivaluedHashMap<String, String> attributes = new MultivaluedHashMap<>();
        attributes.add(UserModel.USERNAME, getUsername());
        attributes.add(UserModel.EMAIL, getEmail());
        attributes.add(UserModel.FIRST_NAME, getFirstName());
        attributes.add(UserModel.LAST_NAME, getLastName());
        attributes.add(UserModel.EMAIL_VERIFIED, String.valueOf(isEmailVerified()));
        attributes.add(UserModel.ENABLED, String.valueOf(isEnabled()));
        attributes.add("birthDate", getBirthDate().toString());
        return attributes;
    }

    static class Builder {
        private final KeycloakSession session;
        private final RealmModel realm;
        private final ComponentModel storageProviderModel;
        private String username;
        private String email;
        private String firstName;
        private String lastName;
        private Date birthDate;
        private Boolean emailVerified;
        private Boolean enabled;

        Builder(KeycloakSession session, RealmModel realm, ComponentModel storageProviderModel, String username) {
            this.session = session;
            this.realm = realm;
            this.storageProviderModel = storageProviderModel;
            this.username = username;
        }

        Builder email(String email) {
            this.email = email;
            return this;
        }

        Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        Builder birthDate(Date birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        Builder emailVerified(Boolean emailVerified) {
            this.emailVerified = emailVerified;
            return this;
        }

        Builder enabled(Boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        CustomUser build() {
            return new CustomUser(
                    session,
                    realm,
                    storageProviderModel,
                    username,
                    email,
                    firstName,
                    lastName,
                    birthDate,
                    emailVerified,
                    enabled);

        }
    }

    @Override
    public SubjectCredentialManager credentialManager() {
        return new LegacyUserCredentialManager(session, realm, this);
    }
}