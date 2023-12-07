package school.sptech.componentes;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.memoria.Memoria;
import school.sptech.Servidor;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MemoriaController extends Componente{
    private final Looca looca = new Looca ();
    private final Memoria memoria;
    private MemoriaDAO memoriaDAO;

    public MemoriaController(String nome) {
        super(nome);
        this.memoria = looca.getMemoria();
        this.memoriaDAO = new MemoriaDAO();
    }

    public boolean verificarMemoria() {
        return memoriaDAO.verificarComponente();
    }

    public void metricaMemoria() {
        Scanner leitor = new Scanner(System.in);

        System.out.println("""
                +-------------------------------------------------+
                |      Defina a métrica para esse componente      |
                +-------------------------------------------------+
                 """);

        super.setMetrica(leitor.nextInt());

        memoriaDAO.adicionarComponente(super.getMetrica());

    }

    public void removerMemoria() {
        memoriaDAO.removerComponente();
    }

    public void monitorarMemoria() {
        double memoriaUsage = memoria.getEmUso();

        String ramFormatted = new DecimalFormat("#.##").format((memoriaUsage * 4) / 1024.0 / 1024.0 / 1024.0).replace(",", ".");
        String dataAtual = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        // mudar tipo pra double, não String - todo
//        memoriaDAO.iniciarMonitoramento(ramFormatted, dataAtual);
    }
}
