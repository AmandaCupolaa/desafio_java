package school.sptech.componentes;

import org.springframework.jdbc.core.JdbcTemplate;
import school.sptech.Operavel;
import school.sptech.bancoDeDados.Conexao;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CpuDAO implements Operavel {
    Conexao conexao;
    JdbcTemplate con;

    public CpuDAO() {
        this.conexao = new Conexao();
        this.con = conexao.getConexaoDoBanco();
    }

    public boolean verificarComponente() {
        boolean existeCPU = true;

        List<Integer> idCpus = con.queryForList("SELECT idComponente FROM Componente WHERE nomeComponente = 'CPU'", Integer.class);

        if (idCpus.isEmpty()) {
            existeCPU = false;
        }

        return existeCPU;
    }

    @Override
    public void adicionarComponente(double metrica) {

        con.update("INSERT INTO Metrica (limiteMaximo) VALUES (?)", metrica);
        Integer idMetrica = con.queryForObject("SELECT idMetrica FROM Metrica ORDER BY idMetrica DESC LIMIT 1", Integer.class);
        con.update("INSERT INTO Componente (nomeComponente, unidadeMedida, fkMetrica) VALUES (?,?,?)", "'CPU'", "'%'", idMetrica);

        System.out.println("CPU adicionada com sucesso!!!");

    }

    @Override
    public void removerComponente() {
        int count = con.queryForObject("SELECT COUNT(*) FROM Componente WHERE nomeComponente = 'CPU'", Integer.class);

        if (count == 0) {
            System.out.println("Nenhum componente CPU encontrado.");
        } else {
            con.update("DELETE FROM Componente WHERE nomeComponente = 'CPU'");
            System.out.println("CPU removida com sucesso.");

        }
    }

    @Override
    public void iniciarMonitoramento(double registro, String dataHora) {

        Integer id = con.queryForObject("SELECT idComponente FROM Componente WHERE nomeComponente = 'CPU'", Integer.class);
        con.update("INSERT INTO Registro (registro, dataHora, fkComponente) VALUES (?,?,?)", registro, dataHora, id);

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
