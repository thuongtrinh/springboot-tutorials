package com.txt.security.registration.entity.authen;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;

@Data
@Entity
@Table(name = "password_reset_token")
@AllArgsConstructor
@NoArgsConstructor
public class PasswordResetToken {

    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String token;

    @OneToOne(targetEntity = Users.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "username")
//    @JoinColumn(nullable = false, name = "username", foreignKey = @ForeignKey(name = "FK_PWD_RESET_USER"))
    private Users user;

    @Column
    private Date expirydate;

    public PasswordResetToken(final String token) {
        this.token = token;
        this.expirydate = calculateExpiryDate(EXPIRATION);
    }

    public PasswordResetToken(final String token, final Users user) {
        this.token = token;
        this.user = user;
        this.expirydate = calculateExpiryDate(EXPIRATION);
    }

    private Date calculateExpiryDate(final int expiryTimeInMinutes) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }

    public void updateToken(final String token) {
        this.token = token;
        this.expirydate = calculateExpiryDate(EXPIRATION);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Token [String=").append(token).append("]").append("[Expires").append(expirydate).append("]");
        return builder.toString();
    }

}
