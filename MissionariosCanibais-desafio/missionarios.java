import busca.*;
import java.util.*;

public class Principal {

    public static void main(String[] args) throws Exception {

        Estado inicial = new MissionariosCanibais(3,3,0);

        BuscaLargura busca = new BuscaLargura();

        Nodo solucao = busca.busca(inicial);

        if (solucao != null) {

            List<Estado> caminho = new ArrayList<>();

            Nodo atual = solucao;

            while (atual != null) {
                caminho.add(atual.getEstado());
                atual = atual.getPai();
            }

            Collections.reverse(caminho);

            for (Estado e : caminho) {
                System.out.println(e);
            }

        } else {
            System.out.println("Sem solução");
        }
    }
}
