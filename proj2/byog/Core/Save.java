package byog.Core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Description: XXX.
 * Author: Corn Liu
 * Email: cornliu@zju.edu.cn
 * Date: 2022/8/13 11:03
 */
public class Save {
    /**
     * Load a doc
     * @return the Game
     */
    public static SaveHelper loadMap() {
        File f = new File("map.ser");
        if (f.exists()) {
            try {
                FileInputStream fs = new FileInputStream(f);
                ObjectInputStream os = new ObjectInputStream(fs);
                SaveHelper loadMap = (SaveHelper) os.readObject();
                os.close();
                return loadMap;
            } catch (FileNotFoundException e) {
                System.out.println("file not found");
                System.exit(0);
            } catch (IOException e) {
                System.out.println(e);
                System.exit(0);
            } catch (ClassNotFoundException e) {
                System.out.println("class not found");
                System.exit(0);
            }
        }

        /* In the case no World has been saved yet, we return null. */
        return null;
    }

    /**
     * Save the map.
     * @param g is the Game.
     */
    public static void saveMap(SaveHelper g) {
        File f = new File("map.ser");
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            FileOutputStream fs = new FileOutputStream(f);
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(g);
            os.close();
        }  catch (FileNotFoundException e) {
            System.out.println("file not found");
            System.exit(0);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(0);
        }
    }
}
