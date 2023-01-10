package proiect;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Utils {
    public static ArrayList<String> categories = new ArrayList<String>() {
        {
            add("Muzica");
            add("Arta");
            add("Parenting");
            add("Antreprenoriat");
            add("Dezvoltare personala");
            add("Dating");
            add("Petreceri");
            add("Natura");
            add("Sanatate");
            add("Psihologie");
            add("Sport");
            add("Tehnologie");
            add("Film");
        }
    };

    public static ArrayList<String> locations = new ArrayList<String>(){{    
        add("Alba");
        add("Arad");
        add("Arges");
        add("Bacau");
        add("Bihor");
        add("Bistrita-Nasaud");
        add("Botosani");
        add("Brasov");
        add("Braila");
        add("Bucuresti");
        add("Buzau");
        add("Caras-Severin");
        add("Calarasi");
        add("Cluj");
        add("Constanta");
        add("Covasna");
        add("Dambovita");
        add("Dolj");
        add("Galati");
        add("Giurgiu");
        add("Gorj");
        add("Harghita");
        add("Hunedoara");
        add("Ialomita");
        add("Iasi");
        add("Ilfov");
        add("Maramures");
        add("Mehedinti");
        add("Mures");
        add("Neamt");
        add("Olt");
        add("Prahova");
        add("Satu-Mare");
        add("Salaj");
        add("Sibiu");
        add("Suceava");
        add("Teleorman");
        add("Timis");
        add("Tulcea");
        add("Vaslui");
        add("Valcea");
        add("Vrancea");
    }};

    public static int[] getStatistics() {
        int[] r = new int[] { 0, 0, 0 };
        try {
            StringBuffer path = new StringBuffer(System.getProperty("user.dir"));
            path.append("\\src\\data\\statistics.txt");

            BufferedReader file = new BufferedReader(new FileReader(path.toString()));
            String[] split = file.readLine().split("~");
            for(int i = 0; i < r.length; i++)
                r[i] = Integer.parseInt(split[i]);
            
            file.close();
            return r;
        } catch(IOException ex) {
            ex.printStackTrace();
            return r;
        }
    }

    public static String getDateMonth(String date) {
        try {
            int m = Integer.parseInt(date.substring(3, 5));
            if(m == 1) return "Ianuarie";
            else if(m == 2) return "Februarie";
            else if(m == 3) return "Martie";
            else if(m == 4) return "Aprilie";
            else if(m == 5) return "Mai";
            else if(m == 6) return "Iunie";
            else if(m == 7) return "Iulie";
            else if(m == 8) return "August";
            else if(m == 9) return "Septembrie";
            else if(m == 10) return "Octombrie";
            else if(m == 11) return "Noiembrie";
            else if(m == 12) return "Decembrie";
            else return "null";
        } catch(NumberFormatException nfe) {
            nfe.printStackTrace();
            return "null";
        }
    }

    public static String dateToString(String date) {
        try {
            int d = Integer.parseInt(date.substring(0, 2));
            String m = getDateMonth(date).toLowerCase();
            int y = Integer.parseInt(date.substring(6));

            StringBuffer sb = new StringBuffer();
            sb.append(d);
            sb.append(" ");
            sb.append(m);
            sb.append(" ");
            sb.append(y);

            return sb.toString();
        } catch(NumberFormatException nfe) {
            nfe.printStackTrace();
            return null;
        }
    }
}
