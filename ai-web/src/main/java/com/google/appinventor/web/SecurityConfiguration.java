package com.google.appinventor.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        JdbcUserDetailService jdbcDao = new JdbcUserDetailService();
        jdbcDao.setDataSource(dataSource);
        return jdbcDao;
    }

    public static class JdbcUserDetailService extends JdbcDaoSupport implements UserDetailsService {

        private static final String QUERY = "select username,password,is_admin from users where username = ?";

        @Override
        public UserDetails loadUserByUsername(String lookupUsername) throws UsernameNotFoundException {
            return getJdbcTemplate().queryForObject(QUERY,
                    new String[]{lookupUsername}, (RowMapper<UserDetails>) (rs, rowNum) -> {
                        String username = rs.getString(1);
                        String password = rs.getString(2);
                        boolean is_admin = rs.getBoolean(3);
                        return new User(username, password,
                                true, true,
                                true, true,
                                AuthorityUtils.createAuthorityList(is_admin ? "ROLE_ADMIN" : "ROLE_USER"));
                    });
        }
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();
    }
}
