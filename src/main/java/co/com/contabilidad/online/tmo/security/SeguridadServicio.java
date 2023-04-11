package co.com.contabilidad.online.tmo.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import co.com.contabilidad.online.tmo.utils.PropiedadesConfiguracion;

@Configuration
public class SeguridadServicio extends WebSecurityConfigurerAdapter {
    
	private static final Logger lOGGER = LoggerFactory.getLogger(SeguridadServicio.class);
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private PropiedadesConfiguracion propiedades;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		lOGGER.info("Ingreso configure HttpSecurity  " );
		http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().httpBasic();
		http.headers().cacheControl();
		lOGGER.info("Salida configure HttpSecurity  " );
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		lOGGER.info("Ingreso configure  AuthenticationManagerBuilder " );
		userDetailsService();
		auth.inMemoryAuthentication().withUser(propiedades.getPropiedad("usuario.autenticacion.nombre"))
				.password(passwordEncoder
						.encode(propiedades.getPropiedad("usuario.autenticacion.password")))
				.roles("administrador");
		lOGGER.info("Salida configure  AuthenticationManagerBuilder " );
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		lOGGER.info("Ingreso passwordEncoder  " );
		return new BCryptPasswordEncoder();
	}
}
