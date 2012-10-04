package denoflionsx.Old.EE;

//package denoflionsx.plugins.EE;
//
//import java.util.HashMap;
//import denoflionsx.core.core;
//
//public class customEMCParser {
//
//    public HashMap<Integer, HashMap> Values = new HashMap();
//
//    public void parse(String line) {
//        try {
//            if (line.contains(".")) {
//                line = line.replace(".", ",");
//                core.print("Custom EMC syntax error detected. Attempting to correct...");
//            }
//            String[] split = line.split(",");
//            int id = Integer.valueOf(split[0]);
//            int dmg = Integer.valueOf(split[1]);
//            int value = Integer.valueOf(split[2]);
//            HashMap<Integer, Integer> temp = new HashMap();
//            if (this.Values.get(id) != null) {
//                temp = this.Values.get(id);
//            }
//            temp.put(dmg, value);
//            this.Values.put(id, temp);
//        } catch (Exception ex) {
//            core.print("Unrecoverable error on line: " + line);
//        }
//
//    }
//}
