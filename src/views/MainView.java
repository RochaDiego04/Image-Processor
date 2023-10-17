package views;

import classes.FileDeleter;
import classes.Image_Concurrent;
import classes.Image_Sequential;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DnDConstants;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;



/**
 *
 * @author Diego
 */
public class MainView extends javax.swing.JFrame {

    /**
     * Creates new form MainView
     */
    public MainView() {
        initComponents();
    //////////////////////////////////////////////////////////////    
    // Associate a DropTarget to jLabel1
    DropTarget dropTarget = new DropTarget(jLabel1, DnDConstants.ACTION_COPY, new DropTargetAdapter() {
    @Override
    public void drop(DropTargetDropEvent event) {
        event.acceptDrop(DnDConstants.ACTION_COPY);
        Transferable transferable = event.getTransferable();
        try {
            List<File> files = (List<File>) transferable.getTransferData(DataFlavor.javaFileListFlavor);
            for (File file : files) {
                // Verify if file is an image before copying it
                if (isImageFile(file)) {
                    // Define destination folder (images.output_images)
                    File destinationFolder = new File("src/images/input_images");
                    destinationFolder.mkdirs();

                    // Copy the file into the destination folder
                    Path sourcePath = file.toPath();
                    Path destinationPath = new File(destinationFolder, file.getName()).toPath();
                    Files.copy(sourcePath, destinationPath);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    });
    jLabel1.setDropTarget(dropTarget);
    }
    //////////////////////////////////////////////////////////////   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl_background = new javax.swing.JPanel();
        pnl_header = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lbl_title = new javax.swing.JLabel();
        btn_clear_input = new javax.swing.JButton();
        btn_clear_output = new javax.swing.JButton();
        lbl_concurrent = new javax.swing.JLabel();
        lbl_sequential = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtArea_sequential = new javax.swing.JTextArea();
        btn_startConcurrent = new javax.swing.JButton();
        btn_startSequential = new javax.swing.JButton();
        lbl_sequentialTime = new javax.swing.JLabel();
        lbl_concurrentTime = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtArea_concurrent = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setResizable(false);

        pnl_background.setBackground(new java.awt.Color(39, 42, 55));
        pnl_background.setPreferredSize(new java.awt.Dimension(800, 900));
        pnl_background.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnl_header.setBackground(new java.awt.Color(29, 31, 41));
        pnl_header.setPreferredSize(new java.awt.Dimension(616, 900));
        pnl_header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(102, 255, 255));
        jLabel1.setFont(new java.awt.Font("Microsoft New Tai Lue", 0, 22)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(203, 197, 197));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Drop your image files here");
        jLabel1.setToolTipText("");
        jLabel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(102, 153, 255), null, new java.awt.Color(115, 117, 146)));
        pnl_header.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 90, 480, 90));

        lbl_title.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 36)); // NOI18N
        lbl_title.setForeground(new java.awt.Color(255, 255, 255));
        lbl_title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_title.setText("Image Binarizer");
        pnl_header.add(lbl_title, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, 470, 60));

        btn_clear_input.setBackground(new java.awt.Color(45, 45, 82));
        btn_clear_input.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_clear_input.setForeground(new java.awt.Color(255, 255, 255));
        btn_clear_input.setText("Clear Input Img");
        btn_clear_input.setToolTipText("");
        btn_clear_input.setAlignmentX(0.5F);
        btn_clear_input.setBorderPainted(false);
        btn_clear_input.setFocusPainted(false);
        btn_clear_input.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_clear_inputMouseEntered(evt);
            }
        });
        btn_clear_input.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clear_inputActionPerformed(evt);
            }
        });
        pnl_header.add(btn_clear_input, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 90, 130, 40));

        btn_clear_output.setBackground(new java.awt.Color(60, 44, 82));
        btn_clear_output.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_clear_output.setForeground(new java.awt.Color(255, 255, 255));
        btn_clear_output.setText("Clear Output Img");
        btn_clear_output.setToolTipText("");
        btn_clear_output.setAlignmentX(0.5F);
        btn_clear_output.setBorderPainted(false);
        btn_clear_output.setFocusPainted(false);
        btn_clear_output.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_clear_outputMouseEntered(evt);
            }
        });
        btn_clear_output.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clear_outputActionPerformed(evt);
            }
        });
        pnl_header.add(btn_clear_output, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 140, 130, 40));

        pnl_background.add(pnl_header, new org.netbeans.lib.awtextra.AbsoluteConstraints(-4, -6, 910, 210));

        lbl_concurrent.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        lbl_concurrent.setForeground(new java.awt.Color(218, 218, 218));
        lbl_concurrent.setText("Concurrent");
        pnl_background.add(lbl_concurrent, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 260, -1, -1));

        lbl_sequential.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        lbl_sequential.setForeground(new java.awt.Color(218, 218, 218));
        lbl_sequential.setText("Sequential");
        pnl_background.add(lbl_sequential, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 260, -1, -1));

        jScrollPane5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        txtArea_sequential.setEditable(false);
        txtArea_sequential.setBackground(new java.awt.Color(11, 12, 16));
        txtArea_sequential.setColumns(20);
        txtArea_sequential.setForeground(new java.awt.Color(255, 255, 255));
        txtArea_sequential.setRows(5);
        txtArea_sequential.setPreferredSize(new java.awt.Dimension(220, 66));
        jScrollPane5.setViewportView(txtArea_sequential);

        pnl_background.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 540, 390, 440));

        btn_startConcurrent.setBackground(new java.awt.Color(71, 104, 104));
        btn_startConcurrent.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btn_startConcurrent.setForeground(new java.awt.Color(255, 255, 255));
        btn_startConcurrent.setText("Start");
        btn_startConcurrent.setToolTipText("");
        btn_startConcurrent.setAlignmentX(0.5F);
        btn_startConcurrent.setBorderPainted(false);
        btn_startConcurrent.setFocusPainted(false);
        btn_startConcurrent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_startConcurrentMouseEntered(evt);
            }
        });
        btn_startConcurrent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_startConcurrentActionPerformed(evt);
            }
        });
        pnl_background.add(btn_startConcurrent, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 300, 160, 60));

        btn_startSequential.setBackground(new java.awt.Color(71, 104, 104));
        btn_startSequential.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btn_startSequential.setForeground(new java.awt.Color(255, 255, 255));
        btn_startSequential.setText("Start");
        btn_startSequential.setToolTipText("");
        btn_startSequential.setAlignmentX(0.5F);
        btn_startSequential.setBorderPainted(false);
        btn_startSequential.setFocusPainted(false);
        btn_startSequential.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_startSequentialMouseEntered(evt);
            }
        });
        btn_startSequential.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_startSequentialActionPerformed(evt);
            }
        });
        pnl_background.add(btn_startSequential, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 300, 160, 60));

        lbl_sequentialTime.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        lbl_sequentialTime.setForeground(new java.awt.Color(218, 218, 218));
        lbl_sequentialTime.setText("Time:");
        pnl_background.add(lbl_sequentialTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 420, -1, 40));

        lbl_concurrentTime.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        lbl_concurrentTime.setForeground(new java.awt.Color(218, 218, 218));
        lbl_concurrentTime.setText("Time:");
        pnl_background.add(lbl_concurrentTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 420, -1, 40));

        jScrollPane6.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        txtArea_concurrent.setEditable(false);
        txtArea_concurrent.setBackground(new java.awt.Color(11, 12, 16));
        txtArea_concurrent.setColumns(20);
        txtArea_concurrent.setForeground(new java.awt.Color(255, 255, 255));
        txtArea_concurrent.setRows(5);
        txtArea_concurrent.setPreferredSize(new java.awt.Dimension(220, 66));
        jScrollPane6.setViewportView(txtArea_concurrent);

        pnl_background.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 540, 390, 440));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl_background, javax.swing.GroupLayout.PREFERRED_SIZE, 899, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl_background, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1080, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_startConcurrentMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_startConcurrentMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_startConcurrentMouseEntered

    private void btn_startSequentialMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_startSequentialMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_startSequentialMouseEntered

    private void btn_clear_inputMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_clear_inputMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_clear_inputMouseEntered

    private void btn_clear_inputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clear_inputActionPerformed
        FileDeleter fileDeleter = new FileDeleter();
        String folderPath = "src/images/input_images/";
        fileDeleter.deleteAllFilesInFolder(folderPath);
    }//GEN-LAST:event_btn_clear_inputActionPerformed

    private void btn_startConcurrentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_startConcurrentActionPerformed
        String inputPath = "src/images/input_images/";
        String outputPath = "src/images/output_images/";

        File inputDirectory = new File(inputPath);
        File outputDirectory = new File(outputPath);

        // Ensure that the output directory exists
        if (!outputDirectory.exists()) {
            outputDirectory.mkdirs();
        }

        File[] imageFiles = inputDirectory.listFiles();

        if (imageFiles != null) {
            int numThreads = Runtime.getRuntime().availableProcessors(); // Get the number of available cores

            ExecutorService executor = Executors.newFixedThreadPool(numThreads);

            long startTime = System.currentTimeMillis();

            for (File imageFile : imageFiles) {
                if (imageFile.isFile()) {
                    String inputImage = imageFile.getAbsolutePath();
                    String outputImage = outputPath + imageFile.getName();

                    executor.submit(() -> {
                        Image_Concurrent obj = new Image_Concurrent(inputImage);
                        obj.binarizeImage(100);
                        BufferedImage img = obj.printImage();
                        try {
                            ImageIO.write(img, "jpg", new File(outputImage));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        // Get the current thread's name or identifier
                        String threadName = Thread.currentThread().getName();
                        System.out.println("Processed: " + imageFile.getName() + " by thread " + threadName);
                    });

                }
            }

            executor.shutdown();
            try {
                executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            long concurrentTime = System.currentTimeMillis() - startTime;
            System.out.println("Concurrent time " + concurrentTime + "ms");
        }
    }//GEN-LAST:event_btn_startConcurrentActionPerformed

    private void btn_startSequentialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_startSequentialActionPerformed
        String inputPath = "src/images/input_images/";
        String outputPath = "src/images/output_images/";

        File inputDirectory = new File(inputPath);
        File outputDirectory = new File(outputPath);

        // Ensure that the output directory exists
        if (!outputDirectory.exists()) {
            outputDirectory.mkdirs();
        }
        
        long startTime = System.currentTimeMillis(); // Time the task(s)
        
        File[] imageFiles = inputDirectory.listFiles(); // get a list of files at input directory

        if (imageFiles != null) {
            for (File imageFile : imageFiles) {
                if (imageFile.isFile()) { // validation of image formats (.jpg, .png) is done when uploading files
                    String inputImage = imageFile.getAbsolutePath();
                    String outputImage = outputPath + imageFile.getName();

                    Image_Sequential obj = new Image_Sequential(inputImage);
                    obj.binarizeImage(100);
                    BufferedImage img = obj.printImage();
                    try {
                        ImageIO.write(img, "jpg", new File(outputImage));
                    } catch (IOException ex) {
                        Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    System.out.println("Processed: " + imageFile.getName());
                }
            }
        }
       
        long sequentialTime  = System.currentTimeMillis() - startTime; // Finisihing task timing
        System.out.println("Sequential time " + sequentialTime + "ms");
    }//GEN-LAST:event_btn_startSequentialActionPerformed

    private void btn_clear_outputMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_clear_outputMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_clear_outputMouseEntered

    private void btn_clear_outputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clear_outputActionPerformed
        FileDeleter fileDeleter = new FileDeleter();
        String folderPath = "src/images/output_images/";
        fileDeleter.deleteAllFilesInFolder(folderPath);
    }//GEN-LAST:event_btn_clear_outputActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainView().setVisible(true);
            }
        });
    }
    
    private boolean isImageFile(File file) {
        String fileName = file.getName().toLowerCase();
        return fileName.endsWith(".jpg") || fileName.endsWith(".png") || fileName.endsWith(".gif") || fileName.endsWith(".bmp") || fileName.endsWith(".jpeg");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btn_clear_input;
    public javax.swing.JButton btn_clear_output;
    public javax.swing.JButton btn_startConcurrent;
    public javax.swing.JButton btn_startSequential;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel lbl_concurrent;
    private javax.swing.JLabel lbl_concurrentTime;
    private javax.swing.JLabel lbl_sequential;
    private javax.swing.JLabel lbl_sequentialTime;
    private javax.swing.JLabel lbl_title;
    private javax.swing.JPanel pnl_background;
    private javax.swing.JPanel pnl_header;
    public javax.swing.JTextArea txtArea_concurrent;
    public javax.swing.JTextArea txtArea_sequential;
    // End of variables declaration//GEN-END:variables
}