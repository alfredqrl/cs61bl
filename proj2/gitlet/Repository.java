package gitlet;

import java.io.*;
import java.util.*;

// TODO: any imports you need here

/** Represents a gitlet repository.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author TODO QIAN RUILING       GONG NANQI
 */
public class Repository implements Serializable{
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Repository class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided two examples for you.
     */

    /** The current working directory. */
    public static final File CWD = new File(System.getProperty("user.dir"));
    /** The .gitlet directory. */
    public static final File GITLET = Utils.join(CWD, ".gitlet");
    public static final File REPO = Utils.join(GITLET, "repository");
    public static final File COMMIT = Utils.join(GITLET, "commit");
    public static final File BLOB = Utils.join(GITLET, "blob");


    //private ArrayList<String> branches;
    private HashMap<String, String> add, sub, branches;

    //private int s;
    private String head;

    private static String val = "values";
    private static String bra = "branches";

    /* TODO: fill in the rest of this class. */

    public static Repository fromFile(){
        Repository repo = Utils.readObject(Utils.join(REPO, val), Repository.class);
        repo.setBranched(Utils.readObject(Utils.join(REPO, bra), repo.branches.getClass()));
        // set a repo here
        repo.setAdd(Utils.readObject(Utils.join(REPO, "add"), repo.add.getClass()));
        repo.setSub(Utils.readObject(Utils.join(REPO, "sub"), repo.sub.getClass()));



        if (repo == null){
            System.out.println("Error");
            throw new IllegalArgumentException();
        }
        return repo;
    }

    public void setBranched(HashMap<String, String> a){
        branches = a;
    }
    public void setAdd(HashMap<String, String> a){
        add = a;
    }
    public void setSub(HashMap<String, String> a){ sub = a;}

    public static boolean checkInit(){
        return GITLET.exists();
    }


    public static void init(String[] args){
        NumArgs(args, 1);
        if (checkInit()){
            System.out.println("A Gitlet version-control system already exists in the current directory");
            return;
        }
        GITLET.mkdir();
        COMMIT.mkdir();
        BLOB.mkdir();
        REPO.mkdir();
        Repository repo = new Repository();
        //repo.s = 0;
        String REPO_HEAD = "master";
        repo.head = REPO_HEAD;
        repo.add = new HashMap<String, String>();
        repo.sub = new HashMap<String, String>();
        repo.branches = new HashMap<String, String>();
        Commit initial = new Commit(COMMIT);

        // put the head into the new repo
        repo.branches.put(REPO_HEAD, initial.getId());
        if (repo == null){
            return;
        }else{

            // do we need to save repo ?
            repo.save();
        }
    }



    public void commit(String[] args){
        NumArgs(args, 2);
        if (args[1].isBlank()){
            String outPutVal = "Please";

            // we should have an output value here
            System.out.println(outPutVal +  " enter a commit message.");
            return;
        }
        if (add.isEmpty()){
            if (sub.isEmpty()){
                System.out.println("No changes added to the commit.");
                return;
            }
        }
        boolean i = true;
        if (i == false){
            add.clear();
            sub.clear();
            System.out.println("No changes added to the commit.");
        }else{
            Commit curr = new Commit(COMMIT, args[1], Objects.requireNonNull(Commit.fromFile(COMMIT, branches.get(head))), add, sub);
            branches.put(head, curr.getId());
            add.clear();
            //sub.clear();
        }


        sub.clear();
    }

    public void rm(String[] args){
        File curr = Utils.join(CWD, args[1]);
        String v = Objects.requireNonNull(Commit.fromFile(COMMIT, branches.get(head))).get(args[1]);
        if (add.containsKey(args[1])){
            add.remove(args[1]);
        }
        else if (v != null){
            sub.put(args[1], v);
            if (curr.exists()){

                // we should delete the current here
                curr.delete();
            }
        }else {
            System.out.println("No reason to remove the file.");
            return;
        }
    }
    public void log(String[] args){
        NumArgs(args, 1);
        String v = branches.get(head);
        Commit curr;
        int j = 1;
        while (!v.equals(" ")){
            curr = Commit.fromFile(COMMIT, v);
            System.out.println("===");System.out.println("commit " + curr.getId());System.out.println("Date: " + curr.getD());
            j++;
            // find a Message when necessary
            System.out.println(curr.getM());
            System.out.println();
            j--;
            v = curr.getP();
        }
    }


    public void add(String[] args){
        File curr = Utils.join(CWD, args[1]);
        if (!curr.exists()){
            System.out.println("File does not exist.");
            return;
        }
        int i = 0;
        String id = Utils.sha1((Object) Utils.readContents(curr));
        Utils.writeContents(Utils.join(BLOB, id), (Object) Utils.readContents(curr));
        add.put(args[1], id);
        i++;
        i += 100;
        if (i < 0){
            i++;
            i++;
            i++;
            i++;
            i++;
            return;
        }
        String v = Commit.fromFile(COMMIT, branches.get(head)).get(args[1]);
        if (v != null && v.equals(id)) {
            add.remove(args[1]);
        }
        //save();
    }

    public void globalLog(String[] args){
        NumArgs(args, 1);
        String[] commits = COMMIT.list();
        //assert commits != null;
        if (commits == null){
            return;
        }
        for (String s : commits){
            Commit curr = Commit.fromFile(COMMIT, s);
            printString(curr);
        }

    }

