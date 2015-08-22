/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package custom;

import controller.HistoryController;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author rifat
 */
public class History {
    
    public void writeText(String Catagorys,String nameOrId, String userName){
        System.out.println("Hited");
        try {
            String content = "Update By    :" + userName + "\nUpdate Date\t:" + LocalDate.now().toString() + "\n------------------------";

            File file = new File("logs/"+ Catagorys+"_" + nameOrId+"_" +"updateinfo.txt");
            if (!file.exists()) {
                file.getParentFile().mkdir();
            }
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.newLine();
            bw.write(content);

            bw.close();

            System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void viewText(String Catagoryes,String nameOrId, String userName){
        try {
            HistoryController hc = new HistoryController();
            StringBuilder sb = new StringBuilder();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/history.fxml"));
            loader.load();
            Parent root = loader.getRoot();
            Stage s1 = new Stage();
            HistoryController historyController = loader.getController();
            
        BufferedReader in;

        try {
            in = new BufferedReader(new FileReader("logs/"+ Catagoryes +nameOrId + "UpdateDetails.text"));
            String str;

            while ((str = in.readLine()) != null) {
                sb.append(str).append("\n");

            }
        } catch (FileNotFoundException ex) {
            System.out.println("File Not found");
//            Logger.getLogger(ViewEmployeController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            historyController.tfHistory.appendText(sb.toString());
            Scene s2 = new Scene(root);
            s1.setScene(s2);
            s1.setTitle("History");
            s1.setResizable(false);
            s1.show();
        } catch (IOException ex) {
            Logger.getLogger(History.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
