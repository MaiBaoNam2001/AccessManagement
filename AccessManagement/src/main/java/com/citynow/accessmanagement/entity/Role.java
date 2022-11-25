package com.citynow.accessmanagement.entity;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "role")
public class Role {

  @Id
  @Column(name = "code", nullable = false)
  @Type(type = "org.hibernate.type.TextType")
  private String id;

  @Column(name = "name")
  @Type(type = "org.hibernate.type.TextType")
  private String name;

  @OneToMany(mappedBy = "roleCode")
  private Set<User> users = new LinkedHashSet<>();

  @OneToMany(mappedBy = "roleCode")
  private Set<RoleAuthority> roleAuthorities = new LinkedHashSet<>();

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<User> getUsers() {
    return users;
  }

  public void setUsers(Set<User> users) {
    this.users = users;
  }

  public Set<RoleAuthority> getRoleAuthorities() {
    return roleAuthorities;
  }

  public void setRoleAuthorities(Set<RoleAuthority> roleAuthorities) {
    this.roleAuthorities = roleAuthorities;
  }

}