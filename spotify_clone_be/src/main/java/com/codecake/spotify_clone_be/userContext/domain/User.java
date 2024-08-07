package com.codecake.spotify_clone_be.userContext;

import com.codecake.spotify_clone_be.sharedkernel.domain.AbstractAuditingEntity;
import jakarta.persistence.*;
import jdk.jshell.JShell;

import java.util.concurrent.Flow;

@Entity
@Table(name = "spotify_user")
public class User extends AbstractAuditingEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSequenceGenerator")
    @SequenceGenerator(name = "userSequenceGenerator", sequenceName = "user_generator", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name ="last_name")
    private String lastName;

    @Column(name ="first_name")
    private String firstName;

    @Column(name ="email")
    private String email;

    private Subscription subscription = Subscription.FREE;

    @Column(name = "image_url")
    private String imageUrl;

//    @Override
//    public Long getId() {
//        return null;
//    }
}


