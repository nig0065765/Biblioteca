package trabalho;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

	// Livro.java
	class Livro {
	    private String titulo;
	    private String autor;
	    private boolean disponivel;

	    public Livro(String titulo, String autor) {
	        this.titulo = titulo;
	        this.autor = autor;
	        this.disponivel = true; // Inicialmente disponível
	    }

	    // Getters
	    public String getTitulo() {
	        return titulo;
	    }

	    public String getAutor() {
	        return autor;
	    }

	    public boolean isDisponivel() {
	        return disponivel;
	    }

	    // Setter para disponivel
	    public void setDisponivel(boolean disponivel) {
	        this.disponivel = disponivel;
	    }
	}

	// SimuladorBiblioteca.java (Classe Principal)
	public class SimuladorBiblioteca {
	    private ArrayList<Livro> catalogoLivros;
	    private Queue<String> filaEspera; // Armazena nomes de usuários esperando

	    public SimuladorBiblioteca() {
	        catalogoLivros = new ArrayList<>();
	        filaEspera = new LinkedList<>(); // LinkedList implementa Queue
	        cadastrarLivros();
	    }

	    // Encapsula o registro inicial de livros
	    private void cadastrarLivros() {
	        catalogoLivros.add(new Livro("O Pequeno Príncipe", "Antoine de Saint-Exupéry"));
	        catalogoLivros.add(new Livro("1984", "George Orwell"));
	        catalogoLivros.add(new Livro("Dom Casmurro", "Machado de Assis"));
	        catalogoLivros.add(new Livro("Sapiens: Uma Breve História da Humanidade", "Yuval Noah Harari"));
	        catalogoLivros.add(new Livro("A Revolução dos Bichos", "George Orwell"));
	        // Você pode definir alguns como indisponíveis para fins de teste, se quiser
	        // catalogoLivros.get(0).setDisponivel(false);
	    }

	    public void mostrarLivrosDisponiveis() {
	        System.out.println("\n--- Livros Disponíveis ---");
	        boolean encontradoDisponivel = false;
	        for (Livro livro : catalogoLivros) {
	            if (livro.isDisponivel()) {
	                System.out.println("Título: " + livro.getTitulo() + " | Autor: " + livro.getAutor());
	                encontradoDisponivel = true;
	            }
	        }
	        if (!encontradoDisponivel) {
	            System.out.println("Nenhum livro disponível no momento.");
	        }
	        System.out.println("--------------------------");
	    }
	    
	    public void emprestarLivro(String tituloLivro, String nomeUsuario) {
	        Livro livroEncontrado = null;
	        for (Livro livro : catalogoLivros) {
	            if (livro.getTitulo().equalsIgnoreCase(tituloLivro)) {
	                livroEncontrado = livro;
	                break;
	            }
	        }

	        if (livroEncontrado != null) {
	            if (livroEncontrado.isDisponivel()) {
	                livroEncontrado.setDisponivel(false);
	                System.out.println("\nSucesso! Livro '" + livroEncontrado.getTitulo() + "' emprestado para " + nomeUsuario + ".");
	            } else {
	                filaEspera.add(nomeUsuario);
	                System.out.println("\nLivro '" + livroEncontrado.getTitulo() + "' não disponível no momento. " + nomeUsuario + " adicionado à fila de espera.");
	            }
	        } else {
	            System.out.println("\nLivro com o título '" + tituloLivro + "'não encontrado no catálogo.");
	        }
	        
	    public void devolverLivro(String tituloLivro) {
	        Livro livroEncontrado = null;
	        for (Livro livro : catalogoLivros) {
	            if (livro.getTitulo().equalsIgnoreCase(tituloLivro)) {
	                livroEncontrado = livro;
	                break;
	            }
	        }

	        if (livroEncontrado != null) {
	            if (!livroEncontrado.isDisponivel()) { // Apenas define como true se estava emprestado
	                livroEncontrado.setDisponivel(true);
	                System.out.println("\nLivro '" + livroEncontrado.getTitulo() + "devolvido com sucesso.");

	                if (!filaEspera.isEmpty()) {
	                    String proximoUsuario = filaEspera.poll(); // Remove da fila
	                    livroEncontrado.setDisponivel(false); // Empresta novamente
	                    System.out.println("Livro '" + livroEncontrado.getTitulo() + "' automaticamente emprestado para " + proximoUsuario + " da fila de espera.");
	                }
	            } else {
	                System.out.println("\nLivro '" + tituloLivro + "' já está disponível e não pode ser devolvido.");
	            }
	        } else {
	            System.out.println("\nLivro com o título '" + tituloLivro + "' não encontrado no catálogo.");
	    		}
	    			}
	    }
	    