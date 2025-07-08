package teste;

import java.util.Scanner;

public class SimuladorBiblioteca {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Cadastrar livros no início
        livro.cadastrarLivros();

        int escolha;
        do {
            System.out.println("\n--- Menu da Biblioteca ---");
            System.out.println("1. Mostrar Livros Disponíveis");
            System.out.println("2. Emprestar Livro");
            System.out.println("3. Devolver Livro");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            escolha = scanner.nextInt();
            scanner.nextLine(); // Consumir \n

            switch (escolha) {
                case 1:
                    livro.mostrarLivrosDisponiveis();
                    break;
                case 2:
                    System.out.print("Digite o título do livro: ");
                    String tituloEmprestar = scanner.nextLine();
                    System.out.print("Digite seu nome: ");
                    String nomeUsuario = scanner.nextLine();
                    livro.emprestarLivro(tituloEmprestar, nomeUsuario);
                    break;
                case 3:
                    System.out.print("Digite o título do livro: ");
                    String tituloDevolver = scanner.nextLine();
                    livro.devolverLivro(tituloDevolver);
                    break;
                case 0:
                    System.out.println("Saindo da biblioteca. Até mais!");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (escolha != 0);

        scanner.close();
    }
}
