package teste;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Classe que representa um sistema simples de gerenciamento de biblioteca,
 * permitindo cadastrar, emprestar e devolver livros, além de gerenciar
 * uma fila de espera global para livros indisponíveis.
 */
public class livro {

    // Lista estática que representa o catálogo de livros disponíveis na biblioteca
    private static ArrayList<livro> catalogoLivros = new ArrayList<>();

    // Fila de espera compartilhada entre todos os livros (estrutura simplificada)
    private static Queue<String> filaEspera = new LinkedList<>();

    // Atributos de instância do livro
    private String titulo;
    private String autor;
    private boolean disponivel;

    /**
     * Construtor da classe livro.
     * @param titulo Título do livro.
     * @param autor Autor do livro.
     */
    public livro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
        this.disponivel = true; // Todo livro novo é inicialmente disponível
    }

    // Getters

    /**
     * Retorna o título do livro.
     * @return Título do livro.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Retorna o autor do livro.
     * @return Autor do livro.
     */
    public String getAutor() {
        return autor;
    }

    /**
     * Verifica se o livro está disponível para empréstimo.
     * @return true se disponível, false caso contrário.
     */
    public boolean isDisponivel() {
        return disponivel;
    }

    // Setter

    /**
     * Altera o status de disponibilidade do livro.
     * @param disponivel Novo status de disponibilidade.
     */
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    // Métodos estáticos

    /**
     * Cadastra alguns livros de exemplo no catálogo.
     */
    public static void cadastrarLivros() {
        catalogoLivros.add(new livro("O Pequeno Príncipe", "Antoine de Saint-Exupéry"));
        catalogoLivros.add(new livro("1984", "George Orwell"));
        catalogoLivros.add(new livro("Dom Casmurro", "Machado de Assis"));
        catalogoLivros.add(new livro("Sapiens: Uma Breve História da Humanidade", "Yuval Noah Harari"));
        catalogoLivros.add(new livro("A Revolução dos Bichos", "George Orwell"));
    }

    /**
     * Exibe todos os livros atualmente disponíveis para empréstimo.
     */
    public static void mostrarLivrosDisponiveis() {
        System.out.println("\n--- Livros Disponíveis ---");
        boolean encontrado = false;

        for (livro livro : catalogoLivros) {
            if (livro.isDisponivel()) {
                System.out.println("Título: " + livro.getTitulo() + " | Autor: " + livro.getAutor());
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("Nenhum livro disponível no momento.");
        }
        System.out.println("--------------------------");
    }

    /**
     * Realiza o empréstimo de um livro, se disponível. Caso contrário,
     * adiciona o usuário à fila de espera.
     *
     * @param titulo Título do livro a ser emprestado.
     * @param usuario Nome do usuário solicitando o empréstimo.
     */
    public static void emprestarLivro(String titulo, String usuario) {
        livro encontrado = buscarLivroPorTitulo(titulo);

        if (encontrado != null) {
            if (encontrado.isDisponivel()) {
                encontrado.setDisponivel(false);
                System.out.println("\nSucesso! Livro '" + encontrado.getTitulo() + "' emprestado para " + usuario + ".");
            } else {
                filaEspera.add(usuario);
                System.out.println("\nLivro indisponível. " + usuario + " foi adicionado à fila de espera.");
            }
        } else {
            System.out.println("\nLivro com o título '" + titulo + "' não encontrado.");
        }
    }

    /**
     * Realiza a devolução de um livro e, caso haja usuários na fila de espera,
     * o livro é automaticamente emprestado ao próximo da fila.
     *
     * @param titulo Título do livro a ser devolvido.
     */
    public static void devolverLivro(String titulo) {
        livro encontrado = buscarLivroPorTitulo(titulo);

        if (encontrado != null) {
            if (!encontrado.isDisponivel()) {
                encontrado.setDisponivel(true);
                System.out.println("\nLivro '" + encontrado.getTitulo() + "' devolvido com sucesso.");

                if (!filaEspera.isEmpty()) {
                    String proximoUsuario = filaEspera.poll();
                    encontrado.setDisponivel(false);
                    System.out.println("Livro '" + encontrado.getTitulo() + "' automaticamente emprestado para " + proximoUsuario + ".");
                }
            } else {
                System.out.println("\nEste livro já está disponível. Não é necessário devolução.");
            }
        } else {
            System.out.println("\nLivro com o título '" + titulo + "' não encontrado.");
        }
    }

    /**
     * Busca um livro no catálogo com base no título informado.
     *
     * @param titulo Título do livro.
     * @return Objeto livro encontrado, ou null se não existir.
     */
    private static livro buscarLivroPorTitulo(String titulo) {
        for (livro livro : catalogoLivros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                return livro;
            }
        }
        return null;
    }
}
