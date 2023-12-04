package school.sptech.bancoDeDados;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class Conexao {
        private JdbcTemplate conecaoDoBanco;

        public Conexao(){
            MysqlDataSource mysqlDataSource = new MysqlDataSource();
            mysqlDataSource.setDatabaseName("DesafioJava");
            mysqlDataSource.setUser("DesafioJava");
            mysqlDataSource.setPassword("deafiojava2023");

            conecaoDoBanco = new JdbcTemplate(mysqlDataSource);

        }

        public JdbcTemplate getConecaoDoBanco(){
            return conecaoDoBanco;
        }

}
