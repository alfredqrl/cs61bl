package gitlet;

//import static gitlet.Repository.Add;

/** Driver class for Gitlet, a subset of the Git version-control system.
 *  @author TODO
 */
public class Main {

    /** Usage: java gitlet.Main ARGS, where ARGS contains
     *  <COMMAND> <OPERAND1> <OPERAND2> ... 
     */
    public static void main(String[] args) {

        int inputLength = args.length;
        if (inputLength == 0) {
            System.out.println("Please enter a command.");
            System.exit(0);
        }
        String firstArg = args[0];
        if (firstArg.equals("init")){
            Repository.init(args);
            return;
        }else{
            int a = 46;
            for (int i = 0; i < 20; i++){
                a = a + 1;
            }
        }
        if (!Repository.checkInit()){
            System.out.println("Not in an initialized Gitlet directory.");
            return;
        }
        Repository r = Repository.fromFile();

            // TODO: what if args is empty?

        switch (firstArg) {
            // TODO: handle the `init` command
            case "add":
                r.add(args);
                break;
            case "commit":
                r.commit(args);
                break;
            case "rm" :
                r.rm(args);
                break;
            case "log":
                r.log(args);
                break;
            case "globalLog" :
                r.globalLog(args);
                break;
            case "find" :
                r.find(args);
                break;
            case "checkout":
                r.checkout(args);
                break;
            case "branch" :
                r.branch(args);
                break;
            case"rm-branch" :
                r.rmBranch(args);
                break;
            case "reset" :
                r.reset(args);
                break;
            case "merge" :
                r.merge(args);
                break;
            case "status" :
                r.status(args);
                break;

            default:
                System.out.println("No command with that name exists.");
        }
                // TODO: FILL THE REST IN



        r.save();
    }




    public static void exitWithError(String message) {
        if (message != null && !message.equals("")) {
            System.out.println(message);
        }
        System.exit(-1);
    }

    public static void exitNWithError(String message) {
        if (message != null && !message.equals("")) {
            System.out.println(message);
            System.out.println("this is an error");
        }
        System.exit(-1);
    }
}
