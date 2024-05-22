package app;

import java.io.BufferedReader;
import java.io.Console;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.util.regex.Pattern;

import entities.BinarySearchTree;

public class App {

    public static String removeAccentsAndSpecialCharacters(String word) {
        String normalized = Normalizer.normalize(word, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        String cleaned = pattern.matcher(normalized).replaceAll("");
        cleaned = cleaned.replaceAll("[^\\p{IsAlphabetic}\\p{IsDigit}\\s]", "");
        return cleaned.toLowerCase();
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        
        Console console = System.console();
        if (console == null) {
            System.err.println("Console não está disponível.");
            return;
        }
        
        console.printf("Digite o caminho do arquivo .txt a ser lido: ");
        String filename = console.readLine();

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8))) {
            String line = br.readLine();
            while (line != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    word = removeAccentsAndSpecialCharacters(word);
                    if (!word.isEmpty()) {
                        bst.insert(word);
                    }
                }
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        System.out.println("Palavras em ordem alfabética:");
        bst.inorder();

        boolean sair = false;

        while (!sair) {
            console.printf("\nOpções:\n");
            console.printf("1. Adicionar palavra\n");
            console.printf("2. Pesquisar palavra\n");
            console.printf("3. Remover palavra\n");
            console.printf("4. Procurar por substring\n");
            console.printf("5. Mostrar palavras adicionadas\n");
            console.printf("6. Mostrar quantidade de palavras adicionadas\n");
            console.printf("7. Finalizar aplicação\n");
            console.printf("\nEscolha a opção desejada: ");

            String optionLine = console.readLine();
            int option;
            try {
                option = Integer.parseInt(optionLine);
            } catch (NumberFormatException e) {
                console.printf("Por favor, digite um número válido.\n");
                continue;
            }

            switch (option) {
                case 1:
                    console.printf("Digite a palavra que deseja adicionar: ");
                    String inputLine = console.readLine().trim();
                    String[] inputWords = removeAccentsAndSpecialCharacters(inputLine).split("\\s+");
                    for (String newWord : inputWords) {
                        if (!newWord.isEmpty()) {
                            bst.insert(newWord);
                            console.printf("Palavra '%s' adicionada com sucesso!\n", newWord);
                        }
                    }
                    break;
                case 2:
                    console.printf("Digite a palavra que deseja pesquisar: ");
                    String searchWord = console.readLine().trim().toLowerCase();
                    searchWord = removeAccentsAndSpecialCharacters(searchWord);
                    if (bst.search(searchWord)) {
                        console.printf("A palavra '%s' está presente.\n", searchWord);
                    } else {
                        console.printf("A palavra '%s' não está presente.\n", searchWord);
                    }
                    break;
                case 3:
                    console.printf("Digite a palavra que deseja remover: ");
                    String removeWord = console.readLine().trim().toLowerCase();
                    removeWord = removeAccentsAndSpecialCharacters(removeWord);
                    if (bst.search(removeWord)) {
                        console.printf("Palavra removida com sucesso!\n");
                        bst.remove(removeWord);
                    } else {
                        console.printf("A palavra '%s' não está presente.\n", removeWord);
                    }
                    break;
                case 4:
                    console.printf("Digite a substring que deseja procurar: ");
                    String substring = console.readLine().trim().toLowerCase();
                    substring = removeAccentsAndSpecialCharacters(substring);
                    console.printf("Palavras contendo a substring '%s':\n", substring);
                    bst.searchSubstringInorder(substring);
                    break;
                case 5:
                    console.printf("Palavras adicionadas:\n");
                    bst.inorder();
                    break;
                case 6:
                    int wordCount = bst.size();
                    console.printf("Quantidade de palavras adicionadas: %d\n", wordCount);
                    break;
                case 7:
                    console.printf("Finalizando a aplicação...\n");
                    sair = true;
                    break;
                default:
                    console.printf("Opção inválida. Por favor, escolha uma opção válida.\n");
                    break;
            }
        }
    }
}
