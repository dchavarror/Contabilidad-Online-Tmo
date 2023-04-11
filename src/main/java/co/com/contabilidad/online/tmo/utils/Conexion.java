package co.com.contabilidad.online.tmo.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@ConfigurationProperties
public class Conexion {

	private static final Logger lOGGER = LoggerFactory.getLogger(Conexion.class);
	
	@Autowired
	private PropiedadesConfiguracion propiedades;
	
	@Autowired
	private EncriptarDesancriptar seguridad;

	@Autowired
	private DataSourceProperties dataSourceProperties;

	@Autowired
	private DataSource dataSource;

	public DataSourceProperties dataSourceProperties() {
		lOGGER.info("Ingreso dataSourceProperties  ");
		try {
			this.dataSourceProperties.setUrl(this.propiedades.getPropiedad("url"));
			this.dataSourceProperties.setUsername(this.propiedades.getPropiedad("usuario"));
			this.dataSourceProperties.setPassword(this.seguridad.desencriptar(this.propiedades.getPropiedad("contrasenia")));
			this.dataSourceProperties.setDriverClassName(this.propiedades.getPropiedad("driver-class-name"));
		} catch (Exception e) {
			lOGGER.error("Error dataSourceProperties: " + e.getMessage());
			e.printStackTrace();
		}
		lOGGER.info("Salida dataSourceProperties  ");
		return this.dataSourceProperties;
	}

	@Bean(name = "dsContabilidadOnline")
	public DataSource dataSource() {
		lOGGER.info("Ingreso dataSource  ");
		try {
			dataSourceProperties();
			this.dataSource = this.dataSourceProperties.initializeDataSourceBuilder().build();
		} catch (Exception e) {
			lOGGER.error("Error dataSource " + e.getMessage());
			e.printStackTrace();
		}
		lOGGER.info("Salida dataSource:  ");
		return this.dataSource;
	}

	@Bean(name = "jdbcContabilidadOnline")
	@Autowired
	public JdbcTemplate masterJdbcTemplate(@Qualifier("dsContabilidadOnline") DataSource dsMaster) {
		
		return new JdbcTemplate(dsMaster);

	}
	
	public static void cerrarConexion(Connection conn) {
		lOGGER.info("Ingreso cerrarConexion  ");
		try {
			if(conn != null) {
			   conn.close();
			}
		} catch (SQLException e) {
			lOGGER.error("Error cerrarConexion: " + e.getMessage());
			e.printStackTrace();
		}
		lOGGER.info("Salida cerrarConexion  ");
	}
}
