import java.io.*;
import java.net.*;
//                                                                  Hareessh P      21BCE1630
public class TCPServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(1630)) {
            System.out.println("Server started. Waiting for client...");

            try (Socket clientSocket = serverSocket.accept();
                 BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)) {

                String fileName = reader.readLine();
                System.out.println("Received file name: " + fileName);

                File file = new File(fileName);
                if (file.exists() && file.isFile()) {
                    String content = readFileContent(file);
                    int wordCount = countWords(content);
                    int charCount = countCharacters(content);

                    String result = "HI Ma'am this is HAREESSH P \t 21BCE1630\n\n\nWord count: " + wordCount + ", Character count: " + charCount;
                    writer.println(result);
                } else {
                    writer.println("File not found.");
                }
            }
            System.out.println("File processed and result sent to client.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String readFileContent(File file) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader fileReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = fileReader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }

    private static int countWords(String content) {
        String[] words = content.trim().split("\\s+");
        return words.length;
    }

    private static int countCharacters(String content) {
        return content.length();
    }
}