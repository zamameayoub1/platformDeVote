package com.example.app.getvote.model;

import com.example.app.login_and_registration.model.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class voter extends User {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vote_id", referencedColumnName = "id")
    private
}
