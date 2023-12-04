package school.sptech.componentes;

import com.github.britooo.looca.api.group.processador.Processador;
import org.springframework.jdbc.core.JdbcTemplate;
import school.sptech.Looca;
import school.sptech.bancoDeDados.Conexao;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Cpu extends Componente {

    Conexao conexao = new Conexao();
    JdbcTemplate con = conexao.getConecaoDoBanco();

    public Cpu(String nome, Double limiteMaximo, String medida) {
        super(nome, limiteMaximo, medida);
    }

    @Override
    public void iniciarMonitoramento() {

        Looca looca1 = new Looca();
        Processador processador = looca1.getProcessador();

        double cpuUsage = processador.getUso();

        String cpuFormatted = new DecimalFormat("#.##").format(cpuUsage).replace(",", ".");
        String dataAtual = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        System.out.println("CPU: " + cpuFormatted + " %");

        Integer id = con.queryForObject("SELECT idComponente FROM Componente WHERE nomeComponente = 'Cpu'", Integer.class);
        con.update("INSERT INTO Registro (registro, dateDado,fkComponente) VALUES (?,?,?)",cpuFormatted, dataAtual, id);

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