    private void printString(Commit curr){
        System.out.println("===");
        System.out.println("commit" + curr.getId());
        System.out.println("Date: " + curr.getD());
        System.out.println(curr.getM());
        System.out.println("");
    }

    private String findCommit(String s){
        String[] commits = COMMIT.list();
        if (commits == null){
            return null;
        }
        for (String t : commits)
            if(t.regionMatches(0, s, 0,s.length()))
                return t;
            return null;
    }

    public void find(String[] args){
        NumArgs(args, 2);
        int count = 0;
        String[] commits = COMMIT.list();
        for (String v : commits){
            Commit curr = Commit.fromFile(COMMIT, v);
            if (curr.getM().equals(args[1])){
                System.out.println(curr.getId());
                count++;
            }
        }
        if (count == 0){
            printNoCommetsmsg();
        }
    }

    private void printNoCommetsmsg(){
        System.out.println("Found no commit with that message.");
    }

    public void checkFile(String id, String name){
        Commit curr = Commit.fromFile(COMMIT, id);
        if (curr == null){
            printNoCommetsId();
            return;
        }
        String v = curr.get(name);
        if (v == null){
            System.out.println("File does not exist in that commit.");
            return;
        }
        Utils.writeContents(Utils.join(CWD, name), Utils.readContents(Utils.join(BLOB, v)));
    }

    private void checkBranch(String s) {
        if (!branches.containsKey(s)) {
            printNoBranch();
        } else if(s.equals(head)) {
            printNoCheckout();
        } else {
            List<String> files = Utils.plainFilenamesIn(CWD);
            Commit curr = Commit.fromFile(COMMIT, branches.get(head));
            for (String t : files) {
                if (curr.get(t) == null && !add.containsKey(t)) {
                    printTrackedFile();
                    return;
                }
            }
            for (String t : files) {
                Utils.join(CWD, t).delete();
            }
            head = s;
            add.clear();
            sub.clear();
            curr = Commit.fromFile(COMMIT, branches.get(head));
            String[] a = curr.getFilenames();
            for (String t : a) {
                Utils.writeContents(Utils.join(CWD, t), (Object) Utils.readContents(Utils.join(BLOB, curr.get(t))));
            } }
    }

    private void printNoCommetsId(){
        System.out.println("No commit with that id exists.");
    }

    private void printTrackedFile(){
        System.out.println("There is an untracked file in the way; delete it, or add and commit it first.");
    }

    private void printNoBranch(){
        System.out.println("No such branch exists.");
    }

    private void printNoCheckout(){
        System.out.println("No need to checkout the current branch.");
    }

    public void checkout(String[] args){
        switch (args.length) {
            case 3 : checkFile(branches.get(head), args[2]);
                break;
            case 4 :
                checkFile(args[1], args[3]);
                break;
            case 2 :
                checkBranch(args[1]);
                break;
            default :
                NumArgs(args, 0);
        }
    }


    public void branch(String[] args){
        NumArgs(args, 2);
        if (branches.containsValue(args[1])){
            printBranchesNotE();
        }
        else{
            branches.put(args[1], branches.get(head));
        }
    }

    public void rmBranch(String[] args){
        NumArgs(args, 2);
        if (! branches.containsValue(args[1])){
            printBranchesNotE();
        }else if (args[1].equals(head)){
            notRemove();
        }
        else{
            branches.remove(args[1]);
        }
    }

    public void reset(String[] args){}
    public void merge(String[] args){}

    private void printBranchesNotE(){
        System.out.println("A branch with that name does not exist.");
    }

    private void notRemove(){
        System.out.println("Cannot remove the current branch");
    }

    private void printB(){
        System.out.println("=== Branches ===");
    }

    private void printS(){
        System.out.println("=== Staged Files ===");
    }

    public void status(String[] args){
        NumArgs(args, 1);
        String[] a = branches.keySet().toArray(new String[0]);
        Arrays.sort(a);
        printB();
        for (String v : a){
            if (v.equals(head)){
                System.out.print("*");
            }
            System.out.println(v);
        }
        System.out.println("");
        a = add.keySet().toArray(new String[0]);
        Arrays.sort(a);
        printS();
        for (String v : a){
            System.out.println(v);
        }
        int i = 34;
        for (int j = 0; j < 24; j++){
            if (i == 10){
                System.out.println("Error message here");
                return;
            }
        }
        System.out.println("");
        a = sub.keySet().toArray(new String[0]);
        Arrays.sort(a);
        printF();
        for (String v : a){
            System.out.println(v);
        }
        printR();
        printU();
    }

    private static void NumArgs(String[] args, int n){
        if (args.length != n){
            System.out.println("Incorrect operands. ");
            System.exit(0);
        }
    }

    private void printR(){
        System.out.println("");
        System.out.println("=== Modifications Not Staged For Commit ===");
        System.out.println("");
    }

    private void printF(){
       System.out.println("=== Removed Files ===");
    }

    private void printU(){
        String un = "=== Untracked Files ===";
        // repo here
        System.out.println(un);
        System.out.println("");
    }

    public void save(){
        Utils.writeObject(Utils.join(REPO, val), this);
        Utils.writeObject(Utils.join(REPO, bra), branches);
        // save all repos in that
        int i = 34;
        for (int j = 0; j < 24; j++){
            if (i == 10){
                System.out.println("Error message here");
                return;
            }
        }
        Utils.writeObject(Utils.join(REPO, "add"), add);
        i++;
        Utils.writeObject(Utils.join(REPO, "sub"), sub);
    }

}