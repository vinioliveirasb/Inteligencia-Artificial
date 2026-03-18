package MissionariosCanibais;

import busca.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        // estado inicial: 3 missionários, 3 canibais e barco na esquerda
        Estado inicial = new MissionariosCanibais(3,3,0);

        // usa o algoritmo de busca em largura da biblioteca
        BuscaLargura busca = new BuscaLargura();

        Nodo solucao = busca.busca(inicial);

        if (solucao != null) {

            // lista que vai armazenar os estados da solução
            List<Estado> caminho = new ArrayList<>();

            Nodo atual = solucao;

            // percorre os nós da solução voltando até o estado inicial
            while (atual != null) {
                caminho.add(atual.getEstado());
                atual = atual.getPai();
            }

            // inverte a ordem para mostrar do início até o objetivo
            Collections.reverse(caminho);

            // imprime cada estado da solução
            for (Estado e : caminho) {
                System.out.println(e);
            }

        } else {
            System.out.println("Sem solução");
        }
    }
}
