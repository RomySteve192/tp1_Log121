package simulation;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import material.*;

/**
 *
 * @author Romy Steve
 */
public class MenuFenetre extends JMenuBar {

    private static final long serialVersionUID = 1L;
    private static final String MENU_FICHIER_TITRE = "Fichier";
    private static final String MENU_FICHIER_CHARGER = "Charger";
    private static final String MENU_FICHIER_QUITTER = "Quitter";
    private static final String MENU_SIMULATION_TITRE = "Simulation";
    private static final String MENU_SIMULATION_CHOISIR = "Choisir";
    private static final String MENU_AIDE_TITRE = "Aide";
    private static final String MENU_AIDE_PROPOS = "� propos de...";

    private static ArrayList<Usine> usines;
    private static ArrayList<Chemin> chemins;

    /**
     *
     */
    public MenuFenetre() {
        ajouterMenuFichier();
        ajouterMenuSimulation();
        ajouterMenuAide();
    }

    /**
     * Cr�er le menu de Fichier
     */
    private void ajouterMenuFichier() {
        JMenu menuFichier = new JMenu(MENU_FICHIER_TITRE);
        JMenuItem menuCharger = new JMenuItem(MENU_FICHIER_CHARGER);
        JMenuItem menuQuitter = new JMenuItem(MENU_FICHIER_QUITTER);

        menuCharger.addActionListener((ActionEvent e) -> {
            JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            fileChooser.setDialogTitle("S�lectionnez un fichier de configuration");
            fileChooser.setAcceptAllFileFilterUsed(false);
            // Cr�er un filtre
            FileNameExtensionFilter filtre = new FileNameExtensionFilter(".xml", "xml");
            fileChooser.addChoosableFileFilter(filtre);

            int returnValue = fileChooser.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                // TODO - Parser le fichier XML s�lectionn�
                File selectedFile = fileChooser.getSelectedFile();
                System.out.println(selectedFile.getAbsolutePath());
                try {
                    XmlReader file = new XmlReader(selectedFile.getAbsolutePath());
                    this.usines = file.getListUsineReseau();
                    this.chemins = file.getListCheminReseau();
                } catch (Exception ex) {

                }
            }
        });

        menuQuitter.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });

        menuFichier.add(menuCharger);
        menuFichier.add(menuQuitter);

        add(menuFichier);

    }

    /**
     * Cr�er le menu de Simulation
     */
    private void ajouterMenuSimulation() {
        JMenu menuSimulation = new JMenu(MENU_SIMULATION_TITRE);
        JMenuItem menuChoisir = new JMenuItem(MENU_SIMULATION_CHOISIR);
        menuSimulation.add(menuChoisir);

        menuChoisir.addActionListener((ActionEvent e) -> {
            // Ouvrir la fen�tre de s�lection
            // TODO - R�cup�rer la bonne strat�gie de vente
            new FenetreStrategie();
        });
        add(menuSimulation);

    }

    /**
     * Cr�er le menu Aide
     */
    private void ajouterMenuAide() {
        JMenu menuAide = new JMenu(MENU_AIDE_TITRE);
        JMenuItem menuPropos = new JMenuItem(MENU_AIDE_PROPOS);
        menuAide.add(menuPropos);

        menuPropos.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(null,
                    "<html><p>Application simulant une chaine de production d'avions.</p>" + "<br>"
                    + "<p>&copy; &nbsp; 2017 &nbsp; Ghizlane El Boussaidi</p>"
                    + "<p>&copy; &nbsp; 2017 &nbsp; Dany Boisvert</p>"
                    + "<p>&copy; &nbsp; 2017 &nbsp; Vincent Mattard</p>" + "<br>"
                    + "<p>&Eacute;cole de technologie sup&eacute;rieure</p></html>");
        });
        add(menuAide);
    }
    
    /**
     * retourne la liste des usines du reseau
     * ArrayList<Usine>
     * @return 
     */
    public ArrayList<Usine> getarrListUsine() {
        return this.usines;
    }

    /**
     * retourne la liste des chemins du reseau
     * @return ArrayList<Chemin>
     */
    public ArrayList<Chemin> getarrListChemin() {
        return this.chemins;
    }

}
