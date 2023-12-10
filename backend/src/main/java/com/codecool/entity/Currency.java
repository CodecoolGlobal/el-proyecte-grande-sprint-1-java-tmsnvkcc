package com.codecool.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "currencies")
@Getter
@Setter
public class Currency {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @OneToMany(mappedBy = "currency")
  @JsonIgnore
  private List<Account> accounts;

  public Currency() {
    this.accounts = new ArrayList<>();
  }

  @Override
  public String toString() {
    return String.format("[Id]: %s | [Accounts]: %s", id, accounts);
  }
}
