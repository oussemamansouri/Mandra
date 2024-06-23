package com.elife.mandra.DAO.Entities.OptionControl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.elife.mandra.DAO.Entities.OptionControl.PrivilegeOption.*;

@RequiredArgsConstructor
public enum RoleOption {
    Admin(Set.of(READ_PRIVILEGE, WRITE_PRIVILEGE, UPDATE_PRIVILEGE, DELETE_PRIVILEGE)),
    Owner(Set.of(READ_PRIVILEGE, WRITE_PRIVILEGE, UPDATE_PRIVILEGE, DELETE_PRIVILEGE)),
    Client(Set.of(READ_PRIVILEGE, WRITE_PRIVILEGE, DELETE_PRIVILEGE));

    @Getter
    private final Set<PrivilegeOption> privileges;

    public List<SimpleGrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = getPrivileges()
                .stream()
                .map(privilege -> new SimpleGrantedAuthority(privilege.name()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name())); // ROLE_ prefix
        return authorities;
    }
}
