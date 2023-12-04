package school.sptech.componentes;

import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.discos.Volume;
import org.springframework.jdbc.core.JdbcTemplate;
import school.sptech.Looca;
import school.sptech.bancoDeDados.Conexao;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Disco extends Componente {
    Conexao conexao = new Conexao();
    JdbcTemplate con = conexao.getConecaoDoBanco();

    public Disco(String nome, Double limiteMaximo, String medida) {
        super(nome, limiteMaximo, medida);
    }

    @Override
    public void iniciarMonitoramento() {
        Looca looca1 = new Looca();
        DiscoGrupo discoGrupo = looca1.getDisco();
        List<Volume> volumes = discoGrupo.getVolumes();

        double discoUsage = volumes.get(0).getDisponivel();

        String discoFormmatted = new DecimalFormat("#.##").format((discoUsage * 7.5) / 1024.0 / 1024.0 / 1024.0).replace(",", ".");
        String dataAtual = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        System.out.println("Temperatura CPU: " + discoFormmatted + " ºC ");

        Integer id = con.queryForObject("SELECT idComponente FROM Componente WHERE nomeComponente = 'Disco'", Integer.class);
        con.update("INSERT INTO Registro (registro, dateDado,fkComponente) VALUES (?,?,?)",discoFormmatted, dataAtual, id);


        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
