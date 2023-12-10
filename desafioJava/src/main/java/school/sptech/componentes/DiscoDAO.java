package school.sptech.componentes;

import org.springframework.jdbc.core.JdbcTemplate;
import school.sptech.Operavel;
import school.sptech.bancoDeDados.Conexao;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class DiscoDAO implements Operavel {
    Conexao conexao;
    JdbcTemplate con;

    public DiscoDAO() {
        this.conexao = new Conexao();
        this.con = conexao.getConexaoDoBanco();
    }

    @Override
    public boolean verificarComponente() {
        boolean existeDisco = false;

        List<Integer> idDiscos = con.queryForList("SELECT idComponente FROM Componente WHERE nomeComponente = 'Disco'", Integer.class);

        if (!idDiscos.isEmpty()) {
            existeDisco = true;
        }

        return existeDisco;
    }

    @Override
    public void adicionarComponente(double metrica) {

        con.update("INSERT INTO Componente (nomeComponente, unidadeMedida, metricaComponente) VALUES (?,?, ?)", "Disco", "%", metrica);

        System.out.println("Disco adicionado com sucesso!!!");

    }

    @Override
    public void removerComponente() {
        int count = con.queryForObject("SELECT COUNT(*) FROM Componente WHERE nomeComponente = 'Disco'", Integer.class);

        if (count == 0) {
            System.out.println("Nenhum componente Disco encontrado.");
        } else {
            con.update("DELETE FROM Componente WHERE nomeComponente = 'Disco'");
            System.out.println("Disco removido com sucesso.");

        }
    }

    @Override
    public void iniciarMonitoramento(double registro, String dataHora) {

            Integer id = con.queryForObject("SELECT idComponente FROM Componente WHERE nomeComponente = 'Disco'", Integer.class);
            con.update("INSERT INTO Registro (registro, dataHora,fkComponente) VALUES (?,?,?)", registro, dataHora, id);

    }
}