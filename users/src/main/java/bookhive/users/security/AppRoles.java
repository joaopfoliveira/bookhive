package bookhive.users.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public enum AppRoles {

    READONLY_USER(new HashSet<>(List.of(AppPermissions.USER_READ))),
    USER(new HashSet<>(Arrays.asList(AppPermissions.USER_READ, AppPermissions.USER_WRITE))),
    ADMIN(new HashSet<>()),
    SUPER_ADMIN(new HashSet<>(Arrays.asList(AppPermissions.USER_READ, AppPermissions.USER_WRITE)));

    final Set<AppPermissions> permissions;

    AppRoles(Set<AppPermissions> permissions) {
        this.permissions = permissions;
    }

    public Set<GrantedAuthority> getAuthorities() {
        log.info("Granted Authority for" + this.name() + " - " + this.permissions.stream().map(p -> new SimpleGrantedAuthority(p.name())).collect(Collectors.toSet()));
        return this.permissions.stream().map(p -> new SimpleGrantedAuthority(p.name())).collect(Collectors.toSet());
    }
}