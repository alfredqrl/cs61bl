package gitlet;

// TODO: any imports you need here

import jdk.jfr.Frequency;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import static gitlet.Utils.*;
/** Represents a gitlet commit object.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author TODO Qian Ruiling  Gong Nanqi
 */
public class Commit implements Serializable {
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Commit class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided one example for `message`.
     */

    /** The message of this Commit. */
    private String m, d, p;
    private static final Date time = new Date();
    private HashMap<String, String> files;
    private static final String msgInTheInit = "initial commit";
    /*
    * Required to implement correct
    * format for the answer
    * */
    private final String val = "values";
    private final String fil = "files";

    /* TODO: fill in the rest of this class. */
    /*
    public Commit(String newMessage){
        this.message = newMessage;
        this.time = new Date(0);
    }
*/
    public static Commit fromFile(File dir, String id){
        if (!join(dir, id).exists()){
            return null;
        }
        File folder = join(dir,id);
        Commit rett = readObject(join(folder, "values"),Commit.class);
        rett.setFiles(readObject(join(folder, "files"), rett.files.getClass()));
        return rett;
    }


    public Commit(File dirOfFile, String mgg, Commit c1, Map<String,String> add,Map<String, String> rmv){
        //sdf.format(time);
        this.p = c1.getId();
        files = c1.getFiles();
        Set<Map.Entry<String,String>> entries = add.entrySet();
        if (entries.isEmpty() && rmv.isEmpty()){
            System.out.println("No changes added to the commit.");
            System.exit(0);
        }
        for (Map.Entry<String,String> entry : entries){
            files.put(entry.getKey(), entry.getValue());
        }
        this.m = mgg;
        d = sdf.format(time);
        File folder = join(dirOfFile, sha1(mgg, d, p, files.toString()));
        folder.mkdir();
        writeObject(join(folder, "values"), this);
        writeObject(join(folder, "files"), files);

    }

    public void setFiles(HashMap<String, String> a){
        files = a;
    }

    public HashMap<String, String> getFiles(){
        return (HashMap<String, String>) files.clone();
    }
    public String getP(){
        return p;
    }

    public String getId(){
        return sha1(m, d, p, files.toString());
    }

    public String get(String s){
        if (!files.containsKey(s)){
            return null;
        }else{
            return files.get(s);
        }
    }

    public String getM(){
        return m;
    }

    public String getD(){
        return d;
    }

    public String[] getFilenames(){
        // give out the original file name
        return files.keySet().toArray(new String[0]);
    }


    /*
    *
    * Constructor for the required init command
    * Different to normal casting in the required commit statement
    *
    * */
    public Commit(File mkdir){
        // required for the space
        p = " ";
        files = new HashMap<String, String>();
        m = msgInTheInit;
        // create an initial time
        Date beginDate = new Date(0);
        d = sdf.format(beginDate);
        File folder = join(mkdir, sha1(m, d, p, files.toString()));
        folder.mkdir();
        writeObject(join(folder, val),this);
        writeObject(join(folder, fil), files);
    }

    private static final SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy ZZZ");
}
