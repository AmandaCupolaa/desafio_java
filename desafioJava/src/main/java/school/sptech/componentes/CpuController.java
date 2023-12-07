package school.sptech.componentes;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.processador.Processador;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class CpuController extends Componente{
    private final Looca looca = new Looca ();
    private final Processador processador;
    private final CpuDAO cpuDAO;

    public CpuController(String nome) {
        super(nome);
        this.processador = looca.getProcessador();
        this.cpuDAO = new CpuDAO();
    }

    @Override
    public boolean isCadastrado() {
        return cpuDAO.verificarComponente();
    }

    @Override
    public void definirMetrica() {
        Scanner leitor = new Scanner(System.in);

        System.out.println("""
                +---------------------------------------+
                |      Defina a métrica para a CPU      |
                +---------------------------------------+
                 """);

        super.setMetrica(leitor.nextInt());

        cpuDAO.adicionarComponente(super.getMetrica());
    }

    public void removerCPU() {
        cpuDAO.removerComponente();
    }

    @Override
    public void dadosComponente() {
        double cpuUsage = processador.getUso();

        String cpuFormatted = new DecimalFormat("#.##").format(cpuUsage).replace(",", ".");
        String dataAtual = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        // mudar tipo pra double, não String - todo
//        cpuDAO.iniciarMonitoramento(cpuFormatted, dataAtual);

    }
}
