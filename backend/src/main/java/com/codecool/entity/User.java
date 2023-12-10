package com.codecool.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "registered_at")
  private Timestamp dateOfRegistration;

  @Column(name = "user_name")
  private String userName;

  @Column(name = "email")
  private String email;

  @Column(name = "hashed_password")
  private String hashedPassword;

  @Column(name = "is_admin")
  private boolean isAdmin;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "account_id")
  @JsonManagedReference
  private Account account;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
    name = "categories_users_join",
    joinColumns = { @JoinColumn(name = "user_id") },
    inverseJoinColumns = { @JoinColumn(name = "category_id") }
  )
  @JsonManagedReference
  private List<TransactionCategory> categories;

  public User(String email, String hashedPassword, Account account) {
    this.email = email;
    this.hashedPassword = hashedPassword;
    this.categories = new ArrayList<>();
    this.dateOfRegistration = new Timestamp(System.currentTimeMillis());
    this.userName = "CHANGE ME!";
    this.account = account;
    this.isAdmin = false;
  }

  @Override
  public String toString() {
    return String.format("[ENTITY]: User | [Id]: %s | [DateOfRegistration]: %s | [UserName]: %s | [Email]: %s | [Account]: %s | [IsAdmin]: %s | [Categories]: %s", id, dateOfRegistration, userName, email, account, isAdmin, categories);
  }
}
