package teste;

import java.util.Scanner;

/**
 * Classe principal que simula a interação do usuário com o sistema de biblioteca.
 * Permite visualizar livros disponíveis, emprestar e devolver livros por meio de um menu simples no console.
 */
public class SimuladorBiblioteca {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Scanner para leitura de entradas do usuário

        // Inicialização do catálogo de livros com alguns títulos pré-definidos
        livro.cadastrarLivros();

        int escolha;

        // Loop do menu principal
        do {
            // Exibe o menu de opções ao usuário
            System.out.println("\n--- Menu da Biblioteca ---");
            System.out.println("1. Mostrar Livros Disponíveis");
            System.out.println("2. Emprestar Livro");
            System.out.println("3. Devolver Livro");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            
            escolha = scanner.nextInt(); // Lê a escolha do usuário (número)
            scanner.nextLine(); // Limpa o buffer do scanner para evitar problemas com o próximo nextLine()

            // Executa a ação com base na escolha do usuário
            switch (escolha) {
                case 1:
                    // Mostra todos os livros disponíveis no catálogo
                    livro.mostrarLivrosDisponiveis();
                    break;

                case 2:
                    // Solicita o título do livro e o nome do usuário, e tenta realizar o empréstimo
                    System.out.print("Digite o título do livro: ");
                    String tituloEmprestar = scanner.nextLine();

                    System.out.print("Digite seu nome: ");
                    String nomeUsuario = scanner.nextLine();

                    livro.emprestarLivro(tituloEmprestar, nomeUsuario);
                    break;

                case 3:
                    // Solicita o título do livro a ser devolvido
                    System.out.print("Digite o título do livro: ");
                    String tituloDevolver = scanner.nextLine();

                    livro.devolverLivro(tituloDevolver);
                    break;

                case 0:
                    // Encerra o programa
                    System.out.println("Saindo da biblioteca. Até mais!");
                    break;

                default:
                    // Informa o usuário em caso de entrada inválida
                    System.out.println("Opção inválida.");
            }

        } while (escolha != 0); // Continua exibindo o menu até que o usuário escolha sair

        scanner.close(); // Fecha o scanner após o uso
    }
}
