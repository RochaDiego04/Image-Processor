
package classes;

import java.io.File;

public class FileDeleter {
    private static boolean isImageFile(File file) {
        String fileName = file.getName().toLowerCase();
        return fileName.endsWith(".jpg") || fileName.endsWith(".png") || fileName.endsWith(".gif") || fileName.endsWith(".bmp") || fileName.endsWith(".jpeg");
    }

    public static void deleteAllFilesInFolder(String folderPath) {
        File directory = new File(folderPath);

        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && isImageFile(file)) {
                        if (file.delete()) {
                            // We could add a success message here.
                        } else {
                            System.err.println("Failed to delete: " + file.getName() + " from " + folderPath);
                        }
                    }
                }
            }
        }
    }
}

