package com.txt.security.registration.entity.authen;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Calendar;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "verification_token")
public class VerificationToken {

    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String token;

    @OneToOne(targetEntity = Users.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "username", foreignKey = @ForeignKey(name = "FK_VERIFY_USER"))
    private Users user;

    @Column
    private Date expirydate;

    @Column
    private String status;

    public VerificationToken(final String token) {
        this.token = token;
        this.expirydate = calculateExpiryDate(EXPIRATION);
    }

    public VerificationToken(final String token, final Users user) {
        this.token = token;
        this.user = user;
        this.expirydate = calculateExpiryDate(EXPIRATION);
    }

    public void updateToken(final String token) {
        this.token = token;
        this.expirydate = calculateExpiryDate(EXPIRATION);
    }

    private Date calculateExpiryDate(final int expiryTimeInMinutes) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }

}
