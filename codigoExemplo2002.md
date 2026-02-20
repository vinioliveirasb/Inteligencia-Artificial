## 💻 Modelagem Completa — Problema das N-Rainhas (N x N) - Feito em AULA 20/02

Abaixo está a implementação baseada nos passos de modelagem:

- Estados (inicial, intermediário e final)
- Regras de transição
- Restrições (validação buscando erro)
- Função objetivo (meta)

```java
public class Rainha {

    // 🔹 Estado do problema
    // Matriz N x N:
    // 0 = posição vazia
    // 1 = existe rainha na posição
    int[][] matriz;

    // Quantidade de rainhas atualmente no tabuleiro
    int qtdRainhas;

    // =====================================================
    // 🔹 1) CONSTRUTOR INICIAL → Estado Inicial
    // =====================================================
    // Cria um tabuleiro vazio (todas posições = 0)
    // Representa o início do problema
    public Rainha(int tamanhoTabuleiro) {

        this.matriz = new int[tamanhoTabuleiro][tamanhoTabuleiro];
        this.qtdRainhas = 0;

        // Inicializa todas as posições com 0
        for (int i = 0; i < tamanhoTabuleiro; i++) {
            for (int j = 0; j < tamanhoTabuleiro; j++) {
                matriz[i][j] = 0;
            }
        }
    }

    // =====================================================
    // 🔹 2) CONSTRUTOR INTERMEDIÁRIO → Novo Estado
    // =====================================================
    // Cria um novo estado baseado em um estado anterior
    // Usado durante a busca (gera ramificações)
    public Rainha(Rainha anterior) {

        int n = anterior.matriz.length;
        this.matriz = new int[n][n];

        // Copia o estado anterior
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.matriz[i][j] = anterior.matriz[i][j];
            }
        }

        this.qtdRainhas = anterior.qtdRainhas;
    }

    // =====================================================
    // 🔹 3) REGRA DE TRANSIÇÃO → Operação do Problema
    // =====================================================
    // Tenta colocar uma rainha em uma posição
    // Gera um novo possível estado
    public boolean colocarRainha(int linha, int coluna) {

        // Se já existe rainha ali, estado inválido
        if (matriz[linha][coluna] == 1)
            return false;

        // Coloca rainha
        matriz[linha][coluna] = 1;
        qtdRainhas++;

        // Valida restrições
        if (!ehValido(linha, coluna)) {

            // Se inválido, desfaz (buscando o erro)
            matriz[linha][coluna] = 0;
            qtdRainhas--;

            return false;
        }

        return true;
    }

    // =====================================================
    // 🔹 4) RESTRIÇÕES → Método ehValido()
    // =====================================================
    // Procura erro no estado:
    // - mesma linha
    // - mesma coluna
    // - mesma diagonal
    private boolean ehValido(int linha, int coluna) {

        int n = matriz.length;

        // Verifica coluna
        for (int i = 0; i < n; i++) {
            if (i != linha && matriz[i][coluna] == 1)
                return false;
        }

        // Verifica linha
        for (int j = 0; j < n; j++) {
            if (j != coluna && matriz[linha][j] == 1)
                return false;
        }

        // Verifica diagonais
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ((i != linha || j != coluna) &&
                    Math.abs(i - linha) == Math.abs(j - coluna) &&
                    matriz[i][j] == 1)
                    return false;
            }
        }

        return true;
    }

    // =====================================================
    // 🔹 5) ESTADO FINAL → Função Objetivo
    // =====================================================
    // O problema está resolvido quando
    // o número de rainhas for igual ao tamanho do tabuleiro
    public boolean ehObjetivo() {
        return qtdRainhas == matriz.length;
    }
}
```

---

# 📌 O que cada parte representa na modelagem

- `matriz` → Estado do problema  
- Construtor inicial → Estado inicial  
- Construtor cópia → Estado intermediário  
- `colocarRainha()` → Regra de transição  
- `ehValido()` → Tratamento de restrições (buscando erro)  
- `ehObjetivo()` → Estado final / função meta  

---

# 🧠 Resumo Conceitual

Problema das N-Rainhas modelado como:

Estado  
+ Regra de Transição  
+ Restrições  
+ Função Objetivo  
= Estrutura pronta para aplicar métodos de busca (DFS, BFS, heurísticos)

A busca irá gerar estados intermediários, validar restrições e parar quando atingir o estado objetivo.
