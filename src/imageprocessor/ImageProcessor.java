
package imageprocessor;

import java.rmi.RemoteException;
import views.MainView;


public class ImageProcessor {

    public static void main(String[] args) throws RemoteException {
        MainView mainView = new MainView();
        mainView.setTitle("Image-Binarizer");
        mainView.setVisible(true);
    }
    
}
