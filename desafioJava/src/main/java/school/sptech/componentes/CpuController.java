package school.sptech.componentes;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.processador.Processador;
import school.sptech.TimerTask.Mensagem;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Timer;

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

        Timer agendador = new Timer();
        Scanner in = new Scanner(System.in);

        double cpuUsage = processador.getUso();

        double cpuFormatted = (double) Math.round(cpuUsage * 100) / 100;
        String dataAtual = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        cpuDAO.iniciarMonitoramento(cpuFormatted, dataAtual);

        System.out.println("Para sair pressione qualquer tecla");
        String mensagemEibir = "Cpu: " + cpuFormatted;

        Mensagem tarefa1 = new Mensagem(mensagemEibir, 1000,5000);
        agendador.schedule(tarefa1, tarefa1.getDelay(), tarefa1.getPeriodo());

        in.nextLine();
        agendador.cancel();
    }
}
