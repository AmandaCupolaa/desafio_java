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

    @Override
    public boolean verificarComponente() {
        boolean existeCPU = false;

        List<Integer> idCpus = con.queryForList("SELECT idComponente FROM Componente WHERE nomeComponente = 'CPU'", Integer.class);

        if (!idCpus.isEmpty()) {
            existeCPU = true;
        }

        return existeCPU;
    }

    @Override
    public void adicionarComponente(double metrica) {

        con.update("INSERT INTO Componente (nomeComponente, unidadeMedida, metricaComponente) VALUES (?,?, ?)", "CPU", "%", metrica);

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
