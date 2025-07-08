package teste;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class livro {

    private static ArrayList<livro> catalogoLivros = new ArrayList<>();
    private static Queue<String> filaEspera = new LinkedList<>(); // fila única para todos os livros

    private String titulo;
    private String autor;
    private boolean disponivel;

    public livro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
        this.disponivel = true;
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

    // Setter
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    // Cadastrar livros no catálogo
    public static void cadastrarLivros() {
        catalogoLivros.add(new livro("O Pequeno Príncipe", "Antoine de Saint-Exupéry"));
        catalogoLivros.add(new livro("1984", "George Orwell"));
        catalogoLivros.add(new livro("Dom Casmurro", "Machado de Assis"));
        catalogoLivros.add(new livro("Sapiens: Uma Breve História da Humanidade", "Yuval Noah Harari"));
        catalogoLivros.add(new livro("A Revolução dos Bichos", "George Orwell"));
    }

    // Mostrar livros disponíveis
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

    // Emprestar livro
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

    // Devolver livro
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

    // Buscar livro
    private static livro buscarLivroPorTitulo(String titulo) {
        for (livro livro : catalogoLivros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                return livro;
            }
        }
        return null;
    }
}
