package trabalho;

import java.util.ArrayList;
import java.util.LinkedList; // Para Queue
import java.util.Queue;
import java.util.Scanner; // Para entrada do usuário (opcional, mas bom para testar)

public class SimuladorBiblioteca {
		
	    public static void main(String[] args) {
	    	
	        SimuladorBiblioteca biblioteca = new SimuladorBiblioteca();
	        Scanner scanner = new Scanner(System.in);

	        int escolha;
	        do {
	            System.out.println("\n--- Menu da Biblioteca ---");
	            System.out.println("1. Mostrar Livros Disponíveis");
	            System.out.println("2. Emprestar Livro");
	            System.out.println("3. Devolver Livro");
	            System.out.println("0. Sair");
	            System.out.print("Escolha uma opção: ");
	            escolha = scanner.nextInt();
	            scanner.nextLine(); // Consumir a quebra de linha

	            switch (escolha) {
	                case 1:
	                    biblioteca.mostrarLivrosDisponiveis();
	                    break;
	                case 2:
	                    System.out.print("Digite o título do livro: ");
	                    String tituloEmprestar = scanner.nextLine();
	                    System.out.print("Digite seu nome: ");
	                    String nomeUsuarioEmprestimo = scanner.nextLine();
	                    biblioteca.emprestarLivro(tituloEmprestar, nomeUsuarioEmprestimo);
	                    break;
	                case 3:
	                    System.out.print("Digite o título do livro que deseja devolver: ");
	                    String tituloDevolver = scanner.nextLine();
	                    biblioteca.devolverLivro(tituloDevolver);
	                    break;
	                case 0:
	                    System.out.println("Saindo do simulador. Até mais!");
	                    break;
	                default:
	                    System.out.println("Opção inválida. Tente novamente.");
	            }
	        } while (escolha != 0);

	        scanner.close();
	    }
	}