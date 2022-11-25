package com.citynow.accessmanagement.entity;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "authority")
public class Authority {

  @Id
  @Column(name = "code", nullable = false)
  @Type(type = "org.hibernate.type.TextType")
  private String id;

  @Column(name = "name")
  @Type(type = "org.hibernate.type.TextType")
  private String name;

  @Column(name = "options")
  @Type(type = "com.vladmihalcea.hibernate.type.json.JsonNodeBinaryType")
  private JsonNode options;

  @OneToMany(mappedBy = "authorityCode")
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

  public JsonNode getOptions() {
    return options;
  }

  public void setOptions(JsonNode options) {
    this.options = options;
  }

  public Set<RoleAuthority> getRoleAuthorities() {
    return roleAuthorities;
  }

  public void setRoleAuthorities(Set<RoleAuthority> roleAuthorities) {
    this.roleAuthorities = roleAuthorities;
  }

}