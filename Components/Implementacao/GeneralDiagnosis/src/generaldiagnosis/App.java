/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package generaldiagnosis;

import data.DataSetComponent;
import interfaces.IDataSet;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author ra222142
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        IGeneralDiagnosis gd = new GeneralDiagnosisComponent();

        IDataSet dataset = new DataSetComponent();

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            JFileChooser file = new JFileChooser();
            file.setFileSelectionMode(JFileChooser.FILES_ONLY);
            FileFilter csv = new FileNameExtensionFilter("CSV", "csv");
            file.addChoosableFileFilter(csv);
            file.setFileFilter(csv);
            file.setAcceptAllFileFilterUsed(false);
            file.showOpenDialog(frame);
            File f = file.getSelectedFile();

            if (f != null) {
                dataset.setDataSource(f.getAbsolutePath());

                System.out.println("OCORRENCIAS mat");
                String ola[][] = gd.occurrence(dataset.requestInstances());
                for (String[] ola1 : ola) {
                    for (String ola2 : ola1)
                        System.out.print(ola2 + " | ");
                    System.out.println();
                }

                System.out.println("\nPORCENTAGENS mat");
                ola = gd.percentage(dataset.requestInstances());
                for (String[] ola1 : ola) {
                    for (String ola2 : ola1)
                        System.out.print(ola2 + " | ");
                    System.out.println();
                }
                
                

                System.out.println("\nOCORRENCIAS interna");
                ola = gd.occurrence();
                for (int i = 0; i < gd.occurrence().length; i++) {
                    System.out.println( gd.occurrence()[i][0] + " " + gd.occurrence()[i][1] );
                }
                
                
                System.out.println("\nPERCENTAGE interna");
                ola = gd.percentage();
                for (String[] ola1 : ola) {
                    for (String ola2 : ola1)
                        System.out.print(ola2 + " | ");
                    System.out.println();
                }

            } else {
                JOptionPane.showMessageDialog(frame, "Não selecionou nenhum arquivo");
            }         
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        } finally {    
            frame.dispose();
        }
        
    }
    
}
